/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.adaptor.position;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.client.resolver.position.PositionAdaptor;
import dml.sz0099.course.app.client.resolver.position.PositionDefaultAdaptor;
import dml.sz0099.course.app.client.wrapper.activity.CoeActivityPositionWrapper;
import dml.sz0099.course.app.module.define.CourseRespCode;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPosition;
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

public class PositionActivityAdaptor extends PositionDefaultAdaptor<CoeActivityPosition> implements PositionAdaptor<CoeActivityPosition>{

	@Autowired
	private CoeActivityPositionWrapper coeActivityPositionWrapper;
	
	
	
	@Override
	public CoeActivityPosition convert(PositionExtend positionExtend) {
		
		return null;
	}

	@Override
	public boolean persist(CoeActivityPosition t) {
		return false;
	}

	@Override
	public boolean mergePositionRef(PositionRef ref) {
		
		super.mergePositionRef(ref);
		Long baseId = ref.getBaseId();
		CoeActivityPosition coeActivityPosition = new CoeActivityPosition();
		Position position = ref.getPosition();
		coeActivityPosition.setId(ref.getId());
		coeActivityPosition.setBaseId(baseId);
		coeActivityPosition.setMainId(ref.getMainId());
		coeActivityPosition.setPositionId(ref.getPositionId());
		coeActivityPosition.setPonMainId(position.getMainId());
		coeActivityPosition.setPonSubId(position.getSubId());
		coeActivityPosition.setCreatedBy(ref.getUserId());
		coeActivityPosition.setLastModifiedBy(ref.getUserId());
		
		coeActivityPosition.setLink(ref.getLink());
		coeActivityPosition.setOriginalLink(ref.getOriginalLink());
		coeActivityPosition.setName(ref.getName());
		coeActivityPosition.setOrderSeq(ref.getOrderSeq());
		coeActivityPosition.setPreIntro(ref.getPreIntro());
		coeActivityPosition.setPreIntroType(ref.getPreIntroType());
		coeActivityPosition.setStatus(ref.getStatus());
		coeActivityPosition.setSubTitle(ref.getSubTitle());
		coeActivityPosition.setTitle(ref.getTitle());
		
		coeActivityPosition.setSaywordId(ref.getSaywordId());
		coeActivityPosition.setSayword(ref.getSayword());
		
		coeActivityPosition.setTopLevel(ref.getTopLevel());
		coeActivityPosition.setRemark(ref.getRemark());
		coeActivityPosition = coeActivityPositionWrapper.mergePositionRef(coeActivityPosition);
		
		ref.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_REF_MERGE_SUCCESS);
		ref.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_REF_MERGE_SUCCESS);
		ref.setSuccess(PositionRef.SUCCESS_YES);
		return true;
	}


	@Override
	public boolean deletePositionList(List<PositionRef> refList) {
		return false;
	}
	
	public PositionExtend config() {
		
		if(this.configExtend==null) {
			PositionExtend extend = new PositionExtend();
			extend.setPosition("system");
			extend.setDevId("sz0099");
			extend.setDomain(CoeActivity.class.getName());
			extend.setModule("activity");
			extend.setProject("ood");
			extend.setVariety("activity");
			extend=super.findExtend(extend);
			super.config(extend);
		}
		return configExtend;
	}
	
	@Override
	public boolean addPositionRef(PositionRef positionRef, CoeActivityPosition coeActivityPosition) {
		boolean refAdded = super.addPositionRef(positionRef, coeActivityPosition);
		if(refAdded) {
			coeActivityPosition = coeActivityPositionWrapper.addPositionRef(coeActivityPosition);
			if(CoeActivityPosition.SUCCESS_YES==coeActivityPosition.getSuccess()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean deletePositionRef(PositionRef ref) {
		CoeActivityPosition coeActivityPosition = new CoeActivityPosition();
		coeActivityPosition.setBaseId(ref.getBaseId());
		coeActivityPosition.setId(ref.getId());
		coeActivityPositionWrapper.deletePositionRef(coeActivityPosition);
		super.deletePositionRef(ref);
		return true;
	}
	
	@Override
	public boolean openPositionRef(PositionRef ref) {
		super.openPositionRef(ref);
		CoeActivityPosition coeActivityPosition = new CoeActivityPosition();
		coeActivityPosition.setBaseId(ref.getBaseId());
		coeActivityPosition.setId(ref.getId());
		coeActivityPosition.setStatus(ref.getStatus());
		coeActivityPosition.setOpenTime(ref.getOpenTime());
		coeActivityPosition.setClosedTime(ref.getClosedTime());
		coeActivityPosition.setLastModifiedBy(ref.getLastModifiedBy());
		coeActivityPosition.setUserId(ref.getUserId());
		coeActivityPosition.setRemark(ref.getRemark());
		coeActivityPositionWrapper.openPositionRef(coeActivityPosition);
		return true;
	}
	
	@Override
	public boolean mergeSimpleSingle(PositionRef ref) {
		super.mergeSimpleSingle(ref);
		CoeActivityPosition coeActivityPosition = new CoeActivityPosition();
		coeActivityPosition.setId(ref.getId());
		coeActivityPosition.setTopLevel(ref.getTopLevel());
		coeActivityPositionWrapper.mergeSimpleSingle(coeActivityPosition);
		return true;
	}
	
	@Override
	public boolean deleteRefByBaseId(PositionRef ref) {
		super.deleteRefByBaseId(ref);
		CoeActivityPosition coeActivityPosition = new CoeActivityPosition();
		coeActivityPosition.setBaseId(ref.getBaseId());
		coeActivityPosition.setExtendId(ref.getExtendId());
		coeActivityPositionWrapper.deleteRefByBaseId(coeActivityPosition);
		return true;
	}

	public Page<CoeActivityPosition> findPageForPosition(CoeActivityPosition coeActivityPosition, Pageable pageable) {
		Long ponMainId=coeActivityPosition.getPonMainId();
		Long ponSubId=coeActivityPosition.getPonSubId();
		Long positionId=coeActivityPosition.getPositionId();
		Integer ponPanel=coeActivityPosition.getPonPanel();
		List<Position> positionList = super.findPosition(ponMainId, ponSubId, positionId, ponPanel);
		Page<CoeActivityPosition> page = null;
		if(null != positionList && !positionList.isEmpty()) {
			int size = positionList.size();
			if(size==1) {
				Position position=positionList.get(0);
				Long baseId = position.getId();
				page = coeActivityPositionWrapper.findByBaseId(baseId, pageable);
				coeActivityPosition.setBaseId(baseId);
				coeActivityPosition.setPonLayout(position.getLayout());
			}else {
				List<Long> baseIdList = new ArrayList<>(size);
				int i=0;
				for(Position position: positionList) {
					baseIdList.add(position.getId());
					if(i==0) {
						Long baseId = position.getId();
						coeActivityPosition.setBaseId(baseId);
						coeActivityPosition.setPonLayout(position.getLayout());
					}
					i++;
				}
				page = coeActivityPositionWrapper.findByBaseIdList(baseIdList, pageable);
			}
		}
		return page;
	}
	public Page<CoeActivityPosition> findPageForPosition(Long ponMainId, Long ponSubId, Long positionId, Integer ponPanel, Pageable pageable) {
		List<Position> positionList = super.findPosition(ponMainId, ponSubId, positionId, ponPanel);
		Page<CoeActivityPosition> page = null;
		if(null != positionList && !positionList.isEmpty()) {
			int size = positionList.size();
			if(size==1) {
				Position position=positionList.get(0);
				Long baseId = position.getId();
				page = coeActivityPositionWrapper.findByBaseId(baseId, pageable);
			}else {
				List<Long> baseIdList = new ArrayList<>(size);
				for(Position position: positionList) {
					baseIdList.add(position.getId());
				}
				page = coeActivityPositionWrapper.findByBaseIdList(baseIdList, pageable);
			}
		}
		return page;
	}
	
}
