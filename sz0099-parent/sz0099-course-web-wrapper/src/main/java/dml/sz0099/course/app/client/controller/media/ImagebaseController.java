package dml.sz0099.course.app.client.controller.media;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.jit4j.core.pub.transfer.dto.FileJsonResult;
import org.jit4j.core.pub.transfer.dto.ImageRequest;
import org.jit4j.core.webmvc.config.ResourcePathConfig;
import org.jit4j.core.webmvc.util.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dml.sz0099.course.app.client.resolver.media.ImageProccessor;
import dml.sz0099.course.app.client.resolver.media.ImageRequestResolver;
import dml.sz0099.course.app.client.resolver.media.ImageResponse;
import dml.sz0099.course.app.client.resolver.media.Picture;
import dml.sz0099.course.app.client.validator.media.ImagebaseValidator;
import dml.sz0099.course.app.client.wrapper.media.ImageExtendWrapper;
import dml.sz0099.course.app.client.wrapper.media.ImagebaseWrapper;
import dml.sz0099.course.app.module.define.SZ0099AppModule;
import dml.sz0099.course.app.persist.entity.media.bo.ImagebaseBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ImagebaseController 控制转发
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
public class ImagebaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImagebaseController.class);

	@Autowired
	private ImagebaseValidator imagebaseValidator;

	@Autowired
	private ImagebaseWrapper imagebaseWrapper;
	
	@Autowired
	private ImageExtendWrapper imageExtendWrapper;
	
	@Autowired
	private ImageRequestResolver imageRequestResolver;
	
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
	

	public ImagebaseController() {
		LOGGER.debug("##>>> init ImagebaseController <<<##");
	}

	@RequestMapping(value = { "/imagebaseUI" }, name = "访问imagebaseUI页面")
	public String accessImagebaseUI(@ModelAttribute("imagebaseBo") ImagebaseBo imagebaseBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessImagebaseUI <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_MEDIA,"/imagebaseUI");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}

	@RequestMapping(value = { "/imagebase/sub" }, name = "访问imagebasesub页面")
	public String accessImagebaseSub(@ModelAttribute("imagebaseBo") ImagebaseBo imagebaseBo, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> accessImagebase <<<##");

		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_SUB_PATH_MEDIA,"/imagebase");
		LOGGER.debug("--->>> resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping("operate/upload")
	@ResponseBody
    public FileJsonResult uploadImages(@ModelAttribute("images") ImageRequest imageRequst, @RequestParam MultipartFile[] files, Model model, HttpServletRequest request){ 
		Long userId = UserUtils.getUserId();
		imageRequst.setUserId(userId);
		
		imageRequst.setFiles(files);
		ImageResponse response = imageProccessor.proccess(imageRequst);
		model.addAttribute("entity", response);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_MEDIA,"/imagesResult");
		LOGGER.debug("--->>> uploadImages.resultPath: {} ---", resultPath);
		FileJsonResult result = new FileJsonResult();
		result.setContent(response.getContent());
		result.setSuccess(response.getSuccess());
		result.setRespCode(response.getRespCode());
		result.setRespMsg(response.getRespMsg());
		if(null != response && ImageResponse.SUCCESS_YES==response.getSuccess()) {
			result.setError(response.getRespMsg());
		}
		return result;
	}
	@RequestMapping("operate/upload/compress")
	@ResponseBody
    public FileJsonResult uploadImages(@ModelAttribute("images") ImageRequest imageRequst, Model model, HttpServletRequest request){ 
		Long userId = UserUtils.getUserId();
		imageRequst.setUserId(userId);
		
		ImageResponse response = imageProccessor.proccess(imageRequst);
		model.addAttribute("entity", response);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_MEDIA,"/imagesResult");
		LOGGER.debug("--->>> uploadImages.resultPath: {} ---", resultPath);
		FileJsonResult result = new FileJsonResult();
		result.setContent(response.getContent());
		result.setSuccess(response.getSuccess());
		result.setRespCode(response.getRespCode());
		result.setRespMsg(response.getRespMsg());
		if(null != response && ImageResponse.SUCCESS_YES==response.getSuccess()) {
			result.setError(response.getRespMsg());
		}
		return result;
	}
	
	
	@RequestMapping("operate/upload/single")
	@ResponseBody
    public FileJsonResult uploadImage(@ModelAttribute("images") ImageRequest imageRequst, @RequestParam MultipartFile[] files, Model model, HttpServletRequest request){ 
		Long userId = UserUtils.getUserId();
		imageRequst.setUserId(userId);
		
		imageRequst.setFiles(files);
		ImageResponse response = imageProccessor.proccessSingle(imageRequst);
		model.addAttribute("entity", response);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_MEDIA,"/imagesResult");
		LOGGER.debug("--->>> uploadImages.resultPath: {} ---", resultPath);
		FileJsonResult result = new FileJsonResult();
		result.setContent(response.getContent());
		result.setSuccess(response.getSuccess());
		result.setRespCode(response.getRespCode());
		result.setRespMsg(response.getRespMsg());
		if(null != response && ImageResponse.SUCCESS_YES==response.getSuccess()) {
			result.setError(response.getRespMsg());
		}
		return result;
	}
	
	@RequestMapping("operate/upload/single/html")
    public String uploadImageHtml(@ModelAttribute("images") ImageRequest imageRequst, @RequestParam MultipartFile[] files, Model model, HttpServletRequest request){ 
		Long userId = UserUtils.getUserId();
		imageRequst.setUserId(userId);
		imageRequst.setFiles(files);
		ImageResponse response = imageProccessor.proccessSingle(imageRequst);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_MEDIA,"/imageRefList");
		LOGGER.debug("--->>> uploadImageHtml.resultPath: {} ---", resultPath);
		model.addAttribute("response", response);
		return resultPath;
	}

}
