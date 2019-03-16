package dml.sz0099.course.app.persist.repository.activity;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.activity.CoeActivity;

/**
 * @formatter:off
 * 
 * description: CoeActivityRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface CoeActivityRepository extends BasicJpaRepository<CoeActivity,Long> {

	@Query("select e from CoeActivity e where e.id=?1")
	CoeActivity findById(Long id);
	
	@Query("select e from CoeActivity e where e.aid=?1")
	CoeActivity findByAid(Long aid);
	
	@Query("select e from CoeActivity e where e.id in ?1")
	public List<CoeActivity> findByIdList(List<Long> idList);
	
	@Query("select e from CoeActivity e where e.id in ?1 and e.publishStatus=?2 and e.deleted=false")
	public List<CoeActivity> findPublishByIdList(List<Long> idList, Integer publishStatus);
	
	@Query("select e from CoeActivity e where e.userId=?1 and e.publishStatus=?2 and e.deleted=false")
	public List<CoeActivity> findByUserIdAndPublishStatus(Long userId, Integer publishStatus);

	@Query("select count(e) from CoeActivity e where e.userId=?1 and e.publishStatus=?2 and e.deleted=false")
	public Long countByUserIdAndPublishStatus(Long userId, Integer publishStatus);
	
	@Query("select count(e) from CoeActivity e where e.userId=?1 and e.template=?2 and e.deleted=false")
	public Long countTemplate(Long userId, Integer template);
	
	@Query("select e from CoeActivity e where e.userId=?1 and e.deleted=false")
	public List<CoeActivity> findByUserId(Long userId);
	
	@Query("select count(e) from CoeActivity e where e.userId=?1 and e.deleted=false")
	public Long countByUserId(Long userId);
	
	@Query("select e from CoeActivity e where e.publishStatus=1 and e.deleted=false and e.name like ?1%")
	public List<CoeActivity> findPublishedByName(String name);
	
	@Query("select e from CoeActivity e where e.publishStatus=1 and e.deleted=false and e.title like ?1%")
	public List<CoeActivity> findPublishedByTitle(String title);
	
	
	@Query("select count(e) from CoeActivity e where e.userId!=?1 and e.publishStatus=?2 and e.deleted=false")
	public Long countForPublishedWithoutSelf(Long userId, Integer publishStatus);
	
	@Query("select e from CoeActivity e where e.mainType=?1 and e.userId in ?2 and e.publishStatus=?3 and e.deleted=false")
	public Page<CoeActivity> findPageByMainTypeAndUserId(Integer mainType, List<Long> userIdList,Integer publishStatus, Pageable pageable);
	
	@Query("select e from CoeActivity e where e.mainType=?1 and e.userId in ?2 and e.publishStatus=?3 and e.deleted=false")
	public List<CoeActivity> findListByMainTypeAndUserId(Integer mainType, List<Long> userIdList, Integer publishStatus);
	
	@Query("select e from CoeActivity e where e.mainType=?1 and e.userId=?2 and e.publishStatus=?3 and e.deleted=false")
	public List<CoeActivity> findByUserIdAndMainType(Integer mainType,Long userId, Integer publishStatus);
	
	@Query("select e from CoeActivity e where e.mainType=?1 and e.userId=?2 and e.deleted=false")
	public List<CoeActivity> findByUserIdAndMainType(Integer mainType,Long userId);
	
	@Query("select e from CoeActivity e where e.userId=?1 and e.template=?2 and e.deleted=false")
	public Page<CoeActivity> findPageForTemplate(Long userId,Integer template, Pageable pageable);
	
	@Query("select e from CoeActivity e where e.template=?1 and e.deleted=false")
	public Page<CoeActivity> findPageForTemplate(Integer template, Pageable pageable);
	
	@Query("select e from CoeActivity e where e.userId=?1 and e.template=?2 and e.id !=?3 and e.deleted=false")
	public Page<CoeActivity> findPageForTemplate(Long userId,Integer template, Long id, Pageable pageable);

}
