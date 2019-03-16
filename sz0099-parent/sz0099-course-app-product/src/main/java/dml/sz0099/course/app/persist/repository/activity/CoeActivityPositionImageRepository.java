package dml.sz0099.course.app.persist.repository.activity;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityPositionImage;

/**
 * @formatter:off
 * 
 * description: CoeActivityPositionImageRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface CoeActivityPositionImageRepository extends BasicJpaRepository<CoeActivityPositionImage,Long> {

	@Query("select e from CoeActivityPositionImage e where e.id=?1")
	CoeActivityPositionImage findById(Long id);
	
	@Query("select e from CoeActivityPositionImage e where e.aid=?1")
	CoeActivityPositionImage findByAid(Long aid);
	
	@Query("select e from CoeActivityPositionImage e where e.id in ?1")
	public List<CoeActivityPositionImage> findByIdList(List<Long> idList);
	
	
	@Query("select e from CoeActivityPositionImage e where e.mainId=?1 and e.userId=?2")
	public CoeActivityPositionImage findByMainIdAndUserId(Long mainId, Long userId);
	
	@Query("select e from CoeActivityPositionImage e where e.mainId=?1")
	public Page<CoeActivityPositionImage> findByMainId(Long mainId, Pageable pageable) ; 
	
	@Query("select e from CoeActivityPositionImage e where e.refId=?1 order by e.orderSeq asc")
	public List<CoeActivityPositionImage> findByRefId(Long refId);
	
	@Query("select e from CoeActivityPositionImage e where e.refId in ?1 order by e.orderSeq asc")
	public List<CoeActivityPositionImage> findByRefIdList(List<Long> refIdList);
	
	@Modifying
	@Query("delete from CoeActivityPositionImage e where e.refId in ?1")
	public void deleteByRefIdList(List<Long> refIdList);
	
	
	@Modifying
	@Query("delete from CoeActivityPositionImage e where e.refId=?1")
	public void deleteByRefId(Long refId);
}
