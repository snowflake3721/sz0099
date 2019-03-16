package dml.sz0099.course.app.persist.repository.activity;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityUser;
import dml.sz0099.course.app.persist.entity.activity.vo.CoeActivityUserVo;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityUser;

/**
 * @formatter:off
 * 
 * description: CoeActivityUserRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface CoeActivityUserRepository extends BasicJpaRepository<CoeActivityUser,Long> {

	@Query("select e from CoeActivityUser e where e.id=?1")
	CoeActivityUser findById(Long id);
	
	@Query("select e from CoeActivityUser e where e.aid=?1")
	CoeActivityUser findByAid(Long aid);
	
	@Query("select e from CoeActivityUser e where e.id in ?1")
	public List<CoeActivityUser> findByIdList(List<Long> idList);
	
	@Query("select e from CoeActivityUser e where e.mainId=?1 and e.status in ?2")
	public List<CoeActivityUser> findByMainId(Long activityId, List<Integer> statusList);
	
	@Query("select e from CoeActivityUser e where e.mainId=?1 and e.status in ?2")
	public Page<CoeActivityUser> findByMainId(Long mainId, List<Integer> statusList, Pageable pageable);
	
	@Query("select e from CoeActivityUser e where e.baseId=?1")
	public List<CoeActivityUser> findByBaseId(Long baseId);
	
	@Query("select e from CoeActivityUser e where e.baseId=?1 and e.identity=?2")
	public CoeActivityUser findByBaseIdAndIdentity(Long baseId, String identity);
	
	@Query("select count(e) from CoeActivityUser e where e.mainId=?1")
	public Long countByMainId(Long activityId) ;
	
	@Query("select e from CoeActivityUser e where e.mainId in ?1 and e.deleted=false")
	public List<CoeActivityUser> findByMainIdList(List<Long> activityIdList) ;
	
	@Query("select e from CoeActivityUser e where e.mainId=?1 and e.identity=?2 and e.baseId!=?3 and e.deleted=false")
	public List<CoeActivityUser> findEffectiveUser(Long mainId, String identity, Long baseId);

	@Query("select count(e) from CoeActivityUser e where e.mainId=?1 and e.status in ?2 and e.deleted=false")
	public Long countPayVerifyUsers(Long mainId, List<Integer> statusList);
	
	@Query("select count(e) from CoeActivityUser e where e.baseId=?1")
	public Long countByBaseId(Long baseId);
	
	
	@Query("select new dml.sz0099.course.app.persist.entity.activity.vo.CoeActivityUserVo(count(e), e.mainId) from CoeActivityUser e where e.mainId in ?1 and e.status in ?2 and e.deleted=false group by e.mainId")
	public List<CoeActivityUserVo> countByMainIdList(List<Long> mainIdList, List<Integer> statusList);
}
