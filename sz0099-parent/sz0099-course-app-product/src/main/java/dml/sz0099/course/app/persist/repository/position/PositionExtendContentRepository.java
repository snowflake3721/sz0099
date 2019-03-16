package dml.sz0099.course.app.persist.repository.position;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.position.PositionExtendContent;

/**
 * @formatter:off
 * 
 * description: PositionExtendContentRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface PositionExtendContentRepository extends BasicJpaRepository<PositionExtendContent,Long> {

	@Query("select e from PositionExtendContent e where e.id=?1")
	PositionExtendContent findById(Long id);
	
	@Query("select e from PositionExtendContent e where e.aid=?1")
	PositionExtendContent findByAid(Long aid);
	
	@Query("select e from PositionExtendContent e where e.id in ?1")
	public List<PositionExtendContent> findByIdList(List<Long> idList);
}
