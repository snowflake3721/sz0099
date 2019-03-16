package dml.sz0099.course.app.client.controller.product;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.jit4j.app.persist.entity.auth.User;
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
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dml.sz0099.course.app.client.validator.product.CourseProductValidator;
import dml.sz0099.course.app.client.wrapper.paragraph.ParagProductWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeOrderWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeProductWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.client.wrapper.product.CourseProductWrapper;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.order.CoeOrder;
import dml.sz0099.course.app.persist.entity.paragraph.ParagProduct;
import dml.sz0099.course.app.persist.entity.product.CoeProduct;
import dml.sz0099.course.app.persist.entity.product.bo.CoeProductBo;
import dml.sz0099.course.app.persist.entity.user.CoeUser;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CourseProductController 控制转发
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
public class CourseProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CourseProductController.class);

	@Autowired
	private CourseProductValidator courseProductValidator;

	@Autowired
	private CourseProductWrapper courseProductWrapper;
	
	@Autowired
	private CoeProductWrapper coeProductWrapper;
	
	@Autowired
	private CoeOrderWrapper coeOrderWrapper;
	
	@Autowired
	private CoeUserWrapper coeUserWrapper;
	
	@Autowired
	private ParagProductWrapper paragProductWrapper;

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
	
	
	/*@InitBinder
	public void initBinder(DataBinder binder) {
		binder.setValidator(courseProductValidator);
	}*/

	public CourseProductController() {
		LOGGER.debug("##>>> init CourseProductController <<<##");
	}

	@RequestMapping(value = { "/index" }, name = "访问courseProduct首页")
	public String indexUI(@ModelAttribute("courseProductBo") CoeProductBo courseProductBo, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexUI <<<##");
		PageRequest pageable = null;
		if(page==null) {
			page=0;
		}
		if(size==null) {
			size=20;
		}
		
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}
		LOGGER.debug("========== MAPPING_SUB_PATH_PRODUCT_BASIC: {} ========== ", MAPPING_SUB_PATH_PRODUCT_BASIC);
		pageable = new PageRequest(page, size, Direction.DESC, "refreshTime");
		Page<CoeProduct> pageResult = coeProductWrapper.findByShelved(courseProductBo, pageable);
		model.addAttribute("page", pageResult);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/indexUI");
		LOGGER.debug("--->>>CourseProductController.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/buyInstruction" }, name = "购买说明")
	public String buyInstructionUI(@ModelAttribute("coeProductBo") CoeProduct courseProduct, Integer page, Integer size, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> buyInstructionUI <<<##");
		String resultPath = "";
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录，先让其登录，再进行购买下单操作
			model.addAttribute("entity", courseProduct);
			courseProduct.setSuccess(CoeProduct.SUCCESS_NO);
			courseProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_NOT_LOGIN);
			courseProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_NOT_LOGIN);
			resultPath = "forward:/loginOnlyUI";//ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/loginOnlyUI");
			
			return resultPath;
		}
		courseProduct.setUserId(user.getId());
		boolean checked = courseProductValidator.validateCoeProductUserBuy( errors, courseProduct);
		if(checked) {
			Long id = courseProduct.getId();
			CoeProduct entity = coeProductWrapper.findById(id);
			courseProduct = entity;
			entity.setSuccess(CoeProduct.SUCCESS_YES);
			entity.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_BUY_INSTRUCTION_SUCCESS);
			entity.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_BUY_INSTRUCTION_SUCCESS);
		}
		model.addAttribute("entity", courseProduct);
		
		LOGGER.debug("========== MAPPING_SUB_PATH_PRODUCT_BASIC: {} ========== ", MAPPING_SUB_PATH_PRODUCT_BASIC);
		resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/buyInstruction");
		LOGGER.debug("--->>>CourseProductController.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/preOrder" }, name = "课程下单准备")
	public String preorderUI(@ModelAttribute("courseProductBo") CoeProductBo courseProductBo, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> preorderUI <<<##");
		Long id = courseProductBo.getId();
		CoeProduct entity = coeProductWrapper.findById(id);
		String resultPath = "";
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录，先让其登录，再进行购买下单操作
			LOGGER.debug("!!! preorderUI, user not login !! !");
			model.addAttribute("entity", courseProductBo);
			courseProductBo.setSuccess(CoeProduct.SUCCESS_NO);
			courseProductBo.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_NOT_LOGIN);
			courseProductBo.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_NOT_LOGIN);
			resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/loginUI");
			return resultPath;
		}
		
		boolean checked = courseProductValidator.validateCoeProduct(errors, entity);
		LOGGER.debug("--->>>preorderUI.checked: {} ---", checked);
		if(checked) {
			CoeOrder coeOrder = coeOrderWrapper.generateOrder(entity, user);
			Long userId = user.getId();
			CoeUser coeUser = coeUserWrapper.findByUserId(userId);
			if(null == coeUser) {
				coeUser = new CoeUser();
				coeUser.setEmail(user.getEmail());
				coeUser.setMobile(user.getMobile());
				coeUser.setQq(user.getQq());
			}
			
			LOGGER.debug("--->>>preorderUI. success, userId: {} ---", userId);
			coeOrder.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_FLOWERID_SUCCESS);
			coeOrder.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_FLOWERID_SUCCESS);
			model.addAttribute("entity", coeOrder);
			model.addAttribute("coeUser", coeUser);
			resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/preorder");
		}else {
			model.addAttribute("entity", entity);
			resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/productInvalid");
		}
		
		LOGGER.debug("========== MAPPING_SUB_PATH_PRODUCT_BASIC: {} ========== ", MAPPING_SUB_PATH_PRODUCT_BASIC);
		
		LOGGER.debug("--->>>CourseProductController.resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "/courseProduct/sub" }, name = "访问courseProductsub页面")
	public String accessCourseProductSub(@ModelAttribute("courseProductBo") CoeProductBo courseProductBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCourseProduct <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_PRODUCT_BASIC,"/courseProduct");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@RequestMapping(value = { "/manage/create" }, name = "录入产品UI")
	public String createUI(@ModelAttribute("entity") CoeProduct courseProduct, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> createUI <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT, "/manage/productCreate");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			courseProduct.setUserId(userId);
			
			Long id = courseProduct.getId();
			CoeProduct entity = null;
			if (null != id) {
				entity = coeProductWrapper.findById(id);
			}
			if(entity == null) {
			
				Long draftNum = courseProductValidator.countDraftList(courseProduct);
				model.addAttribute("draftNum", draftNum);
				if(null != draftNum && draftNum>5) {
					model.addAttribute("entity", courseProduct);
					resultPath = "forward:/sz0099/ood/product/manage/queryDraftList";
				} else {
					// 生成产品草稿信息
					// DETAIL_URL
					courseProduct.setOriginalLink(DETAIL_URL);//产品详情链接
					courseProduct.setCreatedBy(userId);
					courseProduct.setLastModifiedBy(userId);
					entity = coeProductWrapper.createDraft(courseProduct);
				}

			}
			model.addAttribute("entity", entity);
		}
		LOGGER.debug("========== MAPPING_PATH_PRODUCT: {} ========== ", MAPPING_PATH_PRODUCT);
		LOGGER.debug("--->>>createUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/queryDraftList" }, name = "查询用户草稿列表")
	public String queryDraftList(@ModelAttribute("entity") CoeProductBo courseProductBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryDraftList <<<##");
		
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录
		}else {
			Long userId = user.getId();
			courseProductBo.setUserId(userId);
			courseProductValidator.countDraftList(courseProductBo);
			List<CoeProduct> draftList = coeProductWrapper.findDraftList(courseProductBo);
			model.addAttribute("draftList", draftList);
		}
		LOGGER.debug("========== MAPPING_PATH_PRODUCT: {} ========== ", MAPPING_PATH_PRODUCT);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/manage/productDraftList");
		LOGGER.debug("--->>>queryDraftList.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	//查询已发布的产品
	@RequestMapping(value = { "/manage/queryProductList" }, name = "查询用户产品列表")
	public String queryProductList(@ModelAttribute("entity") CoeProduct coeProduct, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryProductList <<<##");
		
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录
		}else {
			Long userId = user.getId();
			coeProduct.setUserId(userId);
			if(page==null) {
				page=0;
			}
			if(size==null) {
				size=20;
			}
			PageRequest pageable = new PageRequest(page,size,Direction.DESC, "id");
			Page<CoeProduct> pageResult = coeProductWrapper.findPublished(coeProduct, pageable);
			model.addAttribute("page", pageResult);
		}
		model.addAttribute("entity", coeProduct);
		LOGGER.debug("========== MAPPING_PATH_PRODUCT: {} ========== ", MAPPING_PATH_PRODUCT);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/manage/productDetailList");
		LOGGER.debug("--->>>queryProductList.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	//只有已发布，已上架，未被禁用与删除的才可以查看
	public static final String DETAIL_URL="/sz0099/ood/product/detail/{id}";
	@RequestMapping(value = { "detail/{id}"}, name = "查询产品详情，供客户浏览")
	public String queryProductDetail(@PathVariable("id")Long id, @ModelAttribute("entity") CoeProduct product, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryProductDetail <<<##");
		boolean checked = courseProductValidator.validateExist( product);
		
		Page<ParagProduct> pageresult = null;
		if(checked) {
			CoeProduct entity = coeProductWrapper.findDetail(id);
			product=entity;
			
			if(page==null) {
				page=0;
			}
			if(size==null) {
				size=30;
			}
				Long userId = product.getUserId();
				PageRequest pageable = new PageRequest(page,size,Direction.ASC, "orderSeq");
				pageresult = paragProductWrapper.findByMainIdAndUserId(id, userId, pageable);
		}
		model.addAttribute("entity", product);
		model.addAttribute("page", pageresult);
		
		LOGGER.debug("========== MAPPING_PATH_PRODUCT: {} ========== ", MAPPING_PATH_PRODUCT);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/productViewDetail");
		LOGGER.debug("--->>>queryProductDetail.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/detail/{id}" }, name = "查询产品详情，供发布者本人浏览")
	public String queryProductDetailForManage(@PathVariable("id")Long id, @ModelAttribute("entity") CoeProduct product, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryProductDetail <<<##");
		
		boolean checked = courseProductValidator.validateExist( product);
		Page<ParagProduct> pageresult = null;
		if(checked) {
			CoeProduct entity = coeProductWrapper.findDetail(id);
			product = entity;
			if(page==null) {
				page=0;
			}
			if(size==null) {
				size=30;
			}
			Long userId = product.getUserId();
			PageRequest pageable = new PageRequest(page,size,Direction.ASC, "orderSeq");
			pageresult = paragProductWrapper.findByMainIdAndUserId(id, userId, pageable);
			
		}
		model.addAttribute("entity", product);
		model.addAttribute("page", pageresult);
		LOGGER.debug("========== MAPPING_PATH_PRODUCT: {} ========== ", MAPPING_PATH_PRODUCT);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/manage/productDetail");
		LOGGER.debug("--->>>queryProductDetail.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	// 查询已发布的产品
	@RequestMapping(value = { "/manage/queryProductManageList" }, name = "查询产品列表，快速编辑")
	public String queryProductManageList(@ModelAttribute("entity") CoeProduct coeProduct, Integer page, Integer size, Model model,
			HttpServletRequest request) {
		LOGGER.debug("##>>> queryProductManageList <<<##");

		User user = UserUtils.getUser();
		if (null == user) {
			// 用户尚未登录
		} else {
			Long userId = user.getId();
			coeProduct.setUserId(userId);
			if (page == null) {
				page = 0;
			}
			if (size == null) {
				size = 20;
			}
			PageRequest pageable = new PageRequest(page, size, Direction.DESC, "id");
			Page<CoeProduct> pageResult = coeProductWrapper.findPublished(coeProduct, pageable);
			model.addAttribute("page", pageResult);
		}
		model.addAttribute("entity", coeProduct);
		LOGGER.debug("========== MAPPING_PATH_PRODUCT: {} ========== ", MAPPING_PATH_PRODUCT);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT, "/manage/productManageList");
		LOGGER.debug("--->>>queryProductManageList.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	@RequestMapping(value = { "/manage/merge/baseinfo" }, name = "录入产品基本信息")
	public String mergeBaseinfo(@ModelAttribute("entity") CoeProduct coeProduct, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeBaseinfo <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			coeProduct.setUserId(userId);
			
			coeProduct.setLastModifiedBy(userId);
			boolean checked = courseProductValidator.validateBaseinfo(errors, coeProduct);
			if(checked) {
				CoeProduct entity = coeProductWrapper.mergeForBaseinfo(coeProduct);
				entity.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_MERGE_BASEINFO_SUCCESS);
				entity.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_MERGE_BASEINFO_SUCCESS);
				model.addAttribute("entity", entity);
			}

		}
		LOGGER.debug("========== MAPPING_PATH_PRODUCT: {} ========== ", MAPPING_PATH_PRODUCT);
		LOGGER.debug("--->>>mergeBaseinfo.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/merge/price" }, name = "录入产品设置价格")
	public String mergePrice(@ModelAttribute("entity") CoeProduct coeProduct, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergePrice <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数
		}else {
			Long userId = user.getId();
			coeProduct.setUserId(userId);
			
			coeProduct.setLastModifiedBy(userId);
			boolean checked = courseProductValidator.validatePrice(errors, coeProduct);
			if(checked) {
				CoeProduct entity = coeProductWrapper.mergeProductForPrice(coeProduct);
				entity.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_MERGE_PRICE_SUCCESS);
				entity.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_MERGE_PRICE_SUCCESS);
				model.addAttribute("entity", entity);
			}

		}
		LOGGER.debug("========== MAPPING_PATH_PRODUCT: {} ========== ", MAPPING_PATH_PRODUCT);
		LOGGER.debug("--->>>mergePrice.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/merge/shelved" }, name = "上下架产品")
	public String mergeForShelved(@ModelAttribute("entity") CoeProduct coeProduct, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForShelved <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数
		}else {
			Long userId = user.getId();
			coeProduct.setUserId(userId);
			
			coeProduct.setLastModifiedBy(userId);
			boolean checked = courseProductValidator.validateForShelved(errors, coeProduct);
			if(checked) {
				CoeProduct entity = coeProductWrapper.mergeShelved(coeProduct);
				entity.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_MERGE_SHELVED_SUCCESS);
				entity.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_MERGE_SHELVED_SUCCESS);
				model.addAttribute("entity", entity);
			}

		}
		LOGGER.debug("========== MAPPING_PATH_PRODUCT: {} ========== ", MAPPING_PATH_PRODUCT);
		LOGGER.debug("--->>>mergeForShelved.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@RequestMapping(value = { "/manage/merge/refresh" }, name = "刷新产品时间")
	public String mergeForRefresh(@ModelAttribute("entity") CoeProduct coeProduct, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForRefresh <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT, "/manage/refreshResult");
		if(null == user) {
			//用户尚未登录，保存请求参数
		}else {
			Long userId = user.getId();
			coeProduct.setUserId(userId);
			coeProduct.setLastModifiedBy(userId);
			boolean checked = courseProductValidator.validateForRefresh(errors, coeProduct);
			if(checked) {
				CoeProduct entity = coeProductWrapper.mergeForRefresh(coeProduct);
				entity.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_REFRESH_SUCCESS);
				entity.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_REFRESH_SUCCESS);
				model.addAttribute("entity", entity);
			}
		}
		LOGGER.debug("========== MAPPING_PATH_PRODUCT: {} ========== ", MAPPING_PATH_PRODUCT);
		LOGGER.debug("--->>>mergeForRefresh.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@RequestMapping(value = { "/manage/merge/editquickly" }, name = "快速编辑")
	public String editQuickly(@ModelAttribute("entity") CoeProduct coeProduct, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> editQuickly <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数
		}else {
			Long userId = user.getId();
			coeProduct.setUserId(userId);
			coeProduct.setLastModifiedBy(userId);
			boolean checked = courseProductValidator.validateForEditQuickly(errors, coeProduct);
			if(checked) {
				CoeProduct entity = coeProductWrapper.mergeForEditQickly(coeProduct);
				entity.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_EDIT_QUICKLY_SUCCESS);
				entity.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_EDIT_QUICKLY_SUCCESS);
				model.addAttribute("entity", entity);
			}
		}
		LOGGER.debug("========== MAPPING_PATH_PRODUCT: {} ========== ", MAPPING_PATH_PRODUCT);
		LOGGER.debug("--->>>editQuickly.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@RequestMapping(value = { "/manage/merge/publish" }, name = "发布产品")
	public String mergeForPublish(@ModelAttribute("entity") CoeProduct coeProduct, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForPublish <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			coeProduct.setUserId(userId);
			
			coeProduct.setLastModifiedBy(userId);
			boolean checked = courseProductValidator.validateForPublish(errors, coeProduct);
			if(checked) {
				CoeProduct entity = coeProductWrapper.mergeForPublish(coeProduct);
				entity.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_MERGE_PUBLISH_SUCCESS);
				entity.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_MERGE_PUBLISH_SUCCESS);
				model.addAttribute("entity", entity);
			}
		}
		LOGGER.debug("========== MAPPING_PATH_PRODUCT: {} ========== ", MAPPING_PATH_PRODUCT);
		LOGGER.debug("--->>>mergeForPublish.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/merge/closed" }, name = "关闭产品")
	public String mergeForClosed(@ModelAttribute("entity") CoeProduct coeProduct, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForClosed <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			coeProduct.setUserId(userId);
			
			coeProduct.setLastModifiedBy(userId);
			boolean checked = courseProductValidator.validateForClosed(errors, coeProduct);
			if(checked) {
				CoeProduct entity = coeProductWrapper.mergeForClosed(coeProduct);
				entity.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_MERGE_CLOSED_SUCCESS);
				entity.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_MERGE_CLOSED_SUCCESS);
				model.addAttribute("entity", entity);
			}
		}
		LOGGER.debug("========== MAPPING_PATH_PRODUCT: {} ========== ", MAPPING_PATH_PRODUCT);
		LOGGER.debug("--->>>mergeForClosed.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/merge/deleted" }, name = "删除产品")
	public String mergeForDeleted(@ModelAttribute("entity") CoeProduct coeProduct, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForDeleted <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			coeProduct.setUserId(userId);
			
			coeProduct.setLastModifiedBy(userId);
			boolean checked = courseProductValidator.validateForDeleted(errors, coeProduct);
			if(checked) {
				CoeProduct entity = coeProductWrapper.mergeForDeleted(coeProduct);
				entity.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_MERGE_DELERED_SUCCESS);
				entity.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_MERGE_DELERED_SUCCESS);
				model.addAttribute("entity", entity);
			}
		}
		LOGGER.debug("========== MAPPING_PATH_PRODUCT: {} ========== ", MAPPING_PATH_PRODUCT);
		LOGGER.debug("--->>>mergeForDeleted.resultPath: {} ---", resultPath);
		return resultPath;
	}
	

}
