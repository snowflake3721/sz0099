package dml.sz0099.course.app.biz.service.activity;

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

import dml.sz0099.course.app.persist.dao.activity.CoeActivityPositionDao;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPosition;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPositionCover;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPositionImage;
import dml.sz0099.course.app.persist.entity.position.PositionRef;


/**
 * 
 * @formatter:off
 * description: CoeActivityPositionServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeActivityPositionServiceImpl extends GenericServiceImpl<CoeActivityPosition, Long> implements CoeActivityPositionService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityPositionServiceImpl.class);

	@Autowired
	private CoeActivityPositionDao coeActivityPositionDao;
	
	@Autowired
	private CoeActivityPositionImageService coeActivityPositionImageService;
	
	@Autowired
	private CoeActivityPositionCoverService coeActivityPositionCoverService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeActivityPositionDao;
	}

	/**
	 * 根据Id查询CoeActivityPosition实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityPosition findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeActivityPosition coeActivityPosition = coeActivityPositionDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeActivityPosition);
		return coeActivityPosition;
	}
	
	@Override
	public CoeActivityPosition findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityPosition coeActivityPosition = coeActivityPositionDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityPosition);
		return coeActivityPosition;
	}

	/**
	 * 根据IdList查询CoeActivityPosition实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityPosition> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeActivityPosition> coeActivityPositionList = coeActivityPositionDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeActivityPositionList);
		return coeActivityPositionList;
	}

	@Transactional
	@Override
	public CoeActivityPosition persistEntity(CoeActivityPosition coeActivityPosition) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeActivityPosition entity = save(coeActivityPosition);
		Long id = coeActivityPosition.getId();
		entity.setOrderSeq(entity.getAid());
		entity.setTopLevel(PositionRef.TOP_LEVEL_1);
		save(entity);
		coeActivityPositionImageService.addPositionImage(coeActivityPosition);
		coeActivityPositionCoverService.addPositionImage(coeActivityPosition);
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeActivityPosition.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeActivityPosition mergeEntity(CoeActivityPosition coeActivityPosition) {
		Long id = coeActivityPosition.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeActivityPosition entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeActivityPosition.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeActivityPosition.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeActivityPosition saveOrUpdate(CoeActivityPosition coeActivityPosition) {
		Long id = coeActivityPosition.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeActivityPosition entity = null;
		if(null != id) {
			entity = mergeEntity(coeActivityPosition);
		}else {
			entity = persistEntity(coeActivityPosition);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeActivityPosition> findPage(CoeActivityPosition coeActivityPosition, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeActivityPosition> page = coeActivityPositionDao.findPage(coeActivityPosition, pageable);
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
	public List<CoeActivityPosition> findByMainIdAndUserId(Long mainId, Long userId) {
		return coeActivityPositionDao.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<CoeActivityPosition> findByMainId(Long mainId, Pageable pageable) {
		return coeActivityPositionDao.findByMainId( mainId,  pageable);
	}
	
	public boolean hasPositionByMainIdAndUserId(Long mainId, Long userId) {
		List<CoeActivityPosition> entity = findByMainIdAndUserId(mainId,userId);
		if(null != entity) {
			return true;
		}
		return false;
	}
	
	public boolean hasPositionByMainIdAndPosition(Long mainId, Integer position) {
		CoeActivityPosition entity = findByMainIdAndPosition(mainId,position);
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
	public CoeActivityPosition findByMainIdAndPosition(Long mainId, Integer position) {
		return coeActivityPositionDao.findByMainIdAndPosition( mainId,  position);
	}

	@Transactional
	@Override
	public CoeActivityPosition mergeForPosition(CoeActivityPosition coeActivityPosition) {
		Long id = coeActivityPosition.getId();
		List<CoeActivityPositionImage> bannerList = coeActivityPosition.getBannerList();
		CoeActivityPosition entity = findById(id);
		if(null != entity) {
			//entity.setAuthorname(coeActivityPosition.getAuthorname());
			//entity.setAuthorId(coeActivityPosition.getAuthorId());
			
			entity.setNickname(coeActivityPosition.getNickname());
			entity.setPenname(coeActivityPosition.getPenname());
			entity.setHeadImg(coeActivityPosition.getHeadImg());
			entity.setUserId(coeActivityPosition.getUserId());
			
			entity.setCoverImage(coeActivityPosition.getCoverImage());
			entity.setLink(coeActivityPosition.getLink());
			entity.setOriginalLink(coeActivityPosition.getOriginalLink());
			entity.setMainId(coeActivityPosition.getMainId());
			entity.setName(coeActivityPosition.getName());
			entity.setPreIntro(coeActivityPosition.getPreIntro());
			entity.setPreIntroType(coeActivityPosition.getPreIntroType());
			entity.setSubTitle(coeActivityPosition.getSubTitle());
			entity.setTitle(coeActivityPosition.getTitle());
			
			entity.setSaywordId(coeActivityPosition.getSaywordId());
			//t.setSayword(ref.getSayword());
			
			entity.setStatus(coeActivityPosition.getStatus());
			entity.setLastModifiedBy(coeActivityPosition.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			coeActivityPositionImageService.mergeForPosition(coeActivityPosition);
			coeActivityPositionCoverService.mergeForPosition(coeActivityPosition);
			bannerList = coeActivityPosition.getBannerList();
			entity = save(entity);
		}else {
			entity = save(coeActivityPosition);
		}
		entity.setBannerList(bannerList);
		return entity;
	}

	public List<CoeActivityPosition> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList){
		return coeActivityPositionDao.findByBaseIdAndMainIdList(baseId,mainIdList);
	}
	
	@Transactional
	public void deleteById(CoeActivityPosition coeActivityPosition) {
		Long id = coeActivityPosition.getId();
		CoeActivityPosition entity = findById(id);
		if(null != entity) {
			coeActivityPositionImageService.deleteByRefId(id);
			coeActivityPositionCoverService.deleteByRefId(id);
			delete(entity);
		}
	}
	
	@Transactional
	public CoeActivityPosition mergePositionRef(CoeActivityPosition coeActivityPosition) {
		Long id = coeActivityPosition.getId();
		if(null != id) {
			CoeActivityPosition entity = findById(id);
			if(null != entity) {
				entity.setLastModifiedBy(coeActivityPosition.getLastModifiedBy());
				DateTime lastModifiedDate = new DateTime();
				entity.setLastModifiedDate(lastModifiedDate);
				entity.setLink(coeActivityPosition.getLink());
				entity.setOriginalLink(coeActivityPosition.getOriginalLink());
				entity.setName(coeActivityPosition.getName());
				entity.setOrderSeq(coeActivityPosition.getOrderSeq());
				entity.setPreIntro(coeActivityPosition.getPreIntro());
				entity.setPreIntroType(coeActivityPosition.getPreIntroType());
				Integer status = coeActivityPosition.getStatus();
				
				if(PositionRef.STATUS_1_OPEN.getValueInt().equals(status)) {
					Date openTime = entity.getOpenTime();
					if(null == openTime) {
						entity.setOpenTime(coeActivityPosition.getOpenTime());
						entity.setRefreshTime(openTime);
					}
					entity.setRemark(coeActivityPosition.getRemark());
				}else if(PositionRef.STATUS_2_CLOSED.getValueInt().equals(status)) {
					Date closedTime = entity.getClosedTime();
					if(null == closedTime) {
						entity.setClosedTime(coeActivityPosition.getClosedTime());
					}
					entity.setRemark(coeActivityPosition.getRemark());
				}
				entity.setStatus(coeActivityPosition.getStatus());
				entity.setSubTitle(coeActivityPosition.getSubTitle());
				entity.setTitle(coeActivityPosition.getTitle());
				entity.setSaywordId(coeActivityPosition.getSaywordId());
				entity.setTopLevel(coeActivityPosition.getTopLevel());
				save(entity);
				coeActivityPosition.setSuccess(PositionRef.SUCCESS_YES);
			}
		}
		return coeActivityPosition;
	}
	
	@Transactional
	public CoeActivityPosition openPositionRef(CoeActivityPosition positionRef) {
		Long id = positionRef.getId();
		if (null != id) {
			CoeActivityPosition entity = findById(id);
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
	public CoeActivityPosition mergeSimpleSingle(CoeActivityPosition positionRef) {
		Long id = positionRef.getId();
		if (null != id) {
			CoeActivityPosition entity = findById(id);
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
	
	public List<CoeActivityPosition> findByBaseId(CoeActivityPosition positionRef) {
		Long baseId = positionRef.getBaseId();
		List<CoeActivityPosition> refList = coeActivityPositionDao.findByBaseId(baseId);
		return refList;
	}
	
	@Transactional
	public CoeActivityPosition deleteRefByBaseId(CoeActivityPosition positionRef) {
		Long baseId = positionRef.getBaseId();
		List<CoeActivityPosition> refList = coeActivityPositionDao.findByBaseId(baseId);
		if(null != refList && !refList.isEmpty()) {
			List<Long> refIdList = new ArrayList<>(refList.size());
			for(CoeActivityPosition ref : refList) {
				Long refId = ref.getId();
				refIdList.add(refId);
			}
			coeActivityPositionImageService.deleteByRefIdList(refIdList);
			coeActivityPositionCoverService.deleteByRefIdList(refIdList);
		}
		positionRef = coeActivityPositionDao.deleteRefByBaseId(positionRef);
		positionRef.setSuccess(PositionRef.SUCCESS_YES);
		return positionRef;
	}
	
	public Page<CoeActivityPosition> findPageForPosition(Long ponMainId,Long ponSubId, Long positionId, Integer ponPanel, Pageable pageable){
		return coeActivityPositionDao.findPageForPosition(ponMainId, ponSubId, positionId, ponPanel, pageable);
	}
	
	public Page<CoeActivityPosition> findByBaseId(Long baseId, Pageable pageable){
		return findByBaseId( baseId,  pageable, true, true);
	}
	public Page<CoeActivityPosition> findByBaseIdList(List<Long> baseIdList, Pageable pageable) {
		return findByBaseIdList(baseIdList,  pageable, true, true);
	}
	
	public Page<CoeActivityPosition> findByBaseId(Long baseId, Pageable pageable, boolean withCover, boolean withImage){
		Page<CoeActivityPosition> page = coeActivityPositionDao.findByBaseId( baseId,  pageable);
		if(null != page && page.getTotalElements()>0) {
			List<CoeActivityPosition> content = page.getContent();
			fillImages(content, withCover, withImage);
		}
		return page;
	}
	
	public Page<CoeActivityPosition> findByBaseIdList(List<Long> baseIdList, Pageable pageable, boolean withCover, boolean withImage) {
		Page<CoeActivityPosition> page = coeActivityPositionDao.findByBaseIdList( baseIdList,  pageable);
		if(null != page && page.getTotalElements()>0) {
			List<CoeActivityPosition> content = page.getContent();
			fillImages(content, withCover, withImage);
		}
		return page;
	}
	
	
	
	/**
	 * @param entityList
	 */
	public Map<Long, CoeActivityPosition> fillImages(List<CoeActivityPosition> entityList, boolean withCover, boolean withImage) {
		Map<Long, CoeActivityPosition> map = new HashMap<>();
		List<Long> refIdList = new ArrayList<>();
		for(CoeActivityPosition ref : entityList) {
			Long refId = ref.getId();
			refIdList.add(refId);
			map.put(refId, ref);
		}
		if(!refIdList.isEmpty()) {
			if(withImage) {
			List<CoeActivityPositionImage> refList = coeActivityPositionImageService.findByRefIdList(refIdList);
			if(null != refList) {
				for(CoeActivityPositionImage p : refList) {
					Long refId = p.getRefId();
					CoeActivityPosition ref = map.get(refId);
					if(null != ref) {
						List<CoeActivityPositionImage> bannerList = ref.getBannerList();
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
			List<CoeActivityPositionCover> refCoverList = coeActivityPositionCoverService.findByRefIdList(refIdList);
			if(null != refCoverList) {
				for(CoeActivityPositionCover p : refCoverList) {
					Long refId = p.getRefId();
					CoeActivityPosition ref = map.get(refId);
					if(null != ref) {
						List<CoeActivityPositionCover> bannerList = ref.getCoverList();
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
