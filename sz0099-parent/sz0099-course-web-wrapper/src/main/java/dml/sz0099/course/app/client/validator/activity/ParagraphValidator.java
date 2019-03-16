package dml.sz0099.course.app.client.validator.activity;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.activity.ParagraphWrapper;
import dml.sz0099.course.app.persist.entity.activity.bo.ParagraphBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ParagraphValidator 校验组件
 * @author bruce yang at 2018-08-29 22:40:14
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Component("activityParagraphValidator")
public class ParagraphValidator implements Validator {
	
	@Autowired
	private ParagraphWrapper activityWrapper;

	@Override
	public boolean supports(Class<?> clazz) {
		return ParagraphBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ParagraphBo activity = (ParagraphBo) target;

		validateUuid(errors, activity.getUuid());

	}

	/**
	 * Paragraph validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.activity.Paragraph.uuid.exist", "UUID不能为空");
		}
	}
	
	

}
