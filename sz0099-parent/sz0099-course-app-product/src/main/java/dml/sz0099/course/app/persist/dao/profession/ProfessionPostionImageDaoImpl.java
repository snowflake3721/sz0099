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

import dml.sz0099.course.app.persist.entity.profession.ProfessionPositionCover;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPositionImage;
import dml.sz0099.course.app.persist.repository.profession.ProfessionPositionImageRepository;
import dml.sz0099.course.app.persist.specification.profession.ProfessionPositionImageSpecification;

/**
 * @formatter:off
 * ProfessionPositionImageDaoImpl 
 * 数据访问封装 
 * ----------------------------------------------------------------------------------------
 * Requirement 				Author 		Date 		Function 
 * init 					bruceyang 	2017-08-16 	basic init
 * 
 * @formatter:on
 */
@Repository
public class ProfessionPostionImageDaoImpl extends GenericDaoImpl<ProfessionPositionImage, Long> implements ProfessionPositionImageDao, Serializable {

	private static final long serialVersionUID = -8291756986054087547L;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionPostionImageDaoImpl.class);

	@Autowired
	private ProfessionPositionImageRepository professionPositionRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = professionPositionRepository;
	}

	/**
	 * 根据Id查询ProfessionPositionImage实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionPositionImage findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		ProfessionPositionImage professionPosition = professionPositionRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, professionPosition);
		return professionPosition;
	}

	@Override
	public ProfessionPositionImage findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionPositionImage professionPosition = professionPositionRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, professionPosition);
		return professionPosition;
	}

	/**
	 * 根据IdList查询ProfessionPositionImage实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionPositionImage> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<ProfessionPositionImage> professionPositionList = professionPositionRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", professionPositionList);
		return professionPositionList;
	}

	/**
	 * 条件查询
	 */
	public Page<ProfessionPositionImage> findPage(ProfessionPositionImage professionPosition, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = ProfessionPositionImageSpecification.getConditionWithQsl(professionPosition);
		Page<ProfessionPositionImage> page = professionPositionRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		ProfessionPositionImage entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		 ProfessionPositionImage entity = findByMainIdAndUserId(mainId, userId);
		 return null != entity;
	}

	@Override
	public ProfessionPositionImage findByMainIdAndUserId(Long mainId, Long userId) {
		return professionPositionRepository.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<ProfessionPositionImage> findByMainId(Long mainId, Pageable pageable) {
		return professionPositionRepository.findByMainId( mainId,  pageable);
	}
	
	public List<ProfessionPositionImage> findByRefId(Long refId) {
		return professionPositionRepository.findByRefId(refId);
	}
	
	public List<ProfessionPositionImage> findByRefIdList(List<Long> refIdList) {
		List<ProfessionPositionImage> refList = professionPositionRepository.findByRefIdList(refIdList);
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
