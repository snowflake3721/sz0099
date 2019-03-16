package dml.sz0099.course.app.persist.dao.activity;

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

import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.profession.Profession;
import dml.sz0099.course.app.persist.repository.activity.CoeActivityRepository;
import dml.sz0099.course.app.persist.specification.activity.CoeActivitySpecification;

/**
 * CoeActivityDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CoeActivityDaoImpl extends GenericDaoImpl<CoeActivity, Long> implements CoeActivityDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityDaoImpl.class);

	@Autowired
	private CoeActivityRepository coeActivityRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeActivityRepository;
	}

	/**
	 * 根据Id查询CoeActivity实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivity findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeActivity coeActivity = coeActivityRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeActivity);
		return coeActivity;
	}

	@Override
	public CoeActivity findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivity coeActivity = coeActivityRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivity);
		return coeActivity;
	}

	/**
	 * 根据IdList查询CoeActivity实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivity> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeActivity> coeActivityList = coeActivityRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", coeActivityList);
		return coeActivityList;
	}
	
	public List<CoeActivity> findPublishByIdList(List<Long> idList, Integer publishStatus){
		List<CoeActivity> coeActivityList = coeActivityRepository.findPublishByIdList(idList,publishStatus);
		LOGGER.debug("--- dao>>>findPublishByIdList end ---------  result is {} ", coeActivityList);
		return coeActivityList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeActivity> findPage(CoeActivity coeActivity, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeActivitySpecification.getConditionWithQsl(coeActivity);
		Page<CoeActivity> page = coeActivityRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CoeActivity entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	public Page<CoeActivity> findPublished(CoeActivity coeActivity, Pageable pageable){
		
		Integer publishStatus = CoeActivity.PUBLISH_STATUS_PUBLISH.getValueInt();
		coeActivity.setPublishStatus(publishStatus);
		BooleanExpression condition = CoeActivitySpecification.getConditionForPublish(coeActivity);
		Page<CoeActivity> page = coeActivityRepository.findAll(condition, pageable);
		return page;
	}
	
	@Override
	public List<CoeActivity> findDraftList(CoeActivity coeActivity) {
		Long userId = coeActivity.getUserId();
		Integer publishStatus = CoeActivity.PUBLISH_STATUS_DRAFT.getValueInt();
		List<CoeActivity> content = coeActivityRepository.findByUserIdAndPublishStatus(userId, publishStatus);
		return content;
	}
	
	public Long countDraftList(CoeActivity coeActivity) {
		Long userId = coeActivity.getUserId();
		Integer publishStatus = CoeActivity.PUBLISH_STATUS_DRAFT.getValueInt();
		return coeActivityRepository.countByUserIdAndPublishStatus(userId, publishStatus);
	}
	
	public Long countTemplate(Long userId, Integer template) {
		return coeActivityRepository.countTemplate(userId,  template);
	}
	
	public List<CoeActivity> findByUserId(Long userId) {
		return coeActivityRepository.findByUserId( userId) ;
	}
	
	public Long countByUserId(Long userId) {
		return coeActivityRepository.countByUserId(userId);
	}
	
	public List<CoeActivity> findPublishedByName(String name){
		return coeActivityRepository.findPublishedByName(name);
	}
	
	public List<CoeActivity> findPublishedByTitle(String title){
		return coeActivityRepository.findPublishedByTitle(title);
	}
	
	public Page<CoeActivity> findByPublished(CoeActivity coeActivity, Pageable pageable){
		BooleanExpression condition = CoeActivitySpecification.getConditionByCodeWithQslExpression(coeActivity);
		Page<CoeActivity> page = coeActivityRepository.findAll( condition,  pageable);
		return page;
	}

	@Override
	public Page<CoeActivity> findPageByUserId(CoeActivity coeActivity, Pageable pageable) {
		BooleanExpression condition = CoeActivitySpecification.getConditionByUserId(coeActivity);
		Page<CoeActivity> page = coeActivityRepository.findAll( condition,  pageable);
		return page;
	}
	
	public Long countForPublishedWithoutSelf(Long userId) {
		Integer publishStatus = Profession.PUBLISH_STATUS_PUBLISH.getValueInt();
		return coeActivityRepository.countForPublishedWithoutSelf( userId, publishStatus);
	}
	public Page<CoeActivity> findByPublishedNotSelf(CoeActivity coeActivity, Pageable pageable){
		BooleanExpression condition = CoeActivitySpecification.getConditionForPublishNotSelf(coeActivity);
		Page<CoeActivity> page = coeActivityRepository.findAll( condition,  pageable);
		return page;
	}
	
	public Page<CoeActivity> findPageByMainTypeAndUserId(Integer mainType, List<Long> userIdList, Integer publishStatus, Pageable pageable) {
		return coeActivityRepository.findPageByMainTypeAndUserId( mainType, userIdList, publishStatus, pageable);
	}
	
	public List<CoeActivity> findListByMainTypeAndUserId(Integer mainType, List<Long> userIdList, Integer publishStatus){
		return coeActivityRepository.findListByMainTypeAndUserId(mainType,userIdList, publishStatus);
	}

	@Override
	public List<CoeActivity> findByUserIdAndMainType(Integer mainType, Long userId, Integer publishStatus) {
		return coeActivityRepository.findByUserIdAndMainType(mainType, userId, publishStatus);
	}
	
	public List<CoeActivity> findByUserIdAndMainType(Integer mainType, Long userId){
		return coeActivityRepository.findByUserIdAndMainType(mainType,userId);
	}
	
	public Page<CoeActivity> findPageForTemplate(Long userId,Integer template, Pageable pageable){
		return coeActivityRepository.findPageForTemplate( userId, template, pageable);
	}
	public Page<CoeActivity> findPageForTemplate(Integer template, Pageable pageable){
		return coeActivityRepository.findPageForTemplate( template, pageable);
	}
	
	public Page<CoeActivity> findPageForTemplate(Long userId,Integer template,Long id, Pageable pageable){
		return coeActivityRepository.findPageForTemplate( userId, template,id, pageable);
	}

}
