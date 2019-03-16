package dml.sz0099.course.app.client.wrapper.order;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.order.CoeOrderTransfer;


/**
 * CoeOrderTransferWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeOrderTransferWrapper {

	/**
	 * 根据Id查询CoeOrderTransfer实体对象
	 * @param id
	 * @return
	 */
	public CoeOrderTransfer findById(Long id);
	
	public boolean existById(Long id);
	
	public CoeOrderTransfer findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeOrderTransfer实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeOrderTransfer> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeOrderTransfer
	 * @return
	 */
	public CoeOrderTransfer persistEntity(CoeOrderTransfer coeOrderTransfer) ;
	
	public CoeOrderTransfer mergeEntity(CoeOrderTransfer coeOrderTransfer) ; 
	
	public CoeOrderTransfer saveOrUpdate(CoeOrderTransfer coeOrderTransfer) ;
	
	public Page<CoeOrderTransfer> findPage(CoeOrderTransfer coeOrderTransfer, Pageable pageable) ; 
	
}
