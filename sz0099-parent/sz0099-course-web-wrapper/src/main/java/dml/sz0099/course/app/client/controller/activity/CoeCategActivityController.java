package dml.sz0099.course.app.client.controller.activity;

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

import dml.sz0099.course.app.client.validator.activity.CoeCategActivityValidator;
import dml.sz0099.course.app.client.wrapper.activity.CoeActivityWrapper;
import dml.sz0099.course.app.client.wrapper.activity.CoeCategActivityWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.data.show.blooming.CircleMain;
import dml.sz0099.course.app.data.show.blooming.converter.CoeActivityConverter;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeCategActivity;
import dml.sz0099.course.app.persist.entity.activity.bo.CoeCategActivityBo;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.user.CoeUser;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeCategActivityController 控制转发
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
public class CoeCategActivityController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeCategActivityController.class);

	@Autowired
	private CoeCategActivityValidator coeCategActivityValidator;

	@Autowired
	private CoeCategActivityWrapper coeCategActivityWrapper;
	
	@Autowired
	private CoeActivityWrapper coeActivityWrapper;
	
	@Autowired
	private CoeUserWrapper coeUserWrapper;
	
	@Autowired
	private CoeActivityConverter coeActivityConverter;

	//模板根目录
		private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
		//模块缩略名称目录
		private String MODULE_PATH_ARTICLE = ResourcePathConfig.getModulesPath(CourseAppModule.APP_OOD_ACTIVITY.getAd());
		//模块主目录,一般与模块缩略名称相同
		private String MAPPING_PATH_ARTICLE = ResourcePathConfig.getModulesPath(CourseAppModule.APP_OOD_ACTIVITY_PATH.currentPath());
		//模块二级目录
		private String MAPPING_SUB_PATH_ARTICLE_BASIC = ResourcePathConfig.getModulesPath(CourseAppModule.APP_OOD_ACTIVITY_PATH_BASIC.currentPath());
		
		@PostConstruct
		public void init() {
			LOGGER.debug("========== TLE_DEFALT_FRONT: {} ========== ", TLE_DEFALT_FRONT);
			LOGGER.debug("========== MODULE_PATH_ARTICLE: {} ========== ", MODULE_PATH_ARTICLE);
			LOGGER.debug("========== MAPPING_PATH_ARTICLE: {} ========== ", MAPPING_PATH_ARTICLE);
			LOGGER.debug("========== MAPPING_SUB_PATH_ARTICLE_BASIC: {} ========== ", MAPPING_SUB_PATH_ARTICLE_BASIC);
		}

	@RequestMapping(value = { "/coeCategActivityUI" }, name = "访问coeCategActivityUI页面")
	public String accessCoeCategActivityUI(@ModelAttribute("coeCategActivityBo") CoeCategActivityBo coeCategActivityBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCoeCategActivityUI <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/coeCategActivityUI");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/searchForCategory" }, name = "查询分类列表")
	public String findPageForPublish(@ModelAttribute("entity")CoeCategActivity coeCategActivity, Integer page, Integer size, Model model){
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
			pageable = new PageRequest(page, size, Direction.DESC, "activity.refreshTime");
			Page<CoeCategActivity> pageResult = coeCategActivityWrapper.findPageForPublish(coeCategActivity, pageable);
			model.addAttribute("page", pageResult);
			Long mainId = coeCategActivity.getCayMainId();
			Long subId = coeCategActivity.getCaySubId();
			Category categoryTree = coeActivityWrapper.findCategoryTree(mainId, subId);
			model.addAttribute("categoryTree", categoryTree);
			
			//当前登录用户最新文章标签
			Page<CoeActivity>  tagPage = coeActivityWrapper.findPageTagForCurrentUser(userId);
			model.addAttribute("tagPage", tagPage);
			
			CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
			model.addAttribute("currentUser", coeUser);
			
			//邀请人最新文章列表
			//Long userId=user.getId();
			Long createdBy = user.getCreatedBy();
			Page<CoeActivity> invitorPage = coeActivityWrapper.findPageForInvitor(createdBy,userId);
			model.addAttribute("invitorPage", invitorPage);
			
			//将分页技能组装成圆形菜单数据
			List<CoeActivity> contentList = invitorPage.getContent();
			CircleMain circleMain = coeActivityConverter.convert(contentList, coeUser);
			model.addAttribute("circleMain", GsonBuilderUtils.toJson(circleMain));
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/searchForCategory");
		LOGGER.debug("--->>>findPageForPublish.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/searchForCategoryFromDetail" }, name = "查询用户文章列表")
	public String findPageForPublishFromDetail(@ModelAttribute("entity")CoeCategActivity categCoeActivity, Integer page, Integer size, Model model){
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
			pageable = new PageRequest(page, size, Direction.DESC, "activity.refreshTime");
			Page<CoeCategActivity> pageResult = coeCategActivityWrapper.findPageForPublishFromDetail(categCoeActivity, pageable);
			model.addAttribute("page", pageResult);
			Long cayMainId = categCoeActivity.getCayMainId();
			Long caySubId = categCoeActivity.getCaySubId();
			
			Category categoryTree = coeActivityWrapper.findCategoryTree(cayMainId, caySubId);
			model.addAttribute("categoryTree", categoryTree);
			
			Long userId=user.getId();
			Page<CoeActivity>  tagPage = coeActivityWrapper.findPageTagForCurrentUser(userId);
			model.addAttribute("tagPage", tagPage);
			
			Long coeActivityId = categCoeActivity.getMainId();
			CoeActivity pe=coeActivityWrapper.findByIdOnly(coeActivityId);
			CoeActivity coeActivity = categCoeActivity.getActivity();
			coeActivity.setUserId(pe.getUserId());
			coeActivity.setTitleLower(pe.getTitle());//借小标题传递显示参数
			coeActivity.setOriginalLink(pe.getOriginalLink());
			categCoeActivity.setActivity(coeActivity);
			
			//detail页面的userId
			Long fixUserId=coeActivity.getUserId();
			Page<CoeActivity> fixedPage = coeActivityWrapper.findPageForCurrentUser(fixUserId);
			model.addAttribute("fixedPage", fixedPage);
			
			
			CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
			model.addAttribute("currentUser", coeUser);
			//将分页技能组装成圆形菜单数据
			List<CoeActivity> contentList = fixedPage.getContent();
			CircleMain circleMain = coeActivityConverter.convert(contentList, coeUser);
			model.addAttribute("circleMain", GsonBuilderUtils.toJson(circleMain));
		}
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/searchForCategoryFromDetail");
		LOGGER.debug("--->>>findPageForPublishFromDetail.resultPath: {} ---", resultPath);
		return resultPath;
	}

}
