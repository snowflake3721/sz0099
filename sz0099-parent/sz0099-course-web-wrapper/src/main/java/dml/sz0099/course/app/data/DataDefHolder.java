/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.jit4j.core.pub.transfer.dto.StaticDataDef;
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

public class DataDefHolder {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataDefHolder.class);
	
	public static Map<Object, StaticDataDef> dataDef = new HashMap<>();
	
	public static Map<Object,DataConverter> dataConverterMap;
	
	
	public static void putInto(Object key, Map<Object,Object> data) {
		StaticDataDef staticDataDef = new StaticDataDef();
		staticDataDef.setData(data);
		dataDef.put(key, staticDataDef);
	}
	
	@PostConstruct
	public void init() {
		
		LOGGER.debug(">>>>> DataDefHolder init >>>>>>>");
		
		if(null != dataConverterMap && !dataConverterMap.isEmpty()) {
			
			for(Map.Entry<Object, DataConverter> entry : dataConverterMap.entrySet()) {
				Object key = entry.getKey();
				LOGGER.debug(">>>>> DataDefHolder init > DataConverter:{} load begin >>>> ", key);
				DataConverter dataConverter  = entry.getValue();
					Collection collection = dataConverter.findAll();
					Map data = dataConverter.convertToMap(collection);
					putInto(dataConverter.getKey(),data);
				
			}
			
		}
		
	}
	
	public static StaticDataDef get(Object key) {
		StaticDataDef staticDataDef = (StaticDataDef)dataDef.get(key);
		return staticDataDef;
	}
	
	public static Object getLabel(String key, Object fieldValue, String fieldname) {
		Object label=null;
		if(null != dataConverterMap && !dataConverterMap.isEmpty()) {
			label = dataConverterMap.get(key).getLabel(fieldValue, fieldname);
		}
		return label;
	}

	public Map<Object, DataConverter> getDataConverterMap() {
		return dataConverterMap;
	}

	public void setDataConverterMap(Map<Object, DataConverter> dataConverterMap) {
		DataDefHolder.dataConverterMap = dataConverterMap;
	}
	
	
	
}
