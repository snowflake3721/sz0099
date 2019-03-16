/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.position;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jit4j.core.persist.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.client.wrapper.position.PositionExtendWrapper;
import dml.sz0099.course.app.client.wrapper.position.PositionRefWrapper;
import dml.sz0099.course.app.client.wrapper.position.PositionWrapper;
import dml.sz0099.course.app.module.define.CourseRespCode;
import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.entity.position.PositionExtend;
import dml.sz0099.course.app.persist.entity.position.PositionRef;

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

public class PositionDefaultAdaptor<T> implements PositionAdaptor<T>{
	
	@Autowired
	protected PositionWrapper positionWrapper;
	
	@Autowired
	protected PositionRefWrapper positionRefWrapper;
	
	@Autowired
	protected PositionExtendWrapper positionExtendWrapper;
	
	protected PositionExtend configExtend;
	
	protected Map<Integer, RefHandler> refHandlerHolder;

	@Override
	public T convert(PositionExtend positionExtend) {
		return null;
	}

	@Override
	public boolean persist(T t) {
		return false;
	}

	@Override
	public boolean mergePositionRef(PositionRef ref) {
		Long orderSeq = ref.getOrderSeq();
		if(null == orderSeq) {
			Integer topLevel = ref.getTopLevel();
			if(null != topLevel) {
				ref.setOrderSeq(topLevel.longValue());
			}
		}
		Integer status = ref.getStatus();
		if(PositionRef.STATUS_2_CLOSED.getValueInt().equals(status)) {
			ref.setClosedTime(new Date());
		}else if(PositionRef.STATUS_1_OPEN.getValueInt().equals(status)) {
			Date refreshTime = new Date();
			ref.setOpenTime(refreshTime);
			ref.setRefreshTime(refreshTime);
		}
		positionRefWrapper.mergePositionRef(ref);
		return true;
	}


	@Override
	public boolean deletePositionList(List<PositionRef> refList) {
		return false;
	}
	

	public PositionWrapper getPositionWrapper() {
		return positionWrapper;
	}

	public void setPositionWrapper(PositionWrapper positionWrapper) {
		this.positionWrapper = positionWrapper;
	}

	@Override
	public Position findSingle(PositionRef ref) {
		Long baseId = ref.getBaseId();
		Position entity = positionWrapper.findById(baseId);
		return entity;
	}

	@Override
	public Map<Long, Position> findMap(List<Long> idList) {
		Map<Long, Position> map = positionWrapper.findMapByIdList(idList);
		return map;
	}
	
	public PositionExtend findExtend(PositionExtend extend) {
		PositionExtend entity  = positionExtendWrapper.findPositionExtend(extend);
		return entity;
	}
	
	public PositionExtend config() {
		
		//子类override
		return this.configExtend;
	}

	@Override
	public PositionExtend config(PositionExtend extend) {
		if(null != extend && getConfigExtend() == null) {
			this.configExtend = new PositionExtend();
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

	public PositionExtend getConfigExtend() {
		
		return configExtend;
	}

	public void setConfigExtend(PositionExtend configExtend) {
		this.configExtend = configExtend;
	}

	public PositionExtendWrapper getPositionExtendWrapper() {
		return positionExtendWrapper;
	}

	public void setPositionExtendWrapper(PositionExtendWrapper positionExtendWrapper) {
		this.positionExtendWrapper = positionExtendWrapper;
	}

	@Override
	public PageResult<T> queryByViewType(PositionRef position, Pageable pageable) {
		Integer viewType = position.getViewType();
		RefHandler refHandler = refHandlerHolder.get(viewType);
		PageResult<T> page = null;
		if(null != refHandler) {
			page = refHandler.queryPage(position,pageable);
			
		}else {
			page = new PageResult() ;
			page.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_REF_INVALID_HANDLER);
			page.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_REF_INVALID_HANDLER);
		}
		return page;
	}

	public Map<Integer, RefHandler> getRefHandlerHolder() {
		return refHandlerHolder;
	}

	public void setRefHandlerHolder(Map<Integer, RefHandler> refHandlerHolder) {
		this.refHandlerHolder = refHandlerHolder;
	}

	@Override
	public boolean addPositionRef(PositionRef ref, T t) {
		ref = positionRefWrapper.addPositionRef(ref);
		if(PositionRef.SUCCESS_YES==ref.getSuccess()) {
			return true;
		}
		
		return false;
	}

	@Override
	public T findByMainId(PositionRef positionRef) {
		
		Integer viewType = positionRef.getViewType();
		RefHandler refHandler = refHandlerHolder.get(viewType);
		if(null != refHandler) {
			return (T)refHandler.findByMainId(positionRef);
		}else {
			positionRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_REF_INVALID_HANDLER);
			positionRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_REF_INVALID_HANDLER);
		}
		return null;
	}

	@Override
	public PositionRef fillPositionRef(T t, PositionRef positionRef) {
		Integer viewType = positionRef.getViewType();
		RefHandler refHandler = refHandlerHolder.get(viewType);
		if(null != refHandler) {
			return refHandler.fillPositionRef(t, positionRef);
		}else {
			positionRef.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_REF_INVALID_HANDLER);
			positionRef.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_REF_INVALID_HANDLER);
		}
		return null;
	}
	
	@Override
	public boolean deletePositionRef(PositionRef ref) {
		positionRefWrapper.deletePositionRef(ref);
		return true;
	}
	
	@Override
	public boolean openPositionRef(PositionRef ref) {
		Integer status = ref.getStatus();
		if(PositionRef.STATUS_2_CLOSED.getValueInt().equals(status)) {
			ref.setClosedTime(new Date());
		}else if(PositionRef.STATUS_1_OPEN.getValueInt().equals(status)) {
			Date refreshTime = new Date();
			ref.setOpenTime(refreshTime);
			ref.setRefreshTime(refreshTime);
		}
		positionRefWrapper.openPositionRef(ref);
		return true;
	}
	
	@Override
	public boolean mergeSimpleSingle(PositionRef ref) {
		positionRefWrapper.mergeSimpleSingle(ref);
		return true;
	}
	@Override
	public boolean deleteRefByBaseId(PositionRef ref) {
		positionRefWrapper.deleteRefByBaseId(ref);
		return true;
	}
	
	

	@Override
	public T convert(PositionRef positionRef) {
		return null;
	}

	public PositionRefWrapper getPositionRefWrapper() {
		return positionRefWrapper;
	}

	public void setPositionRefWrapper(PositionRefWrapper positionRefWrapper) {
		this.positionRefWrapper = positionRefWrapper;
	}

	@Override
	public Page<T> findPageForPosition(Long ponMainId, Long ponSubId, Long positionId, Integer ponPanel, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Position> findPosition(Long mainId, Long subId, Long positionId, Integer panel) {
		List<Position> content = positionWrapper.findPosition(mainId, subId, positionId, panel);
		return content;
	}

}
