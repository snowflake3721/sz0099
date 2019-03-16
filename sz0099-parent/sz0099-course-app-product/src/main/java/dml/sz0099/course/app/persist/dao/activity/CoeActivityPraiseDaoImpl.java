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

import dml.sz0099.course.app.persist.entity.activity.CoeActivityPraise;
import dml.sz0099.course.app.persist.repository.activity.CoeActivityPraiseRepository;
import dml.sz0099.course.app.persist.specification.activity.CoeActivityPraiseSpecification;

/**
 * CoeActivityPraiseDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CoeActivityPraiseDaoImpl extends GenericDaoImpl<CoeActivityPraise, Long> implements CoeActivityPraiseDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityPraiseDaoImpl.class);

	@Autowired
	private CoeActivityPraiseRepository coeActivityPraiseRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeActivityPraiseRepository;
	}

	/**
	 * 根据Id查询CoeActivityPraise实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityPraise findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeActivityPraise coeActivityPraise = coeActivityPraiseRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeActivityPraise);
		return coeActivityPraise;
	}

	@Override
	public CoeActivityPraise findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityPraise coeActivityPraise = coeActivityPraiseRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityPraise);
		return coeActivityPraise;
	}

	/**
	 * 根据IdList查询CoeActivityPraise实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityPraise> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeActivityPraise> coeActivityPraiseList = coeActivityPraiseRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", coeActivityPraiseList);
		return coeActivityPraiseList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeActivityPraise> findPage(CoeActivityPraise coeActivityPraise, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeActivityPraiseSpecification.getConditionWithQsl(coeActivityPraise);
		Page<CoeActivityPraise> page = coeActivityPraiseRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CoeActivityPraise entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		 CoeActivityPraise entity = findByMainIdAndUserId(mainId, userId);
		 return null != entity;
	}

	@Override
	public CoeActivityPraise findByMainIdAndUserId(Long mainId, Long userId) {
		return coeActivityPraiseRepository.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<CoeActivityPraise> findByMainId(Long mainId, Pageable pageable) {
		return coeActivityPraiseRepository.findByMainId( mainId,  pageable);
	}

}
