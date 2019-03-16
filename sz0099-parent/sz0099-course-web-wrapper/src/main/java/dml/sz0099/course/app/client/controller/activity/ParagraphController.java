package dml.sz0099.course.app.client.controller.activity;

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

import dml.sz0099.course.app.client.validator.paragraph.ParagraphValidator;
import dml.sz0099.course.app.client.wrapper.paragraph.ParagraphWrapper;
import dml.sz0099.course.app.module.define.SZ0099AppModule;
import dml.sz0099.course.app.persist.entity.paragraph.bo.ParagraphBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ParagraphController 控制转发
 * @author bruce yang at 2018-08-29 22:41:14
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@RequestMapping("/sz0099/ood/activity")
@Controller("activityParagraphController")
public class ParagraphController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParagraphController.class);

	@Autowired
	private ParagraphValidator paragraphValidator;

	@Autowired
	private ParagraphWrapper paragraphWrapper;

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
	
	
	public ParagraphController() {
		LOGGER.debug("##>>> init ParagraphController <<<##");
	}

	@RequestMapping(value = { "/paragraphUI" }, name = "访问paragraphUI页面")
	public String accessParagraphUI(@ModelAttribute("paragraphBo") ParagraphBo paragraphBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessParagraphUI <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PARAGRAPH,"/paragraphUI");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

}
