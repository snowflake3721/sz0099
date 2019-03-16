package dml.sz0099.course.app.biz.delegate.order;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.order.CoeOrderService;
import dml.sz0099.course.app.persist.entity.order.CoeOrder;
import dml.sz0099.course.app.persist.entity.order.bo.CoeOrderBo;
import dml.sz0099.course.app.persist.entity.product.CoeGrade;
import dml.sz0099.course.app.persist.entity.product.CoeProduct;
import dml.sz0099.course.app.persist.entity.user.CoeUserGrade;

/**
 * coeOrderServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeOrderDelegateImpl implements CoeOrderDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderDelegateImpl.class);
	
	@Autowired
	private CoeOrderService coeOrderService;

	/**
	 * 根据Id查询CoeOrder实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeOrder findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeOrder coeOrder = coeOrderService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeOrder);
		return coeOrder;
	}
	
	public CoeOrder findById(Long id, boolean withProduct) {
		CoeOrder coeOrder = coeOrderService.findById(id, withProduct);
		return coeOrder;
	}
	
	@Override
	public CoeOrder findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeOrder coeOrder = coeOrderService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOrder);
		return coeOrder;
	}
	
	/**
	 * 根据IdList查询CoeOrder实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOrder> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeOrder> coeOrderList = coeOrderService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeOrderList);
		return coeOrderList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeOrder persistEntity(CoeOrder coeOrder) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeOrder entity = coeOrderService.persistEntity(coeOrder);
		Long id = coeOrder.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}
	
	public CoeOrder generateOrder(CoeOrder order) {
		CoeOrder entity = coeOrderService.generateOrder(order);
		return entity;
	}

	@Override
	public CoeOrder mergeEntity(CoeOrder coeOrder) {
		Long id = coeOrder.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeOrder entity = coeOrderService.mergeEntity(coeOrder);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}
	@Override
	public CoeOrder mergeEntityForPull(CoeOrder coeOrder) {
		Long id = coeOrder.getId();
		LOGGER.debug("--- delegate.mergeEntityForPull begin, id is {} ---------  ",id);
		CoeOrder entity = coeOrderService.mergeEntityForPull(coeOrder);
		LOGGER.debug("--- delegate.mergeEntityForPull end , id is:{} ---------", id);
		return entity;
	}
	
	public CoeOrder mergeEntityForEmail(CoeOrderBo coeOrder) {
		Long id = coeOrder.getId();
		LOGGER.debug("--- delegate.mergeEntityForEmail begin, id is {} ---------  ",id);
		CoeOrder entity = coeOrderService.mergeEntityForEmail(coeOrder);
		LOGGER.debug("--- delegate.mergeEntityForEmail end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOrder saveOrUpdate(CoeOrder coeOrder) {
		Long id = coeOrder.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeOrder entity = coeOrderService.saveOrUpdate(coeOrder);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}
	
	@Override
	public CoeOrder confirmOrder(CoeOrder order) {
		
		return coeOrderService.confirmOrder(order);
	}

	@Override
	public Page<CoeOrder> findPage(CoeOrder coeOrder, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeOrder> page = coeOrderService.findPage(coeOrder, pageable);
		return page;
	}
	
	@Override
	public Page<CoeOrder> findPageForMyOrderList(CoeOrderBo coeOrder, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeOrder> page = coeOrderService.findPageForMyOrderList(coeOrder, pageable);
		return page;
	}

	@Override
	public Page<CoeOrder> findPageForOwnerOrderList(CoeOrderBo coeOrder, Pageable pageable){
		Long ownerId = coeOrder.getOwnerId();
		Page<CoeOrder> page = null;
		if(null!=ownerId) {
			page = coeOrderService.findPageForOwnerOrderList(coeOrder, pageable);
		}
		return page;
	}
	
	public CoeOrder mergeForInprocess(CoeOrder order) {
		return coeOrderService.mergeForInprocess(order);
	}
	
	public CoeOrder mergeForSent(CoeOrder order) {
		return coeOrderService.mergeForSent(order);
	}
	
	public Integer calPayPrice(CoeUserGrade userGrade, CoeGrade coeGrade, CoeProduct product) {
		return coeOrderService.calPayPrice(userGrade, coeGrade, product);
	}
}
