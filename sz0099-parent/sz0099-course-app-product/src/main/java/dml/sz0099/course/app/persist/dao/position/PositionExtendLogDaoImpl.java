package dml.sz0099.course.app.persist.dao.position;

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

import dml.sz0099.course.app.persist.entity.position.PositionExtendLog;
import dml.sz0099.course.app.persist.repository.position.PositionExtendLogRepository;
import dml.sz0099.course.app.persist.specification.position.PositionExtendLogSpecification;

/**
 * PositionExtendLogDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class PositionExtendLogDaoImpl extends GenericDaoImpl<PositionExtendLog, Long> implements PositionExtendLogDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PositionExtendLogDaoImpl.class);

	@Autowired
	private PositionExtendLogRepository positionExtendLogRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = positionExtendLogRepository;
	}

	/**
	 * 根据Id查询PositionExtendLog实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public PositionExtendLog findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		PositionExtendLog positionExtendLog = positionExtendLogRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, positionExtendLog);
		return positionExtendLog;
	}

	@Override
	public PositionExtendLog findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		PositionExtendLog positionExtendLog = positionExtendLogRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, positionExtendLog);
		return positionExtendLog;
	}

	/**
	 * 根据IdList查询PositionExtendLog实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<PositionExtendLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<PositionExtendLog> positionExtendLogList = positionExtendLogRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", positionExtendLogList);
		return positionExtendLogList;
	}

	/**
	 * 条件查询
	 */
	public Page<PositionExtendLog> findPage(PositionExtendLog positionExtendLog, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = PositionExtendLogSpecification.getConditionWithQsl(positionExtendLog);
		Page<PositionExtendLog> page = positionExtendLogRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		PositionExtendLog entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}

}
