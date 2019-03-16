package dml.sz0099.course.app.client.validator.media;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jit4j.app.module.define.R;
import org.jit4j.core.pub.transfer.dto.ImageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import dml.sz0099.course.app.client.wrapper.media.ImageRefWrapper;
import dml.sz0099.course.app.module.define.CourseRespCode;
import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.entity.media.ImageRef;
import dml.sz0099.course.app.persist.entity.media.Imagebase;
import dml.sz0099.course.app.persist.entity.media.bo.ImagebaseBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ImagebaseValidator 校验组件
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
public class ImagebaseValidator implements Validator {

	@Autowired
	private ImageExtendValidator imageExtendValidator;
	
	@Autowired
	private ImageRefWrapper imageRefWrapper;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ImagebaseBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ImagebaseBo imagebase = (ImagebaseBo) target;

		validateUuid(errors, imagebase.getUuid());

	}

	/**
	 * Imagebase validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.media.Imagebase.uuid.exist", "UUID不能为空");
		}
	}
	
	

}
