package dml.sz0099.course.app.persist.repository.article;

import java.util.List;

import org.jit4j.app.code.template.CodeGenerated;
import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.article.ParagArticle;

/**
 * @formatter:off
 * 
 * description: ParagArticleRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository("articleParagArticleRepository")
public interface ParagArticleRepository extends BasicJpaRepository<ParagArticle,Long> {

	@Query("select e from ParagArticle e where e.id=?1")
	ParagArticle findById(Long id);
	
	@Query("select e from ParagArticle e where e.aid=?1")
	ParagArticle findByAid(Long aid);
	
	@Query("select e from ParagArticle e where e.id in ?1")
	public List<ParagArticle> findByIdList(List<Long> idList);
	
	@CodeGenerated
	@Query("select e from ParagArticle e where e.mainId=?1")
	public Page<ParagArticle> findByMainId(Long articleId, Pageable pageable);
	
	@CodeGenerated
	@Modifying
	@Query("delete from ParagArticle e where e.mainId=?1 and e.userId=?2")
	public void deleteByArticleIdAndUserId(Long articleId,Long userId);
	
	@Modifying
	@Query("delete from ParagArticle e where e.paragId=?1 and e.userId=?2")
	public void deleteByParagIdAndUserId(Long paragId, Long userId);
	
	@Query("select e from ParagArticle e where e.mainId=?1")
	public List<ParagArticle> findListByMainId(Long articleId);
	
	@Query("select e from ParagArticle e where e.mainId=?1 and e.userId=?2")
	public List<ParagArticle> findListByMainIdAndUserId(Long articleId,Long userId);

	
	@Query("select e from ParagArticle e where e.mainId=?1 and e.userId=?2")
	public Page<ParagArticle> findByMainIdAndUserId(Long articleId, Long userId, Pageable pageable );
	
	@Query("select count(e) from ParagArticle e where e.mainId=?1")
	public Long countByMainId(Long articleId);
}
