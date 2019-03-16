package dml.sz0099.course.app.biz.service.article;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

import dml.sz0099.course.app.persist.dao.article.PhotoParagDao;
import dml.sz0099.course.app.persist.entity.article.Photo;
import dml.sz0099.course.app.persist.entity.article.PhotoParag;
import dml.sz0099.course.app.persist.entity.article.bo.PhotoParagBo;
import dml.sz0099.course.app.persist.entity.media.ImageRef;


/**
 * 
 * @formatter:off
 * description: PhotoParagServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service("articlePhotoParagServiceImpl")
public class PhotoParagServiceImpl extends GenericServiceImpl<PhotoParag, Long> implements PhotoParagService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PhotoParagServiceImpl.class);

	@Autowired
	private PhotoParagDao photoParagDao;
	
	@Autowired
	private PhotoService photoService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = photoParagDao;
	}

	/**
	 * 根据Id查询PhotoParag实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public PhotoParag findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		PhotoParag photoParag = photoParagDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, photoParag);
		return photoParag;
	}
	
	@Override
	public PhotoParag findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		PhotoParag photoParag = photoParagDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, photoParag);
		return photoParag;
	}

	/**
	 * 根据IdList查询PhotoParag实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<PhotoParag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<PhotoParag> photoParagList = photoParagDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", photoParagList);
		return photoParagList;
	}

	@Transactional
	@Override
	public PhotoParag persistEntity(PhotoParag photoParag) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		Long order = photoParag.getOrderSeq();
		
		PhotoParag entity = save(photoParag);
		if(order==null) {
			entity.setOrderSeq(entity.getAid());
			save(entity);
		}
		Long id = photoParag.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(PhotoParag.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public PhotoParag mergeEntity(PhotoParag photoParag) {
		Long id = photoParag.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		PhotoParag entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(photoParag.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(PhotoParag.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public PhotoParag saveOrUpdate(PhotoParag photoParag) {
		Long id = photoParag.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		PhotoParag entity = null;
		if(null != id) {
			entity = mergeEntity(photoParag);
		}else {
			entity = persistEntity(photoParag);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PhotoParag> findPage(PhotoParag photoParag, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<PhotoParag> page = photoParagDao.findPage(photoParag, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return photoParagDao.existById(id);
	}



	public void deleteByParagIdAndUserId(Long paragId, Long userId ){		

		LOGGER.debug("-------service>>>PhotoParagServiceImpl.deleteByParagIdAndUserId----------begin---------");
		//findById(id)
		photoParagDao.deleteByParagIdAndUserId( paragId,  userId );

	}
	
	@Transactional
	public void deleteById(PhotoParag photoParag) {
		deleteById(photoParag, false);
	}
	
	@Transactional
	public void deleteById(PhotoParag photoParag, boolean cascade) {
		Long id = photoParag.getId();
		PhotoParag entity = findById(id);
		if(null != entity) {
			Long userIdEntity = entity.getUserId();
			Long userId = photoParag.getUserId();
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
						ep.setLastModifiedBy(photoParag.getLastModifiedBy());
						ep.setLastModifiedDate(new DateTime());
						photoService.save(ep);
					}
				}
				delete(entity);
			}
		}
	}
	@Transactional
	public void deleteImageById(PhotoParag photoParag, boolean cascade) {
		Long id = photoParag.getId();
		PhotoParag entity = findById(id);
		if(null != entity) {
			Long userIdEntity = entity.getUserId();
			Long userId = photoParag.getUserId();
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
					ep.setLastModifiedBy(photoParag.getLastModifiedBy());
					ep.setLastModifiedDate(dateTime);
					photoService.save(ep);
				}
				entity.setLastModifiedBy(photoParag.getLastModifiedBy());
				entity.setLastModifiedDate(dateTime);
				entity.setType(photoParag.getType());
				save(entity);
			}
		}
	}
	@Transactional
	public PhotoParag addImageById(PhotoParag photoParag, boolean cascade) {
		Long id = photoParag.getId();
		PhotoParag entity = findById(id);
		if(null != entity) {
			Long userIdEntity = entity.getUserId();
			Long userId = photoParag.getUserId();
			if(userIdEntity.equals(userId)) {
				Photo photo = photoParag.getPhoto();
				Long photoId = entity.getPhotoId();
				Photo ep = photoService.findById(photoId);
				DateTime dateTime = new DateTime();
				if(cascade) {
					ep.setSize(photo.getSize());
					ep.setOriginal(photo.getOriginal());
					ep.setHeight(photo.getHeight());
					ep.setWidth(photo.getWidth());
					ep.setSuffix(photo.getSuffix());
					ep.setLastModifiedBy(photoParag.getLastModifiedBy());
					ep.setLastModifiedDate(dateTime);
					ep.setFilename(photo.getFilename());
					ep.setAccessUrl(photo.getAccessUrl());
					ep.setFullurl(photo.getFullurl());
					ep.setViewUrl(photo.getViewUrl());
					ep=photoService.save(ep);
				}
				entity.setWidth(photoParag.getWidth());
				entity.setLastModifiedBy(photoParag.getLastModifiedBy());
				entity.setLastModifiedDate(dateTime);
				entity.setType(photoParag.getType());
				entity.setFullurl(photoParag.getFullurl());
				entity.setExpectedUrl(photoParag.getExpectedUrl());
				entity.setExpectedW(photoParag.getExpectedW());
				save(entity);
				entity.setPhoto(ep);
			}
		}
		photoParag.setSuccess(PhotoParag.SUCCESS_YES);
		return photoParag;
	}

	@Override
	public List<PhotoParag> findByParagIdListAndUserId(List<Long> idList, Long userId) {
		List<PhotoParag> content = photoParagDao.findByParagIdListAndUserId(idList, userId);
		return content;
	}
	
	public List<PhotoParag> findByParagIdList(List<Long> paragIdList){
		List<PhotoParag> content = photoParagDao.findByParagIdList(paragIdList);
		return content;
	}

	@Transactional
	@Override
	public void deleteByParagIdListAndUserId(List<Long> paragIdList, Long userId, boolean cascade) {
		
		if(cascade) {
			List<PhotoParag> paragraphList = findByParagIdListAndUserId(paragIdList, userId);
			//获取图片id
			List<Long> photoIdList = new ArrayList<>();
			if(null != paragraphList) {
				for(PhotoParag p : paragraphList) {
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
		//解除图片与段落关联
		photoParagDao.deleteByParagIdListAndUserId(paragIdList, userId);
	}

	@Transactional
	@Override
	public void deleteByIdList(PhotoParagBo photoParag) {
		List<Long> idList = photoParag.getIdList();
		List<PhotoParag> entityList = findByIdList(idList);
		if(null != entityList) {
			for(PhotoParag entity : entityList) {
				if(null != entity) {
					Long userIdEntity = entity.getUserId();
					Long userId = photoParag.getUserId();
					if(userIdEntity.equals(userId)) {
						delete(entity);
					}
				}
			}
		}
		
	}
	
	public PhotoParag findById(Long id, boolean withPhoto) {
		PhotoParag entity = findById(id);
		if(null != entity && withPhoto  ) {
			Long photoId = entity.getPhotoId();
			Photo photo = photoService.findById(photoId);
			entity.setPhoto(photo);
		}
		return entity;
	}
	
	@Transactional
	public PhotoParag createPhotoParag(PhotoParag photoParag) {
		//单张图片
		Long paragId = photoParag.getParagId();
		
		Photo photo = photoParag.getPhoto();
		if(null != photo && null != paragId) {
			photoParag.setWidth(photo.getWidth());
			photoParag.setFullurl(photo.getFullurl());
			photoParag.setPhotoId(photo.getId());
			
			photoParag = persistEntity(photoParag);
			photo.setOrderSeq(photoParag.getOrderSeq());
			photo = photoService.persistEntity(photo);
			photoParag.setPhoto(photo);
		}
		
		return photoParag;
	}
	
	@Transactional
	public List<PhotoParag> createPhotoParag(List<PhotoParag> photoParagList) {
		//为段落添加图片，多张图片
		//1.保存图片记录 XX 图片记录应在wrapper层先做保存，上传完成同时保存记录
		//2.建立关联，此处仅建立关联即可
		if(null != photoParagList && photoParagList.size()>0) {
			List<PhotoParag> content = new ArrayList<>(photoParagList.size());
			for(PhotoParag p : photoParagList) {
				content.add(createPhotoParag(p));
			}
			return content;
		}
		
		return null;
	}

	@Transactional
	@Override
	public List<PhotoParag> persistForPhoto(List<PhotoParag> photoList) {
		List<PhotoParag> entityList = null;
		if(null != photoList && !photoList.isEmpty()) {
			entityList = new ArrayList<>(photoList.size());
			for(PhotoParag pg : photoList) {
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
	public PhotoParag mergeForTitle(PhotoParag photoParag) {
		PhotoParag entity = photoParag;
		if(null != photoParag) {
			Long id = photoParag.getId();
			entity = findById(id);
			DateTime lastModifiedDate = new DateTime();
			if(null != entity) {
				entity.setOrderSeq(photoParag.getOrderSeq());
				entity.setTitle(photoParag.getTitle());
				//entity.setName(photoParag.getName());
				entity.setDescription(photoParag.getDescription());
				
				entity.setLastModifiedBy(photoParag.getLastModifiedBy());
				entity.setLastModifiedDate(lastModifiedDate);
				
			}
			
			Photo photo = photoParag.getPhoto();
			photo.setLastModifiedDate(lastModifiedDate);
			photo=photoService.mergeForTitle(photo);
			entity.setPhoto(photo);
			entity.setSuccess(PhotoParag.SUCCESS_YES);
		}
		return entity;
	}
	
	@Transactional
	@Override
	public List<PhotoParag> mergeListForTitle(List<PhotoParag> photoParagList) {
		
		List<PhotoParag> entityList = photoParagList;
		if(null != photoParagList && !photoParagList.isEmpty()) {
			entityList = new ArrayList<>(photoParagList.size());
			for(PhotoParag photo : photoParagList) {
				entityList.add(mergeForTitle(photo));
			}
		}
		
		return entityList;
	}

	@Transactional
	@Override
	public PhotoParag mergeRefForDescription(PhotoParag photoParag) {
		
		PhotoParag entity = photoParag;
		if(null != photoParag) {
			Long id = photoParag.getId();
			entity = findById(id);
			DateTime lastModifiedDate = new DateTime();
			if(null != entity) {
				//entity.setOrderSeq(photoParag.getOrderSeq());
				//entity.setName(photoParag.getName());
				entity.setDescription(photoParag.getDescription());
				entity.setLastModifiedBy(photoParag.getLastModifiedBy());
				entity.setLastModifiedDate(lastModifiedDate);
				save(entity);
			}
			
			entity.setSuccess(PhotoParag.SUCCESS_YES);
		}
		return entity;
	}
	
	@Transactional
	public PhotoParag mergeRefForOrder(PhotoParag photoParag) {
		PhotoParag entity = photoParag;
		if (null != photoParag) {
			Long id = photoParag.getId();
			entity = findById(id);
			DateTime lastModifiedDate = new DateTime();
			if (null != entity) {
				 entity.setOrderSeq(photoParag.getOrderSeq());
				// entity.setName(photoParag.getName());
				//entity.setDescription(photoParag.getDescription());
				entity.setLastModifiedBy(photoParag.getLastModifiedBy());
				entity.setLastModifiedDate(lastModifiedDate);
				save(entity);
			}
			entity.setSuccess(PhotoParag.SUCCESS_YES);
		}
		return entity;
	}
	
	public Long countByMainId(Long mainId) {
		return photoParagDao.countByMainId(mainId);
	}

}
