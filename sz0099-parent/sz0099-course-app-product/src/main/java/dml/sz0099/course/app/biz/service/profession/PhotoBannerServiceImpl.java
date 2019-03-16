package dml.sz0099.course.app.biz.service.profession;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.jit4j.core.service.GenericServiceImpl;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.persist.dao.profession.PhotoBannerDao;
import dml.sz0099.course.app.persist.entity.profession.Photo;
import dml.sz0099.course.app.persist.entity.profession.PhotoBanner;
import dml.sz0099.course.app.persist.entity.profession.bo.PhotoBannerBo;


/**
 * 
 * @formatter:off
 * description: PhotoBannerServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service("profPhotoBannerServiceImpl")
public class PhotoBannerServiceImpl extends GenericServiceImpl<PhotoBanner, Long> implements PhotoBannerService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PhotoBannerServiceImpl.class);

	@Autowired
	private PhotoBannerDao photoBannerDao;
	
	@Autowired
	private PhotoService photoService;
	
	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = photoBannerDao;
	}

	/**
	 * 根据Id查询PhotoBanner实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public PhotoBanner findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		PhotoBanner photoBanner = photoBannerDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, photoBanner);
		return photoBanner;
	}
	
	public PhotoBanner findById(Long id, boolean withPhoto) {
		PhotoBanner entity = findById(id);
		if(null != entity && withPhoto  ) {
			Long photoId = entity.getPhotoId();
			Photo photo = photoService.findById(photoId);
			entity.setPhoto(photo);
		}
		return entity;
	}
	
	@Override
	public PhotoBanner findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		PhotoBanner photoBanner = photoBannerDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, photoBanner);
		return photoBanner;
	}

	/**
	 * 根据IdList查询PhotoBanner实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<PhotoBanner> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<PhotoBanner> photoBannerList = photoBannerDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", photoBannerList);
		return photoBannerList;
	}

	@Transactional
	@Override
	public PhotoBanner persistEntity(PhotoBanner photoBanner) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		
		Long order = photoBanner.getOrderSeq();
		
		PhotoBanner entity = save(photoBanner);
		Long id = photoBanner.getId();
		if(order==null) {
			entity.setOrderSeq(entity.getAid());
			save(entity);
		}
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(PhotoBanner.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public PhotoBanner mergeEntity(PhotoBanner photoBanner) {
		Long id = photoBanner.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		PhotoBanner entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(photoBanner.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(PhotoBanner.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public PhotoBanner saveOrUpdate(PhotoBanner photoBanner) {
		Long id = photoBanner.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		PhotoBanner entity = null;
		if(null != id) {
			entity = mergeEntity(photoBanner);
		}else {
			entity = persistEntity(photoBanner);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PhotoBanner> findPage(PhotoBanner photoBanner, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<PhotoBanner> page = photoBannerDao.findPage(photoBanner, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return photoBannerDao.existById(id);
	}

	@Override
	public List<PhotoBanner> findByMainId(Long mainId) {
		List<PhotoBanner> content = photoBannerDao.findByMainId(mainId);
		return content;
	}
	
	public List<PhotoBanner> findByMainId(Long mainId, Long subId) {
		List<PhotoBanner> content = photoBannerDao.findByMainIdAndSubId(mainId, subId);
		return content;
	}
	
	@Transactional
	public void deleteImage(PhotoBanner photoBanner) {
		
		deleteById(photoBanner, true);
		
	}
	
	@Transactional
	public void deleteBySubIdAndMainId(Long subId, Long mainId ){		

		LOGGER.debug("-------service>>>PhotoBannerServiceImpl.deleteBySubIdAndMainId----------begin---------");
		//findById(id)
		photoBannerDao.deleteBySubIdAndMainId( subId,  mainId );

	}
	
	@Transactional
	public void deleteById(PhotoBanner photoBanner) {
		deleteById(photoBanner, true);
	}
	
	@Transactional
	public void deleteById(PhotoBanner photoBanner, boolean cascade) {
		Long id = photoBanner.getId();
		PhotoBanner entity = findById(id);
		if(null != entity) {
			Long userIdEntity = entity.getUserId();
			Long userId = photoBanner.getUserId();
			if(userIdEntity.equals(userId)) {
				Long photoId = entity.getPhotoId();
				Photo ep = photoService.findById(photoId);
				if(null != ep) {
					if(cascade) {
						//硬删除
						photoService.delete(ep);
					}else {
						//软删除
						ep.setDeleted(true);
						ep.setLastModifiedBy(photoBanner.getLastModifiedBy());
						ep.setLastModifiedDate(new DateTime());
						photoService.save(ep);
					}
				}
				delete(entity);
			}
		}
	}
	
	@Transactional
	public void deleteImageById(PhotoBanner photoBanner, boolean cascade) {
		Long id = photoBanner.getId();
		PhotoBanner entity = findById(id);
		if(null != entity) {
			Long userIdEntity = entity.getUserId();
			Long userId = photoBanner.getUserId();
			if(userIdEntity.equals(userId)) {
				Long photoId = entity.getPhotoId();
				Photo ep = photoService.findById(photoId);
				DateTime dateTime = new DateTime();
				if(cascade) {
					ep.setSize(null);
					ep.setOriginal(null);
					ep.setHeight(null);
					ep.setWidth(null);
					ep.setSuffix(null);
					ep.setLastModifiedBy(photoBanner.getLastModifiedBy());
					ep.setLastModifiedDate(dateTime);
					photoService.save(ep);
				}
				entity.setLastModifiedBy(photoBanner.getLastModifiedBy());
				entity.setLastModifiedDate(dateTime);
				entity.setType(photoBanner.getType());
				save(entity);
			}
		}
	}
	@Transactional
	public PhotoBanner addImageById(PhotoBanner photoBanner, boolean cascade) {
		Long id = photoBanner.getId();
		PhotoBanner entity = findById(id);
		if(null != entity) {
			Long userIdEntity = entity.getUserId();
			Long userId = photoBanner.getUserId();
			if(userIdEntity.equals(userId)) {
				Photo photo = photoBanner.getPhoto();
				Long photoId = entity.getPhotoId();
				Photo ep = photoService.findById(photoId);
				DateTime dateTime = new DateTime();
				if(cascade) {
					ep.setSize(photo.getSize());
					ep.setOriginal(photo.getOriginal());
					ep.setHeight(photo.getHeight());
					ep.setWidth(photo.getWidth());
					ep.setSuffix(photo.getSuffix());
					ep.setLastModifiedBy(photoBanner.getLastModifiedBy());
					ep.setLastModifiedDate(dateTime);
					ep.setFilename(photo.getFilename());
					ep.setAccessUrl(photo.getAccessUrl());
					ep.setFullurl(photo.getFullurl());
					ep.setViewUrl(photo.getViewUrl());
					ep=photoService.save(ep);
				}
				entity.setWidth(photoBanner.getWidth());
				entity.setLastModifiedBy(photoBanner.getLastModifiedBy());
				entity.setLastModifiedDate(dateTime);
				entity.setType(photoBanner.getType());
				entity.setFullurl(photoBanner.getFullurl());
				entity.setExpectedUrl(photoBanner.getExpectedUrl());
				entity.setExpectedW(photoBanner.getExpectedW());
				save(entity);
				entity.setPhoto(ep);
			}
		}
		photoBanner.setSuccess(PhotoBanner.SUCCESS_YES);
		return photoBanner;
	}

	@Override
	public List<PhotoBanner> findBySubIdListAndMainId(List<Long> idList, Long mainId) {
		List<PhotoBanner> content = photoBannerDao.findBySubIdListAndMainId(idList, mainId);
		return content;
	}
	
	@Transactional
	@Override
	public void deleteBySubIdListAndMainId(List<Long> subIdList, Long mainId, Long userId, boolean cascade) {
		
		if(cascade) {
			List<PhotoBanner> paragraphList = findBySubIdListAndMainId(subIdList, mainId);
			//获取图片id
			List<Long> photoIdList = new ArrayList<>();
			if(null != paragraphList) {
				for(PhotoBanner p : paragraphList) {
					Long photoId = p.getPhotoId();
					if(null != photoId) {
						photoIdList.add(photoId);
					}
				}
			}
			//删除图片
			if(!photoIdList.isEmpty()) {
				photoService.deleteByIdListAndUserId(photoIdList, userId);
			}
		}
		//解除banner图片与文章关联
		photoBannerDao.deleteBySubIdListAndMainId(subIdList, mainId);
	}

	@Transactional
	@Override
	public void deleteByIdList(PhotoBannerBo photoBanner) {
		List<Long> idList = photoBanner.getIdList();
		List<PhotoBanner> entityList = findByIdList(idList);
		if(null != entityList) {
			for(PhotoBanner entity : entityList) {
				if(null != entity) {
					Long mainIdEntity = entity.getMainId();
					Long mainId = photoBanner.getMainId();
					if(mainIdEntity.equals(mainId)) {
						delete(entity);
					}
				}
			}
		}
		
	}
	
	@Transactional
	public PhotoBanner createPhotoBanner(PhotoBanner photoBanner) {
		//单张图片
		Long subId = photoBanner.getSubId();
		Long mainId = photoBanner.getMainId();//关联图片所属主体
		
		Photo photo = photoBanner.getPhoto();
		if(null != photo && null != subId && null != mainId)  {
			photoBanner.setWidth(photo.getWidth());
			photoBanner.setFullurl(photo.getFullurl());
			photoBanner.setPhotoId(photo.getId());
			
			photoBanner = persistEntity(photoBanner);
			photo.setOrderSeq(photoBanner.getOrderSeq());
			photo = photoService.persistEntity(photo);
			photoBanner.setPhoto(photo);
		}
		
		return photoBanner;
	}
	
	@Transactional
	public List<PhotoBanner> createPhotoBanner(List<PhotoBanner> photoBannerList) {
		//为段落添加图片，多张图片
		//1.保存图片记录 XX 图片记录应在wrapper层先做保存，上传完成同时保存记录
		//2.建立关联，此处仅建立关联即可
		if(null != photoBannerList && photoBannerList.size()>0) {
			List<PhotoBanner> content = new ArrayList<>(photoBannerList.size());
			for(PhotoBanner p : photoBannerList) {
				content.add(createPhotoBanner(p));
			}
			return content;
		}
		
		return null;
	}

	@Transactional
	@Override
	public List<PhotoBanner> persistForPhoto(List<PhotoBanner> photoList) {
		List<PhotoBanner> entityList = null;
		if(null != photoList && !photoList.isEmpty()) {
			entityList = new ArrayList<>(photoList.size());
			for(PhotoBanner pg : photoList) {
				Photo p = pg.getPhoto();
				p = photoService.save(p);
				pg = save(pg);
				pg.setPhoto(p);
				entityList.add(pg);
			}
		}
		return entityList;
	}
	@Transactional
	@Override
	public PhotoBanner mergeForTitle(PhotoBanner photoBanner) {
		PhotoBanner entity = photoBanner;
		if(null != photoBanner) {
			Long id = photoBanner.getId();
			entity = findById(id);
			DateTime lastModifiedDate = new DateTime();
			if(null != entity) {
				entity.setOrderSeq(photoBanner.getOrderSeq());
				entity.setTitle(photoBanner.getTitle());
				//entity.setName(photoBanner.getName());
				entity.setDescription(photoBanner.getDescription());
				
				entity.setLastModifiedBy(photoBanner.getLastModifiedBy());
				entity.setLastModifiedDate(lastModifiedDate);
				
			}
			
			Photo photo = photoBanner.getPhoto();
			photo.setLastModifiedDate(lastModifiedDate);
			photo=photoService.mergeForTitle(photo);
			entity.setPhoto(photo);
			entity.setSuccess(PhotoBanner.SUCCESS_YES);
		}
		return entity;
	}

	@Transactional
	@Override
	public List<PhotoBanner> mergeListForTitle(List<PhotoBanner> photoBannerList) {
		
		List<PhotoBanner> entityList = photoBannerList;
		if(null != photoBannerList && !photoBannerList.isEmpty()) {
			entityList = new ArrayList<>(photoBannerList.size());
			for(PhotoBanner photo : photoBannerList) {
				entityList.add(mergeForTitle(photo));
			}
		}
		
		return entityList;
	}

	@Override
	public List<PhotoBanner> findByMainIdAndSubId(Long mainId, Long subId) {
		
		return photoBannerDao.findByMainIdAndSubId(mainId, subId);
	}
	
	public Map<Long, List<PhotoBanner>> findByMainIdListAndSubId(List<Long> mainIdList, Long subId) {
		List<PhotoBanner> content = photoBannerDao.findByMainIdListAndSubId( mainIdList,  subId);
		Map<Long, List<PhotoBanner>>  contentMap = null;
		if(null != content && !content.isEmpty()) {
			contentMap = new HashMap<>(content.size());
			for(PhotoBanner pc : content) {
				Long mainId = pc.getMainId();
				List<PhotoBanner> clist = contentMap.get(mainId);
				if(null == clist) {
					clist = new ArrayList<>();
					contentMap.put(mainId, clist);
				}
				clist.add(pc);
			}
		}
		return contentMap;
	}
	
	
	@Transactional
	@Override
	public PhotoBanner mergeRefForDescription(PhotoBanner photoBanner) {
		
		PhotoBanner entity = photoBanner;
		if(null != photoBanner) {
			Long id = photoBanner.getId();
			entity = findById(id);
			DateTime lastModifiedDate = new DateTime();
			if(null != entity) {
				//entity.setOrderSeq(photoBanner.getOrderSeq());
				//entity.setName(photoBanner.getName());
				entity.setDescription(photoBanner.getDescription());
				entity.setLastModifiedBy(photoBanner.getLastModifiedBy());
				entity.setLastModifiedDate(lastModifiedDate);
				save(entity);
			}
			
			entity.setSuccess(PhotoBanner.SUCCESS_YES);
		}
		return entity;
	}
	
	@Transactional
	public PhotoBanner mergeRefForOrder(PhotoBanner photoBanner) {
		PhotoBanner entity = photoBanner;
		if (null != photoBanner) {
			Long id = photoBanner.getId();
			entity = findById(id);
			DateTime lastModifiedDate = new DateTime();
			if (null != entity) {
				 entity.setOrderSeq(photoBanner.getOrderSeq());
				// entity.setName(photoBanner.getName());
				//entity.setDescription(photoBanner.getDescription());
				entity.setLastModifiedBy(photoBanner.getLastModifiedBy());
				entity.setLastModifiedDate(lastModifiedDate);
				save(entity);
			}
			entity.setSuccess(PhotoBanner.SUCCESS_YES);
		}
		return entity;
	}
	
	public Long countByMainId(Long mainId) {
		return photoBannerDao.countByMainId(mainId);
	}

}
