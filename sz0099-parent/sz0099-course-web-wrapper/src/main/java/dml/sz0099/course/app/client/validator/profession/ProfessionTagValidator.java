package dml.sz0099.course.app.client.validator.profession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.product.CoeUserGradeWrapper;
import dml.sz0099.course.app.client.wrapper.profession.ProfessionTagWrapper;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.profession.ProfessionTag;
import dml.sz0099.course.app.persist.entity.profession.bo.ProfessionTagBo;
import dml.sz0099.course.app.persist.entity.tag.CoeTag;
import dml.sz0099.course.app.persist.entity.user.CoeUserGrade;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ProfessionTagValidator 校验组件
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
public class ProfessionTagValidator implements Validator {
	
	@Autowired
	private ProfessionTagWrapper professionTagWrapper;
	
	@Autowired
	private CoeUserGradeWrapper coeUserGradeWrapper;

	@Override
	public boolean supports(Class<?> clazz) {
		return ProfessionTagBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ProfessionTagBo professionTag = (ProfessionTagBo) target;

		validateUuid(errors, professionTag.getUuid());

	}

	/**
	 * ProfessionTag validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.product.ProfessionTag.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateAddProductTag(Errors errors, ProfessionTag professionTag) {
		
		Long userId = professionTag.getCreatedBy();
		if(null == userId) {
			professionTag.setSuccess(ProfessionTag.SUCCESS_NO);
			professionTag.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PARAGRAGH_USERID_NULL);
			professionTag.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PARAGRAGH_USERID_NULL);
			return false;
		}
		
		Long professionId = professionTag.getMainId();
		if(null == professionId) {
			professionTag.setSuccess(ProfessionTag.SUCCESS_NO);
			professionTag.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_TAG_DELETE_PROFESSIONID_EMPTY);
			professionTag.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_TAG_DELETE_PROFESSIONID_EMPTY);
			return false;
		}
		
		String name = professionTag.getName();
		professionTag.setName(StringUtils.trim(name));
		if(StringUtils.isBlank(name)) {
			CoeTag tag = professionTag.getTag();
			if(null == tag) {
				professionTag.setSuccess(ProfessionTag.SUCCESS_NO);
				professionTag.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_TAG_EMPTY);
				professionTag.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_TAG_EMPTY);
				return false;
			}
			name = tag.getName();
			tag.setName(StringUtils.trim(name));
			if(StringUtils.isBlank(name)) {
				professionTag.setSuccess(ProfessionTag.SUCCESS_NO);
				professionTag.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_TAG_NAME_EMPTY);
				professionTag.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_TAG_NAME_EMPTY);
				return false;
			}
		}
		
		if(name.length()>8) {
			professionTag.setSuccess(ProfessionTag.SUCCESS_NO);
			professionTag.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_TAG_NAME_LENGTH_EXTRA);
			professionTag.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_TAG_NAME_LENGTH_EXTRA);
			return false;
		}
		
		ProfessionTag  entity = professionTagWrapper.findByMainIdAndName(professionTag);
		if(null != entity) {
			professionTag.setSuccess(ProfessionTag.SUCCESS_NO);
			professionTag.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_TAG_EXIST);
			professionTag.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_TAG_EXIST);
			return false;
		}
		
		Long num = professionTagWrapper.countByMainId(professionId);
		CoeUserGrade coeUserGrade = coeUserGradeWrapper.findByUserId(userId);
		Integer tagNumDefine = 5;
		if(null != coeUserGrade) {
			tagNumDefine = coeUserGrade.getProfessionTagNum();
			if(tagNumDefine == null) {
				tagNumDefine=5;
			}
		}
		if(num >= tagNumDefine ) {
			professionTag.setSuccess(ProfessionTag.SUCCESS_NO);
			professionTag.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_TAG_NUM_EXTRS);
			professionTag.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_TAG_NUM_EXTRS);
			return false;
		}
		
		return true;
	}
	
public boolean validateDeleteProductTag(Errors errors, ProfessionTag productTag) {
		
		Long userId = productTag.getCreatedBy();
		if(null == userId) {
			productTag.setSuccess(ProfessionTag.SUCCESS_NO);
			productTag.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PARAGRAGH_USERID_NULL);
			productTag.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PARAGRAGH_USERID_NULL);
			return false;
		}
		
		Long pTagId = productTag.getId();
		if(null == pTagId) {
			productTag.setSuccess(ProfessionTag.SUCCESS_NO);
			productTag.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PTAGID_EMPTY);
			productTag.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PTAGID_EMPTY);
			return false;
		}
		
		Long professionId = productTag.getMainId();
		if(null == professionId) {
			productTag.setSuccess(ProfessionTag.SUCCESS_NO);
			productTag.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_TAG_PROFESSIONID_EMPTY);
			productTag.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_TAG_PROFESSIONID_EMPTY);
			return false;
		}
		
		Long tagId = productTag.getTagId();
		if(null == tagId) {
			productTag.setSuccess(ProfessionTag.SUCCESS_NO);
			productTag.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_TAGID_EMPTY);
			productTag.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_TAGID_EMPTY);
			return false;
		}
		
		return true;
}

	
}
