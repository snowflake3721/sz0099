package dml.sz0099.course.app.biz.service.category;

import java.util.List;
import java.util.Map;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.category.Category;

/**
 * CoeCategoryService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CategoryService extends GenericService<Category,Long>{


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
	 * @param category
	 * @return
	 */
	public Category persistEntity(Category category) ;
	
	
	public Category mergeEntity(Category category) ; 
	
	public Category saveOrUpdate(Category category) ;
	
	public Page<Category> findPage(Category category, Pageable pageable) ;
	
	
	public Category findByCode(String code);
	
	public boolean existByCode(String code);
	
	public Map<Long, List<Category>> findMapByParentIdList(List<Long> parentIdList);
	public Map<String,Category> findMapByParentId(Long parentId, boolean withChildren);
	
	public boolean existById(Long id) ;
	
	public List<Category> findMainAndSub(Category category) ;
	
	public List<Category> getSorted(List<Category> content);
	
	public Long countByParentId(Category category) ;
	
	public Category deleteCategory(Category category);
	
	public Long countByExtendId(Long extendId) ;
	
	public Category deleteAllByExtend(Category category) ;
	
	public Category findByParentIdList(Category parent, List<Long> pidList, boolean cascade);
	
	public Category findById(Long id, boolean cascade);
	
	public Category findTop(Long parentId, boolean cascade);
	
	public Long findTopId(Long parentId);
	
	public Map<Long,Category> findMapByIdList(List<Long> idList);
	
	public List<Long> findListByCodeWithChilren(String code);
	
	public List<Long> findListByBaseIdWithChilren(Long baseId);
	
}
