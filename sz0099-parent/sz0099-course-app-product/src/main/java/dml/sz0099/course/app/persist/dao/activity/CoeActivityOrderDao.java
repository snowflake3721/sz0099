package dml.sz0099.course.app.persist.dao.activity;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityOrder;

/**
 * CoeActivityOrderDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeActivityOrderDao extends GenericDao<CoeActivityOrder,Long>{

	/**
	 * 根据Id查询CoeActivityOrder实体对象
	 * @param id
	 * @return
	 */
	CoeActivityOrder findById(Long id);
	
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeActivityOrder findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeActivityOrder实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeActivityOrder> findByIdList(List<Long> idList);
	
	public Page<CoeActivityOrder> findPage(CoeActivityOrder coeActivityOrder, Pageable pageable);
	
	public Long countByMainId(Long activityId);
	
	public CoeActivityOrder findByMainId(Long activityId);
	
	public CoeActivityOrder findByUserIdAndMainId(Long userId, Long mainId);
	
	public List<CoeActivityOrder> findByMainIdList(List<Long> activityIdList);
	
	public Page<CoeActivityOrder> findPageWithNotself(CoeActivityOrder coeActivityOrder, Pageable pageable);
	
	public CoeActivityOrder findByOutTradeNo(String outTradeNo);
}
