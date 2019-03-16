package dml.sz0099.course.app.client.validator.activity;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.activity.CoeActivityFavirateWrapper;
import dml.sz0099.course.app.module.define.CoeActivityRespCode;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityFavirate;
import dml.sz0099.course.app.persist.entity.activity.bo.CoeActivityFavirateBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeActivityFavirateValidator 校验组件
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
public class CoeActivityFavirateValidator implements Validator {
	
	@Autowired
	private CoeActivityValidator coeActivityValidator;
	
	@Autowired
	private CoeActivityFavirateWrapper coeActivityFavirateWrapper;

	@Override
	public boolean supports(Class<?> clazz) {
		return CoeActivityFavirateBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CoeActivityFavirateBo coeActivityFavirate = (CoeActivityFavirateBo) target;

		validateUuid(errors, coeActivityFavirate.getUuid());

	}

	/**
	 * CoeActivityFavirate validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.activity.CoeActivityFavirate.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateFavirate(CoeActivityFavirate activityFavirate) {
		Long userId = activityFavirate.getUserId();
		
		if (null == userId) {
			activityFavirate.setSuccess(CoeActivity.SUCCESS_NO);
			activityFavirate.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_FAVIRATE_USER_NOT_LOGIN);
			activityFavirate.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_FAVIRATE_USER_NOT_LOGIN);
			return false;
		}
		
		CoeActivity coeActivity = new CoeActivity();
		coeActivity.setId(activityFavirate.getMainId());
		coeActivity.setUserId(userId);
		boolean existed = coeActivityValidator.validateExistForFavirate(coeActivity);
		if(!existed) {
			activityFavirate.setSuccess(CoeActivity.SUCCESS_NO);
			activityFavirate.setRespCode(coeActivity.getRespCode());
			activityFavirate.setRespMsg(coeActivity.getRespMsg());
			return false;
		}
		
		Long mainId = activityFavirate.getMainId();
		boolean hasFavirated = coeActivityFavirateWrapper.hasFaviratedByMainIdAndUserId(mainId, userId);
		if(hasFavirated) {
			activityFavirate.setSuccess(CoeActivity.SUCCESS_NO);
			activityFavirate.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_FAVIRATE_EXIST);
			activityFavirate.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_FAVIRATE_EXIST);
			return false;
		}
		
		return true;
	}

}
