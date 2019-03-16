package dml.sz0099.course.app.persist.dao.article;

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

import dml.sz0099.course.app.persist.entity.article.CoeArticlePraise;
import dml.sz0099.course.app.persist.repository.article.CoeArticlePraiseRepository;
import dml.sz0099.course.app.persist.specification.article.CoeArticlePraiseSpecification;

/**
 * CoeArticlePraiseDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CoeArticlePraiseDaoImpl extends GenericDaoImpl<CoeArticlePraise, Long> implements CoeArticlePraiseDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeArticlePraiseDaoImpl.class);

	@Autowired
	private CoeArticlePraiseRepository coeArticlePraiseRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeArticlePraiseRepository;
	}

	/**
	 * 根据Id查询CoeArticlePraise实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeArticlePraise findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeArticlePraise coeArticlePraise = coeArticlePraiseRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeArticlePraise);
		return coeArticlePraise;
	}

	@Override
	public CoeArticlePraise findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeArticlePraise coeArticlePraise = coeArticlePraiseRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeArticlePraise);
		return coeArticlePraise;
	}

	/**
	 * 根据IdList查询CoeArticlePraise实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeArticlePraise> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeArticlePraise> coeArticlePraiseList = coeArticlePraiseRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", coeArticlePraiseList);
		return coeArticlePraiseList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeArticlePraise> findPage(CoeArticlePraise coeArticlePraise, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeArticlePraiseSpecification.getConditionWithQsl(coeArticlePraise);
		Page<CoeArticlePraise> page = coeArticlePraiseRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CoeArticlePraise entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		 CoeArticlePraise entity = findByMainIdAndUserId(mainId, userId);
		 return null != entity;
	}

	@Override
	public CoeArticlePraise findByMainIdAndUserId(Long mainId, Long userId) {
		return coeArticlePraiseRepository.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<CoeArticlePraise> findByMainId(Long mainId, Pageable pageable) {
		return coeArticlePraiseRepository.findByMainId( mainId,  pageable);
	}

}
