package dml.sz0099.course.app.client.wrapper.article;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.article.CoeArticleTag;


/**
 * CoeArticleTagWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeArticleTagWrapper {

	/**
	 * 根据Id查询CoeArticleTag实体对象
	 * @param id
	 * @return
	 */
	public CoeArticleTag findById(Long id);
	
	public CoeArticleTag findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeArticleTag实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeArticleTag> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeArticleTag
	 * @return
	 */
	public CoeArticleTag persistEntity(CoeArticleTag coeArticleTag) ;
	
	public CoeArticleTag mergeEntity(CoeArticleTag coeArticleTag) ; 
	
	public CoeArticleTag saveOrUpdate(CoeArticleTag coeArticleTag) ;
	
	public Page<CoeArticleTag> findPage(CoeArticleTag coeArticleTag, Pageable pageable) ; 
	
	public CoeArticleTag findByMainIdAndName(CoeArticleTag coeArticleTag);
	public Long countByMainId(Long articleId);
	public List<CoeArticleTag> findByMainId(Long articleId);
	public Map<Long,List<CoeArticleTag>> findMapByMainIdList(List<Long> articleIdList);
	
	public CoeArticleTag addTag(CoeArticleTag coeArticleTag);
	public CoeArticleTag deleteTag(CoeArticleTag coeArticleTag);
	
	public Page<CoeArticleTag> findPageWithNotself(CoeArticleTag coeArticleTag, Pageable pageable);
}
