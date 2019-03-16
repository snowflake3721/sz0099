package dml.sz0099.course.app.biz.service.paragraph;

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

import dml.sz0099.course.app.persist.dao.paragraph.PhotoBannerDao;
import dml.sz0099.course.app.persist.entity.paragraph.Photo;
import dml.sz0099.course.app.persist.entity.paragraph.PhotoBanner;
import dml.sz0099.course.app.persist.entity.paragraph.bo.PhotoBannerBo;


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
@Service
public class PhotoBannerServiceImpl extends GenericServiceImpl<PhotoBanner, Long> implements PhotoBannerService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PhotoBannerServiceImpl.class);

	@Autowired
	private PhotoBannerDao photoCoverDao;
	
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
	 * 根据Id查询PhotoBanner实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public PhotoBanner findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		PhotoBanner photoCover = photoCoverDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, photoCover);
		return photoCover;
	}
	
	@Override
	public PhotoBanner findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		PhotoBanner photoCover = photoCoverDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, photoCover);
		return photoCover;
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
		List<PhotoBanner> photoCoverList = photoCoverDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", photoCoverList);
		return photoCoverList;
	}

	@Transactional
	@Override
	public PhotoBanner persistEntity(PhotoBanner photoCover) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		PhotoBanner entity = save(photoCover);
		Long id = photoCover.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(PhotoBanner.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public PhotoBanner mergeEntity(PhotoBanner photoCover) {
		Long id = photoCover.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		PhotoBanner entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(photoCover.getLastModifiedBy());
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
	public PhotoBanner saveOrUpdate(PhotoBanner photoCover) {
		Long id = photoCover.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		PhotoBanner entity = null;
		if(null != id) {
			entity = mergeEntity(photoCover);
		}else {
			entity = persistEntity(photoCover);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PhotoBanner> findPage(PhotoBanner photoCover, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<PhotoBanner> page = photoCoverDao.findPage(photoCover, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return photoCoverDao.existById(id);
	}

	@Override
	public List<PhotoBanner> findByMainId(Long mainId) {
		List<PhotoBanner> content = photoCoverDao.findByMainId(mainId);
		return content;
	}
	
	public List<PhotoBanner> findByMainId(Long mainId, Long subId) {
		List<PhotoBanner> content = photoCoverDao.findByMainIdAndSubId(mainId, subId);
		return content;
	}
	
	@Transactional
	public void deleteImage(PhotoBanner userImage) {
		
		Long id = userImage.getId();
		
		PhotoBanner entity = findById(id);
		
	}
	
	@Transactional
	public void deleteBySubIdAndMainId(Long subId, Long mainId ){		

		LOGGER.debug("-------service>>>PhotoBannerServiceImpl.deleteBySubIdAndMainId----------begin---------");
		//findById(id)
		photoCoverDao.deleteBySubIdAndMainId( subId,  mainId );

	}
	
	@Transactional
	public void deleteById(PhotoBanner photoCover) {
		deleteById(photoCover, false);
	}
	
	@Transactional
	public void deleteById(PhotoBanner photoCover, boolean cascade) {
		Long id = photoCover.getId();
		PhotoBanner entity = findById(id);
		if(null != entity) {
			Long mainIdEntity = entity.getMainId();
			Long mainId = photoCover.getMainId();
			if(mainIdEntity.equals(mainId)) {
				Long photoId = entity.getPhotoId();
				Photo ep = photoService.findById(photoId);
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
				delete(entity);
			}
		}
	}

	@Override
	public List<PhotoBanner> findBySubIdListAndMainId(List<Long> idList, Long mainId) {
		List<PhotoBanner> content = photoCoverDao.findBySubIdListAndMainId(idList, mainId);
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
		//解除封面图片与产品关联
		photoCoverDao.deleteBySubIdListAndMainId(subIdList, mainId);
	}

	@Transactional
	@Override
	public void deleteByIdList(PhotoBannerBo photoCover) {
		List<Long> idList = photoCover.getIdList();
		List<PhotoBanner> entityList = findByIdList(idList);
		if(null != entityList) {
			for(PhotoBanner entity : entityList) {
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
	public PhotoBanner createPhotoBanner(PhotoBannerBo photoCover) {
		//单张图片
		Long subId = photoCover.getSubId();
		Long mainId = photoCover.getMainId();//关联图片所属主体
		
		Photo photo = photoCover.getPhoto();
		if(null != photo && null != subId && null != mainId)  {
			photoCover.setWidth(photo.getWidth());
			photoCover.setFullurl(photo.getFullurl());
			photoCover.setPhotoId(photo.getId());
			
			persistEntity(photoCover);
		}
		
		return photoCover;
	}
	
	@Transactional
	public List<PhotoBanner> createPhotoBanner(List<PhotoBannerBo> photoCoverList) {
		//为段落添加图片，多张图片
		//1.保存图片记录 XX 图片记录应在wrapper层先做保存，上传完成同时保存记录
		//2.建立关联，此处仅建立关联即可
		if(null != photoCoverList && photoCoverList.size()>0) {
			List<PhotoBanner> content = new ArrayList<>(photoCoverList.size());
			for(PhotoBannerBo p : photoCoverList) {
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
	public PhotoBanner mergeForTitle(PhotoBanner photoCover) {
		PhotoBanner entity = photoCover;
		if(null != photoCover) {
			Long id = photoCover.getId();
			entity = findById(id);
			DateTime lastModifiedDate = new DateTime();
			if(null != entity) {
				entity.setOrderSeq(photoCover.getOrderSeq());
				entity.setTitle(photoCover.getTitle());
				entity.setName(photoCover.getName());
				
				entity.setLastModifiedBy(photoCover.getLastModifiedBy());
				entity.setLastModifiedDate(lastModifiedDate);
				
			}
			
			Photo photo = photoCover.getPhoto();
			photo.setLastModifiedDate(lastModifiedDate);
			photo=photoService.mergeForTitle(photo);
			entity.setPhoto(photo);
			entity.setSuccess(PhotoBanner.SUCCESS_YES);
		}
		return entity;
	}

	@Transactional
	@Override
	public List<PhotoBanner> mergeListForTitle(List<PhotoBanner> photoCoverList) {
		
		List<PhotoBanner> entityList = photoCoverList;
		if(null != photoCoverList && !photoCoverList.isEmpty()) {
			entityList = new ArrayList<>(photoCoverList.size());
			for(PhotoBanner photo : photoCoverList) {
				entityList.add(mergeForTitle(photo));
			}
		}
		
		return entityList;
	}

	@Override
	public List<PhotoBanner> findByMainIdAndSubId(Long mainId, Long subId) {
		
		return photoCoverDao.findByMainIdAndSubId(mainId, subId);
	}
	
	public Map<Long, List<PhotoBanner>> findByMainIdListAndSubId(List<Long> mainIdList, Long subId) {
		List<PhotoBanner> content = photoCoverDao.findByMainIdListAndSubId( mainIdList,  subId);
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

}
