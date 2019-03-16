/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.biz.service.order;

import java.util.Date;
import java.util.List;

import org.jit4j.core.persist.page.PageRequest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import dml.sz0099.course.app.CourseProdBaseJunit4Test;
import dml.sz0099.course.app.persist.entity.order.CoeOrder;
import dml.sz0099.course.app.persist.entity.order.CoeOrderProduct;
import dml.sz0099.course.app.persist.entity.order.bo.CoeOrderProductBo;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-09-10 22:22:48
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-09-10	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class CoeOrderProductServiceTest extends CourseProdBaseJunit4Test {

	@Autowired
	private CoeOrderProductService coeOrderProductService;

	
	@Test
	public void testFindPageForMyOrderList() {
		PageRequest pageable = new PageRequest();
		pageable.setSize(2);
		pageable.setPage(0);
		String title = "架构";
		CoeOrderProductBo orderProductBo = new CoeOrderProductBo();
		orderProductBo.setTitle(title);
		Long userId = 233116161842835456l;
		orderProductBo.setUserId(userId);
		Date beginTime = new Date();
		
		orderProductBo.setBeginTime(beginTime);
		orderProductBo.setEndTime(beginTime);
		
		CoeOrder order = new CoeOrder();
		order.setStatus(CoeOrder.STATUS_PAY_PAYED);
		
		Page<CoeOrderProduct> page = coeOrderProductService.findPageForMyOrderList(orderProductBo, pageable);
		
		if(null != page) {
			List<CoeOrderProduct> content = page.getContent();
			if(null != content && !content.isEmpty()) {
				CoeOrderProduct cp = content.get(0);
				String titleResult = cp.getTitle();
				
				Assert.assertTrue("title result should be contains title", titleResult.contains(orderProductBo.getTitle()));
			}
		}
		int totalPages = page.getTotalPages();
		Assert.assertTrue("totalPages should > 0 ", totalPages>0);
	}

}
