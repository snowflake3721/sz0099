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
import dml.sz0099.course.app.client.wrapper.profession.ProfessionPositionWrapper;
import dml.sz0099.course.app.module.define.CourseRespCode;
import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.entity.position.PositionExtend;
import dml.sz0099.course.app.persist.entity.position.PositionRef;
import dml.sz0099.course.app.persist.entity.profession.Profession;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPosition;

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

public class PositionProfessionAdaptor extends PositionDefaultAdaptor<ProfessionPosition> implements PositionAdaptor<ProfessionPosition>{

	@Autowired
	private ProfessionPositionWrapper professionPositionWrapper;
	
	
	
	@Override
	public ProfessionPosition convert(PositionExtend positionExtend) {
		
		return null;
	}

	@Override
	public boolean persist(ProfessionPosition t) {
		return false;
	}

	@Override
	public boolean mergePositionRef(PositionRef ref) {
		
		super.mergePositionRef(ref);
		Long baseId = ref.getBaseId();
		ProfessionPosition professionPosition = new ProfessionPosition();
		Position position = ref.getPosition();
		professionPosition.setId(ref.getId());
		professionPosition.setBaseId(baseId);
		professionPosition.setMainId(ref.getMainId());
		professionPosition.setPositionId(ref.getPositionId());
		professionPosition.setPonMainId(position.getMainId());
		professionPosition.setPonSubId(position.getSubId());
		professionPosition.setCreatedBy(ref.getUserId());
		professionPosition.setLastModifiedBy(ref.getUserId());
		
		professionPosition.setLink(ref.getLink());
		professionPosition.setOriginalLink(ref.getOriginalLink());
		professionPosition.setName(ref.getName());
		professionPosition.setOrderSeq(ref.getOrderSeq());
		professionPosition.setPreIntro(ref.getPreIntro());
		professionPosition.setPreIntroType(ref.getPreIntroType());
		professionPosition.setStatus(ref.getStatus());
		professionPosition.setSubTitle(ref.getSubTitle());
		professionPosition.setTitle(ref.getTitle());
		professionPosition.setSaywordId(ref.getSaywordId());
		professionPosition.setSayword(ref.getSayword());
		professionPosition.setTopLevel(ref.getTopLevel());
		professionPosition.setRemark(ref.getRemark());
		professionPosition = professionPositionWrapper.mergePositionRef(professionPosition);
		
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
		
		PositionExtend extend = new PositionExtend();
		extend.setPosition("system");
		extend.setDevId("sz0099");
		extend.setDomain(Profession.class.getName());
		extend.setModule("personal");
		extend.setProject("ood");
		extend.setVariety("profession");
		extend=super.findExtend(extend);
		super.config(extend);
		
		return extend;
	}
	
	@Override
	public boolean addPositionRef(PositionRef positionRef, ProfessionPosition professionPosition) {
		boolean refAdded = super.addPositionRef(positionRef, professionPosition);
		if(refAdded) {
			professionPosition = professionPositionWrapper.addPositionRef(professionPosition);
			if(ProfessionPosition.SUCCESS_YES==professionPosition.getSuccess()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean deletePositionRef(PositionRef ref) {
		ProfessionPosition professionPosition = new ProfessionPosition();
		professionPosition.setBaseId(ref.getBaseId());
		professionPosition.setId(ref.getId());
		professionPositionWrapper.deletePositionRef(professionPosition);
		super.deletePositionRef(ref);
		return true;
	}
	
	@Override
	public boolean openPositionRef(PositionRef ref) {
		super.openPositionRef(ref);
		ProfessionPosition professionPosition = new ProfessionPosition();
		professionPosition.setBaseId(ref.getBaseId());
		professionPosition.setId(ref.getId());
		professionPosition.setStatus(ref.getStatus());
		professionPosition.setOpenTime(ref.getOpenTime());
		professionPosition.setClosedTime(ref.getClosedTime());
		professionPosition.setLastModifiedBy(ref.getLastModifiedBy());
		professionPosition.setUserId(ref.getUserId());
		professionPosition.setRemark(ref.getRemark());
		professionPositionWrapper.openPositionRef(professionPosition);
		return true;
	}
	
	@Override
	public boolean mergeSimpleSingle(PositionRef ref) {
		super.mergeSimpleSingle(ref);
		ProfessionPosition professionPosition = new ProfessionPosition();
		professionPosition.setId(ref.getId());
		professionPosition.setTopLevel(ref.getTopLevel());
		professionPositionWrapper.mergeSimpleSingle(professionPosition);
		return true;
	}
	
	@Override
	public boolean deleteRefByBaseId(PositionRef ref) {
		super.deleteRefByBaseId(ref);
		ProfessionPosition professionPosition = new ProfessionPosition();
		professionPosition.setBaseId(ref.getBaseId());
		professionPosition.setExtendId(ref.getExtendId());
		professionPositionWrapper.deleteRefByBaseId(professionPosition);
		return true;
	}
	
	public Page<ProfessionPosition> findPageForPosition(ProfessionPosition professionPosition, Pageable pageable) {
		Long ponMainId=professionPosition.getPonMainId();
		Long ponSubId=professionPosition.getPonSubId();
		Long positionId=professionPosition.getPositionId();
		Integer ponPanel=professionPosition.getPonPanel();
		List<Position> positionList = super.findPosition(ponMainId, ponSubId, positionId, ponPanel);
		Page<ProfessionPosition> page = null;
		if(null != positionList && !positionList.isEmpty()) {
			int size = positionList.size();
			if(size==1) {
				Position position=positionList.get(0);
				Long baseId = position.getId();
				page = professionPositionWrapper.findByBaseId(baseId, pageable);
				professionPosition.setBaseId(baseId);
				professionPosition.setPonLayout(position.getLayout());
			}else {
				List<Long> baseIdList = new ArrayList<>(size);
				int i=0;
				for(Position position: positionList) {
					baseIdList.add(position.getId());
					if(i==0) {
						Long baseId = position.getId();
						professionPosition.setBaseId(baseId);
						professionPosition.setPonLayout(position.getLayout());
					}
					i++;
				}
				page = professionPositionWrapper.findByBaseIdList(baseIdList, pageable);
			}
		}
		return page;
	}

	public Page<ProfessionPosition> findPageForPosition(Long ponMainId, Long ponSubId, Long positionId, Integer ponPanel, Pageable pageable) {
		List<Position> positionList = super.findPosition(ponMainId, ponSubId, positionId, ponPanel);
		Page<ProfessionPosition> page = null;
		if(null != positionList && !positionList.isEmpty()) {
			int size = positionList.size();
			if(size==1) {
				Position position=positionList.get(0);
				Long baseId = position.getId();
				page = professionPositionWrapper.findByBaseId(baseId, pageable);
			}else {
				List<Long> baseIdList = new ArrayList<>(size);
				for(Position position: positionList) {
					baseIdList.add(position.getId());
				}
				page = professionPositionWrapper.findByBaseIdList(baseIdList, pageable);
			}
		}
		return page;
	}

}
