package dml.sz0099.course.app.biz.service.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.jit4j.core.service.GenericServiceImpl;
import org.jit8j.core.util.CollectionUtil;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.persist.dao.activity.CoeCategActivityDao;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTag;
import dml.sz0099.course.app.persist.entity.activity.CoeCategActivity;


/**
 * 
 * @formatter:off
 * description: CoeCategActivityServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeCategActivityServiceImpl extends GenericServiceImpl<CoeCategActivity, Long> implements CoeCategActivityService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeCategActivityServiceImpl.class);

	@Autowired
	private CoeCategActivityDao coeCategActivityDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeCategActivityDao;
	}

	/**
	 * 根据Id查询CoeCategActivity实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeCategActivity findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeCategActivity coeCategActivity = coeCategActivityDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeCategActivity);
		return coeCategActivity;
	}
	
	@Override
	public CoeCategActivity findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeCategActivity coeCategActivity = coeCategActivityDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeCategActivity);
		return coeCategActivity;
	}

	/**
	 * 根据IdList查询CoeCategActivity实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeCategActivity> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeCategActivity> coeCategActivityList = coeCategActivityDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeCategActivityList);
		return coeCategActivityList;
	}

	@Transactional
	@Override
	public CoeCategActivity persistEntity(CoeCategActivity coeCategActivity) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeCategActivity entity = save(coeCategActivity);
		Long id = coeCategActivity.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeCategActivity.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeCategActivity mergeEntity(CoeCategActivity coeCategActivity) {
		Long id = coeCategActivity.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeCategActivity entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeCategActivity.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeCategActivity.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeCategActivity saveOrUpdate(CoeCategActivity coeCategActivity) {
		Long id = coeCategActivity.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeCategActivity entity = null;
		if(null != id) {
			entity = mergeEntity(coeCategActivity);
		}else {
			entity = persistEntity(coeCategActivity);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeCategActivity> findPage(CoeCategActivity coeCategActivity, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeCategActivity> page = coeCategActivityDao.findPage(coeCategActivity, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeCategActivityDao.existById(id);
	}
	
	public List<CoeCategActivity> findByMainId(Long activityId){
		return coeCategActivityDao.findByMainId(activityId);
	}

	public List<CoeCategActivity> findByMainIdList(List<Long> activityIdList) {
		return coeCategActivityDao.findByMainIdList(activityIdList);
	}
	
	public Map<Long, List<CoeCategActivity>> findMapByMainIdList(List<Long> activityIdList) {
		List<CoeCategActivity> content = findByMainIdList(activityIdList);
		Map<Long, List<CoeCategActivity>> map = null;
		if(null != content && !content.isEmpty()) {
			/*for(CoeCategActivity c : content) {
				Long mainId = c.getMainId();
				List<CoeCategActivity> entityList = map.get(mainId);
				if(null == entityList) {
					entityList = new ArrayList<>();
					map.put(mainId, entityList);
				}
				entityList.add(c);
			}*/
			map = CollectionUtil.convertList2Map(content, "mainId");
		}
		return map;
	}
	
	@Transactional
	public CoeCategActivity changeCategory(CoeCategActivity coeCategActivity) {
		Long maidId = coeCategActivity.getMainId();
		Long baseId = coeCategActivity.getBaseId();
		List<CoeCategActivity> entityList = findByMainId(maidId);
		boolean exist=false;
		if(null != entityList && !entityList.isEmpty()) {
			for(CoeCategActivity entity : entityList) {
				Long entityBaseId = entity.getBaseId();
				if(baseId.equals(entityBaseId)) {
					exist=true;
					continue;
				}
				delete(entity);
				coeCategActivity.setSuccess(CoeCategActivity.SUCCESS_YES);
			}
		}
		if(!exist) {
			coeCategActivity = save(coeCategActivity);
			coeCategActivity.setSuccess(CoeCategActivity.SUCCESS_YES);
		}
		return coeCategActivity;
	}
	
	public Page<CoeCategActivity> findPageForPublish(CoeCategActivity coeCategActivity, Pageable pageable) {
		CoeActivity activity = coeCategActivity.getActivity();
		if(null == activity) {
			activity = new CoeActivity();
			coeCategActivity.setActivity(activity);
		}
		activity.setPublishStatus(CoeActivity.PUBLISH_STATUS_PUBLISH.getValueInt());
		
		return coeCategActivityDao.findPageForPublish(coeCategActivity, pageable);
	}
	
	public Page<CoeCategActivity> findPageForPublishFromDetail(CoeCategActivity coeCategActivity, Pageable pageable){
		CoeActivity coeActivity = coeCategActivity.getActivity();
		if(null == coeActivity) {
			coeActivity = new CoeActivity();
			coeCategActivity.setActivity(coeActivity);
		}
		coeActivity.setPublishStatus(CoeActivity.PUBLISH_STATUS_PUBLISH.getValueInt());
		
		return coeCategActivityDao.findPageForPublishFromDetail(coeCategActivity, pageable);
	}
	
	public Page<CoeCategActivity> findPageForPublishWithChildren(CoeCategActivity coeCategActivity, List<Long> baseIdList, List<Long> excludeMainIdList, Pageable pageable){
		CoeActivity coeActivity = coeCategActivity.getActivity();
		if(null == coeActivity) {
			coeActivity = new CoeActivity();
			coeCategActivity.setActivity(coeActivity);
		}
		
		coeActivity.setPublishStatus(CoeActivity.PUBLISH_STATUS_PUBLISH.getValueInt());
		
		return coeCategActivityDao.findPageForPublishWithChildren(coeCategActivity, baseIdList, excludeMainIdList, pageable);
	}
	
	public Page<CoeCategActivity> findPageForPublish(CoeCategActivity coeCategActivity, List<Long> excludeMainIdList, Pageable pageable) {
		CoeActivity activity = coeCategActivity.getActivity();
		if(null == activity) {
			activity = new CoeActivity();
			coeCategActivity.setActivity(activity);
		}
		activity.setPublishStatus(CoeActivity.PUBLISH_STATUS_PUBLISH.getValueInt());
		
		return coeCategActivityDao.findPageForPublish(coeCategActivity, excludeMainIdList, pageable);
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
			List<CoeCategActivity> coeCategActivityList=findByMainId(mainId);
			//deleteInBatch(coeCategActivityList);//删除原分类
			int i=0;
			int size=0;
			if(null != coeCategActivityList && !coeCategActivityList.isEmpty()) {
				size=coeCategActivityList.size();
			}
			//同步模板分类
			List<CoeCategActivity> categActivityList=findByMainId(templateId);
			if(null != categActivityList && !categActivityList.isEmpty()) {
				List<CoeCategActivity> categEntityList = new ArrayList<>(categActivityList.size());
				for(CoeCategActivity categ : categActivityList) {
					CoeCategActivity categEntity = null;
					if(i<size) {
						categEntity = coeCategActivityList.get(i);
					}else {
						categEntity=new CoeCategActivity();
						categEntity.setCreatedBy(entity.getCreatedBy());
					}
					i++;
					categEntity.setMainId(mainId);
					categEntity.setBaseId(categ.getBaseId());
					categEntity.setCayMainId(categ.getCayMainId());
					categEntity.setCaySubId(categ.getCaySubId());
					categEntity.setLastModifiedBy(entity.getLastModifiedBy());
					categEntity=save(categEntity);
					categEntityList.add(categEntity);
				}
				// categEntityList=save(categEntityList);
				entity.setCategoryList(categEntityList);
			}
			if(i<size) {
				//清除多余项
				for(;i<size;i++) {
					CoeCategActivity categEntity = coeCategActivityList.get(i);
					delete(categEntity);
				}
			}
		}
		return entity;
	}

}
