package dml.sz0099.course.app.client.validator.profession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.profession.ProfessionExtendWrapper;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.profession.ProfessionExtend;
import dml.sz0099.course.app.persist.entity.profession.bo.ProfessionExtendBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ProfessionExtendValidator 校验组件
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
public class ProfessionExtendValidator implements Validator {
	
	@Autowired
	private ProfessionExtendWrapper professionExtendWrapper;

	@Override
	public boolean supports(Class<?> clazz) {
		return ProfessionExtendBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ProfessionExtendBo professionExtend = (ProfessionExtendBo) target;

		validateUuid(errors, professionExtend.getUuid());

	}

	/**
	 * ProfessionExtend validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.position.ProfessionExtend.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateExtendForPersist(ProfessionExtend extend) {
		
		Long id = extend.getId();
		Long userId = extend.getUserId();
		
		if(null == userId) {
			extend.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_EXTEND_USER_NOT_LOGIN);
			extend.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_EXTEND_USER_NOT_LOGIN);
			extend.setSuccess(ProfessionExtend.SUCCESS_NO);
			return false;
		}
		
		if(null != id) {
			ProfessionExtend exist = professionExtendWrapper.findById(id);
			if(exist != null) {
				extend.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_EXTEND_VARIETY_EXIST);
				extend.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_EXTEND_VARIETY_EXIST);
				//项目类别已存在，则应返回该类别
				
				Long existUserId = exist.getUserId();
				if(!userId.equals(existUserId)) {
					extend.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_EXTEND_NOT_SELF);
					extend.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_EXTEND_NOT_SELF);
					extend.setSuccess(ProfessionExtend.SUCCESS_NO);
					return false;
				}
				
			}
		}
		
		/*Long userNum = professionExtendWrapper.countByUserId(userId);
		if(userNum>ProfessionExtend.VARIETYMAXNUM_DEFAULT) {
			extend.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_EXTEND_VARIETY_EXTRA);
			extend.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_EXTEND_VARIETY_EXTRA);
			extend.setSuccess(ProfessionExtend.SUCCESS_NO);
			return false;
		}*/
		return true;
	}
	
	
	public boolean validateExtend(ProfessionExtend extend) {
		validateProfessionExtend(extend);
		return ProfessionExtend.SUCCESS_YES==extend.getSuccess();
	}
	
	public ProfessionExtend validateProfessionExtend(ProfessionExtend extend) {
		String devId = StringUtils.trim(extend.getDevId());
		if(StringUtils.isBlank(devId)) {
			extend.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_MEDIA_DEVID_EMPTY);
			extend.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_MEDIA_DEVID_EMPTY);
			extend.setSuccess(ProfessionExtend.SUCCESS_NO);
			return extend;
		}
		
		
		Long id = extend.getId();
		Long positionId = extend.getPositionId();
		ProfessionExtend professionExtend = null;

		if(null != id ) {
			professionExtend = professionExtendWrapper.findById(id);
			if(null == professionExtend) {
				extend.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_MEDIA_POSITIONID_INVALID);
				extend.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_MEDIA_POSITIONID_INVALID);
				extend.setSuccess(ProfessionExtend.SUCCESS_NO);
				return extend;
			}
			validateDisabled(extend, professionExtend);
			extend.setSuccess(ProfessionExtend.SUCCESS_YES);
			return extend;
		}
		if(null != positionId ) {
			professionExtend = professionExtendWrapper.findByPositionId(positionId);
			if(null == professionExtend) {
				extend.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_MEDIA_POSITIONID_INVALID);
				extend.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_MEDIA_POSITIONID_INVALID);
				extend.setSuccess(ProfessionExtend.SUCCESS_NO);
				return extend;
			}
			
			validateDisabled(extend, professionExtend);
			extend.setSuccess(ProfessionExtend.SUCCESS_YES);
			return extend;
		}
		
		String project = extend.getProject();
		String module = extend.getModule();
		String variety = extend.getVariety();
		String position = extend.getPosition();
		
		if(StringUtils.isBlank(project)) {
			extend.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_MEDIA_PROJECT_EMPTY);
			extend.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_MEDIA_PROJECT_EMPTY);
			extend.setSuccess(ProfessionExtend.SUCCESS_NO);
			return extend;
		}
		
		if(StringUtils.isBlank(module)) {
			extend.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_MEDIA_MODULE_EMPTY);
			extend.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_MEDIA_MODULE_EMPTY);
			extend.setSuccess(ProfessionExtend.SUCCESS_NO);
			return extend;
		}
		if(StringUtils.isBlank(variety)) {
			extend.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_MEDIA_VARIETY_EMPTY);
			extend.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_MEDIA_VARIETY_EMPTY);
			extend.setSuccess(ProfessionExtend.SUCCESS_NO);
			return extend;
		}
		
		if(StringUtils.isBlank(position)) {
			extend.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_MEDIA_POSITION_EMPTY);
			extend.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_MEDIA_POSITION_EMPTY);
			extend.setSuccess(ProfessionExtend.SUCCESS_NO);
			return extend;
		}
		
		professionExtend = professionExtendWrapper.findProfessionExtend(extend);
		
		
		if(null != professionExtend) {
			validateDisabled(extend, professionExtend);
		}else {
			professionExtend = new ProfessionExtend();
			extend.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_MEDIA_IMAGE_EXTEND_NOT_EXIST);
			extend.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_MEDIA_IMAGE_EXTEND_NOT_EXIST);
			extend.setSuccess(ProfessionExtend.SUCCESS_NO);
			return extend;
		}
		professionExtend.setSuccess(ProfessionExtend.SUCCESS_YES);
		return professionExtend;
	}

	/**
	 * @param extend
	 * @param professionExtend
	 */
	private ProfessionExtend validateDisabled(ProfessionExtend extend, ProfessionExtend professionExtend) {
		if(professionExtend.getDisable()) {
			extend.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_MEDIA_POSITIONID_DISABLED);
			extend.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_MEDIA_POSITIONID_DISABLED);
			extend.setSuccess(ProfessionExtend.SUCCESS_NO);
			return extend;
		}
		
		if(professionExtend.getDeleted()) {
			extend.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_MEDIA_POSITIONID_DELETED);
			extend.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_MEDIA_POSITIONID_DELETED);
			extend.setSuccess(ProfessionExtend.SUCCESS_NO);
			return extend;
		}
		return extend;
	}
	
	public ProfessionExtend validateProfessionExtendThenCreate(ProfessionExtend extend) {
		
		ProfessionExtend professionExtend = validateProfessionExtend(extend);
		if(extend.getSuccess()==ProfessionExtend.SUCCESS_NO) {
			if(CourseProductRespCode.SZ0099_CODE_COURSE_MEDIA_IMAGE_EXTEND_NOT_EXIST.equals(extend.getRespCode())) {
				Long userId = extend.getUserId();
				
				if(null==userId) {
					extend.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_MEDIA_USERID_EMPTY);
					extend.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_MEDIA_USERID_EMPTY);
					extend.setSuccess(ProfessionExtend.SUCCESS_NO);
					return extend;
				}
				//尚不存在，执行创建
				extend.setCreatedBy(userId);
				extend.setLastModifiedBy(userId);
				/*String domain = extend.getDomain();
				if(StringUtils.isBlank(domain)) {
					extend.setDomain("http://m.dramala.com");
				}*/
				professionExtend = professionExtendWrapper.create(extend);
				if(null == professionExtend) {
					//创建失败
					extend.setSuccess(ProfessionExtend.SUCCESS_NO);
					extend.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_MEDIA_IMAGE_EXTEND_CREATE_FAIL);
					extend.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_MEDIA_IMAGE_EXTEND_CREATE_FAIL);
					return extend;
				}
				professionExtend.setSuccess(ProfessionExtend.SUCCESS_YES);
			}
		}
		
		professionExtend.setSuccess(ProfessionExtend.SUCCESS_YES);
		return professionExtend;
	}
	
	public boolean validateExtendForDelete(ProfessionExtend extend) {
		
		Long id = extend.getId();
		Long userId = extend.getUserId();
		
		if(null == userId) {
			extend.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_EXTEND_USER_NOT_LOGIN);
			extend.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_EXTEND_USER_NOT_LOGIN);
			extend.setSuccess(ProfessionExtend.SUCCESS_NO);
			return false;
		}
		
		if(null != id) {
			ProfessionExtend exist = professionExtendWrapper.findById(id);
			if(exist == null) {
				extend.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_EXTEND_VARIETY_NOT_EXIST);
				extend.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_EXTEND_VARIETY_NOT_EXIST);
				return false;
			}
			//项目类别已存在，则应返回该类别
			Long existUserId = exist.getUserId();
			if(!userId.equals(existUserId)) {
				extend.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_EXTEND_NOT_SELF);
				extend.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_EXTEND_NOT_SELF);
				extend.setSuccess(ProfessionExtend.SUCCESS_NO);
				return false;
			}
		}
		
		/*Long extendId = extend.getId();
		Long positionNum = positionWrapper.countByExtendId(extendId);
		if(positionNum>0) {
			extend.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_EXTEND_HAS_POSITION);
			extend.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_EXTEND_HAS_POSITION);
			extend.setSuccess(ProfessionExtend.SUCCESS_NO);
			return false;
		}*/
		return true;
	}

}
