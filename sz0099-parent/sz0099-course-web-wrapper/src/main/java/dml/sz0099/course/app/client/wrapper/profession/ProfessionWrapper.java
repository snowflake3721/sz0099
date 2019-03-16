package dml.sz0099.course.app.client.wrapper.profession;

import java.util.List;

import org.jit4j.core.persist.page.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.profession.Profession;


/**
 * ProfessionWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ProfessionWrapper {

	/**
	 * 根据Id查询Profession实体对象
	 * @param id
	 * @return
	 */
	public Profession findById(Long id);
	public Profession findByIdOnly(Long id);
	public Profession findById(Long id, boolean withTags, boolean withCover, boolean withBanner,
			boolean withCategory, boolean withCategoryTree, boolean withPraise,
			boolean withAuthorPayCode, boolean withSayword);
	public Profession findByIdWithCoverAndBanner(Long id);
	
	public boolean existById(Long id);
	
	public Profession findByAid(Long aid);
	
	public Profession findDetail(Long id);
	
	/**
	 * 根据IdList查询Profession实体对象列表
	 * @param idList
	 * @return
	 */
	public List<Profession> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param profession
	 * @return
	 */
	public Profession persistEntity(Profession profession) ;
	
	public Profession mergeEntity(Profession profession) ; 
	
	public Profession saveOrUpdate(Profession profession) ;
	
	public Page<Profession> findPage(Profession profession, Pageable pageable) ; 
	
	public Profession mergeForPublish(Profession profession);
	public Profession mergeForClosed(Profession profession);
	public Profession mergeForDeleted(Profession profession);
	public Profession mergeForRefresh(Profession profession);
	public Profession mergeForUnPublished(Profession profession);
	
	public Page<Profession> findPublished(Profession profession, Pageable pageable);
	public List<Profession> findDraftList(Profession profession);
	public List<Profession> findByUserId(Long userId) ;
	public Long countByUserId(Long userId) ;
	public Long countDraftList(Profession profession);
	public Profession createDraft(Profession profession);
	
	public Profession mergeForBaseinfo(Profession profession);
	public Profession mergeProfessionForTitle(Profession profession);
	public Profession mergeArticleForTitleOnly(Profession profession);
	public Profession mergeArticleForDescriptionOnly(Profession profession);
	
	public Profession persistForCover(Profession profession);
	public Profession persistForBanner(Profession profession);
	
	public PageResult<Profession> findPublishedForSelect(Profession profession, Pageable pageable);
	public Page<Profession> findByPublishedWithAuthor(Profession profession, Pageable pageable);
	public Page<Profession> findByPublished(Profession profession, Pageable pageable);
	public Page<Profession> findPageForRecommend(Profession profession, Pageable pageable);
	
	public Profession mergeForEditQickly(Profession profession);
	public Category findCategoryTree(Long mainId, Long subId);
	
	public List<Profession> findByIdList(List<Long> idList,boolean withTags, boolean withCover, boolean withBanner, 
			boolean withCategory, boolean withPraise, boolean withAuthorPayCode);
	public List<Profession> fillRefBase(List<Profession> content, boolean withTags, boolean withCover, boolean withBanner, boolean withCategory,
			boolean withPraise, boolean withAuthorPayCode);
	public List<Profession> fillRefForList(List<Profession> contentList);
	public List<Profession> fillRefWithCoverAndBannerAndAuthor(List<Profession> contentList);
	public List<Profession> fillRefWithCoverAndBanner(List<Profession> contentList);
	
	public Page<Profession> findPageForInvitor(Profession profession, Pageable pageable);
	public Page<Profession> findPageForInvitor(Long createdBy, Long userId);
	public Page<Profession> findPageForCurrentUser(Long userId);
	public Page<Profession> findPageForCurrentUser(Profession profession, Pageable pageable);
	public Page<Profession> findPageTagForCurrentUser(Long userId);
	public Page<Profession> findPageRefForUser(Profession profession, Pageable pageable);
	
	public Long countForPublishedWithoutSelf(Long userId, Long positionId);
	public Page<Profession> findPageForRandomUserId(Long userId);
	
	public Page<Profession> findPageByMainTypeAndUserId(Integer mainType, List<Long> userIdList, Integer publishStatus, Pageable pageable);
	public Page<Profession> findPageByMainTypeAndUserId(List<Long> userIdList, Pageable pageable);
	public List<Profession> findListByMainTypeAndUserId(List<Long> userIdList);
	public Profession findDetailWithMainTypeList(Long id);
	public Profession findDetailFilledMainTypeList(Long id);
	public Profession findDetailFilledMainTypePage(Long id);
	public Profession mergeForMainType(Profession profession) ;
	public Profession findProfessionForPraisePage(Long id, Pageable pageable) ;
	public Page<Profession> findPublishedWithCoverAndBanner(Profession profession, Pageable pageable);
	
	public Page<Profession> findPageByUserId(Long userId, Pageable pageable);
	public Page<Profession> findPageByUserId(Long userId, Pageable pageable, boolean withCover,boolean withBanner);
	
}
