package dml.sz0099.course.app.client.controller.product;

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

import dml.sz0099.course.app.client.validator.product.CoeProductTagValidator;
import dml.sz0099.course.app.client.wrapper.product.CoeProductTagWrapper;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.paragraph.ParagProduct;
import dml.sz0099.course.app.persist.entity.product.CoeProductTag;
import dml.sz0099.course.app.persist.entity.product.bo.CoeProductTagBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeProductTagController 控制转发
 * @author bruce yang at 2018-08-29 22:41:14
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@RequestMapping("/sz0099/ood/product")
@Controller
public class CoeProductTagController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeProductTagController.class);

	@Autowired
	private CoeProductTagValidator coeProductTagValidator;

	@Autowired
	private CoeProductTagWrapper coeProductTagWrapper;
	
	
	

	//模板根目录
		private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
		//模块缩略名称目录
		private String MODULE_PATH_PRODUCT = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_PRODUCT.getAd());
		//模块主目录,一般与模块缩略名称相同
		private String MAPPING_PATH_PRODUCT = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_PRODUCT_PATH.currentPath());
		//模块二级目录
		private String MAPPING_SUB_PATH_PRODUCT_BASIC = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_PRODUCT_PATH_BASIC.currentPath());
		
		@PostConstruct
		public void init() {
			LOGGER.debug("========== TLE_DEFALT_FRONT: {} ========== ", TLE_DEFALT_FRONT);
			LOGGER.debug("========== MODULE_PATH_PRODUCT: {} ========== ", MODULE_PATH_PRODUCT);
			LOGGER.debug("========== MAPPING_PATH_PRODUCT: {} ========== ", MAPPING_PATH_PRODUCT);
			LOGGER.debug("========== MAPPING_SUB_PATH_PRODUCT_BASIC: {} ========== ", MAPPING_SUB_PATH_PRODUCT_BASIC);
		}
	
	
	public CoeProductTagController() {
		LOGGER.debug("##>>> init CoeProductTagController <<<##");
	}

	@RequestMapping(value = { "/coeProductTagUI" }, name = "访问coeProductTagUI页面")
	public String accessCoeProductTagUI(@ModelAttribute("coeProductTagBo") CoeProductTagBo coeProductTagBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCoeProductTagUI <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/coeProductTagUI");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "/coeProductTag/sub" }, name = "访问coeProductTagsub页面")
	public String accessCoeProductTagSub(@ModelAttribute("coeProductTagBo") CoeProductTagBo coeProductTagBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCoeProductTag <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_PRODUCT_BASIC,"/coeProductTag");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/tag/add" }, name = "添加标签")
	public String addProductTag(@ModelAttribute("productTag") CoeProductTag productTag,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> addProductTag <<<##");

		Long userId = UserUtils.getUserId();
		productTag.setCreatedBy(userId);
		productTag.setLastModifiedBy(userId);
		Long productId = productTag.getMainId();
		//productTag.setProductId(productId);
		boolean checked = coeProductTagValidator.validateAddProductTag(errors, productTag);
		if(checked) {
			productTag = coeProductTagWrapper.addTag(productTag);
			productTag.setSuccess(ParagProduct.SUCCESS_YES);
			productTag.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_TAG_ADD_SUCCESS);
			productTag.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_TAG_ADD_SUCCESS);
		}
		model.addAttribute("entity", productTag);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/manage/tagResult");
		LOGGER.debug("--->>> addParagProduct.resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "manage/tag/delete" }, name = "删除标签")
	public String deleteProductTag(@ModelAttribute("productTag") CoeProductTag productTag,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> deleteProductTag <<<##");

		Long userId = UserUtils.getUserId();
		productTag.setCreatedBy(userId);
		productTag.setLastModifiedBy(userId);
		Long productId = productTag.getMainId();
		//productTag.setProductId(productId);
		boolean checked = coeProductTagValidator.validateDeleteProductTag(errors, productTag);
		if(checked) {
			productTag = coeProductTagWrapper.deleteTag(productTag);
			productTag.setSuccess(ParagProduct.SUCCESS_YES);
			productTag.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_TAG_DELETE_SUCCESS);
			productTag.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_TAG_DELETE_SUCCESS);
		}
		model.addAttribute("entity", productTag);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/manage/tagResult");
		LOGGER.debug("--->>> deleteProductTag.resultPath: {} ---", resultPath);
		return resultPath;
	}
}
