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

import dml.sz0099.course.app.persist.dao.profession.ProfessionPositionImageDao;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPosition;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPositionCover;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPositionImage;


/**
 * 
 * @formatter:off
 * description: ProfessionPositionImageServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class ProfessionPositionImageServiceImpl extends GenericServiceImpl<ProfessionPositionImage, Long> implements ProfessionPositionImageService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionPositionImageServiceImpl.class);

	@Autowired
	private ProfessionPositionImageDao professionPositionDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = professionPositionDao;
	}

	/**
	 * 根据Id查询ProfessionPositionImage实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionPositionImage findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		ProfessionPositionImage professionPosition = professionPositionDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, professionPosition);
		return professionPosition;
	}
	
	@Override
	public ProfessionPositionImage findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionPositionImage professionPosition = professionPositionDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, professionPosition);
		return professionPosition;
	}

	/**
	 * 根据IdList查询ProfessionPositionImage实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionPositionImage> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<ProfessionPositionImage> professionPositionList = professionPositionDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", professionPositionList);
		return professionPositionList;
	}

	@Transactional
	@Override
	public ProfessionPositionImage persistEntity(ProfessionPositionImage professionPosition) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		ProfessionPositionImage entity = save(professionPosition);
		Long id = professionPosition.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(ProfessionPositionImage.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public ProfessionPositionImage mergeEntity(ProfessionPositionImage professionPosition) {
		Long id = professionPosition.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		ProfessionPositionImage entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(professionPosition.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(ProfessionPositionImage.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public ProfessionPositionImage saveOrUpdate(ProfessionPositionImage professionPosition) {
		Long id = professionPosition.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionPositionImage entity = null;
		if(null != id) {
			entity = mergeEntity(professionPosition);
		}else {
			entity = persistEntity(professionPosition);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionPositionImage> findPage(ProfessionPositionImage professionPosition, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<ProfessionPositionImage> page = professionPositionDao.findPage(professionPosition, pageable);
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
	public ProfessionPositionImage findByMainIdAndUserId(Long mainId, Long userId) {
		return professionPositionDao.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<ProfessionPositionImage> findByMainId(Long mainId, Pageable pageable) {
		return professionPositionDao.findByMainId( mainId,  pageable);
	}
	
	public boolean hasPositionByMainIdAndUserId(Long mainId, Long userId) {
		ProfessionPositionImage entity = findByMainIdAndUserId(mainId,userId);
		if(null != entity) {
			return true;
		}
		return false;
	}

	@Transactional
	@Override
	public ProfessionPositionImage mergeForPosition(ProfessionPositionImage professionPosition) {
		Long mainId = professionPosition.getMainId();
		Long userId = professionPosition.getUserId();
		ProfessionPositionImage entity = findByMainIdAndUserId( mainId,  userId);
		if(null != entity) {
			//entity.setNickname(professionPosition.getNickname());
			//entity.setHeadImg(professionPosition.getHeadImg());
			entity.setLastModifiedBy(professionPosition.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			entity = save(entity);
		}else {
			entity = save(professionPosition);
		}
		return entity;
	}
	
	@Transactional
	@Override
	public ProfessionPosition addPositionImage(ProfessionPosition professionPosition) {
		List<ProfessionPositionImage> bannerList = professionPosition.getBannerList();
		if(null != bannerList && !bannerList.isEmpty()) {
			bannerList = save(bannerList);
			professionPosition.setBannerList(bannerList);
		}
		return professionPosition;
	}

	@Transactional
	@Override
	public ProfessionPosition mergeForPosition(ProfessionPosition professionPosition) {
		List<ProfessionPositionImage> bannerImageList = professionPosition.getBannerList();
		Long positionId = professionPosition.getId();
		if(null != bannerImageList) {
			Map<Long, ProfessionPositionImage> existMap = findMapByRefId(positionId);
		
			List<ProfessionPositionImage> needSaveList = new ArrayList<>();
			for(ProfessionPositionImage image : bannerImageList) {
				Long photoId = image.getPhotoId();
				ProfessionPositionImage exist = existMap.get(photoId);
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
				Collection<ProfessionPositionImage> values = existMap.values();
				delete(values);
			}
			bannerImageList=save(needSaveList);
			professionPosition.setBannerList(bannerImageList);
		}
		
		return professionPosition;
	}
	
	public List<ProfessionPositionImage> findByRefId(Long refId) {
		return professionPositionDao.findByRefId(refId);
	}
	
	public List<ProfessionPositionImage> findByRefIdList(List<Long> refIdList) {
		List<ProfessionPositionImage> refList = professionPositionDao.findByRefIdList(refIdList);
		return refList;
	}
	
	public Map<Long, ProfessionPositionImage> findMapByRefId(Long positionId) {
		List<ProfessionPositionImage> existList = findByRefId(positionId);
		Map<Long, ProfessionPositionImage> map = new HashMap<>();
		if(null != existList) {
			for(ProfessionPositionImage image : existList) {
				Long photoId = image.getPhotoId();
				map.put(photoId, image);
			}
		}
		return map;
	}

	
	@Transactional
	@Override
	public void deleteByRefIdList(List<Long> refIdList) {
		professionPositionDao.deleteByRefIdList(refIdList);
	}
	
	@Transactional
	@Override
	public void deleteByRefId(Long refId) {
		professionPositionDao.deleteByRefId(refId);
	}

}
