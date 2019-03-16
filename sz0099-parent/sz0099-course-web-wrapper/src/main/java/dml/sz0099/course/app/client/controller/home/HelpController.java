/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.controller.home;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jit4j.app.persist.entity.auth.User;
import org.jit4j.core.persist.page.PageRequest;
import org.jit4j.core.webmvc.config.ResourcePathConfig;
import org.jit4j.core.webmvc.util.UserUtils;
import org.jit8j.core.util.GsonBuilderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dml.sz0099.course.app.client.wrapper.article.CoeArticleWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.data.show.blooming.CircleMain;
import dml.sz0099.course.app.data.show.blooming.converter.CoeArticleConverter;
import dml.sz0099.course.app.data.show.layout.fetcher.CoeArticleFetcher;
import dml.sz0099.course.app.data.show.layout.fetcher.ProfessionFetcher;
import dml.sz0099.course.app.data.show.layout.fetcher.dto.CoeArticlePageDto;
import dml.sz0099.course.app.data.show.layout.fetcher.dto.ProfessionPageDto;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePosition;
import dml.sz0099.course.app.persist.entity.article.bo.CoeArticleBo;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPosition;
import dml.sz0099.course.app.persist.entity.user.CoeUser;

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
@RequestMapping("/help")
@Controller
public class HelpController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HelpController.class);
	
	
	//模板根目录
		private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
		//模块缩略名称目录
		private String MODULE_PATH_HOME = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_HOME.getAd());
		//模块主目录,一般与模块缩略名称相同
		private String MAPPING_PATH_HOME = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_HOME_PATH.currentPath());
		//模块二级目录
		private String MAPPING_SUB_PATH_HOME_BASIC = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_HOME_PATH_BASIC.currentPath());
		

	@RequestMapping(value = { "/addToDesktop" }, name = "添加公众号到桌面")
	public String addToDesktop( Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> addToDesktop <<<##");
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, "/help","/addToDesktop");
		LOGGER.debug("--->>>addToDesktop.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/topInWechat" }, name = "置顶公众号")
	public String topInWechat( Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> topInWechat <<<##");
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, "/help","/topInWechat");
		LOGGER.debug("--->>>topInWechat.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/readme" }, name = "阅读须知")
	public String readme( Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> readme <<<##");
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, "/help","/readme");
		LOGGER.debug("--->>>readme.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/hzj" }, name = "短信轰炸机")
	public String hzj( Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> hzj <<<##");
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, "/help","/hzj");
		LOGGER.debug("--->>>hzj.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	
}
