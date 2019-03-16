package dml.sz0099.course.app.client.validator.article;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.article.ParagArticleWrapper;
import dml.sz0099.course.app.client.wrapper.article.ParagraphWrapper;
import dml.sz0099.course.app.client.wrapper.article.CoeArticleWrapper;
import dml.sz0099.course.app.module.define.CoeArticleRespCode;
import dml.sz0099.course.app.persist.entity.order.CoeOrder;
import dml.sz0099.course.app.persist.entity.article.ParagArticle;
import dml.sz0099.course.app.persist.entity.article.Paragraph;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.bo.ParagArticleBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ParagArticleValidator 校验组件
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
public class ParagArticleValidator implements Validator {

	@Autowired
	private CoeArticleWrapper articleWrapper;
	
	@Autowired
	private ParagArticleWrapper paragArticleWrapper;
	
	@Autowired
	private ParagraphWrapper paragraphWrapper;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ParagArticleBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ParagArticleBo paragArticle = (ParagArticleBo) target;

		validateUuid(errors, paragArticle.getUuid());

	}

	/**
	 * ParagArticle validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.paragraph.ParagArticle.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateExistArticle(ParagArticle paragArticle) {
		
		Long articleId = paragArticle.getMainId();
		if(null == articleId) {
			paragArticle.setSuccess(CoeOrder.SUCCESS_NO);
			paragArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_PERSONAL_ARTICLE_NOT_EXIST);
			paragArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_PERSONAL_ARTICLE_NOT_EXIST);
			return false;
		}
		
		CoeArticle exist = articleWrapper.findById(articleId);
		if(null == exist) {
			paragArticle.setSuccess(CoeOrder.SUCCESS_NO);
			paragArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_PERSONAL_ARTICLE_NOT_EXIST);
			paragArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_PERSONAL_ARTICLE_NOT_EXIST);
			return false;
		}
		
		Long userId = paragArticle.getUserId();
		Long userIdEntity = exist.getUserId();
		if(userId == null) {
			paragArticle.setSuccess(CoeOrder.SUCCESS_NO);
			paragArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_USER_NOT_LOGIN);
			paragArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_USER_NOT_LOGIN);
			return false;
		}
		
		if(userIdEntity==null) {
			paragArticle.setSuccess(CoeOrder.SUCCESS_NO);
			paragArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_PARAGRAGH_PERSONAL_ARTICLE_USERID_NULL);
			paragArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_PARAGRAGH_PERSONAL_ARTICLE_USERID_NULL);
			return false;
		}
		
		if(!userId.equals(userIdEntity)) {
			paragArticle.setSuccess(CoeOrder.SUCCESS_NO);
			paragArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_PARAGRAGH_ARTICLE_USERID_CONFLICT);
			paragArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_PARAGRAGH_ARTICLE_USERID_CONFLICT);
			return false;
		}
		
		return true;
	}
	
	public boolean validateAddProduct(ParagArticle paragArticle) {
		boolean checked = validateExistArticle(paragArticle);
		if(!checked) {
			return false;
		}
		Long articleId = paragArticle.getMainId();
		Long count = paragArticleWrapper.countByMainId(articleId);
		if(null != count && count>19) {
			paragArticle.setSuccess(CoeOrder.SUCCESS_NO);
			paragArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_PARAGRAGH_TOTAL_EXTRA);
			paragArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_PARAGRAGH_TOTAL_EXTRA);
			return false;
		}
		
		return true;
	}
	
	public boolean validateMergeProduct(ParagArticle paragArticle) {
		boolean checked = validateExistArticle(paragArticle);
		if(!checked) {
			return false;
		}
		
		Long paragId = paragArticle.getParagId();
		boolean exist = paragraphWrapper.existById(paragId);
		if(!exist) {
			paragArticle.setSuccess(CoeOrder.SUCCESS_NO);
			paragArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_PARAGRAGH_NOT_EXIST);
			paragArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_PARAGRAGH_NOT_EXIST);
			return false;
		}
		
		Paragraph paragraph = paragArticle.getParagraph();
		if(null == paragraph) {
			//段落上传数据不存在
			paragArticle.setSuccess(CoeOrder.SUCCESS_NO);
			paragArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_PARAGRAGH_UPLOAD_NOT_EXIST);
			paragArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_PARAGRAGH_UPLOAD_NOT_EXIST);
			return false;
		}
		return true;
	}
	
	public boolean validateForDelete(ParagArticle paragArticle) {
		boolean checked = validateExistArticle(paragArticle);
		if(!checked) {
			return false;
		}
		
		Long paragId = paragArticle.getParagId();
		boolean exist = paragraphWrapper.existById(paragId);
		if(!exist) {
			paragArticle.setSuccess(CoeOrder.SUCCESS_NO);
			paragArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_PARAGRAGH_NOT_EXIST);
			paragArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_PARAGRAGH_NOT_EXIST);
			return false;
		}
		
		Paragraph paragraph = paragArticle.getParagraph();
		if(null == paragraph) {
			//段落上传数据不存在
			paragArticle.setSuccess(CoeOrder.SUCCESS_NO);
			paragArticle.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_PARAGRAGH_UPLOAD_NOT_EXIST);
			paragArticle.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_PARAGRAGH_UPLOAD_NOT_EXIST);
			return false;
		}
		return true;
	}
	
	public boolean validateForDeleteAll(ParagArticle paragArticle) {
		boolean checked = validateExistArticle(paragArticle);
		if(!checked) {
			return false;
		}
		return true;
	}

}
