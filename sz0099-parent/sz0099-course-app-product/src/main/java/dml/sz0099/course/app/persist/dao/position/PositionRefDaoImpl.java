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

import dml.sz0099.course.app.persist.entity.position.PositionRef;
import dml.sz0099.course.app.persist.repository.position.PositionRefRepository;
import dml.sz0099.course.app.persist.specification.position.PositionRefSpecification;

/**
 * <pre>
 * @formatter:off
 *
 * 数据访问封装
 * @author bruce yang at 2018-09-10 19:52:40
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-09-10	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Repository
public class PositionRefDaoImpl extends GenericDaoImpl<PositionRef, Long> implements PositionRefDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PositionRefDaoImpl.class);

	@Autowired
	private PositionRefRepository positionRefRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = positionRefRepository;
	}

	/**
	 * 根据Id查询PositionRef实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public PositionRef findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		PositionRef positionRef = positionRefRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, positionRef);
		return positionRef;
	}

	@Override
	public PositionRef findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		PositionRef positionRef = positionRefRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, positionRef);
		return positionRef;
	}

	/**
	 * 根据IdList查询PositionRef实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<PositionRef> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<PositionRef> positionRefList = positionRefRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", positionRefList);
		return positionRefList;
	}

	/**
	 * 条件查询
	 */
	public Page<PositionRef> findPage(PositionRef positionRef, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = PositionRefSpecification.getConditionWithQsl(positionRef);
		Page<PositionRef> page = positionRefRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		PositionRef entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	public void deleteByBaseId(Long baseId) {
		positionRefRepository.deleteByBaseId(baseId);
	}

	public void deleteByBaseIdList(List<Long> baseIdList) {
		positionRefRepository.deleteByBaseIdList(baseIdList);
	}
	public Long countForBase(PositionRef positionRef) {
		Long baseId = positionRef.getBaseId();
		return positionRefRepository.countByBaseId(baseId);
	}

	@Override
	public List<PositionRef> findByMainId(Long mainId) {
		return positionRefRepository.findByMainId(mainId);
	}
	
	public List<PositionRef> findByBaseId(Long baseId){
		return positionRefRepository.findByBaseId(baseId);
	}
	
	public void deleteByMainId(Long mainId) {
		positionRefRepository.deleteByMainId(mainId);
	}

	@Override
	public Page<PositionRef> findPageByBaseId(Long baseId, Pageable pageable) {
		return positionRefRepository.findPageByBaseId(baseId, pageable);
	}
	
	public List<PositionRef> findByBaseIdList(List<Long> baseIdList) {
		List<PositionRef> entityList = positionRefRepository.findByBaseIdList(baseIdList);
		return entityList;
	}
	
	public List<PositionRef> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList) {
		return positionRefRepository.findByBaseIdAndMainIdList( baseId, mainIdList) ;
	}
	
	public PositionRef findMainIdAndBaseId(PositionRef positionRef) {
		Long baseId = positionRef.getBaseId();
		Long mainId = positionRef.getMainId();
		return positionRefRepository.findMainIdAndBaseId(mainId, baseId);
	}
	
	public PositionRef deleteRefByBaseId(PositionRef positionRef) {
		Long baseId = positionRef.getBaseId();
		positionRefRepository.deleteRefByBaseId(baseId);
		return positionRef;
	}
	
	

}
