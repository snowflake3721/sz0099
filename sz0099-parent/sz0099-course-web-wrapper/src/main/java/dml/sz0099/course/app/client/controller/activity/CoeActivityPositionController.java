package dml.sz0099.course.app.client.controller.activity;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.jit4j.app.persist.entity.auth.User;
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

import dml.sz0099.course.app.client.validator.activity.CoeActivityPositionValidator;
import dml.sz0099.course.app.client.wrapper.activity.CoeActivityPositionWrapper;
import dml.sz0099.course.app.module.define.CoeActivityRespCode;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPosition;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeActivityPositionController 控制转发
 * @author bruce yang at 2018-08-29 22:41:14
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@RequestMapping("/sz0099/ood/position/activity")
@Controller
public class CoeActivityPositionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityPositionController.class);

	@Autowired
	private CoeActivityPositionValidator coeActivityPositionValidator;

	@Autowired
	private CoeActivityPositionWrapper coeActivityPositionWrapper;

	//模板根目录
		private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
		//模块缩略名称目录
		private String MODULE_PATH_POSITION = ResourcePathConfig.getModulesPath(CourseAppModule.APP_SZ0099_POSITION.getAd());
		//模块主目录,一般与模块缩略名称相同
		private String MAPPING_PATH_POSITION = ResourcePathConfig.getModulesPath(CourseAppModule.APP_SZ0099_POSITION_PATH.currentPath());
		//模块二级目录
		private String MAPPING_SUB_PATH_POSITION_BASIC = ResourcePathConfig.getModulesPath(CourseAppModule.APP_SZ0099_POSITION_PATH_BASIC.currentPath());
		
		@PostConstruct
		public void init() {
			LOGGER.debug("========== TLE_DEFALT_FRONT: {} ========== ", TLE_DEFALT_FRONT);
			LOGGER.debug("========== MODULE_PATH_POSITION: {} ========== ", MODULE_PATH_POSITION);
			LOGGER.debug("========== MAPPING_PATH_POSITION: {} ========== ", MAPPING_PATH_POSITION);
			LOGGER.debug("========== MAPPING_SUB_PATH_POSITION_BASIC: {} ========== ", MAPPING_SUB_PATH_POSITION_BASIC);
		}
	
	
	public CoeActivityPositionController() {
		LOGGER.debug("##>>> init CoeActivityPositionController <<<##");
	}

	
	@RequestMapping(value = { "/manage/bind" }, name = "位置绑定活动")
	public String mergeForPosition(@ModelAttribute("entity")  CoeActivityPosition activityPosition, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForPosition <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_POSITION, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			activityPosition.setUserId(userId);
			activityPosition.setOwnerId(userId);
			activityPosition.setCreatedBy(userId);
			activityPosition.setLastModifiedBy(userId);
			boolean checked = coeActivityPositionValidator.validatePosition(activityPosition);
			if(checked) {
				activityPosition = coeActivityPositionWrapper.bindPosition(activityPosition);
				activityPosition.setSuccess(CoeActivityPosition.SUCCESS_YES);
				activityPosition.setRespCode(CoeActivityRespCode.SZ0099_CODE_COURSE_POSITION_MERGE_BIND_SUCCESS);
				activityPosition.setRespMsg(CoeActivityRespCode.SZ0099_MSG_COURSE_POSITION_MERGE_BIND_SUCCESS);
			}
			model.addAttribute("entity", activityPosition);
		}
		LOGGER.debug("========== MAPPING_PATH_POSITION: {} ========== ", MAPPING_PATH_POSITION);
		LOGGER.debug("--->>>mergeForPosition.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/list" }, name = "查询位置列表")
	public String queryPositionList(@ModelAttribute("entity")  CoeActivityPosition activityPosition, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryPositionList <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_POSITION, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			activityPosition.setUserId(userId);
			activityPosition.setOwnerId(userId);
			activityPosition.setCreatedBy(userId);
			activityPosition.setLastModifiedBy(userId);
			boolean checked = coeActivityPositionValidator.validatePosition(activityPosition);
			if(checked) {
				activityPosition = coeActivityPositionWrapper.bindPosition(activityPosition);
				activityPosition.setSuccess(CoeActivityPosition.SUCCESS_YES);
				activityPosition.setRespCode(CoeActivityRespCode.SZ0099_CODE_COURSE_POSITION_QUERY_LIST_SUCCESS);
				activityPosition.setRespMsg(CoeActivityRespCode.SZ0099_MSG_COURSE_POSITION_QUERY_LIST_SUCCESS);
			}
			model.addAttribute("entity", activityPosition);
		}
		LOGGER.debug("========== MAPPING_PATH_POSITION: {} ========== ", MAPPING_PATH_POSITION);
		LOGGER.debug("--->>>queryPositionList.resultPath: {} ---", resultPath);
		return resultPath;
	}

}
