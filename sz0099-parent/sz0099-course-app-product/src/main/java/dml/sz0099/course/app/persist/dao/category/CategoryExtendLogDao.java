package dml.sz0099.course.app.persist.dao.category;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.category.CategoryExtendLog;

/**
 * CategoryExtendLogDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CategoryExtendLogDao extends GenericDao<CategoryExtendLog,Long>{

	/**
	 * 根据Id查询CategoryExtendLog实体对象
	 * @param id
	 * @return
	 */
	CategoryExtendLog findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CategoryExtendLog findByAid(Long aid);
	
	/**
	 * 根据IdList查询CategoryExtendLog实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CategoryExtendLog> findByIdList(List<Long> idList);
	
	public Page<CategoryExtendLog> findPage(CategoryExtendLog categoryExtendLog, Pageable pageable);
	
}
