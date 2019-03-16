package dml.sz0099.course.app.client.validator.activity;

import org.apache.commons.lang3.StringUtils;
import org.jit4j.app.module.define.Jit4jRespCode;
import org.jit4j.app.persist.entity.order.Order;
import org.jit8j.core.util.IdentityCodeUtil;
import org.jit8j.core.util.MobileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.activity.CoeActivityOrderWrapper;
import dml.sz0099.course.app.client.wrapper.activity.CoeActivityUserWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeUserGradeWrapper;
import dml.sz0099.course.app.module.define.CoeActivityRespCode;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityOrder;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityUser;
import dml.sz0099.course.app.persist.entity.user.CoeUser;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeActivityUserValidator 校验组件
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
public class CoeActivityUserValidator implements Validator {
	
	@Autowired
	private CoeActivityUserWrapper coeActivityUserWrapper;
	
	@Autowired
	private CoeActivityOrderWrapper coeActivityOrderWrapper;
	
	@Autowired
	private CoeUserGradeWrapper coeUserGradeWrapper;

	@Override
	public boolean supports(Class<?> clazz) {
		return CoeActivityUser.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CoeActivityUser coeActivityUser = (CoeActivityUser) target;

		validateUuid(errors, coeActivityUser.getUuid());

	}

	/**
	 * CoeActivityUser validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.product.CoeActivityUser.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateMergeMobile(CoeActivityUser coeActivityUser) {
		boolean mobileChecked = validateMobile(coeActivityUser);
		if(!mobileChecked) {
			return false;
		}
		
		boolean checked = validateExist(coeActivityUser);
		if(!checked) {
			return false;
		}
		Long id = coeActivityUser.getId();
		CoeActivityUser entity = coeActivityUserWrapper.findById(id);
		if(null == entity) {
			coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_NO);
			coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ORDER_ACT_USER_ID_EMPTY);
			coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ORDER_ACT_USER_ID_EMPTY);
			return false;
		}
		boolean statusChecked=validateStatus(coeActivityUser, entity);
		if(!statusChecked) {
			return false;
		}
		return true;
	}

	/**
	 * @param coeActivityUser
	 */
	public boolean validateMobile(CoeActivityUser coeActivityUser) {
		String mobile = coeActivityUser.getMobile();
		if(StringUtils.isBlank(mobile)) {
			coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_NO);
			coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ORDER_ACT_USER_MOBILE_EMPTY);
			coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ORDER_ACT_USER_MOBILE_EMPTY);
			return false;
		}
		if(!MobileUtil.isValidMobile(mobile)) {
			coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_NO);
			coeActivityUser.setRespCode(Jit4jRespCode.JIT4J_CODE_MOBILE_INVALID);
			coeActivityUser.setRespMsg(Jit4jRespCode.JIT4J_MSG_MOBILE_INVALID);
			return false;
		}
		return true;
	}
	
	public boolean validateMergeRealname(CoeActivityUser coeActivityUser) {
		boolean realnameChecked = validateRealname(coeActivityUser);
		if(!realnameChecked) {
			return false;
		}
		boolean checked = validateExist(coeActivityUser);
		if(!checked) {
			return false;
		}
		Long id = coeActivityUser.getId();
		CoeActivityUser entity = coeActivityUserWrapper.findById(id);
		if(null == entity) {
			coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_NO);
			coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ORDER_ACT_USER_NOT_EXIST);
			coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ORDER_ACT_USER_NOT_EXIST);
			return false;
		}
		boolean statusChecked=validateStatus(coeActivityUser, entity);
		if(!statusChecked) {
			return false;
		}
		return true;
	}

	/**
	 * @param coeActivityUser
	 */
	public boolean validateRealname(CoeActivityUser coeActivityUser) {
		String realname = coeActivityUser.getRealname();
		if(StringUtils.isBlank(realname)) {
			coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_NO);
			coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ORDER_ACT_USER_REALNAME_EMPTY);
			coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ORDER_ACT_USER_REALNAME_EMPTY);
			return false;
		}
		
		if(realname.length()>16) {
			coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_NO);
			coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ORDER_ACT_USER_REALNAME_LENGTH_EXTRA);
			coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ORDER_ACT_USER_REALNAME_LENGTH_EXTRA);
			return false;
		}
		return true;
	}
	
	public boolean validateMergeIdentity(CoeActivityUser coeActivityUser) {
		boolean identityChecked = validateIdentity(coeActivityUser);
		if(!identityChecked) {
			return false;
		}
		
		boolean checked = validateExist(coeActivityUser);
		if(!checked) {
			return false;
		}
		Long id = coeActivityUser.getId();
		Long baseId=coeActivityUser.getBaseId();
		String identity=coeActivityUser.getIdentity();
		CoeActivityUser entity = coeActivityUserWrapper.findByBaseIdAndIdentity(baseId,identity);
		
		if(null != entity) {
			Long entityId=entity.getId();
			if(!entityId.equals(id)) {
				coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_NO);
				coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ORDER_ACT_USER_IDENTITY_EXIST);
				coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ORDER_ACT_USER_IDENTITY_EXIST);
				return false;
			}
		}
		if(null == entity) {
			entity=coeActivityUserWrapper.findById(id);
			if(null == entity) {
				coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_NO);
				coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ORDER_ACT_USER_NOT_EXIST);
				coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ORDER_ACT_USER_NOT_EXIST);
				return false;
			}
		}
		boolean statusChecked=validateStatus(coeActivityUser, entity);
		if(!statusChecked) {
			return false;
		}
		
		Long mainId = coeActivityUser.getMainId();
		boolean eixst = coeActivityUserWrapper.existEffectiveUser(mainId, identity, baseId);
		if(eixst) {
			coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_NO);
			coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ORDER_ACT_USER_REPEATED_CONFIRMED);
			coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ORDER_ACT_USER_REPEATED_CONFIRMED);
			return false;
		}
		
		return true;
	}

	/**
	 * @param coeActivityUser
	 */
	public boolean validateIdentity(CoeActivityUser coeActivityUser) {
		String identity = coeActivityUser.getIdentity();
		if(StringUtils.isBlank(identity)) {
			coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_NO);
			coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ORDER_ACT_USER_IDENTITY_EMPTY);
			coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ORDER_ACT_USER_IDENTITY_EMPTY);
			return false;
		}
		
		boolean identityChecked = IdentityCodeUtil.isValidIdNo(identity);
		if (!identityChecked) {
			coeActivityUser.setSuccess(CoeUser.SUCCESS_NO);
			coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ORDER_ACT_USER_IDENTITY_INVALID);
			coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ORDER_ACT_USER_IDENTITY_INVALID);
			return false;
		}
		return true;
	}
	
	public boolean validateForConfirm(CoeActivityUser coeActivityUser) {
		Long id=coeActivityUser.getId();
		if(null == id) {
			coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_NO);
			coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ORDER_ACT_USER_ID_EMPTY);
			coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ORDER_ACT_USER_ID_EMPTY);
			return false;
		}
		
		boolean mobileChecked=validateMobile(coeActivityUser);
		if(!mobileChecked) {
			return false;
		}
		boolean realnameChecked=validateRealname(coeActivityUser);
		if(!realnameChecked) {
			return false;
		}
		boolean identityChecked=validateIdentity(coeActivityUser);
		if(!identityChecked) {
			return false;
		}
		
		Long baseId = coeActivityUser.getBaseId();
		String identity = coeActivityUser.getIdentity();
		CoeActivityUser user = coeActivityUserWrapper.findByBaseIdAndIdentity(baseId, identity);
		if(null != user) {
			boolean statusChecked=validateStatus(coeActivityUser, user);
			if(!statusChecked) {
				return false;
			}
		}
		
		//validateStatus查询当前活动下，用户的身份证号，与
		Long mainId = coeActivityUser.getMainId();
		boolean eixst = coeActivityUserWrapper.existEffectiveUser(mainId, identity, baseId);
		if(eixst) {
			coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_NO);
			coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ORDER_ACT_USER_REPEATED_CONFIRMED);
			coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ORDER_ACT_USER_REPEATED_CONFIRMED);
			return false;
		}
		
		return true;
	}

	/**
	 * @param coeActivityUser
	 * @param entity
	 */
	private boolean validateStatus(CoeActivityUser coeActivityUser, CoeActivityUser entity) {
		Integer status = entity.getStatus();
		if(status>Order.ORDER_STATUS_INIT.getValueInt() 
			) {
			coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_NO);
			coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ORDER_ACT_USER_HAS_CONFIRMED);
			coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ORDER_ACT_USER_HAS_CONFIRMED);
			return false;
		}
		return true;
	}
	

	/**
	 * @param coeActivityUser
	 */
	private boolean validateExist(CoeActivityUser coeActivityUser) {
		Long userId = coeActivityUser.getLastModifiedBy();
		if(null == userId) {
			coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_NO);
			coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_LOGIN);
			coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_USER_NOT_LOGIN);
			return false;
		}
		
		Long id=coeActivityUser.getId();
		if(null == id) {
			coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_NO);
			coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ORDER_ACT_USER_ID_EMPTY);
			coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ORDER_ACT_USER_ID_EMPTY);
			return false;
		}
		
		return true;
	}
	
	public boolean validateAddUser(CoeActivityUser coeActivityUser) {
		
		Long userId = coeActivityUser.getCreatedBy();
		if(null == userId) {
			coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_NO);
			coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_LOGIN);
			coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_USER_NOT_LOGIN);
			return false;
		}
		
		Long mainId = coeActivityUser.getMainId();
		Long baseId=coeActivityUser.getBaseId();
		if(null == mainId) {
			coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_NO);
			coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_MAINID_EMPTY);
			coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_MAINID_EMPTY);
			return false;
		}
		if(null == baseId) {
			coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_NO);
			coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ORDER_ACT_USER_BASEID_EMPTY);
			coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ORDER_ACT_USER_BASEID_EMPTY);
			return false;
		}
		
		
		CoeActivityOrder  entity = coeActivityOrderWrapper.findById(baseId);
		if(null == entity) {
			coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_NO);
			coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_ID_NOT_EXIST);
			coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_ID_NOT_EXIST);
			return false;
		}
		
		Integer status = entity.getOrderStatus();
		if(Order.ORDER_STATUS_INIT.getValueInt() != status) {
			coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_NO);
			coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_STATUS_CHANGED);
			coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_STATUS_CHANGED);
			return false;
		}
		
		return true;
	}
	
	public boolean validateDeleteUser( CoeActivityUser coeActivityUser) {
			
			Long userId = coeActivityUser.getLastModifiedBy();
			if(null == userId) {
				coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_NO);
				coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_LOGIN);
				coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_USER_NOT_LOGIN);
				return false;
			}
			
			
			Long id = coeActivityUser.getId();
			if(null == id) {
				coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_NO);
				coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ORDER_ACT_USER_ID_EMPTY);
				coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ORDER_ACT_USER_ID_EMPTY);
				return false;
			}
			
			Long mainId = coeActivityUser.getMainId();
			if(null == mainId) {
				coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_NO);
				coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_MAINID_EMPTY);
				coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_MAINID_EMPTY);
				return false;
			}
			
			Long baseId=coeActivityUser.getBaseId();
			if(null == baseId) {
				coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_NO);
				coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ORDER_ACT_USER_BASEID_EMPTY);
				coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ORDER_ACT_USER_BASEID_EMPTY);
				return false;
			}
			CoeActivityOrder  entity = coeActivityOrderWrapper.findById(baseId);
			if(null == entity) {
				coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_NO);
				coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_ID_NOT_EXIST);
				coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_ID_NOT_EXIST);
				return false;
			}
			
			Integer status = entity.getOrderStatus();
			if(CoeActivityOrder.STATUS_INIT.getValueInt() != status) {
				coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_NO);
				coeActivityUser.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_STATUS_CHANGED);
				coeActivityUser.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_STATUS_CHANGED);
				return false;
			}
			
			return true;
	}

	
}
