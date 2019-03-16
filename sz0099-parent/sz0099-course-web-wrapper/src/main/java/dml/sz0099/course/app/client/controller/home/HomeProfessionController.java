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

import dml.sz0099.course.app.client.multi.fetch.MultiHomeProfessionFetcher;
import dml.sz0099.course.app.client.resolver.adaptor.position.PositionProfessionAdaptor;
import dml.sz0099.course.app.client.resolver.category.CategoryUtil;
import dml.sz0099.course.app.client.validator.profession.ProfessionValidator;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.client.wrapper.profession.CategProfessionWrapper;
import dml.sz0099.course.app.client.wrapper.profession.ParagProfessionWrapper;
import dml.sz0099.course.app.client.wrapper.profession.ProfessionWrapper;
import dml.sz0099.course.app.data.show.blooming.CircleMain;
import dml.sz0099.course.app.data.show.blooming.converter.ProfessionConverter;
import dml.sz0099.course.app.data.show.layout.fetcher.ProfessionFetcher;
import dml.sz0099.course.app.data.show.layout.fetcher.dto.ProfessionPageDto;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.entity.profession.CategProfession;
import dml.sz0099.course.app.persist.entity.profession.Profession;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPosition;
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
@RequestMapping("/sz0099/ood/home/profession")
@Controller
public class HomeProfessionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomeProfessionController.class);

	@Autowired
	private ProfessionValidator professionValidator;

	@Autowired
	private ProfessionWrapper professionWrapper;
	
	@Autowired
	private ParagProfessionWrapper paragProfessionWrapper;
	
	@Autowired
	private CoeUserWrapper coeUserWrapper;
	
	@Autowired
	private ProfessionFetcher professionFetcher;
	
	@Autowired
	private ProfessionConverter professionConverter;
	
	@Autowired
	private PositionProfessionAdaptor positionProfessionAdaptor;
	
	@Autowired
	private MultiHomeProfessionFetcher multiHomeProfessionFetcher;
	
	@Autowired
	private CategProfessionWrapper categoryWrapper;

	//模板根目录
	private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
	//模块缩略名称目录
	private String MODULE_PATH_HOME = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_HOME.getAd());
	//模块主目录,一般与模块缩略名称相同
	private String MAPPING_PATH_HOME = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_HOME_PATH.currentPath());
	//模块二级目录
	private String MAPPING_SUB_PATH_HOME_PROFESSION = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_HOME_PATH_PROFESSION.currentPath());
	
	@PostConstruct
	public void init() {
		LOGGER.debug("========== TLE_DEFALT_FRONT: {} ========== ", TLE_DEFALT_FRONT);
		LOGGER.debug("========== MODULE_PATH_HOME: {} ========== ", MODULE_PATH_HOME);
		LOGGER.debug("========== MAPPING_PATH_HOME: {} ========== ", MAPPING_PATH_HOME);
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_PROFESSION: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
	}
	
	
	public HomeProfessionController() {
		LOGGER.debug("##>>> init HomeProfessionController <<<##");
	}
	
	@RequestMapping(value = { "index3" }, name = "访问技能首页")
	public String indexUI2(@ModelAttribute("profession") Profession profession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexUI2 <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_PROFESSION: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeProfessionFetcher.fetchHomeProfessionIndex(user, model);
		}
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/indexUI");
		LOGGER.debug("--->>>indexUI2.resultPath: {} ---", resultPath);
		return resultPath;
		
	}
	
	@RequestMapping(value = {"/index","/index/recommend" }, name = "访问首页-推荐")
	public String indexRecommend(@ModelAttribute("entity") Profession profession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexRecommend <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_PROFESSION: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeProfessionFetcher.fetchHomeProfessionRecommend(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/indexRecommendUI");
		LOGGER.debug("--->>>indexRecommend.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/recommend/ansy" }, name = "异步加载-推荐")
	public String indexRecommendAnsy(@ModelAttribute("entity") Profession profession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexRecommendAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_PROFESSION: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeProfessionFetcher.fetchHomeProfessionRecommend(user, model);
		}
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/recommendAnsy");
		LOGGER.debug("--->>>indexRecommendAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/index/recommend/more/ansy" }, name = "异步加载-推荐")
	public String indexRecommendMoreAnsy(@ModelAttribute("entity") Profession profession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexRecommendMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_PROFESSION: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			profession.setRecommend(CoeArticle.RECOMMEND_YES.getValueInt());
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "topLevel","refreshTime");
			Page<Profession> recommendPage = professionWrapper.findPageForRecommend(profession, pageable);
			model.addAttribute("recommendPage", recommendPage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/recommendMoreAnsy");
		LOGGER.debug("--->>>indexRecommendMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	@RequestMapping(value = {"/index/major" }, name = "访问首页-八仙过海")
	public String indexMajor(@ModelAttribute("entity") Profession profession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexMajor <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_PROFESSION: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeProfessionFetcher.fetchHomeProfessionMajor(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/indexMajorUI");
		LOGGER.debug("--->>>indexMajor.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/major/ansy" }, name = "异步加载-八仙过海")
	public String indexMajorAnsy(@ModelAttribute("entity") Profession profession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexMajorAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_PROFESSION: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeProfessionFetcher.fetchHomeProfessionMajor(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/majorAnsy");
		LOGGER.debug("--->>>indexMajorAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/index/major/more/ansy" }, name = "异步加载-八仙过海")
	public String indexMajorMoreAnsy(@ModelAttribute("entity") CategProfession categProfession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexMajorMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_PROFESSION: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			
			Category category =categProfession.getCategory();
			if(null == category) {
				category = new Category();
				categProfession.setCategory(category);
			}
			String code = category.getCode();
			if(StringUtils.isBlank(code)) {
				category.setCode(Category.CODE_PROFESSION_MAJOR);
			}
			List<Long> excludeMainIdList = null;
			boolean cover = true;
			boolean banner = true;
			boolean author = false;
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "profession.topLevel","profession.refreshTime");
			Page<CategProfession> majorPage = categoryWrapper.findPageForPublishWithChildren(categProfession, excludeMainIdList, pageable, cover,
					banner, author);
			
			model.addAttribute("majorPage", majorPage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/majorMoreAnsy");
		LOGGER.debug("--->>>indexMajorMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@RequestMapping(value = {"/index/outdoor" }, name = "访问首页-出行")
	public String indexOutdoor(@ModelAttribute("entity") Profession profession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexOutdoor <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_PROFESSION: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeProfessionFetcher.fetchHomeProfessionOutdoor(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/indexOutdoorUI");
		LOGGER.debug("--->>>indexOutdoor.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/outdoor/ansy" }, name = "异步加载-出行")
	public String indexOutdoorAnsy(@ModelAttribute("entity") Profession profession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexOutdoorAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_PROFESSION: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeProfessionFetcher.fetchHomeProfessionOutdoor(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/outdoorAnsy");
		LOGGER.debug("--->>>indexOutdoorAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/index/outdoor/more/ansy" }, name = "异步加载-出行")
	public String indexOutdoorMoreAnsy(@ModelAttribute("entity") CategProfession categProfession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexOutdoorMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_PROFESSION: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			Category category =categProfession.getCategory();
			if(null == category) {
				category = new Category();
				categProfession.setCategory(category);
			}
			String code = category.getCode();
			if(StringUtils.isBlank(code)) {
				category.setCode(Category.CODE_PROFESSION_OUTDOOR);
			}
			List<Long> excludeMainIdList = null;
			boolean cover = true;
			boolean banner = true;
			boolean author = false;
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "profession.topLevel","profession.refreshTime");
			Page<CategProfession> outdoorPage = categoryWrapper.findPageForPublishWithChildren(categProfession, excludeMainIdList, pageable, cover,
					banner, author);
			
			model.addAttribute("outdoorPage", outdoorPage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/outdoorMoreAnsy");
		LOGGER.debug("--->>>indexOutdoorMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@RequestMapping(value = {"/index/life" }, name = "访问首页-生活")
	public String indexLife(@ModelAttribute("entity") Profession profession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexLife <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_PROFESSION: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeProfessionFetcher.fetchHomeProfessionLife(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/indexLifeUI");
		LOGGER.debug("--->>>indexLife.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/life/ansy" }, name = "异步加载-生活")
	public String indexLifeAnsy(@ModelAttribute("entity") Profession profession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexLifeAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_PROFESSION: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeProfessionFetcher.fetchHomeProfessionLife(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/lifeAnsy");
		LOGGER.debug("--->>>indexLifeAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/index/life/more/ansy" }, name = "异步加载-生活")
	public String indexLifeMoreAnsy(@ModelAttribute("entity") CategProfession categProfession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexLifeMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_PROFESSION: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			Category category =categProfession.getCategory();
			if(null == category) {
				category = new Category();
				categProfession.setCategory(category);
			}
			String code = category.getCode();
			if(StringUtils.isBlank(code)) {
				category.setCode(Category.CODE_PROFESSION_LIFE);
			}
			List<Long> excludeMainIdList = null;
			boolean cover = true;
			boolean banner = true;
			boolean author = false;
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "profession.topLevel","profession.refreshTime");
			Page<CategProfession> lifePage = categoryWrapper.findPageForPublishWithChildren(categProfession, excludeMainIdList, pageable, cover,
					banner, author);
			
			model.addAttribute("lifePage", lifePage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/lifeMoreAnsy");
		LOGGER.debug("--->>>indexLifeMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@RequestMapping(value = {"/index/ingenuity" }, name = "访问首页-匠心")
	public String indexIngenuity(@ModelAttribute("entity") Profession profession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexIngenuity <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_PROFESSION: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeProfessionFetcher.fetchHomeProfessionIngenuity(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/indexIngenuityUI");
		LOGGER.debug("--->>>indexIngenuity.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/ingenuity/ansy" }, name = "异步加载-匠心")
	public String indexIngenuityAnsy(@ModelAttribute("entity") Profession profession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexIngenuityAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_PROFESSION: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeProfessionFetcher.fetchHomeProfessionIngenuity(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/ingenuityAnsy");
		LOGGER.debug("--->>>indexIngenuityAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/index/ingenuity/more/ansy" }, name = "异步加载-匠心")
	public String indexIngenuityMoreAnsy(@ModelAttribute("entity") CategProfession categProfession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexIngenuityMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_PROFESSION: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			Category category =categProfession.getCategory();
			if(null == category) {
				category = new Category();
				categProfession.setCategory(category);
			}
			String code = category.getCode();
			if(StringUtils.isBlank(code)) {
				category.setCode(Category.CODE_PROFESSION_MAJOR_INGENUITY);
			}
			List<Long> excludeMainIdList = null;
			boolean cover = true;
			boolean banner = true;
			boolean author = false;
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "profession.topLevel","profession.refreshTime");
			Page<CategProfession> ingenuityPage = categoryWrapper.findPageForPublishWithChildren(categProfession, excludeMainIdList, pageable, cover,
					banner, author);
			
			model.addAttribute("ingenuityPage", ingenuityPage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/ingenuityMoreAnsy");
		LOGGER.debug("--->>>indexIngenuityMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@RequestMapping(value = {"/index/relax" }, name = "访问首页-乐趣")
	public String indexRelax(@ModelAttribute("entity") Profession profession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexRelax <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_PROFESSION: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeProfessionFetcher.fetchHomeProfessionRelax(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/indexRelaxUI");
		LOGGER.debug("--->>>indexRelax.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/relax/ansy" }, name = "异步加载-乐趣")
	public String indexRelaxAnsy(@ModelAttribute("entity") Profession profession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexRelaxAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_PROFESSION: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeProfessionFetcher.fetchHomeProfessionRelax(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/relaxAnsy");
		LOGGER.debug("--->>>indexRelaxAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/index/relax/more/ansy" }, name = "异步加载-乐趣")
	public String indexRelaxMoreAnsy(@ModelAttribute("entity") CategProfession categProfession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexRelaxMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_PROFESSION: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			Category category =categProfession.getCategory();
			if(null == category) {
				category = new Category();
				categProfession.setCategory(category);
			}
			String code = category.getCode();
			if(StringUtils.isBlank(code)) {
				category.setCode(Category.CODE_PROFESSION_RELAX);
			}
			List<Long> excludeMainIdList = null;
			boolean cover = true;
			boolean banner = true;
			boolean author = false;
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "profession.topLevel","profession.refreshTime");
			Page<CategProfession> relaxPage = categoryWrapper.findPageForPublishWithChildren(categProfession, excludeMainIdList, pageable, cover,
					banner, author);
			
			model.addAttribute("relaxPage", relaxPage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/relaxMoreAnsy");
		LOGGER.debug("--->>>indexRelaxMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/native" }, name = "访问首页-特产")
	public String indexNative(@ModelAttribute("entity") Profession profession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexNative <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_PROFESSION: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeProfessionFetcher.fetchHomeProfessionNative(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/indexNativeUI");
		LOGGER.debug("--->>>indexNative.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/native/ansy" }, name = "异步加载-特产")
	public String indexNativeAnsy(@ModelAttribute("entity") Profession profession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexNativeAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_PROFESSION: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeProfessionFetcher.fetchHomeProfessionNative(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/nativeAnsy");
		LOGGER.debug("--->>>indexNativeAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/index/native/more/ansy" }, name = "异步加载-特产")
	public String indexNativeMoreAnsy(@ModelAttribute("entity") CategProfession categProfession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexNativeMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_PROFESSION: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			Category category =categProfession.getCategory();
			if(null == category) {
				category = new Category();
				categProfession.setCategory(category);
			}
			String code = category.getCode();
			if(StringUtils.isBlank(code)) {
				category.setCode(Category.CODE_PROFESSION_NATIVE);
			}
			List<Long> excludeMainIdList = null;
			boolean cover = true;
			boolean banner = true;
			boolean author = false;
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "profession.topLevel","profession.refreshTime");
			Page<CategProfession> nativePage = categoryWrapper.findPageForPublishWithChildren(categProfession, excludeMainIdList, pageable, cover,
					banner, author);
			
			model.addAttribute("nativePage", nativePage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/nativeMoreAnsy");
		LOGGER.debug("--->>>indexNativeMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@RequestMapping(value = {"/index/equip" }, name = "访问首页-装备")
	public String indexEquip(@ModelAttribute("entity") Profession profession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexEquip <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_PROFESSION: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeProfessionFetcher.fetchHomeProfessionEquip(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/indexEquipUI");
		LOGGER.debug("--->>>indexEquip.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/equip/ansy" }, name = "异步加载-装备")
	public String indexEquipAnsy(@ModelAttribute("entity") Profession profession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexEquipAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_PROFESSION: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeProfessionFetcher.fetchHomeProfessionEquip(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/equipAnsy");
		LOGGER.debug("--->>>indexEquipAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/index/equip/more/ansy" }, name = "异步加载-装备")
	public String indexEquipMoreAnsy(@ModelAttribute("entity") CategProfession categProfession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexEquipMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_PROFESSION: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			Category category =categProfession.getCategory();
			if(null == category) {
				category = new Category();
				categProfession.setCategory(category);
			}
			String code = category.getCode();
			if(StringUtils.isBlank(code)) {
				category.setCode(Category.CODE_PROFESSION_OUTDOOR_EQUIPMENT);
			}
			List<Long> excludeMainIdList = null;
			boolean cover = true;
			boolean banner = true;
			boolean author = false;
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "profession.topLevel","profession.refreshTime");
			Page<CategProfession> equipPage = categoryWrapper.findPageForPublishWithChildren(categProfession, excludeMainIdList, pageable, cover,
					banner, author);
			
			model.addAttribute("equipPage", equipPage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/equipMoreAnsy");
		LOGGER.debug("--->>>indexEquipMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@RequestMapping(value = {"/index/factory" }, name = "访问首页-直供")
	public String indexFactory(@ModelAttribute("entity") Profession profession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexFactory <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_PROFESSION: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeProfessionFetcher.fetchHomeProfessionEquip(user, model);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/indexFactoryUI");
		LOGGER.debug("--->>>indexFactory.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = {"/index/factory/ansy" }, name = "异步加载-直供")
	public String indexFactoryAnsy(@ModelAttribute("entity") CoeArticle coeArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexFactoryAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
		User user = UserUtils.getUser();
		if(null != user) {
			multiHomeProfessionFetcher.fetchHomeProfessionEquip(user, model);
		}
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/factoryAnsy");
		LOGGER.debug("--->>>indexFactoryAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/index/factory/more/ansy" }, name = "异步加载-直供")
	public String indexFactoryMoreAnsy(@ModelAttribute("entity") CategProfession categProfession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexFactoryMoreAnsy <<<##");
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_PROFESSION: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
		User user = UserUtils.getUser();
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		if(null != user) {
			Category category =categProfession.getCategory();
			if(null == category) {
				category = new Category();
				categProfession.setCategory(category);
			}
			String code = category.getCode();
			if(StringUtils.isBlank(code)) {
				category.setCode(Category.CODE_PROFESSION_MAJOR_FACTORY);
			}
			List<Long> excludeMainIdList = null;
			boolean cover = true;
			boolean banner = true;
			boolean author = false;
			Pageable pageable = new PageRequest(page,size, Direction.DESC, "profession.topLevel","profession.refreshTime");
			Page<CategProfession> factoryPage = categoryWrapper.findPageForPublishWithChildren(categProfession, excludeMainIdList, pageable, cover,
					banner, author);
			
			model.addAttribute("factoryPage", factoryPage);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/factoryMoreAnsy");
		LOGGER.debug("--->>>indexFactoryMoreAnsy.resultPath: {} ---", resultPath);
		return resultPath;
	}
	

	@RequestMapping(value = { "index2" }, name = "访问技能首页")
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
			/*pageable = new PageRequest(page, size, Direction.DESC, "refreshTime");
			Page<Profession> pageResult = professionWrapper.findByPublished(profession, pageable);
			model.addAttribute("page", pageResult);
			model.addAttribute("position", new Position());*/
			
			long t1=System.currentTimeMillis();
			ProfessionPosition headPosition = new ProfessionPosition();
			headPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
			headPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
			ProfessionPageDto headDto = professionFetcher.fetchIndexHead(headPosition, ProfessionFetcher.STRATEGY_DEFAULT);
			//model.addAttribute("headPosition", headPosition);
			model.addAttribute("headDto", headDto);
			long t2=System.currentTimeMillis();
			LOGGER.debug("t2-t1= {} ms",t2-t1);
			
			ProfessionPosition recommendPosition = new ProfessionPosition();
			recommendPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
			recommendPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
			ProfessionPageDto recommendDto = professionFetcher.fetchIndexRecommend(recommendPosition, ProfessionFetcher.STRATEGY_DEFAULT);
			model.addAttribute("recommendDto", recommendDto);
			long t3=System.currentTimeMillis();
			LOGGER.debug("t3-t2= {} ms",t3-t2);
			
			ProfessionPosition artisanPosition = new ProfessionPosition();
			artisanPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
			artisanPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
			ProfessionPageDto artisanDto = professionFetcher.fetchIndexArtisan(artisanPosition, ProfessionFetcher.STRATEGY_DEFAULT);
			model.addAttribute("artisanDto", artisanDto);
			long t4=System.currentTimeMillis();
			LOGGER.debug("t4-t3= {} ms",t4-t3);
			
			ProfessionPosition todayPosition = new ProfessionPosition();
			todayPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
			todayPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
			ProfessionPageDto todayDto = professionFetcher.fetchRecommendForMainCircle(todayPosition, ProfessionFetcher.STRATEGY_DEFAULT);
			model.addAttribute("todayDto", todayDto);
			long t5=System.currentTimeMillis();
			LOGGER.debug("t5-t4= {} ms",t5-t4);
			
			ProfessionPosition outdoorPosition = new ProfessionPosition();
			outdoorPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
			outdoorPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
			ProfessionPageDto outdoorDto = professionFetcher.fetchIndexOutdoor(outdoorPosition, ProfessionFetcher.STRATEGY_DEFAULT);
			model.addAttribute("outdoorDto", outdoorDto);
			long t6=System.currentTimeMillis();
			LOGGER.debug("t6-t5= {} ms",t6-t5);
			
			ProfessionPosition killPosition = new ProfessionPosition();
			killPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
			killPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
			ProfessionPageDto killDto = professionFetcher.fetchIndexKill(killPosition, ProfessionFetcher.STRATEGY_DEFAULT);
			model.addAttribute("killDto", killDto);
			long t7=System.currentTimeMillis();
			LOGGER.debug("t7-t6= {} ms",t7-t6);
			
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
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/indexUI");
		LOGGER.debug("--->>>indexUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/searchForCategoryFromHome" }, name = "加载更多")
	public String searchForCategoryFromHome(@ModelAttribute("commonDto")ProfessionPageDto commonDto, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> searchForCategoryFromHome <<<##");
		CategProfession categProfession=commonDto.getCategory();
		//professionPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
		//professionPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
		
		PageRequest categoryPageable = null;
		if(page==null) {
			page=0;
		}
		if(size==null || size>30) {
			size=10;
		}
		LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_PROFESSION);
		
		User user = UserUtils.getUser();
		
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			
			//2.查找分类树
			Long mainId = null;
			Long subId = null;
			Category categoryTree = professionWrapper.findCategoryTree(mainId, subId);
			model.addAttribute("categoryTree", categoryTree);
			
			Long baseId = categProfession.getBaseId();
			if(null == baseId) {
				String code = categProfession.getCategory().getCode();
				if(StringUtils.isNotBlank(code)) {
					Category c = CategoryUtil.findByCode(code);
					categProfession.setBaseId(c.getId());
				}
			}
			
			//1.查找文章列表
			categoryPageable = new PageRequest(page, size, Direction.DESC, "profession.refreshTime");
			
			int positionPage = 0;
			if(page<3) {
				//前三页，每页加载对应页码6条推荐
				//超出三页，只加载前6条推荐
				positionPage = page;
			}
			PageRequest positionPageable = new PageRequest(positionPage, 6, Direction.DESC, "topLevel", "refreshTime");
			
			commonDto = professionFetcher.fetchCommonPage(ProfessionFetcher.STRATEGY_DEFAULT, commonDto, positionPageable, categoryPageable);
			
			model.addAttribute("commonDto", commonDto);
			
			Long userId = user.getId();
			CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
			model.addAttribute("currentUser", coeUser);
			
		}
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_HOME_PROFESSION,"/searchForCategoryFromHome");
		LOGGER.debug("--->>>searchForCategoryFromHome.resultPath: {} ---", resultPath);
		return resultPath;
	}

}
