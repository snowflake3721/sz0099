package dml.sz0099.course.app.biz.service.activity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.jit4j.core.service.GenericServiceImpl;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.service.tag.CoeTagService;
import dml.sz0099.course.app.persist.dao.activity.CoeActivityOrderLogDao;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityFee;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityOrder;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityOrderLog;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTime;


/**
 * 
 * @formatter:off
 * description: CoeActivityOrderLogServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeActivityOrderLogServiceImpl extends GenericServiceImpl<CoeActivityOrderLog, Long> implements CoeActivityOrderLogService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityOrderLogServiceImpl.class);

	@Autowired
	private CoeActivityOrderLogDao coeActivityOrderLogDao;
	
	@Autowired
	private CoeTagService coeTagService;
	
	@Autowired
	private CoeActivityService coeActivityService;
	
	@Autowired
	private JoinItemService joinItemService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeActivityOrderLogDao;
	}

	/**
	 * 根据Id查询CoeActivityOrderLog实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityOrderLog findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeActivityOrderLog coeActivityOrderLog = coeActivityOrderLogDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeActivityOrderLog);
		return coeActivityOrderLog;
	}
	
	@Override
	public CoeActivityOrderLog findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityOrderLog coeActivityOrderLog = coeActivityOrderLogDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityOrderLog);
		return coeActivityOrderLog;
	}

	/**
	 * 根据IdList查询CoeActivityOrderLog实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityOrderLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeActivityOrderLog> coeActivityOrderLogList = coeActivityOrderLogDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeActivityOrderLogList);
		return coeActivityOrderLogList;
	}

	@Transactional
	@Override
	public CoeActivityOrderLog persistEntity(CoeActivityOrderLog coeActivityOrderLog) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeActivityOrderLog entity = save(coeActivityOrderLog);
		Long id = coeActivityOrderLog.getId();
		entity.setOrderSeq(entity.getAid());
		
		save(entity);
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeActivityOrderLog.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeActivityOrderLog mergeEntity(CoeActivityOrderLog coeActivityOrderLog) {
		Long id = coeActivityOrderLog.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeActivityOrderLog entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeActivityOrderLog.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeActivityOrderLog.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeActivityOrderLog saveOrUpdate(CoeActivityOrderLog coeActivityOrderLog) {
		Long id = coeActivityOrderLog.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeActivityOrderLog entity = null;
		if(null != id) {
			entity = mergeEntity(coeActivityOrderLog);
		}else {
			entity = persistEntity(coeActivityOrderLog);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeActivityOrderLog> findPage(CoeActivityOrderLog coeActivityOrderLog, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeActivityOrderLog> page = coeActivityOrderLogDao.findPage(coeActivityOrderLog, pageable);
		return page;
	}
	
	
	public CoeActivityOrderLog findByMainId(CoeActivityOrderLog coeActivityOrderLog) {
		Long activityId = coeActivityOrderLog.getMainId();
		if (null != activityId) {
			CoeActivityOrderLog tag = coeActivityOrderLogDao.findByMainId(activityId);
			return tag;
		}
		return null;
	}
	
	public Long countByMainId(Long activityId) {
		return coeActivityOrderLogDao.countByMainId(activityId);
	}
	
	public CoeActivityOrderLog findByMainId(Long activityId) {
		return coeActivityOrderLogDao.findByMainId(activityId);
	}
	
	public Map<Long, CoeActivityOrderLog> findMapByMainIdList(List<Long> mainIdList) {
		List<CoeActivityOrderLog> content = coeActivityOrderLogDao.findByMainIdList(mainIdList);
		Map<Long, CoeActivityOrderLog> map = new HashMap<>();
		if(null != content && !content.isEmpty()) {
			for(CoeActivityOrderLog c : content) {
				Long mainId = c.getMainId();
				map.put(mainId, c);
			}
		}
		return map;
	}
	
	public Map<Long, CoeActivityOrderLog> findMapByOrderIdList(List<Long> orderIdList) {
		List<CoeActivityOrderLog> content = coeActivityOrderLogDao.findByOrderIdList(orderIdList);
		Map<Long, CoeActivityOrderLog> map = new HashMap<>();
		if(null != content && !content.isEmpty()) {
			for(CoeActivityOrderLog c : content) {
				Long orderId = c.getOrderId();
				map.put(orderId, c);
			}
		}
		return map;
	}

	@Transactional
	@Override
	public CoeActivityOrderLog create(CoeActivityOrder order) {
		CoeActivityOrderLog orderLog = new CoeActivityOrderLog();
		
		CoeActivity activity = order.getActivity();
		CoeActivityTime actTime = activity.getActTime();
		CoeActivityFee actFee = activity.getActFee();
		orderLog.setActivityNo(activity.getActivityNo());
		orderLog.setActOrganize(activity.getActOrganize());
		orderLog.setActTimeId(activity.getActTimeId());
		orderLog.setBeginTime(actTime.getBeginTime());
		orderLog.setCashAmount(actFee.getCashAmount());
		orderLog.setCoinAmount(actFee.getCoinAmount());
		orderLog.setCreatedBy(activity.getCreatedBy());
		orderLog.setCurrency(actFee.getCurrency());
		orderLog.setCreatedDate(activity.getCreatedDate());
		orderLog.setDescription(activity.getDescription());
		orderLog.setEndTime(actTime.getEndTime());
		orderLog.setFeeDescription(actFee.getDescription());
		orderLog.setFeeType(actFee.getFeeType());
		orderLog.setRecieveType(actFee.getRecieveType());
		orderLog.setFeeId(activity.getFeeId());
		orderLog.setKilometer(activity.getKilometer());
		orderLog.setLink(activity.getLink());
		orderLog.setMainId(activity.getId());//活动id
		orderLog.setOffTime(actTime.getOffTime());
		orderLog.setOrderId(order.getId());
		orderLog.setOriginalLink(activity.getOriginalLink());
		orderLog.setPenname(activity.getPenname());
		orderLog.setPublishTime(activity.getPublishTime());
		orderLog.setRefreshTime(activity.getRefreshTime());
		orderLog.setRmbAmount(actFee.getRmbAmount());
		orderLog.setRmbAmountOri(actFee.getRmbAmountOri());
		orderLog.setSubTitle(activity.getSubTitle());
		orderLog.setTitle(activity.getTitle());
		orderLog.setTitleLower(activity.getTitleLower());
		orderLog.setUserId(activity.getUserId());
		save(orderLog);
		return orderLog;
	}


}
