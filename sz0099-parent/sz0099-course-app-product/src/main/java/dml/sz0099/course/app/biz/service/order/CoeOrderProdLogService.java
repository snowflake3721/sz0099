package dml.sz0099.course.app.biz.service.order;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.order.CoeOrder;
import dml.sz0099.course.app.persist.entity.order.CoeOrderProdLog;
import dml.sz0099.course.app.persist.entity.product.CoeProduct;

/**
 * CoeOrderProdLogService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeOrderProdLogService extends GenericService<CoeOrderProdLog,Long>{


	/**
	 * 根据Id查询CoeOrderProdLog实体对象
	 * @param id
	 * @return
	 */
	public CoeOrderProdLog findById(Long id);
	
	public CoeOrderProdLog findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeOrderProdLog实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeOrderProdLog> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeOrderProdLog
	 * @return
	 */
	public CoeOrderProdLog persistEntity(CoeOrderProdLog coeOrderProdLog) ;
	
	
	public CoeOrderProdLog mergeEntity(CoeOrderProdLog coeOrderProdLog) ; 
	
	public CoeOrderProdLog saveOrUpdate(CoeOrderProdLog coeOrderProdLog) ;
	
	public Page<CoeOrderProdLog> findPage(CoeOrderProdLog coeOrderProdLog, Pageable pageable) ;
	
	public CoeOrderProdLog saveOrUpdate(CoeProduct coeProduct, CoeOrder coeOrder);
	
}
