/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.position;

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

import dml.sz0099.course.app.client.wrapper.position.PositionExtendLogWrapper;
import dml.sz0099.course.app.client.wrapper.position.PositionExtendWrapper;
import dml.sz0099.course.app.client.wrapper.position.PositionRefWrapper;
import dml.sz0099.course.app.client.wrapper.position.PositionWrapper;
import dml.sz0099.course.app.client.wrapper.user.SaywordWrapper;
import dml.sz0099.course.app.module.define.CourseRespCode;
import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.entity.position.PositionExtend;
import dml.sz0099.course.app.persist.entity.position.PositionRef;
import dml.sz0099.course.app.persist.entity.user.Sayword;

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

public class PositionProccessor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PositionProccessor.class);


	ThreadPoolExecutor executor = new ThreadPoolExecutor(12, 20, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());


	@Autowired
	private PositionRequestResolver positionRequestResolver;
	
	@Autowired
	private PositionExtendWrapper positionExtendWrapper;
	
	@Autowired
	private PositionExtendLogWrapper positionExtendLogWrapper;
	
	@Autowired
	private PositionRefWrapper positionRefWrapper;
	
	@Autowired
	private PositionWrapper positionWrapper;
	
	@Autowired
	private SaywordWrapper saywordWrapper;
	
	private Map<Long, PositionAdaptor> adaptorContainer;
	
	
	public PositionResponse queryRefPage(PositionRef position, Pageable pageable) {
		Long positionId = position.getPositionId();
		PositionAdaptor positionAdaptor = adaptorContainer.get(positionId);
		PageResult result = null;
		PositionResponse response = new PositionResponse();
		if(null != positionAdaptor) {
			// 5.调用
			result = positionAdaptor.queryByViewType(position, pageable);
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
	
	public PositionResponse proccessSingle(PositionRequest request) {
		// 1.解析
		PositionExtend positionExtend = resolvePositionRequest(request);
		PositionResponse response = new PositionResponse();
		if(null != positionExtend && PositionExtend.SUCCESS_YES==positionExtend.getSuccess()) {
			//2 处理绑定
			adaptorProcess(positionExtend, response);
		}
		response.setSuccess(positionExtend.getSuccess());
		response.setRespCode(positionExtend.getRespCode());
		response.setRespMsg(positionExtend.getRespMsg());
		return response;
	}

	public PositionResponse proccess(PositionRequest request) {
		
		// 1.解析
		PositionExtend positionExtend = resolvePositionRequest(request);
		PositionResponse response = new PositionResponse();
		if(null != positionExtend && PositionExtend.SUCCESS_YES==positionExtend.getSuccess()) {
	
			adaptorProcess(positionExtend, response);
			
		}
		response.setSuccess(positionExtend.getSuccess());
		response.setRespCode(positionExtend.getRespCode());
		response.setRespMsg(positionExtend.getRespMsg());
		return response;
	}
	

	/**
	 * @param positionExtend
	 * @param response
	 */
	private void adaptorProcess(PositionExtend positionExtend, PositionResponse response) {
		// 4.转化
		//每个positionId就对应着一个PositionAdaptor,配置在xml中
		Long positionId = positionExtend.getPositionId();
		PositionAdaptor positionAdaptor = adaptorContainer.get(positionId);
		boolean result = false;
		if(null != positionAdaptor) {
			Object obj = positionAdaptor.convert(positionExtend);
			// 5.调用
			result = positionAdaptor.persist(obj);
			if(!result) {
				final PositionExtend log = positionExtend;
				executor.execute(new Runnable() {
					@Override
					public void run() {
						//记录未执行成功的日志
						positionExtendLogWrapper.persistForFail(log);
					}
				});
			}
		}
		//记录调用日志，更新引用计数 TODO
		

		// 6.返回
		List<Position> positions = positionExtend.getPositions();
		List<PositionRef> refList = new ArrayList<>(positions.size());
		for(Position position : positions) {
			refList.addAll(position.getPositionRefs());
		}
		response.setContent(refList);
	}
	
	public PositionResponse<Position> proccessQuery(PositionRequest request) {
		
		// 1.解析
		PositionExtend positionExtend = resolvePositionRequest(request);
		PositionResponse<Position> response = new PositionResponse<>();
		if(null != positionExtend && PositionExtend.SUCCESS_YES==positionExtend.getSuccess()) {
	
			Long positionId = positionExtend.getPositionId();
			PositionAdaptor positionAdaptor = adaptorContainer.get(positionId);
			boolean result = false;
			if(null != positionAdaptor) {
				Position entity = null;//positionAdaptor.queryTree(positionExtend);
				response.setContent(entity);
			}
			
			
		}
		response.setSuccess(positionExtend.getSuccess());
		response.setRespCode(positionExtend.getRespCode());
		response.setRespMsg(positionExtend.getRespMsg());
		return response;
	}

	public PositionExtend resolvePositionRequest(PositionRequest request) {

		PositionExtend positionExtend = positionRequestResolver.resolvePositionRequest(request);
		return positionExtend;
	}
	public PositionExtend resolvePositionRequestForDelete(PositionRequest request) {
		PositionExtend positionExtend = positionRequestResolver.resolvePositionRequestForDelete(request);
		return positionExtend;
	}
	
	public PositionRef addPositionRef(PositionRef positionRef) {
		Long baseId = positionRef.getBaseId();
		Position position = positionWrapper.findById(baseId);
		if (null != position) {
			positionRef.setExtendId(position.getExtendId());
			Long positionId = position.getPositionId();
			positionRef.setPositionId(positionId);
			positionRef.setPosition(position);
			
			Long userId = positionRef.getUserId();
			Sayword sayword = saywordWrapper.findByUserIdAndMainId(userId);
			if(null != sayword) {
				positionRef.setSaywordId(sayword.getId());
				positionRef.setSayword(sayword);
			}
			
			PositionAdaptor positionAdaptor = adaptorContainer.get(positionId);
			Object t = positionAdaptor.findByMainId(positionRef);
			positionRef = positionAdaptor.fillPositionRef(t,positionRef);
			boolean result = positionAdaptor.addPositionRef(positionRef,t);
			if (!result) {
				// TODO log it
				LOGGER.error("!!!positionAdaptor.addPositionRef , {} ", positionAdaptor.getClass().getName());
				LOGGER.error("!!!positionAdaptor.addPositionRef ,data: {} ", GsonBuilderUtils.toJson(positionRef));
			}
			return positionRef;
		}
		return null;

	}

	
	public PositionRef mergePositionRef(PositionRef positionRef) {
		Long mainId = positionRef.getMainId();
		Long baseId = positionRef.getBaseId();
		Position position = positionWrapper.findById(baseId);
		if (null != position) {
			positionRef.setExtendId(position.getExtendId());
			Long positionId = position.getPositionId();
			positionRef.setPositionId(positionId);

			positionRef.setPosition(position);

			PositionAdaptor positionAdaptor = adaptorContainer.get(positionId);
			boolean result = positionAdaptor.mergePositionRef(positionRef);
			if (!result) {
				// TODO log it
				LOGGER.error("!!!positionAdaptor.mergePosition , {} ", positionAdaptor.getClass().getName());
				LOGGER.error("!!!positionAdaptor.mergePosition ,data: {} ", GsonBuilderUtils.toJson(positionRef));
			}
			return positionRef;
		}
		return null;

	}
	
	/**
	 * 移除关联
	 * @param positionRef
	 * @return
	 */
	public PositionRef deletePositionRef(PositionRef positionRef) {
		Long id = positionRef.getId();
		PositionRef entity = positionRefWrapper.findById(id);
		if(null != entity) {
			Long positionId = entity.getPositionId();
			if(null != positionId) {
				PositionAdaptor positionAdaptor = adaptorContainer.get(positionId);
				positionRef.setMainId(entity.getMainId());
				boolean result = positionAdaptor.deletePositionRef(positionRef);
				if(!result) {
					//TODO log it
					LOGGER.error("!!!positionAdaptor.mergePosition , {} ",positionAdaptor.getClass().getName());
					LOGGER.error("!!!positionAdaptor.mergePosition ,data: {} ",GsonBuilderUtils.toJson(entity));
				}
				entity.setSuccess(PositionRef.SUCCESS_YES);
			}
		}
		
		return entity;
	}
	
	/**
	 * 开闭关联
	 * @param positionRef
	 * @return
	 */
	public PositionRef openPositionRef(PositionRef positionRef) {
		Long id = positionRef.getId();
		PositionRef entity = positionRefWrapper.findById(id);
		if(null != entity) {
			Long positionId = entity.getPositionId();
			if(null != positionId) {
				PositionAdaptor positionAdaptor = adaptorContainer.get(positionId);
				positionRef.setMainId(entity.getMainId());
				boolean result = positionAdaptor.openPositionRef(positionRef);
				if(!result) {
					//TODO log it
					LOGGER.error("!!!positionAdaptor.openPositionRef , {} ",positionAdaptor.getClass().getName());
					LOGGER.error("!!!positionAdaptor.openPositionRef ,data: {} ",GsonBuilderUtils.toJson(entity));
				}
				entity.setSuccess(PositionRef.SUCCESS_YES);
			}
		}
		
		return entity;
	}
	
	public PositionRef mergeSimpleSingle(PositionRef positionRef) {
		Long id = positionRef.getId();
		PositionRef entity = positionRefWrapper.findById(id);
		if(null != entity) {
			Long positionId = entity.getPositionId();
			if(null != positionId) {
				PositionAdaptor positionAdaptor = adaptorContainer.get(positionId);
				positionRef.setMainId(entity.getMainId());
				boolean result = positionAdaptor.mergeSimpleSingle(positionRef);
				if(!result) {
					//TODO log it
					LOGGER.error("!!!positionAdaptor.openPositionRef , {} ",positionAdaptor.getClass().getName());
					LOGGER.error("!!!positionAdaptor.openPositionRef ,data: {} ",GsonBuilderUtils.toJson(entity));
				}
				entity.setSuccess(PositionRef.SUCCESS_YES);
			}
		}
		
		return entity;
	}
	public PositionRef deleteRefByBaseId(PositionRef positionRef) {
		Long id = positionRef.getBaseId();
		Position entity = positionWrapper.findById(id);
		if(null != entity) {
			Long positionId = entity.getPositionId();
			positionRef.setPosition(entity);
			if(null != positionId) {
				PositionAdaptor positionAdaptor = adaptorContainer.get(positionId);
				positionRef.setMainId(entity.getMainId());
				boolean result = positionAdaptor.deleteRefByBaseId(positionRef);
				if(!result) {
					//TODO log it
					LOGGER.error("!!!positionAdaptor.openPositionRef , {} ",positionAdaptor.getClass().getName());
					LOGGER.error("!!!positionAdaptor.openPositionRef ,data: {} ",GsonBuilderUtils.toJson(positionRef));
				}
				positionRef.setSuccess(PositionRef.SUCCESS_YES);
			}
		}
		
		return positionRef;
	}
	
	
	
	

	public Map<Long, PositionAdaptor> getAdaptorContainer() {
		return adaptorContainer;
	}

	public void setAdaptorContainer(Map<Long, PositionAdaptor> adaptorContainer) {
		this.adaptorContainer = adaptorContainer;
	}

}
