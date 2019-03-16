/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.adaptor.profession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dml.sz0099.course.app.client.resolver.profession.ProfessionAdaptor;
import dml.sz0099.course.app.client.resolver.profession.ProfessionDefaultAdaptor;
import dml.sz0099.course.app.client.wrapper.profession.ProfessionWrapper;
import dml.sz0099.course.app.module.define.CourseRespCode;
import dml.sz0099.course.app.module.define.SZ0099AppModule;
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

public class ProfessionArticleAdaptor extends ProfessionDefaultAdaptor<CoeCategArticle> implements ProfessionAdaptor<CoeCategArticle>{

	@Autowired
	private ProfessionWrapper professionWrapper;
	
	
	
	@Override
	public CoeCategArticle convert(ProfessionExtend professionExtend) {
		
		return null;
	}

	@Override
	public boolean persist(CoeCategArticle t) {
		return false;
	}

	@Override
	public boolean mergeProfessionRef(ProfessionRef ref) {
		
		super.mergeProfessionRef(ref);
		ref.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_REF_MERGE_SUCCESS);
		ref.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_REF_MERGE_SUCCESS);
		ref.setSuccess(ProfessionRef.SUCCESS_YES);
		return true;
	}


	@Override
	public boolean deleteProfessionList(List<ProfessionRef> refList) {
		return false;
	}
	
	public ProfessionExtend config() {
		
		Long userId=1l;
		ProfessionExtend extend = getConfigDefault( userId);
		extend=super.findExtend(extend);
		super.config(extend);
		
		return extend;
	}
	
	public ProfessionExtend getConfigDefault(Long userId) {
		ProfessionExtend extend = null;
		if(this.configExtend!=null) {
			return this.configExtend;
		}
		if (userId != null) {
			extend = new ProfessionExtend();
			extend.setDevId(SZ0099AppModule.DEVELOPER_ID_SZ0099);
			extend.setProject("ood");
			extend.setModule("personal");
			extend.setVariety("profession");
			extend.setPosition("system");
			extend.setDomain(Profession.class.getName());
			extend.setMainMaxnum(ProfessionExtend.MAINMAXNUM_DEFAULT);
			extend.setSubMaxnum(ProfessionExtend.SUBMAXNUM_DEFAULT);
			extend.setRefMaxnum(ProfessionExtend.REFMAXNUM_DEFAULT);
			extend.setUserId(userId);
			extend.setCreatedBy(userId);
			extend.setLastModifiedBy(userId);
			
			ProfessionExtend entity = super.findExtend(extend);
			if(null == entity) {
				//执行创建
				entity = professionExtendWrapper.create(extend);
			}
			super.config(entity);
		}
		return this.configExtend;
	}
	
	@Override
	public boolean addProfessionRef(ProfessionRef positionRef, CoeCategArticle coeArticle) {
		boolean refAdded = super.addProfessionRef(positionRef, coeArticle);
		
		return refAdded;
	}
	
	@Override
	public boolean deleteProfessionRef(ProfessionRef ref) {
		super.deleteProfessionRef(ref);
		return true;
	}
	
	@Override
	public boolean openProfessionRef(ProfessionRef ref) {
		super.openProfessionRef(ref);
		return true;
	}
	
	@Override
	public boolean mergeSimpleSingle(ProfessionRef ref) {
		super.mergeSimpleSingle(ref);
		return true;
	}
	
	@Override
	public boolean deleteRefByBaseId(ProfessionRef ref) {
		super.deleteRefByBaseId(ref);
		return true;
	}


}
