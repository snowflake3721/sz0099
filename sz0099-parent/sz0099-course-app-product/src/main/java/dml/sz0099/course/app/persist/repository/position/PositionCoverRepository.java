package dml.sz0099.course.app.persist.repository.position;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.article.CoeArticlePositionCover;
import dml.sz0099.course.app.persist.entity.position.PositionCover;

/**
 * @formatter:off
 * 
 * description: PositionCoverRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface PositionCoverRepository extends BasicJpaRepository<PositionCover,Long> {

	@Query("select e from PositionCover e where e.id=?1")
	PositionCover findById(Long id);
	
	@Query("select e from PositionCover e where e.aid=?1")
	PositionCover findByAid(Long aid);
	
	@Query("select e from PositionCover e where e.id in ?1")
	public List<PositionCover> findByIdList(List<Long> idList);
	
	@Query("select e from PositionCover e where e.refId in ?1 order by e.orderSeq asc")
	public List<PositionCover> findByRefIdList(List<Long> refIdList);
	
	@Modifying
	@Query("delete from PositionCover e where e.refId in ?1")
	public void deleteByRefIdList(List<Long> refIdList);
	
	
	@Modifying
	@Query("delete from PositionCover e where e.refId=?1")
	public void deleteByRefId(Long refId);
	
	@Query("select e from PositionCover e where e.refId=?1 order by e.orderSeq asc")
	public List<PositionCover> findByRefId(Long refId);
}
