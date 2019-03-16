package dml.sz0099.course.app.persist.repository.article;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.article.CoeArticleTag;
import dml.sz0099.course.app.persist.entity.article.CoeArticleTag;

/**
 * @formatter:off
 * 
 * description: CoeArticleTagRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface CoeArticleTagRepository extends BasicJpaRepository<CoeArticleTag,Long> {

	@Query("select e from CoeArticleTag e where e.id=?1")
	CoeArticleTag findById(Long id);
	
	@Query("select e from CoeArticleTag e where e.aid=?1")
	CoeArticleTag findByAid(Long aid);
	
	@Query("select e from CoeArticleTag e where e.id in ?1")
	public List<CoeArticleTag> findByIdList(List<Long> idList);
	
	@Query("select e from CoeArticleTag e where e.mainId=?1 and e.name=?2")
	public CoeArticleTag findByMainIdAndName(Long articleId, String name);
	
	@Query("select count(e) from CoeArticleTag e where e.mainId=?1")
	public Long countByMainId(Long articleId) ;
	
	@Query("select e from CoeArticleTag e where e.mainId=?1")
	public List<CoeArticleTag> findByMainId(Long articleId) ;
	
	@Query("select e from CoeArticleTag e where e.mainId in ?1 and e.deleted=false")
	public List<CoeArticleTag> findByMainIdList(List<Long> articleIdList) ;

}
