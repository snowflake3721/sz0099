package dml.sz0099.course.app.client.validator.article;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.article.CoeArticlePositionWrapper;
import dml.sz0099.course.app.module.define.CoeArticleRespCode;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePosition;
import dml.sz0099.course.app.persist.entity.article.bo.CoeArticlePositionBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeArticlePositionValidator 校验组件
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
public class CoeArticlePositionValidator implements Validator {
	
	@Autowired
	private CoeArticleValidator coeArticleValidator;
	
	@Autowired
	private CoeArticlePositionWrapper coeArticlePositionWrapper;

	@Override
	public boolean supports(Class<?> clazz) {
		return CoeArticlePositionBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CoeArticlePositionBo coeArticlePosition = (CoeArticlePositionBo) target;

		validateUuid(errors, coeArticlePosition.getUuid());

	}

	/**
	 * CoeArticlePosition validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.article.CoeArticlePosition.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validatePosition(CoeArticlePosition articlePosition) {
		Long userId = articlePosition.getOwnerId();
		
		if (null == userId) {
			articlePosition.setSuccess(CoeArticle.SUCCESS_NO);
			articlePosition.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_FAVIRATE_USER_NOT_LOGIN);
			articlePosition.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_FAVIRATE_USER_NOT_LOGIN);
			return false;
		}
		
		CoeArticle coeArticle = new CoeArticle();
		coeArticle.setId(articlePosition.getMainId());
		coeArticle.setUserId(userId);
		boolean existed = coeArticleValidator.validateExistForPosition(coeArticle);
		if(!existed) {
			articlePosition.setSuccess(CoeArticle.SUCCESS_NO);
			articlePosition.setRespCode(coeArticle.getRespCode());
			articlePosition.setRespMsg(coeArticle.getRespMsg());
			return false;
		}
		
		Long mainId = articlePosition.getMainId();
		boolean hasPaised = coeArticlePositionWrapper.hasPositionByMainIdAndUserId(mainId, userId);
		if(hasPaised) {
			articlePosition.setSuccess(CoeArticle.SUCCESS_NO);
			articlePosition.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_FAVIRATE_EXIST);
			articlePosition.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_FAVIRATE_EXIST);
			return false;
		}
		
		return true;
	}

}
