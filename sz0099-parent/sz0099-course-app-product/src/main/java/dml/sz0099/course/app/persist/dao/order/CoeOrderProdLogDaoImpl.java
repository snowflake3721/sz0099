package dml.sz0099.course.app.persist.dao.order;

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

import dml.sz0099.course.app.persist.entity.order.CoeOrderProdLog;
import dml.sz0099.course.app.persist.repository.order.CoeOrderProdLogRepository;
import dml.sz0099.course.app.persist.specification.order.CoeOrderProdLogSpecification;

/**
 * CoeOrderProdLogDaoImpl
 * 数据访问封装
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Repository
public class CoeOrderProdLogDaoImpl extends GenericDaoImpl<CoeOrderProdLog, Long> implements CoeOrderProdLogDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderProdLogDaoImpl.class);
	
	@Autowired
	private CoeOrderProdLogRepository coeOrderProdLogRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeOrderProdLogRepository;
	}

	/**
	 * 根据Id查询CoeOrderProdLog实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeOrderProdLog findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeOrderProdLog coeOrderProdLog = coeOrderProdLogRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeOrderProdLog);
		return coeOrderProdLog;
	}
	
	@Override
	public CoeOrderProdLog findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeOrderProdLog coeOrderProdLog = coeOrderProdLogRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOrderProdLog);
		return coeOrderProdLog;
	}
	
	/**
	 * 根据IdList查询CoeOrderProdLog实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOrderProdLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeOrderProdLog> coeOrderProdLogList = coeOrderProdLogRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ",  coeOrderProdLogList);
		return coeOrderProdLogList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeOrderProdLog> findPage(CoeOrderProdLog coeOrderProdLog, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeOrderProdLogSpecification.getConditionWithQsl(coeOrderProdLog);
		Page<CoeOrderProdLog> page = coeOrderProdLogRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public CoeOrderProdLog findByOrderId(Long orderId) {
		CoeOrderProdLog entity = coeOrderProdLogRepository.findByOrderId(orderId);
		return entity;
	}

}
