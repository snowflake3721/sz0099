package dml.sz0099.course.app.client.wrapper.activity;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityTime;


/**
 * CoeActivityTimeWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeActivityTimeWrapper {

	/**
	 * 根据Id查询CoeActivityTime实体对象
	 * @param id
	 * @return
	 */
	public CoeActivityTime findById(Long id);
	
	public CoeActivityTime findById(Long id, boolean withJoinItem);
	
	public CoeActivityTime findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeActivityTime实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeActivityTime> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeActivityTime
	 * @return
	 */
	public CoeActivityTime persistEntity(CoeActivityTime coeActivityTime) ;
	
	public CoeActivityTime mergeEntity(CoeActivityTime coeActivityTime) ; 
	
	public CoeActivityTime saveOrUpdate(CoeActivityTime coeActivityTime) ;
	
	public Page<CoeActivityTime> findPage(CoeActivityTime coeActivityTime, Pageable pageable) ; 
	
	public CoeActivityTime findByMainId(CoeActivityTime coeActivityTime);
	public Long countByMainId(Long activityId);
	public CoeActivityTime findByMainId(Long activityId);
	public Map<Long, CoeActivityTime> findMapByMainIdList(List<Long> activityIdList);
	
	public CoeActivityTime addTime(CoeActivityTime coeActivityTime);
	public CoeActivityTime deleteTime(CoeActivityTime coeActivityTime);
	
	public CoeActivityTime mergeBeginTime(CoeActivityTime coeActivityTime);
	public CoeActivityTime mergeEndTime(CoeActivityTime coeActivityTime);
	public CoeActivityTime mergeOffTime(CoeActivityTime coeActivityTime);
	
	public Page<CoeActivityTime> findPageWithNotself(CoeActivityTime coeActivityTime, Pageable pageable);
}
