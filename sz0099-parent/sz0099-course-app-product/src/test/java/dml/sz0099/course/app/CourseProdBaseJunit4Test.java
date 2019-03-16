package dml.sz0099.course.app;

import org.jit4j.app.DmlaBaseJunit4Test;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;


/**
 * 
 * @formatter:off
 * 
 * description: 使用junit4进行测试   @RunWith(SpringJUnit4ClassRunner.class) 
 * @author bruce yang at 2018-08-24 16:25:57
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@ContextConfiguration   
({"classpath*:test-course-prod-spring-content.xml"}) //加载配置文件  
public class CourseProdBaseJunit4Test extends DmlaBaseJunit4Test {
	
	@Test
	public void testInit() {
		Assert.assertTrue("junit init sucess ", 2>1);
	}
}
