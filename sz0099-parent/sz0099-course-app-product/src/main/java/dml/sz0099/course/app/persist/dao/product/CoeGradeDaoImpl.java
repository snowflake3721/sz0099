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

import dml.sz0099.course.app.persist.entity.product.CoeGrade;
import dml.sz0099.course.app.persist.repository.product.CoeGradeRepository;
import dml.sz0099.course.app.persist.specification.product.CoeGradeSpecification;

/**
 * CoeGradeDaoImpl
 * 数据访问封装
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Repository
public class CoeGradeDaoImpl extends GenericDaoImpl<CoeGrade, Long> implements CoeGradeDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeGradeDaoImpl.class);
	
	@Autowired
	private CoeGradeRepository coeGradeRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeGradeRepository;
	}

	/**
	 * 根据Id查询CoeGrade实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeGrade findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeGrade coeGrade = coeGradeRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeGrade);
		return coeGrade;
	}
	
	@Override
	public CoeGrade findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeGrade coeGrade = coeGradeRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeGrade);
		return coeGrade;
	}
	
	/**
	 * 根据IdList查询CoeGrade实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeGrade> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeGrade> coeGradeList = coeGradeRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ",  coeGradeList);
		return coeGradeList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeGrade> findPage(CoeGrade coeGrade, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeGradeSpecification.getConditionWithQsl(coeGrade);
		Page<CoeGrade> page = coeGradeRepository.findAll(condition, pageable);
		return page;
	}
	
	public CoeGrade findByGrade(Integer grade) {
		CoeGrade entity = coeGradeRepository.findByGrade( grade);
		return entity;
	}

}
