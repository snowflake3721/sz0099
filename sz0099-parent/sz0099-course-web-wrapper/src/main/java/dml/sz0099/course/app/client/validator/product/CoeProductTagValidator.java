package dml.sz0099.course.app.client.validator.product;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.product.CoeProductTagWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeUserGradeWrapper;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.order.CoeOrder;
import dml.sz0099.course.app.persist.entity.product.CoeProductTag;
import dml.sz0099.course.app.persist.entity.product.bo.CoeProductTagBo;
import dml.sz0099.course.app.persist.entity.tag.CoeTag;
import dml.sz0099.course.app.persist.entity.user.CoeUserGrade;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeProductTagValidator 校验组件
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
public class CoeProductTagValidator implements Validator {
	
	@Autowired
	private CoeProductTagWrapper coeProductTagWrapper;
	
	@Autowired
	private CoeUserGradeWrapper coeUserGradeWrapper;

	@Override
	public boolean supports(Class<?> clazz) {
		return CoeProductTagBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CoeProductTagBo coeProductTag = (CoeProductTagBo) target;

		validateUuid(errors, coeProductTag.getUuid());

	}

	/**
	 * CoeProductTag validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.product.CoeProductTag.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateAddProductTag(Errors errors, CoeProductTag productTag) {
		
		Long userId = productTag.getCreatedBy();
		if(null == userId) {
			productTag.setSuccess(CoeOrder.SUCCESS_NO);
			productTag.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PARAGRAGH_USERID_NULL);
			productTag.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PARAGRAGH_USERID_NULL);
			return false;
		}
		
		Long productId = productTag.getMainId();
		if(null == productId) {
			productTag.setSuccess(CoeOrder.SUCCESS_NO);
			productTag.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_DELETE_PRODUCTID_EMPTY);
			productTag.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_DELETE_PRODUCTID_EMPTY);
			return false;
		}
		
		String name = productTag.getName();
		productTag.setName(StringUtils.trim(name));
		if(StringUtils.isBlank(name)) {
			CoeTag tag = productTag.getTag();
			if(null == tag) {
				productTag.setSuccess(CoeOrder.SUCCESS_NO);
				productTag.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_TAG_EMPTY);
				productTag.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_TAG_EMPTY);
				return false;
			}
			name = tag.getName();
			tag.setName(StringUtils.trim(name));
			if(StringUtils.isBlank(name)) {
				productTag.setSuccess(CoeOrder.SUCCESS_NO);
				productTag.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_TAG_NAME_EMPTY);
				productTag.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_TAG_NAME_EMPTY);
				return false;
			}
		}
		
		CoeProductTag  entity = coeProductTagWrapper.findByMainIdAndName(productTag);
		if(null != entity) {
			productTag.setSuccess(CoeOrder.SUCCESS_NO);
			productTag.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_TAG_EXIST);
			productTag.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_TAG_EXIST);
			return false;
		}
		
		Long num = coeProductTagWrapper.countByMainId(productId);
		CoeUserGrade coeUserGrade = coeUserGradeWrapper.findByUserId(userId);
		Integer tagNumDefine = 5;
		if(null != coeUserGrade) {
			tagNumDefine = coeUserGrade.getTagNum();
			if(tagNumDefine == null) {
				tagNumDefine=5;
			}
		}
		if(num >= tagNumDefine ) {
			productTag.setSuccess(CoeOrder.SUCCESS_NO);
			productTag.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_TAG_NUM_EXTRS);
			productTag.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_TAG_NUM_EXTRS);
			return false;
		}
		
		return true;
	}
	
public boolean validateDeleteProductTag(Errors errors, CoeProductTag productTag) {
		
		Long userId = productTag.getCreatedBy();
		if(null == userId) {
			productTag.setSuccess(CoeOrder.SUCCESS_NO);
			productTag.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PARAGRAGH_USERID_NULL);
			productTag.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PARAGRAGH_USERID_NULL);
			return false;
		}
		
		Long pTagId = productTag.getId();
		if(null == pTagId) {
			productTag.setSuccess(CoeOrder.SUCCESS_NO);
			productTag.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PTAGID_EMPTY);
			productTag.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PTAGID_EMPTY);
			return false;
		}
		
		Long productId = productTag.getMainId();
		if(null == productId) {
			productTag.setSuccess(CoeOrder.SUCCESS_NO);
			productTag.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCTID_EMPTY);
			productTag.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCTID_EMPTY);
			return false;
		}
		
		Long tagId = productTag.getTagId();
		if(null == tagId) {
			productTag.setSuccess(CoeOrder.SUCCESS_NO);
			productTag.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_TAGID_EMPTY);
			productTag.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_TAGID_EMPTY);
			return false;
		}
		
		return true;
}

	
}
