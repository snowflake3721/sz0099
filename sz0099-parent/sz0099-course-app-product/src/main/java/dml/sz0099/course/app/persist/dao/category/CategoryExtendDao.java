package dml.sz0099.course.app.persist.dao.category;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.category.CategoryExtend;

/**
 * CategoryExtendDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CategoryExtendDao extends GenericDao<CategoryExtend,Long>{

	/**
	 * 根据Id查询CategoryExtend实体对象
	 * @param id
	 * @return
	 */
	CategoryExtend findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CategoryExtend findByAid(Long aid);
	
	/**
	 * 根据IdList查询CategoryExtend实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CategoryExtend> findByIdList(List<Long> idList);
	
	public Page<CategoryExtend> findPage(CategoryExtend categoryExtend, Pageable pageable);
	
	public CategoryExtend findByPositionId(Long positionId) ;
	
	public CategoryExtend findCategoryExtend(CategoryExtend extend);

	public Long findPositionIdById(Long id);
	
	public Long countByUserId(Long userId) ;
	
}
