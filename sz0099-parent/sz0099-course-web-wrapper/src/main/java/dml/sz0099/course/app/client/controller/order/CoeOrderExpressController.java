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

import dml.sz0099.course.app.client.validator.order.CoeOrderExpressValidator;
import dml.sz0099.course.app.client.wrapper.order.CoeOrderExpressWrapper;
import dml.sz0099.course.app.persist.entity.order.bo.CoeOrderExpressBo;
import dml.sz0099.course.app.module.define.SZ0099AppModule;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeOrderExpressController 控制转发
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
public class CoeOrderExpressController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderExpressController.class);

	@Autowired
	private CoeOrderExpressValidator coeOrderExpressValidator;

	@Autowired
	private CoeOrderExpressWrapper coeOrderExpressWrapper;

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
		binder.setValidator(coeOrderExpressValidator);
	}

	public CoeOrderExpressController() {
		LOGGER.debug("##>>> init CoeOrderExpressController <<<##");
	}

	@RequestMapping(value = { "/coeOrderExpressUI" }, name = "访问coeOrderExpressUI页面")
	public String accessCoeOrderExpressUI(@ModelAttribute("coeOrderExpressBo") CoeOrderExpressBo coeOrderExpressBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCoeOrderExpressUI <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ORDER,"/coeOrderExpressUI");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "/coeOrderExpress/sub" }, name = "访问coeOrderExpresssub页面")
	public String accessCoeOrderExpressSub(@ModelAttribute("coeOrderExpressBo") CoeOrderExpressBo coeOrderExpressBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCoeOrderExpress <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_ORDER,"/coeOrderExpress");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

}
