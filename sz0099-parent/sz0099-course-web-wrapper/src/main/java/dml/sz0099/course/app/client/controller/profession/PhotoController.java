package dml.sz0099.course.app.client.controller.profession;

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

import dml.sz0099.course.app.client.validator.paragraph.PhotoValidator;
import dml.sz0099.course.app.client.wrapper.paragraph.PhotoWrapper;
import dml.sz0099.course.app.module.define.SZ0099AppModule;
import dml.sz0099.course.app.persist.entity.paragraph.bo.PhotoBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * PhotoController 控制转发
 * @author bruce yang at 2018-08-29 22:41:14
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@RequestMapping("/sz0099/product/personal")
@Controller("profPhotoController")
public class PhotoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PhotoController.class);

	@Autowired
	private PhotoValidator photoValidator;

	@Autowired
	private PhotoWrapper photoWrapper;

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
		binder.setValidator(photoValidator);
	}

	public PhotoController() {
		LOGGER.debug("##>>> init PhotoController <<<##");
	}

	@RequestMapping(value = { "/photoUI" }, name = "访问photoUI页面")
	public String accessPhotoUI(@ModelAttribute("photoBo") PhotoBo photoBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessPhotoUI <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PARAGRAPH,"/photoUI");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "/photo/sub" }, name = "访问photosub页面")
	public String accessPhotoSub(@ModelAttribute("photoBo") PhotoBo photoBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessPhoto <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_PARAGRAPH,"/photo");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

}
