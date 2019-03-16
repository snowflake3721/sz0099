package dml.sz0099.course.app.client.wrapper.product;

import java.util.List;

import dml.sz0099.course.app.persist.entity.product.CoeProduct;


/**
 * CourseProductWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CourseProductWrapper {

	/**
	 * 根据Id查询CourseProduct实体对象
	 * @param id
	 * @return
	 */
	public CoeProduct findById(Long id);
	
	/**
	 * 根据IdList查询CourseProduct实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeProduct> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param courseProduct
	 * @return
	 */
	public CoeProduct persistEntity(CoeProduct courseProduct) ;
	
}
