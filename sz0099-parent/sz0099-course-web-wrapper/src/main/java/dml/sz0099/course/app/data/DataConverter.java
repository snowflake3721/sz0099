/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.data;

import java.util.Collection;
import java.util.Map;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-09-16 10:04:30
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-09-16	basic init
 * 
 * @formatter:on
 * </pre>
 */

public interface DataConverter<M, K,V> {

	Map<K, V> convertToMap(Collection<V> collection);
	
	Collection<V> findAll();
	
	M getKey();
	
	V getValue(K k);
	
	Object getLabel(K k, String fieldName);
}
