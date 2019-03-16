package dml.sz0099.course.app.persist.dao.order;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.order.CoeOrderProdLog;

/**
 * CoeOrderProdLogDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeOrderProdLogDao extends GenericDao<CoeOrderProdLog,Long>{

	/**
	 * 根据Id查询CoeOrderProdLog实体对象
	 * @param id
	 * @return
	 */
	CoeOrderProdLog findById(Long id);
	
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeOrderProdLog findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeOrderProdLog实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeOrderProdLog> findByIdList(List<Long> idList);
	
	public Page<CoeOrderProdLog> findPage(CoeOrderProdLog coeOrderProdLog, Pageable pageable);
	
	public CoeOrderProdLog findByOrderId(Long orderId);
	
}
