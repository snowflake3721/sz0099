package dml.sz0099.course.app.persist.repository.activity;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityPositionCover;

/**
 * @formatter:off
 * 
 * description: CoeActivityPositionCoverRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface CoeActivityPositionCoverRepository extends BasicJpaRepository<CoeActivityPositionCover,Long> {

	@Query("select e from CoeActivityPositionCover e where e.id=?1")
	CoeActivityPositionCover findById(Long id);
	
	@Query("select e from CoeActivityPositionCover e where e.aid=?1")
	CoeActivityPositionCover findByAid(Long aid);
	
	@Query("select e from CoeActivityPositionCover e where e.id in ?1")
	public List<CoeActivityPositionCover> findByIdList(List<Long> idList);
	
	
	@Query("select e from CoeActivityPositionCover e where e.mainId=?1 and e.userId=?2")
	public CoeActivityPositionCover findByMainIdAndUserId(Long mainId, Long userId);
	
	@Query("select e from CoeActivityPositionCover e where e.mainId=?1")
	public Page<CoeActivityPositionCover> findByMainId(Long mainId, Pageable pageable) ; 
	
	@Query("select e from CoeActivityPositionCover e where e.refId=?1")
	public List<CoeActivityPositionCover> findByRefId(Long refId);
	
	@Query("select e from CoeActivityPositionCover e where e.refId in ?1")
	public List<CoeActivityPositionCover> findByRefIdList(List<Long> refIdList);
	
	@Modifying
	@Query("delete from CoeActivityPositionCover e where e.refId in ?1")
	public void deleteByRefIdList(List<Long> refIdList);
	
	
	@Modifying
	@Query("delete from CoeActivityPositionCover e where e.refId=?1")
	public void deleteByRefId(Long refId);
}
