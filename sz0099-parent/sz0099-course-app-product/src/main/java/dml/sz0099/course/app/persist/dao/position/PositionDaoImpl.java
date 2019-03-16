package dml.sz0099.course.app.persist.dao.position;

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

import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.repository.position.PositionRepository;
import dml.sz0099.course.app.persist.specification.position.PositionSpecification;

/**
 * PositionDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class PositionDaoImpl extends GenericDaoImpl<Position, Long> implements PositionDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PositionDaoImpl.class);

	@Autowired
	private PositionRepository positionRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = positionRepository;
	}

	/**
	 * 根据Id查询Position实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Position findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		Position position = positionRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, position);
		return position;
	}

	@Override
	public Position findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		Position position = positionRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, position);
		return position;
	}

	/**
	 * 根据IdList查询Position实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<Position> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<Position> positionList = positionRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", positionList);
		return positionList;
	}

	/**
	 * 条件查询
	 */
	public Page<Position> findPage(Position position, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = PositionSpecification.getConditionWithQsl(position);
		Page<Position> page = positionRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		Position entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}

	@Override
	public List<Position> findMainIdAndExtendId(Position position) {
		Long mainId = position.getMainId();
		Long extendId = position.getExtendId();
		return positionRepository.findMainIdAndExtendId( mainId,  extendId);
	}
	
	public List<Position> findMainIdAndPositionId(Long mainId, Long positionId){
		
		return positionRepository.findMainIdAndPositionId( mainId,  positionId);
	}
	
	public List<Position> findPosition(Long mainId, Long subId, Long positionId, Integer panel){
		
		return positionRepository.findPosition( mainId, subId, positionId, panel);
	}

}
