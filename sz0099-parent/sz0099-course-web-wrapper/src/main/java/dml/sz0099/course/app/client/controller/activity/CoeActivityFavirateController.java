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
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dml.sz0099.course.app.client.validator.activity.CoeActivityFavirateValidator;
import dml.sz0099.course.app.client.wrapper.activity.CoeActivityFavirateWrapper;
import dml.sz0099.course.app.module.define.CoeActivityRespCode;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityFavirate;
import dml.sz0099.course.app.persist.entity.activity.bo.CoeActivityFavirateBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeActivityFavirateController 控制转发
 * @author bruce yang at 2018-08-29 22:41:14
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@RequestMapping("/sz0099/ood/activity")
@Controller
public class CoeActivityFavirateController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityFavirateController.class);

	@Autowired
	private CoeActivityFavirateValidator coeActivityFavirateValidator;

	@Autowired
	private CoeActivityFavirateWrapper coeActivityFavirateWrapper;

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
	
	
	public CoeActivityFavirateController() {
		LOGGER.debug("##>>> init CoeActivityFavirateController <<<##");
	}

	@RequestMapping(value = { "/coeActivityFavirateUI" }, name = "访问coeActivityFavirateUI页面")
	public String accessCoeActivityFavirateUI(@ModelAttribute("coeActivityFavirateBo") CoeActivityFavirateBo coeActivityFavirateBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCoeActivityFavirateUI <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/coeActivityFavirateUI");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "/coeActivityFavirate/sub" }, name = "访问coeActivityFaviratesub页面")
	public String accessCoeActivityFavirateSub(@ModelAttribute("coeActivityFavirateBo") CoeActivityFavirateBo coeActivityFavirateBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCoeActivityFavirate <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_ARTICLE_BASIC,"/coeActivityFavirate");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/favirate" }, name = "收藏文章")
	public String mergeForFavirate(@ModelAttribute("entity")  CoeActivityFavirate activityFavirate, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForFavirate <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			activityFavirate.setUserId(userId);
			activityFavirate.setCreatedBy(userId);
			activityFavirate.setLastModifiedBy(userId);
			boolean checked = coeActivityFavirateValidator.validateFavirate(activityFavirate);
			if(checked) {
				activityFavirate = coeActivityFavirateWrapper.favirate(activityFavirate);
				activityFavirate.setSuccess(CoeActivityFavirate.SUCCESS_YES);
				activityFavirate.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_MERGE_FAVIRATE_SUCCESS);
				activityFavirate.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_MERGE_FAVIRATE_SUCCESS);
			}
			model.addAttribute("entity", activityFavirate);
		}
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		LOGGER.debug("--->>>mergeForFavirate.resultPath: {} ---", resultPath);
		return resultPath;
	}

}
