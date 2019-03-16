package dml.sz0099.course.app.persist.repository.product;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.product.CoeCategProd;

/**
 * @formatter:off
 * 
 * description: CoeCategProdRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface CoeCategProdRepository extends BasicJpaRepository<CoeCategProd,Long> {

	@Query("select e from CoeCategProd e where e.id=?1")
	CoeCategProd findById(Long id);
	
	@Query("select e from CoeCategProd e where e.aid=?1")
	CoeCategProd findByAid(Long aid);
	
	@Query("select e from CoeCategProd e where e.id in ?1")
	public List<CoeCategProd> findByIdList(List<Long> idList);
	
	@Query("select e from CoeCategProd e where e.mainId in ?1 and e.deleted=false")
	public List<CoeCategProd> findByMainIdList(List<Long> productIdList);
	
	@Query("select e from CoeCategProd e where e.mainId=?1 and e.deleted=false")
	public List<CoeCategProd> findByMainId(Long productId);
}
