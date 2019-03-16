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

import dml.sz0099.course.app.persist.entity.activity.CoeActivityPosition;
import dml.sz0099.course.app.persist.entity.position.PositionRef;
import dml.sz0099.course.app.persist.repository.activity.CoeActivityPositionRepository;
import dml.sz0099.course.app.persist.specification.activity.CoeActivityPositionSpecification;

/**
 * CoeActivityPositionDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CoeActivityPostionDaoImpl extends GenericDaoImpl<CoeActivityPosition, Long> implements CoeActivityPositionDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityPostionDaoImpl.class);

	@Autowired
	private CoeActivityPositionRepository coeActivityPositionRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeActivityPositionRepository;
	}

	/**
	 * 根据Id查询CoeActivityPosition实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityPosition findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeActivityPosition coeActivityPosition = coeActivityPositionRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeActivityPosition);
		return coeActivityPosition;
	}

	@Override
	public CoeActivityPosition findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityPosition coeActivityPosition = coeActivityPositionRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityPosition);
		return coeActivityPosition;
	}

	/**
	 * 根据IdList查询CoeActivityPosition实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityPosition> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeActivityPosition> coeActivityPositionList = coeActivityPositionRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", coeActivityPositionList);
		return coeActivityPositionList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeActivityPosition> findPage(CoeActivityPosition coeActivityPosition, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeActivityPositionSpecification.getConditionWithQsl(coeActivityPosition);
		Page<CoeActivityPosition> page = coeActivityPositionRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CoeActivityPosition entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		List<CoeActivityPosition> entity = findByMainIdAndUserId(mainId, userId);
		 return null != entity;
	}

	@Override
	public List<CoeActivityPosition> findByMainIdAndUserId(Long mainId, Long userId) {
		return coeActivityPositionRepository.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<CoeActivityPosition> findByMainId(Long mainId, Pageable pageable) {
		return coeActivityPositionRepository.findByMainId( mainId,  pageable);
	}
	
	public CoeActivityPosition findByMainIdAndPosition(Long mainId, Integer position) {
		return coeActivityPositionRepository.findByMainIdAndPosition( mainId,  position);
	}
	
	public List<CoeActivityPosition> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList){
		return coeActivityPositionRepository.findByBaseIdAndMainIdList(baseId,mainIdList);
	}
	public List<CoeActivityPosition> findByBaseId(Long baseId){
		return coeActivityPositionRepository.findByBaseId(baseId);
	}
	public CoeActivityPosition deleteRefByBaseId(CoeActivityPosition positionRef) {
		Long baseId = positionRef.getBaseId();
		coeActivityPositionRepository.deleteRefByBaseId(baseId);
		return positionRef;
	}
	
	public Page<CoeActivityPosition> findPageForPosition(Long ponMainId,Long ponSubId, Long positionId, Integer ponPanel, Pageable pageable){
		return coeActivityPositionRepository.findPageForPosition(ponMainId, ponSubId, positionId, ponPanel, pageable);
	}
	
	public Page<CoeActivityPosition> findByBaseId(Long baseId, Pageable pageable){
		return coeActivityPositionRepository.findByBaseId( baseId,  pageable);
	}
	public Page<CoeActivityPosition> findByBaseIdList(List<Long> baseIdList, Pageable pageable) {
		return coeActivityPositionRepository.findByBaseIdList(baseIdList,  pageable);
	}

}
