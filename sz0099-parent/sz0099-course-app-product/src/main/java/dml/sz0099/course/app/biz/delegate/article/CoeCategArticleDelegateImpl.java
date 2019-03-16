package dml.sz0099.course.app.biz.delegate.article;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.article.CoeCategArticleService;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.CoeCategArticle;

/**
 * coeCategArticleServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeCategArticleDelegateImpl implements CoeCategArticleDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeCategArticleDelegateImpl.class);
	
	@Autowired
	private CoeCategArticleService coeCategArticleService;

	/**
	 * 根据Id查询CoeCategArticle实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeCategArticle findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeCategArticle coeCategArticle = coeCategArticleService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeCategArticle);
		return coeCategArticle;
	}
	
	@Override
	public CoeCategArticle findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeCategArticle coeCategArticle = coeCategArticleService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeCategArticle);
		return coeCategArticle;
	}
	
	/**
	 * 根据IdList查询CoeCategArticle实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeCategArticle> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeCategArticle> coeCategArticleList = coeCategArticleService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeCategArticleList);
		return coeCategArticleList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeCategArticle persistEntity(CoeCategArticle coeCategArticle) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeCategArticle entity = coeCategArticleService.persistEntity(coeCategArticle);
		Long id = coeCategArticle.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeCategArticle mergeEntity(CoeCategArticle coeCategArticle) {
		Long id = coeCategArticle.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeCategArticle entity = coeCategArticleService.mergeEntity(coeCategArticle);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeCategArticle saveOrUpdate(CoeCategArticle coeCategArticle) {
		Long id = coeCategArticle.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeCategArticle entity = coeCategArticleService.saveOrUpdate(coeCategArticle);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeCategArticle> findPage(CoeCategArticle coeCategArticle, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeCategArticle> page = coeCategArticleService.findPage(coeCategArticle, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeCategArticleService.existById(id);
	}
	
	public Map<Long, List<CoeCategArticle>> findMapByMainIdList(List<Long> articleIdList) {
		return coeCategArticleService.findMapByMainIdList(articleIdList);
	}
	
	public List<CoeCategArticle> findByMainId(Long articleId){
		return coeCategArticleService.findByMainId(articleId);
	}
	
	public CoeCategArticle changeCategory(CoeCategArticle coeCategArticle) {
		return coeCategArticleService.changeCategory(coeCategArticle);
	}
	
	public Page<CoeCategArticle> findPageForPublish(CoeCategArticle coeCategArticle, Pageable pageable) {
		return coeCategArticleService.findPageForPublish(coeCategArticle, pageable);
	}
	
	public Page<CoeCategArticle> findPageForPublishFromDetail(CoeCategArticle coeCategArticle, Pageable pageable){
		return coeCategArticleService.findPageForPublishFromDetail( coeCategArticle,  pageable);
	}
	
	public Page<CoeCategArticle> findPageForPublishWithChildren(CoeCategArticle coeCategArticle, List<Long> baseIdList, List<Long> excludeMainIdList, Pageable pageable){
		return coeCategArticleService.findPageForPublishWithChildren(coeCategArticle, baseIdList, excludeMainIdList, pageable);
	}
	public Page<CoeCategArticle> findPageForPublish(CoeCategArticle coeCategArticle, List<Long> excludeMainIdList, Pageable pageable){
		return coeCategArticleService.findPageForPublish(coeCategArticle, excludeMainIdList, pageable);
	}
}
