package dml.sz0099.course.app.client.validator.position;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.position.PositionExtendWrapper;
import dml.sz0099.course.app.module.define.CourseRespCode;
import dml.sz0099.course.app.persist.entity.position.PositionExtend;
import dml.sz0099.course.app.persist.entity.position.PositionExtend;
import dml.sz0099.course.app.persist.entity.position.bo.PositionExtendBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * PositionExtendValidator 校验组件
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
public class PositionExtendValidator implements Validator {
	
	@Autowired
	private PositionExtendWrapper positionExtendWrapper;

	@Override
	public boolean supports(Class<?> clazz) {
		return PositionExtendBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		PositionExtendBo positionExtend = (PositionExtendBo) target;

		validateUuid(errors, positionExtend.getUuid());

	}

	/**
	 * PositionExtend validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.position.PositionExtend.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateExtendForPersist(PositionExtend extend) {
		
		Long id = extend.getId();
		Long userId = extend.getUserId();
		
		if(null == userId) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_EXTEND_USER_NOT_LOGIN);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_EXTEND_USER_NOT_LOGIN);
			extend.setSuccess(PositionExtend.SUCCESS_NO);
			return false;
		}
		
		if(null != id) {
			PositionExtend exist = positionExtendWrapper.findById(id);
			if(exist != null) {
				extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_EXTEND_VARIETY_EXIST);
				extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_EXTEND_VARIETY_EXIST);
				//项目类别已存在，则应返回该类别
				
				Long existUserId = exist.getUserId();
				if(!userId.equals(existUserId)) {
					extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_EXTEND_NOT_SELF);
					extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_EXTEND_NOT_SELF);
					extend.setSuccess(PositionExtend.SUCCESS_NO);
					return false;
				}
				
			}
		}
		
		/*Long userNum = positionExtendWrapper.countByUserId(userId);
		if(userNum>PositionExtend.VARIETYMAXNUM_DEFAULT) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_EXTEND_VARIETY_EXTRA);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_EXTEND_VARIETY_EXTRA);
			extend.setSuccess(PositionExtend.SUCCESS_NO);
			return false;
		}*/
		return true;
	}
	
	
	public boolean validateExtend(PositionExtend extend) {
		validatePositionExtend(extend);
		return PositionExtend.SUCCESS_YES==extend.getSuccess();
	}
	
	public PositionExtend validatePositionExtend(PositionExtend extend) {
		String devId = StringUtils.trim(extend.getDevId());
		if(StringUtils.isBlank(devId)) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_DEVID_EMPTY);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_DEVID_EMPTY);
			extend.setSuccess(PositionExtend.SUCCESS_NO);
			return extend;
		}
		
		
		Long id = extend.getId();
		Long positionId = extend.getPositionId();
		PositionExtend positionExtend = null;

		if(null != id ) {
			positionExtend = positionExtendWrapper.findById(id);
			if(null == positionExtend) {
				extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_POSITIONID_INVALID);
				extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_POSITIONID_INVALID);
				extend.setSuccess(PositionExtend.SUCCESS_NO);
				return extend;
			}
			validateDisabled(extend, positionExtend);
			extend.setSuccess(PositionExtend.SUCCESS_YES);
			return extend;
		}
		if(null != positionId ) {
			positionExtend = positionExtendWrapper.findByPositionId(positionId);
			if(null == positionExtend) {
				extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_POSITIONID_INVALID);
				extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_POSITIONID_INVALID);
				extend.setSuccess(PositionExtend.SUCCESS_NO);
				return extend;
			}
			
			validateDisabled(extend, positionExtend);
			extend.setSuccess(PositionExtend.SUCCESS_YES);
			return extend;
		}
		
		String project = extend.getProject();
		String module = extend.getModule();
		String variety = extend.getVariety();
		String position = extend.getPosition();
		
		if(StringUtils.isBlank(project)) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_PROJECT_EMPTY);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_PROJECT_EMPTY);
			extend.setSuccess(PositionExtend.SUCCESS_NO);
			return extend;
		}
		
		if(StringUtils.isBlank(module)) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_MODULE_EMPTY);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_MODULE_EMPTY);
			extend.setSuccess(PositionExtend.SUCCESS_NO);
			return extend;
		}
		if(StringUtils.isBlank(variety)) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_VARIETY_EMPTY);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_VARIETY_EMPTY);
			extend.setSuccess(PositionExtend.SUCCESS_NO);
			return extend;
		}
		
		if(StringUtils.isBlank(position)) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_POSITION_EMPTY);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_POSITION_EMPTY);
			extend.setSuccess(PositionExtend.SUCCESS_NO);
			return extend;
		}
		
		positionExtend = positionExtendWrapper.findPositionExtend(extend);
		
		
		if(null != positionExtend) {
			validateDisabled(extend, positionExtend);
		}else {
			positionExtend = new PositionExtend();
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_IMAGE_EXTEND_NOT_EXIST);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_IMAGE_EXTEND_NOT_EXIST);
			extend.setSuccess(PositionExtend.SUCCESS_NO);
			return extend;
		}
		positionExtend.setSuccess(PositionExtend.SUCCESS_YES);
		return positionExtend;
	}

	/**
	 * @param extend
	 * @param positionExtend
	 */
	private PositionExtend validateDisabled(PositionExtend extend, PositionExtend positionExtend) {
		if(positionExtend.getDisable()) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_POSITIONID_DISABLED);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_POSITIONID_DISABLED);
			extend.setSuccess(PositionExtend.SUCCESS_NO);
			return extend;
		}
		
		if(positionExtend.getDeleted()) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_POSITIONID_DELETED);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_POSITIONID_DELETED);
			extend.setSuccess(PositionExtend.SUCCESS_NO);
			return extend;
		}
		return extend;
	}
	
	public PositionExtend validatePositionExtendThenCreate(PositionExtend extend) {
		
		PositionExtend positionExtend = validatePositionExtend(extend);
		if(extend.getSuccess()==PositionExtend.SUCCESS_NO) {
			if(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_IMAGE_EXTEND_NOT_EXIST.equals(extend.getRespCode())) {
				Long userId = extend.getUserId();
				
				if(null==userId) {
					extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_USERID_EMPTY);
					extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_USERID_EMPTY);
					extend.setSuccess(PositionExtend.SUCCESS_NO);
					return extend;
				}
				//尚不存在，执行创建
				extend.setCreatedBy(userId);
				extend.setLastModifiedBy(userId);
				/*String domain = extend.getDomain();
				if(StringUtils.isBlank(domain)) {
					extend.setDomain("http://m.dramala.com");
				}*/
				positionExtend = positionExtendWrapper.create(extend);
				if(null == positionExtend) {
					//创建失败
					extend.setSuccess(PositionExtend.SUCCESS_NO);
					extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_IMAGE_EXTEND_CREATE_FAIL);
					extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_IMAGE_EXTEND_CREATE_FAIL);
					return extend;
				}
				positionExtend.setSuccess(PositionExtend.SUCCESS_YES);
			}
		}
		
		positionExtend.setSuccess(PositionExtend.SUCCESS_YES);
		return positionExtend;
	}
	
	public boolean validateExtendForDelete(PositionExtend extend) {
		
		Long id = extend.getId();
		Long userId = extend.getUserId();
		
		if(null == userId) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_EXTEND_USER_NOT_LOGIN);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_EXTEND_USER_NOT_LOGIN);
			extend.setSuccess(PositionExtend.SUCCESS_NO);
			return false;
		}
		
		if(null != id) {
			PositionExtend exist = positionExtendWrapper.findById(id);
			if(exist == null) {
				extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_EXTEND_VARIETY_NOT_EXIST);
				extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_EXTEND_VARIETY_NOT_EXIST);
				return false;
			}
			//项目类别已存在，则应返回该类别
			Long existUserId = exist.getUserId();
			if(!userId.equals(existUserId)) {
				extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_EXTEND_NOT_SELF);
				extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_EXTEND_NOT_SELF);
				extend.setSuccess(PositionExtend.SUCCESS_NO);
				return false;
			}
		}
		
		/*Long extendId = extend.getId();
		Long positionNum = positionWrapper.countByExtendId(extendId);
		if(positionNum>0) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_EXTEND_HAS_POSITION);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_EXTEND_HAS_POSITION);
			extend.setSuccess(PositionExtend.SUCCESS_NO);
			return false;
		}*/
		return true;
	}

}
