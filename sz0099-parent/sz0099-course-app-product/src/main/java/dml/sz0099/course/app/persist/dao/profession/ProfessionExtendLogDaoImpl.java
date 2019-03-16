package dml.sz0099.course.app.persist.dao.profession;

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

import dml.sz0099.course.app.persist.entity.profession.ProfessionExtendLog;
import dml.sz0099.course.app.persist.repository.profession.ProfessionExtendLogRepository;
import dml.sz0099.course.app.persist.specification.profession.ProfessionExtendLogSpecification;

/**
 * ProfessionExtendLogDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class ProfessionExtendLogDaoImpl extends GenericDaoImpl<ProfessionExtendLog, Long> implements ProfessionExtendLogDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionExtendLogDaoImpl.class);

	@Autowired
	private ProfessionExtendLogRepository professionExtendLogRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = professionExtendLogRepository;
	}

	/**
	 * 根据Id查询ProfessionExtendLog实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionExtendLog findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		ProfessionExtendLog professionExtendLog = professionExtendLogRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, professionExtendLog);
		return professionExtendLog;
	}

	@Override
	public ProfessionExtendLog findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionExtendLog professionExtendLog = professionExtendLogRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, professionExtendLog);
		return professionExtendLog;
	}

	/**
	 * 根据IdList查询ProfessionExtendLog实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionExtendLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<ProfessionExtendLog> professionExtendLogList = professionExtendLogRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", professionExtendLogList);
		return professionExtendLogList;
	}

	/**
	 * 条件查询
	 */
	public Page<ProfessionExtendLog> findPage(ProfessionExtendLog professionExtendLog, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = ProfessionExtendLogSpecification.getConditionWithQsl(professionExtendLog);
		Page<ProfessionExtendLog> page = professionExtendLogRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		ProfessionExtendLog entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}

}
