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
import dml.sz0099.course.app.client.wrapper.article.CoeArticlePositionWrapper;
import dml.sz0099.course.app.module.define.CourseRespCode;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePosition;
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

public class PositionArticleAdaptor extends PositionDefaultAdaptor<CoeArticlePosition> implements PositionAdaptor<CoeArticlePosition>{

	@Autowired
	private CoeArticlePositionWrapper coeArticlePositionWrapper;
	
	
	
	@Override
	public CoeArticlePosition convert(PositionExtend positionExtend) {
		
		return null;
	}

	@Override
	public boolean persist(CoeArticlePosition t) {
		return false;
	}

	@Override
	public boolean mergePositionRef(PositionRef ref) {
		
		super.mergePositionRef(ref);
		Long baseId = ref.getBaseId();
		CoeArticlePosition coeArticlePosition = new CoeArticlePosition();
		Position position = ref.getPosition();
		coeArticlePosition.setId(ref.getId());
		coeArticlePosition.setBaseId(baseId);
		coeArticlePosition.setMainId(ref.getMainId());
		coeArticlePosition.setPositionId(ref.getPositionId());
		coeArticlePosition.setPonMainId(position.getMainId());
		coeArticlePosition.setPonSubId(position.getSubId());
		coeArticlePosition.setCreatedBy(ref.getUserId());
		coeArticlePosition.setLastModifiedBy(ref.getUserId());
		
		coeArticlePosition.setLink(ref.getLink());
		coeArticlePosition.setOriginalLink(ref.getOriginalLink());
		coeArticlePosition.setName(ref.getName());
		coeArticlePosition.setOrderSeq(ref.getOrderSeq());
		coeArticlePosition.setPreIntro(ref.getPreIntro());
		coeArticlePosition.setPreIntroType(ref.getPreIntroType());
		coeArticlePosition.setStatus(ref.getStatus());
		coeArticlePosition.setSubTitle(ref.getSubTitle());
		coeArticlePosition.setTitle(ref.getTitle());
		
		coeArticlePosition.setSaywordId(ref.getSaywordId());
		coeArticlePosition.setSayword(ref.getSayword());
		
		coeArticlePosition.setTopLevel(ref.getTopLevel());
		coeArticlePosition.setRemark(ref.getRemark());
		coeArticlePosition = coeArticlePositionWrapper.mergePositionRef(coeArticlePosition);
		
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
			extend.setDomain(CoeArticle.class.getName());
			extend.setModule("article");
			extend.setProject("ood");
			extend.setVariety("article");
			extend=super.findExtend(extend);
			super.config(extend);
		}
		return configExtend;
	}
	
	@Override
	public boolean addPositionRef(PositionRef positionRef, CoeArticlePosition coeArticlePosition) {
		boolean refAdded = super.addPositionRef(positionRef, coeArticlePosition);
		if(refAdded) {
			coeArticlePosition = coeArticlePositionWrapper.addPositionRef(coeArticlePosition);
			if(CoeArticlePosition.SUCCESS_YES==coeArticlePosition.getSuccess()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean deletePositionRef(PositionRef ref) {
		CoeArticlePosition coeArticlePosition = new CoeArticlePosition();
		coeArticlePosition.setBaseId(ref.getBaseId());
		coeArticlePosition.setId(ref.getId());
		coeArticlePositionWrapper.deletePositionRef(coeArticlePosition);
		super.deletePositionRef(ref);
		return true;
	}
	
	@Override
	public boolean openPositionRef(PositionRef ref) {
		super.openPositionRef(ref);
		CoeArticlePosition coeArticlePosition = new CoeArticlePosition();
		coeArticlePosition.setBaseId(ref.getBaseId());
		coeArticlePosition.setId(ref.getId());
		coeArticlePosition.setStatus(ref.getStatus());
		coeArticlePosition.setOpenTime(ref.getOpenTime());
		coeArticlePosition.setClosedTime(ref.getClosedTime());
		coeArticlePosition.setLastModifiedBy(ref.getLastModifiedBy());
		coeArticlePosition.setUserId(ref.getUserId());
		coeArticlePosition.setRemark(ref.getRemark());
		coeArticlePositionWrapper.openPositionRef(coeArticlePosition);
		return true;
	}
	
	@Override
	public boolean mergeSimpleSingle(PositionRef ref) {
		super.mergeSimpleSingle(ref);
		CoeArticlePosition coeArticlePosition = new CoeArticlePosition();
		coeArticlePosition.setId(ref.getId());
		coeArticlePosition.setTopLevel(ref.getTopLevel());
		coeArticlePositionWrapper.mergeSimpleSingle(coeArticlePosition);
		return true;
	}
	
	@Override
	public boolean deleteRefByBaseId(PositionRef ref) {
		super.deleteRefByBaseId(ref);
		CoeArticlePosition coeArticlePosition = new CoeArticlePosition();
		coeArticlePosition.setBaseId(ref.getBaseId());
		coeArticlePosition.setExtendId(ref.getExtendId());
		coeArticlePositionWrapper.deleteRefByBaseId(coeArticlePosition);
		return true;
	}

	public Page<CoeArticlePosition> findPageForPosition(CoeArticlePosition coeArticlePosition, Pageable pageable) {
		Long ponMainId=coeArticlePosition.getPonMainId();
		Long ponSubId=coeArticlePosition.getPonSubId();
		Long positionId=coeArticlePosition.getPositionId();
		Integer ponPanel=coeArticlePosition.getPonPanel();
		List<Position> positionList = super.findPosition(ponMainId, ponSubId, positionId, ponPanel);
		Page<CoeArticlePosition> page = null;
		if(null != positionList && !positionList.isEmpty()) {
			int size = positionList.size();
			if(size==1) {
				Position position=positionList.get(0);
				Long baseId = position.getId();
				page = coeArticlePositionWrapper.findByBaseId(baseId, pageable);
				coeArticlePosition.setBaseId(baseId);
				coeArticlePosition.setPonLayout(position.getLayout());
			}else {
				List<Long> baseIdList = new ArrayList<>(size);
				int i=0;
				for(Position position: positionList) {
					baseIdList.add(position.getId());
					if(i==0) {
						Long baseId = position.getId();
						coeArticlePosition.setBaseId(baseId);
						coeArticlePosition.setPonLayout(position.getLayout());
					}
					i++;
				}
				page = coeArticlePositionWrapper.findByBaseIdList(baseIdList, pageable);
			}
		}
		return page;
	}
	public Page<CoeArticlePosition> findPageForPosition(Long ponMainId, Long ponSubId, Long positionId, Integer ponPanel, Pageable pageable) {
		List<Position> positionList = super.findPosition(ponMainId, ponSubId, positionId, ponPanel);
		Page<CoeArticlePosition> page = null;
		if(null != positionList && !positionList.isEmpty()) {
			int size = positionList.size();
			if(size==1) {
				Position position=positionList.get(0);
				Long baseId = position.getId();
				page = coeArticlePositionWrapper.findByBaseId(baseId, pageable);
			}else {
				List<Long> baseIdList = new ArrayList<>(size);
				for(Position position: positionList) {
					baseIdList.add(position.getId());
				}
				page = coeArticlePositionWrapper.findByBaseIdList(baseIdList, pageable);
			}
		}
		return page;
	}
	
}
