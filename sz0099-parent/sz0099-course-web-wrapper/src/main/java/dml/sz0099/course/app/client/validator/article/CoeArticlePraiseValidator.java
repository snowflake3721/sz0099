package dml.sz0099.course.app.client.validator.article;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.article.CoeArticlePraiseWrapper;
import dml.sz0099.course.app.module.define.CoeArticleRespCode;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePraise;
import dml.sz0099.course.app.persist.entity.article.bo.CoeArticlePraiseBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeArticlePraiseValidator 校验组件
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
public class CoeArticlePraiseValidator implements Validator {
	
	@Autowired
	private CoeArticleValidator coeArticleValidator;
	
	@Autowired
	private CoeArticlePraiseWrapper coeArticlePraiseWrapper;

	@Override
	public boolean supports(Class<?> clazz) {
		return CoeArticlePraiseBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CoeArticlePraiseBo coeArticlePraise = (CoeArticlePraiseBo) target;

		validateUuid(errors, coeArticlePraise.getUuid());

	}

	/**
	 * CoeArticlePraise validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.article.CoeArticlePraise.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateForDoPraise(CoeArticlePraise articlePraise) {
		Long userId = articlePraise.getUserId();
		
		if (null == userId) {
			articlePraise.setSuccess(CoeArticle.SUCCESS_NO);
			articlePraise.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_PRAISE_USER_NOT_LOGIN);
			articlePraise.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_PRAISE_USER_NOT_LOGIN);
			return false;
		}
		
		CoeArticle coeArticle = new CoeArticle();
		coeArticle.setId(articlePraise.getMainId());
		coeArticle.setUserId(userId);
		boolean existed = coeArticleValidator.validateExistForPraise(coeArticle);
		if(!existed) {
			articlePraise.setSuccess(CoeArticle.SUCCESS_NO);
			articlePraise.setRespCode(coeArticle.getRespCode());
			articlePraise.setRespMsg(coeArticle.getRespMsg());
			return false;
		}
		
		/*Long mainId = articlePraise.getMainId();
		boolean hasPaised = coeArticlePraiseWrapper.hasPraisedByMainIdAndUserId(mainId, userId);
		if(hasPaised) {
			articlePraise.setSuccess(CoeArticle.SUCCESS_NO);
			articlePraise.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_PRAISE_EXIST);
			articlePraise.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_PRAISE_EXIST);
			return false;
		}*/
		
		return true;
	}
	
	public boolean validateForPraise(CoeArticlePraise articlePraise) {
		Long userId = articlePraise.getUserId();
		
		if (null == userId) {
			articlePraise.setSuccess(CoeArticle.SUCCESS_NO);
			articlePraise.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_PRAISE_USER_NOT_LOGIN);
			articlePraise.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_PRAISE_USER_NOT_LOGIN);
			return false;
		}
		
		CoeArticle coeArticle = new CoeArticle();
		coeArticle.setId(articlePraise.getMainId());
		coeArticle.setUserId(userId);
		boolean existed = coeArticleValidator.validateExistForPraise(coeArticle);
		if(!existed) {
			articlePraise.setSuccess(CoeArticle.SUCCESS_NO);
			articlePraise.setRespCode(coeArticle.getRespCode());
			articlePraise.setRespMsg(coeArticle.getRespMsg());
			return false;
		}
		
		Long mainId = articlePraise.getMainId();
		CoeArticlePraise entity = coeArticlePraiseWrapper.findByMainIdAndUserId(mainId, userId);
		if(null != entity) {
			articlePraise.setWord(entity.getWord());
			articlePraise.setId(entity.getId());
			articlePraise.setSuccess(CoeArticle.SUCCESS_YES);
			articlePraise.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_PRAISE_EXIST);
			articlePraise.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_PRAISE_EXIST);
			return true;
		}
		
		return true;
	}

}
