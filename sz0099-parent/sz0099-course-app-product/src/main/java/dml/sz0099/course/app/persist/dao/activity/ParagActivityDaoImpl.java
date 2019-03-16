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

import dml.sz0099.course.app.persist.entity.activity.ParagActivity;
import dml.sz0099.course.app.persist.repository.activity.ParagActivityRepository;
import dml.sz0099.course.app.persist.specification.activity.ParagActivitySpecification;

/**
 * ParagActivityDaoImpl 数据访问封装
 *  ----------------------------------------------------------------------------------------
 * Requirement 		Author 		Date 		Function 
 * init 			bruceyang 	2017-08-16 	basic init
 * 
 * 
 */
@Repository("activityParagActivityDaoImpl")
public class ParagActivityDaoImpl extends GenericDaoImpl<ParagActivity, Long> implements ParagActivityDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParagActivityDaoImpl.class);

	@Autowired
	private ParagActivityRepository paragActivityRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = paragActivityRepository;
	}

	/**
	 * 根据Id查询ParagActivity实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ParagActivity findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		ParagActivity paragActivity = paragActivityRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, paragActivity);
		return paragActivity;
	}

	@Override
	public ParagActivity findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		ParagActivity paragActivity = paragActivityRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, paragActivity);
		return paragActivity;
	}

	/**
	 * 根据IdList查询ParagActivity实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ParagActivity> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<ParagActivity> paragActivityList = paragActivityRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", paragActivityList);
		return paragActivityList;
	}

	/**
	 * 条件查询
	 */
	public Page<ParagActivity> findPage(ParagActivity paragActivity, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = ParagActivitySpecification.getConditionWithQsl(paragActivity);
		Page<ParagActivity> page = paragActivityRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		ParagActivity entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}



	public Page<ParagActivity> findByMainId(Long activityId, Pageable pageable ){		

		LOGGER.debug("-------dao>>>ParagActivityDaoImpl.findByMainId----------begin---------");
		Page<ParagActivity> page = paragActivityRepository.findByMainId( activityId,  pageable );
		return page;
	}
	
	public Page<ParagActivity> findByMainIdAndUserId(Long activityId, Long userId, Pageable pageable ){
		return paragActivityRepository.findByMainIdAndUserId(activityId, userId, pageable);
	}




	public void deleteByActivityIdAndUserId(Long activityId, Long userId ){		

		LOGGER.debug("-------dao>>>ParagActivityDaoImpl.deleteByActivityIdAndUserId----------begin---------");
		paragActivityRepository.deleteByActivityIdAndUserId( activityId,  userId );
	}
	
	public void deleteByParagIdAndUserId(Long paragId, Long userId, boolean cascade ) {
		paragActivityRepository.deleteByParagIdAndUserId(paragId,userId);
	}

	@Override
	public List<ParagActivity> findListByMainId(Long activityId) {
		List<ParagActivity> content = paragActivityRepository.findListByMainId(activityId);
		return content;
	}
	
	public List<ParagActivity> findListByMainIdAndUserId(Long activityId,Long userId){
		List<ParagActivity> content = paragActivityRepository.findListByMainIdAndUserId(activityId, userId);
		return content;
	}
	
	public Long countByMainId(Long activityId) {
		return paragActivityRepository.countByMainId(activityId);
	}


}
