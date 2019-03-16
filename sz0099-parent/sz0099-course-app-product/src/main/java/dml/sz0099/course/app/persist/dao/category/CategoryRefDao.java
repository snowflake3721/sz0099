package dml.sz0099.course.app.persist.dao.category;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.category.CategoryRef;

/**
 * CategoryRefDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CategoryRefDao extends GenericDao<CategoryRef,Long>{

	/**
	 * 根据Id查询CategoryRef实体对象
	 * @param id
	 * @return
	 */
	CategoryRef findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CategoryRef findByAid(Long aid);
	
	/**
	 * 根据IdList查询CategoryRef实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CategoryRef> findByIdList(List<Long> idList);
	
	public Page<CategoryRef> findPage(CategoryRef categoryRef, Pageable pageable);
	
	public void deleteByBaseId(Long baseId) ;
	
	public Long countForBase(CategoryRef categoryRef);
	
	public List<CategoryRef> findByMainId(Long mainId);
	
	public void deleteByMainId(Long mainId);
}
