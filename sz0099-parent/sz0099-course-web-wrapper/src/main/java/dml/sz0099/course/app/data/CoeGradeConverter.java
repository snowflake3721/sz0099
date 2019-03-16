/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dml.sz0099.course.app.client.wrapper.product.CoeGradeWrapper;
import dml.sz0099.course.app.persist.entity.product.CoeGrade;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-09-16 10:25:17
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-09-16	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class CoeGradeConverter extends DefaultConverter<String, Integer, CoeGrade> implements DataConverter<String, Integer,CoeGrade> {

	@Autowired
	private CoeGradeWrapper coeGradeWrapper;
	
	public static final String SZ0099_COURSE_PROD_COEGRADE="sz0099_course_prod_coeGrade";
	
	@Override
	public Map<Integer, CoeGrade> convertToMap(Collection<CoeGrade> goeGradeList){
		Map<Integer, CoeGrade> gradeMap = new HashMap<>();
		for(CoeGrade grade : goeGradeList) {
			Integer g = grade.getGrade();
			gradeMap.put(g, grade);
		}
		return gradeMap;
	}
	
	@Override
	public Collection<CoeGrade> findAll() {
		List<CoeGrade> goeGradeList = coeGradeWrapper.findAll();
		return goeGradeList;
	}
	
	@Override
	public String getKey() {
		return SZ0099_COURSE_PROD_COEGRADE;
	}

}
