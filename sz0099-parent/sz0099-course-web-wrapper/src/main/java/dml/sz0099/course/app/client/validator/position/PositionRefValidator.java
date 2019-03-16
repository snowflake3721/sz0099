package dml.sz0099.course.app.client.validator.position;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.position.PositionRefWrapper;
import dml.sz0099.course.app.client.wrapper.position.PositionWrapper;
import dml.sz0099.course.app.module.define.CourseRespCode;
import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.entity.position.PositionExtend;
import dml.sz0099.course.app.persist.entity.position.PositionRef;
import dml.sz0099.course.app.persist.entity.position.bo.PositionRefBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * PositionRefValidator 校验组件
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
public class PositionRefValidator implements Validator {
	
	@Autowired
	private PositionWrapper positionWrapper;
	
	@Autowired
	private PositionRefWrapper positionRefWrapper;

	@Override
	public boolean supports(Class<?> clazz) {
		return PositionRefBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		PositionRefBo positionRef = (PositionRefBo) target;

		validateUuid(errors, positionRef.getUuid());

	}

	/**
	 * PositionRef validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.position.PositionRef.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateForAdd(PositionRef positionRef) {
		boolean checked = checkBase(positionRef);
		if(!checked) {
			return false;
		}
		Integer viewType = positionRef.getViewType();
		if(null==viewType) {
			positionRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_REF_VIEWTYPE_EMPTY);
			positionRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_REF_VIEWTYPE_EMPTY);
			positionRef.setSuccess(PositionExtend.SUCCESS_NO);
			return false;
		}
		PositionRef entity = positionRefWrapper.findMainIdAndBaseId(positionRef);
		if(null != entity) {
			positionRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_REF_HAS_BINDED);
			positionRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_REF_HAS_BINDED);
			positionRef.setSuccess(PositionExtend.SUCCESS_NO);
			return false;
		}
		return true;
	}
	
	public boolean validateForDelete(PositionRef positionRef) {
		
		Long id = positionRef.getId();
		if(null==id) {
			positionRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_REF_ID_EMPTY);
			positionRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_REF_ID_EMPTY);
			positionRef.setSuccess(PositionExtend.SUCCESS_NO);
			return false;
		}
		
		boolean checked = checkBase(positionRef);
		if(!checked) {
			return false;
		}
		Integer viewType = positionRef.getViewType();
		if(null==viewType) {
			positionRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_REF_VIEWTYPE_EMPTY);
			positionRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_REF_VIEWTYPE_EMPTY);
			positionRef.setSuccess(PositionExtend.SUCCESS_NO);
			return false;
		}
		
		return true;
	}
	
public boolean validateForMerge(PositionRef positionRef) {
		
		Long id = positionRef.getId();
		if(null==id) {
			positionRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_REF_ID_EMPTY);
			positionRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_REF_ID_EMPTY);
			positionRef.setSuccess(PositionExtend.SUCCESS_NO);
			return false;
		}
		
		boolean checked = checkBase(positionRef);
		if(!checked) {
			return false;
		}
		Integer viewType = positionRef.getViewType();
		if(null==viewType) {
			positionRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_REF_VIEWTYPE_EMPTY);
			positionRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_REF_VIEWTYPE_EMPTY);
			positionRef.setSuccess(PositionExtend.SUCCESS_NO);
			return false;
		}
		
		Integer status = positionRef.getStatus();
		if(PositionRef.STATUS_2_CLOSED.getValueInt().equals(status)) {
			String remark = positionRef.getRemark();
			if(StringUtils.isBlank(remark)) {
				positionRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_REF_REMARK_EMPTY);
				positionRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_REF_REMARK_EMPTY);
				positionRef.setSuccess(PositionExtend.SUCCESS_NO);
				return false;
			}
		}
		
		return true;
	}


	public boolean validateForOpen(PositionRef positionRef) {
		
		Long id = positionRef.getId();
		if(null==id) {
			positionRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_REF_ID_EMPTY);
			positionRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_REF_ID_EMPTY);
			positionRef.setSuccess(PositionExtend.SUCCESS_NO);
			return false;
		}
		
		boolean checked = checkBase(positionRef);
		if(!checked) {
			return false;
		}
		Integer viewType = positionRef.getViewType();
		if(null==viewType) {
			positionRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_REF_VIEWTYPE_EMPTY);
			positionRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_REF_VIEWTYPE_EMPTY);
			positionRef.setSuccess(PositionExtend.SUCCESS_NO);
			return false;
		}
		
		return true;
	}
	
	public boolean validateForSimpleSingle(PositionRef positionRef) {
		
		Long id = positionRef.getId();
		if(null==id) {
			positionRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_REF_ID_EMPTY);
			positionRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_REF_ID_EMPTY);
			positionRef.setSuccess(PositionExtend.SUCCESS_NO);
			return false;
		}
		Long userId = positionRef.getUserId();
		if(null == userId) {
			positionRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_EXTEND_USER_NOT_LOGIN);
			positionRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_EXTEND_USER_NOT_LOGIN);
			positionRef.setSuccess(PositionExtend.SUCCESS_NO);
			return false;
		}
		
		Integer topLevel = positionRef.getTopLevel();
		if(null ==topLevel) {
			positionRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_REF_TOPLEVEL_EMPTY);
			positionRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_REF_TOPLEVEL_EMPTY);
			positionRef.setSuccess(PositionExtend.SUCCESS_NO);
			return false;
		}
		
		return true;
	}
	public boolean validateForSync(PositionRef positionRef) {
		
		boolean checked = checkBase(positionRef);
		if(!checked) {
			return false;
		}
		
		return true;
	}

	public boolean validateForDeleteRefByBaseId(PositionRef positionRef) {
		
		boolean checked = checkBase(positionRef);
		if(!checked) {
			return false;
		}
		
		return true;
	}

	
	public boolean validateForFindList(PositionRef positionRef) {
		
		return checkBase(positionRef);
		
	}

	/**
	 * @param positionRef
	 */
	private boolean checkBase(PositionRef positionRef) {
		Long extendId = positionRef.getExtendId();
		
		Long baseId = positionRef.getBaseId();
		
		Long userId = positionRef.getUserId();
		
		if(null == userId) {
			positionRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_EXTEND_USER_NOT_LOGIN);
			positionRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_EXTEND_USER_NOT_LOGIN);
			positionRef.setSuccess(PositionExtend.SUCCESS_NO);
			return false;
		}
		
		if(null == baseId) {
			positionRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_REF_BASEID_EMPTY);
			positionRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_REF_BASEID_EMPTY);
			positionRef.setSuccess(PositionExtend.SUCCESS_NO);
			return false;
		}
		
		if(null == extendId) {
			positionRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_REF_EXTENDID_EMPTY);
			positionRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_REF_EXTENDID_EMPTY);
			positionRef.setSuccess(PositionExtend.SUCCESS_NO);
			return false;
		}
		
		Position position = positionWrapper.findById(baseId);
		if(position==null) {
			positionRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_REF_POSITON_NOT_EXIST);
			positionRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_REF_POSITON_NOT_EXIST);
			positionRef.setSuccess(PositionExtend.SUCCESS_NO);
			return false;
		}
		if(position.getDeleted()) {
			positionRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_REF_POSITON_DELETED);
			positionRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_REF_POSITON_DELETED);
			positionRef.setSuccess(PositionExtend.SUCCESS_NO);
			return false;
		}
		return true;
	}

}
