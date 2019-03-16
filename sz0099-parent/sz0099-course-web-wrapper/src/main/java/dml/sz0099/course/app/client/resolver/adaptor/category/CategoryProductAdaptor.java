/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.adaptor.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dml.sz0099.course.app.client.resolver.category.CategoryAdaptor;
import dml.sz0099.course.app.client.resolver.category.CategoryDefaultAdaptor;
import dml.sz0099.course.app.client.wrapper.product.CoeCategProdWrapper;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.category.CategoryExtend;
import dml.sz0099.course.app.persist.entity.category.CategoryRef;
import dml.sz0099.course.app.persist.entity.product.CoeCategProd;
import dml.sz0099.course.app.persist.entity.product.CoeProduct;

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

public class CategoryProductAdaptor extends CategoryDefaultAdaptor<CoeCategProd> implements CategoryAdaptor<CoeCategProd>{

	@Autowired
	private CoeCategProdWrapper coeCategProdWrapper;
	
	@Override
	public CoeCategProd convert(CategoryExtend categoryExtend) {
		return null;
	}

	@Override
	public boolean persist(CoeCategProd t) {
		return false;
	}

	@Override
	public boolean mergeCategory(CategoryRef ref) {
		
		Long baseId = ref.getBaseId();
		CoeCategProd coeCategProd = new CoeCategProd();
		Category category = ref.getCategory();
		coeCategProd.setId(ref.getId());
		coeCategProd.setBaseId(baseId);
		coeCategProd.setMainId(ref.getMainId());
		coeCategProd.setCayMainId(category.getMainId());
		coeCategProd.setCaySubId(category.getSubId());
		coeCategProd.setCreatedBy(ref.getUserId());
		coeCategProd.setLastModifiedBy(ref.getUserId());
		coeCategProd = coeCategProdWrapper.changeCategory(coeCategProd);
		
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
		extend.setDomain(CoeProduct.class.getName());
		extend.setModule("product");
		extend.setProject("ood");
		extend.setVariety("product");
		extend=super.findExtend(extend);
		super.config(extend);
		
		return extend;
	}


}
