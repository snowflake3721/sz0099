package dml.sz0099.course.app.persist.dao.activity;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityFee;

/**
 * CoeActivityFeeDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeActivityFeeDao extends GenericDao<CoeActivityFee,Long>{

	/**
	 * 根据Id查询CoeActivityFee实体对象
	 * @param id
	 * @return
	 */
	CoeActivityFee findById(Long id);
	
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeActivityFee findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeActivityFee实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeActivityFee> findByIdList(List<Long> idList);
	
	public Page<CoeActivityFee> findPage(CoeActivityFee coeActivityFee, Pageable pageable);
	
	public Long countByMainId(Long activityId);
	
	public CoeActivityFee findByMainId(Long activityId);
	
	public List<CoeActivityFee> findByMainIdList(List<Long> activityIdList);
	
	public Page<CoeActivityFee> findPageWithNotself(CoeActivityFee coeActivityFee, Pageable pageable);
	
}
