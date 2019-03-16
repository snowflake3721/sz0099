package dml.sz0099.course.app.client.controller.activity;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.jit4j.core.persist.page.PageRequest;
import org.jit4j.core.webmvc.config.ResourcePathConfig;
import org.jit4j.core.webmvc.util.UserUtils;
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

import dml.sz0099.course.app.client.validator.activity.CoeActivityUserValidator;
import dml.sz0099.course.app.client.wrapper.activity.CoeActivityUserWrapper;
import dml.sz0099.course.app.client.wrapper.activity.CoeActivityWrapper;
import dml.sz0099.course.app.module.define.CoeActivityRespCode;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityUser;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeActivityUserController 控制转发
 * @author bruce yang at 2018-08-29 22:41:14
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@RequestMapping("/sz0099/ood/activity/order/user")
@Controller
public class CoeActivityUserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityUserController.class);

	@Autowired
	private CoeActivityUserValidator coeActivityUserValidator;

	@Autowired
	private CoeActivityUserWrapper coeActivityUserWrapper;
	
	@Autowired
	private CoeActivityWrapper coeActivityWrapper;
	
	
	
	
	

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
	
	public CoeActivityUserController() {
		LOGGER.debug("##>>> init CoeActivityUserController <<<##");
	}

	@RequestMapping(value = { "manage/add" }, name = "添加用户")
	public String addUser(@ModelAttribute("entity") CoeActivityUser coeActivityUser,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> addUser <<<##");

		Long userId = UserUtils.getUserId();
		coeActivityUser.setCreatedBy(userId);
		coeActivityUser.setLastModifiedBy(userId);
		coeActivityUser.setUserId(userId);
		Long activityId = coeActivityUser.getMainId();
		boolean checked = coeActivityUserValidator.validateAddUser(coeActivityUser);
		if(checked) {
			coeActivityUser = coeActivityUserWrapper.addUser(coeActivityUser);
			coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_YES);
			coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ORDER_ACT_USER_ADD_SUCCESS);
			coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ORDER_ACT_USER_ADD_SUCCESS);
		}
		model.addAttribute("entity", coeActivityUser);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/user/userAddUI");
		LOGGER.debug("--->>> addUser.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/delete" }, name = "删除用户")
	public String deleteUser(@ModelAttribute("entity") CoeActivityUser coeActivityUser,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> deleteUser <<<##");

		Long userId = UserUtils.getUserId();
		coeActivityUser.setCreatedBy(userId);
		coeActivityUser.setLastModifiedBy(userId);
		Long activityId = coeActivityUser.getMainId();
		boolean checked = coeActivityUserValidator.validateDeleteUser(coeActivityUser);
		if(checked) {
			coeActivityUser = coeActivityUserWrapper.deleteUser(coeActivityUser);
			coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_YES);
			coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ORDER_ACT_USER_DELETE_SUCCESS);
			coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ORDER_ACT_USER_DELETE_SUCCESS);
		}
		model.addAttribute("entity", coeActivityUser);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/manage/mergeResult");
		LOGGER.debug("--->>> deleteUser.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/mergeRealname" }, name = "更新真实姓名")
	public String mergeRealname(@ModelAttribute("coeActivityUser") CoeActivityUser coeActivityUser,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeRealname <<<##");

		Long userId = UserUtils.getUserId();
		coeActivityUser.setCreatedBy(userId);
		coeActivityUser.setLastModifiedBy(userId);
		boolean checked = coeActivityUserValidator.validateMergeRealname(coeActivityUser);
		if(checked) {
			coeActivityUser = coeActivityUserWrapper.mergeRealname(coeActivityUser);
			coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_YES);
			coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ORDER_ACT_USER_REALNAME_MERGE_SUCCESS);
			coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ORDER_ACT_USER_REALNAME_MERGE_SUCCESS);
		}
		model.addAttribute("entity", coeActivityUser);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/manage/mergeResult");
		LOGGER.debug("--->>> mergeRealname.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/mergeIdentity" }, name = "更新身份证号")
	public String mergeIdentity(@ModelAttribute("coeActivityUser") CoeActivityUser coeActivityUser,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeIdentity <<<##");

		Long userId = UserUtils.getUserId();
		coeActivityUser.setCreatedBy(userId);
		coeActivityUser.setLastModifiedBy(userId);
		boolean checked = coeActivityUserValidator.validateMergeIdentity(coeActivityUser);
		if(checked) {
			coeActivityUser = coeActivityUserWrapper.mergeIdentity(coeActivityUser);
			coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_YES);
			coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ORDER_ACT_USER_IDENTITY_MERGE_SUCCESS);
			coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ORDER_ACT_USER_IDENTITY_MERGE_SUCCESS);
		}
		model.addAttribute("entity", coeActivityUser);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/manage/mergeResult");
		LOGGER.debug("--->>> mergeIdentity.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/mergeMobile" }, name = "更新手机号")
	public String mergeMobile(@ModelAttribute("coeActivityUser") CoeActivityUser coeActivityUser,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeMobile <<<##");

		Long userId = UserUtils.getUserId();
		coeActivityUser.setCreatedBy(userId);
		coeActivityUser.setLastModifiedBy(userId);
		boolean checked = coeActivityUserValidator.validateMergeMobile(coeActivityUser);
		if(checked) {
			coeActivityUser = coeActivityUserWrapper.mergeMobile(coeActivityUser);
			coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_YES);
			coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ORDER_ACT_USER_MOBILE_MERGE_SUCCESS);
			coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ORDER_ACT_USER_MOBILE_MERGE_SUCCESS);
		}
		model.addAttribute("entity", coeActivityUser);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/manage/mergeResult");
		LOGGER.debug("--->>> mergeMobile.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/findUserPage" }, name = "分页查询报名者")
	public String findUserPage(@ModelAttribute("condition") CoeActivityUser user, Integer page, Integer size, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> findUserPage <<<##");
		Long userId = UserUtils.getUserId();
		user.setCreatedBy(userId);
		user.setLastModifiedBy(userId);
		user.setUserId(userId);
		if(page==null || page<0) {
			page=0;
		}
		if(size==null || size>110) {
			size=100;
		}
		Page<CoeActivityUser> pageResult = null;
		if(null != userId) {
			Pageable pageable = new PageRequest(page, size, Direction.DESC, "aid");
			pageResult = coeActivityUserWrapper.findPageByMainId(user, pageable);
		}
		LOGGER.debug("----------findUserPage: {}", request.getServletPath());
		model.addAttribute("entityPage", pageResult);
		
		Long mainId = user.getMainId();
		CoeActivity activity = coeActivityWrapper.findByIdWithTimeAndFee(mainId);
		model.addAttribute("activity", activity);
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/user/findUserPage");
		LOGGER.debug("--->>> findUserPage.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
}
