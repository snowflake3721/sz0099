package dml.sz0099.course.app.client.controller.position;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.jit4j.core.webmvc.config.ResourcePathConfig;
import org.jit4j.core.webmvc.util.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dml.sz0099.course.app.client.validator.position.PositionExtendValidator;
import dml.sz0099.course.app.client.wrapper.position.PositionExtendWrapper;
import dml.sz0099.course.app.module.define.CourseRespCode;
import dml.sz0099.course.app.module.define.SZ0099AppModule;
import dml.sz0099.course.app.persist.entity.position.PositionExtend;
import dml.sz0099.course.app.persist.entity.position.PositionExtend;
import dml.sz0099.course.app.persist.entity.position.bo.PositionExtendBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * PositionExtendController 控制转发
 * @author bruce yang at 2018-08-29 22:41:14
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@RequestMapping("/sz0099/ood/position")
@Controller
public class PositionExtendController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PositionExtendController.class);

	@Autowired
	private PositionExtendValidator positionExtendValidator;

	@Autowired
	private PositionExtendWrapper positionExtendWrapper;

	//模板根目录
	private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
	//模块缩略名称目录
	private String MODULE_PATH_POSITION = ResourcePathConfig.getModulesPath(SZ0099AppModule.APP_SZ0099_POSITION.getAd());
	//模块主目录,一般与模块缩略名称相同
	private String MAPPING_PATH_POSITION = ResourcePathConfig.getModulesPath(SZ0099AppModule.APP_SZ0099_POSITION_PATH.currentPath());
	//模块二级目录
	private String MAPPING_SUB_PATH_POSITION = ResourcePathConfig.getModulesPath(SZ0099AppModule.APP_SZ0099_POSITION_PATH_BASIC.currentPath());
	
	@PostConstruct
	public void init() {
		LOGGER.debug("========== TLE_DEFALT_FRONT: {} ========== ", TLE_DEFALT_FRONT);
		LOGGER.debug("========== MODULE_PATH_POSITION: {} ========== ", MODULE_PATH_POSITION);
		LOGGER.debug("========== MAPPING_PATH_POSITION: {} ========== ", MAPPING_PATH_POSITION);
		LOGGER.debug("========== MAPPING_SUB_PATH_POSITION: {} ========== ", MAPPING_SUB_PATH_POSITION);
	}
	

	public PositionExtendController() {
		LOGGER.debug("##>>> init PositionExtendController <<<##");
	}

	@RequestMapping(value = { "/positionExtendUI" }, name = "访问positionExtendUI页面")
	public String accessPositionExtendUI(@ModelAttribute("positionExtendBo") PositionExtendBo positionExtendBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessPositionExtendUI <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_POSITION,"/positionExtendUI");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "/positionExtend/sub" }, name = "访问positionExtendsub页面")
	public String accessPositionExtendSub(@ModelAttribute("positionExtendBo") PositionExtendBo positionExtendBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessPositionExtend <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_POSITION,"/positionExtend");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/extend/manage/list" }, name = "位置模块列表")
	public String queryPositionList(@ModelAttribute("entity")PositionExtend extend, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> queryPositionList <<<##");
		extend.setUserId(UserUtils.getUserId());
		Long userId = UserUtils.getUserId();
		List<PositionExtend>  contentList = null;
		if(null != userId) {
			contentList = positionExtendWrapper.findAll();
			extend.setSuccess(PositionExtend.SUCCESS_YES);
		}else {
			extend.setSuccess(PositionExtend.SUCCESS_NO);
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_EXTEND_USER_NOT_LOGIN);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_EXTEND_USER_NOT_LOGIN);
		}
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_POSITION,"/manage/positionList");
		model.addAttribute("contentList", contentList);
		LOGGER.debug("--->>> queryPositionList.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/extend/manage/create" }, name = "新建或编辑项目")
	public String extendCreate(@ModelAttribute("entity") PositionExtend extend, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> extendCreate <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_POSITION,"/manage/extendAddUI");
		Long userId = UserUtils.getUserId();
		extend.setUserId(userId);
		
		if(null != userId) {
			Long id = extend.getId();
			if(null != id) {
				extend=positionExtendWrapper.findById(id);
			}
			boolean checked = positionExtendValidator.validateExtendForPersist(extend);
			
			if(checked) {
				//创建一个
				if(id == null) {
					extend.setCreatedBy(userId);
					extend.setLastModifiedBy(userId);
					extend=positionExtendWrapper.create(extend);
					extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_EXTEND_PERSIST_SUCCESS);
					extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_EXTEND_PERSIST_SUCCESS);
				}
				extend.setSuccess(PositionExtend.SUCCESS_YES);
			}
		}else {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_EXTEND_USER_NOT_LOGIN);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_EXTEND_USER_NOT_LOGIN);
			extend.setSuccess(PositionExtend.SUCCESS_NO);
		}
		
		model.addAttribute("entity", extend);
		
		LOGGER.debug("--->>> extendCreate.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@RequestMapping(value = { "/extend/manage/merge" }, name = "保存项目")
	public String saveExtend(@ModelAttribute("position") PositionExtend extend, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> saveExtend <<<##");
		Long userId = UserUtils.getUserId();
		extend.setUserId(userId);
		boolean checked = positionExtendValidator.validateExtend(extend);
		
		if(checked) {
			extend.setLastModifiedBy(userId);
			extend = positionExtendWrapper.mergeEntity(extend);
			extend.setSuccess(PositionExtend.SUCCESS_YES);
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_EXTEND_SAVE_SUCCESS);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_EXTEND_SAVE_SUCCESS);
		}
		model.addAttribute("entity", extend);
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_POSITION,"/manage/commonResult");
		LOGGER.debug("--->>> saveExtend.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/extend/manage/delete" }, name = "删除项目")
	public String deleteExtend(@ModelAttribute("entity") PositionExtend extend, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> deleteExtend <<<##");
		Long userId = UserUtils.getUserId();
		extend.setUserId(userId);
		boolean checked = positionExtendValidator.validateExtendForDelete(extend);
		
		if(checked) {
			extend.setLastModifiedBy(userId);
			extend = positionExtendWrapper.deleteEntity(extend);
			extend.setSuccess(PositionExtend.SUCCESS_YES);
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_EXTEND_DELETE_SUCCESS);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_EXTEND_DELETE_SUCCESS);
		}
		model.addAttribute("entity", extend);
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_POSITION,"/manage/commonResult");
		LOGGER.debug("--->>> deleteExtend.resultPath: {} ---", resultPath);
		return resultPath;
	}

}
