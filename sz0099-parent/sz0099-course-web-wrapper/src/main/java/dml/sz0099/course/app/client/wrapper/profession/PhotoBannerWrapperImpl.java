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

import dml.sz0099.course.app.biz.delegate.profession.ProfPhotoBannerDelegate;
import dml.sz0099.course.app.client.resolver.adaptor.media.BannerProfessionAdaptor;
import dml.sz0099.course.app.client.resolver.media.ImagePathUtil;
import dml.sz0099.course.app.client.resolver.media.PhotoUtil;
import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.entity.media.ImageRef;
import dml.sz0099.course.app.persist.entity.media.Imagebase;
import dml.sz0099.course.app.persist.entity.profession.Photo;
import dml.sz0099.course.app.persist.entity.profession.PhotoBanner;
import dml.sz0099.course.app.persist.entity.profession.bo.PhotoBannerBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * PhotoBannerWrapperImpl,组件封装
 * @author bruce yang at 2018-08-29 21:12:56
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Service("profPhotoBannerWrapperImpl")
public class PhotoBannerWrapperImpl implements PhotoBannerWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PhotoBannerWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private ProfPhotoBannerDelegate photoBannerDelegate;
	
	@Autowired
	private BannerProfessionAdaptor bannerProfessionAdaptor;
	
	/**
	 * 根据Id查询PhotoBanner实体对象
	 * @param id
	 * @return
	 */
	@Override
	public PhotoBanner findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		PhotoBanner photoBanner = photoBannerDelegate.findById(id);
		if(null != photoBanner) {
			String description = photoBanner.getDescription();
			if(StringUtils.isNotBlank(description)) {
				description= HtmlUtils.htmlUnescape(description);
				photoBanner.setDescription(description);
			}
		}
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, photoBanner);
		return photoBanner;
	}
	
	public PhotoBanner findById(Long id, boolean withPhoto) {
		return photoBannerDelegate.findById(id, withPhoto);
	}
	
	@Override
	public PhotoBanner findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		PhotoBanner photoBanner = photoBannerDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, photoBanner);
		return photoBanner;
	}
	
	/**
	 * 根据IdList查询PhotoBanner实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<PhotoBanner> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<PhotoBanner> photoBannerList = photoBannerDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  photoBannerList);
		return photoBannerList;
	}
	
	@Override
	public PhotoBanner persistEntity(PhotoBanner photoBanner) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		PhotoBanner entity = photoBannerDelegate.persistEntity(photoBanner);
		Long id = photoBanner.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PhotoBanner mergeEntity(PhotoBanner photoBanner) {
		Long id = photoBanner.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		PhotoBanner entity = photoBannerDelegate.mergeEntity(photoBanner);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PhotoBanner saveOrUpdate(PhotoBanner photoBanner) {
		Long id = photoBanner.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		PhotoBanner entity = photoBannerDelegate.saveOrUpdate(photoBanner);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PhotoBanner> findPage(PhotoBanner photoBanner, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<PhotoBanner> page = photoBannerDelegate.findPage(photoBanner, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return photoBannerDelegate.existById(id);
	}


	public void deleteBySubIdAndMainId(Long subId, Long mainId ){		

		LOGGER.debug("-------wrapper>>>PhotoBannerWrapperImpl.deleteBySubIdAndMainId----------begin---------");
		photoBannerDelegate.deleteBySubIdAndMainId( subId,  mainId );
	}

	@Override
	public List<PhotoBanner> findBySubIdListAndMainId(List<Long> idList, Long mainId) {
		return photoBannerDelegate.findBySubIdListAndMainId(idList, mainId);
	}

	@Override
	public void deleteBySubIdListAndMainId(List<Long> subIdList, Long mainId, Long userId, boolean cascade) {
		photoBannerDelegate.deleteBySubIdListAndMainId(subIdList, mainId,userId, cascade);
		
	}

	@Override
	public void deleteById(PhotoBanner photoBanner) {
		Long id = photoBanner.getId();
		PhotoBanner entity = findById(id,true);
		if(null != entity) {
			Photo photo = entity.getPhoto();
			if(null != photo) {
				String absolutePath = photo.getAbsolute();
				ImagePathUtil.deleteImage(absolutePath);
			}
		}
		photoBannerDelegate.deleteById(photoBanner);
	}

	@Override
	public void deleteByIdList(PhotoBannerBo photoBanner) {
		photoBannerDelegate.deleteByIdList(photoBanner);
		
	}

	@Override
	public PhotoBanner createPhotoBanner(PhotoBanner photoBanner) {
		return photoBannerDelegate.createPhotoBanner(photoBanner);
	}

	@Override
	public List<PhotoBanner> createPhotoBanner(List<PhotoBanner> photoBannerList) {
		return photoBannerDelegate.createPhotoBanner(photoBannerList);
	}

	@Override
	public PhotoBanner mergeForTitle(PhotoBanner photoBanner) {
		
		return photoBannerDelegate.mergeForTitle(photoBanner);
	}

	@Override
	public List<PhotoBanner> mergeListForTitle(List<PhotoBanner> photoBannerList) {
		return photoBannerDelegate.mergeListForTitle(photoBannerList);
	}
	
	public List<PhotoBanner> persistForPhoto(List<PhotoBanner> photoBannerList){
		return photoBannerDelegate.persistForPhoto(photoBannerList);
	}

	@Override
	public Map<Long, List<PhotoBanner>> findByMainIdListAndSubId(List<Long> mainIdList, Long subId) {
		
		return photoBannerDelegate.findByMainIdListAndSubId( mainIdList,  subId);
	}
	
	@Override
	public void deleteImageById(ImageRef ref) {
		Long id = ref.getId();
		PhotoBanner entity = findById(id,true);
		
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
		photoBannerDelegate.deleteImageById(entity,true);
	}
	
	public PhotoBanner changeImageById(ImageRef ref) {
		Long id = ref.getId();
		PhotoBanner entity = findById(id,true);
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
		return photoBannerDelegate.addImageById(entity,true);
	}
	public PhotoBanner fillAndConvert(ImageRef ref, PhotoBanner entity) {
		
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
			String accessUrl = ImagePathUtil.buildAccessUrl(bannerProfessionAdaptor.getAccessUrlMapping(),fullnameWithRelative);
			photo.setAccessUrl(accessUrl);
			base.setAccessUrl(accessUrl);
			
			
			entity.setType(ImageRef.TYPE_IMG.getValueInt());
			File dest = ImagePathUtil.persist2Disk(absolutePath, new File(fullnameWithAbsolute),file);
			//ref = coverArticleAdaptor.convertToRef(ref,entity);
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
	public PhotoBanner addImageById(ImageRef ref) {
		Long id = ref.getId();
		PhotoBanner entity = findById(id,true);
		PhotoBanner photoBanner = null;
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
		return photoBannerDelegate.addImageById(entity,true);
	}
	
	public PhotoBanner createPhotoBanner(ImageRef ref) {
		Imagebase base = ref.getBase();
		if(null == base) {
			base=new Imagebase();
			ref.setBase(base);
		}
		base.setCreatedBy(ref.getCreatedBy());
		base.setUserId(ref.getUserId());
		base.setLastModifiedBy(ref.getLastModifiedBy());
		
		base.setStrategy(1);//设置单一生成策略
		String basePath = bannerProfessionAdaptor.getBasePath();
		String basePathFolder = bannerProfessionAdaptor.getBasePathFolder();
		String accessUrlMapping = bannerProfessionAdaptor.getAccessUrlMapping();
		ImageExtend extend = bannerProfessionAdaptor.config();
		ref = ImagePathUtil.fillRefThenGenerate(extend, ref, basePath, basePathFolder, accessUrlMapping);
		PhotoBanner photoBanner = bannerProfessionAdaptor.convertFromRef(ref);
		photoBanner = createPhotoBanner(photoBanner);
		return photoBanner;
	}
	
	
	@Override
	public PhotoBanner mergeRefForDescription(PhotoBanner photoBanner) {
		String description = photoBanner.getDescription();
		if(StringUtils.isNotBlank(description)) {
			description=HtmlUtils.htmlEscape(description, "utf-8");
			photoBanner.setDescription(description);
		}
		return photoBannerDelegate.mergeRefForDescription(photoBanner);
	}
	
	public PhotoBanner mergeRefForOrder(PhotoBanner photoBanner) {
		Long orderSeq = photoBanner.getOrderSeq();
		Long id = photoBanner.getId();
		if(null != orderSeq && null != id) {
			return photoBannerDelegate.mergeRefForOrder(photoBanner);
		}
		return photoBanner;
	}

	public Long countByMainId(Long mainId) {
		return photoBannerDelegate.countByMainId(mainId);
	}


}
