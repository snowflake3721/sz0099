package dml.sz0099.course.app.client.wrapper.article;

import java.util.List;

import org.jit4j.core.persist.page.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.CoeArticleTag;
import dml.sz0099.course.app.persist.entity.category.Category;


/**
 * CourseArticleWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeArticleWrapper {

	/**
	 * 根据Id查询CoeArticle实体对象
	 * @param id
	 * @return
	 */
	public CoeArticle findById(Long id);
	public CoeArticle findByIdOnly(Long id) ;
	public CoeArticle findByIdWithCoverAndBanner(Long id);
	public boolean existById(Long id);
	public boolean publishedById(Long id);
	public CoeArticle findById(Long id, boolean withTags, boolean withCover, boolean withBanner, 
			boolean withCategory, boolean withCategoryTree, boolean withPraise,
			boolean withAuthorPayCode, boolean withSayword);
	
	/**
	 * 根据IdList查询CoeArticle实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeArticle> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeArticle
	 * @return
	 */
	public CoeArticle persistEntity(CoeArticle coeArticle) ;
	
	public CoeArticle createDraft(CoeArticle coeArticle) ;
	
	public CoeArticle mergeArticle(CoeArticle coeArticle);
	public CoeArticle mergeForBaseinfo(CoeArticle coeArticle);
	
	public CoeArticle saveOrUpdate(CoeArticle coeArticle);
	
	public CoeArticle mergeForUnPublished(CoeArticle coeArticle);
	public CoeArticle mergeForRefresh(CoeArticle coeArticle);
	public CoeArticle mergeForEditQickly(CoeArticle coeArticle);
	
	
	public CoeArticle mergeArticleForLink(CoeArticle coeArticle);
	
	public CoeArticle mergeArticleForTitle(CoeArticle coeArticle);
	public CoeArticle mergeArticleForTitleOnly(CoeArticle coeArticle);
	public CoeArticle mergeArticleForDescriptionOnly(CoeArticle coeArticle);
	
	public List<CoeArticle> findByPublished(CoeArticle coeArticle);
	public Page<CoeArticle> findByPublished(CoeArticle coeArticle, Pageable pageable);
	public Page<CoeArticle> findPageForRecommend(CoeArticle coeArticle, Pageable pageable);
	
	public List<CoeArticle> findPublishedByName(String name);
	
	public List<CoeArticle> findPublishedByTitle(String title);
	
	public List<CoeArticle> findDraftList(CoeArticle coeArticle);
	public Long countDraftList(CoeArticle coeArticle);
	public Long countPublishedByUserId(CoeArticle coeArticle);
	public Long countPublishedByUserId(Long userId);
	
	public CoeArticle findDetail(Long id);
	
	public CoeArticle mergeForPublish(CoeArticle coeArticle);
	public CoeArticle mergeForClosed(CoeArticle coeArticle);
	public CoeArticle mergeForDeleted(CoeArticle coeArticle);
	public CoeArticle mergeForPraise(CoeArticle coeArticle);
	public Page<CoeArticle> findPublished(CoeArticle coeArticle, Pageable pageable);
	
	
	public CoeArticle persistForCover(CoeArticle coeArticle);
	public CoeArticle persistForBanner(CoeArticle coeArticle);
	
	public PageResult<CoeArticle> findPublishedForSelect(CoeArticle coeArticle, Pageable pageable);
	public Page<CoeArticle> findByPublished(CoeArticle coeArticle, Pageable pageable, boolean withCategoryTree);
	public Category findCategoryTree(Long mainId, Long subId);
	
	public List<CoeArticle> findByIdList(List<Long> idList,boolean withTags, boolean withCover, boolean withBanner, 
			boolean withCategory, boolean withPraise, boolean withAuthorPayCode);
	public List<CoeArticle> fillRef(List<CoeArticle> content, boolean withTags, boolean withCover, boolean withBanner, boolean withCategory,
			boolean withPraise, boolean withAuthorPayCode);
	public List<CoeArticle> fillRefForList(List<CoeArticle> contentList);
	
	public List<CoeArticle> fillRefWithCoverAndBanner(List<CoeArticle> contentList);
	public List<CoeArticle> fillRefWithCoverAndBannerAndAuthor(List<CoeArticle> contentList);
	public Page<CoeArticle> findPublishedWithCoverAndBanner(CoeArticle coeArticle, Pageable pageable);
	
	public CoeArticle findDetailLastRefreshByUserId(Long userId);
	
	
	public Page<CoeArticle> findPageForInvitor(CoeArticle coeArticle, Pageable pageable);
	public Page<CoeArticle> findPageForInvitor(Long createdBy, Long userId);
	public Page<CoeArticle> findPageForCurrentUser(Long userId);
	public Page<CoeArticle> findPageForCurrentUser(CoeArticle coeArticle, Pageable pageable);
	public Page<CoeArticle> findPageTagForCurrentUser(Long userId);
	public Page<CoeArticle> findPageRefForUser(CoeArticle coeArticle, Pageable pageable);
	
	public Long countForPublishedWithoutSelf(Long userId);
	public Page<CoeArticle> findPageForRandomUserId(Long userId);
	
	public Page<CoeArticle> findPageByMainTypeAndUserId(Integer mainType, List<Long> userIdList, Integer publishStatus, Pageable pageable);
	public Page<CoeArticle> findPageByMainTypeAndUserId(List<Long> userIdList, Pageable pageable);
	public List<CoeArticle> findListByMainTypeAndUserId(List<Long> userIdList);
	public CoeArticle findMainTypeByUserId(Long userId);
	public CoeArticle findDetailWithMainTypeList(Long id);
	public CoeArticle findDetailFilledMainTypeList(Long id);
	public CoeArticle findDetailFilledMainTypePage(Long id);
	public CoeArticle mergeForMainType(CoeArticle coeArticle) ;
	public CoeArticle findCoeArticleForPraisePage(Long id, Pageable pageable) ;
	
}
