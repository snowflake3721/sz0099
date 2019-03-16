package dml.sz0099.course.app.client.controller.profession;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

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

import dml.sz0099.course.app.client.validator.profession.ProfessionTagValidator;
import dml.sz0099.course.app.client.wrapper.profession.ProfessionTagWrapper;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.paragraph.ParagProduct;
import dml.sz0099.course.app.persist.entity.profession.ProfessionTag;
import dml.sz0099.course.app.persist.entity.profession.bo.ProfessionTagBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ProfessionTagController 控制转发
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
public class ProfessionTagController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionTagController.class);

	@Autowired
	private ProfessionTagValidator professionTagValidator;

	@Autowired
	private ProfessionTagWrapper professionTagWrapper;
	
	
	

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
	
	public ProfessionTagController() {
		LOGGER.debug("##>>> init ProfessionTagController <<<##");
	}

	@RequestMapping(value = { "/professionTagUI" }, name = "访问professionTagUI页面")
	public String accessProfessionTagUI(@ModelAttribute("professionTagBo") ProfessionTagBo professionTagBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessProfessionTagUI <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/professionTagUI");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "/professionTag/sub" }, name = "访问professionTagsub页面")
	public String accessProfessionTagSub(@ModelAttribute("professionTagBo") ProfessionTagBo professionTagBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessProfessionTag <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_PERSONAL_BASIC,"/professionTag");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "profession/manage/tag/add" }, name = "添加标签")
	public String addProductTag(@ModelAttribute("professionTag") ProfessionTag professionTag,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> addProductTag <<<##");

		Long userId = UserUtils.getUserId();
		professionTag.setCreatedBy(userId);
		professionTag.setLastModifiedBy(userId);
		Long professionId = professionTag.getMainId();
		boolean checked = professionTagValidator.validateAddProductTag(errors, professionTag);
		if(checked) {
			professionTag = professionTagWrapper.addTag(professionTag);
			professionTag.setSuccess(ParagProduct.SUCCESS_YES);
			professionTag.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_TAG_ADD_SUCCESS);
			professionTag.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_TAG_ADD_SUCCESS);
		}
		model.addAttribute("entity", professionTag);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/manage/tagResult");
		LOGGER.debug("--->>> addParagProduct.resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "profession/manage/tag/delete" }, name = "删除标签")
	public String deleteProductTag(@ModelAttribute("professionTag") ProfessionTag professionTag,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> deleteProductTag <<<##");

		Long userId = UserUtils.getUserId();
		professionTag.setCreatedBy(userId);
		professionTag.setLastModifiedBy(userId);
		Long professionId = professionTag.getMainId();
		boolean checked = professionTagValidator.validateDeleteProductTag(errors, professionTag);
		if(checked) {
			professionTag = professionTagWrapper.deleteTag(professionTag);
			professionTag.setSuccess(ParagProduct.SUCCESS_YES);
			professionTag.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_TAG_DELETE_SUCCESS);
			professionTag.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_TAG_DELETE_SUCCESS);
		}
		model.addAttribute("entity", professionTag);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/manage/tagResult");
		LOGGER.debug("--->>> deleteProductTag.resultPath: {} ---", resultPath);
		return resultPath;
	}
}
