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

import dml.sz0099.course.app.persist.entity.article.CoeArticlePositionCover;
import dml.sz0099.course.app.persist.repository.article.CoeArticlePositionCoverRepository;
import dml.sz0099.course.app.persist.specification.article.CoeArticlePositionCoverSpecification;

/**
 * CoeArticlePositionCoverDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CoeArticlePostionCoverDaoImpl extends GenericDaoImpl<CoeArticlePositionCover, Long> implements CoeArticlePositionCoverDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeArticlePostionCoverDaoImpl.class);

	@Autowired
	private CoeArticlePositionCoverRepository coeArticlePositionRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeArticlePositionRepository;
	}

	/**
	 * 根据Id查询CoeArticlePositionCover实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeArticlePositionCover findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeArticlePositionCover coeArticlePosition = coeArticlePositionRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeArticlePosition);
		return coeArticlePosition;
	}

	@Override
	public CoeArticlePositionCover findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeArticlePositionCover coeArticlePosition = coeArticlePositionRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeArticlePosition);
		return coeArticlePosition;
	}

	/**
	 * 根据IdList查询CoeArticlePositionCover实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeArticlePositionCover> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeArticlePositionCover> coeArticlePositionList = coeArticlePositionRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", coeArticlePositionList);
		return coeArticlePositionList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeArticlePositionCover> findPage(CoeArticlePositionCover coeArticlePosition, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeArticlePositionCoverSpecification.getConditionWithQsl(coeArticlePosition);
		Page<CoeArticlePositionCover> page = coeArticlePositionRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CoeArticlePositionCover entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		 CoeArticlePositionCover entity = findByMainIdAndUserId(mainId, userId);
		 return null != entity;
	}

	@Override
	public CoeArticlePositionCover findByMainIdAndUserId(Long mainId, Long userId) {
		return coeArticlePositionRepository.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<CoeArticlePositionCover> findByMainId(Long mainId, Pageable pageable) {
		return coeArticlePositionRepository.findByMainId( mainId,  pageable);
	}
	
	@Override
	public void deleteByRefIdList(List<Long> refIdList) {
		coeArticlePositionRepository.deleteByRefIdList(refIdList);
	}
	
	@Override
	public void deleteByRefId(Long refId) {
		coeArticlePositionRepository.deleteByRefId(refId);
	}
	
	public List<CoeArticlePositionCover> findByRefId(Long refId) {
		return coeArticlePositionRepository.findByRefId(refId);
	}
	
	public List<CoeArticlePositionCover> findByRefIdList(List<Long> refIdList) {
		List<CoeArticlePositionCover> refList = coeArticlePositionRepository.findByRefIdList(refIdList);
		return refList;
	}

}
