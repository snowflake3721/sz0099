package dml.sz0099.course.app.client.validator.activity;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.activity.ParagActivityWrapper;
import dml.sz0099.course.app.client.wrapper.activity.ParagraphWrapper;
import dml.sz0099.course.app.client.wrapper.activity.CoeActivityWrapper;
import dml.sz0099.course.app.module.define.CoeActivityRespCode;
import dml.sz0099.course.app.persist.entity.order.CoeOrder;
import dml.sz0099.course.app.persist.entity.activity.ParagActivity;
import dml.sz0099.course.app.persist.entity.activity.Paragraph;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.bo.ParagActivityBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ParagActivityValidator 校验组件
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
public class ParagActivityValidator implements Validator {

	@Autowired
	private CoeActivityWrapper activityWrapper;
	
	@Autowired
	private ParagActivityWrapper paragActivityWrapper;
	
	@Autowired
	private ParagraphWrapper paragraphWrapper;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ParagActivityBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ParagActivityBo paragActivity = (ParagActivityBo) target;

		validateUuid(errors, paragActivity.getUuid());

	}

	/**
	 * ParagActivity validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.paragraph.ParagActivity.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateExistActivity(ParagActivity paragActivity) {
		
		Long activityId = paragActivity.getMainId();
		if(null == activityId) {
			paragActivity.setSuccess(CoeOrder.SUCCESS_NO);
			paragActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_COURSE_PERSONAL_ACTIVITY_NOT_EXIST);
			paragActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_COURSE_PERSONAL_ACTIVITY_NOT_EXIST);
			return false;
		}
		
		CoeActivity exist = activityWrapper.findById(activityId);
		if(null == exist) {
			paragActivity.setSuccess(CoeOrder.SUCCESS_NO);
			paragActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_COURSE_PERSONAL_ACTIVITY_NOT_EXIST);
			paragActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_COURSE_PERSONAL_ACTIVITY_NOT_EXIST);
			return false;
		}
		
		Long userId = paragActivity.getUserId();
		Long userIdEntity = exist.getUserId();
		if(userId == null) {
			paragActivity.setSuccess(CoeOrder.SUCCESS_NO);
			paragActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_LOGIN);
			paragActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_USER_NOT_LOGIN);
			return false;
		}
		
		if(userIdEntity==null) {
			paragActivity.setSuccess(CoeOrder.SUCCESS_NO);
			paragActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_COURSE_PARAGRAGH_PERSONAL_ACTIVITY_USERID_NULL);
			paragActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_COURSE_PARAGRAGH_PERSONAL_ACTIVITY_USERID_NULL);
			return false;
		}
		
		if(!userId.equals(userIdEntity)) {
			paragActivity.setSuccess(CoeOrder.SUCCESS_NO);
			paragActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_COURSE_PARAGRAGH_ACTIVITY_USERID_CONFLICT);
			paragActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_COURSE_PARAGRAGH_ACTIVITY_USERID_CONFLICT);
			return false;
		}
		
		return true;
	}
	
	public boolean validateAddProduct(ParagActivity paragActivity) {
		boolean checked = validateExistActivity(paragActivity);
		if(!checked) {
			return false;
		}
		Long activityId = paragActivity.getMainId();
		Long count = paragActivityWrapper.countByMainId(activityId);
		if(null != count && count>19) {
			paragActivity.setSuccess(CoeOrder.SUCCESS_NO);
			paragActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_COURSE_PARAGRAGH_TOTAL_EXTRA);
			paragActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_COURSE_PARAGRAGH_TOTAL_EXTRA);
			return false;
		}
		
		return true;
	}
	
	public boolean validateMergeProduct(ParagActivity paragActivity) {
		boolean checked = validateExistActivity(paragActivity);
		if(!checked) {
			return false;
		}
		
		Long paragId = paragActivity.getParagId();
		boolean exist = paragraphWrapper.existById(paragId);
		if(!exist) {
			paragActivity.setSuccess(CoeOrder.SUCCESS_NO);
			paragActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_COURSE_PARAGRAGH_NOT_EXIST);
			paragActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_COURSE_PARAGRAGH_NOT_EXIST);
			return false;
		}
		
		Paragraph paragraph = paragActivity.getParagraph();
		if(null == paragraph) {
			//段落上传数据不存在
			paragActivity.setSuccess(CoeOrder.SUCCESS_NO);
			paragActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_COURSE_PARAGRAGH_UPLOAD_NOT_EXIST);
			paragActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_COURSE_PARAGRAGH_UPLOAD_NOT_EXIST);
			return false;
		}
		return true;
	}
	
	public boolean validateForDelete(ParagActivity paragActivity) {
		boolean checked = validateExistActivity(paragActivity);
		if(!checked) {
			return false;
		}
		
		Long paragId = paragActivity.getParagId();
		boolean exist = paragraphWrapper.existById(paragId);
		if(!exist) {
			paragActivity.setSuccess(CoeOrder.SUCCESS_NO);
			paragActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_COURSE_PARAGRAGH_NOT_EXIST);
			paragActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_COURSE_PARAGRAGH_NOT_EXIST);
			return false;
		}
		
		Paragraph paragraph = paragActivity.getParagraph();
		if(null == paragraph) {
			//段落上传数据不存在
			paragActivity.setSuccess(CoeOrder.SUCCESS_NO);
			paragActivity.setRespCode(CoeActivityRespCode.SZ0099_CODE_COURSE_PARAGRAGH_UPLOAD_NOT_EXIST);
			paragActivity.setRespMsg(CoeActivityRespCode.SZ0099_MSG_COURSE_PARAGRAGH_UPLOAD_NOT_EXIST);
			return false;
		}
		return true;
	}
	
	public boolean validateForDeleteAll(ParagActivity paragActivity) {
		boolean checked = validateExistActivity(paragActivity);
		if(!checked) {
			return false;
		}
		return true;
	}

}
