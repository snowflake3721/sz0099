package dml.sz0099.course.app.client.validator.profession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.profession.ProfessionPraiseWrapper;
import dml.sz0099.course.app.module.define.CoeArticleRespCode;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePraise;
import dml.sz0099.course.app.persist.entity.profession.Profession;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPraise;
import dml.sz0099.course.app.persist.entity.profession.bo.ProfessionPraiseBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ProfessionPraiseValidator 校验组件
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
public class ProfessionPraiseValidator implements Validator {
	
	@Autowired
	private ProfessionValidator professionValidator;
	
	@Autowired
	private ProfessionPraiseWrapper professionPraiseWrapper;

	@Override
	public boolean supports(Class<?> clazz) {
		return ProfessionPraiseBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ProfessionPraiseBo professionPraise = (ProfessionPraiseBo) target;

		validateUuid(errors, professionPraise.getUuid());

	}

	/**
	 * ProfessionPraise validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.profession.ProfessionPraise.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateForDoPraise(ProfessionPraise professionPraise) {
		Long userId = professionPraise.getUserId();
		
		if (null == userId) {
			professionPraise.setSuccess(Profession.SUCCESS_NO);
			professionPraise.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_PRAISE_USER_NOT_LOGIN);
			professionPraise.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_PRAISE_USER_NOT_LOGIN);
			return false;
		}
		
		Profession profession = new Profession();
		profession.setId(professionPraise.getMainId());
		profession.setUserId(userId);
		boolean existed = professionValidator.validateExistForPraise(profession);
		if(!existed) {
			professionPraise.setSuccess(Profession.SUCCESS_NO);
			professionPraise.setRespCode(profession.getRespCode());
			professionPraise.setRespMsg(profession.getRespMsg());
			return false;
		}
		
		/*Long mainId = professionPraise.getMainId();
		boolean hasPaised = professionPraiseWrapper.hasPraisedByMainIdAndUserId(mainId, userId);
		if(hasPaised) {
			professionPraise.setSuccess(Profession.SUCCESS_NO);
			professionPraise.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_PRAISE_EXIST);
			professionPraise.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_PRAISE_EXIST);
			return false;
		}*/
		
		return true;
	}
	
	public boolean validateForPraise(ProfessionPraise professionPraise) {
		Long userId = professionPraise.getUserId();
		
		if (null == userId) {
			professionPraise.setSuccess(Profession.SUCCESS_NO);
			professionPraise.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_PRAISE_USER_NOT_LOGIN);
			professionPraise.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_PRAISE_USER_NOT_LOGIN);
			return false;
		}
		
		Profession profession = new Profession();
		profession.setId(professionPraise.getMainId());
		profession.setUserId(userId);
		boolean existed = professionValidator.validateExistForPraise(profession);
		if(!existed) {
			professionPraise.setSuccess(Profession.SUCCESS_NO);
			professionPraise.setRespCode(profession.getRespCode());
			professionPraise.setRespMsg(profession.getRespMsg());
			return false;
		}
		
		Long mainId = professionPraise.getMainId();
		ProfessionPraise entity = professionPraiseWrapper.findByMainIdAndUserId(mainId, userId);
		if(null != entity) {
			professionPraise.setId(entity.getId());
			professionPraise.setWord(entity.getWord());
			professionPraise.setSuccess(Profession.SUCCESS_YES);
			professionPraise.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_PRAISE_EXIST);
			professionPraise.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_PRAISE_EXIST);
			return true;
		}
		
		return true;
	}
	
	

}
