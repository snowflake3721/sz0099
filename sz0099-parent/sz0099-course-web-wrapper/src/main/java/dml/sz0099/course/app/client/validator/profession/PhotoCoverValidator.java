package dml.sz0099.course.app.client.validator.profession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.resolver.adaptor.media.CoverProfessionAdaptor;
import dml.sz0099.course.app.client.wrapper.profession.PhotoCoverWrapper;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.entity.media.ImageRef;
import dml.sz0099.course.app.persist.entity.profession.PhotoCover;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * PhotoCoverValidator 校验组件
 * @author bruce yang at 2018-08-29 22:40:14
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Component("professionPhotoCoverValidator")
public class PhotoCoverValidator implements Validator {
	
	@Autowired
	private PhotoCoverWrapper photoCoverWrapper;
	
	@Autowired
	private CoverProfessionAdaptor coverProfessionAdaptor;

	@Override
	public boolean supports(Class<?> clazz) {
		return PhotoCover.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		PhotoCover photoCover = (PhotoCover) target;
		validateUuid(errors, photoCover.getUuid());
	}
	
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.article.PhotoCover.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateForCreate(ImageRef ref) {
		
		Long userId = ref.getUserId();
		if(null == userId) {
			ref.setSuccess(PhotoCover.SUCCESS_NO);
			ref.setRespCode(CourseProductRespCode.SZ0099_CODE_OOD_PROFESSION_PARAGRAGH_PHOTO_USERID_EMPLTY);
			ref.setRespMsg(CourseProductRespCode.SZ0099_MSG_OOD_PROFESSION_PARAGRAGH_PHOTO_USERID_EMPLTY);
			return false;
		}
		
		Long mainId=ref.getMainId();
		if(null == mainId) {
			ref.setSuccess(PhotoCover.SUCCESS_NO);
			ref.setRespCode(CourseProductRespCode.SZ0099_CODE_OOD_PROFESSION_PHOTO_MAINID_EMPLTY);
			ref.setRespMsg(CourseProductRespCode.SZ0099_MSG_OOD_PROFESSION_PHOTO_MAINID_EMPLTY);
			return false;
		}
		
		Long count = photoCoverWrapper.countByMainId(mainId);
		ImageExtend extend = coverProfessionAdaptor.config();
		if(null != count) {
			Integer mainMaxnum = extend.getMainMaxnum();
			if(count.intValue() >= mainMaxnum) {
				ref.setSuccess(PhotoCover.SUCCESS_NO);
				ref.setRespCode(CourseProductRespCode.SZ0099_CODE_OOD_PROFESSION_PHOTO_MAINNUM_EXTRA);
				ref.setRespMsg(CourseProductRespCode.SZ0099_MSG_OOD_PROFESSION_PHOTO_MAINNUM_EXTRA);
				return false;
			}
		}
		return true;
	}
	
	public boolean validateForDeleteImageRef(ImageRef ref) {
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
			ref.setSuccess(PhotoCover.SUCCESS_NO);
			ref.setRespCode(CourseProductRespCode.SZ0099_CODE_OOD_PROFESSION_PARAGRAGH_PHOTO_USERID_EMPLTY);
			ref.setRespMsg(CourseProductRespCode.SZ0099_MSG_OOD_PROFESSION_PARAGRAGH_PHOTO_USERID_EMPLTY);
			return false;
		}
		
		Long id = ref.getId();
		if(null == id) {
			ref.setSuccess(PhotoCover.SUCCESS_NO);
			ref.setRespCode(CourseProductRespCode.SZ0099_CODE_OOD_PROFESSION_PARAGRAGH_PHOTO_ID_EMPLTY);
			ref.setRespMsg(CourseProductRespCode.SZ0099_MSG_OOD_PROFESSION_PARAGRAGH_PHOTO_ID_EMPLTY);
			return false;
		}
		
		PhotoCover entity = photoCoverWrapper.findById(id);
		if(null == entity) {
			ref.setSuccess(PhotoCover.SUCCESS_NO);
			ref.setRespCode(CourseProductRespCode.SZ0099_CODE_OOD_PROFESSION_PARAGRAGH_PHOTO_ID_ENTITY_EMPLTY);
			ref.setRespMsg(CourseProductRespCode.SZ0099_MSG_OOD_PROFESSION_PARAGRAGH_PHOTO_ID_ENTITY_EMPLTY);
			return false;
		}
		
		Long userIdE = entity.getUserId();
		if(!userId.equals(userIdE)) {
			ref.setSuccess(PhotoCover.SUCCESS_NO);
			ref.setRespCode(CourseProductRespCode.SZ0099_CODE_OOD_PROFESSION_PARAGRAGH_PHOTO_USERID_INVALID);
			ref.setRespMsg(CourseProductRespCode.SZ0099_MSG_OOD_PROFESSION_PARAGRAGH_PHOTO_USERID_INVALID);
			return false;
		}
		return true;
		
	}

}
