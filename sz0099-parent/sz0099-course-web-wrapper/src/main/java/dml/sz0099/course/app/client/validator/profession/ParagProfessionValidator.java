package dml.sz0099.course.app.client.validator.profession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.profession.ParagProfessionWrapper;
import dml.sz0099.course.app.client.wrapper.profession.ParagraphWrapper;
import dml.sz0099.course.app.client.wrapper.profession.ProfessionWrapper;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.order.CoeOrder;
import dml.sz0099.course.app.persist.entity.profession.ParagProfession;
import dml.sz0099.course.app.persist.entity.profession.Paragraph;
import dml.sz0099.course.app.persist.entity.profession.Profession;
import dml.sz0099.course.app.persist.entity.profession.bo.ParagProfessionBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ParagProfessionValidator 校验组件
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
public class ParagProfessionValidator implements Validator {

	@Autowired
	private ProfessionWrapper professionWrapper;
	
	@Autowired
	private ParagProfessionWrapper paragProfessionWrapper;
	
	@Autowired
	private ParagraphWrapper paragraphWrapper;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ParagProfessionBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ParagProfessionBo paragProfession = (ParagProfessionBo) target;

		validateUuid(errors, paragProfession.getUuid());

	}

	/**
	 * ParagProfession validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.paragraph.ParagProfession.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateExistProfession(ParagProfession paragProfession) {
		
		Long professionId = paragProfession.getMainId();
		if(null == professionId) {
			paragProfession.setSuccess(CoeOrder.SUCCESS_NO);
			paragProfession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PERSONAL_PROFESSION_NOT_EXIST);
			paragProfession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PERSONAL_PROFESSION_NOT_EXIST);
			return false;
		}
		
		Profession exist = professionWrapper.findById(professionId);
		if(null == exist) {
			paragProfession.setSuccess(CoeOrder.SUCCESS_NO);
			paragProfession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PERSONAL_PROFESSION_NOT_EXIST);
			paragProfession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PERSONAL_PROFESSION_NOT_EXIST);
			return false;
		}
		
		Long userId = paragProfession.getUserId();
		Long userIdEntity = exist.getUserId();
		if(userId == null) {
			paragProfession.setSuccess(CoeOrder.SUCCESS_NO);
			paragProfession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_USER_NOT_LOGIN);
			paragProfession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_USER_NOT_LOGIN);
			return false;
		}
		
		if(userIdEntity==null) {
			paragProfession.setSuccess(CoeOrder.SUCCESS_NO);
			paragProfession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PARAGRAGH_PERSONAL_PROFESSION_USERID_NULL);
			paragProfession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PARAGRAGH_PERSONAL_PROFESSION_USERID_NULL);
			return false;
		}
		
		if(!userId.equals(userIdEntity)) {
			paragProfession.setSuccess(CoeOrder.SUCCESS_NO);
			paragProfession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PARAGRAGH_PROFESSION_USERID_CONFLICT);
			paragProfession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PARAGRAGH_PROFESSION_USERID_CONFLICT);
			return false;
		}
		
		return true;
	}
	
	public boolean validateAddProduct(ParagProfession paragProfession) {
		boolean checked = validateExistProfession(paragProfession);
		if(!checked) {
			return false;
		}
		Long professionId = paragProfession.getMainId();
		Long count = paragProfessionWrapper.countByMainId(professionId);
		if(null != count && count>19) {
			//最多不能超19段
			paragProfession.setSuccess(CoeOrder.SUCCESS_NO);
			paragProfession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PARAGRAGH_TOTAL_EXTRA);
			paragProfession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PARAGRAGH_TOTAL_EXTRA);
			return false;
		}
		
		return true;
	}
	
	public boolean validateMergeProduct(ParagProfession paragProfession) {
		boolean checked = validateExistProfession(paragProfession);
		if(!checked) {
			return false;
		}
		
		Long paragId = paragProfession.getParagId();
		boolean exist = paragraphWrapper.existById(paragId);
		if(!exist) {
			paragProfession.setSuccess(CoeOrder.SUCCESS_NO);
			paragProfession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PARAGRAGH_NOT_EXIST);
			paragProfession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PARAGRAGH_NOT_EXIST);
			return false;
		}
		
		Paragraph paragraph = paragProfession.getParagraph();
		if(null == paragraph) {
			//段落上传数据不存在
			paragProfession.setSuccess(CoeOrder.SUCCESS_NO);
			paragProfession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PARAGRAGH_UPLOAD_NOT_EXIST);
			paragProfession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PARAGRAGH_UPLOAD_NOT_EXIST);
			return false;
		}
		return true;
	}
	
	public boolean validateForDelete(ParagProfession paragProfession) {
		boolean checked = validateExistProfession(paragProfession);
		if(!checked) {
			return false;
		}
		
		Long paragId = paragProfession.getParagId();
		boolean exist = paragraphWrapper.existById(paragId);
		if(!exist) {
			paragProfession.setSuccess(CoeOrder.SUCCESS_NO);
			paragProfession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PARAGRAGH_NOT_EXIST);
			paragProfession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PARAGRAGH_NOT_EXIST);
			return false;
		}
		
		Paragraph paragraph = paragProfession.getParagraph();
		if(null == paragraph) {
			//段落上传数据不存在
			paragProfession.setSuccess(CoeOrder.SUCCESS_NO);
			paragProfession.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PARAGRAGH_UPLOAD_NOT_EXIST);
			paragProfession.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PARAGRAGH_UPLOAD_NOT_EXIST);
			return false;
		}
		return true;
	}
	
	public boolean validateForDeleteAll(ParagProfession paragProfession) {
		boolean checked = validateExistProfession(paragProfession);
		if(!checked) {
			return false;
		}
		return true;
	}

}
