package dml.sz0099.course.app.persist.dao.article;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jit4j.core.persist.dao.GenericDaoImpl;
import org.jit8j.core.util.GsonBuilderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.article.CoeCategArticle;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.repository.article.CoeCategArticleRepository;
import dml.sz0099.course.app.persist.specification.article.CoeCategArticleSpecification;

/**
 * CoeCategArticleDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CoeCategArticleDaoImpl extends GenericDaoImpl<CoeCategArticle, Long> implements CoeCategArticleDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeCategArticleDaoImpl.class);

	@Autowired
	private CoeCategArticleRepository coeCategArticleRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeCategArticleRepository;
	}

	/**
	 * 根据Id查询CoeCategArticle实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeCategArticle findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeCategArticle coeCategArticle = coeCategArticleRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeCategArticle);
		return coeCategArticle;
	}

	@Override
	public CoeCategArticle findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeCategArticle coeCategArticle = coeCategArticleRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeCategArticle);
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
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeCategArticle> coeCategArticleList = coeCategArticleRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", coeCategArticleList);
		return coeCategArticleList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeCategArticle> findPage(CoeCategArticle coeCategArticle, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeCategArticleSpecification.getConditionWithQsl(coeCategArticle,null);
		Page<CoeCategArticle> page = coeCategArticleRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CoeCategArticle entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}

	@Override
	public List<CoeCategArticle> findByMainIdList(List<Long> productIdList) {
		return coeCategArticleRepository.findByMainIdList(productIdList);
	}
	
	public List<CoeCategArticle> findByMainId(Long productId){
		return coeCategArticleRepository.findByMainId(productId);
	}
	
	public Page<CoeCategArticle> findPageForPublish(CoeCategArticle coeCategArticle, Pageable pageable) {
		BooleanExpression condition = CoeCategArticleSpecification.getConditionWithQsl(coeCategArticle,null);
		return coeCategArticleRepository.findAll(condition, pageable);
	}
	
	public Page<CoeCategArticle> findPageForPublish(CoeCategArticle coeCategArticle, List<Long> excludeMainIdList, Pageable pageable) {
		BooleanExpression condition = CoeCategArticleSpecification.getConditionWithQsl(coeCategArticle, excludeMainIdList);
		return coeCategArticleRepository.findAll(condition, pageable);
	}
	
	public Page<CoeCategArticle> findPageForPublishWithChildren(CoeCategArticle coeCategArticle,List<Long> baseIdList, List<Long> excludeMainIdList, Pageable pageable) {
		String ccaCondition = GsonBuilderUtils.toJsonByOmitnull(coeCategArticle);
		LOGGER.debug("--- findPageForPublishWithChildren, ccaCondition:{}, baseIdList : {}, excludeMainIdList:{} ---------  ",ccaCondition, GsonBuilderUtils.toJson(baseIdList),GsonBuilderUtils.toJson(excludeMainIdList));
		BooleanExpression condition = CoeCategArticleSpecification.getConditionWithChilren(coeCategArticle, baseIdList, excludeMainIdList);
		return coeCategArticleRepository.findAll(condition, pageable);
	}
	
	public Page<CoeCategArticle> findPageForPublishFromDetail(CoeCategArticle coeCategArticle,  Pageable pageable) {
		BooleanExpression condition = CoeCategArticleSpecification.getConditionFromDetail(coeCategArticle);
		return coeCategArticleRepository.findAll(condition, pageable);
	}
}
