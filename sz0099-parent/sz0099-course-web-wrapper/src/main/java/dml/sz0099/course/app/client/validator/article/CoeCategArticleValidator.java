package dml.sz0099.course.app.client.validator.article;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.persist.entity.article.bo.CoeCategArticleBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeCategArticleValidator 校验组件
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
public class CoeCategArticleValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CoeCategArticleBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CoeCategArticleBo coeCategArticle = (CoeCategArticleBo) target;

		validateUuid(errors, coeCategArticle.getUuid());

	}

	/**
	 * CoeCategArticle validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.article.CoeCategArticle.uuid.exist", "UUID不能为空");
		}
	}

}
