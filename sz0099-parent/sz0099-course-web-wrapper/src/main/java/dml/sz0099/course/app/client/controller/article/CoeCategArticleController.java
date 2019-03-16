package dml.sz0099.course.app.client.controller.article;

import java.util.List;

import javax.annotation.PostConstruct;
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
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dml.sz0099.course.app.client.validator.article.CoeCategArticleValidator;
import dml.sz0099.course.app.client.wrapper.article.CoeArticleWrapper;
import dml.sz0099.course.app.client.wrapper.article.CoeCategArticleWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.data.show.blooming.CircleMain;
import dml.sz0099.course.app.data.show.blooming.converter.CoeArticleConverter;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.CoeCategArticle;
import dml.sz0099.course.app.persist.entity.article.bo.CoeCategArticleBo;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.user.CoeUser;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeCategArticleController 控制转发
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
public class CoeCategArticleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeCategArticleController.class);

	@Autowired
	private CoeCategArticleValidator coeCategArticleValidator;

	@Autowired
	private CoeCategArticleWrapper coeCategArticleWrapper;
	
	@Autowired
	private CoeArticleWrapper coeArticleWrapper;
	
	@Autowired
	private CoeUserWrapper coeUserWrapper;
	
	@Autowired
	private CoeArticleConverter coeArticleConverter;

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

	@RequestMapping(value = { "/coeCategArticleUI" }, name = "访问coeCategArticleUI页面")
	public String accessCoeCategArticleUI(@ModelAttribute("coeCategArticleBo") CoeCategArticleBo coeCategArticleBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCoeCategArticleUI <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/coeCategArticleUI");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/searchForCategory" }, name = "查询分类列表")
	public String findPageForPublish(@ModelAttribute("entity")CoeCategArticle coeCategArticle, Integer page, Integer size, Model model){
		LOGGER.debug("##>>> findPageForPublish <<<##");
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
			LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_ARTICLE_BASIC);
			
			Long userId =user.getId();
			pageable = new PageRequest(page, size, Direction.DESC, "article.refreshTime");
			Page<CoeCategArticle> pageResult = coeCategArticleWrapper.findPageForPublish(coeCategArticle, pageable);
			model.addAttribute("page", pageResult);
			Long mainId = coeCategArticle.getCayMainId();
			Long subId = coeCategArticle.getCaySubId();
			Category categoryTree = coeArticleWrapper.findCategoryTree(mainId, subId);
			model.addAttribute("categoryTree", categoryTree);
			
			//当前登录用户最新文章标签
			Page<CoeArticle>  tagPage = coeArticleWrapper.findPageTagForCurrentUser(userId);
			model.addAttribute("tagPage", tagPage);
			
			CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
			model.addAttribute("currentUser", coeUser);
			
			//邀请人最新文章列表
			//Long userId=user.getId();
			Long createdBy = user.getCreatedBy();
			Page<CoeArticle> invitorPage = coeArticleWrapper.findPageForInvitor(createdBy,userId);
			model.addAttribute("invitorPage", invitorPage);
			
			//将分页技能组装成圆形菜单数据
			List<CoeArticle> contentList = invitorPage.getContent();
			CircleMain circleMain = coeArticleConverter.convert(contentList, coeUser);
			model.addAttribute("circleMain", GsonBuilderUtils.toJson(circleMain));
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/searchForCategory");
		LOGGER.debug("--->>>findPageForPublish.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/searchForCategoryFromDetail" }, name = "查询用户文章列表")
	public String findPageForPublishFromDetail(@ModelAttribute("entity")CoeCategArticle categCoeArticle, Integer page, Integer size, Model model){
		LOGGER.debug("##>>> findPageForPublishFromDetail <<<##");
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
			pageable = new PageRequest(page, size, Direction.DESC, "article.refreshTime");
			Page<CoeCategArticle> pageResult = coeCategArticleWrapper.findPageForPublishFromDetail(categCoeArticle, pageable);
			model.addAttribute("page", pageResult);
			Long cayMainId = categCoeArticle.getCayMainId();
			Long caySubId = categCoeArticle.getCaySubId();
			
			Category categoryTree = coeArticleWrapper.findCategoryTree(cayMainId, caySubId);
			model.addAttribute("categoryTree", categoryTree);
			
			Long userId=user.getId();
			Page<CoeArticle>  tagPage = coeArticleWrapper.findPageTagForCurrentUser(userId);
			model.addAttribute("tagPage", tagPage);
			
			Long coeArticleId = categCoeArticle.getMainId();
			CoeArticle pe=coeArticleWrapper.findByIdOnly(coeArticleId);
			CoeArticle coeArticle = categCoeArticle.getArticle();
			coeArticle.setUserId(pe.getUserId());
			coeArticle.setTitleLower(pe.getTitle());//借小标题传递显示参数
			coeArticle.setOriginalLink(pe.getOriginalLink());
			categCoeArticle.setArticle(coeArticle);
			
			//detail页面的userId
			Long fixUserId=coeArticle.getUserId();
			Page<CoeArticle> fixedPage = coeArticleWrapper.findPageForCurrentUser(fixUserId);
			model.addAttribute("fixedPage", fixedPage);
			
			
			CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
			model.addAttribute("currentUser", coeUser);
			//将分页技能组装成圆形菜单数据
			List<CoeArticle> contentList = fixedPage.getContent();
			CircleMain circleMain = coeArticleConverter.convert(contentList, coeUser);
			model.addAttribute("circleMain", GsonBuilderUtils.toJson(circleMain));
		}
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/searchForCategoryFromDetail");
		LOGGER.debug("--->>>findPageForPublishFromDetail.resultPath: {} ---", resultPath);
		return resultPath;
	}

}
