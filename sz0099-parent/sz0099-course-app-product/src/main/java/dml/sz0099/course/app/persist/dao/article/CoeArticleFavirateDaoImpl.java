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

import dml.sz0099.course.app.persist.entity.article.CoeArticleFavirate;
import dml.sz0099.course.app.persist.repository.article.CoeArticleFavirateRepository;
import dml.sz0099.course.app.persist.specification.article.CoeArticleFavirateSpecification;

/**
 * CoeArticleFavirateDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CoeArticleFavirateDaoImpl extends GenericDaoImpl<CoeArticleFavirate, Long> implements CoeArticleFavirateDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeArticleFavirateDaoImpl.class);

	@Autowired
	private CoeArticleFavirateRepository coeArticleFavirateRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeArticleFavirateRepository;
	}

	/**
	 * 根据Id查询CoeArticleFavirate实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeArticleFavirate findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeArticleFavirate coeArticleFavirate = coeArticleFavirateRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeArticleFavirate);
		return coeArticleFavirate;
	}

	@Override
	public CoeArticleFavirate findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeArticleFavirate coeArticleFavirate = coeArticleFavirateRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeArticleFavirate);
		return coeArticleFavirate;
	}

	/**
	 * 根据IdList查询CoeArticleFavirate实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeArticleFavirate> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeArticleFavirate> coeArticleFavirateList = coeArticleFavirateRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", coeArticleFavirateList);
		return coeArticleFavirateList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeArticleFavirate> findPage(CoeArticleFavirate coeArticleFavirate, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeArticleFavirateSpecification.getConditionWithQsl(coeArticleFavirate);
		Page<CoeArticleFavirate> page = coeArticleFavirateRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CoeArticleFavirate entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		 CoeArticleFavirate entity = findByMainIdAndUserId(mainId, userId);
		 return null != entity;
	}

	@Override
	public CoeArticleFavirate findByMainIdAndUserId(Long mainId, Long userId) {
		return coeArticleFavirateRepository.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<CoeArticleFavirate> findByMainId(Long mainId, Pageable pageable) {
		return coeArticleFavirateRepository.findByMainId( mainId,  pageable);
	}

}
