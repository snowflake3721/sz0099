package dml.sz0099.course.app.client.wrapper.category;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.category.Category;


/**
 * CategoryWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CategoryWrapper {

	/**
	 * 根据Id查询Category实体对象
	 * @param id
	 * @return
	 */
	public Category findById(Long id);
	
	public boolean existById(Long id);
	
	public Category findByAid(Long aid);
	
	/**
	 * 根据IdList查询Category实体对象列表
	 * @param idList
	 * @return
	 */
	public List<Category> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param category
	 * @return
	 */
	public Category persistEntity(Category category) ;
	
	public Category mergeEntity(Category category) ; 
	
	public Category saveOrUpdate(Category category) ;
	
	public Page<Category> findPage(Category category, Pageable pageable) ; 
	
	public List<Category> findMainAndSub(Category category);
	
	public Category createCategory(Category category);
	
	public Category findByCode(String code);
	
	public Long countByParentId(Category category);
	public Long countByExtendId(Long extendId);
	
	public Category deleteCategory(Category category);
	
	public Category deleteAllByExtend(Category category);
	public Category findById(Long id, boolean cascade);
	
	/**
	 * 根据parentId获取顶级类，包含子级节点
	 * @param id
	 * @param cascade
	 * @return
	 */
	public Category findTop(Long parentId, boolean cascade);
	
	public Map<Long,Category> findMapByIdList(List<Long> idList);
	
	public List<Long> findListByCodeWithChilren(String code);
	public List<Long> findListByBaseIdWithChilren(Long baseId);
	
}
