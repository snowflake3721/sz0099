package dml.sz0099.course.app.client.controller.product;

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

import dml.sz0099.course.app.client.validator.product.CoeUserTagValidator;
import dml.sz0099.course.app.client.wrapper.product.CoeUserTagWrapper;
import dml.sz0099.course.app.persist.entity.product.bo.CoeUserTagBo;
import dml.sz0099.course.app.module.define.SZ0099AppModule;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeUserTagController 控制转发
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
public class CoeUserTagController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserTagController.class);

	@Autowired
	private CoeUserTagValidator coeUserTagValidator;

	@Autowired
	private CoeUserTagWrapper coeUserTagWrapper;

	//模板根目录
	private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
	//模块缩略名称目录
	private String MODULE_PATH_PRODUCT = ResourcePathConfig.getModulesPath(SZ0099AppModule.APP_SZ0099_DEMO.getAd());
	//模块主目录,一般与模块缩略名称相同
	private String MAPPING_PATH_PRODUCT = ResourcePathConfig.getModulesPath(SZ0099AppModule.APP_SZ0099_DEMO_PATH.currentPath());
	//模块二级目录
	private String MAPPING_SUB_PATH_PRODUCT = ResourcePathConfig.getModulesPath(SZ0099AppModule.APP_SZ0099_DEMO_PATH_SUB.currentPath());
	
	@PostConstruct
	public void init() {
		LOGGER.debug("========== TLE_DEFALT_FRONT: {} ========== ", TLE_DEFALT_FRONT);
		LOGGER.debug("========== MODULE_PATH_PRODUCT: {} ========== ", MODULE_PATH_PRODUCT);
		LOGGER.debug("========== MAPPING_PATH_PRODUCT: {} ========== ", MAPPING_PATH_PRODUCT);
		LOGGER.debug("========== MAPPING_SUB_PATH_PRODUCT: {} ========== ", MAPPING_SUB_PATH_PRODUCT);
	}
	
	
	@InitBinder
	public void initBinder(DataBinder binder) {
		binder.setValidator(coeUserTagValidator);
	}

	public CoeUserTagController() {
		LOGGER.debug("##>>> init CoeUserTagController <<<##");
	}

	@RequestMapping(value = { "/coeUserTagUI" }, name = "访问coeUserTagUI页面")
	public String accessCoeUserTagUI(@ModelAttribute("coeUserTagBo") CoeUserTagBo coeUserTagBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCoeUserTagUI <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/coeUserTagUI");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "/coeUserTag/sub" }, name = "访问coeUserTagsub页面")
	public String accessCoeUserTagSub(@ModelAttribute("coeUserTagBo") CoeUserTagBo coeUserTagBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCoeUserTag <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_PRODUCT,"/coeUserTag");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

}
