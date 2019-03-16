package dml.sz0099.course.app.persist.dao.article;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.article.ParagArticle;

/**
 * ParagArticleDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ParagArticleDao extends GenericDao<ParagArticle,Long>{

	/**
	 * 根据Id查询ParagArticle实体对象
	 * @param id
	 * @return
	 */
	ParagArticle findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	ParagArticle findByAid(Long aid);
	
	/**
	 * 根据IdList查询ParagArticle实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ParagArticle> findByIdList(List<Long> idList);
	
	public Page<ParagArticle> findPage(ParagArticle paragProduct, Pageable pageable);
	public List<ParagArticle> findListByMainId(Long articleId);
	public List<ParagArticle> findListByMainIdAndUserId(Long articleId,Long userId);


	public Page<ParagArticle> findByMainId(Long articleId, Pageable pageable );
	public Page<ParagArticle> findByMainIdAndUserId(Long articleId, Long userId, Pageable pageable );



	public void deleteByArticleIdAndUserId(Long articleId, Long userId );
	public Long countByMainId(Long articleId);
	public void deleteByParagIdAndUserId(Long paragId, Long userId, boolean cascade );

}
