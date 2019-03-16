/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.data;

import java.util.Collection;
import java.util.Map;

import org.jit4j.app.persist.entity.module.LabelContext;
import org.jit4j.app.persist.entity.module.PropertyContext;

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

public interface PropertyConverter<V> {

	PropertyContext initPropertyContext();
	
	Map<Integer, LabelContext> convertToLabelContext(Collection<V> collection);
	
	Collection<V> findAll();
	
	String getDomain();
	
	String getProperty();
	
	String getInputLabel();
	
	String getNameBar();
	
	String getDevId();
	
	String getDescription();
	
	String getPlaceHolder();
	
	LabelContext getValue(Integer k);
	
	String getLabelCustom(Integer k, String fieldName);
}
