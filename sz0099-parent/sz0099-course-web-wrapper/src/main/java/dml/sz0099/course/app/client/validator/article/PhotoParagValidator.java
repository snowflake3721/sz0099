package dml.sz0099.course.app.client.validator.article;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.resolver.adaptor.media.ParagArticleAdaptor;
import dml.sz0099.course.app.client.wrapper.article.PhotoParagWrapper;
import dml.sz0099.course.app.module.define.CoeArticleRespCode;
import dml.sz0099.course.app.persist.entity.article.PhotoParag;
import dml.sz0099.course.app.persist.entity.article.bo.PhotoParagBo;
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
@Component("articlePhotoParagValidator")
public class PhotoParagValidator implements Validator {
	
	@Autowired
	private PhotoParagWrapper photoParagWrapper;
	
	@Autowired
	private ParagArticleAdaptor paragArticleAdaptor;

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
			errors.rejectValue("uuid", "org.jit4j.app.article.PhotoParag.uuid.exist", "UUID不能为空");
		}
	}
	
	public boolean validateForCreate(ImageRef ref) {
		
		Long userId = ref.getUserId();
		if(null == userId) {
			ref.setSuccess(PhotoParag.SUCCESS_NO);
			ref.setRespCode(CoeArticleRespCode.SZ0099_CODE_OOD_ARTICLE_PARAGRAGH_PHOTO_USERID_EMPLTY);
			ref.setRespMsg(CoeArticleRespCode.SZ0099_MSG_OOD_ARTICLE_PARAGRAGH_PHOTO_USERID_EMPLTY);
			return false;
		}
		
		Long mainId=ref.getMainId();
		if(null == mainId) {
			ref.setSuccess(PhotoParag.SUCCESS_NO);
			ref.setRespCode(CoeArticleRespCode.SZ0099_CODE_OOD_ARTICLE_PHOTO_MAINID_EMPLTY);
			ref.setRespMsg(CoeArticleRespCode.SZ0099_MSG_OOD_ARTICLE_PHOTO_MAINID_EMPLTY);
			return false;
		}
		
		Long count = photoParagWrapper.countByMainId(mainId);
		ImageExtend extend = paragArticleAdaptor.config();
		if(null != count) {
			Integer mainMaxnum = extend.getMainMaxnum();
			if(count.intValue() >= mainMaxnum) {
				ref.setSuccess(PhotoParag.SUCCESS_NO);
				ref.setRespCode(CoeArticleRespCode.SZ0099_CODE_OOD_ARTICLE_PHOTO_MAINNUM_EXTRA);
				ref.setRespMsg(CoeArticleRespCode.SZ0099_MSG_OOD_ARTICLE_PHOTO_MAINNUM_EXTRA);
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
			ref.setSuccess(PhotoParag.SUCCESS_NO);
			ref.setRespCode(CoeArticleRespCode.SZ0099_CODE_OOD_ARTICLE_PARAGRAGH_PHOTO_USERID_EMPLTY);
			ref.setRespMsg(CoeArticleRespCode.SZ0099_MSG_OOD_ARTICLE_PARAGRAGH_PHOTO_USERID_EMPLTY);
			return false;
		}
		
		Long id = ref.getId();
		if(null == id) {
			ref.setSuccess(PhotoParag.SUCCESS_NO);
			ref.setRespCode(CoeArticleRespCode.SZ0099_CODE_OOD_ARTICLE_PARAGRAGH_PHOTO_ID_EMPLTY);
			ref.setRespMsg(CoeArticleRespCode.SZ0099_MSG_OOD_ARTICLE_PARAGRAGH_PHOTO_ID_EMPLTY);
			return false;
		}
		
		PhotoParag entity = photoParagWrapper.findById(id);
		if(null == entity) {
			ref.setSuccess(PhotoParag.SUCCESS_NO);
			ref.setRespCode(CoeArticleRespCode.SZ0099_CODE_OOD_ARTICLE_PARAGRAGH_PHOTO_ID_ENTITY_EMPLTY);
			ref.setRespMsg(CoeArticleRespCode.SZ0099_MSG_OOD_ARTICLE_PARAGRAGH_PHOTO_ID_ENTITY_EMPLTY);
			return false;
		}
		
		Long userIdE = entity.getUserId();
		if(!userId.equals(userIdE)) {
			ref.setSuccess(PhotoParag.SUCCESS_NO);
			ref.setRespCode(CoeArticleRespCode.SZ0099_CODE_OOD_ARTICLE_PARAGRAGH_PHOTO_USERID_INVALID);
			ref.setRespMsg(CoeArticleRespCode.SZ0099_MSG_OOD_ARTICLE_PARAGRAGH_PHOTO_USERID_INVALID);
			return false;
		}
		return true;
		
	}

}
