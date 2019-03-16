package dml.sz0099.course.app.client.validator.product;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.persist.entity.product.bo.CoeProductBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeProductValidator 校验组件
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
public class CoeProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CoeProductBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CoeProductBo coeProduct = (CoeProductBo) target;

		validateUuid(errors, coeProduct.getUuid());

	}

	/**
	 * CoeProduct validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.product.CoeProduct.uuid.exist", "UUID不能为空");
		}
	}

}
