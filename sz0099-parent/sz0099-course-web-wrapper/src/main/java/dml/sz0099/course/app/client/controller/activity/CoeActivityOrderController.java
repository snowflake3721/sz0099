package dml.sz0099.course.app.client.controller.activity;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.jit4j.app.client.wrapper.pay.PaymentWrapper;
import org.jit4j.app.persist.entity.pay.PayConfig;
import org.jit4j.app.persist.entity.pay.wechat.OrderQueryResponse;
import org.jit4j.app.persist.entity.pay.wechat.PayResponseWechat;
import org.jit4j.core.persist.page.PageRequest;
import org.jit4j.core.pub.transfer.dto.pay.PaymentDto;
import org.jit4j.core.webmvc.config.ResourcePathConfig;
import org.jit4j.core.webmvc.util.IpUtils;
import org.jit4j.core.webmvc.util.UserUtils;
import org.jit8j.core.util.GsonBuilderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dml.sz0099.course.app.client.validator.activity.CoeActivityOrderValidator;
import dml.sz0099.course.app.client.wrapper.activity.CoeActivityOrderWrapper;
import dml.sz0099.course.app.module.define.CoeActivityRespCode;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityOrder;
import dml.sz0099.course.app.persist.entity.activity.vo.CoeActivityOrderVo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeActivityOrderController 控制转发
 * @author bruce yang at 2018-08-29 22:41:14
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@RequestMapping("/sz0099/ood/activity/order")
@Controller
public class CoeActivityOrderController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityOrderController.class);

	@Autowired
	private CoeActivityOrderValidator coeActivityOrderValidator;

	@Autowired
	private CoeActivityOrderWrapper coeActivityOrderWrapper;
	
	
	@Resource
	private PaymentWrapper paymentWrapper;

	//模板根目录
	private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
	//模块缩略名称目录
	private String MODULE_PATH_ACTIVITY = ResourcePathConfig.getModulesPath(CourseAppModule.APP_OOD_ACTIVITY.getAd());
	//模块主目录,一般与模块缩略名称相同
	private String MAPPING_PATH_ACTIVITY = ResourcePathConfig.getModulesPath(CourseAppModule.APP_OOD_ACTIVITY_PATH.currentPath());
	//模块二级目录
	private String MAPPING_SUB_PATH_ACTIVITY_BASIC = ResourcePathConfig.getModulesPath(CourseAppModule.APP_OOD_ACTIVITY_PATH_BASIC.currentPath());
	
	@PostConstruct
	public void init() {
		LOGGER.debug("========== TLE_DEFALT_FRONT: {} ========== ", TLE_DEFALT_FRONT);
		LOGGER.debug("========== MODULE_PATH_ACTIVITY: {} ========== ", MODULE_PATH_ACTIVITY);
		LOGGER.debug("========== MAPPING_PATH_ACTIVITY: {} ========== ", MAPPING_PATH_ACTIVITY);
		LOGGER.debug("========== MAPPING_SUB_PATH_ACTIVITY_BASIC: {} ========== ", MAPPING_SUB_PATH_ACTIVITY_BASIC);
	}
	
	public CoeActivityOrderController() {
		LOGGER.debug("##>>> init CoeActivityOrderController <<<##");
	}

	@RequestMapping(value = { "manage/addUI" }, name = "创建订单")
	public String addOrder(@ModelAttribute("entity") CoeActivityOrder coeActivityOrder,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> addOrder <<<##");

		Long userId = UserUtils.getUserId();
		coeActivityOrder.setCreatedBy(userId);
		coeActivityOrder.setLastModifiedBy(userId);
		coeActivityOrder.setUserId(userId);
		boolean checked = coeActivityOrderValidator.validateAddOrder(coeActivityOrder);
		if(checked) {
			coeActivityOrder = coeActivityOrderWrapper.findInitOrder(coeActivityOrder);
			coeActivityOrder.setSuccess(CoeActivityOrder.SUCCESS_YES);
			coeActivityOrder.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_SAVED_SUCCESS);
			coeActivityOrder.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_SAVED_SUCCESS);
			model.addAttribute("entity", coeActivityOrder);
		}
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/order/addUI");
		LOGGER.debug("--->>> addOrder.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/confirmOrder" }, name = "确认订单")
	public String confirmOrder(@ModelAttribute("entity") CoeActivityOrder coeActivityOrder,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> confirmOrder <<<##");

		Long userId = UserUtils.getUserId();
		coeActivityOrder.setCreatedBy(userId);
		coeActivityOrder.setLastModifiedBy(userId);
		coeActivityOrder.setUserId(userId);
		boolean checked = coeActivityOrderValidator.validateConfirmOrder(coeActivityOrder);
		if(checked) {
			coeActivityOrder.setClientIp(IpUtils.getIpAddr(request));
			coeActivityOrder = coeActivityOrderWrapper.confirmOrder(coeActivityOrder);
			coeActivityOrder.setSuccess(CoeActivityOrder.SUCCESS_YES);
			coeActivityOrder.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_CONFIRM_SUCCESS);
			coeActivityOrder.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_CONFIRM_SUCCESS);
			model.addAttribute("entity", coeActivityOrder);
		}
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/order/confirmOrder");
		LOGGER.debug("--->>> confirmOrder.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/cancelOrder" }, name = "取消订单")
	public String cancelOrder(@ModelAttribute("entity") CoeActivityOrder coeActivityOrder,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> cancelOrder <<<##");

		Long userId = UserUtils.getUserId();
		coeActivityOrder.setCreatedBy(userId);
		coeActivityOrder.setLastModifiedBy(userId);
		coeActivityOrder.setUserId(userId);
		boolean checked = coeActivityOrderValidator.validateCancelOrder(coeActivityOrder);
		if(checked) {
			coeActivityOrder = coeActivityOrderWrapper.cancelOrder(coeActivityOrder);
			coeActivityOrder.setSuccess(CoeActivityOrder.SUCCESS_YES);
			coeActivityOrder.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_CANCEL_SUCCESS);
			coeActivityOrder.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_CANCEL_SUCCESS);
			model.addAttribute("entity", coeActivityOrder);
		}
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/order/cancelOrder");
		LOGGER.debug("--->>> cancelOrder.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/prePay" }, name = "订单支付")
	public String prePay(@ModelAttribute("entity") CoeActivityOrder order,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> prePay <<<##");

		Long userId = UserUtils.getUserId();
		order.setCreatedBy(userId);
		order.setLastModifiedBy(userId);
		order.setUserId(userId);
		boolean checked = coeActivityOrderValidator.validatePrePay(order);
		if(checked) {
			Long id = order.getId();
			order.setClientIp(IpUtils.getIpAddr(request));
			Integer payType = order.getPayType();
			if(null == payType) {
				order.setPayType(PayConfig.PAY_TYPE_WECHAT);
			}
			CoeActivityOrderVo orderVo = coeActivityOrderWrapper.findPrePayOrder(order);
			//计算订单时间，2小时内支付有效，超时将关闭订单
			//order.setSuccess(CoeActivityOrder.SUCCESS_YES);
			model.addAttribute("entity", orderVo.getActOrder());
			model.addAttribute("respWechat", orderVo.getResponse());
			String orderVoJson = GsonBuilderUtils.toJson(orderVo);
			LOGGER.debug("##>>> orderVoJson: {} <<<##" , orderVoJson);
		}else {
			PayResponseWechat respWechat = new PayResponseWechat();
			respWechat.setRespCode(order.getRespCode());
			respWechat.setRespMsg(order.getRespMsg());
			respWechat.setSuccess(order.getSuccess());
			model.addAttribute("respWechat", respWechat);
			String respWechatJson = GsonBuilderUtils.toJson(respWechat);
			LOGGER.debug("##>>> respWechatJson: {} <<<##" , respWechatJson);
		}
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/order/prePayUI");
		LOGGER.debug("--->>> prePay.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/mergePayTime" }, name = "更新支付时间")
	public String mergePayTime(@ModelAttribute("coeActivityOrder") CoeActivityOrder coeActivityOrder,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergePayTime <<<##");

		Long userId = UserUtils.getUserId();
		coeActivityOrder.setCreatedBy(userId);
		coeActivityOrder.setLastModifiedBy(userId);
		boolean checked = coeActivityOrderValidator.validateMergePayTime(coeActivityOrder);
		if(checked) {
			coeActivityOrder = coeActivityOrderWrapper.mergePayTime(coeActivityOrder);
			coeActivityOrder.setSuccess(CoeActivityOrder.SUCCESS_YES);
			coeActivityOrder.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ORDER_ACT_PAYTIME_MERGE_SUCCESS);
			coeActivityOrder.setRespMsg(CoeActivityRespCode.SZ0099_CODE_OOD_ORDER_ACT_PAYTIME_MERGE_SUCCESS);
		}
		model.addAttribute("entity", coeActivityOrder);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/manage/mergeResult");
		LOGGER.debug("--->>> mergePayTime.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/mergeExeTime" }, name = "更新出行时间")
	public String mergeExeTime(@ModelAttribute("coeActivityOrder") CoeActivityOrder coeActivityOrder,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeExeTime <<<##");

		Long userId = UserUtils.getUserId();
		coeActivityOrder.setCreatedBy(userId);
		coeActivityOrder.setLastModifiedBy(userId);
		boolean checked = coeActivityOrderValidator.validateMergeExeTime(coeActivityOrder);
		if(checked) {
			coeActivityOrder = coeActivityOrderWrapper.mergeExeTime(coeActivityOrder);
			coeActivityOrder.setSuccess(CoeActivityOrder.SUCCESS_YES);
			coeActivityOrder.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ORDER_ACT_EXETIME_MERGE_SUCCESS);
			coeActivityOrder.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ORDER_ACT_EXETIME_MERGE_SUCCESS);
		}
		model.addAttribute("entity", coeActivityOrder);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/manage/mergeResult");
		LOGGER.debug("--->>> mergeExeTime.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/mergeFinishTime" }, name = "更新完成时间")
	public String mergeFinishTime(@ModelAttribute("coeActivityOrder") CoeActivityOrder coeActivityOrder,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeFinishTime <<<##");

		Long userId = UserUtils.getUserId();
		coeActivityOrder.setCreatedBy(userId);
		coeActivityOrder.setLastModifiedBy(userId);
		boolean checked = coeActivityOrderValidator.validateMergeFinishTime(coeActivityOrder);
		if(checked) {
			coeActivityOrder = coeActivityOrderWrapper.mergeFinishTime(coeActivityOrder);
			coeActivityOrder.setSuccess(CoeActivityOrder.SUCCESS_YES);
			coeActivityOrder.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ORDER_ACT_FINISHTIME_MERGE_SUCCESS);
			coeActivityOrder.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ORDER_ACT_FINISHTIME_MERGE_SUCCESS);
		}
		model.addAttribute("entity", coeActivityOrder);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/manage/mergeResult");
		LOGGER.debug("--->>> mergeFinishTime.resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "manage/delete" }, name = "删除活动订单")
	public String deleteOrder(@ModelAttribute("coeActivityOrder") CoeActivityOrder coeActivityOrder,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> deleteOrder <<<##");

		Long userId = UserUtils.getUserId();
		coeActivityOrder.setCreatedBy(userId);
		coeActivityOrder.setLastModifiedBy(userId);
		Long activityId = coeActivityOrder.getMainId();
		boolean checked = coeActivityOrderValidator.validateDeleteOrder(coeActivityOrder);
		if(checked) {
			coeActivityOrder = coeActivityOrderWrapper.deleteOrder(coeActivityOrder);
			coeActivityOrder.setSuccess(CoeActivityOrder.SUCCESS_YES);
			coeActivityOrder.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_DELETE_SUCCESS);
			coeActivityOrder.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_DELETE_SUCCESS);
		}
		model.addAttribute("entity", coeActivityOrder);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/manage/tagResult");
		LOGGER.debug("--->>> deleteOrder.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/orderquery" }, name = "查询订单")
	public String orderquery(@ModelAttribute("entity") CoeActivityOrder order,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> orderquery <<<##");
		
		LOGGER.debug("----------orderqueryForWechat: {}", request.getServletPath());
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/order/query");
		LOGGER.debug("--->>> orderquery.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/findPage" }, name = "分页查询我的活动订单")
	public String findPage(@ModelAttribute("entity") CoeActivityOrder order, Integer page, Integer size, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> findPage <<<##");
		Long userId = UserUtils.getUserId();
		order.setCreatedBy(userId);
		order.setLastModifiedBy(userId);
		order.setUserId(userId);
		if(page==null || page<0) {
			page=0;
		}
		if(size==null || size>110) {
			size=100;
		}
		Page<CoeActivityOrder> pageResult = null;
		if(null != userId) {
			Pageable pageable = new PageRequest(page, size, Direction.DESC, "orderTime");
			pageResult = coeActivityOrderWrapper.findPage(order, true, pageable);
		}
		LOGGER.debug("----------findPage: {}", request.getServletPath());
		model.addAttribute("pageResult", pageResult);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/order/findPage");
		LOGGER.debug("--->>> findPage.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/doOrderquery" }, name = "查询订单")
	public String doOrderquery(@ModelAttribute("entity") CoeActivityOrder order,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> orderquery <<<##");
		PaymentDto paymentDto = new PaymentDto();
		Long id = order.getId();
		String outTradeNo = String.valueOf(id);
		paymentDto.setOutTradeNo(outTradeNo);
		paymentDto.setUserId(UserUtils.getUserId());
		paymentDto.setCreatedBy(UserUtils.getUserId());
		paymentDto.setPayPlat(PayConfig.PAY_TYPE_WECHAT);
		OrderQueryResponse response = paymentWrapper.orderqueryForWechat(paymentDto);
		LOGGER.debug("----------orderqueryForWechat: {}", request.getServletPath());
		model.addAttribute("response", response);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/order/queryResult");
		LOGGER.debug("--->>> orderquery.resultPath: {} ---", resultPath);
		return resultPath;
	}
}
