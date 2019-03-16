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

import dml.sz0099.course.app.client.validator.activity.CoeActivityTimeValidator;
import dml.sz0099.course.app.client.wrapper.activity.CoeActivityTimeWrapper;
import dml.sz0099.course.app.module.define.CoeActivityRespCode;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTime;
import dml.sz0099.course.app.persist.entity.paragraph.ParagProduct;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeActivityTimeController 控制转发
 * @author bruce yang at 2018-08-29 22:41:14
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@RequestMapping("/sz0099/ood/activity/time")
@Controller
public class CoeActivityTimeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityTimeController.class);

	@Autowired
	private CoeActivityTimeValidator coeActivityTimeValidator;

	@Autowired
	private CoeActivityTimeWrapper coeActivityTimeWrapper;
	
	
	

	//模板根目录
	private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
	//模块缩略名称目录
	private String MODULE_PATH_ARTICLE = ResourcePathConfig.getModulesPath(CourseAppModule.APP_OOD_ACTIVITY.getAd());
	//模块主目录,一般与模块缩略名称相同
	private String MAPPING_PATH_ARTICLE = ResourcePathConfig.getModulesPath(CourseAppModule.APP_OOD_ACTIVITY_PATH.currentPath());
	//模块二级目录
	private String MAPPING_SUB_PATH_ARTICLE_BASIC = ResourcePathConfig.getModulesPath(CourseAppModule.APP_OOD_ACTIVITY_PATH_BASIC.currentPath());
	
	@PostConstruct
	public void init() {
		LOGGER.debug("========== TLE_DEFALT_FRONT: {} ========== ", TLE_DEFALT_FRONT);
		LOGGER.debug("========== MODULE_PATH_ARTICLE: {} ========== ", MODULE_PATH_ARTICLE);
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_ARTICLE_BASIC);
	}
	
	public CoeActivityTimeController() {
		LOGGER.debug("##>>> init CoeActivityTimeController <<<##");
	}

	@RequestMapping(value = { "manage/add" }, name = "添加时间")
	public String addActivityTime(@ModelAttribute("coeActivityTime") CoeActivityTime coeActivityTime,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> addActivityTime <<<##");

		Long userId = UserUtils.getUserId();
		coeActivityTime.setCreatedBy(userId);
		coeActivityTime.setLastModifiedBy(userId);
		Long activityId = coeActivityTime.getMainId();
		boolean checked = coeActivityTimeValidator.validateAddActivityTime(coeActivityTime);
		if(checked) {
			coeActivityTime = coeActivityTimeWrapper.addTime(coeActivityTime);
			coeActivityTime.setSuccess(CoeActivityTime.SUCCESS_YES);
			coeActivityTime.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_TIME_SAVED_SUCCESS);
			coeActivityTime.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_TIME_SAVED_SUCCESS);
		}
		model.addAttribute("entity", coeActivityTime);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/manage/mergeResult");
		LOGGER.debug("--->>> addActivityTime.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/mergeBeginTime" }, name = "更新开始时间")
	public String mergeBeginTime(@ModelAttribute("coeActivityTime") CoeActivityTime coeActivityTime,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeBeginTime <<<##");

		Long userId = UserUtils.getUserId();
		coeActivityTime.setCreatedBy(userId);
		coeActivityTime.setLastModifiedBy(userId);
		boolean checked = coeActivityTimeValidator.validateMergeBeginTime(coeActivityTime);
		if(checked) {
			coeActivityTime = coeActivityTimeWrapper.mergeBeginTime(coeActivityTime);
			coeActivityTime.setSuccess(CoeActivityTime.SUCCESS_YES);
			coeActivityTime.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_TIME_BEGINTIME_MERGE_SUCCESS);
			coeActivityTime.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_TIME_BEGINTIME_MERGE_SUCCESS);
		}
		model.addAttribute("entity", coeActivityTime);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/manage/mergeResult");
		LOGGER.debug("--->>> mergeBeginTime.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/mergeEndTime" }, name = "更新开始时间")
	public String mergeEndTime(@ModelAttribute("coeActivityTime") CoeActivityTime coeActivityTime,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeEndTime <<<##");

		Long userId = UserUtils.getUserId();
		coeActivityTime.setCreatedBy(userId);
		coeActivityTime.setLastModifiedBy(userId);
		boolean checked = coeActivityTimeValidator.validateMergeEndTime(coeActivityTime);
		if(checked) {
			coeActivityTime = coeActivityTimeWrapper.mergeEndTime(coeActivityTime);
			coeActivityTime.setSuccess(CoeActivityTime.SUCCESS_YES);
			coeActivityTime.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_TIME_ENDTIME_MERGE_SUCCESS);
			coeActivityTime.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_TIME_ENDTIME_MERGE_SUCCESS);
		}
		model.addAttribute("entity", coeActivityTime);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/manage/mergeResult");
		LOGGER.debug("--->>> mergeEndTime.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/mergeOffTime" }, name = "更新截止报名时间")
	public String mergeOffTime(@ModelAttribute("coeActivityTime") CoeActivityTime coeActivityTime,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeOffTime <<<##");

		Long userId = UserUtils.getUserId();
		coeActivityTime.setCreatedBy(userId);
		coeActivityTime.setLastModifiedBy(userId);
		boolean checked = coeActivityTimeValidator.validateMergeOffTime(coeActivityTime);
		if(checked) {
			coeActivityTime = coeActivityTimeWrapper.mergeOffTime(coeActivityTime);
			coeActivityTime.setSuccess(CoeActivityTime.SUCCESS_YES);
			coeActivityTime.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_TIME_OFFTIME_MERGE_SUCCESS);
			coeActivityTime.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_TIME_OFFTIME_MERGE_SUCCESS);
		}
		model.addAttribute("entity", coeActivityTime);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/manage/mergeResult");
		LOGGER.debug("--->>> mergeOffTime.resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "manage/delete" }, name = "删除时间")
	public String deleteActivityTime(@ModelAttribute("coeActivityTime") CoeActivityTime coeActivityTime,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> deleteActivityTime <<<##");

		Long userId = UserUtils.getUserId();
		coeActivityTime.setCreatedBy(userId);
		coeActivityTime.setLastModifiedBy(userId);
		Long activityId = coeActivityTime.getMainId();
		boolean checked = coeActivityTimeValidator.validateDeleteActivityTime(coeActivityTime);
		if(checked) {
			coeActivityTime = coeActivityTimeWrapper.deleteTime(coeActivityTime);
			coeActivityTime.setSuccess(CoeActivityTime.SUCCESS_YES);
			coeActivityTime.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_TIME_DELETE_SUCCESS);
			coeActivityTime.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_TIME_DELETE_SUCCESS);
		}
		model.addAttribute("entity", coeActivityTime);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/manage/tagResult");
		LOGGER.debug("--->>> deleteActivityTime.resultPath: {} ---", resultPath);
		return resultPath;
	}
}
