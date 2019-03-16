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

import dml.sz0099.course.app.persist.dao.activity.CoeActivityPositionCoverDao;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPosition;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPositionCover;


/**
 * 
 * @formatter:off
 * description: CoeActivityPositionCoverServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeActivityPositionCoverServiceImpl extends GenericServiceImpl<CoeActivityPositionCover, Long> implements CoeActivityPositionCoverService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityPositionCoverServiceImpl.class);

	@Autowired
	private CoeActivityPositionCoverDao coeActivityPositionCoverDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeActivityPositionCoverDao;
	}

	/**
	 * 根据Id查询CoeActivityPositionCover实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityPositionCover findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeActivityPositionCover coeActivityPosition = coeActivityPositionCoverDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeActivityPosition);
		return coeActivityPosition;
	}
	
	@Override
	public CoeActivityPositionCover findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityPositionCover coeActivityPosition = coeActivityPositionCoverDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityPosition);
		return coeActivityPosition;
	}

	/**
	 * 根据IdList查询CoeActivityPositionCover实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityPositionCover> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeActivityPositionCover> coeActivityPositionList = coeActivityPositionCoverDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeActivityPositionList);
		return coeActivityPositionList;
	}

	@Transactional
	@Override
	public CoeActivityPositionCover persistEntity(CoeActivityPositionCover coeActivityPosition) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeActivityPositionCover entity = save(coeActivityPosition);
		Long id = coeActivityPosition.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeActivityPositionCover.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeActivityPositionCover mergeEntity(CoeActivityPositionCover coeActivityPosition) {
		Long id = coeActivityPosition.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeActivityPositionCover entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeActivityPosition.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeActivityPositionCover.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeActivityPositionCover saveOrUpdate(CoeActivityPositionCover coeActivityPosition) {
		Long id = coeActivityPosition.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeActivityPositionCover entity = null;
		if(null != id) {
			entity = mergeEntity(coeActivityPosition);
		}else {
			entity = persistEntity(coeActivityPosition);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeActivityPositionCover> findPage(CoeActivityPositionCover coeActivityPosition, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeActivityPositionCover> page = coeActivityPositionCoverDao.findPage(coeActivityPosition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeActivityPositionCoverDao.existById(id);
	}

	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return coeActivityPositionCoverDao.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public CoeActivityPositionCover findByMainIdAndUserId(Long mainId, Long userId) {
		return coeActivityPositionCoverDao.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<CoeActivityPositionCover> findByMainId(Long mainId, Pageable pageable) {
		return coeActivityPositionCoverDao.findByMainId( mainId,  pageable);
	}
	
	public boolean hasPositionByMainIdAndUserId(Long mainId, Long userId) {
		CoeActivityPositionCover entity = findByMainIdAndUserId(mainId,userId);
		if(null != entity) {
			return true;
		}
		return false;
	}

	@Transactional
	@Override
	public CoeActivityPositionCover mergeForPosition(CoeActivityPositionCover coeActivityPosition) {
		Long mainId = coeActivityPosition.getMainId();
		Long userId = coeActivityPosition.getUserId();
		CoeActivityPositionCover entity = findByMainIdAndUserId( mainId,  userId);
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
		List<CoeActivityPositionCover> bannerList = activityPosition.getCoverList();
		if(null != bannerList && !bannerList.isEmpty()) {
			bannerList = save(bannerList);
			activityPosition.setCoverList(bannerList);
		}
		return activityPosition;
	}

	@Transactional
	@Override
	public CoeActivityPosition mergeForPosition(CoeActivityPosition activityPosition) {
		List<CoeActivityPositionCover> bannerImageList = activityPosition.getCoverList();
		Long positionId = activityPosition.getId();
		if(null != bannerImageList) {
			Map<Long, CoeActivityPositionCover> existMap = findMapByRefId(positionId);
		
			List<CoeActivityPositionCover> needSaveList = new ArrayList<>();
			for(CoeActivityPositionCover image : bannerImageList) {
				Long photoId = image.getPhotoId();
				CoeActivityPositionCover exist = existMap.get(photoId);
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
				Collection<CoeActivityPositionCover> values = existMap.values();
				delete(values);
			}
			bannerImageList=save(needSaveList);
			activityPosition.setCoverList(bannerImageList);
		}
		
		return activityPosition;
	}
	
	public Map<Long, CoeActivityPositionCover> findMapByRefId(Long positionId) {
		List<CoeActivityPositionCover> existList = findByRefId(positionId);
		Map<Long, CoeActivityPositionCover> map = new HashMap<>();
		if(null != existList) {
			for(CoeActivityPositionCover image : existList) {
				Long photoId = image.getPhotoId();
				map.put(photoId, image);
			}
		}
		return map;
	}

	
	@Transactional
	@Override
	public void deleteByRefIdList(List<Long> refIdList) {
		coeActivityPositionCoverDao.deleteByRefIdList(refIdList);
	}
	
	@Transactional
	@Override
	public void deleteByRefId(Long refId) {
		coeActivityPositionCoverDao.deleteByRefId(refId);
	}
	
	public List<CoeActivityPositionCover> findByRefId(Long refId) {
		return coeActivityPositionCoverDao.findByRefId(refId);
	}
	
	@Override
	public List<CoeActivityPositionCover> findByRefIdList(List<Long> refIdList) {
		List<CoeActivityPositionCover> refList = coeActivityPositionCoverDao.findByRefIdList(refIdList);
		return refList;
	}

}
