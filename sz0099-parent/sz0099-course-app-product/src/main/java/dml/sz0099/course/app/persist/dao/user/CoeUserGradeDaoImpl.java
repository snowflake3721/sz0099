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

import dml.sz0099.course.app.persist.entity.user.CoeUserGrade;
import dml.sz0099.course.app.persist.repository.user.CoeUserGradeRepository;
import dml.sz0099.course.app.persist.specification.user.CoeUserGradeSpecification;

/**
 * CoeUserGradeDaoImpl
 * 数据访问封装
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Repository
public class CoeUserGradeDaoImpl extends GenericDaoImpl<CoeUserGrade, Long> implements CoeUserGradeDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserGradeDaoImpl.class);
	
	@Autowired
	private CoeUserGradeRepository coeUserGradeRepository;
	

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeUserGradeRepository;
	}

	/**
	 * 根据Id查询CoeUserGrade实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeUserGrade findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeUserGrade coeUserGrade = coeUserGradeRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeUserGrade);
		return coeUserGrade;
	}
	
	@Override
	public CoeUserGrade findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeUserGrade coeUserGrade = coeUserGradeRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeUserGrade);
		return coeUserGrade;
	}
	
	/**
	 * 根据IdList查询CoeUserGrade实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeUserGrade> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeUserGrade> coeUserGradeList = coeUserGradeRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ",  coeUserGradeList);
		return coeUserGradeList;
	}
	
	public CoeUserGrade findByUserId(Long userId) {
		CoeUserGrade coeUserGrade = coeUserGradeRepository.findByUserId(userId);
		return coeUserGrade;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeUserGrade> findPage(CoeUserGrade coeUserGrade, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeUserGradeSpecification.getConditionWithQsl(coeUserGrade);
		Page<CoeUserGrade> page = coeUserGradeRepository.findAll(condition, pageable);
		return page;
	}

}
