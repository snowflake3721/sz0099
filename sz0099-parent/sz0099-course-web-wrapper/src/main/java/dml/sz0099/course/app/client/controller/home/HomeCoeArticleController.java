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

import dml.sz0099.course.app.client.multi.fetch.MultiHomeArticleFetcher;
import dml.sz0099.course.app.client.resolver.category.CategoryUtil;
import dml.sz0099.course.app.client.validator.article.CoeArticleValidator;
import dml.sz0099.course.app.client.wrapper.article.CoeArticleWrapper;
import dml.sz0099.course.app.client.wrapper.article.CoeCategArticleWrapper;
import dml.sz0099.course.app.client.wrapper.article.ParagArticleWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeOrderWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.data.show.blooming.CircleMain;
import dml.sz0099.course.app.data.show.blooming.converter.CoeArticleConverter;
import dml.sz0099.course.app.data.show.layout.fetcher.CoeArticleFetcher;
import dml.sz0099.course.app.data.show.layout.fetcher.dto.CoeArticlePageDto;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePosition;
import dml.sz0099.course.app.persist.entity.article.CoeCategArticle;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.position.Position;
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
@RequestMapping("/sz0099/ood/home/article")
@Controller
public class HomeCoeArticleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomeCoeArticleController.class);

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
	private CoeCategArticleWrapper categoryWrapper;
	
	@Autowired
	private CoeArticleFetcher coeArticleFetcher;
	
	@Autowired
	private CoeArticleConverter coeArticleConverter;
	
	@Autowired
	private MultiHomeArticleFetcher multiHomeArticleFetcher;

	//模板根目录
			private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
			//模块缩略名称目录
			private String MODULE_PATH_HOME = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_HOME.getAd());
			//模块主目录,一般与模块缩略名称相同
			private String MAPPING_PATH_HOME = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_HOME_PATH.currentPath());
			//模块二级目录
			private String MAPPING_SUB_PATH_HOME_ARTICLE = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_HOME_PATH_ARTICLE.currentPath());
	
	@PostConstruct
	public void init() {
		LOGGER.debug("========== TLE_DEFALT_FRONT: {} ========== ", TLE_DEFALT_FRONT);
		LOGGER.debug("========== MODULE_PATH_HOME: {} ========== ", MODULE_PATH_HOME);
		LOGGER.debug("========== MAPPING_PATH_HOME: {} ========== ", MAPPING_PATH_HOME);
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_ARTICLE: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
	}
	
	
	public HomeCoeArticleController() {
		LOGGER.debug("##>>> init CoeArticleController <<<##");
	}
	
	@RequestMapping(value = { "/index" }, name = "访问coeArticle首页")
	public String indexUI(@ModelAttribute("coeArticle") CoeArticle coeArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexUI <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeArticleFetcher.fetchHomeArticleIndex(user, model);
		}
				
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ARTICLE,"/indexUI");
		LOGGER.debug("--->>>indexUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	@RequestMapping(value = {"/index/flag" }, name = "访问首页-群侠旗帜")
	public String indexFlag(@ModelAttribute("entity") CoeArticle coeArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexFlag <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeArticleFetcher.fetchHomeArticleFlag(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ARTICLE,"/indexFlagUI");
		LOGGER.debug("--->>>indexFlag.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/flag/ansy" }, name = "异步加载-群侠旗帜")
	public String indexFlagAnsy(@ModelAttribute("entity") CoeArticle coeArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexFlagAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeArticleFetcher.fetchHomeArticleFlag(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ARTICLE,"/flagAnsy");
		LOGGER.debug("--->>>indexFlagAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/index/flag/more/ansy" }, name = "异步加载-更多群侠旗帜")
	public String indexFlagMoreAnsy(@ModelAttribute("entity") CoeCategArticle coeCategArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexFlagMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			Category category =coeCategArticle.getCategory();
			if(null == category) {
				category = new Category();
				coeCategArticle.setCategory(category);
			}
			String code = category.getCode();
			if(StringUtils.isBlank(code)) {
				category.setCode(Category.CODE_ARTICLE_FLAG);
			}
			List<Long> excludeMainIdList = null;
			boolean cover = true;
			boolean banner = true;
			boolean author = false;
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "article.topLevel","article.refreshTime");
			Page<CoeCategArticle> flagPage = categoryWrapper.findPageForPublishWithChildren(coeCategArticle, excludeMainIdList, pageable, cover,
					banner, author);
			model.addAttribute("flagPage", flagPage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ARTICLE,"/flagMoreAnsy");
		LOGGER.debug("--->>>indexFlagMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/recommend" }, name = "访问首页-推荐")
	public String indexRecommend(@ModelAttribute("entity") CoeArticle coeArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexRecommend <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeArticleFetcher.fetchHomeArticleRecommend(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ARTICLE,"/indexRecommendUI");
		LOGGER.debug("--->>>indexRecommend.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/index/recommend/more/ansy" }, name = "异步加载-推荐")
	public String indexRecommendMoreAnsy(@ModelAttribute("entity") CoeArticle coeArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexRecommendMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			coeArticle.setRecommend(CoeArticle.RECOMMEND_YES.getValueInt());
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "topLevel","refreshTime");
			Page<CoeArticle> recommendPage = coeArticleWrapper.findPageForRecommend(coeArticle, pageable);
			model.addAttribute("recommendPage", recommendPage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ARTICLE,"/recommendMoreAnsy");
		LOGGER.debug("--->>>indexRecommendMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	
	
	
	
	@RequestMapping(value = {"/index/foot" }, name = "访问首页-徒步")
	public String indexFoot(@ModelAttribute("entity") CoeArticle coeArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexFootUI <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeArticleFetcher.fetchHomeArticleFoot(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ARTICLE,"/indexFootUI");
		LOGGER.debug("--->>>indexFootUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/foot/ansy" }, name = "异步加载-徒步")
	public String indexFootAnsy(@ModelAttribute("entity") CoeArticle coeArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexFootAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeArticleFetcher.fetchHomeArticleFoot(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ARTICLE,"/footAnsy");
		LOGGER.debug("--->>>indexFootAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/index/foot/more/ansy" }, name = "异步加载-更多徒步")
	public String indexFootMoreAnsy(@ModelAttribute("entity") CoeCategArticle coeCategArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexFootMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			Category category =coeCategArticle.getCategory();
			if(null == category) {
				category = new Category();
				coeCategArticle.setCategory(category);
			}
			String code = category.getCode();
			if(StringUtils.isBlank(code)) {
				category.setCode(Category.CODE_ARTICLE_SHARED_FOOT);
			}
			List<Long> excludeMainIdList = null;
			boolean cover = true;
			boolean banner = true;
			boolean author = false;
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "article.topLevel","article.refreshTime");
			Page<CoeCategArticle> footPage = categoryWrapper.findPageForPublishWithChildren(coeCategArticle, excludeMainIdList, pageable, cover,
					banner, author);
			model.addAttribute("footPage", footPage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ARTICLE,"/footMoreAnsy");
		LOGGER.debug("--->>>indexFootMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = {"/index/travel" }, name = "访问首页-旅行")
	public String indexTravelUI(@ModelAttribute("entity") CoeArticle coeArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexTravelUI <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeArticleFetcher.fetchHomeArticleTravel(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ARTICLE,"/indexTravelUI");
		LOGGER.debug("--->>>indexTravelUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/travel/ansy" }, name = "异步加载-旅行")
	public String indexTravelAnsy(@ModelAttribute("entity") CoeArticle coeArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexTravelAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeArticleFetcher.fetchHomeArticleTravel(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ARTICLE,"/travelAnsy");
		LOGGER.debug("--->>>indexTravelAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/index/travel/more/ansy" }, name = "异步加载-更多旅行")
	public String indexTravelMoreAnsy(@ModelAttribute("entity") CoeCategArticle coeCategArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexTravelMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			Category category =coeCategArticle.getCategory();
			if(null == category) {
				category = new Category();
				coeCategArticle.setCategory(category);
			}
			String code = category.getCode();
			if(StringUtils.isBlank(code)) {
				category.setCode(Category.CODE_ARTICLE_SHARED_TRAVEL);
			}
			List<Long> excludeMainIdList = null;
			boolean cover = true;
			boolean banner = true;
			boolean author = false;
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "article.topLevel","article.refreshTime");
			Page<CoeCategArticle> travelPage = categoryWrapper.findPageForPublishWithChildren(coeCategArticle, excludeMainIdList, pageable, cover,
					banner, author);
			model.addAttribute("travelPage", travelPage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ARTICLE,"/travelMoreAnsy");
		LOGGER.debug("--->>>indexTravelMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	
	@RequestMapping(value = {"/index/bike" }, name = "访问首页-骑行")
	public String indexBikeUI(@ModelAttribute("entity") CoeArticle coeArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexBikeUI <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeArticleFetcher.fetchHomeArticleBike(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ARTICLE,"/indexBikeUI");
		LOGGER.debug("--->>>indexBikeUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/bike/ansy" }, name = "异步加载-骑行")
	public String indexBikeAnsy(@ModelAttribute("entity") CoeArticle coeArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexBikeAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeArticleFetcher.fetchHomeArticleBike(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ARTICLE,"/bikeAnsy");
		LOGGER.debug("--->>>indexBikeAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	@RequestMapping(value = { "/index/bike/more/ansy" }, name = "异步加载-更多骑行")
	public String indexBikeMoreAnsy(@ModelAttribute("entity") CoeCategArticle coeCategArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexBikeMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			Category category =coeCategArticle.getCategory();
			if(null == category) {
				category = new Category();
				coeCategArticle.setCategory(category);
			}
			String code = category.getCode();
			if(StringUtils.isBlank(code)) {
				category.setCode(Category.CODE_ARTICLE_SHARED_BIKE);
			}
			List<Long> excludeMainIdList = null;
			boolean cover = true;
			boolean banner = true;
			boolean author = false;
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "article.topLevel","article.refreshTime");
			Page<CoeCategArticle> bikePage = categoryWrapper.findPageForPublishWithChildren(coeCategArticle, excludeMainIdList, pageable, cover,
					banner, author);
			model.addAttribute("bikePage", bikePage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ARTICLE,"/bikeMoreAnsy");
		LOGGER.debug("--->>>indexBikeMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@RequestMapping(value = {"/index/welfare" }, name = "访问首页-公益")
	public String indexWelfareUI(@ModelAttribute("entity") CoeArticle coeArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexWelfareUI <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeArticleFetcher.fetchHomeArticleWelfare(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ARTICLE,"/indexWelfareUI");
		LOGGER.debug("--->>>indexWelfareUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/welfare/ansy" }, name = "异步加载-公益")
	public String indexWelfareAnsy(@ModelAttribute("entity") CoeArticle coeArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexWelfareAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeArticleFetcher.fetchHomeArticleWelfare(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ARTICLE,"/welfareAnsy");
		LOGGER.debug("--->>>indexWelfareAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	@RequestMapping(value = { "/index/welfare/more/ansy" }, name = "异步加载-更多公益")
	public String indexWelfareMoreAnsy(@ModelAttribute("entity") CoeCategArticle coeCategArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexWelfareMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			Category category =coeCategArticle.getCategory();
			if(null == category) {
				category = new Category();
				coeCategArticle.setCategory(category);
			}
			String code = category.getCode();
			if(StringUtils.isBlank(code)) {
				category.setCode(Category.CODE_ARTICLE_SHARED_WELFARE);
			}
			List<Long> excludeMainIdList = null;
			boolean cover = true;
			boolean banner = true;
			boolean author = false;
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "article.topLevel","article.refreshTime");
			Page<CoeCategArticle> welfarePage = categoryWrapper.findPageForPublishWithChildren(coeCategArticle, excludeMainIdList, pageable, cover,
					banner, author);
			model.addAttribute("welfarePage", welfarePage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ARTICLE,"/welfareMoreAnsy");
		LOGGER.debug("--->>>indexWelfareMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@RequestMapping(value = {"/index/roadline" }, name = "访问首页-线路")
	public String indexRoadlineUI(@ModelAttribute("entity") CoeArticle coeArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexRoadlineUI <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeArticleFetcher.fetchHomeArticleRoadline(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ARTICLE,"/indexRoadlineUI");
		LOGGER.debug("--->>>indexRoadlineUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/roadline/ansy" }, name = "异步加载-线路")
	public String indexRoadlineAnsy(@ModelAttribute("entity") CoeArticle coeArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexRoadlineAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeArticleFetcher.fetchHomeArticleRoadline(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ARTICLE,"/roadlineAnsy");
		LOGGER.debug("--->>>indexRoadlineAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/index/roadline/more/ansy" }, name = "异步加载-更多线路")
	public String indexRoadlineMoreAnsy(@ModelAttribute("entity") CoeCategArticle coeCategArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexRoadlineMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			Category category =coeCategArticle.getCategory();
			if(null == category) {
				category = new Category();
				coeCategArticle.setCategory(category);
			}
			String code = category.getCode();
			if(StringUtils.isBlank(code)) {
				category.setCode(Category.CODE_ROADLINE);
			}
			List<Long> excludeMainIdList = null;
			boolean cover = true;
			boolean banner = true;
			boolean author = false;
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "article.topLevel","article.refreshTime");
			Page<CoeCategArticle> roadlinePage = categoryWrapper.findPageForPublishWithChildren(coeCategArticle, excludeMainIdList, pageable, cover,
					banner, author);
			model.addAttribute("roadlinePage", roadlinePage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ARTICLE,"/roadlineMoreAnsy");
		LOGGER.debug("--->>>indexRoadlineMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	
	@RequestMapping(value = {"/index/eat" }, name = "访问首页-美食")
	public String indexEatUI(@ModelAttribute("entity") CoeArticle coeArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexEatUI <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeArticleFetcher.fetchHomeArticleRoadline(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ARTICLE,"/indexEatUI");
		LOGGER.debug("--->>>indexEatUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/eat/ansy" }, name = "异步加载-美食")
	public String indexEatAnsy(@ModelAttribute("entity") CoeArticle coeArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexEatAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeArticleFetcher.fetchHomeArticleRoadline(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ARTICLE,"/eatAnsy");
		LOGGER.debug("--->>>indexEatAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/index/eat/more/ansy" }, name = "异步加载-更多美食")
	public String indexEatMoreAnsy(@ModelAttribute("entity") CoeCategArticle coeCategArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexEatMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			Category category =coeCategArticle.getCategory();
			if(null == category) {
				category = new Category();
				coeCategArticle.setCategory(category);
			}
			String code = category.getCode();
			if(StringUtils.isBlank(code)) {
				category.setCode(Category.CODE_ARTICLE_EAT);
			}
			List<Long> excludeMainIdList = null;
			boolean cover = true;
			boolean banner = true;
			boolean author = false;
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "article.topLevel","article.refreshTime");
			Page<CoeCategArticle> eatPage = categoryWrapper.findPageForPublishWithChildren(coeCategArticle, excludeMainIdList, pageable, cover,
					banner, author);
			model.addAttribute("eatPage", eatPage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ARTICLE,"/eatMoreAnsy");
		LOGGER.debug("--->>>indexEatMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = {"/index/equip" }, name = "访问首页-装备")
	public String indexEquipUI(@ModelAttribute("entity") CoeArticle coeArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexEquipUI <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeArticleFetcher.fetchHomeArticleEquip(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ARTICLE,"/indexEquipUI");
		LOGGER.debug("--->>>indexEquipUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/equip/ansy" }, name = "异步加载-装备")
	public String indexEquipAnsy(@ModelAttribute("entity") CoeArticle coeArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexWelfareAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeArticleFetcher.fetchHomeArticleEquip(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ARTICLE,"/equipAnsy");
		LOGGER.debug("--->>>indexEquipAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	@RequestMapping(value = { "/index/equip/more/ansy" }, name = "异步加载-更多装备")
	public String indexEquipMoreAnsy(@ModelAttribute("entity") CoeCategArticle coeCategArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexEquipMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			Category category =coeCategArticle.getCategory();
			if(null == category) {
				category = new Category();
				coeCategArticle.setCategory(category);
			}
			String code = category.getCode();
			if(StringUtils.isBlank(code)) {
				category.setCode(Category.CODE_ARTICLE_EQUIP);
			}
			List<Long> excludeMainIdList = null;
			boolean cover = true;
			boolean banner = true;
			boolean author = false;
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "article.topLevel","article.refreshTime");
			Page<CoeCategArticle> equipPage = categoryWrapper.findPageForPublishWithChildren(coeCategArticle, excludeMainIdList, pageable, cover,
					banner, author);
			model.addAttribute("equipPage", equipPage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ARTICLE,"/equipMoreAnsy");
		LOGGER.debug("--->>>indexEquipMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	
	
	

	@RequestMapping(value = { "/index2" }, name = "访问coeArticle首页")
	public String indexUI2(@ModelAttribute("coeArticle") CoeArticle coeArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexUI2 <<<##");
		PageRequest pageable = null;
		if(page==null) {
			page=0;
		}
		if(size==null) {
			size=20;
		}
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
		
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			//1.查找文章列表
			//pageable = new PageRequest(page, size, Direction.DESC, "refreshTime");
			//Page<CoeArticle> pageResult = coeArticleWrapper.findByPublished(coeArticle, pageable);
			//model.addAttribute("page", pageResult);
			long t1=System.currentTimeMillis();
			
			CoeArticlePosition headPosition = new CoeArticlePosition();
			headPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
			headPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
			CoeArticlePageDto headDto = coeArticleFetcher.fetchArticleIndexHead(headPosition, CoeArticleFetcher.STRATEGY_DEFAULT);
			//model.addAttribute("headPosition", headPosition);
			model.addAttribute("headDto", headDto);
			long t2=System.currentTimeMillis();
			LOGGER.debug("t2-t1= {} ms",t2-t1);
			
			CoeArticlePosition explorerPosition = new CoeArticlePosition();
			explorerPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
			explorerPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
			CoeArticlePageDto explorerDto = coeArticleFetcher.fetchExplorer(explorerPosition, CoeArticleFetcher.STRATEGY_DEFAULT);
			//model.addAttribute("explorerPosition", explorerPosition);
			model.addAttribute("explorerDto", explorerDto);
			long t3=System.currentTimeMillis();
			LOGGER.debug("t3-t2= {} ms",t3-t2);
			
			CoeArticlePosition rememberPosition = new CoeArticlePosition();
			rememberPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
			rememberPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
			CoeArticlePageDto rememberDto = coeArticleFetcher.fetchSpecialRemember(rememberPosition, CoeArticleFetcher.STRATEGY_DEFAULT);
			//model.addAttribute("rememberPosition", rememberPosition);
			model.addAttribute("rememberDto", rememberDto);
			long t4=System.currentTimeMillis();
			LOGGER.debug("t4-t3= {} ms",t4-t3);
			
			CoeArticlePosition farmstayPosition = new CoeArticlePosition();
			farmstayPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
			farmstayPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
			CoeArticlePageDto farmstayDto = coeArticleFetcher.fetchEatFarmstay(farmstayPosition, CoeArticleFetcher.STRATEGY_DEFAULT);
			//model.addAttribute("farmstayPosition", farmstayPosition);
			model.addAttribute("farmstayDto", farmstayDto);
			long t5=System.currentTimeMillis();
			LOGGER.debug("t5-t4= {} ms",t5-t4);
			
			CoeArticlePosition outdoorPosition = new CoeArticlePosition();
			outdoorPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
			outdoorPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
			CoeArticlePageDto outdoorDto = coeArticleFetcher.fetchEatOutdoor(outdoorPosition, CoeArticleFetcher.STRATEGY_DEFAULT);
			//model.addAttribute("outdoorPosition", outdoorPosition);
			model.addAttribute("outdoorDto", outdoorDto);
			long t6=System.currentTimeMillis();
			LOGGER.debug("t6-t5= {} ms",t6-t5);
			
			CoeArticlePosition equipRealPosition = new CoeArticlePosition();
			equipRealPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
			equipRealPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
			CoeArticlePageDto equipRealDto = coeArticleFetcher.fetchEquipReal(equipRealPosition, CoeArticleFetcher.STRATEGY_DEFAULT);
			//model.addAttribute("equipRealPosition", equipRealPosition);
			model.addAttribute("equipRealDto", equipRealDto);
			long t7=System.currentTimeMillis();
			LOGGER.debug("t7-t6= {} ms",t7-t6);
			
			CoeArticlePosition actionFlagPosition = new CoeArticlePosition();
			actionFlagPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
			actionFlagPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
			CoeArticlePageDto actionFlagDto = coeArticleFetcher.fetchActionFlag(actionFlagPosition, CoeArticleFetcher.STRATEGY_DEFAULT);
			//model.addAttribute("actionFlagPosition", actionFlagPosition);
			model.addAttribute("actionFlagDto", actionFlagDto);
			long t8=System.currentTimeMillis();
			LOGGER.debug("t8-t7= {} ms",t8-t7);
			
			//2.查找分类树
			Long mainId = null;
			Long subId = null;
			Category categoryTree = coeArticleWrapper.findCategoryTree(mainId, subId);
			model.addAttribute("categoryTree", categoryTree);
			long t9=System.currentTimeMillis();
			LOGGER.debug("t9-t8= {} ms",t9-t8);
			
			//3.邀请人前三篇文章列表
			Long userId=user.getId();
			Long createdBy = user.getCreatedBy();
			Page<CoeArticle> invitorPage = coeArticleWrapper.findPageForInvitor(createdBy,userId);
			model.addAttribute("invitorPage", invitorPage);
			
			CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
			model.addAttribute("currentUser", coeUser);
			//将分页技能组装成圆形菜单数据
			List<CoeArticle> contentList = invitorPage.getContent();
			CircleMain circleMain = coeArticleConverter.convert(contentList,coeUser);
			model.addAttribute("circleMain", GsonBuilderUtils.toJson(circleMain));
			
			long t10=System.currentTimeMillis();
			LOGGER.debug("t10-t9= {} ms",t10-t9);
			
			//4.当前登录用户最新文章标签
			Page<CoeArticle>  tagPage = coeArticleWrapper.findPageTagForCurrentUser(userId);
			model.addAttribute("tagPage", tagPage);
			
			
			long t11=System.currentTimeMillis();
			LOGGER.debug("t11-t10= {} ms",t11-t10);
			LOGGER.debug("t11-t1= {} ms",t11-t1);
			
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ARTICLE,"/indexUI");
		LOGGER.debug("--->>>indexUI2.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/searchForCategoryFromHome" }, name = "加载更多")
	public String searchForCategoryFromHome(@ModelAttribute("commonDto")CoeArticlePageDto commonDto, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> searchForCategoryFromHome <<<##");
		CoeCategArticle coeCategArticle=commonDto.getCategory();
		//coeArticlePosition.setPonMainId(Robot.ROBOT_PLAT.getId());
		//coeArticlePosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
		
		PageRequest categoryPageable = null;
		if(page==null) {
			page=0;
		}
		if(size==null || size>30) {
			size=10;
		}
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_ARTICLE);
		
		User user = UserUtils.getUser();
		
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			
			//2.查找分类树
			Long mainId = null;
			Long subId = null;
			Category categoryTree = coeArticleWrapper.findCategoryTree(mainId, subId);
			model.addAttribute("categoryTree", categoryTree);
			
			Long baseId = coeCategArticle.getBaseId();
			if(null == baseId) {
				String code = coeCategArticle.getCategory().getCode();
				if(StringUtils.isNotBlank(code)) {
					Category c = CategoryUtil.findByCode(code);
					coeCategArticle.setBaseId(c.getId());
				}
			}
			
			//1.查找文章列表
			categoryPageable = new PageRequest(page, size, Direction.DESC, "article.refreshTime");
			
			int positionPage = 0;
			if(page<3) {
				//前三页，每页加载对应页码6条推荐
				//超出三页，只加载前6条推荐
				positionPage = page;
			}
			PageRequest positionPageable = new PageRequest(positionPage, 6, Direction.DESC, "topLevel", "refreshTime");
			
			commonDto = coeArticleFetcher.fetchCommonPage(CoeArticleFetcher.STRATEGY_DEFAULT, commonDto, positionPageable, categoryPageable);
			
			model.addAttribute("commonDto", commonDto);
			
			Long userId = user.getId();
			CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
			model.addAttribute("currentUser", coeUser);
			
		}
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_ARTICLE,"/searchForCategoryFromHome");
		LOGGER.debug("--->>>searchForCategoryFromHome.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	

}
