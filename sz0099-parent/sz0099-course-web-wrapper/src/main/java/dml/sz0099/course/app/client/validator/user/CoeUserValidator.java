package dml.sz0099.course.app.client.validator.user;

import org.apache.commons.lang3.StringUtils;
import org.jit4j.app.client.controller.shiro.basic.LoginValidator;
import org.jit4j.app.client.wrapper.auth.RoleWrapper;
import org.jit4j.app.client.wrapper.auth.UserRoleWrapper;
import org.jit4j.app.persist.entity.auth.Role;
import org.jit4j.app.persist.entity.auth.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.article.CoeArticleWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
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
public class CoeUserValidator implements Validator {
	
	@Autowired
	private LoginValidator loginValidator;
	
	@Autowired
	private CoeUserWrapper coeUserWrapper;
	
	
	@Autowired
	private CoeUserVerifyWrapper coeUserVerifyWrapper;
	
	
	@Autowired
	private UserRoleWrapper userRoleWrapper;
	
	@Autowired
	private RoleWrapper roleWrapper;
	
	@Autowired
	private CoeArticleWrapper coeArticleWrapper;

	@Override
	public boolean supports(Class<?> clazz) {
		return CoeUserBo.class.equals(clazz);
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
	
	
	private boolean validateEmail(Errors errors, CoeUser user) {
		String email = user.getEmail();
		
		boolean emailChecked = loginValidator.validateEmailFormat(errors, email);
		if(!emailChecked) {
			ObjectError oe = errors.getAllErrors().get(0);
			user.setSuccess(CoeUser.SUCCESS_NO);
			user.setRespCode(oe.getCode());
			user.setRespMsg(oe.getDefaultMessage());
			return false;
		}
		return true;
	}
	
	public boolean validateForModifyEmail(Errors errors, CoeUser user) {
		
		boolean emailChecked = validateEmail(errors, user);
		if(!emailChecked) {
			return false;
		}
		
		boolean checked = validateForUserEmail(errors,user);
		
		if(!checked) {
			return false;
		}
		
		return true;
	}

	/**
	 * @param errors
	 * @param user
	 * @return
	 */
	private boolean validateForUserEmail(Errors errors, CoeUser user) {
		String email = user.getEmail();
		CoeUser entity = coeUserWrapper.findByEmail(email);
		if(null != entity) {
			user.setSuccess(CoeUser.SUCCESS_NO);
			user.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_EMAIL_EXISTS);
			user.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_EMAIL_EXISTS);
			return false;
		}
		
		return true;
	}
	
	public boolean validateForModifyMobile(Errors errors, CoeUser user) {
		String mobile = user.getMobile();
		boolean mobileChecked = loginValidator.validateMobileFormat(errors, mobile);
		if (!mobileChecked) {
			ObjectError oe = errors.getAllErrors().get(0);
			user.setSuccess(CoeUser.SUCCESS_NO);
			user.setRespCode(oe.getCode());
			user.setRespMsg(oe.getDefaultMessage());
			return false;
		}
		CoeUser exist = coeUserWrapper.findByMobile(mobile);
		if(null != exist) {
			user.setSuccess(CoeUser.SUCCESS_NO);
			user.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_MOBILE_EXISTS);
			user.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_MOBILE_EXISTS);
			return false;
		}
		
		return true;
	}
	
	public boolean validateForModifyNickname(Errors errors, CoeUser user) {
		String nickname = user.getNickname();
		
		if (StringUtils.isBlank(nickname)) {
			user.setSuccess(CoeUser.SUCCESS_NO);
			user.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_NICKNAME_EMPTY);
			user.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_NICKNAME_EMPTY);
			return false;
		}
		return true;
	}
	
	public boolean validateForModifyPostname(Errors errors, CoeUser user) {
		String postname = user.getPostname();
		
		if (StringUtils.isBlank(postname)) {
			user.setSuccess(CoeUser.SUCCESS_NO);
			user.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_POSTNAME_EMPTY);
			user.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_POSTNAME_EMPTY);
			return false;
		}
		return true;
	}
	
	public static final String PRIVACY_POSTNAMESHOW="postnameShow";
	public static final String PRIVACY_EMAILSHOW="emailShow";
	public static final String PRIVACY_MOBILESHOW="mobileShow";
	public static final String PRIVACY_QQSHOW="qqShow";
	public boolean validateForPrivacySetting(Errors errors, CoeUser user, String fieldname) {
		Integer showValue = user.getPostnameShow();
		
		if(PRIVACY_POSTNAMESHOW.equals(fieldname)) {
			showValue = user.getPostnameShow();
		}else if(PRIVACY_EMAILSHOW.equals(fieldname)) {
			showValue = user.getEmailShow();
		}else if(PRIVACY_MOBILESHOW.equals(fieldname)) {
			showValue = user.getMobileShow();
		}else if(PRIVACY_QQSHOW.equals(fieldname)) {
			showValue = user.getQqShow();
		}
		
		if (showValue == null 
				|| (!CoeUser.PRIVACY_SETTING_0_HIDE.getValueInt().equals(showValue) 
					&& !CoeUser.PRIVACY_SETTING_1_SHOW.getValueInt().equals(showValue)
						)
			) {
			user.setSuccess(CoeUser.SUCCESS_NO);
			user.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_PRIVATE_SETTING_ONLY1OR0);
			user.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_PRIVATE_SETTING_ONLY1OR0);
			return false;
		}
		return true;
	}
	
	/*public boolean validateForModifySayword(Errors errors, CoeUser user) {
		String sayword = user.getSayword();
		
		if (StringUtils.isBlank(sayword)) {
			user.setSuccess(CoeUser.SUCCESS_NO);
			user.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USER_SAYWORD_EMPTY);
			user.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USER_SAYWORD_EMPTY);
			return false;
		}
		
		Long userId = user.getUserId();
		if (userId == null) {
			user.setSuccess(CoeUser.SUCCESS_NO);
			user.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_USERID_EMPTY);
			user.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_USERID_EMPTY);
			return false;
		}
		return true;
	}*/
	
	
	public boolean validateForApplyClubLeader(UserRole userRole) {
		Long userId = userRole.getUserId();
		String leaderCode = Role.CODE_CLUB_LEADER.getValueStr();
		Role role = roleWrapper.findRoleByCode(leaderCode);
		Long roleId = role.getId();
		
		UserRole entity = userRoleWrapper.findByUserIdAndRoleId(userId, roleId);
		if(null != entity) {
			Integer verified = entity.getVerified();
			if(UserRole.VERIFIED_YES.getValueInt().equals(verified)) {
				
			}
		}
		
		
		Long articleNum = coeArticleWrapper.countPublishedByUserId(userId);
		if(null == articleNum || articleNum<2) {
			userRole.setSuccess(CoeUser.SUCCESS_NO);
			userRole.setRespCode(CourseProductRespCode.SZ0099_CODE_OOD_USER_APPLY_CLUB_LEADER_ARTICLE_NUM_NOT_ENOUGH);
			userRole.setRespMsg(CourseProductRespCode.SZ0099_MSG_OOD_USER_APPLY_CLUB_LEADER_ARTICLE_NUM_NOT_ENOUGH);
			return false;
		}
		
		
		
		return true;
		
	}
	
	
	public boolean validateForDoApplyClubLeader(CoeUser coeUser) {
		Long userId=coeUser.getUserId();
		CoeUser entity = coeUserWrapper.findByUserId(userId);
		if(null == entity) {
			coeUser.setSuccess(CoeUser.SUCCESS_NO);
			coeUser.setRespCode(CourseProductRespCode.SZ0099_CODE_OOD_USER_APPLY_CLUB_LEADER_ARTICLE_USER_NOT_EXIST);
			coeUser.setRespMsg(CourseProductRespCode.SZ0099_MSG_OOD_USER_APPLY_CLUB_LEADER_ARTICLE_USER_NOT_EXIST);
			return false;
		}
		
		CoeUserVerify verify = coeUserVerifyWrapper.findByUserId(userId);
		if(null == verify) {
			coeUser.setSuccess(CoeUser.SUCCESS_NO);
			coeUser.setRespCode(CourseProductRespCode.SZ0099_CODE_OOD_USER_APPLY_CLUB_LEADER_ARTICLE_USER_NOT_EXIST);
			coeUser.setRespMsg(CourseProductRespCode.SZ0099_MSG_OOD_USER_APPLY_CLUB_LEADER_ARTICLE_USER_NOT_EXIST);
			return false;
		}
		
		Integer idstatus=verify.getIdstatus();
		if(!CoeUserVerify.IDSTATUS_2_YES.getValueInt().equals(idstatus)) {
			coeUser.setSuccess(CoeUser.SUCCESS_NO);
			coeUser.setRespCode(CourseProductRespCode.SZ0099_CODE_OOD_USER_APPLY_CLUB_LEADER_ARTICLE_IDSTATUS_NOT_VERIFIED);
			coeUser.setRespMsg(CourseProductRespCode.SZ0099_MSG_OOD_USER_APPLY_CLUB_LEADER_ARTICLE_IDSTATUS_NOT_VERIFIED);
			return false;
		}
		
		String mobile = entity.getMobile();
		if(StringUtils.isBlank(mobile)) {
			coeUser.setSuccess(CoeUser.SUCCESS_NO);
			coeUser.setRespCode(CourseProductRespCode.SZ0099_CODE_OOD_USER_APPLY_CLUB_LEADER_ARTICLE_MOBILE_NOT_BIND);
			coeUser.setRespMsg(CourseProductRespCode.SZ0099_MSG_OOD_USER_APPLY_CLUB_LEADER_ARTICLE_MOBILE_NOT_BIND);
			return false;
		}
		
		UserRole userRole = coeUserWrapper.findClubLeader(coeUser);
		if(userRole != null) {
			Integer verified = userRole.getVerified();
			
			
		}
		return true;
	}

}
