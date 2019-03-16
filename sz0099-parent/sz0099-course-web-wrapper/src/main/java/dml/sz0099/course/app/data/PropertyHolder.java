/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.data;

import java.util.Collection;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.jit4j.app.module.define.L;
import org.jit4j.app.persist.entity.module.LabelContext;
import org.jit4j.app.persist.entity.module.PropertyContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-09-16 09:41:23
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-09-16	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class PropertyHolder {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyHolder.class);
	
	
	public static Map<String,PropertyConverter> propertyConverterMap;
	
	
	@PostConstruct
	public void init() {
		
		LOGGER.debug(">>>>> PropertyHolder init >>>>>>>");
		
		if(null != propertyConverterMap && !propertyConverterMap.isEmpty()) {
			
			for(Map.Entry<String, PropertyConverter> entry : propertyConverterMap.entrySet()) {
				String key = entry.getKey();
				LOGGER.debug(">>>>> PropertyHolder init > PropertyConverter:{} load begin >>>> ", key);
				PropertyConverter dataConverter  = entry.getValue();
					Collection collection = dataConverter.findAll();
					PropertyContext data = dataConverter.initPropertyContext();
					Map<Integer, LabelContext> labelMap = dataConverter.convertToLabelContext(collection);
					data.setLabelMap(labelMap);
			}
		}
	}
	
	public static Map<String,PropertyContext> get(String domain) {
		
		return L.getPropertyContextMap(domain);
	}
	
	public static PropertyContext getContext(String property, String domain) {
		
		return L.getPropertyContext(property, domain);
	}
	
	public static LabelContext getLabelcontext(Integer value, String property, String domain) {
		return L.getLabelContext(value, property, domain);
	}

	public static Map<String, PropertyConverter> getPropertyConverterMap() {
		return propertyConverterMap;
	}

	public void setPropertyConverterMap(Map<String, PropertyConverter> propertyConverterMap) {
		PropertyHolder.propertyConverterMap = propertyConverterMap;
	}
	
}
