package dml.sz0099.course.app.persist.repository.activity;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityTime;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTime;

/**
 * @formatter:off
 * 
 * description: CoeActivityTimeRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface CoeActivityTimeRepository extends BasicJpaRepository<CoeActivityTime,Long> {

	@Query("select e from CoeActivityTime e where e.id=?1")
	CoeActivityTime findById(Long id);
	
	@Query("select e from CoeActivityTime e where e.aid=?1")
	CoeActivityTime findByAid(Long aid);
	
	@Query("select e from CoeActivityTime e where e.id in ?1")
	public List<CoeActivityTime> findByIdList(List<Long> idList);
	
	@Query("select e from CoeActivityTime e where e.mainId=?1")
	public CoeActivityTime findByMainId(Long activityId);
	
	@Query("select count(e) from CoeActivityTime e where e.mainId=?1")
	public Long countByMainId(Long activityId) ;
	
	@Query("select e from CoeActivityTime e where e.mainId in ?1 and e.deleted=false")
	public List<CoeActivityTime> findByMainIdList(List<Long> activityIdList) ;

}
