/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.profession;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jit4j.core.persist.entity.BaseEntityExtendLong;
import org.jit4j.core.persist.page.Jit4jPageResult;
import org.jit4j.core.persist.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.client.wrapper.profession.ProfessionExtendWrapper;
import dml.sz0099.course.app.client.wrapper.profession.ProfessionRefWrapper;
import dml.sz0099.course.app.client.wrapper.profession.ProfessionWrapper;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.CoeCategArticle;
import dml.sz0099.course.app.persist.entity.profession.Profession;
import dml.sz0099.course.app.persist.entity.profession.ProfessionExtend;
import dml.sz0099.course.app.persist.entity.profession.ProfessionRef;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-09-23 12:04:45
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-09-23	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class ProfessionDefaultAdaptor<T> implements ProfessionAdaptor<T>{
	
	@Autowired
	protected ProfessionWrapper professionWrapper;
	
	@Autowired
	protected ProfessionRefWrapper professionRefWrapper;
	
	@Autowired
	protected ProfessionExtendWrapper professionExtendWrapper;
	
	protected ProfessionExtend configExtend;
	
	protected Map<Integer, ProfessionRefHandler> refHandlerHolder;

	@Override
	public T convert(ProfessionExtend professionExtend) {
		return null;
	}

	@Override
	public boolean persist(T t) {
		return false;
	}

	@Override
	public boolean mergeProfessionRef(ProfessionRef ref) {
		Long orderSeq = ref.getOrderSeq();
		if(null == orderSeq) {
			Integer topLevel = ref.getTopLevel();
			if(null != topLevel) {
				ref.setOrderSeq(topLevel.longValue());
			}
		}
		Integer status = ref.getStatus();
		if(ProfessionRef.STATUS_2_CLOSED.getValueInt().equals(status)) {
			ref.setClosedTime(new Date());
		}else if(ProfessionRef.STATUS_1_OPEN.getValueInt().equals(status)) {
			Date refreshTime = new Date();
			ref.setOpenTime(refreshTime);
			ref.setRefreshTime(refreshTime);
		}
		professionRefWrapper.mergeProfessionRef(ref);
		return true;
	}


	@Override
	public boolean deleteProfessionList(List<ProfessionRef> refList) {
		return false;
	}
	

	public ProfessionWrapper getProfessionWrapper() {
		return professionWrapper;
	}

	public void setProfessionWrapper(ProfessionWrapper professionWrapper) {
		this.professionWrapper = professionWrapper;
	}

	@Override
	public Profession findSingle(ProfessionRef ref) {
		Long baseId = ref.getBaseId();
		Profession entity = professionWrapper.findById(baseId);
		return entity;
	}

	@Override
	public Map<Long, Profession> findMap(List<Long> idList) {
		Map<Long, Profession> map = null;//professionWrapper.findMapByIdList(idList);
		return map;
	}
	
	public ProfessionExtend findExtend(ProfessionExtend extend) {
		ProfessionExtend entity  = professionExtendWrapper.findProfessionExtend(extend);
		return entity;
	}
	
	public ProfessionExtend config() {
		
		//子类override
		return this.configExtend;
	}
	
	@Override
	public ProfessionExtend config(ProfessionExtend extend) {
		if(null != extend && getConfigExtend() == null) {
			this.configExtend = new ProfessionExtend();
			this.configExtend.setId(extend.getId());
			this.configExtend.setAid(extend.getAid());
			this.configExtend.setDevId(extend.getDevId());
			//this.configExtend.setDepthMaxnum(extend.getDepthMaxnum());
			this.configExtend.setDomain(extend.getDomain());
			this.configExtend.setMainMaxnum(extend.getMainMaxnum());
			this.configExtend.setModule(extend.getModule());
			this.configExtend.setOrderSeq(extend.getOrderSeq());
			this.configExtend.setPosition(extend.getPosition());
			this.configExtend.setPositionId(extend.getPositionId());
			this.configExtend.setProject(extend.getProject());
			this.configExtend.setRefMaxnum(extend.getRefMaxnum());
			this.configExtend.setSubMaxnum(extend.getSubMaxnum());
			this.configExtend.setUserId(extend.getUserId());
			this.configExtend.setVariety(extend.getVariety());
		}
		return getConfigExtend();
	}

	public ProfessionExtend getConfigExtend() {
		return configExtend;
	}

	public void setConfigExtend(ProfessionExtend configExtend) {
		this.configExtend = configExtend;
	}

	public ProfessionExtendWrapper getProfessionExtendWrapper() {
		return professionExtendWrapper;
	}

	public void setProfessionExtendWrapper(ProfessionExtendWrapper professionExtendWrapper) {
		this.professionExtendWrapper = professionExtendWrapper;
	}

	@Override
	public PageResult<ProfessionRef> queryByViewType(ProfessionRef profession, Pageable pageable) {
		Integer viewType = profession.getViewType();
		ProfessionRefHandler refHandler = refHandlerHolder.get(viewType);
		PageResult<BaseEntityExtendLong> page = null;
		PageResult<ProfessionRef> refPage = null;
		if (null == refHandler) {
			refPage = new PageResult();
			refPage.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_REF_INVALID_HANDLER);
			refPage.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_REF_INVALID_HANDLER);
			return refPage;
		}
		page = refHandler.queryPage(profession, pageable);
		if (null == page || page.getTotalElements() == 0) {
			refPage = new PageResult();
			refPage.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_REF_FINDLIST_EMPTY);
			refPage.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_REF_FINDLIST_EMPTY);
			return refPage;
		}
		long totalElements = page.getTotalElements();
		List<BaseEntityExtendLong> contentList = page.getContent();
		Long baseId = profession.getBaseId();
		List<Long> mainIdList = new ArrayList<>();
		List<ProfessionRef> resultList = new ArrayList<>(contentList.size());
		Map<Long, ProfessionRef> refMap = new HashMap<>(contentList.size());
		for (BaseEntityExtendLong content : contentList) {
			Long mainId = content.getId();
			mainIdList.add(mainId);
			ProfessionRef ref = new ProfessionRef();
			ref.setProfession(profession.getProfession());

			if (content instanceof CoeCategArticle) {
				CoeCategArticle cca = (CoeCategArticle) content;
				CoeArticle a = cca.getArticle();
				ref.setMainEntity(a);
			}
			ref.setBaseId(baseId);
			ref.setExtendId(profession.getExtendId());
			ref.setPositionId(profession.getPositionId());
			refMap.put(mainId, ref);
			resultList.add(ref);
		}
		List<ProfessionRef> refExistList = professionRefWrapper.findByBaseIdAndMainIdList(baseId, mainIdList);
		if (null != refExistList && !refExistList.isEmpty()) {
			for (ProfessionRef exist : refExistList) {
				Long mainId = exist.getMainId();
				ProfessionRef needFill = refMap.get(mainId);
				if (null != needFill) {
					needFill.setAid(exist.getAid());
					needFill.setId(exist.getId());
					needFill.setStatus(exist.getStatus());
					needFill.setOpenTime(exist.getOpenTime());
					needFill.setClosedTime(exist.getClosedTime());
					// needFill.setExtendId(extendId);
				}
			}
		}

		refPage = new Jit4jPageResult<>(resultList, pageable, totalElements);

		return refPage;
	}

	public Map<Integer, ProfessionRefHandler> getRefHandlerHolder() {
		return refHandlerHolder;
	}

	public void setRefHandlerHolder(Map<Integer, ProfessionRefHandler> refHandlerHolder) {
		this.refHandlerHolder = refHandlerHolder;
	}

	@Override
	public boolean addProfessionRef(ProfessionRef ref, T t) {
		ref = professionRefWrapper.addProfessionRef(ref);
		if(ProfessionRef.SUCCESS_YES==ref.getSuccess()) {
			return true;
		}
		
		return false;
	}

	@Override
	public T findByMainId(ProfessionRef professionRef) {
		
		Integer viewType = professionRef.getViewType();
		ProfessionRefHandler refHandler = refHandlerHolder.get(viewType);
		if(null != refHandler) {
			T mainEntity = (T)refHandler.findByMainId(professionRef);
			//professionRef.setMainEntity(mainEntity);
			return mainEntity;
		}else {
			professionRef.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_REF_INVALID_HANDLER);
			professionRef.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_REF_INVALID_HANDLER);
		}
		return null;
	}

	@Override
	public ProfessionRef fillProfessionRef(T t, ProfessionRef professionRef) {
		Integer viewType = professionRef.getViewType();
		ProfessionRefHandler refHandler = refHandlerHolder.get(viewType);
		if(null != refHandler) {
			return refHandler.fillProfessionRef(t, professionRef);
		}else {
			professionRef.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PROFESSION_REF_INVALID_HANDLER);
			professionRef.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PROFESSION_REF_INVALID_HANDLER);
		}
		return null;
	}
	
	@Override
	public boolean deleteProfessionRef(ProfessionRef ref) {
		professionRefWrapper.deleteProfessionRef(ref);
		return true;
	}
	
	@Override
	public boolean openProfessionRef(ProfessionRef ref) {
		Integer status = ref.getStatus();
		if(ProfessionRef.STATUS_2_CLOSED.getValueInt().equals(status)) {
			ref.setClosedTime(new Date());
		}else if(ProfessionRef.STATUS_1_OPEN.getValueInt().equals(status)) {
			Date refreshTime = new Date();
			ref.setOpenTime(refreshTime);
			ref.setRefreshTime(refreshTime);
		}
		professionRefWrapper.openProfessionRef(ref);
		return true;
	}
	
	@Override
	public boolean mergeSimpleSingle(ProfessionRef ref) {
		professionRefWrapper.mergeSimpleSingle(ref);
		return true;
	}
	@Override
	public boolean deleteRefByBaseId(ProfessionRef ref) {
		professionRefWrapper.deleteRefByBaseId(ref);
		return true;
	}
	
	

	@Override
	public T convert(ProfessionRef professionRef) {
		return null;
	}

	public ProfessionRefWrapper getProfessionRefWrapper() {
		return professionRefWrapper;
	}

	public void setProfessionRefWrapper(ProfessionRefWrapper professionRefWrapper) {
		this.professionRefWrapper = professionRefWrapper;
	}

}
