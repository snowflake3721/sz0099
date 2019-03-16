package dml.sz0099.course.app.client.wrapper.article;

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

import dml.sz0099.course.app.biz.delegate.article.ArticlePhotoParagDelegate;
import dml.sz0099.course.app.client.resolver.adaptor.media.ParagArticleAdaptor;
import dml.sz0099.course.app.client.resolver.media.ImagePathUtil;
import dml.sz0099.course.app.client.resolver.media.PhotoUtil;
import dml.sz0099.course.app.persist.entity.article.Photo;
import dml.sz0099.course.app.persist.entity.article.PhotoParag;
import dml.sz0099.course.app.persist.entity.article.bo.PhotoParagBo;
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
@Service("articlePhotoParagWrapperImpl")
public class PhotoParagWrapperImpl implements PhotoParagWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PhotoParagWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private ArticlePhotoParagDelegate photoParagDelegate;
	
	@Autowired
	private ParagArticleAdaptor paragArticleAdaptor;
	
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
			Photo photo = entity.getPhoto();
			if(null != photo) {
				String absolutePath = photo.getAbsolute();
				ImagePathUtil.deleteImage(absolutePath);
			}
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
			Photo photo = entity.getPhoto();
			if(null != photo) {
				String absolutePath = photo.getAbsolute();
				ImagePathUtil.deleteImage(absolutePath);
				entity.setType(ImageRef.TYPE_TEXT.getValueInt());
			}
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
				Photo photo = entity.getPhoto();
				if(null != photo) {
					String absolutePath = photo.getAbsolute();
					ImagePathUtil.deleteImage(absolutePath);
					entity.setType(ImageRef.TYPE_TEXT.getValueInt());
				}
				//再生成新图
				fillAndConvert(ref, entity);
			}
		}
		return photoParagDelegate.addImageById(entity,true);
	}
	public PhotoParag fillAndConvert(ImageRef ref, PhotoParag entity) {
		
		Imagebase base = ref.getBase();
		MultipartFile file = ref.getFile();
		Photo photo = entity.getPhoto();
		Long id = entity.getId();
		
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
			Long newFilename = id+entity.getVersion();//id+版本号
			String filename=String.valueOf(newFilename);//文件名要更换，消除缓存影响
			String fullnameWithAbsolute = ImagePathUtil.buildFullname(absolutePath, filename, suffix);
			String fullnameWithRelative = ImagePathUtil.buildFullname(relativePath, filename, suffix);
			String accessUrl = ImagePathUtil.buildAccessUrl(paragArticleAdaptor.getAccessUrlMapping(),fullnameWithRelative);
			photo.setAccessUrl(accessUrl);
			base.setAccessUrl(accessUrl);
			
			
			entity.setType(ImageRef.TYPE_IMG.getValueInt());
			File dest = ImagePathUtil.persist2Disk(absolutePath, new File(fullnameWithAbsolute),file);
			//ref = paragArticleAdaptor.convertToRef(ref,entity);
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
		String basePath = paragArticleAdaptor.getBasePath();
		String basePathFolder = paragArticleAdaptor.getBasePathFolder();
		String accessUrlMapping = paragArticleAdaptor.getAccessUrlMapping();
		ImageExtend extend = paragArticleAdaptor.config();
		ref = ImagePathUtil.fillRefThenGenerate(extend, ref, basePath, basePathFolder, accessUrlMapping);
		PhotoParag photoParag = paragArticleAdaptor.convertFromRef(ref);
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


}
