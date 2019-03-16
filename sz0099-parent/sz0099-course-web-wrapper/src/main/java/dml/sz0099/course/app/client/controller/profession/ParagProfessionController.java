package dml.sz0099.course.app.client.controller.profession;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.jit4j.core.persist.page.PageRequest;
import org.jit4j.core.webmvc.config.ResourcePathConfig;
import org.jit4j.core.webmvc.util.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dml.sz0099.course.app.client.resolver.adaptor.media.ParagProfessionAdaptor;
import dml.sz0099.course.app.client.validator.profession.ParagProfessionValidator;
import dml.sz0099.course.app.client.wrapper.profession.ParagProfessionWrapper;
import dml.sz0099.course.app.client.wrapper.profession.ParagraphWrapper;
import dml.sz0099.course.app.module.define.CourseAppModule;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.article.ParagArticle;
import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.entity.profession.ParagProfession;
import dml.sz0099.course.app.persist.entity.profession.bo.ParagProfessionBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ParagProfessionController 控制转发
 * @author bruce yang at 2018-08-29 22:41:14
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@RequestMapping("/sz0099/ood/personal")
@Controller
public class ParagProfessionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParagProfessionController.class);

	@Autowired
	private ParagProfessionValidator paragProfessionValidator;

	@Autowired
	private ParagProfessionWrapper paragProfessionWrapper;
	
	@Autowired
	private ParagraphWrapper paragraphWrapper;
	
	@Autowired
	private ParagProfessionAdaptor paragraphAdaptor;

	// 模板根目录
		private String TLE_DEFALT_FRONT = ResourcePathConfig.getTemplateSubFront();
		// 模块缩略名称目录
		private String MODULE_PATH_PERSONAL = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_PERSONAL.getAd());
		// 模块主目录,一般与模块缩略名称相同
		private String MAPPING_PATH_PERSONAL = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_PERSONAL_PATH.currentPath());
		// 模块二级目录
		private String MAPPING_SUB_PATH_PERSONAL_BASIC = ResourcePathConfig.getModulesPath(CourseAppModule.APP_COURSE_PERSONAL_PATH_BASIC.currentPath());

		@PostConstruct
		public void init() {
			LOGGER.debug("========== TLE_DEFALT_FRONT: {} ========== ", TLE_DEFALT_FRONT);
			LOGGER.debug("========== MODULE_PATH_PERSONAL: {} ========== ", MODULE_PATH_PERSONAL);
			LOGGER.debug("========== MAPPING_PATH_PERSONAL: {} ========== ", MAPPING_PATH_PERSONAL);
			LOGGER.debug("========== MAPPING_SUB_PATH_PARAGRAPH: {} ========== ", MAPPING_SUB_PATH_PERSONAL_BASIC);
		}
	

	public ParagProfessionController() {
		LOGGER.debug("##>>> init ParagProfessionController <<<##");
	}
	
	
	@RequestMapping(value = { "profession/manage/paragraph/resetOrderSeq" }, name = "编号重排")
	public String resetOrderSeq(@ModelAttribute("entity") ParagProfession paragProfession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> resetOrderSeq <<<##");
		Long userId = UserUtils.getUserId();
		Long articleId = paragProfession.getMainId();
		paragProfession.setUserId(userId);
		paragProfession.setCreatedBy(userId);
		boolean checked = paragProfessionValidator.validateExistProfession(paragProfession);
		Page<ParagProfession> pageresult = null;
		if(checked) {
			pageresult=paragProfessionWrapper.resetOrderSeq(articleId, userId);
		}
		model.addAttribute("page", pageresult);
		//获取上传组件参数
		ImageExtend imageExtend = paragraphAdaptor.config();
		model.addAttribute("extend", imageExtend);
		
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/manage/paragraphEditListUI");
		LOGGER.debug("--->>> resetOrderSeq.resultPath: {} ---", resultPath);
		return resultPath;
	}


	/**
	 * 个人编辑或管理人员访问
	 * @param paragProfessionBo
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "profession/manage/paragraph/editListUI" }, name = "访问段落编辑页")
	public String editParagProfessionList(@ModelAttribute("entity") ParagProfession paragProfession, Integer page, Integer size, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> editParagProfessionList <<<##");

		Long userId = UserUtils.getUserId();
		Long professionId = paragProfession.getBaseId();
		paragProfession.setMainId(professionId);
		paragProfession.setUserId(userId);
		paragProfession.setCreatedBy(userId);
		boolean checked = paragProfessionValidator.validateExistProfession(paragProfession);
		if(checked) {
			if(page==null) {
				page=0;
			}
			if(size==null) {
				size=30;
			}
			PageRequest pageable = new PageRequest(page,size,Direction.ASC, "orderSeq");
			Page<ParagProfession> pageresult = paragProfessionWrapper.findByMainIdAndUserId(professionId, userId, pageable);
			long total = pageresult.getTotalElements();
			if(total==0) {
				//创建一个段落，执行保存
				paragProfession=paragProfessionWrapper.createParagProfession(paragProfession);
			}else {
				model.addAttribute("page", pageresult);
			}
			paragProfession.setSuccess(ParagProfession.SUCCESS_YES);
		}
		model.addAttribute("entity", paragProfession);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/manage/paragraphEditListUI");
		LOGGER.debug("--->>> editParagProfessionList.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "profession/manage/paragraph/add" }, name = "添加段落")
	public String addParagProfession(@ModelAttribute("paragProfession") ParagProfession paragProfession, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> addParagProfession <<<##");

		Long userId = UserUtils.getUserId();
		paragProfession.setUserId(userId);
		paragProfession.setCreatedBy(userId);
		boolean checked = paragProfessionValidator.validateAddProduct(paragProfession);
		if(checked) {
			paragProfession = paragProfessionWrapper.createParagProfession(paragProfession);
			paragProfession.setSuccess(ParagProfession.SUCCESS_YES);
			paragProfession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PARAGRAGH_ADD_SUCCESS);
			paragProfession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PARAGRAGH_ADD_SUCCESS);
		
		}
		model.addAttribute("entity", paragProfession);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/manage/paragraphAddUI");
		LOGGER.debug("--->>> addParagProfession.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "profession/manage/paragraph/mergeSingle" }, name = "保存段落")
	public String mergeParagProfession(@ModelAttribute("paragProfession") ParagProfession paragProfession, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> mergeParagProfession <<<##");

		Long userId = UserUtils.getUserId();
		paragProfession.setUserId(userId);
		paragProfession.setLastModifiedBy(userId);
		paragProfession.getId();
		boolean checked = paragProfessionValidator.validateMergeProduct(paragProfession);
		if(checked) {
			paragProfession = paragProfessionWrapper.mergeEntity(paragProfession);
			paragProfession.setSuccess(ParagProfession.SUCCESS_YES);
			paragProfession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PARAGRAGH_MERGE_SUCCESS);
			paragProfession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PARAGRAGH_MERGE_SUCCESS);
		}
		model.addAttribute("entity", paragProfession);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/manage/commonResult");
		LOGGER.debug("--->>> mergeParagProfession.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "profession/manage/paragraph/deleteSingle" }, name = "删除段落")
	public String deleteParagProfession(@ModelAttribute("paragProfession") ParagProfession paragProfession, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> deleteParagProfession <<<##");

		Long userId = UserUtils.getUserId();
		paragProfession.setUserId(userId);
		paragProfession.setLastModifiedBy(userId);
		paragProfession.getId();
		boolean checked = paragProfessionValidator.validateForDelete(paragProfession);
		if(checked) {
			paragraphAdaptor.deleteFromRemote(paragProfession);
			paragProfessionWrapper.deleteByParagIdAndUserId(paragProfession.getParagId(), userId, true);
			paragProfession.setSuccess(ParagProfession.SUCCESS_YES);
			paragProfession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PARAGRAGH_DELETE_SUCCESS);
			paragProfession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PARAGRAGH_DELETE_SUCCESS);
		}
		model.addAttribute("entity", paragProfession);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/manage/commonResult");
		LOGGER.debug("--->>> deleteParagProfession.resultPath: {} ---", resultPath);
		return resultPath;
	}
	
	@RequestMapping(value = { "profession/manage/paragraph/deleteAll" }, name = "删除全部段落")
	public String deleteParagProfessionAll(@ModelAttribute("paragProfession") ParagProfession paragProfession, Model model, HttpServletRequest request) {
		LOGGER.debug("##>>> deleteParagProfessionAll <<<##");

		Long userId = UserUtils.getUserId();
		paragProfession.setUserId(userId);
		paragProfession.setLastModifiedBy(userId);
		paragProfession.getId();
		Long professionId = paragProfession.getMainId();
		boolean checked = paragProfessionValidator.validateForDeleteAll(paragProfession);
		if(checked) {
			paragraphAdaptor.deleteFromRemote(paragProfession);
			paragProfessionWrapper.deleteByProfessionIdAndUserId(professionId, userId);
			paragProfession.setSuccess(ParagProfession.SUCCESS_YES);
			paragProfession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PARAGRAGH_DELETE_ALL_SUCCESS);
			paragProfession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PARAGRAGH_DELETE_ALL_SUCCESS);
		}
		model.addAttribute("entity", paragProfession);
		String resultPath = ResourcePathConfig.generateUrlPath(TLE_DEFALT_FRONT, MAPPING_PATH_PERSONAL,"/manage/commonResult");
		LOGGER.debug("--->>> deleteParagProfessionAll.resultPath: {} ---", resultPath);
		return resultPath;
	}

}
