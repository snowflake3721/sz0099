package dml.sz0099.course.app.client.controller.home;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.jit4j.app.module.define.Robot;
import org.jit4j.app.persist.entity.auth.User;
import org.jit4j.core.persist.page.PageRequest;
import org.jit4j.core.webmvc.config.ResourcePathConfig;
import org.jit4j.core.webmvc.util.UserUtils;
import org.jit8j.core.util.GsonBuilderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dml.sz0099.course.app.client.multi.fetch.MultiHomeActivityFetcher;
import dml.sz0099.course.app.client.resolver.category.CategoryUtil;
import dml.sz0099.course.app.client.validator.activity.CoeActivityValidator;
import dml.sz0099.course.app.client.wrapper.activity.CoeActivityWrapper;
import dml.sz0099.course.app.client.wrapper.activity.CoeCategActivityWrapper;
import dml.sz0099.course.app.client.wrapper.activity.ParagActivityWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeOrderWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.data.show.blooming.CircleMain;
import dml.sz0099.course.app.data.show.blooming.converter.CoeActivityConverter;
import dml.sz0099.course.app.data.show.layout.fetcher.CoeActivityFetcher;
import dml.sz0099.course.app.data.show.layout.fetcher.dto.CoeActivityPageDto;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPosition;
import dml.sz0099.course.app.persist.entity.activity.CoeCategActivity;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.position.Position;
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
@RequestMapping("/sz0099/ood/home/activity")
@Controller
public class HomeCoeActivityController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomeCoeActivityController.class);

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
	private CoeCategActivityWrapper categoryWrapper;
	
	@Autowired
	private CoeActivityFetcher coeActivityFetcher;
	
	@Autowired
	private CoeActivityConverter coeActivityConverter;
	
	@Autowired
	private MultiHomeActivityFetcher multiHomeActivityFetcher;

	//模板根目录
			private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
			//模块缩略名称目录
			private String MODULE_PATH_HOME = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_HOME.getAd());
			//模块主目录,一般与模块缩略名称相同
			private String MAPPING_PATH_HOME = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_HOME_PATH.currentPath());
			//模块二级目录
			private String MAPPING_SUB_PATH_HOME_ACTIVITY = ResourcePathConfig.getModulesPath(CourseAppModule.APP_OOD_HOME_PATH_ACTIVITY.currentPath());
	
	@PostConstruct
	public void init() {
		LOGGER.debug("========== TLE_DEFALT_FRONT: {} ========== ", TLE_DEFALT_FRONT);
		LOGGER.debug("========== MODULE_PATH_HOME: {} ========== ", MODULE_PATH_HOME);
		LOGGER.debug("========== MAPPING_PATH_HOME: {} ========== ", MAPPING_PATH_HOME);
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
	}
	
	
	public HomeCoeActivityController() {
		LOGGER.debug("##>>> init CoeActivityController <<<##");
	}
	
	@RequestMapping(value = { "/index" }, name = "访问coeActivity首页")
	public String indexUI(@ModelAttribute("coeActivity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexUI <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeActivityFetcher.fetchHomeActivityIndex(user, model);
		}
				
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/indexUI");
		LOGGER.debug("--->>>indexUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	@RequestMapping(value = {"/index/flag" }, name = "访问首页-群侠旗帜")
	public String indexFlag(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexFlag <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeActivityFetcher.fetchHomeActivityFlag(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/indexFlagUI");
		LOGGER.debug("--->>>indexFlag.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/flag/ansy" }, name = "异步加载-群侠旗帜")
	public String indexFlagAnsy(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexFlagAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeActivityFetcher.fetchHomeActivityFlag(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/flagAnsy");
		LOGGER.debug("--->>>indexFlagAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/index/flag/more/ansy" }, name = "异步加载-更多群侠旗帜")
	public String indexFlagMoreAnsy(@ModelAttribute("entity") CoeCategActivity coeCategActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexFlagMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			Category category =coeCategActivity.getCategory();
			if(null == category) {
				category = new Category();
				coeCategActivity.setCategory(category);
			}
			String code = category.getCode();
			if(StringUtils.isBlank(code)) {
				category.setCode(Category.CODE_ACTIVITY_JOIN_FLAG);
			}
			List<Long> excludeMainIdList = null;
			boolean cover = true;
			boolean banner = true;
			boolean author = false;
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "activity.topLevel","activity.refreshTime");
			Page<CoeCategActivity> flagPage = categoryWrapper.findPageForPublishWithChildren(coeCategActivity, excludeMainIdList, pageable, cover,
					banner, author);
			model.addAttribute("flagPage", flagPage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/flagMoreAnsy");
		LOGGER.debug("--->>>indexFlagMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/recommend" }, name = "访问首页-推荐")
	public String indexRecommend(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexRecommend <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeActivityFetcher.fetchHomeActivityRecommend(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/indexRecommendUI");
		LOGGER.debug("--->>>indexRecommend.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/index/recommend/more/ansy" }, name = "异步加载-推荐")
	public String indexRecommendMoreAnsy(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexRecommendMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			coeActivity.setRecommend(CoeActivity.RECOMMEND_YES.getValueInt());
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "topLevel","refreshTime");
			Page<CoeActivity> recommendPage = coeActivityWrapper.findPageForRecommend(coeActivity, pageable);
			model.addAttribute("recommendPage", recommendPage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/recommendMoreAnsy");
		LOGGER.debug("--->>>indexRecommendMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@RequestMapping(value = {"/index/all" }, name = "访问文章首页-活动")
	public String indexAll(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexAll <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeActivityFetcher.fetchHomeActivityJoin(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/indexJoinUI");
		LOGGER.debug("--->>>indexAll.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/all/ansy" }, name = "异步加载-活动")
	public String indexAllAnsy(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexAllAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeActivityFetcher.fetchHomeActivityJoin(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/joinAnsy");
		LOGGER.debug("--->>>indexAllAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/index/all/more/ansy" }, name = "异步加载-更多活动")
	public String indexAllMoreAnsy(@ModelAttribute("entity") CoeCategActivity coeCategActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexAllMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			Category category =coeCategActivity.getCategory();
			if(null == category) {
				category = new Category();
				coeCategActivity.setCategory(category);
			}
			String code = category.getCode();
			if(StringUtils.isBlank(code)) {
				category.setCode(Category.CODE_ACTIVITY_JOIN);
			}
			List<Long> excludeMainIdList = null;
			boolean cover = true;
			boolean banner = true;
			boolean author = false;
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "activity.topLevel","activity.refreshTime");
			Page<CoeCategActivity> joinPage = categoryWrapper.findPageForPublishWithChildren(coeCategActivity, excludeMainIdList, pageable, cover,
					banner, author);
			model.addAttribute("joinPage", joinPage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/joinMoreAnsy");
		LOGGER.debug("--->>>indexAllMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	
	@RequestMapping(value = {"/index/foot" }, name = "访问首页-徒步")
	public String indexFoot(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexFootUI <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeActivityFetcher.fetchHomeActivityFoot(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/indexFootUI");
		LOGGER.debug("--->>>indexFootUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/foot/ansy" }, name = "异步加载-徒步")
	public String indexFootAnsy(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexFootAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeActivityFetcher.fetchHomeActivityFoot(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/footAnsy");
		LOGGER.debug("--->>>indexFootAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/index/foot/more/ansy" }, name = "异步加载-更多徒步")
	public String indexFootMoreAnsy(@ModelAttribute("entity") CoeCategActivity coeCategActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexFootMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			Category category =coeCategActivity.getCategory();
			if(null == category) {
				category = new Category();
				coeCategActivity.setCategory(category);
			}
			String code = category.getCode();
			if(StringUtils.isBlank(code)) {
				category.setCode(Category.CODE_ACTIVITY_JOIN_FOOT);
			}
			List<Long> excludeMainIdList = null;
			boolean cover = true;
			boolean banner = true;
			boolean author = false;
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "activity.topLevel","activity.refreshTime");
			Page<CoeCategActivity> footPage = categoryWrapper.findPageForPublishWithChildren(coeCategActivity, excludeMainIdList, pageable, cover,
					banner, author);
			model.addAttribute("footPage", footPage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/footMoreAnsy");
		LOGGER.debug("--->>>indexFootMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = {"/index/travel" }, name = "访问首页-旅行")
	public String indexTravelUI(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexTravelUI <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeActivityFetcher.fetchHomeActivityTravel(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/indexTravelUI");
		LOGGER.debug("--->>>indexTravelUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/travel/ansy" }, name = "异步加载-旅行")
	public String indexTravelAnsy(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexTravelAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeActivityFetcher.fetchHomeActivityTravel(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/travelAnsy");
		LOGGER.debug("--->>>indexTravelAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/index/travel/more/ansy" }, name = "异步加载-更多旅行")
	public String indexTravelMoreAnsy(@ModelAttribute("entity") CoeCategActivity coeCategActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexTravelMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			Category category =coeCategActivity.getCategory();
			if(null == category) {
				category = new Category();
				coeCategActivity.setCategory(category);
			}
			String code = category.getCode();
			if(StringUtils.isBlank(code)) {
				category.setCode(Category.CODE_ACTIVITY_JOIN_TRAVEL);
			}
			List<Long> excludeMainIdList = null;
			boolean cover = true;
			boolean banner = true;
			boolean author = false;
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "activity.topLevel","activity.refreshTime");
			Page<CoeCategActivity> travelPage = categoryWrapper.findPageForPublishWithChildren(coeCategActivity, excludeMainIdList, pageable, cover,
					banner, author);
			model.addAttribute("travelPage", travelPage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/travelMoreAnsy");
		LOGGER.debug("--->>>indexTravelMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	
	@RequestMapping(value = {"/index/bike" }, name = "访问首页-骑行")
	public String indexBikeUI(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexBikeUI <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeActivityFetcher.fetchHomeActivityBike(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/indexBikeUI");
		LOGGER.debug("--->>>indexBikeUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/bike/ansy" }, name = "异步加载-骑行")
	public String indexBikeAnsy(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexBikeAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeActivityFetcher.fetchHomeActivityBike(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/bikeAnsy");
		LOGGER.debug("--->>>indexBikeAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	@RequestMapping(value = { "/index/bike/more/ansy" }, name = "异步加载-更多骑行")
	public String indexBikeMoreAnsy(@ModelAttribute("entity") CoeCategActivity coeCategActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexBikeMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			Category category =coeCategActivity.getCategory();
			if(null == category) {
				category = new Category();
				coeCategActivity.setCategory(category);
			}
			String code = category.getCode();
			if(StringUtils.isBlank(code)) {
				category.setCode(Category.CODE_ACTIVITY_JOIN_BIKE);
			}
			List<Long> excludeMainIdList = null;
			boolean cover = true;
			boolean banner = true;
			boolean author = false;
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "activity.topLevel","activity.refreshTime");
			Page<CoeCategActivity> bikePage = categoryWrapper.findPageForPublishWithChildren(coeCategActivity, excludeMainIdList, pageable, cover,
					banner, author);
			model.addAttribute("bikePage", bikePage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/bikeMoreAnsy");
		LOGGER.debug("--->>>indexBikeMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	
	@RequestMapping(value = {"/index/car" }, name = "访问首页-自驾")
	public String indexCarUI(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexCarUI <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeActivityFetcher.fetchHomeActivityCar(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/indexCarUI");
		LOGGER.debug("--->>>indexCarUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/car/ansy" }, name = "异步加载-自驾")
	public String indexCarAnsy(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexCarAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeActivityFetcher.fetchHomeActivityCar(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/carAnsy");
		LOGGER.debug("--->>>indexCarAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	@RequestMapping(value = { "/index/car/more/ansy" }, name = "异步加载-更多自驾")
	public String indexCarMoreAnsy(@ModelAttribute("entity") CoeCategActivity coeCategActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexCarMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			Category category =coeCategActivity.getCategory();
			if(null == category) {
				category = new Category();
				coeCategActivity.setCategory(category);
			}
			String code = category.getCode();
			if(StringUtils.isBlank(code)) {
				category.setCode(Category.CODE_ACTIVITY_JOIN_CAR);
			}
			List<Long> excludeMainIdList = null;
			boolean cover = true;
			boolean banner = true;
			boolean author = false;
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "activity.topLevel","activity.refreshTime");
			Page<CoeCategActivity> carPage = categoryWrapper.findPageForPublishWithChildren(coeCategActivity, excludeMainIdList, pageable, cover,
					banner, author);
			model.addAttribute("carPage", carPage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/carMoreAnsy");
		LOGGER.debug("--->>>indexCarMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	@RequestMapping(value = {"/index/volunteer" }, name = "访问首页-志愿者")
	public String indexVolunteerUI(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexVolunteerUI <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeActivityFetcher.fetchHomeActivityVolunteer(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/indexVolunteerUI");
		LOGGER.debug("--->>>indexVolunteerUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/volunteer/ansy" }, name = "异步加载-志愿者")
	public String indexVolunteerAnsy(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexVolunteerAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeActivityFetcher.fetchHomeActivityVolunteer(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/volunteerAnsy");
		LOGGER.debug("--->>>indexVolunteerAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	@RequestMapping(value = { "/index/volunteer/more/ansy" }, name = "异步加载-更多志愿者活动")
	public String indexVolunteerMoreAnsy(@ModelAttribute("entity") CoeCategActivity coeCategActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexVolunteerMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			Category category =coeCategActivity.getCategory();
			if(null == category) {
				category = new Category();
				coeCategActivity.setCategory(category);
			}
			String code = category.getCode();
			if(StringUtils.isBlank(code)) {
				category.setCode(Category.CODE_ACTIVITY_JOIN_VOLUNTEER);
			}
			List<Long> excludeMainIdList = null;
			boolean cover = true;
			boolean banner = true;
			boolean author = false;
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "activity.topLevel","activity.refreshTime");
			Page<CoeCategActivity> volunteerPage = categoryWrapper.findPageForPublishWithChildren(coeCategActivity, excludeMainIdList, pageable, cover,
					banner, author);
			model.addAttribute("volunteerPage", volunteerPage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/volunteerMoreAnsy");
		LOGGER.debug("--->>>indexVolunteerMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	@RequestMapping(value = {"/index/free" }, name = "访问首页-自由行")
	public String indexFreeUI(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexFreeUI <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeActivityFetcher.fetchHomeActivityFree(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/indexFreeUI");
		LOGGER.debug("--->>>indexFreeUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/free/ansy" }, name = "异步加载-自由行")
	public String indexFreeAnsy(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexFreeAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeActivityFetcher.fetchHomeActivityCar(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/freeAnsy");
		LOGGER.debug("--->>>indexFreeAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	@RequestMapping(value = { "/index/free/more/ansy" }, name = "异步加载-更多自由行")
	public String indexFreeMoreAnsy(@ModelAttribute("entity") CoeCategActivity coeCategActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexFreeMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			Category category =coeCategActivity.getCategory();
			if(null == category) {
				category = new Category();
				coeCategActivity.setCategory(category);
			}
			String code = category.getCode();
			if(StringUtils.isBlank(code)) {
				category.setCode(Category.CODE_ACTIVITY_JOIN_FREE);
			}
			List<Long> excludeMainIdList = null;
			boolean cover = true;
			boolean banner = true;
			boolean author = false;
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "activity.topLevel","activity.refreshTime");
			Page<CoeCategActivity> freePage = categoryWrapper.findPageForPublishWithChildren(coeCategActivity, excludeMainIdList, pageable, cover,
					banner, author);
			model.addAttribute("freePage", freePage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/freeMoreAnsy");
		LOGGER.debug("--->>>indexFreeMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	@RequestMapping(value = {"/index/eat" }, name = "访问首页-聚餐")
	public String indexEatUI(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexEatUI <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeActivityFetcher.fetchHomeActivityEat(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/indexEatUI");
		LOGGER.debug("--->>>indexEatUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/eat/ansy" }, name = "异步加载-聚餐")
	public String indexEatAnsy(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexEatAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeActivityFetcher.fetchHomeActivityCar(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/eatAnsy");
		LOGGER.debug("--->>>indexEatAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	@RequestMapping(value = { "/index/eat/more/ansy" }, name = "异步加载-更多聚餐")
	public String indexEatMoreAnsy(@ModelAttribute("entity") CoeCategActivity coeCategActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexEatMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			Category category =coeCategActivity.getCategory();
			if(null == category) {
				category = new Category();
				coeCategActivity.setCategory(category);
			}
			String code = category.getCode();
			if(StringUtils.isBlank(code)) {
				category.setCode(Category.CODE_ACTIVITY_JOIN_EAT);
			}
			List<Long> excludeMainIdList = null;
			boolean cover = true;
			boolean banner = true;
			boolean author = false;
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "activity.topLevel","activity.refreshTime");
			Page<CoeCategActivity> eatPage = categoryWrapper.findPageForPublishWithChildren(coeCategActivity, excludeMainIdList, pageable, cover,
					banner, author);
			model.addAttribute("eatPage", eatPage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/eatMoreAnsy");
		LOGGER.debug("--->>>indexEatMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	@RequestMapping(value = {"/index/pick" }, name = "访问首页-采摘")
	public String indexPickUI(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexPickUI <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeActivityFetcher.fetchHomeActivityPick(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/indexPickUI");
		LOGGER.debug("--->>>indexPickUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/pick/ansy" }, name = "异步加载-采摘")
	public String indexPickAnsy(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexPickAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeActivityFetcher.fetchHomeActivityPick(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/pickAnsy");
		LOGGER.debug("--->>>indexPickAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	@RequestMapping(value = { "/index/pick/more/ansy" }, name = "异步加载-更多采摘")
	public String indexPickMoreAnsy(@ModelAttribute("entity") CoeCategActivity coeCategActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexPickMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			Category category =coeCategActivity.getCategory();
			if(null == category) {
				category = new Category();
				coeCategActivity.setCategory(category);
			}
			String code = category.getCode();
			if(StringUtils.isBlank(code)) {
				category.setCode(Category.CODE_ACTIVITY_JOIN_PICK);
			}
			List<Long> excludeMainIdList = null;
			boolean cover = true;
			boolean banner = true;
			boolean author = false;
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "activity.topLevel","activity.refreshTime");
			Page<CoeCategActivity> pickPage = categoryWrapper.findPageForPublishWithChildren(coeCategActivity, excludeMainIdList, pageable, cover,
					banner, author);
			model.addAttribute("pickPage", pickPage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/pickMoreAnsy");
		LOGGER.debug("--->>>indexPickMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	@RequestMapping(value = {"/index/welfare" }, name = "访问首页-公益")
	public String indexWelfareUI(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexWelfareUI <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeActivityFetcher.fetchHomeActivityWelfare(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/indexWelfareUI");
		LOGGER.debug("--->>>indexWelfareUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/welfare/ansy" }, name = "异步加载-公益")
	public String indexWelfareAnsy(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexWelfareAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeActivityFetcher.fetchHomeActivityWelfare(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/welfareAnsy");
		LOGGER.debug("--->>>indexWelfareAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	@RequestMapping(value = { "/index/welfare/more/ansy" }, name = "异步加载-更多公益")
	public String indexWelfareMoreAnsy(@ModelAttribute("entity") CoeCategActivity coeCategActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexWelfareMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			Category category =coeCategActivity.getCategory();
			if(null == category) {
				category = new Category();
				coeCategActivity.setCategory(category);
			}
			String code = category.getCode();
			if(StringUtils.isBlank(code)) {
				category.setCode(Category.CODE_ACTIVITY_JOIN_WELFARE);
			}
			List<Long> excludeMainIdList = null;
			boolean cover = true;
			boolean banner = true;
			boolean author = false;
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "activity.topLevel","activity.refreshTime");
			Page<CoeCategActivity> welfarePage = categoryWrapper.findPageForPublishWithChildren(coeCategActivity, excludeMainIdList, pageable, cover,
					banner, author);
			model.addAttribute("welfarePage", welfarePage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/welfareMoreAnsy");
		LOGGER.debug("--->>>indexWelfareMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@RequestMapping(value = {"/index/roadline" }, name = "访问首页-线路")
	public String indexRoadlineUI(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexRoadlineUI <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeActivityFetcher.fetchHomeActivityRoadline(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/indexRoadlineUI");
		LOGGER.debug("--->>>indexRoadlineUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/roadline/ansy" }, name = "异步加载-线路")
	public String indexRoadlineAnsy(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexRoadlineAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeActivityFetcher.fetchHomeActivityRoadline(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/roadlineAnsy");
		LOGGER.debug("--->>>indexRoadlineAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/index/roadline/more/ansy" }, name = "异步加载-更多线路")
	public String indexRoadlineMoreAnsy(@ModelAttribute("entity") CoeCategActivity coeCategActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexRoadlineMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			Category category =coeCategActivity.getCategory();
			if(null == category) {
				category = new Category();
				coeCategActivity.setCategory(category);
			}
			String code = category.getCode();
			if(StringUtils.isBlank(code)) {
				category.setCode(Category.CODE_ROADLINE);
			}
			List<Long> excludeMainIdList = null;
			boolean cover = true;
			boolean banner = true;
			boolean author = false;
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "activity.topLevel","activity.refreshTime");
			Page<CoeCategActivity> roadlinePage = categoryWrapper.findPageForPublishWithChildren(coeCategActivity, excludeMainIdList, pageable, cover,
					banner, author);
			model.addAttribute("roadlinePage", roadlinePage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/roadlineMoreAnsy");
		LOGGER.debug("--->>>indexRoadlineMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	
	@RequestMapping(value = {"/index/equip" }, name = "访问首页-装备")
	public String indexEquipUI(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexEquipUI <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeActivityFetcher.fetchHomeActivityEquip(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/indexEquipUI");
		LOGGER.debug("--->>>indexEquipUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/equip/ansy" }, name = "异步加载-装备")
	public String indexEquipAnsy(@ModelAttribute("entity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexWelfareAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeActivityFetcher.fetchHomeActivityEquip(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/equipAnsy");
		LOGGER.debug("--->>>indexEquipAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	@RequestMapping(value = { "/index/equip/more/ansy" }, name = "异步加载-更多装备")
	public String indexEquipMoreAnsy(@ModelAttribute("entity") CoeCategActivity coeCategActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexEquipMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			Category category =coeCategActivity.getCategory();
			if(null == category) {
				category = new Category();
				coeCategActivity.setCategory(category);
			}
			String code = category.getCode();
			if(StringUtils.isBlank(code)) {
				category.setCode(Category.CODE_ARTICLE_EQUIP);
			}
			List<Long> excludeMainIdList = null;
			boolean cover = true;
			boolean banner = true;
			boolean author = false;
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "activity.topLevel","activity.refreshTime");
			Page<CoeCategActivity> equipPage = categoryWrapper.findPageForPublishWithChildren(coeCategActivity, excludeMainIdList, pageable, cover,
					banner, author);
			model.addAttribute("equipPage", equipPage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/equipMoreAnsy");
		LOGGER.debug("--->>>indexEquipMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	
	
	

	@RequestMapping(value = { "/index2" }, name = "访问coeActivity首页")
	public String indexUI2(@ModelAttribute("coeActivity") CoeActivity coeActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexUI2 <<<##");
		PageRequest pageable = null;
		if(page==null) {
			page=0;
		}
		if(size==null) {
			size=20;
		}
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			//1.查找文章列表
			//pageable = new PageRequest(page, size, Direction.DESC, "refreshTime");
			//Page<CoeActivity> pageResult = coeActivityWrapper.findByPublished(coeActivity, pageable);
			//model.addAttribute("page", pageResult);
			long t1=System.currentTimeMillis();
			
			CoeActivityPosition headPosition = new CoeActivityPosition();
			headPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
			headPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
			CoeActivityPageDto headDto = coeActivityFetcher.fetchActivityIndexHead(headPosition, CoeActivityFetcher.STRATEGY_DEFAULT);
			//model.addAttribute("headPosition", headPosition);
			model.addAttribute("headDto", headDto);
			long t2=System.currentTimeMillis();
			LOGGER.debug("t2-t1= {} ms",t2-t1);
			
			CoeActivityPosition explorerPosition = new CoeActivityPosition();
			explorerPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
			explorerPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
			CoeActivityPageDto explorerDto = coeActivityFetcher.fetchExplorer(explorerPosition, CoeActivityFetcher.STRATEGY_DEFAULT);
			//model.addAttribute("explorerPosition", explorerPosition);
			model.addAttribute("explorerDto", explorerDto);
			long t3=System.currentTimeMillis();
			LOGGER.debug("t3-t2= {} ms",t3-t2);
			
			CoeActivityPosition rememberPosition = new CoeActivityPosition();
			rememberPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
			rememberPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
			CoeActivityPageDto rememberDto = coeActivityFetcher.fetchSpecialRemember(rememberPosition, CoeActivityFetcher.STRATEGY_DEFAULT);
			//model.addAttribute("rememberPosition", rememberPosition);
			model.addAttribute("rememberDto", rememberDto);
			long t4=System.currentTimeMillis();
			LOGGER.debug("t4-t3= {} ms",t4-t3);
			
			CoeActivityPosition farmstayPosition = new CoeActivityPosition();
			farmstayPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
			farmstayPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
			CoeActivityPageDto farmstayDto = coeActivityFetcher.fetchEatFarmstay(farmstayPosition, CoeActivityFetcher.STRATEGY_DEFAULT);
			//model.addAttribute("farmstayPosition", farmstayPosition);
			model.addAttribute("farmstayDto", farmstayDto);
			long t5=System.currentTimeMillis();
			LOGGER.debug("t5-t4= {} ms",t5-t4);
			
			CoeActivityPosition outdoorPosition = new CoeActivityPosition();
			outdoorPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
			outdoorPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
			CoeActivityPageDto outdoorDto = coeActivityFetcher.fetchEatOutdoor(outdoorPosition, CoeActivityFetcher.STRATEGY_DEFAULT);
			//model.addAttribute("outdoorPosition", outdoorPosition);
			model.addAttribute("outdoorDto", outdoorDto);
			long t6=System.currentTimeMillis();
			LOGGER.debug("t6-t5= {} ms",t6-t5);
			
			CoeActivityPosition equipRealPosition = new CoeActivityPosition();
			equipRealPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
			equipRealPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
			CoeActivityPageDto equipRealDto = coeActivityFetcher.fetchEquipReal(equipRealPosition, CoeActivityFetcher.STRATEGY_DEFAULT);
			//model.addAttribute("equipRealPosition", equipRealPosition);
			model.addAttribute("equipRealDto", equipRealDto);
			long t7=System.currentTimeMillis();
			LOGGER.debug("t7-t6= {} ms",t7-t6);
			
			CoeActivityPosition actionFlagPosition = new CoeActivityPosition();
			actionFlagPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
			actionFlagPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
			CoeActivityPageDto actionFlagDto = coeActivityFetcher.fetchActionFlag(actionFlagPosition, CoeActivityFetcher.STRATEGY_DEFAULT);
			//model.addAttribute("actionFlagPosition", actionFlagPosition);
			model.addAttribute("actionFlagDto", actionFlagDto);
			long t8=System.currentTimeMillis();
			LOGGER.debug("t8-t7= {} ms",t8-t7);
			
			//2.查找分类树
			Long mainId = null;
			Long subId = null;
			Category categoryTree = coeActivityWrapper.findCategoryTree(mainId, subId);
			model.addAttribute("categoryTree", categoryTree);
			long t9=System.currentTimeMillis();
			LOGGER.debug("t9-t8= {} ms",t9-t8);
			
			//3.邀请人前三篇文章列表
			Long userId=user.getId();
			Long createdBy = user.getCreatedBy();
			Page<CoeActivity> invitorPage = coeActivityWrapper.findPageForInvitor(createdBy,userId);
			model.addAttribute("invitorPage", invitorPage);
			
			CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
			model.addAttribute("currentUser", coeUser);
			//将分页技能组装成圆形菜单数据
			List<CoeActivity> contentList = invitorPage.getContent();
			CircleMain circleMain = coeActivityConverter.convert(contentList,coeUser);
			model.addAttribute("circleMain", GsonBuilderUtils.toJson(circleMain));
			
			long t10=System.currentTimeMillis();
			LOGGER.debug("t10-t9= {} ms",t10-t9);
			
			//4.当前登录用户最新文章标签
			Page<CoeActivity>  tagPage = coeActivityWrapper.findPageTagForCurrentUser(userId);
			model.addAttribute("tagPage", tagPage);
			
			
			long t11=System.currentTimeMillis();
			LOGGER.debug("t11-t10= {} ms",t11-t10);
			LOGGER.debug("t11-t1= {} ms",t11-t1);
			
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/indexUI");
		LOGGER.debug("--->>>indexUI2.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/searchForCategoryFromHome" }, name = "加载更多")
	public String searchForCategoryFromHome(@ModelAttribute("commonDto")CoeActivityPageDto commonDto, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> searchForCategoryFromHome <<<##");
		CoeCategActivity coeCategActivity=commonDto.getCategory();
		//coeActivityPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
		//coeActivityPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
		
		PageRequest categoryPageable = null;
		if(page==null) {
			page=0;
		}
		if(size==null || size>30) {
			size=10;
		}
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ACTIVITY: {} ========== ", MAPPING_SUB_PATH_HOME_ACTIVITY);
		
		User user = UserUtils.getUser();
		
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			
			//2.查找分类树
			Long mainId = null;
			Long subId = null;
			Category categoryTree = coeActivityWrapper.findCategoryTree(mainId, subId);
			model.addAttribute("categoryTree", categoryTree);
			
			Long baseId = coeCategActivity.getBaseId();
			if(null == baseId) {
				String code = coeCategActivity.getCategory().getCode();
				if(StringUtils.isNotBlank(code)) {
					Category c = CategoryUtil.findByCode(code);
					coeCategActivity.setBaseId(c.getId());
				}
			}
			
			//1.查找文章列表
			categoryPageable = new PageRequest(page, size, Direction.DESC, "activity.refreshTime");
			
			int positionPage = 0;
			if(page<3) {
				//前三页，每页加载对应页码6条推荐
				//超出三页，只加载前6条推荐
				positionPage = page;
			}
			PageRequest positionPageable = new PageRequest(positionPage, 6, Direction.DESC, "topLevel", "refreshTime");
			
			commonDto = coeActivityFetcher.fetchCommonPage(CoeActivityFetcher.STRATEGY_DEFAULT, commonDto, positionPageable, categoryPageable);
			
			model.addAttribute("commonDto", commonDto);
			
			Long userId = user.getId();
			CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
			model.addAttribute("currentUser", coeUser);
			
		}
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ACTIVITY,"/searchForCategoryFromHome");
		LOGGER.debug("--->>>searchForCategoryFromHome.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	

}
