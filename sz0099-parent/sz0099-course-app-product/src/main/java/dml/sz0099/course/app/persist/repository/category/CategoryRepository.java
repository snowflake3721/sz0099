package dml.sz0099.course.app.persist.repository.category;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.category.Category;

/**
 * @formatter:off
 * 
 * description: CategoryRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface CategoryRepository extends BasicJpaRepository<Category,Long> {

	@Query("select e from Category e where e.id=?1")
	Category findById(Long id);
	
	@Query("select e from Category e where e.aid=?1")
	Category findByAid(Long aid);
	
	@Query("select e from Category e where e.id in ?1")
	public List<Category> findByIdList(List<Long> idList);
	
	
	@Query("select e from Category e where e.parentId=?1")
	public List<Category> findByParentId(Long parentId);
	
	@Query("select e from Category e where e.parentId in ?1")
	public List<Category> findByParentIdList(List<Long> parentIdList);
	
	@Query("select e from Category e where e.code=?1")
	public Category findByCode(String code);
	
	@Query("select e from Category e where e.extendId=?1 and e.mainId=?2 and e.subId=?3 and e.userId=?4 order by e.orderSeq asc")
	public List<Category> findMainAndSub(Long extendId,Long mainId, Long subId, Long userId);
	
	@Query("select count(e) from Category e where e.parentId=?1")
	public Long countByParentId(Long id);
	
	@Query("select count(e) from Category e where e.extendId=?1")
	public Long countByExtendId(Long extendId);
	
	@Modifying
	@Query("delete from Category e where e.extendId=?1 and e.userId=?2 and e.mainId=?3 and e.subId=?4")
	public void deleteAllByExtend(Long extendId, Long userId, Long mainId, Long subId);
	
	@Query("select e from Category e where e.extendId=?1 and e.userId=?2 and e.mainId=?3 and e.subId=?4")
	public List<Category> findForMain(Long extendId, Long userId, Long mainId, Long subId);
}
