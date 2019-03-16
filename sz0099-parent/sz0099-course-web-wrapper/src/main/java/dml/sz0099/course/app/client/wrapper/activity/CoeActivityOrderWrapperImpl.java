/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.wrapper.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jit4j.app.client.wrapper.order.OrderWrapper;
import org.jit4j.app.client.wrapper.pay.PaymentWrapper;
import org.jit4j.app.client.wrapper.pay.wechat.PayRequestWechatWrapper;
import org.jit4j.app.client.wrapper.pay.wechat.PayResponseWechatWrapper;
import org.jit4j.app.persist.entity.balance.PayBusiness;
import org.jit4j.app.persist.entity.order.Order;
import org.jit4j.app.persist.entity.order.OrderActivity;
import org.jit4j.app.persist.entity.pay.PayConfig;
import org.jit4j.app.persist.entity.pay.wechat.OrderQueryResponse;
import org.jit4j.app.persist.entity.pay.wechat.PayRequestWechat;
import org.jit4j.app.persist.entity.pay.wechat.PayResponseWechat;
import org.jit4j.app.persist.entity.plat.Plat;
import org.jit4j.core.persist.entity.BaseEntity;
import org.jit4j.core.pub.transfer.dto.pay.PaymentDto;
import org.jit4j.core.webmvc.util.UserUtils;
import org.jit8j.core.util.GsonBuilderUtils;
import org.jit8j.core.util.OrderUtil;
import org.jit8j.core.util.generator.DistributeIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.activity.CoeActivityOrderDelegate;
import dml.sz0099.course.app.module.define.CoeActivityRespCode;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityFee;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityOrder;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityOrderLog;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityUser;
import dml.sz0099.course.app.persist.entity.activity.vo.CoeActivityOrderVo;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2019-01-05 10:28:31
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2019-01-05	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Service
public class CoeActivityOrderWrapperImpl implements CoeActivityOrderWrapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityOrderWrapperImpl.class);

	@Autowired
	private CoeActivityOrderDelegate coeActivityOrderDelegate;
	
	@Autowired
	private CoeActivityWrapper coeActivityWrapper;
	
	@Autowired
	private OrderWrapper orderWrapper;
	
	@Autowired
	private PaymentWrapper paymentWrapper;
	
	@Autowired
	private PayResponseWechatWrapper payResponseWechatWrapper;
	
	@Autowired
	private PayRequestWechatWrapper payRequestWechatWrapper;
	
	@Value("${coeActivityOrderWrapper.orderPayTimeout:7200}")
	private Long orderPayTimeout;
	
	@Autowired
	private CoeActivityOrderLogWrapper coeActivityOrderLogWrapper;
	
	/**
	 * 根据Id查询CoeActivityOrder实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityOrder findById(Long id) {
		LOGGER.debug("--- wrapper>>>findById begin --------- id is:{} ", id);
		CoeActivityOrder coeActivityOrder = coeActivityOrderDelegate.findById(id);
		LOGGER.debug("--- wrapper>>>findById end --------- id is:{} , result is {} ", id, coeActivityOrder);
		return coeActivityOrder;
	}
	
	public CoeActivityOrder findById(Long id, boolean withJoinItem, boolean withActivity) {
		CoeActivityOrder coeActivityOrder = coeActivityOrderDelegate.findById(id,withJoinItem);
		LOGGER.debug("--- wrapper>>>findById end --------- id is:{} , result is {} ", id, coeActivityOrder);
		if(null != coeActivityOrder ) {
			if(withActivity) {
				Long mainId=coeActivityOrder.getMainId();
				CoeActivity activity = coeActivityWrapper.findByIdWithTimeAndFee(mainId);
				coeActivityOrder.setActivity(activity);
			}
		}
		return coeActivityOrder;
	}
	
	@Override
	public CoeActivityOrder findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityOrder coeActivityOrder = coeActivityOrderDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityOrder);
		return coeActivityOrder;
	}
	
	/**
	 * 根据IdList查询CoeActivityOrder实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityOrder> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper>>>findByIdList begin ---------  ");
		List<CoeActivityOrder> coeActivityOrderList = coeActivityOrderDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper>>>findByIdList end ---------  result is {} ",  coeActivityOrderList);
		return coeActivityOrderList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeActivityOrder persistEntity(CoeActivityOrder coeActivityOrder) {
		LOGGER.debug("--- wrapper>>>persistEntity begin ---------  ");
		CoeActivityOrder entity = coeActivityOrderDelegate.persistEntity(coeActivityOrder);
		Long id = coeActivityOrder.getId();
		LOGGER.debug("--- wrapper>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeActivityOrder mergeEntity(CoeActivityOrder coeActivityOrder) {
		Long id = coeActivityOrder.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		CoeActivityOrder entity = coeActivityOrderDelegate.mergeEntity(coeActivityOrder);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeActivityOrder saveOrUpdate(CoeActivityOrder coeActivityOrder) {
		Long id = coeActivityOrder.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		CoeActivityOrder entity = coeActivityOrderDelegate.saveOrUpdate(coeActivityOrder);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeActivityOrder> findPage(CoeActivityOrder coeActivityOrder, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<CoeActivityOrder> page = coeActivityOrderDelegate.findPage(coeActivityOrder, pageable);
		return page;
	}
	@Override
	public Page<CoeActivityOrder> findPage(CoeActivityOrder coeActivityOrder, boolean withAct, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<CoeActivityOrder> page = coeActivityOrderDelegate.findPage(coeActivityOrder, pageable);
		if(page != null && page.getTotalElements()>0) {
			List<CoeActivityOrder> content = page.getContent();
			Map<Long, CoeActivityOrder> orderMap = new HashMap<>();
			List<Long> idList = new ArrayList<>(content.size());
			for(CoeActivityOrder order : content) {
				Long id = order.getId();
				orderMap.put(id, order);
				idList.add(id);
			}
			
			Map<Long, CoeActivityOrderLog> logMap = null;
			if(withAct) {
				logMap = coeActivityOrderLogWrapper.findMapByOrderIdList(idList);
			}
			
			for(CoeActivityOrder order : content) {
				Long id = order.getId();
				if(null != logMap) {
					CoeActivityOrderLog orderLog = logMap.get(id);
					order.setOrderLog(orderLog);
				}
			}
		}
		return page;
	}
	
	public CoeActivityOrder findInitOrder(Long userId, Long mainId) {
		return coeActivityOrderDelegate.findInitOrder(userId, mainId);
	}
	
	public CoeActivityOrder findInitOrder(CoeActivityOrder coeActivityOrder) {
		Long mainId = coeActivityOrder.getMainId();
		CoeActivity activity=coeActivityWrapper.findByIdWithTimeAndFee(mainId);
		coeActivityOrder.setActivity(activity);
		CoeActivityOrder order = coeActivityOrderDelegate.findInitOrder(coeActivityOrder);
		order.setActivity(activity);
		return order;
	}
	
	/**
	 * 订单支付时，查验已报名且支付人数是否满员
	 * 查验是否在截止报名时间之前
	 * 生成预下单参数，进行支付
	 */
	public CoeActivityOrderVo findPrePayOrder(CoeActivityOrder coeActivityOrder) {
		//1.查询订单
		Long id = coeActivityOrder.getId();
		CoeActivityOrder actOrder = findById(id,false, true);
		actOrder.setPayType(coeActivityOrder.getPayType());//设置支付方式
		String outTradeNo = actOrder.getOutTradeNo();//作为向第三方传入的outTradeNo
		Integer status = actOrder.getOrderStatus();
		
		CoeActivityOrderVo actOrderVo = new CoeActivityOrderVo();
		actOrderVo.setActOrder(actOrder);
		
		PayResponseWechat response = new PayResponseWechat();
		//判断订单状态，是否取消、已成功、关闭、退款
		if(Order.ORDER_STATUS_CANCEL.getValueInt().equals(status)){
			response.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_HAS_CANCELED);
			response.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_HAS_CANCELED);
			response.setSuccess(PayResponseWechat.SUCCESS_NO);
			actOrderVo.setResponse(response);
			return actOrderVo;
		}else if(Order.ORDER_STATUS_PAY_SUCCESS.getValueInt().equals(status)){
			response.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_HAS_PAY_SUCCESS);
			response.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_HAS_PAY_SUCCESS);
			response.setSuccess(PayResponseWechat.SUCCESS_NO);
			actOrderVo.setResponse(response);
			return actOrderVo;
		}else if(Order.ORDER_STATUS_WAIT_RUN.getValueInt().equals(status)){
			response.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_WAIT_RUN_DIRECTORY);
			response.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_WAIT_RUN_DIRECTORY);
			response.setSuccess(PayResponseWechat.SUCCESS_NO);
			actOrderVo.setResponse(response);
			return actOrderVo;
		}else if(Order.ORDER_STATUS_SENT.getValueInt().equals(status)){
			response.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_HAS_SENT);
			response.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_HAS_SENT);
			response.setSuccess(PayResponseWechat.SUCCESS_NO);
			actOrderVo.setResponse(response);
			return actOrderVo;
		}
		
		
		if(Order.ORDER_STATUS_GENERATED.getValueInt().equals(status)
				|| Order.ORDER_STATUS_PAY_BEGIN.getValueInt().equals(status)
				|| Order.ORDER_STATUS_PAY_FAILURE.getValueInt().equals(status)
				){
			actOrderVo=doUnifiedOrder( actOrderVo);
		}
		
		if(Order.ORDER_STATUS_CHECKOUT.getValueInt().equals(status)) {
			PaymentDto paymentDto = new PaymentDto();
			paymentDto.setOutTradeNo(outTradeNo);
			paymentDto.setUserId(UserUtils.getUserId());
			paymentDto.setCreatedBy(UserUtils.getUserId());
			paymentDto.setPayPlat(PayConfig.PAY_TYPE_WECHAT);
			//查询订单状态
			OrderQueryResponse oresp = paymentWrapper.orderqueryForWechat(paymentDto);
			String tradeState=oresp.getTradeState();
			if(OrderQueryResponse.TRADE_STATE_SUCCESS.equals(tradeState)){
				response.setSuccess(PayResponseWechat.SUCCESS_NO);
				response.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_HAS_PAY_SUCCESS);
				response.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_HAS_PAY_SUCCESS);
				actOrderVo.setResponse(response);
			}else if(OrderQueryResponse.TRADE_STATE_USERPAYING.equals(tradeState)) {
				response.setSuccess(PayResponseWechat.SUCCESS_NO);
				response.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_PAYING);
				response.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_PAYING);
				actOrderVo.setResponse(response);
			}else if(OrderQueryResponse.TRADE_STATE_NOTPAY.equals(tradeState)) {
				actOrderVo=doUnifiedOrder(actOrderVo);
			}else {
				response.setSuccess(PayResponseWechat.SUCCESS_NO);
				response.setRespCode(oresp.getTradeState());
				response.setRespMsg(oresp.getTradeStateDesc());
				actOrderVo.setResponse(response);
			}
		}
		
		return actOrderVo;
	}

	/**
	 * @param actOrder
	 * @param outTradeNo
	 * @param actOrderVo
	 */
	private CoeActivityOrderVo doUnifiedOrder( CoeActivityOrderVo actOrderVo) {
		
		CoeActivityOrder actOrder=actOrderVo.getActOrder(); 
		String outTradeNo = actOrder.getOutTradeNo();
		
		PayResponseWechat response=null;
		//查询是否下单成功，若成功，且未过期，则直接返回，用户可继续进行支付
		response = payResponseWechatWrapper.findByOutTradeNo(outTradeNo, true, true);
		if(null != response) {
			String prepayId = response.getPrepayId();
			if(StringUtils.isNotBlank(prepayId)) {
				
				Long requestId = response.getRequestId();
				PayRequestWechat request = payRequestWechatWrapper.findById(requestId);
				
				Date timeExpire = request.getTimeExpire();
				Date current = new Date();
				boolean expired = OrderUtil.hasExpired(current, timeExpire);
				if(!expired) {
					//若未过期
					actOrderVo.setResponse(response);
					return actOrderVo;
				}
			}
		}
		
		//1.是否直接进入等待出行
		boolean waitRunDirectly = checkWaitRun(actOrder);
		if(waitRunDirectly) {
			actOrder.setOrderStatus(Order.ORDER_STATUS_WAIT_RUN.getValueInt());
			mergeWaitRun(actOrder);
			response = new PayResponseWechat();
			response.setSuccess(BaseEntity.SUCCESS_YES);
			response.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_WAIT_RUN_DIRECTORY);
			response.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_WAIT_RUN_DIRECTORY);
			actOrderVo.setResponse(response);
			return actOrderVo;
		}
		
		//若已过期,重新生成订单编号
		outTradeNo = String.valueOf(DistributeIdGenerator.getFlowIdWorkerInstance().nextId());
		actOrder.setOutTradeNo(outTradeNo);	
		
		//若已过期，则重新生成outTradeNo,并调统一下单重新生成perpayId
		PaymentDto paymentDto = new PaymentDto();
		//1.1 创建订单获取订单编号outTradeNo
		paymentDto.setProductId(actOrder.getId());//将订单子域id写入productId，替换掉传入的id, 此项可选
		paymentDto.setTitle(actOrder.getTitle());//设置title
		
		paymentDto.setOutTradeNo(outTradeNo);//设置订单编号
		paymentDto.setCreatedBy(actOrder.getUserId());
		paymentDto.setAmountPay(actOrder.getTotalRmb());
		paymentDto.setOrderType(PayBusiness.CODE_ACTIVITY_RMB);
		paymentDto.setOrderId(actOrder.getId());
		paymentDto.setUserId(actOrder.getUserId());
		paymentDto.setPayeeUserId(Plat.getSingleInstance().getPlatId());
		paymentDto.setPayPlat(actOrder.getPayType());
		paymentDto.setClientIp(actOrder.getClientIp());
		Date timeStart = new Date();
		paymentDto.setTimeStart(timeStart);
		Date timeExpired = OrderUtil.calExpiredTime(timeStart, OrderUtil.PAY_ORDER_TIME_EXPIRED_S_DEFAULT);
		paymentDto.setTimeExpired(timeExpired);
		
		actOrder.setPayBegin(timeStart);
		mergePayBegin(actOrder);
		
		String moduleKey=CourseAppModule.APP_OOD_ACTIVITY.getModuleKey();
		paymentDto.setModuleKey(moduleKey);
		String msgSubKey=CourseAppModule.APP_OOD_ACTIVITY_MSG_ORDER_MERGE_STATUS.getMsgKey();
		paymentDto.setMsgSubKey(msgSubKey);
		
		//调统一下单
		response=paymentWrapper.unifiedorderForWechat(paymentDto);
		
		//成功后，执行订单状态更新
		syncResponse( response,  actOrder);
		
		actOrderVo.setResponse(response);
		
		return actOrderVo;
	}
	
	
	
	
	
	
	public CoeActivityOrderVo findPrePayOrder2(CoeActivityOrder coeActivityOrder) {
		//1.查询订单
		Long id = coeActivityOrder.getId();
		CoeActivityOrder actOrder = findById(id,false, true);
		
		CoeActivityOrderVo actOrderVo = new CoeActivityOrderVo();
		actOrderVo.setActOrder(actOrder);
		Integer status = actOrder.getOrderStatus();
		
		//1.是否直接进入等待出行
		boolean waitRunDirectly = checkWaitRun(actOrder);
		if(waitRunDirectly) {
			actOrder.setOrderStatus(Order.ORDER_STATUS_WAIT_RUN.getValueInt());
			mergeWaitRun(actOrder);
			PayResponseWechat response = new PayResponseWechat();
			response.setSuccess(BaseEntity.SUCCESS_YES);
			response.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_WAIT_RUN_DIRECTORY);
			response.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_WAIT_RUN_DIRECTORY);
			actOrderVo.setResponse(response);
			return actOrderVo;
		}
		
		
		Order order = new Order();
		order.setId(id);
		order.setOrderType(PayBusiness.CODE_ACTIVITY_RMB);
		String clientIp=coeActivityOrder.getClientIp();
		order.setClientIp(clientIp);
		
		//2.是否进行继续支付
		if(Order.ORDER_STATUS_PAY_BEGIN.getValueInt().equals(status)
			|| Order.ORDER_STATUS_CHECKOUT.getValueInt().equals(status)
			) {
			PayResponseWechat response = orderWrapper.payOrderWechatAgain(order);
			response.setSuccess(BaseEntity.SUCCESS_YES);
			actOrderVo.setResponse(response);
			return actOrderVo;
		}
		
		//3.调用创建订单接口
		PayResponseWechat response = createOrder(actOrder, order);
		actOrderVo.setResponse(response);
		
		return actOrderVo;
	}

	/**
	 * @param id
	 * @param actOrder
	 * @param order
	 * @return
	 */
	private PayResponseWechat createOrder(CoeActivityOrder actOrder, Order order) {
		Long id = actOrder.getId();
		CoeActivity activity = actOrder.getActivity();
		String title="";
		Long authorId=null;
		if(null != activity) {
			title=activity.getTitle();
			authorId=activity.getUserId();
		}
		
		//3.转化成订单，调用预下单请求接口
		order.setAmountPay(actOrder.getTotalRmb());
		//order.setDomainId(actOrder.getMainId());//活动id
		order.setPayType(PayConfig.PAY_TYPE_WECHAT);
		order.setTitle("活动");
		order.setUserId(actOrder.getUserId());
		order.setCreatedBy(actOrder.getUserId());
		
		String moduleKey=CourseAppModule.APP_OOD_ACTIVITY.getModuleKey();
		order.setModuleKey(moduleKey);
		String msgSubKey=CourseAppModule.APP_OOD_ACTIVITY_MSG_ORDER_MERGE_STATUS.getMsgKey();
		order.setMsgSubKey(msgSubKey);
		
		OrderActivity orderActivity = new OrderActivity();
		order.setOrderActivity(orderActivity);
		
		orderActivity.setAuthorId(authorId);
		orderActivity.setOrderId(id);
		orderActivity.setTitle(title);
		orderActivity.setMainId(actOrder.getMainId());
		orderActivity.setOffTime(activity.getActTime().getOffTime());
		
		//4.保存预下单请求
		actOrder.setOrderStatus(Order.ORDER_STATUS_PAY_BEGIN.getValueInt());
		mergePayBegin(actOrder);
		PayResponseWechat response = orderWrapper.payOrderWechat(order);
		//5.同步响应信息
		syncResponse(response, actOrder);
		response.setSuccess(BaseEntity.SUCCESS_YES);
		return response;
	}

	/**
	 * @param actOrder
	 * @param actOrderVo
	 * @param status
	 */
	private boolean checkWaitRun(CoeActivityOrder actOrder) {
		Long totalRmb = actOrder.getTotalRmb();
		CoeActivity activity = actOrder.getActivity();
		Integer status = actOrder.getOrderStatus();
		if(activity != null) {
			CoeActivityFee actFee = activity.getActFee();
			if(null != actFee) {
				Integer recieveType = actFee.getRecieveType();
				if(CoeActivityFee.RECIEVETYPE_OFFLIME.getValueInt().equals(recieveType)) {
					return true;
				}
				if(CoeActivityFee.FEETYPE_FREE.getValueInt().equals(recieveType)) {
					return true;
				}
			}
		}
		if(null == totalRmb || totalRmb <1 ) {
			//若费用金额小于1，跳过下单付款步骤，直接报名成功
			if(Order.ORDER_STATUS_GENERATED.getValueInt().equals(status)
			|| Order.ORDER_STATUS_PAY_BEGIN.getValueInt().equals(status)
			|| Order.ORDER_STATUS_CHECKOUT.getValueInt().equals(status)
			) {
				return true;
			}
		}
		return false;
	}
	
	private void syncResponse(PayResponseWechat response, CoeActivityOrder order) {
		int success = response.getSuccess();
		LOGGER.debug("-----------2004.1.0 wrapper.payOrder>>>syncResponse>>> {} --------- ",GsonBuilderUtils.toJsonPretty(response));
		if(PayResponseWechat.SUCCESS_YES==success) {
			String returnCode = response.getReturnCode();
			if(PayConfig.RETURNCODE_SUCCESS.equals(returnCode)) {
				String resultCode = response.getResultCode();
				Date payTime = new Date();
				order.setPayTime(payTime);
				if(PayConfig.RESULTCODE_SUCCESS.equals(resultCode)) {
					//3.1 修改订单状态信息
					order.setOrderStatus(Order.ORDER_STATUS_CHECKOUT.getValueInt());
					
					LOGGER.debug("----------- 2004.1.1 wrapper.payOrder>>>syncResponse>>>mergeCheckout , order.id:{}  --------- ",order.getId());
					mergeCheckout(order);
					
				} else if (PayConfig.RESULTCODE_FAIL.equals(resultCode)){
					LOGGER.debug("----------- 2004.2.1 wrapper.payOrder>>>syncResponse>>>mergeFailure , order.id:{}  --------- ",order.getId());
					order.setOrderStatus(Order.ORDER_STATUS_PAY_FAILURE.getValueInt());
					mergeFailure(order);
					//若同步响应失败，则直接更新成失败
				}
			}else {
				LOGGER.debug("----------- 2004.3.1 wrapper.payOrder>>>syncResponse>>>mergeFailure , order.id:{}  --------- ",order.getId());
				order.setOrderStatus(Order.ORDER_STATUS_PAY_FAILURE.getValueInt());
				mergeFailure(order);
			}
		}
	}
	

	@Override
	public CoeActivityOrder findByMainId(CoeActivityOrder coeActivityOrder) {
		return coeActivityOrderDelegate.findByMainId(coeActivityOrder);
	}
	
	public CoeActivityOrder createOrder(CoeActivityOrder coeActivityOrder) {
		return coeActivityOrderDelegate.createOrder(coeActivityOrder);
	}
	
	public CoeActivityOrder createOrder(CoeActivity activity) {
		return coeActivityOrderDelegate.createOrder(activity);
	}
	
	public CoeActivityOrder cancelOrder(CoeActivityOrder order) {
		return coeActivityOrderDelegate.cancelOrder(order);
	}
	public CoeActivityOrder confirmOrder(CoeActivityOrder order) {
		Long mainId=order.getMainId();
		
		CoeActivity  entity = coeActivityWrapper.findByIdWithTimeAndFee(mainId);
		if(null != entity) {
			order.setActivity(entity);
			CoeActivityFee actFee = entity.getActFee();
			if(null != actFee) {
				List<CoeActivityUser> userList = order.getUserList();
				Integer num = userList.size();
				order.setNum(num);
				
				Long rmb=actFee.getRmbAmount();
				if(null == rmb) {
					rmb=0l;
				}
				order.setRmb(rmb);
				Long totalRmb = rmb*num;
				order.setTotalRmb(totalRmb);
				
			}
			Date orderTime = new Date();
			order.setOrderTime(orderTime);
			order.setTitle(entity.getTitle());
			return coeActivityOrderDelegate.confirmOrder(order);
		}
		return order;
	}
	
	public CoeActivityOrder deleteOrder(CoeActivityOrder coeActivityOrder) {
		return coeActivityOrderDelegate.deleteOrder(coeActivityOrder);
	}
	
	public Long countByMainId(Long activityId) {
		return coeActivityOrderDelegate.countByMainId(activityId);
	}

	@Override
	public CoeActivityOrder findByMainId(Long activityId) {
		return coeActivityOrderDelegate.findByMainId(activityId);
	}
	
	public Map<Long, CoeActivityOrder>  findMapByMainIdList(List<Long> mainIdList) {
		return coeActivityOrderDelegate.findMapByMainIdList(mainIdList);
	}
	
	public Page<CoeActivityOrder> findPageWithNotself(CoeActivityOrder coeActivityOrder, Pageable pageable){
		return coeActivityOrderDelegate.findPageWithNotself(coeActivityOrder, pageable);
	}
	
	public CoeActivityOrder mergePayTime(CoeActivityOrder coeActivityOrder) {
		return coeActivityOrderDelegate.mergePayTime(coeActivityOrder);
	}
	public CoeActivityOrder mergeExeTime(CoeActivityOrder coeActivityOrder) {
		return coeActivityOrderDelegate.mergeExeTime(coeActivityOrder);
	}
	public CoeActivityOrder mergeFinishTime(CoeActivityOrder coeActivityOrder) {
		return coeActivityOrderDelegate.mergeFinishTime(coeActivityOrder);
	}
	
	public CoeActivityOrder mergePayBegin(CoeActivityOrder coeActivityOrder) {
		return coeActivityOrderDelegate.mergePayBegin(coeActivityOrder);
	}
	
	public CoeActivityOrder mergeCheckout(CoeActivityOrder coeActivityOrder) {
		return coeActivityOrderDelegate.mergeCheckout(coeActivityOrder);
	}
	
	public CoeActivityOrder mergeWaitRun(CoeActivityOrder coeActivityOrder) {
		return coeActivityOrderDelegate.mergeWaitRun(coeActivityOrder);
	}
	
	public CoeActivityOrder mergeFailure(CoeActivityOrder coeActivityOrder) {
		return coeActivityOrderDelegate.mergeFailure(coeActivityOrder);
	}

}
