package dml.sz0099.course.app.client.validator.article;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.article.CoeArticleFavirateWrapper;
import dml.sz0099.course.app.module.define.CoeArticleRespCode;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.CoeArticleFavirate;
import dml.sz0099.course.app.persist.entity.article.bo.CoeArticleFavirateBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeArticleFavirateValidator 校验组件
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
public class CoeArticleFavirateValidator implements Validator {
	
	@Autowired
	private CoeArticleValidator coeArticleValidator;
	
	@Autowired
	private CoeArticleFavirateWrapper coeArticleFavirateWrapper;

	@Override
	public boolean supports(Class<?> clazz) {
		return CoeArticleFavirateBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CoeArticleFavirateBo coeArticleFavirate = (CoeArticleFavirateBo) target;

		validateUuid(errors, coeArticleFavirate.getUuid());

	}

	/**
	 * CoeArticleFavirate validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.article.CoeArticleFavirate.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateFavirate(CoeArticleFavirate articleFavirate) {
		Long userId = articleFavirate.getUserId();
		
		if (null == userId) {
			articleFavirate.setSuccess(CoeArticle.SUCCESS_NO);
			articleFavirate.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_FAVIRATE_USER_NOT_LOGIN);
			articleFavirate.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_FAVIRATE_USER_NOT_LOGIN);
			return false;
		}
		
		CoeArticle coeArticle = new CoeArticle();
		coeArticle.setId(articleFavirate.getMainId());
		coeArticle.setUserId(userId);
		boolean existed = coeArticleValidator.validateExistForFavirate(coeArticle);
		if(!existed) {
			articleFavirate.setSuccess(CoeArticle.SUCCESS_NO);
			articleFavirate.setRespCode(coeArticle.getRespCode());
			articleFavirate.setRespMsg(coeArticle.getRespMsg());
			return false;
		}
		
		Long mainId = articleFavirate.getMainId();
		boolean hasFavirated = coeArticleFavirateWrapper.hasFaviratedByMainIdAndUserId(mainId, userId);
		if(hasFavirated) {
			articleFavirate.setSuccess(CoeArticle.SUCCESS_NO);
			articleFavirate.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_FAVIRATE_EXIST);
			articleFavirate.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_FAVIRATE_EXIST);
			return false;
		}
		
		return true;
	}

}
