package dml.sz0099.course.app.client.controller.article;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.jit4j.core.persist.page.PageRequest;
import org.jit4j.core.webmvc.config.ResourcePathConfig;
import org.jit4j.core.webmvc.util.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dml.sz0099.course.app.client.resolver.adaptor.media.ParagArticleAdaptor;
import dml.sz0099.course.app.client.validator.article.ParagArticleValidator;
import dml.sz0099.course.app.client.wrapper.article.ParagArticleWrapper;
import dml.sz0099.course.app.client.wrapper.article.ParagraphWrapper;
import dml.sz0099.course.app.module.define.CoeArticleRespCode;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.persist.entity.article.ParagArticle;
import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.entity.media.ImageRef;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ParagArticleController 控制转发
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
public class ParagArticleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParagArticleController.class);

	@Autowired
	private ParagArticleValidator paragArticleValidator;

	@Autowired
	private ParagArticleWrapper paragArticleWrapper;
	
	@Autowired
	private ParagraphWrapper paragraphWrapper;
	
	@Autowired
	private ParagArticleAdaptor paragraphAdaptor;

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
	

	public ParagArticleController() {
		LOGGER.debug("##>>> init ParagArticleController <<<##");
	}

	
	@RequestMapping(value = { "manage/paragraph/resetOrderSeq" }, name = "编号重排")
	public String resetOrderSeq(@ModelAttribute("entity") ParagArticle paragArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> resetOrderSeq <<<##");
		Long userId = UserUtils.getUserId();
		Long articleId = paragArticle.getMainId();
		paragArticle.setUserId(userId);
		paragArticle.setCreatedBy(userId);
		boolean checked = paragArticleValidator.validateExistArticle(paragArticle);
		Page<ParagArticle> pageresult = null;
		if(checked) {
			pageresult=paragArticleWrapper.resetOrderSeq(articleId, userId);
		}
		model.addAttribute("page", pageresult);
		//获取上传组件参数
		ImageExtend imageExtend = paragraphAdaptor.config();
		model.addAttribute("extend", imageExtend);
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/manage/paragraphEditListUI");
		LOGGER.debug("--->>> resetOrderSeq.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	/**
	 * 个人编辑或管理人员访问
	 * @param paragArticleBo
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "manage/paragraph/editListUI" }, name = "访问段落编辑页")
	public String editParagArticleList(@ModelAttribute("entity") ParagArticle paragArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> editParagArticleList <<<##");

		Long userId = UserUtils.getUserId();
		Long articleId = paragArticle.getMainId();
		paragArticle.setUserId(userId);
		paragArticle.setCreatedBy(userId);
		boolean checked = paragArticleValidator.validateExistArticle(paragArticle);
		if(checked) {
			if(page==null) {
				page=0;
			}
			if(size==null) {
				size=100;
			}
			PageRequest pageable = new PageRequest(page,size,Direction.ASC, "orderSeq");
			Page<ParagArticle> pageresult = paragArticleWrapper.findByMainIdAndUserId(articleId, userId, pageable);
			long total = pageresult.getTotalElements();
			if(total==0) {
				//创建一个段落，执行保存
				paragArticle=paragArticleWrapper.createParagArticle(paragArticle);
			}else {
				model.addAttribute("page", pageresult);
			}
			paragArticle.setSuccess(ParagArticle.SUCCESS_YES);
		}
		model.addAttribute("entity", paragArticle);
		
		//获取上传组件参数
		ImageExtend imageExtend = paragraphAdaptor.config();
		model.addAttribute("extend", imageExtend);
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/manage/paragraphEditListUI");
		LOGGER.debug("--->>> editParagArticleList.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/paragraph/add" }, name = "添加段落")
	public String addParagArticle(@ModelAttribute("paragArticle") ParagArticle paragArticle, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> addParagArticle <<<##");

		Long userId = UserUtils.getUserId();
		paragArticle.setUserId(userId);
		paragArticle.setCreatedBy(userId);
		Long articleId = paragArticle.getMainId();
		boolean checked = paragArticleValidator.validateAddProduct(paragArticle);
		if(checked) {
			paragArticle = paragArticleWrapper.createParagArticle(paragArticle);
			paragArticle.setSuccess(ParagArticle.SUCCESS_YES);
			paragArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_PARAGRAGH_ADD_SUCCESS);
			paragArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_PARAGRAGH_ADD_SUCCESS);
		
		}
		model.addAttribute("entity", paragArticle);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/manage/paragraphAddUI");
		LOGGER.debug("--->>> addParagArticle.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/paragraph/mergeSingle" }, name = "保存段落")
	public String mergeParagArticle(@ModelAttribute("paragArticle") ParagArticle paragArticle, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeParagArticle <<<##");

		Long userId = UserUtils.getUserId();
		paragArticle.setUserId(userId);
		paragArticle.setLastModifiedBy(userId);
		paragArticle.getId();
		Long articleId = paragArticle.getMainId();
		boolean checked = paragArticleValidator.validateMergeProduct(paragArticle);
		if(checked) {
			paragArticle = paragArticleWrapper.mergeEntity(paragArticle);
			paragArticle.setSuccess(ParagArticle.SUCCESS_YES);
			paragArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_PARAGRAGH_MERGE_SUCCESS);
			paragArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_PARAGRAGH_MERGE_SUCCESS);
		}
		model.addAttribute("entity", paragArticle);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/manage/commonResult");
		LOGGER.debug("--->>> mergeParagArticle.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/paragraph/deleteSingle" }, name = "删除段落")
	public String deleteParagArticle(@ModelAttribute("paragArticle") ParagArticle paragArticle, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> deleteParagArticle <<<##");

		Long userId = UserUtils.getUserId();
		paragArticle.setUserId(userId);
		paragArticle.setLastModifiedBy(userId);
		paragArticle.getId();
		Long articleId = paragArticle.getMainId();
		boolean checked = paragArticleValidator.validateForDelete(paragArticle);
		if(checked) {
			paragraphAdaptor.deleteFromRemote(paragArticle);
			paragArticleWrapper.deleteByParagIdAndUserId(paragArticle.getParagId(), userId, true);
			paragArticle.setSuccess(ParagArticle.SUCCESS_YES);
			paragArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_PARAGRAGH_DELETE_SUCCESS);
			paragArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_PARAGRAGH_DELETE_SUCCESS);
		}
		model.addAttribute("entity", paragArticle);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/manage/commonResult");
		LOGGER.debug("--->>> deleteParagArticle.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/paragraph/deleteAll" }, name = "删除全部段落")
	public String deleteParagArticleAll(@ModelAttribute("paragArticle") ParagArticle paragArticle, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> deleteParagArticleAll <<<##");

		Long userId = UserUtils.getUserId();
		paragArticle.setUserId(userId);
		paragArticle.setLastModifiedBy(userId);
		paragArticle.getId();
		Long articleId = paragArticle.getMainId();
		boolean checked = paragArticleValidator.validateForDeleteAll(paragArticle);
		if(checked) {
			paragraphAdaptor.deleteFromRemote(paragArticle);
			paragArticleWrapper.deleteByArticleIdAndUserId(articleId, userId);
			paragArticle.setSuccess(ParagArticle.SUCCESS_YES);
			paragArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_PARAGRAGH_DELETE_ALL_SUCCESS);
			paragArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_PARAGRAGH_DELETE_ALL_SUCCESS);
		}
		model.addAttribute("entity", paragArticle);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/manage/commonResult");
		LOGGER.debug("--->>> deleteParagArticleAll.resultPath: {} ---", resultPath);
		return resultPath;
	}

	
	
	
	

}
