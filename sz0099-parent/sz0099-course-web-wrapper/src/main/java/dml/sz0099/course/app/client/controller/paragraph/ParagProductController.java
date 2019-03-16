package dml.sz0099.course.app.client.controller.paragraph;

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

import dml.sz0099.course.app.client.resolver.adaptor.media.ParagraphAdaptor;
import dml.sz0099.course.app.client.validator.paragraph.ParagProductValidator;
import dml.sz0099.course.app.client.wrapper.paragraph.ParagProductWrapper;
import dml.sz0099.course.app.client.wrapper.paragraph.ParagraphWrapper;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.paragraph.ParagProduct;
import dml.sz0099.course.app.persist.entity.paragraph.bo.ParagProductBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ParagProductController 控制转发
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
public class ParagProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParagProductController.class);

	@Autowired
	private ParagProductValidator paragProductValidator;

	@Autowired
	private ParagProductWrapper paragProductWrapper;
	
	@Autowired
	private ParagraphWrapper paragraphWrapper;
	
	@Autowired
	private ParagraphAdaptor paragraphAdaptor;

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
		LOGGER.debug("========== MAPPING_SUB_PATH_PARAGRAPH: {} ========== ", MAPPING_SUB_PATH_PRODUCT_BASIC);
	}
	

	public ParagProductController() {
		LOGGER.debug("##>>> init ParagProductController <<<##");
	}


	/**
	 * 个人编辑或管理人员访问
	 * @param paragProductBo
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "manage/paragraph/editListUI" }, name = "访问段落编辑页")
	public String editParagProductList(@ModelAttribute("entity") ParagProduct paragProduct, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> editParagProductList <<<##");

		Long userId = UserUtils.getUserId();
		paragProduct.setUserId(userId);
		paragProduct.setCreatedBy(userId);
		Long productId = paragProduct.getMainId();
		boolean checked = paragProductValidator.validateExistProduct(paragProduct);
		if(checked) {
			if(page==null) {
				page=0;
			}
			if(size==null) {
				size=30;
			}
			PageRequest pageable = new PageRequest(page,size,Direction.ASC, "orderSeq");
			Page<ParagProduct> pageresult = paragProductWrapper.findByMainIdAndUserId(productId, userId, pageable);
			long total = pageresult.getTotalElements();
			if(total==0) {
				//创建一个段落，执行保存
				paragProduct=paragProductWrapper.createParagProduct(paragProduct);
			}else {
				model.addAttribute("page", pageresult);
			}
		}
		model.addAttribute("entity", paragProduct);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/manage/paragraphEditListUI");
		LOGGER.debug("--->>> editParagProductList.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/paragraph/add" }, name = "添加段落")
	public String addParagProduct(@ModelAttribute("paragProduct") ParagProduct paragProduct, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> addParagProduct <<<##");

		Long userId = UserUtils.getUserId();
		paragProduct.setUserId(userId);
		paragProduct.setCreatedBy(userId);
		Long productId = paragProduct.getMainId();
		boolean checked = paragProductValidator.validateAddProduct(paragProduct);
		if(checked) {
			paragProduct = paragProductWrapper.createParagProduct(paragProduct);
			paragProduct.setSuccess(ParagProduct.SUCCESS_YES);
			paragProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PARAGRAGH_ADD_SUCCESS);
			paragProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PARAGRAGH_ADD_SUCCESS);
		
		}
		model.addAttribute("entity", paragProduct);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/manage/paragraphAddUI");
		LOGGER.debug("--->>> addParagProduct.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/paragraph/mergeSingle" }, name = "保存段落")
	public String mergeParagProduct(@ModelAttribute("paragProduct") ParagProduct paragProduct, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeParagProduct <<<##");

		Long userId = UserUtils.getUserId();
		paragProduct.setUserId(userId);
		paragProduct.setLastModifiedBy(userId);
		paragProduct.getId();
		Long productId = paragProduct.getMainId();
		boolean checked = paragProductValidator.validateMergeProduct(paragProduct);
		if(checked) {
			paragProduct = paragProductWrapper.mergeEntity(paragProduct);
			paragProduct.setSuccess(ParagProduct.SUCCESS_YES);
			paragProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PARAGRAGH_MERGE_SUCCESS);
			paragProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PARAGRAGH_MERGE_SUCCESS);
		}
		model.addAttribute("entity", paragProduct);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/manage/commonResult");
		LOGGER.debug("--->>> mergeParagProduct.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/paragraph/deleteSingle" }, name = "删除段落")
	public String deleteParagProduct(@ModelAttribute("paragProduct") ParagProduct paragProduct, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> deleteParagProduct <<<##");

		Long userId = UserUtils.getUserId();
		paragProduct.setUserId(userId);
		paragProduct.setLastModifiedBy(userId);
		paragProduct.getId();
		Long productId = paragProduct.getMainId();
		boolean checked = paragProductValidator.validateForDelete(paragProduct);
		if(checked) {
			paragraphAdaptor.deleteFromRemote(paragProduct);
			paragProductWrapper.deleteByParagIdAndUserId(paragProduct.getParagId(), userId, true);
			paragProduct.setSuccess(ParagProduct.SUCCESS_YES);
			paragProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PARAGRAGH_DELETE_SUCCESS);
			paragProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PARAGRAGH_DELETE_SUCCESS);
		}
		model.addAttribute("entity", paragProduct);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/manage/commonResult");
		LOGGER.debug("--->>> deleteParagProduct.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/paragraph/deleteAll" }, name = "删除全部段落")
	public String deleteParagProductAll(@ModelAttribute("paragProduct") ParagProduct paragProduct, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> deleteParagProductAll <<<##");

		Long userId = UserUtils.getUserId();
		paragProduct.setUserId(userId);
		paragProduct.setLastModifiedBy(userId);
		paragProduct.getId();
		Long productId = paragProduct.getMainId();
		//paragProduct.setProductId(productId);
		boolean checked = paragProductValidator.validateForDeleteAll(paragProduct);
		if(checked) {
			paragraphAdaptor.deleteFromRemote(paragProduct);
			paragProductWrapper.deleteByMainIdAndUserId(productId, userId);
			paragProduct.setSuccess(ParagProduct.SUCCESS_YES);
			paragProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PARAGRAGH_DELETE_ALL_SUCCESS);
			paragProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PARAGRAGH_DELETE_ALL_SUCCESS);
		}
		model.addAttribute("entity", paragProduct);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/manage/commonResult");
		LOGGER.debug("--->>> deleteParagProductAll.resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "/paragProduct/sub" }, name = "访问paragProductsub页面")
	public String accessParagProductSub(@ModelAttribute("paragProductBo") ParagProductBo paragProductBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessParagProduct <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_PRODUCT_BASIC,"/paragProduct");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	

}
