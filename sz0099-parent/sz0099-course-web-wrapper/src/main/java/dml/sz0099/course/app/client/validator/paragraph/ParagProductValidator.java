package dml.sz0099.course.app.client.validator.paragraph;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.paragraph.ParagProductWrapper;
import dml.sz0099.course.app.client.wrapper.paragraph.ParagraphWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeProductWrapper;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.order.CoeOrder;
import dml.sz0099.course.app.persist.entity.paragraph.ParagProduct;
import dml.sz0099.course.app.persist.entity.paragraph.Paragraph;
import dml.sz0099.course.app.persist.entity.paragraph.bo.ParagProductBo;
import dml.sz0099.course.app.persist.entity.product.CoeProduct;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ParagProductValidator 校验组件
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
public class ParagProductValidator implements Validator {

	@Autowired
	private CoeProductWrapper coeProductWrapper;
	
	@Autowired
	private ParagProductWrapper paragProductWrapper;
	
	@Autowired
	private ParagraphWrapper paragraphWrapper;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ParagProductBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ParagProductBo paragProduct = (ParagProductBo) target;

		validateUuid(errors, paragProduct.getUuid());

	}

	/**
	 * ParagProduct validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.paragraph.ParagProduct.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateExistProduct(ParagProduct paragProduct) {
		
		Long productId = paragProduct.getMainId();
		if(null == productId) {
			paragProduct.setSuccess(CoeOrder.SUCCESS_NO);
			paragProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_NOT_EXIST);
			paragProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_NOT_EXIST);
			return false;
		}
		
		CoeProduct exist = coeProductWrapper.findById(productId);
		if(null == exist) {
			paragProduct.setSuccess(CoeOrder.SUCCESS_NO);
			paragProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_NOT_EXIST);
			paragProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_NOT_EXIST);
			return false;
		}
		
		Long userId = paragProduct.getUserId();
		Long userIdEntity = exist.getUserId();
		if(userId == null) {
			paragProduct.setSuccess(CoeOrder.SUCCESS_NO);
			paragProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PARAGRAGH_USERID_NULL);
			paragProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PARAGRAGH_USERID_NULL);
			return false;
		}
		
		if(userIdEntity==null) {
			paragProduct.setSuccess(CoeOrder.SUCCESS_NO);
			paragProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PARAGRAGH_PRODUCT_USERID_NULL);
			paragProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PARAGRAGH_PRODUCT_USERID_NULL);
			return false;
		}
		
		if(!userId.equals(userIdEntity)) {
			paragProduct.setSuccess(CoeOrder.SUCCESS_NO);
			paragProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PARAGRAGH_USERID_CONFLICT);
			paragProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PARAGRAGH_USERID_CONFLICT);
			return false;
		}
		
		return true;
	}
	
	public boolean validateAddProduct(ParagProduct paragProduct) {
		boolean checked = validateExistProduct(paragProduct);
		if(!checked) {
			return false;
		}
		Long productId = paragProduct.getMainId();
		Long count = paragProductWrapper.countByMainId(productId);
		if(null != count && count>25) {
			paragProduct.setSuccess(CoeOrder.SUCCESS_NO);
			paragProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PARAGRAGH_TOTAL_EXTRA);
			paragProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PARAGRAGH_TOTAL_EXTRA);
			return false;
		}
		
		return true;
	}
	
	public boolean validateMergeProduct(ParagProduct paragProduct) {
		boolean checked = validateExistProduct(paragProduct);
		if(!checked) {
			return false;
		}
		
		Long paragId = paragProduct.getParagId();
		boolean exist = paragraphWrapper.existById(paragId);
		if(!exist) {
			paragProduct.setSuccess(CoeOrder.SUCCESS_NO);
			paragProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PARAGRAGH_NOT_EXIST);
			paragProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PARAGRAGH_NOT_EXIST);
			return false;
		}
		
		Paragraph paragraph = paragProduct.getParagraph();
		if(null == paragraph) {
			//段落上传数据不存在
			paragProduct.setSuccess(CoeOrder.SUCCESS_NO);
			paragProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PARAGRAGH_UPLOAD_NOT_EXIST);
			paragProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PARAGRAGH_UPLOAD_NOT_EXIST);
			return false;
		}
		return true;
	}
	
	public boolean validateForDelete(ParagProduct paragProduct) {
		boolean checked = validateExistProduct(paragProduct);
		if(!checked) {
			return false;
		}
		
		Long paragId = paragProduct.getParagId();
		boolean exist = paragraphWrapper.existById(paragId);
		if(!exist) {
			paragProduct.setSuccess(CoeOrder.SUCCESS_NO);
			paragProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PARAGRAGH_NOT_EXIST);
			paragProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PARAGRAGH_NOT_EXIST);
			return false;
		}
		
		Paragraph paragraph = paragProduct.getParagraph();
		if(null == paragraph) {
			//段落上传数据不存在
			paragProduct.setSuccess(CoeOrder.SUCCESS_NO);
			paragProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PARAGRAGH_UPLOAD_NOT_EXIST);
			paragProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PARAGRAGH_UPLOAD_NOT_EXIST);
			return false;
		}
		return true;
	}
	
	public boolean validateForDeleteAll(ParagProduct paragProduct) {
		boolean checked = validateExistProduct(paragProduct);
		if(!checked) {
			return false;
		}
		return true;
	}

}
