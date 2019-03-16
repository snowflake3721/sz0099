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

import dml.sz0099.course.app.persist.dao.article.CoeArticlePositionImageDao;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePosition;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePositionImage;


/**
 * 
 * @formatter:off
 * description: CoeArticlePositionImageServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeArticlePositionImageServiceImpl extends GenericServiceImpl<CoeArticlePositionImage, Long> implements CoeArticlePositionImageService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeArticlePositionImageServiceImpl.class);

	@Autowired
	private CoeArticlePositionImageDao coeArticlePositionDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeArticlePositionDao;
	}

	/**
	 * 根据Id查询CoeArticlePositionImage实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeArticlePositionImage findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeArticlePositionImage coeArticlePosition = coeArticlePositionDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeArticlePosition);
		return coeArticlePosition;
	}
	
	@Override
	public CoeArticlePositionImage findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeArticlePositionImage coeArticlePosition = coeArticlePositionDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeArticlePosition);
		return coeArticlePosition;
	}

	/**
	 * 根据IdList查询CoeArticlePositionImage实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeArticlePositionImage> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeArticlePositionImage> coeArticlePositionList = coeArticlePositionDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeArticlePositionList);
		return coeArticlePositionList;
	}

	@Transactional
	@Override
	public CoeArticlePositionImage persistEntity(CoeArticlePositionImage coeArticlePosition) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeArticlePositionImage entity = save(coeArticlePosition);
		Long id = coeArticlePosition.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeArticlePositionImage.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeArticlePositionImage mergeEntity(CoeArticlePositionImage coeArticlePosition) {
		Long id = coeArticlePosition.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeArticlePositionImage entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeArticlePosition.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeArticlePositionImage.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeArticlePositionImage saveOrUpdate(CoeArticlePositionImage coeArticlePosition) {
		Long id = coeArticlePosition.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeArticlePositionImage entity = null;
		if(null != id) {
			entity = mergeEntity(coeArticlePosition);
		}else {
			entity = persistEntity(coeArticlePosition);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeArticlePositionImage> findPage(CoeArticlePositionImage coeArticlePosition, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeArticlePositionImage> page = coeArticlePositionDao.findPage(coeArticlePosition, pageable);
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
	public CoeArticlePositionImage findByMainIdAndUserId(Long mainId, Long userId) {
		return coeArticlePositionDao.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<CoeArticlePositionImage> findByMainId(Long mainId, Pageable pageable) {
		return coeArticlePositionDao.findByMainId( mainId,  pageable);
	}
	
	public boolean hasPositionByMainIdAndUserId(Long mainId, Long userId) {
		CoeArticlePositionImage entity = findByMainIdAndUserId(mainId,userId);
		if(null != entity) {
			return true;
		}
		return false;
	}

	@Transactional
	@Override
	public CoeArticlePositionImage mergeForPosition(CoeArticlePositionImage coeArticlePosition) {
		Long mainId = coeArticlePosition.getMainId();
		Long userId = coeArticlePosition.getUserId();
		CoeArticlePositionImage entity = findByMainIdAndUserId( mainId,  userId);
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
		List<CoeArticlePositionImage> bannerList = articlePosition.getBannerList();
		if(null != bannerList && !bannerList.isEmpty()) {
			bannerList = save(bannerList);
			articlePosition.setBannerList(bannerList);
		}
		return articlePosition;
	}

	@Transactional
	@Override
	public CoeArticlePosition mergeForPosition(CoeArticlePosition articlePosition) {
		List<CoeArticlePositionImage> bannerImageList = articlePosition.getBannerList();
		Long positionId = articlePosition.getId();
		if(null != bannerImageList) {
			Map<Long, CoeArticlePositionImage> existMap = findMapByRefId(positionId);
		
			List<CoeArticlePositionImage> needSaveList = new ArrayList<>();
			for(CoeArticlePositionImage image : bannerImageList) {
				Long photoId = image.getPhotoId();
				CoeArticlePositionImage exist = existMap.get(photoId);
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
				Collection<CoeArticlePositionImage> values = existMap.values();
				delete(values);
			}
			bannerImageList=save(needSaveList);
			articlePosition.setBannerList(bannerImageList);
		}
		
		return articlePosition;
	}
	
	public List<CoeArticlePositionImage> findByRefId(Long refId) {
		return coeArticlePositionDao.findByRefId(refId);
	}
	
	@Override
	public List<CoeArticlePositionImage> findByRefIdList(List<Long> refIdList) {
		List<CoeArticlePositionImage> refList = coeArticlePositionDao.findByRefIdList(refIdList);
		return refList;
	}
	
	public Map<Long, CoeArticlePositionImage> findMapByRefId(Long positionId) {
		List<CoeArticlePositionImage> existList = findByRefId(positionId);
		Map<Long, CoeArticlePositionImage> map = new HashMap<>();
		if(null != existList) {
			for(CoeArticlePositionImage image : existList) {
				Long photoId = image.getPhotoId();
				map.put(photoId, image);
			}
		}
		return map;
	}

	
	@Transactional
	@Override
	public void deleteByRefIdList(List<Long> refIdList) {
		coeArticlePositionDao.deleteByRefIdList(refIdList);
	}
	
	@Transactional
	@Override
	public void deleteByRefId(Long refId) {
		coeArticlePositionDao.deleteByRefId(refId);
	}

}
