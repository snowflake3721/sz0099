/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.biz.service.article;

import org.jit4j.core.persist.page.PageRequest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.CourseProdBaseJunit4Test;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-12-29 22:48:59
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-12-29	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class CoeArticlePraiseServiceTest extends CourseProdBaseJunit4Test{

	@Autowired
	private CoeArticlePraiseService coeArticlePraiseService;
	
	@Test
	public void testFixAllPraise() {
		
		Pageable pageable = new PageRequest(0,100);
		boolean success = coeArticlePraiseService.fixAllPraise(pageable);
		Assert.assertTrue("fixed", success);
	}
}
