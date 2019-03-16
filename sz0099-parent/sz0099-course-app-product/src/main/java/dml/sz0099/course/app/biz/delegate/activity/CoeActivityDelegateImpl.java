package dml.sz0099.course.app.biz.delegate.activity;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.activity.CoeActivityService;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;

/**
 * CoeActivityDelegateImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeActivityDelegateImpl implements CoeActivityDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityDelegateImpl.class);
	
	@Autowired
	private CoeActivityService coeActivityService;

	/**
	 * 根据Id查询CoeActivity实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivity findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeActivity coeActivity = coeActivityService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeActivity);
		return coeActivity;
	}
	
	public boolean existById(Long id) {
		return coeActivityService.existById(id);
	}
	
	/**
	 * 根据IdList查询CoeActivity实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivity> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeActivity> coeActivityList = coeActivityService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeActivityList);
		return coeActivityList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeActivity persistEntity(CoeActivity coeActivity) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeActivity entity = coeActivityService.persistEntity(coeActivity);
		Long id = coeActivity.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}
	
	public CoeActivity createDraft(CoeActivity coeActivity) {
		
		CoeActivity entity = coeActivityService.createDraft(coeActivity);
		return entity;
	}

	@Override
	public CoeActivity mergeActivity(CoeActivity coeActivity) {
		return coeActivityService.mergeActivity(coeActivity);
	}
	@Override
	public CoeActivity mergeForBaseinfo(CoeActivity coeActivity) {
		return coeActivityService.mergeForBaseinfo(coeActivity);
	}

	@Override
	public CoeActivity saveOrUpdate(CoeActivity coeActivity) {
		return coeActivityService.saveOrUpdate(coeActivity);
	}

	@Override
	public CoeActivity mergeForUnPublished(CoeActivity coeActivity) {
		return coeActivityService.mergeForUnPublished(coeActivity);
	}

	@Override
	public CoeActivity mergeActivityForLink(CoeActivity coeActivity) {
		return coeActivityService.mergeActivityForLink(coeActivity);
	}

	@Override
	public CoeActivity mergeActivityForTitle(CoeActivity coeActivity) {
		return coeActivityService.mergeActivityForTitle(coeActivity);
	}
	
	@Override
	public CoeActivity mergeActivityForTitleOnly(CoeActivity coeActivity) {
		return coeActivityService.mergeActivityForTitleOnly(coeActivity);
	}
	public CoeActivity mergeActivityForDescriptionOnly(CoeActivity coeActivity) {
		return coeActivityService.mergeActivityForDescriptionOnly(coeActivity);
	}

	@Override
	public List<CoeActivity> findDraftList(CoeActivity coeActivity){
		return coeActivityService.findDraftList(coeActivity);
	}
	
	@Override
	public Long countDraftList(CoeActivity coeActivity) {
		return coeActivityService.countDraftList(coeActivity);
	}
	
	public Long countTemplateForUser(Long userId) {
		return coeActivityService.countTemplateForUser(userId);
	}
	
	public CoeActivity findDetail(Long id) {
		return coeActivityService.findDetail(id);
	}
	
	public Page<CoeActivity> findPublished(CoeActivity coeActivity, Pageable pageable) {
		return coeActivityService.findPublished(coeActivity,pageable);
	}
	
	public CoeActivity mergeForRefresh(CoeActivity coeActivity) {
		return coeActivityService.mergeForRefresh(coeActivity);
	}
	
	public CoeActivity mergeForEditQickly(CoeActivity coeActivity) {
		return coeActivityService.mergeForEditQickly(coeActivity);
	}
	
	public CoeActivity mergeForPublish(CoeActivity coeActivity) {
		return coeActivityService.mergeForPublish(coeActivity);
	}
	
	public CoeActivity mergeForClosed(CoeActivity coeActivity) {
		return coeActivityService.mergeForClosed(coeActivity);
	}
	
	public CoeActivity mergeForTemplate(CoeActivity coeActivity) {
		return coeActivityService.mergeForTemplate(coeActivity);
	}
	
	public CoeActivity mergeForDeleted(CoeActivity coeActivity) {
		return coeActivityService.mergeForDeleted(coeActivity);
	}
	
	public boolean publishedById(Long id) {
		boolean published = coeActivityService.publishedById(id);
		return published;
	}
	
	@Override
	public List<CoeActivity> findByPublished(CoeActivity coeActivity) {
		return coeActivityService.findByPublished(coeActivity);
	}
	@Override
	public Page<CoeActivity> findByPublished(CoeActivity coeActivity, Pageable pageable){
		return coeActivityService.findByPublished(coeActivity,pageable);
	}

	@Override
	public List<CoeActivity> findPublishedByName(String name) {
		return coeActivityService.findPublishedByName(name);
	}

	@Override
	public List<CoeActivity> findPublishedByTitle(String title) {
		return coeActivityService.findPublishedByTitle(title);
	}
	
	public CoeActivity mergeForPraise(CoeActivity coeActivity) {
		return null;//coeActivityService.mergeForPraise(coeActivity);
	}
	
	public Page<CoeActivity> findPageByUserId(Long userId , Pageable pageable){
		return coeActivityService.findPageByUserId( userId ,  pageable);
	}
	
	public Page<CoeActivity> findPageByUserId(CoeActivity coeActivityr , Pageable pageable){
		return coeActivityService.findPageByUserId( coeActivityr ,  pageable);
	}
	
	public Long countForPublishedWithoutSelf(Long userId) {
		return coeActivityService.countForPublishedWithoutSelf( userId);
	}
	
	public Page<CoeActivity> findByPublishedNotSelf(CoeActivity coeActivity, Pageable pageable){
		return coeActivityService.findByPublishedNotSelf(coeActivity,pageable);
	}
	
	@Override
	public List<CoeActivity> findByUserId(Long userId) {
		return coeActivityService.findByUserId( userId) ;
	}
	
	public Long countByUserId(Long userId) {
		return coeActivityService.countByUserId(userId);
	}
	
	public Page<CoeActivity> findPageByMainTypeAndUserId(Integer mainType, List<Long> userIdList, Integer publishStatus, Pageable pageable){
		return coeActivityService.findPageByMainTypeAndUserId(mainType, userIdList, publishStatus, pageable);
	}
	
	public Page<CoeActivity> findPageByMainTypeAndUserId(List<Long> userIdList, Pageable pageable){
		return coeActivityService.findPageByMainTypeAndUserId(userIdList, pageable);
	}
	
	public List<CoeActivity> findListByMainTypeAndUserId(List<Long> userIdList){
		return coeActivityService.findListByMainTypeAndUserId( userIdList);
	}
	
	public CoeActivity mergeForMainType(CoeActivity coeActivity) {
		return coeActivityService.mergeForMainType(coeActivity);
	}
	
	public CoeActivity findMainTypeByUserId(Long userId) {
		return coeActivityService.findMainTypeByUserId(userId);
	}
	
	public Page<CoeActivity> findPageForTemplate(Long userId,Integer template, Pageable pageable){
		return coeActivityService.findPageForTemplate( userId, template, pageable);
	}
	public Page<CoeActivity> findPageForTemplate(Long userId,Integer template, Long id, Pageable pageable){
		return coeActivityService.findPageForTemplate( userId, template, id, pageable);
	}
	
	public CoeActivity loadTemplate(CoeActivity coeActivity) {
		return coeActivityService.loadTemplate( coeActivity);
	}
}
