package dml.sz0099.course.app.persist.dao.order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jit4j.core.persist.dao.GenericDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.order.CoeOrder;
import dml.sz0099.course.app.persist.entity.order.bo.CoeOrderBo;
import dml.sz0099.course.app.persist.repository.product.CoeOrderRepository;
import dml.sz0099.course.app.persist.specification.order.CoeOrderSpecification;

/**
 * CoeOrderDaoImpl
 * 数据访问封装
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Repository
public class CoeOrderDaoImpl extends GenericDaoImpl<CoeOrder, Long> implements CoeOrderDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderDaoImpl.class);
	
	@Autowired
	private CoeOrderRepository coeOrderRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeOrderRepository;
	}

	/**
	 * 根据Id查询CoeOrder实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeOrder findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeOrder coeOrder = coeOrderRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeOrder);
		return coeOrder;
	}
	
	@Override
	public CoeOrder findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeOrder coeOrder = coeOrderRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOrder);
		return coeOrder;
	}
	
	/**
	 * 根据IdList查询CoeOrder实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOrder> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeOrder> coeOrderList = coeOrderRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ",  coeOrderList);
		return coeOrderList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeOrder> findPage(CoeOrder coeOrder, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeOrderSpecification.getConditionWithQsl(coeOrder);
		Page<CoeOrder> page = coeOrderRepository.findAll(condition, pageable);
		return page;
	}
	
	/**
	 * 用户订单查询
	 */
	public Page<CoeOrder> findPageForMyOrderList(CoeOrderBo coeOrder, Pageable pageable) {
		LOGGER.debug("--- service.findPageForMyOrderList ---------  ");
		Long userId = coeOrder.getUserId();
		BooleanExpression condition = CoeOrderSpecification.getConditionForMyOrder(coeOrder);
		Page<CoeOrder> page = null;
		if(null!=userId) {
			page = coeOrderRepository.findAll(condition, pageable);
		}
		return page;
	}
	
	/**
	 * 客服订单查询
	 * @param coeOrder
	 * @param pageable
	 * @return
	 */
	public Page<CoeOrder> findPageForOwnerOrderList(CoeOrderBo coeOrder, Pageable pageable) {
		LOGGER.debug("--- dao.findPageForOwnerOrderList ---------  ");
		Long ownerId = coeOrder.getOwnerId();
		BooleanExpression condition = CoeOrderSpecification.getConditionForOwner(coeOrder);
		Page<CoeOrder> page = null;
		if(null!=ownerId) {
			page = coeOrderRepository.findAll(condition, pageable);
		}
		return page;
	}

}
