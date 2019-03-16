package dml.sz0099.course.app.biz.service.article;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.jit4j.core.service.GenericServiceImpl;
import org.jit8j.core.util.CollectionUtil;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.persist.dao.article.CoeCategArticleDao;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.CoeCategArticle;


/**
 * 
 * @formatter:off
 * description: CoeCategArticleServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeCategArticleServiceImpl extends GenericServiceImpl<CoeCategArticle, Long> implements CoeCategArticleService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeCategArticleServiceImpl.class);

	@Autowired
	private CoeCategArticleDao coeCategArticleDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeCategArticleDao;
	}

	/**
	 * 根据Id查询CoeCategArticle实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeCategArticle findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeCategArticle coeCategArticle = coeCategArticleDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeCategArticle);
		return coeCategArticle;
	}
	
	@Override
	public CoeCategArticle findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeCategArticle coeCategArticle = coeCategArticleDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeCategArticle);
		return coeCategArticle;
	}

	/**
	 * 根据IdList查询CoeCategArticle实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeCategArticle> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeCategArticle> coeCategArticleList = coeCategArticleDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeCategArticleList);
		return coeCategArticleList;
	}

	@Transactional
	@Override
	public CoeCategArticle persistEntity(CoeCategArticle coeCategArticle) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeCategArticle entity = save(coeCategArticle);
		Long id = coeCategArticle.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeCategArticle.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeCategArticle mergeEntity(CoeCategArticle coeCategArticle) {
		Long id = coeCategArticle.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeCategArticle entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeCategArticle.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeCategArticle.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeCategArticle saveOrUpdate(CoeCategArticle coeCategArticle) {
		Long id = coeCategArticle.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeCategArticle entity = null;
		if(null != id) {
			entity = mergeEntity(coeCategArticle);
		}else {
			entity = persistEntity(coeCategArticle);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeCategArticle> findPage(CoeCategArticle coeCategArticle, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeCategArticle> page = coeCategArticleDao.findPage(coeCategArticle, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeCategArticleDao.existById(id);
	}
	
	public List<CoeCategArticle> findByMainId(Long articleId){
		return coeCategArticleDao.findByMainId(articleId);
	}

	public List<CoeCategArticle> findByMainIdList(List<Long> articleIdList) {
		return coeCategArticleDao.findByMainIdList(articleIdList);
	}
	
	public Map<Long, List<CoeCategArticle>> findMapByMainIdList(List<Long> articleIdList) {
		List<CoeCategArticle> content = findByMainIdList(articleIdList);
		Map<Long, List<CoeCategArticle>> map = null;
		if(null != content && !content.isEmpty()) {
			/*for(CoeCategArticle c : content) {
				Long mainId = c.getMainId();
				List<CoeCategArticle> entityList = map.get(mainId);
				if(null == entityList) {
					entityList = new ArrayList<>();
					map.put(mainId, entityList);
				}
				entityList.add(c);
			}*/
			map = CollectionUtil.convertList2Map(content, "mainId");
		}
		return map;
	}
	
	@Transactional
	public CoeCategArticle changeCategory(CoeCategArticle coeCategArticle) {
		Long maidId = coeCategArticle.getMainId();
		Long baseId = coeCategArticle.getBaseId();
		List<CoeCategArticle> entityList = findByMainId(maidId);
		boolean exist=false;
		if(null != entityList && !entityList.isEmpty()) {
			for(CoeCategArticle entity : entityList) {
				Long entityBaseId = entity.getBaseId();
				if(baseId.equals(entityBaseId)) {
					exist=true;
					continue;
				}
				delete(entity);
				coeCategArticle.setSuccess(CoeCategArticle.SUCCESS_YES);
			}
		}
		if(!exist) {
			coeCategArticle = save(coeCategArticle);
			coeCategArticle.setSuccess(CoeCategArticle.SUCCESS_YES);
		}
		return coeCategArticle;
	}
	
	public Page<CoeCategArticle> findPageForPublish(CoeCategArticle coeCategArticle, Pageable pageable) {
		CoeArticle article = coeCategArticle.getArticle();
		if(null == article) {
			article = new CoeArticle();
			coeCategArticle.setArticle(article);
		}
		article.setPublishStatus(CoeArticle.PUBLISH_STATUS_PUBLISH.getValueInt());
		
		return coeCategArticleDao.findPageForPublish(coeCategArticle, pageable);
	}
	
	public Page<CoeCategArticle> findPageForPublishFromDetail(CoeCategArticle coeCategArticle, Pageable pageable){
		CoeArticle coeArticle = coeCategArticle.getArticle();
		if(null == coeArticle) {
			coeArticle = new CoeArticle();
			coeCategArticle.setArticle(coeArticle);
		}
		coeArticle.setPublishStatus(CoeArticle.PUBLISH_STATUS_PUBLISH.getValueInt());
		
		return coeCategArticleDao.findPageForPublishFromDetail(coeCategArticle, pageable);
	}
	
	public Page<CoeCategArticle> findPageForPublishWithChildren(CoeCategArticle coeCategArticle, List<Long> baseIdList, List<Long> excludeMainIdList, Pageable pageable){
		CoeArticle coeArticle = coeCategArticle.getArticle();
		if(null == coeArticle) {
			coeArticle = new CoeArticle();
			coeCategArticle.setArticle(coeArticle);
		}
		
		coeArticle.setPublishStatus(CoeArticle.PUBLISH_STATUS_PUBLISH.getValueInt());
		
		return coeCategArticleDao.findPageForPublishWithChildren(coeCategArticle, baseIdList, excludeMainIdList, pageable);
	}
	
	public Page<CoeCategArticle> findPageForPublish(CoeCategArticle coeCategArticle, List<Long> excludeMainIdList, Pageable pageable) {
		CoeArticle article = coeCategArticle.getArticle();
		if(null == article) {
			article = new CoeArticle();
			coeCategArticle.setArticle(article);
		}
		article.setPublishStatus(CoeArticle.PUBLISH_STATUS_PUBLISH.getValueInt());
		
		return coeCategArticleDao.findPageForPublish(coeCategArticle, excludeMainIdList, pageable);
	}

}
