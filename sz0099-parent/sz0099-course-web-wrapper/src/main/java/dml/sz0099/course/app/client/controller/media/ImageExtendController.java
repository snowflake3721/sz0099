package dml.sz0099.course.app.client.controller.media;

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

import dml.sz0099.course.app.client.validator.media.ImageExtendValidator;
import dml.sz0099.course.app.client.wrapper.media.ImageExtendWrapper;
import dml.sz0099.course.app.module.define.SZ0099AppModule;
import dml.sz0099.course.app.persist.entity.media.bo.ImageExtendBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ImageExtendController 控制转发
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
public class ImageExtendController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageExtendController.class);

	@Autowired
	private ImageExtendValidator imageExtendValidator;

	@Autowired
	private ImageExtendWrapper imageExtendWrapper;

	//模板根目录
	private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
	//模块缩略名称目录
	private String MODULE_PATH_MEDIA = ResourcePathConfig.getModulesPath(SZ0099AppModule.APP_SZ0099_DEMO.getAd());
	//模块主目录,一般与模块缩略名称相同
	private String MAPPING_PATH_MEDIA = ResourcePathConfig.getModulesPath(SZ0099AppModule.APP_SZ0099_DEMO_PATH.currentPath());
	//模块二级目录
	private String MAPPING_SUB_PATH_MEDIA = ResourcePathConfig.getModulesPath(SZ0099AppModule.APP_SZ0099_DEMO_PATH_SUB.currentPath());
	
	@PostConstruct
	public void init() {
		LOGGER.debug("========== TLE_DEFALT_FRONT: {} ========== ", TLE_DEFALT_FRONT);
		LOGGER.debug("========== MODULE_PATH_MEDIA: {} ========== ", MODULE_PATH_MEDIA);
		LOGGER.debug("========== MAPPING_PATH_MEDIA: {} ========== ", MAPPING_PATH_MEDIA);
		LOGGER.debug("========== MAPPING_SUB_PATH_MEDIA: {} ========== ", MAPPING_SUB_PATH_MEDIA);
	}
	
	
	@InitBinder
	public void initBinder(DataBinder binder) {
		binder.setValidator(imageExtendValidator);
	}

	public ImageExtendController() {
		LOGGER.debug("##>>> init ImageExtendController <<<##");
	}

	@RequestMapping(value = { "/imageExtendUI" }, name = "访问imageExtendUI页面")
	public String accessImageExtendUI(@ModelAttribute("imageExtendBo") ImageExtendBo imageExtendBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessImageExtendUI <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_MEDIA,"/imageExtendUI");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "/imageExtend/sub" }, name = "访问imageExtendsub页面")
	public String accessImageExtendSub(@ModelAttribute("imageExtendBo") ImageExtendBo imageExtendBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessImageExtend <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_MEDIA,"/imageExtend");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

}
