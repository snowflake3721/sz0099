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

import dml.sz0099.course.app.persist.dao.position.PositionImageDao;
import dml.sz0099.course.app.persist.entity.position.PositionImage;
import dml.sz0099.course.app.persist.entity.position.PositionRef;


/**
 * 
 * @formatter:off
 * description: PositionImageServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class PositionImageServiceImpl extends GenericServiceImpl<PositionImage, Long> implements PositionImageService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PositionImageServiceImpl.class);

	@Autowired
	private PositionImageDao positionImageDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = positionImageDao;
	}

	/**
	 * 根据Id查询PositionImage实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public PositionImage findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		PositionImage positionImage = positionImageDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, positionImage);
		return positionImage;
	}
	
	@Override
	public PositionImage findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		PositionImage positionImage = positionImageDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, positionImage);
		return positionImage;
	}

	/**
	 * 根据IdList查询PositionImage实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<PositionImage> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<PositionImage> positionImageList = positionImageDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", positionImageList);
		return positionImageList;
	}

	@Transactional
	@Override
	public PositionImage persistEntity(PositionImage positionImage) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		PositionImage entity = save(positionImage);
		Long id = positionImage.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(PositionImage.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public PositionImage mergeEntity(PositionImage positionImage) {
		Long id = positionImage.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		PositionImage entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(positionImage.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(PositionImage.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public PositionImage saveOrUpdate(PositionImage positionImage) {
		Long id = positionImage.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		PositionImage entity = null;
		if(null != id) {
			entity = mergeEntity(positionImage);
		}else {
			entity = persistEntity(positionImage);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PositionImage> findPage(PositionImage positionImage, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<PositionImage> page = positionImageDao.findPage(positionImage, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return positionImageDao.existById(id);
	}

	@Override
	public List<PositionImage> findByRefIdList(List<Long> refIdList) {
		List<PositionImage> refList = positionImageDao.findByRefIdList(refIdList);
		return refList;
	}

	@Transactional
	@Override
	public void deleteByRefIdList(List<Long> refIdList) {
		positionImageDao.deleteByRefIdList(refIdList);
	}
	
	@Transactional
	@Override
	public void deleteByRefId(Long refId) {
		positionImageDao.deleteByRefId(refId);
	}
	
	@Transactional
	@Override
	public PositionRef addPositionImage(PositionRef positionRef) {
		List<PositionImage> bannerList = positionRef.getBannerList();
		if(null != bannerList && !bannerList.isEmpty()) {
			bannerList = save(bannerList);
			positionRef.setBannerList(bannerList);
		}
		return positionRef;
	}
	
	@Transactional
	@Override
	public PositionRef mergeForPosition(PositionRef positionRef) {
		List<PositionImage> bannerImageList = positionRef.getBannerList();
		Long positionId = positionRef.getId();
		if(null != bannerImageList) {
			Map<Long, PositionImage> existMap = findMapByRefId(positionId);
		
			List<PositionImage> needSaveList = new ArrayList<>();
			for(PositionImage image : bannerImageList) {
				Long photoId = image.getPhotoId();
				PositionImage exist = existMap.get(photoId);
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
				Collection<PositionImage> values = existMap.values();
				delete(values);
			}
			bannerImageList=save(needSaveList);
			positionRef.setBannerList(bannerImageList);
		}
		
		return positionRef;
	}
	
	public Map<Long, PositionImage> findMapByRefId(Long refId) {
		List<PositionImage> existList = findByRefId(refId);
		Map<Long, PositionImage> map = new HashMap<>();
		if(null != existList) {
			for(PositionImage image : existList) {
				Long photoId = image.getPhotoId();
				map.put(photoId, image);
			}
		}
		return map;
	}
	
	public List<PositionImage> findByRefId(Long refId) {
		return positionImageDao.findByRefId(refId);
	}

}
