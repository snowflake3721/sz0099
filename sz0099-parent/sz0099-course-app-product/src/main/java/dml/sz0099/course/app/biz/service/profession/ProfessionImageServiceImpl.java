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

import dml.sz0099.course.app.persist.dao.profession.ProfessionImageDao;
import dml.sz0099.course.app.persist.entity.profession.ProfessionImage;
import dml.sz0099.course.app.persist.entity.profession.ProfessionRef;


/**
 * 
 * @formatter:off
 * description: ProfessionImageServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class ProfessionImageServiceImpl extends GenericServiceImpl<ProfessionImage, Long> implements ProfessionImageService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionImageServiceImpl.class);

	@Autowired
	private ProfessionImageDao professionImageDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = professionImageDao;
	}

	/**
	 * 根据Id查询ProfessionImage实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionImage findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		ProfessionImage professionImage = professionImageDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, professionImage);
		return professionImage;
	}
	
	@Override
	public ProfessionImage findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionImage professionImage = professionImageDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, professionImage);
		return professionImage;
	}

	/**
	 * 根据IdList查询ProfessionImage实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionImage> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<ProfessionImage> professionImageList = professionImageDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", professionImageList);
		return professionImageList;
	}

	@Transactional
	@Override
	public ProfessionImage persistEntity(ProfessionImage professionImage) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		ProfessionImage entity = save(professionImage);
		Long id = professionImage.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(ProfessionImage.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public ProfessionImage mergeEntity(ProfessionImage professionImage) {
		Long id = professionImage.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		ProfessionImage entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(professionImage.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(ProfessionImage.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public ProfessionImage saveOrUpdate(ProfessionImage professionImage) {
		Long id = professionImage.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionImage entity = null;
		if(null != id) {
			entity = mergeEntity(professionImage);
		}else {
			entity = persistEntity(professionImage);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionImage> findPage(ProfessionImage professionImage, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<ProfessionImage> page = professionImageDao.findPage(professionImage, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return professionImageDao.existById(id);
	}

	@Override
	public List<ProfessionImage> findByRefIdList(List<Long> refIdList) {
		List<ProfessionImage> refList = professionImageDao.findByRefIdList(refIdList);
		return refList;
	}

	@Transactional
	@Override
	public void deleteByRefIdList(List<Long> refIdList) {
		professionImageDao.deleteByRefIdList(refIdList);
	}
	
	@Transactional
	@Override
	public void deleteByRefId(Long refId) {
		professionImageDao.deleteByRefId(refId);
	}
	
	@Transactional
	@Override
	public ProfessionRef addProfessionImage(ProfessionRef professionRef) {
		List<ProfessionImage> bannerList = professionRef.getBannerList();
		if(null != bannerList && !bannerList.isEmpty()) {
			bannerList = save(bannerList);
			professionRef.setBannerList(bannerList);
		}
		return professionRef;
	}
	
	@Transactional
	@Override
	public ProfessionRef mergeForProfession(ProfessionRef professionRef) {
		List<ProfessionImage> bannerImageList = professionRef.getBannerList();
		Long professionId = professionRef.getId();
		if(null != bannerImageList) {
			Map<Long, ProfessionImage> existMap = findMapByRefId(professionId);
		
			List<ProfessionImage> needSaveList = new ArrayList<>();
			for(ProfessionImage image : bannerImageList) {
				Long photoId = image.getPhotoId();
				ProfessionImage exist = existMap.get(photoId);
				if(exist != null) {
					exist.setFullurl(image.getFullurl());
					exist.setWidth(image.getWidth());
					exist.setExpectedUrl(image.getExpectedUrl());
					//exist.setImageUrl(image.getImageUrl());
					exist.setAuthorId(image.getAuthorId());
					exist.setUserId(image.getUserId());
					needSaveList.add(exist);
					existMap.remove(photoId);
				}else {
					needSaveList.add(image);
				}
			}
			if(null != existMap && !existMap.isEmpty()) {
				Collection<ProfessionImage> values = existMap.values();
				delete(values);
			}
			bannerImageList=save(needSaveList);
			professionRef.setBannerList(bannerImageList);
		}
		
		return professionRef;
	}
	
	public Map<Long, ProfessionImage> findMapByRefId(Long refId) {
		List<ProfessionImage> existList = findByRefId(refId);
		Map<Long, ProfessionImage> map = new HashMap<>();
		if(null != existList) {
			for(ProfessionImage image : existList) {
				Long photoId = image.getPhotoId();
				map.put(photoId, image);
			}
		}
		return map;
	}
	
	public List<ProfessionImage> findByRefId(Long refId) {
		return professionImageDao.findByRefId(refId);
	}

}
