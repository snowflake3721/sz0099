/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.adaptor.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dml.sz0099.course.app.client.resolver.category.CategoryAdaptor;
import dml.sz0099.course.app.client.resolver.category.CategoryDefaultAdaptor;
import dml.sz0099.course.app.client.wrapper.activity.CoeCategActivityWrapper;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.category.CategoryExtend;
import dml.sz0099.course.app.persist.entity.category.CategoryRef;
import dml.sz0099.course.app.persist.entity.activity.CoeCategActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;

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

public class CategoryActivityAdaptor extends CategoryDefaultAdaptor<CoeCategActivity> implements CategoryAdaptor<CoeCategActivity>{

	@Autowired
	private CoeCategActivityWrapper coeCategActivityWrapper;
	
	@Override
	public CoeCategActivity convert(CategoryExtend categoryExtend) {
		return null;
	}

	@Override
	public boolean persist(CoeCategActivity t) {
		return false;
	}

	@Override
	public boolean mergeCategory(CategoryRef ref) {
		
		Long baseId = ref.getBaseId();
		CoeCategActivity coeCategActivity = new CoeCategActivity();
		Category category = ref.getCategory();
		coeCategActivity.setId(ref.getId());
		coeCategActivity.setBaseId(baseId);
		coeCategActivity.setMainId(ref.getMainId());
		coeCategActivity.setCayMainId(category.getMainId());
		coeCategActivity.setCaySubId(category.getSubId());
		coeCategActivity.setCreatedBy(ref.getUserId());
		coeCategActivity.setLastModifiedBy(ref.getUserId());
		coeCategActivity = coeCategActivityWrapper.changeCategory(coeCategActivity);
		
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
		extend.setDomain(CoeActivity.class.getName());
		extend.setModule("activity");
		extend.setProject("ood");
		extend.setVariety("activity");
		extend=super.findExtend(extend);
		super.config(extend);
		
		return extend;
	}


}
