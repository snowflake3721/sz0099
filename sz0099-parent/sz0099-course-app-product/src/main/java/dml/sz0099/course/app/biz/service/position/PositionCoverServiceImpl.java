package dml.sz0099.course.app.biz.service.position;

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

import dml.sz0099.course.app.persist.dao.position.PositionCoverDao;
import dml.sz0099.course.app.persist.entity.position.PositionCover;
import dml.sz0099.course.app.persist.entity.position.PositionRef;


/**
 * 
 * @formatter:off
 * description: PositionCoverServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class PositionCoverServiceImpl extends GenericServiceImpl<PositionCover, Long> implements PositionCoverService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PositionCoverServiceImpl.class);

	@Autowired
	private PositionCoverDao positionCoverDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = positionCoverDao;
	}

	/**
	 * 根据Id查询PositionCover实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public PositionCover findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		PositionCover positionCover = positionCoverDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, positionCover);
		return positionCover;
	}
	
	@Override
	public PositionCover findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		PositionCover positionCover = positionCoverDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, positionCover);
		return positionCover;
	}

	/**
	 * 根据IdList查询PositionCover实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<PositionCover> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<PositionCover> positionCoverList = positionCoverDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", positionCoverList);
		return positionCoverList;
	}

	@Transactional
	@Override
	public PositionCover persistEntity(PositionCover positionCover) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		PositionCover entity = save(positionCover);
		Long id = positionCover.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(PositionCover.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public PositionCover mergeEntity(PositionCover positionCover) {
		Long id = positionCover.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		PositionCover entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(positionCover.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(PositionCover.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public PositionCover saveOrUpdate(PositionCover positionCover) {
		Long id = positionCover.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		PositionCover entity = null;
		if(null != id) {
			entity = mergeEntity(positionCover);
		}else {
			entity = persistEntity(positionCover);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PositionCover> findPage(PositionCover positionCover, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<PositionCover> page = positionCoverDao.findPage(positionCover, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return positionCoverDao.existById(id);
	}

	

	@Transactional
	@Override
	public void deleteByRefIdList(List<Long> refIdList) {
		positionCoverDao.deleteByRefIdList(refIdList);
	}
	
	@Transactional
	@Override
	public void deleteByRefId(Long refId) {
		positionCoverDao.deleteByRefId(refId);
	}
	
	@Transactional
	@Override
	public PositionRef addPositionCover(PositionRef positionRef) {
		List<PositionCover> bannerList = positionRef.getCoverList();
		if(null != bannerList && !bannerList.isEmpty()) {
			bannerList = save(bannerList);
			positionRef.setCoverList(bannerList);
		}
		return positionRef;
	}
	
	@Transactional
	@Override
	public PositionRef mergeForPosition(PositionRef positionRef) {
		List<PositionCover> bannerCoverList = positionRef.getCoverList();
		Long positionId = positionRef.getId();
		if(null != bannerCoverList) {
			Map<Long, PositionCover> existMap = findMapByRefId(positionId);
		
			List<PositionCover> needSaveList = new ArrayList<>();
			for(PositionCover image : bannerCoverList) {
				Long photoId = image.getPhotoId();
				PositionCover exist = existMap.get(photoId);
				if(exist != null) {
					exist.setFullurl(image.getFullurl());
					exist.setWidth(image.getWidth());
					exist.setExpectedUrl(image.getExpectedUrl());
					//exist.setCoverUrl(image.getCoverUrl());
					//exist.setAuthorId(image.getAuthorId());
					exist.setUserId(image.getUserId());
					exist.setOwnerId(image.getOwnerId());
					needSaveList.add(exist);
					existMap.remove(photoId);
				}else {
					needSaveList.add(image);
				}
			}
			if(null != existMap && !existMap.isEmpty()) {
				Collection<PositionCover> values = existMap.values();
				delete(values);
			}
			bannerCoverList=save(needSaveList);
			positionRef.setCoverList(bannerCoverList);
		}
		
		return positionRef;
	}
	
	public Map<Long, PositionCover> findMapByRefId(Long refId) {
		List<PositionCover> existList = findByRefId(refId);
		Map<Long, PositionCover> map = new HashMap<>();
		if(null != existList) {
			for(PositionCover image : existList) {
				Long photoId = image.getPhotoId();
				map.put(photoId, image);
			}
		}
		return map;
	}
	
	public List<PositionCover> findByRefId(Long refId) {
		return positionCoverDao.findByRefId(refId);
	}
	
	@Override
	public List<PositionCover> findByRefIdList(List<Long> refIdList) {
		List<PositionCover> refList = positionCoverDao.findByRefIdList(refIdList);
		return refList;
	}

}
