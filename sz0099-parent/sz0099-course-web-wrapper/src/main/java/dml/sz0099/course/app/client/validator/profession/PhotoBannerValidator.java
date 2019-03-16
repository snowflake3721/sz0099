package dml.sz0099.course.app.client.validator.profession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.resolver.adaptor.media.BannerProfessionAdaptor;
import dml.sz0099.course.app.client.wrapper.profession.PhotoBannerWrapper;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.entity.media.ImageRef;
import dml.sz0099.course.app.persist.entity.profession.PhotoBanner;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * PhotoBannerValidator 校验组件
 * @author bruce yang at 2018-08-29 22:40:14
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Component("professionPhotoBannerValidator")
public class PhotoBannerValidator implements Validator {
	
	@Autowired
	private PhotoBannerWrapper photoBannerWrapper;
	
	@Autowired
	private BannerProfessionAdaptor bannerProfessionAdaptor;

	@Override
	public boolean supports(Class<?> clazz) {
		return PhotoBanner.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		PhotoBanner photoBanner = (PhotoBanner) target;
		validateUuid(errors, photoBanner.getUuid());
	}
	
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.article.PhotoBanner.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateForCreate(ImageRef ref) {
		
		Long userId = ref.getUserId();
		if(null == userId) {
			ref.setSuccess(PhotoBanner.SUCCESS_NO);
			ref.setRespCode(CourseProductRespCode.SZ0099_CODE_OOD_PROFESSION_PARAGRAGH_PHOTO_USERID_EMPLTY);
			ref.setRespMsg(CourseProductRespCode.SZ0099_MSG_OOD_PROFESSION_PARAGRAGH_PHOTO_USERID_EMPLTY);
			return false;
		}
		
		Long mainId=ref.getMainId();
		if(null == mainId) {
			ref.setSuccess(PhotoBanner.SUCCESS_NO);
			ref.setRespCode(CourseProductRespCode.SZ0099_CODE_OOD_PROFESSION_PHOTO_MAINID_EMPLTY);
			ref.setRespMsg(CourseProductRespCode.SZ0099_MSG_OOD_PROFESSION_PHOTO_MAINID_EMPLTY);
			return false;
		}
		
		Long count = photoBannerWrapper.countByMainId(mainId);
		ImageExtend extend = bannerProfessionAdaptor.config();
		if(null != count) {
			Integer mainMaxnum = extend.getMainMaxnum();
			if(count.intValue() >= mainMaxnum) {
				ref.setSuccess(PhotoBanner.SUCCESS_NO);
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
			ref.setSuccess(PhotoBanner.SUCCESS_NO);
			ref.setRespCode(CourseProductRespCode.SZ0099_CODE_OOD_PROFESSION_PARAGRAGH_PHOTO_USERID_EMPLTY);
			ref.setRespMsg(CourseProductRespCode.SZ0099_MSG_OOD_PROFESSION_PARAGRAGH_PHOTO_USERID_EMPLTY);
			return false;
		}
		
		Long id = ref.getId();
		if(null == id) {
			ref.setSuccess(PhotoBanner.SUCCESS_NO);
			ref.setRespCode(CourseProductRespCode.SZ0099_CODE_OOD_PROFESSION_PARAGRAGH_PHOTO_ID_EMPLTY);
			ref.setRespMsg(CourseProductRespCode.SZ0099_MSG_OOD_PROFESSION_PARAGRAGH_PHOTO_ID_EMPLTY);
			return false;
		}
		
		PhotoBanner entity = photoBannerWrapper.findById(id);
		if(null == entity) {
			ref.setSuccess(PhotoBanner.SUCCESS_NO);
			ref.setRespCode(CourseProductRespCode.SZ0099_CODE_OOD_PROFESSION_PARAGRAGH_PHOTO_ID_ENTITY_EMPLTY);
			ref.setRespMsg(CourseProductRespCode.SZ0099_MSG_OOD_PROFESSION_PARAGRAGH_PHOTO_ID_ENTITY_EMPLTY);
			return false;
		}
		
		Long userIdE = entity.getUserId();
		if(!userId.equals(userIdE)) {
			ref.setSuccess(PhotoBanner.SUCCESS_NO);
			ref.setRespCode(CourseProductRespCode.SZ0099_CODE_OOD_PROFESSION_PARAGRAGH_PHOTO_USERID_INVALID);
			ref.setRespMsg(CourseProductRespCode.SZ0099_MSG_OOD_PROFESSION_PARAGRAGH_PHOTO_USERID_INVALID);
			return false;
		}
		return true;
		
	}

}
