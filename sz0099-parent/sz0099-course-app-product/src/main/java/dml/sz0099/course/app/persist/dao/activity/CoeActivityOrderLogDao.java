package dml.sz0099.course.app.persist.dao.activity;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityOrderLog;

/**
 * CoeActivityOrderLogDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeActivityOrderLogDao extends GenericDao<CoeActivityOrderLog,Long>{

	/**
	 * 根据Id查询CoeActivityOrderLog实体对象
	 * @param id
	 * @return
	 */
	CoeActivityOrderLog findById(Long id);
	
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeActivityOrderLog findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeActivityOrderLog实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeActivityOrderLog> findByIdList(List<Long> idList);
	
	public Page<CoeActivityOrderLog> findPage(CoeActivityOrderLog coeActivityOrderLog, Pageable pageable);
	
	public Long countByMainId(Long activityId);
	
	public CoeActivityOrderLog findByMainId(Long activityId);
	
	public List<CoeActivityOrderLog> findByMainIdList(List<Long> activityIdList);
	public List<CoeActivityOrderLog> findByOrderIdList(List<Long> orderIdList);
	
}
