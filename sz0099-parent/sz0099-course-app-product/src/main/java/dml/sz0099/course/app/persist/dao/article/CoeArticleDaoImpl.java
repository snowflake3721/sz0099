package dml.sz0099.course.app.persist.dao.article;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jit4j.core.persist.dao.GenericDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.profession.Profession;
import dml.sz0099.course.app.persist.repository.article.CoeArticleRepository;
import dml.sz0099.course.app.persist.specification.article.CoeArticleSpecification;

/**
 * CoeArticleDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CoeArticleDaoImpl extends GenericDaoImpl<CoeArticle, Long> implements CoeArticleDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeArticleDaoImpl.class);

	@Autowired
	private CoeArticleRepository coeArticleRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeArticleRepository;
	}

	/**
	 * 根据Id查询CoeArticle实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeArticle findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeArticle coeArticle = coeArticleRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeArticle);
		return coeArticle;
	}

	@Override
	public CoeArticle findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeArticle coeArticle = coeArticleRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeArticle);
		return coeArticle;
	}

	/**
	 * 根据IdList查询CoeArticle实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeArticle> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeArticle> coeArticleList = coeArticleRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", coeArticleList);
		return coeArticleList;
	}
	
	public List<CoeArticle> findPublishByIdList(List<Long> idList, Integer publishStatus){
		List<CoeArticle> coeArticleList = coeArticleRepository.findPublishByIdList(idList,publishStatus);
		LOGGER.debug("--- dao>>>findPublishByIdList end ---------  result is {} ", coeArticleList);
		return coeArticleList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeArticle> findPage(CoeArticle coeArticle, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeArticleSpecification.getConditionWithQsl(coeArticle);
		Page<CoeArticle> page = coeArticleRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CoeArticle entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	public Page<CoeArticle> findPublished(CoeArticle coeArticle, Pageable pageable){
		
		Integer publishStatus = CoeArticle.PUBLISH_STATUS_PUBLISH.getValueInt();
		coeArticle.setPublishStatus(publishStatus);
		BooleanExpression condition = CoeArticleSpecification.getConditionForPublish(coeArticle);
		Page<CoeArticle> page = coeArticleRepository.findAll(condition, pageable);
		return page;
	}
	
	@Override
	public List<CoeArticle> findDraftList(CoeArticle coeArticle) {
		Long userId = coeArticle.getUserId();
		Integer publishStatus = CoeArticle.PUBLISH_STATUS_DRAFT.getValueInt();
		List<CoeArticle> content = coeArticleRepository.findByUserIdAndPublishStatus(userId, publishStatus);
		return content;
	}
	
	public Long countDraftList(CoeArticle coeArticle) {
		Long userId = coeArticle.getUserId();
		Integer publishStatus = CoeArticle.PUBLISH_STATUS_DRAFT.getValueInt();
		return coeArticleRepository.countByUserIdAndPublishStatus(userId, publishStatus);
	}
	
	public List<CoeArticle> findByUserId(Long userId) {
		return coeArticleRepository.findByUserId( userId) ;
	}
	
	public Long countByUserId(Long userId) {
		return coeArticleRepository.countByUserId(userId);
	}
	
	public List<CoeArticle> findPublishedByName(String name){
		return coeArticleRepository.findPublishedByName(name);
	}
	
	public List<CoeArticle> findPublishedByTitle(String title){
		return coeArticleRepository.findPublishedByTitle(title);
	}
	
	public Page<CoeArticle> findByPublished(CoeArticle coeArticle, Pageable pageable){
		BooleanExpression condition = CoeArticleSpecification.getConditionByCodeWithQslExpression(coeArticle);
		Page<CoeArticle> page = coeArticleRepository.findAll( condition,  pageable);
		return page;
	}

	@Override
	public Page<CoeArticle> findPageByUserId(CoeArticle coeArticle, Pageable pageable) {
		BooleanExpression condition = CoeArticleSpecification.getConditionByUserId(coeArticle);
		Page<CoeArticle> page = coeArticleRepository.findAll( condition,  pageable);
		return page;
	}
	
	public Long countForPublishedWithoutSelf(Long userId) {
		Integer publishStatus = Profession.PUBLISH_STATUS_PUBLISH.getValueInt();
		return coeArticleRepository.countForPublishedWithoutSelf( userId, publishStatus);
	}
	public Page<CoeArticle> findByPublishedNotSelf(CoeArticle coeArticle, Pageable pageable){
		BooleanExpression condition = CoeArticleSpecification.getConditionForPublishNotSelf(coeArticle);
		Page<CoeArticle> page = coeArticleRepository.findAll( condition,  pageable);
		return page;
	}
	
	public Page<CoeArticle> findPageByMainTypeAndUserId(Integer mainType, List<Long> userIdList, Integer publishStatus, Pageable pageable) {
		return coeArticleRepository.findPageByMainTypeAndUserId( mainType, userIdList, publishStatus, pageable);
	}
	
	public List<CoeArticle> findListByMainTypeAndUserId(Integer mainType, List<Long> userIdList, Integer publishStatus){
		return coeArticleRepository.findListByMainTypeAndUserId(mainType,userIdList, publishStatus);
	}

	@Override
	public List<CoeArticle> findByUserIdAndMainType(Integer mainType, Long userId, Integer publishStatus) {
		return coeArticleRepository.findByUserIdAndMainType(mainType, userId, publishStatus);
	}
	
	public List<CoeArticle> findByUserIdAndMainType(Integer mainType, Long userId){
		return coeArticleRepository.findByUserIdAndMainType(mainType,userId);
	}
	
	public Long countPublishedByUserId(CoeArticle coeArticle) {
		Integer publishStatus = coeArticle.getPublishStatus();
		Long userId = coeArticle.getUserId();
		return coeArticleRepository.countPublishedByUserId(userId, publishStatus);
	}

}
