package dml.sz0099.course.app.client.controller.user;

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

import dml.sz0099.course.app.client.validator.user.SaywordValidator;
import dml.sz0099.course.app.client.wrapper.user.SaywordWrapper;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.Sayword;

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
public class SaywordController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SaywordController.class);

	@Autowired
	private SaywordValidator saywordValidator;

	@Autowired
	private SaywordWrapper saywordWrapper;

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
	
	
	public SaywordController() {
		LOGGER.debug("##>>> init CoeUserController <<<##");
	}

	
	@RequestMapping(value = { "myinfo/modifySayword" }, name = "修改传说UI")
	public String modifySayword(@ModelAttribute("entity") Sayword sayword, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> modifySayword <<<##");
		Long userId = UserUtils.getUserId();
		sayword.setUserId(userId);
		sayword.setLastModifiedBy(userId);
		sayword.setCreatedBy(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"personal/saywordEditUI");
		LOGGER.debug("--->>> modifySayword.resultPath: {} ---", resultPath);
		if(null != userId) {
			sayword = findThenCreate(sayword);
			
			sayword.setSuccess(CoeUser.SUCCESS_YES);
		}
		model.addAttribute("entity", sayword);
		return resultPath;
	}


	/**
	 * @param userId
	 * @return
	 */
	private Sayword findThenCreate(Sayword sayword) {
		Sayword entity = saywordWrapper.findAndFixedSayword(sayword);
		//若用户信息不存在，执行创建
		if(null == entity) {
			//执行创建
			sayword = saywordWrapper.persistEntity(sayword);
		}else {
			sayword = entity;
		}
		return sayword;
	}
	
	@RequestMapping(value = { "myinfo/doModifySayword" }, name = "执行修改传说")
	public String doModifySayword(@ModelAttribute("entity") Sayword sayword, Errors errors, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> coeuser.doModifySayword <<<##");
		Long userId = UserUtils.getUserId();
		sayword.setUserId(userId);
		sayword.setCreatedBy(userId);
		sayword.setLastModifiedBy(userId);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/commonResult");
		LOGGER.debug("--->>> doModifyNickname.resultPath: {} ---", resultPath);
		
		boolean checked = saywordValidator.validateForModifySayword(errors, sayword);
		if(checked) {
			//校验通过
			sayword = saywordWrapper.mergeSayword(sayword);
			sayword.setSuccess(CoeUser.SUCCESS_YES);
			sayword.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_SAYWORD_MODIFY_SUCCESS);
			sayword.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_SAYWORD_MODIFY_SUCCESS);
		}
		model.addAttribute("entity", sayword);
		return resultPath;
	}


}
