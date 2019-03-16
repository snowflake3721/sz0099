package dml.sz0099.course.app.biz.service.order;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.order.CoeOrder;
import dml.sz0099.course.app.persist.entity.order.CoeOrderAsign;

/**
 * CoeOrderAsignService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeOrderAsignService extends GenericService<CoeOrderAsign,Long>{


	/**
	 * 根据Id查询CoeOrderAsign实体对象
	 * @param id
	 * @return
	 */
	public CoeOrderAsign findById(Long id);
	
	public boolean existById(Long id);
	
	public CoeOrderAsign findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeOrderAsign实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeOrderAsign> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeOrderAsign
	 * @return
	 */
	public CoeOrderAsign persistEntity(CoeOrderAsign coeOrderAsign) ;
	
	
	public CoeOrderAsign mergeEntity(CoeOrderAsign coeOrderAsign) ; 
	
	public CoeOrderAsign saveOrUpdate(CoeOrderAsign coeOrderAsign) ;
	
	public Page<CoeOrderAsign> findPage(CoeOrderAsign coeOrderAsign, Pageable pageable) ;
	
	
	public CoeOrderAsign mergeForInprocess(CoeOrder order);
	
	public CoeOrderAsign mergeForSent(CoeOrder order);
	
	public CoeOrderAsign findByOrderId(Long orderId);
	
}
