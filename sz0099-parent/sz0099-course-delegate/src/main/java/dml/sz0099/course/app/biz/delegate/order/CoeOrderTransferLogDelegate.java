package dml.sz0099.course.app.biz.delegate.order;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.order.CoeOrderTransferLog;

/**
 * CoeOrderTransferLogDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeOrderTransferLogDelegate {

	/**
	 * 根据Id查询CoeOrderTransferLog实体对象
	 * @param id
	 * @return
	 */
	public CoeOrderTransferLog findById(Long id);
	
	public boolean existById(Long id);
	
	public CoeOrderTransferLog findByAid(Long aid);

	/**
	 * 根据IdList查询CoeOrderTransferLog实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeOrderTransferLog> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeOrderTransferLog
	 * @return
	 */
	public CoeOrderTransferLog persistEntity(CoeOrderTransferLog coeOrderTransferLog) ;
	
	public CoeOrderTransferLog mergeEntity(CoeOrderTransferLog coeOrderTransferLog) ; 
	
	public CoeOrderTransferLog saveOrUpdate(CoeOrderTransferLog coeOrderTransferLog) ;
	
	public Page<CoeOrderTransferLog> findPage(CoeOrderTransferLog coeOrderTransferLog, Pageable pageable) ;
	
}
