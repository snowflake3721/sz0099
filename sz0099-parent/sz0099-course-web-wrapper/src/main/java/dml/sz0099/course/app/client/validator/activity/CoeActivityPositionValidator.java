package dml.sz0099.course.app.client.validator.activity;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.activity.CoeActivityPositionWrapper;
import dml.sz0099.course.app.module.define.CoeActivityRespCode;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPosition;
import dml.sz0099.course.app.persist.entity.activity.bo.CoeActivityPositionBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeActivityPositionValidator 校验组件
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
public class CoeActivityPositionValidator implements Validator {
	
	@Autowired
	private CoeActivityValidator coeActivityValidator;
	
	@Autowired
	private CoeActivityPositionWrapper coeActivityPositionWrapper;

	@Override
	public boolean supports(Class<?> clazz) {
		return CoeActivityPositionBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CoeActivityPositionBo coeActivityPosition = (CoeActivityPositionBo) target;

		validateUuid(errors, coeActivityPosition.getUuid());

	}

	/**
	 * CoeActivityPosition validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.activity.CoeActivityPosition.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validatePosition(CoeActivityPosition activityPosition) {
		Long userId = activityPosition.getOwnerId();
		
		if (null == userId) {
			activityPosition.setSuccess(CoeActivity.SUCCESS_NO);
			activityPosition.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_FAVIRATE_USER_NOT_LOGIN);
			activityPosition.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_FAVIRATE_USER_NOT_LOGIN);
			return false;
		}
		
		CoeActivity coeActivity = new CoeActivity();
		coeActivity.setId(activityPosition.getMainId());
		coeActivity.setUserId(userId);
		boolean existed = coeActivityValidator.validateExistForPosition(coeActivity);
		if(!existed) {
			activityPosition.setSuccess(CoeActivity.SUCCESS_NO);
			activityPosition.setRespCode(coeActivity.getRespCode());
			activityPosition.setRespMsg(coeActivity.getRespMsg());
			return false;
		}
		
		Long mainId = activityPosition.getMainId();
		boolean hasPaised = coeActivityPositionWrapper.hasPositionByMainIdAndUserId(mainId, userId);
		if(hasPaised) {
			activityPosition.setSuccess(CoeActivity.SUCCESS_NO);
			activityPosition.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_FAVIRATE_EXIST);
			activityPosition.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_FAVIRATE_EXIST);
			return false;
		}
		
		return true;
	}

}
