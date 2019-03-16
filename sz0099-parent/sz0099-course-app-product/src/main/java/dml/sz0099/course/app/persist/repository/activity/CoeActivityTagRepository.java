package dml.sz0099.course.app.persist.repository.activity;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityTag;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTag;

/**
 * @formatter:off
 * 
 * description: CoeActivityTagRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface CoeActivityTagRepository extends BasicJpaRepository<CoeActivityTag,Long> {

	@Query("select e from CoeActivityTag e where e.id=?1")
	CoeActivityTag findById(Long id);
	
	@Query("select e from CoeActivityTag e where e.aid=?1")
	CoeActivityTag findByAid(Long aid);
	
	@Query("select e from CoeActivityTag e where e.id in ?1")
	public List<CoeActivityTag> findByIdList(List<Long> idList);
	
	@Query("select e from CoeActivityTag e where e.mainId=?1 and e.name=?2")
	public CoeActivityTag findByMainIdAndName(Long activityId, String name);
	
	@Query("select count(e) from CoeActivityTag e where e.mainId=?1")
	public Long countByMainId(Long activityId) ;
	
	@Query("select e from CoeActivityTag e where e.mainId=?1")
	public List<CoeActivityTag> findByMainId(Long activityId) ;
	
	@Query("select e from CoeActivityTag e where e.mainId in ?1 and e.deleted=false")
	public List<CoeActivityTag> findByMainIdList(List<Long> activityIdList) ;

}
