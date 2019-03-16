package dml.sz0099.course.app.client.validator.activity;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.activity.CoeActivityPraiseWrapper;
import dml.sz0099.course.app.module.define.CoeActivityRespCode;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPraise;
import dml.sz0099.course.app.persist.entity.activity.bo.CoeActivityPraiseBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeActivityPraiseValidator 校验组件
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
public class CoeActivityPraiseValidator implements Validator {
	
	@Autowired
	private CoeActivityValidator coeActivityValidator;
	
	@Autowired
	private CoeActivityPraiseWrapper coeActivityPraiseWrapper;

	@Override
	public boolean supports(Class<?> clazz) {
		return CoeActivityPraiseBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CoeActivityPraiseBo coeActivityPraise = (CoeActivityPraiseBo) target;

		validateUuid(errors, coeActivityPraise.getUuid());

	}

	/**
	 * CoeActivityPraise validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.activity.CoeActivityPraise.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateForDoPraise(CoeActivityPraise activityPraise) {
		Long userId = activityPraise.getUserId();
		
		if (null == userId) {
			activityPraise.setSuccess(CoeActivity.SUCCESS_NO);
			activityPraise.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_PRAISE_USER_NOT_LOGIN);
			activityPraise.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_PRAISE_USER_NOT_LOGIN);
			return false;
		}
		
		CoeActivity coeActivity = new CoeActivity();
		coeActivity.setId(activityPraise.getMainId());
		coeActivity.setUserId(userId);
		boolean existed = coeActivityValidator.validateExistForPraise(coeActivity);
		if(!existed) {
			activityPraise.setSuccess(CoeActivity.SUCCESS_NO);
			activityPraise.setRespCode(coeActivity.getRespCode());
			activityPraise.setRespMsg(coeActivity.getRespMsg());
			return false;
		}
		
		/*Long mainId = activityPraise.getMainId();
		boolean hasPaised = coeActivityPraiseWrapper.hasPraisedByMainIdAndUserId(mainId, userId);
		if(hasPaised) {
			activityPraise.setSuccess(CoeActivity.SUCCESS_NO);
			activityPraise.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_PRAISE_EXIST);
			activityPraise.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_PRAISE_EXIST);
			return false;
		}*/
		
		return true;
	}
	
	public boolean validateForPraise(CoeActivityPraise activityPraise) {
		Long userId = activityPraise.getUserId();
		
		if (null == userId) {
			activityPraise.setSuccess(CoeActivity.SUCCESS_NO);
			activityPraise.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_PRAISE_USER_NOT_LOGIN);
			activityPraise.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_PRAISE_USER_NOT_LOGIN);
			return false;
		}
		
		CoeActivity coeActivity = new CoeActivity();
		coeActivity.setId(activityPraise.getMainId());
		coeActivity.setUserId(userId);
		boolean existed = coeActivityValidator.validateExistForPraise(coeActivity);
		if(!existed) {
			activityPraise.setSuccess(CoeActivity.SUCCESS_NO);
			activityPraise.setRespCode(coeActivity.getRespCode());
			activityPraise.setRespMsg(coeActivity.getRespMsg());
			return false;
		}
		
		Long mainId = activityPraise.getMainId();
		CoeActivityPraise entity = coeActivityPraiseWrapper.findByMainIdAndUserId(mainId, userId);
		if(null != entity) {
			activityPraise.setWord(entity.getWord());
			activityPraise.setId(entity.getId());
			activityPraise.setSuccess(CoeActivity.SUCCESS_YES);
			activityPraise.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_PRAISE_EXIST);
			activityPraise.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_PRAISE_EXIST);
			return true;
		}
		
		return true;
	}

}
