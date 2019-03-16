package dml.sz0099.course.app.persist.repository.article;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.article.CoeArticlePraise;

/**
 * @formatter:off
 * 
 * description: CoeArticlePraiseRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface CoeArticlePraiseRepository extends BasicJpaRepository<CoeArticlePraise,Long> {

	@Query("select e from CoeArticlePraise e where e.id=?1")
	CoeArticlePraise findById(Long id);
	
	@Query("select e from CoeArticlePraise e where e.aid=?1")
	CoeArticlePraise findByAid(Long aid);
	
	@Query("select e from CoeArticlePraise e where e.id in ?1")
	public List<CoeArticlePraise> findByIdList(List<Long> idList);
	
	
	@Query("select e from CoeArticlePraise e where e.mainId=?1 and e.userId=?2")
	public CoeArticlePraise findByMainIdAndUserId(Long mainId, Long userId);
	
	@Query("select e from CoeArticlePraise e where e.mainId=?1")
	public Page<CoeArticlePraise> findByMainId(Long mainId, Pageable pageable) ; 
}
