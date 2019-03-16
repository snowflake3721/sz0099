/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.biz.service.product;

import java.util.List;
import java.util.Random;

import org.jit4j.core.persist.page.PageRequest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import dml.sz0099.course.app.CourseProdBaseJunit4Test;
import dml.sz0099.course.app.module.define.SZ0099Robot;
import dml.sz0099.course.app.persist.entity.product.CoeGrade;
import dml.sz0099.course.app.persist.entity.product.CoeProduct;

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

public class CoeProductServiceTest extends CourseProdBaseJunit4Test {

	@Autowired
	private CoeProductService coeProductService;

	@Test
	public void testFindAll() {
		List<CoeProduct> all = coeProductService.findAll();

		Assert.assertTrue("empty", all.isEmpty());
	}

	@Test
	public void testPersistEntity() {
		for(int i=0;i<15;i++) {
		CoeProduct coeProduct = createCoeProduct();
		Long id = coeProduct.getId();
		Assert.assertTrue("id should >0", id > 0);
		}
	}

	/**
	 * @return
	 */
	private CoeProduct createCoeProduct() {
		CoeProduct coeProduct = new CoeProduct();
		Random r = new Random();
		int num = r.nextInt();
		coeProduct.setStrategy(CoeProduct.STRATEGY_2_GRADE.getValueInt());
		coeProduct.setDescription("JAVA从入门到精通，学习java不再难，由浅入深，循序渐进学java"+num);
		coeProduct.setMinutes(7200);
		coeProduct.setName("JAVA系列"+num);
		coeProduct.setTitle("Java从入门到精通"+num);
		coeProduct.setPriceCur(2999);
		coeProduct.setPriceOri(79800);
		//coeProduct.setProductNo("AR_100000");
		coeProduct.setGrade(CoeGrade.GRADE_L1);
		coeProduct.setShelved(CoeProduct.SHELVED_YES.getValueInt());
		coeProduct.setLink("http://m.dramala.com/ood/architecture"+num);
		coeProduct.setCreatedBy(SZ0099Robot.ROBOT_SZ0099_MEIMEI.getId());
		coeProduct.setLastModifiedBy(SZ0099Robot.ROBOT_SZ0099_MEIMEI.getId());
		
		//coeProduct.setCategoryId(1l);
		
		coeProductService.persistEntity(coeProduct);
		return coeProduct;
	}

	@Test
	public void testFindByShelved() {
		CoeProduct coeProduct = new CoeProduct();
		coeProduct.setTitle("架构");
		List<CoeProduct> result = coeProductService.findByShelved(coeProduct);
		Assert.assertTrue("result list size should >0", result.size() > 0);
	}

	@Test
	public void testFindShelvedByName() {
		String name = "架构";
		List<CoeProduct> result = coeProductService.findShelvedByName(name);
		Assert.assertTrue("result list size should >0", result.size() > 0);
	}

	@Test
	public void testFindShelvedByTitle() {
		String title = "架构";
		List<CoeProduct> result = coeProductService.findShelvedByTitle(title);
		Assert.assertTrue("result list size should >0", result.size() > 0);

	}
	
	@Test
	public void testMergeProductForLink() {
		CoeProduct coeProduct = new CoeProduct();
		coeProduct.setTitle("架构");
		List<CoeProduct> result =  coeProductService.findByShelved(coeProduct);
		String oriLink = "";
		String mergedLink = "";
		if(null != result) {
			CoeProduct entity = result.get(0);
			Long id = entity.getId();
			oriLink = entity.getLink();
			Random r = new Random();
			entity.setLink("http://m.dramala.com/ood/merge/link/test"+r.nextInt());
			coeProductService.mergeProductForLink(entity);
			CoeProduct merged = coeProductService.findById(id);
			mergedLink = merged.getLink();
		}
		Assert.assertNotEquals("link has changed , not equals", oriLink, mergedLink);
	}
	
	@Test
	public void testFindByShelvedPage() {
		PageRequest pageable = new PageRequest();
		pageable.setSize(2);
		pageable.setPage(0);
		CoeProduct coeProduct = new CoeProduct();
		coeProduct.setTitle("架构");
		Page<CoeProduct> page = coeProductService.findByShelved(coeProduct, pageable);
		
		if(null != page) {
			List<CoeProduct> content = page.getContent();
			if(null != content && !content.isEmpty()) {
				CoeProduct cp = content.get(0);
				String titleResult = cp.getTitle();
				
				Assert.assertTrue("title result should be contains title", titleResult.contains(coeProduct.getTitle()));
			}
		}
		int totalPages = page.getTotalPages();
		Assert.assertTrue("totalPages should > 0 ", totalPages>0);
	}

}
