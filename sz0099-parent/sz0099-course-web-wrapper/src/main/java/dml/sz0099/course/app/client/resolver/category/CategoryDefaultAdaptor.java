/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.category;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dml.sz0099.course.app.client.wrapper.category.CategoryExtendWrapper;
import dml.sz0099.course.app.client.wrapper.category.CategoryWrapper;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.category.CategoryExtend;
import dml.sz0099.course.app.persist.entity.category.CategoryRef;

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

public class CategoryDefaultAdaptor<T> implements CategoryAdaptor<T>{
	
	protected CategoryWrapper categoryWrapper;
	
	protected CategoryExtendWrapper categoryExtendWrapper;
	
	protected CategoryExtend configExtend;

	@Override
	public T convert(CategoryExtend categoryExtend) {
		return null;
	}

	@Override
	public boolean persist(T t) {
		return false;
	}

	@Override
	public boolean mergeCategory(CategoryRef ref) {
		return false;
	}

	@Override
	public boolean deleteCategory(CategoryRef ref) {
		return false;
	}

	@Override
	public boolean deleteCategoryList(List<CategoryRef> refList) {
		return false;
	}
	
	public Category queryTree( Long mainId, Long subId) {
		return queryTree(null,mainId,subId);
	}
	
	public Category queryTree(Long extendId, Long mainId, Long subId) {
		Category category = new Category();
		Long userId = null;
		if(extendId==null) {
			if(null != getConfigExtend()) {
				extendId=getConfigExtend().getId();
				userId = getConfigExtend().getUserId();
			}else {
				CategoryExtend extend = config();
				if(null != extend) {
					extendId = extend.getId();
					userId = extend.getUserId();
				}
			}
		}
		category.setExtendId(extendId);
		category.setMainId(mainId);
		category.setSubId(subId);
		category.setUserId(userId);
		List<Category> children = categoryWrapper.findMainAndSub(category);
		category.setChildren(children);
		return category;
	}

	@Override
	public Category queryTree(CategoryExtend categoryExtend) {
		
		List<Category> categorys = categoryExtend.getCategorys();
		
		Category result = new Category();
		if(null != categorys && !categorys.isEmpty()) {
			for(Category category : categorys) {
				List<Category> children = categoryWrapper.findMainAndSub(category);
				result.setChildren(children);
			}
			categoryExtend.setSuccess(CategoryExtend.SUCCESS_YES);
		}
		return result;
	}

	public CategoryWrapper getCategoryWrapper() {
		return categoryWrapper;
	}

	public void setCategoryWrapper(CategoryWrapper categoryWrapper) {
		this.categoryWrapper = categoryWrapper;
	}

	@Override
	public Category findSingle(CategoryRef ref) {
		Long baseId = ref.getBaseId();
		Category entity = categoryWrapper.findById(baseId);
		return entity;
	}

	@Override
	public Map<Long, Category> findMap(List<Long> idList) {
		Map<Long, Category> map = categoryWrapper.findMapByIdList(idList);
		return map;
	}
	
	public CategoryExtend findExtend(CategoryExtend extend) {
		CategoryExtend entity  = categoryExtendWrapper.findCategoryExtend(extend);
		return entity;
	}
	
	public CategoryExtend config() {
		
		//子类override
		return this.configExtend;
	}

	@Override
	public CategoryExtend config(CategoryExtend extend) {
		if(null != extend && getConfigExtend() == null) {
			this.configExtend = new CategoryExtend();
			this.configExtend.setId(extend.getId());
			this.configExtend.setAid(extend.getAid());
			this.configExtend.setDevId(extend.getDevId());
			this.configExtend.setDepthMaxnum(extend.getDepthMaxnum());
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

	public CategoryExtend getConfigExtend() {
		return configExtend;
	}

	public void setConfigExtend(CategoryExtend configExtend) {
		this.configExtend = configExtend;
	}

	public CategoryExtendWrapper getCategoryExtendWrapper() {
		return categoryExtendWrapper;
	}

	public void setCategoryExtendWrapper(CategoryExtendWrapper categoryExtendWrapper) {
		this.categoryExtendWrapper = categoryExtendWrapper;
	}


}
