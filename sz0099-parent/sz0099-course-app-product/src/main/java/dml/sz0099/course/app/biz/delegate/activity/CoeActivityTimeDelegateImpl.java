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

import dml.sz0099.course.app.biz.service.activity.CoeActivityTimeService;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTime;

/**
 * coeActivityTimeServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeActivityTimeDelegateImpl implements CoeActivityTimeDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityTimeDelegateImpl.class);
	
	@Autowired
	private CoeActivityTimeService coeActivityTimeService;

	/**
	 * 根据Id查询CoeActivityTime实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityTime findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeActivityTime coeActivityTime = coeActivityTimeService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeActivityTime);
		return coeActivityTime;
	}
	
	public CoeActivityTime findById(Long id, boolean withJoinItem) {
		CoeActivityTime coeActivityTime = coeActivityTimeService.findById(id,withJoinItem);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeActivityTime);
		return coeActivityTime;
	}
	
	@Override
	public CoeActivityTime findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityTime coeActivityTime = coeActivityTimeService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityTime);
		return coeActivityTime;
	}
	
	/**
	 * 根据IdList查询CoeActivityTime实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityTime> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeActivityTime> coeActivityTimeList = coeActivityTimeService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeActivityTimeList);
		return coeActivityTimeList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeActivityTime persistEntity(CoeActivityTime coeActivityTime) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeActivityTime entity = coeActivityTimeService.persistEntity(coeActivityTime);
		Long id = coeActivityTime.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeActivityTime mergeEntity(CoeActivityTime coeActivityTime) {
		Long id = coeActivityTime.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeActivityTime entity = coeActivityTimeService.mergeEntity(coeActivityTime);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeActivityTime saveOrUpdate(CoeActivityTime coeActivityTime) {
		Long id = coeActivityTime.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeActivityTime entity = coeActivityTimeService.saveOrUpdate(coeActivityTime);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeActivityTime> findPage(CoeActivityTime coeActivityTime, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeActivityTime> page = coeActivityTimeService.findPage(coeActivityTime, pageable);
		return page;
	}

	@Override
	public CoeActivityTime findByMainId(CoeActivityTime coeActivityTime) {
		return coeActivityTimeService.findByMainId(coeActivityTime);
	}
	
	public CoeActivityTime addTime(CoeActivityTime coeActivityTime) {
		return coeActivityTimeService.addTime(coeActivityTime);
	}
	
	public CoeActivityTime deleteTime(CoeActivityTime coeActivityTime) {
		return coeActivityTimeService.deleteTime(coeActivityTime);
	}
	
	public Long countByMainId(Long activityId) {
		return coeActivityTimeService.countByMainId(activityId);
	}

	@Override
	public CoeActivityTime findByMainId(Long activityId) {
		return coeActivityTimeService.findByMainId(activityId);
	}
	
	public Map<Long, CoeActivityTime>  findMapByMainIdList(List<Long> mainIdList) {
		return coeActivityTimeService.findMapByMainIdList(mainIdList);
	}
	
	public Page<CoeActivityTime> findPageWithNotself(CoeActivityTime coeActivityTime, Pageable pageable){
		return coeActivityTimeService.findPageWithNotself(coeActivityTime, pageable);
	}
	
	public CoeActivityTime mergeBeginTime(CoeActivityTime coeActivityTime) {
		return coeActivityTimeService.mergeBeginTime(coeActivityTime);
	}
	public CoeActivityTime mergeEndTime(CoeActivityTime coeActivityTime) {
		return coeActivityTimeService.mergeEndTime(coeActivityTime);
	}
	public CoeActivityTime mergeOffTime(CoeActivityTime coeActivityTime) {
		return coeActivityTimeService.mergeOffTime(coeActivityTime);
	}
}
