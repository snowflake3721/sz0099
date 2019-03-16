package dml.sz0099.course.app.persist.repository.profession;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.profession.ProfessionPosition;

/**
 * @formatter:off
 * 
 * description: ProfessionPositionRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface ProfessionPositionRepository extends BasicJpaRepository<ProfessionPosition,Long> {

	@Query("select e from ProfessionPosition e where e.id=?1")
	ProfessionPosition findById(Long id);
	
	@Query("select e from ProfessionPosition e where e.aid=?1")
	ProfessionPosition findByAid(Long aid);
	
	@Query("select e from ProfessionPosition e where e.id in ?1")
	public List<ProfessionPosition> findByIdList(List<Long> idList);
	
	
	@Query("select e from ProfessionPosition e where e.mainId=?1 and e.userId=?2")
	public List<ProfessionPosition> findByMainIdAndUserId(Long mainId, Long userId);
	
	@Query("select e from ProfessionPosition e where e.mainId=?1")
	public Page<ProfessionPosition> findByMainId(Long mainId, Pageable pageable) ; 
	
	@Query("select e from ProfessionPosition e where e.mainId=?1 and e.ponLayout=?2")
	public ProfessionPosition findByMainIdAndPosition(Long mainId, Integer position);
	
	@Query("select e from ProfessionPosition e where e.baseId=?1 and e.mainId in ?2 and e.deleted=false")
	public List<ProfessionPosition> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList);
	
	
	@Query("select e from ProfessionPosition e where e.baseId=?1")
	public List<ProfessionPosition> findByBaseId(Long baseId);
	
	@Query("select e from ProfessionPosition e where e.baseId=?1 and e.deleted=?2 order by e.topLevel desc")
	public List<ProfessionPosition> findByBaseId(Long baseId, boolean deleted);
	
	@Modifying
	@Query("delete from ProfessionPosition e where e.baseId=?1")
	public void deleteRefByBaseId(Long baseId);
	
	/**
	 * 这四项联合可以确定这个位置即==Position.id，分页查询在这个位置下的所有关联对象
	 * @param ponMainId
	 * @param ponSubId
	 * @param positionId
	 * @param ponPanel
	 * @param pageable
	 * @return
	 */
	@Query("select e from ProfessionPosition e where e.ponMainId=?1 and ponSubId=?2 and positionId=?3 and ponPanel=?4")
	public Page<ProfessionPosition> findPageForPosition(Long ponMainId,Long ponSubId, Long positionId, Integer ponPanel, Pageable pageable);
	
	@Query("select e from ProfessionPosition e where e.baseId=?1 and e.deleted=false and e.disable=false")
	public Page<ProfessionPosition> findByBaseId(Long baseId, Pageable pageable);
	
	@Query("select e from ProfessionPosition e where e.baseId in ?1 and e.deleted=false and e.disable=false")
	public Page<ProfessionPosition> findByBaseIdList(List<Long> baseIdList, Pageable pageable);
	
	
}
