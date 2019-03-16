/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.data;

import java.util.Map;

import org.jit4j.core.persist.entity.BaseEntity;
import org.jit4j.core.pub.transfer.dto.StaticDataDef;
import org.springframework.security.util.FieldUtils;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-09-16 10:13:00
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-09-16	basic init
 * @param <K>
 * @param <V>
 * 
 * @formatter:on
 * </pre>
 */

public abstract class DefaultConverter<M, K, V> implements DataConverter<M, K, V> {


	public V getValue(M m, K k) {
		V v = null;
		if(null != m) {
			StaticDataDef staticDataDef = (StaticDataDef)DataDefHolder.get(m);
			if(null != staticDataDef) {
				Map map = (Map)staticDataDef.getData();
				v=(V)map.get(k);
			}
		}
		return v;
	}
	
	public V getValue(K k) {
		M m = getKey();
		V v = getValue(m, k);
		return v;
	}

	@Override
	public Object getLabel(K k, String fieldName) {
		Object vv = null;
		V v = getValue(k);
		if(null != v ) {
			if( v instanceof String) {
				return v;
			}else if(v instanceof Map) {
				Map vs = (Map)v;
				vv = vs.get(fieldName);
			}else if(v instanceof BaseEntity){
				//vv = getFieldValue(k, fieldName);
				try {
					vv = FieldUtils.getFieldValue(v, fieldName);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return vv;
	}

}
