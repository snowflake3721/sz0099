package dml.sz0099.course.app.client.controller.profession;

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

import dml.sz0099.course.app.client.resolver.profession.ProfessionProccessor;
import dml.sz0099.course.app.client.resolver.profession.ProfessionResponse;
import dml.sz0099.course.app.client.validator.profession.ProfessionRefValidator;
import dml.sz0099.course.app.client.wrapper.profession.ProfessionRefWrapper;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.profession.ProfessionRef;
import dml.sz0099.course.app.persist.entity.profession.bo.ProfessionRefBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ProfessionRefController 控制转发
 * @author bruce yang at 2018-08-29 22:41:14
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@RequestMapping("/sz0099/ood/personal/profession")
@Controller
public class ProfessionRefController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionRefController.class);

	@Autowired
	private ProfessionRefValidator professionRefValidator;

	@Autowired
	private ProfessionRefWrapper professionRefWrapper;
	
	@Autowired
	private ProfessionProccessor professionProccessor;

		//模板根目录
		private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
		//模块缩略名称目录
		private String MODULE_PATH_PERSONAL = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_PERSONAL.getAd());
		//模块主目录,一般与模块缩略名称相同
		private String MAPPING_PATH_PERSONAL = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_PERSONAL_PATH.currentPath());
		//模块二级目录
		private String MAPPING_SUB_PATH_PERSONAL = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_PERSONAL_PATH_BASIC.currentPath());
		
		@PostConstruct
		public void init() {
			LOGGER.debug("========== TLE_DEFALT_FRONT: {} ========== ", TLE_DEFALT_FRONT);
			LOGGER.debug("========== MODULE_PATH_POSITION: {} ========== ", MODULE_PATH_PERSONAL);
			LOGGER.debug("========== MAPPING_PATH_POSITION: {} ========== ", MAPPING_PATH_PERSONAL);
			LOGGER.debug("========== MAPPING_SUB_PATH_POSITION: {} ========== ", MAPPING_SUB_PATH_PERSONAL);
		}
	
	
	public ProfessionRefController() {
		LOGGER.debug("##>>> init ProfessionRefController <<<##");
	}

	@RequestMapping(value = { "ref/manage/findRefLsit" }, name = "查询列表")
	public String findRefLsit(@ModelAttribute("entity") ProfessionRef professionRef, int page, int size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> findRefLsit <<<##");
		Long baseId=professionRef.getBaseId();
		professionRef.setUserId(UserUtils.getUserId());
		if(page<0) {
			page=0;
		}
		if(size>100) {
			size=100;
		}
		boolean checked = professionRefValidator.validateForFindList(professionRef);
		if(checked) {
			Pageable pageable = new PageRequest(page,size,Direction.DESC,"topLevel");
			Page<ProfessionRef> pageResult = professionRefWrapper.findPageByBaseId(baseId, pageable, true, true);
			
			professionRef.setSuccess(ProfessionRef.SUCCESS_YES);
			
			if(null == pageResult || pageResult.getTotalElements()<1) {
				professionRef.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_REF_FINDLIST_EMPTY);
				professionRef.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_REF_FINDLIST_EMPTY);
			}else {
				professionRef.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_REF_FINDLIST_SUCCESS);
				professionRef.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_REF_FINDLIST_SUCCESS);
			}
			model.addAttribute("pageResult", pageResult);
		}
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/manage/professionRefPage");
		LOGGER.debug("--->>> findRefLsit.resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "/professionRef/sub" }, name = "访问professionRefsub页面")
	public String accessProfessionRefSub(@ModelAttribute("professionRefBo") ProfessionRefBo professionRefBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessProfessionRef <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_PERSONAL,"/professionRef");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/selectNeedBindRefList" }, name = "查询需要绑定的列表")
	public String selectNeedBindRefList(@ModelAttribute("profession") ProfessionRef profession, int page, int size,Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> selectNeedBindRefList <<<##");
		Long userId = UserUtils.getUserId();
		profession.setUserId(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/manage/selectNeedBindRefList");
		if(null != userId) {
			Long id = profession.getId();
			Pageable pageable = new PageRequest(page, size);
			ProfessionResponse response = professionProccessor.queryRefPage(profession, pageable);
			model.addAttribute("response", response);
		}
		model.addAttribute("entity", profession);
		LOGGER.debug("--->>> selectNeedBindRefList.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/manage/loadNeedBindRefList" }, name = "加载更多需要绑定的列表")
	public String loadNeedBindRefList(@ModelAttribute("profession") ProfessionRef profession, int page, int size,Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> loadNeedBindRefList <<<##");
		Long userId = UserUtils.getUserId();
		profession.setUserId(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/manage/loadNeedBindRefList");
		if(null != userId) {
			Long id = profession.getId();
			Pageable pageable = new PageRequest(page, size);
			ProfessionResponse response = professionProccessor.queryRefPage(profession, pageable);
			model.addAttribute("response", response);
		}
		model.addAttribute("entity", profession);
		LOGGER.debug("--->>> loadNeedBindRefList.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@ResponseBody
	@RequestMapping(value = { "ref/manage/add" }, name = "添加关联")
	public JsonResult addProfessionRef(@ModelAttribute("profession") ProfessionRef professionRef, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> addProfessionRef <<<##");
		Long userId = UserUtils.getUserId();
		professionRef.setUserId(userId);
		professionRef.setCreatedBy(userId);
		professionRef.setLastModifiedBy(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/manage/commonResult");
		boolean checked = professionRefValidator.validateForAdd(professionRef);
		if(checked) {
			Long id = professionRef.getId();
			professionRef = professionProccessor.addProfessionRef(professionRef);
		}
		professionRef.setMainEntity(null);
		model.addAttribute("entity", professionRef);
		JsonResult result = new JsonResult();
		result.setContent(professionRef);
		result.setSuccess(ProfessionRef.SUCCESS_YES);
		LOGGER.debug("--->>> addProfessionRef.resultPath: {} ---", resultPath);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = { "ref/manage/delete" }, name = "移除关联")
	public JsonResult deleteProfessionRef(@ModelAttribute("profession") ProfessionRef professionRef, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> deleteProfessionRef <<<##");
		Long userId = UserUtils.getUserId();
		professionRef.setUserId(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/manage/commonResult");
		boolean checked = professionRefValidator.validateForDelete(professionRef);
		if(checked) {
			Long id = professionRef.getId();
			professionRef = professionProccessor.deleteProfessionRef(professionRef);
		}
		model.addAttribute("entity", professionRef);
		JsonResult result = new JsonResult();
		result.setContent(professionRef);
		result.setSuccess(ProfessionRef.SUCCESS_YES);
		LOGGER.debug("--->>> deleteProfessionRef.resultPath: {} ---", resultPath);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = { "ref/manage/open" }, name = "开闭关联")
	public JsonResult openProfessionRef(@ModelAttribute("profession") ProfessionRef professionRef, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> openProfessionRef <<<##");
		Long userId = UserUtils.getUserId();
		professionRef.setUserId(userId);
		professionRef.setLastModifiedBy(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/manage/commonResult");
		boolean checked = professionRefValidator.validateForOpen(professionRef);
		if(checked) {
			Long id = professionRef.getId();
			professionRef = professionProccessor.openProfessionRef(professionRef);
		}
		model.addAttribute("entity", professionRef);
		JsonResult result = new JsonResult();
		result.setContent(professionRef);
		result.setSuccess(ProfessionRef.SUCCESS_YES);
		LOGGER.debug("--->>> openProfessionRef.resultPath: {} ---", resultPath);
		return result;
	}
	

	@RequestMapping(value = { "ref/manage/findByIdForEdit" }, name = "查询关联")
	public String findByIdForEdit(@ModelAttribute("profession") ProfessionRef professionRef, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> findByIdForEdit <<<##");
		Long userId = UserUtils.getUserId();
		professionRef.setUserId(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/manage/professionRefEditUI");
		if(null != userId) {
			Long id = professionRef.getId();
			professionRef = professionRefWrapper.findById(id,true);
			professionRef.setSuccess(ProfessionRef.SUCCESS_YES);
		}
		model.addAttribute("entity", professionRef);
		LOGGER.debug("--->>> findByIdForEdit.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "ref/manage/mergeProfessionRef" }, name = "修改关联")
	public String mergeProfessionRef(@ModelAttribute("profession") ProfessionRef professionRef, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeProfessionRef <<<##");
		Long userId = UserUtils.getUserId();
		professionRef.setUserId(userId);
		professionRef.setLastModifiedBy(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/manage/commonResult");
		
		boolean checked = professionRefValidator.validateForMerge(professionRef);
		if(checked) {
			professionRef = professionProccessor.mergeProfessionRef(professionRef);
			//professionRef.setSuccess(ProfessionRef.SUCCESS_YES);
		}
		model.addAttribute("entity", professionRef);
		LOGGER.debug("--->>> mergeProfessionRef.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@RequestMapping(value = { "ref/manage/saveSimpleSingle" }, name = "简易修改关联")
	public String saveSimpleSingle(@ModelAttribute("profession") ProfessionRef professionRef, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeProfessionRef <<<##");
		Long userId = UserUtils.getUserId();
		professionRef.setUserId(userId);
		professionRef.setLastModifiedBy(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/manage/commonResult");
		
		boolean checked = professionRefValidator.validateForSimpleSingle(professionRef);
		if(checked) {
			professionRef = professionProccessor.mergeSimpleSingle(professionRef);
		}
		model.addAttribute("entity", professionRef);
		LOGGER.debug("--->>> mergeProfessionRef.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "ref/manage/deleteRefByBaseId" }, name = "删除该位置下所有关联文章")
	public String deleteRefByBaseId(@ModelAttribute("profession") ProfessionRef professionRef, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeProfessionRef <<<##");
		Long userId = UserUtils.getUserId();
		professionRef.setUserId(userId);
		professionRef.setLastModifiedBy(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/manage/commonResult");
		
		boolean checked = professionRefValidator.validateForDeleteRefByBaseId(professionRef);
		if(checked) {
			professionRef = professionProccessor.deleteRefByBaseId(professionRef);
		}
		model.addAttribute("entity", professionRef);
		LOGGER.debug("--->>> mergeProfessionRef.resultPath: {} ---", resultPath);
		return resultPath;
	}
}
