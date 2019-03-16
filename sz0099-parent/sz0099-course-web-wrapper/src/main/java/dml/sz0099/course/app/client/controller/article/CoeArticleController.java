package dml.sz0099.course.app.client.controller.article;

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

import dml.sz0099.course.app.client.resolver.adaptor.media.BannerArticleAdaptor;
import dml.sz0099.course.app.client.resolver.adaptor.media.CoverArticleAdaptor;
import dml.sz0099.course.app.client.validator.article.CoeArticleValidator;
import dml.sz0099.course.app.client.wrapper.article.CoeArticleWrapper;
import dml.sz0099.course.app.client.wrapper.article.ParagArticleWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeOrderWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.data.show.blooming.CircleMain;
import dml.sz0099.course.app.data.show.blooming.converter.CoeArticleConverter;
import dml.sz0099.course.app.data.show.layout.fetcher.CoeArticleFetcher;
import dml.sz0099.course.app.module.define.CoeArticleRespCode;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.ParagArticle;
import dml.sz0099.course.app.persist.entity.article.bo.CoeArticleBo;
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
 * CoeArticleController 控制转发
 * @author bruce yang at 2018-08-29 22:41:14
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@RequestMapping("/sz0099/ood/article")
@Controller
public class CoeArticleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeArticleController.class);

	private static final Marker ARTICLE_DESCRIPTION_FILE=MarkerFactory.getMarker("ArticleDescriptionFile");
	private static final Marker ARTICLE_TITLE_FILE=MarkerFactory.getMarker("ArticleTitleFile");
	
	
	@Autowired
	private CoeArticleValidator coeArticleValidator;

	@Autowired
	private CoeArticleWrapper coeArticleWrapper;
	
	@Autowired
	private CoeOrderWrapper coeOrderWrapper;
	
	@Autowired
	private CoeUserWrapper coeUserWrapper;
	
	@Autowired
	private ParagArticleWrapper paragArticleWrapper;
	
	@Autowired
	private CoeArticleFetcher coeArticleFetcher;
	
	@Autowired
	private CoeArticleConverter coeArticleConverter;
	
	@Autowired
	private CoverArticleAdaptor coverArticleAdaptor;
	
	@Autowired
	private BannerArticleAdaptor bannerArticleAdaptor;

	//模板根目录
	private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
	//模块缩略名称目录
	private String MODULE_PATH_ARTICLE = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_ARTICLE.getAd());
	//模块主目录,一般与模块缩略名称相同
	private String MAPPING_PATH_ARTICLE = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_ARTICLE_PATH.currentPath());
	//模块二级目录
	private String MAPPING_SUB_PATH_ARTICLE_BASIC = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_ARTICLE_PATH_BASIC.currentPath());
	
	@PostConstruct
	public void init() {
		LOGGER.debug("========== TLE_DEFALT_FRONT: {} ========== ", TLE_DEFALT_FRONT);
		LOGGER.debug("========== MODULE_PATH_ARTICLE: {} ========== ", MODULE_PATH_ARTICLE);
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_ARTICLE_BASIC);
	}
	
	
	public CoeArticleController() {
		LOGGER.debug("##>>> init CoeArticleController <<<##");
	}

	@RequestMapping(value = { "/index" }, name = "访问coeArticle首页")
	public String indexUI(@ModelAttribute("coeArticleBo") CoeArticleBo coeArticleBo, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexUI <<<##");
		PageRequest pageable = null;
		if(page==null) {
			page=0;
		}
		if(size==null) {
			size=20;
		}
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_ARTICLE_BASIC);
		
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			
			//1.查找文章列表
			pageable = new PageRequest(page, size, Direction.DESC, "refreshTime");
			Page<CoeArticle> pageResult = coeArticleWrapper.findByPublished(coeArticleBo, pageable);
			model.addAttribute("page", pageResult);
			model.addAttribute("position", new Position());
			
			//2.查找分类树
			Long mainId = null;
			Long subId = null;
			Category categoryTree = coeArticleWrapper.findCategoryTree(mainId, subId);
			model.addAttribute("categoryTree", categoryTree);
			
			//3.邀请人前三篇文章列表
			Long userId=user.getId();
			Long createdBy = user.getCreatedBy();
			Page<CoeArticle> invitorPage = coeArticleWrapper.findPageForInvitor(createdBy,userId);
			model.addAttribute("invitorPage", invitorPage);
			
			CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
			model.addAttribute("currentUser", coeUser);
			
			//将分页技能组装成圆形菜单数据
			List<CoeArticle> contentList = invitorPage.getContent();
			CircleMain circleMain = coeArticleConverter.convert(contentList, coeUser);
			model.addAttribute("circleMain", GsonBuilderUtils.toJson(circleMain));
			
			//4.当前登录用户最新文章标签
			Page<CoeArticle>  tagPage = coeArticleWrapper.findPageTagForCurrentUser(userId);
			model.addAttribute("tagPage", tagPage);
			
			
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/indexUI");
		LOGGER.debug("--->>>CoeArticleController.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/queryList" }, name = "查询列表")
	public String queryList(@ModelAttribute("coeArticleBo") CoeArticleBo coeArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
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
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_ARTICLE_BASIC);
		pageable = new PageRequest(page, size, Direction.DESC, "refreshTime");
		Page<CoeArticle> pageResult = coeArticleWrapper.findByPublished(coeArticle, pageable);
		model.addAttribute("page", pageResult);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/queryList");
		LOGGER.debug("--->>>queryList.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/buyInstruction" }, name = "购买说明")
	public String buyInstructionUI(@ModelAttribute("coeArticleBo") CoeArticle coeArticle, Integer page, Integer size, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> buyInstructionUI <<<##");
		String resultPath = "";
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录，先让其登录，再进行购买下单操作
			model.addAttribute("entity", coeArticle);
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_ORDER_NOT_LOGIN);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_ORDER_NOT_LOGIN);
			resultPath = "forward:/loginOnlyUI";//ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/loginOnlyUI");
			
			return resultPath;
		}
		coeArticle.setUserId(user.getId());
		boolean checked = coeArticleValidator.validateCoeArticleUserBuy( errors, coeArticle);
		if(checked) {
			Long id = coeArticle.getId();
			CoeArticle entity = coeArticleWrapper.findById(id);
			coeArticle = entity;
			entity.setSuccess(CoeArticle.SUCCESS_YES);
			entity.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_BUY_INSTRUCTION_SUCCESS);
			entity.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_BUY_INSTRUCTION_SUCCESS);
		}
		model.addAttribute("entity", coeArticle);
		
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_ARTICLE_BASIC);
		resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/buyInstruction");
		LOGGER.debug("--->>>CoeArticleController.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@ResponseBody
	@RequestMapping(value = { "/findPageForInvitor" }, name = "邀请人文章圆形菜单")
	public JsonResult<CircleMain> findPageForInvitor(@ModelAttribute("article") CoeArticle article, Model model, HttpServletRequest request) {
		User user = UserUtils.getUser();
		JsonResult<CircleMain> result = new JsonResult<>();
		if (null != user) {
			long t9 = System.currentTimeMillis();
			// 3.邀请人前三篇文章列表
			Long userId = user.getId();
			Long createdBy = user.getCreatedBy();

			//查询自己的数据
			// Page<CoeArticle> invitorPage = coeArticleWrapper.findPageForInvitor(createdBy, userId);
			Page<CoeArticle> invitorPage = coeArticleWrapper.findPageForCurrentUser(userId);
			model.addAttribute("invitorPage", invitorPage);

			CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
			model.addAttribute("currentUser", coeUser);
			// 将分页技能组装成圆形菜单数据
			List<CoeArticle> contentList = invitorPage.getContent();
			CircleMain circleMain = coeArticleConverter.convert(contentList, coeUser);
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
	public JsonResult<CircleMain> findByUserId(@ModelAttribute("article") CoeArticle article,  Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> findByUserId <<<##");
		
		User user = UserUtils.getUser();
		JsonResult<CircleMain> result = new JsonResult<>();
		if(null != user){
			
			//指定人文章列表
			Long userId=article.getUserId();
			
			Page<CoeArticle> fixUserPage = coeArticleWrapper.findPageForCurrentUser(userId);
			model.addAttribute("fixUserPage", fixUserPage);
			
			CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
			model.addAttribute("currentUser", coeUser);
			//将分页文章组装成圆形菜单数据
			List<CoeArticle> contentList = fixUserPage.getContent();
			CircleMain circleMain = coeArticleConverter.convert(contentList,coeUser);
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
	
	public static final String LINK_HUAN="/sz0099/ood/article/changeByUserId/";
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
				Page<CoeArticle> fixUserPage = coeArticleWrapper.findPageForCurrentUser(userId);
				model.addAttribute("fixUserPage", fixUserPage);
				
				CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
				model.addAttribute("currentUser", coeUser);
				//将分页技能组装成圆形菜单数据
				List<CoeArticle> contentList = fixUserPage.getContent();
				CircleMain circleMain = coeArticleConverter.convert(contentList, coeUser);
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
	
	
	@RequestMapping(value = { "/manage/create" }, name = "录入文章UI")
	public String createUI(@ModelAttribute("entity") CoeArticle coeArticle, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> createUI <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE, "/manage/articleCreate");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_USER_NOT_LOGIN);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_USER_NOT_LOGIN);
		}else {
			Long userId = user.getId();
			coeArticle.setUserId(userId);
			
			Long id = coeArticle.getId();
			CoeArticle entity = null;
			if (null != id) {
				entity = coeArticleWrapper.findById(id);
			}
			if(entity == null) {
			
				Long draftNum = coeArticleValidator.countDraftList(coeArticle);
				model.addAttribute("draftNum", draftNum);
				if(null != draftNum && draftNum>5) {
					model.addAttribute("entity", coeArticle);
					resultPath = "forward:/sz0099/ood/article/manage/queryDraftList";
				} else {
					// 生成文章草稿信息
					// DETAIL_URL
					coeArticle.setOriginalLink(DETAIL_URL);//文章详情链接
					coeArticle.setCreatedBy(userId);
					coeArticle.setLastModifiedBy(userId);
					entity = coeArticleWrapper.createDraft(coeArticle);
					//重定向到新建草稿
					return "redirect:/sz0099/ood/article/manage/create?id="+entity.getId();
				}

			}
			model.addAttribute("entity", entity);
			
			//获取上传组件参数
			ImageExtend coverExtend = coverArticleAdaptor.config();
			model.addAttribute("coverExtend", coverExtend);
			
			ImageExtend bannerExtend = bannerArticleAdaptor.config();
			model.addAttribute("bannerExtend", bannerExtend);
		}
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		LOGGER.debug("--->>>createUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/queryDraftList" }, name = "查询用户草稿列表")
	public String queryDraftList(@ModelAttribute("entity") CoeArticleBo coeArticleBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryDraftList <<<##");
		
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录
		}else {
			Long userId = user.getId();
			coeArticleBo.setUserId(userId);
			coeArticleValidator.countDraftList(coeArticleBo);
			List<CoeArticle> draftList = coeArticleWrapper.findDraftList(coeArticleBo);
			model.addAttribute("draftList", draftList);
		}
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/manage/articleDraftList");
		LOGGER.debug("--->>>queryDraftList.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/queryDraftList/ansy" }, name = "异步查询用户草稿列表")
	public String queryDraftListAnsy(@ModelAttribute("entity") CoeArticle coeArticle, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryDraftListAnsy <<<##");
		
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录
		}else {
			Long userId = user.getId();
			coeArticle.setUserId(userId);
			coeArticleValidator.countDraftList(coeArticle);
			List<CoeArticle> draftList = coeArticleWrapper.findDraftList(coeArticle);
			model.addAttribute("draftList", draftList);
		}
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/manage/articleDraftListAnsy");
		LOGGER.debug("--->>>queryDraftListAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	//查询已发布的文章
	@RequestMapping(value = { "/manage/queryArticleList" }, name = "查询用户文章列表")
	public String queryArticleList(@ModelAttribute("entity") CoeArticle coeArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryArticleList <<<##");
		
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录
		}else {
			Long userId = user.getId();
			coeArticle.setUserId(userId);
			if(page==null) {
				page=0;
			}
			if(size==null) {
				size=20;
			}
			PageRequest pageable = new PageRequest(page,size,Direction.DESC, "id");
			Page<CoeArticle> pageResult = coeArticleWrapper.findPublishedWithCoverAndBanner(coeArticle, pageable);
			model.addAttribute("page", pageResult);
		}
		model.addAttribute("entity", coeArticle);
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/manage/articleDetailList");
		LOGGER.debug("--->>>queryArticleList.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/queryArticleList/ansy" }, name = "异步查询用户文章列表")
	public String queryArticleListAnsy(@ModelAttribute("entity") CoeArticle coeArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryArticleListAnsy <<<##");
		
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录
		}else {
			Long userId = user.getId();
			coeArticle.setUserId(userId);
			if(page==null || page<0) {
				page=0;
			}
			if(size==null || size>50) {
				size=20;
			}
			PageRequest pageable = new PageRequest(page,size,Direction.DESC, "id");
			Page<CoeArticle> pageResult = coeArticleWrapper.findPublishedWithCoverAndBanner(coeArticle, pageable);
			model.addAttribute("page", pageResult);
		}
		model.addAttribute("entity", coeArticle);
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/manage/articleDetailListAnsy");
		LOGGER.debug("--->>>queryArticleListAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/searchArticleList/ansy" }, name = "异步搜索查询用户文章列表")
	public String searchArticleListAnsy(@ModelAttribute("entity") CoeArticle coeArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> searchArticleListAnsy <<<##");
		
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录
		}else {
			Long userId = user.getId();
			coeArticle.setUserId(userId);
			if(page==null || page<0) {
				page=0;
			}
			if(size==null || size>50) {
				size=20;
			}
			PageRequest pageable = new PageRequest(page,size,Direction.DESC, "id");
			Page<CoeArticle> pageResult = coeArticleWrapper.findPublishedWithCoverAndBanner(coeArticle, pageable);
			model.addAttribute("page", pageResult);
		}
		model.addAttribute("entity", coeArticle);
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/manage/searchArticleListAnsy");
		LOGGER.debug("--->>>searchArticleListAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	//只有已发布，已上架，未被禁用与删除的才可以查看
	public static final String DETAIL_URL="/sz0099/ood/article/detail/{id}";
	@RequestMapping(value = { "detail/{id}"}, name = "查询文章详情，供客户浏览")
	public String queryArticleDetail(@PathVariable("id")Long id, @ModelAttribute("entity") CoeArticle article, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryArticleDetail <<<##");
		boolean checked = coeArticleValidator.validateExist( article);
		
		Page<ParagArticle> pageresult = null;
		if(checked) {
			CoeArticle entity = coeArticleWrapper.findDetailFilledMainTypeList(id);
			article=entity;
			
			if(page==null || page<0) {
				page=0;
			}
			if(size==null || size>35) {
				size=30;
			}
			Long userId = article.getUserId();
			PageRequest pageable = new PageRequest(page,size,Direction.ASC, "orderSeq");
			pageresult = paragArticleWrapper.findByMainIdAndUserId(id, userId, pageable);
		}
		model.addAttribute("entity", article);
		model.addAttribute("page", pageresult);
		
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/articleViewDetail");
		LOGGER.debug("--->>>queryArticleDetail.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	public static final String USER_NEW_URL="/sz0099/ood/article/userNew/";
	@RequestMapping(value = { "userNew/{userId}"}, name = "查询用户最新刷新的一篇文章")
	public String queryArticleDetailForUserNew(@PathVariable("userId")Long userId, @ModelAttribute("entity") CoeArticle article, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryArticleDetailForUserNew <<<##");
		
		Long loginUserId = UserUtils.getUserId();
		boolean checked = loginUserId!=null;
		Page<ParagArticle> pageresult = null;
		if(checked) {
			CoeArticle entity = coeArticleWrapper.findDetailLastRefreshByUserId(userId);
			if(null != entity) {
				article=entity;
				Long id = entity.getId();
				if(page==null || page<0) {
					page=0;
				}
				if(size==null || size>30) {
					size=30;
				}
				PageRequest pageable = new PageRequest(page,size,Direction.ASC, "orderSeq");
				pageresult = paragArticleWrapper.findByMainIdAndUserId(id, userId, pageable);
			}
		}
		model.addAttribute("entity", article);
		model.addAttribute("page", pageresult);
		
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/articleViewDetail");
		LOGGER.debug("--->>>queryArticleDetailForUserNew.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/detail/{id}" }, name = "查询文章详情，供发布者本人浏览")
	public String queryArticleDetailForManage(@PathVariable("id")Long id, @ModelAttribute("entity") CoeArticle article, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryArticleDetailForManage <<<##");
		
		boolean checked = coeArticleValidator.validateExist( article);
		Page<ParagArticle> pageresult = null;
		if(checked) {
			CoeArticle entity = coeArticleWrapper.findDetail(id);
			article = entity;
			if(page==null) {
				page=0;
			}
			if(size==null) {
				size=100;
			}
			Long userId = article.getUserId();
			PageRequest pageable = new PageRequest(page,size,Direction.ASC, "orderSeq");
			pageresult = paragArticleWrapper.findByMainIdAndUserId(id, userId, pageable);
			
		}
		model.addAttribute("entity", article);
		model.addAttribute("page", pageresult);
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/manage/articleDetail");
		LOGGER.debug("--->>>queryArticleDetailForManage.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/detailPreview/{id}" }, name = "查询文章详情，供发布者本人浏览")
	public String detailPreview(@PathVariable("id")Long id, @ModelAttribute("entity") CoeArticle article, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> detailPreview <<<##");
		
		boolean checked = coeArticleValidator.validateExist( article);
		Page<ParagArticle> pageresult = null;
		if(checked) {
			CoeArticle entity = coeArticleWrapper.findDetail(id);
			article = entity;
			if(page==null || page<0) {
				page=0;
			}
			if(size==null || size>110) {
				size=100;
			}
			Long userId = article.getUserId();
			PageRequest pageable = new PageRequest(page,size,Direction.ASC, "orderSeq");
			pageresult = paragArticleWrapper.findByMainIdAndUserId(id, userId, pageable);
			
		}
		model.addAttribute("entity", article);
		model.addAttribute("page", pageresult);
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/manage/articleDetailPreview");
		LOGGER.debug("--->>>detailPreview.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	// 查询已发布的文章
	@RequestMapping(value = { "/manage/queryArticleManageList" }, name = "查询文章列表，快速编辑")
	public String queryArticleManageList(@ModelAttribute("entity") CoeArticle coeArticle, Integer page, Integer size, Model model,
			HttpServletRequest request) {
		LOGGER.debug("##>>> queryArticleManageList <<<##");

		User user = UserUtils.getUser();
		if (null == user) {
			// 用户尚未登录
		} else {
			Long userId = user.getId();
			coeArticle.setUserId(userId);
			if (page == null || page<0) {
				page = 0;
			}
			if (size == null || size>50) {
				size = 20;
			}
			PageRequest pageable = new PageRequest(page, size, Direction.DESC, "id");
			Page<CoeArticle> pageResult = coeArticleWrapper.findPublished(coeArticle, pageable);
			model.addAttribute("page", pageResult);
		}
		model.addAttribute("entity", coeArticle);
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE, "/manage/articleManageList");
		LOGGER.debug("--->>>queryArticleManageList.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/queryArticleManageList/ansy" }, name = "异步查询文章列表，快速编辑")
	public String queryArticleManageListAnsy(@ModelAttribute("entity") CoeArticle coeArticle, Integer page, Integer size, Model model,
			HttpServletRequest request) {
		LOGGER.debug("##>>> queryArticleManageListAnsy <<<##");

		User user = UserUtils.getUser();
		if (null == user) {
			// 用户尚未登录
		} else {
			Long userId = user.getId();
			coeArticle.setUserId(userId);
			if (page == null || page<0) {
				page = 0;
			}
			if (size == null || size>50) {
				size = 20;
			}
			PageRequest pageable = new PageRequest(page, size, Direction.DESC, "id");
			Page<CoeArticle> pageResult = coeArticleWrapper.findPublished(coeArticle, pageable);
			model.addAttribute("page", pageResult);
		}
		model.addAttribute("entity", coeArticle);
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE, "/manage/articleManageListAnsy");
		LOGGER.debug("--->>>queryArticleManageListAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	@RequestMapping(value = { "/manage/merge/baseinfo" }, name = "录入文章基本信息")
	public String mergeBaseinfo(@ModelAttribute("entity") CoeArticle coeArticle, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeBaseinfo <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			coeArticle.setUserId(userId);
			
			coeArticle.setLastModifiedBy(userId);
			boolean checked = coeArticleValidator.validateBaseinfo(errors, coeArticle);
			if(checked) {
				CoeArticle entity = coeArticleWrapper.mergeForBaseinfo(coeArticle);
				entity.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_MERGE_BASEINFO_SUCCESS);
				entity.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_MERGE_BASEINFO_SUCCESS);
				model.addAttribute("entity", entity);
			}

		}
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		LOGGER.debug("--->>>mergeBaseinfo.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/merge/title" }, name = "录入文章标题")
	public String mergeForTitle(@ModelAttribute("entity") CoeArticle coeArticle, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForTitle <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数
		}else {
			Long userId = user.getId();
			coeArticle.setUserId(userId);
			
			coeArticle.setLastModifiedBy(userId);
			boolean checked = coeArticleValidator.validatePrice(errors, coeArticle);
			if(checked) {
				CoeArticle entity = coeArticleWrapper.mergeArticleForTitle(coeArticle);
				entity.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_MERGE_TITLE_SUCCESS);
				entity.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_MERGE_TITLE_SUCCESS);
				model.addAttribute("entity", entity);
			}

		}
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		LOGGER.debug("--->>>mergeForTitle.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/merge/title/ansy" }, name = "异步更新文章标题")
	public String mergeForTitleAnsy(@ModelAttribute("entity") CoeArticle coeArticle, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForTitleAnsy <<<##");
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE, "/manage/saveResult");
		String title = coeArticle.getTitle();
		Long id = coeArticle.getId();
		Long userId = UserUtils.getUserId();
		LOGGER.debug(ARTICLE_TITLE_FILE,"--->>>mergeForTitleAnsy: id:{}, userId:{}, title:{} ---", id, userId, title);
		coeArticle.setUserId(userId);
		coeArticle.setLastModifiedBy(userId);
		boolean checked = coeArticleValidator.validateForMergeTitleOnly(coeArticle);
		if(checked) {
			CoeArticle entity = coeArticleWrapper.mergeArticleForTitleOnly(coeArticle);
			entity.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_MERGE_TITLE_SUCCESS);
			entity.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_MERGE_TITLE_SUCCESS);
			entity.setSuccess(CoeArticle.SUCCESS_YES);
			//entity.lastModifiedDate
			model.addAttribute("entity", entity);
		}
		
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		LOGGER.debug("--->>>mergeForTitleAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/merge/description/ansy" }, name = "异步更新文章描述")
	public String mergeForDescriptionAnsy(@ModelAttribute("entity") CoeArticle coeArticle, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForDescriptionAnsy <<<##");
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE, "/manage/saveResult");
		String description = coeArticle.getDescription();
		Long id = coeArticle.getId();
		Long userId = UserUtils.getUserId();
		LOGGER.debug(ARTICLE_DESCRIPTION_FILE,"--->>>mergeForDescriptionAnsy: id:{}, userId:{}, description:{} ---", id, userId, description);
		
		coeArticle.setUserId(userId);
		coeArticle.setLastModifiedBy(userId);
		boolean checked = coeArticleValidator.validateForMergeDescriptionOnly(coeArticle);
		if(checked) {
			CoeArticle entity = coeArticleWrapper.mergeArticleForDescriptionOnly(coeArticle);
			entity.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_MERGE_DESCRPTION_SUCCESS);
			entity.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_MERGE_DESCRPTION_SUCCESS);
			entity.setSuccess(CoeArticle.SUCCESS_YES);
			model.addAttribute("entity", entity);
		}
		
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		LOGGER.debug("--->>>mergeForDescriptionAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/merge/penname/ansy" }, name = "异步更新笔名绰号")
	public String mergeForPennameAnsy(@ModelAttribute("entity") CoeArticle coeArticle, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForPennameAnsy <<<##");
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE, "/manage/saveResult");
		String title = coeArticle.getTitle();
		Long id = coeArticle.getId();
		Long userId = UserUtils.getUserId();
		LOGGER.debug(ARTICLE_TITLE_FILE,"--->>>mergeForPennameAnsy: id:{}, userId:{}, title:{} ---", id, userId, title);
		coeArticle.setUserId(userId);
		coeArticle.setLastModifiedBy(userId);
		boolean checked = coeArticleValidator.validateForMergeTitleOnly(coeArticle);
		if(checked) {
			//TODO异步更新绰号，作用不大，先搁浅
			CoeArticle entity = coeArticleWrapper.mergeArticleForTitleOnly(coeArticle);
			entity.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_MERGE_TITLE_SUCCESS);
			entity.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_MERGE_TITLE_SUCCESS);
			entity.setSuccess(CoeArticle.SUCCESS_YES);
			//entity.lastModifiedDate
			model.addAttribute("entity", entity);
		}
		
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		LOGGER.debug("--->>>mergeForPennameAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/merge/unpublish" }, name = "撤回与发布文章")
	public String mergeForUnPublished(@ModelAttribute("entity") CoeArticle coeArticle, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForUnPublished <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数
			coeArticle.setSuccess(CoeArticle.SUCCESS_YES);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_PARAGRAGH_USERID_NULL);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_PARAGRAGH_USERID_NULL);
		}else {
			Long userId = user.getId();
			coeArticle.setUserId(userId);
			
			coeArticle.setLastModifiedBy(userId);
			boolean checked = coeArticleValidator.validateForPublished(errors, coeArticle);
			if(checked) {
				CoeArticle entity = coeArticleWrapper.mergeForUnPublished(coeArticle);
				entity.setSuccess(CoeArticle.SUCCESS_YES);
				entity.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_MERGE_UNPUBLISHED_SUCCESS);
				entity.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_MERGE_UNPUBLISHED_SUCCESS);
				model.addAttribute("entity", entity);
			}

		}
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		LOGGER.debug("--->>>mergeForUnPublished.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@RequestMapping(value = { "/manage/merge/refresh" }, name = "刷新文章时间")
	public String mergeForRefresh(@ModelAttribute("entity") CoeArticle coeArticle, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForRefresh <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE, "/manage/refreshResult");
		if(null == user) {
			//用户尚未登录，保存请求参数
		}else {
			Long userId = user.getId();
			coeArticle.setUserId(userId);
			coeArticle.setLastModifiedBy(userId);
			boolean checked = coeArticleValidator.validateForRefresh(errors, coeArticle);
			if(checked) {
				CoeArticle entity = coeArticleWrapper.mergeForRefresh(coeArticle);
				entity.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_REFRESH_SUCCESS);
				entity.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_REFRESH_SUCCESS);
				model.addAttribute("entity", entity);
			}
		}
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		LOGGER.debug("--->>>mergeForRefresh.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@RequestMapping(value = { "/manage/merge/editquickly" }, name = "快速编辑")
	public String editQuickly(@ModelAttribute("entity") CoeArticle coeArticle, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> editQuickly <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数
		}else {
			Long userId = user.getId();
			coeArticle.setUserId(userId);
			coeArticle.setLastModifiedBy(userId);
			boolean checked = coeArticleValidator.validateForEditQuickly(errors, coeArticle);
			if(checked) {
				CoeArticle entity = coeArticleWrapper.mergeForEditQickly(coeArticle);
				entity.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_EDIT_QUICKLY_SUCCESS);
				entity.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_EDIT_QUICKLY_SUCCESS);
				model.addAttribute("entity", entity);
			}
		}
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		LOGGER.debug("--->>>editQuickly.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@RequestMapping(value = { "/manage/merge/publish" }, name = "发布文章")
	public String mergeForPublish(@ModelAttribute("entity") CoeArticle coeArticle, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForPublish <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			coeArticle.setUserId(userId);
			
			coeArticle.setLastModifiedBy(userId);
			boolean checked = coeArticleValidator.validateForPublish(errors, coeArticle);
			if(checked) {
				CoeArticle entity = coeArticleWrapper.mergeForPublish(coeArticle);
				entity.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_MERGE_PUBLISH_SUCCESS);
				entity.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_MERGE_PUBLISH_SUCCESS);
				model.addAttribute("entity", entity);
			}
		}
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		LOGGER.debug("--->>>mergeForPublish.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/merge/closed" }, name = "关闭文章")
	public String mergeForClosed(@ModelAttribute("entity") CoeArticle coeArticle, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForClosed <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			coeArticle.setUserId(userId);
			
			coeArticle.setLastModifiedBy(userId);
			boolean checked = coeArticleValidator.validateForClosed(errors, coeArticle);
			if(checked) {
				CoeArticle entity = coeArticleWrapper.mergeForClosed(coeArticle);
				entity.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_MERGE_CLOSED_SUCCESS);
				entity.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_MERGE_CLOSED_SUCCESS);
				model.addAttribute("entity", entity);
			}
		}
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		LOGGER.debug("--->>>mergeForClosed.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/merge/deleted" }, name = "删除文章")
	public String mergeForDeleted(@ModelAttribute("entity") CoeArticle coeArticle, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForDeleted <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			coeArticle.setUserId(userId);
			
			coeArticle.setLastModifiedBy(userId);
			boolean checked = coeArticleValidator.validateForDeleted(errors, coeArticle);
			if(checked) {
				CoeArticle entity = coeArticleWrapper.mergeForDeleted(coeArticle);
				entity.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_MERGE_DELERED_SUCCESS);
				entity.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_MERGE_DELERED_SUCCESS);
				model.addAttribute("entity", entity);
			}
		}
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		LOGGER.debug("--->>>mergeForDeleted.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@RequestMapping(value = { "/manage/merge/mainType" }, name = "设置最新文章")
	public String mergeForMainType(@ModelAttribute("entity") CoeArticle coeArticle, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeForMainType <<<##");
		
		User user = UserUtils.getUser();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE, "/manage/commonResult");
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			Long userId = user.getId();
			coeArticle.setUserId(userId);
			
			coeArticle.setLastModifiedBy(userId);
			boolean checked = coeArticleValidator.validateForMainType(errors, coeArticle);
			if(checked) {
				CoeArticle entity = coeArticleWrapper.mergeForMainType(coeArticle);
				entity.setSuccess(Profession.SUCCESS_YES);
				entity.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_MERGE_MAINTYPE_SUCCESS);
				entity.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_MERGE_MAINTYPE_SUCCESS);
				model.addAttribute("entity", entity);
			}
		}
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		LOGGER.debug("--->>>mergeForMainType.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/findArticleForPraisePage/{id}"}, name = "查询文章含点赞用户最新文章列表")
	public String findArticleForPraisePage(@PathVariable("id")Long id, @ModelAttribute("entity") CoeArticle article, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> findArticleForPraisePage <<<##");
		
		Long userId = UserUtils.getUserId();
		boolean checked = coeArticleValidator.validateExist( article);
		
		if(checked && userId != null) {
			if(page==null) {
				page=0;
			}
			if(size==null || size>10) {
				size=10;
			}
			//当前登录用户文章标签
			Page<CoeArticle> tagPage = coeArticleWrapper.findPageTagForCurrentUser(userId);
			model.addAttribute("tagPage", tagPage);
			
			PageRequest pageable = new PageRequest(page,size,Direction.ASC, "refreshTime");
			CoeArticle entity = coeArticleWrapper.findCoeArticleForPraisePage(id,pageable);
			article = entity;
			article.setSuccess(CoeArticle.SUCCESS_YES);
			article.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_PRAISE_PAGE_SUCCESS);
			article.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_PRAISE_PAGE_SUCCESS);
		}
		model.addAttribute("entity", article);
		
		LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/findPraisePageFromDetail");
		LOGGER.debug("--->>>findArticleForPraisePage.resultPath: {} ---", resultPath);
		return resultPath;
	}
	

}
