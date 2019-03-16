/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.profession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.jit4j.core.persist.page.PageResult;
import org.jit8j.core.util.GsonBuilderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.client.wrapper.profession.ProfessionExtendLogWrapper;
import dml.sz0099.course.app.client.wrapper.profession.ProfessionExtendWrapper;
import dml.sz0099.course.app.client.wrapper.profession.ProfessionRefWrapper;
import dml.sz0099.course.app.client.wrapper.profession.ProfessionWrapper;
import dml.sz0099.course.app.module.define.CourseRespCode;
import dml.sz0099.course.app.persist.entity.profession.Profession;
import dml.sz0099.course.app.persist.entity.profession.ProfessionExtend;
import dml.sz0099.course.app.persist.entity.profession.ProfessionRef;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-09-22 20:18:10
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-09-22	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class ProfessionProccessor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionProccessor.class);


	ThreadPoolExecutor executor = new ThreadPoolExecutor(12, 20, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());


	@Autowired
	private ProfessionRequestResolver professionRequestResolver;
	
	@Autowired
	private ProfessionExtendWrapper professionExtendWrapper;
	
	@Autowired
	private ProfessionExtendLogWrapper professionExtendLogWrapper;
	
	@Autowired
	private ProfessionRefWrapper professionRefWrapper;
	
	@Autowired
	private ProfessionWrapper professionWrapper;
	
	private Map<Long, ProfessionAdaptor> adaptorContainer;
	
	
	public ProfessionResponse queryRefPage(ProfessionRef profession, Pageable pageable) {
		Long positionId = profession.getPositionId();
		ProfessionAdaptor professionAdaptor = adaptorContainer.get(positionId);
		PageResult result = null;
		ProfessionResponse response = new ProfessionResponse();
		if(null != professionAdaptor) {
			// 5.调用
			result = professionAdaptor.queryByViewType(profession, pageable);
			
			response.setRespCode(result.getRespCode());
			response.setRespMsg(result.getRespMsg());
			response.setContent(result);
		}else {
			//不正确的位置适配器
			response.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_POSITION_INVALID_ADAPTOR);
			response.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_POSITION_INVALID_ADAPTOR);
		}
		return response;
	}
	
	public ProfessionResponse proccessSingle(ProfessionRequest request) {
		// 1.解析
		ProfessionExtend professionExtend = resolveProfessionRequest(request);
		ProfessionResponse response = new ProfessionResponse();
		if(null != professionExtend && ProfessionExtend.SUCCESS_YES==professionExtend.getSuccess()) {
			//2 处理绑定
			adaptorProcess(professionExtend, response);
		}
		response.setSuccess(professionExtend.getSuccess());
		response.setRespCode(professionExtend.getRespCode());
		response.setRespMsg(professionExtend.getRespMsg());
		return response;
	}

	public ProfessionResponse proccess(ProfessionRequest request) {
		
		// 1.解析
		ProfessionExtend professionExtend = resolveProfessionRequest(request);
		ProfessionResponse response = new ProfessionResponse();
		if(null != professionExtend && ProfessionExtend.SUCCESS_YES==professionExtend.getSuccess()) {
	
			adaptorProcess(professionExtend, response);
			
		}
		response.setSuccess(professionExtend.getSuccess());
		response.setRespCode(professionExtend.getRespCode());
		response.setRespMsg(professionExtend.getRespMsg());
		return response;
	}
	

	/**
	 * @param professionExtend
	 * @param response
	 */
	private void adaptorProcess(ProfessionExtend professionExtend, ProfessionResponse response) {
		// 4.转化
		//每个positionId就对应着一个ProfessionAdaptor,配置在xml中
		Long positionId = professionExtend.getPositionId();
		ProfessionAdaptor professionAdaptor = adaptorContainer.get(positionId);
		boolean result = false;
		if(null != professionAdaptor) {
			Object obj = professionAdaptor.convert(professionExtend);
			// 5.调用
			result = professionAdaptor.persist(obj);
			if(!result) {
				final ProfessionExtend log = professionExtend;
				executor.execute(new Runnable() {
					@Override
					public void run() {
						//记录未执行成功的日志
						professionExtendLogWrapper.persistForFail(log);
					}
				});
			}
		}
		//记录调用日志，更新引用计数 TODO
		

		// 6.返回
		List<Profession> professions = professionExtend.getProfessions();
		List<ProfessionRef> refList = new ArrayList<>(professions.size());
		for(Profession profession : professions) {
			refList.addAll(profession.getRefList());
		}
		response.setContent(refList);
	}
	
	public ProfessionResponse<Profession> proccessQuery(ProfessionRequest request) {
		
		// 1.解析
		ProfessionExtend professionExtend = resolveProfessionRequest(request);
		ProfessionResponse<Profession> response = new ProfessionResponse<>();
		if(null != professionExtend && ProfessionExtend.SUCCESS_YES==professionExtend.getSuccess()) {
	
			Long positionId = professionExtend.getPositionId();
			ProfessionAdaptor professionAdaptor = adaptorContainer.get(positionId);
			boolean result = false;
			if(null != professionAdaptor) {
				Profession entity = null;//professionAdaptor.queryTree(professionExtend);
				response.setContent(entity);
			}
			
			
		}
		response.setSuccess(professionExtend.getSuccess());
		response.setRespCode(professionExtend.getRespCode());
		response.setRespMsg(professionExtend.getRespMsg());
		return response;
	}

	public ProfessionExtend resolveProfessionRequest(ProfessionRequest request) {

		ProfessionExtend professionExtend = professionRequestResolver.resolveProfessionRequest(request);
		return professionExtend;
	}
	public ProfessionExtend resolveProfessionRequestForDelete(ProfessionRequest request) {
		ProfessionExtend professionExtend = professionRequestResolver.resolveProfessionRequestForDelete(request);
		return professionExtend;
	}
	
	public ProfessionRef addProfessionRef(ProfessionRef professionRef) {
		Long baseId = professionRef.getBaseId();
		Profession profession = professionWrapper.findById(baseId);
		if (null != profession) {
			professionRef.setExtendId(profession.getExtendId());
			Long positionId = profession.getPositionId();
			professionRef.setPositionId(positionId);
			professionRef.setProfession(profession);
			
			ProfessionAdaptor professionAdaptor = adaptorContainer.get(positionId);
			Object t = professionAdaptor.findByMainId(professionRef);
			professionRef = professionAdaptor.fillProfessionRef(t,professionRef);
			boolean result = professionAdaptor.addProfessionRef(professionRef,t);
			if (!result) {
				// TODO log it
				LOGGER.error("!!!professionAdaptor.addProfessionRef , {} ", professionAdaptor.getClass().getName());
				LOGGER.error("!!!professionAdaptor.addProfessionRef ,data: {} ", GsonBuilderUtils.toJson(professionRef));
			}
			return professionRef;
		}
		return null;

	}

	
	public ProfessionRef mergeProfessionRef(ProfessionRef professionRef) {
		Long mainId = professionRef.getMainId();
		Long baseId = professionRef.getBaseId();
		Profession profession = professionWrapper.findById(baseId);
		if (null != profession) {
			professionRef.setExtendId(profession.getExtendId());
			Long positionId = profession.getPositionId();
			professionRef.setPositionId(positionId);

			professionRef.setProfession(profession);

			ProfessionAdaptor professionAdaptor = adaptorContainer.get(positionId);
			boolean result = professionAdaptor.mergeProfessionRef(professionRef);
			if (!result) {
				// TODO log it
				LOGGER.error("!!!professionAdaptor.mergeProfession , {} ", professionAdaptor.getClass().getName());
				LOGGER.error("!!!professionAdaptor.mergeProfession ,data: {} ", GsonBuilderUtils.toJson(professionRef));
			}
			return professionRef;
		}
		return null;

	}
	
	/**
	 * 移除关联
	 * @param professionRef
	 * @return
	 */
	public ProfessionRef deleteProfessionRef(ProfessionRef professionRef) {
		Long id = professionRef.getId();
		ProfessionRef entity = professionRefWrapper.findById(id);
		if(null != entity) {
			Long positionId = entity.getPositionId();
			if(null != positionId) {
				ProfessionAdaptor professionAdaptor = adaptorContainer.get(positionId);
				professionRef.setMainId(entity.getMainId());
				boolean result = professionAdaptor.deleteProfessionRef(professionRef);
				if(!result) {
					//TODO log it
					LOGGER.error("!!!professionAdaptor.mergeProfession , {} ",professionAdaptor.getClass().getName());
					LOGGER.error("!!!professionAdaptor.mergeProfession ,data: {} ",GsonBuilderUtils.toJson(entity));
				}
				entity.setSuccess(ProfessionRef.SUCCESS_YES);
			}
		}
		
		return entity;
	}
	
	/**
	 * 开闭关联
	 * @param professionRef
	 * @return
	 */
	public ProfessionRef openProfessionRef(ProfessionRef professionRef) {
		Long id = professionRef.getId();
		ProfessionRef entity = professionRefWrapper.findById(id);
		if(null != entity) {
			Long positionId = entity.getPositionId();
			if(null != positionId) {
				ProfessionAdaptor professionAdaptor = adaptorContainer.get(positionId);
				professionRef.setMainId(entity.getMainId());
				boolean result = professionAdaptor.openProfessionRef(professionRef);
				if(!result) {
					//TODO log it
					LOGGER.error("!!!professionAdaptor.openProfessionRef , {} ",professionAdaptor.getClass().getName());
					LOGGER.error("!!!professionAdaptor.openProfessionRef ,data: {} ",GsonBuilderUtils.toJson(entity));
				}
				entity.setSuccess(ProfessionRef.SUCCESS_YES);
			}
		}
		
		return entity;
	}
	
	public ProfessionRef mergeSimpleSingle(ProfessionRef professionRef) {
		Long id = professionRef.getId();
		ProfessionRef entity = professionRefWrapper.findById(id);
		if(null != entity) {
			Long positionId = entity.getPositionId();
			if(null != positionId) {
				ProfessionAdaptor professionAdaptor = adaptorContainer.get(positionId);
				professionRef.setMainId(entity.getMainId());
				boolean result = professionAdaptor.mergeSimpleSingle(professionRef);
				if(!result) {
					//TODO log it
					LOGGER.error("!!!professionAdaptor.openProfessionRef , {} ",professionAdaptor.getClass().getName());
					LOGGER.error("!!!professionAdaptor.openProfessionRef ,data: {} ",GsonBuilderUtils.toJson(entity));
				}
				entity.setSuccess(ProfessionRef.SUCCESS_YES);
			}
		}
		
		return entity;
	}
	public ProfessionRef deleteRefByBaseId(ProfessionRef professionRef) {
		Long id = professionRef.getBaseId();
		Profession entity = professionWrapper.findById(id);
		if(null != entity) {
			Long positionId = entity.getPositionId();
			professionRef.setProfession(entity);
			if(null != positionId) {
				ProfessionAdaptor professionAdaptor = adaptorContainer.get(positionId);
				//professionRef.setMainId(entity.getMainId());
				boolean result = professionAdaptor.deleteRefByBaseId(professionRef);
				if(!result) {
					//TODO log it
					LOGGER.error("!!!professionAdaptor.openProfessionRef , {} ",professionAdaptor.getClass().getName());
					LOGGER.error("!!!professionAdaptor.openProfessionRef ,data: {} ",GsonBuilderUtils.toJson(professionRef));
				}
				professionRef.setSuccess(ProfessionRef.SUCCESS_YES);
			}
		}
		
		return professionRef;
	}
	
	
	
	

	public Map<Long, ProfessionAdaptor> getAdaptorContainer() {
		return adaptorContainer;
	}

	public void setAdaptorContainer(Map<Long, ProfessionAdaptor> adaptorContainer) {
		this.adaptorContainer = adaptorContainer;
	}

}
