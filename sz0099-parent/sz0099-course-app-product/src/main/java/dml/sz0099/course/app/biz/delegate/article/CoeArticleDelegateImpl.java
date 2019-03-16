package dml.sz0099.course.app.biz.delegate.article;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.article.CoeArticleService;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;

/**
 * CoeArticleDelegateImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeArticleDelegateImpl implements CoeArticleDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeArticleDelegateImpl.class);
	
	@Autowired
	private CoeArticleService coeArticleService;

	/**
	 * 根据Id查询CoeArticle实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeArticle findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeArticle coeArticle = coeArticleService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeArticle);
		return coeArticle;
	}
	
	public boolean existById(Long id) {
		return coeArticleService.existById(id);
	}
	
	/**
	 * 根据IdList查询CoeArticle实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeArticle> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeArticle> coeArticleList = coeArticleService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeArticleList);
		return coeArticleList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeArticle persistEntity(CoeArticle coeArticle) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeArticle entity = coeArticleService.persistEntity(coeArticle);
		Long id = coeArticle.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}
	
	public CoeArticle createDraft(CoeArticle coeArticle) {
		
		CoeArticle entity = coeArticleService.createDraft(coeArticle);
		return entity;
	}

	@Override
	public CoeArticle mergeArticle(CoeArticle coeArticle) {
		return coeArticleService.mergeArticle(coeArticle);
	}
	@Override
	public CoeArticle mergeForBaseinfo(CoeArticle coeArticle) {
		return coeArticleService.mergeForBaseinfo(coeArticle);
	}

	@Override
	public CoeArticle saveOrUpdate(CoeArticle coeArticle) {
		return coeArticleService.saveOrUpdate(coeArticle);
	}

	@Override
	public CoeArticle mergeForUnPublished(CoeArticle coeArticle) {
		return coeArticleService.mergeForUnPublished(coeArticle);
	}

	@Override
	public CoeArticle mergeArticleForLink(CoeArticle coeArticle) {
		return coeArticleService.mergeArticleForLink(coeArticle);
	}

	@Override
	public CoeArticle mergeArticleForTitle(CoeArticle coeArticle) {
		return coeArticleService.mergeArticleForTitle(coeArticle);
	}
	@Override
	public CoeArticle mergeArticleForTitleOnly(CoeArticle coeArticle) {
		return coeArticleService.mergeArticleForTitleOnly(coeArticle);
	}
	public CoeArticle mergeArticleForDescriptionOnly(CoeArticle coeArticle) {
		return coeArticleService.mergeArticleForDescriptionOnly(coeArticle);
	}

	@Override
	public List<CoeArticle> findDraftList(CoeArticle coeArticle){
		return coeArticleService.findDraftList(coeArticle);
	}
	
	@Override
	public Long countDraftList(CoeArticle coeArticle) {
		return coeArticleService.countDraftList(coeArticle);
	}
	
	public CoeArticle findDetail(Long id) {
		return coeArticleService.findDetail(id);
	}
	
	public Page<CoeArticle> findPublished(CoeArticle coeArticle, Pageable pageable) {
		return coeArticleService.findPublished(coeArticle,pageable);
	}
	
	public CoeArticle mergeForRefresh(CoeArticle coeArticle) {
		return coeArticleService.mergeForRefresh(coeArticle);
	}
	
	public CoeArticle mergeForEditQickly(CoeArticle coeArticle) {
		return coeArticleService.mergeForEditQickly(coeArticle);
	}
	
	public CoeArticle mergeForPublish(CoeArticle coeArticle) {
		return coeArticleService.mergeForPublish(coeArticle);
	}
	
	public CoeArticle mergeForClosed(CoeArticle coeArticle) {
		return coeArticleService.mergeForClosed(coeArticle);
	}
	
	public CoeArticle mergeForDeleted(CoeArticle coeArticle) {
		return coeArticleService.mergeForDeleted(coeArticle);
	}
	
	public boolean publishedById(Long id) {
		boolean published = coeArticleService.publishedById(id);
		return published;
	}
	
	@Override
	public List<CoeArticle> findByPublished(CoeArticle coeArticle) {
		return coeArticleService.findByPublished(coeArticle);
	}
	@Override
	public Page<CoeArticle> findByPublished(CoeArticle coeArticle, Pageable pageable){
		return coeArticleService.findByPublished(coeArticle,pageable);
	}

	@Override
	public List<CoeArticle> findPublishedByName(String name) {
		return coeArticleService.findPublishedByName(name);
	}

	@Override
	public List<CoeArticle> findPublishedByTitle(String title) {
		return coeArticleService.findPublishedByTitle(title);
	}
	
	public CoeArticle mergeForPraise(CoeArticle coeArticle) {
		return null;//coeArticleService.mergeForPraise(coeArticle);
	}
	
	public Page<CoeArticle> findPageByUserId(Long userId , Pageable pageable){
		return coeArticleService.findPageByUserId( userId ,  pageable);
	}
	
	public Page<CoeArticle> findPageByUserId(CoeArticle coeArticler , Pageable pageable){
		return coeArticleService.findPageByUserId( coeArticler ,  pageable);
	}
	
	public Long countForPublishedWithoutSelf(Long userId) {
		return coeArticleService.countForPublishedWithoutSelf( userId);
	}
	
	public Page<CoeArticle> findByPublishedNotSelf(CoeArticle coeArticle, Pageable pageable){
		return coeArticleService.findByPublishedNotSelf(coeArticle,pageable);
	}
	
	@Override
	public List<CoeArticle> findByUserId(Long userId) {
		return coeArticleService.findByUserId( userId) ;
	}
	
	public Long countByUserId(Long userId) {
		return coeArticleService.countByUserId(userId);
	}
	
	public Page<CoeArticle> findPageByMainTypeAndUserId(Integer mainType, List<Long> userIdList, Integer publishStatus, Pageable pageable){
		return coeArticleService.findPageByMainTypeAndUserId(mainType, userIdList, publishStatus, pageable);
	}
	
	public Page<CoeArticle> findPageByMainTypeAndUserId(List<Long> userIdList, Pageable pageable){
		return coeArticleService.findPageByMainTypeAndUserId(userIdList, pageable);
	}
	
	public List<CoeArticle> findListByMainTypeAndUserId(List<Long> userIdList){
		return coeArticleService.findListByMainTypeAndUserId( userIdList);
	}
	
	public CoeArticle mergeForMainType(CoeArticle coeArticle) {
		return coeArticleService.mergeForMainType(coeArticle);
	}
	
	public CoeArticle findMainTypeByUserId(Long userId) {
		return coeArticleService.findMainTypeByUserId(userId);
	}
	
	public Long countPublishedByUserId(CoeArticle coeArticle) {
		return coeArticleService.countPublishedByUserId(coeArticle);
	}
}
