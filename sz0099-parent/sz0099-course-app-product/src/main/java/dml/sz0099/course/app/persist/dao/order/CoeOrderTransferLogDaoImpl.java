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

import dml.sz0099.course.app.persist.entity.order.CoeOrderTransferLog;
import dml.sz0099.course.app.persist.repository.order.CoeOrderTransferLogRepository;
import dml.sz0099.course.app.persist.specification.order.CoeOrderTransferLogSpecification;

/**
 * CoeOrderTransferLogDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CoeOrderTransferLogDaoImpl extends GenericDaoImpl<CoeOrderTransferLog, Long> implements CoeOrderTransferLogDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderTransferLogDaoImpl.class);

	@Autowired
	private CoeOrderTransferLogRepository coeOrderTransferLogRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeOrderTransferLogRepository;
	}

	/**
	 * 根据Id查询CoeOrderTransferLog实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeOrderTransferLog findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeOrderTransferLog coeOrderTransferLog = coeOrderTransferLogRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeOrderTransferLog);
		return coeOrderTransferLog;
	}

	@Override
	public CoeOrderTransferLog findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeOrderTransferLog coeOrderTransferLog = coeOrderTransferLogRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOrderTransferLog);
		return coeOrderTransferLog;
	}

	/**
	 * 根据IdList查询CoeOrderTransferLog实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOrderTransferLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeOrderTransferLog> coeOrderTransferLogList = coeOrderTransferLogRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", coeOrderTransferLogList);
		return coeOrderTransferLogList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeOrderTransferLog> findPage(CoeOrderTransferLog coeOrderTransferLog, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeOrderTransferLogSpecification.getConditionWithQsl(coeOrderTransferLog);
		Page<CoeOrderTransferLog> page = coeOrderTransferLogRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CoeOrderTransferLog entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}

}
