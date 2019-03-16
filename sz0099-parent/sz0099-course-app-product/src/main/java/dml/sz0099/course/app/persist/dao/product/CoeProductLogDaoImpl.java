package dml.sz0099.course.app.persist.dao.product;

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

import dml.sz0099.course.app.persist.entity.product.CoeProductLog;
import dml.sz0099.course.app.persist.repository.product.CoeProductLogRepository;
import dml.sz0099.course.app.persist.specification.product.CoeProductLogSpecification;

/**
 * CoeProductLogDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CoeProductLogDaoImpl extends GenericDaoImpl<CoeProductLog, Long> implements CoeProductLogDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeProductLogDaoImpl.class);

	@Autowired
	private CoeProductLogRepository coeProductLogRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeProductLogRepository;
	}

	/**
	 * 根据Id查询CoeProductLog实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeProductLog findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeProductLog coeProductLog = coeProductLogRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeProductLog);
		return coeProductLog;
	}

	@Override
	public CoeProductLog findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeProductLog coeProductLog = coeProductLogRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeProductLog);
		return coeProductLog;
	}

	/**
	 * 根据IdList查询CoeProductLog实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeProductLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeProductLog> coeProductLogList = coeProductLogRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", coeProductLogList);
		return coeProductLogList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeProductLog> findPage(CoeProductLog coeProductLog, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeProductLogSpecification.getConditionWithQsl(coeProductLog);
		Page<CoeProductLog> page = coeProductLogRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CoeProductLog entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}

}
