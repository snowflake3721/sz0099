package dml.sz0099.course.app.persist.dao.category;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.category.CategoryExtendContent;

/**
 * CategoryExtendContentDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CategoryExtendContentDao extends GenericDao<CategoryExtendContent,Long>{

	/**
	 * 根据Id查询CategoryExtendContent实体对象
	 * @param id
	 * @return
	 */
	CategoryExtendContent findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CategoryExtendContent findByAid(Long aid);
	
	/**
	 * 根据IdList查询CategoryExtendContent实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CategoryExtendContent> findByIdList(List<Long> idList);
	
	public Page<CategoryExtendContent> findPage(CategoryExtendContent categoryExtendContent, Pageable pageable);
	
}
