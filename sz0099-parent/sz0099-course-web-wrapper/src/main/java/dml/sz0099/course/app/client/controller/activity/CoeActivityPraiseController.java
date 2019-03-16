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

import dml.sz0099.course.app.client.validator.activity.CoeActivityPraiseValidator;
import dml.sz0099.course.app.client.wrapper.activity.CoeActivityPraiseWrapper;
import dml.sz0099.course.app.module.define.CoeActivityRespCode;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPraise;
import dml.sz0099.course.app.persist.entity.activity.bo.CoeActivityPraiseBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeActivityPraiseController 控制转发
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
public class CoeActivityPraiseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityPraiseController.class);

	@Autowired
	private CoeActivityPraiseValidator coeActivityPraiseValidator;

	@Autowired
	private CoeActivityPraiseWrapper coeActivityPraiseWrapper;

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
	
	
	public CoeActivityPraiseController() {
		LOGGER.debug("##>>> init CoeActivityPraiseController <<<##");
	}

	@RequestMapping(value = { "/coeActivityPraiseUI" }, name = "访问coeActivityPraiseUI页面")
	public String accessCoeActivityPraiseUI(@ModelAttribute("coeActivityPraiseBo") CoeActivityPraiseBo coeActivityPraiseBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCoeActivityPraiseUI <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/coeActivityPraiseUI");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "/coeActivityPraise/sub" }, name = "访问coeActivityPraisesub页面")
	public String accessCoeActivityPraiseSub(@ModelAttribute("coeActivityPraiseBo") CoeActivityPraiseBo coeActivityPraiseBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCoeActivityPraise <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_ARTICLE_BASIC,"/coeActivityPraise");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/praise" }, name = "点赞文章预备")
	public String mergeForPraise(@ModelAttribute("entity")  CoeActivityPraise activityPraise, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForPraise <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE, "/praisePre");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			activityPraise.setUserId(userId);
			activityPraise.setCreatedBy(userId);
			activityPraise.setLastModifiedBy(userId);
			boolean checked = coeActivityPraiseValidator.validateForPraise(activityPraise);
			if(checked) {
				activityPraise.setSuccess(CoeActivityPraise.SUCCESS_YES);
				//activityPraise.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_MERGE_PRAISE_SUCCESS);
				//activityPraise.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_MERGE_PRAISE_SUCCESS);
			}
			model.addAttribute("entity", activityPraise);
		}
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		LOGGER.debug("--->>>mergeForPraise.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/doPraise" }, name = "点赞文章")
	public String doMergeForPraise(@ModelAttribute("entity")  CoeActivityPraise activityPraise, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> doMergeForPraise <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			activityPraise.setUserId(userId);
			activityPraise.setCreatedBy(userId);
			activityPraise.setLastModifiedBy(userId);
			boolean checked = coeActivityPraiseValidator.validateForDoPraise(activityPraise);
			if(checked) {
				activityPraise = coeActivityPraiseWrapper.praise(activityPraise);
				activityPraise.setSuccess(CoeActivityPraise.SUCCESS_YES);
				activityPraise.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_MERGE_PRAISE_SUCCESS);
				activityPraise.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_MERGE_PRAISE_SUCCESS);
			}
			model.addAttribute("entity", activityPraise);
		}
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		LOGGER.debug("--->>>doMergeForPraise.resultPath: {} ---", resultPath);
		return resultPath;
	}

}
