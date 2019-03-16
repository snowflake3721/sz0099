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

import dml.sz0099.course.app.client.validator.article.CoeArticlePraiseValidator;
import dml.sz0099.course.app.client.wrapper.article.CoeArticlePraiseWrapper;
import dml.sz0099.course.app.module.define.CoeArticleRespCode;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePraise;
import dml.sz0099.course.app.persist.entity.article.bo.CoeArticlePraiseBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeArticlePraiseController 控制转发
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
public class CoeArticlePraiseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeArticlePraiseController.class);

	@Autowired
	private CoeArticlePraiseValidator coeArticlePraiseValidator;

	@Autowired
	private CoeArticlePraiseWrapper coeArticlePraiseWrapper;

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
	
	
	public CoeArticlePraiseController() {
		LOGGER.debug("##>>> init CoeArticlePraiseController <<<##");
	}

	@RequestMapping(value = { "/coeArticlePraiseUI" }, name = "访问coeArticlePraiseUI页面")
	public String accessCoeArticlePraiseUI(@ModelAttribute("coeArticlePraiseBo") CoeArticlePraiseBo coeArticlePraiseBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCoeArticlePraiseUI <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/coeArticlePraiseUI");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "/coeArticlePraise/sub" }, name = "访问coeArticlePraisesub页面")
	public String accessCoeArticlePraiseSub(@ModelAttribute("coeArticlePraiseBo") CoeArticlePraiseBo coeArticlePraiseBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCoeArticlePraise <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_ARTICLE_BASIC,"/coeArticlePraise");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/praise" }, name = "点赞文章预备")
	public String mergeForPraise(@ModelAttribute("entity")  CoeArticlePraise articlePraise, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForPraise <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE, "/praisePre");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			articlePraise.setUserId(userId);
			articlePraise.setCreatedBy(userId);
			articlePraise.setLastModifiedBy(userId);
			boolean checked = coeArticlePraiseValidator.validateForPraise(articlePraise);
			if(checked) {
				articlePraise.setSuccess(CoeArticlePraise.SUCCESS_YES);
				//articlePraise.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_MERGE_PRAISE_SUCCESS);
				//articlePraise.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_MERGE_PRAISE_SUCCESS);
			}
			model.addAttribute("entity", articlePraise);
		}
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		LOGGER.debug("--->>>mergeForPraise.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/doPraise" }, name = "点赞文章")
	public String doMergeForPraise(@ModelAttribute("entity")  CoeArticlePraise articlePraise, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> doMergeForPraise <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			articlePraise.setUserId(userId);
			articlePraise.setCreatedBy(userId);
			articlePraise.setLastModifiedBy(userId);
			boolean checked = coeArticlePraiseValidator.validateForDoPraise(articlePraise);
			if(checked) {
				articlePraise = coeArticlePraiseWrapper.praise(articlePraise);
				articlePraise.setSuccess(CoeArticlePraise.SUCCESS_YES);
				articlePraise.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_MERGE_PRAISE_SUCCESS);
				articlePraise.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_MERGE_PRAISE_SUCCESS);
			}
			model.addAttribute("entity", articlePraise);
		}
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		LOGGER.debug("--->>>doMergeForPraise.resultPath: {} ---", resultPath);
		return resultPath;
	}

}
