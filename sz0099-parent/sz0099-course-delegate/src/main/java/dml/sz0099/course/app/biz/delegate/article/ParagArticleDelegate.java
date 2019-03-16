package dml.sz0099.course.app.biz.delegate.article;

import java.util.List;

import org.jit4j.core.persist.page.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import dml.sz0099.course.app.persist.entity.article.ParagArticle;

/**
 * ParagArticleDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ParagArticleDelegate {

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
	

	public Page<ParagArticle> findByMainId(Long professionId, Pageable pageable );
	public Page<ParagArticle> findByMainIdAndUserId(Long professionId, Long userId, Pageable pageable );
	public Page<ParagArticle> resetOrderSeq(Long articleId, Long userId );
	
	public void deleteByArticleIdAndUserId(Long professionId, Long userId );

	public ParagArticle createParagArticle(ParagArticle paragArticle);
	
	public Long countByMainId(Long professionId) ;
	
	public void deleteByParagIdAndUserId(Long paragId, Long userId, boolean cascade );

}
