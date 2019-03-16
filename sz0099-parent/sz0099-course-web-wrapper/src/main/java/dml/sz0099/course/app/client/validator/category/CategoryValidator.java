package dml.sz0099.course.app.client.validator.category;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.category.CategoryExtendWrapper;
import dml.sz0099.course.app.client.wrapper.category.CategoryRefWrapper;
import dml.sz0099.course.app.client.wrapper.category.CategoryWrapper;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.module.define.CourseRespCode;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.category.CategoryExtend;
import dml.sz0099.course.app.persist.entity.category.CategoryRef;
import dml.sz0099.course.app.persist.entity.category.bo.CategoryBo;
import dml.sz0099.course.app.persist.entity.order.CoeOrder;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeCategoryValidator 校验组件
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
public class CategoryValidator implements Validator {
	
	@Autowired
	private CategoryExtendWrapper categoryExtendWrapper;
	
	@Autowired
	private CategoryWrapper categoryWrapper;
	
	@Autowired
	private CategoryRefWrapper categoryRefWrapper;

	@Override
	public boolean supports(Class<?> clazz) {
		return Object.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CategoryBo coeCategory = (CategoryBo) target;

		validateUuid(errors, coeCategory.getUuid());

	}

	/**
	 * CoeCategory validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.product.CoeCategory.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateAddCategory(Category category) {
		
		boolean checked = checkExist(category);
		if(!checked) {
			return false;
		}
		
		String code = category.getCode();//验证唯一性
		Category entity = categoryWrapper.findByCode(code);
		if(null != entity) {
			category.setSuccess(Category.SUCCESS_NO);
			category.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_CATEGORY_CODE_EXIST);
			category.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_CATEGORY_CODE_EXIST);
			return false;
		}
		
		return true;
	}
	
	public boolean validateMergeCategory(Category category) {
		
		Long id = category.getId();
		Category entity = categoryWrapper.findById(id);
		if(null == entity) {
			category.setSuccess(Category.SUCCESS_NO);
			category.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_CATEGORY_NOT_EXIST);
			category.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_CATEGORY_NOT_EXIST);
			return false;
		}
		
		boolean checked = checkExist(category);
		if(!checked) {
			return false;
		}
		
		String code = category.getCode();//验证唯一性
		Category entityC = categoryWrapper.findByCode(code);
		if(null != entityC && !entity.getId().equals(id)) {
			category.setSuccess(Category.SUCCESS_NO);
			category.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_CATEGORY_CODE_EXIST);
			category.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_CATEGORY_CODE_EXIST);
			return false;
		}
		
		return true;
	}

	/**
	 * @param category
	 */
	private boolean checkExist(Category category) {
		Long extendId = category.getExtendId();//验证项目分类是否存在
		Long positionId = category.getPositionId();
		Long userId = category.getUserId();//验证用户是否登录
		
		if(null == userId) {
			category.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_CATEGORY_EXTEND_USER_NOT_LOGIN);
			category.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_CATEGORY_EXTEND_USER_NOT_LOGIN);
			category.setSuccess(CategoryExtend.SUCCESS_NO);
			return false;
		}
		
		Long mainId = category.getMainId();
		if(null == mainId) {
			category.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_CATEGORY_MAINID_EMPTY);
			category.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_CATEGORY_MAINID_EMPTY);
			category.setSuccess(CategoryExtend.SUCCESS_NO);
			return false;
		}
		
		Long subId = category.getSubId();
		if(null == subId) {
			category.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_CATEGORY_SUBID_EMPTY);
			category.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_CATEGORY_SUBID_EMPTY);
			category.setSuccess(CategoryExtend.SUCCESS_NO);
			return false;
		}
		
		CategoryExtend extend = null;
		if(null != extendId) {
			extend = categoryExtendWrapper.findById(extendId);
		}
		
		if(null == extend && null != positionId) {
			extend = categoryExtendWrapper.findByPositionId(positionId);
		}
		
		if(null == extend) {
			category.setSuccess(Category.SUCCESS_NO);
			category.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_CATEGORY_EXTEND_NOT_EXIST);
			category.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_CATEGORY_EXTEND_NOT_EXIST);
			return false;
		}
		return true;
	}
	
	public boolean validateForDelete(Category category) {
		boolean checked = checkExist(category);
		if(!checked) {
			return false;
		}
		
		Long id = category.getId();
		boolean exist = categoryWrapper.existById(id);
		if(!exist) {
			category.setSuccess(CoeOrder.SUCCESS_NO);
			category.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_CATEGORY_NOT_EXIST);
			category.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_CATEGORY_NOT_EXIST);
			return false;
		}
		
		//检查是否有子类
		Long childrenNum = categoryWrapper.countByParentId(category);
		if(null != childrenNum && childrenNum>0) {
			category.setSuccess(CoeOrder.SUCCESS_NO);
			category.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_CATEGORY_HAS_CHILDREN);
			category.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_CATEGORY_HAS_CHILDREN);
			return false;
		}
		
		//检查类别下是否绑定有产品
		CategoryRef categoryRef = new CategoryRef();
		categoryRef.setBaseId(id);
		Long refNum = categoryRefWrapper.countForBase(categoryRef);
		if(null != refNum && refNum>0) {
			category.setSuccess(CoeOrder.SUCCESS_NO);
			category.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_CATEGORY_HAS_REF);
			category.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_CATEGORY_HAS_REF);
			return false;
		}
		return true;
	}
	
	
	public boolean validateForDeleteAll(Category category) {
		boolean checked = checkExist(category);
		if(!checked) {
			return false;
		}
		return true;
	}

}
