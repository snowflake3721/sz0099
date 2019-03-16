package dml.sz0099.course.app.persist.repository.position;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.position.PositionRef;

/**
 * @formatter:off
 * 
 * description: PositionRefRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface PositionRefRepository extends BasicJpaRepository<PositionRef,Long> {

	@Query("select e from PositionRef e where e.id=?1")
	PositionRef findById(Long id);
	
	@Query("select e from PositionRef e where e.aid=?1")
	PositionRef findByAid(Long aid);
	
	@Query("select e from PositionRef e where e.id in ?1")
	public List<PositionRef> findByIdList(List<Long> idList);
	
	@Modifying
	@Query("delete from PositionRef e where e.baseId=?1")
	public void deleteByBaseId(Long baseId);
	
	@Modifying
	@Query("delete from PositionRef e where e.baseId in ?1")
	public void deleteByBaseIdList(List<Long> baseIdList);
	
	@Query("select count(e) from PositionRef e where e.baseId=?1 and e.deleted=false")
	public Long countByBaseId(Long baseId);
	
	@Query("select e from PositionRef e where e.mainId=?1 and e.deleted=false")
	public List<PositionRef> findByMainId(Long mainId);
	
	@Modifying
	@Query("delete from PositionRef e where e.mainId=?1")
	public void deleteByMainId(Long mainId);
	
	
	@Query("select e from PositionRef e where e.baseId=?1 and e.deleted=false")
	public Page<PositionRef> findPageByBaseId(Long baseId, Pageable pageable);
	
	@Query("select e from PositionRef e where e.baseId in ?1 and e.deleted=false")
	public List<PositionRef> findByBaseIdList(List<Long> baseIdList);
	
	@Query("select e from PositionRef e where e.baseId=?1 and e.mainId in ?2 and e.deleted=false")
	public List<PositionRef> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList);
	
	@Query("select e from PositionRef e where e.mainId=?1 and e.baseId=?2")
	public PositionRef findMainIdAndBaseId(Long mainId, Long baseId);
	
	
	@Query("select e from PositionRef e where e.baseId=?1")
	public List<PositionRef> findByBaseId(Long baseId);
	
	@Query("select e from PositionRef e where e.baseId=?1 and e.deleted=?2 order by e.topLevel desc")
	public List<PositionRef> findByBaseId(Long baseId, boolean deleted);
	
	@Modifying
	@Query("delete from PositionRef e where e.baseId=?1")
	public void deleteRefByBaseId(Long baseId);
}
