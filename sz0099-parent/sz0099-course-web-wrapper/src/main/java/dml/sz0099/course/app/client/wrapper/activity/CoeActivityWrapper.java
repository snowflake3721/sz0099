package dml.sz0099.course.app.client.wrapper.activity;

import java.util.List;

import org.jit4j.core.persist.page.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTag;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.category.Category;


/**
 * CourseActivityWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeActivityWrapper {

	/**
	 * 根据Id查询CoeActivity实体对象
	 * @param id
	 * @return
	 */
	public CoeActivity findById(Long id);
	public CoeActivity findByIdOnly(Long id) ;
	public CoeActivity findByIdWithTimeAndFee(Long id);
	public CoeActivity findByIdWithCoverAndBanner(Long id);
	public boolean existById(Long id);
	public boolean publishedById(Long id);
	public CoeActivity findById(Long id, boolean withTags, boolean withCover, boolean withBanner, 
			boolean withCategory, boolean withCategoryTree, boolean withPraise,
			boolean withAuthorPayCode, boolean withSayword, boolean withJoinItem, boolean withFee);
	
	/**
	 * 根据IdList查询CoeActivity实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeActivity> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeActivity
	 * @return
	 */
	public CoeActivity persistEntity(CoeActivity coeActivity) ;
	
	public CoeActivity createDraft(CoeActivity coeActivity) ;
	
	public CoeActivity mergeActivity(CoeActivity coeActivity);
	public CoeActivity mergeForBaseinfo(CoeActivity coeActivity);
	
	public CoeActivity saveOrUpdate(CoeActivity coeActivity);
	
	public CoeActivity mergeForUnPublished(CoeActivity coeActivity);
	public CoeActivity mergeForRefresh(CoeActivity coeActivity);
	public CoeActivity mergeForEditQickly(CoeActivity coeActivity);
	
	
	public CoeActivity mergeActivityForLink(CoeActivity coeActivity);
	
	public CoeActivity mergeActivityForTitle(CoeActivity coeActivity);
	public CoeActivity mergeActivityForTitleOnly(CoeActivity coeActivity);
	public CoeActivity mergeActivityForDescriptionOnly(CoeActivity coeActivity);
	
	public List<CoeActivity> findByPublished(CoeActivity coeActivity);
	public Page<CoeActivity> findByPublished(CoeActivity coeActivity, Pageable pageable);
	public Page<CoeActivity> findPageForRecommend(CoeActivity coeActivity, Pageable pageable);
	
	public List<CoeActivity> findPublishedByName(String name);
	
	public List<CoeActivity> findPublishedByTitle(String title);
	
	public List<CoeActivity> findDraftList(CoeActivity coeActivity);
	public Long countDraftList(CoeActivity coeActivity);
	
	public CoeActivity findDetail(Long id);
	
	public CoeActivity mergeForPublish(CoeActivity coeActivity);
	public CoeActivity mergeForClosed(CoeActivity coeActivity);
	public CoeActivity mergeForTemplate(CoeActivity coeActivity);
	public CoeActivity mergeForDeleted(CoeActivity coeActivity);
	public CoeActivity mergeForPraise(CoeActivity coeActivity);
	public Page<CoeActivity> findPublished(CoeActivity coeActivity, Pageable pageable);
	
	
	public CoeActivity persistForCover(CoeActivity coeActivity);
	public CoeActivity persistForBanner(CoeActivity coeActivity);
	
	public PageResult<CoeActivity> findPublishedForSelect(CoeActivity coeActivity, Pageable pageable);
	public Page<CoeActivity> findByPublished(CoeActivity coeActivity, Pageable pageable, boolean withCategoryTree);
	public Category findCategoryTree(Long mainId, Long subId);
	
	public List<CoeActivity> findByIdList(List<Long> idList,boolean withTags, boolean withCover, boolean withBanner, 
			boolean withCategory, boolean withPraise, boolean withAuthorPayCode, boolean withJoinItem , boolean withFee);
	public List<CoeActivity> fillRef(List<CoeActivity> content, boolean withTags, boolean withCover, boolean withBanner, boolean withCategory,
			boolean withPraise, boolean withAuthorPayCode, boolean withJoinItem , boolean withFee);
	public List<CoeActivity> fillRefForList(List<CoeActivity> contentList);
	
	public List<CoeActivity> fillRefWithCoverAndBanner(List<CoeActivity> contentList);
	public List<CoeActivity> fillRefWithCoverAndBannerAndAuthor(List<CoeActivity> contentList);
	public Page<CoeActivity> findPublishedWithCoverAndBanner(CoeActivity coeActivity, Pageable pageable);
	
	public CoeActivity findDetailLastRefreshByUserId(Long userId);
	
	
	public Page<CoeActivity> findPageForInvitor(CoeActivity coeActivity, Pageable pageable);
	public Page<CoeActivity> findPageForInvitor(Long createdBy, Long userId);
	public Page<CoeActivity> findPageForCurrentUser(Long userId);
	public Page<CoeActivity> findPageForCurrentUser(CoeActivity coeActivity, Pageable pageable);
	public Page<CoeActivity> findPageTagForCurrentUser(Long userId);
	public Page<CoeActivity> findPageRefForUser(CoeActivity coeActivity, Pageable pageable);
	
	public Long countForPublishedWithoutSelf(Long userId);
	public Long countTemplateForUser(Long userId);
	
	public Page<CoeActivity> findPageForRandomUserId(Long userId);
	
	public Page<CoeActivity> findPageByMainTypeAndUserId(Integer mainType, List<Long> userIdList, Integer publishStatus, Pageable pageable);
	public Page<CoeActivity> findPageByMainTypeAndUserId(List<Long> userIdList, Pageable pageable);
	public List<CoeActivity> findListByMainTypeAndUserId(List<Long> userIdList);
	public CoeActivity findMainTypeByUserId(Long userId);
	public CoeActivity findDetailWithMainTypeList(Long id);
	public CoeActivity findDetailFilledMainTypeList(Long id);
	public CoeActivity findDetailFilledMainTypePage(Long id);
	public CoeActivity mergeForMainType(CoeActivity coeActivity) ;
	public CoeActivity findCoeActivityForPraisePage(Long id, Pageable pageable) ;
	
	public Page<CoeActivity> findPageForTemplate(Long userId,Integer template, Pageable pageable);
	public Page<CoeActivity> findPageForTemplate(Long userId,Integer template, Long id, Pageable pageable);
	public CoeActivity loadTemplate(CoeActivity coeActivity) ;
	
}
