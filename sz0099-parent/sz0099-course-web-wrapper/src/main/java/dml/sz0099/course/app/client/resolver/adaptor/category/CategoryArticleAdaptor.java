/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.adaptor.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dml.sz0099.course.app.client.resolver.category.CategoryAdaptor;
import dml.sz0099.course.app.client.resolver.category.CategoryDefaultAdaptor;
import dml.sz0099.course.app.client.wrapper.article.CoeCategArticleWrapper;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.category.CategoryExtend;
import dml.sz0099.course.app.persist.entity.category.CategoryRef;
import dml.sz0099.course.app.persist.entity.article.CoeCategArticle;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-09-23 12:04:45
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-09-23	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class CategoryArticleAdaptor extends CategoryDefaultAdaptor<CoeCategArticle> implements CategoryAdaptor<CoeCategArticle>{

	@Autowired
	private CoeCategArticleWrapper coeCategArticleWrapper;
	
	@Override
	public CoeCategArticle convert(CategoryExtend categoryExtend) {
		return null;
	}

	@Override
	public boolean persist(CoeCategArticle t) {
		return false;
	}

	@Override
	public boolean mergeCategory(CategoryRef ref) {
		
		Long baseId = ref.getBaseId();
		CoeCategArticle coeCategArticle = new CoeCategArticle();
		Category category = ref.getCategory();
		coeCategArticle.setId(ref.getId());
		coeCategArticle.setBaseId(baseId);
		coeCategArticle.setMainId(ref.getMainId());
		coeCategArticle.setCayMainId(category.getMainId());
		coeCategArticle.setCaySubId(category.getSubId());
		coeCategArticle.setCreatedBy(ref.getUserId());
		coeCategArticle.setLastModifiedBy(ref.getUserId());
		coeCategArticle = coeCategArticleWrapper.changeCategory(coeCategArticle);
		
		return true;
	}

	@Override
	public boolean deleteCategory(CategoryRef ref) {
		return false;
	}

	@Override
	public boolean deleteCategoryList(List<CategoryRef> refList) {
		return false;
	}
	
	public CategoryExtend config() {
		
		CategoryExtend extend = new CategoryExtend();
		extend.setPosition("system");
		extend.setDevId("sz0099");
		extend.setDomain(CoeArticle.class.getName());
		extend.setModule("article");
		extend.setProject("ood");
		extend.setVariety("article");
		extend=super.findExtend(extend);
		super.config(extend);
		
		return extend;
	}


}
