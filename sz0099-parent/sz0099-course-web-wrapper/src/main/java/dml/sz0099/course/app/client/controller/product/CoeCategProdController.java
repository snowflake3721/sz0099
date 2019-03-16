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

import dml.sz0099.course.app.client.validator.product.CoeCategProdValidator;
import dml.sz0099.course.app.client.wrapper.product.CoeCategProdWrapper;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.module.define.SZ0099AppModule;
import dml.sz0099.course.app.persist.entity.product.bo.CoeCategProdBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeCategProdController 控制转发
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
public class CoeCategProdController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeCategProdController.class);

	@Autowired
	private CoeCategProdValidator coeCategProdValidator;

	@Autowired
	private CoeCategProdWrapper coeCategProdWrapper;

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
	

	public CoeCategProdController() {
		LOGGER.debug("##>>> init CoeCategProdController <<<##");
	}

	@RequestMapping(value = { "/categProdUI" }, name = "访问coeCategProdUI页面")
	public String accessCoeCategProdUI(@ModelAttribute("coeCategProdBo") CoeCategProdBo coeCategProdBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCoeCategProdUI <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PRODUCT,"/coeCategProdUI");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "/categProd/sub" }, name = "访问coeCategProdsub页面")
	public String accessCoeCategProdSub(@ModelAttribute("coeCategProdBo") CoeCategProdBo coeCategProdBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessCoeCategProd <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_PRODUCT_BASIC,"/coeCategProd");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

}
