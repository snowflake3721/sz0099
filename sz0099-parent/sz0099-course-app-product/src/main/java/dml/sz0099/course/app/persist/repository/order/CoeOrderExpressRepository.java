package dml.sz0099.course.app.persist.repository.order;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.order.CoeOrderExpress;

/**
 * @formatter:off
 * 
 * description: CoeOrderExpressRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface CoeOrderExpressRepository extends BasicJpaRepository<CoeOrderExpress,Long> {

	@Query("select e from CoeOrderExpress e where e.id=?1")
	CoeOrderExpress findById(Long id);
	
	@Query("select e from CoeOrderExpress e where e.aid=?1")
	CoeOrderExpress findByAid(Long aid);
	
	@Query("select e from CoeOrderExpress e where e.id in ?1")
	public List<CoeOrderExpress> findByIdList(List<Long> idList);
	
	@Query("select e from CoeOrderExpress e where e.orderId=?1")
	public CoeOrderExpress findByOrderId(Long orderId);
}
