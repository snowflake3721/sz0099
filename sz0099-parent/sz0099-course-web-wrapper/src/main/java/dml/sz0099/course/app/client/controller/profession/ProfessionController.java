package dml.sz0099.course.app.client.controller.profession;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.jit4j.app.persist.entity.auth.User;
import org.jit4j.core.persist.page.PageRequest;
import org.jit4j.core.pub.transfer.dto.JsonResult;
import org.jit4j.core.webmvc.config.ResourcePathConfig;
import org.jit4j.core.webmvc.util.UserUtils;
import org.jit8j.core.util.GsonBuilderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dml.sz0099.course.app.client.resolver.adaptor.position.PositionProfessionAdaptor;
import dml.sz0099.course.app.client.validator.profession.ProfessionValidator;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.client.wrapper.profession.ParagProfessionWrapper;
import dml.sz0099.course.app.client.wrapper.profession.ProfessionWrapper;
import dml.sz0099.course.app.data.show.blooming.CircleMain;
import dml.sz0099.course.app.data.show.blooming.converter.ProfessionConverter;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.entity.profession.ParagProfession;
import dml.sz0099.course.app.persist.entity.profession.Profession;
import dml.sz0099.course.app.persist.entity.profession.bo.ProfessionBo;
import dml.sz0099.course.app.persist.entity.user.CoeUser;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ProfessionController 控制转发
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
@Controller("profProfessionController")
public class ProfessionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionController.class);

	private static final Marker PROFESSION_DESCRIPTION_FILE=MarkerFactory.getMarker("ProfessionDescriptionFile");
	private static final Marker PROFESSION_TITLE_FILE=MarkerFactory.getMarker("ProfessionTitleFile");
	
	
	@Autowired
	private ProfessionValidator professionValidator;

	@Autowired
	private ProfessionWrapper professionWrapper;
	
	@Autowired
	private ParagProfessionWrapper paragProfessionWrapper;
	
	@Autowired
	private ProfessionConverter professionConverter;
	
	@Autowired
	private PositionProfessionAdaptor positionProfessionAdaptor;
	
	@Autowired
	private CoeUserWrapper coeUserWrapper;
	
	
	

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
	
	

	public ProfessionController() {
		LOGGER.debug("##>>> init ProfessionController <<<##");
	}
	
	
		
	@RequestMapping(value = { "profession/index" }, name = "访问技能首页")
	public String indexUI(@ModelAttribute("profession") Profession profession, Integer page, Integer size, Model model, HttpServletRequest request) {
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
		}else {
			pageable = new PageRequest(page, size, Direction.DESC, "refreshTime");
			Page<Profession> pageResult = professionWrapper.findByPublished(profession, pageable);
			model.addAttribute("page", pageResult);
			model.addAttribute("position", new Position());
			
			Long mainId = null;
			Long subId = null;
			Category categoryTree = professionWrapper.findCategoryTree(mainId, subId);
			model.addAttribute("categoryTree", categoryTree);
			
			//邀请人技能列表
			Long userId=user.getId();
			Long createdBy = user.getCreatedBy();
			Page<Profession> invitorPage = professionWrapper.findPageForInvitor(createdBy,userId);
			model.addAttribute("invitorPage", invitorPage);
			
			CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
			model.addAttribute("currentUser", coeUser);
			//将分页技能组装成圆形菜单数据
			List<Profession> contentList = invitorPage.getContent();
			CircleMain circleMain = professionConverter.convert(contentList, coeUser);
			model.addAttribute("circleMain", GsonBuilderUtils.toJson(circleMain));
			
			//当前登录用户技能标签
			Page<Profession>  tagPage = professionWrapper.findPageTagForCurrentUser(userId);
			model.addAttribute("tagPage", tagPage);
			
			//今日神技 大屏
			
			
			//能工巧匠 双栏图文列表
			
			
		}
		
		
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/indexUI");
		LOGGER.debug("--->>>ProfessionController.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@ResponseBody
	@RequestMapping(value = { "profession/findPageForInvitor" }, name = "邀请人技能圆形菜单")
	public JsonResult<CircleMain> findPageForInvitor(@ModelAttribute("article") Profession article, Model model, HttpServletRequest request) {
		User user = UserUtils.getUser();
		JsonResult<CircleMain> result = new JsonResult<>();
		if (null != user) {
			long t9 = System.currentTimeMillis();
			// 3.邀请人前三篇文章列表
			Long userId = user.getId();
			Long createdBy = user.getCreatedBy();
			Page<Profession> invitorPage = professionWrapper.findPageForCurrentUser(userId);
			// Page<Profession> invitorPage = professionWrapper.findPageForInvitor(createdBy, userId);
			model.addAttribute("invitorPage", invitorPage);

			CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
			model.addAttribute("currentUser", coeUser);
			// 将分页技能组装成圆形菜单数据
			List<Profession> contentList = invitorPage.getContent();
			CircleMain circleMain = professionConverter.convert(contentList, coeUser);
			model.addAttribute("circleMain", GsonBuilderUtils.toJson(circleMain));

			long t10 = System.currentTimeMillis();
			LOGGER.debug("t10-t9= {} ms", t10 - t9);
			result.setContent(circleMain);
			result.setSuccess(Profession.SUCCESS_YES);
			result.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_FIND_MAINCIRCLE_SUCCESS);
			result.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_FIND_MAINCIRCLE_SUCCESS);
		} else {
			result.setSuccess(Profession.SUCCESS_NO);
			result.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_NOT_LOGIN);
			result.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_NOT_LOGIN);
		}
		LOGGER.debug("--->>>findPageForInvitor.result: {} ---", result.getRespCode());
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = { "profession/findByUserId" }, name = "技能圆形菜单")
	public JsonResult<CircleMain> findByUserId(@ModelAttribute("profession") Profession profession,  Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> findByUserId <<<##");
		
		User user = UserUtils.getUser();
		JsonResult<CircleMain> result = new JsonResult<>();
		if(null != user){
			
			//指定人技能列表
			Long userId=profession.getUserId();
			
			Page<Profession> fixUserPage = professionWrapper.findPageForCurrentUser(userId);
			model.addAttribute("fixUserPage", fixUserPage);
			
			CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
			model.addAttribute("currentUser", coeUser);
			//将分页技能组装成圆形菜单数据
			List<Profession> contentList = fixUserPage.getContent();
			CircleMain circleMain = professionConverter.convert(contentList, coeUser);
			//model.addAttribute("circleMain", GsonBuilderUtils.toJson(circleMain));
			result.setContent(circleMain);
			result.setSuccess(Profession.SUCCESS_YES);
			result.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_FIND_MAINCIRCLE_SUCCESS);
			result.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_FIND_MAINCIRCLE_SUCCESS);
		}else {
			result.setSuccess(Profession.SUCCESS_NO);
			result.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_NOT_LOGIN);
			result.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_NOT_LOGIN);
		}
		
		LOGGER.debug("--->>>findByUserId.result: {} ---", result.getRespCode());
		return result;
	}
	
	public static final String LINK_HUAN="/sz0099/ood/personal/profession/changeByUserId/";
	@ResponseBody
	@RequestMapping(value = { "profession/changeByUserId/{userId}" }, name = "技能圆形菜单[换]")
	public JsonResult<CircleMain> changeByUserId(@PathVariable("userId") Long userId, @ModelAttribute("profession") Profession profession,  Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> changeByUserId <<<##");
		
		User user = UserUtils.getUser();
		JsonResult<CircleMain> result = new JsonResult<>();
		if(null != user){
			
			if(userId==null) {
				userId=profession.getUserId();
			}
			//邀请者id
			//查询绑定该邀请者的绑定者id,如果没绑定，则系统随机取一个发布了的技能用户
			if(null != userId) {
				Page<Profession> fixUserPage = professionWrapper.findPageForCurrentUser(userId);
				model.addAttribute("fixUserPage", fixUserPage);
				
				CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
				model.addAttribute("currentUser", coeUser);
				//将分页技能组装成圆形菜单数据
				List<Profession> contentList = fixUserPage.getContent();
				CircleMain circleMain = professionConverter.convert(contentList, coeUser);
				if(null != circleMain) {
					result.setContent(circleMain);
					result.setSuccess(Profession.SUCCESS_YES);
					result.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_FIND_MAINCIRCLE_SUCCESS);
					result.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_FIND_MAINCIRCLE_SUCCESS);
				}else {
					result.setSuccess(Profession.SUCCESS_NO);
					result.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_USER_EMPTY);
					result.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_USER_EMPTY);
				}
			}else {
				result.setSuccess(Profession.SUCCESS_NO);
				result.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_USERID_EMPTY);
				result.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_USERID_EMPTY);
			}
		}else {
			result.setSuccess(Profession.SUCCESS_NO);
			result.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_NOT_LOGIN);
			result.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_NOT_LOGIN);
		}
		
		LOGGER.debug("--->>>changeByUserId.result: {} ---", result.getRespCode());
		return result;
	}
	
	public static final String LINK_BANG="/sz0099/ood/personal/profession/findPageRefForUser/";
	@RequestMapping(value = { "/profession/findPageRefForUser/{userId}" }, name = "查询用户技能分页带关联文章")
	public String findPageRefForUser(@PathVariable("userId") Long userId, 
			@ModelAttribute("entity") Profession profession, 
			Integer page, Integer size, 
			String linkTitle, String linkBack,
			Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> findPageRefForUser <<<##");
		PageRequest pageable = null;
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=5;
		}
		if(userId != null) {
			profession.setUserId(userId);
		}
		boolean checked = professionValidator.validateForUser(profession);
		if(checked) {
			User user = UserUtils.getUser();
			if(null == user) {
				//用户尚未登录
			}else {
				pageable = new PageRequest(page, size, Direction.DESC, "refreshTime");
				
				Page<Profession> refPage = professionWrapper.findPageRefForUser(profession, pageable);
				model.addAttribute("entityPage", refPage);
			}
		}
		model.addAttribute("linkTitle", linkTitle);
		model.addAttribute("linkBack", linkBack);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/professionWithRefPage");
		LOGGER.debug("--->>>findPageRefForUser.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/profession/manage/queryProfessionList" }, name = "查询用户技能列表")
	public String queryProfessionList(@ModelAttribute("entity") ProfessionBo profession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryProfessionList <<<##");
		
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录
		}else {
			Long userId = user.getId();
			profession.setUserId(userId);
			if(page==null || page<0) {
				page=0;
			}
			if(size==null || size>50) {
				size=20;
			}
			PageRequest pageable = new PageRequest(page,size,Direction.DESC, "id");
			Page<Profession> pageResult = professionWrapper.findPageByUserId(userId,pageable,true,true);
			model.addAttribute("entityPage", pageResult);
		}
		LOGGER.debug("========== MAPPING_PATH_PERSONAL: {} ========== ", MAPPING_PATH_PERSONAL);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/manage/professionDraftList");
		LOGGER.debug("--->>>queryProfessionList.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/profession/manage/queryProfessionList/ansy" }, name = "异步查询用户技能列表")
	public String queryProfessionListAnsy(@ModelAttribute("entity") ProfessionBo profession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryProfessionListAnsy <<<##");
		
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录
		}else {
			Long userId = user.getId();
			profession.setUserId(userId);
			if(page==null || page<0) {
				page=0;
			}
			if(size==null || size>50) {
				size=20;
			}
			PageRequest pageable = new PageRequest(page,size,Direction.DESC, "id");
			Page<Profession> pageResult = professionWrapper.findPageByUserId(userId,pageable,true,true);
			model.addAttribute("entityPage", pageResult);
		}
		LOGGER.debug("========== MAPPING_PATH_PERSONAL: {} ========== ", MAPPING_PATH_PERSONAL);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/manage/professionDraftListAnsy");
		LOGGER.debug("--->>>queryProfessionListAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	//查询已发布的技能
	@RequestMapping(value = { "/profession/manage/queryDetailList" }, name = "技能详情列表")
	public String queryDetailList(@ModelAttribute("entity") Profession profession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryDetailList <<<##");
		
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录
		}else {
			Long userId = user.getId();
			profession.setUserId(userId);
			if(page==null || page<0) {
				page=0;
			}
			if(size==null || size>50) {
				size=20;
			}
			PageRequest pageable = new PageRequest(page,size,Direction.DESC, "id");
			Page<Profession> pageResult = professionWrapper.findPublishedWithCoverAndBanner(profession, pageable);
			model.addAttribute("page", pageResult);
		}
		model.addAttribute("entity", profession);
		LOGGER.debug("========== MAPPING_PATH_PERSONAL: {} ========== ", MAPPING_PATH_PERSONAL);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/manage/professionDetailList");
		LOGGER.debug("--->>>queryDetailList.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/profession/manage/queryDetailList/ansy" }, name = "异步查询技能详情列表")
	public String queryDetailListAnsy(@ModelAttribute("entity") Profession profession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryDetailListAnsy <<<##");
		
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录
		}else {
			Long userId = user.getId();
			profession.setUserId(userId);
			if(page==null || page<0) {
				page=0;
			}
			if(size==null || size>50) {
				size=20;
			}
			PageRequest pageable = new PageRequest(page,size,Direction.DESC, "id");
			Page<Profession> pageResult = professionWrapper.findPublishedWithCoverAndBanner(profession, pageable);
			model.addAttribute("page", pageResult);
		}
		model.addAttribute("entity", profession);
		LOGGER.debug("========== MAPPING_PATH_PERSONAL: {} ========== ", MAPPING_PATH_PERSONAL);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/manage/professionDetailListAnsy");
		LOGGER.debug("--->>>queryDetailListAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
		
	// 查询已发布的技能，用户快速编辑
	@RequestMapping(value = { "profession/manage/queryProfessionManageList" }, name = "快速编辑")
	public String queryProfessionManageList(@ModelAttribute("entity") Profession profession, Integer page, Integer size, Model model,
			HttpServletRequest request) {
		LOGGER.debug("##>>> queryProfessionManageList <<<##");

		User user = UserUtils.getUser();
		if (null == user) {
			// 用户尚未登录
		} else {
			Long userId = user.getId();
			profession.setUserId(userId);
			if(page==null || page<0) {
				page=0;
			}
			if(size==null || size>50) {
				size=20;
			}
			PageRequest pageable = new PageRequest(page, size, Direction.DESC, "id");
			Page<Profession> pageResult = professionWrapper.findPublished(profession, pageable);
			model.addAttribute("page", pageResult);
		}
		model.addAttribute("entity", profession);
		LOGGER.debug("========== MAPPING_PATH_PERSONAL: {} ========== ", MAPPING_PATH_PERSONAL);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL, "/manage/professionManageList");
		LOGGER.debug("--->>>queryProfessionManageList.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	// 查询已发布的技能，用户快速编辑
	@RequestMapping(value = { "profession/manage/queryProfessionManageList/ansy" }, name = "快速编辑")
	public String queryProfessionManageListAnsy(@ModelAttribute("entity") Profession profession, Integer page, Integer size, Model model,
			HttpServletRequest request) {
		LOGGER.debug("##>>> queryProfessionManageListAnsy <<<##");

		User user = UserUtils.getUser();
		if (null == user) {
			// 用户尚未登录
		} else {
			Long userId = user.getId();
			profession.setUserId(userId);
			if(page==null || page<0) {
				page=0;
			}
			if(size==null || size>50) {
				size=20;
			}
			PageRequest pageable = new PageRequest(page, size, Direction.DESC, "id");
			Page<Profession> pageResult = professionWrapper.findPublished(profession, pageable);
			model.addAttribute("page", pageResult);
		}
		model.addAttribute("entity", profession);
		LOGGER.debug("========== MAPPING_PATH_PERSONAL: {} ========== ", MAPPING_PATH_PERSONAL);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL, "/manage/professionManageListAnsy");
		LOGGER.debug("--->>>queryProfessionManageListAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "profession/manage/searchProfessionList/ansy" }, name = "异步搜索查询用户技能列表")
	public String searchProfessionListAnsy(@ModelAttribute("entity") Profession profession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> searchProfessionListAnsy <<<##");
		
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录
		}else {
			Long userId = user.getId();
			profession.setUserId(userId);
			if(page==null || page<0) {
				page=0;
			}
			if(size==null || size>50) {
				size=20;
			}
			PageRequest pageable = new PageRequest(page,size,Direction.DESC, "id");
			Page<Profession> pageResult = professionWrapper.findPublishedWithCoverAndBanner(profession, pageable);
			model.addAttribute("page", pageResult);
		}
		model.addAttribute("entity", profession);
		LOGGER.debug("========== MAPPING_PATH_PERSONAL: {} ========== ", MAPPING_PATH_PERSONAL);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/manage/searchProfessionListAnsy");
		LOGGER.debug("--->>>searchProfessionListAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
		

	
	public static final String DETAIL_URL="/sz0099/ood/personal/profession/detail/{id}";
	@RequestMapping(value = { "/profession/manage/create" }, name = "添加技能UI")
	public String createUI(@ModelAttribute("entity") Profession profession, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> createUI <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL, "/manage/professionCreate");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			profession.setUserId(userId);
			
			Long id = profession.getId();
			Profession entity = null;
			if (null != id) {
				entity = professionWrapper.findById(id);
			}
			if(entity == null) {
			
				Long draftNum = professionValidator.countByUserId(profession);
				model.addAttribute("draftNum", draftNum);
				if(null != draftNum && draftNum>=3) {
					model.addAttribute("entity", profession);
					resultPath = "forward:/sz0099/ood/personal/profession/queryProfessionList";
				} else {
					// 生成技能信息
					// DETAIL_URL
					profession.setOriginalLink(DETAIL_URL);//技能详情链接
					profession.setCreatedBy(userId);
					profession.setLastModifiedBy(userId);
					//professionValidator.valida
					entity = professionWrapper.createDraft(profession);
					return "redirect:/sz0099/ood/personal/profession/manage/create?id="+entity.getId();
				}

			}
			model.addAttribute("entity", entity);
		}
		LOGGER.debug("========== MAPPING_PATH_PERSONAL: {} ========== ", MAPPING_PATH_PERSONAL);
		LOGGER.debug("--->>>createUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "profession/detail/{id}"}, name = "查询技能详情，供客户浏览")
	public String queryProfessionDetail(@PathVariable("id")Long id, @ModelAttribute("entity") Profession profession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryProfessionDetail <<<##");
		
		Long userId2 = UserUtils.getUserId();
		boolean checked = professionValidator.validateExist( profession);
		
		Page<ParagProfession> pageresult = null;
		if(checked) {
			Profession entity = professionWrapper.findDetailFilledMainTypeList(id);
			if(null != entity) {
				profession=entity;
				if(page==null) {
					page=0;
				}
				if(size==null || size>35) {
					size=30;
				}
				Long userId = profession.getUserId();
				PageRequest pageable = new PageRequest(page,size,Direction.ASC, "orderSeq");
				pageresult = paragProfessionWrapper.findByMainIdAndUserId(id, userId, pageable);
			}
			profession.setSuccess(Profession.SUCCESS_YES);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_VIEW_DETAIL_SUCCESS);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_VIEW_DETAIL_SUCCESS);
		}
		model.addAttribute("entity", profession);
		model.addAttribute("page", pageresult);
		
		LOGGER.debug("========== MAPPING_PATH_PERSONAL: {} ========== ", MAPPING_PATH_PERSONAL);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/professionViewDetail");
		LOGGER.debug("--->>>queryProfessionDetail.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "profession/manage/detailPreview/{id}" }, name = "查询技能详情，供发布者本人浏览")
	public String detailPreview(@PathVariable("id")Long id, @ModelAttribute("entity") Profession profession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> detailPreview <<<##");
		
		boolean checked = professionValidator.validateExist( profession);
		Page<ParagProfession> pageresult = null;
		if(checked) {
			Profession entity = professionWrapper.findDetail(id);
			profession=entity;
			if(page==null || page<0) {
				page=0;
			}
			if(size==null || size>110) {
				size=100;
			}
			Long userId = entity.getUserId();
			PageRequest pageable = new PageRequest(page,size,Direction.ASC, "orderSeq");
			pageresult = paragProfessionWrapper.findByMainIdAndUserId(id, userId, pageable);
			
		}
		model.addAttribute("entity", profession);
		model.addAttribute("page", pageresult);
		LOGGER.debug("========== MAPPING_PATH_PERSONAL: {} ========== ", MAPPING_PATH_PERSONAL);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/manage/professionDetailPreview");
		LOGGER.debug("--->>>detailPreview.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "profession/manage/detail/{id}" }, name = "查询技能详情，供发布者本人浏览")
	public String queryProfessionDetailForManage(@PathVariable("id")Long id, @ModelAttribute("entity") Profession profession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryProfessionDetailForManage <<<##");
		
		boolean checked = professionValidator.validateExist( profession);
		Page<ParagProfession> pageresult = null;
		if(checked) {
			Profession entity = professionWrapper.findDetail(id);
			profession = entity;
			if(page==null) {
				page=0;
			}
			if(size==null) {
				size=30;
			}
			Long userId = profession.getUserId();
			PageRequest pageable = new PageRequest(page,size,Direction.ASC, "orderSeq");
			pageresult = paragProfessionWrapper.findByMainIdAndUserId(id, userId, pageable);
			
			profession.setSuccess(Profession.SUCCESS_YES);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_VIEW_DETAIL_SUCCESS);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_VIEW_DETAIL_SUCCESS);
		}
		model.addAttribute("entity", profession);
		model.addAttribute("page", pageresult);
		LOGGER.debug("========== MAPPING_PATH_PERSONAL: {} ========== ", MAPPING_PATH_PERSONAL);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/manage/professionDetail");
		LOGGER.debug("--->>>queryProfessionDetailForManage.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "profession/findProfessionForPraisePage/{id}"}, name = "查询技能含点赞用户主技能列表")
	public String findProfessionForPraisePage(@PathVariable("id")Long id, @ModelAttribute("entity") Profession profession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> findProfessionForPraisePage <<<##");
		
		Long userId = UserUtils.getUserId();
		boolean checked = professionValidator.validateExist( profession);
		
		if(checked && userId != null) {
			if(page==null) {
				page=0;
			}
			if(size==null || size>10) {
				size=10;
			}
			//当前登录用户技能标签
			Page<Profession> tagPage = professionWrapper.findPageTagForCurrentUser(userId);
			model.addAttribute("tagPage", tagPage);
			
			PageRequest pageable = new PageRequest(page,size,Direction.ASC, "refreshTime");
			Profession entity = professionWrapper.findProfessionForPraisePage(id,pageable);
			profession = entity;
			profession.setSuccess(Profession.SUCCESS_YES);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_PRAISE_PAGE_SUCCESS);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_PRAISE_PAGE_SUCCESS);
		}
		model.addAttribute("entity", profession);
		
		LOGGER.debug("========== MAPPING_PATH_PERSONAL: {} ========== ", MAPPING_PATH_PERSONAL);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/findPraisePageFromDetail");
		LOGGER.debug("--->>>findProfessionForPraisePage.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@RequestMapping(value = { "profession/manage/merge/baseinfo" }, name = "保存技能基本信息")
	public String mergeBaseinfo(@ModelAttribute("entity") Profession profession, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeBaseinfo <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			profession.setUserId(userId);
			
			profession.setLastModifiedBy(userId);
			boolean checked = professionValidator.validateBaseinfo(errors, profession);
			if(checked) {
				Profession entity = professionWrapper.mergeForBaseinfo(profession);
				entity.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_MERGE_BASEINFO_SUCCESS);
				entity.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_MERGE_BASEINFO_SUCCESS);
				model.addAttribute("entity", entity);
			}


		}
		LOGGER.debug("========== MAPPING_PATH_PERSONAL: {} ========== ", MAPPING_PATH_PERSONAL);
		LOGGER.debug("--->>>mergeBaseinfo.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "profession/manage/merge/title/ansy" }, name = "异步更新技能标题")
	public String mergeForTitleAnsy(@ModelAttribute("entity") Profession profession, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForTitleAnsy <<<##");
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL, "/manage/saveResult");
		String title = profession.getTitle();
		Long id = profession.getId();
		Long userId = UserUtils.getUserId();
		LOGGER.debug(PROFESSION_TITLE_FILE,"--->>>mergeForTitleAnsy: id:{}, userId:{}, title:{} ---", id, userId, title);
		profession.setUserId(userId);
		profession.setLastModifiedBy(userId);
		boolean checked = professionValidator.validateForMergeTitleOnly(profession);
		if(checked) {
			Profession entity = professionWrapper.mergeArticleForTitleOnly(profession);
			entity.setRespCode(CourseProductRespCode.SZ0099_CODE_OOD_PROFESSION_MERGE_TITLE_SUCCESS);
			entity.setRespMsg(CourseProductRespCode.SZ0099_MSG_OOD_PROFESSION_MERGE_TITLE_SUCCESS);
			entity.setSuccess(Profession.SUCCESS_YES);
			model.addAttribute("entity", entity);
		}
		
		LOGGER.debug("========== MAPPING_PATH_PERSONAL: {} ========== ", MAPPING_PATH_PERSONAL);
		LOGGER.debug("--->>>mergeForTitleAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "profession/manage/merge/description/ansy" }, name = "异步更新技能描述")
	public String mergeForDescriptionAnsy(@ModelAttribute("entity") Profession profession, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForDescriptionAnsy <<<##");
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL, "/manage/saveResult");
		String description = profession.getDescription();
		Long id = profession.getId();
		Long userId = UserUtils.getUserId();
		LOGGER.debug(PROFESSION_DESCRIPTION_FILE,"--->>>mergeForDescriptionAnsy: id:{}, userId:{}, description:{} ---", id, userId, description);
		
		profession.setUserId(userId);
		profession.setLastModifiedBy(userId);
		boolean checked = professionValidator.validateForMergeDescriptionOnly(profession);
		if(checked) {
			Profession entity = professionWrapper.mergeArticleForDescriptionOnly(profession);
			entity.setRespCode(CourseProductRespCode.SZ0099_CODE_OOD_PROFESSION_MERGE_DESCRPTION_SUCCESS);
			entity.setRespMsg(CourseProductRespCode.SZ0099_MSG_OOD_PROFESSION_MERGE_DESCRPTION_SUCCESS);
			entity.setSuccess(Profession.SUCCESS_YES);
			model.addAttribute("entity", entity);
		}
		
		LOGGER.debug("========== MAPPING_PATH_PERSONAL: {} ========== ", MAPPING_PATH_PERSONAL);
		LOGGER.debug("--->>>mergeForDescriptionAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "profession/manage/merge/editquickly" }, name = "快速编辑")
	public String editQuickly(@ModelAttribute("entity") Profession profession, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> editQuickly <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数
		}else {
			Long userId = user.getId();
			profession.setUserId(userId);
			profession.setLastModifiedBy(userId);
			boolean checked = professionValidator.validateForEditQuickly(errors, profession);
			if(checked) {
				Profession entity = professionWrapper.mergeForEditQickly(profession);
				entity.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_EDIT_QUICKLY_SUCCESS);
				entity.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_EDIT_QUICKLY_SUCCESS);
				model.addAttribute("entity", entity);
			}
		}
		LOGGER.debug("========== MAPPING_PATH_PERSONAL: {} ========== ", MAPPING_PATH_PERSONAL);
		LOGGER.debug("--->>>editQuickly.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "profession/manage/merge/publish" }, name = "发布技能")
	public String mergeForPublish(@ModelAttribute("entity") Profession profession, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForPublish <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			profession.setUserId(userId);
			
			profession.setLastModifiedBy(userId);
			boolean checked = professionValidator.validateForPublish(errors, profession);
			if(checked) {
				Profession entity = professionWrapper.mergeForPublish(profession);
				entity.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_MERGE_PUBLISH_SUCCESS);
				entity.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_MERGE_PUBLISH_SUCCESS);
				model.addAttribute("entity", entity);
			}
		}
		LOGGER.debug("========== MAPPING_PATH_PERSONAL: {} ========== ", MAPPING_PATH_PERSONAL);
		LOGGER.debug("--->>>mergeForPublish.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "profession/manage/merge/closed" }, name = "关闭技能")
	public String mergeForClosed(@ModelAttribute("entity") Profession profession, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForClosed <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			profession.setUserId(userId);
			
			profession.setLastModifiedBy(userId);
			boolean checked = professionValidator.validateForClosed(errors, profession);
			if(checked) {
				Profession entity = professionWrapper.mergeForClosed(profession);
				entity.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_MERGE_CLOSED_SUCCESS);
				entity.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_MERGE_CLOSED_SUCCESS);
				model.addAttribute("entity", entity);
			}
		}
		LOGGER.debug("========== MAPPING_PATH_PERSONAL: {} ========== ", MAPPING_PATH_PERSONAL);
		LOGGER.debug("--->>>mergeForClosed.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "profession/manage/merge/refresh" }, name = "刷新技能时间")
	public String mergeForRefresh(@ModelAttribute("entity") Profession profession, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForRefresh <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL, "/manage/refreshResult");
		if(null == user) {
			//用户尚未登录，保存请求参数
		}else {
			Long userId = user.getId();
			profession.setUserId(userId);
			profession.setLastModifiedBy(userId);
			boolean checked = professionValidator.validateForRefresh(errors, profession);
			if(checked) {
				Profession entity = professionWrapper.mergeForRefresh(profession);
				entity.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_REFRESH_SUCCESS);
				entity.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_REFRESH_SUCCESS);
				model.addAttribute("entity", entity);
			}
		}
		LOGGER.debug("========== MAPPING_PATH_PERSONAL: {} ========== ", MAPPING_PATH_PERSONAL);
		LOGGER.debug("--->>>mergeForRefresh.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "profession/manage/merge/unpublish" }, name = "撤回与发布技能")
	public String mergeForUnPublished(@ModelAttribute("entity") Profession profession, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForUnPublished <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数
			profession.setSuccess(Profession.SUCCESS_YES);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PARAGRAGH_USERID_NULL);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PARAGRAGH_USERID_NULL);
		}else {
			Long userId = user.getId();
			profession.setUserId(userId);
			
			profession.setLastModifiedBy(userId);
			boolean checked = professionValidator.validateForPublished(errors, profession);
			if(checked) {
				Profession entity = professionWrapper.mergeForUnPublished(profession);
				entity.setSuccess(Profession.SUCCESS_YES);
				entity.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_MERGE_UNPUBLISHED_SUCCESS);
				entity.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_MERGE_UNPUBLISHED_SUCCESS);
				model.addAttribute("entity", entity);
			}

		}
		LOGGER.debug("========== MAPPING_PATH_PERSONAL: {} ========== ", MAPPING_PATH_PERSONAL);
		LOGGER.debug("--->>>mergeForUnPublished.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "profession/manage/merge/deleted" }, name = "删除技能")
	public String mergeForDeleted(@ModelAttribute("entity") Profession profession, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForDeleted <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			profession.setUserId(userId);
			
			profession.setLastModifiedBy(userId);
			boolean checked = professionValidator.validateForDeleted(errors, profession);
			if(checked) {
				Profession entity = professionWrapper.mergeForDeleted(profession);
				entity.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_MERGE_DELERED_SUCCESS);
				entity.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_MERGE_DELERED_SUCCESS);
				model.addAttribute("entity", entity);
			}
		}
		LOGGER.debug("========== MAPPING_PATH_PERSONAL: {} ========== ", MAPPING_PATH_PERSONAL);
		LOGGER.debug("--->>>mergeForDeleted.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	/**
	 * 查找绑定位置及已绑定的数据
	 * @param profession
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "profession/manage/bindListUI" }, name = "已绑定技能")
	public String bindListUI(@ModelAttribute("position") Profession profession, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> bindListUI <<<##");
		Long userId = UserUtils.getUserId();
		profession.setUserId(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/manage/professionBindListUI");
		List<Profession>  professionList = new ArrayList<>();
		if(null != userId) {
			Long baseId = profession.getBaseId();
			profession=professionWrapper.findByIdOnly(baseId);
			professionList.add(profession);
			//refList = professionWrapper.findMainIdAndExtendId(profession);
		}
		model.addAttribute("professionList", professionList);
		model.addAttribute("entity", profession);
		LOGGER.debug("--->>> bindListUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "profession/manage/merge/mainType" }, name = "设置主技能")
	public String mergeForMainType(@ModelAttribute("entity") Profession profession, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForMainType <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			profession.setUserId(userId);
			
			profession.setLastModifiedBy(userId);
			boolean checked = professionValidator.validateForMainType(errors, profession);
			if(checked) {
				Profession entity = professionWrapper.mergeForMainType(profession);
				entity.setSuccess(Profession.SUCCESS_YES);
				entity.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_MERGE_MAINTYPE_SUCCESS);
				entity.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_MERGE_MAINTYPE_SUCCESS);
				model.addAttribute("entity", entity);
			}
		}
		LOGGER.debug("========== MAPPING_PATH_PERSONAL: {} ========== ", MAPPING_PATH_PERSONAL);
		LOGGER.debug("--->>>mergeForMainType.resultPath: {} ---", resultPath);
		return resultPath;
	}

}
