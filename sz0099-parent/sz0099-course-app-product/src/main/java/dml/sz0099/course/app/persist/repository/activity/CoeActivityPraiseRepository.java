package dml.sz0099.course.app.persist.repository.activity;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityPraise;

/**
 * @formatter:off
 * 
 * description: CoeActivityPraiseRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface CoeActivityPraiseRepository extends BasicJpaRepository<CoeActivityPraise,Long> {

	@Query("select e from CoeActivityPraise e where e.id=?1")
	CoeActivityPraise findById(Long id);
	
	@Query("select e from CoeActivityPraise e where e.aid=?1")
	CoeActivityPraise findByAid(Long aid);
	
	@Query("select e from CoeActivityPraise e where e.id in ?1")
	public List<CoeActivityPraise> findByIdList(List<Long> idList);
	
	
	@Query("select e from CoeActivityPraise e where e.mainId=?1 and e.userId=?2")
	public CoeActivityPraise findByMainIdAndUserId(Long mainId, Long userId);
	
	@Query("select e from CoeActivityPraise e where e.mainId=?1")
	public Page<CoeActivityPraise> findByMainId(Long mainId, Pageable pageable) ; 
}
