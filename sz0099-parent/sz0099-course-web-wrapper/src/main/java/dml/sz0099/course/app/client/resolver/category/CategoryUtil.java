/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.category;

import java.util.HashMap;
import java.util.Map;

import dml.sz0099.course.app.client.wrapper.category.CategoryWrapper;
import dml.sz0099.course.app.persist.entity.category.Category;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-10-05 18:07:32
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-10-05	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class CategoryUtil {

	protected static CategoryWrapper categoryWrapper;
	
	private static Map<Long , Category> categoryMap=new HashMap<>();
	private static Map<String , Category> categoryMapWithCode=new HashMap<>();
	
	public static  Category findById(Long id) {
		Category entity = categoryMap.get(id);
		if(null == entity && null != categoryWrapper) {
			entity = categoryWrapper.findById(id);
			if(null != entity) {
				categoryMap.put(id, entity);
				categoryMapWithCode.put(entity.getCode(), entity);
			}
		}
		return entity;
	}
	
	public static  Category findByCode(String code) {
		Category entity = categoryMapWithCode.get(code);
		if(null == entity && null != categoryMapWithCode) {
			entity = categoryWrapper.findByCode(code);
			if(null != entity) {
				categoryMapWithCode.put(code, entity);
				categoryMap.put(entity.getId(), entity);
			}
		}
		return entity;
	}
	
	public static  Category get(Long id) {
		Category entity = findById(id);
		return entity;
	}
	
	public static  String getName(Long id) {
		Category entity = get(id);
		String name = "";
		if(null != entity) {
			name = entity.getName();
		}
		return name;
	}
	

	public CategoryWrapper getCategoryWrapper() {
		return categoryWrapper;
	}

	public void setCategoryWrapper(CategoryWrapper categoryWrapper) {
		this.categoryWrapper = categoryWrapper;
	}

	public Map<Long, Category> getCategoryMap() {
		return categoryMap;
	}

	public void setCategoryMap(Map<Long, Category> categoryMap) {
		this.categoryMap = categoryMap;
	}
}
