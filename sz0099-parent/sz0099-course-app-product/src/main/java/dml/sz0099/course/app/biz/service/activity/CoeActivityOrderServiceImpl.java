package dml.sz0099.course.app.biz.service.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.jit4j.app.persist.entity.order.Order;
import org.jit4j.app.persist.entity.pay.PayConfig;
import org.jit4j.app.persist.entity.pay.wechat.PayNotifyWechat;
import org.jit4j.core.service.GenericServiceImpl;
import org.jit8j.core.util.generator.DistributeIdGenerator;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.persist.dao.activity.CoeActivityOrderDao;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityOrder;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityOrderLog;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityUser;


/**
 * 
 * @formatter:off
 * description: CoeActivityOrderServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeActivityOrderServiceImpl extends GenericServiceImpl<CoeActivityOrder, Long> implements CoeActivityOrderService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityOrderServiceImpl.class);

	@Autowired
	private CoeActivityOrderDao coeActivityOrderDao;
	
	@Autowired
	private CoeActivityService coeActivityService;
	
	@Autowired
	private CoeActivityUserService coeActivityUserService;
	
	@Autowired
	private CoeActivityOrderLogService coeActivityOrderLogService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeActivityOrderDao;
	}

	/**
	 * 根据Id查询CoeActivityOrder实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityOrder findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeActivityOrder coeActivityOrder = coeActivityOrderDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeActivityOrder);
		return coeActivityOrder;
	}
	
	@Override
	public CoeActivityOrder findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityOrder coeActivityOrder = coeActivityOrderDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityOrder);
		return coeActivityOrder;
	}

	/**
	 * 根据IdList查询CoeActivityOrder实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityOrder> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeActivityOrder> coeActivityOrderList = coeActivityOrderDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeActivityOrderList);
		return coeActivityOrderList;
	}

	@Transactional
	@Override
	public CoeActivityOrder persistEntity(CoeActivityOrder coeActivityOrder) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		String outTradeNo=String.valueOf(DistributeIdGenerator.getFlowIdWorkerInstance().nextId());
		coeActivityOrder.setOutTradeNo(outTradeNo);
		CoeActivityOrder entity = save(coeActivityOrder);
		Long id = coeActivityOrder.getId();
		entity.setOrderSeq(entity.getAid());
		
		//初始一个集合时间、集合地点
		coeActivityUserService.addUser(entity);
		
		save(entity);
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeActivityOrder.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeActivityOrder mergeEntity(CoeActivityOrder coeActivityOrder) {
		Long id = coeActivityOrder.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeActivityOrder entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeActivityOrder.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeActivityOrder.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeActivityOrder saveOrUpdate(CoeActivityOrder coeActivityOrder) {
		Long id = coeActivityOrder.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeActivityOrder entity = null;
		if(null != id) {
			entity = mergeEntity(coeActivityOrder);
		}else {
			entity = persistEntity(coeActivityOrder);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeActivityOrder> findPage(CoeActivityOrder coeActivityOrder, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeActivityOrder> page = coeActivityOrderDao.findPage(coeActivityOrder, pageable);
		return page;
	}
	
	@Transactional
	public CoeActivityOrder createOrder(CoeActivityOrder coeActivityOrder) {
		
		coeActivityOrder=persistEntity(coeActivityOrder);
		//coeActivityUserService.addUser(coeActivityOrder);
		return coeActivityOrder;
	}
	
	/**
	 * 创建订单，初始化
	 */
	@Transactional
	public CoeActivityOrder createOrder(CoeActivity coeActivity) {
		
		Long id = coeActivity.getId();
		Long userId = coeActivity.getUserId();
		CoeActivityOrder coeActivityOrder = new CoeActivityOrder();
		coeActivityOrder.setMainId(id);
		coeActivityOrder.setCreatedBy(userId);
		coeActivityOrder.setUserId(userId);
		coeActivityOrder.setLastModifiedBy(userId);
		coeActivityOrder.setOrderStatus(CoeActivityOrder.STATUS_INIT.getValueInt());
		coeActivityOrder=createOrder(coeActivityOrder);
		
		return coeActivityOrder;
	}
	
	/**
	 * 确认下单
	 * @param order
	 * @return
	 */
	@Transactional
	public CoeActivityOrder confirmOrder(CoeActivityOrder order) {
		
		Long id = order.getId();
		
		CoeActivityOrder entity = findById(id);
		
		if(null != entity) {
			entity.setOrderTime(order.getOrderTime());
			entity.setRmb(order.getRmb());
			entity.setTotalRmb(order.getTotalRmb());
			entity.setNum(order.getNum());
			entity.setClientIp(order.getClientIp());
			entity.setTitle(order.getTitle());
			entity.setOrderStatus(Order.ORDER_STATUS_GENERATED.getValueInt());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			entity.setLastModifiedBy(order.getLastModifiedBy());
			save(entity);
			
			coeActivityUserService.confirmOrder(order);
			entity.setSuccess(CoeActivityOrder.SUCCESS_YES);
			
			//保存快照
			CoeActivityOrderLog orderLog = coeActivityOrderLogService.create(order);
			entity.setOrderLogId(orderLog.getId());
			order.setOrderLogId(orderLog.getId());
			save(entity);
		}
		
		return order;
	}
	
	/**
	 * 查询该用户的初始化订单
	 * @param userId
	 * @param activityId
	 * @return
	 */
	@Transactional
	public CoeActivityOrder findInitOrder(Long userId, Long mainId) {
		CoeActivityOrder entity = findByUserIdAndMainId( userId,  mainId);
		if(null != entity) {
			List<CoeActivityUser> userList = entity.getUserList();
			if(null == userList || userList.isEmpty()) {
				Long baseId = entity.getId();
				userList = coeActivityUserService.findByBaseId(baseId);
				if(null != userList) {
					entity.setUserList(userList);
				}else {
					coeActivityUserService.addUser(entity);
				}
			}
		}
		return entity;
	}
	
	public CoeActivityOrder findInitOrder(CoeActivityOrder coeActivityOrder) {
		Long userId=coeActivityOrder.getUserId();
		Long mainId=coeActivityOrder.getMainId();
		CoeActivityOrder entity = coeActivityOrderDao.findByUserIdAndMainId(userId,mainId);
		CoeActivity activity = coeActivityOrder.getActivity();
		if(null == entity) {
			CoeActivity coeActivity = new CoeActivity();
			coeActivity.setUserId(userId);
			coeActivity.setId(mainId);
			if(null != activity) {
				coeActivity.setTitle(activity.getTitle());
			}
			coeActivityOrder.setOrderStatus(Order.ORDER_STATUS_INIT.getValueInt());
			entity=createOrder(coeActivityOrder);
		}
		List<CoeActivityUser> userList = entity.getUserList();
		if(null == userList || userList.isEmpty()) {
			Long baseId = entity.getId();
			userList = coeActivityUserService.findByBaseId(baseId);
			if(null != userList) {
				entity.setUserList(userList);
			}else {
				coeActivityUserService.addUser(entity);
			}
		}
		
		return entity;
	}
	
	public CoeActivityOrder findByUserIdAndMainId(Long userId, Long mainId) {
		CoeActivityOrder order = coeActivityOrderDao.findByUserIdAndMainId(userId,mainId);
		if(null == order) {
			CoeActivity coeActivity = new CoeActivity();
			coeActivity.setUserId(userId);
			coeActivity.setId(mainId);
			order=createOrder(coeActivity);
		}
		return order;
	}
	
	@Transactional
	public CoeActivityOrder deleteOrder(CoeActivityOrder coeActivityOrder) {
		Long id = coeActivityOrder.getId();
		Long activityId = coeActivityOrder.getMainId();
		CoeActivityOrder entity = findById(id);
		if (null != entity) {
			Long entityMainId = entity.getMainId();
			if (entityMainId.equals(activityId)) {
				delete(entity);
				coeActivityOrder.setSuccess(CoeActivityOrder.SUCCESS_YES);
			}
		}

		return coeActivityOrder;
	}
	
	@Transactional
	public CoeActivityOrder cancelOrder(CoeActivityOrder order) {
		Long id = order.getId();
		Long mainId = order.getMainId();
		CoeActivityOrder entity = findById(id);
		if (null != entity) {
			Long entityMainId = entity.getMainId();
			if (entityMainId.equals(mainId)) {
				Integer status = entity.getOrderStatus();
				if(CoeActivityOrder.STATUS_PAY_WAIT.getValueInt().equals(status)) {
					entity.setOrderStatus(CoeActivityOrder.STATUS_CANCEL.getValueInt());
					entity.setLastModifiedBy(order.getLastModifiedBy());
					DateTime cancelTime = new DateTime();
					entity.setLastModifiedDate(cancelTime);
					entity.setCancelTime(cancelTime.toDate());
					entity.setRemark(order.getRemark());
					save(entity);
					coeActivityUserService.cancelOrder(order);
					entity.setSuccess(CoeActivityOrder.SUCCESS_YES);
					
				}
			}
		}
		
		return entity;
	}
	
	public CoeActivityOrder findByMainId(CoeActivityOrder coeActivityOrder) {
		Long activityId = coeActivityOrder.getMainId();
		if (null != activityId) {
			CoeActivityOrder tag = coeActivityOrderDao.findByMainId(activityId);
			return tag;
		}
		return null;
	}
	
	public Long countByMainId(Long activityId) {
		return coeActivityOrderDao.countByMainId(activityId);
	}
	
	public CoeActivityOrder findByMainId(Long activityId) {
		return coeActivityOrderDao.findByMainId(activityId);
	}
	
	public Map<Long, CoeActivityOrder> findMapByMainIdList(List<Long> mainIdList) {
		List<CoeActivityOrder> content = coeActivityOrderDao.findByMainIdList(mainIdList);
		Map<Long, CoeActivityOrder> map = new HashMap<>();
		if(null != content && !content.isEmpty()) {
			for(CoeActivityOrder c : content) {
				Long mainId = c.getMainId();
				map.put(mainId, c);
			}
		}
		return map;
	}
	
	public Page<CoeActivityOrder> findPageWithNotself(CoeActivityOrder coeActivityOrder, Pageable pageable){
		Page<CoeActivityOrder> page = coeActivityOrderDao.findPageWithNotself(coeActivityOrder, pageable);
		if(null != page && page.getTotalElements()>0) {
			List<CoeActivityOrder> content = page.getContent();
			List<Long> mainIdList = new ArrayList<>(content.size());
			Map<Long, CoeActivityOrder> tagMap = new HashMap<>(content.size());
			Map<Long, CoeActivityOrder> tagRepeatMap = new HashMap<>(content.size());
			for(CoeActivityOrder c : content) {
				Long mainId = c.getMainId();
				if(!mainIdList.contains(mainId)) {
					mainIdList.add(mainId);
					tagMap.put(mainId, c);
				}else {
					tagRepeatMap.put(mainId, c);
				}
			}
			List<CoeActivity> mainList = coeActivityService.findPublishByIdList(mainIdList);
			if(null != mainList && !mainList.isEmpty()) {
				for(CoeActivity m : mainList) {
					Long mid = m.getId();
					CoeActivityOrder mc = tagMap.get(mid);
					if(null != mc) {
						mc.setActivity(m);
					}
					CoeActivityOrder mcr = tagRepeatMap.get(mid);
					if(null != mcr) {
						mcr.setActivity(m);
					}
				}
			}
		}
		return page;
	}
	
	/**
	 * 废弃
	 */
	@Transactional
	@Override
	public CoeActivityOrder mergePayTime(CoeActivityOrder coeActivityOrder) {
		Long id = coeActivityOrder.getId();
		CoeActivityOrder entity = findById(id);
		if(null != entity) {
			entity.setOrderStatus(coeActivityOrder.getOrderStatus());
			entity.setPayTime(coeActivityOrder.getPayTime());
			entity.setLastModifiedBy(coeActivityOrder.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			entity.setSuccess(CoeActivityOrder.SUCCESS_YES);
		}
		return entity;
	}

	@Transactional
	@Override
	public CoeActivityOrder mergeExeTime(CoeActivityOrder coeActivityOrder) {
		Long id = coeActivityOrder.getId();
		CoeActivityOrder entity = findById(id);
		if(null != entity) {
			entity.setOrderStatus(coeActivityOrder.getOrderStatus());
			entity.setExeTime(coeActivityOrder.getExeTime());
			entity.setLastModifiedBy(coeActivityOrder.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			entity.setSuccess(CoeActivityOrder.SUCCESS_YES);
		}
		return entity;
	}

	@Transactional
	@Override
	public CoeActivityOrder mergeFinishTime(CoeActivityOrder coeActivityOrder) {
		Long id = coeActivityOrder.getId();
		CoeActivityOrder entity = findById(id);
		if(null != entity) {
			entity.setOrderStatus(coeActivityOrder.getOrderStatus());
			entity.setFinishTime(coeActivityOrder.getFinishTime());
			entity.setLastModifiedBy(coeActivityOrder.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			entity.setSuccess(CoeActivityOrder.SUCCESS_YES);
		}
		return entity;
	}
	
	@Transactional
	@Override
	public CoeActivityOrder mergePayBegin(CoeActivityOrder coeActivityOrder) {
		Long id = coeActivityOrder.getId();
		CoeActivityOrder entity = findById(id);
		if(null != entity) {
			entity.setOrderStatus(coeActivityOrder.getOrderStatus());
			entity.setPayBegin(coeActivityOrder.getPayBegin());
			entity.setOutTradeNo(coeActivityOrder.getOutTradeNo());
			entity.setLastModifiedBy(coeActivityOrder.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			entity.setSuccess(CoeActivityOrder.SUCCESS_YES);
			save(entity);
		}
		return entity;
	}
	
	@Transactional
	@Override
	public CoeActivityOrder mergeCheckout(CoeActivityOrder coeActivityOrder) {
		Long id = coeActivityOrder.getId();
		CoeActivityOrder entity = findById(id);
		if(null != entity) {
			Integer orderStatus = entity.getOrderStatus();
			if(
					Order.ORDER_STATUS_GENERATED.getValueInt().equals(orderStatus)
					|| Order.ORDER_STATUS_PAY_BEGIN.getValueInt().equals(orderStatus)
					) {
				entity.setOrderStatus(coeActivityOrder.getOrderStatus());
				entity.setPayTime(coeActivityOrder.getPayTime());
				entity.setLastModifiedBy(coeActivityOrder.getLastModifiedBy());
				DateTime lastModifiedDate = new DateTime();
				entity.setLastModifiedDate(lastModifiedDate);
				entity.setSuccess(CoeActivityOrder.SUCCESS_YES);
				save(entity);
			}
		}
		return entity;
	}
	
	public CoeActivityOrder mergeWaitRun(CoeActivityOrder coeActivityOrder) {
		Long id = coeActivityOrder.getId();
		CoeActivityOrder entity = findById(id);
		if(null != entity) {
			Integer orderStatus = entity.getOrderStatus();
			if(
					Order.ORDER_STATUS_GENERATED.getValueInt().equals(orderStatus)
					|| Order.ORDER_STATUS_PAY_BEGIN.getValueInt().equals(orderStatus)
					|| Order.ORDER_STATUS_CHECKOUT.getValueInt().equals(orderStatus)
			) {
				entity.setOrderStatus(coeActivityOrder.getOrderStatus());
				//entity.setPayTime(coeActivityOrder.getPayTime());
				entity.setLastModifiedBy(coeActivityOrder.getLastModifiedBy());
				DateTime lastModifiedDate = new DateTime();
				entity.setLastModifiedDate(lastModifiedDate);
				entity.setSuccess(CoeActivityOrder.SUCCESS_YES);
				save(entity);
			}
		}
		return entity;
	}
	
	@Transactional
	@Override
	public CoeActivityOrder mergeFailure(CoeActivityOrder coeActivityOrder) {
		Long id = coeActivityOrder.getId();
		CoeActivityOrder entity = findById(id);
		if(null != entity) {
			Integer orderStatus = entity.getOrderStatus();
			if(
					Order.ORDER_STATUS_GENERATED.getValueInt().equals(orderStatus)
					|| Order.ORDER_STATUS_PAY_BEGIN.getValueInt().equals(orderStatus)
					|| Order.ORDER_STATUS_CHECKOUT.getValueInt().equals(orderStatus)) {
				entity.setOrderStatus(coeActivityOrder.getOrderStatus());
				entity.setPayTime(coeActivityOrder.getPayTime());
				entity.setLastModifiedBy(coeActivityOrder.getLastModifiedBy());
				DateTime lastModifiedDate = new DateTime();
				entity.setLastModifiedDate(lastModifiedDate);
				entity.setSuccess(CoeActivityOrder.SUCCESS_YES);
				save(entity);
			}
		}
		return entity;
	}
	
	@Transactional
	@Override
	public CoeActivityOrder findById(Long id, boolean withCoeActivityUser) {
		CoeActivityOrder entity = findById(id);
		if(null != entity && withCoeActivityUser) {
			List<CoeActivityUser> coeActivityUserList = coeActivityUserService.findByBaseId(id);
			entity.setUserList(coeActivityUserList);
		}
		return entity;
	}
	
	@Transactional
	@Override
	public CoeActivityOrder mergeStatus(Order order) {
		Long id = order.getId();
		CoeActivityOrder entity = findById(id);
		if(null != entity) {
			Integer orderStatus = order.getOrderStatus();
			Integer entityStatus=entity.getOrderStatus();
			boolean changed=false;
			
			if(Order.ORDER_STATUS_GENERATED.getValueInt().equals(orderStatus)) {
				if(Order.ORDER_STATUS_INIT.getValueInt().equals(entityStatus)) {
					entity.setOrderStatus(order.getOrderStatus());
					changed=true;
				}
			}else if(Order.ORDER_STATUS_PAY_BEGIN.getValueInt().equals(orderStatus)) {
				if(Order.ORDER_STATUS_GENERATED.getValueInt().equals(entityStatus)) {
					entity.setOrderStatus(order.getOrderStatus());
					entity.setPayBegin(order.getPayBegin());
					changed=true;
				}
			}else if(Order.ORDER_STATUS_CHECKOUT.getValueInt().equals(orderStatus)) {
				if(Order.ORDER_STATUS_PAY_BEGIN.getValueInt().equals(entityStatus)) {
					entity.setOrderStatus(order.getOrderStatus());
					entity.setPayTime(order.getLastModifiedDate().toDate());
					changed=true;
				}
			}else if(Order.ORDER_STATUS_PAY_SUCCESS.getValueInt().equals(orderStatus)) {
				if(Order.ORDER_STATUS_PAY_BEGIN.getValueInt().equals(entityStatus)
						|| Order.ORDER_STATUS_CHECKOUT.getValueInt().equals(entityStatus)) {
					entity.setOrderStatus(order.getOrderStatus());
					entity.setPayTime(order.getLastModifiedDate().toDate());
					changed=true;
				}
			}else if(Order.ORDER_STATUS_PAY_FAILURE.getValueInt().equals(orderStatus)) {
				if(Order.ORDER_STATUS_PAY_BEGIN.getValueInt().equals(entityStatus)
						|| Order.ORDER_STATUS_CHECKOUT.getValueInt().equals(entityStatus)) {
					entity.setOrderStatus(order.getOrderStatus());
					entity.setPayTime(order.getLastModifiedDate().toDate());
					changed=true;
				}
			}else if(Order.ORDER_STATUS_CANCEL.getValueInt().equals(orderStatus)) {
				if(Order.ORDER_STATUS_INIT.getValueInt().equals(entityStatus)
						) {
					entity.setOrderStatus(order.getOrderStatus());
					entity.setCancelTime(order.getLastModifiedDate().toDate());
					changed=true;
				}
			}else if(Order.ORDER_STATUS_SENT.getValueInt().equals(orderStatus)) {
				if(Order.ORDER_STATUS_PAY_SUCCESS.getValueInt().equals(entityStatus)
						) {
					entity.setOrderStatus(order.getOrderStatus());
					entity.setExeTime(order.getLastModifiedDate().toDate());
					changed=true;
				}
			}else if(Order.ORDER_STATUS_RECIEVED.getValueInt().equals(orderStatus)) {
				if(Order.ORDER_STATUS_SENT.getValueInt().equals(entityStatus)
						) {
					entity.setOrderStatus(order.getOrderStatus());
					//entity.setExeTime(order.getLastModifiedDate().toDate());
					changed=true;
				}
			}else if(Order.ORDER_STATUS_FINISHED.getValueInt().equals(orderStatus)) {
				if(Order.ORDER_STATUS_RECIEVED.getValueInt().equals(entityStatus)
						) {
					entity.setOrderStatus(order.getOrderStatus());
					entity.setFinishTime(order.getLastModifiedDate().toDate());
					changed=true;
				}
			}else if(Order.ORDER_STATUS_CLOSED.getValueInt().equals(orderStatus)) {
					//直接关闭订单
					entity.setOrderStatus(order.getOrderStatus());
					entity.setClosedTime(order.getLastModifiedDate().toDate());
					changed=true;
			}
			
			entity.setLastModifiedBy(order.getLastModifiedBy());
			entity.setLastModifiedDate(order.getLastModifiedDate());
			if(changed) {
				save(entity);
			}
		}
		return entity;
	}
	
	public CoeActivityOrder mergeStatus(PayNotifyWechat notify) {
		String outTradeNo = notify.getOutTradeNo();
		CoeActivityOrder entity = findByOutTradeNo(outTradeNo,false);
		if(null != entity) {
			Integer entityStatus=entity.getOrderStatus();
			String resultCode = notify.getResultCode();
			String returnCode = notify.getReturnCode();
			String respCode = notify.getRespCode();
			String respMsg = notify.getRespMsg();
			Date notifyTime=new Date();
			boolean changed=false;
			if(PayConfig.RETURNCODE_SUCCESS.equals(returnCode)
					&& PayConfig.RESULTCODE_SUCCESS.equals(resultCode)
					) {
				if(Order.ORDER_STATUS_PAY_BEGIN.getValueInt().equals(entityStatus)
				|| Order.ORDER_STATUS_CHECKOUT.getValueInt().equals(entityStatus)
				|| Order.ORDER_STATUS_PAY_FAILURE.getValueInt().equals(entityStatus)
				) {
					entity.setOrderStatus(Order.ORDER_STATUS_PAY_SUCCESS.getValueInt());
					entity.setPayTime(notifyTime);
					changed=true;
				}
			}else {
				if(Order.ORDER_STATUS_PAY_BEGIN.getValueInt().equals(entityStatus)
						|| Order.ORDER_STATUS_CHECKOUT.getValueInt().equals(entityStatus)
				) {
					entity.setOrderStatus(Order.ORDER_STATUS_PAY_FAILURE.getValueInt());
					entity.setPayTime(notifyTime);
					changed=true;
					
				}
			}
			if(changed) {
				save(entity);
				coeActivityUserService.mergeStatus(entity);
			}
		}
		
		return entity;
	}
	
	public CoeActivityOrder findByOutTradeNo(String outTradeNo) {
		return coeActivityOrderDao.findByOutTradeNo(outTradeNo);
	}
	
	public CoeActivityOrder findByOutTradeNo(String outTradeNo, boolean withUser) {
		
		CoeActivityOrder entity = coeActivityOrderDao.findByOutTradeNo(outTradeNo);
			
		if(null != entity && withUser) {
			Long baseId = entity.getId();
			List<CoeActivityUser> coeActivityUserList = coeActivityUserService.findByBaseId(baseId);
			entity.setUserList(coeActivityUserList);
		}
		return entity;
	}
	

}
