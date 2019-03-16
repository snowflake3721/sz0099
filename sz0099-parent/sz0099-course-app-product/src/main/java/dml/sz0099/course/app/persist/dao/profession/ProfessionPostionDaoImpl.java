package dml.sz0099.course.app.persist.dao.profession;

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

import dml.sz0099.course.app.persist.entity.profession.ProfessionPosition;
import dml.sz0099.course.app.persist.repository.profession.ProfessionPositionRepository;
import dml.sz0099.course.app.persist.specification.profession.ProfessionPositionSpecification;

/**
 * @formatter:off
 * <pre>
 * 
 * ProfessionPositionDaoImpl 数据访问封装 
 * ----------------------------------------------------------------------------------------
 * Requirement 		Author 		Date 		Function 
 * init 			bruceyang 	2017-08-16 	basic init
 * </pre>
 * @formatter:on
 */
@Repository
public class ProfessionPostionDaoImpl extends GenericDaoImpl<ProfessionPosition, Long> implements ProfessionPositionDao, Serializable {

	private static final long serialVersionUID = -4567107508518943660L;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionPostionDaoImpl.class);

	@Autowired
	private ProfessionPositionRepository professionPositionRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = professionPositionRepository;
	}

	/**
	 * 根据Id查询ProfessionPosition实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionPosition findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		ProfessionPosition professionPosition = professionPositionRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, professionPosition);
		return professionPosition;
	}

	@Override
	public ProfessionPosition findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionPosition professionPosition = professionPositionRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, professionPosition);
		return professionPosition;
	}

	/**
	 * 根据IdList查询ProfessionPosition实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionPosition> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<ProfessionPosition> professionPositionList = professionPositionRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", professionPositionList);
		return professionPositionList;
	}

	/**
	 * 条件查询
	 */
	public Page<ProfessionPosition> findPage(ProfessionPosition professionPosition, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = ProfessionPositionSpecification.getConditionWithQsl(professionPosition);
		Page<ProfessionPosition> page = professionPositionRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		ProfessionPosition entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		List<ProfessionPosition> entity = findByMainIdAndUserId(mainId, userId);
		 return null != entity;
	}

	@Override
	public List<ProfessionPosition> findByMainIdAndUserId(Long mainId, Long userId) {
		return professionPositionRepository.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<ProfessionPosition> findByMainId(Long mainId, Pageable pageable) {
		return professionPositionRepository.findByMainId( mainId,  pageable);
	}
	
	public ProfessionPosition findByMainIdAndPosition(Long mainId, Integer position) {
		return professionPositionRepository.findByMainIdAndPosition( mainId,  position);
	}
	
	public List<ProfessionPosition> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList){
		return professionPositionRepository.findByBaseIdAndMainIdList(baseId,mainIdList);
	}
	public List<ProfessionPosition> findByBaseId(Long baseId){
		return professionPositionRepository.findByBaseId(baseId);
	}
	public ProfessionPosition deleteRefByBaseId(ProfessionPosition positionRef) {
		Long baseId = positionRef.getBaseId();
		professionPositionRepository.deleteRefByBaseId(baseId);
		return positionRef;
	}

	public Page<ProfessionPosition> findPageForPosition(Long ponMainId,Long ponSubId, Long positionId, Integer ponPanel, Pageable pageable){
		return professionPositionRepository.findPageForPosition(ponMainId, ponSubId, positionId, ponPanel, pageable);
	}
	
	public Page<ProfessionPosition> findByBaseId(Long baseId, Pageable pageable){
		return professionPositionRepository.findByBaseId(baseId, pageable);
	}
	public Page<ProfessionPosition> findByBaseIdList(List<Long> baseIdList, Pageable pageable){
		return professionPositionRepository.findByBaseIdList(baseIdList, pageable);
	}
}
