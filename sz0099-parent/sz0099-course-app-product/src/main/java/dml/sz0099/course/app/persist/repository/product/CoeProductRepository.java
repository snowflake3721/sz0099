package dml.sz0099.course.app.persist.repository.product;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.product.CoeProduct;

/**
 * @formatter:off
 * 
 * description: CoeProductRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface CoeProductRepository extends BasicJpaRepository<CoeProduct,Long> {

	@Query("select e from CoeProduct e where e.id=?1 and e.deleted=false")
	CoeProduct findById(Long id);
	
	@Query("select e from CoeProduct e where e.id in ?1")
	public List<CoeProduct> findByIdList(List<Long> idList);
	
	@Query("select e from CoeProduct e where e.shelved=1 and e.deleted=false and e.name like ?1%")
	public List<CoeProduct> findShelvedByName(String name);
	
	@Query("select e from CoeProduct e where e.shelved=1 and e.deleted=false and e.title like ?1%")
	public List<CoeProduct> findShelvedByTitle(String title);
	
	@Query("select e from CoeProduct e where e.userId=?1 and e.publishStatus=?2")
	public List<CoeProduct> findByUserIdAndPublishStatus(Long userId, Integer publishStatus);

	@Query("select count(e) from CoeProduct e where e.userId=?1 and e.publishStatus=?2")
	public Long countByUserIdAndPublishStatus(Long userId, Integer publishStatus);
	
	@Query("select e from CoeProduct e where e.userId=?1 and e.publishStatus=?2")
	public Page<CoeProduct> findPageByUserId(Long userId, Integer publishStatus, Pageable pageable);

}

