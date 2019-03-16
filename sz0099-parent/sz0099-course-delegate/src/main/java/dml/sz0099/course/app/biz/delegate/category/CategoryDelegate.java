package dml.sz0099.course.app.biz.delegate.category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.category.Category;

/**
 * CoeCategoryDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CategoryDelegate {

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
	
	public boolean existById(Long id);
	
	public List<Category> findMainAndSub(Category category) ;
	
	public Category findByCode(String code);
	
	public Long countByParentId(Category category);
	
	public Category deleteCategory(Category category);
	
	public Long countByExtendId(Long extendId) ;
	
	public Category deleteAllByExtend(Category category) ;
	
	public Category findById(Long id, boolean cascade);
	
	public Category findTop(Long parentId, boolean cascade);
	
	public Map<Long,Category> findMapByIdList(List<Long> idList);
	
	public List<Long> findListByCodeWithChilren(String code);
	
	public List<Long> findListByBaseIdWithChilren(Long baseId);
	
}
