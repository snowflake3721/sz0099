package dml.sz0099.course.app.client.controller.profession;

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

import dml.sz0099.course.app.client.validator.profession.CategProfessionValidator;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.client.wrapper.profession.CategProfessionWrapper;
import dml.sz0099.course.app.client.wrapper.profession.ProfessionWrapper;
import dml.sz0099.course.app.data.show.blooming.CircleMain;
import dml.sz0099.course.app.data.show.blooming.converter.ProfessionConverter;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.profession.CategProfession;
import dml.sz0099.course.app.persist.entity.profession.Profession;
import dml.sz0099.course.app.persist.entity.profession.bo.CategProfessionBo;
import dml.sz0099.course.app.persist.entity.user.CoeUser;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CategProfessionController 控制转发
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
@Controller
public class CategProfessionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategProfessionController.class);

	@Autowired
	private CategProfessionValidator categProfessionValidator;

	@Autowired
	private CategProfessionWrapper categProfessionWrapper;
	
	@Autowired
	private ProfessionWrapper professionWrapper;
	
	@Autowired
	private ProfessionConverter professionConverter;
	
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

	@RequestMapping(value = { "/categProfessionUI" }, name = "访问categProfessionUI页面")
	public String accessCategProfessionUI(@ModelAttribute("categProfessionBo") CategProfessionBo categProfessionBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCategProfessionUI <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/categProfessionUI");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "profession/searchForCategory" }, name = "查询分类列表")
	public String findPageForPublish(@ModelAttribute("entity")CategProfession categProfession, Integer page, Integer size, Model model){
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
			pageable = new PageRequest(page, size, Direction.DESC, "profession.refreshTime");
			Page<CategProfession> pageResult = categProfessionWrapper.findPageForPublish(categProfession, pageable);
			model.addAttribute("page", pageResult);
			Long mainId = categProfession.getCayMainId();
			Long subId = categProfession.getCaySubId();
			Category categoryTree = professionWrapper.findCategoryTree(mainId, subId);
			model.addAttribute("categoryTree", categoryTree);
			
			Long userId=user.getId();
			Long createdBy = user.getCreatedBy();
			Page<Profession> invitorPage = professionWrapper.findPageForInvitor(createdBy,userId);
			model.addAttribute("invitorPage", invitorPage);
			
			Page<Profession>  tagPage = professionWrapper.findPageTagForCurrentUser(userId);
			model.addAttribute("tagPage", tagPage);
			
			CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
			model.addAttribute("currentUser", coeUser);
			//将分页技能组装成圆形菜单数据
			List<Profession> contentList = invitorPage.getContent();
			CircleMain circleMain = professionConverter.convert(contentList,coeUser);
			model.addAttribute("circleMain", GsonBuilderUtils.toJson(circleMain));
		}
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/searchForCategory");
		LOGGER.debug("--->>>findPageForPublish.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "profession/searchForCategoryFromDetail" }, name = "查询用户技能列表")
	public String findPageForPublishFromDetail(@ModelAttribute("entity")CategProfession categProfession, Integer page, Integer size, Model model){
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
			pageable = new PageRequest(page, size, Direction.DESC, "profession.refreshTime");
			Page<CategProfession> pageResult = categProfessionWrapper.findPageForPublishFromDetail(categProfession, pageable);
			model.addAttribute("page", pageResult);
			Long cayMainId = categProfession.getCayMainId();
			Long caySubId = categProfession.getCaySubId();
			
			Category categoryTree = professionWrapper.findCategoryTree(cayMainId, caySubId);
			model.addAttribute("categoryTree", categoryTree);
			
			Long userId=user.getId();
			Page<Profession>  tagPage = professionWrapper.findPageTagForCurrentUser(userId);
			model.addAttribute("tagPage", tagPage);
			
			Long professionId = categProfession.getMainId();
			Profession pe=professionWrapper.findByIdOnly(professionId);
			Profession profession = categProfession.getProfession();
			profession.setUserId(pe.getUserId());
			profession.setTitleLower(pe.getTitle());//借小标题传递显示参数
			profession.setOriginalLink(pe.getOriginalLink());
			categProfession.setProfession(profession);
			
			//detail页面的userId
			Long fixUserId=profession.getUserId();
			Page<Profession> fixedPage = professionWrapper.findPageForCurrentUser(fixUserId);
			model.addAttribute("fixedPage", fixedPage);
			
			
			CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
			model.addAttribute("currentUser", coeUser);
			//将分页技能组装成圆形菜单数据
			List<Profession> contentList = fixedPage.getContent();
			CircleMain circleMain = professionConverter.convert(contentList, coeUser);
			model.addAttribute("circleMain", GsonBuilderUtils.toJson(circleMain));
		}
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/searchForCategoryFromDetail");
		LOGGER.debug("--->>>findPageForPublishFromDetail.resultPath: {} ---", resultPath);
		return resultPath;
	}

}
