package dml.sz0099.course.app.persist.repository.activity;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityFee;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityFee;

/**
 * @formatter:off
 * 
 * description: CoeActivityFeeRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface CoeActivityFeeRepository extends BasicJpaRepository<CoeActivityFee,Long> {

	@Query("select e from CoeActivityFee e where e.id=?1")
	CoeActivityFee findById(Long id);
	
	@Query("select e from CoeActivityFee e where e.aid=?1")
	CoeActivityFee findByAid(Long aid);
	
	@Query("select e from CoeActivityFee e where e.id in ?1")
	public List<CoeActivityFee> findByIdList(List<Long> idList);
	
	@Query("select e from CoeActivityFee e where e.mainId=?1")
	public CoeActivityFee findByMainId(Long activityId);
	
	@Query("select count(e) from CoeActivityFee e where e.mainId=?1")
	public Long countByMainId(Long activityId) ;
	
	@Query("select e from CoeActivityFee e where e.mainId in ?1 and e.deleted=false")
	public List<CoeActivityFee> findByMainIdList(List<Long> activityIdList) ;

}
