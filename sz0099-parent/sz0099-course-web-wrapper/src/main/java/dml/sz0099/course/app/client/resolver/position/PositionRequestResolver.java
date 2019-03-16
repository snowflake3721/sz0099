/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.position;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jit8j.core.util.GsonBuilderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import dml.sz0099.course.app.client.validator.position.PositionExtendValidator;
import dml.sz0099.course.app.client.wrapper.position.PositionRefWrapper;
import dml.sz0099.course.app.module.define.CourseRespCode;
import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.entity.position.PositionExtend;
import dml.sz0099.course.app.persist.entity.position.PositionRef;
import dml.sz0099.course.app.persist.entity.media.Imagebase;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-09-22 13:15:46
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-09-22	basic init
 * 
 * @formatter:on
 * </pre>
 */
public class PositionRequestResolver {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PositionRequestResolver.class);
	
	
	@Autowired
	private PositionExtendValidator positionExtendValidator;
	
	@Autowired
	private PositionRefWrapper positionRefWrapper;

	
	private PositionRequest trimRequest(PositionRequest request) {
		request.setDevId(StringUtils.trim(request.getDevId()));
		request.setModule(StringUtils.trim(request.getModule()));
		request.setPosition(StringUtils.trim(request.getPosition()));
		request.setPositionId(request.getPositionId());
		request.setProject(StringUtils.trim(request.getProject()));
		request.setUserId(request.getUserId());
		request.setVariety(StringUtils.trim(request.getVariety()));
		request.setDomain(StringUtils.trim(request.getDomain()));
		
		LOGGER.debug(GsonBuilderUtils.toJsonPretty(request));
		return request;
	}
	
	public PositionExtend resolvePositionRequestForDelete(PositionRequest request) {
		PositionExtend extend = new PositionExtend();
		extend.setDevId(StringUtils.trim(request.getDevId()));
		extend.setModule(StringUtils.trim(request.getModule()));
		extend.setPosition(StringUtils.trim(request.getPosition()));
		extend.setPositionId(request.getPositionId());
		extend.setProject(StringUtils.trim(request.getProject()));
		extend.setUserId(request.getUserId());
		extend.setVariety(StringUtils.trim(request.getVariety()));
		//执行校验并生成extendId
		extend = positionExtendValidator.validatePositionExtend(extend);
		/*if(extend.getSuccess()!=PositionExtend.SUCCESS_YES) {
			//校验不通过，直接返回
			return extend;
		}*/
		return extend;
	}

	/**
	 * @param request
	 * @return
	 */
	public PositionExtend resolvePositionRequest(PositionRequest request) {
		
		request = trimRequest(request);
		PositionExtend extend = new PositionExtend();
		
		LOGGER.debug(">>>>resolvePositionRequest begin>>>>>>>");
		extend.setDevId(request.getDevId());
		extend.setModule(request.getModule());
		extend.setPosition(request.getPosition());
		extend.setPositionId(request.getPositionId());
		extend.setProject(request.getProject());
		extend.setUserId(request.getUserId());
		extend.setVariety(request.getVariety());
		extend.setDomain(request.getDomain());
		
		//执行校验并生成extendId
		extend = positionExtendValidator.validatePositionExtendThenCreate(extend);
		if(extend.getSuccess()!=PositionExtend.SUCCESS_YES) {
			//校验不通过，直接返回
			return extend;
		}
		
		Long mainId = request.getMainId();
		Long subId = request.getSubId();
		if(null == mainId) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_MAINID_EMPTY);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_MAINID_EMPTY);
			extend.setSuccess(PositionExtend.SUCCESS_NO);
			return extend;
		}
		
		if(null == subId) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_SUBID_EMPTY);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_SUBID_EMPTY);
			extend.setSuccess(PositionExtend.SUCCESS_NO);
			return extend;
		}
		
		
		Long extendId = extend.getId();
		List<Position> positions = request.getPositionList();
		if(null == positions) {
			positions=new ArrayList<>();
			Position position = new Position();
			positions.add(position);
		}
		
		for(int i=0; i<positions.size();i++) {
			Position position = positions.get(i);
			position.setMainId(mainId);
			position.setSubId(subId);
			position.setExtendId(extendId);
			position.setPositionId(extend.getPositionId());
			position.setUserId(extend.getUserId());
			
			List<PositionRef> positionRefs = position.getPositionRefs();
			if(null != positionRefs && !positionRefs.isEmpty()) {
				for(PositionRef ref : positionRefs) {
					ref.setExtendId(extendId);
					ref.setPositionId(extend.getPositionId());
				}
			}
		}
		
			
		extend.setSuccess(PositionExtend.SUCCESS_YES);//解析成功
		LOGGER.debug(GsonBuilderUtils.toJsonPretty(extend));
			
		LOGGER.debug(">>>>resolvePositionRequest end>>>>>>>");
		return extend;
	}
	
	public PositionExtend validateMaxnum(PositionExtend extend, PositionRequest request){
		//验证数量
		Long extendId = extend.getId();
		Integer mainMaxnum = extend.getMainMaxnum();
		Integer subMaxnum = extend.getSubMaxnum();
		Integer sizeMax = extend.getRefMaxnum();
		
		Long mainId = request.getMainId();
		Long subId = request.getSubId();
		
		PositionRef positionRef = new PositionRef();
		positionRef.setMainId(mainId);
		//positionRef.setSubId(subId);
		positionRef.setExtendId(extendId);
		positionRef.setPositionId(extend.getPositionId());
		
		if(null == mainId) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_MAINID_EMPTY);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_MAINID_EMPTY);
			extend.setSuccess(PositionExtend.SUCCESS_NO);
			return extend;
		}
		
		if(null == subId) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_SUBID_EMPTY);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_SUBID_EMPTY);
			extend.setSuccess(PositionExtend.SUCCESS_NO);
			return extend;
		}
		
		Long subNum = positionRefWrapper.countForSub(positionRef);
		if(subNum>subMaxnum) {
			//子类产品数已超限
			
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_SUBNUM_EXTRA);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_SUBNUM_EXTRA);
			extend.setSuccess(PositionExtend.SUCCESS_NO);
			
			return extend;
		}
		
		Long mainNum = positionRefWrapper.countForMain(positionRef);
		if(mainNum>mainMaxnum) {
			//主体图片数已超限
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_MAINNUM_EXTRA);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_MAINNUM_EXTRA);
			extend.setSuccess(PositionExtend.SUCCESS_NO);
			return extend;
		}
		
			
			Long userId = request.getUserId();
			//全部校验通过，生成图片保存路径、访问路径等信息，并将其持久化
			Imagebase imagebase = new Imagebase();
			//imagebase.setExtend(extend);
			imagebase.setExtendId(extendId);
			
			imagebase.setUserId(userId);
			imagebase.setCreatedBy(userId);
			imagebase.setLastModifiedBy(userId);
			
			Integer strategy = request.getStrategy();
			if(null == strategy || 0==strategy) {
				strategy=1;
			}
			imagebase.setStrategy(strategy);
			
			PositionRef ref = new PositionRef();
			
			ref.setUserId(userId);
			ref.setMainId(request.getMainId());
			//ref.setSubId(request.getSubId());
			ref.setExtendId(extendId);
			ref.setCreatedBy(userId);
			ref.setLastModifiedBy(userId);
			
			
			//生成文件名,文件名即id
			return extend;
	}
	
}
