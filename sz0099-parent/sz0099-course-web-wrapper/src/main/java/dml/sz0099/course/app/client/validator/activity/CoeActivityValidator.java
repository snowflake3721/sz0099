package dml.sz0099.course.app.client.validator.activity;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.activity.CoeActivityWrapper;
import dml.sz0099.course.app.client.wrapper.activity.CoeCategActivityWrapper;
import dml.sz0099.course.app.module.define.CoeActivityRespCode;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityFee;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTime;
import dml.sz0099.course.app.persist.entity.activity.CoeCategActivity;
import dml.sz0099.course.app.persist.entity.activity.PhotoCover;
import dml.sz0099.course.app.persist.entity.activity.bo.CoeActivityBo;
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
public class CoeActivityValidator implements Validator {

	@Value("${CoeActivityFee.feeType.limit.enable:false}")
	private boolean feeTypeLimit;
	@Autowired
	private CoeActivityWrapper coeActivityWrapper;
	
	@Autowired
	private CoeCategActivityWrapper coeCategActivityWrapper;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return CoeActivityBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CoeActivityBo courseProduct = (CoeActivityBo) target;

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
			errors.rejectValue("uuid", "org.jit4j.app.activity.CourseProduct.uuid.exist", "UUID不能为空");
		}
	}
	
	
	public boolean validateCoeActivityUserBuy(Errors errors, CoeActivity coeActivity) {

		if (null == coeActivity) {
			coeActivity = new CoeActivity();
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_BUY_NOT_EXIST);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_BUY_NOT_EXIST);
			return false;
		}
		
		Long userId = coeActivity.getUserId();
		if(null == userId) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_ORDER_NOT_LOGIN);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_ORDER_NOT_LOGIN);
			return false;
		}
		
		Long id = coeActivity.getId();
		CoeActivity entity = coeActivityWrapper.findByIdOnly(id);
		
		if(null == entity) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_BUY_NOT_EXIST);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_BUY_NOT_EXIST);
			return false;
		}
		
		Long userIdEntity = entity.getUserId();
		if(userIdEntity.equals(userId)) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_BUY_USER_SELF);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_BUY_USER_SELF);
			return false;
		}
		
		Integer published = entity.getPublishStatus();
		if(!CoeActivity.PUBLISH_STATUS_PUBLISH.getValueInt().equals(published)) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_BUY_SHELVED_NO);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_BUY_SHELVED_NO);
			return false;
		}
		
		boolean deleted = entity.getDeleted();
		if(deleted) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_BUY_HAS_DELTETED);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_BUY_HAS_DELTETED);
			return false;
		}
		return true;

	}
	
	public boolean validateCoeActivity(Errors errors, CoeActivity coeActivity) {
		
		if(null == coeActivity) {
			coeActivity = new CoeActivity(); 
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_NOT_EXIST);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_NOT_EXIST);
			return false;
		}
		
		Integer shelved = coeActivity.getPublishStatus();
		if(!CoeActivity.PUBLISH_STATUS_PUBLISH.getValueInt().equals(shelved)) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_SHELVED_NO);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_SHELVED_NO);
			return false;
		}
		
		boolean deleted = coeActivity.getDeleted();
		if(deleted) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_HAS_DELTETED);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_HAS_DELTETED);
			return false;
		}
		
		Integer publishStatus = coeActivity.getPublishStatus();
		if(!CoeActivity.PUBLISH_STATUS_PUBLISH.getValueInt().equals(publishStatus)) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_NOT_PUBLISH);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_NOT_PUBLISH);
			return false;
		}
		
		return true;
	}
	
	public boolean validateCoeActivityForPreOrder(Errors errors, CoeActivity coeActivity) {
		boolean checked = validateCoeActivity( errors,  coeActivity);
		if(!checked) {
			return false;
		}
		return true;
	}
	
	public Long countDraftList(CoeActivity coeActivity) {
		Long draftNum = coeActivityWrapper.countDraftList(coeActivity);
		if (null != draftNum && draftNum > 5) {
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_DRAFT_EXTRA);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_DRAFT_EXTRA);
		}
		return draftNum;
	}
	

	public boolean validateBaseinfo(Errors errors, CoeActivity coeActivity) {
		boolean existChecked = validateExist(coeActivity);
		if(!existChecked) {
			return false;
		}
		
		boolean checked = validateName(coeActivity);
		if(!checked) {
			return false;
		}
		
		 checked = validateTitle(coeActivity);
		if(!checked) {
			return false;
		}
		
		checked = validateDescription(coeActivity);
		if(!checked) {
			return false;
		}
		
		Long userId = coeActivity.getLastModifiedBy();
		if(null==userId) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_LOGIN);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_USER_NOT_LOGIN);
			return false;
		}
		
		Integer organize=coeActivity.getActOrganize();
		if(null==organize || organize==CoeActivity.ACT_ORGANIZE_NO.getValueInt()) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_ORGANIZE_EMPTY);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_ORGANIZE_EMPTY);
			return false;
		}
		
		Integer difficulty = coeActivity.getDifficulty();
		if(null != difficulty) {
			if(difficulty<0 || difficulty>99) {
				coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
				coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_DIFFICULTY_INVALID);
				coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_DIFFICULTY_INVALID);
				return false;
			}
		}

		
		return true;
	}

	/**
	 * @param coeActivity
	 */
	private boolean validateDescription(CoeActivity coeActivity) {
		String description = coeActivity.getDescription();
		if(StringUtils.isBlank(description)) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_DESCRIPTION_EMPTY);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_DESCRIPTION_EMPTY);
			return false;
		}
		
		if(description.length()>2048) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_DESCRIPTION_EXTRA_LENGTH);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_DESCRIPTION_EXTRA_LENGTH);
			return false;
		}
		return true;
	}

	/**
	 * @param coeActivity
	 */
	private boolean validateNameAndTitle(CoeActivity coeActivity) {
		
		boolean nameChecked = validateName(coeActivity);
		if(!nameChecked) {
			return false;
		}
			
		boolean titleChecked = validateTitle(coeActivity);
		if(!titleChecked) {
			return false;
		}
		return true;
	}

	/**
	 * @param coeActivity
	 */
	private boolean validateTitle(CoeActivity coeActivity) {
		String title = coeActivity.getTitle();
		if(StringUtils.isBlank(title)) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_TITLE_EMPTY);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_TITLE_EMPTY);
			return false;
		}
		
		if(title.length()>64) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_TITLE_EXTRA_LENGTH);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_TITLE_EXTRA_LENGTH);
			return false;
		}
		String subTitle = coeActivity.getSubTitle();
		if(StringUtils.isNotBlank(subTitle) && subTitle.length()>32) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_SUBTITLE_EXTRA_LENGTH);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_SUBTITLE_EXTRA_LENGTH);
			return false;
		}
		return true;
	}

	/**
	 * @param coeActivity
	 */
	private boolean validateName(CoeActivity coeActivity) {
		String name = coeActivity.getName();
		/*if(StringUtils.isBlank(name)) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_NAME_EMPTY);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_NAME_EMPTY);
			return false;
		}*/
		
		if(StringUtils.isNotBlank(name) && name.length()>32) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_NAME_EXTRA_LENGTH);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_NAME_EXTRA_LENGTH);
			return false;
		}
		return true;
	}

	/**
	 * @param coeActivity
	 * @return
	 */
	public boolean validateExist(CoeActivity coeActivity, boolean authorOwnCheck, int type) {
		if(null == coeActivity) {
			coeActivity = new CoeActivity(); 
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_NOT_EXIST);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_NOT_EXIST);
			return false;
		}
		
		Long id = coeActivity.getId();
		if(null == id) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_NOT_EXIST);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_NOT_EXIST);
			return false;
		}
		
		CoeActivity entity = coeActivityWrapper.findByIdOnly(id);
		
		if(entity==null) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_NOT_EXIST);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_NOT_EXIST);
			return false;
		}
		Integer status = entity.getPublishStatus();
		boolean closed = CoeActivity.PUBLISH_STATUS_CLOSED.getValueInt().equals(status);
		if(closed) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_MERGE_HAS_CLOSED);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_MERGE_HAS_CLOSED);
			return false;
		}
		
		boolean deleted = entity.getDeleted();
		if(deleted) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_MERGE_HAS_DELERED);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_MERGE_HAS_DELERED);
			return false;
		}
		
		if(authorOwnCheck) {
			Long userIdEntity = entity.getUserId();
			Long userId = coeActivity.getUserId();
			if(userIdEntity.equals(userId)) {
				coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
				if(type==1) {
				coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_PRAISE_USER_SELF_NOALLOW);
				coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_PRAISE_USER_SELF_NOALLOW);
				}else {
					coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_FAVIRATE_USER_SELF_NOALLOW);
					coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_FAVIRATE_USER_SELF_NOALLOW);
				}
				
				return false;
			}
		}
		return true;
	}
	public boolean validateExist(CoeActivity coeActivity) {
		return validateExist(coeActivity,false,0);
	}
	
	public boolean validateExistForPraise(CoeActivity coeActivity) {
		return validateExist(coeActivity,true,1);
	}
	
	public boolean validateExistForFavirate(CoeActivity coeActivity) {
		return validateExist(coeActivity,true,2);
	}
	
	public boolean validateExistForPosition(CoeActivity coeActivity) {
		return validateExist(coeActivity,false,3);
	}
	
	
	public boolean validateForMainType(Errors errors, CoeActivity coeActivity) {
		boolean existChecked = validateExist(coeActivity);
		if (!existChecked) {
			return false;
		}
		
		Long id = coeActivity.getId();
		CoeActivity entity = coeActivityWrapper.findByIdOnly(id);
		Long userId= coeActivity.getUserId();
		Long userIdEntity = entity.getUserId();
		if(!userIdEntity.equals(userId)) {
			coeActivity.setSuccess(Profession.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_NOT_USER_SELF);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_NOT_USER_SELF);
			return false;
		}
		return true;
	}
	
	
	public boolean validatePrice(Errors errors, CoeActivity coeActivity) {

		boolean existChecked = validateExist(coeActivity);
		if (!existChecked) {
			return false;
		}

		Long userId = coeActivity.getLastModifiedBy();
		if (null == userId) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_LOGIN);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_USER_NOT_LOGIN);
			return false;
		}

		return true;
	}

	
	public boolean validateForPublished(Errors errors, CoeActivity coeActivity) {
		boolean existChecked = validateExist(coeActivity);
		if (!existChecked) {
			return false;
		}
		
		Long id = coeActivity.getId();
		CoeActivity entity = coeActivityWrapper.findByIdOnly(id);
		Long userId= coeActivity.getUserId();
		Long userIdEntity = entity.getUserId();
		if(!userIdEntity.equals(userId)) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_NOT_USER_SELF);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_NOT_USER_SELF);
			return false;
		}
		return true;
	}
	
	public boolean validateForRefresh(Errors errors, CoeActivity coeActivity) {
		boolean existChecked = validateExist(coeActivity);
		if (!existChecked) {
			return false;
		}
		
		Long id = coeActivity.getId();
		CoeActivity entity = coeActivityWrapper.findByIdOnly(id);
		Long userId= coeActivity.getUserId();
		Long userIdEntity = entity.getUserId();
		if(!userIdEntity.equals(userId)) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_NOT_USER_SELF);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_NOT_USER_SELF);
			return false;
		}
		
		Date refreshTime = entity.getRefreshTime();//刷新时间
		Date publishTime = entity.getPublishTime();//初始发布时间
		Date currentTime = new Date();//当前时间
		if(null != refreshTime && org.jit8j.core.util.DateUtils.daysBetween(refreshTime, currentTime)<7) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_REFRESH_INVALID);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_REFRESH_INVALID);
			return false;
		}
		
		if(null != publishTime && org.jit8j.core.util.DateUtils.daysBetween(publishTime, currentTime)>365) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_REFRESH_EXTRA);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_REFRESH_EXTRA);
			return false;
		}
		return true;
	}
	
	public boolean validateForEditQuickly(Errors errors, CoeActivity coeActivity) {
		boolean existChecked = validateExist(coeActivity);
		if(!existChecked) {
			return false;
		}
		
		boolean checked = validateNameAndTitle(coeActivity);
		if(!checked) {
			return false;
		}
		
		return true;
	}
	
	public boolean validateForPublish(Errors errors, CoeActivity coeActivity) {
		boolean existChecked = validateExist(coeActivity);
		if(!existChecked) {
			return false;
		}
		
		boolean checked = validateBaseinfo(errors, coeActivity);
		if(!checked) {
			return false;
		}
		
		Long id = coeActivity.getId();
		CoeActivity entity = coeActivityWrapper.findByIdWithCoverAndBanner(id);
		
		Integer organize=entity.getActOrganize();
		if(null==organize || organize==CoeActivity.ACT_ORGANIZE_NO.getValueInt()) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_ORGANIZE_EMPTY);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_ORGANIZE_EMPTY);
			return false;
		}
		
		List<CoeCategActivity> categoryList = coeCategActivityWrapper.findByMainId(id);
		if(null == categoryList || categoryList.isEmpty()) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_CATEGORY_EMPTY);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_CATEGORY_EMPTY);
			return false;
		}
		Integer publishStatus = entity.getPublishStatus();
		if(CoeActivity.PUBLISH_STATUS_PUBLISH.getValueInt().equals(publishStatus)) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_MERGE_HAS_PUBLISHED);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_MERGE_HAS_PUBLISHED);
			return false;
		}
		
		List<PhotoCover> coverList = entity.getCoverList();
		if(null == coverList || coverList.isEmpty()) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_PUBLISHED_NO_COVER);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_PUBLISHED_NO_COVER);
			return false;
		}
		
		CoeActivityTime actTime = entity.getActTime();
		if(null == actTime) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_JOINITEM_EMPTY);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_JOINITEM_EMPTY);
			return false;
		}
		
		Date beginTime = actTime.getBeginTime();
		if(null == beginTime) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_TIME_BEGINTIME_EMPTY);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_TIME_BEGINTIME_EMPTY);
			return false;
		}
		
		Date endTime = actTime.getEndTime();
		if(null == endTime) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_TIME_ENDTIME_EMPTY);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_TIME_ENDTIME_EMPTY);
			return false;
		}
		
		Date offTime = actTime.getOffTime();
		if(null == offTime) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_TIME_OFFTIME_EMPTY);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_TIME_OFFTIME_EMPTY);
			return false;
		}
		
		if(beginTime.after(endTime)) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_TIME_BENGINTIME_GT_ENDTIME);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_TIME_BENGINTIME_GT_ENDTIME);
			return false;
		}
		
		if(offTime.after(endTime)) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_TIME_OFFTIME_GT_ENDTIME);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_TIME_OFFTIME_GT_ENDTIME);
			return false;
		}
		
		CoeActivityFee actFee = entity.getActFee();
		if(null == actFee) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_FEE_EMPTY);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_FEE_EMPTY);
			return false;
		}
		
		Integer currency = actFee.getCurrency();
		if(!CoeActivityFee.CURRENCY_RMB.getValueInt().equals(currency)) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_FEE_CURRENCY_RMB_ONLY);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_FEE_CURRENCY_RMB_ONLY);
			return false;
		}
		
		Integer feeType = actFee.getFeeType();
		if(feeType==null) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_FEE_FEETYPE_EMPTY);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_FEE_FEETYPE_EMPTY);
			return false;
		}
		
		String description = actFee.getDescription();
		if(StringUtils.isNoneBlank(description) && description.length()>1024) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_FEE_DESCRIPTION_LENGTH_EXTRA);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_FEE_DESCRIPTION_LENGTH_EXTRA);
			return false;
		}
		
		Long rmbAmount = actFee.getRmbAmount();
		if(rmbAmount!=null && rmbAmount<0) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_FEE_RMBAMOUNT_GTE_0);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_FEE_RMBAMOUNT_GTE_0);
			return false;
		}
		
		if(feeTypeLimit) {
			if(CoeActivityFee.FEETYPE_AA.getValueInt().equals(feeType)) {
				if(rmbAmount<100) {
					coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
					coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_FEE_FEETYPE_AA_LTE_100);
					coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_FEE_FEETYPE_AA_LTE_100);
					return false;
				}
			}else if(CoeActivityFee.FEETYPE_FREE.getValueInt().equals(feeType)) {
				if(rmbAmount>0) {
					coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
					coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_FEE_FEETYPE_FREE_MUST_0);
					coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_FEE_FEETYPE_FREE_MUST_0);
					return false;
				}
			}else if(CoeActivityFee.FEETYPE_SELF.getValueInt().equals(feeType)) {
				if(rmbAmount<1000) {
					coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
					coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_FEE_FEETYPE_SELF_LTE_1000);
					coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_FEE_FEETYPE_SELF_LTE_1000);
					return false;
				}
			}
		}
		
		Long rmbAmountOri = actFee.getRmbAmountOri();
		if(rmbAmountOri!=null && rmbAmountOri<0) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_FEE_RMBAMOUNTORI_GTE_0);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_FEE_RMBAMOUNTORI_GTE_0);
			return false;
		}
		/*else if(CoeActivity.PUBLISH_STATUS_CLOSED.getValueInt().equals(publishStatus)) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_MERGE_HAS_CLOSED);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_MERGE_HAS_CLOSED);
			return false;
		}*/
		
		return true;
	}
	
	public boolean validateForClosed(Errors errors, CoeActivity coeActivity) {
		boolean existChecked = validateExist(coeActivity);
		if(!existChecked) {
			return false;
		}
		
		Long id = coeActivity.getId();
		CoeActivity entity = coeActivityWrapper.findById(id);
		
		Long userId = coeActivity.getUserId();
		Long userIdEntity = entity.getUserId();
		if(!userIdEntity.equals(userId)) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_NOT_USER_SELF);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_NOT_USER_SELF);
			return false;
		}
		
		Integer publishStatus = entity.getPublishStatus();
		if(CoeActivity.PUBLISH_STATUS_CLOSED.getValueInt().equals(publishStatus)) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_MERGE_HAS_CLOSED);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_MERGE_HAS_CLOSED);
			return false;
		}
		
		return true;
	}
	
	public boolean validateForTemplate(CoeActivity coeActivity) {
		boolean existChecked = validateExist(coeActivity);
		if(!existChecked) {
			return false;
		}
		
		Long id = coeActivity.getId();
		CoeActivity entity = coeActivityWrapper.findById(id);
		
		Long userId = coeActivity.getUserId();
		Long userIdEntity = entity.getUserId();
		if(!userIdEntity.equals(userId)) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_NOT_USER_SELF);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_NOT_USER_SELF);
			return false;
		}
		
		Long maxNum=coeActivityWrapper.countTemplateForUser(userId);
		if(null != maxNum && maxNum>20) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_TEMPLATE_MAXNUM_EXTRA);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_TEMPLATE_MAXNUM_EXTRA);
			return false;
		}
		
		return true;
	}
	
	public boolean validateForDeleted(Errors errors, CoeActivity coeActivity) {
		boolean existChecked = validateExist(coeActivity);
		if(!existChecked) {
			return false;
		}
		
		Long id = coeActivity.getId();
		CoeActivity entity = coeActivityWrapper.findById(id);
		
		Long userId = coeActivity.getUserId();
		Long userIdEntity = entity.getUserId();
		if(!userIdEntity.equals(userId)) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_NOT_USER_SELF);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_NOT_USER_SELF);
			return false;
		}
		
		Integer publishStatus = entity.getPublishStatus();
		if(CoeActivity.PUBLISH_STATUS_CLOSED.getValueInt().equals(publishStatus)) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_MERGE_HAS_CLOSED);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_MERGE_HAS_CLOSED);
			return false;
		}
		
		return true;
	}
	
	public boolean validateForMergeTitleOnly(CoeActivity coeActivity) {

		Long userId = coeActivity.getLastModifiedBy();
		if (null == userId) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_LOGIN);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_USER_NOT_LOGIN);
			return false;
		}
		
		boolean existChecked = validateExist(coeActivity);
		if (!existChecked) {
			return false;
		}
		
		String title=coeActivity.getTitle();
		if(title.length()>64) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_TITLE_EXTRA_LENGTH);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_TITLE_EXTRA_LENGTH);
			return false;
		}

		return true;
	}
	
	public boolean validateForMergeDescriptionOnly(CoeActivity coeActivity) {

		Long userId = coeActivity.getLastModifiedBy();
		if (null == userId) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_LOGIN);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_USER_NOT_LOGIN);
			return false;
		}
		
		boolean existChecked = validateExist(coeActivity);
		if (!existChecked) {
			return false;
		}
		
		String description=coeActivity.getDescription();
		if(StringUtils.isNoneBlank(description) && description.length()>2048) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_DESCRIPTION_EXTRA_LENGTH);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_DESCRIPTION_EXTRA_LENGTH);
			return false;
		}

		return true;
	}
	
	public boolean validateForLoadTemplate(CoeActivity coeActivity) {
		if(null == coeActivity) {
			coeActivity = new CoeActivity(); 
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_NOT_EXIST);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_NOT_EXIST);
			return false;
		}
		
		Long id = coeActivity.getId();
		if(null == id) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_NOT_EXIST);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_NOT_EXIST);
			return false;
		}
		
		Long templateId = coeActivity.getTemplateId();
		if(null == templateId) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_TEMPLATE_ID_EMPLTY);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_TEMPLATE_ID_EMPLTY);
			return false;
		}
		CoeActivity entity = coeActivityWrapper.findByIdOnly(id);
		
		if(entity==null) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_NOT_EXIST);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_NOT_EXIST);
			return false;
		}
		
		Integer status = entity.getPublishStatus();
		boolean closed = CoeActivity.PUBLISH_STATUS_CLOSED.getValueInt().equals(status);
		if(closed) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_MERGE_HAS_CLOSED);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_MERGE_HAS_CLOSED);
			return false;
		}
		
		boolean deleted = entity.getDeleted();
		if(deleted) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_MERGE_HAS_DELERED);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_MERGE_HAS_DELERED);
			return false;
		}
		
		Long userIdEntity = entity.getUserId();
		Long userId = coeActivity.getUserId();
		if(!userIdEntity.equals(userId)) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_SELF);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_USER_NOT_SELF);
			return false;
		}
		
		CoeActivity template = coeActivityWrapper.findByIdOnly(templateId);
		if(template==null) {
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_TEMPLATE_ID_NOT_EXIST);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_TEMPLATE_ID_NOT_EXIST);
			return false;
		}
		
		Integer tpl=template.getTemplate();
		Long tplUserId = template.getUserId();
		if(null == tpl || CoeActivity.TEMPLATE_NO.getValueInt().equals(tpl)){
			coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
			coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_TEMPLATE_NOTA);
			coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_TEMPLATE_NOTA);
			return false;
		}else if(CoeActivity.TEMPLATE_USER.getValueInt().equals(tpl)) {
			if(!userId.equals(tplUserId)) {
				coeActivity.setSuccess(CoeActivity.SUCCESS_NO);
				coeActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_TEMPLATE_NOT_COMMON);
				coeActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_TEMPLATE_NOT_COMMON);
				return false;
			}
		}
		
		return true;
	}

}
