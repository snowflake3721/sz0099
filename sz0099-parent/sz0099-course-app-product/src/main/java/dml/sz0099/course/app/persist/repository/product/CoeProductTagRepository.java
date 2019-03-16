package dml.sz0099.course.app.persist.repository.product;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.product.CoeProductTag;

/**
 * @formatter:off
 * 
 * description: CoeProductTagRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface CoeProductTagRepository extends BasicJpaRepository<CoeProductTag,Long> {

	@Query("select e from CoeProductTag e where e.id=?1")
	CoeProductTag findById(Long id);
	
	@Query("select e from CoeProductTag e where e.aid=?1")
	CoeProductTag findByAid(Long aid);
	
	@Query("select e from CoeProductTag e where e.id in ?1")
	public List<CoeProductTag> findByIdList(List<Long> idList);
	
	@Query("select e from CoeProductTag e where e.mainId=?1 and e.name=?2")
	public CoeProductTag findByMainIdAndName(Long productId, String name);
	
	@Query("select count(e) from CoeProductTag e where e.mainId=?1")
	public Long countByMainId(Long productId) ;
	
	@Query("select e from CoeProductTag e where e.mainId=?1")
	public List<CoeProductTag> findByMainId(Long productId) ;
	
	@Query("select e from CoeProductTag e where e.mainId in ?1 and e.deleted=false")
	public List<CoeProductTag> findByMainIdList(List<Long> productIdList) ;
}
