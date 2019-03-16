package dml.sz0099.course.app.biz.delegate.order;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.order.CoeOrderProduct;
import dml.sz0099.course.app.persist.entity.order.bo.CoeOrderProductBo;

/**
 * CoeOrderProductDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeOrderProductDelegate {

	/**
	 * 根据Id查询CoeOrderProduct实体对象
	 * @param id
	 * @return
	 */
	public CoeOrderProduct findById(Long id);
	
	public boolean existById(Long id);
	
	public CoeOrderProduct findByAid(Long aid);

	/**
	 * 根据IdList查询CoeOrderProduct实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeOrderProduct> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeOrderProduct
	 * @return
	 */
	public CoeOrderProduct persistEntity(CoeOrderProduct coeOrderProduct) ;
	
	public CoeOrderProduct mergeEntity(CoeOrderProduct coeOrderProduct) ; 
	
	public CoeOrderProduct saveOrUpdate(CoeOrderProduct coeOrderProduct) ;
	
	public Page<CoeOrderProduct> findPage(CoeOrderProduct coeOrderProduct, Pageable pageable) ;
	
	public Page<CoeOrderProduct> findPageForMyOrderList(CoeOrderProductBo orderProductBo, Pageable pageable);
	
}
