package dml.sz0099.course.app.client.controller.activity;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.jit4j.app.client.data.holder.UserRoleHolder;
import org.jit4j.app.persist.entity.auth.User;
import org.jit4j.app.persist.entity.auth.UserRole;
import org.jit4j.app.persist.entity.order.Order;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dml.sz0099.course.app.client.resolver.adaptor.media.BannerActivityAdaptor;
import dml.sz0099.course.app.client.resolver.adaptor.media.CoverActivityAdaptor;
import dml.sz0099.course.app.client.validator.activity.CoeActivityValidator;
import dml.sz0099.course.app.client.wrapper.activity.CoeActivityOrderWrapper;
import dml.sz0099.course.app.client.wrapper.activity.CoeActivityUserWrapper;
import dml.sz0099.course.app.client.wrapper.activity.CoeActivityWrapper;
import dml.sz0099.course.app.client.wrapper.activity.ParagActivityWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeOrderWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.data.show.blooming.CircleMain;
import dml.sz0099.course.app.data.show.blooming.converter.CoeActivityConverter;
import dml.sz0099.course.app.data.show.layout.fetcher.CoeActivityFetcher;
import dml.sz0099.course.app.module.define.CoeActivityRespCode;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityUser;
import dml.sz0099.course.app.persist.entity.activity.ParagActivity;
import dml.sz0099.course.app.persist.entity.activity.bo.CoeActivityBo;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.entity.profession.Profession;
import dml.sz0099.course.app.persist.entity.user.CoeUser;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeActivityController 控制转发
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
public class CoeActivityController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityController.class);

	private static final Marker ACTIVITY_DESCRIPTION_FILE=MarkerFactory.getMarker("ActivityDescriptionFile");
	private static final Marker ACTIVITY_TITLE_FILE=MarkerFactory.getMarker("ActivityTitleFile");
	
	
	@Autowired
	private CoeActivityValidator coeActivityValidator;

	@Autowired
	private CoeActivityWrapper coeActivityWrapper;
	
	@Autowired
	private CoeOrderWrapper coeOrderWrapper;
	
	@Autowired
	private CoeUserWrapper coeUserWrapper;
	
	@Autowired
	private ParagActivityWrapper paragActivityWrapper;
	
	@Autowired
	private CoeActivityFetcher coeActivityFetcher;
	
	@Autowired
	private CoeActivityConverter coeActivityConverter;
	
	@Autowired
	private CoverActivityAdaptor coverActivityAdaptor;
	
	@Autowired
	private BannerActivityAdaptor bannerActivityAdaptor;
	
	@Autowired
	private CoeActivityOrderWrapper coeActivityOrderWrapper;
	
	@Autowired
	private CoeActivityUserWrapper coeActivityUserWrapper;
	
	@Autowired
	private UserRoleHolder userRoleHolder;

	//模板根目录
	private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
	//模块缩略名称目录
	private String MODULE_PATH_ACTIVITY = ResourcePathConfig.getModulesPath(CourseAppModule.APP_OOD_ACTIVITY.getAd());
	//模块主目录,一般与模块缩略名称相同
	private String MAPPING_PATH_ACTIVITY = ResourcePathConfig.getModulesPath(CourseAppModule.APP_OOD_ACTIVITY_PATH.currentPath());
	//模块二级目录
	private String MAPPING_SUB_PATH_ACTIVITY_BASIC = ResourcePathConfig.getModulesPath(CourseAppModule.APP_OOD_ACTIVITY_PATH_BASIC.currentPath());
	
	@PostConstruct
	public void init() {
		LOGGER.debug("========== TLE_DEFALT_FRONT: {} ========== ", TLE_DEFALT_FRONT);
		LOGGER.debug("========== MODULE_PATH_ACTIVITY: {} ========== ", MODULE_PATH_ACTIVITY);
		LOGGER.debug("========== MAPPING_PATH_ACTIVITY: {} ========== ", MAPPING_PATH_ACTIVITY);
		LOGGER.debug("========== MAPPING_SUB_PATH_ACTIVITY_BASIC: {} ========== ", MAPPING_SUB_PATH_ACTIVITY_BASIC);
	}
	
	
	public CoeActivityController() {
		LOGGER.debug("##>>> init CoeActivityController <<<##");
	}

	@RequestMapping(value = { "/index" }, name = "访问coeActivity首页")
	public String indexUI(@ModelAttribute("coeActivityBo") CoeActivityBo coeActivityBo, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexUI <<<##");
		PageRequest pageable = null;
		if(page==null) {
			page=0;
		}
		if(size==null) {
			size=20;
		}
		LOGGER.debug("========== MAPPING_SUB_PATH_ACTIVITY_BASIC: {} ========== ", MAPPING_SUB_PATH_ACTIVITY_BASIC);
		
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			
			//1.查找活动列表
			pageable = new PageRequest(page, size, Direction.DESC, "refreshTime");
			Page<CoeActivity> pageResult = coeActivityWrapper.findByPublished(coeActivityBo, pageable);
			model.addAttribute("page", pageResult);
			model.addAttribute("position", new Position());
			
			//2.查找分类树
			Long mainId = null;
			Long subId = null;
			Category categoryTree = coeActivityWrapper.findCategoryTree(mainId, subId);
			model.addAttribute("categoryTree", categoryTree);
			
			//3.邀请人前三篇活动列表
			Long userId=user.getId();
			Long createdBy = user.getCreatedBy();
			Page<CoeActivity> invitorPage = coeActivityWrapper.findPageForInvitor(createdBy,userId);
			model.addAttribute("invitorPage", invitorPage);
			
			CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
			model.addAttribute("currentUser", coeUser);
			
			//将分页技能组装成圆形菜单数据
			List<CoeActivity> contentList = invitorPage.getContent();
			CircleMain circleMain = coeActivityConverter.convert(contentList, coeUser);
			model.addAttribute("circleMain", GsonBuilderUtils.toJson(circleMain));
			
			//4.当前登录用户最新活动标签
			Page<CoeActivity>  tagPage = coeActivityWrapper.findPageTagForCurrentUser(userId);
			model.addAttribute("tagPage", tagPage);
			
			
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/indexUI");
		LOGGER.debug("--->>>CoeActivityController.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/queryList" }, name = "查询列表")
	public String queryList(@ModelAttribute("coeActivityBo") CoeActivityBo coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryList <<<##");
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
		LOGGER.debug("========== MAPPING_SUB_PATH_ACTIVITY_BASIC: {} ========== ", MAPPING_SUB_PATH_ACTIVITY_BASIC);
		pageable = new PageRequest(page, size, Direction.DESC, "refreshTime");
		Page<CoeActivity> pageResult = coeActivityWrapper.findByPublished(coeActivity, pageable);
		model.addAttribute("page", pageResult);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/queryList");
		LOGGER.debug("--->>>queryList.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/buyInstruction" }, name = "购买说明")
	public String buyInstructionUI(@ModelAttribute("coeActivityBo") CoeActivity coeActivity, Integer page, Integer size, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> buyInstructionUI <<<##");
		String resultPath = "";
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录，先让其登录，再进行购买下单操作
			model.addAttribute("entity", coeActivity);
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_ORDER_NOT_LOGIN);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_ORDER_NOT_LOGIN);
			resultPath = "forward:/loginOnlyUI";//ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/loginOnlyUI");
			
			return resultPath;
		}
		coeActivity.setUserId(user.getId());
		boolean checked = coeActivityValidator.validateCoeActivityUserBuy( errors, coeActivity);
		if(checked) {
			Long id = coeActivity.getId();
			CoeActivity entity = coeActivityWrapper.findById(id);
			coeActivity = entity;
			entity.setSuccess(CoeActivity.SUCCESS_YES);
			entity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_BUY_INSTRUCTION_SUCCESS);
			entity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_BUY_INSTRUCTION_SUCCESS);
		}
		model.addAttribute("entity", coeActivity);
		
		LOGGER.debug("========== MAPPING_SUB_PATH_ACTIVITY_BASIC: {} ========== ", MAPPING_SUB_PATH_ACTIVITY_BASIC);
		resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/buyInstruction");
		LOGGER.debug("--->>>CoeActivityController.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@ResponseBody
	@RequestMapping(value = { "/findPageForInvitor" }, name = "邀请人活动圆形菜单")
	public JsonResult<CircleMain> findPageForInvitor(@ModelAttribute("activity") CoeActivity activity, Model model, HttpServletRequest request) {
		User user = UserUtils.getUser();
		JsonResult<CircleMain> result = new JsonResult<>();
		if (null != user) {
			long t9 = System.currentTimeMillis();
			// 3.邀请人前三篇活动列表
			Long userId = user.getId();
			Long createdBy = user.getCreatedBy();
			Page<CoeActivity> invitorPage = coeActivityWrapper.findPageForInvitor(createdBy, userId);
			model.addAttribute("invitorPage", invitorPage);

			CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
			model.addAttribute("currentUser", coeUser);
			// 将分页技能组装成圆形菜单数据
			List<CoeActivity> contentList = invitorPage.getContent();
			CircleMain circleMain = coeActivityConverter.convert(contentList, coeUser);
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
		LOGGER.debug("--->>>findByUserId.result: {} ---", result.getRespCode());
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = { "/findByUserId" }, name = "技能圆形菜单")
	public JsonResult<CircleMain> findByUserId(@ModelAttribute("activity") CoeActivity activity,  Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> findByUserId <<<##");
		
		User user = UserUtils.getUser();
		JsonResult<CircleMain> result = new JsonResult<>();
		if(null != user){
			
			//指定人活动列表
			Long userId=activity.getUserId();
			
			Page<CoeActivity> fixUserPage = coeActivityWrapper.findPageForCurrentUser(userId);
			model.addAttribute("fixUserPage", fixUserPage);
			
			CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
			model.addAttribute("currentUser", coeUser);
			//将分页活动组装成圆形菜单数据
			List<CoeActivity> contentList = fixUserPage.getContent();
			CircleMain circleMain = coeActivityConverter.convert(contentList,coeUser);
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
	
	public static final String LINK_HUAN="/sz0099/ood/activity/changeByUserId/";
	@ResponseBody
	@RequestMapping(value = { "/changeByUserId/{userId}" }, name = "技能圆形菜单[换]")
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
				Page<CoeActivity> fixUserPage = coeActivityWrapper.findPageForCurrentUser(userId);
				model.addAttribute("fixUserPage", fixUserPage);
				
				CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
				model.addAttribute("currentUser", coeUser);
				//将分页技能组装成圆形菜单数据
				List<CoeActivity> contentList = fixUserPage.getContent();
				CircleMain circleMain = coeActivityConverter.convert(contentList, coeUser);
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
	
	
	@RequestMapping(value = { "/manage/create" }, name = "录入活动UI")
	public String createUI(@ModelAttribute("entity") CoeActivity coeActivity, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> createUI <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY, "/manage/activityCreate");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_LOGIN);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_USER_NOT_LOGIN);

		}else {
			Long userId = user.getId();
			coeActivity.setUserId(userId);
			
			Long id = coeActivity.getId();
			CoeActivity entity = null;
			if (null != id) {
				entity = coeActivityWrapper.findById(id);
			}
			if(entity == null) {
			
				Long draftNum = coeActivityValidator.countDraftList(coeActivity);
				model.addAttribute("draftNum", draftNum);
				if(null != draftNum && draftNum>5) {
					model.addAttribute("entity", coeActivity);
					resultPath = "forward:/sz0099/ood/activity/manage/queryDraftList";
				} else {
					// 生成活动草稿信息
					// DETAIL_URL
					coeActivity.setOriginalLink(DETAIL_URL);//活动详情链接
					coeActivity.setCreatedBy(userId);
					coeActivity.setLastModifiedBy(userId);
					entity = coeActivityWrapper.createDraft(coeActivity);
					//重定向到新建草稿
					return "redirect:/sz0099/ood/activity/manage/create?id="+entity.getId();
				}

			}
			model.addAttribute("entity", entity);
			
			//获取上传组件参数
			ImageExtend coverExtend = coverActivityAdaptor.config(userId);
			model.addAttribute("coverExtend", coverExtend);
			
			ImageExtend bannerExtend = bannerActivityAdaptor.config(userId);
			model.addAttribute("bannerExtend", bannerExtend);
		}
		LOGGER.debug("========== MAPPING_PATH_ACTIVITY: {} ========== ", MAPPING_PATH_ACTIVITY);
		LOGGER.debug("--->>>createUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/queryDraftList" }, name = "查询用户草稿列表")
	public String queryDraftList(@ModelAttribute("entity") CoeActivityBo coeActivityBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryDraftList <<<##");
		
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录
		}else {
			Long userId = user.getId();
			coeActivityBo.setUserId(userId);
			coeActivityValidator.countDraftList(coeActivityBo);
			List<CoeActivity> draftList = coeActivityWrapper.findDraftList(coeActivityBo);
			model.addAttribute("draftList", draftList);
		}
		LOGGER.debug("========== MAPPING_PATH_ACTIVITY: {} ========== ", MAPPING_PATH_ACTIVITY);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/manage/activityDraftList");
		LOGGER.debug("--->>>queryDraftList.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/queryDraftList/ansy" }, name = "异步查询用户草稿列表")
	public String queryDraftListAnsy(@ModelAttribute("entity") CoeActivity coeActivity, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryDraftListAnsy <<<##");
		
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录
		}else {
			Long userId = user.getId();
			coeActivity.setUserId(userId);
			coeActivityValidator.countDraftList(coeActivity);
			List<CoeActivity> draftList = coeActivityWrapper.findDraftList(coeActivity);
			model.addAttribute("draftList", draftList);
		}
		LOGGER.debug("========== MAPPING_PATH_ACTIVITY: {} ========== ", MAPPING_PATH_ACTIVITY);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/manage/activityDraftListAnsy");
		LOGGER.debug("--->>>queryDraftListAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	//查询已发布的活动
	@RequestMapping(value = { "/manage/queryActivityList" }, name = "查询用户活动列表")
	public String queryActivityList(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryActivityList <<<##");
		
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录
		}else {
			Long userId = user.getId();
			coeActivity.setUserId(userId);
			if(page==null) {
				page=0;
			}
			if(size==null) {
				size=20;
			}
			PageRequest pageable = new PageRequest(page,size,Direction.DESC, "id");
			Page<CoeActivity> pageResult = coeActivityWrapper.findPublishedWithCoverAndBanner(coeActivity, pageable);
			model.addAttribute("page", pageResult);
		}
		model.addAttribute("entity", coeActivity);
		LOGGER.debug("========== MAPPING_PATH_ACTIVITY: {} ========== ", MAPPING_PATH_ACTIVITY);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/manage/activityDetailList");
		LOGGER.debug("--->>>queryActivityList.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/queryActivityList/ansy" }, name = "异步查询用户活动列表")
	public String queryActivityListAnsy(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryActivityListAnsy <<<##");
		
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录
		}else {
			Long userId = user.getId();
			coeActivity.setUserId(userId);
			if(page==null || page<0) {
				page=0;
			}
			if(size==null || size>50) {
				size=20;
			}
			PageRequest pageable = new PageRequest(page,size,Direction.DESC, "id");
			Page<CoeActivity> pageResult = coeActivityWrapper.findPublishedWithCoverAndBanner(coeActivity, pageable);
			model.addAttribute("page", pageResult);
		}
		model.addAttribute("entity", coeActivity);
		LOGGER.debug("========== MAPPING_PATH_ACTIVITY: {} ========== ", MAPPING_PATH_ACTIVITY);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/manage/activityDetailListAnsy");
		LOGGER.debug("--->>>queryActivityListAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/searchActivityList/ansy" }, name = "异步搜索查询用户活动列表")
	public String searchActivityListAnsy(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> searchActivityListAnsy <<<##");
		
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录
		}else {
			Long userId = user.getId();
			coeActivity.setUserId(userId);
			if(page==null || page<0) {
				page=0;
			}
			if(size==null || size>50) {
				size=20;
			}
			PageRequest pageable = new PageRequest(page,size,Direction.DESC, "id");
			Page<CoeActivity> pageResult = coeActivityWrapper.findPublishedWithCoverAndBanner(coeActivity, pageable);
			model.addAttribute("page", pageResult);
		}
		model.addAttribute("entity", coeActivity);
		LOGGER.debug("========== MAPPING_PATH_ACTIVITY: {} ========== ", MAPPING_PATH_ACTIVITY);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/manage/searchActivityListAnsy");
		LOGGER.debug("--->>>searchActivityListAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	//只有已发布，已上架，未被禁用与删除的才可以查看
	public static final String DETAIL_URL="/sz0099/ood/activity/detail/{id}";
	@RequestMapping(value = { "detail/{id}"}, name = "查询活动详情，供客户浏览")
	public String queryActivityDetail(@PathVariable("id")Long id, @ModelAttribute("entity") CoeActivity activity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryActivityDetail <<<##");
		boolean checked = coeActivityValidator.validateExist( activity);
		
		Page<ParagActivity> pageresult = null;
		if(checked) {
			CoeActivity entity = coeActivityWrapper.findDetailFilledMainTypeList(id);
			activity=entity;
			
			if(page==null || page<0) {
				page=0;
			}
			if(size==null || size>35) {
				size=30;
			}
			Long userId = activity.getUserId();
			PageRequest pageable = new PageRequest(page,size,Direction.ASC, "orderSeq");
			pageresult = paragActivityWrapper.findByMainIdAndUserId(id, userId, pageable);
			
			UserRole userRole = userRoleHolder.findClubLeaderByUserId(userId);
			model.addAttribute("userRole", userRole);
		}
		model.addAttribute("entity", activity);
		model.addAttribute("page", pageresult);
		
		//查询已报列表(仅加载100名)
		Pageable pageable = new PageRequest(0,100,Direction.DESC, "aid");
		Page<CoeActivityUser> userPage = coeActivityUserWrapper.findByMainId(id,pageable);
		model.addAttribute("userPage", userPage);
		
		boolean status=false;
		if(null != userPage && userPage.getTotalElements() > 0) {
			List<CoeActivityUser> userList = userPage.getContent();
			if(null != userList && !userList.isEmpty()) {
				for(CoeActivityUser u: userList) {
					Long uid = u.getUserId();
					Integer userStatus = u.getStatus();
					//该用户已报
					if(uid.equals(UserUtils.getUserId()) && userStatus != null && userStatus >= Order.ORDER_STATUS_PAY_SUCCESS.getValueInt()) {
						status=true;
						break;
					}
				}
			}
		}
		
		model.addAttribute("status", status);
		
		LOGGER.debug("========== MAPPING_PATH_ACTIVITY: {} ========== ", MAPPING_PATH_ACTIVITY);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/activityViewDetail");
		LOGGER.debug("--->>>queryActivityDetail.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	public static final String USER_NEW_URL="/sz0099/ood/activity/userNew/";
	@RequestMapping(value = { "userNew/{userId}"}, name = "查询用户最新刷新的一篇活动")
	public String queryActivityDetailForUserNew(@PathVariable("userId")Long userId, @ModelAttribute("entity") CoeActivity activity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryActivityDetailForUserNew <<<##");
		
		Long loginUserId = UserUtils.getUserId();
		boolean checked = loginUserId!=null;
		Page<ParagActivity> pageresult = null;
		if(checked) {
			CoeActivity entity = coeActivityWrapper.findDetailLastRefreshByUserId(userId);
			if(null != entity) {
				activity=entity;
				Long id = entity.getId();
				if(page==null || page<0) {
					page=0;
				}
				if(size==null || size>30) {
					size=30;
				}
				PageRequest pageable = new PageRequest(page,size,Direction.ASC, "orderSeq");
				pageresult = paragActivityWrapper.findByMainIdAndUserId(id, userId, pageable);
			}
		}
		model.addAttribute("entity", activity);
		model.addAttribute("page", pageresult);
		
		LOGGER.debug("========== MAPPING_PATH_ACTIVITY: {} ========== ", MAPPING_PATH_ACTIVITY);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/activityViewDetail");
		LOGGER.debug("--->>>queryActivityDetailForUserNew.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/detail/{id}" }, name = "查询活动详情，供发布者本人浏览")
	public String queryActivityDetailForManage(@PathVariable("id")Long id, @ModelAttribute("entity") CoeActivity activity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryActivityDetailForManage <<<##");
		
		boolean checked = coeActivityValidator.validateExist( activity);
		Page<ParagActivity> pageresult = null;
		if(checked) {
			CoeActivity entity = coeActivityWrapper.findDetail(id);
			activity = entity;
			if(page==null) {
				page=0;
			}
			if(size==null) {
				size=100;
			}
			Long userId = activity.getUserId();
			PageRequest pageable = new PageRequest(page,size,Direction.ASC, "orderSeq");
			pageresult = paragActivityWrapper.findByMainIdAndUserId(id, userId, pageable);
			
		}
		model.addAttribute("entity", activity);
		model.addAttribute("page", pageresult);
		LOGGER.debug("========== MAPPING_PATH_ACTIVITY: {} ========== ", MAPPING_PATH_ACTIVITY);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/manage/activityDetail");
		LOGGER.debug("--->>>queryActivityDetailForManage.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/detailPreview/{id}" }, name = "查询活动详情，供发布者本人浏览")
	public String detailPreview(@PathVariable("id")Long id, @ModelAttribute("entity") CoeActivity activity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> detailPreview <<<##");
		
		boolean checked = coeActivityValidator.validateExist( activity);
		Page<ParagActivity> pageresult = null;
		if(checked) {
			CoeActivity entity = coeActivityWrapper.findDetail(id);
			activity = entity;
			if(page==null || page<0) {
				page=0;
			}
			if(size==null || size>110) {
				size=100;
			}
			Long userId = activity.getUserId();
			PageRequest pageable = new PageRequest(page,size,Direction.ASC, "orderSeq");
			pageresult = paragActivityWrapper.findByMainIdAndUserId(id, userId, pageable);
			
		}
		model.addAttribute("entity", activity);
		model.addAttribute("page", pageresult);
		
		//查询已报列表
		List<CoeActivityUser> userList = coeActivityUserWrapper.findByMainId(id);
		model.addAttribute("userList", userList);
		
		boolean status=false;
		if(null != userList && !userList.isEmpty()) {
			for(CoeActivityUser u: userList) {
				Long uid = u.getUserId();
				Integer userStatus = u.getStatus();
				//该用户已报
				if(uid.equals(UserUtils.getUserId()) && userStatus != null && userStatus >= Order.ORDER_STATUS_PAY_SUCCESS.getValueInt()) {
					status=true;
					break;
				}
			}
		}
		model.addAttribute("status", status);
				
		LOGGER.debug("========== MAPPING_PATH_ACTIVITY: {} ========== ", MAPPING_PATH_ACTIVITY);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/manage/activityDetailPreview");
		LOGGER.debug("--->>>detailPreview.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	// 查询已发布的活动
	@RequestMapping(value = { "/manage/queryActivityManageList" }, name = "查询活动列表，快速编辑")
	public String queryActivityManageList(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model,
			HttpServletRequest request) {
		LOGGER.debug("##>>> queryActivityManageList <<<##");

		User user = UserUtils.getUser();
		if (null == user) {
			// 用户尚未登录
		} else {
			Long userId = user.getId();
			coeActivity.setUserId(userId);
			if (page == null || page<0) {
				page = 0;
			}
			if (size == null || size>50) {
				size = 20;
			}
			PageRequest pageable = new PageRequest(page, size, Direction.DESC, "id");
			Page<CoeActivity> pageResult = coeActivityWrapper.findPublished(coeActivity, pageable);
			model.addAttribute("page", pageResult);
		}
		model.addAttribute("entity", coeActivity);
		LOGGER.debug("========== MAPPING_PATH_ACTIVITY: {} ========== ", MAPPING_PATH_ACTIVITY);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY, "/manage/activityManageList");
		LOGGER.debug("--->>>queryActivityManageList.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/queryActivityManageList/ansy" }, name = "异步查询活动列表，快速编辑")
	public String queryActivityManageListAnsy(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model,
			HttpServletRequest request) {
		LOGGER.debug("##>>> queryActivityManageListAnsy <<<##");

		User user = UserUtils.getUser();
		if (null == user) {
			// 用户尚未登录
		} else {
			Long userId = user.getId();
			coeActivity.setUserId(userId);
			if (page == null || page<0) {
				page = 0;
			}
			if (size == null || size>50) {
				size = 20;
			}
			PageRequest pageable = new PageRequest(page, size, Direction.DESC, "id");
			Page<CoeActivity> pageResult = coeActivityWrapper.findPublished(coeActivity, pageable);
			model.addAttribute("page", pageResult);
		}
		model.addAttribute("entity", coeActivity);
		LOGGER.debug("========== MAPPING_PATH_ACTIVITY: {} ========== ", MAPPING_PATH_ACTIVITY);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY, "/manage/activityManageListAnsy");
		LOGGER.debug("--->>>queryActivityManageListAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	@RequestMapping(value = { "/manage/merge/baseinfo" }, name = "录入活动基本信息")
	public String mergeBaseinfo(@ModelAttribute("entity") CoeActivity coeActivity, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeBaseinfo <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			coeActivity.setUserId(userId);
			
			coeActivity.setLastModifiedBy(userId);
			boolean checked = coeActivityValidator.validateBaseinfo(errors, coeActivity);
			if(checked) {
				CoeActivity entity = coeActivityWrapper.mergeForBaseinfo(coeActivity);
				entity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_MERGE_BASEINFO_SUCCESS);
				entity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_MERGE_BASEINFO_SUCCESS);
				model.addAttribute("entity", entity);
			}

		}
		LOGGER.debug("========== MAPPING_PATH_ACTIVITY: {} ========== ", MAPPING_PATH_ACTIVITY);
		LOGGER.debug("--->>>mergeBaseinfo.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/merge/title" }, name = "录入活动标题")
	public String mergeForTitle(@ModelAttribute("entity") CoeActivity coeActivity, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForTitle <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数
		}else {
			Long userId = user.getId();
			coeActivity.setUserId(userId);
			
			coeActivity.setLastModifiedBy(userId);
			boolean checked = coeActivityValidator.validatePrice(errors, coeActivity);
			if(checked) {
				CoeActivity entity = coeActivityWrapper.mergeActivityForTitle(coeActivity);
				entity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_MERGE_TITLE_SUCCESS);
				entity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_MERGE_TITLE_SUCCESS);
				model.addAttribute("entity", entity);
			}

		}
		LOGGER.debug("========== MAPPING_PATH_ACTIVITY: {} ========== ", MAPPING_PATH_ACTIVITY);
		LOGGER.debug("--->>>mergeForTitle.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/merge/title/ansy" }, name = "异步更新活动标题")
	public String mergeForTitleAnsy(@ModelAttribute("entity") CoeActivity coeActivity, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForTitleAnsy <<<##");
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY, "/manage/saveResult");
		
		String title = coeActivity.getTitle();
		Long id = coeActivity.getId();
		Long userId = UserUtils.getUserId();
		LOGGER.debug(ACTIVITY_TITLE_FILE,"--->>>mergeForTitleAnsy: id:{}, userId:{}, title:{} ---", id, userId, title);
		coeActivity.setUserId(userId);
		coeActivity.setLastModifiedBy(userId);
		boolean checked = coeActivityValidator.validateForMergeTitleOnly(coeActivity);
		if(checked) {
			CoeActivity entity = coeActivityWrapper.mergeActivityForTitleOnly(coeActivity);
			entity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_MERGE_TITLE_SUCCESS);
			entity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_MERGE_TITLE_SUCCESS);
			entity.setSuccess(CoeActivity.SUCCESS_YES);
			//entity.lastModifiedDate
			model.addAttribute("entity", entity);
		}
		
		LOGGER.debug("========== MAPPING_PATH_ACTIVITY: {} ========== ", MAPPING_PATH_ACTIVITY);
		LOGGER.debug("--->>>mergeForTitleAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/merge/description/ansy" }, name = "异步更新活动描述")
	public String mergeForDescriptionAnsy(@ModelAttribute("entity") CoeActivity coeActivity, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForDescriptionAnsy <<<##");
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY, "/manage/saveResult");
		String description = coeActivity.getDescription();
		Long id = coeActivity.getId();
		Long userId = UserUtils.getUserId();
		LOGGER.debug(ACTIVITY_DESCRIPTION_FILE,"--->>>mergeForDescriptionAnsy: id:{}, userId:{}, description:{} ---", id, userId, description);
		
		coeActivity.setUserId(userId);
		coeActivity.setLastModifiedBy(userId);
		boolean checked = coeActivityValidator.validateForMergeDescriptionOnly(coeActivity);
		if(checked) {
			CoeActivity entity = coeActivityWrapper.mergeActivityForDescriptionOnly(coeActivity);
			entity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_MERGE_DESCRPTION_SUCCESS);
			entity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_MERGE_DESCRPTION_SUCCESS);
			entity.setSuccess(CoeActivity.SUCCESS_YES);
			model.addAttribute("entity", entity);
		}
		
		LOGGER.debug("========== MAPPING_PATH_ACTIVITY: {} ========== ", MAPPING_PATH_ACTIVITY);
		LOGGER.debug("--->>>mergeForDescriptionAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/merge/unpublish" }, name = "撤回与发布活动")
	public String mergeForUnPublished(@ModelAttribute("entity") CoeActivity coeActivity, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForUnPublished <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数
			coeActivity.setSuccess(CoeActivity.SUCCESS_YES);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_COURSE_PARAGRAGH_USERID_NULL);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_COURSE_PARAGRAGH_USERID_NULL);
		}else {
			Long userId = user.getId();
			coeActivity.setUserId(userId);
			
			coeActivity.setLastModifiedBy(userId);
			boolean checked = coeActivityValidator.validateForPublished(errors, coeActivity);
			if(checked) {
				CoeActivity entity = coeActivityWrapper.mergeForUnPublished(coeActivity);
				entity.setSuccess(CoeActivity.SUCCESS_YES);
				entity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_MERGE_UNPUBLISHED_SUCCESS);
				entity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_MERGE_UNPUBLISHED_SUCCESS);
				model.addAttribute("entity", entity);
			}

		}
		LOGGER.debug("========== MAPPING_PATH_ACTIVITY: {} ========== ", MAPPING_PATH_ACTIVITY);
		LOGGER.debug("--->>>mergeForUnPublished.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@RequestMapping(value = { "/manage/merge/refresh" }, name = "刷新活动时间")
	public String mergeForRefresh(@ModelAttribute("entity") CoeActivity coeActivity, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForRefresh <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY, "/manage/refreshResult");
		if(null == user) {
			//用户尚未登录，保存请求参数
		}else {
			Long userId = user.getId();
			coeActivity.setUserId(userId);
			coeActivity.setLastModifiedBy(userId);
			boolean checked = coeActivityValidator.validateForRefresh(errors, coeActivity);
			if(checked) {
				CoeActivity entity = coeActivityWrapper.mergeForRefresh(coeActivity);
				entity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_REFRESH_SUCCESS);
				entity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_REFRESH_SUCCESS);
				model.addAttribute("entity", entity);
			}
		}
		LOGGER.debug("========== MAPPING_PATH_ACTIVITY: {} ========== ", MAPPING_PATH_ACTIVITY);
		LOGGER.debug("--->>>mergeForRefresh.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@RequestMapping(value = { "/manage/merge/editquickly" }, name = "快速编辑")
	public String editQuickly(@ModelAttribute("entity") CoeActivity coeActivity, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> editQuickly <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数
		}else {
			Long userId = user.getId();
			coeActivity.setUserId(userId);
			coeActivity.setLastModifiedBy(userId);
			boolean checked = coeActivityValidator.validateForEditQuickly(errors, coeActivity);
			if(checked) {
				CoeActivity entity = coeActivityWrapper.mergeForEditQickly(coeActivity);
				entity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_EDIT_QUICKLY_SUCCESS);
				entity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_EDIT_QUICKLY_SUCCESS);
				model.addAttribute("entity", entity);
			}
		}
		LOGGER.debug("========== MAPPING_PATH_ACTIVITY: {} ========== ", MAPPING_PATH_ACTIVITY);
		LOGGER.debug("--->>>editQuickly.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@RequestMapping(value = { "/manage/merge/publish" }, name = "发布活动")
	public String mergeForPublish(@ModelAttribute("entity") CoeActivity coeActivity, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForPublish <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			coeActivity.setUserId(userId);
			
			coeActivity.setLastModifiedBy(userId);
			boolean checked = coeActivityValidator.validateForPublish(errors, coeActivity);
			if(checked) {
				CoeActivity entity = coeActivityWrapper.mergeForPublish(coeActivity);
				entity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_MERGE_PUBLISH_SUCCESS);
				entity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_MERGE_PUBLISH_SUCCESS);
				model.addAttribute("entity", entity);
			}
		}
		LOGGER.debug("--->>>mergeForPublish.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@RequestMapping(value = { "/manage/merge/template" }, name = "保存模板")
	public String mergeForTemplate(@ModelAttribute("entity") CoeActivity coeActivity, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForTemplate <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY, "/manage/saveResult");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_LOGIN);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_USER_NOT_LOGIN);
		}else {
			Long userId = user.getId();
			coeActivity.setUserId(userId);
			coeActivity.setLastModifiedBy(userId);
			boolean checked = coeActivityValidator.validateForTemplate(coeActivity);
			if(checked) {
				CoeActivity entity = coeActivityWrapper.mergeForTemplate(coeActivity);
				entity.setSuccess(CoeActivity.SUCCESS_YES);
				entity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_TEMPLATE_SAVE_SUCCESS);
				entity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_TEMPLATE_SAVE_SUCCESS);
				model.addAttribute("entity", entity);
			}
		}
		LOGGER.debug("--->>>mergeForTemplate.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/merge/closed" }, name = "关闭活动")
	public String mergeForClosed(@ModelAttribute("entity") CoeActivity coeActivity, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForClosed <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			coeActivity.setUserId(userId);
			
			coeActivity.setLastModifiedBy(userId);
			boolean checked = coeActivityValidator.validateForClosed(errors, coeActivity);
			if(checked) {
				CoeActivity entity = coeActivityWrapper.mergeForClosed(coeActivity);
				entity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_MERGE_CLOSED_SUCCESS);
				entity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_MERGE_CLOSED_SUCCESS);
				model.addAttribute("entity", entity);
			}
		}
		LOGGER.debug("========== MAPPING_PATH_ACTIVITY: {} ========== ", MAPPING_PATH_ACTIVITY);
		LOGGER.debug("--->>>mergeForClosed.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/merge/deleted" }, name = "删除活动")
	public String mergeForDeleted(@ModelAttribute("entity") CoeActivity coeActivity, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForDeleted <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			coeActivity.setUserId(userId);
			
			coeActivity.setLastModifiedBy(userId);
			boolean checked = coeActivityValidator.validateForDeleted(errors, coeActivity);
			if(checked) {
				CoeActivity entity = coeActivityWrapper.mergeForDeleted(coeActivity);
				entity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_MERGE_DELERED_SUCCESS);
				entity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_MERGE_DELERED_SUCCESS);
				model.addAttribute("entity", entity);
			}
		}
		LOGGER.debug("========== MAPPING_PATH_ACTIVITY: {} ========== ", MAPPING_PATH_ACTIVITY);
		LOGGER.debug("--->>>mergeForDeleted.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@RequestMapping(value = { "/manage/merge/mainType" }, name = "设置最新活动")
	public String mergeForMainType(@ModelAttribute("entity") CoeActivity coeActivity, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForMainType <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			coeActivity.setUserId(userId);
			
			coeActivity.setLastModifiedBy(userId);
			boolean checked = coeActivityValidator.validateForMainType(errors, coeActivity);
			if(checked) {
				CoeActivity entity = coeActivityWrapper.mergeForMainType(coeActivity);
				entity.setSuccess(Profession.SUCCESS_YES);
				entity.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_MERGE_MAINTYPE_SUCCESS);
				entity.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_MERGE_MAINTYPE_SUCCESS);
				model.addAttribute("entity", entity);
			}
		}
		LOGGER.debug("========== MAPPING_PATH_ACTIVITY: {} ========== ", MAPPING_PATH_ACTIVITY);
		LOGGER.debug("--->>>mergeForMainType.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/findActivityForPraisePage/{id}"}, name = "查询活动含点赞用户最新活动列表")
	public String findActivityForPraisePage(@PathVariable("id")Long id, @ModelAttribute("entity") CoeActivity activity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> findActivityForPraisePage <<<##");
		
		Long userId = UserUtils.getUserId();
		boolean checked = coeActivityValidator.validateExist( activity);
		
		if(checked && userId != null) {
			if(page==null) {
				page=0;
			}
			if(size==null || size>10) {
				size=10;
			}
			//当前登录用户活动标签
			Page<CoeActivity> tagPage = coeActivityWrapper.findPageTagForCurrentUser(userId);
			model.addAttribute("tagPage", tagPage);
			
			PageRequest pageable = new PageRequest(page,size,Direction.ASC, "refreshTime");
			CoeActivity entity = coeActivityWrapper.findCoeActivityForPraisePage(id,pageable);
			activity = entity;
			activity.setSuccess(CoeActivity.SUCCESS_YES);
			activity.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_PRAISE_PAGE_SUCCESS);
			activity.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_PRAISE_PAGE_SUCCESS);
		}
		model.addAttribute("entity", activity);
		
		LOGGER.debug("========== MAPPING_PATH_ACTIVITY: {} ========== ", MAPPING_PATH_ACTIVITY);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/findPraisePageFromDetail");
		LOGGER.debug("--->>>findActivityForPraisePage.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/findTemplate/ansy" }, name = "查询模板")
	public String findTemplate(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, String position, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> findTemplate <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY, "/manage/templateSelect");
		if(page==null) {
			page=0;
		}
		if(size==null || size>10) {
			size=1;
		}
		Page<CoeActivity> entityPage = null;
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_LOGIN);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_USER_NOT_LOGIN);
		}else {
			Long userId = user.getId();
			coeActivity.setUserId(userId);
			coeActivity.setLastModifiedBy(userId);
			Integer template=coeActivity.getTemplate();
			Long id = coeActivity.getId();
			if(template==null || template<10) {
				template=CoeActivity.TEMPLATE_USER.getValueInt();
			}
			PageRequest pageable = new PageRequest(page,size,Direction.ASC, "refreshTime");
			entityPage = coeActivityWrapper.findPageForTemplate(userId, template, id, pageable);
		}
		model.addAttribute("entityPage", entityPage);
		model.addAttribute("position", position);
		LOGGER.debug("--->>>findTemplate.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/findPageForTemplate/ansy" }, name = "查询模板数据")
	public String findPageForTemplate(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, String position, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> findPageForTemplate <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY, "/manage/templatePage");
		if(page==null) {
			page=0;
		}
		if(size==null || size>10) {
			size=10;
		}
		Page<CoeActivity> entityPage = null;
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_LOGIN);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_USER_NOT_LOGIN);
		}else {
			Long userId = user.getId();
			coeActivity.setUserId(userId);
			coeActivity.setLastModifiedBy(userId);
			Integer template=coeActivity.getTemplate();
			if(template==null || template<10) {
				template=CoeActivity.TEMPLATE_USER.getValueInt();
			}
			PageRequest pageable = new PageRequest(page,size,Direction.ASC, "refreshTime");
			entityPage = coeActivityWrapper.findPageForTemplate(userId, template, pageable);
		}
		model.addAttribute("entityPage", entityPage);
		LOGGER.debug("--->>>findPageForTemplate.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/loadTemplate" }, name = "载入模板")
	public String loadTemplate(@ModelAttribute("entity") CoeActivity coeActivity, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> loadTemplate <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY, "/manage/saveResult");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_LOGIN);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_USER_NOT_LOGIN);
		}else {
			Long userId = user.getId();
			coeActivity.setUserId(userId);
			coeActivity.setLastModifiedBy(userId);
			boolean checked = coeActivityValidator.validateForLoadTemplate(coeActivity);
			if(checked) {
				CoeActivity entity = coeActivityWrapper.loadTemplate(coeActivity);
				entity.setSuccess(CoeActivity.SUCCESS_YES);
				entity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_TEMPLATE_LOAD_SUCCESS);
				entity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_TEMPLATE_LOAD_SUCCESS);
				model.addAttribute("entity", entity);
			}
		}
		LOGGER.debug("--->>>loadTemplate.resultPath: {} ---", resultPath);
		return resultPath;
	}

}
