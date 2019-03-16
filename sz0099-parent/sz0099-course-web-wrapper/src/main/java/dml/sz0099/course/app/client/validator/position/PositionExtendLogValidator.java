package dml.sz0099.course.app.client.validator.position;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.persist.entity.position.bo.PositionExtendLogBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * PositionExtendLogValidator 校验组件
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
public class PositionExtendLogValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PositionExtendLogBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		PositionExtendLogBo positionExtendLog = (PositionExtendLogBo) target;

		validateUuid(errors, positionExtendLog.getUuid());

	}

	/**
	 * PositionExtendLog validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.position.PositionExtendLog.uuid.exist", "UUID不能为空");
		}
	}

}
