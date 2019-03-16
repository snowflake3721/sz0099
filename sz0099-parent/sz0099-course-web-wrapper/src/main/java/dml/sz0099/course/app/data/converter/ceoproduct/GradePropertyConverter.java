/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.data.converter.ceoproduct;

import java.util.Collection;
import java.util.Map;

import javax.annotation.Resource;

import org.jit4j.app.persist.entity.module.LabelContext;
import org.jit8j.core.util.NumChineseUtil;
import org.springframework.beans.factory.annotation.Autowired;

import dml.sz0099.course.app.client.wrapper.product.CoeGradeWrapper;
import dml.sz0099.course.app.data.DefaultPropertyConverter;
import dml.sz0099.course.app.data.PropertyConverter;
import dml.sz0099.course.app.module.define.SZ0099AppModule;
import dml.sz0099.course.app.persist.entity.product.CoeGrade;
import dml.sz0099.course.app.persist.entity.product.CoeProduct;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-09-19 09:17:25
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-09-19	basic init
 * @param <V>
 * 
 * @formatter:on
 * </pre>
 */

public class GradePropertyConverter extends DefaultPropertyConverter<CoeGrade> implements PropertyConverter<CoeGrade> {

	@Autowired
	private CoeGradeWrapper coeGradeWrapper;
	
	@Override
	public Map<Integer, LabelContext> convertToLabelContext(Collection<CoeGrade> collection) {
		if(null!= collection && !collection.isEmpty()) {
			for(CoeGrade coeGrade : collection) {
				Integer valueInt = coeGrade.getGrade();
				String label = coeGrade.getName();
				String labelAlias = coeGrade.getName();
				String description = coeGrade.getDescription();
				Integer rates = coeGrade.getRates();
				
				LabelContext current = super.propertyContext.initLabel(valueInt, label, labelAlias, description);
				
				if(null != rates) {
					String rebate = NumChineseUtil.rebateConvert(rates);
					StringBuilder sb = new StringBuilder();
					sb.append(rates).append(" -- ").append(rebate).toString();
					current.setCol1(sb.toString());
				}
			}
		}
		return super.propertyContext.getLabelMap();
	}

	@Override
	public Collection<CoeGrade> findAll() {
		Collection<CoeGrade> content = coeGradeWrapper.findAll();
		return content;
	}

	@Override
	public String getDomain() {
		return CoeProduct.class.getName();
	}

	@Override
	public String getProperty() {
		return "grade";
	}

	@Override
	public String getInputLabel() {
		return "等级";
	}

	@Override
	public String getNameBar() {
		return "选择等级";
	}

	@Override
	public String getDevId() {
		return SZ0099AppModule.DEVELOPER_ID_SZ0099;
	}

	@Override
	public String getDescription() {
		return "等级：产品设立优惠策略为'等级'时，产品价格（以现价为基准）将随着用户等级的不同而进行折扣计算，用以区分用户群体，低于该级别的用户不能享受优惠策略，数据项为：级别-折扣系数，100为不打折";
	}

	@Override
	public String getPlaceHolder() {
		
		return "等级优惠的最低门槛";
	}

}
