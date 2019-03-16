package dml.sz0099.course.app.persist.repository.order;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.order.CoeOrderProduct;

/**
 * @formatter:off
 * 
 * description: CoeOrderProductRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface CoeOrderProductRepository extends BasicJpaRepository<CoeOrderProduct,Long> {

	@Query("select e from CoeOrderProduct e where e.id=?1")
	CoeOrderProduct findById(Long id);
	
	@Query("select e from CoeOrderProduct e where e.aid=?1")
	CoeOrderProduct findByAid(Long aid);
	
	@Query("select e from CoeOrderProduct e where e.id in ?1")
	public List<CoeOrderProduct> findByIdList(List<Long> idList);
	
	
	@Query("select e from CoeOrderProduct e where e.orderId in ?1")
	public List<CoeOrderProduct> findByOrderIdList(List<Long> orderIdList);
	
	@Query("select e from CoeOrderProduct e where e.orderId=?1")
	public List<CoeOrderProduct> findByOrderId(Long orderId);
}
