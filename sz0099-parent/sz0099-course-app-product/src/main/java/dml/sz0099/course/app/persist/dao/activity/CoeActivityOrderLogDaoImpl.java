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

import dml.sz0099.course.app.persist.entity.activity.CoeActivityOrderLog;
import dml.sz0099.course.app.persist.repository.activity.CoeActivityOrderLogRepository;
import dml.sz0099.course.app.persist.specification.activity.CoeActivityOrderLogSpecification;

/**
 * CoeActivityOrderLogDaoImpl
 * 数据访问封装
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Repository
public class CoeActivityOrderLogDaoImpl extends GenericDaoImpl<CoeActivityOrderLog, Long> implements CoeActivityOrderLogDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityOrderLogDaoImpl.class);
	
	@Autowired
	private CoeActivityOrderLogRepository coeActivityOrderLogRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeActivityOrderLogRepository;
	}

	/**
	 * 根据Id查询CoeActivityOrderLog实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityOrderLog findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeActivityOrderLog coeActivityOrderLog = coeActivityOrderLogRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeActivityOrderLog);
		return coeActivityOrderLog;
	}
	
	@Override
	public CoeActivityOrderLog findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityOrderLog coeActivityOrderLog = coeActivityOrderLogRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityOrderLog);
		return coeActivityOrderLog;
	}
	
	/**
	 * 根据IdList查询CoeActivityOrderLog实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityOrderLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeActivityOrderLog> coeActivityOrderLogList = coeActivityOrderLogRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ",  coeActivityOrderLogList);
		return coeActivityOrderLogList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeActivityOrderLog> findPage(CoeActivityOrderLog coeActivityOrderLog, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeActivityOrderLogSpecification.getConditionWithQsl(coeActivityOrderLog);
		Page<CoeActivityOrderLog> page = coeActivityOrderLogRepository.findAll(condition, pageable);
		return page;
	}
	
	public Long countByMainId(Long mainId) {
		return coeActivityOrderLogRepository.countByMainId(mainId);
	}
	
	public CoeActivityOrderLog findByMainId(Long mainId){
		return coeActivityOrderLogRepository.findByMainId(mainId);
	}
	
	@Override
	public List<CoeActivityOrderLog> findByMainIdList(List<Long> activityIdList) {
		return coeActivityOrderLogRepository.findByMainIdList(activityIdList);
	}
	@Override
	public List<CoeActivityOrderLog> findByOrderIdList(List<Long> orderIdList) {
		return coeActivityOrderLogRepository.findByOrderIdList(orderIdList);
	}

}
