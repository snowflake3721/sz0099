package dml.sz0099.course.app.client.wrapper.article;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.article.ParagArticle;


/**
 * ParagArticleWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ParagArticleWrapper {

	/**
	 * 根据Id查询ParagArticle实体对象
	 * @param id
	 * @return
	 */
	public ParagArticle findById(Long id);
	
	public boolean existById(Long id);
	
	public ParagArticle findByAid(Long aid);
	
	/**
	 * 根据IdList查询ParagArticle实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ParagArticle> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param paragArticle
	 * @return
	 */
	public ParagArticle persistEntity(ParagArticle paragArticle) ;
	
	public ParagArticle mergeEntity(ParagArticle paragArticle) ; 
	
	public ParagArticle saveOrUpdate(ParagArticle paragArticle) ;
	
	public Page<ParagArticle> findPage(ParagArticle paragArticle, Pageable pageable) ; 
	

	public Long countByMainId(Long articleId);
	public Page<ParagArticle> findByMainId(Long articleId, Pageable pageable );
	public Page<ParagArticle> findByMainIdAndUserId(Long articleId, Long userId, Pageable pageable );

	public Page<ParagArticle> resetOrderSeq(Long articleId, Long userId);

	public void deleteByArticleIdAndUserId(Long articleId, Long userId );
	public ParagArticle createParagArticle(ParagArticle paragArticle);
	public void deleteByParagIdAndUserId(Long paragId, Long userId, boolean cascade );

}
