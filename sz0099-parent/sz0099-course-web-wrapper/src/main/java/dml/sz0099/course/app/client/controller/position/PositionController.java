package dml.sz0099.course.app.client.controller.position;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.jit4j.core.persist.page.PageRequest;
import org.jit4j.core.webmvc.config.ResourcePathConfig;
import org.jit4j.core.webmvc.util.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dml.sz0099.course.app.client.resolver.position.PositionProccessor;
import dml.sz0099.course.app.client.resolver.position.PositionRequest;
import dml.sz0099.course.app.client.resolver.position.PositionResponse;
import dml.sz0099.course.app.client.validator.position.PositionValidator;
import dml.sz0099.course.app.client.wrapper.position.PositionWrapper;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.paragraph.ParagProduct;
import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.entity.position.bo.PositionBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * PositionController 控制转发
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
public class PositionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PositionController.class);

	@Autowired
	private PositionValidator positionValidator;

	@Autowired
	private PositionWrapper positionWrapper;

	@Autowired
	private PositionProccessor positionProccessor;

	//模板根目录
		private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
		//模块缩略名称目录
		private String MODULE_PATH_POSITION = ResourcePathConfig.getModulesPath(CourseAppModule.APP_SZ0099_POSITION.getAd());
		//模块主目录,一般与模块缩略名称相同
		private String MAPPING_PATH_POSITION = ResourcePathConfig.getModulesPath(CourseAppModule.APP_SZ0099_POSITION_PATH.currentPath());
		//模块二级目录
		private String MAPPING_SUB_PATH_POSITION_BASIC = ResourcePathConfig.getModulesPath(CourseAppModule.APP_SZ0099_POSITION_PATH_BASIC.currentPath());
		
		@PostConstruct
		public void init() {
			LOGGER.debug("========== TLE_DEFALT_FRONT: {} ========== ", TLE_DEFALT_FRONT);
			LOGGER.debug("========== MODULE_PATH_POSITION: {} ========== ", MODULE_PATH_POSITION);
			LOGGER.debug("========== MAPPING_PATH_POSITION: {} ========== ", MAPPING_PATH_POSITION);
			LOGGER.debug("========== MAPPING_SUB_PATH_POSITION_BASIC: {} ========== ", MAPPING_SUB_PATH_POSITION_BASIC);
		}
		
	
	
	public PositionController() {
		LOGGER.debug("##>>> init CoePositionController <<<##");
	}

	@RequestMapping(value = { "/manage/create" }, name = "创建位置")
	public String create(@ModelAttribute("position") Position position, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> create <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_POSITION,"/positionUI");
		LOGGER.debug("--->>> create.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/editListUI" }, name = "编辑位置")
	public String editListUI(@ModelAttribute("position") Position position, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> editListUI <<<##");
		Long userId = UserUtils.getUserId();
		position.setUserId(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_POSITION,"/manage/positionEditListUI");
		List<Position>  positionList = null;
		if(null != userId) {
			positionList = positionWrapper.findMainIdAndExtendId(position);
		}
		model.addAttribute("positionList", positionList);
		model.addAttribute("entity", position);
		LOGGER.debug("--->>> editListUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	/**
	 * 查找绑定位置及已绑定的数据
	 * @param position
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/manage/bindListUI" }, name = "绑定位置")
	public String bindListUI(@ModelAttribute("position") Position position, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> bindListUI <<<##");
		Long userId = UserUtils.getUserId();
		position.setUserId(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_POSITION,"/manage/positionBindListUI");
		List<Position>  positionList = null;
		if(null != userId) {
			positionList = positionWrapper.findMainIdAndExtendId(position);
		}
		model.addAttribute("positionList", positionList);
		model.addAttribute("entity", position);
		LOGGER.debug("--->>> bindListUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/findBindRefListById" }, name = "查询已绑定列表")
	public String findBindRefListById(@ModelAttribute("position") Position position, int page, int size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> findBindRefListById <<<##");
		Long userId = UserUtils.getUserId();
		position.setUserId(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_POSITION,"/manage/positionBindListUI");
		Position  entity = null;
		if(null != userId) {
			Long id = position.getId();
			Pageable pageable = new PageRequest(page, size);
			entity = positionWrapper.findById(id, true, true, pageable);
			position = entity;
		}
		model.addAttribute("entity", position);
		LOGGER.debug("--->>> findBindRefListById.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@RequestMapping(value = { "/manage/subEditListUI" }, name = "编辑子类")
	public String subEditListUI(@ModelAttribute("position") Position position, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> subEditListUI <<<##");
		Long userId = UserUtils.getUserId();
		position.setUserId(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_POSITION,"/manage/subPositionEditListUI");
		List<Position>  positionList = null;
		if(null != userId) {
			Position entity = positionWrapper.findById(position.getId());
			position = entity;
		}
		model.addAttribute("entity", position);
		LOGGER.debug("--->>> subEditListUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	

	@RequestMapping(value = { "/sub" }, name = "访问positionsub页面")
	public String accessCoePositionSub(@ModelAttribute("positionBo") PositionBo positionBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCoePosition <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_POSITION_BASIC,"/position");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	
	@RequestMapping(value = { "manage/add" }, name = "添加位置")
	public String addPosition(@ModelAttribute("position") Position position, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> addPosition <<<##");

		Long userId = UserUtils.getUserId();
		position.setUserId(userId);
		position.setCreatedBy(userId);
		position.setLastModifiedBy(userId);
		//位置允许重复添加
		boolean checked = positionValidator.validateAddPosition(position);
		if(checked) {
			position = positionWrapper.createPosition(position);
			position.setSuccess(Position.SUCCESS_YES);
			position.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_POSITION_ADD_SUCCESS);
			position.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_POSITION_ADD_SUCCESS);
		}
		model.addAttribute("entity", position);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_POSITION,"/manage/positionAddUI");
		LOGGER.debug("--->>> addPosition.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/mergeSingle" }, name = "保存位置")
	public String mergePosition(@ModelAttribute("position") Position position, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergePosition <<<##");

		Long userId = UserUtils.getUserId();
		position.setUserId(userId);
		position.setLastModifiedBy(userId);
		boolean checked = positionValidator.validateMergePosition(position);
		if(checked) {
			position = positionWrapper.mergeEntity(position);
			position.setSuccess(Position.SUCCESS_YES);
			position.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_POSITION_MERGE_SUCCESS);
			position.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_POSITION_MERGE_SUCCESS);
		}
		model.addAttribute("entity", position);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_POSITION,"/manage/commonResult");
		LOGGER.debug("--->>> mergePosition.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/deleteSingle" }, name = "删除位置")
	public String deletePosition(@ModelAttribute("position") Position position, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> deletePosition <<<##");

		Long userId = UserUtils.getUserId();
		position.setUserId(userId);
		position.setLastModifiedBy(userId);
		boolean checked = positionValidator.validateForDelete(position);
		if(checked) {
			positionWrapper.deletePosition(position);
			position.setSuccess(Position.SUCCESS_YES);
			position.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_POSITION_DELETE_SUCCESS);
			position.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_POSITION_DELETE_SUCCESS);
		}
		model.addAttribute("entity", position);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_POSITION,"/manage/commonResult");
		LOGGER.debug("--->>> deletePosition.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "manage/deleteAll" }, name = "删除全部位置")
	public String deletePositionAll(@ModelAttribute("position") Position position, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> deletePositionAll <<<##");

		Long userId = UserUtils.getUserId();
		position.setUserId(userId);
		position.setLastModifiedBy(userId);
		boolean checked = positionValidator.validateForDeleteAll(position);
		if(checked) {
			positionWrapper.deleteAllByExtend(position);
			position.setSuccess(ParagProduct.SUCCESS_YES);
			position.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_POSITION_DELETE_ALL_SUCCESS);
			position.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_POSITION_DELETE_ALL_SUCCESS);
		}
		model.addAttribute("entity", position);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_POSITION,"/manage/commonResult");
		LOGGER.debug("--->>> deletePositionAll.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	

}
