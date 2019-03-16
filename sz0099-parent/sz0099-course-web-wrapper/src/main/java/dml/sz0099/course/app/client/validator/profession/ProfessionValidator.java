package dml.sz0099.course.app.client.validator.profession;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.profession.CategProfessionWrapper;
import dml.sz0099.course.app.client.wrapper.profession.ProfessionWrapper;
import dml.sz0099.course.app.module.define.CoeArticleRespCode;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.order.CoeOrder;
import dml.sz0099.course.app.persist.entity.profession.CategProfession;
import dml.sz0099.course.app.persist.entity.profession.PhotoCover;
import dml.sz0099.course.app.persist.entity.profession.Profession;
import dml.sz0099.course.app.persist.entity.profession.bo.ProfessionBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ProfessionValidator 校验组件
 * @author bruce yang at 2018-08-29 22:40:14
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Component
public class ProfessionValidator implements Validator {
	
	@Autowired
	private ProfessionWrapper professionWrapper;
	
	@Autowired
	private CategProfessionWrapper categprofessionWrapper;

	@Override
	public boolean supports(Class<?> clazz) {
		return ProfessionBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ProfessionBo profession = (ProfessionBo) target;

		validateUuid(errors, profession.getUuid());

	}
	
	public Long countDraftList(Profession profession) {
		Long draftNum = professionWrapper.countDraftList(profession);
		if (null != draftNum && draftNum > 3) {
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PERSONAL_PROFESSION_EXTRA);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PERSONAL_PROFESSION_EXTRA);
		}
		return draftNum;
	}
	
	public Long countByUserId(Profession profession) {
		Long professionNum = null;
		Long userId = profession.getUserId();
		if (null != userId) {
			professionNum = professionWrapper.countByUserId(userId);
			if (null != professionNum && professionNum >= 3) {
				profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PERSONAL_PROFESSION_EXTRA);
				profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PERSONAL_PROFESSION_EXTRA);
			}
		}
		return professionNum;
	}

	/**
	 * Profession validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.profession.Profession.uuid.exist", "UUID不能为空");
		}
	}
	
	
	
	public boolean validateBaseinfo(Errors errors, Profession profession) {
		boolean existChecked = validateExist(profession);
		if(!existChecked) {
			return false;
		}
		
		boolean checked = validateNameAndTitle(profession);
		if(!checked) {
			return false;
		}
		
		String description = profession.getDescription();
		if(StringUtils.isBlank(description)) {
			profession.setSuccess(CoeOrder.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_DESCRIPTION_EMPTY);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_DESCRIPTION_EMPTY);
			return false;
		}
		
		if(description.length()>2048) {
			profession.setSuccess(CoeOrder.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_DESCRIPTION_EXTRA_LENGTH);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_DESCRIPTION_EXTRA_LENGTH);
			return false;
		}
		
		Long userId = profession.getLastModifiedBy();
		if(null==userId) {
			profession.setSuccess(Profession.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PARAGRAGH_USERID_NULL);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PARAGRAGH_USERID_NULL);
			return false;
		}
		return true;
	}
	
	public boolean validateForMergeTitleOnly(Profession profession) {

		Long userId = profession.getLastModifiedBy();
		if (null == userId) {
			profession.setSuccess(Profession.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_USER_NOT_LOGIN);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_USER_NOT_LOGIN);
			return false;
		}
		
		boolean existChecked = validateExist(profession);
		if (!existChecked) {
			return false;
		}
		
		String title=profession.getTitle();
		if(StringUtils.isNotBlank(title) && title.length()>32) {
			profession.setSuccess(CoeOrder.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_TITLE_EXTRA_LENGTH);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_TITLE_EXTRA_LENGTH);
			return false;
		}

		return true;
	}
	
	public boolean validateForMergeDescriptionOnly(Profession profession) {

		Long userId = profession.getLastModifiedBy();
		if (null == userId) {
			profession.setSuccess(Profession.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_USER_NOT_LOGIN);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_USER_NOT_LOGIN);
			return false;
		}
		
		boolean existChecked = validateExist(profession);
		if (!existChecked) {
			return false;
		}
		
		String description=profession.getDescription();
		if(StringUtils.isNoneBlank(description) && description.length()>2048) {
			profession.setSuccess(CoeOrder.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_DESCRIPTION_EXTRA_LENGTH);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_DESCRIPTION_EXTRA_LENGTH);
			return false;
		}

		return true;
	}
	
	/**
	 * @param profession
	 */
	private boolean validateNameAndTitle(Profession profession) {
		
		boolean namechecked = validateName(profession);
		if(!namechecked) {
			return false;
		}
		
		String title = profession.getTitle();
		if(StringUtils.isBlank(title)) {
			profession.setSuccess(CoeOrder.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_TITLE_EMPTY);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_TITLE_EMPTY);
			return false;
		}
		
		
		if(title.length()>32) {
			profession.setSuccess(CoeOrder.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_TITLE_EXTRA_LENGTH);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_TITLE_EXTRA_LENGTH);
			return false;
		}
		
		String subTitle = profession.getSubTitle();
		if(StringUtils.isNotBlank(subTitle) && subTitle.length()>32) {
			profession.setSuccess(CoeOrder.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_SUBTITLE_EXTRA_LENGTH);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_SUBTITLE_EXTRA_LENGTH);
			return false;
		}
		return true;
	}

	/**
	 * @param profession
	 */
	private boolean validateName(Profession profession) {
		String name = profession.getName();
		/*if(StringUtils.isBlank(name)) {
			profession.setSuccess(CoeOrder.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_NAME_EMPTY);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_NAME_EMPTY);
			return false;
		}*/
		
		if(StringUtils.isNotBlank(name) && name.length()>32) {
			profession.setSuccess(CoeOrder.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_NAME_EXTRA_LENGTH);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_NAME_EXTRA_LENGTH);
			return false;
		}
		return true;
	}
	
	public boolean validateForUser(Profession profession) {
		if(null == profession) {
			profession = new Profession(); 
			profession.setSuccess(Profession.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PERSONAL_PROFESSION_NOT_EXIST);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PERSONAL_PROFESSION_NOT_EXIST);
			return false;
		}
		Long userId = profession.getUserId();
		if(null == userId) {
			profession.setSuccess(CoeOrder.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_USERID_EMPTY);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_USERID_EMPTY);
			return false;
		}
		return true;
	}
	
	/**
	 * @param profession
	 * @return
	 */
	public boolean validateExist(Profession profession, boolean authorOwnCheck, int type) {
		if(null == profession) {
			profession = new Profession(); 
			profession.setSuccess(Profession.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PERSONAL_PROFESSION_NOT_EXIST);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PERSONAL_PROFESSION_NOT_EXIST);
			return false;
		}
		
		Long id = profession.getId();
		if(null == id) {
			profession.setSuccess(CoeOrder.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PERSONAL_PROFESSION_NOT_EXIST);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PERSONAL_PROFESSION_NOT_EXIST);
			return false;
		}
		
		Profession entity = professionWrapper.findByIdOnly(id);
		
		if(entity==null) {
			profession.setSuccess(CoeOrder.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PERSONAL_PROFESSION_NOT_EXIST);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PERSONAL_PROFESSION_NOT_EXIST);
			return false;
		}
		
		boolean deleted = entity.getDeleted();
		if(deleted) {
			profession.setSuccess(CoeOrder.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_MERGE_HAS_DELERED);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_MERGE_HAS_DELERED);
			return false;
		}
		
		if(authorOwnCheck) {
			Long userIdEntity = entity.getUserId();
			Long userId = profession.getUserId();
			if(userIdEntity.equals(userId)) {
				profession.setSuccess(CoeOrder.SUCCESS_NO);
				if(type==1) {
					profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_PRAISE_USER_SELF_NOALLOW);
					profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_PRAISE_USER_SELF_NOALLOW);
				}else {
					profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_FAVIRATE_USER_SELF_NOALLOW);
					profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_FAVIRATE_USER_SELF_NOALLOW);
				}
				
				return false;
			}
		}
		return true;
	}
	
	public boolean validateExist(Profession profession) {
		return validateExist(profession,false,0);
	}
	
	public boolean validateExistForFavirate(Profession profession) {
		return validateExist(profession,true,2);
	}
	
	public boolean validateExistForPraise(Profession profession) {
		return validateExist(profession,true,1);
	}
	
	public boolean validateForEditQuickly(Errors errors, Profession profession) {
		boolean existChecked = validateExist(profession);
		if(!existChecked) {
			return false;
		}
		
		boolean checked = validateNameAndTitle(profession);
		if(!checked) {
			return false;
		}
		
		return true;
	}
	
	
	public boolean validateForPublish(Errors errors, Profession profession) {
		boolean existChecked = validateExist(profession);
		if(!existChecked) {
			return false;
		}
		
		boolean checked = validateBaseinfo(errors, profession);
		if(!checked) {
			return false;
		}
		
		Long id = profession.getId();
		Profession entity = professionWrapper.findByIdWithCoverAndBanner(id);
		
		
		
		Integer publishStatus = entity.getPublishStatus();
		if(Profession.PUBLISH_STATUS_PUBLISH.getValueInt().equals(publishStatus)) {
			profession.setSuccess(Profession.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_MERGE_HAS_PUBLISHED);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_MERGE_HAS_PUBLISHED);
			return false;
		}
		
		List<PhotoCover> coverList = entity.getCoverList();
		if(null == coverList || coverList.isEmpty()) {
			profession.setSuccess(Profession.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_COVER_EMPTY);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_COVER_EMPTY);
			return false;
		}
		
		List<CategProfession> categoryList = categprofessionWrapper.findByMainId(id);
		if(null == categoryList || categoryList.isEmpty()) {
			profession.setSuccess(Profession.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_CATEGORY_EMPTY);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_CATEGORY_EMPTY);
			return false;
		}
		
		return true;
	}
	
	public boolean validateForClosed(Errors errors, Profession profession) {
		boolean existChecked = validateExist(profession);
		if(!existChecked) {
			return false;
		}
		
		Long id = profession.getId();
		Profession entity = professionWrapper.findById(id);
		
		Long userId = profession.getUserId();
		Long userIdEntity = entity.getUserId();
		if(!userIdEntity.equals(userId)) {
			profession.setSuccess(Profession.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_NOT_USER_SELF);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_NOT_USER_SELF);
			return false;
		}
		
		Integer publishStatus = entity.getPublishStatus();
		if(Profession.PUBLISH_STATUS_CLOSED.getValueInt().equals(publishStatus)) {
			profession.setSuccess(Profession.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_MERGE_HAS_CLOSED);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_MERGE_HAS_CLOSED);
			return false;
		}
		
		return true;
	}
	
	public boolean validateForRefresh(Errors errors, Profession profession) {
		boolean existChecked = validateExist(profession);
		if (!existChecked) {
			return false;
		}
		
		Long id = profession.getId();
		Profession entity = professionWrapper.findByIdOnly(id);
		Long userId= profession.getUserId();
		Long userIdEntity = entity.getUserId();
		if(!userIdEntity.equals(userId)) {
			profession.setSuccess(Profession.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_NOT_USER_SELF);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_NOT_USER_SELF);
			return false;
		}
		
		Date refreshTime = entity.getRefreshTime();//刷新时间
		Date publishTime = entity.getPublishTime();//初始发布时间
		Date currentTime = new Date();//当前时间
		if(null != refreshTime && org.jit8j.core.util.DateUtils.daysBetween(refreshTime, currentTime)<7) {
			profession.setSuccess(Profession.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_REFRESH_INVALID);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_REFRESH_INVALID);
			return false;
		}
		
		if(null != publishTime && org.jit8j.core.util.DateUtils.daysBetween(publishTime, currentTime)>365*2) {
			profession.setSuccess(Profession.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_REFRESH_EXTRA);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_REFRESH_EXTRA);
			return false;
		}
		return true;
	}
	
	public boolean validateForPublished(Errors errors, Profession profession) {
		boolean existChecked = validateExist(profession);
		if (!existChecked) {
			return false;
		}
		
		Long id = profession.getId();
		Profession entity = professionWrapper.findByIdOnly(id);
		Long userId= profession.getUserId();
		Long userIdEntity = entity.getUserId();
		if(!userIdEntity.equals(userId)) {
			profession.setSuccess(Profession.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_NOT_USER_SELF);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_NOT_USER_SELF);
			return false;
		}
		return true;
	}
	
	public boolean validateForMainType(Errors errors, Profession profession) {
		boolean existChecked = validateExist(profession);
		if (!existChecked) {
			return false;
		}
		
		Long id = profession.getId();
		Profession entity = professionWrapper.findByIdOnly(id);
		Long userId= profession.getUserId();
		Long userIdEntity = entity.getUserId();
		if(!userIdEntity.equals(userId)) {
			profession.setSuccess(Profession.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_NOT_USER_SELF);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_NOT_USER_SELF);
			return false;
		}
		return true;
	}
	
	public boolean validateForDeleted(Errors errors, Profession profession) {
		boolean existChecked = validateExist(profession);
		if(!existChecked) {
			return false;
		}
		
		Long id = profession.getId();
		Profession entity = professionWrapper.findById(id);
		
		Long userId = profession.getUserId();
		Long userIdEntity = entity.getUserId();
		if(!userIdEntity.equals(userId)) {
			profession.setSuccess(Profession.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_NOT_USER_SELF);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_NOT_USER_SELF);
			return false;
		}
		
		Integer publishStatus = entity.getPublishStatus();
		if(Profession.PUBLISH_STATUS_CLOSED.getValueInt().equals(publishStatus)) {
			profession.setSuccess(Profession.SUCCESS_NO);
			profession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_MERGE_HAS_CLOSED);
			profession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_MERGE_HAS_CLOSED);
			return false;
		}
		
		return true;
	}

}
