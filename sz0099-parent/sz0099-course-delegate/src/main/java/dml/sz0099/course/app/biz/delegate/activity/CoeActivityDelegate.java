package dml.sz0099.course.app.biz.delegate.activity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.CoeActivity;

/**
 * CoeActivityDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeActivityDelegate {

	/**
	 * 根据Id查询CoeActivity实体对象
	 * @param id
	 * @return
	 */
	public CoeActivity findById(Long id);
	
	public boolean existById(Long id) ;

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
	
	public CoeActivity mergeActivityForLink(CoeActivity coeActivity);
	
	public CoeActivity mergeActivityForTitle(CoeActivity coeActivity);
	public CoeActivity mergeActivityForTitleOnly(CoeActivity coeActivity);
	public CoeActivity mergeActivityForDescriptionOnly(CoeActivity coeActivity);
	
	public List<CoeActivity> findDraftList(CoeActivity coeActivity);
	
	public Long countDraftList(CoeActivity coeActivity) ;
	
	public Long countTemplateForUser(Long userId) ;
	
	public CoeActivity findDetail(Long id) ;
	
	public Page<CoeActivity> findPublished(CoeActivity coeActivity, Pageable pageable) ;
	
	public CoeActivity mergeForRefresh(CoeActivity coeActivity);
	
	public CoeActivity mergeForEditQickly(CoeActivity coeActivity) ;
	
	public CoeActivity mergeForPublish(CoeActivity coeActivity);
	
	public CoeActivity mergeForClosed(CoeActivity coeActivity) ;
	
	public CoeActivity mergeForTemplate(CoeActivity coeActivity);
	
	public CoeActivity mergeForDeleted(CoeActivity coeActivity);
	
	public boolean publishedById(Long id) ;
	
	public List<CoeActivity> findByPublished(CoeActivity coeActivity);
	public Page<CoeActivity> findByPublished(CoeActivity coeActivity, Pageable pageable);
	
	public List<CoeActivity> findPublishedByName(String name);
	
	public List<CoeActivity> findPublishedByTitle(String title);
	
	public CoeActivity mergeForPraise(CoeActivity coeActivity);
	
	public Page<CoeActivity> findPageByUserId(Long userId , Pageable pageable);
	public Page<CoeActivity> findPageByUserId(CoeActivity coeActivityr , Pageable pageable);
	
	public Page<CoeActivity> findByPublishedNotSelf(CoeActivity coeActivity, Pageable pageable);
	public Long countForPublishedWithoutSelf(Long userId);
	
	public List<CoeActivity> findByUserId(Long userId);
	
	public Long countByUserId(Long userId) ;
	
	public Page<CoeActivity> findPageByMainTypeAndUserId(Integer mainType, List<Long> userIdList, Integer publishStatus, Pageable pageable);
	public Page<CoeActivity> findPageByMainTypeAndUserId(List<Long> userIdList, Pageable pageable);
	public List<CoeActivity> findListByMainTypeAndUserId(List<Long> userIdList);
	public CoeActivity mergeForMainType(CoeActivity coeActivity);
	
	public CoeActivity findMainTypeByUserId(Long userId);
	
	public Page<CoeActivity> findPageForTemplate(Long userId,Integer template, Pageable pageable);
	public Page<CoeActivity> findPageForTemplate(Long userId,Integer template, Long id, Pageable pageable);
	
	public CoeActivity loadTemplate(CoeActivity coeActivity);
}
