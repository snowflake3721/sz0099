package dml.sz0099.course.app.persist.dao.product;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;

import dml.sz0099.course.app.persist.entity.product.CoeProduct;

/**
 * CourseProductDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CourseProductDao extends GenericDao<CoeProduct,Long>{

	/**
	 * 根据Id查询CourseProduct实体对象
	 * @param id
	 * @return
	 */
	CoeProduct findById(Long id);
	
	/**
	 * 根据IdList查询CourseProduct实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeProduct> findByIdList(List<Long> idList);
	
}
