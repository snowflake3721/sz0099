/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.biz.service.user;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jit4j.core.persist.page.PageRequest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.util.HtmlUtils;

import dml.sz0099.course.app.CourseProdBaseJunit4Test;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-12-30 10:44:48
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-12-30	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class SaywordServiceTest extends CourseProdBaseJunit4Test{

	@Autowired
	private SaywordService saywordService;
	
	@Test
	public void testFixAllPraise() {
		
		Pageable pageable = new PageRequest(0,100);
		boolean success = saywordService.fixAllSayword(pageable);
		Assert.assertTrue("fixed", success);
	}
	
	public static void main(String[] args) {
		String content=HtmlUtils.htmlEscapeHex("\\xF0\\x9F\\x98\\x84\\xE7\\x84");
		System.out.println(content);
		String javaContent = StringEscapeUtils.escapeJava(content);
		System.out.println(javaContent);
	}
}
