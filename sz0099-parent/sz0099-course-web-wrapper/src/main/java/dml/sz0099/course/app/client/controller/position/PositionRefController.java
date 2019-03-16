package dml.sz0099.course.app.client.controller.position;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.jit4j.core.persist.page.PageRequest;
import org.jit4j.core.pub.transfer.dto.JsonResult;
import org.jit4j.core.webmvc.config.ResourcePathConfig;
import org.jit4j.core.webmvc.util.UserUtils;
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
import org.springframework.web.bind.annotation.ResponseBody;

import dml.sz0099.course.app.client.resolver.position.PositionProccessor;
import dml.sz0099.course.app.client.resolver.position.PositionResponse;
import dml.sz0099.course.app.client.validator.position.PositionRefValidator;
import dml.sz0099.course.app.client.wrapper.position.PositionRefWrapper;
import dml.sz0099.course.app.module.define.CourseRespCode;
import dml.sz0099.course.app.module.define.SZ0099AppModule;
import dml.sz0099.course.app.persist.entity.position.PositionRef;
import dml.sz0099.course.app.persist.entity.position.bo.PositionRefBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * PositionRefController 控制转发
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
public class PositionRefController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PositionRefController.class);

	@Autowired
	private PositionRefValidator positionRefValidator;

	@Autowired
	private PositionRefWrapper positionRefWrapper;
	
	@Autowired
	private PositionProccessor positionProccessor;

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
	
	
	public PositionRefController() {
		LOGGER.debug("##>>> init PositionRefController <<<##");
	}

	@RequestMapping(value = { "ref/manage/findRefList" }, name = "查询列表")
	public String findRefList(@ModelAttribute("entity") PositionRef positionRef, int page, int size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> findRefList <<<##");
		Long baseId=positionRef.getBaseId();
		positionRef.setUserId(UserUtils.getUserId());
		positionRef.setOwnerId(UserUtils.getUserId());
		if(page<0) {
			page=0;
		}
		if(size>100) {
			size=100;
		}
		boolean checked = positionRefValidator.validateForFindList(positionRef);
		if(checked) {
			Pageable pageable = new PageRequest(page,size,Direction.DESC,"topLevel");
			Page<PositionRef> pageResult = positionRefWrapper.findPageByBaseId(baseId, pageable, true);
			
			positionRef.setSuccess(PositionRef.SUCCESS_YES);
			
			if(null == pageResult || pageResult.getTotalElements()<1) {
				positionRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_REF_FINDLIST_EMPTY);
				positionRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_REF_FINDLIST_EMPTY);
			}else {
				positionRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_REF_FINDLIST_SUCCESS);
				positionRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_REF_FINDLIST_SUCCESS);
			}
			model.addAttribute("pageResult", pageResult);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_POSITION,"/manage/positionRefPage");
		LOGGER.debug("--->>> findRefList.resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "/positionRef/sub" }, name = "访问positionRefsub页面")
	public String accessPositionRefSub(@ModelAttribute("positionRefBo") PositionRefBo positionRefBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessPositionRef <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_POSITION,"/positionRef");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/selectNeedBindRefList" }, name = "查询需要绑定的列表")
	public String selectNeedBindRefList(@ModelAttribute("position") PositionRef position, int page, int size,Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> selectNeedBindRefList <<<##");
		Long userId = UserUtils.getUserId();
		position.setUserId(userId);
		position.setOwnerId(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_POSITION,"/manage/selectNeedBindRefList");
		if(null != userId) {
			Long id = position.getId();
			Pageable pageable = new PageRequest(page, size);
			PositionResponse response = positionProccessor.queryRefPage(position, pageable);
			model.addAttribute("response", response);
		}
		model.addAttribute("entity", position);
		LOGGER.debug("--->>> selectNeedBindRefList.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/loadNeedBindRefList" }, name = "加载更多需要绑定的列表")
	public String loadNeedBindRefList(@ModelAttribute("position") PositionRef position, int page, int size,Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> loadNeedBindRefList <<<##");
		Long userId = UserUtils.getUserId();
		position.setUserId(userId);
		position.setOwnerId(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_POSITION,"/manage/loadNeedBindRefList");
		if(null != userId) {
			Long id = position.getId();
			Pageable pageable = new PageRequest(page, size);
			PositionResponse response = positionProccessor.queryRefPage(position, pageable);
			model.addAttribute("response", response);
		}
		model.addAttribute("entity", position);
		LOGGER.debug("--->>> loadNeedBindRefList.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@ResponseBody
	@RequestMapping(value = { "ref/manage/add" }, name = "添加关联")
	public JsonResult addPositionRef(@ModelAttribute("position") PositionRef positionRef, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> addPositionRef <<<##");
		Long userId = UserUtils.getUserId();
		positionRef.setUserId(userId);
		positionRef.setCreatedBy(userId);
		positionRef.setOwnerId(userId);
		positionRef.setLastModifiedBy(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_POSITION,"/manage/commonResult");
		boolean checked = positionRefValidator.validateForAdd(positionRef);
		if(checked) {
			Long id = positionRef.getId();
			positionRef = positionProccessor.addPositionRef(positionRef);
		}
		model.addAttribute("entity", positionRef);
		JsonResult result = new JsonResult();
		result.setContent(positionRef);
		result.setSuccess(PositionRef.SUCCESS_YES);
		LOGGER.debug("--->>> addPositionRef.resultPath: {} ---", resultPath);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = { "ref/manage/delete" }, name = "移除关联")
	public JsonResult deletePositionRef(@ModelAttribute("position") PositionRef positionRef, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> deletePositionRef <<<##");
		Long userId = UserUtils.getUserId();
		positionRef.setUserId(userId);
		positionRef.setOwnerId(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_POSITION,"/manage/commonResult");
		boolean checked = positionRefValidator.validateForDelete(positionRef);
		if(checked) {
			Long id = positionRef.getId();
			positionRef = positionProccessor.deletePositionRef(positionRef);
		}
		model.addAttribute("entity", positionRef);
		JsonResult result = new JsonResult();
		result.setContent(positionRef);
		result.setSuccess(PositionRef.SUCCESS_YES);
		LOGGER.debug("--->>> deletePositionRef.resultPath: {} ---", resultPath);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = { "ref/manage/open" }, name = "开闭关联")
	public JsonResult openPositionRef(@ModelAttribute("position") PositionRef positionRef, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> openPositionRef <<<##");
		Long userId = UserUtils.getUserId();
		positionRef.setUserId(userId);
		positionRef.setLastModifiedBy(userId);
		positionRef.setOwnerId(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_POSITION,"/manage/commonResult");
		boolean checked = positionRefValidator.validateForOpen(positionRef);
		if(checked) {
			Long id = positionRef.getId();
			positionRef = positionProccessor.openPositionRef(positionRef);
		}
		model.addAttribute("entity", positionRef);
		JsonResult result = new JsonResult();
		result.setContent(positionRef);
		result.setSuccess(PositionRef.SUCCESS_YES);
		LOGGER.debug("--->>> openPositionRef.resultPath: {} ---", resultPath);
		return result;
	}
	

	@RequestMapping(value = { "ref/manage/findByIdForEdit" }, name = "查询关联")
	public String findByIdForEdit(@ModelAttribute("position") PositionRef positionRef, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> findByIdForEdit <<<##");
		Long userId = UserUtils.getUserId();
		positionRef.setUserId(userId);
		positionRef.setOwnerId(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_POSITION,"/manage/positionRefEditUI");
		if(null != userId) {
			Long id = positionRef.getId();
			positionRef = positionRefWrapper.findById(id,true);
			positionRef.setSuccess(PositionRef.SUCCESS_YES);
		}
		model.addAttribute("entity", positionRef);
		LOGGER.debug("--->>> findByIdForEdit.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "ref/manage/mergePositionRef" }, name = "修改关联")
	public String mergePositionRef(@ModelAttribute("position") PositionRef positionRef, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergePositionRef <<<##");
		Long userId = UserUtils.getUserId();
		positionRef.setUserId(userId);
		positionRef.setOwnerId(userId);
		positionRef.setLastModifiedBy(userId);
		positionRef.setCreatedBy(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_POSITION,"/manage/commonResult");
		
		boolean checked = positionRefValidator.validateForMerge(positionRef);
		if(checked) {
			positionRef = positionProccessor.mergePositionRef(positionRef);
			//positionRef.setSuccess(PositionRef.SUCCESS_YES);
		}
		model.addAttribute("entity", positionRef);
		LOGGER.debug("--->>> mergePositionRef.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@RequestMapping(value = { "ref/manage/saveSimpleSingle" }, name = "简易修改关联")
	public String saveSimpleSingle(@ModelAttribute("position") PositionRef positionRef, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergePositionRef <<<##");
		Long userId = UserUtils.getUserId();
		positionRef.setUserId(userId);
		positionRef.setLastModifiedBy(userId);
		positionRef.setOwnerId(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_POSITION,"/manage/commonResult");
		
		boolean checked = positionRefValidator.validateForSimpleSingle(positionRef);
		if(checked) {
			positionRef = positionProccessor.mergeSimpleSingle(positionRef);
		}
		model.addAttribute("entity", positionRef);
		LOGGER.debug("--->>> mergePositionRef.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "ref/manage/sync" }, name = "同步关联")
	public String sync(@ModelAttribute("position") PositionRef positionRef, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> sync <<<##");
		Long userId = UserUtils.getUserId();
		positionRef.setUserId(userId);
		positionRef.setLastModifiedBy(userId);
		positionRef.setOwnerId(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_POSITION,"/manage/commonResult");
		
		boolean checked = positionRefValidator.validateForSync(positionRef);
		if(checked) {
			positionRef = positionProccessor.addPositionRef(positionRef);
			positionRef.setSuccess(PositionRef.SUCCESS_YES);
			positionRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_REF_SYNC_SUCCESS);
			positionRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_REF_SYNC_SUCCESS);
		}
		model.addAttribute("entity", positionRef);
		LOGGER.debug("--->>> sync.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "ref/manage/deleteRefByBaseId" }, name = "删除该位置下所有关联文章")
	public String deleteRefByBaseId(@ModelAttribute("position") PositionRef positionRef, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergePositionRef <<<##");
		Long userId = UserUtils.getUserId();
		positionRef.setUserId(userId);
		positionRef.setOwnerId(userId);
		positionRef.setLastModifiedBy(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_POSITION,"/manage/commonResult");
		
		boolean checked = positionRefValidator.validateForDeleteRefByBaseId(positionRef);
		if(checked) {
			positionRef = positionProccessor.deleteRefByBaseId(positionRef);
		}
		model.addAttribute("entity", positionRef);
		LOGGER.debug("--->>> mergePositionRef.resultPath: {} ---", resultPath);
		return resultPath;
	}
}
