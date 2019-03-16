package dml.sz0099.course.app.biz.service.media;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

import dml.sz0099.course.app.persist.dao.media.ImageRefDao;
import dml.sz0099.course.app.persist.entity.media.ImageRef;
import dml.sz0099.course.app.persist.entity.media.Imagebase;


/**
 * 
 * @formatter:off
 * description: ImageRefServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class ImageRefServiceImpl extends GenericServiceImpl<ImageRef, Long> implements ImageRefService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageRefServiceImpl.class);

	@Autowired
	private ImageRefDao imageRefDao;
	
	@Autowired
	private ImageExtendService imageExtendService;
	
	@Autowired
	private ImagebaseService imagebaseService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = imageRefDao;
	}

	/**
	 * 根据Id查询ImageRef实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ImageRef findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		ImageRef imageRef = imageRefDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, imageRef);
		return imageRef;
	}
	
	@Override
	public ImageRef findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		ImageRef imageRef = imageRefDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, imageRef);
		return imageRef;
	}

	/**
	 * 根据IdList查询ImageRef实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ImageRef> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<ImageRef> imageRefList = imageRefDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", imageRefList);
		return imageRefList;
	}

	@Transactional
	@Override
	public ImageRef persistEntity(ImageRef imageRef) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		ImageRef entity = save(imageRef);
		Long id = imageRef.getId();
		entity.setOrderSeq(entity.getAid());
		save(entity);
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(ImageRef.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public ImageRef mergeEntity(ImageRef imageRef) {
		Long id = imageRef.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		ImageRef entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(imageRef.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(ImageRef.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public ImageRef saveOrUpdate(ImageRef imageRef) {
		Long id = imageRef.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		ImageRef entity = null;
		if(null != id) {
			entity = mergeEntity(imageRef);
		}else {
			entity = persistEntity(imageRef);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ImageRef> findPage(ImageRef imageRef, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<ImageRef> page = imageRefDao.findPage(imageRef, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return imageRefDao.existById(id);
	}
	
	public Long countForMain(ImageRef imageRef) {
		return imageRefDao.countForMain(imageRef);
	}
	
	public Long countForSub(ImageRef imageRef) {
		return imageRefDao.countForSub(imageRef);
	}
	
	public Long countForBase(ImageRef imageRef) {
		return imageRefDao.countForBase(imageRef);
	}
	
	@Transactional
	public List<ImageRef> persistEntity(List<ImageRef> imageRefList) {
		if(null != imageRefList) {
			List<ImageRef> entityList = new ArrayList<>();
			for(ImageRef ref : imageRefList) {
				ref=save(ref);
				ref.setOrderSeq(ref.getAid());
				save(ref);
				entityList.add(ref);
			}
			return entityList;
		}
		return imageRefList;
	}
	
	public Long findPositionId(Long id) {
		ImageRef entity = findById(id);
		Long extendId = entity.getExtendId();
		Long positionId = imageExtendService.findPositionIdById(extendId);
		return positionId;
	}
	
	@Transactional
	public ImageRef mergeForTitle(ImageRef imageRef) {
		Long id = imageRef.getId();
		ImageRef entity = findById(id);
		if(null != entity ) {
			entity.setOrderSeq(imageRef.getOrderSeq());
			entity.setTitle(imageRef.getTitle());
			entity.setName(imageRef.getName());
			entity.setDescription(imageRef.getDescription());
			entity.setLastModifiedBy(imageRef.getLastModifiedBy());
			entity.setSuccess(ImageRef.SUCCESS_YES);
			save(entity);
			return entity;
		}else {
			entity = imageRef;
			entity.setSuccess(ImageRef.SUCCESS_NO);
		}
		return entity;
	}
	
	public void deleteById(ImageRef imageRef, boolean isEntity) {
		Long id = imageRef.getId();
		ImageRef entity = imageRef;
		if(!isEntity) {
			entity = findById(id);
		}
		Long userId = imageRef.getUserId();
		Long entityUserId = entity.getUserId();
		LOGGER.debug(">>>>>>deleteById {} :userId {} vs {} entityUserId , delete", id, userId, entityUserId);
		if(entityUserId.equals(userId)) {
			LOGGER.debug(">>>>>>deleteById {}:delete begin... ",id);
			Long baseId = entity.getBaseId();
			Imagebase imagebase = imagebaseService.findById(baseId);
			
			boolean candeleted = true;
			Integer subRefNum = imagebase.getSubRefNum();
			if(null != subRefNum && subRefNum>1) {
				imagebase.setSubRefNum(subRefNum-1);
				candeleted=false;
			}
			Integer mainRefNum = imagebase.getMainRefNum();
			if(null != mainRefNum && mainRefNum>1) {
				imagebase.setMainRefNum(mainRefNum-1);
				candeleted=false;
			}
			
			if(candeleted) {
				LOGGER.debug(">>>>>>imagebaseService imagebase.setDeleted(true), Imagebase.id:{} , ImageRef.id:{} ... ",baseId,id);
				imagebase.setDeleted(true);
				imagebase.setLastModifiedBy(entity.getLastModifiedBy());
				imagebase.setLastModifiedDate(new DateTime());
				imagebaseService.save(imagebase);
			}
			delete(entity);
		}
	}
	@Transactional
	public void deleteById(ImageRef imageRef) {
		deleteById(imageRef, false);
	}
	
	public ImageRef deleteByMainIdAndSubId(ImageRef imageRef) {
		List<ImageRef> entityList = findMainOrSub(imageRef);
		if(null != entityList  && !entityList.isEmpty()) {
			for(ImageRef entity : entityList) {
				deleteById(entity, true);
			}
		}
		imageRef.setSuccess(ImageRef.SUCCESS_YES);
		return imageRef;
	}
	
	public List<ImageRef> findMainOrSub(ImageRef imageRef) {
		
		List<ImageRef> content = null;
		if(null != imageRef) {
			Long extendId = imageRef.getExtendId();
			Long mainId = imageRef.getMainId();
			Long subId = imageRef.getSubId();
			
			if(null != mainId) {
				if(subId!=null) {
					content = findByExtendIdAndMainId(extendId,mainId,subId);
				}else {
					content = findByExtendIdAndMainId(extendId,mainId);
				}
			}
		}
		return content;
	}

	/**
	 * @param extendId
	 * @param mainId
	 * @return
	 */
	private List<ImageRef> findByExtendIdAndMainId(Long extendId, Long mainId) {

		List<ImageRef> content = imageRefDao.findByExtendIdAndMainId(extendId, mainId);
		return content;
	}

	/**
	 * @param extendId
	 * @param mainId
	 * @param subId
	 * @return
	 */
	private List<ImageRef> findByExtendIdAndMainId(Long extendId, Long mainId, Long subId) {
		List<ImageRef> content = imageRefDao.findByExtendIdAndMainId(extendId, mainId, subId);
		return content;
	}

}
