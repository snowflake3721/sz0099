package dml.sz0099.course.app.persist.repository.activity;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.activity.JoinItem;
import dml.sz0099.course.app.persist.entity.activity.JoinItem;

/**
 * @formatter:off
 * 
 * description: JoinItemRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface JoinItemRepository extends BasicJpaRepository<JoinItem,Long> {

	@Query("select e from JoinItem e where e.id=?1")
	JoinItem findById(Long id);
	
	@Query("select e from JoinItem e where e.aid=?1")
	JoinItem findByAid(Long aid);
	
	@Query("select e from JoinItem e where e.id in ?1")
	public List<JoinItem> findByIdList(List<Long> idList);
	
	@Query("select e from JoinItem e where e.mainId=?1")
	public List<JoinItem> findByMainId(Long activityId);
	
	@Query("select e from JoinItem e where e.baseId=?1")
	public List<JoinItem> findByBaseId(Long baseId);
	
	@Query("select count(e) from JoinItem e where e.mainId=?1")
	public Long countByMainId(Long activityId) ;
	
	@Query("select e from JoinItem e where e.mainId in ?1 and e.deleted=false")
	public List<JoinItem> findByMainIdList(List<Long> activityIdList) ;

}
