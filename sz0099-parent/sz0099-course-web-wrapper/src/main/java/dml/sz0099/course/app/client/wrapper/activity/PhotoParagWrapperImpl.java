package dml.sz0099.course.app.client.wrapper.activity;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import dml.sz0099.course.app.biz.delegate.activity.ActivityPhotoParagDelegate;
import dml.sz0099.course.app.client.resolver.adaptor.media.ParagActivityAdaptor;
import dml.sz0099.course.app.client.resolver.media.ImagePathUtil;
import dml.sz0099.course.app.client.resolver.media.PhotoUtil;
import dml.sz0099.course.app.module.define.CoeActivityRespCode;
import dml.sz0099.course.app.persist.entity.activity.Photo;
import dml.sz0099.course.app.persist.entity.activity.PhotoParag;
import dml.sz0099.course.app.persist.entity.activity.bo.PhotoParagBo;
import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.entity.media.ImageRef;
import dml.sz0099.course.app.persist.entity.media.Imagebase;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * PhotoParagWrapperImpl,组件封装
 * @author bruce yang at 2018-08-29 21:12:56
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Service("activityPhotoParagWrapperImpl")
public class PhotoParagWrapperImpl implements PhotoParagWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PhotoParagWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private ActivityPhotoParagDelegate photoParagDelegate;
	
	@Autowired
	private ParagActivityAdaptor paragActivityAdaptor;
	
	@Autowired
	private PhotoWrapper photoWrapper;
	
	/**
	 * 根据Id查询PhotoParag实体对象
	 * @param id
	 * @return
	 */
	@Override
	public PhotoParag findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		PhotoParag photoParag = photoParagDelegate.findById(id);
		if(null != photoParag) {
			String description = photoParag.getDescription();
			if(StringUtils.isNotBlank(description)) {
				description=StringEscapeUtils.unescapeJava(description);
				description= HtmlUtils.htmlUnescape(description);
				photoParag.setDescription(description);
			}
		}
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, photoParag);
		return photoParag;
	}
	
	public PhotoParag findById(Long id, boolean withPhoto) {
		return photoParagDelegate.findById(id, withPhoto);
	}
	
	@Override
	public PhotoParag findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		PhotoParag photoParag = photoParagDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, photoParag);
		return photoParag;
	}
	
	/**
	 * 根据IdList查询PhotoParag实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<PhotoParag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<PhotoParag> photoParagList = photoParagDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  photoParagList);
		return photoParagList;
	}
	
	@Override
	public PhotoParag persistEntity(PhotoParag photoParag) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		PhotoParag entity = photoParagDelegate.persistEntity(photoParag);
		Long id = photoParag.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PhotoParag mergeEntity(PhotoParag photoParag) {
		Long id = photoParag.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		PhotoParag entity = photoParagDelegate.mergeEntity(photoParag);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PhotoParag saveOrUpdate(PhotoParag photoParag) {
		Long id = photoParag.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		PhotoParag entity = photoParagDelegate.saveOrUpdate(photoParag);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PhotoParag> findPage(PhotoParag photoParag, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<PhotoParag> page = photoParagDelegate.findPage(photoParag, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return photoParagDelegate.existById(id);
	}


	public void deleteByParagIdAndUserId(Long paragId, Long userId ){		

		LOGGER.debug("-------wrapper>>>PhotoParagWrapperImpl.deleteByParagIdAndUserId----------begin---------");
		photoParagDelegate.deleteByParagIdAndUserId( paragId,  userId );
	}

	@Override
	public List<PhotoParag> findByParagIdListAndUserId(List<Long> idList, Long userId) {
		return photoParagDelegate.findByParagIdListAndUserId(idList, userId);
	}

	@Override
	public void deleteByParagIdListAndUserId(List<Long> paragIdList, Long userId, boolean cascade) {
		photoParagDelegate.deleteByParagIdListAndUserId(paragIdList, userId,cascade);
		
	}

	@Override
	public void deleteById(PhotoParag photoParag) {
		Long id = photoParag.getId();
		PhotoParag entity = findById(id,true);
		if(null != entity) {
			deleteImage(entity);
			/*Photo photo = entity.getPhoto();
			if(null != photo) {
				String absolutePath = photo.getAbsolute();
				ImagePathUtil.deleteImage(absolutePath);
			}*/
		}
		photoParagDelegate.deleteById(photoParag,true);
	}
	
	@Override
	public void deleteImageById(ImageRef ref) {
		Long id = ref.getId();
		PhotoParag entity = findById(id,true);
		
		if(null != entity) {
			entity.setUserId(ref.getUserId());
			entity.setLastModifiedBy(ref.getLastModifiedBy());
			//检测是否有引用，如果有，则不能删除图片，仅可删除记录 TODO
			deleteImage(entity);
		}
		photoParagDelegate.deleteImageById(entity,true);
	}

	@Override
	public void deleteByIdList(PhotoParagBo photoParag) {
		photoParagDelegate.deleteByIdList(photoParag);
	}
	
	public PhotoParag changeImageById(ImageRef ref) {
		Long id = ref.getId();
		PhotoParag entity = findById(id,true);
		if(null != entity) {
			Imagebase base = ref.getBase();
			if(null == base) {
				base = new Imagebase();
				ref.setBase(base);
			}
			MultipartFile file = ref.getFile();
			if(null != file) {
				//先删除原图
				entity.setUserId(ref.getUserId());
				entity.setLastModifiedBy(ref.getLastModifiedBy());
				
				deleteImage(entity);
				//再生成新图
				fillAndConvert(ref, entity);
			}
		}
		return photoParagDelegate.addImageById(entity,true);
	}

	/**
	 * @param id
	 * @param entity
	 * @param tpl
	 * @param photo
	 */
	private void deleteImage(PhotoParag entity) {
		Photo photo = entity.getPhoto();
		Integer tpl=entity.getTemplate();
		if(null != photo) {
			//检测是否有引用，如果有，则不能删除图片，仅可删除记录的路径 TODO
			Long photoId=photo.getId();
			String filename=photo.getFilename();
			Long num=photoWrapper.countNotSelfByFilename(photoId, filename);
			if(num<1) {
				//无引用，可直接删除
				String absolutePath = photo.getAbsolute();
				ImagePathUtil.deleteImage(absolutePath);
			}
			entity.setType(ImageRef.TYPE_TEXT.getValueInt());
		}
	}
	public PhotoParag fillAndConvert(ImageRef ref, PhotoParag entity) {
		
		Imagebase base = ref.getBase();
		MultipartFile file = ref.getFile();
		Photo photo = entity.getPhoto();
		
		String originalFilename = base.getOriginal();
		if(StringUtils.isBlank(originalFilename)) {
			originalFilename = file.getOriginalFilename();
		}
		base.setSize(file.getSize());
		base.setContentType(file.getContentType());
		base.setOriginal(originalFilename);
		String suffix = ImagePathUtil.splitForSuffix(originalFilename);
		base.setSuffix(suffix);
		
		if(null != photo) {
			String absolutePath = photo.getAbsolute();
			String relativePath = photo.getRelative();
			Long newFilename = ImagePathUtil.generateFilename();//
			String filename=String.valueOf(newFilename);//文件名要更换，消除缓存影响
			String fullnameWithAbsolute = ImagePathUtil.buildFullname(absolutePath, filename, suffix);
			String fullnameWithRelative = ImagePathUtil.buildFullname(relativePath, filename, suffix);
			String accessUrl = ImagePathUtil.buildAccessUrl(paragActivityAdaptor.getAccessUrlMapping(),fullnameWithRelative);
			photo.setAccessUrl(accessUrl);
			base.setAccessUrl(accessUrl);
			
			
			entity.setType(ImageRef.TYPE_IMG.getValueInt());
			File dest = ImagePathUtil.persist2Disk(absolutePath, new File(fullnameWithAbsolute),file);
			//ref = paragActivityAdaptor.convertToRef(ref,entity);
			base.setFile(dest);
			base.setAbsolute(absolutePath);
			base.setFilename(filename);
			photo.setFilename(filename);
			base.setStrategy(1);//设置单一生成策略
			photo.setStrategy(1);//设置单一生成策略
			photo.setContentType(file.getContentType());
			photo.setSize(file.getSize());
			photo.setContentType(file.getContentType());
			photo.setOriginal(originalFilename);
			photo.setSuffix(suffix);
			Integer refnum=photo.getRefnum();
			if(refnum==null) {
				refnum=1;
			}
			photo.setRefnum(refnum);
			
			ref=ImagePathUtil.generateImage(ref);
			
			photo.setViewUrl(base.getViewUrl());
			photo.setAccessUrl(base.getAccessUrl());
			photo.setWidth(base.getWidth());
			photo.setHeight(base.getHeight());
			photo.setLastModifiedBy(ref.getLastModifiedBy());
			entity.setFullurl(PhotoUtil.getFullUrl(photo.getDomain(), base.getViewUrl()));
			entity.setExpectedUrl(ref.getExpectedUrl());
			entity.setExpectedW(ref.getExpectedW());
			
			entity.setWidth(ref.getWidth());
			entity.setLastModifiedBy(ref.getLastModifiedBy());
		}
		return entity;
	}
	
	
	
	
	@Override
	public PhotoParag addImageById(ImageRef ref) {
		Long id = ref.getId();
		PhotoParag entity = findById(id,true);
		PhotoParag photoParag = null;
		if(null != entity) {
			Imagebase base = ref.getBase();
			if(null == base) {
				base = new Imagebase();
				ref.setBase(base);
			}
			MultipartFile file = ref.getFile();
			if(null != file) {
				fillAndConvert( ref,  entity);
			}
		}
		return photoParagDelegate.addImageById(entity,true);
	}
	
	public PhotoParag createPhotoParag(ImageRef ref) {
		Imagebase base = ref.getBase();
		if(null == base) {
			base=new Imagebase();
			ref.setBase(base);
		}
		base.setCreatedBy(ref.getCreatedBy());
		base.setUserId(ref.getUserId());
		base.setLastModifiedBy(ref.getLastModifiedBy());
		
		base.setStrategy(1);//设置单一生成策略
		String basePath = paragActivityAdaptor.getBasePath();
		String basePathFolder = paragActivityAdaptor.getBasePathFolder();
		String accessUrlMapping = paragActivityAdaptor.getAccessUrlMapping();
		ImageExtend extend = paragActivityAdaptor.config(ref.getUserId());
		ref = ImagePathUtil.fillRefThenGenerate(extend, ref, basePath, basePathFolder, accessUrlMapping);
		PhotoParag photoParag = paragActivityAdaptor.convertFromRef(ref);
		photoParag = createPhotoParag(photoParag);
		return photoParag;
	}

	@Override
	public PhotoParag createPhotoParag(PhotoParag photoParag) {
		return photoParagDelegate.createPhotoParag(photoParag);
	}

	@Override
	public List<PhotoParag> createPhotoParag(List<PhotoParag> photoParagList) {
		return photoParagDelegate.createPhotoParag(photoParagList);
	}

	@Override
	public PhotoParag mergeForTitle(PhotoParag photoParag) {
		
		return photoParagDelegate.mergeForTitle(photoParag);
	}
	
	@Override
	public PhotoParag mergeRefForDescription(PhotoParag photoParag) {
		String description = photoParag.getDescription();
		if(StringUtils.isNotBlank(description)) {
			description=HtmlUtils.htmlEscape(description, "utf-8");
			description=StringEscapeUtils.escapeJava(description);
			photoParag.setDescription(description);
		}
		return photoParagDelegate.mergeRefForDescription(photoParag);
	}
	
	public PhotoParag mergeRefForOrder(PhotoParag photoParag) {
		Long orderSeq = photoParag.getOrderSeq();
		Long id = photoParag.getId();
		if(null != orderSeq && null != id) {
			return photoParagDelegate.mergeRefForOrder(photoParag);
		}
		return photoParag;
	}
	

	@Override
	public List<PhotoParag> mergeListForTitle(List<PhotoParag> photoParagList) {
		return photoParagDelegate.mergeListForTitle(photoParagList);
	}
	
	public Long countByMainId(Long mainId) {
		return photoParagDelegate.countByMainId(mainId);
	}


	public void mergeTemplateById(PhotoParag photoParag) {
		
		photoParagDelegate.mergeTemplateById(photoParag);
	}
	public void mergeTemplateById(ImageRef ref) {
		PhotoParag photoParag = new PhotoParag();
		photoParag.setId(ref.getId());
		photoParag.setLastModifiedBy(ref.getLastModifiedBy());
		Integer template = ref.getTemplate();
		photoParag.setTemplate(template);
		mergeTemplateById(photoParag);
		ref.setSuccess(ImageRef.SUCCESS_YES);
		if(template>0) {
			ref.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_PARAGRAGH_PHOTO_TEMPLATE_MERGE_SUCESS);
			ref.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_PARAGRAGH_PHOTO_TEMPLATE_MERGE_SUCESS);
		}else {
			ref.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_PARAGRAGH_PHOTO_TEMPLATE_CANCEL_SUCESS);
			ref.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_PARAGRAGH_PHOTO_TEMPLATE_CANCEL_SUCESS);
		}
	}

	@Override
	public Long countNotSelfByTemplateId(Long templateId) {
		return photoParagDelegate.countNotSelfByTemplateId(templateId);
	}
}
