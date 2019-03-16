package dml.sz0099.course.app.client.validator.article;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.article.CoeArticleWrapper;
import dml.sz0099.course.app.client.wrapper.article.CoeCategArticleWrapper;
import dml.sz0099.course.app.module.define.CoeArticleRespCode;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.CoeCategArticle;
import dml.sz0099.course.app.persist.entity.article.PhotoCover;
import dml.sz0099.course.app.persist.entity.article.bo.CoeArticleBo;
import dml.sz0099.course.app.persist.entity.profession.Profession;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CourseProductValidator 校验组件
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
public class CoeArticleValidator implements Validator {

	
	@Autowired
	private CoeArticleWrapper coeArticleWrapper;
	
	@Autowired
	private CoeCategArticleWrapper coeCategArticleWrapper;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return CoeArticleBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CoeArticleBo courseProduct = (CoeArticleBo) target;

		validateUuid(errors, courseProduct.getUuid());

	}

	/**
	 * CourseProduct validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.article.CourseProduct.uuid.exist", "UUID不能为空");
		}
	}
	
	
	public boolean validateCoeArticleUserBuy(Errors errors, CoeArticle coeArticle) {

		if (null == coeArticle) {
			coeArticle = new CoeArticle();
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_BUY_NOT_EXIST);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_BUY_NOT_EXIST);
			return false;
		}
		
		Long userId = coeArticle.getUserId();
		if(null == userId) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_ORDER_NOT_LOGIN);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_ORDER_NOT_LOGIN);
			return false;
		}
		
		Long id = coeArticle.getId();
		CoeArticle entity = coeArticleWrapper.findByIdOnly(id);
		
		if(null == entity) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_BUY_NOT_EXIST);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_BUY_NOT_EXIST);
			return false;
		}
		
		Long userIdEntity = entity.getUserId();
		if(userIdEntity.equals(userId)) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_BUY_USER_SELF);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_BUY_USER_SELF);
			return false;
		}
		
		Integer published = entity.getPublishStatus();
		if(!CoeArticle.PUBLISH_STATUS_PUBLISH.getValueInt().equals(published)) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_BUY_SHELVED_NO);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_BUY_SHELVED_NO);
			return false;
		}
		
		boolean deleted = entity.getDeleted();
		if(deleted) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_BUY_HAS_DELTETED);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_BUY_HAS_DELTETED);
			return false;
		}
		return true;

	}
	
	public boolean validateCoeArticle(Errors errors, CoeArticle coeArticle) {
		
		if(null == coeArticle) {
			coeArticle = new CoeArticle(); 
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_NOT_EXIST);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_NOT_EXIST);
			return false;
		}
		
		Integer shelved = coeArticle.getPublishStatus();
		if(!CoeArticle.PUBLISH_STATUS_PUBLISH.getValueInt().equals(shelved)) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_SHELVED_NO);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_SHELVED_NO);
			return false;
		}
		
		boolean deleted = coeArticle.getDeleted();
		if(deleted) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_HAS_DELTETED);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_HAS_DELTETED);
			return false;
		}
		
		Integer publishStatus = coeArticle.getPublishStatus();
		if(!CoeArticle.PUBLISH_STATUS_PUBLISH.getValueInt().equals(publishStatus)) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_NOT_PUBLISH);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_NOT_PUBLISH);
			return false;
		}
		
		return true;
	}
	
	public boolean validateCoeArticleForPreOrder(Errors errors, CoeArticle coeArticle) {
		boolean checked = validateCoeArticle( errors,  coeArticle);
		if(!checked) {
			return false;
		}
		return true;
	}
	
	public Long countDraftList(CoeArticle coeArticle) {
		Long draftNum = coeArticleWrapper.countDraftList(coeArticle);
		if (null != draftNum && draftNum > 5) {
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_DRAFT_EXTRA);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_DRAFT_EXTRA);
		}
		return draftNum;
	}
	

	public boolean validateBaseinfo(Errors errors, CoeArticle coeArticle) {
		boolean existChecked = validateExist(coeArticle);
		if(!existChecked) {
			return false;
		}
		
		boolean checked = validateName(coeArticle);
		if(!checked) {
			return false;
		}
		
		 checked = validateTitle(coeArticle);
		if(!checked) {
			return false;
		}
		
		checked = validateDescription(coeArticle);
		if(!checked) {
			return false;
		}
		
		Long userId = coeArticle.getLastModifiedBy();
		if(null==userId) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_USER_NOT_LOGIN);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_USER_NOT_LOGIN);
			return false;
		}

		
		return true;
	}

	/**
	 * @param coeArticle
	 */
	private boolean validateDescription(CoeArticle coeArticle) {
		String description = coeArticle.getDescription();
		if(StringUtils.isBlank(description)) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_DESCRIPTION_EMPTY);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_DESCRIPTION_EMPTY);
			return false;
		}
		
		if(description.length()>2048) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_DESCRIPTION_EXTRA_LENGTH);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_DESCRIPTION_EXTRA_LENGTH);
			return false;
		}
		return true;
	}

	/**
	 * @param coeArticle
	 */
	private boolean validateNameAndTitle(CoeArticle coeArticle) {
		
		boolean nameChecked = validateName(coeArticle);
		if(!nameChecked) {
			return false;
		}
			
		boolean titleChecked = validateTitle(coeArticle);
		if(!titleChecked) {
			return false;
		}
		return true;
	}

	/**
	 * @param coeArticle
	 */
	private boolean validateTitle(CoeArticle coeArticle) {
		String title = coeArticle.getTitle();
		if(StringUtils.isBlank(title)) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_TITLE_EMPTY);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_TITLE_EMPTY);
			return false;
		}
		
		if(title.length()>64) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_TITLE_EXTRA_LENGTH);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_TITLE_EXTRA_LENGTH);
			return false;
		}
		String subTitle = coeArticle.getSubTitle();
		if(StringUtils.isNotBlank(subTitle) && subTitle.length()>32) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_SUBTITLE_EXTRA_LENGTH);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_SUBTITLE_EXTRA_LENGTH);
			return false;
		}
		return true;
	}

	/**
	 * @param coeArticle
	 */
	private boolean validateName(CoeArticle coeArticle) {
		String name = coeArticle.getName();
		/*if(StringUtils.isBlank(name)) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_NAME_EMPTY);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_NAME_EMPTY);
			return false;
		}*/
		
		if(StringUtils.isNotBlank(name) && name.length()>32) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_NAME_EXTRA_LENGTH);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_NAME_EXTRA_LENGTH);
			return false;
		}
		return true;
	}

	/**
	 * @param coeArticle
	 * @return
	 */
	public boolean validateExist(CoeArticle coeArticle, boolean authorOwnCheck, int type) {
		if(null == coeArticle) {
			coeArticle = new CoeArticle(); 
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_NOT_EXIST);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_NOT_EXIST);
			return false;
		}
		
		Long id = coeArticle.getId();
		if(null == id) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_NOT_EXIST);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_NOT_EXIST);
			return false;
		}
		
		CoeArticle entity = coeArticleWrapper.findByIdOnly(id);
		
		if(entity==null) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_NOT_EXIST);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_NOT_EXIST);
			return false;
		}
		Integer status = entity.getPublishStatus();
		boolean closed = CoeArticle.PUBLISH_STATUS_CLOSED.getValueInt().equals(status);
		if(closed) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_MERGE_HAS_CLOSED);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_MERGE_HAS_CLOSED);
			return false;
		}
		
		boolean deleted = entity.getDeleted();
		if(deleted) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_MERGE_HAS_DELERED);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_MERGE_HAS_DELERED);
			return false;
		}
		
		if(authorOwnCheck) {
			Long userIdEntity = entity.getUserId();
			Long userId = coeArticle.getUserId();
			if(userIdEntity.equals(userId)) {
				coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
				if(type==1) {
				coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_PRAISE_USER_SELF_NOALLOW);
				coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_PRAISE_USER_SELF_NOALLOW);
				}else {
					coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_FAVIRATE_USER_SELF_NOALLOW);
					coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_FAVIRATE_USER_SELF_NOALLOW);
				}
				
				return false;
			}
		}
		return true;
	}
	public boolean validateExist(CoeArticle coeArticle) {
		return validateExist(coeArticle,false,0);
	}
	
	public boolean validateExistForPraise(CoeArticle coeArticle) {
		return validateExist(coeArticle,true,1);
	}
	
	public boolean validateExistForFavirate(CoeArticle coeArticle) {
		return validateExist(coeArticle,true,2);
	}
	
	public boolean validateExistForPosition(CoeArticle coeArticle) {
		return validateExist(coeArticle,false,3);
	}
	
	public boolean validateForMainType(Errors errors, CoeArticle coeArticle) {
		boolean existChecked = validateExist(coeArticle);
		if (!existChecked) {
			return false;
		}
		
		Long id = coeArticle.getId();
		CoeArticle entity = coeArticleWrapper.findByIdOnly(id);
		Long userId= coeArticle.getUserId();
		Long userIdEntity = entity.getUserId();
		if(!userIdEntity.equals(userId)) {
			coeArticle.setSuccess(Profession.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_NOT_USER_SELF);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_NOT_USER_SELF);
			return false;
		}
		return true;
	}
	
	
	public boolean validatePrice(Errors errors, CoeArticle coeArticle) {

		boolean existChecked = validateExist(coeArticle);
		if (!existChecked) {
			return false;
		}

		Long userId = coeArticle.getLastModifiedBy();
		if (null == userId) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_USER_NOT_LOGIN);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_USER_NOT_LOGIN);
			return false;
		}

		return true;
	}
	
	public boolean validateForMergeTitleOnly(CoeArticle coeArticle) {

		Long userId = coeArticle.getLastModifiedBy();
		if (null == userId) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_USER_NOT_LOGIN);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_USER_NOT_LOGIN);
			return false;
		}
		
		boolean existChecked = validateExist(coeArticle);
		if (!existChecked) {
			return false;
		}
		
		String title=coeArticle.getTitle();
		if(title.length()>64) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_TITLE_EXTRA_LENGTH);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_TITLE_EXTRA_LENGTH);
			return false;
		}

		return true;
	}
	
	public boolean validateForMergeDescriptionOnly(CoeArticle coeArticle) {

		Long userId = coeArticle.getLastModifiedBy();
		if (null == userId) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_USER_NOT_LOGIN);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_USER_NOT_LOGIN);
			return false;
		}
		
		boolean existChecked = validateExist(coeArticle);
		if (!existChecked) {
			return false;
		}
		
		String description=coeArticle.getDescription();
		if(StringUtils.isNoneBlank(description) && description.length()>2048) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_DESCRIPTION_EXTRA_LENGTH);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_DESCRIPTION_EXTRA_LENGTH);
			return false;
		}

		return true;
	}

	
	public boolean validateForPublished(Errors errors, CoeArticle coeArticle) {
		boolean existChecked = validateExist(coeArticle);
		if (!existChecked) {
			return false;
		}
		
		Long id = coeArticle.getId();
		CoeArticle entity = coeArticleWrapper.findByIdOnly(id);
		Long userId= coeArticle.getUserId();
		Long userIdEntity = entity.getUserId();
		if(!userIdEntity.equals(userId)) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_NOT_USER_SELF);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_NOT_USER_SELF);
			return false;
		}
		return true;
	}
	
	public boolean validateForRefresh(Errors errors, CoeArticle coeArticle) {
		boolean existChecked = validateExist(coeArticle);
		if (!existChecked) {
			return false;
		}
		
		Long id = coeArticle.getId();
		CoeArticle entity = coeArticleWrapper.findByIdOnly(id);
		Long userId= coeArticle.getUserId();
		Long userIdEntity = entity.getUserId();
		if(!userIdEntity.equals(userId)) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_NOT_USER_SELF);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_NOT_USER_SELF);
			return false;
		}
		
		Date refreshTime = entity.getRefreshTime();//刷新时间
		Date publishTime = entity.getPublishTime();//初始发布时间
		Date currentTime = new Date();//当前时间
		if(null != refreshTime && org.jit8j.core.util.DateUtils.daysBetween(refreshTime, currentTime)<7) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_REFRESH_INVALID);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_REFRESH_INVALID);
			return false;
		}
		
		if(null != publishTime && org.jit8j.core.util.DateUtils.daysBetween(publishTime, currentTime)>365) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_REFRESH_EXTRA);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_REFRESH_EXTRA);
			return false;
		}
		return true;
	}
	
	public boolean validateForEditQuickly(Errors errors, CoeArticle coeArticle) {
		boolean existChecked = validateExist(coeArticle);
		if(!existChecked) {
			return false;
		}
		
		boolean checked = validateNameAndTitle(coeArticle);
		if(!checked) {
			return false;
		}
		
		return true;
	}
	
	public boolean validateForPublish(Errors errors, CoeArticle coeArticle) {
		boolean existChecked = validateExist(coeArticle);
		if(!existChecked) {
			return false;
		}
		
		boolean checked = validateBaseinfo(errors, coeArticle);
		if(!checked) {
			return false;
		}
		
		Long id = coeArticle.getId();
		CoeArticle entity = coeArticleWrapper.findByIdWithCoverAndBanner(id);
		
		List<CoeCategArticle> categoryList = coeCategArticleWrapper.findByMainId(id);
		if(null == categoryList || categoryList.isEmpty()) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_CATEGORY_EMPTY);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_CATEGORY_EMPTY);
			return false;
		}
		Integer publishStatus = entity.getPublishStatus();
		if(CoeArticle.PUBLISH_STATUS_PUBLISH.getValueInt().equals(publishStatus)) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_MERGE_HAS_PUBLISHED);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_MERGE_HAS_PUBLISHED);
			return false;
		}
		
		List<PhotoCover> coverList = entity.getCoverList();
		if(null == coverList || coverList.isEmpty()) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_PUBLISHED_NO_COVER);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_PUBLISHED_NO_COVER);
			return false;
		}
		/*else if(CoeArticle.PUBLISH_STATUS_CLOSED.getValueInt().equals(publishStatus)) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_MERGE_HAS_CLOSED);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_MERGE_HAS_CLOSED);
			return false;
		}*/
		
		return true;
	}
	
	public boolean validateForClosed(Errors errors, CoeArticle coeArticle) {
		boolean existChecked = validateExist(coeArticle);
		if(!existChecked) {
			return false;
		}
		
		Long id = coeArticle.getId();
		CoeArticle entity = coeArticleWrapper.findById(id);
		
		Long userId = coeArticle.getUserId();
		Long userIdEntity = entity.getUserId();
		if(!userIdEntity.equals(userId)) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_NOT_USER_SELF);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_NOT_USER_SELF);
			return false;
		}
		
		Integer publishStatus = entity.getPublishStatus();
		if(CoeArticle.PUBLISH_STATUS_CLOSED.getValueInt().equals(publishStatus)) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_MERGE_HAS_CLOSED);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_MERGE_HAS_CLOSED);
			return false;
		}
		
		return true;
	}
	
	public boolean validateForDeleted(Errors errors, CoeArticle coeArticle) {
		boolean existChecked = validateExist(coeArticle);
		if(!existChecked) {
			return false;
		}
		
		Long id = coeArticle.getId();
		CoeArticle entity = coeArticleWrapper.findById(id);
		
		Long userId = coeArticle.getUserId();
		Long userIdEntity = entity.getUserId();
		if(!userIdEntity.equals(userId)) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_NOT_USER_SELF);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_NOT_USER_SELF);
			return false;
		}
		
		Integer publishStatus = entity.getPublishStatus();
		if(CoeArticle.PUBLISH_STATUS_CLOSED.getValueInt().equals(publishStatus)) {
			coeArticle.setSuccess(CoeArticle.SUCCESS_NO);
			coeArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_MERGE_HAS_CLOSED);
			coeArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_MERGE_HAS_CLOSED);
			return false;
		}
		
		return true;
	}

}
