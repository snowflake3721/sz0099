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

import dml.sz0099.course.app.persist.dao.profession.PhotoCoverDao;
import dml.sz0099.course.app.persist.entity.profession.Photo;
import dml.sz0099.course.app.persist.entity.profession.PhotoCover;
import dml.sz0099.course.app.persist.entity.profession.bo.PhotoCoverBo;


/**
 * 
 * @formatter:off
 * description: PhotoCoverServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service("profPhotoCoverServiceImpl")
public class PhotoCoverServiceImpl extends GenericServiceImpl<PhotoCover, Long> implements PhotoCoverService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PhotoCoverServiceImpl.class);

	@Autowired
	private PhotoCoverDao photoCoverDao;
	
	@Autowired
	private PhotoService photoService;
	
	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = photoCoverDao;
	}

	/**
	 * 根据Id查询PhotoCover实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public PhotoCover findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		PhotoCover photoCover = photoCoverDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, photoCover);
		return photoCover;
	}
	
	public PhotoCover findById(Long id, boolean withPhoto) {
		PhotoCover entity = findById(id);
		if(null != entity && withPhoto  ) {
			Long photoId = entity.getPhotoId();
			Photo photo = photoService.findById(photoId);
			entity.setPhoto(photo);
		}
		return entity;
	}
	
	@Override
	public PhotoCover findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		PhotoCover photoCover = photoCoverDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, photoCover);
		return photoCover;
	}

	/**
	 * 根据IdList查询PhotoCover实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<PhotoCover> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<PhotoCover> photoCoverList = photoCoverDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", photoCoverList);
		return photoCoverList;
	}

	@Transactional
	@Override
	public PhotoCover persistEntity(PhotoCover photoCover) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		Long order = photoCover.getOrderSeq();
		
		PhotoCover entity = save(photoCover);
		Long id = photoCover.getId();
		if(order==null) {
			entity.setOrderSeq(entity.getAid());
			save(entity);
		}
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(PhotoCover.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public PhotoCover mergeEntity(PhotoCover photoCover) {
		Long id = photoCover.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		PhotoCover entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(photoCover.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(PhotoCover.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public PhotoCover saveOrUpdate(PhotoCover photoCover) {
		Long id = photoCover.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		PhotoCover entity = null;
		if(null != id) {
			entity = mergeEntity(photoCover);
		}else {
			entity = persistEntity(photoCover);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PhotoCover> findPage(PhotoCover photoCover, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<PhotoCover> page = photoCoverDao.findPage(photoCover, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return photoCoverDao.existById(id);
	}

	@Override
	public List<PhotoCover> findByMainId(Long mainId) {
		List<PhotoCover> content = photoCoverDao.findByMainId(mainId);
		return content;
	}
	
	public List<PhotoCover> findByMainId(Long mainId, Long subId) {
		List<PhotoCover> content = photoCoverDao.findByMainIdAndSubId(mainId, subId);
		return content;
	}
	
	@Transactional
	public void deleteImage(PhotoCover photoCover) {
		deleteById(photoCover, true);
	}
	
	@Transactional
	public void deleteBySubIdAndMainId(Long subId, Long mainId ){		

		LOGGER.debug("-------service>>>PhotoCoverServiceImpl.deleteBySubIdAndMainId----------begin---------");
		//findById(id)
		photoCoverDao.deleteBySubIdAndMainId( subId,  mainId );

	}
	
	@Transactional
	public void deleteById(PhotoCover photoCover) {
		deleteById(photoCover, true);
	}
	
	@Transactional
	public void deleteById(PhotoCover photoCover, boolean cascade) {
		Long id = photoCover.getId();
		PhotoCover entity = findById(id);
		if(null != entity) {
			Long userIdEntity = entity.getUserId();
			Long userId = photoCover.getUserId();
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
						ep.setLastModifiedBy(photoCover.getLastModifiedBy());
						ep.setLastModifiedDate(new DateTime());
						photoService.save(ep);
					}
				}
				delete(entity);
			}
		}
	}
	
	@Transactional
	public void deleteImageById(PhotoCover photoCover, boolean cascade) {
		Long id = photoCover.getId();
		PhotoCover entity = findById(id);
		if(null != entity) {
			Long userIdEntity = entity.getUserId();
			Long userId = photoCover.getUserId();
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
					ep.setLastModifiedBy(photoCover.getLastModifiedBy());
					ep.setLastModifiedDate(dateTime);
					photoService.save(ep);
				}
				entity.setLastModifiedBy(photoCover.getLastModifiedBy());
				entity.setLastModifiedDate(dateTime);
				entity.setType(photoCover.getType());
				save(entity);
			}
		}
	}
	@Transactional
	public PhotoCover addImageById(PhotoCover photoCover, boolean cascade) {
		Long id = photoCover.getId();
		PhotoCover entity = findById(id);
		if(null != entity) {
			Long userIdEntity = entity.getUserId();
			Long userId = photoCover.getUserId();
			if(userIdEntity.equals(userId)) {
				Photo photo = photoCover.getPhoto();
				Long photoId = entity.getPhotoId();
				Photo ep = photoService.findById(photoId);
				DateTime dateTime = new DateTime();
				if(cascade) {
					ep.setSize(photo.getSize());
					ep.setOriginal(photo.getOriginal());
					ep.setHeight(photo.getHeight());
					ep.setWidth(photo.getWidth());
					ep.setSuffix(photo.getSuffix());
					ep.setLastModifiedBy(photoCover.getLastModifiedBy());
					ep.setLastModifiedDate(dateTime);
					ep.setFilename(photo.getFilename());
					ep.setAccessUrl(photo.getAccessUrl());
					ep.setFullurl(photo.getFullurl());
					ep.setViewUrl(photo.getViewUrl());
					ep=photoService.save(ep);
				}
				entity.setWidth(photoCover.getWidth());
				entity.setLastModifiedBy(photoCover.getLastModifiedBy());
				entity.setLastModifiedDate(dateTime);
				entity.setType(photoCover.getType());
				entity.setFullurl(photoCover.getFullurl());
				entity.setExpectedUrl(photoCover.getExpectedUrl());
				entity.setExpectedW(photoCover.getExpectedW());
				save(entity);
				entity.setPhoto(ep);
			}
		}
		photoCover.setSuccess(PhotoCover.SUCCESS_YES);
		return photoCover;
	}

	@Override
	public List<PhotoCover> findBySubIdListAndMainId(List<Long> idList, Long mainId) {
		List<PhotoCover> content = photoCoverDao.findBySubIdListAndMainId(idList, mainId);
		return content;
	}
	
	@Transactional
	@Override
	public void deleteBySubIdListAndMainId(List<Long> subIdList, Long mainId, Long userId, boolean cascade) {
		
		if(cascade) {
			List<PhotoCover> paragraphList = findBySubIdListAndMainId(subIdList, mainId);
			//获取图片id
			List<Long> photoIdList = new ArrayList<>();
			if(null != paragraphList) {
				for(PhotoCover p : paragraphList) {
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
		//解除封面图片与文章关联
		photoCoverDao.deleteBySubIdListAndMainId(subIdList, mainId);
	}

	@Transactional
	@Override
	public void deleteByIdList(PhotoCoverBo photoCover) {
		List<Long> idList = photoCover.getIdList();
		List<PhotoCover> entityList = findByIdList(idList);
		if(null != entityList) {
			for(PhotoCover entity : entityList) {
				if(null != entity) {
					Long mainIdEntity = entity.getMainId();
					Long mainId = photoCover.getMainId();
					if(mainIdEntity.equals(mainId)) {
						delete(entity);
					}
				}
			}
		}
		
	}
	
	@Transactional
	public PhotoCover createPhotoCover(PhotoCover photoCover) {
		//单张图片
		Long subId = photoCover.getSubId();
		Long mainId = photoCover.getMainId();//关联图片所属主体
		
		Photo photo = photoCover.getPhoto();
		if(null != photo && null != subId && null != mainId)  {
			photoCover.setWidth(photo.getWidth());
			photoCover.setFullurl(photo.getFullurl());
			photoCover.setPhotoId(photo.getId());
			
			photoCover = persistEntity(photoCover);
			photo.setOrderSeq(photoCover.getOrderSeq());
			photo = photoService.persistEntity(photo);
			photoCover.setPhoto(photo);
		}
		
		return photoCover;
	}
	
	@Transactional
	public List<PhotoCover> createPhotoCover(List<PhotoCover> photoCoverList) {
		//为段落添加图片，多张图片
		//1.保存图片记录 XX 图片记录应在wrapper层先做保存，上传完成同时保存记录
		//2.建立关联，此处仅建立关联即可
		if(null != photoCoverList && photoCoverList.size()>0) {
			List<PhotoCover> content = new ArrayList<>(photoCoverList.size());
			for(PhotoCover p : photoCoverList) {
				content.add(createPhotoCover(p));
			}
			return content;
		}
		
		return null;
	}

	@Transactional
	@Override
	public List<PhotoCover> persistForPhoto(List<PhotoCover> photoList) {
		List<PhotoCover> entityList = null;
		if(null != photoList && !photoList.isEmpty()) {
			entityList = new ArrayList<>(photoList.size());
			for(PhotoCover pg : photoList) {
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
	public PhotoCover mergeForTitle(PhotoCover photoCover) {
		PhotoCover entity = photoCover;
		if(null != photoCover) {
			Long id = photoCover.getId();
			entity = findById(id);
			DateTime lastModifiedDate = new DateTime();
			if(null != entity) {
				entity.setOrderSeq(photoCover.getOrderSeq());
				entity.setTitle(photoCover.getTitle());
				//entity.setName(photoCover.getName());
				entity.setDescription(photoCover.getDescription());
				
				entity.setLastModifiedBy(photoCover.getLastModifiedBy());
				entity.setLastModifiedDate(lastModifiedDate);
				
			}
			
			Photo photo = photoCover.getPhoto();
			photo.setLastModifiedDate(lastModifiedDate);
			photo=photoService.mergeForTitle(photo);
			entity.setPhoto(photo);
			entity.setSuccess(PhotoCover.SUCCESS_YES);
		}
		return entity;
	}

	@Transactional
	@Override
	public List<PhotoCover> mergeListForTitle(List<PhotoCover> photoCoverList) {
		
		List<PhotoCover> entityList = photoCoverList;
		if(null != photoCoverList && !photoCoverList.isEmpty()) {
			entityList = new ArrayList<>(photoCoverList.size());
			for(PhotoCover photo : photoCoverList) {
				entityList.add(mergeForTitle(photo));
			}
		}
		
		return entityList;
	}

	@Override
	public List<PhotoCover> findByMainIdAndSubId(Long mainId, Long subId) {
		
		return photoCoverDao.findByMainIdAndSubId(mainId, subId);
	}
	
	public Map<Long, List<PhotoCover>> findByMainIdListAndSubId(List<Long> mainIdList, Long subId) {
		List<PhotoCover> content = photoCoverDao.findByMainIdListAndSubId( mainIdList,  subId);
		Map<Long, List<PhotoCover>>  contentMap = null;
		if(null != content && !content.isEmpty()) {
			contentMap = new HashMap<>(content.size());
			for(PhotoCover pc : content) {
				Long mainId = pc.getMainId();
				List<PhotoCover> clist = contentMap.get(mainId);
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
	public PhotoCover mergeRefForDescription(PhotoCover photoCover) {
		
		PhotoCover entity = photoCover;
		if(null != photoCover) {
			Long id = photoCover.getId();
			entity = findById(id);
			DateTime lastModifiedDate = new DateTime();
			if(null != entity) {
				//entity.setOrderSeq(photoCover.getOrderSeq());
				//entity.setName(photoCover.getName());
				entity.setDescription(photoCover.getDescription());
				entity.setLastModifiedBy(photoCover.getLastModifiedBy());
				entity.setLastModifiedDate(lastModifiedDate);
				save(entity);
			}
			
			entity.setSuccess(PhotoCover.SUCCESS_YES);
		}
		return entity;
	}
	
	@Transactional
	public PhotoCover mergeRefForOrder(PhotoCover photoCover) {
		PhotoCover entity = photoCover;
		if (null != photoCover) {
			Long id = photoCover.getId();
			entity = findById(id);
			DateTime lastModifiedDate = new DateTime();
			if (null != entity) {
				 entity.setOrderSeq(photoCover.getOrderSeq());
				// entity.setName(photoCover.getName());
				//entity.setDescription(photoCover.getDescription());
				entity.setLastModifiedBy(photoCover.getLastModifiedBy());
				entity.setLastModifiedDate(lastModifiedDate);
				save(entity);
			}
			entity.setSuccess(PhotoCover.SUCCESS_YES);
		}
		return entity;
	}
	
	public Long countByMainId(Long mainId) {
		return photoCoverDao.countByMainId(mainId);
	}

}
