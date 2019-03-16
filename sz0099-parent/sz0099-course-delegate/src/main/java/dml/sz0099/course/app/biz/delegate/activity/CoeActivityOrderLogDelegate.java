package dml.sz0099.course.app.biz.delegate.activity;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityOrder;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityOrderLog;

/**
 * CoeActivityOrderLogService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeActivityOrderLogDelegate {


	/**
	 * 根据Id查询CoeActivityOrderLog实体对象
	 * @param id
	 * @return
	 */
	public CoeActivityOrderLog findById(Long id);
	public CoeActivityOrderLog findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeActivityOrderLog实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeActivityOrderLog> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param orderLog
	 * @return
	 */
	public CoeActivityOrderLog persistEntity(CoeActivityOrderLog orderLog) ;
	
	
	public CoeActivityOrderLog mergeEntity(CoeActivityOrderLog orderLog) ; 
	
	public CoeActivityOrderLog saveOrUpdate(CoeActivityOrderLog orderLog) ;
	
	public Page<CoeActivityOrderLog> findPage(CoeActivityOrderLog orderLog, Pageable pageable) ;
	
	public CoeActivityOrderLog findByMainId(CoeActivityOrderLog orderLog);
	
	public Long countByMainId(Long activityId) ;
	
	public CoeActivityOrderLog findByMainId(Long activityId);
	
	public Map<Long, CoeActivityOrderLog>  findMapByMainIdList(List<Long> mainIdList) ;
	public Map<Long, CoeActivityOrderLog>  findMapByOrderIdList(List<Long> orderIdList) ;
	
	public CoeActivityOrderLog create(CoeActivityOrder order) ;
	
}
