package dml.sz0099.course.app.persist.repository.profession;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.profession.ProfessionExtend;

/**
 * @formatter:off
 * 
 * description: ProfessionExtendRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface ProfessionExtendRepository extends BasicJpaRepository<ProfessionExtend,Long> {

	@Query("select e from ProfessionExtend e where e.id=?1")
	ProfessionExtend findById(Long id);
	
	@Query("select e from ProfessionExtend e where e.aid=?1")
	ProfessionExtend findByAid(Long aid);
	
	@Query("select e from ProfessionExtend e where e.id in ?1")
	public List<ProfessionExtend> findByIdList(List<Long> idList);
	
	@Query("select e from ProfessionExtend e where e.positionId=?1")
	public ProfessionExtend findByPositionId(Long positionId);
	
	@Query("select e from ProfessionExtend e where e.devId=?1 and e.project=?2 and e.module=?3 and e.variety=?4 and e.position=?5")
	public ProfessionExtend findProfessionExtend(String devId, String project, String module, String variety, String position);

	@Query("select count(e) from ProfessionExtend e where e.userId=?1")
	public Long countByUserId(Long userId);
}