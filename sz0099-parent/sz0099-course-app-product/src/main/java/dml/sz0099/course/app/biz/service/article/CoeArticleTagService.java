package dml.sz0099.course.app.biz.service.article;

import java.util.List;
import java.util.Map;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.article.CoeArticleTag;
import dml.sz0099.course.app.persist.entity.article.CoeArticleTag;

/**
 * CoeArticleTagService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeArticleTagService extends GenericService<CoeArticleTag,Long>{


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
	 * @param articleTag
	 * @return
	 */
	public CoeArticleTag persistEntity(CoeArticleTag articleTag) ;
	
	
	public CoeArticleTag mergeEntity(CoeArticleTag articleTag) ; 
	
	public CoeArticleTag saveOrUpdate(CoeArticleTag articleTag) ;
	
	public Page<CoeArticleTag> findPage(CoeArticleTag articleTag, Pageable pageable) ;
	
	public CoeArticleTag findByMainIdAndName(CoeArticleTag articleTag);
	
	public CoeArticleTag addTag(CoeArticleTag articleTag) ;
	
	public CoeArticleTag deleteTag(CoeArticleTag articleTag);
	
	public Long countByMainId(Long articleId) ;
	
	public List<CoeArticleTag> findByMainId(Long articleId);
	
	public Map<Long, List<CoeArticleTag>> findMapByMainIdList(List<Long> mainIdList) ;
	
	public Page<CoeArticleTag> findPageWithNotself(CoeArticleTag coeArticleTag, Pageable pageable);
}
