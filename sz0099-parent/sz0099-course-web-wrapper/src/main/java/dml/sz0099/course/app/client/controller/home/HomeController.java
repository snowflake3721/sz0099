/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.controller.home;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jit4j.app.persist.entity.auth.User;
import org.jit4j.core.persist.page.PageRequest;
import org.jit4j.core.webmvc.config.ResourcePathConfig;
import org.jit4j.core.webmvc.util.UserUtils;
import org.jit8j.core.util.GsonBuilderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dml.sz0099.course.app.client.wrapper.article.CoeArticleWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.data.show.blooming.CircleMain;
import dml.sz0099.course.app.data.show.blooming.converter.CoeArticleConverter;
import dml.sz0099.course.app.data.show.layout.fetcher.CoeArticleFetcher;
import dml.sz0099.course.app.data.show.layout.fetcher.ProfessionFetcher;
import dml.sz0099.course.app.data.show.layout.fetcher.dto.CoeArticlePageDto;
import dml.sz0099.course.app.data.show.layout.fetcher.dto.ProfessionPageDto;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePosition;
import dml.sz0099.course.app.persist.entity.article.bo.CoeArticleBo;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPosition;
import dml.sz0099.course.app.persist.entity.user.CoeUser;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-11-10 12:05:28
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-11-10	basic init
 * 
 * @formatter:on
 * </pre>
 */
@RequestMapping("/sz0099/ood")
@Controller
public class HomeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private CoeArticleWrapper coeArticleWrapper;
	
	@Autowired
	private CoeArticleConverter coeArticleConverter;
	
	@Autowired
	private CoeUserWrapper coeUserWrapper;
	
	@Autowired
	private CoeArticleFetcher coeArticleFetcher;
	
	@Autowired
	private ProfessionFetcher professionFetcher;
	
	//模板根目录
		private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
		//模块缩略名称目录
		private String MODULE_PATH_HOME = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_HOME.getAd());
		//模块主目录,一般与模块缩略名称相同
		private String MAPPING_PATH_HOME = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_HOME_PATH.currentPath());
		//模块二级目录
		private String MAPPING_SUB_PATH_HOME_BASIC = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_HOME_PATH_BASIC.currentPath());
		
		@RequestMapping(value = {"/index" }, name = "访问首页-推荐")
		public String indexUI(@ModelAttribute("entity") CoeArticle coeArticle, Integer page, Integer size, Model model, HttpServletRequest request) {
			return "forward:/sz0099/ood/home/article/index/recommend?st=general";
		}
	@RequestMapping(value = { "/index3" }, name = "访问首页")
	public String indexUI3(@ModelAttribute("coeArticleBo") CoeArticleBo coeArticleBo, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> indexUI <<<##");
		PageRequest pageable = null;
		if(page==null) {
			page=0;
		}
		if(size==null) {
			size=20;
		}
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_BASIC);
		
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			//邀请人技能列表
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
			
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_HOME,"/indexUI");
		LOGGER.debug("--->>>indexUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "home/fetchIndexFlagQX" }, name = "加载旗帜")
	public String fetchIndexFlagQX(@ModelAttribute("entity") CoeArticlePosition coeArticlePosition, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> fetchIndexFlagQX <<<##");
		PageRequest pageable = null;
		if(page==null) {
			page=0;
		}
		if(size==null) {
			size=2;
		}
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_BASIC);
		
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			//群侠旗帜列表
			if(null == coeArticlePosition) {
				coeArticlePosition = new CoeArticlePosition();
			}
			CoeArticlePageDto coeArticlePageDto = coeArticleFetcher.fetchIndexFlagQX(coeArticlePosition, CoeArticleFetcher.STRATEGY_DEFAULT, pageable);
			Page<CoeArticlePosition> flagPage = coeArticlePageDto.getPositionPage();
			model.addAttribute("entityPage", flagPage);
			
			coeArticlePosition.setSuccess(CoeArticlePosition.SUCCESS_YES);
			
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_HOME,"/indexFlagUI");
		LOGGER.debug("--->>>fetchIndexFlagQX.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "home/fetchIndexFlagXF" }, name = "加载旗帜")
	public String fetchIndexFlagXF(@ModelAttribute("entity") CoeArticlePosition coeArticlePosition, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> fetchIndexFlagXF <<<##");
		PageRequest pageable = null;
		if(page==null) {
			page=0;
		}
		if(size==null) {
			size=2;
		}
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_BASIC);
		
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			//邀请人技能列表
			if(null == coeArticlePosition) {
				coeArticlePosition = new CoeArticlePosition();
			}
			CoeArticlePageDto coeArticlePageDto = coeArticleFetcher.fetchIndexFlagXF(coeArticlePosition, CoeArticleFetcher.STRATEGY_DEFAULT, pageable);
			Page<CoeArticlePosition> flagPage = coeArticlePageDto.getPositionPage();
			
			model.addAttribute("entityPage", flagPage);
			
			coeArticlePosition.setSuccess(CoeArticlePosition.SUCCESS_YES);
			
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_HOME,"/indexFlagUI");
		LOGGER.debug("--->>>fetchIndexFlagXF.resultPath: {} ---", resultPath);
		return resultPath;
	}
	@RequestMapping(value = { "home/fetchIndexRoadLine" }, name = "加载路线")
	public String fetchIndexRoadLine(@ModelAttribute("entity") CoeArticlePosition coeArticlePosition, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> fetchIndexRoadLine <<<##");
		PageRequest pageable = null;
		if(page==null) {
			page=0;
		}
		if(size==null) {
			size=2;
		}
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_BASIC);
		
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			if(null == coeArticlePosition) {
				coeArticlePosition = new CoeArticlePosition();
			}
			
			CoeArticlePageDto coeArticlePageDto = coeArticleFetcher.fetchIndexRoadLine(coeArticlePosition, CoeArticleFetcher.STRATEGY_DEFAULT, pageable);
			Page<CoeArticlePosition> flagPage = coeArticlePageDto.getPositionPage();
			model.addAttribute("entityPage", flagPage);
			
			coeArticlePosition.setSuccess(CoeArticlePosition.SUCCESS_YES);
			
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_HOME,"/indexRoadLineUI");
		LOGGER.debug("--->>>fetchIndexRoadLine.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "home/fetchIndexRecommend" }, name = "加载技能推荐")
	public String fetchIndexRecommend(@ModelAttribute("entity") ProfessionPosition professionPosition, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> fetchIndexRecommend <<<##");
		PageRequest pageable = null;
		if(page==null) {
			page=0;
		}
		if(size==null) {
			size=2;
		}
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_BASIC);
		
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			if(null == professionPosition) {
				professionPosition = new ProfessionPosition();
			}
			ProfessionPageDto professionPageDto = professionFetcher.fetchIndexRecommend(professionPosition, ProfessionFetcher.STRATEGY_DEFAULT);
			Page<ProfessionPosition> flagPage = professionPageDto.getPositionPage();
			
			model.addAttribute("entityPage", flagPage);
			
			professionPosition.setSuccess(ProfessionPosition.SUCCESS_YES);
			
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_HOME,"/indexRecommendUI");
		LOGGER.debug("--->>>fetchIndexRecommend.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	@RequestMapping(value = { "home/fetchIndexIngenuity" }, name = "加载匠心")
	public String fetchIndexIngenuity(@ModelAttribute("entity") ProfessionPosition professionPosition, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> fetchIndexIngenuity <<<##");
		PageRequest pageable = null;
		if(page==null) {
			page=0;
		}
		if(size==null) {
			size=2;
		}
		LOGGER.debug("========== MAPPING_SUB_PATH_HOME_BASIC: {} ========== ", MAPPING_SUB_PATH_HOME_BASIC);
		
		User user = UserUtils.getUser();
		if(null == user) {
			//用户尚未登录，保存请求参数，以备下单需要
		}else {
			//邀请人技能列表
			if(null == professionPosition) {
				professionPosition = new ProfessionPosition();
			}
			
			ProfessionPageDto professionPageDto = professionFetcher.fetchIndexIngenuity(professionPosition, ProfessionFetcher.STRATEGY_DEFAULT);
			Page<ProfessionPosition> entityPage = professionPageDto.getPositionPage();
			model.addAttribute("entityPage", entityPage);
			
			professionPosition.setSuccess(CoeArticlePosition.SUCCESS_YES);
			
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_HOME,"/indexIngenuityUI");
		LOGGER.debug("--->>>fetchIndexIngenuity.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
}
