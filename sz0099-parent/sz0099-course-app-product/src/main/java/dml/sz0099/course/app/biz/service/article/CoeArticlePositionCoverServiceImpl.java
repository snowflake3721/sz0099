package dml.sz0099.course.app.biz.service.article;

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

import dml.sz0099.course.app.persist.dao.article.CoeArticlePositionCoverDao;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePosition;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePositionCover;


/**
 * 
 * @formatter:off
 * description: CoeArticlePositionCoverServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeArticlePositionCoverServiceImpl extends GenericServiceImpl<CoeArticlePositionCover, Long> implements CoeArticlePositionCoverService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeArticlePositionCoverServiceImpl.class);

	@Autowired
	private CoeArticlePositionCoverDao coeArticlePositionCoverDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeArticlePositionCoverDao;
	}

	/**
	 * 根据Id查询CoeArticlePositionCover实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeArticlePositionCover findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeArticlePositionCover coeArticlePosition = coeArticlePositionCoverDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeArticlePosition);
		return coeArticlePosition;
	}
	
	@Override
	public CoeArticlePositionCover findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeArticlePositionCover coeArticlePosition = coeArticlePositionCoverDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeArticlePosition);
		return coeArticlePosition;
	}

	/**
	 * 根据IdList查询CoeArticlePositionCover实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeArticlePositionCover> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeArticlePositionCover> coeArticlePositionList = coeArticlePositionCoverDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeArticlePositionList);
		return coeArticlePositionList;
	}

	@Transactional
	@Override
	public CoeArticlePositionCover persistEntity(CoeArticlePositionCover coeArticlePosition) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeArticlePositionCover entity = save(coeArticlePosition);
		Long id = coeArticlePosition.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeArticlePositionCover.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeArticlePositionCover mergeEntity(CoeArticlePositionCover coeArticlePosition) {
		Long id = coeArticlePosition.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeArticlePositionCover entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeArticlePosition.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeArticlePositionCover.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeArticlePositionCover saveOrUpdate(CoeArticlePositionCover coeArticlePosition) {
		Long id = coeArticlePosition.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeArticlePositionCover entity = null;
		if(null != id) {
			entity = mergeEntity(coeArticlePosition);
		}else {
			entity = persistEntity(coeArticlePosition);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeArticlePositionCover> findPage(CoeArticlePositionCover coeArticlePosition, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeArticlePositionCover> page = coeArticlePositionCoverDao.findPage(coeArticlePosition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeArticlePositionCoverDao.existById(id);
	}

	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return coeArticlePositionCoverDao.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public CoeArticlePositionCover findByMainIdAndUserId(Long mainId, Long userId) {
		return coeArticlePositionCoverDao.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<CoeArticlePositionCover> findByMainId(Long mainId, Pageable pageable) {
		return coeArticlePositionCoverDao.findByMainId( mainId,  pageable);
	}
	
	public boolean hasPositionByMainIdAndUserId(Long mainId, Long userId) {
		CoeArticlePositionCover entity = findByMainIdAndUserId(mainId,userId);
		if(null != entity) {
			return true;
		}
		return false;
	}

	@Transactional
	@Override
	public CoeArticlePositionCover mergeForPosition(CoeArticlePositionCover coeArticlePosition) {
		Long mainId = coeArticlePosition.getMainId();
		Long userId = coeArticlePosition.getUserId();
		CoeArticlePositionCover entity = findByMainIdAndUserId( mainId,  userId);
		if(null != entity) {
			//entity.setNickname(coeArticlePosition.getNickname());
			//entity.setHeadImg(coeArticlePosition.getHeadImg());
			entity.setLastModifiedBy(coeArticlePosition.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			entity = save(entity);
		}else {
			entity = save(coeArticlePosition);
		}
		return entity;
	}
	
	@Transactional
	@Override
	public CoeArticlePosition addPositionImage(CoeArticlePosition articlePosition) {
		List<CoeArticlePositionCover> bannerList = articlePosition.getCoverList();
		if(null != bannerList && !bannerList.isEmpty()) {
			bannerList = save(bannerList);
			articlePosition.setCoverList(bannerList);
		}
		return articlePosition;
	}

	@Transactional
	@Override
	public CoeArticlePosition mergeForPosition(CoeArticlePosition articlePosition) {
		List<CoeArticlePositionCover> bannerImageList = articlePosition.getCoverList();
		Long positionId = articlePosition.getId();
		if(null != bannerImageList) {
			Map<Long, CoeArticlePositionCover> existMap = findMapByRefId(positionId);
		
			List<CoeArticlePositionCover> needSaveList = new ArrayList<>();
			for(CoeArticlePositionCover image : bannerImageList) {
				Long photoId = image.getPhotoId();
				CoeArticlePositionCover exist = existMap.get(photoId);
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
				Collection<CoeArticlePositionCover> values = existMap.values();
				delete(values);
			}
			bannerImageList=save(needSaveList);
			articlePosition.setCoverList(bannerImageList);
		}
		
		return articlePosition;
	}
	
	public Map<Long, CoeArticlePositionCover> findMapByRefId(Long positionId) {
		List<CoeArticlePositionCover> existList = findByRefId(positionId);
		Map<Long, CoeArticlePositionCover> map = new HashMap<>();
		if(null != existList) {
			for(CoeArticlePositionCover image : existList) {
				Long photoId = image.getPhotoId();
				map.put(photoId, image);
			}
		}
		return map;
	}

	
	@Transactional
	@Override
	public void deleteByRefIdList(List<Long> refIdList) {
		coeArticlePositionCoverDao.deleteByRefIdList(refIdList);
	}
	
	@Transactional
	@Override
	public void deleteByRefId(Long refId) {
		coeArticlePositionCoverDao.deleteByRefId(refId);
	}
	
	public List<CoeArticlePositionCover> findByRefId(Long refId) {
		return coeArticlePositionCoverDao.findByRefId(refId);
	}
	
	@Override
	public List<CoeArticlePositionCover> findByRefIdList(List<Long> refIdList) {
		List<CoeArticlePositionCover> refList = coeArticlePositionCoverDao.findByRefIdList(refIdList);
		return refList;
	}

}
