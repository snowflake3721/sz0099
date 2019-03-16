package dml.sz0099.course.app.client.controller.product;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.jit4j.core.persist.page.PageRequest;
import org.jit4j.core.webmvc.config.ResourcePathConfig;
import org.jit4j.core.webmvc.util.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dml.sz0099.course.app.client.validator.product.CoeOrderValidator;
import dml.sz0099.course.app.client.wrapper.product.CoeOrderWrapper;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.order.CoeOrder;
import dml.sz0099.course.app.persist.entity.order.bo.CoeOrderBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeOrderController 控制转发
 * @author bruce yang at 2018-08-29 22:41:14
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@RequestMapping("/sz0099/ood/product")
@Controller
public class CoeOrderController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderController.class);

	@Autowired
	private CoeOrderValidator coeOrderValidator;

	@Autowired
	private CoeOrderWrapper coeOrderWrapper;

	//模板根目录
		private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
		//模块缩略名称目录
		private String MODULE_PATH_PRODUCT = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_PRODUCT.getAd());
		//模块主目录,一般与模块缩略名称相同
		private String MAPPING_PATH_PRODUCT = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_PRODUCT_PATH.currentPath());
		//模块二级目录
		private String MAPPING_SUB_PATH_PRODUCT_BASIC = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_PRODUCT_PATH_BASIC.currentPath());
	
	@PostConstruct
	public void init() {
		LOGGER.debug("========== TLE_DEFALT_FRONT: {} ========== ", TLE_DEFALT_FRONT);
		LOGGER.debug("========== MODULE_PATH_PRODUCT: {} ========== ", MODULE_PATH_PRODUCT);
		LOGGER.debug("========== MAPPING_PATH_PRODUCT: {} ========== ", MAPPING_PATH_PRODUCT);
		LOGGER.debug("========== MAPPING_SUB_PATH_PRODUCT: {} ========== ", MAPPING_SUB_PATH_PRODUCT_BASIC);
	}
	

	public CoeOrderController() {
		LOGGER.debug("##>>> init CoeOrderController <<<##");
	}

	@RequestMapping(value = { "/coeOrderUI" }, name = "访问coeOrderUI页面")
	public String accessCoeOrderUI(@ModelAttribute("coeOrderBo") CoeOrderBo coeOrderBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCoeOrderUI <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/coeOrderUI");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "order/confirm" }, name = "确认下单")
	public String confirmOrder(@ModelAttribute("entity") CoeOrderBo coeOrderBo, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCoeOrder <<<##");
		Long userId = UserUtils.getUserId();
		coeOrderBo.setUserId(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/orderConfirm");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		
		//执行订单校验，校验通过后，修改订单状态
		boolean checked = coeOrderValidator.validatePayOrder(errors, coeOrderBo);
		if(checked) {
			coeOrderBo.setLastModifiedBy(userId);
			coeOrderBo.setOrderTime(new Date());
			coeOrderWrapper.confirmOrder(coeOrderBo);
			coeOrderBo.setSuccess(CoeOrderBo.SUCCESS_YES);
			coeOrderBo.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_SUCCESS);
			coeOrderBo.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_SUCCESS);
		}
		return resultPath;
	}
	
	@RequestMapping(value = { "/order/myCoeOrderList" }, name = "访问我的订单列表")
	public String myCoeOrderList(@ModelAttribute("coeOrderBo") CoeOrderBo coeOrderBo, Errors errors, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> myCoeOrderList <<<##");
		Object beginTime = request.getParameter("beginTime");
		if(page==null) {
			page=0;
		}
		if(size==null) {
			size=2;
		}
		
		//校验查询条件，并更正
		coeOrderValidator.validateForMyOrderList(errors, coeOrderBo);
		
		Long userId = UserUtils.getUserId();
		Page<CoeOrder> pageResult = null;
		//用户尚未登录时跳至登录页面 TODO
		if(null != userId) {
			PageRequest pageable = new PageRequest(page,size,Direction.DESC, "orderTime");
			pageable.setSize(size);
			pageable.setPage(page);
			coeOrderBo.setUserId(userId);
			coeOrderBo.setCreatedBy(userId);
			pageResult = coeOrderWrapper.findPageForMyOrderList(coeOrderBo, pageable);
		}
		model.addAttribute("page", pageResult);
		model.addAttribute("coeOrderBo", coeOrderBo);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/personal/myCoeOrderList");
		LOGGER.debug("--->>> myCoeOrderList.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "order/queryPullCode" }, name = "查看提取码")
	public String queryPullCode(@ModelAttribute("entity") CoeOrder coeOrder, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryPullCode <<<##");
		Long userId = UserUtils.getUserId();
		coeOrder.setUserId(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/personal/queryPullCode");
		LOGGER.debug("--->>> queryPullCode.resultPath: {} ---", resultPath);
		
		//执行订单校验
		boolean checked = coeOrderValidator.validateForQueryPullCode(errors, coeOrder);
		if(checked) {
			//校验通过，更新提取状态
			coeOrder.setLastModifiedBy(userId);
			coeOrder.setPullTime(new Date());
			coeOrder.setPullStatus(CoeOrder.PULL_STATUS_YES);
			coeOrderWrapper.mergeEntityForPull(coeOrder);
			Long id=coeOrder.getId();
			coeOrder=coeOrderWrapper.findById(id, true);
			model.addAttribute("entity", coeOrder);
			coeOrder.setSuccess(CoeOrderBo.SUCCESS_YES);
			coeOrder.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_QUERY_PULLCODE_PASSED);
			coeOrder.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_QUERY_PULLCODE_PASSED);
		}
		return resultPath;
	}
	
	
	@RequestMapping(value = { "order/modifyEmail" }, name = "修改订单email")
	public String modifyEmail(@ModelAttribute("entity") CoeOrderBo coeOrderBo, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryPullCode <<<##");
		Long userId = UserUtils.getUserId();
		coeOrderBo.setUserId(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/personal/modifyEmail");
		LOGGER.debug("--->>> queryPullCode.resultPath: {} ---", resultPath);
		
		//执行订单校验
		boolean checked = coeOrderValidator.validateForOrderEmail(errors, coeOrderBo, false);
		if(checked) {
			//校验通过
			//coeOrderBo.setLastModifiedBy(userId);
			//coeOrderBo.setPullTime(new Date());
			//coeOrderBo.setPullStatus(CoeOrder.PULL_STATUS_YES);
			//coeOrderWrapper.mergeEntityForPull(coeOrderBo);
			coeOrderBo.setSuccess(CoeOrderBo.SUCCESS_YES);
			coeOrderBo.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_MODIFY_EMAIL_PASSED);
			coeOrderBo.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_MODIFY_EMAIL_PASSED);
		}
		return resultPath;
	}
	
	@RequestMapping(value = { "order/doModifyEmail" }, name = "执行修改订单email")
	public String doModifyEmail(@ModelAttribute("entity") CoeOrderBo coeOrderBo, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> doModifyEmail <<<##");
		Long userId = UserUtils.getUserId();
		coeOrderBo.setUserId(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/commonResult");
		LOGGER.debug("--->>> doModifyEmail.resultPath: {} ---", resultPath);
		
		//执行订单校验
		boolean checked = coeOrderValidator.validateForModifyEmail(errors, coeOrderBo);
		if(checked) {
			//校验通过
			coeOrderBo.setLastModifiedBy(userId);
			coeOrderWrapper.mergeEntityForEmail(coeOrderBo);
			coeOrderBo.setSuccess(CoeOrderBo.SUCCESS_YES);
			coeOrderBo.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_MODIFY_EMAIL_SUCCESS);
			coeOrderBo.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_MODIFY_EMAIL_SUCCESS);
		}
		return resultPath;
	}
	
	
	
	@RequestMapping(value = { "/order/manage/manageOrderList" }, name = "管理订单")
	public String manageOrderList(@ModelAttribute("coeOrderBo") CoeOrderBo coeOrderBo, Errors errors, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> manageOrderList <<<##");
		Object beginTime = request.getParameter("beginTime");
		if(page==null) {
			page=0;
		}
		if(size==null) {
			size=2;
		}
		
		//校验查询条件，并更正
		coeOrderValidator.validateForMyOrderList(errors, coeOrderBo);
		
		Long userId = UserUtils.getUserId();
		Page<CoeOrder> pageResult = null;
		//用户尚未登录时跳至登录页面 TODO
		if(null != userId) {
			PageRequest pageable = new PageRequest(page,size,Direction.DESC, "orderTime");
			pageable.setSize(size);
			pageable.setPage(page);
			//coeOrderBo.setUserId(userId);
			//coeOrderBo.setCreatedBy(userId);
			coeOrderBo.setOwnerId(userId);//查询自己的订单
			pageResult = coeOrderWrapper.findPageForOwnerOrderList(coeOrderBo, pageable);
		}
		model.addAttribute("page", pageResult);
		model.addAttribute("coeOrderBo", coeOrderBo);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/manage/manageOrderList");
		LOGGER.debug("--->>> manageOrderList.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	/**
	 * 进行订单处理
	 * @param coeOrder
	 * @param errors
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/order/manage/mergeForInprocess" }, name = "处理订单")
	public String mergeForInprocess(@ModelAttribute("coeOrder") CoeOrder coeOrder, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForInprocess <<<##");
		//校验查询条件，并更正
		coeOrderValidator.validateForInprocess(errors, coeOrder);
		
		Long userId = UserUtils.getUserId();
		//用户尚未登录时跳至登录页面 TODO
		if(null != userId) {
			coeOrder.setUserId(userId);
			coeOrder.setCreatedBy(userId);
			coeOrder.setOwnerId(userId);//查询自己的订单
			coeOrder.setLastModifiedBy(userId);
			coeOrder.setResolverId(userId);
			coeOrder = coeOrderWrapper.mergeForInprocess(coeOrder);
			coeOrder.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_INPROCESS_SUCCESS);
			coeOrder.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_INPROCESS_SUCCESS);
		}
		model.addAttribute("entity", coeOrder);
		model.addAttribute("coeOrderBo", coeOrder);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/manage/commonResult");
		LOGGER.debug("--->>> mergeForInprocess.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	/**
	 * 进行订单处理
	 * @param coeOrder
	 * @param errors
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/order/manage/mergeForSent" }, name = "订单发货")
	public String mergeForSent(@ModelAttribute("coeOrder") CoeOrder coeOrder, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForSent <<<##");
		//校验查询条件，并更正
		coeOrderValidator.validateForSent(errors, coeOrder);
		
		Long userId = UserUtils.getUserId();
		//用户尚未登录时跳至登录页面 TODO
		if(null != userId) {
			coeOrder.setUserId(userId);
			coeOrder.setCreatedBy(userId);
			coeOrder.setOwnerId(userId);//查询自己的订单
			coeOrder.setLastModifiedBy(userId);
			coeOrder.setResolverId(userId);
			coeOrder = coeOrderWrapper.mergeForSent(coeOrder);
			coeOrder.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_SENT_SUCCESS);
			coeOrder.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_SENT_SUCCESS);
		}
		model.addAttribute("entity", coeOrder);
		model.addAttribute("coeOrderBo", coeOrder);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/manage/commonResult");
		LOGGER.debug("--->>> mergeForSent.resultPath: {} ---", resultPath);
		return resultPath;
	}
	

}
