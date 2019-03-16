package dml.sz0099.course.app.persist.dao.user;

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

import dml.sz0099.course.app.persist.entity.user.CoeUserGradeLog;
import dml.sz0099.course.app.persist.repository.user.CoeUserGradeLogRepository;
import dml.sz0099.course.app.persist.specification.user.CoeUserGradeLogSpecification;

/**
 * CoeUserGradeLogDaoImpl
 * 数据访问封装
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Repository
public class CoeUserGradeLogDaoImpl extends GenericDaoImpl<CoeUserGradeLog, Long> implements CoeUserGradeLogDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserGradeLogDaoImpl.class);
	
	@Autowired
	private CoeUserGradeLogRepository coeUserGradeLogRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeUserGradeLogRepository;
	}

	/**
	 * 根据Id查询CoeUserGradeLog实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeUserGradeLog findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeUserGradeLog coeUserGradeLog = coeUserGradeLogRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeUserGradeLog);
		return coeUserGradeLog;
	}
	
	@Override
	public CoeUserGradeLog findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeUserGradeLog coeUserGradeLog = coeUserGradeLogRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeUserGradeLog);
		return coeUserGradeLog;
	}
	
	/**
	 * 根据IdList查询CoeUserGradeLog实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeUserGradeLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeUserGradeLog> coeUserGradeLogList = coeUserGradeLogRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ",  coeUserGradeLogList);
		return coeUserGradeLogList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeUserGradeLog> findPage(CoeUserGradeLog coeUserGradeLog, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeUserGradeLogSpecification.getConditionWithQsl(coeUserGradeLog);
		Page<CoeUserGradeLog> page = coeUserGradeLogRepository.findAll(condition, pageable);
		return page;
	}

}
