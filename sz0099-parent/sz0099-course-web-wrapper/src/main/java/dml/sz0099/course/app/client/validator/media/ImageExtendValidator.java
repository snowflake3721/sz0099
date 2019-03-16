package dml.sz0099.course.app.client.validator.media;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.media.ImageExtendWrapper;
import dml.sz0099.course.app.module.define.CourseRespCode;
import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.entity.media.bo.ImageExtendBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ImageExtendValidator 校验组件
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
public class ImageExtendValidator implements Validator {

	@Autowired
	private ImageExtendWrapper imageExtendWrapper;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ImageExtendBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ImageExtendBo imageExtend = (ImageExtendBo) target;

		validateUuid(errors, imageExtend.getUuid());

	}

	/**
	 * ImageExtend validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.media.ImageExtend.uuid.exist", "UUID不能为空");
		}
	}
	
	//public ImageExtend validateImageRequest(ImageExtend extend)
	public ImageExtend validateImageExtend(ImageExtend extend) {
		String devId = StringUtils.trim(extend.getDevId());
		if(StringUtils.isBlank(devId)) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_DEVID_EMPTY);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_DEVID_EMPTY);
			extend.setSuccess(ImageExtend.SUCCESS_NO);
			return extend;
		}
		
		Long positionId = extend.getPositionId();
		ImageExtend imageExtend = null;
		if(null != positionId ) {
			imageExtend = imageExtendWrapper.findByPositionId(positionId);
			if(null == imageExtend) {
				extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_POSITIONID_INVALID);
				extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_POSITIONID_INVALID);
				extend.setSuccess(ImageExtend.SUCCESS_NO);
				return extend;
			}
			
			if(imageExtend.getDisable()) {
				extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_POSITIONID_DISABLED);
				extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_POSITIONID_DISABLED);
				extend.setSuccess(ImageExtend.SUCCESS_NO);
				return extend;
			}
			
			if(imageExtend.getDeleted()) {
				extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_POSITIONID_DELETED);
				extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_POSITIONID_DELETED);
				extend.setSuccess(ImageExtend.SUCCESS_NO);
				return extend;
			}
			extend.setSuccess(ImageExtend.SUCCESS_YES);
			return extend;
		}
		
		String project = extend.getProject();
		String module = extend.getModule();
		String variety = extend.getVariety();
		String position = extend.getPosition();
		
		if(StringUtils.isBlank(project)) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_PROJECT_EMPTY);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_PROJECT_EMPTY);
			extend.setSuccess(ImageExtend.SUCCESS_NO);
			return extend;
		}
		
		if(StringUtils.isBlank(module)) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_MODULE_EMPTY);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_MODULE_EMPTY);
			extend.setSuccess(ImageExtend.SUCCESS_NO);
			return extend;
		}
		if(StringUtils.isBlank(variety)) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_VARIETY_EMPTY);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_VARIETY_EMPTY);
			extend.setSuccess(ImageExtend.SUCCESS_NO);
			return extend;
		}
		
		if(StringUtils.isBlank(position)) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_POSITION_EMPTY);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_POSITION_EMPTY);
			extend.setSuccess(ImageExtend.SUCCESS_NO);
			return extend;
		}
		
		imageExtend = imageExtendWrapper.findImageExtend(extend);
		
		
		if(null != imageExtend) {
			if(imageExtend.getDisable()) {
				extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_POSITIONID_DISABLED);
				extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_POSITIONID_DISABLED);
				extend.setSuccess(ImageExtend.SUCCESS_NO);
				return extend;
			}
			
			if(imageExtend.getDeleted()) {
				extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_POSITIONID_DELETED);
				extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_POSITIONID_DELETED);
				extend.setSuccess(ImageExtend.SUCCESS_NO);
				return extend;
			}
		}else {
			imageExtend = new ImageExtend();
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_IMAGE_EXTEND_NOT_EXIST);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_IMAGE_EXTEND_NOT_EXIST);
			extend.setSuccess(ImageExtend.SUCCESS_NO);
			return extend;
		}
		imageExtend.setSuccess(ImageExtend.SUCCESS_YES);
		return imageExtend;
	}
	
	public ImageExtend validateImageExtendThenCreate(ImageExtend extend) {
		
		ImageExtend imageExtend = validateImageExtend(extend);
		if(extend.getSuccess()==ImageExtend.SUCCESS_NO) {
			if(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_IMAGE_EXTEND_NOT_EXIST.equals(extend.getRespCode())) {
				Long userId = extend.getUserId();
				
				if(null==userId) {
					extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_USERID_EMPTY);
					extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_USERID_EMPTY);
					extend.setSuccess(ImageExtend.SUCCESS_NO);
					return extend;
				}
				//尚不存在，执行创建
				extend.setCreatedBy(userId);
				extend.setLastModifiedBy(userId);
				String domain = extend.getDomain();
				if(StringUtils.isBlank(domain)) {
					extend.setDomain("http://m.dramala.com");
				}
				imageExtend = imageExtendWrapper.persistEntity(extend);
				if(null == imageExtend) {
					//创建失败
					extend.setSuccess(ImageExtend.SUCCESS_NO);
					extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_IMAGE_EXTEND_CREATE_FAIL);
					extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_IMAGE_EXTEND_CREATE_FAIL);
					return extend;
				}
				imageExtend.setSuccess(ImageExtend.SUCCESS_YES);
			}
		}
		
		imageExtend.setSuccess(ImageExtend.SUCCESS_YES);
		return imageExtend;
	}
	

}
