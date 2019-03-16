package dml.sz0099.course.app.persist.repository.profession;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.profession.ProfessionPraise;

/**
 * @formatter:off
 * 
 * description: ProfessionPraiseRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface ProfessionPraiseRepository extends BasicJpaRepository<ProfessionPraise,Long> {

	@Query("select e from ProfessionPraise e where e.id=?1")
	ProfessionPraise findById(Long id);
	
	@Query("select e from ProfessionPraise e where e.aid=?1")
	ProfessionPraise findByAid(Long aid);
	
	@Query("select e from ProfessionPraise e where e.id in ?1")
	public List<ProfessionPraise> findByIdList(List<Long> idList);
	
	
	@Query("select e from ProfessionPraise e where e.mainId=?1 and e.userId=?2")
	public ProfessionPraise findByMainIdAndUserId(Long mainId, Long userId);
	
	@Query("select e from ProfessionPraise e where e.mainId=?1")
	public Page<ProfessionPraise> findByMainId(Long mainId, Pageable pageable) ; 
}
