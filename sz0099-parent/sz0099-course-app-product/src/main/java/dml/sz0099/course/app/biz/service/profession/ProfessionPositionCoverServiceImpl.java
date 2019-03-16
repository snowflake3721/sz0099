package dml.sz0099.course.app.biz.service.profession;

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

import dml.sz0099.course.app.persist.dao.profession.ProfessionPositionCoverDao;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPosition;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPositionCover;


/**
 * 
 * @formatter:off
 * description: ProfessionPositionCoverServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class ProfessionPositionCoverServiceImpl extends GenericServiceImpl<ProfessionPositionCover, Long> implements ProfessionPositionCoverService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionPositionCoverServiceImpl.class);

	@Autowired
	private ProfessionPositionCoverDao professionPositionDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = professionPositionDao;
	}

	/**
	 * 根据Id查询ProfessionPositionCover实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionPositionCover findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		ProfessionPositionCover professionPosition = professionPositionDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, professionPosition);
		return professionPosition;
	}
	
	@Override
	public ProfessionPositionCover findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionPositionCover professionPosition = professionPositionDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, professionPosition);
		return professionPosition;
	}

	/**
	 * 根据IdList查询ProfessionPositionCover实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionPositionCover> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<ProfessionPositionCover> professionPositionList = professionPositionDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", professionPositionList);
		return professionPositionList;
	}

	@Transactional
	@Override
	public ProfessionPositionCover persistEntity(ProfessionPositionCover professionPosition) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		ProfessionPositionCover entity = save(professionPosition);
		Long id = professionPosition.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(ProfessionPositionCover.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public ProfessionPositionCover mergeEntity(ProfessionPositionCover professionPosition) {
		Long id = professionPosition.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		ProfessionPositionCover entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(professionPosition.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(ProfessionPositionCover.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public ProfessionPositionCover saveOrUpdate(ProfessionPositionCover professionPosition) {
		Long id = professionPosition.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionPositionCover entity = null;
		if(null != id) {
			entity = mergeEntity(professionPosition);
		}else {
			entity = persistEntity(professionPosition);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionPositionCover> findPage(ProfessionPositionCover professionPosition, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<ProfessionPositionCover> page = professionPositionDao.findPage(professionPosition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return professionPositionDao.existById(id);
	}

	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return professionPositionDao.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public ProfessionPositionCover findByMainIdAndUserId(Long mainId, Long userId) {
		return professionPositionDao.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<ProfessionPositionCover> findByMainId(Long mainId, Pageable pageable) {
		return professionPositionDao.findByMainId( mainId,  pageable);
	}
	
	public boolean hasPositionByMainIdAndUserId(Long mainId, Long userId) {
		ProfessionPositionCover entity = findByMainIdAndUserId(mainId,userId);
		if(null != entity) {
			return true;
		}
		return false;
	}

	@Transactional
	@Override
	public ProfessionPositionCover mergeForPosition(ProfessionPositionCover professionPosition) {
		Long mainId = professionPosition.getMainId();
		Long userId = professionPosition.getUserId();
		ProfessionPositionCover entity = findByMainIdAndUserId( mainId,  userId);
		if(null != entity) {
			//entity.setNickname(professionPosition.getNickname());
			//entity.setHeadImg(professionPosition.getHeadImg());
			entity.setLastModifiedBy(professionPosition.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			entity = save(entity);
		}else {
			entity = save(professionPosition);
		}
		return entity;
	}
	
	@Transactional
	@Override
	public ProfessionPosition addPositionCover(ProfessionPosition professionPosition) {
		List<ProfessionPositionCover> bannerList = professionPosition.getCoverList();
		if(null != bannerList && !bannerList.isEmpty()) {
			bannerList = save(bannerList);
			professionPosition.setCoverList(bannerList);
		}
		return professionPosition;
	}

	@Transactional
	@Override
	public ProfessionPosition mergeForPosition(ProfessionPosition professionPosition) {
		List<ProfessionPositionCover> bannerCoverList = professionPosition.getCoverList();
		Long positionId = professionPosition.getId();
		if(null != bannerCoverList) {
			Map<Long, ProfessionPositionCover> existMap = findMapByRefId(positionId);
		
			List<ProfessionPositionCover> needSaveList = new ArrayList<>();
			for(ProfessionPositionCover image : bannerCoverList) {
				Long photoId = image.getPhotoId();
				ProfessionPositionCover exist = existMap.get(photoId);
				if(exist != null) {
					exist.setFullurl(image.getFullurl());
					exist.setWidth(image.getWidth());
					exist.setExpectedUrl(image.getExpectedUrl());
					//exist.setCoverUrl(image.getCoverUrl());
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
				Collection<ProfessionPositionCover> values = existMap.values();
				delete(values);
			}
			bannerCoverList=save(needSaveList);
			professionPosition.setCoverList(bannerCoverList);
		}
		
		return professionPosition;
	}
	
	public List<ProfessionPositionCover> findByRefId(Long refId) {
		return professionPositionDao.findByRefId(refId);
	}
	
	public List<ProfessionPositionCover> findByRefIdList(List<Long> refIdList) {
		List<ProfessionPositionCover> refList = professionPositionDao.findByRefIdList(refIdList);
		return refList;
	}
	
	public Map<Long, ProfessionPositionCover> findMapByRefId(Long positionId) {
		List<ProfessionPositionCover> existList = findByRefId(positionId);
		Map<Long, ProfessionPositionCover> map = new HashMap<>();
		if(null != existList) {
			for(ProfessionPositionCover image : existList) {
				Long photoId = image.getPhotoId();
				map.put(photoId, image);
			}
		}
		return map;
	}

	
	@Transactional
	@Override
	public void deleteByRefIdList(List<Long> refIdList) {
		professionPositionDao.deleteByRefIdList(refIdList);
	}
	
	@Transactional
	@Override
	public void deleteByRefId(Long refId) {
		professionPositionDao.deleteByRefId(refId);
	}

}
