package dml.sz0099.course.app.biz.delegate.order;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.order.CoeOrderProdLog;

/**
 * CoeOrderProdLogDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeOrderProdLogDelegate {

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
	
}
