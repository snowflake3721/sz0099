package dml.sz0099.course.app.client.controller.paragraph;

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

import dml.sz0099.course.app.client.validator.paragraph.PhotoParagValidator;
import dml.sz0099.course.app.client.wrapper.paragraph.PhotoParagWrapper;
import dml.sz0099.course.app.module.define.SZ0099AppModule;
import dml.sz0099.course.app.persist.entity.paragraph.bo.PhotoParagBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * PhotoParagController 控制转发
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
public class PhotoParagController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PhotoParagController.class);

	@Autowired
	private PhotoParagValidator photoParagValidator;

	@Autowired
	private PhotoParagWrapper photoParagWrapper;

	//模板根目录
	private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
	//模块缩略名称目录
	private String MODULE_PATH_PARAGRAPH = ResourcePathConfig.getModulesPath(SZ0099AppModule.APP_SZ0099_DEMO.getAd());
	//模块主目录,一般与模块缩略名称相同
	private String MAPPING_PATH_PARAGRAPH = ResourcePathConfig.getModulesPath(SZ0099AppModule.APP_SZ0099_DEMO_PATH.currentPath());
	//模块二级目录
	private String MAPPING_SUB_PATH_PARAGRAPH = ResourcePathConfig.getModulesPath(SZ0099AppModule.APP_SZ0099_DEMO_PATH_SUB.currentPath());
	
	@PostConstruct
	public void init() {
		LOGGER.debug("========== TLE_DEFALT_FRONT: {} ========== ", TLE_DEFALT_FRONT);
		LOGGER.debug("========== MODULE_PATH_PARAGRAPH: {} ========== ", MODULE_PATH_PARAGRAPH);
		LOGGER.debug("========== MAPPING_PATH_PARAGRAPH: {} ========== ", MAPPING_PATH_PARAGRAPH);
		LOGGER.debug("========== MAPPING_SUB_PATH_PARAGRAPH: {} ========== ", MAPPING_SUB_PATH_PARAGRAPH);
	}
	
	
	@InitBinder
	public void initBinder(DataBinder binder) {
		binder.setValidator(photoParagValidator);
	}

	public PhotoParagController() {
		LOGGER.debug("##>>> init PhotoParagController <<<##");
	}

	@RequestMapping(value = { "/photoParagUI" }, name = "访问photoParagUI页面")
	public String accessPhotoParagUI(@ModelAttribute("photoParagBo") PhotoParagBo photoParagBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessPhotoParagUI <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PARAGRAPH,"/photoParagUI");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "/photoParag/sub" }, name = "访问photoParagsub页面")
	public String accessPhotoParagSub(@ModelAttribute("photoParagBo") PhotoParagBo photoParagBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessPhotoParag <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_PARAGRAPH,"/photoParag");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

}
