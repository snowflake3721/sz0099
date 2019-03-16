package dml.sz0099.course.app.persist.dao.order;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.order.CoeOrder;
import dml.sz0099.course.app.persist.entity.order.bo.CoeOrderBo;

/**
 * CoeOrderDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeOrderDao extends GenericDao<CoeOrder,Long>{

	/**
	 * 根据Id查询CoeOrder实体对象
	 * @param id
	 * @return
	 */
	CoeOrder findById(Long id);
	
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeOrder findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeOrder实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeOrder> findByIdList(List<Long> idList);
	
	public Page<CoeOrder> findPage(CoeOrder coeOrder, Pageable pageable);
	
	public Page<CoeOrder> findPageForMyOrderList(CoeOrderBo coeOrder, Pageable pageable);
	
	public Page<CoeOrder> findPageForOwnerOrderList(CoeOrderBo coeOrder, Pageable pageable);
	
}
