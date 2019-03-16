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

import dml.sz0099.course.app.persist.entity.activity.CoeActivityFee;
import dml.sz0099.course.app.persist.repository.activity.CoeActivityFeeRepository;
import dml.sz0099.course.app.persist.specification.activity.CoeActivityFeeSpecification;

/**
 * CoeActivityFeeDaoImpl
 * 数据访问封装
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Repository
public class CoeActivityFeeDaoImpl extends GenericDaoImpl<CoeActivityFee, Long> implements CoeActivityFeeDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityFeeDaoImpl.class);
	
	@Autowired
	private CoeActivityFeeRepository coeActivityFeeRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeActivityFeeRepository;
	}

	/**
	 * 根据Id查询CoeActivityFee实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityFee findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeActivityFee coeActivityFee = coeActivityFeeRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeActivityFee);
		return coeActivityFee;
	}
	
	@Override
	public CoeActivityFee findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityFee coeActivityFee = coeActivityFeeRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityFee);
		return coeActivityFee;
	}
	
	/**
	 * 根据IdList查询CoeActivityFee实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityFee> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeActivityFee> coeActivityFeeList = coeActivityFeeRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ",  coeActivityFeeList);
		return coeActivityFeeList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeActivityFee> findPage(CoeActivityFee coeActivityFee, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeActivityFeeSpecification.getConditionWithQsl(coeActivityFee);
		Page<CoeActivityFee> page = coeActivityFeeRepository.findAll(condition, pageable);
		return page;
	}
	
	public Page<CoeActivityFee> findPageWithNotself(CoeActivityFee coeActivityFee, Pageable pageable) {
		LOGGER.debug("--- dao.findPageWithNotself ---------  ");
		BooleanExpression condition = CoeActivityFeeSpecification.getConditionWithNotself(coeActivityFee);
		Page<CoeActivityFee> page = coeActivityFeeRepository.findAll(condition, pageable);
		return page;
	}
	
	public Long countByMainId(Long mainId) {
		return coeActivityFeeRepository.countByMainId(mainId);
	}
	
	public CoeActivityFee findByMainId(Long mainId){
		return coeActivityFeeRepository.findByMainId(mainId);
	}
	
	@Override
	public List<CoeActivityFee> findByMainIdList(List<Long> activityIdList) {
		return coeActivityFeeRepository.findByMainIdList(activityIdList);
	}

}
