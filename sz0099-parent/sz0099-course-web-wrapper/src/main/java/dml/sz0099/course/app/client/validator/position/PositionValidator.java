package dml.sz0099.course.app.client.validator.position;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.position.PositionExtendWrapper;
import dml.sz0099.course.app.client.wrapper.position.PositionRefWrapper;
import dml.sz0099.course.app.client.wrapper.position.PositionWrapper;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.module.define.CourseRespCode;
import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.entity.position.PositionExtend;
import dml.sz0099.course.app.persist.entity.position.PositionRef;
import dml.sz0099.course.app.persist.entity.position.bo.PositionBo;
import dml.sz0099.course.app.persist.entity.order.CoeOrder;
import dml.sz0099.course.app.persist.entity.position.bo.PositionBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * PositionValidator 校验组件
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
public class PositionValidator implements Validator {

	@Autowired
	private PositionExtendWrapper positionExtendWrapper;
	
	@Autowired
	private PositionWrapper positionWrapper;
	
	@Autowired
	private PositionRefWrapper positionRefWrapper;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return PositionBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		PositionBo position = (PositionBo) target;

		validateUuid(errors, position.getUuid());

	}

	/**
	 * Position validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.position.Position.uuid.exist", "UUID不能为空");
		}
	}
	
	
	public boolean validateAddPosition(Position position) {
		
		boolean checked = checkExist(position);
		if(!checked) {
			return false;
		}
		
		/*String code = position.getCode();//验证唯一性
		Position entity = positionWrapper.findByCode(code);
		if(null != entity) {
			position.setSuccess(Position.SUCCESS_NO);
			position.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_CODE_EXIST);
			position.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_CODE_EXIST);
			return false;
		}*/
		
		return true;
	}
	
	public boolean validateMergePosition(Position position) {
		
		Long id = position.getId();
		Position entity = positionWrapper.findById(id);
		if(null == entity) {
			position.setSuccess(Position.SUCCESS_NO);
			position.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_NOT_EXIST);
			position.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_NOT_EXIST);
			return false;
		}
		
		boolean checked = checkExist(position);
		if(!checked) {
			return false;
		}
		
		/*String code = position.getCode();//验证唯一性
		Position entityC = positionWrapper.findByCode(code);
		if(null != entityC && !entity.getId().equals(id)) {
			position.setSuccess(Position.SUCCESS_NO);
			position.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_CODE_EXIST);
			position.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_CODE_EXIST);
			return false;
		}*/
		
		return true;
	}

	/**
	 * @param position
	 */
	private boolean checkExist(Position position) {
		Long extendId = position.getExtendId();//验证项目分类是否存在
		Long positionId = position.getPositionId();
		Long userId = position.getUserId();//验证用户是否登录
		
		if(null == userId) {
			position.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_EXTEND_USER_NOT_LOGIN);
			position.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_EXTEND_USER_NOT_LOGIN);
			position.setSuccess(PositionExtend.SUCCESS_NO);
			return false;
		}
		
		Long mainId = position.getMainId();
		if(null == mainId) {
			position.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_MAINID_EMPTY);
			position.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_MAINID_EMPTY);
			position.setSuccess(PositionExtend.SUCCESS_NO);
			return false;
		}
		
		Long subId = position.getSubId();
		if(null == subId) {
			position.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_SUBID_EMPTY);
			position.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_SUBID_EMPTY);
			position.setSuccess(PositionExtend.SUCCESS_NO);
			return false;
		}
		
		PositionExtend extend = null;
		if(null != extendId) {
			extend = positionExtendWrapper.findById(extendId);
		}
		
		if(null == extend && null != positionId) {
			extend = positionExtendWrapper.findByPositionId(positionId);
		}
		
		if(null == extend) {
			position.setSuccess(Position.SUCCESS_NO);
			position.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_EXTEND_NOT_EXIST);
			position.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_EXTEND_NOT_EXIST);
			return false;
		}
		return true;
	}
	
	public boolean validateForDelete(Position position) {
		boolean checked = checkExist(position);
		if(!checked) {
			return false;
		}
		
		Long id = position.getId();
		boolean exist = positionWrapper.existById(id);
		if(!exist) {
			position.setSuccess(CoeOrder.SUCCESS_NO);
			position.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_POSITION_NOT_EXIST);
			position.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_POSITION_NOT_EXIST);
			return false;
		}
		
		/*//检查是否有子类
		Long childrenNum = positionWrapper.countByParentId(position);
		if(null != childrenNum && childrenNum>0) {
			position.setSuccess(CoeOrder.SUCCESS_NO);
			position.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_POSITION_HAS_CHILDREN);
			position.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_POSITION_HAS_CHILDREN);
			return false;
		}*/
		
		//检查类别下是否绑定有文章
		PositionRef positionRef = new PositionRef();
		positionRef.setBaseId(id);
		Long refNum = positionRefWrapper.countForBase(positionRef);
		if(null != refNum && refNum>0) {
			position.setSuccess(CoeOrder.SUCCESS_NO);
			position.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_POSITION_HAS_REF);
			position.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_POSITION_HAS_REF);
			return false;
		}
		return true;
	}
	
	
	public boolean validateForDeleteAll(Position position) {
		boolean checked = checkExist(position);
		if(!checked) {
			return false;
		}
		return true;
	}

}
