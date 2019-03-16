package dml.sz0099.course.app.client.controller.media;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.jit4j.core.webmvc.config.ResourcePathConfig;
import org.jit4j.core.webmvc.util.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dml.sz0099.course.app.client.resolver.media.ImageProccessor;
import dml.sz0099.course.app.client.validator.media.ImageRefValidator;
import dml.sz0099.course.app.client.wrapper.media.ImageRefWrapper;
import dml.sz0099.course.app.module.define.CourseRespCode;
import dml.sz0099.course.app.module.define.SZ0099AppModule;
import dml.sz0099.course.app.persist.entity.media.ImageRef;
import dml.sz0099.course.app.persist.entity.media.bo.ImageRefBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ImageRefController 控制转发
 * @author bruce yang at 2018-08-29 22:41:14
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@RequestMapping("/sz0099/ood/media")
@Controller
public class ImageRefController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageRefController.class);

	@Autowired
	private ImageRefValidator imageRefValidator;

	@Autowired
	private ImageRefWrapper imageRefWrapper;
	
	@Autowired
	private ImageProccessor imageProccessor;

	//模板根目录
		private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
		//模块缩略名称目录
		private String MODULE_PATH_MEDIA = ResourcePathConfig.getModulesPath(SZ0099AppModule.APP_SZ0099_MEDIA.getAd());
		//模块主目录,一般与模块缩略名称相同
		private String MAPPING_PATH_MEDIA = ResourcePathConfig.getModulesPath(SZ0099AppModule.APP_SZ0099_MEDIA_PATH.currentPath());
		//模块二级目录
		private String MAPPING_SUB_PATH_MEDIA = ResourcePathConfig.getModulesPath(SZ0099AppModule.APP_SZ0099_MEDIA_PATH_SUB.currentPath());
		
		@PostConstruct
		public void init() {
			LOGGER.debug("========== TLE_DEFALT_FRONT: {} ========== ", TLE_DEFALT_FRONT);
			LOGGER.debug("========== MODULE_PATH_MEDIA: {} ========== ", MODULE_PATH_MEDIA);
			LOGGER.debug("========== MAPPING_PATH_MEDIA: {} ========== ", MAPPING_PATH_MEDIA);
			LOGGER.debug("========== MAPPING_SUB_PATH_MEDIA: {} ========== ", MAPPING_SUB_PATH_MEDIA);
		}

	

	public ImageRefController() {
		LOGGER.debug("##>>> init ImageRefController <<<##");
	}

	@RequestMapping(value = { "/operate/mergeImageRef" }, name = "保存图片小标题和序号")
	public String mergeImageRef(@ModelAttribute("imageRef") ImageRef imageRef, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeImageRef <<<##");
		imageRef.setUserId(UserUtils.getUserId());
		imageRef.setLastModifiedBy(UserUtils.getUserId());
		boolean checked = imageRefValidator.validateImageRef(imageRef);
		
		if(checked) {
			imageRef = imageProccessor.mergeImageRef(imageRef);
			imageRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_IMAGE_REF_MERGE_TITLE_SUCCESS);
			imageRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_IMAGE_REF_MERGE_TITLE_SUCCESS);
		}
		model.addAttribute("entity", imageRef);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_MEDIA,"/imagesResult");
		LOGGER.debug("--->>> mergeImageRef.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "/operate/deleteImageRef" }, name = "删除单张图片")
	public String deleteImageRef(@ModelAttribute("imageRef") ImageRef imageRef, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> deleteImageRef <<<##");
		imageRef.setUserId(UserUtils.getUserId());
		imageRef.setLastModifiedBy(UserUtils.getUserId());
		boolean checked = imageRefValidator.validateImageRef(imageRef);
		
		if(checked) {
			imageRef = imageProccessor.deleteImageRef(imageRef);
			imageRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_IMAGE_REF_DELETE_SUCCESS);
			imageRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_IMAGE_REF_DELETE_SUCCESS);
		}
		model.addAttribute("entity", imageRef);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_MEDIA,"/imagesResult");
		LOGGER.debug("--->>> deleteImageRef.resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "/imageRef/sub" }, name = "访问imageRefsub页面")
	public String accessImageRefSub(@ModelAttribute("imageRefBo") ImageRefBo imageRefBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessImageRef <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_MEDIA,"/imageRef");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

}
