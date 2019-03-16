package dml.sz0099.course.app.biz.service.activity;

import java.util.List;
import java.util.Map;

import org.jit4j.app.persist.entity.order.Order;
import org.jit4j.app.persist.entity.pay.wechat.PayNotifyWechat;
import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityOrder;

/**
 * CoeActivityOrderService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeActivityOrderService extends GenericService<CoeActivityOrder,Long>{


	/**
	 * 根据Id查询CoeActivityOrder实体对象
	 * @param id
	 * @return
	 */
	public CoeActivityOrder findById(Long id);
	public CoeActivityOrder findById(Long id, boolean withJoinItem);
	
	public CoeActivityOrder findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeActivityOrder实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeActivityOrder> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param order
	 * @return
	 */
	public CoeActivityOrder persistEntity(CoeActivityOrder order) ;
	
	
	public CoeActivityOrder mergeEntity(CoeActivityOrder order) ; 
	
	public CoeActivityOrder saveOrUpdate(CoeActivityOrder order) ;
	
	public Page<CoeActivityOrder> findPage(CoeActivityOrder order, Pageable pageable) ;
	
	public CoeActivityOrder findByMainId(CoeActivityOrder order);
	
	public CoeActivityOrder findInitOrder(Long userId, Long mainId);
	public CoeActivityOrder findInitOrder(CoeActivityOrder coeActivityOrder);
	
	public CoeActivityOrder createOrder(CoeActivityOrder order) ;
	
	public CoeActivityOrder createOrder(CoeActivity activity) ;
	
	public CoeActivityOrder confirmOrder(CoeActivityOrder order);
	public CoeActivityOrder cancelOrder(CoeActivityOrder order);
	
	public CoeActivityOrder deleteOrder(CoeActivityOrder order);
	
	public Long countByMainId(Long activityId) ;
	
	public CoeActivityOrder findByMainId(Long activityId);
	
	public Map<Long, CoeActivityOrder>  findMapByMainIdList(List<Long> mainIdList) ;
	
	public Page<CoeActivityOrder> findPageWithNotself(CoeActivityOrder coeActivityOrder, Pageable pageable);
	
	public CoeActivityOrder mergePayTime(CoeActivityOrder coeActivityOrder);
	public CoeActivityOrder mergePayBegin(CoeActivityOrder coeActivityOrder);
	public CoeActivityOrder mergeExeTime(CoeActivityOrder coeActivityOrder);
	public CoeActivityOrder mergeFinishTime(CoeActivityOrder coeActivityOrder);
	
	public CoeActivityOrder mergeCheckout(CoeActivityOrder coeActivityOrder);
	public CoeActivityOrder mergeFailure(CoeActivityOrder coeActivityOrder);
	public CoeActivityOrder mergeWaitRun(CoeActivityOrder coeActivityOrder);
	
	public CoeActivityOrder mergeStatus(Order order);
	
	public CoeActivityOrder mergeStatus(PayNotifyWechat notify);
}
