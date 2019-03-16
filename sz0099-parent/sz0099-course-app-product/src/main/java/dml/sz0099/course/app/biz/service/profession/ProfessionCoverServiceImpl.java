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

import dml.sz0099.course.app.persist.dao.profession.ProfessionCoverDao;
import dml.sz0099.course.app.persist.entity.profession.ProfessionRef;
import dml.sz0099.course.app.persist.entity.profession.ProfessionCover;
import dml.sz0099.course.app.persist.entity.profession.ProfessionImage;


/**
 * 
 * @formatter:off
 * description: ProfessionCoverServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class ProfessionCoverServiceImpl extends GenericServiceImpl<ProfessionCover, Long> implements ProfessionCoverService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionCoverServiceImpl.class);

	@Autowired
	private ProfessionCoverDao professionRefDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = professionRefDao;
	}

	/**
	 * 根据Id查询ProfessionCover实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionCover findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		ProfessionCover professionRef = professionRefDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, professionRef);
		return professionRef;
	}
	
	@Override
	public ProfessionCover findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionCover professionRef = professionRefDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, professionRef);
		return professionRef;
	}

	/**
	 * 根据IdList查询ProfessionCover实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionCover> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<ProfessionCover> professionRefList = professionRefDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", professionRefList);
		return professionRefList;
	}

	@Transactional
	@Override
	public ProfessionCover persistEntity(ProfessionCover professionRef) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		ProfessionCover entity = save(professionRef);
		Long id = professionRef.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(ProfessionCover.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public ProfessionCover mergeEntity(ProfessionCover professionRef) {
		Long id = professionRef.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		ProfessionCover entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(professionRef.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(ProfessionCover.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public ProfessionCover saveOrUpdate(ProfessionCover professionRef) {
		Long id = professionRef.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionCover entity = null;
		if(null != id) {
			entity = mergeEntity(professionRef);
		}else {
			entity = persistEntity(professionRef);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionCover> findPage(ProfessionCover professionRef, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<ProfessionCover> page = professionRefDao.findPage(professionRef, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return professionRefDao.existById(id);
	}

	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return professionRefDao.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public ProfessionCover findByMainIdAndUserId(Long mainId, Long userId) {
		return professionRefDao.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<ProfessionCover> findByMainId(Long mainId, Pageable pageable) {
		return professionRefDao.findByMainId( mainId,  pageable);
	}
	
	public boolean hasPositionByMainIdAndUserId(Long mainId, Long userId) {
		ProfessionCover entity = findByMainIdAndUserId(mainId,userId);
		if(null != entity) {
			return true;
		}
		return false;
	}

	@Transactional
	@Override
	public ProfessionCover mergeForProfession(ProfessionCover professionRef) {
		Long mainId = professionRef.getMainId();
		Long userId = professionRef.getUserId();
		ProfessionCover entity = findByMainIdAndUserId( mainId,  userId);
		if(null != entity) {
			//entity.setNickname(professionRef.getNickname());
			//entity.setHeadImg(professionRef.getHeadImg());
			entity.setLastModifiedBy(professionRef.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			entity = save(entity);
		}else {
			entity = save(professionRef);
		}
		return entity;
	}
	
	@Transactional
	@Override
	public ProfessionRef addProfessionCover(ProfessionRef professionRef) {
		List<ProfessionCover> bannerList = professionRef.getCoverList();
		if(null != bannerList && !bannerList.isEmpty()) {
			bannerList = save(bannerList);
			professionRef.setCoverList(bannerList);
		}
		return professionRef;
	}

	@Transactional
	@Override
	public ProfessionRef mergeForProfession(ProfessionRef professionRef) {
		List<ProfessionCover> bannerCoverList = professionRef.getCoverList();
		Long positionId = professionRef.getId();
		if(null != bannerCoverList) {
			Map<Long, ProfessionCover> existMap = findMapByRefId(positionId);
		
			List<ProfessionCover> needSaveList = new ArrayList<>();
			for(ProfessionCover image : bannerCoverList) {
				Long photoId = image.getPhotoId();
				ProfessionCover exist = existMap.get(photoId);
				if(exist != null) {
					exist.setFullurl(image.getFullurl());
					exist.setWidth(image.getWidth());
					exist.setExpectedUrl(image.getExpectedUrl());
					//exist.setCoverUrl(image.getCoverUrl());
					exist.setAuthorId(image.getAuthorId());
					exist.setUserId(image.getUserId());
					needSaveList.add(exist);
					existMap.remove(photoId);
				}else {
					needSaveList.add(image);
				}
			}
			if(null != existMap && !existMap.isEmpty()) {
				Collection<ProfessionCover> values = existMap.values();
				delete(values);
			}
			bannerCoverList=save(needSaveList);
			professionRef.setCoverList(bannerCoverList);
		}
		
		return professionRef;
	}
	
	public List<ProfessionCover> findByRefId(Long refId) {
		return professionRefDao.findByRefId(refId);
	}
	
	@Override
	public List<ProfessionCover> findByRefIdList(List<Long> refIdList) {
		List<ProfessionCover> refList = professionRefDao.findByRefIdList(refIdList);
		return refList;
	}
	
	public Map<Long, ProfessionCover> findMapByRefId(Long positionId) {
		List<ProfessionCover> existList = findByRefId(positionId);
		Map<Long, ProfessionCover> map = new HashMap<>();
		if(null != existList) {
			for(ProfessionCover image : existList) {
				Long photoId = image.getPhotoId();
				map.put(photoId, image);
			}
		}
		return map;
	}

	
	@Transactional
	@Override
	public void deleteByRefIdList(List<Long> refIdList) {
		professionRefDao.deleteByRefIdList(refIdList);
	}
	
	@Transactional
	@Override
	public void deleteByRefId(Long refId) {
		professionRefDao.deleteByRefId(refId);
	}

}
