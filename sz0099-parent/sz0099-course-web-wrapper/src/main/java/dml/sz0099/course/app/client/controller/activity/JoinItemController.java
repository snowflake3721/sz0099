package dml.sz0099.course.app.client.controller.activity;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.jit4j.core.webmvc.config.ResourcePathConfig;
import org.jit4j.core.webmvc.util.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dml.sz0099.course.app.client.validator.activity.JoinItemValidator;
import dml.sz0099.course.app.client.wrapper.activity.JoinItemWrapper;
import dml.sz0099.course.app.module.define.CoeActivityRespCode;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.persist.entity.activity.JoinItem;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * JoinItemController 控制转发
 * @author bruce yang at 2018-08-29 22:41:14
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@RequestMapping("/sz0099/ood/activity/joinItem")
@Controller
public class JoinItemController {

	private static final Logger LOGGER = LoggerFactory.getLogger(JoinItemController.class);

	@Autowired
	private JoinItemValidator joinItemValidatorValidator;

	@Autowired
	private JoinItemWrapper joinItemWrapper;
	
	
	

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
	
	public JoinItemController() {
		LOGGER.debug("##>>> init JoinItemController <<<##");
	}

	@RequestMapping(value = { "manage/add" }, name = "添加集合时间")
	public String addJoinItem(@ModelAttribute("joinItem") JoinItem joinItem,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> addJoinItem <<<##");

		Long userId = UserUtils.getUserId();
		joinItem.setCreatedBy(userId);
		joinItem.setLastModifiedBy(userId);
		Long activityId = joinItem.getMainId();
		boolean checked = joinItemValidatorValidator.validateAddJoinItem(joinItem);
		if(checked) {
			joinItem = joinItemWrapper.addJoinItem(joinItem);
			joinItem.setSuccess(JoinItem.SUCCESS_YES);
			joinItem.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_JOINITEM_ADD_SUCCESS);
			joinItem.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_JOINITEM_ADD_SUCCESS);
		}
		model.addAttribute("entity", joinItem);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/manage/joinItemAddUI");
		LOGGER.debug("--->>> addJoinItem.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/delete" }, name = "删除集合时间")
	public String deleteJoinItem(@ModelAttribute("joinItem") JoinItem joinItem,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> deleteJoinItem <<<##");

		Long userId = UserUtils.getUserId();
		joinItem.setCreatedBy(userId);
		joinItem.setLastModifiedBy(userId);
		Long activityId = joinItem.getMainId();
		boolean checked = joinItemValidatorValidator.validateDeleteJoinItem(joinItem);
		if(checked) {
			joinItem = joinItemWrapper.deleteJoinItem(joinItem);
			joinItem.setSuccess(JoinItem.SUCCESS_YES);
			joinItem.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_JOINITEM_DELETE_SUCCESS);
			joinItem.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_JOINITEM_DELETE_SUCCESS);
		}
		model.addAttribute("entity", joinItem);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/manage/mergeResult");
		LOGGER.debug("--->>> deleteJoinItem.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/mergeJoinTime" }, name = "更新集合时间")
	public String mergeJoinTime(@ModelAttribute("joinItem") JoinItem joinItem,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeJoinTime <<<##");

		Long userId = UserUtils.getUserId();
		joinItem.setCreatedBy(userId);
		joinItem.setLastModifiedBy(userId);
		boolean checked = joinItemValidatorValidator.validateMergeJoinTime(joinItem);
		if(checked) {
			joinItem = joinItemWrapper.mergeJoinTime(joinItem);
			joinItem.setSuccess(JoinItem.SUCCESS_YES);
			joinItem.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_JOINITEM_JOINTIME_MERGE_SUCCESS);
			joinItem.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_JOINITEM_JOINTIME_MERGE_SUCCESS);
		}
		model.addAttribute("entity", joinItem);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/manage/mergeResult");
		LOGGER.debug("--->>> mergeJoinTime.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/mergePlace" }, name = "更新集合地点")
	public String mergePlace(@ModelAttribute("joinItem") JoinItem joinItem,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergePlace <<<##");

		Long userId = UserUtils.getUserId();
		joinItem.setCreatedBy(userId);
		joinItem.setLastModifiedBy(userId);
		boolean checked = joinItemValidatorValidator.validateMergePlace(joinItem);
		if(checked) {
			joinItem = joinItemWrapper.mergePlace(joinItem);
			joinItem.setSuccess(JoinItem.SUCCESS_YES);
			joinItem.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_JOINITEM_PLACE_MERGE_SUCCESS);
			joinItem.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_JOINITEM_PLACE_MERGE_SUCCESS);
		}
		model.addAttribute("entity", joinItem);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/manage/mergeResult");
		LOGGER.debug("--->>> mergePlace.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/mergeDescription" }, name = "更新集合补充说明")
	public String mergeDescription(@ModelAttribute("joinItem") JoinItem joinItem,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeDescription <<<##");

		Long userId = UserUtils.getUserId();
		joinItem.setCreatedBy(userId);
		joinItem.setLastModifiedBy(userId);
		boolean checked = joinItemValidatorValidator.validateMergeDescription(joinItem);
		if(checked) {
			joinItem = joinItemWrapper.mergeDescription(joinItem);
			joinItem.setSuccess(JoinItem.SUCCESS_YES);
			joinItem.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_JOINITEM_DESCRIPTION_MERGE_SUCCESS);
			joinItem.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_JOINITEM_DESCRIPTION_MERGE_SUCCESS);
		}
		model.addAttribute("entity", joinItem);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/manage/mergeResult");
		LOGGER.debug("--->>> mergeDescription.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
}
