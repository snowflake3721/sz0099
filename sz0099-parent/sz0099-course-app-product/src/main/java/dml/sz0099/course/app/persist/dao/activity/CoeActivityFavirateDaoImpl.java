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

import dml.sz0099.course.app.persist.entity.activity.CoeActivityFavirate;
import dml.sz0099.course.app.persist.repository.activity.CoeActivityFavirateRepository;
import dml.sz0099.course.app.persist.specification.activity.CoeActivityFavirateSpecification;

/**
 * CoeActivityFavirateDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CoeActivityFavirateDaoImpl extends GenericDaoImpl<CoeActivityFavirate, Long> implements CoeActivityFavirateDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityFavirateDaoImpl.class);

	@Autowired
	private CoeActivityFavirateRepository coeActivityFavirateRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeActivityFavirateRepository;
	}

	/**
	 * 根据Id查询CoeActivityFavirate实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityFavirate findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeActivityFavirate coeActivityFavirate = coeActivityFavirateRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeActivityFavirate);
		return coeActivityFavirate;
	}

	@Override
	public CoeActivityFavirate findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityFavirate coeActivityFavirate = coeActivityFavirateRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityFavirate);
		return coeActivityFavirate;
	}

	/**
	 * 根据IdList查询CoeActivityFavirate实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityFavirate> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeActivityFavirate> coeActivityFavirateList = coeActivityFavirateRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", coeActivityFavirateList);
		return coeActivityFavirateList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeActivityFavirate> findPage(CoeActivityFavirate coeActivityFavirate, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeActivityFavirateSpecification.getConditionWithQsl(coeActivityFavirate);
		Page<CoeActivityFavirate> page = coeActivityFavirateRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CoeActivityFavirate entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		 CoeActivityFavirate entity = findByMainIdAndUserId(mainId, userId);
		 return null != entity;
	}

	@Override
	public CoeActivityFavirate findByMainIdAndUserId(Long mainId, Long userId) {
		return coeActivityFavirateRepository.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<CoeActivityFavirate> findByMainId(Long mainId, Pageable pageable) {
		return coeActivityFavirateRepository.findByMainId( mainId,  pageable);
	}

}
