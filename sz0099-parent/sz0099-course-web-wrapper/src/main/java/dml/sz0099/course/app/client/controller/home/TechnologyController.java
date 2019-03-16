/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.controller.home;

import javax.servlet.http.HttpServletRequest;

import org.jit4j.app.persist.entity.module.ModuleContext;
import org.jit4j.core.webmvc.config.ResourcePathConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dml.sz0099.course.app.data.handler.util.DataInitUtil;
import dml.sz0099.course.app.module.define.CourseAppModule;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-11-10 12:05:28
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-11-10	basic init
 * 
 * @formatter:on
 * </pre>
 */
@RequestMapping("/tech")
@Controller
public class TechnologyController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TechnologyController.class);
	
	
	//模板根目录
		private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
		//模块缩略名称目录
		private String MODULE_PATH_HOME = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_HOME.getAd());
		//模块主目录,一般与模块缩略名称相同
		private String MAPPING_PATH_HOME = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_HOME_PATH.currentPath());
		//模块二级目录
		private String MAPPING_SUB_PATH_HOME_BASIC = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_HOME_PATH_BASIC.currentPath());
		

	@RequestMapping(value = { "/index" }, name = "技术合作")
	public String techIndex( Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> techIndex <<<##");
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, "/tech","/index");
		LOGGER.debug("--->>>techIndex.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@Autowired
	private DataInitUtil dataInitUtil;
	
	@RequestMapping(value = { "/initData" }, name = "初始化数据")
	public String initData( Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> initData <<<##");
		//dataInitUtil.initAppModule();
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, "/tech","/initData");
		LOGGER.debug("--->>>initData.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/doInitData/appModule" }, name = "初始化appModule")
	public String initAppModule( Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> initAppModule <<<##");
		dataInitUtil.initAppModule();
		ModuleContext moduleContext = new ModuleContext();
		moduleContext.setSuccess(ModuleContext.SUCCESS_YES);
		moduleContext.setRespCode("INITAPPMODULE_SUCCESS");
		moduleContext.setRespMsg("appModule初始化成功");
		model.addAttribute("entity", moduleContext);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, "/tech","/commonResult");
		LOGGER.debug("--->>>initAppModule.resultPath: {} ---", resultPath);
		return resultPath;
	}
	@RequestMapping(value = { "/doInitData/initWechatConfig" }, name = "初始化initWechatConfig")
	public String initWechatConfig( Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> initWechatConfig <<<##");
		dataInitUtil.initWechatConfig();
		ModuleContext moduleContext = new ModuleContext();
		moduleContext.setSuccess(ModuleContext.SUCCESS_YES);
		moduleContext.setRespCode("INITWECHATCONFIG_SUCCESS");
		moduleContext.setRespMsg("initWechatConfig初始化成功");
		model.addAttribute("entity", moduleContext);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, "/tech","/commonResult");
		LOGGER.debug("--->>>initWechatConfig.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
}
