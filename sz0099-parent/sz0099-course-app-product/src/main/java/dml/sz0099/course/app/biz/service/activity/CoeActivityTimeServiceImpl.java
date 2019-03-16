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

import dml.sz0099.course.app.biz.service.tag.CoeTagService;
import dml.sz0099.course.app.persist.dao.activity.CoeActivityTimeDao;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTime;
import dml.sz0099.course.app.persist.entity.activity.CoeCategActivity;
import dml.sz0099.course.app.persist.entity.activity.JoinItem;


/**
 * 
 * @formatter:off
 * description: CoeActivityTimeServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeActivityTimeServiceImpl extends GenericServiceImpl<CoeActivityTime, Long> implements CoeActivityTimeService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityTimeServiceImpl.class);

	@Autowired
	private CoeActivityTimeDao coeActivityTimeDao;
	
	@Autowired
	private CoeTagService coeTagService;
	
	@Autowired
	private CoeActivityService coeActivityService;
	
	@Autowired
	private JoinItemService joinItemService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeActivityTimeDao;
	}

	/**
	 * 根据Id查询CoeActivityTime实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityTime findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeActivityTime coeActivityTime = coeActivityTimeDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeActivityTime);
		return coeActivityTime;
	}
	
	@Override
	public CoeActivityTime findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityTime coeActivityTime = coeActivityTimeDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityTime);
		return coeActivityTime;
	}

	/**
	 * 根据IdList查询CoeActivityTime实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityTime> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeActivityTime> coeActivityTimeList = coeActivityTimeDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeActivityTimeList);
		return coeActivityTimeList;
	}

	@Transactional
	@Override
	public CoeActivityTime persistEntity(CoeActivityTime coeActivityTime) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeActivityTime entity = save(coeActivityTime);
		Long id = coeActivityTime.getId();
		entity.setOrderSeq(entity.getAid());
		
		//初始一个集合时间、集合地点
		joinItemService.addJoinItem(entity);
		
		save(entity);
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeActivityTime.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeActivityTime mergeEntity(CoeActivityTime coeActivityTime) {
		Long id = coeActivityTime.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeActivityTime entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeActivityTime.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeActivityTime.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeActivityTime saveOrUpdate(CoeActivityTime coeActivityTime) {
		Long id = coeActivityTime.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeActivityTime entity = null;
		if(null != id) {
			entity = mergeEntity(coeActivityTime);
		}else {
			entity = persistEntity(coeActivityTime);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeActivityTime> findPage(CoeActivityTime coeActivityTime, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeActivityTime> page = coeActivityTimeDao.findPage(coeActivityTime, pageable);
		return page;
	}
	
	@Transactional
	public CoeActivityTime addTime(CoeActivityTime coeActivityTime) {
		coeActivityTime=persistEntity(coeActivityTime);
		return coeActivityTime;
	}
	
	@Transactional
	public CoeActivityTime addTime(CoeActivity coeActivity) {
		Long id = coeActivity.getId();
		Long userId = coeActivity.getUserId();
		CoeActivityTime coeActivityTime = new CoeActivityTime();
		coeActivityTime.setMainId(id);
		coeActivityTime.setCreatedBy(userId);
		coeActivityTime.setUserId(userId);
		coeActivityTime.setLastModifiedBy(userId);
		Date beginTime = new Date();
		coeActivityTime.setBeginTime(beginTime);
		coeActivityTime.setEndTime(beginTime);
		coeActivityTime.setOffTime(beginTime);
		coeActivityTime=addTime(coeActivityTime);
		return coeActivityTime;
	}
	
	@Transactional
	public CoeActivityTime deleteTime(CoeActivityTime coeActivityTime) {
		Long pTagId = coeActivityTime.getId();
		Long activityId = coeActivityTime.getMainId();
		CoeActivityTime entity = findById(pTagId);
		if (null != entity) {
			Long entityProfessionId = entity.getMainId();
			if (entityProfessionId.equals(activityId)) {
				delete(entity);
				coeActivityTime.setSuccess(CoeActivityTime.SUCCESS_YES);
			}
		}

		return coeActivityTime;
	}
	
	public CoeActivityTime findByMainId(CoeActivityTime coeActivityTime) {
		Long activityId = coeActivityTime.getMainId();
		if (null != activityId) {
			CoeActivityTime tag = coeActivityTimeDao.findByMainId(activityId);
			return tag;
		}
		return null;
	}
	
	public Long countByMainId(Long activityId) {
		return coeActivityTimeDao.countByMainId(activityId);
	}
	
	public CoeActivityTime findByMainId(Long activityId) {
		return coeActivityTimeDao.findByMainId(activityId);
	}
	
	public Map<Long, CoeActivityTime> findMapByMainIdList(List<Long> mainIdList) {
		List<CoeActivityTime> content = coeActivityTimeDao.findByMainIdList(mainIdList);
		Map<Long, CoeActivityTime> map = new HashMap<>();
		if(null != content && !content.isEmpty()) {
			for(CoeActivityTime c : content) {
				Long mainId = c.getMainId();
				map.put(mainId, c);
			}
		}
		return map;
	}
	
	public Page<CoeActivityTime> findPageWithNotself(CoeActivityTime coeActivityTime, Pageable pageable){
		Page<CoeActivityTime> page = coeActivityTimeDao.findPageWithNotself(coeActivityTime, pageable);
		if(null != page && page.getTotalElements()>0) {
			List<CoeActivityTime> content = page.getContent();
			List<Long> mainIdList = new ArrayList<>(content.size());
			Map<Long, CoeActivityTime> tagMap = new HashMap<>(content.size());
			Map<Long, CoeActivityTime> tagRepeatMap = new HashMap<>(content.size());
			for(CoeActivityTime c : content) {
				Long mainId = c.getMainId();
				if(!mainIdList.contains(mainId)) {
					mainIdList.add(mainId);
					tagMap.put(mainId, c);
				}else {
					tagRepeatMap.put(mainId, c);
				}
			}
			List<CoeActivity> mainList = coeActivityService.findPublishByIdList(mainIdList);
			if(null != mainList && !mainList.isEmpty()) {
				for(CoeActivity m : mainList) {
					Long mid = m.getId();
					CoeActivityTime mc = tagMap.get(mid);
					if(null != mc) {
						mc.setActivity(m);
					}
					CoeActivityTime mcr = tagRepeatMap.get(mid);
					if(null != mcr) {
						mcr.setActivity(m);
					}
				}
			}
		}
		return page;
	}
	
	@Transactional
	@Override
	public CoeActivityTime mergeBeginTime(CoeActivityTime coeActivityTime) {
		Long id = coeActivityTime.getId();
		CoeActivityTime entity = findById(id);
		if(null != entity) {
			entity.setBeginTime(coeActivityTime.getBeginTime());
			entity.setLastModifiedBy(coeActivityTime.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			entity.setSuccess(CoeActivityTime.SUCCESS_YES);
		}
		return entity;
	}

	@Transactional
	@Override
	public CoeActivityTime mergeEndTime(CoeActivityTime coeActivityTime) {
		Long id = coeActivityTime.getId();
		CoeActivityTime entity = findById(id);
		if(null != entity) {
			entity.setEndTime(coeActivityTime.getEndTime());
			entity.setLastModifiedBy(coeActivityTime.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			entity.setSuccess(CoeActivityTime.SUCCESS_YES);
		}
		return entity;
	}

	@Transactional
	@Override
	public CoeActivityTime mergeOffTime(CoeActivityTime coeActivityTime) {
		Long id = coeActivityTime.getId();
		CoeActivityTime entity = findById(id);
		if(null != entity) {
			entity.setOffTime(coeActivityTime.getOffTime());
			entity.setLastModifiedBy(coeActivityTime.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			entity.setSuccess(CoeActivityTime.SUCCESS_YES);
		}
		return entity;
	}
	
	@Transactional
	@Override
	public CoeActivityTime findById(Long id, boolean withJoinItem) {
		CoeActivityTime entity = findById(id);
		if(null != entity && withJoinItem) {
			List<JoinItem> joinItemList = joinItemService.findByBaseId(id);
			entity.setJoinItemList(joinItemList);
		}
		return entity;
	}
	
	@Transactional
	@Override
	public CoeActivity syncFromTemplate(CoeActivity entity, CoeActivity template) {
		if(null == entity || template==null) {
			return null;
		}
		Long mainId = entity.getId();
		Long templateId = template.getId();
		if(null != mainId && null != templateId) {
			CoeActivityTime timeEntity=findByMainId(mainId);
			
			if(null == timeEntity) {
				timeEntity = new CoeActivityTime();
				timeEntity.setCreatedBy(entity.getCreatedBy());
				timeEntity.setLastModifiedBy(entity.getLastModifiedBy());
				DateTime creationDate = new DateTime();
				timeEntity.setCreatedDate(creationDate);
				timeEntity.setLastModifiedDate(creationDate);
				timeEntity.setMainId(mainId);
			}else {
				timeEntity.setLastModifiedBy(entity.getLastModifiedBy());
				timeEntity.setLastModifiedDate(entity.getLastModifiedDate());
			}
			
			//同步模板地点
			CoeActivityTime timeActivity=findByMainId(templateId);
			if(null != timeActivity) {
				Long baseIdEntity = timeEntity.getId();
				Long baseIdTemplate = template.getId();
				List<JoinItem>  baseEntityList = joinItemService.findByBaseId(baseIdEntity);
				List<JoinItem>  baseTemplateList = joinItemService.findByBaseId(baseIdTemplate);
				if(null != baseTemplateList && !baseTemplateList.isEmpty()) {
					int i=0;
					int size = 0;
					if(null != baseEntityList && !baseEntityList.isEmpty()) {
						size = baseEntityList.size();
					}
					for(JoinItem joinItem : baseTemplateList) {
						JoinItem joinItemEntity = null;
						if(i<size) {
							joinItemEntity=baseEntityList.get(i);
						}else {
							joinItemEntity=new JoinItem();
							joinItemEntity.setCreatedBy(timeEntity.getCreatedBy());
							joinItemEntity.setCreatedDate(timeEntity.getCreatedDate());
							joinItemEntity.setLastModifiedBy(timeEntity.getLastModifiedBy());
							joinItemEntity.setLastModifiedDate(timeEntity.getLastModifiedDate());
							joinItemEntity.setMainId(mainId);
						}
						i++;
						
						joinItemEntity.setPlace(joinItem.getPlace());
						joinItemEntity.setDescription(joinItem.getDescription());
						joinItemEntity.setLastModifiedBy(entity.getLastModifiedBy());
						joinItemService.saveOrUpdate(joinItemEntity);
					}
					//清除多余项
					if(i<size) {
						for(;i<size;i++) {
							JoinItem joinItem = baseEntityList.get(i);
							joinItemService.delete(joinItem);
						}
					}
				}
			}
		}
		return entity;
	}

}
