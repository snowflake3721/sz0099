package dml.sz0099.course.app.persist.dao.activity;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityTime;

/**
 * CoeActivityTimeDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeActivityTimeDao extends GenericDao<CoeActivityTime,Long>{

	/**
	 * 根据Id查询CoeActivityTime实体对象
	 * @param id
	 * @return
	 */
	CoeActivityTime findById(Long id);
	
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeActivityTime findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeActivityTime实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeActivityTime> findByIdList(List<Long> idList);
	
	public Page<CoeActivityTime> findPage(CoeActivityTime coeActivityTime, Pageable pageable);
	
	public Long countByMainId(Long activityId);
	
	public CoeActivityTime findByMainId(Long activityId);
	
	public List<CoeActivityTime> findByMainIdList(List<Long> activityIdList);
	
	public Page<CoeActivityTime> findPageWithNotself(CoeActivityTime coeActivityTime, Pageable pageable);
	
}
