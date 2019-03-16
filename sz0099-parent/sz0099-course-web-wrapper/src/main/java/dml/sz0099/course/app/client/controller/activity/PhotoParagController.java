package dml.sz0099.course.app.client.controller.activity;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.jit4j.app.persist.entity.auth.User;
import org.jit4j.core.pub.transfer.dto.JsonResult;
import org.jit4j.core.webmvc.config.ResourcePathConfig;
import org.jit4j.core.webmvc.util.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dml.sz0099.course.app.client.validator.activity.PhotoParagValidator;
import dml.sz0099.course.app.client.wrapper.activity.CoeActivityWrapper;
import dml.sz0099.course.app.client.wrapper.activity.PhotoParagWrapper;
import dml.sz0099.course.app.module.define.CoeActivityRespCode;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.PhotoParag;
import dml.sz0099.course.app.persist.entity.media.ImageRef;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * PhotoParagController 控制转发
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
@Controller("activityPhotoParagController")
public class PhotoParagController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PhotoParagController.class);

	@Autowired
	private PhotoParagValidator photoParagValidator;

	@Autowired
	private PhotoParagWrapper photoParagWrapper;
	
	@Autowired
	private CoeActivityWrapper coeActivityWrapper;

	//模板根目录
	private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
	//模块缩略名称目录
	private String MODULE_PATH_ACTIVITY = ResourcePathConfig.getModulesPath(CourseAppModule.APP_OOD_ACTIVITY.getAd());
	//模块主目录,一般与模块缩略名称相同
	private String MAPPING_PATH_ACTIVITY = ResourcePathConfig.getModulesPath(CourseAppModule.APP_OOD_ACTIVITY_PATH.currentPath());
	//模块二级目录
	private String MAPPING_SUB_PATH_ACTIVITY_BASIC = ResourcePathConfig.getModulesPath(CourseAppModule.APP_OOD_ACTIVITY_PATH_BASIC.currentPath());
	
	@PostConstruct
	public void init() {
		LOGGER.debug("========== TLE_DEFALT_FRONT: {} ========== ", TLE_DEFALT_FRONT);
		LOGGER.debug("========== MODULE_PATH_ACTIVITY: {} ========== ", MODULE_PATH_ACTIVITY);
		LOGGER.debug("========== MAPPING_PATH_ACTIVITY: {} ========== ", MAPPING_PATH_ACTIVITY);
		LOGGER.debug("========== MAPPING_SUB_PATH_ACTIVITY_BASIC: {} ========== ", MAPPING_SUB_PATH_ACTIVITY_BASIC);
	}
	
	public PhotoParagController() {
		LOGGER.debug("##>>> init PhotoParagController <<<##");
	}

	@RequestMapping(value = { "/parag/create" }, name = "创建内容")
	public String create(@ModelAttribute("ref") ImageRef ref, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> create <<<##");
		Long userId = UserUtils.getUserId();
		if(null != userId) {
			ref.setUserId(userId);
			ref.setCreatedBy(userId);
			ref.setLastModifiedBy(userId);
			boolean checked = photoParagValidator.validateForCreate(ref);
			if(checked) {
				PhotoParag photoParag = photoParagWrapper.createPhotoParag(ref);
				photoParag.setSuccess(PhotoParag.SUCCESS_YES);
				model.addAttribute("ref", photoParag);
				
				Long mainId = ref.getMainId();
				CoeActivity activity = coeActivityWrapper.findByIdOnly(mainId);
				boolean showTemplate=false;
				Integer t = activity.getTemplate();
				if(t != null && t > CoeActivity.TEMPLATE_NO.getValueInt()) {
					showTemplate = true;
				}
				model.addAttribute("showTemplate", showTemplate);
			}
		}
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"parag/create");
		LOGGER.debug("--->>> create.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/parag/delete" }, name = "删除一个图片段落")
	public String delete(@ModelAttribute("ref") PhotoParag ref, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> delete <<<##");
		Long userId = UserUtils.getUserId();
		if(null != userId) {
			ref.setUserId(userId);
			ref.setCreatedBy(userId);
			ref.setLastModifiedBy(userId);
			//删除之前要检验是否是模板
			photoParagWrapper.deleteById(ref);
		}
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"commonResult");
		LOGGER.debug("--->>> delete.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/parag/mergeRefForDescription" }, name = "保存内容")
	public String mergeRefForDescription(@ModelAttribute("ref") PhotoParag ref, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeRefForDescription <<<##");
		Long userId = UserUtils.getUserId();
		if(null != userId) {
			ref.setUserId(userId);
			ref.setCreatedBy(userId);
			ref.setLastModifiedBy(userId);
			photoParagWrapper.mergeRefForDescription(ref);
		}
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"commonResult");
		LOGGER.debug("--->>> mergeRefForDescription.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/parag/mergeRefForOrder" }, name = "保存排序")
	@ResponseBody
	public JsonResult mergeRefForOrder(@ModelAttribute("ref") PhotoParag ref, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeRefForOrder <<<##");
		Long userId = UserUtils.getUserId();
		JsonResult result = new JsonResult();
		if(null != userId) {
			ref.setUserId(userId);
			ref.setCreatedBy(userId);
			ref.setLastModifiedBy(userId);
			photoParagWrapper.mergeRefForOrder(ref);
			ref.setSuccess(PhotoParag.SUCCESS_YES);
			result.setSuccess(JsonResult.SUCCESS_YES);
		}else {
			result.setSuccess(JsonResult.SUCCESS_NO);
		}
		result.setContent(ref);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"commonResult");
		LOGGER.debug("--->>> mergeRefForOrder.resultPath: {} ---", resultPath);
		return result;
	}
	
	
	@RequestMapping(value = { "/parag/refreshRefForDescription" }, name = "重新加载内容")
	@ResponseBody
	public JsonResult refreshRefForDescription(@ModelAttribute("ref") PhotoParag ref, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> refreshRefForDescription <<<##");
		Long userId = UserUtils.getUserId();
		JsonResult result = new JsonResult();
		Long id = ref.getId();
		if(null != userId && null != id) {
			ref.setUserId(userId);
			ref.setCreatedBy(userId);
			ref.setLastModifiedBy(userId);
			PhotoParag entity = photoParagWrapper.findById(id);
			result.setContent(entity);
		}
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"commonResult");
		LOGGER.debug("--->>> refreshRefForDescription.resultPath: {} ---", resultPath);
		return result;
	}
	
	@RequestMapping(value = { "/parag/addImageById" }, name = "添加图片")
	//@ResponseBody
	public String addImageRef(@ModelAttribute("ref") ImageRef ref, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> addImageRef <<<##");
		User user = UserUtils.getUser();
		Long id = ref.getId();
		if(null != user && null != id) {
			Long userId = UserUtils.getUserId();
			ref.setUserId(userId);
			ref.setCreatedBy(userId);
			ref.setLastModifiedBy(userId);
			boolean checked = photoParagValidator.validateForAddImageRef(ref);
			if(checked) {
				PhotoParag entity = photoParagWrapper.addImageById(ref);
				model.addAttribute("ref", entity);
			}
		}
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"parag/addRefImage");
		LOGGER.debug("--->>> addImageRef.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/parag/changeImageById" }, name = "更改图片")
	//@ResponseBody
	public String changeImageRef(@ModelAttribute("ref") ImageRef ref, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> changeImageRef <<<##");
		User user = UserUtils.getUser();
		Long id = ref.getId();
		if(null != user && null != id) {
			Long userId = UserUtils.getUserId();
			ref.setUserId(userId);
			ref.setCreatedBy(userId);
			ref.setLastModifiedBy(userId);
			boolean checked = photoParagValidator.validateForChangeImageRef(ref);
			if(checked) {
				PhotoParag entity = photoParagWrapper.changeImageById(ref);
				model.addAttribute("ref", entity);
			}
		}
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"parag/addRefImage");
		LOGGER.debug("--->>> changeImageRef.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/parag/deleteImageById" }, name = "删除图片")
	@ResponseBody
	public JsonResult deleteImageRef(@ModelAttribute("ref") ImageRef ref, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> addImageRef <<<##");
		User user = UserUtils.getUser();
		JsonResult result = new JsonResult();
		Long id = ref.getId();
		if(null != user && null != id) {
			Long userId = UserUtils.getUserId();
			ref.setUserId(userId);
			ref.setCreatedBy(userId);
			ref.setLastModifiedBy(userId);
			boolean checked = photoParagValidator.validateForDeleteImageRef(ref);
			if(checked) {
				photoParagWrapper.deleteImageById(ref);
			}
			result.setSuccess(JsonResult.SUCCESS_YES);
			result.setRespCode(ref.getRespCode());
			result.setRespMsg(ref.getRespMsg());
		}else {
			result.setSuccess(JsonResult.SUCCESS_NO);
		}
		result.setContent(ref);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"commonResult");
		LOGGER.debug("--->>> deleteImageRef.resultPath: {} ---", resultPath);
		return result;
	}
	
	@RequestMapping(value = { "/parag/mergeTemplateById" }, name = "设置模板")
	public String mergeTemplateById(@ModelAttribute("entity") ImageRef ref, Model model, HttpServletRequest request) {
		
		LOGGER.debug("##>>> mergeTemplateById <<<##");
		User user = UserUtils.getUser();
		Long id = ref.getId();
		if(null != user && null != id) {
			Long userId = UserUtils.getUserId();
			ref.setUserId(userId);
			ref.setCreatedBy(userId);
			ref.setLastModifiedBy(userId);
			boolean checked = photoParagValidator.validateForMergeTemplateById(ref);
			if(checked) {
				photoParagWrapper.mergeTemplateById(ref);
			}
		}
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"parag/mergeRefTemplate");
		LOGGER.debug("--->>> mergeTemplateById.resultPath: {} ---", resultPath);
		return resultPath;
	}

}
