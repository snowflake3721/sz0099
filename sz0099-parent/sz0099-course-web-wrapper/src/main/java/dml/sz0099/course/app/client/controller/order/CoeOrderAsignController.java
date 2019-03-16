package dml.sz0099.course.app.client.controller.order;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.jit4j.core.webmvc.config.ResourcePathConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dml.sz0099.course.app.client.validator.order.CoeOrderAsignValidator;
import dml.sz0099.course.app.client.wrapper.order.CoeOrderAsignWrapper;
import dml.sz0099.course.app.persist.entity.order.bo.CoeOrderAsignBo;
import dml.sz0099.course.app.module.define.SZ0099AppModule;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeOrderAsignController 控制转发
 * @author bruce yang at 2018-08-29 22:41:14
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@RequestMapping("/sz0099/template")
@Controller
public class CoeOrderAsignController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderAsignController.class);

	@Autowired
	private CoeOrderAsignValidator coeOrderAsignValidator;

	@Autowired
	private CoeOrderAsignWrapper coeOrderAsignWrapper;

	//模板根目录
	private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
	//模块缩略名称目录
	private String MODULE_PATH_ORDER = ResourcePathConfig.getModulesPath(SZ0099AppModule.APP_SZ0099_DEMO.getAd());
	//模块主目录,一般与模块缩略名称相同
	private String MAPPING_PATH_ORDER = ResourcePathConfig.getModulesPath(SZ0099AppModule.APP_SZ0099_DEMO_PATH.currentPath());
	//模块二级目录
	private String MAPPING_SUB_PATH_ORDER = ResourcePathConfig.getModulesPath(SZ0099AppModule.APP_SZ0099_DEMO_PATH_SUB.currentPath());
	
	@PostConstruct
	public void init() {
		LOGGER.debug("========== TLE_DEFALT_FRONT: {} ========== ", TLE_DEFALT_FRONT);
		LOGGER.debug("========== MODULE_PATH_ORDER: {} ========== ", MODULE_PATH_ORDER);
		LOGGER.debug("========== MAPPING_PATH_ORDER: {} ========== ", MAPPING_PATH_ORDER);
		LOGGER.debug("========== MAPPING_SUB_PATH_ORDER: {} ========== ", MAPPING_SUB_PATH_ORDER);
	}
	
	
	@InitBinder
	public void initBinder(DataBinder binder) {
		binder.setValidator(coeOrderAsignValidator);
	}

	public CoeOrderAsignController() {
		LOGGER.debug("##>>> init CoeOrderAsignController <<<##");
	}

	@RequestMapping(value = { "/coeOrderAsignUI" }, name = "访问coeOrderAsignUI页面")
	public String accessCoeOrderAsignUI(@ModelAttribute("coeOrderAsignBo") CoeOrderAsignBo coeOrderAsignBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCoeOrderAsignUI <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ORDER,"/coeOrderAsignUI");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "/coeOrderAsign/sub" }, name = "访问coeOrderAsignsub页面")
	public String accessCoeOrderAsignSub(@ModelAttribute("coeOrderAsignBo") CoeOrderAsignBo coeOrderAsignBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCoeOrderAsign <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_ORDER,"/coeOrderAsign");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

}
