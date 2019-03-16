package dml.sz0099.course.app.client.validator.category;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.category.CategoryExtendWrapper;
import dml.sz0099.course.app.client.wrapper.category.CategoryWrapper;
import dml.sz0099.course.app.module.define.CourseRespCode;
import dml.sz0099.course.app.persist.entity.category.CategoryExtend;
import dml.sz0099.course.app.persist.entity.category.bo.CategoryExtendBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CategoryExtendValidator 校验组件
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
public class CategoryExtendValidator implements Validator {
	
	@Autowired
	private CategoryExtendWrapper categoryExtendWrapper;
	
	@Autowired
	private CategoryWrapper categoryWrapper;

	@Override
	public boolean supports(Class<?> clazz) {
		return CategoryExtendBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CategoryExtendBo categoryExtend = (CategoryExtendBo) target;

		validateUuid(errors, categoryExtend.getUuid());

	}

	/**
	 * CategoryExtend validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.category.CategoryExtend.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateCategoryExtendForExist(CategoryExtend extend) {
		Long id = extend.getId();
		//if(null)
		return true;
	}
	
	public boolean validateExtendForPersist(CategoryExtend extend) {
		
		Long id = extend.getId();
		Long userId = extend.getUserId();
		
		if(null == userId) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_CATEGORY_EXTEND_USER_NOT_LOGIN);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_CATEGORY_EXTEND_USER_NOT_LOGIN);
			extend.setSuccess(CategoryExtend.SUCCESS_NO);
			return false;
		}
		
		if(null != id) {
			CategoryExtend exist = categoryExtendWrapper.findById(id);
			if(exist != null) {
				extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_CATEGORY_EXTEND_VARIETY_EXIST);
				extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_CATEGORY_EXTEND_VARIETY_EXIST);
				//项目类别已存在，则应返回该类别
				
				Long existUserId = exist.getUserId();
				if(!userId.equals(existUserId)) {
					extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_CATEGORY_EXTEND_NOT_SELF);
					extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_CATEGORY_EXTEND_NOT_SELF);
					extend.setSuccess(CategoryExtend.SUCCESS_NO);
					return false;
				}
				
			}
		}
		
		Long userNum = categoryExtendWrapper.countByUserId(userId);
		if(userNum>CategoryExtend.VARIETYMAXNUM_DEFAULT) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_CATEGORY_EXTEND_VARIETY_EXTRA);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_CATEGORY_EXTEND_VARIETY_EXTRA);
			extend.setSuccess(CategoryExtend.SUCCESS_NO);
			return false;
		}
		return true;
	}
	
	public boolean validateExtend(CategoryExtend extend) {
		validateCategoryExtend(extend);
		return CategoryExtend.SUCCESS_YES==extend.getSuccess();
	}
	
	public CategoryExtend validateCategoryExtend(CategoryExtend extend) {
		String devId = StringUtils.trim(extend.getDevId());
		if(StringUtils.isBlank(devId)) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_DEVID_EMPTY);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_DEVID_EMPTY);
			extend.setSuccess(CategoryExtend.SUCCESS_NO);
			return extend;
		}
		
		
		Long id = extend.getId();
		Long positionId = extend.getPositionId();
		CategoryExtend categoryExtend = null;

		if(null != id ) {
			categoryExtend = categoryExtendWrapper.findById(id);
			if(null == categoryExtend) {
				extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_POSITIONID_INVALID);
				extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_POSITIONID_INVALID);
				extend.setSuccess(CategoryExtend.SUCCESS_NO);
				return extend;
			}
			validateDisabled(extend, categoryExtend);
			extend.setSuccess(CategoryExtend.SUCCESS_YES);
			return extend;
		}
		if(null != positionId ) {
			categoryExtend = categoryExtendWrapper.findByPositionId(positionId);
			if(null == categoryExtend) {
				extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_POSITIONID_INVALID);
				extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_POSITIONID_INVALID);
				extend.setSuccess(CategoryExtend.SUCCESS_NO);
				return extend;
			}
			
			validateDisabled(extend, categoryExtend);
			extend.setSuccess(CategoryExtend.SUCCESS_YES);
			return extend;
		}
		
		String project = extend.getProject();
		String module = extend.getModule();
		String variety = extend.getVariety();
		String position = extend.getPosition();
		
		if(StringUtils.isBlank(project)) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_PROJECT_EMPTY);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_PROJECT_EMPTY);
			extend.setSuccess(CategoryExtend.SUCCESS_NO);
			return extend;
		}
		
		if(StringUtils.isBlank(module)) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_MODULE_EMPTY);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_MODULE_EMPTY);
			extend.setSuccess(CategoryExtend.SUCCESS_NO);
			return extend;
		}
		if(StringUtils.isBlank(variety)) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_VARIETY_EMPTY);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_VARIETY_EMPTY);
			extend.setSuccess(CategoryExtend.SUCCESS_NO);
			return extend;
		}
		
		if(StringUtils.isBlank(position)) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_POSITION_EMPTY);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_POSITION_EMPTY);
			extend.setSuccess(CategoryExtend.SUCCESS_NO);
			return extend;
		}
		
		categoryExtend = categoryExtendWrapper.findCategoryExtend(extend);
		
		
		if(null != categoryExtend) {
			validateDisabled(extend, categoryExtend);
		}else {
			categoryExtend = new CategoryExtend();
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_IMAGE_EXTEND_NOT_EXIST);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_IMAGE_EXTEND_NOT_EXIST);
			extend.setSuccess(CategoryExtend.SUCCESS_NO);
			return extend;
		}
		categoryExtend.setSuccess(CategoryExtend.SUCCESS_YES);
		return categoryExtend;
	}

	/**
	 * @param extend
	 * @param categoryExtend
	 */
	private CategoryExtend validateDisabled(CategoryExtend extend, CategoryExtend categoryExtend) {
		if(categoryExtend.getDisable()) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_POSITIONID_DISABLED);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_POSITIONID_DISABLED);
			extend.setSuccess(CategoryExtend.SUCCESS_NO);
			return extend;
		}
		
		if(categoryExtend.getDeleted()) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_POSITIONID_DELETED);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_POSITIONID_DELETED);
			extend.setSuccess(CategoryExtend.SUCCESS_NO);
			return extend;
		}
		return extend;
	}
	
	public CategoryExtend validateCategoryExtendThenCreate(CategoryExtend extend) {
		
		CategoryExtend categoryExtend = validateCategoryExtend(extend);
		if(extend.getSuccess()==CategoryExtend.SUCCESS_NO) {
			if(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_IMAGE_EXTEND_NOT_EXIST.equals(extend.getRespCode())) {
				Long userId = extend.getUserId();
				
				if(null==userId) {
					extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_USERID_EMPTY);
					extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_USERID_EMPTY);
					extend.setSuccess(CategoryExtend.SUCCESS_NO);
					return extend;
				}
				//尚不存在，执行创建
				extend.setCreatedBy(userId);
				extend.setLastModifiedBy(userId);
				/*String domain = extend.getDomain();
				if(StringUtils.isBlank(domain)) {
					extend.setDomain("http://m.dramala.com");
				}*/
				categoryExtend = categoryExtendWrapper.create(extend);
				if(null == categoryExtend) {
					//创建失败
					extend.setSuccess(CategoryExtend.SUCCESS_NO);
					extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_IMAGE_EXTEND_CREATE_FAIL);
					extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_IMAGE_EXTEND_CREATE_FAIL);
					return extend;
				}
				categoryExtend.setSuccess(CategoryExtend.SUCCESS_YES);
			}
		}
		
		categoryExtend.setSuccess(CategoryExtend.SUCCESS_YES);
		return categoryExtend;
	}
	
public boolean validateExtendForDelete(CategoryExtend extend) {
		
		Long id = extend.getId();
		Long userId = extend.getUserId();
		
		if(null == userId) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_CATEGORY_EXTEND_USER_NOT_LOGIN);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_CATEGORY_EXTEND_USER_NOT_LOGIN);
			extend.setSuccess(CategoryExtend.SUCCESS_NO);
			return false;
		}
		
		if(null != id) {
			CategoryExtend exist = categoryExtendWrapper.findById(id);
			if(exist == null) {
				extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_CATEGORY_EXTEND_VARIETY_NOT_EXIST);
				extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_CATEGORY_EXTEND_VARIETY_NOT_EXIST);
				return false;
			}
			//项目类别已存在，则应返回该类别
			Long existUserId = exist.getUserId();
			if(!userId.equals(existUserId)) {
				extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_CATEGORY_EXTEND_NOT_SELF);
				extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_CATEGORY_EXTEND_NOT_SELF);
				extend.setSuccess(CategoryExtend.SUCCESS_NO);
				return false;
			}
		}
		
		Long extendId = extend.getId();
		Long categoryNum = categoryWrapper.countByExtendId(extendId);
		if(categoryNum>0) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_CATEGORY_EXTEND_HAS_CATEGORY);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_CATEGORY_EXTEND_HAS_CATEGORY);
			extend.setSuccess(CategoryExtend.SUCCESS_NO);
			return false;
		}
		return true;
	}

}
