package dml.sz0099.course.app.client.controller.activity;

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

import dml.sz0099.course.app.client.validator.activity.CoeActivityTagValidator;
import dml.sz0099.course.app.client.wrapper.activity.CoeActivityTagWrapper;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTag;
import dml.sz0099.course.app.persist.entity.activity.bo.CoeActivityTagBo;
import dml.sz0099.course.app.persist.entity.paragraph.ParagProduct;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeActivityTagController 控制转发
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
public class CoeActivityTagController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityTagController.class);

	@Autowired
	private CoeActivityTagValidator coeActivityTagValidator;

	@Autowired
	private CoeActivityTagWrapper coeActivityTagWrapper;
	
	
	

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
	
	public CoeActivityTagController() {
		LOGGER.debug("##>>> init CoeActivityTagController <<<##");
	}

	@RequestMapping(value = { "/coeActivityTagUI" }, name = "访问coeActivityTagUI页面")
	public String accessCoeActivityTagUI(@ModelAttribute("coeActivityTagBo") CoeActivityTagBo coeActivityTagBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCoeActivityTagUI <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/coeActivityTagUI");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "/findRelative" }, name = "查询标签相关文章")
	public String findRelative(@ModelAttribute("entity") CoeActivityTag entity, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> findRelative <<<##");
		String name = entity.getName();
		Page<CoeActivityTag> entityPage = null;
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "aid");
		if(StringUtils.isNotBlank(name)) {
			entityPage = coeActivityTagWrapper.findPageWithNotself(entity, pageable);
		}
		
		model.addAttribute("entityPage", entityPage);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"tag/findRelativeAnsy");
		LOGGER.debug("--->>> findRelative.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/tag/add" }, name = "添加标签")
	public String addActivityTag(@ModelAttribute("coeActivityTag") CoeActivityTag coeActivityTag,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> addActivityTag <<<##");

		Long userId = UserUtils.getUserId();
		coeActivityTag.setCreatedBy(userId);
		coeActivityTag.setLastModifiedBy(userId);
		Long activityId = coeActivityTag.getMainId();
		boolean checked = coeActivityTagValidator.validateAddActivityTag(errors, coeActivityTag);
		if(checked) {
			coeActivityTag = coeActivityTagWrapper.addTag(coeActivityTag);
			coeActivityTag.setSuccess(ParagProduct.SUCCESS_YES);
			coeActivityTag.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_TAG_ADD_SUCCESS);
			coeActivityTag.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_TAG_ADD_SUCCESS);
		}
		model.addAttribute("entity", coeActivityTag);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/manage/tagResult");
		LOGGER.debug("--->>> addParagProduct.resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "manage/tag/delete" }, name = "删除标签")
	public String deleteActivityTag(@ModelAttribute("coeActivityTag") CoeActivityTag coeActivityTag,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> deleteActivityTag <<<##");

		Long userId = UserUtils.getUserId();
		coeActivityTag.setCreatedBy(userId);
		coeActivityTag.setLastModifiedBy(userId);
		Long activityId = coeActivityTag.getMainId();
		boolean checked = coeActivityTagValidator.validateDeleteActivityTag(errors, coeActivityTag);
		if(checked) {
			coeActivityTag = coeActivityTagWrapper.deleteTag(coeActivityTag);
			coeActivityTag.setSuccess(ParagProduct.SUCCESS_YES);
			coeActivityTag.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_TAG_DELETE_SUCCESS);
			coeActivityTag.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_TAG_DELETE_SUCCESS);
		}
		model.addAttribute("entity", coeActivityTag);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/manage/tagResult");
		LOGGER.debug("--->>> deleteActivityTag.resultPath: {} ---", resultPath);
		return resultPath;
	}
}
