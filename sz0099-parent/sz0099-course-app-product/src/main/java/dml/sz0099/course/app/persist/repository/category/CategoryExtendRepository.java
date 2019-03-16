package dml.sz0099.course.app.persist.repository.category;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.category.CategoryExtend;

/**
 * @formatter:off
 * 
 * description: CategoryExtendRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface CategoryExtendRepository extends BasicJpaRepository<CategoryExtend,Long> {

	@Query("select e from CategoryExtend e where e.id=?1")
	CategoryExtend findById(Long id);
	
	@Query("select e from CategoryExtend e where e.aid=?1")
	CategoryExtend findByAid(Long aid);
	
	@Query("select e from CategoryExtend e where e.id in ?1")
	public List<CategoryExtend> findByIdList(List<Long> idList);
	
	@Query("select e from CategoryExtend e where e.positionId=?1")
	public CategoryExtend findByPositionId(Long positionId);
	
	@Query("select e from CategoryExtend e where e.devId=?1 and e.project=?2 and e.module=?3 and e.variety=?4 and e.position=?5")
	public CategoryExtend findCategoryExtend(String devId, String project, String module, String variety, String position);

	@Query("select count(e) from CategoryExtend e where e.userId=?1")
	public Long countByUserId(Long userId);
}
