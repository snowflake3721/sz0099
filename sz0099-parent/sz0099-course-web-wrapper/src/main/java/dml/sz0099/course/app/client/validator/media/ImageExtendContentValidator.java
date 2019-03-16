package dml.sz0099.course.app.client.validator.media;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.persist.entity.media.bo.ImageExtendContentBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ImageExtendContentValidator 校验组件
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
public class ImageExtendContentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ImageExtendContentBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ImageExtendContentBo imageExtendContent = (ImageExtendContentBo) target;

		validateUuid(errors, imageExtendContent.getUuid());

	}

	/**
	 * ImageExtendContent validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.media.ImageExtendContent.uuid.exist", "UUID不能为空");
		}
	}

}
