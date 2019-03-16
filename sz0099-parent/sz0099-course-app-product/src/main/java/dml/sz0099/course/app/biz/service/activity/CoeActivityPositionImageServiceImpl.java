package dml.sz0099.course.app.biz.service.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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

import dml.sz0099.course.app.persist.dao.activity.CoeActivityPositionImageDao;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPosition;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPositionImage;


/**
 * 
 * @formatter:off
 * description: CoeActivityPositionImageServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeActivityPositionImageServiceImpl extends GenericServiceImpl<CoeActivityPositionImage, Long> implements CoeActivityPositionImageService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityPositionImageServiceImpl.class);

	@Autowired
	private CoeActivityPositionImageDao coeActivityPositionDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeActivityPositionDao;
	}

	/**
	 * 根据Id查询CoeActivityPositionImage实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityPositionImage findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeActivityPositionImage coeActivityPosition = coeActivityPositionDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeActivityPosition);
		return coeActivityPosition;
	}
	
	@Override
	public CoeActivityPositionImage findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityPositionImage coeActivityPosition = coeActivityPositionDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityPosition);
		return coeActivityPosition;
	}

	/**
	 * 根据IdList查询CoeActivityPositionImage实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityPositionImage> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeActivityPositionImage> coeActivityPositionList = coeActivityPositionDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeActivityPositionList);
		return coeActivityPositionList;
	}

	@Transactional
	@Override
	public CoeActivityPositionImage persistEntity(CoeActivityPositionImage coeActivityPosition) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeActivityPositionImage entity = save(coeActivityPosition);
		Long id = coeActivityPosition.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeActivityPositionImage.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeActivityPositionImage mergeEntity(CoeActivityPositionImage coeActivityPosition) {
		Long id = coeActivityPosition.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeActivityPositionImage entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeActivityPosition.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeActivityPositionImage.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeActivityPositionImage saveOrUpdate(CoeActivityPositionImage coeActivityPosition) {
		Long id = coeActivityPosition.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeActivityPositionImage entity = null;
		if(null != id) {
			entity = mergeEntity(coeActivityPosition);
		}else {
			entity = persistEntity(coeActivityPosition);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeActivityPositionImage> findPage(CoeActivityPositionImage coeActivityPosition, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeActivityPositionImage> page = coeActivityPositionDao.findPage(coeActivityPosition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeActivityPositionDao.existById(id);
	}

	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return coeActivityPositionDao.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public CoeActivityPositionImage findByMainIdAndUserId(Long mainId, Long userId) {
		return coeActivityPositionDao.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<CoeActivityPositionImage> findByMainId(Long mainId, Pageable pageable) {
		return coeActivityPositionDao.findByMainId( mainId,  pageable);
	}
	
	public boolean hasPositionByMainIdAndUserId(Long mainId, Long userId) {
		CoeActivityPositionImage entity = findByMainIdAndUserId(mainId,userId);
		if(null != entity) {
			return true;
		}
		return false;
	}

	@Transactional
	@Override
	public CoeActivityPositionImage mergeForPosition(CoeActivityPositionImage coeActivityPosition) {
		Long mainId = coeActivityPosition.getMainId();
		Long userId = coeActivityPosition.getUserId();
		CoeActivityPositionImage entity = findByMainIdAndUserId( mainId,  userId);
		if(null != entity) {
			//entity.setNickname(coeActivityPosition.getNickname());
			//entity.setHeadImg(coeActivityPosition.getHeadImg());
			entity.setLastModifiedBy(coeActivityPosition.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			entity = save(entity);
		}else {
			entity = save(coeActivityPosition);
		}
		return entity;
	}
	
	@Transactional
	@Override
	public CoeActivityPosition addPositionImage(CoeActivityPosition activityPosition) {
		List<CoeActivityPositionImage> bannerList = activityPosition.getBannerList();
		if(null != bannerList && !bannerList.isEmpty()) {
			bannerList = save(bannerList);
			activityPosition.setBannerList(bannerList);
		}
		return activityPosition;
	}

	@Transactional
	@Override
	public CoeActivityPosition mergeForPosition(CoeActivityPosition activityPosition) {
		List<CoeActivityPositionImage> bannerImageList = activityPosition.getBannerList();
		Long positionId = activityPosition.getId();
		if(null != bannerImageList) {
			Map<Long, CoeActivityPositionImage> existMap = findMapByRefId(positionId);
		
			List<CoeActivityPositionImage> needSaveList = new ArrayList<>();
			for(CoeActivityPositionImage image : bannerImageList) {
				Long photoId = image.getPhotoId();
				CoeActivityPositionImage exist = existMap.get(photoId);
				if(exist != null) {
					exist.setFullurl(image.getFullurl());
					exist.setWidth(image.getWidth());
					exist.setExpectedUrl(image.getExpectedUrl());
					//exist.setImageUrl(image.getImageUrl());
					//exist.setAuthorId(image.getAuthorId());
					exist.setOwnerId(image.getOwnerId());
					exist.setUserId(image.getUserId());
					needSaveList.add(exist);
					existMap.remove(photoId);
				}else {
					needSaveList.add(image);
				}
			}
			if(null != existMap && !existMap.isEmpty()) {
				Collection<CoeActivityPositionImage> values = existMap.values();
				delete(values);
			}
			bannerImageList=save(needSaveList);
			activityPosition.setBannerList(bannerImageList);
		}
		
		return activityPosition;
	}
	
	public List<CoeActivityPositionImage> findByRefId(Long refId) {
		return coeActivityPositionDao.findByRefId(refId);
	}
	
	@Override
	public List<CoeActivityPositionImage> findByRefIdList(List<Long> refIdList) {
		List<CoeActivityPositionImage> refList = coeActivityPositionDao.findByRefIdList(refIdList);
		return refList;
	}
	
	public Map<Long, CoeActivityPositionImage> findMapByRefId(Long positionId) {
		List<CoeActivityPositionImage> existList = findByRefId(positionId);
		Map<Long, CoeActivityPositionImage> map = new HashMap<>();
		if(null != existList) {
			for(CoeActivityPositionImage image : existList) {
				Long photoId = image.getPhotoId();
				map.put(photoId, image);
			}
		}
		return map;
	}

	
	@Transactional
	@Override
	public void deleteByRefIdList(List<Long> refIdList) {
		coeActivityPositionDao.deleteByRefIdList(refIdList);
	}
	
	@Transactional
	@Override
	public void deleteByRefId(Long refId) {
		coeActivityPositionDao.deleteByRefId(refId);
	}

}
