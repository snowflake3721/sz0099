/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.category;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.jit8j.core.util.GsonBuilderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import dml.sz0099.course.app.client.wrapper.category.CategoryExtendLogWrapper;
import dml.sz0099.course.app.client.wrapper.category.CategoryExtendWrapper;
import dml.sz0099.course.app.client.wrapper.category.CategoryRefWrapper;
import dml.sz0099.course.app.client.wrapper.category.CategoryWrapper;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.category.CategoryExtend;
import dml.sz0099.course.app.persist.entity.category.CategoryRef;

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

public class CategoryProccessor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryProccessor.class);


	ThreadPoolExecutor executor = new ThreadPoolExecutor(12, 20, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());


	@Autowired
	private CategoryRequestResolver categoryRequestResolver;
	
	@Autowired
	private CategoryExtendWrapper categoryExtendWrapper;
	
	@Autowired
	private CategoryExtendLogWrapper categoryExtendLogWrapper;
	
	@Autowired
	private CategoryRefWrapper categoryRefWrapper;
	
	@Autowired
	private CategoryWrapper categoryWrapper;
	
	private Map<Long, CategoryAdaptor> adaptorContainer;
	
	public CategoryResponse proccessSingle(CategoryRequest request) {
		// 1.解析
		CategoryExtend categoryExtend = resolveCategoryRequest(request);
		CategoryResponse response = new CategoryResponse();
		if(null != categoryExtend && CategoryExtend.SUCCESS_YES==categoryExtend.getSuccess()) {
			//2 处理绑定
			adaptorProcess(categoryExtend, response);
		}
		response.setSuccess(categoryExtend.getSuccess());
		response.setRespCode(categoryExtend.getRespCode());
		response.setRespMsg(categoryExtend.getRespMsg());
		return response;
	}

	public CategoryResponse proccess(CategoryRequest request) {
		
		// 1.解析
		CategoryExtend categoryExtend = resolveCategoryRequest(request);
		CategoryResponse response = new CategoryResponse();
		if(null != categoryExtend && CategoryExtend.SUCCESS_YES==categoryExtend.getSuccess()) {
	
			adaptorProcess(categoryExtend, response);
			
		}
		response.setSuccess(categoryExtend.getSuccess());
		response.setRespCode(categoryExtend.getRespCode());
		response.setRespMsg(categoryExtend.getRespMsg());
		return response;
	}
	

	/**
	 * @param categoryExtend
	 * @param response
	 */
	private void adaptorProcess(CategoryExtend categoryExtend, CategoryResponse response) {
		// 4.转化
		//每个positionId就对应着一个CategoryAdaptor,配置在xml中
		Long positionId = categoryExtend.getPositionId();
		CategoryAdaptor categoryAdaptor = adaptorContainer.get(positionId);
		boolean result = false;
		if(null != categoryAdaptor) {
			Object obj = categoryAdaptor.convert(categoryExtend);
			// 5.调用
			result = categoryAdaptor.persist(obj);
			if(!result) {
				final CategoryExtend log = categoryExtend;
				executor.execute(new Runnable() {
					@Override
					public void run() {
						//记录未执行成功的日志
						categoryExtendLogWrapper.persistForFail(log);
					}
				});
			}
		}
		//记录调用日志，更新引用计数 TODO
		

		// 6.返回
		List<Category> categorys = categoryExtend.getCategorys();
		List<CategoryRef> refList = new ArrayList<>(categorys.size());
		for(Category category : categorys) {
			refList.addAll(category.getCategoryRefs());
		}
		response.setContent(refList);
	}
	
	public CategoryResponse<Category> proccessQuery(CategoryRequest request) {
		
		// 1.解析
		CategoryExtend categoryExtend = resolveCategoryRequest(request);
		CategoryResponse<Category> response = new CategoryResponse<>();
		if(null != categoryExtend && CategoryExtend.SUCCESS_YES==categoryExtend.getSuccess()) {
	
			Long positionId = categoryExtend.getPositionId();
			CategoryAdaptor categoryAdaptor = adaptorContainer.get(positionId);
			boolean result = false;
			if(null != categoryAdaptor) {
				Category entity = categoryAdaptor.queryTree(categoryExtend);
				response.setContent(entity);
			}
			
			
		}
		response.setSuccess(categoryExtend.getSuccess());
		response.setRespCode(categoryExtend.getRespCode());
		response.setRespMsg(categoryExtend.getRespMsg());
		return response;
	}

	public CategoryExtend resolveCategoryRequest(CategoryRequest request) {

		CategoryExtend categoryExtend = categoryRequestResolver.resolveCategoryRequest(request);
		return categoryExtend;
	}
	public CategoryExtend resolveCategoryRequestForDelete(CategoryRequest request) {
		CategoryExtend categoryExtend = categoryRequestResolver.resolveCategoryRequestForDelete(request);
		return categoryExtend;
	}

	
	public CategoryRef mergeCategoryRef(CategoryRef categoryRef) {
		Long mainId = categoryRef.getMainId();
		Long baseId = categoryRef.getBaseId();
		Category category = categoryWrapper.findById(baseId);
		if (null != category) {
			categoryRef.setExtendId(category.getExtendId());
			Long positionId = category.getPositionId();
			categoryRef.setPositionId(positionId);

			CategoryRef entity = categoryRefWrapper.changeSingle(categoryRef);
			entity.setCategory(category);

			if (CategoryRef.SUCCESS_YES == entity.getSuccess()) {
				CategoryAdaptor categoryAdaptor = adaptorContainer.get(positionId);
				boolean result = categoryAdaptor.mergeCategory(entity);
				if (!result) {
					// TODO log it
					LOGGER.error("!!!categoryAdaptor.mergeCategory , {} ", categoryAdaptor.getClass().getName());
					LOGGER.error("!!!categoryAdaptor.mergeCategory ,data: {} ", GsonBuilderUtils.toJson(entity));
				}
			}
			return entity;
		}
		return null;

	}
	
	/**
	 * 删除单张图片
	 * @param categoryRef
	 * @return
	 */
	public CategoryRef deleteCategoryRef(CategoryRef categoryRef) {
		Long id = categoryRef.getId();
		Long positionId = categoryRefWrapper.findPositionId(id);
		CategoryRef entity = categoryRefWrapper.findById(id);
		if(null != positionId) {
			CategoryAdaptor categoryAdaptor = adaptorContainer.get(positionId);
			//categoryRef.setSubId(entity.getSubId());
			categoryRef.setMainId(entity.getMainId());
			boolean result = categoryAdaptor.deleteCategory(categoryRef);
			
			if(!result) {
				//TODO log it
				LOGGER.error("!!!categoryAdaptor.mergeCategory , {} ",categoryAdaptor.getClass().getName());
				LOGGER.error("!!!categoryAdaptor.mergeCategory ,data: {} ",GsonBuilderUtils.toJson(entity));
			}
			categoryRefWrapper.deleteById(categoryRef);
			entity.setSuccess(CategoryRef.SUCCESS_YES);
		}
		
		return entity;
	}
	
	public boolean deleteByRequest(CategoryRequest request) {
		CategoryExtend categoryExtend  = resolveCategoryRequestForDelete(request);
		if(CategoryExtend.SUCCESS_YES==categoryExtend.getSuccess()) {
			
			Long mainId = request.getMainId();
			Long subId = request.getSubId();
			Long extendId = categoryExtend.getId();
			Long userId = request.getUserId();
			CategoryRef categoryRef = new CategoryRef();
			categoryRef.setMainId(mainId);
			//categoryRef.setSubId(subId);
			categoryRef.setExtendId(extendId);
			categoryRef.setUserId(userId);
			categoryRef.setLastModifiedBy(userId);
			
			if(null != extendId) {
				categoryRefWrapper.deleteByMainIdAndSubId(categoryRef);
			}
		}
		return true;
	}
	

	public Map<Long, CategoryAdaptor> getAdaptorContainer() {
		return adaptorContainer;
	}

	public void setAdaptorContainer(Map<Long, CategoryAdaptor> adaptorContainer) {
		this.adaptorContainer = adaptorContainer;
	}

}
