package dml.sz0099.course.app.persist.repository.profession;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.profession.ProfessionRef;

/**
 * @formatter:off
 * 
 * description: ProfessionRefRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface ProfessionRefRepository extends BasicJpaRepository<ProfessionRef,Long> {

	@Query("select e from ProfessionRef e where e.id=?1")
	ProfessionRef findById(Long id);
	
	@Query("select e from ProfessionRef e where e.aid=?1")
	ProfessionRef findByAid(Long aid);
	
	@Query("select e from ProfessionRef e where e.id in ?1")
	public List<ProfessionRef> findByIdList(List<Long> idList);
	
	@Modifying
	@Query("delete from ProfessionRef e where e.baseId=?1")
	public void deleteByBaseId(Long baseId);
	
	@Modifying
	@Query("delete from ProfessionRef e where e.baseId in ?1")
	public void deleteByBaseIdList(List<Long> baseIdList);
	
	@Query("select count(e) from ProfessionRef e where e.baseId=?1 and e.deleted=false")
	public Long countByBaseId(Long baseId);
	
	@Query("select e from ProfessionRef e where e.mainId=?1 and e.deleted=false")
	public List<ProfessionRef> findByMainId(Long mainId);
	
	@Modifying
	@Query("delete from ProfessionRef e where e.mainId=?1")
	public void deleteByMainId(Long mainId);
	
	
	@Query("select e from ProfessionRef e where e.baseId=?1 and e.deleted=false")
	public Page<ProfessionRef> findPageByBaseId(Long baseId, Pageable pageable);
	
	@Query("select e from ProfessionRef e where e.baseId in ?1 and e.deleted=false")
	public List<ProfessionRef> findByBaseIdList(List<Long> baseIdList);
	
	@Query("select e from ProfessionRef e where e.baseId=?1 and e.mainId in ?2 and e.deleted=false")
	public List<ProfessionRef> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList);
	
	@Query("select e from ProfessionRef e where e.baseId=?1 and e.mainId=?2")
	public ProfessionRef findMainIdAndBaseId(Long mainId, Long baseId);
	
	
	@Query("select e from ProfessionRef e where e.baseId=?1")
	public List<ProfessionRef> findByBaseId(Long baseId);
	
	@Query("select e from ProfessionRef e where e.baseId=?1 and e.deleted=?2 order by e.topLevel desc")
	public List<ProfessionRef> findByBaseId(Long baseId, boolean deleted);
	
	@Modifying
	@Query("delete from ProfessionRef e where e.baseId=?1")
	public void deleteRefByBaseId(Long baseId);
}
