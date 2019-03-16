package dml.sz0099.course.app.client.wrapper.category;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.category.Category;


/**
 * CoeCategoryWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeCategoryWrapper {

	/**
	 * 根据Id查询CoeCategory实体对象
	 * @param id
	 * @return
	 */
	public Category findById(Long id);
	
	public Category findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeCategory实体对象列表
	 * @param idList
	 * @return
	 */
	public List<Category> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeCategory
	 * @return
	 */
	public Category persistEntity(Category coeCategory) ;
	
	public Category mergeEntity(Category coeCategory) ; 
	
	public Category saveOrUpdate(Category coeCategory) ;
	
	public Page<Category> findPage(Category coeCategory, Pageable pageable) ; 
	
}
