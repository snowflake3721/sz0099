package dml.sz0099.course.app.persist.repository.article;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.article.CoeCategArticle;

/**
 * @formatter:off
 * 
 * description: CoeCategArticleRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface CoeCategArticleRepository extends BasicJpaRepository<CoeCategArticle,Long> {

	@Query("select e from CoeCategArticle e where e.id=?1")
	CoeCategArticle findById(Long id);
	
	@Query("select e from CoeCategArticle e where e.aid=?1")
	CoeCategArticle findByAid(Long aid);
	
	@Query("select e from CoeCategArticle e where e.id in ?1")
	public List<CoeCategArticle> findByIdList(List<Long> idList);
	
	@Query("select e from CoeCategArticle e where e.mainId in ?1 and e.deleted=false")
	public List<CoeCategArticle> findByMainIdList(List<Long> mainIdList);
	
	@Query("select e from CoeCategArticle e where e.mainId=?1 and e.deleted=false")
	public List<CoeCategArticle> findByMainId(Long mainId);
}
