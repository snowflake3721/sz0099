package dml.sz0099.course.app.client.validator.category;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.category.CategoryWrapper;
import dml.sz0099.course.app.module.define.CourseRespCode;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.category.CategoryRef;
import dml.sz0099.course.app.persist.entity.category.bo.CategoryRefBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CategoryRefValidator 校验组件
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
public class CategoryRefValidator implements Validator {
	
	@Autowired
	private CategoryWrapper categoryWrapper;

	@Override
	public boolean supports(Class<?> clazz) {
		return CategoryRefBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CategoryRefBo categoryRef = (CategoryRefBo) target;

		validateUuid(errors, categoryRef.getUuid());

	}

	/**
	 * CategoryRef validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.category.CategoryRef.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateForChange(CategoryRef categoryRef) {
		Long baseId = categoryRef.getBaseId();
		Long mainId = categoryRef.getMainId();
		if(null == mainId) {
			categoryRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_CATEGORY_MAINID_EMPTY);
			categoryRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_CATEGORY_MAINID_EMPTY);
			categoryRef.setSuccess(CategoryRef.SUCCESS_NO);
			return false;
		}
		
		if(null == baseId) {
			categoryRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_CATEGORY_NOT_EXIST);
			categoryRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_CATEGORY_NOT_EXIST);
			return false;
		}
		
		Category category = categoryWrapper.findById(baseId);
		
		if(null == category) {
			categoryRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_CATEGORY_NOT_EXIST);
			categoryRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_CATEGORY_NOT_EXIST);
			categoryRef.setSuccess(CategoryRef.SUCCESS_NO);
			return false;
		}
		
		/*Long mainIdEntity = category.getMainId();
		if(mainId.equals(mainIdEntity)) {
			categoryRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_CATEGORY_REF_HAS_BIND);
			categoryRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_CATEGORY_REF_HAS_BIND);
			categoryRef.setSuccess(CategoryRef.SUCCESS_NO);
			return false;
		}*/
		
		return true;
	}

}
