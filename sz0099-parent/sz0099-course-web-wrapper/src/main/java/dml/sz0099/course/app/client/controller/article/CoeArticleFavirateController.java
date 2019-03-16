package dml.sz0099.course.app.client.controller.article;

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

import dml.sz0099.course.app.client.validator.article.CoeArticleFavirateValidator;
import dml.sz0099.course.app.client.wrapper.article.CoeArticleFavirateWrapper;
import dml.sz0099.course.app.module.define.CoeArticleRespCode;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.CoeArticleFavirate;
import dml.sz0099.course.app.persist.entity.article.bo.CoeArticleFavirateBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeArticleFavirateController 控制转发
 * @author bruce yang at 2018-08-29 22:41:14
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@RequestMapping("/sz0099/ood/article")
@Controller
public class CoeArticleFavirateController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeArticleFavirateController.class);

	@Autowired
	private CoeArticleFavirateValidator coeArticleFavirateValidator;

	@Autowired
	private CoeArticleFavirateWrapper coeArticleFavirateWrapper;

	//模板根目录
		private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
		//模块缩略名称目录
		private String MODULE_PATH_ARTICLE = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_ARTICLE.getAd());
		//模块主目录,一般与模块缩略名称相同
		private String MAPPING_PATH_ARTICLE = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_ARTICLE_PATH.currentPath());
		//模块二级目录
		private String MAPPING_SUB_PATH_ARTICLE_BASIC = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_ARTICLE_PATH_BASIC.currentPath());
		
		@PostConstruct
		public void init() {
			LOGGER.debug("========== TLE_DEFALT_FRONT: {} ========== ", TLE_DEFALT_FRONT);
			LOGGER.debug("========== MODULE_PATH_ARTICLE: {} ========== ", MODULE_PATH_ARTICLE);
			LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
			LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_ARTICLE_BASIC);
		}
	
	
	public CoeArticleFavirateController() {
		LOGGER.debug("##>>> init CoeArticleFavirateController <<<##");
	}

	@RequestMapping(value = { "/coeArticleFavirateUI" }, name = "访问coeArticleFavirateUI页面")
	public String accessCoeArticleFavirateUI(@ModelAttribute("coeArticleFavirateBo") CoeArticleFavirateBo coeArticleFavirateBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCoeArticleFavirateUI <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/coeArticleFavirateUI");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "/coeArticleFavirate/sub" }, name = "访问coeArticleFaviratesub页面")
	public String accessCoeArticleFavirateSub(@ModelAttribute("coeArticleFavirateBo") CoeArticleFavirateBo coeArticleFavirateBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCoeArticleFavirate <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_ARTICLE_BASIC,"/coeArticleFavirate");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/favirate" }, name = "收藏文章")
	public String mergeForFavirate(@ModelAttribute("entity")  CoeArticleFavirate articleFavirate, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForFavirate <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			articleFavirate.setUserId(userId);
			articleFavirate.setCreatedBy(userId);
			articleFavirate.setLastModifiedBy(userId);
			boolean checked = coeArticleFavirateValidator.validateFavirate(articleFavirate);
			if(checked) {
				articleFavirate = coeArticleFavirateWrapper.favirate(articleFavirate);
				articleFavirate.setSuccess(CoeArticleFavirate.SUCCESS_YES);
				articleFavirate.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_MERGE_FAVIRATE_SUCCESS);
				articleFavirate.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_MERGE_FAVIRATE_SUCCESS);
			}
			model.addAttribute("entity", articleFavirate);
		}
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		LOGGER.debug("--->>>mergeForFavirate.resultPath: {} ---", resultPath);
		return resultPath;
	}

}
