package dml.sz0099.course.app.biz.delegate.order;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.order.CoeOrder;
import dml.sz0099.course.app.persist.entity.order.bo.CoeOrderBo;
import dml.sz0099.course.app.persist.entity.product.CoeGrade;
import dml.sz0099.course.app.persist.entity.product.CoeProduct;
import dml.sz0099.course.app.persist.entity.user.CoeUserGrade;

/**
 * CoeOrderDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeOrderDelegate {

	/**
	 * 根据Id查询CoeOrder实体对象
	 * @param id
	 * @return
	 */
	public CoeOrder findById(Long id);
	public CoeOrder findById(Long id, boolean withProduct) ;
	
	public CoeOrder findByAid(Long aid);

	/**
	 * 根据IdList查询CoeOrder实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeOrder> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeOrder
	 * @return
	 */
	public CoeOrder persistEntity(CoeOrder coeOrder) ;
	
	public CoeOrder generateOrder(CoeOrder order);
	
	public CoeOrder confirmOrder(CoeOrder order);
	
	public CoeOrder mergeEntity(CoeOrder coeOrder) ; 
	
	public CoeOrder mergeEntityForPull(CoeOrder coeOrder);
	public CoeOrder mergeEntityForEmail(CoeOrderBo coeOrder);
	
	public CoeOrder saveOrUpdate(CoeOrder coeOrder) ;
	
	public Page<CoeOrder> findPage(CoeOrder coeOrder, Pageable pageable) ;
	
	public Page<CoeOrder> findPageForMyOrderList(CoeOrderBo coeOrder, Pageable pageable) ;
	
	public Page<CoeOrder> findPageForOwnerOrderList(CoeOrderBo coeOrder, Pageable pageable);
	
	public CoeOrder mergeForInprocess(CoeOrder order);
	
	public CoeOrder mergeForSent(CoeOrder order) ;
	
	public Integer calPayPrice(CoeUserGrade userGrade, CoeGrade coeGrade, CoeProduct product);
	
}
