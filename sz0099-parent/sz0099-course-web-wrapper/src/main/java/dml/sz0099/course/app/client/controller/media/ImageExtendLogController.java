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

import dml.sz0099.course.app.client.validator.media.ImageExtendLogValidator;
import dml.sz0099.course.app.client.wrapper.media.ImageExtendLogWrapper;
import dml.sz0099.course.app.module.define.SZ0099AppModule;
import dml.sz0099.course.app.persist.entity.media.bo.ImageExtendLogBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ImageExtendLogController 控制转发
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
public class ImageExtendLogController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageExtendLogController.class);

	@Autowired
	private ImageExtendLogValidator imageExtendLogValidator;

	@Autowired
	private ImageExtendLogWrapper imageExtendLogWrapper;

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
		binder.setValidator(imageExtendLogValidator);
	}

	public ImageExtendLogController() {
		LOGGER.debug("##>>> init ImageExtendLogController <<<##");
	}

	@RequestMapping(value = { "/imageExtendLogUI" }, name = "访问imageExtendLogUI页面")
	public String accessImageExtendLogUI(@ModelAttribute("imageExtendLogBo") ImageExtendLogBo imageExtendLogBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessImageExtendLogUI <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_MEDIA,"/imageExtendLogUI");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "/imageExtendLog/sub" }, name = "访问imageExtendLogsub页面")
	public String accessImageExtendLogSub(@ModelAttribute("imageExtendLogBo") ImageExtendLogBo imageExtendLogBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessImageExtendLog <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_MEDIA,"/imageExtendLog");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

}
