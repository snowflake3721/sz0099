/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.adaptor.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dml.sz0099.course.app.client.resolver.category.CategoryAdaptor;
import dml.sz0099.course.app.client.resolver.category.CategoryDefaultAdaptor;
import dml.sz0099.course.app.client.wrapper.profession.CategProfessionWrapper;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.category.CategoryExtend;
import dml.sz0099.course.app.persist.entity.category.CategoryRef;
import dml.sz0099.course.app.persist.entity.profession.CategProfession;
import dml.sz0099.course.app.persist.entity.profession.Profession;

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

public class CategoryProfessionAdaptor extends CategoryDefaultAdaptor<CategProfession> implements CategoryAdaptor<CategProfession>{

	@Autowired
	private CategProfessionWrapper categProfessionWrapper;
	
	@Override
	public CategProfession convert(CategoryExtend categoryExtend) {
		return null;
	}

	@Override
	public boolean persist(CategProfession t) {
		return false;
	}

	@Override
	public boolean mergeCategory(CategoryRef ref) {
		
		Long baseId = ref.getBaseId();
		CategProfession categProfession = new CategProfession();
		Category category = ref.getCategory();
		categProfession.setId(ref.getId());
		categProfession.setBaseId(baseId);
		categProfession.setMainId(ref.getMainId());
		categProfession.setCayMainId(category.getMainId());
		categProfession.setCaySubId(category.getSubId());
		categProfession.setCreatedBy(ref.getUserId());
		categProfession.setLastModifiedBy(ref.getUserId());
		categProfession = categProfessionWrapper.changeCategory(categProfession);
		
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
		extend.setDomain(Profession.class.getName());
		extend.setModule("personal");
		extend.setProject("ood");
		extend.setVariety("profession");
		extend=super.findExtend(extend);
		super.config(extend);
		
		return extend;
	}


}
