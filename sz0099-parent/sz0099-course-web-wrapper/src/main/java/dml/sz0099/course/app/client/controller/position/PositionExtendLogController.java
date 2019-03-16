package dml.sz0099.course.app.client.controller.position;

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

import dml.sz0099.course.app.client.validator.position.PositionExtendLogValidator;
import dml.sz0099.course.app.client.wrapper.position.PositionExtendLogWrapper;
import dml.sz0099.course.app.persist.entity.position.bo.PositionExtendLogBo;
import dml.sz0099.course.app.module.define.SZ0099AppModule;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * PositionExtendLogController 控制转发
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
public class PositionExtendLogController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PositionExtendLogController.class);

	@Autowired
	private PositionExtendLogValidator positionExtendLogValidator;

	@Autowired
	private PositionExtendLogWrapper positionExtendLogWrapper;

	//模板根目录
	private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
	//模块缩略名称目录
	private String MODULE_PATH_POSITION = ResourcePathConfig.getModulesPath(SZ0099AppModule.APP_SZ0099_DEMO.getAd());
	//模块主目录,一般与模块缩略名称相同
	private String MAPPING_PATH_POSITION = ResourcePathConfig.getModulesPath(SZ0099AppModule.APP_SZ0099_DEMO_PATH.currentPath());
	//模块二级目录
	private String MAPPING_SUB_PATH_POSITION = ResourcePathConfig.getModulesPath(SZ0099AppModule.APP_SZ0099_DEMO_PATH_SUB.currentPath());
	
	@PostConstruct
	public void init() {
		LOGGER.debug("========== TLE_DEFALT_FRONT: {} ========== ", TLE_DEFALT_FRONT);
		LOGGER.debug("========== MODULE_PATH_POSITION: {} ========== ", MODULE_PATH_POSITION);
		LOGGER.debug("========== MAPPING_PATH_POSITION: {} ========== ", MAPPING_PATH_POSITION);
		LOGGER.debug("========== MAPPING_SUB_PATH_POSITION: {} ========== ", MAPPING_SUB_PATH_POSITION);
	}
	
	
	@InitBinder
	public void initBinder(DataBinder binder) {
		binder.setValidator(positionExtendLogValidator);
	}

	public PositionExtendLogController() {
		LOGGER.debug("##>>> init PositionExtendLogController <<<##");
	}

	@RequestMapping(value = { "/positionExtendLogUI" }, name = "访问positionExtendLogUI页面")
	public String accessPositionExtendLogUI(@ModelAttribute("positionExtendLogBo") PositionExtendLogBo positionExtendLogBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessPositionExtendLogUI <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_POSITION,"/positionExtendLogUI");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "/positionExtendLog/sub" }, name = "访问positionExtendLogsub页面")
	public String accessPositionExtendLogSub(@ModelAttribute("positionExtendLogBo") PositionExtendLogBo positionExtendLogBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessPositionExtendLog <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_POSITION,"/positionExtendLog");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

}
