package dml.sz0099.course.app.client.controller.profession;

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

import dml.sz0099.course.app.client.validator.profession.ProfessionPraiseValidator;
import dml.sz0099.course.app.client.wrapper.profession.ProfessionPraiseWrapper;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPraise;
import dml.sz0099.course.app.persist.entity.profession.bo.ProfessionPraiseBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ProfessionPraiseController 控制转发
 * @author bruce yang at 2018-08-29 22:41:14
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@RequestMapping("/sz0099/ood/personal")
@Controller
public class ProfessionPraiseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionPraiseController.class);

	@Autowired
	private ProfessionPraiseValidator professionPraiseValidator;

	@Autowired
	private ProfessionPraiseWrapper professionPraiseWrapper;

	// 模板根目录
		private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
		// 模块缩略名称目录
		private String MODULE_PATH_PERSONAL = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_PERSONAL.getAd());
		// 模块主目录,一般与模块缩略名称相同
		private String MAPPING_PATH_PERSONAL = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_PERSONAL_PATH.currentPath());
		// 模块二级目录
		private String MAPPING_SUB_PATH_PERSONAL_BASIC = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_PERSONAL_PATH_BASIC.currentPath());

		@PostConstruct
		public void init() {
			LOGGER.debug("========== TLE_DEFALT_FRONT: {} ========== ", TLE_DEFALT_FRONT);
			LOGGER.debug("========== MODULE_PATH_PERSONAL: {} ========== ", MODULE_PATH_PERSONAL);
			LOGGER.debug("========== MAPPING_PATH_PERSONAL: {} ========== ", MAPPING_PATH_PERSONAL);
			LOGGER.debug("========== MAPPING_SUB_PATH_PARAGRAPH: {} ========== ", MAPPING_SUB_PATH_PERSONAL_BASIC);
		}
	
	
	public ProfessionPraiseController() {
		LOGGER.debug("##>>> init ProfessionPraiseController <<<##");
	}

	
	@RequestMapping(value = { "profession/praise" }, name = "点赞技能预备")
	public String mergeForPraise(@ModelAttribute("entity")  ProfessionPraise professionPraise, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForPraise <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL, "/praisePre");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			professionPraise.setUserId(userId);
			professionPraise.setCreatedBy(userId);
			professionPraise.setLastModifiedBy(userId);
			boolean checked = professionPraiseValidator.validateForPraise(professionPraise);
			if(checked) {
				professionPraise.setSuccess(ProfessionPraise.SUCCESS_YES);
				//professionPraise.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_MERGE_PRAISE_SUCCESS);
				//professionPraise.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_MERGE_PRAISE_SUCCESS);
			}
			model.addAttribute("entity", professionPraise);
		}
		LOGGER.debug("========== MAPPING_PATH_PERSONAL: {} ========== ", MAPPING_PATH_PERSONAL);
		LOGGER.debug("--->>>mergeForPraise.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "profession/doPraise" }, name = "点赞技能")
	public String doMergeForPraise(@ModelAttribute("entity")  ProfessionPraise professionPraise, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> doMergeForPraise <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			professionPraise.setUserId(userId);
			professionPraise.setCreatedBy(userId);
			professionPraise.setLastModifiedBy(userId);
			boolean checked = professionPraiseValidator.validateForDoPraise(professionPraise);
			if(checked) {
				professionPraise = professionPraiseWrapper.praise(professionPraise);
				professionPraise.setSuccess(ProfessionPraise.SUCCESS_YES);
				professionPraise.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_MERGE_PRAISE_SUCCESS);
				professionPraise.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_MERGE_PRAISE_SUCCESS);
			}
			model.addAttribute("entity", professionPraise);
		}
		LOGGER.debug("========== MAPPING_PATH_PERSONAL: {} ========== ", MAPPING_PATH_PERSONAL);
		LOGGER.debug("--->>>doMergeForPraise.resultPath: {} ---", resultPath);
		return resultPath;
	}

}
