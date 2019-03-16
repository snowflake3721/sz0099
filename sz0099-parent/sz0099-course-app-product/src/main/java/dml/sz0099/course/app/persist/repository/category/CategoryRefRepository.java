package dml.sz0099.course.app.persist.repository.category;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.category.CategoryRef;

/**
 * @formatter:off
 * 
 * description: CategoryRefRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface CategoryRefRepository extends BasicJpaRepository<CategoryRef,Long> {

	@Query("select e from CategoryRef e where e.id=?1")
	CategoryRef findById(Long id);
	
	@Query("select e from CategoryRef e where e.aid=?1")
	CategoryRef findByAid(Long aid);
	
	@Query("select e from CategoryRef e where e.id in ?1")
	public List<CategoryRef> findByIdList(List<Long> idList);
	
	@Modifying
	@Query("delete from CategoryRef e where e.baseId=?1")
	public void deleteByBaseId(Long baseId);
	
	@Query("select count(e) from CategoryRef e where e.baseId=?1 and e.deleted=false")
	public Long countByBaseId(Long baseId);
	
	@Query("select e from CategoryRef e where e.mainId=?1 and e.deleted=false")
	public List<CategoryRef> findByMainId(Long mainId);
	
	@Modifying
	@Query("delete from CategoryRef e where e.mainId=?1")
	public void deleteByMainId(Long mainId);
}
