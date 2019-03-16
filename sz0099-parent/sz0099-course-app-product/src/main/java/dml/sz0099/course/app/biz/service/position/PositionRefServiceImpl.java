package dml.sz0099.course.app.biz.service.position;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.jit4j.core.service.GenericServiceImpl;
import org.jit8j.core.util.Base64Util;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.persist.dao.position.PositionRefDao;
import dml.sz0099.course.app.persist.entity.position.PositionCover;
import dml.sz0099.course.app.persist.entity.position.PositionImage;
import dml.sz0099.course.app.persist.entity.position.PositionRef;


/**
 * 
 * @formatter:off
 * description: PositionRefServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class PositionRefServiceImpl extends GenericServiceImpl<PositionRef, Long> implements PositionRefService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PositionRefServiceImpl.class);

	@Autowired
	private PositionRefDao positionRefDao;
	
	@Autowired
	private PositionImageService positionImageService;
	
	@Autowired
	private PositionCoverService positionCoverService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = positionRefDao;
	}

	/**
	 * 根据Id查询PositionRef实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public PositionRef findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		PositionRef positionRef = positionRefDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, positionRef);
		return positionRef;
	}
	
	@Override
	public PositionRef findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		PositionRef positionRef = positionRefDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, positionRef);
		return positionRef;
	}

	/**
	 * 根据IdList查询PositionRef实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<PositionRef> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<PositionRef> positionRefList = positionRefDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", positionRefList);
		return positionRefList;
	}

	@Transactional
	@Override
	public PositionRef persistEntity(PositionRef positionRef) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		PositionRef entity = save(positionRef);
		Long id = positionRef.getId();
		entity.setOrderSeq(entity.getAid());
		entity.setTopLevel(PositionRef.TOP_LEVEL_1);
		save(entity);
		positionImageService.addPositionImage(positionRef);
		positionCoverService.addPositionCover(positionRef);
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(PositionRef.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public PositionRef mergeEntity(PositionRef positionRef) {
		Long id = positionRef.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		PositionRef entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(positionRef.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(PositionRef.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public PositionRef saveOrUpdate(PositionRef positionRef) {
		Long id = positionRef.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		PositionRef entity = null;
		if(null != id) {
			entity = mergeEntity(positionRef);
		}else {
			entity = persistEntity(positionRef);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PositionRef> findPage(PositionRef positionRef, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<PositionRef> page = positionRefDao.findPage(positionRef, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return positionRefDao.existById(id);
	}
	
	@Transactional
	@Override
	public void deleteByBaseId(Long baseId) {
		positionRefDao.deleteByBaseId(baseId);
	}
	
	@Transactional
	@Override
	public void deleteByBaseId(Long baseId, boolean cascade) {
		if(cascade) {
			List<PositionRef> entityList = findByBaseId(baseId);
			if(null != entityList && !entityList.isEmpty()) {
				List<Long> refIdList = new ArrayList<>();
				for(PositionRef ref : entityList) {
					Long id = ref.getId();
					refIdList.add(id);
				}
				positionImageService.deleteByRefIdList(refIdList);
				positionCoverService.deleteByRefIdList(refIdList);
			}
		}
		positionRefDao.deleteByBaseId(baseId);
	}
	
	@Transactional
	@Override
	public void deleteByBaseIdList(List<Long> baseIdList, boolean cascade) {
		if(cascade) {
			List<PositionRef> entityList = findByBaseIdList(baseIdList);
			if(null != entityList && !entityList.isEmpty()) {
				List<Long> refIdList = new ArrayList<>();
				for(PositionRef ref : entityList) {
					Long id = ref.getId();
					refIdList.add(id);
				}
				positionImageService.deleteByRefIdList(refIdList);
				positionCoverService.deleteByRefIdList(refIdList);
			}
			delete(entityList);
		}
		//positionRefDao.deleteByBaseIdList(baseIdList);
	}
	
	public Long countForBase(PositionRef positionRef) {
		Long baseId = positionRef.getBaseId();
		return positionRefDao.countForBase(positionRef);
	}
	
	public List<PositionRef> findByMainId(Long mainId) {
		return positionRefDao.findByMainId(mainId);
	}
	
	@Transactional
	@Override
	public PositionRef changeSingle(PositionRef positionRef) {
		Long baseId = positionRef.getBaseId();
		PositionRef entity = findMainIdAndBaseId(positionRef);
		if(null != entity) {
			//entity.setBaseId(baseId);
			//entity.setUserId(positionRef.getUserId());
			entity.setLastModifiedBy(positionRef.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			entity.setExtendId(positionRef.getExtendId());
			entity.setPositionId(positionRef.getPositionId());
			
			//entity.setAuthorId(positionRef.getAuthorId());
			//entity.setAuthorname(positionRef.getAuthorname());
			entity.setNickname(positionRef.getNickname());
			entity.setPenname(positionRef.getPenname());
			entity.setHeadImg(positionRef.getHeadImg());
			entity.setUserId(positionRef.getUserId());
			
			
			entity.setCoverImage(positionRef.getCoverImage());
			
			entity.setDescription(positionRef.getDescription());
			entity.setLink(positionRef.getLink());
			entity.setOriginalLink(positionRef.getOriginalLink());
			entity.setName(positionRef.getName());
			entity.setOrderSeq(positionRef.getOrderSeq());
			entity.setPreIntro(positionRef.getPreIntro());
			entity.setPreIntroType(positionRef.getPreIntroType());
			entity.setStatus(positionRef.getStatus());
			entity.setSubTitle(positionRef.getSubTitle());
			entity.setTitle(positionRef.getTitle());
			//entity.setTopLevel(positionRef.getTopLevel());
			positionImageService.mergeForPosition(positionRef);
			positionCoverService.mergeForPosition(positionRef);
			positionRef=save(entity);
			positionRef.setSuccess(PositionRef.SUCCESS_YES);
		}else {
			positionRef=persistEntity(positionRef);
			positionRef.setSuccess(PositionRef.SUCCESS_YES);
		}
		
		return positionRef;
	}

	@Override
	public List<PositionRef> findByBaseId(Long baseId) {
		List<PositionRef> entityList = positionRefDao.findByBaseId(baseId);
		return entityList;
	}
	
	public List<PositionRef> findByBaseIdList(List<Long> baseIdList) {
		List<PositionRef> entityList = positionRefDao.findByBaseIdList(baseIdList);
		return entityList;
	}
	
	public List<PositionRef> findByBaseId(Long baseId, boolean cascade){
		List<PositionRef> entityList = findByBaseId(baseId);
		if(null != entityList && cascade) {
			fillImages(entityList);
		}
		return entityList;
	}
	
	public Page<PositionRef> findPageByBaseId(Long baseId, Pageable pageable) {
		return positionRefDao.findPageByBaseId(baseId, pageable);
	}
	
	public Page<PositionRef> findPageByBaseId(Long baseId, Pageable pageable, boolean withImages) {
		Page<PositionRef> page = findPageByBaseId(baseId, pageable);
		List<PositionRef> entityList = page.getContent();
		if(withImages) {
			fillImages(entityList);
		}
		return page;
	}

	/**
	 * @param entityList
	 */
	public Map<Long, PositionRef> fillImages(List<PositionRef> entityList) {
		Map<Long, PositionRef> map = new HashMap<>();
		List<Long> refIdList = new ArrayList<>();
		for(PositionRef ref : entityList) {
			Long refId = ref.getId();
			refIdList.add(refId);
			map.put(refId, ref);
			String nickname = ref.getNickname();
			ref.setNickname(Base64Util.decode(nickname));
		}
		if(!refIdList.isEmpty()) {
			List<PositionImage> refList = positionImageService.findByRefIdList(refIdList);
			if(null != refList) {
				for(PositionImage p : refList) {
					Long refId = p.getRefId();
					PositionRef ref = map.get(refId);
					if(null != ref) {
						List<PositionImage> bannerList = ref.getBannerList();
						if(null == bannerList) {
							bannerList = new ArrayList<>();
							ref.setBannerList(bannerList);
						}
						bannerList.add(p);
					}
				}
			}
			List<PositionCover> refCoverList = positionCoverService.findByRefIdList(refIdList);
			if(null != refCoverList) {
				for(PositionCover p : refCoverList) {
					Long refId = p.getRefId();
					PositionRef ref = map.get(refId);
					if(null != ref) {
						List<PositionCover> bannerList = ref.getCoverList();
						if(null == bannerList) {
							bannerList = new ArrayList<>();
							ref.setCoverList(bannerList);
						}
						bannerList.add(p);
					}
				}
			}
			
		}
		return map;
	}
	
	public List<PositionRef> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList) {
		return positionRefDao.findByBaseIdAndMainIdList( baseId, mainIdList) ;
	}
	
	public PositionRef findMainIdAndBaseId(PositionRef positionRef) {
		return positionRefDao.findMainIdAndBaseId(positionRef);
	}
	
	@Transactional
	public void deleteById(PositionRef positionRef) {
		Long id = positionRef.getId();
		PositionRef entity = findById(id);
		if(null != entity) {
			positionImageService.deleteByRefId(id);
			delete(entity);
		}
	}
	
	@Transactional
	public void deleteByBaseId(PositionRef positionRef) {
		Long baseId = positionRef.getBaseId();
		deleteByBaseId(baseId, true);
	}
	
	@Transactional
	public PositionRef mergePositionRef(PositionRef positionRef) {
		Long id = positionRef.getId();
		if(null != id) {
			PositionRef entity = findById(id);
			if(null != entity) {
				entity.setLastModifiedBy(positionRef.getLastModifiedBy());
				DateTime lastModifiedDate = new DateTime();
				entity.setLastModifiedDate(lastModifiedDate);
				entity.setLink(positionRef.getLink());
				entity.setOriginalLink(positionRef.getOriginalLink());
				entity.setName(positionRef.getName());
				entity.setOrderSeq(positionRef.getOrderSeq());
				entity.setPreIntro(positionRef.getPreIntro());
				entity.setPreIntroType(positionRef.getPreIntroType());
				Integer status = positionRef.getStatus();
				entity.setStatus(status);
				if(PositionRef.STATUS_1_OPEN.getValueInt().equals(status)) {
					Date openTime = entity.getOpenTime();
					if(null == openTime) {
						entity.setOpenTime(positionRef.getOpenTime());
						entity.setRefreshTime(openTime);
					}
					entity.setRemark(positionRef.getRemark());
				}else if(PositionRef.STATUS_2_CLOSED.getValueInt().equals(status)) {
					Date closedTime = entity.getClosedTime();
					if(null == closedTime) {
						entity.setClosedTime(positionRef.getClosedTime());
					}
					entity.setRemark(positionRef.getRemark());
				}
				
				entity.setSubTitle(positionRef.getSubTitle());
				entity.setTitle(positionRef.getTitle());
				entity.setTopLevel(positionRef.getTopLevel());
				save(entity);
				positionRef.setSuccess(PositionRef.SUCCESS_YES);
			}
		}
		return positionRef;
	}
	
	@Transactional
	public PositionRef openPositionRef(PositionRef positionRef) {
		Long id = positionRef.getId();
		if(null != id) {
			PositionRef entity = findById(id);
			if(null != entity) {
				entity.setLastModifiedBy(positionRef.getLastModifiedBy());
				DateTime lastModifiedDate = new DateTime();
				entity.setLastModifiedDate(lastModifiedDate);
				Integer status = positionRef.getStatus();
				entity.setStatus(status);
				if(PositionRef.STATUS_1_OPEN.getValueInt().equals(status)) {
					entity.setOpenTime(positionRef.getOpenTime());
					entity.setRemark(positionRef.getRemark());
				}else if(PositionRef.STATUS_2_CLOSED.getValueInt().equals(status)) {
					entity.setClosedTime(positionRef.getClosedTime());
					entity.setRemark(positionRef.getRemark());
				}
				positionRef=entity;
				positionRef.setSuccess(PositionRef.SUCCESS_YES);
			}
		}
		
		return positionRef;
	}
	
	@Transactional
	public PositionRef mergeSimpleSingle(PositionRef positionRef) {
		Long id = positionRef.getId();
		if (null != id) {
			PositionRef entity = findById(id);
			if (null != entity) {
				entity.setTopLevel(positionRef.getTopLevel());
				entity.setLastModifiedBy(positionRef.getLastModifiedBy());
				DateTime lastModifiedDate = new DateTime();
				entity.setLastModifiedDate(lastModifiedDate);
				save(entity);
				positionRef=entity;
				positionRef.setSuccess(PositionRef.SUCCESS_YES);
			}
		}
		return positionRef;
	}
	
	@Transactional
	public PositionRef deleteRefByBaseId(PositionRef positionRef) {
		Long baseId = positionRef.getBaseId();
		List<PositionRef> refList = positionRefDao.findByBaseId(baseId);
		if(null != refList && !refList.isEmpty()) {
			List<Long> refIdList = new ArrayList<>(refList.size());
			for(PositionRef ref : refList) {
				Long refId = ref.getId();
				refIdList.add(refId);
			}
			positionImageService.deleteByRefIdList(refIdList);
		}
		positionRef = positionRefDao.deleteRefByBaseId(positionRef);
		positionRef.setSuccess(PositionRef.SUCCESS_YES);
		return positionRef;
	}

}
