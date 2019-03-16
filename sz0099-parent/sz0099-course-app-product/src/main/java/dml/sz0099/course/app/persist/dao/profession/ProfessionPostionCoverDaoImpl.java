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

import dml.sz0099.course.app.persist.entity.profession.ProfessionCover;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPositionCover;
import dml.sz0099.course.app.persist.repository.profession.ProfessionPositionCoverRepository;
import dml.sz0099.course.app.persist.specification.profession.ProfessionPositionCoverSpecification;

/**
 * @formatter:off
 * ProfessionPositionCoverDaoImpl 
 * 数据访问封装 
 * ----------------------------------------------------------------------------------------
 * Requirement 				Author 		Date 		Function 
 * init 					bruceyang 	2017-08-16 	basic init
 * 
 * @formatter:on
 */
@Repository
public class ProfessionPostionCoverDaoImpl extends GenericDaoImpl<ProfessionPositionCover, Long> implements ProfessionPositionCoverDao, Serializable {

	private static final long serialVersionUID = -8291756986054087547L;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionPostionCoverDaoImpl.class);

	@Autowired
	private ProfessionPositionCoverRepository professionPositionRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = professionPositionRepository;
	}

	/**
	 * 根据Id查询ProfessionPositionCover实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionPositionCover findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		ProfessionPositionCover professionPosition = professionPositionRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, professionPosition);
		return professionPosition;
	}

	@Override
	public ProfessionPositionCover findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionPositionCover professionPosition = professionPositionRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, professionPosition);
		return professionPosition;
	}

	/**
	 * 根据IdList查询ProfessionPositionCover实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionPositionCover> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<ProfessionPositionCover> professionPositionList = professionPositionRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", professionPositionList);
		return professionPositionList;
	}

	/**
	 * 条件查询
	 */
	public Page<ProfessionPositionCover> findPage(ProfessionPositionCover professionPosition, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = ProfessionPositionCoverSpecification.getConditionWithQsl(professionPosition);
		Page<ProfessionPositionCover> page = professionPositionRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		ProfessionPositionCover entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		 ProfessionPositionCover entity = findByMainIdAndUserId(mainId, userId);
		 return null != entity;
	}

	@Override
	public ProfessionPositionCover findByMainIdAndUserId(Long mainId, Long userId) {
		return professionPositionRepository.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<ProfessionPositionCover> findByMainId(Long mainId, Pageable pageable) {
		return professionPositionRepository.findByMainId( mainId,  pageable);
	}
	
	public List<ProfessionPositionCover> findByRefId(Long refId) {
		return professionPositionRepository.findByRefId(refId);
	}
	
	public List<ProfessionPositionCover> findByRefIdList(List<Long> refIdList) {
		List<ProfessionPositionCover> refList = professionPositionRepository.findByRefIdList(refIdList);
		return refList;
	}
	
	@Override
	public void deleteByRefIdList(List<Long> refIdList) {
		professionPositionRepository.deleteByRefIdList(refIdList);
	}
	
	@Override
	public void deleteByRefId(Long refId) {
		professionPositionRepository.deleteByRefId(refId);
	}

}
