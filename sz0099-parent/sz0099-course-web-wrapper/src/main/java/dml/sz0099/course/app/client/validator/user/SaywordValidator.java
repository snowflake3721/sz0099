package dml.sz0099.course.app.client.validator.user;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.Sayword;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * SaywordValidator 校验组件
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
public class SaywordValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Sayword.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Sayword sayword = (Sayword) target;

		validateUuid(errors, sayword.getUuid());

	}

	/**
	 * Sayword validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.product.Sayword.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateForModifySayword(Errors errors, Sayword sayword) {
		String description = sayword.getDescription();
		
		if (StringUtils.isBlank(description)) {
			sayword.setSuccess(CoeUser.SUCCESS_NO);
			sayword.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_SAYWORD_EMPTY);
			sayword.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_SAYWORD_EMPTY);
			return false;
		}
		
		Long userId = sayword.getUserId();
		if (userId == null) {
			sayword.setSuccess(CoeUser.SUCCESS_NO);
			sayword.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USERID_EMPTY);
			sayword.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USERID_EMPTY);
			return false;
		}
		return true;
	}

}
