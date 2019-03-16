package dml.sz0099.course.app.client.wrapper.profession;

import java.io.File;
import java.util.List;
import java.util.Map;

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

import dml.sz0099.course.app.biz.delegate.profession.ProfPhotoCoverDelegate;
import dml.sz0099.course.app.client.resolver.adaptor.media.CoverProfessionAdaptor;
import dml.sz0099.course.app.client.resolver.media.ImagePathUtil;
import dml.sz0099.course.app.client.resolver.media.PhotoUtil;
import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.entity.media.ImageRef;
import dml.sz0099.course.app.persist.entity.media.Imagebase;
import dml.sz0099.course.app.persist.entity.profession.Photo;
import dml.sz0099.course.app.persist.entity.profession.PhotoCover;
import dml.sz0099.course.app.persist.entity.profession.bo.PhotoCoverBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * PhotoCoverWrapperImpl,组件封装
 * @author bruce yang at 2018-08-29 21:12:56
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Service("profPhotoCoverWrapperImpl")
public class PhotoCoverWrapperImpl implements PhotoCoverWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PhotoCoverWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private ProfPhotoCoverDelegate photoCoverDelegate;
	
	@Autowired
	private CoverProfessionAdaptor coverProfessionAdaptor;
	
	/**
	 * 根据Id查询PhotoCover实体对象
	 * @param id
	 * @return
	 */
	@Override
	public PhotoCover findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		PhotoCover photoCover = photoCoverDelegate.findById(id);
		if(null != photoCover) {
			String description = photoCover.getDescription();
			if(StringUtils.isNotBlank(description)) {
				description= HtmlUtils.htmlUnescape(description);
				photoCover.setDescription(description);
			}
		}
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, photoCover);
		return photoCover;
	}
	
	public PhotoCover findById(Long id, boolean withPhoto) {
		return photoCoverDelegate.findById(id, withPhoto);
	}
	
	@Override
	public PhotoCover findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		PhotoCover photoCover = photoCoverDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, photoCover);
		return photoCover;
	}
	
	/**
	 * 根据IdList查询PhotoCover实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<PhotoCover> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<PhotoCover> photoCoverList = photoCoverDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  photoCoverList);
		return photoCoverList;
	}
	
	@Override
	public PhotoCover persistEntity(PhotoCover photoCover) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		PhotoCover entity = photoCoverDelegate.persistEntity(photoCover);
		Long id = photoCover.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PhotoCover mergeEntity(PhotoCover photoCover) {
		Long id = photoCover.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		PhotoCover entity = photoCoverDelegate.mergeEntity(photoCover);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PhotoCover saveOrUpdate(PhotoCover photoCover) {
		Long id = photoCover.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		PhotoCover entity = photoCoverDelegate.saveOrUpdate(photoCover);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PhotoCover> findPage(PhotoCover photoCover, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<PhotoCover> page = photoCoverDelegate.findPage(photoCover, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return photoCoverDelegate.existById(id);
	}


	public void deleteBySubIdAndMainId(Long subId, Long mainId ){		

		LOGGER.debug("-------wrapper>>>PhotoCoverWrapperImpl.deleteBySubIdAndMainId----------begin---------");
		photoCoverDelegate.deleteBySubIdAndMainId( subId,  mainId );
	}

	@Override
	public List<PhotoCover> findBySubIdListAndMainId(List<Long> idList, Long mainId) {
		return photoCoverDelegate.findBySubIdListAndMainId(idList, mainId);
	}

	@Override
	public void deleteBySubIdListAndMainId(List<Long> subIdList, Long mainId, Long userId, boolean cascade) {
		photoCoverDelegate.deleteBySubIdListAndMainId(subIdList, mainId,userId, cascade);
		
	}

	@Override
	public void deleteById(PhotoCover photoCover) {
		Long id = photoCover.getId();
		PhotoCover entity = findById(id,true);
		if(null != entity) {
			Photo photo = entity.getPhoto();
			if(null != photo) {
				String absolutePath = photo.getAbsolute();
				ImagePathUtil.deleteImage(absolutePath);
			}
		}
		photoCoverDelegate.deleteById(photoCover,true);
	}

	@Override
	public void deleteByIdList(PhotoCoverBo photoCover) {
		photoCoverDelegate.deleteByIdList(photoCover);
		
	}

	@Override
	public PhotoCover createPhotoCover(PhotoCover photoCover) {
		return photoCoverDelegate.createPhotoCover(photoCover);
	}

	@Override
	public List<PhotoCover> createPhotoCover(List<PhotoCover> photoCoverList) {
		return photoCoverDelegate.createPhotoCover(photoCoverList);
	}

	@Override
	public PhotoCover mergeForTitle(PhotoCover photoCover) {
		
		return photoCoverDelegate.mergeForTitle(photoCover);
	}

	@Override
	public List<PhotoCover> mergeListForTitle(List<PhotoCover> photoCoverList) {
		return photoCoverDelegate.mergeListForTitle(photoCoverList);
	}
	
	public List<PhotoCover> persistForPhoto(List<PhotoCover> photoCoverList){
		return photoCoverDelegate.persistForPhoto(photoCoverList);
	}

	@Override
	public Map<Long, List<PhotoCover>> findByMainIdListAndSubId(List<Long> mainIdList, Long subId) {
		
		return photoCoverDelegate.findByMainIdListAndSubId( mainIdList,  subId);
	}
	
	@Override
	public void deleteImageById(ImageRef ref) {
		Long id = ref.getId();
		PhotoCover entity = findById(id,true);
		
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
		photoCoverDelegate.deleteImageById(entity,true);
	}
	
	public PhotoCover changeImageById(ImageRef ref) {
		Long id = ref.getId();
		PhotoCover entity = findById(id,true);
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
		return photoCoverDelegate.addImageById(entity,true);
	}
	public PhotoCover fillAndConvert(ImageRef ref, PhotoCover entity) {
		
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
			String accessUrl = ImagePathUtil.buildAccessUrl(coverProfessionAdaptor.getAccessUrlMapping(),fullnameWithRelative);
			photo.setAccessUrl(accessUrl);
			base.setAccessUrl(accessUrl);
			
			
			entity.setType(ImageRef.TYPE_IMG.getValueInt());
			File dest = ImagePathUtil.persist2Disk(absolutePath, new File(fullnameWithAbsolute),file);
			//ref = coverProfessionAdaptor.convertToRef(ref,entity);
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
	public PhotoCover addImageById(ImageRef ref) {
		Long id = ref.getId();
		PhotoCover entity = findById(id,true);
		PhotoCover photoCover = null;
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
		return photoCoverDelegate.addImageById(entity,true);
	}
	
	public PhotoCover createPhotoCover(ImageRef ref) {
		Imagebase base = ref.getBase();
		if(null == base) {
			base=new Imagebase();
			ref.setBase(base);
		}
		base.setCreatedBy(ref.getCreatedBy());
		base.setUserId(ref.getUserId());
		base.setLastModifiedBy(ref.getLastModifiedBy());
		
		base.setStrategy(1);//设置单一生成策略
		String basePath = coverProfessionAdaptor.getBasePath();
		String basePathFolder = coverProfessionAdaptor.getBasePathFolder();
		String accessUrlMapping = coverProfessionAdaptor.getAccessUrlMapping();
		ImageExtend extend = coverProfessionAdaptor.config();
		ref = ImagePathUtil.fillRefThenGenerate(extend, ref, basePath, basePathFolder, accessUrlMapping);
		PhotoCover photoCover = coverProfessionAdaptor.convertFromRef(ref);
		photoCover = createPhotoCover(photoCover);
		return photoCover;
	}
	
	
	@Override
	public PhotoCover mergeRefForDescription(PhotoCover photoCover) {
		String description = photoCover.getDescription();
		if(StringUtils.isNotBlank(description)) {
			description=HtmlUtils.htmlEscape(description, "utf-8");
			photoCover.setDescription(description);
		}
		return photoCoverDelegate.mergeRefForDescription(photoCover);
	}
	
	public PhotoCover mergeRefForOrder(PhotoCover photoCover) {
		Long orderSeq = photoCover.getOrderSeq();
		Long id = photoCover.getId();
		if(null != orderSeq && null != id) {
			return photoCoverDelegate.mergeRefForOrder(photoCover);
		}
		return photoCover;
	}

	public Long countByMainId(Long mainId) {
		return photoCoverDelegate.countByMainId(mainId);
	}


}
