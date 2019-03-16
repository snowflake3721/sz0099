package dml.sz0099.course.app.client.validator.activity;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.activity.CoeActivityTagWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeUserGradeWrapper;
import dml.sz0099.course.app.module.define.CoeActivityRespCode;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTag;
import dml.sz0099.course.app.persist.entity.activity.bo.CoeActivityTagBo;
import dml.sz0099.course.app.persist.entity.order.CoeOrder;
import dml.sz0099.course.app.persist.entity.tag.CoeTag;
import dml.sz0099.course.app.persist.entity.user.CoeUserGrade;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeActivityTagValidator 校验组件
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
public class CoeActivityTagValidator implements Validator {
	
	@Autowired
	private CoeActivityTagWrapper coeActivityTagWrapper;
	
	@Autowired
	private CoeUserGradeWrapper coeUserGradeWrapper;

	@Override
	public boolean supports(Class<?> clazz) {
		return CoeActivityTagBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CoeActivityTagBo coeActivityTag = (CoeActivityTagBo) target;

		validateUuid(errors, coeActivityTag.getUuid());

	}

	/**
	 * CoeActivityTag validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.product.CoeActivityTag.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateAddActivityTag(Errors errors, CoeActivityTag coeActivityTag) {
		
		Long userId = coeActivityTag.getCreatedBy();
		if(null == userId) {
			coeActivityTag.setSuccess(CoeOrder.SUCCESS_NO);
			coeActivityTag.setRespCode(CoeActivityRespCode.SZ0099_CODE_COURSE_PARAGRAGH_USERID_NULL);
			coeActivityTag.setRespMsg(CoeActivityRespCode.SZ0099_MSG_COURSE_PARAGRAGH_USERID_NULL);
			return false;
		}
		
		Long professionId = coeActivityTag.getMainId();
		if(null == professionId) {
			coeActivityTag.setSuccess(CoeOrder.SUCCESS_NO);
			coeActivityTag.setRespCode(CoeActivityRespCode.SZ0099_CODE_COURSE_TAG_DELETE_ARTICLEID_EMPTY);
			coeActivityTag.setRespMsg(CoeActivityRespCode.SZ0099_MSG_COURSE_TAG_DELETE_ARTICLEID_EMPTY);
			return false;
		}
		
		String name = coeActivityTag.getName();
		coeActivityTag.setName(StringUtils.trim(name));
		if(StringUtils.isBlank(name)) {
			CoeTag tag = coeActivityTag.getTag();
			if(null == tag) {
				coeActivityTag.setSuccess(CoeOrder.SUCCESS_NO);
				coeActivityTag.setRespCode(CoeActivityRespCode.SZ0099_CODE_COURSE_TAG_EMPTY);
				coeActivityTag.setRespMsg(CoeActivityRespCode.SZ0099_MSG_COURSE_TAG_EMPTY);
				return false;
			}
			name = tag.getName();
			tag.setName(StringUtils.trim(name));
			if(StringUtils.isBlank(name)) {
				coeActivityTag.setSuccess(CoeOrder.SUCCESS_NO);
				coeActivityTag.setRespCode(CoeActivityRespCode.SZ0099_CODE_COURSE_TAG_NAME_EMPTY);
				coeActivityTag.setRespMsg(CoeActivityRespCode.SZ0099_MSG_COURSE_TAG_NAME_EMPTY);
				return false;
			}
		}
		
		CoeActivityTag  entity = coeActivityTagWrapper.findByMainIdAndName(coeActivityTag);
		if(null != entity) {
			coeActivityTag.setSuccess(CoeOrder.SUCCESS_NO);
			coeActivityTag.setRespCode(CoeActivityRespCode.SZ0099_CODE_COURSE_ARTICLE_TAG_EXIST);
			coeActivityTag.setRespMsg(CoeActivityRespCode.SZ0099_MSG_COURSE_ARTICLE_TAG_EXIST);
			return false;
		}
		
		Long num = coeActivityTagWrapper.countByMainId(professionId);
		CoeUserGrade coeUserGrade = coeUserGradeWrapper.findByUserId(userId);
		Integer tagNumDefine = 5;
		if(null != coeUserGrade) {
			/*tagNumDefine = coeUserGrade.getCoeActivityTagNum();
			if(tagNumDefine == null) {
				tagNumDefine=3;
			}*/
		}
		if(num >= tagNumDefine ) {
			coeActivityTag.setSuccess(CoeOrder.SUCCESS_NO);
			coeActivityTag.setRespCode(CoeActivityRespCode.SZ0099_CODE_COURSE_TAG_NUM_EXTRS);
			coeActivityTag.setRespMsg(CoeActivityRespCode.SZ0099_MSG_COURSE_TAG_NUM_EXTRS);
			return false;
		}
		
		return true;
	}
	
public boolean validateDeleteActivityTag(Errors errors, CoeActivityTag coeActivityTag) {
		
		Long userId = coeActivityTag.getCreatedBy();
		if(null == userId) {
			coeActivityTag.setSuccess(CoeOrder.SUCCESS_NO);
			coeActivityTag.setRespCode(CoeActivityRespCode.SZ0099_CODE_COURSE_PARAGRAGH_USERID_NULL);
			coeActivityTag.setRespMsg(CoeActivityRespCode.SZ0099_MSG_COURSE_PARAGRAGH_USERID_NULL);
			return false;
		}
		
		Long pTagId = coeActivityTag.getId();
		if(null == pTagId) {
			coeActivityTag.setSuccess(CoeOrder.SUCCESS_NO);
			coeActivityTag.setRespCode(CoeActivityRespCode.SZ0099_CODE_COURSE_PTAGID_EMPTY);
			coeActivityTag.setRespMsg(CoeActivityRespCode.SZ0099_MSG_COURSE_PTAGID_EMPTY);
			return false;
		}
		
		Long professionId = coeActivityTag.getMainId();
		if(null == professionId) {
			coeActivityTag.setSuccess(CoeOrder.SUCCESS_NO);
			coeActivityTag.setRespCode(CoeActivityRespCode.SZ0099_CODE_COURSE_TAG_ARTICLEID_EMPTY);
			coeActivityTag.setRespMsg(CoeActivityRespCode.SZ0099_MSG_COURSE_TAG_ARTICLEID_EMPTY);
			return false;
		}
		
		Long tagId = coeActivityTag.getTagId();
		if(null == tagId) {
			coeActivityTag.setSuccess(CoeOrder.SUCCESS_NO);
			coeActivityTag.setRespCode(CoeActivityRespCode.SZ0099_CODE_COURSE_TAGID_EMPTY);
			coeActivityTag.setRespMsg(CoeActivityRespCode.SZ0099_MSG_COURSE_TAGID_EMPTY);
			return false;
		}
		
		return true;
}

	
}
