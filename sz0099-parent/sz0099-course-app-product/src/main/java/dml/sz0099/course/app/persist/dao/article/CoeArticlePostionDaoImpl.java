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

import dml.sz0099.course.app.persist.entity.article.CoeArticlePosition;
import dml.sz0099.course.app.persist.entity.position.PositionRef;
import dml.sz0099.course.app.persist.repository.article.CoeArticlePositionRepository;
import dml.sz0099.course.app.persist.specification.article.CoeArticlePositionSpecification;

/**
 * CoeArticlePositionDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CoeArticlePostionDaoImpl extends GenericDaoImpl<CoeArticlePosition, Long> implements CoeArticlePositionDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeArticlePostionDaoImpl.class);

	@Autowired
	private CoeArticlePositionRepository coeArticlePositionRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeArticlePositionRepository;
	}

	/**
	 * 根据Id查询CoeArticlePosition实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeArticlePosition findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeArticlePosition coeArticlePosition = coeArticlePositionRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeArticlePosition);
		return coeArticlePosition;
	}

	@Override
	public CoeArticlePosition findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeArticlePosition coeArticlePosition = coeArticlePositionRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeArticlePosition);
		return coeArticlePosition;
	}

	/**
	 * 根据IdList查询CoeArticlePosition实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeArticlePosition> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeArticlePosition> coeArticlePositionList = coeArticlePositionRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", coeArticlePositionList);
		return coeArticlePositionList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeArticlePosition> findPage(CoeArticlePosition coeArticlePosition, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeArticlePositionSpecification.getConditionWithQsl(coeArticlePosition);
		Page<CoeArticlePosition> page = coeArticlePositionRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CoeArticlePosition entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		List<CoeArticlePosition> entity = findByMainIdAndUserId(mainId, userId);
		 return null != entity;
	}

	@Override
	public List<CoeArticlePosition> findByMainIdAndUserId(Long mainId, Long userId) {
		return coeArticlePositionRepository.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<CoeArticlePosition> findByMainId(Long mainId, Pageable pageable) {
		return coeArticlePositionRepository.findByMainId( mainId,  pageable);
	}
	
	public CoeArticlePosition findByMainIdAndPosition(Long mainId, Integer position) {
		return coeArticlePositionRepository.findByMainIdAndPosition( mainId,  position);
	}
	
	public List<CoeArticlePosition> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList){
		return coeArticlePositionRepository.findByBaseIdAndMainIdList(baseId,mainIdList);
	}
	public List<CoeArticlePosition> findByBaseId(Long baseId){
		return coeArticlePositionRepository.findByBaseId(baseId);
	}
	public CoeArticlePosition deleteRefByBaseId(CoeArticlePosition positionRef) {
		Long baseId = positionRef.getBaseId();
		coeArticlePositionRepository.deleteRefByBaseId(baseId);
		return positionRef;
	}
	
	public Page<CoeArticlePosition> findPageForPosition(Long ponMainId,Long ponSubId, Long positionId, Integer ponPanel, Pageable pageable){
		return coeArticlePositionRepository.findPageForPosition(ponMainId, ponSubId, positionId, ponPanel, pageable);
	}
	
	public Page<CoeArticlePosition> findByBaseId(Long baseId, Pageable pageable){
		return coeArticlePositionRepository.findByBaseId( baseId,  pageable);
	}
	public Page<CoeArticlePosition> findByBaseIdList(List<Long> baseIdList, Pageable pageable) {
		return coeArticlePositionRepository.findByBaseIdList(baseIdList,  pageable);
	}

}
