package dml.sz0099.course.app.client.wrapper.activity;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.jit4j.core.persist.page.PageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.activity.ParagActivityDelegate;
import dml.sz0099.course.app.persist.entity.activity.ParagActivity;
import dml.sz0099.course.app.persist.entity.activity.Paragraph;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ParagActivityWrapperImpl,组件封装
 * @author bruce yang at 2018-08-29 21:12:56
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Service("activityParagActivityWrapperImpl")
public class ParagActivityWrapperImpl implements ParagActivityWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ParagActivityWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private ParagActivityDelegate paragActivityDelegate;
	
	@Autowired
	private ParagraphWrapper paragraphWrapper;
	
	/**
	 * 根据Id查询ParagActivity实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ParagActivity findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		ParagActivity paragActivity = paragActivityDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, paragActivity);
		return paragActivity;
	}
	
	@Override
	public ParagActivity findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		ParagActivity paragActivity = paragActivityDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, paragActivity);
		return paragActivity;
	}
	
	/**
	 * 根据IdList查询ParagActivity实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ParagActivity> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<ParagActivity> paragActivityList = paragActivityDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  paragActivityList);
		return paragActivityList;
	}
	
	@Override
	public ParagActivity persistEntity(ParagActivity paragActivity) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		ParagActivity entity = paragActivityDelegate.persistEntity(paragActivity);
		Long id = paragActivity.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ParagActivity mergeEntity(ParagActivity paragActivity) {
		Long id = paragActivity.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		ParagActivity entity = paragActivityDelegate.mergeEntity(paragActivity);
		Paragraph paragraph = paragActivity.getParagraph();
		paragraph.setLastModifiedBy(paragActivity.getLastModifiedBy());
		paragraph.setUserId(paragActivity.getUserId());
		paragraph.setOrderSeq(paragActivity.getOrderSeq());
		paragraph=paragraphWrapper.mergeEntity(paragraph);
		paragActivity.setParagraph(paragraph);
		return entity;
	}

	@Override
	public ParagActivity saveOrUpdate(ParagActivity paragActivity) {
		Long id = paragActivity.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		ParagActivity entity = paragActivityDelegate.saveOrUpdate(paragActivity);
		return entity;
	}

	@Override
	public Page<ParagActivity> findPage(ParagActivity paragActivity, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<ParagActivity> page = paragActivityDelegate.findPage(paragActivity, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return paragActivityDelegate.existById(id);
	}


	public Page<ParagActivity> findByMainId(Long activityId, Pageable pageable ){		

		LOGGER.debug("-------wrapper>>>ParagActivityWrapperImpl.findByMainId----------begin---------");

		Page<ParagActivity> page = paragActivityDelegate.findByMainId( activityId,  pageable );


		return page;
	}
	
	public Page<ParagActivity> findByMainIdAndUserId(Long activityId, Long userId, Pageable pageable ){
		return paragActivityDelegate.findByMainIdAndUserId(activityId, userId, pageable);
	}

	public Page<ParagActivity> resetOrderSeq(Long activityId, Long userId ){
		return paragActivityDelegate.resetOrderSeq(activityId, userId);
	}


	public void deleteByActivityIdAndUserId(Long activityId, Long userId ){		

		LOGGER.debug("-------wrapper>>>ParagActivityWrapperImpl.deleteByActivityIdAndUserId----------begin---------");

		paragActivityDelegate.deleteByActivityIdAndUserId( activityId,  userId );

	}
	
	public void deleteByParagIdAndUserId(Long paragId, Long userId, boolean cascade ) {
		paragActivityDelegate.deleteByParagIdAndUserId(paragId, userId, cascade);
	}
	
	public ParagActivity createParagActivity(ParagActivity paragActivity) {
		return paragActivityDelegate.createParagActivity(paragActivity);
	}

	@Override
	public Long countByMainId(Long activityId) {
		return paragActivityDelegate.countByMainId(activityId);
	}


}
