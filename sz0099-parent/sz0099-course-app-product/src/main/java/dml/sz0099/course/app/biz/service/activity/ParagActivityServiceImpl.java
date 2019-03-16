package dml.sz0099.course.app.biz.service.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.jit4j.core.persist.page.PageRequest;
import org.jit4j.core.service.GenericServiceImpl;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.persist.dao.activity.ParagActivityDao;
import dml.sz0099.course.app.persist.entity.activity.ParagActivity;
import dml.sz0099.course.app.persist.entity.activity.Paragraph;


/**
 * 
 * @formatter:off
 * description: ParagActivityServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service("activityParagActivityServiceImpl")
public class ParagActivityServiceImpl extends GenericServiceImpl<ParagActivity, Long> implements ParagActivityService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParagActivityServiceImpl.class);

	@Autowired
	private ParagActivityDao paragActivityDao;
	
	@Autowired
	private ParagraphService paragraphService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = paragActivityDao;
	}

	/**
	 * 根据Id查询ParagActivity实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ParagActivity findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		ParagActivity paragActivity = paragActivityDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, paragActivity);
		return paragActivity;
	}
	
	@Override
	public ParagActivity findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		ParagActivity paragActivity = paragActivityDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, paragActivity);
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
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<ParagActivity> paragActivityList = paragActivityDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", paragActivityList);
		return paragActivityList;
	}

	@Transactional
	@Override
	public ParagActivity persistEntity(ParagActivity paragActivity) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		ParagActivity entity = save(paragActivity);
		Long id = paragActivity.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(ParagActivity.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public ParagActivity mergeEntity(ParagActivity paragActivity) {
		Long id = paragActivity.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		ParagActivity entity = findById(id);
		if(entity != null) {
			entity.setOrderSeq(paragActivity.getOrderSeq());
			entity.setLastModifiedBy(paragActivity.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(ParagActivity.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public ParagActivity saveOrUpdate(ParagActivity paragActivity) {
		Long id = paragActivity.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		ParagActivity entity = null;
		if(null != id) {
			entity = mergeEntity(paragActivity);
		}else {
			entity = persistEntity(paragActivity);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ParagActivity> findPage(ParagActivity paragActivity, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<ParagActivity> page = paragActivityDao.findPage(paragActivity, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return paragActivityDao.existById(id);
	}



	public Page<ParagActivity> findByMainId(Long activityId, Pageable pageable ){		

		LOGGER.debug("-------service>>>findByMainId----------begin---------");
		Page<ParagActivity> page = paragActivityDao.findByMainId( activityId,  pageable );
		fillParagraphForContent(page);
		return page;
	}
	
	public Page<ParagActivity> findByMainIdAndUserId(Long activityId, Long userId, Pageable pageable ){		

		LOGGER.debug("-------service>>>findByMainIdAndUserId----------begin---------");
		Page<ParagActivity> paragActivity = findByMainIdAndUserId(activityId, userId, pageable, true);
		
		return paragActivity;
	}
	
	public Page<ParagActivity> findByMainIdAndUserId(Long activityId, Long userId, Pageable pageable, boolean withParagraph){
		LOGGER.debug("-------service>>>findByMainIdAndUserId----------begin,withParagraph: {} ---------", withParagraph);
		Page<ParagActivity> page = paragActivityDao.findByMainIdAndUserId( activityId, userId, pageable );
		if(withParagraph) {
			fillParagraphForContent(page);
		}
		return page;
	}

	/**
	 * @param withParagraph
	 * @param page
	 */
	private void fillParagraphForContent( Page<ParagActivity> page) {
		if( null != page) {
			List<ParagActivity> content = page.getContent();
			fillParagraphForContent(content);
		}
	}

	/**
	 * @param content
	 */
	private void fillParagraphForContent(List<ParagActivity> content) {
		if(null != content && !content.isEmpty()) {
			List<Long> paragIdList = new ArrayList<>();
			Map<Long , ParagActivity> map = new HashMap<>(paragIdList.size());
			for(ParagActivity p : content) {
				Long paragId = p.getParagId();
				paragIdList.add(paragId);
				map.put(paragId, p);
			}
			List<Paragraph> paragList = paragraphService.findByIdList(paragIdList);
			for(Paragraph p : paragList) {
				Long paragId = p.getId();
				ParagActivity product = map.get(paragId);
				product.setParagraph(p);
			}
			
		}
	}
	
	
	public List<ParagActivity> findListByMainId(Long activityId){
		List<ParagActivity> content = paragActivityDao.findListByMainId(activityId);
		fillParagraphForContent(content);
		return content;
	}
	
	public List<ParagActivity> findListByMainIdAndUserId(Long activityId,Long userId){
		List<ParagActivity> content = paragActivityDao.findListByMainIdAndUserId(activityId, userId);
		fillParagraphForContent(content);
		return content;
	}
	
	public Page<ParagActivity> resetOrderSeq(Long activityId, Long userId) {
		PageRequest pageable = new PageRequest(0, 100, Direction.ASC, "orderSeq");
		Page<ParagActivity> page = paragActivityDao.findByMainIdAndUserId(activityId, userId, pageable);
		List<ParagActivity> content = page.getContent();
		if (null != content && !content.isEmpty()) {
			List<Long> paragIdList = new ArrayList<>();
			Map<Long, ParagActivity> map = new HashMap<>(paragIdList.size());
			for (ParagActivity p : content) {
				Long paragId = p.getParagId();
				paragIdList.add(paragId);
				map.put(paragId, p);
			}
			List<Paragraph> paragList = paragraphService.resetOrderSeqByIdList(paragIdList, true, userId);
			for (Paragraph p : paragList) {
				Long paragId = p.getId();
				ParagActivity product = map.get(paragId);
				product.setParagraph(p);
			}
		}
		return page;
	}


	/**
	 * 添加段落
	 * @param paragActivity
	 * @return
	 */
	@Transactional
	public ParagActivity createParagActivity(ParagActivity paragActivity) {
		Long activityId = paragActivity.getMainId();
		Long createdBy = paragActivity.getCreatedBy();
		Long userId = paragActivity.getUserId();

		if (null != activityId && null != userId) {
			
			//findByp
			// 1.创建段落
			Paragraph paragraph = new Paragraph();
			paragraph.setCreatedBy(createdBy);
			paragraph.setUserId(userId);
			Paragraph entityParag = paragraphService.createParagraph(paragraph);

			// 2.关联产品与段落
			Long paragId = entityParag.getId();
			Long aid = entityParag.getAid();
			paragActivity.setOrderSeq(aid);
			paragActivity.setParagId(paragId);
			paragActivity = persistEntity(paragActivity);
			
			paragActivity.setSuccess(ParagActivity.SUCCESS_YES);
		}

		return paragActivity;
	}
	

	/**
	 * 删除产品与段落关联（仅删除关联，不清除段落）
	 */
	@Transactional
	public void deleteByActivityIdAndUserId(Long activityId, Long userId ){		
		LOGGER.debug("-------service>>>ParagActivityServiceImpl.deleteByActivityIdAndUserId----------begin---------");
		deleteByActivityIdAndUserId( activityId,  userId, true);
	}
	
	/**
	 * 删除某用户下的某个产品的全部段落
	 * @param activityId
	 * @param userId
	 * @param cascade
	 */
	@Transactional
	public void deleteByActivityIdAndUserId(Long activityId, Long userId, boolean cascade ) {
		
		if(cascade) {
			List<ParagActivity>  content = findListByMainIdAndUserId(activityId,userId);
			if(null != content && !content.isEmpty()) {
				
				List<Long> paragIdList = new ArrayList<>(content.size());
				for(ParagActivity c : content) {
					paragIdList.add(c.getParagId());
				}
				
				//1.删除段落(同时级联删除段落中的图片)
				paragraphService.deleteByIdListAndUserId(paragIdList, userId, cascade);
				
			}
		}
		//2.解除段落与产品关联
		paragActivityDao.deleteByActivityIdAndUserId(activityId, userId);
	}
	
	@Transactional
	public void deleteByParagIdAndUserId(Long paragId, Long userId, boolean cascade ) {
		
		if(cascade) {
			List<Long> paragIdList = new ArrayList<>(1);
			paragIdList.add(paragId);
			paragraphService.deleteByIdListAndUserId(paragIdList, userId, cascade);
		}
		paragActivityDao.deleteByParagIdAndUserId(paragId, userId,cascade);
	}
	
	public Long countByMainId(Long activityId) {
		return paragActivityDao.countByMainId(activityId);
	}

	


}
