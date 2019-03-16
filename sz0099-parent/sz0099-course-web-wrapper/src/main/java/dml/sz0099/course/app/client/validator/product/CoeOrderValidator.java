package dml.sz0099.course.app.client.validator.product;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jit4j.app.client.controller.shiro.basic.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.product.CoeOrderWrapper;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.order.CoeOrder;
import dml.sz0099.course.app.persist.entity.order.bo.CoeOrderBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeOrderValidator 校验组件
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
public class CoeOrderValidator implements Validator {

	@Autowired
	private CoeOrderWrapper coeOrderWrapper;
	
	@Autowired
	private LoginValidator loginValidator;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Object.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CoeOrderBo coeOrder = (CoeOrderBo) target;

		validateUuid(errors, coeOrder.getUuid());

	}

	/**
	 * CoeOrder validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.product.CoeOrder.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateForMyOrderList(Errors errors, CoeOrderBo orderBo){
		Date beginTime = orderBo.getBeginTime();
		Date endTime = orderBo.getEndTime();
		
		if(null == beginTime) {
			beginTime = DateUtils.truncate(new Date(), Calendar.DATE);
			endTime = DateUtils.addDays(beginTime, 1);
			//默认查一周的订单
			orderBo.setBeginTime(DateUtils.addDays(beginTime, -7));
			orderBo.setEndTime(endTime);
			return true;
		}
		
		if(null==endTime) {
			endTime = DateUtils.addDays(beginTime, 1);
			orderBo.setEndTime(endTime);
			return true;
		}
		
		long days = daysBetween(beginTime, endTime);
		if(days<=0) {
			endTime = DateUtils.addDays(beginTime, 1);
			orderBo.setEndTime(endTime);
		}else if(days>31) {
			endTime = DateUtils.addDays(beginTime, 31);
			orderBo.setEndTime(endTime);
		}
		
		return true;
	}
	
	public static long daysBetween(Date begin, Date end) {
		long beginTime = begin.getTime();
		long endTime = end.getTime();
		long days = (endTime-beginTime)/(1000 * 3600 * 24);
		return days;
	}
	
	public boolean validatePayOrder(Errors errors, CoeOrder order) {
		
		Long id = order.getId();
		CoeOrder entity = coeOrderWrapper.findById(id);
		if(null == entity) {
			order.setSuccess(CoeOrder.SUCCESS_NO);
			order.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_NOT_EXIST);
			order.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_NOT_EXIST);
			return false;
		}
		
		Long userId = order.getUserId();
		
		if(null == userId) {
			order.setSuccess(CoeOrder.SUCCESS_NO);
			order.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_NOT_LOGIN);
			order.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_NOT_LOGIN);
			return false;
		}
		
		Long entityUserId = entity.getUserId();
		
		if(!userId.equals(entityUserId)) {
			order.setSuccess(CoeOrder.SUCCESS_NO);
			order.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_USERID_CONFICT);
			order.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_USERID_CONFICT);
			return false;
		}
		
		boolean emailChecked = validateEmail(errors, order);
		if(!emailChecked) {
			return false;
		}
		
		String mobile = order.getMobile();
		boolean mobileChecked = loginValidator.validateMobileFormat(errors, mobile);
		if(!mobileChecked) {
			ObjectError oe = errors.getAllErrors().get(0);
			order.setSuccess(CoeOrder.SUCCESS_NO);
			order.setRespCode(oe.getCode());
			order.setRespMsg(oe.getDefaultMessage());
		}
		
		Integer status = entity.getStatus();
		if(!CoeOrder.STATUS_PAY_WAIT.equals(status)) {
			if(CoeOrder.STATUS_PAY_PAYED.equals(status)) {
				order.setSuccess(CoeOrder.SUCCESS_NO);
				order.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_HAS_PAYED);
				order.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_HAS_PAYED);
				return false;
			}else if(CoeOrder.STATUS_PAY_INPROCESS.equals(status)) {
				order.setSuccess(CoeOrder.SUCCESS_NO);
				order.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_HAS_INPROCESS);
				order.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_HAS_INPROCESS);
				return false;
			}
			else if(CoeOrder.STATUS_PAY_SENT.equals(status)) {
				order.setSuccess(CoeOrder.SUCCESS_NO);
				order.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_HAS_SENT);
				order.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_HAS_SENT);
				return false;
			}else if(CoeOrder.STATUS_PAY_RECIEVED.equals(status)) {
				order.setSuccess(CoeOrder.SUCCESS_NO);
				order.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_HAS_RECIEVED);
				order.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_HAS_RECIEVED);
				return false;
			}else if(CoeOrder.STATUS_PAY_TM_RECIEVED.equals(status)) {
				order.setSuccess(CoeOrder.SUCCESS_NO);
				order.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_HAS_TM_RECIEVED);
				order.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_HAS_TM_RECIEVED);
				return false;
			}
		}
		
		return true;
	}

	/**
	 * @param errors
	 * @param order
	 */
	private boolean validateEmail(Errors errors, CoeOrder order) {
		String email = order.getEmail();
		
		boolean emailChecked = loginValidator.validateEmailFormat(errors, email);
		if(!emailChecked) {
			ObjectError oe = errors.getAllErrors().get(0);
			order.setSuccess(CoeOrder.SUCCESS_NO);
			order.setRespCode(oe.getCode());
			order.setRespMsg(oe.getDefaultMessage());
			return false;
		}
		return true;
	}
	
	public boolean validateForQueryPullCode(Errors errors, CoeOrder order) {
		
		Long id = order.getId();
		CoeOrder entity = coeOrderWrapper.findById(id);
		if(null == entity) {
			order.setSuccess(CoeOrder.SUCCESS_NO);
			order.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_NOT_EXIST);
			order.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_NOT_EXIST);
			return false;
		}
		
		Long userId = entity.getUserId();
		Long userIdCurrent = order.getUserId();
		if(!(userId != null && userIdCurrent!=null && userId.equals(userIdCurrent))) {
			order.setSuccess(CoeOrder.SUCCESS_NO);
			order.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_INVALID_USERID);
			order.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_INVALID_USERID);
			return false;
		}
		
		Integer status = entity.getStatus();
		if(status<CoeOrder.STATUS_PAY_SENT) {
			order.setSuccess(CoeOrder.SUCCESS_NO);
			order.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_NO_SENT);
			order.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_NO_SENT);
			return false;
		}
		
		//过期校验1
		Date expiredTime = entity.getExpiredTime();
		Date current = new Date();
		if(expiredTime==null) {
			order.setSuccess(CoeOrder.SUCCESS_NO);
			order.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_NULL_EXPIRED);
			order.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_NULL_EXPIRED);
			return false;
		}
		
		if(current.after(expiredTime)) {
			order.setSuccess(CoeOrder.SUCCESS_NO);
			order.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_EXPIRED);
			order.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_EXPIRED);
			return false;
		}
		//过期校验2
		Integer pullStatus = entity.getPullStatus();
		if(pullStatus>=CoeOrder.PULL_STATUS_EXPIRED) {
			order.setSuccess(CoeOrder.SUCCESS_NO);
			order.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_EXPIRED);
			order.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_EXPIRED);
			return false;
		}
		
		Integer flowStatus = entity.getFlowStatus();
		
		if(CoeOrder.FLOW_STATUS_CLOSED.equals(flowStatus)) {
			order.setSuccess(CoeOrder.SUCCESS_NO);
			order.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_CLOSED);
			order.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_CLOSED);
			return false;
		}
		
		if(CoeOrder.FLOW_STATUS_CANCEL.equals(flowStatus)) {
			order.setSuccess(CoeOrder.SUCCESS_NO);
			order.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_CANCEL);
			order.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_CANCEL);
			return false;
		}
		
		//order.setPullCode(entity.getPullCode());
		//order.setLink(entity.getLink());
		
		return true;
	}
	
	public boolean validateForOrderEmail(Errors errors, CoeOrder order, boolean isDo) {
			
			Long id = order.getId();
			CoeOrder entity = coeOrderWrapper.findById(id);
			if(null == entity) {
				order.setSuccess(CoeOrder.SUCCESS_NO);
				order.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_NOT_EXIST);
				order.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_NOT_EXIST);
				return false;
			}
			
			Long userId = entity.getUserId();
			Long userIdCurrent = order.getUserId();
			if(!(userId != null && userIdCurrent!=null && userId.equals(userIdCurrent))) {
				order.setSuccess(CoeOrder.SUCCESS_NO);
				order.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_INVALID_USERID);
				order.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_INVALID_USERID);
				return false;
			}
			
			Integer status = entity.getStatus();
			if(status>=CoeOrder.STATUS_PAY_SENT) {
				order.setSuccess(CoeOrder.SUCCESS_NO);
				order.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_SENT);
				order.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_SENT);
				return false;
			}
			
			//过期校验2
			Integer pullStatus = entity.getPullStatus();
			if(pullStatus>=CoeOrder.PULL_STATUS_EXPIRED) {
				order.setSuccess(CoeOrder.SUCCESS_NO);
				order.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_EXPIRED);
				order.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_EXPIRED);
				return false;
			}
			
			Integer flowStatus = entity.getFlowStatus();
			
			if(CoeOrder.FLOW_STATUS_COMPLETED <= flowStatus) {
				order.setSuccess(CoeOrder.SUCCESS_NO);
				order.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_FINISHED);
				order.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_FINISHED);
				return false;
			}
			
			if(!isDo) {
				order.setEmail(entity.getEmail());
				order.setStatus(entity.getStatus());
			}
			return true;
		}
	
	public boolean validateForModifyEmail(Errors errors, CoeOrder order) {
		boolean checked = validateForOrderEmail(errors,order, true);
		
		if(!checked) {
			return false;
		}
		
		boolean emailChecked = validateEmail(errors, order);
		if(!emailChecked) {
			return false;
		}
		
		return true;
	}
	
	public boolean validateForInprocess(Errors errors, CoeOrder order) {
		
		Long id = order.getId();
		CoeOrder entity = coeOrderWrapper.findById(id);
		if(null == entity) {
			order.setSuccess(CoeOrder.SUCCESS_NO);
			order.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_NOT_EXIST);
			order.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_NOT_EXIST);
			return false;
		}
		
		Long ownerId = entity.getOwnerId();
		Long ownerIdCurrent = order.getOwnerId();
		if(!(ownerId != null && ownerIdCurrent!=null && ownerId.equals(ownerIdCurrent))) {
			order.setSuccess(CoeOrder.SUCCESS_NO);
			order.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_INVALID_USERID);
			order.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_INVALID_USERID);
			return false;
		}
		
		Integer status = entity.getStatus();
		if(status>CoeOrder.STATUS_PAY_PAYED) {
			order.setSuccess(CoeOrder.SUCCESS_NO);
			order.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_HAS_IN_PROCESS);
			order.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_HAS_IN_PROCESS);
			return false;
		}
		
		return true;
	}
	
public boolean validateForSent(Errors errors, CoeOrder order) {
		
		Long id = order.getId();
		CoeOrder entity = coeOrderWrapper.findById(id);
		if(null == entity) {
			order.setSuccess(CoeOrder.SUCCESS_NO);
			order.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_NOT_EXIST);
			order.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_NOT_EXIST);
			return false;
		}
		
		Long ownerId = entity.getOwnerId();
		Long ownerIdCurrent = order.getOwnerId();
		if(!(ownerId != null && ownerIdCurrent!=null && ownerId.equals(ownerIdCurrent))) {
			order.setSuccess(CoeOrder.SUCCESS_NO);
			order.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_INVALID_USERID);
			order.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_INVALID_USERID);
			return false;
		}
		
		Integer status = entity.getStatus();
		if(status>CoeOrder.STATUS_PAY_INPROCESS) {
			order.setSuccess(CoeOrder.SUCCESS_NO);
			order.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_HAS_IN_PROCESS);
			order.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_HAS_IN_PROCESS);
			return false;
		}
		
		return true;
	}

}
