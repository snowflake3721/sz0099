package dml.sz0099.course.app.client.validator.profession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.profession.ProfessionFavirateWrapper;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.profession.Profession;
import dml.sz0099.course.app.persist.entity.profession.ProfessionFavirate;
import dml.sz0099.course.app.persist.entity.profession.bo.ProfessionFavirateBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ProfessionFavirateValidator 校验组件
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
public class ProfessionFavirateValidator implements Validator {
	
	@Autowired
	private ProfessionValidator professionValidator;
	
	@Autowired
	private ProfessionFavirateWrapper professionFavirateWrapper;

	@Override
	public boolean supports(Class<?> clazz) {
		return ProfessionFavirateBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ProfessionFavirateBo professionFavirate = (ProfessionFavirateBo) target;

		validateUuid(errors, professionFavirate.getUuid());

	}

	/**
	 * ProfessionFavirate validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.profession.ProfessionFavirate.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateFavirate(ProfessionFavirate professionFavirate) {
		Long userId = professionFavirate.getUserId();
		
		if (null == userId) {
			professionFavirate.setSuccess(Profession.SUCCESS_NO);
			professionFavirate.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_FAVIRATE_USER_NOT_LOGIN);
			professionFavirate.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_FAVIRATE_USER_NOT_LOGIN);
			return false;
		}
		
		Profession profession = new Profession();
		profession.setId(professionFavirate.getMainId());
		profession.setUserId(userId);
		boolean existed = professionValidator.validateExistForFavirate(profession);
		if(!existed) {
			professionFavirate.setSuccess(Profession.SUCCESS_NO);
			professionFavirate.setRespCode(profession.getRespCode());
			professionFavirate.setRespMsg(profession.getRespMsg());
			return false;
		}
		
		Long mainId = professionFavirate.getMainId();
		boolean hasFavirated = professionFavirateWrapper.hasFaviratedByMainIdAndUserId(mainId, userId);
		if(hasFavirated) {
			professionFavirate.setSuccess(Profession.SUCCESS_NO);
			professionFavirate.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_FAVIRATE_EXIST);
			professionFavirate.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_FAVIRATE_EXIST);
			return false;
		}
		
		return true;
	}

}
