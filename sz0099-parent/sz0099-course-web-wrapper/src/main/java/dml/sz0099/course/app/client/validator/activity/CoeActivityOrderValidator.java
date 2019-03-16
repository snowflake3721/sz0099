package dml.sz0099.course.app.client.validator.activity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jit4j.app.persist.entity.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.activity.CoeActivityOrderWrapper;
import dml.sz0099.course.app.client.wrapper.activity.CoeActivityUserWrapper;
import dml.sz0099.course.app.client.wrapper.activity.CoeActivityWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeUserGradeWrapper;
import dml.sz0099.course.app.module.define.CoeActivityRespCode;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityOrder;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTime;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityUser;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeActivityOrderValidator 校验组件
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
public class CoeActivityOrderValidator implements Validator {
	
	@Autowired
	private CoeActivityOrderWrapper coeActivityOrderWrapper;
	
	@Autowired
	private CoeActivityUserValidator coeActivityUserValidator;
	
	@Autowired
	private CoeActivityWrapper coeActivityWrapper;
	
	@Autowired
	private CoeActivityUserWrapper coeActivityUserWrapper;
	
	@Autowired
	private CoeUserGradeWrapper coeUserGradeWrapper;

	@Override
	public boolean supports(Class<?> clazz) {
		return CoeActivityOrder.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CoeActivityOrder coeActivityOrder = (CoeActivityOrder) target;

		validateUuid(errors, coeActivityOrder.getUuid());

	}

	/**
	 * CoeActivityOrder validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.product.CoeActivityOrder.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateMergePayTime(CoeActivityOrder coeActivityOrder) {
		Date payTime = coeActivityOrder.getPayTime();
		if(null == payTime) {
			coeActivityOrder.setSuccess(CoeActivityOrder.SUCCESS_NO);
			coeActivityOrder.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_PAYTIME_EMPTY);
			coeActivityOrder.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_PAYTIME_EMPTY);
			return false;
		}
		
		boolean checked = validateExist(coeActivityOrder);
		if(!checked) {
			return false;
		}
		Long id = coeActivityOrder.getId();
		CoeActivityOrder entity = coeActivityOrderWrapper.findById(id);
		if(null == entity) {
			coeActivityOrder.setSuccess(CoeActivityOrder.SUCCESS_NO);
			coeActivityOrder.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_ID_NOT_EXIST);
			coeActivityOrder.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_ID_NOT_EXIST);
			return false;
		}
		return true;
	}
	
	public boolean validateMergeExeTime(CoeActivityOrder coeActivityOrder) {
		Date endTime = coeActivityOrder.getExeTime();
		if(null == endTime) {
			coeActivityOrder.setSuccess(CoeActivityOrder.SUCCESS_NO);
			coeActivityOrder.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_EXETIME_EMPTY);
			coeActivityOrder.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_EXETIME_EMPTY);
			return false;
		}
		
		boolean checked = validateExist(coeActivityOrder);
		if(!checked) {
			return false;
		}
		Long id = coeActivityOrder.getId();
		CoeActivityOrder entity = coeActivityOrderWrapper.findById(id);
		if(null == entity) {
			coeActivityOrder.setSuccess(CoeActivityOrder.SUCCESS_NO);
			coeActivityOrder.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_ID_NOT_EXIST);
			coeActivityOrder.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_ID_NOT_EXIST);
			return false;
		}
		return true;
	}
	
	public boolean validateMergeFinishTime(CoeActivityOrder coeActivityOrder) {
		Date offTime = coeActivityOrder.getFinishTime();
		if(null == offTime) {
			coeActivityOrder.setSuccess(CoeActivityOrder.SUCCESS_NO);
			coeActivityOrder.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_FINISHTIME_EMPTY);
			coeActivityOrder.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_FINISHTIME_EMPTY);
			return false;
		}
		
		boolean checked = validateExist(coeActivityOrder);
		if(!checked) {
			return false;
		}
		Long id = coeActivityOrder.getId();
		CoeActivityOrder entity = coeActivityOrderWrapper.findById(id);
		if(null == entity) {
			coeActivityOrder.setSuccess(CoeActivityOrder.SUCCESS_NO);
			coeActivityOrder.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_ID_NOT_EXIST);
			coeActivityOrder.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_ID_NOT_EXIST);
			return false;
		}
		
		return true;
	}

	/**
	 * @param coeActivityOrder
	 */
	private boolean validateExist(CoeActivityOrder coeActivityOrder) {
		Long userId = coeActivityOrder.getLastModifiedBy();
		if(null == userId) {
			coeActivityOrder.setSuccess(CoeActivityOrder.SUCCESS_NO);
			
			coeActivityOrder.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_LOGIN);
			coeActivityOrder.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_USER_NOT_LOGIN);
			return false;
		}
		
		
		Long id=coeActivityOrder.getId();
		if(null == id) {
			coeActivityOrder.setSuccess(CoeActivityOrder.SUCCESS_NO);
			coeActivityOrder.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_ID_EMPTY);
			coeActivityOrder.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_ID_EMPTY);
			return false;
		}
		
		return true;
	}
	
	public boolean validateAddOrder(CoeActivityOrder coeActivityOrder) {
		
		Long userId = coeActivityOrder.getCreatedBy();
		if(null == userId) {
			coeActivityOrder.setSuccess(CoeActivityOrder.SUCCESS_NO);
			coeActivityOrder.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_LOGIN);
			coeActivityOrder.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_USER_NOT_LOGIN);
			return false;
		}
		
		Long mainId = coeActivityOrder.getMainId();
		if(null == mainId) {
			coeActivityOrder.setSuccess(CoeActivityOrder.SUCCESS_NO);
			coeActivityOrder.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_MAINID_EMPTY);
			coeActivityOrder.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_MAINID_EMPTY);
			return false;
		}
		
		CoeActivity  entity = coeActivityWrapper.findById(mainId);
		if(null == entity) {
			coeActivityOrder.setSuccess(CoeActivityOrder.SUCCESS_NO);
			coeActivityOrder.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_MAINID_EMPTY);
			coeActivityOrder.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_MAINID_EMPTY);
			return false;
		}
		
		return true;
	}
	
	public boolean validatePrePay(CoeActivityOrder order) {
		Long userId = order.getLastModifiedBy();
		if(null == userId) {
			order.setSuccess(CoeActivityOrder.SUCCESS_NO);
			order.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_LOGIN);
			order.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_USER_NOT_LOGIN);
			return false;
		}
		
		Long id=order.getId();
		if(null == id) {
			order.setSuccess(CoeActivityOrder.SUCCESS_NO);
			order.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_ID_EMPTY);
			order.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_ID_EMPTY);
			return false;
		}
		
		CoeActivityOrder entity = coeActivityOrderWrapper.findById(id);
		if(null == entity) {
			order.setSuccess(CoeActivityOrder.SUCCESS_NO);
			order.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_ID_NOT_EXIST);
			order.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_ID_NOT_EXIST);
			return false;
		}
		
		Integer status = entity.getOrderStatus();
		if(!Order.ORDER_STATUS_GENERATED.getValueInt().equals(status)
			&& !Order.ORDER_STATUS_PAY_BEGIN.getValueInt().equals(status)
			&& !Order.ORDER_STATUS_CHECKOUT.getValueInt().equals(status)
			&& !Order.ORDER_STATUS_PAY_FAILURE.getValueInt().equals(status)
				) {
			order.setSuccess(CoeActivityOrder.SUCCESS_NO);
			order.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_WHENPAY_STATUS_INVALID);
			order.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_WHENPAY_STATUS_INVALID);
			return false;
		}
		
		Long mainId = entity.getMainId();
		Long payedNum=coeActivityUserWrapper.countPayVerifyUsers(mainId);
		Long currentNum=coeActivityUserWrapper.countByBaseId(id);
		
		CoeActivity coeActivity = coeActivityWrapper.findByIdWithTimeAndFee(mainId);
		Integer maxNum = coeActivity.getMaxNum();
		if(null != maxNum && null != payedNum) {
			if(payedNum + currentNum > maxNum) {
				order.setSuccess(CoeActivityOrder.SUCCESS_NO);
				order.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_USER_UPTO_MAXNUM);
				order.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_USER_UPTO_MAXNUM);
				return false;
			}
		}
		
		CoeActivityTime actTime = coeActivity.getActTime();
		Date offTime = actTime.getOffTime();
		Date current = new Date();
		if(null != offTime && current.after(offTime)) {
			order.setSuccess(CoeActivityOrder.SUCCESS_NO);
			order.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_OFFTIME_END);
			order.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_OFFTIME_END);
			return false;
		}
		
		return true;
	}
	
	public boolean validateCancelOrder(CoeActivityOrder order) {
		boolean baseChecked = validateBase(order);
		if(!baseChecked) {
			return false;
		}
		Long id = order.getId();
		CoeActivityOrder orderEntity = coeActivityOrderWrapper.findById(id);
		if(null != orderEntity) {
			Integer status = orderEntity.getOrderStatus();
			if(status != CoeActivityOrder.STATUS_PAY_WAIT.getValueInt() 
			) {
				order.setSuccess(CoeActivityOrder.SUCCESS_NO);
				order.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_STATUS_CANNOT_CANCEL);
				order.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_STATUS_CANNOT_CANCEL);
				return false;
			}
		}
		
		return true;
	}
	
	public boolean validateConfirmOrder(CoeActivityOrder order) {
		boolean baseChecked = validateBase(order);
		if(!baseChecked) {
			return false;
		}
		
		Long id = order.getId();
		CoeActivityOrder orderEntity = coeActivityOrderWrapper.findById(id);
		if(null != orderEntity) {
			Integer status = orderEntity.getOrderStatus();
			if(CoeActivityOrder.STATUS_INIT.getValueInt()!=status) {
				order.setSuccess(CoeActivityOrder.SUCCESS_NO);
				order.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_STATUS_INVALID);
				order.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_STATUS_INVALID);
				return false;
			}
		}
		
		List<CoeActivityUser>  userList = order.getUserList();
		if(null == userList) {
			order.setSuccess(CoeActivityOrder.SUCCESS_NO);
			order.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ORDER_ACT_USER_EMPTY);
			order.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ORDER_ACT_USER_EMPTY);
			return false;
		}
		
		Map<String,CoeActivityUser> map=new HashMap<>(userList.size());
		for(CoeActivityUser user : userList) {
			user.setBaseId(id);
			boolean checked = coeActivityUserValidator.validateForConfirm(user);
			if(!checked) {
				order.setSuccess(CoeActivityOrder.SUCCESS_NO);
				order.setRespCode(user.getRespCode());
				order.setRespMsg(user.getRespMsg());
				return false;
			}
			String identity = user.getIdentity();
			CoeActivityUser exist = map.get(identity);
			if(null == exist) {
				map.put(identity, user);
			}else {
				order.setSuccess(CoeActivityOrder.SUCCESS_NO);
				order.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ORDER_ACT_USER_IDENTITY_EXIST);
				order.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ORDER_ACT_USER_IDENTITY_REPEATED);
				return false;
			}
			
		}
		
		return true;
	}

	/**
	 * @param order
	 * @return
	 */
	private boolean validateBase(CoeActivityOrder order) {
		Long userId = order.getLastModifiedBy();
		if(null == userId) {
			order.setSuccess(CoeActivityOrder.SUCCESS_NO);
			order.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_LOGIN);
			order.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_USER_NOT_LOGIN);
			return false;
		}
		
		Long mainId = order.getMainId();
		if(null == mainId) {
			order.setSuccess(CoeActivityOrder.SUCCESS_NO);
			order.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_MAINID_EMPTY);
			order.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_MAINID_EMPTY);
			return false;
		}
		
		Long id=order.getId();
		if(null == id) {
			order.setSuccess(CoeActivityOrder.SUCCESS_NO);
			order.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_ID_EMPTY);
			order.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_ID_EMPTY);
			return false;
		}
		return true;
	}
	
	public boolean validateDeleteOrder( CoeActivityOrder coeActivityOrder) {
			
			Long userId = coeActivityOrder.getCreatedBy();
			if(null == userId) {
				coeActivityOrder.setSuccess(CoeActivityOrder.SUCCESS_NO);
				coeActivityOrder.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_LOGIN);
				coeActivityOrder.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_USER_NOT_LOGIN);
				return false;
			}
			
			Long id = coeActivityOrder.getId();
			if(null == id) {
				coeActivityOrder.setSuccess(CoeActivityOrder.SUCCESS_NO);
				coeActivityOrder.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_ID_EMPTY);
				coeActivityOrder.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_ID_EMPTY);
				return false;
			}
			
			Long mainId = coeActivityOrder.getMainId();
			if(null == mainId) {
				coeActivityOrder.setSuccess(CoeActivityOrder.SUCCESS_NO);
				coeActivityOrder.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACT_ORDER_MAINID_EMPTY);
				coeActivityOrder.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACT_ORDER_MAINID_EMPTY);
				return false;
			}
			
			return true;
	}

	
}
