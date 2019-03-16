package dml.sz0099.course.app.biz.service.profession;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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

import dml.sz0099.course.app.persist.dao.profession.ProfessionRefDao;
import dml.sz0099.course.app.persist.entity.profession.ProfessionCover;
import dml.sz0099.course.app.persist.entity.profession.ProfessionImage;
import dml.sz0099.course.app.persist.entity.profession.ProfessionRef;


/**
 * 
 * @formatter:off
 * description: ProfessionRefServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class ProfessionRefServiceImpl extends GenericServiceImpl<ProfessionRef, Long> implements ProfessionRefService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionRefServiceImpl.class);

	@Autowired
	private ProfessionRefDao professionRefDao;
	
	@Autowired
	private ProfessionImageService professionImageService;
	
	@Autowired
	private ProfessionCoverService professionCoverService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = professionRefDao;
	}

	/**
	 * 根据Id查询ProfessionRef实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionRef findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		ProfessionRef professionRef = professionRefDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, professionRef);
		return professionRef;
	}
	
	@Override
	public ProfessionRef findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionRef professionRef = professionRefDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, professionRef);
		return professionRef;
	}

	/**
	 * 根据IdList查询ProfessionRef实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionRef> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<ProfessionRef> professionRefList = professionRefDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", professionRefList);
		return professionRefList;
	}

	@Transactional
	@Override
	public ProfessionRef persistEntity(ProfessionRef professionRef) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		ProfessionRef entity = save(professionRef);
		Long id = professionRef.getId();
		entity.setOrderSeq(entity.getAid());
		entity.setTopLevel(entity.getAid().intValue());
		save(entity);
		professionImageService.addProfessionImage(professionRef);
		professionCoverService.addProfessionCover(professionRef);
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(ProfessionRef.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public ProfessionRef mergeEntity(ProfessionRef professionRef) {
		Long id = professionRef.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		ProfessionRef entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(professionRef.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(ProfessionRef.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public ProfessionRef saveOrUpdate(ProfessionRef professionRef) {
		Long id = professionRef.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionRef entity = null;
		if(null != id) {
			entity = mergeEntity(professionRef);
		}else {
			entity = persistEntity(professionRef);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionRef> findPage(ProfessionRef professionRef, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<ProfessionRef> page = professionRefDao.findPage(professionRef, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return professionRefDao.existById(id);
	}
	
	@Transactional
	@Override
	public void deleteByBaseId(Long baseId) {
		professionRefDao.deleteByBaseId(baseId);
	}
	
	@Transactional
	@Override
	public void deleteByBaseId(Long baseId, boolean cascade) {
		if(cascade) {
			List<ProfessionRef> entityList = findByBaseId(baseId);
			if(null != entityList && !entityList.isEmpty()) {
				List<Long> refIdList = new ArrayList<>();
				for(ProfessionRef ref : entityList) {
					Long id = ref.getId();
					refIdList.add(id);
				}
				professionImageService.deleteByRefIdList(refIdList);
				professionCoverService.deleteByRefIdList(refIdList);
			}
		}
		professionRefDao.deleteByBaseId(baseId);
	}
	
	@Transactional
	@Override
	public void deleteByBaseIdList(List<Long> baseIdList, boolean cascade) {
		if(cascade) {
			List<ProfessionRef> entityList = findByBaseIdList(baseIdList);
			if(null != entityList && !entityList.isEmpty()) {
				List<Long> refIdList = new ArrayList<>();
				for(ProfessionRef ref : entityList) {
					Long id = ref.getId();
					refIdList.add(id);
				}
				professionImageService.deleteByRefIdList(refIdList);
				professionCoverService.deleteByRefIdList(refIdList);
			}
			delete(entityList);
		}
		//professionRefDao.deleteByBaseIdList(baseIdList);
	}
	
	public Long countForBase(ProfessionRef professionRef) {
		Long baseId = professionRef.getBaseId();
		return professionRefDao.countForBase(professionRef);
	}
	
	public List<ProfessionRef> findByMainId(Long mainId) {
		return professionRefDao.findByMainId(mainId);
	}
	
	@Transactional
	@Override
	public ProfessionRef changeSingle(ProfessionRef professionRef) {
		Long baseId = professionRef.getBaseId();
		ProfessionRef entity = findMainIdAndBaseId(professionRef);
		if(null != entity) {
			entity.setBaseId(baseId);
			entity.setUserId(professionRef.getUserId());
			entity.setLastModifiedBy(professionRef.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			entity.setExtendId(professionRef.getExtendId());
			entity.setPositionId(professionRef.getPositionId());
			entity.setMainNo(professionRef.getMainNo());
			
			entity.setAuthorId(professionRef.getAuthorId());
			entity.setAuthorname(professionRef.getAuthorname());
			entity.setCoverImage(professionRef.getCoverImage());
			
			entity.setDescription(professionRef.getDescription());
			entity.setLink(professionRef.getLink());
			entity.setOriginalLink(professionRef.getOriginalLink());
			entity.setName(professionRef.getName());
			entity.setOrderSeq(professionRef.getOrderSeq());
			entity.setPreIntro(professionRef.getPreIntro());
			entity.setPreIntroType(professionRef.getPreIntroType());
			entity.setStatus(professionRef.getStatus());
			entity.setSubTitle(professionRef.getSubTitle());
			entity.setTitle(professionRef.getTitle());
			entity.setTopLevel(professionRef.getTopLevel());
			professionImageService.mergeForProfession(professionRef);
			professionCoverService.mergeForProfession(professionRef);
			professionRef=save(entity);
			professionRef.setSuccess(ProfessionRef.SUCCESS_YES);
		}else {
			professionRef=persistEntity(professionRef);
			professionRef.setSuccess(ProfessionRef.SUCCESS_YES);
		}
		
		return professionRef;
	}

	@Override
	public List<ProfessionRef> findByBaseId(Long baseId) {
		List<ProfessionRef> entityList = professionRefDao.findByBaseId(baseId);
		return entityList;
	}
	
	public List<ProfessionRef> findByBaseIdList(List<Long> baseIdList) {
		List<ProfessionRef> entityList = professionRefDao.findByBaseIdList(baseIdList);
		return entityList;
	}
	
	public List<ProfessionRef> findByBaseId(Long baseId, boolean cascade){
		List<ProfessionRef> entityList = findByBaseId(baseId);
		if(null != entityList && cascade) {
			fillImages(entityList, true , true);
		}
		return entityList;
	}
	
	public Page<ProfessionRef> findPageByBaseId(Long baseId, Pageable pageable) {
		return professionRefDao.findPageByBaseId(baseId, pageable);
	}
	
	public Page<ProfessionRef> findPageByBaseId(Long baseId, Pageable pageable, boolean withCover, boolean withBanner) {
		Page<ProfessionRef> page = findPageByBaseId(baseId, pageable);
		List<ProfessionRef> entityList = page.getContent();
		if(withCover || withBanner) {
			fillImages(entityList, withCover, withBanner);
		}
		return page;
	}

	/**
	 * @param entityList
	 */
	public Map<Long, ProfessionRef> fillImages(List<ProfessionRef> entityList, boolean withCover, boolean withBanner) {
		Map<Long, ProfessionRef> map = new HashMap<>();
		List<Long> refIdList = new ArrayList<>();
		for(ProfessionRef ref : entityList) {
			Long refId = ref.getId();
			refIdList.add(refId);
			map.put(refId, ref);
		}
		if(!refIdList.isEmpty()) {
			if(withBanner) {
				List<ProfessionImage> refList = professionImageService.findByRefIdList(refIdList);
				if(null != refList) {
					for(ProfessionImage p : refList) {
						Long refId = p.getRefId();
						ProfessionRef ref = map.get(refId);
						if(null != ref) {
							List<ProfessionImage> bannerList = ref.getBannerList();
							if(null == bannerList) {
								bannerList = new ArrayList<>();
								ref.setBannerList(bannerList);
							}
							bannerList.add(p);
						}
					}
				}
			}
			
			if(withCover) {
				List<ProfessionCover> refCoverList = professionCoverService.findByRefIdList(refIdList);
				if(null != refCoverList) {
					for(ProfessionCover p : refCoverList) {
						Long refId = p.getRefId();
						ProfessionRef ref = map.get(refId);
						if(null != ref) {
							List<ProfessionCover> coverList = ref.getCoverList();
							if(null == coverList) {
								coverList = new ArrayList<>();
								ref.setCoverList(coverList);
							}
							coverList.add(p);
						}
					}
				}
			}
		}
		return map;
	}
	
	public List<ProfessionRef> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList) {
		return professionRefDao.findByBaseIdAndMainIdList( baseId, mainIdList) ;
	}
	
	public ProfessionRef findMainIdAndBaseId(ProfessionRef professionRef) {
		return professionRefDao.findMainIdAndBaseId(professionRef);
	}
	
	@Transactional
	public void deleteById(ProfessionRef professionRef) {
		Long id = professionRef.getId();
		ProfessionRef entity = findById(id);
		if(null != entity) {
			professionImageService.deleteByRefId(id);
			delete(entity);
		}
	}
	
	@Transactional
	public void deleteByBaseId(ProfessionRef professionRef) {
		Long baseId = professionRef.getBaseId();
		deleteByBaseId(baseId, true);
	}
	
	@Transactional
	public ProfessionRef mergeProfessionRef(ProfessionRef professionRef) {
		Long id = professionRef.getId();
		if(null != id) {
			ProfessionRef entity = findById(id);
			if(null != entity) {
				entity.setLastModifiedBy(professionRef.getLastModifiedBy());
				DateTime lastModifiedDate = new DateTime();
				entity.setLastModifiedDate(lastModifiedDate);
				entity.setLink(professionRef.getLink());
				entity.setOriginalLink(professionRef.getOriginalLink());
				entity.setName(professionRef.getName());
				entity.setOrderSeq(professionRef.getOrderSeq());
				entity.setPreIntro(professionRef.getPreIntro());
				entity.setPreIntroType(professionRef.getPreIntroType());
				Integer status = professionRef.getStatus();
				entity.setStatus(status);
				if(ProfessionRef.STATUS_1_OPEN.getValueInt().equals(status)) {
					Date openTime = entity.getOpenTime();
					if(null == openTime) {
						entity.setOpenTime(professionRef.getOpenTime());
						entity.setRefreshTime(openTime);
					}
					entity.setRemark(professionRef.getRemark());
				}else if(ProfessionRef.STATUS_2_CLOSED.getValueInt().equals(status)) {
					Date closedTime = entity.getClosedTime();
					if(null == closedTime) {
						entity.setClosedTime(professionRef.getClosedTime());
					}
					entity.setRemark(professionRef.getRemark());
				}
				
				entity.setSubTitle(professionRef.getSubTitle());
				entity.setTitle(professionRef.getTitle());
				entity.setTopLevel(professionRef.getTopLevel());
				save(entity);
				professionRef.setSuccess(ProfessionRef.SUCCESS_YES);
			}
		}
		return professionRef;
	}
	
	@Transactional
	public ProfessionRef openProfessionRef(ProfessionRef professionRef) {
		Long id = professionRef.getId();
		if(null != id) {
			ProfessionRef entity = findById(id);
			if(null != entity) {
				entity.setLastModifiedBy(professionRef.getLastModifiedBy());
				DateTime lastModifiedDate = new DateTime();
				entity.setLastModifiedDate(lastModifiedDate);
				Integer status = professionRef.getStatus();
				entity.setStatus(status);
				if(ProfessionRef.STATUS_1_OPEN.getValueInt().equals(status)) {
					entity.setOpenTime(professionRef.getOpenTime());
					entity.setRemark(professionRef.getRemark());
				}else if(ProfessionRef.STATUS_2_CLOSED.getValueInt().equals(status)) {
					entity.setClosedTime(professionRef.getClosedTime());
					entity.setRemark(professionRef.getRemark());
				}
				professionRef=entity;
				professionRef.setSuccess(ProfessionRef.SUCCESS_YES);
			}
		}
		
		return professionRef;
	}
	
	@Transactional
	public ProfessionRef mergeSimpleSingle(ProfessionRef professionRef) {
		Long id = professionRef.getId();
		if (null != id) {
			ProfessionRef entity = findById(id);
			if (null != entity) {
				entity.setTopLevel(professionRef.getTopLevel());
				entity.setLastModifiedBy(professionRef.getLastModifiedBy());
				DateTime lastModifiedDate = new DateTime();
				entity.setLastModifiedDate(lastModifiedDate);
				save(entity);
				professionRef=entity;
				professionRef.setSuccess(ProfessionRef.SUCCESS_YES);
			}
		}
		return professionRef;
	}
	
	@Transactional
	public ProfessionRef deleteRefByBaseId(ProfessionRef professionRef) {
		Long baseId = professionRef.getBaseId();
		List<ProfessionRef> refList = professionRefDao.findByBaseId(baseId);
		if(null != refList && !refList.isEmpty()) {
			List<Long> refIdList = new ArrayList<>(refList.size());
			for(ProfessionRef ref : refList) {
				Long refId = ref.getId();
				refIdList.add(refId);
			}
			professionImageService.deleteByRefIdList(refIdList);
		}
		professionRef = professionRefDao.deleteRefByBaseId(professionRef);
		professionRef.setSuccess(ProfessionRef.SUCCESS_YES);
		return professionRef;
	}

}
