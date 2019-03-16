package dml.sz0099.course.app.biz.service.article;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.article.CoeArticle;

/**
 * CoeArticleService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeArticleService extends GenericService<CoeArticle,Long>{


	/**
	 * 根据Id查询CoeArticle实体对象
	 * @param id
	 * @return
	 */
	public CoeArticle findById(Long id);
	
	public boolean existById(Long id);
	
	/**
	 * 根据IdList查询CoeArticle实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeArticle> findByIdList(List<Long> idList);
	
	public List<CoeArticle> findPublishByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeArticle
	 * @return
	 */
	public CoeArticle persistEntity(CoeArticle coeArticle) ;
	
	public CoeArticle createDraft(CoeArticle coeArticle);
	
	
	
	public CoeArticle mergeArticle(CoeArticle coeArticle);
	public CoeArticle mergeForBaseinfo(CoeArticle coeArticle);
	
	public CoeArticle saveOrUpdate(CoeArticle coeArticle);
	
	public CoeArticle mergeForUnPublished(CoeArticle coeArticle);
	
	public CoeArticle mergeArticleForLink(CoeArticle coeArticle);
	
	public CoeArticle mergeArticleForTitle(CoeArticle coeArticle);
	public CoeArticle mergeArticleForTitleOnly(CoeArticle coeArticle);
	public CoeArticle mergeArticleForDescriptionOnly(CoeArticle coeArticle);
	
	
	public List<CoeArticle> findDraftList(CoeArticle coeArticle);
	
	public Long countDraftList(CoeArticle coeArticle);
	
	public CoeArticle findDetail(Long id);
	
	public Page<CoeArticle> findPublished(CoeArticle coeArticle, Pageable pageable);
	
	public CoeArticle mergeForRefresh(CoeArticle coeArticle);
	
	public CoeArticle mergeForEditQickly(CoeArticle coeArticle);
	
	public CoeArticle mergeForPublish(CoeArticle coeArticle) ;
	
	public CoeArticle mergeForClosed(CoeArticle coeArticle);
	
	public CoeArticle mergeForDeleted(CoeArticle coeArticle);
	
	public boolean publishedById(Long id) ;
	
	public List<CoeArticle> findByPublished(CoeArticle coeArticle);
	public Page<CoeArticle> findByPublished(CoeArticle coeArticle, Pageable pageable);
	
	public List<CoeArticle> findPublishedByName(String name);
	
	public List<CoeArticle> findPublishedByTitle(String title);
	
	public Page<CoeArticle> findPageByUserId(Long userId , Pageable pageable);
	public Page<CoeArticle> findPageByUserId(CoeArticle coeArticler , Pageable pageable);
	
	public Long countForPublishedWithoutSelf(Long userId);
	public Page<CoeArticle> findByPublishedNotSelf(CoeArticle coeArticle, Pageable pageable);
	
	public List<CoeArticle> findByUserId(Long userId);
	public Long countByUserId(Long userId);	
	
	public Page<CoeArticle> findPageByMainTypeAndUserId(Integer mainType, List<Long> userIdList, Integer publishStatus, Pageable pageable);
	public Page<CoeArticle> findPageByMainTypeAndUserId(List<Long> userIdList, Pageable pageable);
	public List<CoeArticle> findListByMainTypeAndUserId(List<Long> userIdList);
	public CoeArticle mergeForMainType(CoeArticle coeArticle);
	
	public CoeArticle findMainTypeByUserId(Long userId);
	
	public Long countPublishedByUserId(CoeArticle coeArticle) ;
}
