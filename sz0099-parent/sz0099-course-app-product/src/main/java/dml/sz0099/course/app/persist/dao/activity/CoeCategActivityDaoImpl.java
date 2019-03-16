package dml.sz0099.course.app.persist.dao.activity;

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

import dml.sz0099.course.app.persist.entity.activity.CoeCategActivity;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.repository.activity.CoeCategActivityRepository;
import dml.sz0099.course.app.persist.specification.activity.CoeCategActivitySpecification;

/**
 * CoeCategActivityDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CoeCategActivityDaoImpl extends GenericDaoImpl<CoeCategActivity, Long> implements CoeCategActivityDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeCategActivityDaoImpl.class);

	@Autowired
	private CoeCategActivityRepository coeCategActivityRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeCategActivityRepository;
	}

	/**
	 * 根据Id查询CoeCategActivity实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeCategActivity findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeCategActivity coeCategActivity = coeCategActivityRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeCategActivity);
		return coeCategActivity;
	}

	@Override
	public CoeCategActivity findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeCategActivity coeCategActivity = coeCategActivityRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeCategActivity);
		return coeCategActivity;
	}

	/**
	 * 根据IdList查询CoeCategActivity实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeCategActivity> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeCategActivity> coeCategActivityList = coeCategActivityRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", coeCategActivityList);
		return coeCategActivityList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeCategActivity> findPage(CoeCategActivity coeCategActivity, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeCategActivitySpecification.getConditionWithQsl(coeCategActivity,null);
		Page<CoeCategActivity> page = coeCategActivityRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CoeCategActivity entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}

	@Override
	public List<CoeCategActivity> findByMainIdList(List<Long> productIdList) {
		return coeCategActivityRepository.findByMainIdList(productIdList);
	}
	
	public List<CoeCategActivity> findByMainId(Long productId){
		return coeCategActivityRepository.findByMainId(productId);
	}
	
	public Page<CoeCategActivity> findPageForPublish(CoeCategActivity coeCategActivity, Pageable pageable) {
		BooleanExpression condition = CoeCategActivitySpecification.getConditionWithQsl(coeCategActivity,null);
		return coeCategActivityRepository.findAll(condition, pageable);
	}
	
	public Page<CoeCategActivity> findPageForPublish(CoeCategActivity coeCategActivity, List<Long> excludeMainIdList, Pageable pageable) {
		BooleanExpression condition = CoeCategActivitySpecification.getConditionWithQsl(coeCategActivity, excludeMainIdList);
		return coeCategActivityRepository.findAll(condition, pageable);
	}
	
	public Page<CoeCategActivity> findPageForPublishWithChildren(CoeCategActivity coeCategActivity,List<Long> baseIdList, List<Long> excludeMainIdList, Pageable pageable) {
		String ccaCondition = GsonBuilderUtils.toJsonByOmitnull(coeCategActivity);
		LOGGER.debug("--- findPageForPublishWithChildren, ccaCondition:{}, baseIdList : {}, excludeMainIdList:{} ---------  ",ccaCondition, GsonBuilderUtils.toJson(baseIdList),GsonBuilderUtils.toJson(excludeMainIdList));
		BooleanExpression condition = CoeCategActivitySpecification.getConditionWithChilren(coeCategActivity, baseIdList, excludeMainIdList);
		return coeCategActivityRepository.findAll(condition, pageable);
	}
	
	public Page<CoeCategActivity> findPageForPublishFromDetail(CoeCategActivity coeCategActivity,  Pageable pageable) {
		BooleanExpression condition = CoeCategActivitySpecification.getConditionFromDetail(coeCategActivity);
		return coeCategActivityRepository.findAll(condition, pageable);
	}
}
