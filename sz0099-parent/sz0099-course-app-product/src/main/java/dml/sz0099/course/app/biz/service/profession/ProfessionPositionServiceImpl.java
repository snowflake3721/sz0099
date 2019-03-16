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

import dml.sz0099.course.app.persist.dao.profession.ProfessionPositionDao;
import dml.sz0099.course.app.persist.entity.position.PositionRef;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPosition;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPositionCover;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPositionImage;


/**
 * 
 * @formatter:off
 * description: ProfessionPositionServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class ProfessionPositionServiceImpl extends GenericServiceImpl<ProfessionPosition, Long> implements ProfessionPositionService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionPositionServiceImpl.class);

	@Autowired
	private ProfessionPositionDao professionPositionDao;
	
	@Autowired
	private ProfessionPositionImageService professionPositionImageService;
	
	@Autowired
	private ProfessionPositionCoverService professionPositionCoverService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = professionPositionDao;
	}

	/**
	 * 根据Id查询ProfessionPosition实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionPosition findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		ProfessionPosition professionPosition = professionPositionDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, professionPosition);
		return professionPosition;
	}
	
	@Override
	public ProfessionPosition findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionPosition professionPosition = professionPositionDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, professionPosition);
		return professionPosition;
	}

	/**
	 * 根据IdList查询ProfessionPosition实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionPosition> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<ProfessionPosition> professionPositionList = professionPositionDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", professionPositionList);
		return professionPositionList;
	}

	@Transactional
	@Override
	public ProfessionPosition persistEntity(ProfessionPosition professionPosition) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		ProfessionPosition entity = save(professionPosition);
		Long id = professionPosition.getId();
		entity.setOrderSeq(entity.getAid());
		entity.setTopLevel(PositionRef.TOP_LEVEL_1);
		save(entity);
		professionPositionImageService.addPositionImage(professionPosition);
		professionPositionCoverService.addPositionCover(professionPosition);
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(ProfessionPosition.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public ProfessionPosition mergeEntity(ProfessionPosition professionPosition) {
		Long id = professionPosition.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		ProfessionPosition entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(professionPosition.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(ProfessionPosition.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public ProfessionPosition saveOrUpdate(ProfessionPosition professionPosition) {
		Long id = professionPosition.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionPosition entity = null;
		if(null != id) {
			entity = mergeEntity(professionPosition);
		}else {
			entity = persistEntity(professionPosition);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionPosition> findPage(ProfessionPosition professionPosition, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<ProfessionPosition> page = professionPositionDao.findPage(professionPosition, pageable);
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
	public List<ProfessionPosition> findByMainIdAndUserId(Long mainId, Long userId) {
		return professionPositionDao.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<ProfessionPosition> findByMainId(Long mainId, Pageable pageable) {
		return professionPositionDao.findByMainId( mainId,  pageable);
	}
	
	public boolean hasPositionByMainIdAndUserId(Long mainId, Long userId) {
		List<ProfessionPosition> entity = findByMainIdAndUserId(mainId,userId);
		if(null != entity) {
			return true;
		}
		return false;
	}
	
	public boolean hasPositionByMainIdAndPosition(Long mainId, Integer position) {
		ProfessionPosition entity = findByMainIdAndPosition(mainId,position);
		if(null != entity) {
			return true;
		}
		return false;
	}

	/**
	 * @param mainId
	 * @param position
	 * @return
	 */
	public ProfessionPosition findByMainIdAndPosition(Long mainId, Integer position) {
		return professionPositionDao.findByMainIdAndPosition( mainId,  position);
	}

	@Transactional
	@Override
	public ProfessionPosition mergeForPosition(ProfessionPosition professionPosition) {
		Long id = professionPosition.getId();
		List<ProfessionPositionImage> bannerList = professionPosition.getBannerList();
		ProfessionPosition entity = findById(id);
		if(null != entity) {
			//entity.setAuthorname(professionPosition.getAuthorname());
			//entity.setAuthorId(professionPosition.getAuthorId());
			
			entity.setNickname(professionPosition.getNickname());
			entity.setPenname(professionPosition.getPenname());
			entity.setHeadImg(professionPosition.getHeadImg());
			entity.setUserId(professionPosition.getUserId());
			
			entity.setCoverImage(professionPosition.getCoverImage());
			entity.setLink(professionPosition.getLink());
			entity.setOriginalLink(professionPosition.getOriginalLink());
			entity.setMainId(professionPosition.getMainId());
			entity.setName(professionPosition.getName());
			entity.setPreIntro(professionPosition.getPreIntro());
			entity.setPreIntroType(professionPosition.getPreIntroType());
			entity.setSubTitle(professionPosition.getSubTitle());
			entity.setTitle(professionPosition.getTitle());
			entity.setSaywordId(professionPosition.getSaywordId());
			entity.setSayword(professionPosition.getSayword());
			entity.setStatus(professionPosition.getStatus());
			entity.setLastModifiedBy(professionPosition.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			professionPositionImageService.mergeForPosition(professionPosition);
			professionPositionCoverService.mergeForPosition(professionPosition);
			bannerList = professionPosition.getBannerList();
			entity = save(entity);
		}else {
			entity = save(professionPosition);
		}
		entity.setBannerList(bannerList);
		return entity;
	}

	public List<ProfessionPosition> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList){
		return professionPositionDao.findByBaseIdAndMainIdList(baseId,mainIdList);
	}
	
	@Transactional
	public void deleteById(ProfessionPosition professionPosition) {
		Long id = professionPosition.getId();
		ProfessionPosition entity = findById(id);
		if(null != entity) {
			professionPositionImageService.deleteByRefId(id);
			professionPositionCoverService.deleteByRefId(id);
			delete(entity);
		}
	}
	
	@Transactional
	public ProfessionPosition mergePositionRef(ProfessionPosition professionPosition) {
		Long id = professionPosition.getId();
		if(null != id) {
			ProfessionPosition entity = findById(id);
			if(null != entity) {
				entity.setLastModifiedBy(professionPosition.getLastModifiedBy());
				DateTime lastModifiedDate = new DateTime();
				entity.setLastModifiedDate(lastModifiedDate);
				entity.setLink(professionPosition.getLink());
				entity.setOriginalLink(professionPosition.getOriginalLink());
				entity.setName(professionPosition.getName());
				entity.setOrderSeq(professionPosition.getOrderSeq());
				entity.setPreIntro(professionPosition.getPreIntro());
				entity.setPreIntroType(professionPosition.getPreIntroType());
				Integer status = professionPosition.getStatus();
				
				if(PositionRef.STATUS_1_OPEN.getValueInt().equals(status)) {
					Date openTime = entity.getOpenTime();
					if(null == openTime) {
						entity.setOpenTime(professionPosition.getOpenTime());
						entity.setRefreshTime(openTime);
					}
					entity.setRemark(professionPosition.getRemark());
				}else if(PositionRef.STATUS_2_CLOSED.getValueInt().equals(status)) {
					Date closedTime = entity.getClosedTime();
					if(null == closedTime) {
						entity.setClosedTime(professionPosition.getClosedTime());
					}
					entity.setRemark(professionPosition.getRemark());
				}
				entity.setSaywordId(professionPosition.getSaywordId());
				//entity.setSayword(professionPosition.getSayword());
				entity.setStatus(professionPosition.getStatus());
				entity.setSubTitle(professionPosition.getSubTitle());
				entity.setTitle(professionPosition.getTitle());
				entity.setTopLevel(professionPosition.getTopLevel());
				entity.setSaywordId(professionPosition.getSaywordId());
				save(entity);
				professionPosition.setSuccess(PositionRef.SUCCESS_YES);
			}
		}
		return professionPosition;
	}
	
	@Transactional
	public ProfessionPosition openPositionRef(ProfessionPosition positionRef) {
		Long id = positionRef.getId();
		if (null != id) {
			ProfessionPosition entity = findById(id);
			if (null != entity) {
				entity.setLastModifiedBy(positionRef.getLastModifiedBy());
				DateTime lastModifiedDate = new DateTime();
				entity.setLastModifiedDate(lastModifiedDate);
				Integer status = positionRef.getStatus();
				entity.setStatus(status);
				if (PositionRef.STATUS_1_OPEN.getValueInt().equals(status)) {
					entity.setOpenTime(positionRef.getOpenTime());
					entity.setRemark(positionRef.getRemark());
				} else if (PositionRef.STATUS_2_CLOSED.getValueInt().equals(status)) {
					entity.setClosedTime(positionRef.getClosedTime());
					entity.setRemark(positionRef.getRemark());
				}
				positionRef = entity;
				positionRef.setSuccess(PositionRef.SUCCESS_YES);
			}
		}
		return positionRef;
	}
	
	@Transactional
	public ProfessionPosition mergeSimpleSingle(ProfessionPosition positionRef) {
		Long id = positionRef.getId();
		if (null != id) {
			ProfessionPosition entity = findById(id);
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
	
	public List<ProfessionPosition> findByBaseId(ProfessionPosition positionRef) {
		Long baseId = positionRef.getBaseId();
		List<ProfessionPosition> refList = professionPositionDao.findByBaseId(baseId);
		return refList;
	}
	
	@Transactional
	public ProfessionPosition deleteRefByBaseId(ProfessionPosition positionRef) {
		Long baseId = positionRef.getBaseId();
		List<ProfessionPosition> refList = professionPositionDao.findByBaseId(baseId);
		if(null != refList && !refList.isEmpty()) {
			List<Long> refIdList = new ArrayList<>(refList.size());
			for(ProfessionPosition ref : refList) {
				Long refId = ref.getId();
				refIdList.add(refId);
			}
			professionPositionImageService.deleteByRefIdList(refIdList);
			professionPositionCoverService.deleteByRefIdList(refIdList);
		}
		positionRef = professionPositionDao.deleteRefByBaseId(positionRef);
		positionRef.setSuccess(PositionRef.SUCCESS_YES);
		return positionRef;
	}
	
	public Page<ProfessionPosition> findPageForPosition(Long ponMainId,Long ponSubId, Long positionId, Integer ponPanel, Pageable pageable){
		return professionPositionDao.findPageForPosition(ponMainId, ponSubId, positionId, ponPanel, pageable);
	}
	public Page<ProfessionPosition> findByBaseId(Long baseId, Pageable pageable){
		return findByBaseId(baseId, pageable, true, true);
	}
	public Page<ProfessionPosition> findByBaseIdList(List<Long> baseIdList, Pageable pageable){
		return findByBaseIdList(baseIdList, pageable, true, true);
	}
	
	public Page<ProfessionPosition> findByBaseId(Long baseId, Pageable pageable, boolean withCover, boolean withImage){
		Page<ProfessionPosition> page = professionPositionDao.findByBaseId( baseId,  pageable);
		if(null != page && page.getTotalElements()>0) {
			List<ProfessionPosition> content = page.getContent();
			fillImages(content, withCover, withImage);
		}
		return page;
	}
	
	public Page<ProfessionPosition> findByBaseIdList(List<Long> baseIdList, Pageable pageable, boolean withCover, boolean withImage) {
		Page<ProfessionPosition> page = professionPositionDao.findByBaseIdList( baseIdList,  pageable);
		if(null != page && page.getTotalElements()>0) {
			List<ProfessionPosition> content = page.getContent();
			fillImages(content, withCover, withImage);
		}
		return page;
	}
	
	
	
	/**
	 * @param entityList
	 */
	public Map<Long, ProfessionPosition> fillImages(List<ProfessionPosition> entityList, boolean withCover, boolean withImage) {
		Map<Long, ProfessionPosition> map = new HashMap<>();
		List<Long> refIdList = new ArrayList<>();
		for(ProfessionPosition ref : entityList) {
			Long refId = ref.getId();
			refIdList.add(refId);
			map.put(refId, ref);
		}
		if(!refIdList.isEmpty()) {
			if(withImage) {
			List<ProfessionPositionImage> refList = professionPositionImageService.findByRefIdList(refIdList);
			if(null != refList) {
				for(ProfessionPositionImage p : refList) {
					Long refId = p.getRefId();
					ProfessionPosition ref = map.get(refId);
					if(null != ref) {
						List<ProfessionPositionImage> bannerList = ref.getBannerList();
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
			List<ProfessionPositionCover> refCoverList = professionPositionCoverService.findByRefIdList(refIdList);
			if(null != refCoverList) {
				for(ProfessionPositionCover p : refCoverList) {
					Long refId = p.getRefId();
					ProfessionPosition ref = map.get(refId);
					if(null != ref) {
						List<ProfessionPositionCover> bannerList = ref.getCoverList();
						if(null == bannerList) {
							bannerList = new ArrayList<>();
							ref.setCoverList(bannerList);
						}
						bannerList.add(p);
					}
				}
			}
			}
			
		}
		return map;
	}
}
