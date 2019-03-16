package dml.sz0099.course.app.persist.repository.activity;

import java.util.List;

import org.jit4j.app.code.template.CodeGenerated;
import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.activity.ParagActivity;

/**
 * @formatter:off
 * 
 * description: ParagActivityRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository("activityParagActivityRepository")
public interface ParagActivityRepository extends BasicJpaRepository<ParagActivity,Long> {

	@Query("select e from ParagActivity e where e.id=?1")
	ParagActivity findById(Long id);
	
	@Query("select e from ParagActivity e where e.aid=?1")
	ParagActivity findByAid(Long aid);
	
	@Query("select e from ParagActivity e where e.id in ?1")
	public List<ParagActivity> findByIdList(List<Long> idList);
	
	@CodeGenerated
	@Query("select e from ParagActivity e where e.mainId=?1")
	public Page<ParagActivity> findByMainId(Long activityId, Pageable pageable);
	
	@CodeGenerated
	@Modifying
	@Query("delete from ParagActivity e where e.mainId=?1 and e.userId=?2")
	public void deleteByActivityIdAndUserId(Long activityId,Long userId);
	
	@Modifying
	@Query("delete from ParagActivity e where e.paragId=?1 and e.userId=?2")
	public void deleteByParagIdAndUserId(Long paragId, Long userId);
	
	@Query("select e from ParagActivity e where e.mainId=?1")
	public List<ParagActivity> findListByMainId(Long activityId);
	
	@Query("select e from ParagActivity e where e.mainId=?1 and e.userId=?2")
	public List<ParagActivity> findListByMainIdAndUserId(Long activityId,Long userId);

	
	@Query("select e from ParagActivity e where e.mainId=?1 and e.userId=?2")
	public Page<ParagActivity> findByMainIdAndUserId(Long activityId, Long userId, Pageable pageable );
	
	@Query("select count(e) from ParagActivity e where e.mainId=?1")
	public Long countByMainId(Long activityId);
}
