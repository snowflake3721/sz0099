package dml.sz0099.course.app.client.validator.activity;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.activity.CoeActivityFeeWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeUserGradeWrapper;
import dml.sz0099.course.app.module.define.CoeActivityRespCode;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityFee;
import dml.sz0099.course.app.persist.entity.order.CoeOrder;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeActivityFeeValidator 校验组件
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
public class CoeActivityFeeValidator implements Validator {
	
	@Autowired
	private CoeActivityFeeWrapper coeActivityFeeWrapper;
	
	@Autowired
	private CoeUserGradeWrapper coeUserGradeWrapper;

	@Override
	public boolean supports(Class<?> clazz) {
		return CoeActivityFee.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CoeActivityFee coeActivityFee = (CoeActivityFee) target;

		validateUuid(errors, coeActivityFee.getUuid());

	}

	/**
	 * CoeActivityFee validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.product.CoeActivityFee.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateMergeFee(CoeActivityFee coeActivityFee) {
		Integer currency = coeActivityFee.getCurrency();
		if(null == currency) {
			coeActivityFee.setSuccess(CoeActivityFee.SUCCESS_NO);
			coeActivityFee.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_FEE_CURRENCY_EMPTY);
			coeActivityFee.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_FEE_CURRENCY_EMPTY);
			return false;
		}
		
		boolean checked = validateExist(coeActivityFee);
		if(!checked) {
			return false;
		}
		Long id = coeActivityFee.getId();
		CoeActivityFee entity = coeActivityFeeWrapper.findById(id);
		if(null == entity) {
			coeActivityFee.setSuccess(CoeActivityFee.SUCCESS_NO);
			coeActivityFee.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_NOT_EXIST);
			coeActivityFee.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_NOT_EXIST);
			return false;
		}
		return true;
	}
	

	/**
	 * @param coeActivityFee
	 */
	private boolean validateExist(CoeActivityFee coeActivityFee) {
		Long userId = coeActivityFee.getLastModifiedBy();
		if(null == userId) {
			coeActivityFee.setSuccess(CoeActivityFee.SUCCESS_NO);
			coeActivityFee.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_LOGIN);
			coeActivityFee.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_USER_NOT_LOGIN);
			return false;
		}
		
		
		Long id=coeActivityFee.getId();
		if(null == id) {
			coeActivityFee.setSuccess(CoeActivityFee.SUCCESS_NO);
			coeActivityFee.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_FEE_ID_EMPTY);
			coeActivityFee.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_FEE_ID_EMPTY);
			return false;
		}
		
		return true;
	}
	
	public boolean validateMergeDescription(CoeActivityFee coeActivityFee) {
		
		boolean checked = validateExist(coeActivityFee);
		if(!checked) {
			return false;
		}
		
		String description = coeActivityFee.getDescription();
		if(StringUtils.isNotBlank(description) && description.length()>1024) {
			coeActivityFee.setSuccess(CoeActivityFee.SUCCESS_NO);
			coeActivityFee.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_FEE_DESCRIPTION_LENGTH_EXTRA);
			coeActivityFee.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_FEE_DESCRIPTION_LENGTH_EXTRA);
			return false;
		}
		
		return true;
	}

	
}
