package dml.sz0099.course.app.client.controller.activity;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.jit4j.core.persist.page.PageRequest;
import org.jit4j.core.webmvc.config.ResourcePathConfig;
import org.jit4j.core.webmvc.util.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dml.sz0099.course.app.client.resolver.adaptor.media.ParagActivityAdaptor;
import dml.sz0099.course.app.client.validator.activity.ParagActivityValidator;
import dml.sz0099.course.app.client.wrapper.activity.CoeActivityWrapper;
import dml.sz0099.course.app.client.wrapper.activity.ParagActivityWrapper;
import dml.sz0099.course.app.client.wrapper.activity.ParagraphWrapper;
import dml.sz0099.course.app.module.define.CoeActivityRespCode;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.ParagActivity;
import dml.sz0099.course.app.persist.entity.media.ImageExtend;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ParagActivityController 控制转发
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
public class ParagActivityController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParagActivityController.class);

	@Autowired
	private ParagActivityValidator paragActivityValidator;

	@Autowired
	private ParagActivityWrapper paragActivityWrapper;
	
	@Autowired
	private CoeActivityWrapper coeActivityWrapper;
	
	@Autowired
	private ParagraphWrapper paragraphWrapper;
	
	@Autowired
	private ParagActivityAdaptor paragraphAdaptor;

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
	

	public ParagActivityController() {
		LOGGER.debug("##>>> init ParagActivityController <<<##");
	}

	
	@RequestMapping(value = { "manage/paragraph/resetOrderSeq" }, name = "编号重排")
	public String resetOrderSeq(@ModelAttribute("entity") ParagActivity paragActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> resetOrderSeq <<<##");
		Long userId = UserUtils.getUserId();
		Long activityId = paragActivity.getMainId();
		paragActivity.setUserId(userId);
		paragActivity.setCreatedBy(userId);
		boolean checked = paragActivityValidator.validateExistActivity(paragActivity);
		Page<ParagActivity> pageresult = null;
		if(checked) {
			pageresult=paragActivityWrapper.resetOrderSeq(activityId, userId);
			paragActivity.setSuccess(ParagActivity.SUCCESS_YES);
			paragActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_RESET_ORDER_SUCCESS);
			paragActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_RESET_ORDER_SUCCESS);
		}
		model.addAttribute("page", pageresult);
		//获取上传组件参数
		ImageExtend imageExtend = paragraphAdaptor.config(userId);
		model.addAttribute("extend", imageExtend);
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/manage/paragraphEditListUI");
		LOGGER.debug("--->>> resetOrderSeq.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	/**
	 * 个人编辑或管理人员访问
	 * @param paragActivityBo
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "manage/paragraph/editListUI" }, name = "访问段落编辑页")
	public String editParagActivityList(@ModelAttribute("entity") ParagActivity paragActivity, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> editParagActivityList <<<##");

		Long userId = UserUtils.getUserId();
		Long activityId = paragActivity.getMainId();
		paragActivity.setUserId(userId);
		paragActivity.setCreatedBy(userId);
		boolean checked = paragActivityValidator.validateExistActivity(paragActivity);
		if(checked) {
			if(page==null) {
				page=0;
			}
			if(size==null) {
				size=100;
			}
			PageRequest pageable = new PageRequest(page,size,Direction.ASC, "orderSeq");
			Page<ParagActivity> pageresult = paragActivityWrapper.findByMainIdAndUserId(activityId, userId, pageable);
			long total = pageresult.getTotalElements();
			if(total==0) {
				//创建一个段落，执行保存
				paragActivity=paragActivityWrapper.createParagActivity(paragActivity);
			}else {
				model.addAttribute("page", pageresult);
			}
			paragActivity.setSuccess(ParagActivity.SUCCESS_YES);
		}
		CoeActivity activity = coeActivityWrapper.findByIdOnly(activityId);
		paragActivity.setActivity(activity);
		model.addAttribute("entity", paragActivity);
		
		//获取上传组件参数
		ImageExtend imageExtend = paragraphAdaptor.config(userId);
		model.addAttribute("extend", imageExtend);
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/manage/paragraphEditListUI");
		LOGGER.debug("--->>> editParagActivityList.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/paragraph/add" }, name = "添加段落")
	public String addParagActivity(@ModelAttribute("paragActivity") ParagActivity paragActivity, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> addParagActivity <<<##");

		Long userId = UserUtils.getUserId();
		paragActivity.setUserId(userId);
		paragActivity.setCreatedBy(userId);
		Long activityId = paragActivity.getMainId();
		boolean checked = paragActivityValidator.validateAddProduct(paragActivity);
		if(checked) {
			paragActivity = paragActivityWrapper.createParagActivity(paragActivity);
			paragActivity.setSuccess(ParagActivity.SUCCESS_YES);
			paragActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_COURSE_PARAGRAGH_ADD_SUCCESS);
			paragActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_COURSE_PARAGRAGH_ADD_SUCCESS);
		
		}
		model.addAttribute("entity", paragActivity);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/manage/paragraphAddUI");
		LOGGER.debug("--->>> addParagActivity.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/paragraph/mergeSingle" }, name = "保存段落")
	public String mergeParagActivity(@ModelAttribute("paragActivity") ParagActivity paragActivity, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeParagActivity <<<##");

		Long userId = UserUtils.getUserId();
		paragActivity.setUserId(userId);
		paragActivity.setLastModifiedBy(userId);
		paragActivity.getId();
		Long activityId = paragActivity.getMainId();
		boolean checked = paragActivityValidator.validateMergeProduct(paragActivity);
		if(checked) {
			paragActivity = paragActivityWrapper.mergeEntity(paragActivity);
			paragActivity.setSuccess(ParagActivity.SUCCESS_YES);
			paragActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_COURSE_PARAGRAGH_MERGE_SUCCESS);
			paragActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_COURSE_PARAGRAGH_MERGE_SUCCESS);
		}
		model.addAttribute("entity", paragActivity);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/manage/commonResult");
		LOGGER.debug("--->>> mergeParagActivity.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/paragraph/deleteSingle" }, name = "删除段落")
	public String deleteParagActivity(@ModelAttribute("paragActivity") ParagActivity paragActivity, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> deleteParagActivity <<<##");

		Long userId = UserUtils.getUserId();
		paragActivity.setUserId(userId);
		paragActivity.setLastModifiedBy(userId);
		paragActivity.getId();
		Long activityId = paragActivity.getMainId();
		boolean checked = paragActivityValidator.validateForDelete(paragActivity);
		if(checked) {
			paragraphAdaptor.deleteFromRemote(paragActivity);
			paragActivityWrapper.deleteByParagIdAndUserId(paragActivity.getParagId(), userId, true);
			paragActivity.setSuccess(ParagActivity.SUCCESS_YES);
			paragActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_COURSE_PARAGRAGH_DELETE_SUCCESS);
			paragActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_COURSE_PARAGRAGH_DELETE_SUCCESS);
		}
		model.addAttribute("entity", paragActivity);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/manage/commonResult");
		LOGGER.debug("--->>> deleteParagActivity.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/paragraph/deleteAll" }, name = "删除全部段落")
	public String deleteParagActivityAll(@ModelAttribute("paragActivity") ParagActivity paragActivity, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> deleteParagActivityAll <<<##");

		Long userId = UserUtils.getUserId();
		paragActivity.setUserId(userId);
		paragActivity.setLastModifiedBy(userId);
		paragActivity.getId();
		Long activityId = paragActivity.getMainId();
		boolean checked = paragActivityValidator.validateForDeleteAll(paragActivity);
		if(checked) {
			paragraphAdaptor.deleteFromRemote(paragActivity);
			paragActivityWrapper.deleteByActivityIdAndUserId(activityId, userId);
			paragActivity.setSuccess(ParagActivity.SUCCESS_YES);
			paragActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_COURSE_PARAGRAGH_DELETE_ALL_SUCCESS);
			paragActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_COURSE_PARAGRAGH_DELETE_ALL_SUCCESS);
		}
		model.addAttribute("entity", paragActivity);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ARTICLE,"/manage/commonResult");
		LOGGER.debug("--->>> deleteParagActivityAll.resultPath: {} ---", resultPath);
		return resultPath;
	}

	
	
	
	

}
