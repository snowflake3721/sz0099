package dml.sz0099.course.app.client.validator.profession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.profession.ProfessionRefWrapper;
import dml.sz0099.course.app.client.wrapper.profession.ProfessionWrapper;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.profession.Profession;
import dml.sz0099.course.app.persist.entity.profession.ProfessionRef;
import dml.sz0099.course.app.persist.entity.profession.bo.ProfessionRefBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ProfessionRefValidator 校验组件
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
public class ProfessionRefValidator implements Validator {
	
	@Autowired
	private ProfessionWrapper professionWrapper;
	
	@Autowired
	private ProfessionRefWrapper professionRefWrapper;

	@Override
	public boolean supports(Class<?> clazz) {
		return ProfessionRefBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ProfessionRefBo professionRef = (ProfessionRefBo) target;

		validateUuid(errors, professionRef.getUuid());

	}

	/**
	 * ProfessionRef validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.profession.ProfessionRef.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateForAdd(ProfessionRef professionRef) {
		boolean checked = checkBase(professionRef);
		if(!checked) {
			return false;
		}
		Integer viewType = professionRef.getViewType();
		if(null==viewType) {
			professionRef.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_REF_VIEWTYPE_EMPTY);
			professionRef.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_REF_VIEWTYPE_EMPTY);
			professionRef.setSuccess(ProfessionRef.SUCCESS_NO);
			return false;
		}
		ProfessionRef entity = professionRefWrapper.findMainIdAndBaseId(professionRef);
		if(null != entity) {
			professionRef.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_REF_HAS_BINDED);
			professionRef.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_REF_HAS_BINDED);
			professionRef.setSuccess(ProfessionRef.SUCCESS_NO);
			return false;
		}
		return true;
	}
	
	public boolean validateForDelete(ProfessionRef professionRef) {
		
		Long id = professionRef.getId();
		if(null==id) {
			professionRef.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_REF_ID_EMPTY);
			professionRef.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_REF_ID_EMPTY);
			professionRef.setSuccess(ProfessionRef.SUCCESS_NO);
			return false;
		}
		
		boolean checked = checkBase(professionRef);
		if(!checked) {
			return false;
		}
		Integer viewType = professionRef.getViewType();
		if(null==viewType) {
			professionRef.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_REF_VIEWTYPE_EMPTY);
			professionRef.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_REF_VIEWTYPE_EMPTY);
			professionRef.setSuccess(ProfessionRef.SUCCESS_NO);
			return false;
		}
		
		return true;
	}
	
public boolean validateForMerge(ProfessionRef professionRef) {
		
		Long id = professionRef.getId();
		if(null==id) {
			professionRef.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_REF_ID_EMPTY);
			professionRef.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_REF_ID_EMPTY);
			professionRef.setSuccess(ProfessionRef.SUCCESS_NO);
			return false;
		}
		
		boolean checked = checkBase(professionRef);
		if(!checked) {
			return false;
		}
		Integer viewType = professionRef.getViewType();
		if(null==viewType) {
			professionRef.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_REF_VIEWTYPE_EMPTY);
			professionRef.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_REF_VIEWTYPE_EMPTY);
			professionRef.setSuccess(ProfessionRef.SUCCESS_NO);
			return false;
		}
		
		Integer status = professionRef.getStatus();
		if(ProfessionRef.STATUS_2_CLOSED.getValueInt().equals(status)) {
			String remark = professionRef.getRemark();
			if(StringUtils.isBlank(remark)) {
				professionRef.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_REF_REMARK_EMPTY);
				professionRef.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_REF_REMARK_EMPTY);
				professionRef.setSuccess(ProfessionRef.SUCCESS_NO);
				return false;
			}
		}
		
		return true;
	}


	public boolean validateForOpen(ProfessionRef professionRef) {
		
		Long id = professionRef.getId();
		if(null==id) {
			professionRef.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_REF_ID_EMPTY);
			professionRef.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_REF_ID_EMPTY);
			professionRef.setSuccess(ProfessionRef.SUCCESS_NO);
			return false;
		}
		
		boolean checked = checkBase(professionRef);
		if(!checked) {
			return false;
		}
		Integer viewType = professionRef.getViewType();
		if(null==viewType) {
			professionRef.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_REF_VIEWTYPE_EMPTY);
			professionRef.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_REF_VIEWTYPE_EMPTY);
			professionRef.setSuccess(ProfessionRef.SUCCESS_NO);
			return false;
		}
		
		return true;
	}
	
public boolean validateForSimpleSingle(ProfessionRef professionRef) {
		
		Long id = professionRef.getId();
		if(null==id) {
			professionRef.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_REF_ID_EMPTY);
			professionRef.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_REF_ID_EMPTY);
			professionRef.setSuccess(ProfessionRef.SUCCESS_NO);
			return false;
		}
		Long userId = professionRef.getUserId();
		if(null == userId) {
			professionRef.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_EXTEND_USER_NOT_LOGIN);
			professionRef.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_EXTEND_USER_NOT_LOGIN);
			professionRef.setSuccess(ProfessionRef.SUCCESS_NO);
			return false;
		}
		
		Integer topLevel = professionRef.getTopLevel();
		if(null ==topLevel) {
			professionRef.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_REF_TOPLEVEL_EMPTY);
			professionRef.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_REF_TOPLEVEL_EMPTY);
			professionRef.setSuccess(ProfessionRef.SUCCESS_NO);
			return false;
		}
		
		return true;
	}


	public boolean validateForDeleteRefByBaseId(ProfessionRef professionRef) {
		
		boolean checked = checkBase(professionRef);
		if(!checked) {
			return false;
		}
		
		return true;
	}

	
	public boolean validateForFindList(ProfessionRef professionRef) {
		
		return checkBase(professionRef);
		
	}

	/**
	 * @param professionRef
	 */
	private boolean checkBase(ProfessionRef professionRef) {
		//Long extendId = professionRef.getExtendId();
		
		Long baseId = professionRef.getBaseId();
		
		Long userId = professionRef.getUserId();
		
		if(null == userId) {
			professionRef.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_EXTEND_USER_NOT_LOGIN);
			professionRef.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_EXTEND_USER_NOT_LOGIN);
			professionRef.setSuccess(ProfessionRef.SUCCESS_NO);
			return false;
		}
		
		if(null == baseId) {
			professionRef.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_REF_BASEID_EMPTY);
			professionRef.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_REF_BASEID_EMPTY);
			professionRef.setSuccess(ProfessionRef.SUCCESS_NO);
			return false;
		}
		
		/*if(null == extendId) {
			professionRef.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_REF_EXTENDID_EMPTY);
			professionRef.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_REF_EXTENDID_EMPTY);
			professionRef.setSuccess(ProfessionRef.SUCCESS_NO);
			return false;
		}*/
		
		Profession profession = professionWrapper.findById(baseId);
		if(profession==null) {
			professionRef.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_REF_POSITON_NOT_EXIST);
			professionRef.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_REF_POSITON_NOT_EXIST);
			professionRef.setSuccess(ProfessionRef.SUCCESS_NO);
			return false;
		}
		if(profession.getDeleted()) {
			professionRef.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_REF_POSITON_DELETED);
			professionRef.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_REF_POSITON_DELETED);
			professionRef.setSuccess(ProfessionRef.SUCCESS_NO);
			return false;
		}
		return true;
	}

}
