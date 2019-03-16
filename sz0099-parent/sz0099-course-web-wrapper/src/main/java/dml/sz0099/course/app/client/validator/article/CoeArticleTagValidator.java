package dml.sz0099.course.app.client.validator.article;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.article.CoeArticleTagWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeUserGradeWrapper;
import dml.sz0099.course.app.module.define.CoeArticleRespCode;
import dml.sz0099.course.app.persist.entity.article.CoeArticleTag;
import dml.sz0099.course.app.persist.entity.article.bo.CoeArticleTagBo;
import dml.sz0099.course.app.persist.entity.order.CoeOrder;
import dml.sz0099.course.app.persist.entity.tag.CoeTag;
import dml.sz0099.course.app.persist.entity.user.CoeUserGrade;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeArticleTagValidator 校验组件
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
public class CoeArticleTagValidator implements Validator {
	
	@Autowired
	private CoeArticleTagWrapper coeArticleTagWrapper;
	
	@Autowired
	private CoeUserGradeWrapper coeUserGradeWrapper;

	@Override
	public boolean supports(Class<?> clazz) {
		return CoeArticleTagBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CoeArticleTagBo coeArticleTag = (CoeArticleTagBo) target;

		validateUuid(errors, coeArticleTag.getUuid());

	}

	/**
	 * CoeArticleTag validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.product.CoeArticleTag.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateAddArticleTag(Errors errors, CoeArticleTag coeArticleTag) {
		
		Long userId = coeArticleTag.getCreatedBy();
		if(null == userId) {
			coeArticleTag.setSuccess(CoeOrder.SUCCESS_NO);
			coeArticleTag.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_PARAGRAGH_USERID_NULL);
			coeArticleTag.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_PARAGRAGH_USERID_NULL);
			return false;
		}
		
		Long professionId = coeArticleTag.getMainId();
		if(null == professionId) {
			coeArticleTag.setSuccess(CoeOrder.SUCCESS_NO);
			coeArticleTag.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_TAG_DELETE_ARTICLEID_EMPTY);
			coeArticleTag.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_TAG_DELETE_ARTICLEID_EMPTY);
			return false;
		}
		
		String name = coeArticleTag.getName();
		coeArticleTag.setName(StringUtils.trim(name));
		if(StringUtils.isBlank(name)) {
			CoeTag tag = coeArticleTag.getTag();
			if(null == tag) {
				coeArticleTag.setSuccess(CoeOrder.SUCCESS_NO);
				coeArticleTag.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_TAG_EMPTY);
				coeArticleTag.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_TAG_EMPTY);
				return false;
			}
			name = tag.getName();
			tag.setName(StringUtils.trim(name));
			if(StringUtils.isBlank(name)) {
				coeArticleTag.setSuccess(CoeOrder.SUCCESS_NO);
				coeArticleTag.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_TAG_NAME_EMPTY);
				coeArticleTag.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_TAG_NAME_EMPTY);
				return false;
			}
		}
		
		CoeArticleTag  entity = coeArticleTagWrapper.findByMainIdAndName(coeArticleTag);
		if(null != entity) {
			coeArticleTag.setSuccess(CoeOrder.SUCCESS_NO);
			coeArticleTag.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_ARTICLE_TAG_EXIST);
			coeArticleTag.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_ARTICLE_TAG_EXIST);
			return false;
		}
		
		Long num = coeArticleTagWrapper.countByMainId(professionId);
		CoeUserGrade coeUserGrade = coeUserGradeWrapper.findByUserId(userId);
		Integer tagNumDefine = 5;
		if(null != coeUserGrade) {
			/*tagNumDefine = coeUserGrade.getCoeArticleTagNum();
			if(tagNumDefine == null) {
				tagNumDefine=3;
			}*/
		}
		if(num >= tagNumDefine ) {
			coeArticleTag.setSuccess(CoeOrder.SUCCESS_NO);
			coeArticleTag.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_TAG_NUM_EXTRS);
			coeArticleTag.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_TAG_NUM_EXTRS);
			return false;
		}
		
		return true;
	}
	
public boolean validateDeleteArticleTag(Errors errors, CoeArticleTag coeArticleTag) {
		
		Long userId = coeArticleTag.getCreatedBy();
		if(null == userId) {
			coeArticleTag.setSuccess(CoeOrder.SUCCESS_NO);
			coeArticleTag.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_PARAGRAGH_USERID_NULL);
			coeArticleTag.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_PARAGRAGH_USERID_NULL);
			return false;
		}
		
		Long pTagId = coeArticleTag.getId();
		if(null == pTagId) {
			coeArticleTag.setSuccess(CoeOrder.SUCCESS_NO);
			coeArticleTag.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_PTAGID_EMPTY);
			coeArticleTag.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_PTAGID_EMPTY);
			return false;
		}
		
		Long professionId = coeArticleTag.getMainId();
		if(null == professionId) {
			coeArticleTag.setSuccess(CoeOrder.SUCCESS_NO);
			coeArticleTag.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_TAG_ARTICLEID_EMPTY);
			coeArticleTag.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_TAG_ARTICLEID_EMPTY);
			return false;
		}
		
		Long tagId = coeArticleTag.getTagId();
		if(null == tagId) {
			coeArticleTag.setSuccess(CoeOrder.SUCCESS_NO);
			coeArticleTag.setRespCode(CoeArticleRespCode.SZ0099_CODE_COURSE_TAGID_EMPTY);
			coeArticleTag.setRespMsg(CoeArticleRespCode.SZ0099_MSG_COURSE_TAGID_EMPTY);
			return false;
		}
		
		return true;
}

	
}
