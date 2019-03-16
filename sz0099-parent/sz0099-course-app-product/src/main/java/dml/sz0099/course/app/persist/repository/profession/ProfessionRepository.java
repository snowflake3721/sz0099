package dml.sz0099.course.app.persist.repository.profession;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.profession.Profession;

/**
 * @formatter:off
 * 
 * description: ProfessionRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface ProfessionRepository extends BasicJpaRepository<Profession,Long> {

	@Query("select e from Profession e where e.id=?1")
	Profession findById(Long id);
	
	@Query("select e from Profession e where e.aid=?1")
	Profession findByAid(Long aid);
	
	@Query("select e from Profession e where e.id in ?1")
	public List<Profession> findByIdList(List<Long> idList);
	
	@Query("select e from Profession e where e.userId=?1 and e.publishStatus=?2 and e.deleted=false")
	public List<Profession> findByUserIdAndPublishStatus(Long userId, Integer publishStatus);

	@Query("select count(e) from Profession e where e.userId=?1 and e.publishStatus=?2 and e.deleted=false")
	public Long countByUserIdAndPublishStatus(Long userId, Integer publishStatus);
	
	@Query("select e from Profession e where e.userId=?1 and e.deleted=false")
	public List<Profession> findByUserId(Long userId);
	
	@Query("select count(e) from Profession e where e.userId=?1 and e.deleted=false")
	public Long countByUserId(Long userId);
	
	@Query("select e from Profession e where e.publishStatus=1 and e.deleted=false and e.name like ?1%")
	public List<Profession> findPublishedByName(String name);
	
	@Query("select e from Profession e where e.publishStatus=1 and e.deleted=false and e.title like ?1%")
	public List<Profession> findPublishedByTitle(String title);
	
	@Query("select count(e) from Profession e where e.userId!=?1 and e.positionId=?2 and e.publishStatus=?3 and e.deleted=false")
	public Long countForPublishedWithoutSelf(Long userId, Long positionId, Integer publishStatus);
	
	@Query("select e from Profession e where e.mainType=?1 and e.userId in ?2 and e.publishStatus=?3 and e.deleted=false")
	public Page<Profession> findPageByMainTypeAndUserId(Integer mainType, List<Long> userIdList,Integer publishStatus, Pageable pageable);
	
	@Query("select e from Profession e where e.mainType=?1 and e.userId in ?2 and e.publishStatus=?3 and e.deleted=false")
	public List<Profession> findListByMainTypeAndUserId(Integer mainType, List<Long> userIdList, Integer publishStatus);
	
	@Query("select e from Profession e where e.mainType=?1 and e.userId=?2 and e.publishStatus=?3 and e.deleted=false")
	public List<Profession> findByUserIdAndMainType(Integer mainType,Long userId, Integer publishStatus);
	
	@Query("select e from Profession e where e.mainType=?1 and e.userId=?2 and e.deleted=false")
	public List<Profession> findByUserIdAndMainType(Integer mainType,Long userId);
}
