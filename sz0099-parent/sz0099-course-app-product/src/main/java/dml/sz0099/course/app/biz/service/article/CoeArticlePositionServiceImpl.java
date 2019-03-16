package dml.sz0099.course.app.biz.service.article;

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

import dml.sz0099.course.app.persist.dao.article.CoeArticlePositionDao;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePosition;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePositionCover;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePositionImage;
import dml.sz0099.course.app.persist.entity.position.PositionRef;


/**
 * 
 * @formatter:off
 * description: CoeArticlePositionServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeArticlePositionServiceImpl extends GenericServiceImpl<CoeArticlePosition, Long> implements CoeArticlePositionService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeArticlePositionServiceImpl.class);

	@Autowired
	private CoeArticlePositionDao coeArticlePositionDao;
	
	@Autowired
	private CoeArticlePositionImageService coeArticlePositionImageService;
	
	@Autowired
	private CoeArticlePositionCoverService coeArticlePositionCoverService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeArticlePositionDao;
	}

	/**
	 * 根据Id查询CoeArticlePosition实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeArticlePosition findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeArticlePosition coeArticlePosition = coeArticlePositionDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeArticlePosition);
		return coeArticlePosition;
	}
	
	@Override
	public CoeArticlePosition findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeArticlePosition coeArticlePosition = coeArticlePositionDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeArticlePosition);
		return coeArticlePosition;
	}

	/**
	 * 根据IdList查询CoeArticlePosition实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeArticlePosition> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeArticlePosition> coeArticlePositionList = coeArticlePositionDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeArticlePositionList);
		return coeArticlePositionList;
	}

	@Transactional
	@Override
	public CoeArticlePosition persistEntity(CoeArticlePosition coeArticlePosition) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeArticlePosition entity = save(coeArticlePosition);
		Long id = coeArticlePosition.getId();
		entity.setOrderSeq(entity.getAid());
		entity.setTopLevel(PositionRef.TOP_LEVEL_1);
		save(entity);
		coeArticlePositionImageService.addPositionImage(coeArticlePosition);
		coeArticlePositionCoverService.addPositionImage(coeArticlePosition);
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeArticlePosition.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeArticlePosition mergeEntity(CoeArticlePosition coeArticlePosition) {
		Long id = coeArticlePosition.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeArticlePosition entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeArticlePosition.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeArticlePosition.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeArticlePosition saveOrUpdate(CoeArticlePosition coeArticlePosition) {
		Long id = coeArticlePosition.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeArticlePosition entity = null;
		if(null != id) {
			entity = mergeEntity(coeArticlePosition);
		}else {
			entity = persistEntity(coeArticlePosition);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeArticlePosition> findPage(CoeArticlePosition coeArticlePosition, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeArticlePosition> page = coeArticlePositionDao.findPage(coeArticlePosition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeArticlePositionDao.existById(id);
	}

	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return coeArticlePositionDao.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public List<CoeArticlePosition> findByMainIdAndUserId(Long mainId, Long userId) {
		return coeArticlePositionDao.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<CoeArticlePosition> findByMainId(Long mainId, Pageable pageable) {
		return coeArticlePositionDao.findByMainId( mainId,  pageable);
	}
	
	public boolean hasPositionByMainIdAndUserId(Long mainId, Long userId) {
		List<CoeArticlePosition> entity = findByMainIdAndUserId(mainId,userId);
		if(null != entity) {
			return true;
		}
		return false;
	}
	
	public boolean hasPositionByMainIdAndPosition(Long mainId, Integer position) {
		CoeArticlePosition entity = findByMainIdAndPosition(mainId,position);
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
	public CoeArticlePosition findByMainIdAndPosition(Long mainId, Integer position) {
		return coeArticlePositionDao.findByMainIdAndPosition( mainId,  position);
	}

	@Transactional
	@Override
	public CoeArticlePosition mergeForPosition(CoeArticlePosition coeArticlePosition) {
		Long id = coeArticlePosition.getId();
		List<CoeArticlePositionImage> bannerList = coeArticlePosition.getBannerList();
		CoeArticlePosition entity = findById(id);
		if(null != entity) {
			//entity.setAuthorname(coeArticlePosition.getAuthorname());
			//entity.setAuthorId(coeArticlePosition.getAuthorId());
			
			entity.setNickname(coeArticlePosition.getNickname());
			entity.setPenname(coeArticlePosition.getPenname());
			entity.setHeadImg(coeArticlePosition.getHeadImg());
			entity.setUserId(coeArticlePosition.getUserId());
			
			entity.setCoverImage(coeArticlePosition.getCoverImage());
			entity.setLink(coeArticlePosition.getLink());
			entity.setOriginalLink(coeArticlePosition.getOriginalLink());
			entity.setMainId(coeArticlePosition.getMainId());
			entity.setName(coeArticlePosition.getName());
			entity.setPreIntro(coeArticlePosition.getPreIntro());
			entity.setPreIntroType(coeArticlePosition.getPreIntroType());
			entity.setSubTitle(coeArticlePosition.getSubTitle());
			entity.setTitle(coeArticlePosition.getTitle());
			
			entity.setSaywordId(coeArticlePosition.getSaywordId());
			//t.setSayword(ref.getSayword());
			
			entity.setStatus(coeArticlePosition.getStatus());
			entity.setLastModifiedBy(coeArticlePosition.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			coeArticlePositionImageService.mergeForPosition(coeArticlePosition);
			coeArticlePositionCoverService.mergeForPosition(coeArticlePosition);
			bannerList = coeArticlePosition.getBannerList();
			entity = save(entity);
		}else {
			entity = save(coeArticlePosition);
		}
		entity.setBannerList(bannerList);
		return entity;
	}

	public List<CoeArticlePosition> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList){
		return coeArticlePositionDao.findByBaseIdAndMainIdList(baseId,mainIdList);
	}
	
	@Transactional
	public void deleteById(CoeArticlePosition coeArticlePosition) {
		Long id = coeArticlePosition.getId();
		CoeArticlePosition entity = findById(id);
		if(null != entity) {
			coeArticlePositionImageService.deleteByRefId(id);
			coeArticlePositionCoverService.deleteByRefId(id);
			delete(entity);
		}
	}
	
	@Transactional
	public CoeArticlePosition mergePositionRef(CoeArticlePosition coeArticlePosition) {
		Long id = coeArticlePosition.getId();
		if(null != id) {
			CoeArticlePosition entity = findById(id);
			if(null != entity) {
				entity.setLastModifiedBy(coeArticlePosition.getLastModifiedBy());
				DateTime lastModifiedDate = new DateTime();
				entity.setLastModifiedDate(lastModifiedDate);
				entity.setLink(coeArticlePosition.getLink());
				entity.setOriginalLink(coeArticlePosition.getOriginalLink());
				entity.setName(coeArticlePosition.getName());
				entity.setOrderSeq(coeArticlePosition.getOrderSeq());
				entity.setPreIntro(coeArticlePosition.getPreIntro());
				entity.setPreIntroType(coeArticlePosition.getPreIntroType());
				Integer status = coeArticlePosition.getStatus();
				
				if(PositionRef.STATUS_1_OPEN.getValueInt().equals(status)) {
					Date openTime = entity.getOpenTime();
					if(null == openTime) {
						entity.setOpenTime(coeArticlePosition.getOpenTime());
						entity.setRefreshTime(openTime);
					}
					entity.setRemark(coeArticlePosition.getRemark());
				}else if(PositionRef.STATUS_2_CLOSED.getValueInt().equals(status)) {
					Date closedTime = entity.getClosedTime();
					if(null == closedTime) {
						entity.setClosedTime(coeArticlePosition.getClosedTime());
					}
					entity.setRemark(coeArticlePosition.getRemark());
				}
				entity.setStatus(coeArticlePosition.getStatus());
				entity.setSubTitle(coeArticlePosition.getSubTitle());
				entity.setTitle(coeArticlePosition.getTitle());
				entity.setSaywordId(coeArticlePosition.getSaywordId());
				entity.setTopLevel(coeArticlePosition.getTopLevel());
				save(entity);
				coeArticlePosition.setSuccess(PositionRef.SUCCESS_YES);
			}
		}
		return coeArticlePosition;
	}
	
	@Transactional
	public CoeArticlePosition openPositionRef(CoeArticlePosition positionRef) {
		Long id = positionRef.getId();
		if (null != id) {
			CoeArticlePosition entity = findById(id);
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
	public CoeArticlePosition mergeSimpleSingle(CoeArticlePosition positionRef) {
		Long id = positionRef.getId();
		if (null != id) {
			CoeArticlePosition entity = findById(id);
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
	
	public List<CoeArticlePosition> findByBaseId(CoeArticlePosition positionRef) {
		Long baseId = positionRef.getBaseId();
		List<CoeArticlePosition> refList = coeArticlePositionDao.findByBaseId(baseId);
		return refList;
	}
	
	@Transactional
	public CoeArticlePosition deleteRefByBaseId(CoeArticlePosition positionRef) {
		Long baseId = positionRef.getBaseId();
		List<CoeArticlePosition> refList = coeArticlePositionDao.findByBaseId(baseId);
		if(null != refList && !refList.isEmpty()) {
			List<Long> refIdList = new ArrayList<>(refList.size());
			for(CoeArticlePosition ref : refList) {
				Long refId = ref.getId();
				refIdList.add(refId);
			}
			coeArticlePositionImageService.deleteByRefIdList(refIdList);
			coeArticlePositionCoverService.deleteByRefIdList(refIdList);
		}
		positionRef = coeArticlePositionDao.deleteRefByBaseId(positionRef);
		positionRef.setSuccess(PositionRef.SUCCESS_YES);
		return positionRef;
	}
	
	public Page<CoeArticlePosition> findPageForPosition(Long ponMainId,Long ponSubId, Long positionId, Integer ponPanel, Pageable pageable){
		return coeArticlePositionDao.findPageForPosition(ponMainId, ponSubId, positionId, ponPanel, pageable);
	}
	
	public Page<CoeArticlePosition> findByBaseId(Long baseId, Pageable pageable){
		return findByBaseId( baseId,  pageable, true, true);
	}
	public Page<CoeArticlePosition> findByBaseIdList(List<Long> baseIdList, Pageable pageable) {
		return findByBaseIdList(baseIdList,  pageable, true, true);
	}
	
	public Page<CoeArticlePosition> findByBaseId(Long baseId, Pageable pageable, boolean withCover, boolean withImage){
		Page<CoeArticlePosition> page = coeArticlePositionDao.findByBaseId( baseId,  pageable);
		if(null != page && page.getTotalElements()>0) {
			List<CoeArticlePosition> content = page.getContent();
			fillImages(content, withCover, withImage);
		}
		return page;
	}
	
	public Page<CoeArticlePosition> findByBaseIdList(List<Long> baseIdList, Pageable pageable, boolean withCover, boolean withImage) {
		Page<CoeArticlePosition> page = coeArticlePositionDao.findByBaseIdList( baseIdList,  pageable);
		if(null != page && page.getTotalElements()>0) {
			List<CoeArticlePosition> content = page.getContent();
			fillImages(content, withCover, withImage);
		}
		return page;
	}
	
	
	
	/**
	 * @param entityList
	 */
	public Map<Long, CoeArticlePosition> fillImages(List<CoeArticlePosition> entityList, boolean withCover, boolean withImage) {
		Map<Long, CoeArticlePosition> map = new HashMap<>();
		List<Long> refIdList = new ArrayList<>();
		for(CoeArticlePosition ref : entityList) {
			Long refId = ref.getId();
			refIdList.add(refId);
			map.put(refId, ref);
		}
		if(!refIdList.isEmpty()) {
			if(withImage) {
			List<CoeArticlePositionImage> refList = coeArticlePositionImageService.findByRefIdList(refIdList);
			if(null != refList) {
				for(CoeArticlePositionImage p : refList) {
					Long refId = p.getRefId();
					CoeArticlePosition ref = map.get(refId);
					if(null != ref) {
						List<CoeArticlePositionImage> bannerList = ref.getBannerList();
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
			List<CoeArticlePositionCover> refCoverList = coeArticlePositionCoverService.findByRefIdList(refIdList);
			if(null != refCoverList) {
				for(CoeArticlePositionCover p : refCoverList) {
					Long refId = p.getRefId();
					CoeArticlePosition ref = map.get(refId);
					if(null != ref) {
						List<CoeArticlePositionCover> bannerList = ref.getCoverList();
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
