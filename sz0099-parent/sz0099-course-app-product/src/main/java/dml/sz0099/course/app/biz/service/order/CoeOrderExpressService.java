package dml.sz0099.course.app.biz.service.order;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.order.CoeOrder;
import dml.sz0099.course.app.persist.entity.order.CoeOrderExpress;

/**
 * CoeOrderExpressService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeOrderExpressService extends GenericService<CoeOrderExpress,Long>{


	/**
	 * 根据Id查询CoeOrderExpress实体对象
	 * @param id
	 * @return
	 */
	public CoeOrderExpress findById(Long id);
	
	public boolean existById(Long id);
	
	public CoeOrderExpress findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeOrderExpress实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeOrderExpress> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeOrderExpress
	 * @return
	 */
	public CoeOrderExpress persistEntity(CoeOrderExpress coeOrderExpress) ;
	
	
	public CoeOrderExpress mergeEntity(CoeOrderExpress coeOrderExpress) ; 
	
	public CoeOrderExpress saveOrUpdate(CoeOrderExpress coeOrderExpress) ;
	
	public Page<CoeOrderExpress> findPage(CoeOrderExpress coeOrderExpress, Pageable pageable) ;
	
	public CoeOrderExpress mergeForSent(CoeOrder order);
	
	public CoeOrderExpress findByOrderId(Long orderId);
	
}
