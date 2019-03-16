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

import dml.sz0099.course.app.client.validator.order.CoeOrderProductValidator;
import dml.sz0099.course.app.client.wrapper.order.CoeOrderProductWrapper;
import dml.sz0099.course.app.persist.entity.order.bo.CoeOrderProductBo;
import dml.sz0099.course.app.module.define.SZ0099AppModule;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeOrderProductController 控制转发
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
public class CoeOrderProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderProductController.class);

	@Autowired
	private CoeOrderProductValidator coeOrderProductValidator;

	@Autowired
	private CoeOrderProductWrapper coeOrderProductWrapper;

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
		binder.setValidator(coeOrderProductValidator);
	}

	public CoeOrderProductController() {
		LOGGER.debug("##>>> init CoeOrderProductController <<<##");
	}

	@RequestMapping(value = { "/coeOrderProductUI" }, name = "访问coeOrderProductUI页面")
	public String accessCoeOrderProductUI(@ModelAttribute("coeOrderProductBo") CoeOrderProductBo coeOrderProductBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCoeOrderProductUI <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_ORDER,"/coeOrderProductUI");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "/coeOrderProduct/sub" }, name = "访问coeOrderProductsub页面")
	public String accessCoeOrderProductSub(@ModelAttribute("coeOrderProductBo") CoeOrderProductBo coeOrderProductBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCoeOrderProduct <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_ORDER,"/coeOrderProduct");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

}
