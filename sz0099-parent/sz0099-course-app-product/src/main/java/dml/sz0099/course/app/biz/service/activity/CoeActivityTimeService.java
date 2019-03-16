package dml.sz0099.course.app.biz.service.activity;

import java.util.List;
import java.util.Map;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTime;

/**
 * CoeActivityTimeService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeActivityTimeService extends GenericService<CoeActivityTime,Long>{


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
	 * @param activityTime
	 * @return
	 */
	public CoeActivityTime persistEntity(CoeActivityTime activityTime) ;
	
	
	public CoeActivityTime mergeEntity(CoeActivityTime activityTime) ; 
	
	public CoeActivityTime saveOrUpdate(CoeActivityTime activityTime) ;
	
	public Page<CoeActivityTime> findPage(CoeActivityTime activityTime, Pageable pageable) ;
	
	public CoeActivityTime findByMainId(CoeActivityTime activityTime);
	
	public CoeActivityTime addTime(CoeActivityTime activityTime) ;
	
	public CoeActivityTime addTime(CoeActivity activity) ;
	
	public CoeActivityTime deleteTime(CoeActivityTime activityTime);
	
	public Long countByMainId(Long activityId) ;
	
	public CoeActivityTime findByMainId(Long activityId);
	
	public Map<Long, CoeActivityTime>  findMapByMainIdList(List<Long> mainIdList) ;
	
	public Page<CoeActivityTime> findPageWithNotself(CoeActivityTime coeActivityTime, Pageable pageable);
	
	public CoeActivityTime mergeBeginTime(CoeActivityTime coeActivityTime);
	public CoeActivityTime mergeEndTime(CoeActivityTime coeActivityTime);
	public CoeActivityTime mergeOffTime(CoeActivityTime coeActivityTime);
	
	public CoeActivity syncFromTemplate(CoeActivity entity, CoeActivity template);
}
