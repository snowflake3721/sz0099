package dml.sz0099.course.app.persist.repository.activity;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.activity.CoeCategActivity;

/**
 * @formatter:off
 * 
 * description: CoeCategActivityRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface CoeCategActivityRepository extends BasicJpaRepository<CoeCategActivity,Long> {

	@Query("select e from CoeCategActivity e where e.id=?1")
	CoeCategActivity findById(Long id);
	
	@Query("select e from CoeCategActivity e where e.aid=?1")
	CoeCategActivity findByAid(Long aid);
	
	@Query("select e from CoeCategActivity e where e.id in ?1")
	public List<CoeCategActivity> findByIdList(List<Long> idList);
	
	@Query("select e from CoeCategActivity e where e.mainId in ?1 and e.deleted=false")
	public List<CoeCategActivity> findByMainIdList(List<Long> mainIdList);
	
	@Query("select e from CoeCategActivity e where e.mainId=?1 and e.deleted=false")
	public List<CoeCategActivity> findByMainId(Long mainId);
}
