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

import dml.sz0099.course.app.persist.entity.activity.CoeActivityTime;
import dml.sz0099.course.app.persist.repository.activity.CoeActivityTimeRepository;
import dml.sz0099.course.app.persist.specification.activity.CoeActivityTimeSpecification;

/**
 * CoeActivityTimeDaoImpl
 * 数据访问封装
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Repository
public class CoeActivityTimeDaoImpl extends GenericDaoImpl<CoeActivityTime, Long> implements CoeActivityTimeDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityTimeDaoImpl.class);
	
	@Autowired
	private CoeActivityTimeRepository coeActivityTimeRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeActivityTimeRepository;
	}

	/**
	 * 根据Id查询CoeActivityTime实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityTime findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeActivityTime coeActivityTime = coeActivityTimeRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeActivityTime);
		return coeActivityTime;
	}
	
	@Override
	public CoeActivityTime findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityTime coeActivityTime = coeActivityTimeRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityTime);
		return coeActivityTime;
	}
	
	/**
	 * 根据IdList查询CoeActivityTime实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityTime> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeActivityTime> coeActivityTimeList = coeActivityTimeRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ",  coeActivityTimeList);
		return coeActivityTimeList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeActivityTime> findPage(CoeActivityTime coeActivityTime, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeActivityTimeSpecification.getConditionWithQsl(coeActivityTime);
		Page<CoeActivityTime> page = coeActivityTimeRepository.findAll(condition, pageable);
		return page;
	}
	
	public Page<CoeActivityTime> findPageWithNotself(CoeActivityTime coeActivityTime, Pageable pageable) {
		LOGGER.debug("--- dao.findPageWithNotself ---------  ");
		BooleanExpression condition = CoeActivityTimeSpecification.getConditionWithNotself(coeActivityTime);
		Page<CoeActivityTime> page = coeActivityTimeRepository.findAll(condition, pageable);
		return page;
	}
	
	public Long countByMainId(Long mainId) {
		return coeActivityTimeRepository.countByMainId(mainId);
	}
	
	public CoeActivityTime findByMainId(Long mainId){
		return coeActivityTimeRepository.findByMainId(mainId);
	}
	
	@Override
	public List<CoeActivityTime> findByMainIdList(List<Long> activityIdList) {
		return coeActivityTimeRepository.findByMainIdList(activityIdList);
	}

}
