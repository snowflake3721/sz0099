package dml.sz0099.course.app.client.validator.activity;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.activity.CoeActivityTimeWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeUserGradeWrapper;
import dml.sz0099.course.app.module.define.CoeActivityRespCode;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTime;
import dml.sz0099.course.app.persist.entity.order.CoeOrder;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeActivityTimeValidator 校验组件
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
public class CoeActivityTimeValidator implements Validator {
	
	@Autowired
	private CoeActivityTimeWrapper coeActivityTimeWrapper;
	
	@Autowired
	private CoeUserGradeWrapper coeUserGradeWrapper;

	@Override
	public boolean supports(Class<?> clazz) {
		return CoeActivityTime.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CoeActivityTime coeActivityTime = (CoeActivityTime) target;

		validateUuid(errors, coeActivityTime.getUuid());

	}

	/**
	 * CoeActivityTime validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.product.CoeActivityTime.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateMergeBeginTime(CoeActivityTime coeActivityTime) {
		Date beginTime = coeActivityTime.getBeginTime();
		if(null == beginTime) {
			coeActivityTime.setSuccess(CoeActivityTime.SUCCESS_NO);
			coeActivityTime.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_TIME_BEGINTIME_EMPTY);
			coeActivityTime.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_TIME_BEGINTIME_EMPTY);
			return false;
		}
		
		boolean checked = validateExist(coeActivityTime);
		if(!checked) {
			return false;
		}
		Long id = coeActivityTime.getId();
		CoeActivityTime entity = coeActivityTimeWrapper.findById(id);
		if(null == entity) {
			coeActivityTime.setSuccess(CoeActivityTime.SUCCESS_NO);
			coeActivityTime.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_TIME_ID_NOT_EXIST);
			coeActivityTime.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_TIME_ID_NOT_EXIST);
			return false;
		}
		return true;
	}
	
	public boolean validateMergeEndTime(CoeActivityTime coeActivityTime) {
		Date endTime = coeActivityTime.getEndTime();
		if(null == endTime) {
			coeActivityTime.setSuccess(CoeActivityTime.SUCCESS_NO);
			coeActivityTime.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_TIME_ENDTIME_EMPTY);
			coeActivityTime.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_TIME_ENDTIME_EMPTY);
			return false;
		}
		
		boolean checked = validateExist(coeActivityTime);
		if(!checked) {
			return false;
		}
		Long id = coeActivityTime.getId();
		CoeActivityTime entity = coeActivityTimeWrapper.findById(id);
		if(null == entity) {
			coeActivityTime.setSuccess(CoeActivityTime.SUCCESS_NO);
			coeActivityTime.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_TIME_ID_NOT_EXIST);
			coeActivityTime.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_TIME_ID_NOT_EXIST);
			return false;
		}
		return true;
	}
	
	public boolean validateMergeOffTime(CoeActivityTime coeActivityTime) {
		Date offTime = coeActivityTime.getOffTime();
		if(null == offTime) {
			coeActivityTime.setSuccess(CoeActivityTime.SUCCESS_NO);
			coeActivityTime.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_TIME_OFFTIME_EMPTY);
			coeActivityTime.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_TIME_OFFTIME_EMPTY);
			return false;
		}
		
		boolean checked = validateExist(coeActivityTime);
		if(!checked) {
			return false;
		}
		Long id = coeActivityTime.getId();
		CoeActivityTime entity = coeActivityTimeWrapper.findById(id);
		if(null == entity) {
			coeActivityTime.setSuccess(CoeActivityTime.SUCCESS_NO);
			coeActivityTime.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_TIME_ID_NOT_EXIST);
			coeActivityTime.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_TIME_ID_NOT_EXIST);
			return false;
		}
		
		return true;
	}

	/**
	 * @param coeActivityTime
	 */
	private boolean validateExist(CoeActivityTime coeActivityTime) {
		Long userId = coeActivityTime.getLastModifiedBy();
		if(null == userId) {
			coeActivityTime.setSuccess(CoeActivityTime.SUCCESS_NO);
			
			coeActivityTime.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_LOGIN);
			coeActivityTime.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_USER_NOT_LOGIN);
			return false;
		}
		
		
		Long id=coeActivityTime.getId();
		if(null == id) {
			coeActivityTime.setSuccess(CoeActivityTime.SUCCESS_NO);
			coeActivityTime.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_TIME_ID_EMPTY);
			coeActivityTime.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_TIME_ID_EMPTY);
			return false;
		}
		
		return true;
	}
	
	public boolean validateAddActivityTime(CoeActivityTime coeActivityTime) {
		
		Long userId = coeActivityTime.getCreatedBy();
		if(null == userId) {
			coeActivityTime.setSuccess(CoeOrder.SUCCESS_NO);
			coeActivityTime.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_LOGIN);
			coeActivityTime.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_USER_NOT_LOGIN);
			return false;
		}
		
		Long mainId = coeActivityTime.getMainId();
		if(null == mainId) {
			coeActivityTime.setSuccess(CoeOrder.SUCCESS_NO);
			coeActivityTime.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_TIME_ACTIVITYID_EMPTY);
			coeActivityTime.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_TIME_ACTIVITYID_EMPTY);
			return false;
		}
		
		CoeActivityTime  entity = coeActivityTimeWrapper.findByMainId(coeActivityTime);
		if(null == entity) {
			coeActivityTime.setSuccess(CoeOrder.SUCCESS_NO);
			coeActivityTime.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_TIME_ACTIVITY_NOT_EXIST);
			coeActivityTime.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_TIME_ACTIVITY_NOT_EXIST);
			return false;
		}
		
		return true;
	}
	
	public boolean validateDeleteActivityTime( CoeActivityTime coeActivityTime) {
			
			Long userId = coeActivityTime.getCreatedBy();
			if(null == userId) {
				coeActivityTime.setSuccess(CoeOrder.SUCCESS_NO);
				coeActivityTime.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_LOGIN);
				coeActivityTime.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_USER_NOT_LOGIN);
				return false;
			}
			
			Long pTimeId = coeActivityTime.getId();
			if(null == pTimeId) {
				coeActivityTime.setSuccess(CoeOrder.SUCCESS_NO);
				coeActivityTime.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_TIME_ID_EMPTY);
				coeActivityTime.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_TIME_ID_EMPTY);
				return false;
			}
			
			Long mainId = coeActivityTime.getMainId();
			if(null == mainId) {
				coeActivityTime.setSuccess(CoeOrder.SUCCESS_NO);
				coeActivityTime.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_TIME_ACTIVITYID_EMPTY);
				coeActivityTime.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_TIME_ACTIVITYID_EMPTY);
				return false;
			}
			
			return true;
	}

	
}
