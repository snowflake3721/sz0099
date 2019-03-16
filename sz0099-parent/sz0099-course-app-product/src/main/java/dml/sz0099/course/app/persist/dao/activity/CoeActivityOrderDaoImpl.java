package dml.sz0099.course.app.persist.dao.activity;

import java.io.Serializable;
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

import dml.sz0099.course.app.persist.entity.activity.CoeActivityOrder;
import dml.sz0099.course.app.persist.repository.activity.CoeActivityOrderRepository;
import dml.sz0099.course.app.persist.specification.activity.CoeActivityOrderSpecification;

/**
 * CoeActivityOrderDaoImpl
 * 数据访问封装
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Repository
public class CoeActivityOrderDaoImpl extends GenericDaoImpl<CoeActivityOrder, Long> implements CoeActivityOrderDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityOrderDaoImpl.class);
	
	@Autowired
	private CoeActivityOrderRepository coeActivityOrderRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeActivityOrderRepository;
	}

	/**
	 * 根据Id查询CoeActivityOrder实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityOrder findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeActivityOrder coeActivityOrder = coeActivityOrderRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeActivityOrder);
		return coeActivityOrder;
	}
	
	@Override
	public CoeActivityOrder findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityOrder coeActivityOrder = coeActivityOrderRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityOrder);
		return coeActivityOrder;
	}
	
	/**
	 * 根据IdList查询CoeActivityOrder实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityOrder> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeActivityOrder> coeActivityOrderList = coeActivityOrderRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ",  coeActivityOrderList);
		return coeActivityOrderList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeActivityOrder> findPage(CoeActivityOrder coeActivityOrder, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeActivityOrderSpecification.getConditionWithQsl(coeActivityOrder);
		Page<CoeActivityOrder> page = coeActivityOrderRepository.findAll(condition, pageable);
		return page;
	}
	
	public Page<CoeActivityOrder> findPageWithNotself(CoeActivityOrder coeActivityOrder, Pageable pageable) {
		LOGGER.debug("--- dao.findPageWithNotself ---------  ");
		BooleanExpression condition = CoeActivityOrderSpecification.getConditionWithNotself(coeActivityOrder);
		Page<CoeActivityOrder> page = coeActivityOrderRepository.findAll(condition, pageable);
		return page;
	}
	
	public Long countByMainId(Long mainId) {
		return coeActivityOrderRepository.countByMainId(mainId);
	}
	
	public CoeActivityOrder findByMainId(Long mainId){
		return coeActivityOrderRepository.findByMainId(mainId);
	}
	
	@Override
	public List<CoeActivityOrder> findByMainIdList(List<Long> activityIdList) {
		return coeActivityOrderRepository.findByMainIdList(activityIdList);
	}
	
	public CoeActivityOrder findByUserIdAndMainId(Long userId, Long mainId) {
		return coeActivityOrderRepository.findByUserIdAndMainId(userId,mainId);
	}
	
	public CoeActivityOrder findByOutTradeNo(String outTradeNo) {
		return coeActivityOrderRepository.findByOutTradeNo(outTradeNo);
	}

}
