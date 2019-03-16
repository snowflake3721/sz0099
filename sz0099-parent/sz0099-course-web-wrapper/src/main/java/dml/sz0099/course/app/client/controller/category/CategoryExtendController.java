package dml.sz0099.course.app.client.controller.category;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.jit4j.core.webmvc.config.ResourcePathConfig;
import org.jit4j.core.webmvc.util.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dml.sz0099.course.app.client.validator.category.CategoryExtendValidator;
import dml.sz0099.course.app.client.wrapper.category.CategoryExtendWrapper;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.module.define.CourseRespCode;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.category.CategoryExtend;
import dml.sz0099.course.app.persist.entity.category.bo.CategoryExtendBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CategoryExtendController 控制转发
 * @author bruce yang at 2018-08-29 22:41:14
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@RequestMapping("/sz0099/ood/category")
@Controller
public class CategoryExtendController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryExtendController.class);

	@Autowired
	private CategoryExtendValidator categoryExtendValidator;

	@Autowired
	private CategoryExtendWrapper categoryExtendWrapper;

	//模板根目录
	private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
	//模块缩略名称目录
	private String MODULE_PATH_CATEGORY = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_CATEGORY.getAd());
	//模块主目录,一般与模块缩略名称相同
	private String MAPPING_PATH_CATEGORY = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_CATEGORY_PATH.currentPath());
	//模块二级目录
	private String MAPPING_SUB_PATH_CATEGORY_BASIC = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_CATEGORY_PATH_BASIC.currentPath());
	
	@PostConstruct
	public void init() {
		LOGGER.debug("========== TLE_DEFALT_FRONT: {} ========== ", TLE_DEFALT_FRONT);
		LOGGER.debug("========== MODULE_PATH_CATEGORY: {} ========== ", MODULE_PATH_CATEGORY);
		LOGGER.debug("========== MAPPING_PATH_CATEGORY: {} ========== ", MAPPING_PATH_CATEGORY);
		LOGGER.debug("========== MAPPING_SUB_PATH_CATEGORY_BASIC: {} ========== ", MAPPING_SUB_PATH_CATEGORY_BASIC);
	}
	
	public CategoryExtendController() {
		LOGGER.debug("##>>> init CategoryExtendController <<<##");
	}
	
	@RequestMapping(value = { "/extend/manage/list" }, name = "类别模块项目列表")
	public String queryCategoryList(@ModelAttribute("category")CategoryExtend extend, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryCategoryList <<<##");
		extend.setUserId(UserUtils.getUserId());
		Long userId = UserUtils.getUserId();
		List<CategoryExtend>  contentList = null;
		if(null != userId) {
			contentList = categoryExtendWrapper.findAll();
			extend.setSuccess(CategoryExtend.SUCCESS_YES);
		}else {
			extend.setSuccess(CategoryExtend.SUCCESS_NO);
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_CATEGORY_EXTEND_USER_NOT_LOGIN);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_CATEGORY_EXTEND_USER_NOT_LOGIN);
		}
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_CATEGORY,"/manage/categoryList");
		model.addAttribute("contentList", contentList);
		LOGGER.debug("--->>> queryCategoryList.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/extend/manage/create" }, name = "新建或编辑项目")
	public String extendCreate(@ModelAttribute("category") CategoryExtend extend, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> extendCreate <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_CATEGORY,"/manage/extendAddUI");
		Long userId = UserUtils.getUserId();
		extend.setUserId(userId);
		
		if(null != userId) {
			Long id = extend.getId();
			if(null != id) {
				extend=categoryExtendWrapper.findById(id);
			}
			boolean checked = categoryExtendValidator.validateExtendForPersist(extend);
			
			if(checked) {
				//创建一个
				if(id == null) {
					extend.setCreatedBy(userId);
					extend=categoryExtendWrapper.create(extend);
					extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_CATEGORY_EXTEND_PERSIST_SUCCESS);
					extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_CATEGORY_EXTEND_PERSIST_SUCCESS);
				}
				extend.setSuccess(CategoryExtend.SUCCESS_YES);
			}
		}else {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_CATEGORY_EXTEND_USER_NOT_LOGIN);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_CATEGORY_EXTEND_USER_NOT_LOGIN);
			extend.setSuccess(CategoryExtend.SUCCESS_NO);
		}
		
		model.addAttribute("entity", extend);
		
		LOGGER.debug("--->>> extendCreate.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/extend/manage/merge" }, name = "保存项目")
	public String saveExtend(@ModelAttribute("category") CategoryExtend extend, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> saveExtend <<<##");
		Long userId = UserUtils.getUserId();
		extend.setUserId(userId);
		boolean checked = categoryExtendValidator.validateExtend(extend);
		
		if(checked) {
			extend.setLastModifiedBy(userId);
			extend = categoryExtendWrapper.mergeEntity(extend);
			extend.setSuccess(CategoryExtend.SUCCESS_YES);
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_CATEGORY_EXTEND_SAVE_SUCCESS);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_CATEGORY_EXTEND_SAVE_SUCCESS);
		}
		model.addAttribute("entity", extend);
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_CATEGORY,"/manage/commonResult");
		LOGGER.debug("--->>> saveExtend.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/extend/manage/delete" }, name = "删除项目")
	public String deleteExtend(@ModelAttribute("entity") CategoryExtend extend, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> deleteExtend <<<##");
		Long userId = UserUtils.getUserId();
		extend.setUserId(userId);
		boolean checked = categoryExtendValidator.validateExtendForDelete(extend);
		
		if(checked) {
			extend.setLastModifiedBy(userId);
			extend = categoryExtendWrapper.deleteEntity(extend);
			extend.setSuccess(CategoryExtend.SUCCESS_YES);
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_CATEGORY_EXTEND_DELETE_SUCCESS);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_CATEGORY_EXTEND_DELETE_SUCCESS);
		}
		model.addAttribute("entity", extend);
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_CATEGORY,"/manage/commonResult");
		LOGGER.debug("--->>> deleteExtend.resultPath: {} ---", resultPath);
		return resultPath;
	}
	

	@RequestMapping(value = { "/categoryExtendUI" }, name = "访问categoryExtendUI页面")
	public String accessCategoryExtendUI(@ModelAttribute("categoryExtendBo") CategoryExtendBo categoryExtendBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCategoryExtendUI <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_CATEGORY,"/categoryExtendUI");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "/categoryExtend/sub" }, name = "访问categoryExtendsub页面")
	public String accessCategoryExtendSub(@ModelAttribute("categoryExtendBo") CategoryExtendBo categoryExtendBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCategoryExtend <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_CATEGORY_BASIC,"/categoryExtend");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

}
