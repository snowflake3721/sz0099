package dml.sz0099.course.app.client.controller.user;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.jit4j.app.persist.entity.auth.User;
import org.jit4j.app.persist.entity.auth.UserRole;
import org.jit4j.core.pub.transfer.dto.FileJsonResult;
import org.jit4j.core.pub.transfer.dto.ImageRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dml.sz0099.course.app.client.validator.user.CoeUserValidator;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityUser;
import dml.sz0099.course.app.persist.entity.user.CoeUser;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeUserController 控制转发
 * @author bruce yang at 2018-08-29 22:41:14
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@RequestMapping("/sz0099/ood/product/personal")
@Controller
public class CoeUserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserController.class);

	@Autowired
	private CoeUserValidator coeUserValidator;

	@Autowired
	private CoeUserWrapper coeUserWrapper;

	//模板根目录
		private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
		//模块缩略名称目录
		private String MODULE_PATH_PRODUCT = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_PRODUCT.getAd());
		//模块主目录,一般与模块缩略名称相同
		private String MAPPING_PATH_PRODUCT = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_PRODUCT_PATH.currentPath());
		//模块二级目录
		private String MAPPING_SUB_PATH_PRODUCT_BASIC = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_PRODUCT_PATH_BASIC.currentPath());
		
		@PostConstruct
		public void init() {
			LOGGER.debug("========== TLE_DEFALT_FRONT: {} ========== ", TLE_DEFALT_FRONT);
			LOGGER.debug("========== MODULE_PATH_PRODUCT: {} ========== ", MODULE_PATH_PRODUCT);
			LOGGER.debug("========== MAPPING_PATH_PRODUCT: {} ========== ", MAPPING_PATH_PRODUCT);
			LOGGER.debug("========== MAPPING_SUB_PATH_PRODUCT_BASIC: {} ========== ", MAPPING_SUB_PATH_PRODUCT_BASIC);
		}
	
	
	public CoeUserController() {
		LOGGER.debug("##>>> init CoeUserController <<<##");
	}

	@RequestMapping(value = { "/myinfoUI" }, name = "访问myinfoUI页面")
	public String editMyinfoUI(@ModelAttribute("coeUser") CoeUser coeUser, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> editMyinfoUI <<<##");
		Long userId = UserUtils.getUserId();
		coeUser.setUserId(userId);
		if(null == userId) {
			//请先登录再操作
		}else {
			coeUser = findThenCreate(coeUser);
		}
		model.addAttribute("entity", coeUser);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/personal/myinfoUI");
		LOGGER.debug("--->>> editMyinfoUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping("operate/upload")
	@ResponseBody
    public FileJsonResult uploadImages(@ModelAttribute("images") ImageRequest imageRequst, @RequestParam MultipartFile[] files, Model model, HttpServletRequest request){ 
		Long userId = UserUtils.getUserId();
		
		imageRequst.setFiles(files);
		//model.addAttribute("entity", response);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/imagesResult");
		LOGGER.debug("--->>> uploadImages.resultPath: {} ---", resultPath);
		FileJsonResult result = new FileJsonResult();
		/*result.setContent(response.getContent());
		result.setSuccess(response.getSuccess());
		result.setRespCode(response.getRespCode());
		result.setRespMsg(response.getRespMsg());
		if(null != response && ImageResponse.SUCCESS_YES==response.getSuccess()) {
			result.setError(response.getRespMsg());
		}*/
		return result;
	}
	
	
	@RequestMapping(value = { "myinfo/doModifyEmail" }, name = "执行修改用户email")
	public String doModifyEmail(@ModelAttribute("entity") CoeUser coeUser, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> coeuser.doModifyEmail <<<##");
		Long userId = UserUtils.getUserId();
		coeUser.setUserId(userId);
		coeUser.setLastModifiedBy(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/commonResult");
		LOGGER.debug("--->>> doModifyEmail.resultPath: {} ---", resultPath);
		
		//执行订单校验
		boolean checked = coeUserValidator.validateForModifyEmail(errors, coeUser);
		if(checked) {
			//校验通过
			coeUser = coeUserWrapper.mergeForEmail(coeUser);
			coeUser.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_EMAIL_MODIFY_SUCCESS);
			coeUser.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_EMAIL_MODIFY_SUCCESS);
		}
		model.addAttribute("entity", coeUser);
		return resultPath;
	}
	
	@RequestMapping(value = { "myinfo/doModifyMobile" }, name = "执行修改用户手机号")
	public String doModifyMobile(@ModelAttribute("entity") CoeUser coeUser, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> coeuser.doModifyMobile <<<##");
		Long userId = UserUtils.getUserId();
		coeUser.setUserId(userId);
		coeUser.setLastModifiedBy(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/commonResult");
		LOGGER.debug("--->>> doModifyMobile.resultPath: {} ---", resultPath);
		
		//执行订单校验
		boolean checked = coeUserValidator.validateForModifyMobile(errors, coeUser);
		if(checked) {
			//校验通过
			coeUser = coeUserWrapper.mergeForMobile(coeUser);
			coeUser.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_MOBILE_MODIFY_SUCCESS);
			coeUser.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_MOBILE_MODIFY_SUCCESS);
		}
		model.addAttribute("entity", coeUser);
		return resultPath;
	}
	
	
	@RequestMapping(value = { "myinfo/doModifyNickname" }, name = "执行修改用户昵称")
	public String doModifyNickname(@ModelAttribute("entity") CoeUser coeUser, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> coeuser.doModifyNickname <<<##");
		Long userId = UserUtils.getUserId();
		coeUser.setUserId(userId);
		coeUser.setLastModifiedBy(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/commonResult");
		LOGGER.debug("--->>> doModifyNickname.resultPath: {} ---", resultPath);
		
		boolean checked = coeUserValidator.validateForModifyNickname(errors, coeUser);
		if(checked) {
			//校验通过
			coeUser = coeUserWrapper.mergeForNickname(coeUser);
			coeUser.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_NICKNAME_MODIFY_SUCCESS);
			coeUser.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_NICKNAME_MODIFY_SUCCESS);
		}
		model.addAttribute("entity", coeUser);
		return resultPath;
	}
	
	@RequestMapping(value = { "myinfo/doModifyPostname" }, name = "执行修改联系名称")
	public String doModifyPostname(@ModelAttribute("entity") CoeUser coeUser, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> coeuser.doModifyPostname <<<##");
		Long userId = UserUtils.getUserId();
		coeUser.setUserId(userId);
		coeUser.setLastModifiedBy(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/commonResult");
		LOGGER.debug("--->>> doModifyPostname.resultPath: {} ---", resultPath);
		
		boolean checked = coeUserValidator.validateForModifyPostname(errors, coeUser);
		if(checked) {
			//校验通过
			coeUser = coeUserWrapper.mergeForPostname(coeUser);
			coeUser.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_POSTNAME_MODIFY_SUCCESS);
			coeUser.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_POSTNAME_MODIFY_SUCCESS);
		}
		model.addAttribute("entity", coeUser);
		return resultPath;
	}
	
	@RequestMapping(value = { "myinfo/doModifyPostnameShow" }, name = "设置联系人显示隐藏")
	public String doModifyPostnameShow(@ModelAttribute("entity") CoeUser coeUser, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> coeuser.doModifyPostnameShow <<<##");
		Long userId = UserUtils.getUserId();
		coeUser.setUserId(userId);
		coeUser.setLastModifiedBy(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/commonResult");
		LOGGER.debug("--->>> doModifyPostnameShow.resultPath: {} ---", resultPath);
		
		boolean checked = coeUserValidator.validateForPrivacySetting(errors, coeUser,CoeUserValidator.PRIVACY_POSTNAMESHOW);
		if(checked) {
			//校验通过
			coeUser = coeUserWrapper.mergeForPostnameShow(coeUser);
			coeUser.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_PRIVATE_SETTING_SUCCESS);
			coeUser.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_PRIVATE_SETTING_SUCCESS);
		}
		model.addAttribute("entity", coeUser);
		return resultPath;
	}
	
	@RequestMapping(value = { "myinfo/doModifyMobileShow" }, name = "设置手机号显示隐藏")
	public String doModifyMobileShow(@ModelAttribute("entity") CoeUser coeUser, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> coeuser.doModifyMobileShow <<<##");
		Long userId = UserUtils.getUserId();
		coeUser.setUserId(userId);
		coeUser.setLastModifiedBy(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/commonResult");
		LOGGER.debug("--->>> doModifyMobileShow.resultPath: {} ---", resultPath);
		
		boolean checked = coeUserValidator.validateForPrivacySetting(errors, coeUser,CoeUserValidator.PRIVACY_MOBILESHOW);
		if(checked) {
			//校验通过
			coeUser = coeUserWrapper.mergeForMobileShow(coeUser);
			coeUser.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_PRIVATE_SETTING_SUCCESS);
			coeUser.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_PRIVATE_SETTING_SUCCESS);
		}
		model.addAttribute("entity", coeUser);
		return resultPath;
	}
	
	@RequestMapping(value = { "myinfo/doModifyEmailShow" }, name = "设置Email显示隐藏")
	public String doModifyEmailShow(@ModelAttribute("entity") CoeUser coeUser, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> coeuser.doModifyEmailShow <<<##");
		Long userId = UserUtils.getUserId();
		coeUser.setUserId(userId);
		coeUser.setLastModifiedBy(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/commonResult");
		LOGGER.debug("--->>> doModifyEmailShow.resultPath: {} ---", resultPath);
		
		boolean checked = coeUserValidator.validateForPrivacySetting(errors, coeUser,CoeUserValidator.PRIVACY_EMAILSHOW);
		if(checked) {
			//校验通过
			coeUser = coeUserWrapper.mergeForEmailShow(coeUser);
			coeUser.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_PRIVATE_SETTING_SUCCESS);
			coeUser.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_PRIVATE_SETTING_SUCCESS);
		}
		model.addAttribute("entity", coeUser);
		return resultPath;
	}
	
	@RequestMapping(value = { "myinfo/doModifyQqShow" }, name = "设置QQ显示隐藏")
	public String doModifyQqShow(@ModelAttribute("entity") CoeUser coeUser, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> coeuser.doModifyEmailShow <<<##");
		Long userId = UserUtils.getUserId();
		coeUser.setUserId(userId);
		coeUser.setLastModifiedBy(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/commonResult");
		LOGGER.debug("--->>> doModifyQqShow.resultPath: {} ---", resultPath);
		
		boolean checked = coeUserValidator.validateForPrivacySetting(errors, coeUser,CoeUserValidator.PRIVACY_QQSHOW);
		if(checked) {
			//校验通过
			coeUser = coeUserWrapper.mergeForQqShow(coeUser);
			coeUser.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_PRIVATE_SETTING_SUCCESS);
			coeUser.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_PRIVATE_SETTING_SUCCESS);
		}
		model.addAttribute("entity", coeUser);
		return resultPath;
	}
	

	/**
	 * @param userId
	 * @return
	 */
	private CoeUser findThenCreate(CoeUser coeUser) {
		//若用户信息不存在，执行创建
		User user = UserUtils.getUser();
		CoeUser entity = coeUserWrapper.findThenCreate(user);
		
		return entity;
	}
	
	@RequestMapping(value = { "/applyClubLeader" }, name = "申请领队页面")
	public String applyClubLeader(@ModelAttribute("condition") CoeUser coeUser, Integer page, Integer size, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> applyClubLeader <<<##");
		Long userId = UserUtils.getUserId();
		coeUser.setUserId(userId);
		UserRole userRole = coeUserWrapper.findClubLeader(coeUser);
		String resultPage="/user/applyClubLeader";
		if(null != userRole) {
			//resultPage="/user/upgradeClubLeader";
		}
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,resultPage);
		model.addAttribute("userRole", userRole);
		
		LOGGER.debug("--->>> applyClubLeader.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/doApplyClubLeader" }, name = "执行申请领队")
	public String doApplyClubLeader(@ModelAttribute("condition") CoeUser coeUser, Integer page, Integer size, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> doApplyClubLeader <<<##");
		Long userId = UserUtils.getUserId();
		coeUser.setUserId(userId);
		
		coeUserValidator.validateForDoApplyClubLeader(coeUser);
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/user/applyClubLeaderResult");
		LOGGER.debug("--->>> doApplyClubLeader.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/myIndex" }, name = "申请领队")
	public String myIndex(@ModelAttribute("condition") CoeActivityUser user, Integer page, Integer size, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> myIndex <<<##");
	
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/user/index");
		LOGGER.debug("--->>> myIndex.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
}
