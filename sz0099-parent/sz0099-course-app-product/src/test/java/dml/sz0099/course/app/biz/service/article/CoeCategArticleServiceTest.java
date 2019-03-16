/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.biz.service.article;

import org.jit4j.core.persist.page.PageRequest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.CourseProdBaseJunit4Test;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.CoeCategArticle;
import dml.sz0099.course.app.persist.entity.category.Category;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-11-01 12:09:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-11-01	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class CoeCategArticleServiceTest extends CourseProdBaseJunit4Test{

	@Autowired
	private CoeCategArticleService coeCategArticleService;

	@Test
	public void testFindPage() {
		CoeCategArticle coeCategArticle = new CoeCategArticle();
		Category category = new Category();
		category.setCode("article_profession");
		CoeArticle coeArticle = new CoeArticle();
		coeArticle.setTitle("a");
		coeArticle.setUserId(233116161842835456l);
		coeCategArticle.setArticle(coeArticle);
		Pageable pageable = new PageRequest(0, 2);
		coeCategArticle.setCategory(category);
		Page<CoeCategArticle>  page = coeCategArticleService.findPage(coeCategArticle, pageable);
		System.out.println(page);
	}
	
	@Test
	public void testFindPageWithAllCategory() {
		CoeCategArticle coeCategArticle = new CoeCategArticle();
		//Category category = new Category();
		//category.setCode("article_profession");
		CoeArticle coeArticle = new CoeArticle();
		coeArticle.setTitle("a");
		//coeArticle.setUserId(233116161842835456l);
		coeCategArticle.setArticle(coeArticle);
		Pageable pageable = new PageRequest(0, 2);
		//coeCategArticle.setCategory(category);
		Page<CoeCategArticle>  page = coeCategArticleService.findPage(coeCategArticle, pageable);
		System.out.println(page);
	}
}
