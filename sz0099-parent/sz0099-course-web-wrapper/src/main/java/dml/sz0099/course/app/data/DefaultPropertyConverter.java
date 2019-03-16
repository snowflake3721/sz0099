/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.data;

import java.util.Collection;
import java.util.Map;

import org.jit4j.app.module.define.L;
import org.jit4j.app.persist.entity.module.LabelContext;
import org.jit4j.app.persist.entity.module.PropertyContext;
import org.springframework.security.util.FieldUtils;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-09-19 08:07:03
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-09-19	basic init
 * 
 * @formatter:on
 * </pre>
 */

public abstract class DefaultPropertyConverter< V> implements PropertyConverter< V> {

	protected PropertyContext propertyContext;

	public PropertyContext initPropertyContext() {
		if(null == propertyContext) {
			String devId = this.getDevId();
			String inputLabel = this.getInputLabel();
			String nameBar = this.getNameBar();
			String placeHolder = this.getPlaceHolder();
			String property = this.getProperty();
			String domain = this.getDomain();
			String description = this.getDescription();
			propertyContext = L.initProperty(inputLabel, nameBar, placeHolder, description, property, domain, devId);
		}
		return propertyContext;
	}

	@Override
	public LabelContext getValue(Integer k) {
		LabelContext labelContext = L.getLabelContext(k, this.getProperty(), this.getDomain());
		return labelContext;
	}

	@Override
	public String getLabelCustom(Integer k, String fieldName) {
		LabelContext labelContext = getValue(k);
		String vv = "";
		if (null != labelContext) {
			try {
				Object result = FieldUtils.getFieldValue(labelContext, fieldName);
				if (null != result) {
					if (result instanceof String) {
						vv = (String) result;
					} else {
						vv = String.valueOf(result);
					}
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return vv;
	}

	/**
	 * 若有注入，以注入属性为主
	 * @param propertyContext
	 */
	public void setPropertyContext(PropertyContext propertyContext) {
		PropertyContext context = this.initPropertyContext();
		if(null != context) {
			context.setNameBar(propertyContext.getNameBar());
			context.setCol1(propertyContext.getCol1());
			context.setCol2(propertyContext.getCol2());
			context.setDescription(propertyContext.getDescription());
			context.setDevId(propertyContext.getDevId());
			context.setDomain(propertyContext.getDomain());
			context.setInputLabel(propertyContext.getInputLabel());
			context.setPlaceHolder(propertyContext.getPlaceHolder());
			context.setProperty(propertyContext.getProperty());
		}
		this.propertyContext = propertyContext;
	}

	public PropertyContext getPropertyContext() {
		if(this.propertyContext==null) {
			this.initPropertyContext();
		}
		return propertyContext;
	}

}
