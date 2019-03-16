package dml.sz0099.course.app.biz.delegate.activity;

import java.io.Serializable;
import java.util.List;

import org.jit4j.core.persist.page.PageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.activity.ParagActivityService;
import dml.sz0099.course.app.persist.entity.activity.ParagActivity;

/**
 * paragActivityServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class ParagActivityDelegateImpl implements ParagActivityDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParagActivityDelegateImpl.class);
	
	@Autowired
	private ParagActivityService paragActivityService;

	/**
	 * 根据Id查询ParagActivity实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ParagActivity findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		ParagActivity paragActivity = paragActivityService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, paragActivity);
		return paragActivity;
	}
	
	@Override
	public ParagActivity findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		ParagActivity paragActivity = paragActivityService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, paragActivity);
		return paragActivity;
	}
	
	/**
	 * 根据IdList查询ParagActivity实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ParagActivity> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<ParagActivity> paragActivityList = paragActivityService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  paragActivityList);
		return paragActivityList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public ParagActivity persistEntity(ParagActivity paragActivity) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		ParagActivity entity = paragActivityService.persistEntity(paragActivity);
		Long id = paragActivity.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ParagActivity mergeEntity(ParagActivity paragActivity) {
		Long id = paragActivity.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		ParagActivity entity = paragActivityService.mergeEntity(paragActivity);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ParagActivity saveOrUpdate(ParagActivity paragActivity) {
		Long id = paragActivity.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		ParagActivity entity = paragActivityService.saveOrUpdate(paragActivity);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ParagActivity> findPage(ParagActivity paragActivity, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<ParagActivity> page = paragActivityService.findPage(paragActivity, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return paragActivityService.existById(id);
	}


	public Page<ParagActivity> findByMainId(Long activityId, Pageable pageable ){		

		LOGGER.debug("-------delegate>>>ParagActivityDelegateImpl.findByMainId----------begin---------");

		Page<ParagActivity> paragActivity = paragActivityService.findByMainId( activityId,  pageable );

		LOGGER.info("-------delegate>>>ParagActivityDelegateImpl.findByMainId----------end---------");

		return paragActivity;
	}
	
	public Page<ParagActivity> findByMainIdAndUserId(Long activityId, Long userId, Pageable pageable ){		

		LOGGER.debug("-------delegate>>>ParagActivityDelegateImpl.findByMainIdAndUserId----------begin---------");
		Page<ParagActivity> paragActivity = paragActivityService.findByMainIdAndUserId( activityId, userId, pageable );
		return paragActivity;
	}

	public Page<ParagActivity> resetOrderSeq(Long activityId, Long userId ){
		return paragActivityService.resetOrderSeq(activityId, userId);
	}


	public void deleteByActivityIdAndUserId(Long activityId, Long userId ){		

		LOGGER.debug("-------delegate>>>ParagActivityDelegateImpl.deleteByActivityIdAndUserId----------begin---------");
		paragActivityService.deleteByActivityIdAndUserId( activityId,  userId );
	}
	
	public void deleteByParagIdAndUserId(Long paragId, Long userId, boolean cascade ) {
		paragActivityService.deleteByParagIdAndUserId(paragId, userId, cascade);
	}
	
	public ParagActivity createParagActivity(ParagActivity paragActivity) {
		return paragActivityService.createParagActivity(paragActivity);
	}
	
	public Long countByMainId(Long activityId) {
		return paragActivityService.countByMainId(activityId);
	}


}
