package dml.sz0099.course.app.persist.dao.category;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.category.Category;

/**
 * CoeCategoryDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CategoryDao extends GenericDao<Category,Long>{

	/**
	 * 根据Id查询CoeCategory实体对象
	 * @param id
	 * @return
	 */
	Category findById(Long id);
	
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	Category findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeCategory实体对象列表
	 * @param idList
	 * @return
	 */
	public List<Category> findByIdList(List<Long> idList);
	
	public Page<Category> findPage(Category category, Pageable pageable);
	
	public List<Category> findByParentId(Long parentId);
	
	public List<Category> findByParentIdList(List<Long> parentIdList);
	
	public Category findByCode(String code);
	
	public boolean existById(Long id);
	
	public List<Category> findMainAndSub(Category category) ;
	
	public Long countByParentId(Category category) ;
	
	public Long countByExtendId(Long extendId) ;
	
	public Category deleteAllByExtend(Category category);
	
	public List<Category> findForMain(Long extendId, Long userId, Long mainId, Long subId);
}
