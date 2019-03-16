/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.profession;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jit8j.core.util.GsonBuilderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import dml.sz0099.course.app.client.validator.profession.ProfessionExtendValidator;
import dml.sz0099.course.app.client.wrapper.profession.ProfessionRefWrapper;
import dml.sz0099.course.app.module.define.CourseRespCode;
import dml.sz0099.course.app.persist.entity.profession.Profession;
import dml.sz0099.course.app.persist.entity.profession.ProfessionExtend;
import dml.sz0099.course.app.persist.entity.profession.ProfessionRef;
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
public class ProfessionRequestResolver {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionRequestResolver.class);
	
	
	@Autowired
	private ProfessionExtendValidator professionExtendValidator;
	
	@Autowired
	private ProfessionRefWrapper professionRefWrapper;

	
	private ProfessionRequest trimRequest(ProfessionRequest request) {
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
	
	public ProfessionExtend resolveProfessionRequestForDelete(ProfessionRequest request) {
		ProfessionExtend extend = new ProfessionExtend();
		extend.setDevId(StringUtils.trim(request.getDevId()));
		extend.setModule(StringUtils.trim(request.getModule()));
		request.setPosition(StringUtils.trim(request.getPosition()));
		request.setPositionId(request.getPositionId());
		extend.setProject(StringUtils.trim(request.getProject()));
		extend.setUserId(request.getUserId());
		extend.setVariety(StringUtils.trim(request.getVariety()));
		//执行校验并生成extendId
		extend = professionExtendValidator.validateProfessionExtend(extend);
		/*if(extend.getSuccess()!=ProfessionExtend.SUCCESS_YES) {
			//校验不通过，直接返回
			return extend;
		}*/
		return extend;
	}

	/**
	 * @param request
	 * @return
	 */
	public ProfessionExtend resolveProfessionRequest(ProfessionRequest request) {
		
		request = trimRequest(request);
		ProfessionExtend extend = new ProfessionExtend();
		
		LOGGER.debug(">>>>resolveProfessionRequest begin>>>>>>>");
		extend.setDevId(request.getDevId());
		extend.setModule(request.getModule());
		request.setPosition(StringUtils.trim(request.getPosition()));
		request.setPositionId(request.getPositionId());
		extend.setProject(request.getProject());
		extend.setUserId(request.getUserId());
		extend.setVariety(request.getVariety());
		extend.setDomain(request.getDomain());
		
		//执行校验并生成extendId
		extend = professionExtendValidator.validateProfessionExtendThenCreate(extend);
		if(extend.getSuccess()!=ProfessionExtend.SUCCESS_YES) {
			//校验不通过，直接返回
			return extend;
		}
		
		Long mainId = request.getMainId();
		Long subId = request.getSubId();
		if(null == mainId) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_MAINID_EMPTY);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_MAINID_EMPTY);
			extend.setSuccess(ProfessionExtend.SUCCESS_NO);
			return extend;
		}
		
		if(null == subId) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_SUBID_EMPTY);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_SUBID_EMPTY);
			extend.setSuccess(ProfessionExtend.SUCCESS_NO);
			return extend;
		}
		
		
		Long extendId = extend.getId();
		List<Profession> professions = request.getProfessionList();
		if(null == professions) {
			professions=new ArrayList<>();
			Profession profession = new Profession();
			professions.add(profession);
		}
		
		for(int i=0; i<professions.size();i++) {
			Profession profession = professions.get(i);
			//profession.setMainId(mainId);
			//profession.setSubId(subId);
			profession.setExtendId(extendId);
			profession.setPositionId(extend.getPositionId());
			profession.setUserId(extend.getUserId());
			
			List<ProfessionRef> refList = profession.getRefList();
			if(null != refList && !refList.isEmpty()) {
				for(ProfessionRef ref : refList) {
					ref.setExtendId(extendId);
					ref.setPositionId(extend.getPositionId());
				}
			}
		}
		
			
		extend.setSuccess(ProfessionExtend.SUCCESS_YES);//解析成功
		LOGGER.debug(GsonBuilderUtils.toJsonPretty(extend));
			
		LOGGER.debug(">>>>resolveProfessionRequest end>>>>>>>");
		return extend;
	}
	
	public ProfessionExtend validateMaxnum(ProfessionExtend extend, ProfessionRequest request){
		//验证数量
		Long extendId = extend.getId();
		Integer mainMaxnum = extend.getMainMaxnum();
		Integer subMaxnum = extend.getSubMaxnum();
		Integer sizeMax = extend.getRefMaxnum();
		
		Long mainId = request.getMainId();
		Long subId = request.getSubId();
		
		ProfessionRef professionRef = new ProfessionRef();
		professionRef.setMainId(mainId);
		//professionRef.setSubId(subId);
		professionRef.setExtendId(extendId);
		professionRef.setPositionId(extend.getPositionId());
		
		if(null == mainId) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_MAINID_EMPTY);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_MAINID_EMPTY);
			extend.setSuccess(ProfessionExtend.SUCCESS_NO);
			return extend;
		}
		
		if(null == subId) {
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_SUBID_EMPTY);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_SUBID_EMPTY);
			extend.setSuccess(ProfessionExtend.SUCCESS_NO);
			return extend;
		}
		
		Long subNum = professionRefWrapper.countForSub(professionRef);
		if(subNum>subMaxnum) {
			//子类产品数已超限
			
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_SUBNUM_EXTRA);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_SUBNUM_EXTRA);
			extend.setSuccess(ProfessionExtend.SUCCESS_NO);
			
			return extend;
		}
		
		Long mainNum = professionRefWrapper.countForMain(professionRef);
		if(mainNum>mainMaxnum) {
			//主体图片数已超限
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_MAINNUM_EXTRA);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_MAINNUM_EXTRA);
			extend.setSuccess(ProfessionExtend.SUCCESS_NO);
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
			
			ProfessionRef ref = new ProfessionRef();
			
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
