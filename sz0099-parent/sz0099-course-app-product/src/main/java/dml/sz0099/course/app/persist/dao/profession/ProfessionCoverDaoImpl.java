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
import dml.sz0099.course.app.persist.repository.profession.ProfessionCoverRepository;
import dml.sz0099.course.app.persist.specification.profession.ProfessionCoverSpecification;

/**
 * @formatter:off
 * ProfessionCoverDaoImpl 
 * 数据访问封装 
 * ----------------------------------------------------------------------------------------
 * Requirement 				Author 		Date 		Function 
 * init 					bruceyang 	2017-08-16 	basic init
 * 
 * @formatter:on
 */
@Repository
public class ProfessionCoverDaoImpl extends GenericDaoImpl<ProfessionCover, Long> implements ProfessionCoverDao, Serializable {

	private static final long serialVersionUID = -8291756986054087547L;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionCoverDaoImpl.class);

	@Autowired
	private ProfessionCoverRepository professionRefRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = professionRefRepository;
	}

	/**
	 * 根据Id查询ProfessionCover实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionCover findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		ProfessionCover professionRef = professionRefRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, professionRef);
		return professionRef;
	}

	@Override
	public ProfessionCover findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionCover professionRef = professionRefRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, professionRef);
		return professionRef;
	}

	/**
	 * 根据IdList查询ProfessionCover实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionCover> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<ProfessionCover> professionRefList = professionRefRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", professionRefList);
		return professionRefList;
	}

	/**
	 * 条件查询
	 */
	public Page<ProfessionCover> findPage(ProfessionCover professionRef, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = ProfessionCoverSpecification.getConditionWithQsl(professionRef);
		Page<ProfessionCover> page = professionRefRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		ProfessionCover entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		 ProfessionCover entity = findByMainIdAndUserId(mainId, userId);
		 return null != entity;
	}

	@Override
	public ProfessionCover findByMainIdAndUserId(Long mainId, Long userId) {
		return professionRefRepository.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<ProfessionCover> findByMainId(Long mainId, Pageable pageable) {
		return professionRefRepository.findByMainId( mainId,  pageable);
	}
	
	public List<ProfessionCover> findByRefId(Long refId) {
		return professionRefRepository.findByRefId(refId);
	}
	
	public List<ProfessionCover> findByRefIdList(List<Long> refIdList) {
		List<ProfessionCover> refList = professionRefRepository.findByRefIdList(refIdList);
		return refList;
	}
	
	@Override
	public void deleteByRefIdList(List<Long> refIdList) {
		professionRefRepository.deleteByRefIdList(refIdList);
	}
	
	@Override
	public void deleteByRefId(Long refId) {
		professionRefRepository.deleteByRefId(refId);
	}

}
