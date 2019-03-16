/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.biz.service.category;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import dml.sz0099.course.app.CourseProdBaseJunit4Test;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.category.CategoryExtend;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-10-03 15:01:22
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-10-03	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class CategoryServiceTest extends CourseProdBaseJunit4Test{

	@Autowired
	private CategoryService categoryService;
	
	@Test
	public void testPersistEntityForTop() {
		Long parentId = CategoryExtend.TOP_PARENTID;
		Long positionId = 235871259891298304l;
		Long mainId = 10000l;
		Long subId = 10000l;	
		Long userId = 233116161842835456l;
		Long extendId= 235871259891298304l;
		String code = "code01"; 
		String name="name01";
		for(int i=0;i<5 ;i++) {
			Category category = getCategory( parentId,  positionId,  mainId, 
					subId,  userId, extendId,  code+i,  name+i);
			categoryService.persistEntity(category);
		}
	}
	
	@Test
	public void testPersistEntityForChild1() {
		Long parentId = 236319286783340544l;
		Long positionId = 235871259891298304l;
		Long mainId = 10000l;
		Long subId = 10000l;	
		Long userId = 233116161842835456l;
		Long extendId= 235871259891298304l;
		String code = "3488childCode01"; 
		String name="3488childName01";
		
		for(int i=0;i<3 ;i++) {
			Category category = getCategory( parentId,  positionId,  mainId, 
					subId,  userId, extendId,  code+i,  name+i);
			categoryService.persistEntity(category);
		}
	}
	@Test
	public void testPersistEntityForChild10() {
		Long parentId = 236319286783340544l;
		Long positionId = 235871259891298304l;
		Long mainId = 10000l;
		Long subId = 10000l;	
		Long userId = 233116161842835456l;
		Long extendId= 235871259891298304l;
		String code = "6960-3488childCode01"; 
		String name="6960-3488childName01";
		
		for(int i=0;i<2 ;i++) {
			Category category = getCategory( parentId,  positionId,  mainId, 
					subId,  userId, extendId,  code+i,  name+i);
			categoryService.persistEntity(category);
		}
	}
	
	
	@Test
	public void testPersistEntityForChild2() {
		Long parentId = 236390422804131840l;
		Long positionId = 235871259891298304l;
		Long mainId = 10000l;
		Long subId = 10000l;	
		Long userId = 233116161842835456l;
		Long extendId= 235871259891298304l;
		String code = "9776childCode01"; 
		String name="9776childName01";
		
		for(int i=0;i<2 ;i++) {
			Category category = getCategory( parentId,  positionId,  mainId, 
					subId,  userId, extendId,  code+i,  name+i);
			categoryService.persistEntity(category);
		}
	}
	
	@Test
	public void testFindByExtend() {
		Long positionId = 235871259891298304l;
		Long mainId = 10000l;
		Long subId = 10000l;	
		Long userId = 233116161842835456l;
		Long extendId= 235871259891298304l;
		Category category = new Category();
		category.setPositionId(positionId);
		category.setMainId(mainId);
		category.setSubId(subId);
		category.setUserId(userId);
		category.setExtendId(extendId);
		List<Category> content = categoryService.findMainAndSub(category);
		List<Category> sorted = categoryService.getSorted(content);
		
		Assert.assertNotNull(sorted.get(0).getChildren());
		System.out.println(sorted);
	}
	
	@Test
	public void testFindById() {
		Long id = 236372158917079040l;
		Category entity = categoryService.findById(id,true);
		Assert.assertNotNull(entity.getChildren());
	}
	
	@Test
	public void testFindTopId() {
		Long pid = 236390422804131840l;
		Long topId = categoryService.findTopId(pid);
		Category parent = categoryService.findById(topId);
		Long t = parent.getParentId();
		System.out.println(t + "  vs [" + CategoryExtend.TOP_PARENTID + "]");
		Assert.assertEquals("should be == ", CategoryExtend.TOP_PARENTID, t);
	}
	
	
	private static Category getCategory(Long parentId, Long positionId, Long mainId, 
			Long subId, Long userId,Long extendId, String code, String name) {
		Category category = new Category();
		category.setParentId(parentId);
		category.setPositionId(positionId);
		category.setMainId(mainId);
		category.setSubId(subId);
		category.setUserId(userId);
		category.setExtendId(extendId);
		category.setCode(code);
		category.setName(name);
		return category;
	}
}
