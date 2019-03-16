/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.biz.service.product;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import dml.sz0099.course.app.CourseProdBaseJunit4Test;
import dml.sz0099.course.app.persist.entity.product.CoeGrade;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-09-13 07:38:36
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-09-13	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class CoeGradeServiceTest extends CourseProdBaseJunit4Test {

	@Autowired
	private CoeGradeService coeGradeService;
	
	@Test
	public void testInitData() {
		coeGradeService.deleteAll();
		List<CoeGrade> entityList = coeGradeService.initData();
		Assert.assertTrue("init data for CoeGrade", 2>1);
	}
	
}
