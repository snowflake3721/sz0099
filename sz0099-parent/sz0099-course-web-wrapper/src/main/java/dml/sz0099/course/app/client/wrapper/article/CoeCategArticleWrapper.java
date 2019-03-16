package dml.sz0099.course.app.client.wrapper.article;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.article.CoeCategArticle;


/**
 * CoeCategArticleWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeCategArticleWrapper {

	/**
	 * 根据Id查询CoeCategArticle实体对象
	 * @param id
	 * @return
	 */
	public CoeCategArticle findById(Long id);
	
	public boolean existById(Long id);
	
	public CoeCategArticle findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeCategArticle实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeCategArticle> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeCategArticle
	 * @return
	 */
	public CoeCategArticle persistEntity(CoeCategArticle coeCategArticle) ;
	
	public CoeCategArticle mergeEntity(CoeCategArticle coeCategArticle) ; 
	
	public CoeCategArticle saveOrUpdate(CoeCategArticle coeCategArticle) ;
	
	public Page<CoeCategArticle> findPage(CoeCategArticle coeCategArticle, Pageable pageable) ; 
	public Page<CoeCategArticle> findPageForPublish(CoeCategArticle coeCategArticle, Pageable pageable) ; 
	public Page<CoeCategArticle> findPageForPublishFromDetail(CoeCategArticle coeCategArticle, Pageable pageable);
	
	public Map<Long, List<CoeCategArticle>> findMapByMainIdList(List<Long> articleIdList);
	
	public List<CoeCategArticle> findByMainId(Long articleId);
	
	public CoeCategArticle changeCategory(CoeCategArticle coeCategArticle);
	
	public Page<CoeCategArticle> findPageForPublishWithChildren(CoeCategArticle coeCategArticle, List<Long> excludeMainIdList, Pageable pageable, boolean cover, boolean banner, boolean author);
	public Page<CoeCategArticle> findPageForPublish(CoeCategArticle coeCategArticle, List<Long> excludeMainIdList, Pageable pageable, boolean cover, boolean banner, boolean author);
}
