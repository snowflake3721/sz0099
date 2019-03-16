package dml.sz0099.course.app.client.controller.user;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.jit4j.app.persist.entity.auth.User;
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
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dml.sz0099.course.app.client.validator.user.CoeUserVerifyValidator;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.client.wrapper.user.CoeUserVerifyWrapper;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.CoeUserVerify;

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
@RequestMapping("/sz0099/ood/personal")
@Controller
public class CoeUserVerifyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserVerifyController.class);

	@Autowired
	private CoeUserVerifyValidator coeUserVerifyValidator;

	@Autowired
	private CoeUserVerifyWrapper coeUserVerifyWrapper;
	
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
			LOGGER.debug("========== MAPPING_SUB_PATH_PERSONAL_BASIC: {} ========== ", MAPPING_SUB_PATH_PERSONAL_BASIC);
		}
	
	
	public CoeUserVerifyController() {
		LOGGER.debug("##>>> init CoeUserController <<<##");
	}
	
	
	@RequestMapping(value = { "myinfo/doModifyRealname" }, name = "执行修改真实姓名")
	public String doModifyRealname(@ModelAttribute("entity") CoeUserVerify coeUserVerify, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> coeuser.doModifyRealname <<<##");
		Long userId = UserUtils.getUserId();
		coeUserVerify.setUserId(userId);
		coeUserVerify.setLastModifiedBy(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"manage/commonResult");
		LOGGER.debug("--->>> doModifyRealname.resultPath: {} ---", resultPath);
		
		boolean checked = coeUserVerifyValidator.validateForModifyRealname(errors, coeUserVerify);
		if(checked) {
			//校验通过
			coeUserVerify = coeUserVerifyWrapper.mergeForRealname(coeUserVerify);
			coeUserVerify.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_REALNAME_MODIFY_SUCCESS);
			coeUserVerify.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_REALNAME_MODIFY_SUCCESS);
		}
		model.addAttribute("entity", coeUserVerify);
		return resultPath;
	}

	@RequestMapping(value = { "/myinfo/verifyUI" }, name = "访问认证审核UI页面")
	public String editVerifyUI(@ModelAttribute("coeUser") CoeUserVerify coeUserVerify, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> editVerifyUI <<<##");
		
		Long userId = UserUtils.getUserId();
		coeUserVerify.setUserId(userId);
		if(null == userId) {
			//请先登录再操作
		}else {
			coeUserVerify = findThenCreate(coeUserVerify);
		}
		model.addAttribute("entity", coeUserVerify);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/manage/verifyUI");
		LOGGER.debug("--->>> editVerifyUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
	
	
	
	@RequestMapping(value = { "myinfo/applyIdentity" }, name = "身份证认证申请")
	public String applyIdentity(@ModelAttribute("entity") CoeUserVerify coeUser, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> applyIdentity <<<##");
		Long userId = UserUtils.getUserId();
		coeUser.setUserId(userId);
		coeUser.setLastModifiedBy(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"manage/commonResult");
		LOGGER.debug("--->>> applyIdentity.resultPath: {} ---", resultPath);
		
		boolean checked = coeUserVerifyValidator.validateForApplyIdentity(errors, coeUser);
		if(checked) {
			//校验通过
			coeUser = coeUserVerifyWrapper.applyIdentity(coeUser);
			coeUser.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_IDENTITY_VERIFY_SUCCESS);
			coeUser.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_IDENTITY_VERIFY_SUCCESS);
		}
		model.addAttribute("entity", coeUser);
		return resultPath;
	}
	
	
	@RequestMapping(value = { "/myinfo/recievImgUI" }, name = "访问收款码页面")
	public String recievImgUI(@ModelAttribute("coeUser") CoeUser coeUser, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> recievImgUI <<<##");
		
		Long userId = UserUtils.getUserId();
		coeUser.setUserId(userId);
		if(null == userId) {
			//请先登录再操作
		}else {
			coeUser = coeUserWrapper.findByUserId(userId,true);
		}
		model.addAttribute("entity", coeUser);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/manage/recieveImgUI");
		LOGGER.debug("--->>> recievImgUI.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	private CoeUserVerify findThenCreate(CoeUserVerify coeUserVerify) {
		//若用户信息不存在，执行创建
		CoeUser coeUser = coeUserVerify.getCoeUser();
		if(null == coeUser) {
			coeUser = new CoeUser();
		}
		User user = UserUtils.getUser();
		CoeUser entity = coeUserWrapper.findThenCreate(user);
		if(null != entity) {
			CoeUserVerify verify = entity.getUserVerify();
			if(null == verify) {
				coeUserVerify.setId(entity.getId());
				coeUserVerify.setUserId(entity.getUserId());
				coeUserVerify.setIdstatus(CoeUserVerify.IDSTATUS_0_NO.getValueInt());
				coeUserVerify.setCreatedBy(entity.getCreatedBy());
				coeUserVerify.setLastModifiedBy(entity.getLastModifiedBy());
				coeUserVerify.setCoeUserId(entity.getId());
				coeUserVerify=coeUserVerifyWrapper.persistEntity(coeUserVerify);
			}else {
				verify.setCoeUser(entity);
				entity.setUserVerify(null);
				coeUserVerify=verify;
			}
		}
		return coeUserVerify;
	}
	

	@RequestMapping(value = { "/myinfo/findVerifyPage" }, name = "查询认证用户")
	//@RequiresRoles(value= {"CLUB_CREATOR","CLUB_MANAGER","plat_creator","plat_manager", "plat_super_manager"}, logical=Logical.OR)
	public String findVerifyPage(@ModelAttribute("entity") CoeUserVerify coeUserVerify, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> findVerifyPage <<<##");
		
		Long userId = UserUtils.getUserId();
		coeUserVerify.setUserId(userId);
		
		if(page==null) {
			page=0;
		}
		if(size==null || size>50) {
			size=20;
		}
		
		Page<CoeUserVerify> pageResult = null;
		if(null == userId) {
			//请先登录再操作
		}else {
			Pageable pageable = new PageRequest(page, size);
			Integer idstatus = coeUserVerify.getIdstatus();
			if(null==idstatus) {
				idstatus=CoeUserVerify.IDSTATUS_1_PROCESS.getValueInt();
			}
			pageResult = coeUserVerifyWrapper.findPageForVerify(coeUserVerify, pageable);
		}
		model.addAttribute("entityPage", pageResult);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/manage/findVerifyPage");
		LOGGER.debug("--->>> findVerifyPage.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "myinfo/idverifyPass" }, name = "身份证认证审核通过")
	public String idverifyPass(@ModelAttribute("entity") CoeUserVerify coeUser, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> idverifyPass <<<##");
		Long userId = UserUtils.getUserId();
		coeUser.setUserId(userId);
		coeUser.setLastModifiedBy(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"manage/verifyResult");
		LOGGER.debug("--->>> idverifyPass.resultPath: {} ---", resultPath);
		
		boolean checked = coeUserVerifyValidator.validateForIdentityPass(errors, coeUser);
		if(checked) {
			//审核通过
			coeUser.setIdstatus(CoeUserVerify.IDSTATUS_2_YES.getValueInt());
			coeUser.setRemark("已通过");
			coeUser = coeUserVerifyWrapper.verifyIdentity(coeUser);
			coeUser.setSuccess(CoeUserVerify.SUCCESS_YES);
			coeUser.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_IDVERIFY_HAS_PASSED);
			coeUser.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_IDVERIFY_HAS_PASSED);
		}
		model.addAttribute("entity", coeUser);
		return resultPath;
	}
	
	@RequestMapping(value = { "myinfo/idverifyReject" }, name = "身份证认证驳回")
	public String idverifyReject(@ModelAttribute("entity") CoeUserVerify coeUser, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> idverifyReject <<<##");
		Long userId = UserUtils.getUserId();
		coeUser.setUserId(userId);
		coeUser.setLastModifiedBy(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"manage/verifyResult");
		LOGGER.debug("--->>> idverifyReject.resultPath: {} ---", resultPath);
		
		boolean checked = coeUserVerifyValidator.validateForIdentityReject(errors, coeUser);
		if(checked) {
			//校验通过
			coeUser.setIdstatus(CoeUserVerify.IDSTATUS_3_REJECT.getValueInt());
			coeUser = coeUserVerifyWrapper.verifyIdentity(coeUser);
			coeUser.setSuccess(CoeUserVerify.SUCCESS_YES);
			coeUser.setRespCode(CourseProductRespCode.SZ0099_CODE_OOD_USER_VERIFY_REJECT);
			coeUser.setRespMsg(CourseProductRespCode.SZ0099_MSG_OOD_USER_VERIFY_REJECT);
		}
		model.addAttribute("entity", coeUser);
		return resultPath;
	}
	
}
