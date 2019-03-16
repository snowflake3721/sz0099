package dml.sz0099.course.app.client.validator.user;

import org.apache.commons.lang3.StringUtils;
import org.jit4j.app.client.controller.shiro.basic.LoginValidator;
import org.jit8j.core.util.IdentityCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.user.CoeUserVerifyWrapper;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.product.bo.CoeUserBo;
import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.CoeUserVerify;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeUserValidator 校验组件
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
public class CoeUserVerifyValidator implements Validator {
	
	@Autowired
	private LoginValidator loginValidator;
	
	@Autowired
	private CoeUserVerifyWrapper coeUserVerifyWrapper;

	@Override
	public boolean supports(Class<?> clazz) {
		return CoeUserVerify.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CoeUserBo coeUser = (CoeUserBo) target;

		validateUuid(errors, coeUser.getUuid());

	}

	/**
	 * CoeUser validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.product.CoeUser.uuid.exist", "UUID不能为空");
		}
	}
	
	
	public boolean validateForModifyRealname(Errors errors, CoeUserVerify user) {
		String realname = user.getRealname();
		
		if (StringUtils.isBlank(realname)) {
			user.setSuccess(CoeUser.SUCCESS_NO);
			user.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_REALNAME_EMPTY);
			user.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_REALNAME_EMPTY);
			return false;
		}
		return true;
	}
	
	/**
	 * 校验身份证合法性
	 * @param errors
	 * @param verify
	 * @return
	 */
	public boolean validateForIdentityPass(Errors errors, CoeUserVerify verify) {
		
		
		Long userId = verify.getUserId();
		if(null == userId) {
			verify.setSuccess(CoeUser.SUCCESS_NO);
			verify.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_USER_NOT_LOGIN);
			verify.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_USER_NOT_LOGIN);
			return false;
		}
		
		Long id = verify.getId();
		if(null == id) {
			verify.setSuccess(CoeUser.SUCCESS_NO);
			verify.setRespCode(CourseProductRespCode.SZ0099_CODE_OOD_USER_VERIFY_ID_EMPTY);
			verify.setRespMsg(CourseProductRespCode.SZ0099_MSG_OOD_USER_VERIFY_ID_EMPTY);
			return false;
		}
		
		CoeUserVerify entityVerify = coeUserVerifyWrapper.findById(id);
		if(null == entityVerify) {
			verify.setSuccess(CoeUser.SUCCESS_NO);
			verify.setRespCode(CourseProductRespCode.SZ0099_CODE_OOD_USER_VERIFY_EMPTY);
			verify.setRespMsg(CourseProductRespCode.SZ0099_MSG_OOD_USER_VERIFY_EMPTY);
			return false;
		}
		
		String identity = entityVerify.getIdentity();
		if(StringUtils.isBlank(identity)) {
			verify.setSuccess(CoeUser.SUCCESS_NO);
			verify.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_IDENTITY_EMPTY);
			verify.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_IDENTITY_EMPTY);
			return false;
		}
		boolean identityChecked = IdentityCodeUtil.isValidIdNo(identity);
		if (!identityChecked) {
			verify.setSuccess(CoeUser.SUCCESS_NO);
			verify.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_IDENTITY_INVALID);
			verify.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_IDENTITY_INVALID);
			return false;
		}
		
		String face = entityVerify.getIdentityFace();
		if(StringUtils.isBlank(face)) {
			verify.setSuccess(CoeUser.SUCCESS_NO);
			verify.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_IDVERIFY_FACE_EMPTY);
			verify.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_IDVERIFY_FACE_EMPTY);
			return false;
		}
		
		String back = entityVerify.getIdentityFace();
		if(StringUtils.isBlank(back)) {
			verify.setSuccess(CoeUser.SUCCESS_NO);
			verify.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_IDVERIFY_FACE_EMPTY);
			verify.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_IDVERIFY_FACE_EMPTY);
			return false;
		}
		
		Integer vefiryStatus = entityVerify.getIdstatus();
		
		if(CoeUserVerify.IDSTATUS_2_YES.getValueInt().equals(vefiryStatus)) {
			verify.setSuccess(CoeUser.SUCCESS_NO);
			verify.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_IDVERIFY_HAS_PASSED);
			verify.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_IDVERIFY_HAS_PASSED);
			return false;
		}
		
		return true;
	}
	
	/**
	 * 审核拒绝
	 * @param errors
	 * @param verify
	 * @return
	 */
	public boolean validateForIdentityReject(Errors errors, CoeUserVerify verify) {
		
		
		Long userId = verify.getUserId();
		if(null == userId) {
			verify.setSuccess(CoeUser.SUCCESS_NO);
			verify.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_USER_NOT_LOGIN);
			verify.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_USER_NOT_LOGIN);
			return false;
		}
		
		Long id = verify.getId();
		if(null == id) {
			verify.setSuccess(CoeUser.SUCCESS_NO);
			verify.setRespCode(CourseProductRespCode.SZ0099_CODE_OOD_USER_VERIFY_ID_EMPTY);
			verify.setRespMsg(CourseProductRespCode.SZ0099_MSG_OOD_USER_VERIFY_ID_EMPTY);
			return false;
		}
		
		String remark = verify.getRemark();
		if(StringUtils.isBlank(remark)) {
			verify.setSuccess(CoeUser.SUCCESS_NO);
			verify.setRespCode(CourseProductRespCode.SZ0099_CODE_OOD_USER_VERIFY_REMARK_BLANK);
			verify.setRespMsg(CourseProductRespCode.SZ0099_MSG_OOD_USER_VERIFY_REMARK_BLANK);
			return false;
		}
		
		CoeUserVerify entityVerify = coeUserVerifyWrapper.findById(id);
		if(null == entityVerify) {
			verify.setSuccess(CoeUser.SUCCESS_NO);
			verify.setRespCode(CourseProductRespCode.SZ0099_CODE_OOD_USER_VERIFY_EMPTY);
			verify.setRespMsg(CourseProductRespCode.SZ0099_MSG_OOD_USER_VERIFY_EMPTY);
			return false;
		}
		
		return true;
	}
	
	public boolean validateForApplyIdentity(Errors errors, CoeUserVerify user) {
		String identity = StringUtils.trim(user.getIdentity());
		user.setIdentity(identity);
		
		Long userId = user.getUserId();
		if(null == userId) {
			user.setSuccess(CoeUser.SUCCESS_NO);
			user.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USERID_EMPTY);
			user.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USERID_EMPTY);
			return false;
		}
		
		if(StringUtils.isBlank(identity)) {
			user.setSuccess(CoeUser.SUCCESS_NO);
			user.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_IDENTITY_EMPTY);
			user.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_IDENTITY_EMPTY);
			return false;
		}
		boolean identityChecked = IdentityCodeUtil.isValidIdNo(identity);
		if (!identityChecked) {
			user.setSuccess(CoeUser.SUCCESS_NO);
			user.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_IDENTITY_INVALID);
			user.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_IDENTITY_INVALID);
			return false;
		}
		
		CoeUserVerify exist = coeUserVerifyWrapper.findNotSelfByIdentity(user);
		if(null != exist){
			user.setSuccess(CoeUser.SUCCESS_NO);
			user.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_IDENTITY_EXISTS);
			user.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_IDENTITY_EXISTS);
			return false;
		}
		
		CoeUserVerify entity = coeUserVerifyWrapper.findByUserId(userId);
		if(null == entity) {
			user.setSuccess(CoeUser.SUCCESS_NO);
			user.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_NOT_EXIST);
			user.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_NOT_EXIST);
			return false;
		}
		Long userIdE = entity.getUserId();
		if(!userIdE.equals(userId)) {
			user.setSuccess(CoeUser.SUCCESS_NO);
			user.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_ID_INVALID);
			user.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_ID_INVALID);
			return false;
		}
		Integer vefiryStatus = entity.getIdstatus();
		if(CoeUserVerify.IDSTATUS_1_PROCESS.getValueInt().equals(vefiryStatus)) {
			user.setSuccess(CoeUser.SUCCESS_NO);
			user.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_IDVERIFY_HAS_INPROCESS);
			user.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_IDVERIFY_HAS_INPROCESS);
			return false;
		}
		
		if(CoeUserVerify.IDSTATUS_2_YES.getValueInt().equals(vefiryStatus)) {
			user.setSuccess(CoeUser.SUCCESS_NO);
			user.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_IDVERIFY_HAS_PASSED);
			user.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_IDVERIFY_HAS_PASSED);
			return false;
		}
		
		String face = entity.getIdentityFace();
		if(StringUtils.isBlank(face)) {
			user.setSuccess(CoeUser.SUCCESS_NO);
			user.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_IDVERIFY_FACE_EMPTY);
			user.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_IDVERIFY_FACE_EMPTY);
			return false;
		}
		
		String back = entity.getIdentityFace();
		if(StringUtils.isBlank(back)) {
			user.setSuccess(CoeUser.SUCCESS_NO);
			user.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_IDVERIFY_FACE_EMPTY);
			user.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_IDVERIFY_FACE_EMPTY);
			return false;
		}
		
		return true;
	}


}
