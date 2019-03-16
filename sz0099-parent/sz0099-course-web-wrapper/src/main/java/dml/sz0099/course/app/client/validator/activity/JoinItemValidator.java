package dml.sz0099.course.app.client.validator.activity;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.activity.CoeActivityTimeWrapper;
import dml.sz0099.course.app.client.wrapper.activity.JoinItemWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeUserGradeWrapper;
import dml.sz0099.course.app.module.define.CoeActivityRespCode;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTime;
import dml.sz0099.course.app.persist.entity.activity.JoinItem;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * JoinItemValidator 校验组件
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
public class JoinItemValidator implements Validator {
	
	@Autowired
	private JoinItemWrapper joinItemWrapper;
	
	@Autowired
	private CoeActivityTimeWrapper coeActivityTimeWrapper;
	
	@Autowired
	private CoeUserGradeWrapper coeUserGradeWrapper;

	@Override
	public boolean supports(Class<?> clazz) {
		return JoinItem.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		JoinItem joinItem = (JoinItem) target;

		validateUuid(errors, joinItem.getUuid());

	}

	/**
	 * JoinItem validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.product.JoinItem.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateMergeJoinTime(JoinItem joinItem) {
		Date joinTime = joinItem.getJoinTime();
		if(null == joinTime) {
			joinItem.setSuccess(JoinItem.SUCCESS_NO);
			joinItem.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_JOINITEM_JOINTIME_EMPTY);
			joinItem.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_JOINITEM_JOINTIME_EMPTY);
			return false;
		}
		
		boolean checked = validateExist(joinItem);
		if(!checked) {
			return false;
		}
		Long id = joinItem.getId();
		JoinItem entity = joinItemWrapper.findById(id);
		if(null == entity) {
			joinItem.setSuccess(JoinItem.SUCCESS_NO);
			joinItem.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_JOINITEM_ID_NOT_EXIST);
			joinItem.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_JOINITEM_ID_NOT_EXIST);
			return false;
		}
		return true;
	}
	
	public boolean validateMergePlace(JoinItem joinItem) {
		String place = joinItem.getPlace();
		if(StringUtils.isBlank(place)) {
			joinItem.setSuccess(JoinItem.SUCCESS_NO);
			joinItem.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_JOINITEM_PLACE_EMPTY);
			joinItem.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_JOINITEM_PLACE_EMPTY);
			return false;
		}
		
		if(place.length()>32) {
			joinItem.setSuccess(JoinItem.SUCCESS_NO);
			joinItem.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_JOINITEM_PLACE_LENGTH_EXTRA);
			joinItem.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_JOINITEM_PLACE_LENGTH_EXTRA);
			return false;
		}
		
		boolean checked = validateExist(joinItem);
		if(!checked) {
			return false;
		}
		Long id = joinItem.getId();
		JoinItem entity = joinItemWrapper.findById(id);
		if(null == entity) {
			joinItem.setSuccess(JoinItem.SUCCESS_NO);
			joinItem.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_JOINITEM_ID_NOT_EXIST);
			joinItem.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_JOINITEM_ID_NOT_EXIST);
			return false;
		}
		return true;
	}
	
	public boolean validateMergeDescription(JoinItem joinItem) {
		String description = joinItem.getDescription();
		if(StringUtils.isNotBlank(description) && description.length()>512) {
			joinItem.setSuccess(JoinItem.SUCCESS_NO);
			joinItem.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_JOINITEM_DESCRIPTION_LENGTH_EXTRA);
			joinItem.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_JOINITEM_DESCRIPTION_LENGTH_EXTRA);
			return false;
		}
		
		boolean checked = validateExist(joinItem);
		if(!checked) {
			return false;
		}
		Long id = joinItem.getId();
		JoinItem entity = joinItemWrapper.findById(id);
		if(null == entity) {
			joinItem.setSuccess(JoinItem.SUCCESS_NO);
			joinItem.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_JOINITEM_ID_NOT_EXIST);
			joinItem.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_JOINITEM_ID_NOT_EXIST);
			return false;
		}
		return true;
	}
	

	/**
	 * @param joinItem
	 */
	private boolean validateExist(JoinItem joinItem) {
		Long userId = joinItem.getLastModifiedBy();
		if(null == userId) {
			joinItem.setSuccess(JoinItem.SUCCESS_NO);
			joinItem.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_LOGIN);
			joinItem.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_USER_NOT_LOGIN);
			return false;
		}
		
		Long id=joinItem.getId();
		if(null == id) {
			joinItem.setSuccess(JoinItem.SUCCESS_NO);
			joinItem.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_TIME_ID_EMPTY);
			joinItem.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_TIME_ID_EMPTY);
			return false;
		}
		
		return true;
	}
	
	public boolean validateAddJoinItem(JoinItem joinItem) {
		
		Long userId = joinItem.getCreatedBy();
		if(null == userId) {
			joinItem.setSuccess(JoinItem.SUCCESS_NO);
			joinItem.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_LOGIN);
			joinItem.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_USER_NOT_LOGIN);
			return false;
		}
		
		Long mainId = joinItem.getMainId();
		Long baseId=joinItem.getBaseId();
		if(null == mainId) {
			joinItem.setSuccess(JoinItem.SUCCESS_NO);
			joinItem.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_TIME_ACTIVITYID_EMPTY);
			joinItem.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_TIME_ACTIVITYID_EMPTY);
			return false;
		}
		if(null == baseId) {
			joinItem.setSuccess(JoinItem.SUCCESS_NO);
			joinItem.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_TIME_ID_EMPTY);
			joinItem.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_TIME_ID_EMPTY);
			return false;
		}
		
		
		CoeActivityTime  entity = coeActivityTimeWrapper.findById(baseId);
		if(null == entity) {
			joinItem.setSuccess(JoinItem.SUCCESS_NO);
			joinItem.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_TIME_ID_NOT_EXIST);
			joinItem.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_TIME_ID_NOT_EXIST);
			return false;
		}
		
		return true;
	}
	
	public boolean validateDeleteJoinItem( JoinItem joinItem) {
			
			Long userId = joinItem.getLastModifiedBy();
			if(null == userId) {
				joinItem.setSuccess(JoinItem.SUCCESS_NO);
				joinItem.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_LOGIN);
				joinItem.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_USER_NOT_LOGIN);
				return false;
			}
			
			
			Long pTimeId = joinItem.getId();
			if(null == pTimeId) {
				joinItem.setSuccess(JoinItem.SUCCESS_NO);
				joinItem.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_TIME_ID_EMPTY);
				joinItem.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_TIME_ID_EMPTY);
				return false;
			}
			
			Long mainId = joinItem.getMainId();
			if(null == mainId) {
				joinItem.setSuccess(JoinItem.SUCCESS_NO);
				joinItem.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_TIME_ACTIVITYID_EMPTY);
				joinItem.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_TIME_ACTIVITYID_EMPTY);
				return false;
			}
			
			return true;
	}

	
}
