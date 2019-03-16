package dml.sz0099.course.app.client.controller.category;

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
import dml.sz0099.course.app.client.validator.category.CategoryRefValidator;
import dml.sz0099.course.app.client.wrapper.category.CategoryRefWrapper;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.module.define.CourseRespCode;
import dml.sz0099.course.app.module.define.SZ0099AppModule;
import dml.sz0099.course.app.persist.entity.category.CategoryRef;
import dml.sz0099.course.app.persist.entity.category.bo.CategoryRefBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CategoryRefController 控制转发
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
public class CategoryRefController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryRefController.class);

	@Autowired
	private CategoryRefValidator categoryRefValidator;

	@Autowired
	private CategoryRefWrapper categoryRefWrapper;
	
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
	

	public CategoryRefController() {
		LOGGER.debug("##>>> init CategoryRefController <<<##");
	}

	@RequestMapping(value = { "/ref/query" }, name = "访问categoryRefUI页面")
	public String query(@ModelAttribute("categoryRefBo") CategoryRefBo categoryRefBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCategoryRefUI <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_CATEGORY,"/categoryRefUI");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/ref/change" }, name = "更改类别")
	public String changeCategory(@ModelAttribute("categoryRef") CategoryRef categoryRef, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> changeCategory <<<##");
		Long userId = UserUtils.getUserId();
		categoryRef.setUserId(userId);
		categoryRef.setLastModifiedBy(userId);
		categoryRef.setCreatedBy(userId);
		boolean checked = categoryRefValidator.validateForChange(categoryRef);
		if(checked) {
			
			categoryRef = categoryProccessor.mergeCategoryRef(categoryRef);
			categoryRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_CATEGORY_REF_BIND_SUCCESS);
			categoryRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_CATEGORY_REF_BIND_SUCCESS);
			categoryRef.setSuccess(CategoryRef.SUCCESS_YES);
			
		}
		model.addAttribute("entity", categoryRef);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_CATEGORY,"/manage/commonResult");
		LOGGER.debug("--->>> changeCategory.resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "/categoryRef/sub" }, name = "访问categoryRefsub页面")
	public String accessCategoryRefSub(@ModelAttribute("categoryRefBo") CategoryRefBo categoryRefBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCategoryRef <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_CATEGORY_BASIC,"/categoryRef");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

}
