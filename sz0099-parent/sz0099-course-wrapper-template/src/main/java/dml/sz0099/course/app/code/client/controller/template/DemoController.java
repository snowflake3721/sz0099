package dml.sz0099.course.app.code.client.controller.template;

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

import dml.sz0099.course.app.code.client.validator.template.DemoValidator;
import dml.sz0099.course.app.code.client.wrapper.template.DemoWrapper;
import dml.sz0099.course.app.code.persist.entity.template.bo.DemoBo;
import dml.sz0099.course.app.module.define.SZ0099AppModule;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * DemoController 控制转发
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
public class DemoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

	@Autowired
	private DemoValidator demoValidator;

	@Autowired
	private DemoWrapper demoWrapper;

	//模板根目录
	private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
	//模块缩略名称目录
	private String MODULE_PATH_TEMPLATE = ResourcePathConfig.getModulesPath(SZ0099AppModule.APP_SZ0099_DEMO.getAd());
	//模块主目录,一般与模块缩略名称相同
	private String MAPPING_PATH_TEMPLATE = ResourcePathConfig.getModulesPath(SZ0099AppModule.APP_SZ0099_DEMO_PATH.currentPath());
	//模块二级目录
	private String MAPPING_SUB_PATH_TEMPLATE = ResourcePathConfig.getModulesPath(SZ0099AppModule.APP_SZ0099_DEMO_PATH_SUB.currentPath());
	
	@PostConstruct
	public void init() {
		LOGGER.debug("========== TLE_DEFALT_FRONT: {} ========== ", TLE_DEFALT_FRONT);
		LOGGER.debug("========== MODULE_PATH_TEMPLATE: {} ========== ", MODULE_PATH_TEMPLATE);
		LOGGER.debug("========== MAPPING_PATH_TEMPLATE: {} ========== ", MAPPING_PATH_TEMPLATE);
		LOGGER.debug("========== MAPPING_SUB_PATH_TEMPLATE: {} ========== ", MAPPING_SUB_PATH_TEMPLATE);
	}
	
	
	@InitBinder
	public void initBinder(DataBinder binder) {
		binder.setValidator(demoValidator);
	}

	public DemoController() {
		LOGGER.debug("##>>> init DemoController <<<##");
	}

	@RequestMapping(value = { "/demoUI" }, name = "访问demoUI页面")
	public String accessDemoUI(@ModelAttribute("demoBo") DemoBo demoBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessDemoUI <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_TEMPLATE,"/demoUI");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "/demo/sub" }, name = "访问demosub页面")
	public String accessDemoSub(@ModelAttribute("demoBo") DemoBo demoBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessDemo <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_TEMPLATE,"/demo");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

}
