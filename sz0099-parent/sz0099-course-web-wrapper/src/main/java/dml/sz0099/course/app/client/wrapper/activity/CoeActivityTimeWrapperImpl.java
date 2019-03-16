package dml.sz0099.course.app.client.wrapper.activity;

import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.activity.CoeActivityTimeDelegate;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTime;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTime;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeActivityTimeWrapperImpl,组件封装
 * @author bruce yang at 2018-08-29 21:12:56
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Service
public class CoeActivityTimeWrapperImpl implements CoeActivityTimeWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityTimeWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeActivityTimeDelegate coeActivityTimeDelegate;
	
	/**
	 * 根据Id查询CoeActivityTime实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityTime findById(Long id) {
		LOGGER.debug("--- CoeActivityTimeWrapperImpl.findById begin --------- id is:{} ", id);
		CoeActivityTime coeActivityTime = coeActivityTimeDelegate.findById(id);
		LOGGER.debug("--- CoeActivityTimeWrapperImpl.findById end --------- id is:{} , result is {} ", id, coeActivityTime);
		return coeActivityTime;
	}
	
	public CoeActivityTime findById(Long id, boolean withJoinItem) {
		LOGGER.debug("--- CoeActivityTimeWrapperImpl.findById begin --------- id is:{} ", id);
		CoeActivityTime coeActivityTime = coeActivityTimeDelegate.findById(id, withJoinItem);
		LOGGER.debug("--- CoeActivityTimeWrapperImpl.findById end --------- id is:{} , result is {} ", id, coeActivityTime);
		return coeActivityTime;
	}
	
	@Override
	public CoeActivityTime findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityTime coeActivityTime = coeActivityTimeDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityTime);
		return coeActivityTime;
	}
	
	/**
	 * 根据IdList查询CoeActivityTime实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityTime> findByIdList(List<Long> idList) {
		LOGGER.debug("--- CoeActivityTimeWrapperImpl.findByIdList begin ---------  ");
		List<CoeActivityTime> coeActivityTimeList = coeActivityTimeDelegate.findByIdList(idList);
		LOGGER.debug("--- CoeActivityTimeWrapperImpl.findByIdList end ---------  result is {} ",  coeActivityTimeList);
		return coeActivityTimeList;
	}
	
	@Override
	public CoeActivityTime persistEntity(CoeActivityTime coeActivityTime) {
		LOGGER.debug("--- CoeActivityTimeWrapperImpl.persistEntity begin ---------  ");
		CoeActivityTime entity = coeActivityTimeDelegate.persistEntity(coeActivityTime);
		Long id = coeActivityTime.getId();
		LOGGER.debug("--- CoeActivityTimeWrapperImpl.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeActivityTime mergeEntity(CoeActivityTime coeActivityTime) {
		Long id = coeActivityTime.getId();
		LOGGER.debug("--- CoeActivityTimeWrapperImpl.mergeEntity begin, id is {} ---------  ",id);
		CoeActivityTime entity = coeActivityTimeDelegate.mergeEntity(coeActivityTime);
		LOGGER.debug("--- CoeActivityTimeWrapperImpl.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeActivityTime saveOrUpdate(CoeActivityTime coeActivityTime) {
		Long id = coeActivityTime.getId();
		LOGGER.debug("--- CoeActivityTimeWrapperImpl.saveOrUpdate begin, id is {} ---------  ",id);
		CoeActivityTime entity = coeActivityTimeDelegate.saveOrUpdate(coeActivityTime);
		LOGGER.debug("--- CoeActivityTimeWrapperImpl.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeActivityTime> findPage(CoeActivityTime coeActivityTime, Pageable pageable) {
		LOGGER.debug("--- CoeActivityTimeWrapperImpl.findPage ---------  ");
		Page<CoeActivityTime> page = coeActivityTimeDelegate.findPage(coeActivityTime, pageable);
		return page;
	}

	@Override
	public CoeActivityTime findByMainId(CoeActivityTime coeActivityTime) {
		
		return coeActivityTimeDelegate.findByMainId(coeActivityTime);
	}
	
	public CoeActivityTime addTime(CoeActivityTime coeActivityTime) {
		return coeActivityTimeDelegate.addTime(coeActivityTime);
	}

	@Override
	public CoeActivityTime deleteTime(CoeActivityTime coeActivityTime) {
		return coeActivityTimeDelegate.deleteTime(coeActivityTime);
	}

	@Override
	public Long countByMainId(Long activityId) {
		return coeActivityTimeDelegate.countByMainId(activityId);
	}
	
	public CoeActivityTime findByMainId(Long activityId) {
		return coeActivityTimeDelegate.findByMainId(activityId);
	}
	
	@Override
	public Map<Long, CoeActivityTime> findMapByMainIdList(List<Long> activityIdList) {
		return coeActivityTimeDelegate.findMapByMainIdList(activityIdList);
	}
	
	public Page<CoeActivityTime> findPageWithNotself(CoeActivityTime coeActivityTime, Pageable pageable){
		return coeActivityTimeDelegate.findPageWithNotself(coeActivityTime, pageable);
	}

	@Override
	public CoeActivityTime mergeBeginTime(CoeActivityTime coeActivityTime) {
		CoeActivityTime entity = coeActivityTimeDelegate.mergeBeginTime(coeActivityTime);
		return entity;
	}

	@Override
	public CoeActivityTime mergeEndTime(CoeActivityTime coeActivityTime) {
		CoeActivityTime entity = coeActivityTimeDelegate.mergeEndTime(coeActivityTime);
		return entity;
	}

	@Override
	public CoeActivityTime mergeOffTime(CoeActivityTime coeActivityTime) {
		CoeActivityTime entity = coeActivityTimeDelegate.mergeOffTime(coeActivityTime);
		return entity;
	}
}
