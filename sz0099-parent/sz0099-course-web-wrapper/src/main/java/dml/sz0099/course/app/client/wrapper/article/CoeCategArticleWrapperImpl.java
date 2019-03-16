package dml.sz0099.course.app.client.wrapper.article;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.article.CoeCategArticleDelegate;
import dml.sz0099.course.app.client.wrapper.category.CategoryWrapper;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.CoeCategArticle;
import dml.sz0099.course.app.persist.entity.category.Category;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeCategArticleWrapperImpl,组件封装
 * @author bruce yang at 2018-08-29 21:12:56
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Service
public class CoeCategArticleWrapperImpl implements CoeCategArticleWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeCategArticleWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeCategArticleDelegate coeCategArticleDelegate;
	
	@Autowired
	private CoeArticleWrapper coeArticleWrapper;
	
	@Autowired
	private CategoryWrapper categoryWrapper;
	
	
	/**
	 * 根据Id查询CoeCategArticle实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeCategArticle findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		CoeCategArticle coeCategArticle = coeCategArticleDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, coeCategArticle);
		return coeCategArticle;
	}
	
	@Override
	public CoeCategArticle findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeCategArticle coeCategArticle = coeCategArticleDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeCategArticle);
		return coeCategArticle;
	}
	
	/**
	 * 根据IdList查询CoeCategArticle实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeCategArticle> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<CoeCategArticle> coeCategArticleList = coeCategArticleDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  coeCategArticleList);
		return coeCategArticleList;
	}
	
	@Override
	public CoeCategArticle persistEntity(CoeCategArticle coeCategArticle) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		CoeCategArticle entity = coeCategArticleDelegate.persistEntity(coeCategArticle);
		Long id = coeCategArticle.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeCategArticle mergeEntity(CoeCategArticle coeCategArticle) {
		Long id = coeCategArticle.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		CoeCategArticle entity = coeCategArticleDelegate.mergeEntity(coeCategArticle);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeCategArticle saveOrUpdate(CoeCategArticle coeCategArticle) {
		Long id = coeCategArticle.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		CoeCategArticle entity = coeCategArticleDelegate.saveOrUpdate(coeCategArticle);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeCategArticle> findPage(CoeCategArticle coeCategArticle, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<CoeCategArticle> page = coeCategArticleDelegate.findPage(coeCategArticle, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return coeCategArticleDelegate.existById(id);
	}
	
	public Map<Long, List<CoeCategArticle>> findMapByMainIdList(List<Long> articleIdList) {
		return coeCategArticleDelegate.findMapByMainIdList(articleIdList);
	}
	
	public List<CoeCategArticle> findByMainId(Long articleId){
		return coeCategArticleDelegate.findByMainId(articleId);
	}

	@Override
	public CoeCategArticle changeCategory(CoeCategArticle coeCategArticle) {
		return coeCategArticleDelegate.changeCategory(coeCategArticle);
	}
	
	public Page<CoeCategArticle> findPageForPublish(CoeCategArticle coeCategArticle, Pageable pageable) {
		Page<CoeCategArticle> page = coeCategArticleDelegate.findPageForPublish(coeCategArticle, pageable);
		List<CoeCategArticle> contentList = page.getContent();
		if (null != contentList && !contentList.isEmpty()) {
			List<CoeArticle> articleList = new ArrayList<>(contentList.size());
			for (CoeCategArticle cca : contentList) {
				if (null != cca) {
					CoeArticle article = cca.getArticle();
					articleList.add(article);
				}
			}
			coeArticleWrapper.fillRefForList(articleList);
		}
		return page;
	}
	
	public Page<CoeCategArticle> findPageForPublishFromDetail(CoeCategArticle coeCategArticle, Pageable pageable){
		Page<CoeCategArticle> page = coeCategArticleDelegate.findPageForPublishFromDetail(coeCategArticle, pageable);
		List<CoeCategArticle> contentList = page.getContent();
		if (null != contentList && !contentList.isEmpty()) {
			List<CoeArticle> coeArticleList = new ArrayList<>(contentList.size());
			for (CoeCategArticle cca : contentList) {
				if (null != cca) {
					CoeArticle coeArticle = cca.getArticle();
					coeArticleList.add(coeArticle);
				}
			}
			coeArticleWrapper.fillRefForList(coeArticleList);
		}
		return page;
	}
	
	
	public Page<CoeCategArticle> findPageForPublish(CoeCategArticle coeCategArticle, List<Long> excludeMainIdList,  Pageable pageable, boolean cover, boolean banner, boolean author) {
		Page<CoeCategArticle> page = coeCategArticleDelegate.findPageForPublish(coeCategArticle, excludeMainIdList, pageable);
		List<CoeCategArticle> contentList = page.getContent();
		if (null != contentList && !contentList.isEmpty()) {
			List<CoeArticle> articleList = new ArrayList<>(contentList.size());
			for (CoeCategArticle cca : contentList) {
				if (null != cca) {
					CoeArticle article = cca.getArticle();
					articleList.add(article);
				}
			}
			if(cover && banner && author ) {
				coeArticleWrapper.fillRefWithCoverAndBannerAndAuthor(articleList);
			}else {
					coeArticleWrapper.fillRefWithCoverAndBanner(articleList);
			}
		}
		return page;
	}
	
	public Page<CoeCategArticle> findPageForPublishWithChildren(CoeCategArticle coeCategArticle, List<Long> excludeMainIdList,  Pageable pageable, boolean cover, boolean banner, boolean author){
		CoeArticle coeArticle = coeCategArticle.getArticle();
		if(null == coeArticle) {
			coeArticle = new CoeArticle();
			coeCategArticle.setArticle(coeArticle);
		}
		
		Category category = coeCategArticle.getCategory();
		Long baseId = coeCategArticle.getBaseId();
		
		List<Long> baseIdList = null;
		if(null != baseId) {
			baseIdList = categoryWrapper.findListByBaseIdWithChilren(baseId);
		}else {
			if(null != category) {
				Long id = category.getId();
				if(null != id) {
					baseIdList = categoryWrapper.findListByBaseIdWithChilren(id);
				}else {
					String code = category.getCode();
					baseIdList = categoryWrapper.findListByCodeWithChilren(code);
				}
			}
		}
		
		coeArticle.setPublishStatus(CoeArticle.PUBLISH_STATUS_PUBLISH.getValueInt());
		
		if(null != baseIdList || baseId!= null) {
			if(null == baseId && !baseIdList.isEmpty()) {
				coeCategArticle.setBaseId(baseIdList.get(0));
			}
			LOGGER.debug("-----2-findPageForPublishWithChildren.baseId:{} -------",baseId);
			Page<CoeCategArticle>  categoryPage = coeCategArticleDelegate.findPageForPublishWithChildren(coeCategArticle, baseIdList, excludeMainIdList, pageable);
			if(null != categoryPage && categoryPage.getTotalElements()>0) {
				LOGGER.debug("-----2-findPageForPublishWithChildren, baseId:{},  totalElements : {} -------",baseId, categoryPage.getTotalElements());
				List<CoeCategArticle> contentList = categoryPage.getContent();
				if (null != contentList && !contentList.isEmpty()) {
					List<CoeArticle> coeArticleList = new ArrayList<>(contentList.size());
					for (CoeCategArticle cca : contentList) {
						if (null != cca) {
							CoeArticle artEntity = cca.getArticle();
							coeArticleList.add(artEntity);
						}
					}
					if(cover && banner && author ) {
							coeArticleWrapper.fillRefWithCoverAndBannerAndAuthor(coeArticleList);
					}else {
							coeArticleWrapper.fillRefWithCoverAndBanner(coeArticleList);
					}
				}
			}
			return categoryPage;
		}
		return null;
	}
}
