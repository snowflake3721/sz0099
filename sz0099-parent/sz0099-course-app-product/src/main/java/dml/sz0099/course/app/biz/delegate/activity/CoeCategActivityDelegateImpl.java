package dml.sz0099.course.app.biz.delegate.activity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.activity.CoeCategActivityService;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeCategActivity;

/**
 * coeCategActivityServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeCategActivityDelegateImpl implements CoeCategActivityDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeCategActivityDelegateImpl.class);
	
	@Autowired
	private CoeCategActivityService coeCategActivityService;

	/**
	 * 根据Id查询CoeCategActivity实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeCategActivity findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeCategActivity coeCategActivity = coeCategActivityService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeCategActivity);
		return coeCategActivity;
	}
	
	@Override
	public CoeCategActivity findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeCategActivity coeCategActivity = coeCategActivityService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeCategActivity);
		return coeCategActivity;
	}
	
	/**
	 * 根据IdList查询CoeCategActivity实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeCategActivity> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeCategActivity> coeCategActivityList = coeCategActivityService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeCategActivityList);
		return coeCategActivityList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeCategActivity persistEntity(CoeCategActivity coeCategActivity) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeCategActivity entity = coeCategActivityService.persistEntity(coeCategActivity);
		Long id = coeCategActivity.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeCategActivity mergeEntity(CoeCategActivity coeCategActivity) {
		Long id = coeCategActivity.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeCategActivity entity = coeCategActivityService.mergeEntity(coeCategActivity);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeCategActivity saveOrUpdate(CoeCategActivity coeCategActivity) {
		Long id = coeCategActivity.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeCategActivity entity = coeCategActivityService.saveOrUpdate(coeCategActivity);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeCategActivity> findPage(CoeCategActivity coeCategActivity, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeCategActivity> page = coeCategActivityService.findPage(coeCategActivity, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeCategActivityService.existById(id);
	}
	
	public Map<Long, List<CoeCategActivity>> findMapByMainIdList(List<Long> activityIdList) {
		return coeCategActivityService.findMapByMainIdList(activityIdList);
	}
	
	public List<CoeCategActivity> findByMainId(Long activityId){
		return coeCategActivityService.findByMainId(activityId);
	}
	
	public CoeCategActivity changeCategory(CoeCategActivity coeCategActivity) {
		return coeCategActivityService.changeCategory(coeCategActivity);
	}
	
	public Page<CoeCategActivity> findPageForPublish(CoeCategActivity coeCategActivity, Pageable pageable) {
		return coeCategActivityService.findPageForPublish(coeCategActivity, pageable);
	}
	
	public Page<CoeCategActivity> findPageForPublishFromDetail(CoeCategActivity coeCategActivity, Pageable pageable){
		return coeCategActivityService.findPageForPublishFromDetail( coeCategActivity,  pageable);
	}
	
	public Page<CoeCategActivity> findPageForPublishWithChildren(CoeCategActivity coeCategActivity, List<Long> baseIdList, List<Long> excludeMainIdList, Pageable pageable){
		return coeCategActivityService.findPageForPublishWithChildren(coeCategActivity, baseIdList, excludeMainIdList, pageable);
	}
	public Page<CoeCategActivity> findPageForPublish(CoeCategActivity coeCategActivity, List<Long> excludeMainIdList, Pageable pageable){
		return coeCategActivityService.findPageForPublish(coeCategActivity, excludeMainIdList, pageable);
	}
}
