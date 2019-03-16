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

import dml.sz0099.course.app.persist.entity.position.PositionCover;
import dml.sz0099.course.app.persist.repository.position.PositionCoverRepository;
import dml.sz0099.course.app.persist.specification.position.PositionCoverSpecification;

/**
 * PositionCoverDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class PositionCoverDaoImpl extends GenericDaoImpl<PositionCover, Long> implements PositionCoverDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PositionCoverDaoImpl.class);

	@Autowired
	private PositionCoverRepository positionCoverRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = positionCoverRepository;
	}

	/**
	 * 根据Id查询PositionCover实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public PositionCover findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		PositionCover positionCover = positionCoverRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, positionCover);
		return positionCover;
	}

	@Override
	public PositionCover findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		PositionCover positionCover = positionCoverRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, positionCover);
		return positionCover;
	}

	/**
	 * 根据IdList查询PositionCover实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<PositionCover> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<PositionCover> positionCoverList = positionCoverRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", positionCoverList);
		return positionCoverList;
	}

	/**
	 * 条件查询
	 */
	public Page<PositionCover> findPage(PositionCover positionCover, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = PositionCoverSpecification.getConditionWithQsl(positionCover);
		Page<PositionCover> page = positionCoverRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		PositionCover entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	public List<PositionCover> findByRefIdList(List<Long> refIdList) {
		List<PositionCover> refList = positionCoverRepository.findByRefIdList(refIdList);
		return refList;
	}
	
	public void deleteByRefIdList(List<Long> refIdList) {
		positionCoverRepository.deleteByRefIdList(refIdList);
	}
	
	@Override
	public void deleteByRefId(Long refId) {
		positionCoverRepository.deleteByRefId(refId);
	}
	
	public List<PositionCover> findByRefId(Long refId) {
		return positionCoverRepository.findByRefId(refId);
	}

}
