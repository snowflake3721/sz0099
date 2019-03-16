/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.category;

import java.util.List;
import java.util.Map;

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

public interface CategoryAdaptor<T> {
	
	CategoryExtend config(CategoryExtend extend);

	T convert(CategoryExtend categoryExtend);
	
	boolean persist(T t);
	
	boolean mergeCategory(CategoryRef ref);
	
	boolean deleteCategory(CategoryRef ref);
	
	boolean deleteCategoryList(List<CategoryRef> refList);
	
	Category queryTree(CategoryExtend categoryExtend);
	
	Category findSingle(CategoryRef ref);
	
	Map<Long,Category> findMap(List<Long> idList);
	
	public Category queryTree( Long mainId, Long subId);
}
