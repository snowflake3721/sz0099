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

import dml.sz0099.course.app.client.validator.profession.ProfessionFavirateValidator;
import dml.sz0099.course.app.client.wrapper.profession.ProfessionFavirateWrapper;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.profession.ProfessionFavirate;
import dml.sz0099.course.app.persist.entity.profession.bo.ProfessionFavirateBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ProfessionFavirateController 控制转发
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
public class ProfessionFavirateController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionFavirateController.class);

	@Autowired
	private ProfessionFavirateValidator professionFavirateValidator;

	@Autowired
	private ProfessionFavirateWrapper professionFavirateWrapper;

	//模板根目录
		private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
		//模块缩略名称目录
		private String MODULE_PATH_PERSONAL = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_PERSONAL.getAd());
		//模块主目录,一般与模块缩略名称相同
		private String MAPPING_PATH_PERSONAL = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_PERSONAL_PATH.currentPath());
		//模块二级目录
		private String MAPPING_SUB_PATH_PERSONAL_BASIC = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_PERSONAL_PATH_BASIC.currentPath());
		
		@PostConstruct
		public void init() {
			LOGGER.debug("========== TLE_DEFALT_FRONT: {} ========== ", TLE_DEFALT_FRONT);
			LOGGER.debug("========== MODULE_PATH_PERSONAL: {} ========== ", MODULE_PATH_PERSONAL);
			LOGGER.debug("========== MAPPING_PATH_PERSONAL: {} ========== ", MAPPING_PATH_PERSONAL);
			LOGGER.debug("========== MAPPING_SUB_PATH_PERSONAL_BASIC: {} ========== ", MAPPING_SUB_PATH_PERSONAL_BASIC);
		}
	
	
	public ProfessionFavirateController() {
		LOGGER.debug("##>>> init ProfessionFavirateController <<<##");
	}

	@RequestMapping(value = { "/professionFavirateUI" }, name = "访问professionFavirateUI页面")
	public String accessProfessionFavirateUI(@ModelAttribute("professionFavirateBo") ProfessionFavirateBo professionFavirateBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessProfessionFavirateUI <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/professionFavirateUI");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "/professionFavirate/sub" }, name = "访问professionFaviratesub页面")
	public String accessProfessionFavirateSub(@ModelAttribute("professionFavirateBo") ProfessionFavirateBo professionFavirateBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessProfessionFavirate <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_PERSONAL_BASIC,"/professionFavirate");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "profession/favirate" }, name = "收藏技能")
	public String mergeForFavirate(@ModelAttribute("entity")  ProfessionFavirate articleFavirate, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForFavirate <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			articleFavirate.setUserId(userId);
			articleFavirate.setCreatedBy(userId);
			articleFavirate.setLastModifiedBy(userId);
			boolean checked = professionFavirateValidator.validateFavirate(articleFavirate);
			if(checked) {
				articleFavirate = professionFavirateWrapper.favirate(articleFavirate);
				articleFavirate.setSuccess(ProfessionFavirate.SUCCESS_YES);
				articleFavirate.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_MERGE_FAVIRATE_SUCCESS);
				articleFavirate.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_MERGE_FAVIRATE_SUCCESS);
			}
			model.addAttribute("entity", articleFavirate);
		}
		LOGGER.debug("========== MAPPING_PATH_PERSONAL: {} ========== ", MAPPING_PATH_PERSONAL);
		LOGGER.debug("--->>>mergeForFavirate.resultPath: {} ---", resultPath);
		return resultPath;
	}

}
