package dml.sz0099.course.app.client.controller.article;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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

import dml.sz0099.course.app.client.validator.article.CoeArticleTagValidator;
import dml.sz0099.course.app.client.wrapper.article.CoeArticleTagWrapper;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.article.CoeArticleTag;
import dml.sz0099.course.app.persist.entity.article.bo.CoeArticleTagBo;
import dml.sz0099.course.app.persist.entity.paragraph.ParagProduct;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeArticleTagController 控制转发
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
public class CoeArticleTagController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeArticleTagController.class);

	@Autowired
	private CoeArticleTagValidator coeArticleTagValidator;

	@Autowired
	private CoeArticleTagWrapper coeArticleTagWrapper;
	
	
	

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
	
	public CoeArticleTagController() {
		LOGGER.debug("##>>> init CoeArticleTagController <<<##");
	}

	@RequestMapping(value = { "/coeArticleTagUI" }, name = "访问coeArticleTagUI页面")
	public String accessCoeArticleTagUI(@ModelAttribute("coeArticleTagBo") CoeArticleTagBo coeArticleTagBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCoeArticleTagUI <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/coeArticleTagUI");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "/findRelative" }, name = "查询标签相关文章")
	public String findRelative(@ModelAttribute("entity") CoeArticleTag entity, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> findRelative <<<##");
		String name = entity.getName();
		Page<CoeArticleTag> entityPage = null;
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "aid");
		if(StringUtils.isNotBlank(name)) {
			entityPage = coeArticleTagWrapper.findPageWithNotself(entity, pageable);
		}
		
		model.addAttribute("entityPage", entityPage);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"tag/findRelativeAnsy");
		LOGGER.debug("--->>> findRelative.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/tag/add" }, name = "添加标签")
	public String addArticleTag(@ModelAttribute("coeArticleTag") CoeArticleTag coeArticleTag,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> addArticleTag <<<##");

		Long userId = UserUtils.getUserId();
		coeArticleTag.setCreatedBy(userId);
		coeArticleTag.setLastModifiedBy(userId);
		Long articleId = coeArticleTag.getMainId();
		boolean checked = coeArticleTagValidator.validateAddArticleTag(errors, coeArticleTag);
		if(checked) {
			coeArticleTag = coeArticleTagWrapper.addTag(coeArticleTag);
			coeArticleTag.setSuccess(ParagProduct.SUCCESS_YES);
			coeArticleTag.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_TAG_ADD_SUCCESS);
			coeArticleTag.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_TAG_ADD_SUCCESS);
		}
		model.addAttribute("entity", coeArticleTag);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/manage/tagResult");
		LOGGER.debug("--->>> addParagProduct.resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "manage/tag/delete" }, name = "删除标签")
	public String deleteArticleTag(@ModelAttribute("coeArticleTag") CoeArticleTag coeArticleTag,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> deleteArticleTag <<<##");

		Long userId = UserUtils.getUserId();
		coeArticleTag.setCreatedBy(userId);
		coeArticleTag.setLastModifiedBy(userId);
		Long articleId = coeArticleTag.getMainId();
		boolean checked = coeArticleTagValidator.validateDeleteArticleTag(errors, coeArticleTag);
		if(checked) {
			coeArticleTag = coeArticleTagWrapper.deleteTag(coeArticleTag);
			coeArticleTag.setSuccess(ParagProduct.SUCCESS_YES);
			coeArticleTag.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_TAG_DELETE_SUCCESS);
			coeArticleTag.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_TAG_DELETE_SUCCESS);
		}
		model.addAttribute("entity", coeArticleTag);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/manage/tagResult");
		LOGGER.debug("--->>> deleteArticleTag.resultPath: {} ---", resultPath);
		return resultPath;
	}
}
