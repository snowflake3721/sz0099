package dml.sz0099.course.app.persist.repository.order;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.order.CoeOrderAsign;
import dml.sz0099.course.app.persist.entity.order.CoeOrderExpress;

/**
 * @formatter:off
 * 
 * description: CoeOrderAsignRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface CoeOrderAsignRepository extends BasicJpaRepository<CoeOrderAsign,Long> {

	@Query("select e from CoeOrderAsign e where e.id=?1")
	CoeOrderAsign findById(Long id);
	
	@Query("select e from CoeOrderAsign e where e.aid=?1")
	CoeOrderAsign findByAid(Long aid);
	
	@Query("select e from CoeOrderAsign e where e.id in ?1")
	public List<CoeOrderAsign> findByIdList(List<Long> idList);
	
	@Query("select e from CoeOrderAsign e where e.orderId=?1")
	public CoeOrderAsign findByOrderId(Long orderId);
}
