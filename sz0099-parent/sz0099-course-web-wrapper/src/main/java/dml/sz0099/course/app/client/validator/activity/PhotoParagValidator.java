package dml.sz0099.course.app.client.validator.activity;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.resolver.adaptor.media.ParagActivityAdaptor;
import dml.sz0099.course.app.client.wrapper.activity.PhotoParagWrapper;
import dml.sz0099.course.app.module.define.CoeActivityRespCode;
import dml.sz0099.course.app.persist.entity.activity.PhotoParag;
import dml.sz0099.course.app.persist.entity.activity.bo.PhotoParagBo;
import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.entity.media.ImageRef;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * PhotoParagValidator 校验组件
 * @author bruce yang at 2018-08-29 22:40:14
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Component("activityPhotoParagValidator")
public class PhotoParagValidator implements Validator {
	
	@Autowired
	private PhotoParagWrapper photoParagWrapper;
	
	@Autowired
	private ParagActivityAdaptor paragActivityAdaptor;

	@Override
	public boolean supports(Class<?> clazz) {
		return PhotoParagBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		PhotoParagBo photoParag = (PhotoParagBo) target;
		validateUuid(errors, photoParag.getUuid());
	}
	
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.activity.PhotoParag.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateForCreate(ImageRef ref) {
		
		Long userId = ref.getUserId();
		if(null == userId) {
			ref.setSuccess(PhotoParag.SUCCESS_NO);
			ref.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_PARAGRAGH_PHOTO_USERID_EMPLTY);
			ref.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_PARAGRAGH_PHOTO_USERID_EMPLTY);
			return false;
		}
		
		Long mainId=ref.getMainId();
		if(null == mainId) {
			ref.setSuccess(PhotoParag.SUCCESS_NO);
			ref.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_PHOTO_MAINID_EMPLTY);
			ref.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_PHOTO_MAINID_EMPLTY);
			return false;
		}
		
		Long count = photoParagWrapper.countByMainId(mainId);
		ImageExtend extend = paragActivityAdaptor.config();
		if(null != count) {
			Integer mainMaxnum = extend.getMainMaxnum();
			if(count.intValue() >= mainMaxnum) {
				ref.setSuccess(PhotoParag.SUCCESS_NO);
				ref.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_PHOTO_MAINNUM_EXTRA);
				ref.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_PHOTO_MAINNUM_EXTRA);
				return false;
			}
		}
		return true;
	}
	
	public boolean validateForDeleteImageRef(ImageRef ref) {
		return validateIsOwnSelf(ref);
	}
	public boolean validateForMergeTemplateById(ImageRef ref) {
		return validateIsOwnSelf(ref);
	}
	
	public boolean validateForAddImageRef(ImageRef ref) {
		return validateIsOwnSelf(ref);
	}
	
	public boolean validateForChangeImageRef(ImageRef ref) {
		return validateIsOwnSelf(ref);
	}
	
	public boolean validateIsOwnSelf(ImageRef ref) {
		
		Long userId = ref.getUserId();
		if(null == userId) {
			ref.setSuccess(PhotoParag.SUCCESS_NO);
			ref.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_LOGIN);
			ref.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_USER_NOT_LOGIN);
			return false;
		}
		
		Long id = ref.getId();
		if(null == id) {
			ref.setSuccess(PhotoParag.SUCCESS_NO);
			ref.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_PARAGRAGH_PHOTO_ID_EMPLTY);
			ref.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_PARAGRAGH_PHOTO_ID_EMPLTY);
			return false;
		}
		
		PhotoParag entity = photoParagWrapper.findById(id);
		if(null == entity) {
			ref.setSuccess(PhotoParag.SUCCESS_NO);
			ref.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_PARAGRAGH_PHOTO_ID_ENTITY_EMPLTY);
			ref.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_PARAGRAGH_PHOTO_ID_ENTITY_EMPLTY);
			return false;
		}
		
		Long userIdE = entity.getUserId();
		if(!userId.equals(userIdE)) {
			ref.setSuccess(PhotoParag.SUCCESS_NO);
			ref.setRespCode(CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_PARAGRAGH_PHOTO_USERID_INVALID);
			ref.setRespMsg(CoeActivityRespCode.SZ0099_MSG_OOD_ACTIVITY_PARAGRAGH_PHOTO_USERID_INVALID);
			return false;
		}
		return true;
		
	}

}
