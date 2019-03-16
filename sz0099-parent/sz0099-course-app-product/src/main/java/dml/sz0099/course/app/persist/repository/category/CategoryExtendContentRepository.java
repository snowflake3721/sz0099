package dml.sz0099.course.app.persist.repository.category;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.category.CategoryExtendContent;

/**
 * @formatter:off
 * 
 * description: CategoryExtendContentRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface CategoryExtendContentRepository extends BasicJpaRepository<CategoryExtendContent,Long> {

	@Query("select e from CategoryExtendContent e where e.id=?1")
	CategoryExtendContent findById(Long id);
	
	@Query("select e from CategoryExtendContent e where e.aid=?1")
	CategoryExtendContent findByAid(Long aid);
	
	@Query("select e from CategoryExtendContent e where e.id in ?1")
	public List<CategoryExtendContent> findByIdList(List<Long> idList);
}
