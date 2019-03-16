package dml.sz0099.course.app.client.controller.activity;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.jit4j.core.webmvc.config.ResourcePathConfig;
import org.jit4j.core.webmvc.util.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dml.sz0099.course.app.client.validator.activity.CoeActivityFeeValidator;
import dml.sz0099.course.app.client.wrapper.activity.CoeActivityFeeWrapper;
import dml.sz0099.course.app.module.define.CoeActivityRespCode;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityFee;
import dml.sz0099.course.app.persist.entity.paragraph.ParagProduct;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeActivityFeeController 控制转发
 * @author bruce yang at 2018-08-29 22:41:14
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@RequestMapping("/sz0099/ood/activity/fee")
@Controller
public class CoeActivityFeeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityFeeController.class);

	@Autowired
	private CoeActivityFeeValidator coeActivityFeeValidator;

	@Autowired
	private CoeActivityFeeWrapper coeActivityFeeWrapper;
	
	
	

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
	
	public CoeActivityFeeController() {
		LOGGER.debug("##>>> init CoeActivityFeeController <<<##");
	}

	
	@RequestMapping(value = { "manage/mergeFee" }, name = "更新费用")
	public String mergeFee(@ModelAttribute("coeActivityFee") CoeActivityFee coeActivityFee,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeFee <<<##");

		Long userId = UserUtils.getUserId();
		coeActivityFee.setCreatedBy(userId);
		coeActivityFee.setLastModifiedBy(userId);
		boolean checked = coeActivityFeeValidator.validateMergeFee(coeActivityFee);
		if(checked) {
			coeActivityFee = coeActivityFeeWrapper.mergeFee(coeActivityFee);
			coeActivityFee.setSuccess(CoeActivityFee.SUCCESS_YES);
			coeActivityFee.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_FEE_MERGE_SUCCESS);
			coeActivityFee.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_FEE_MERGE_SUCCESS);
		}
		model.addAttribute("entity", coeActivityFee);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/manage/mergeResult");
		LOGGER.debug("--->>> mergeFee.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	@RequestMapping(value = { "manage/mergeDescription" }, name = "更新费用说明")
	public String mergeDescription(@ModelAttribute("coeActivityFee") CoeActivityFee coeActivityFee,Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeDescription <<<##");

		Long userId = UserUtils.getUserId();
		coeActivityFee.setCreatedBy(userId);
		coeActivityFee.setLastModifiedBy(userId);
		boolean checked = coeActivityFeeValidator.validateMergeDescription(coeActivityFee);
		if(checked) {
			coeActivityFee = coeActivityFeeWrapper.mergeDescription(coeActivityFee);
			coeActivityFee.setSuccess(CoeActivityFee.SUCCESS_YES);
			coeActivityFee.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_FEE_DESCRIPTION_MERGE_SUCCESS);
			coeActivityFee.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_FEE_DESCRIPTION_MERGE_SUCCESS);
		}
		model.addAttribute("entity", coeActivityFee);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ACTIVITY,"/manage/mergeResult");
		LOGGER.debug("--->>> mergeDescription.resultPath: {} ---", resultPath);
		return resultPath;
	}

	
}
