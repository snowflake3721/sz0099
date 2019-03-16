package dml.sz0099.course.app.client.validator.media;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.media.ImageRefWrapper;
import dml.sz0099.course.app.module.define.CourseRespCode;
import dml.sz0099.course.app.persist.entity.media.ImageRef;
import dml.sz0099.course.app.persist.entity.media.bo.ImageRefBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ImageRefValidator 校验组件
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
public class ImageRefValidator implements Validator {

	@Autowired
	private ImageRefWrapper imageRefWrapper;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ImageRefBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ImageRefBo imageRef = (ImageRefBo) target;

		validateUuid(errors, imageRef.getUuid());

	}

	/**
	 * ImageRef validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.media.ImageRef.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateImageRef(ImageRef imageRef) {
		
		
		Long userId = imageRef.getUserId();
		
		if(null == userId) {
			imageRef.setSuccess(ImageRef.SUCCESS_NO);
			imageRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_IMAGE_REF_NOT_LOGIN);
			imageRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_IMAGE_REF_NOT_LOGIN);
			return false;
		}
		
		Long id = imageRef.getId();
		
		if(null == id) {
			imageRef.setSuccess(ImageRef.SUCCESS_NO);
			imageRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_IMAGE_REF_ID_EMPTY);
			imageRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_IMAGE_REF_ID_EMPTY);
			return false;
		}
		
		boolean exist = imageRefWrapper.existById(id);
		if(!exist) {
			imageRef.setSuccess(ImageRef.SUCCESS_NO);
			imageRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_IMAGE_REF_NOT_EXIST);
			imageRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_IMAGE_REF_NOT_EXIST);
			return false;
		}
		return true;
	}

}
