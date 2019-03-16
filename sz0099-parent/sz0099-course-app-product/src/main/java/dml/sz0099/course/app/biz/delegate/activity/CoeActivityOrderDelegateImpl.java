package dml.sz0099.course.app.biz.delegate.activity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.jit4j.app.persist.entity.order.Order;
import org.jit4j.app.persist.entity.pay.wechat.PayNotifyWechat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.activity.CoeActivityOrderService;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityOrder;

/**
 * coeActivityOrderServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeActivityOrderDelegateImpl implements CoeActivityOrderDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityOrderDelegateImpl.class);
	
	@Autowired
	private CoeActivityOrderService coeActivityOrderService;

	/**
	 * 根据Id查询CoeActivityOrder实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityOrder findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeActivityOrder coeActivityOrder = coeActivityOrderService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeActivityOrder);
		return coeActivityOrder;
	}
	
	public CoeActivityOrder findById(Long id, boolean withJoinItem) {
		CoeActivityOrder coeActivityOrder = coeActivityOrderService.findById(id,withJoinItem);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeActivityOrder);
		return coeActivityOrder;
	}
	
	@Override
	public CoeActivityOrder findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityOrder coeActivityOrder = coeActivityOrderService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityOrder);
		return coeActivityOrder;
	}
	
	/**
	 * 根据IdList查询CoeActivityOrder实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityOrder> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeActivityOrder> coeActivityOrderList = coeActivityOrderService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeActivityOrderList);
		return coeActivityOrderList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeActivityOrder persistEntity(CoeActivityOrder coeActivityOrder) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeActivityOrder entity = coeActivityOrderService.persistEntity(coeActivityOrder);
		Long id = coeActivityOrder.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeActivityOrder mergeEntity(CoeActivityOrder coeActivityOrder) {
		Long id = coeActivityOrder.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeActivityOrder entity = coeActivityOrderService.mergeEntity(coeActivityOrder);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeActivityOrder saveOrUpdate(CoeActivityOrder coeActivityOrder) {
		Long id = coeActivityOrder.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeActivityOrder entity = coeActivityOrderService.saveOrUpdate(coeActivityOrder);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeActivityOrder> findPage(CoeActivityOrder coeActivityOrder, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeActivityOrder> page = coeActivityOrderService.findPage(coeActivityOrder, pageable);
		return page;
	}
	
	public CoeActivityOrder findInitOrder(Long userId, Long mainId) {
		return coeActivityOrderService.findInitOrder(userId, mainId);
	}
	
	public CoeActivityOrder findInitOrder(CoeActivityOrder coeActivityOrder) {
		return coeActivityOrderService.findInitOrder(coeActivityOrder);
	}

	@Override
	public CoeActivityOrder findByMainId(CoeActivityOrder coeActivityOrder) {
		return coeActivityOrderService.findByMainId(coeActivityOrder);
	}
	
	public CoeActivityOrder createOrder(CoeActivityOrder coeActivityOrder) {
		return coeActivityOrderService.createOrder(coeActivityOrder);
	}
	
	public CoeActivityOrder createOrder(CoeActivity activity) {
		return coeActivityOrderService.createOrder(activity);
	}
	
	public CoeActivityOrder confirmOrder(CoeActivityOrder order) {
		return coeActivityOrderService.confirmOrder(order);
	}
	public CoeActivityOrder cancelOrder(CoeActivityOrder order) {
		return coeActivityOrderService.cancelOrder(order);
	}
	
	public CoeActivityOrder deleteOrder(CoeActivityOrder coeActivityOrder) {
		return coeActivityOrderService.deleteOrder(coeActivityOrder);
	}
	
	public Long countByMainId(Long activityId) {
		return coeActivityOrderService.countByMainId(activityId);
	}

	@Override
	public CoeActivityOrder findByMainId(Long activityId) {
		return coeActivityOrderService.findByMainId(activityId);
	}
	
	public Map<Long, CoeActivityOrder>  findMapByMainIdList(List<Long> mainIdList) {
		return coeActivityOrderService.findMapByMainIdList(mainIdList);
	}
	
	public Page<CoeActivityOrder> findPageWithNotself(CoeActivityOrder coeActivityOrder, Pageable pageable){
		return coeActivityOrderService.findPageWithNotself(coeActivityOrder, pageable);
	}
	
	public CoeActivityOrder mergePayTime(CoeActivityOrder coeActivityOrder) {
		return coeActivityOrderService.mergePayTime(coeActivityOrder);
	}
	public CoeActivityOrder mergePayBegin(CoeActivityOrder coeActivityOrder) {
		return coeActivityOrderService.mergePayBegin(coeActivityOrder);
	}
	public CoeActivityOrder mergeExeTime(CoeActivityOrder coeActivityOrder) {
		return coeActivityOrderService.mergeExeTime(coeActivityOrder);
	}
	public CoeActivityOrder mergeFinishTime(CoeActivityOrder coeActivityOrder) {
		return coeActivityOrderService.mergeFinishTime(coeActivityOrder);
	}
	public CoeActivityOrder mergeCheckout(CoeActivityOrder coeActivityOrder) {
		return coeActivityOrderService.mergeCheckout(coeActivityOrder);
	}
	public CoeActivityOrder mergeFailure(CoeActivityOrder coeActivityOrder) {
		return coeActivityOrderService.mergeFailure(coeActivityOrder);
	}
	
	public CoeActivityOrder mergeWaitRun(CoeActivityOrder coeActivityOrder) {
		return coeActivityOrderService.mergeWaitRun(coeActivityOrder);
	}
	
	public CoeActivityOrder mergeStatus(Order order) {
		return coeActivityOrderService.mergeStatus(order);
	}
	
	public CoeActivityOrder mergeStatus(PayNotifyWechat notify) {
		return coeActivityOrderService.mergeStatus(notify);
	}
}
