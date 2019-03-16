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

import dml.sz0099.course.app.client.resolver.category.CategoryProccessor;
import dml.sz0099.course.app.client.resolver.category.CategoryRequest;
import dml.sz0099.course.app.client.resolver.category.CategoryResponse;
import dml.sz0099.course.app.client.validator.category.CategoryValidator;
import dml.sz0099.course.app.client.wrapper.category.CategoryExtendWrapper;
import dml.sz0099.course.app.client.wrapper.category.CategoryWrapper;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.category.bo.CategoryBo;
import dml.sz0099.course.app.persist.entity.paragraph.ParagProduct;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeCategoryController 控制转发
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
public class CategoryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryValidator categoryValidator;

	@Autowired
	private CategoryWrapper categoryWrapper;
	
	@Autowired
	private CategoryExtendWrapper categoryExtendWrapper;
	
	@Autowired
	private CategoryProccessor categoryProccessor;

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
		
	
	
	public CategoryController() {
		LOGGER.debug("##>>> init CoeCategoryController <<<##");
	}

	@RequestMapping(value = { "/manage/create" }, name = "创建类别")
	public String create(@ModelAttribute("category") Category category, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> create <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_CATEGORY,"/categoryUI");
		LOGGER.debug("--->>> create.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/editListUI" }, name = "编辑类别")
	public String editListUI(@ModelAttribute("category") Category category, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> editListUI <<<##");
		Long userId = UserUtils.getUserId();
		category.setUserId(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_CATEGORY,"/manage/categoryEditListUI");
		List<Category>  categoryList = null;
		if(null != userId) {
			categoryList = categoryWrapper.findMainAndSub(category);
		}
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("entity", category);
		LOGGER.debug("--->>> editListUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/subEditListUI" }, name = "编辑子类")
	public String subEditListUI(@ModelAttribute("category") Category category, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> subEditListUI <<<##");
		Long userId = UserUtils.getUserId();
		category.setUserId(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_CATEGORY,"/manage/subCategoryEditListUI");
		List<Category>  categoryList = null;
		if(null != userId) {
			Category entity = categoryWrapper.findById(category.getId(), true);
			category = entity;
		}
		model.addAttribute("entity", category);
		LOGGER.debug("--->>> subEditListUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	

	@RequestMapping(value = { "/sub" }, name = "访问categorysub页面")
	public String accessCoeCategorySub(@ModelAttribute("categoryBo") CategoryBo categoryBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCoeCategory <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_CATEGORY_BASIC,"/category");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	
	@RequestMapping(value = { "manage/add" }, name = "添加分类")
	public String addCategory(@ModelAttribute("category") Category category, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> addCategory <<<##");

		Long userId = UserUtils.getUserId();
		category.setUserId(userId);
		category.setCreatedBy(userId);
		boolean checked = categoryValidator.validateAddCategory(category);
		if(checked) {
			category = categoryWrapper.createCategory(category);
			category.setSuccess(Category.SUCCESS_YES);
			category.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_CATEGORY_ADD_SUCCESS);
			category.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_CATEGORY_ADD_SUCCESS);
		}
		model.addAttribute("entity", category);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_CATEGORY,"/manage/categoryAddUI");
		LOGGER.debug("--->>> addCategory.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/addSub" }, name = "添加子类")
	public String addSubCategory(@ModelAttribute("category") Category category, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> addSubCategory <<<##");

		Long userId = UserUtils.getUserId();
		category.setUserId(userId);
		category.setCreatedBy(userId);
		boolean checked = categoryValidator.validateAddCategory(category);
		if(checked) {
			category = categoryWrapper.createCategory(category);//创建子类
			Category node = categoryWrapper.findTop(category.getParentId(), true);//刷新节点
			category.setSuccess(Category.SUCCESS_YES);
			category.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_CATEGORY_ADD_SUCCESS);
			category.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_CATEGORY_ADD_SUCCESS);
			model.addAttribute("node", node);
		}
		model.addAttribute("entity", category);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_CATEGORY,"/manage/subCategoryAddUI");
		LOGGER.debug("--->>> addSubCategory.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/refreshSub" }, name = "刷新节点")
	public String refreshSubCategory(@ModelAttribute("category") Category category, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> refreshSubCategory <<<##");

		Long userId = UserUtils.getUserId();
		category.setUserId(userId);
		boolean checked = categoryValidator.validateAddCategory(category);
		if(checked) {
			Category node = categoryWrapper.findTop(category.getParentId(), true);//刷新节点
			category.setSuccess(Category.SUCCESS_YES);
			category.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_CATEGORY_REFRESH_SUCCESS);
			category.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_CATEGORY_REFRESH_SUCCESS);
			model.addAttribute("node", node);
		}
		model.addAttribute("entity", category);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_CATEGORY,"/manage/subCategoryAddUI");
		LOGGER.debug("--->>> refreshSubCategory.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/mergeSingle" }, name = "保存分类")
	public String mergeCategory(@ModelAttribute("category") Category category, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeCategory <<<##");

		Long userId = UserUtils.getUserId();
		category.setUserId(userId);
		category.setLastModifiedBy(userId);
		boolean checked = categoryValidator.validateMergeCategory(category);
		if(checked) {
			category = categoryWrapper.mergeEntity(category);
			category.setSuccess(Category.SUCCESS_YES);
			category.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_CATEGORY_MERGE_SUCCESS);
			category.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_CATEGORY_MERGE_SUCCESS);
		}
		model.addAttribute("entity", category);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_CATEGORY,"/manage/commonResult");
		LOGGER.debug("--->>> mergeCategory.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/deleteSingle" }, name = "删除分类")
	public String deleteCategory(@ModelAttribute("category") Category category, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> deleteCategory <<<##");

		Long userId = UserUtils.getUserId();
		category.setUserId(userId);
		category.setLastModifiedBy(userId);
		boolean checked = categoryValidator.validateForDelete(category);
		if(checked) {
			categoryWrapper.deleteCategory(category);
			category.setSuccess(Category.SUCCESS_YES);
			category.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_CATEGORY_DELETE_SUCCESS);
			category.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_CATEGORY_DELETE_SUCCESS);
		}
		model.addAttribute("entity", category);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_CATEGORY,"/manage/commonResult");
		LOGGER.debug("--->>> deleteCategory.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/deleteAll" }, name = "删除全部分类")
	public String deleteCategoryAll(@ModelAttribute("category") Category category, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> deleteCategoryAll <<<##");

		Long userId = UserUtils.getUserId();
		category.setUserId(userId);
		category.setLastModifiedBy(userId);
		boolean checked = categoryValidator.validateForDeleteAll(category);
		if(checked) {
			categoryWrapper.deleteAllByExtend(category);
			category.setSuccess(ParagProduct.SUCCESS_YES);
			category.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_CATEGORY_DELETE_ALL_SUCCESS);
			category.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_CATEGORY_DELETE_ALL_SUCCESS);
		}
		model.addAttribute("entity", category);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_CATEGORY,"/manage/commonResult");
		LOGGER.debug("--->>> deleteCategoryAll.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "query/tree" }, name = "查询分类树")
	public String queryCategoryTree(@ModelAttribute("categoryRequest") CategoryRequest categoryRequest, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryCategoryTree <<<##");

		Long userId = UserUtils.getUserId();
		categoryRequest.setUserId(userId);
		Category category = null;
		CategoryResponse<Category> response = categoryProccessor.proccessQuery(categoryRequest);
		//boolean checked = categoryValidator.validateAddCategory(category);
		if(null != response) {
			category = response.getContent();//刷新节点
			category.setSuccess(response.getSuccess());
			category.setRespCode(response.getRespCode());
			category.setRespMsg(response.getRespMsg());
			model.addAttribute("node", category);
		}
		model.addAttribute("entity", category);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_CATEGORY,"/manage/subCategoryAddUI");
		LOGGER.debug("--->>> queryCategoryTree.resultPath: {} ---", resultPath);
		return resultPath;
	}
	

}
