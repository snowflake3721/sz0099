package dml.sz0099.course.app.persist.dao.order;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.order.CoeOrderProduct;
import dml.sz0099.course.app.persist.entity.order.bo.CoeOrderProductBo;

/**
 * CoeOrderProductDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeOrderProductDao extends GenericDao<CoeOrderProduct,Long>{

	/**
	 * 根据Id查询CoeOrderProduct实体对象
	 * @param id
	 * @return
	 */
	CoeOrderProduct findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeOrderProduct findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeOrderProduct实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeOrderProduct> findByIdList(List<Long> idList);
	
	public Page<CoeOrderProduct> findPage(CoeOrderProduct coeOrderProduct, Pageable pageable);
	
	public Page<CoeOrderProduct> findPageForMyOrderList(CoeOrderProductBo orderProduct, Pageable pageable);
	
	public List<CoeOrderProduct> findByOrderIdList(List<Long> orderIdList) ;
	
	public List<CoeOrderProduct> findByOrderId(Long orderId) ;
}
