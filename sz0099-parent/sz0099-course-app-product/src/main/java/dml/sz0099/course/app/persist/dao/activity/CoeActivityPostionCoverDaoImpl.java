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

import dml.sz0099.course.app.persist.entity.activity.CoeActivityPositionCover;
import dml.sz0099.course.app.persist.repository.activity.CoeActivityPositionCoverRepository;
import dml.sz0099.course.app.persist.specification.activity.CoeActivityPositionCoverSpecification;

/**
 * CoeActivityPositionCoverDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CoeActivityPostionCoverDaoImpl extends GenericDaoImpl<CoeActivityPositionCover, Long> implements CoeActivityPositionCoverDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityPostionCoverDaoImpl.class);

	@Autowired
	private CoeActivityPositionCoverRepository coeActivityPositionRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeActivityPositionRepository;
	}

	/**
	 * 根据Id查询CoeActivityPositionCover实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityPositionCover findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeActivityPositionCover coeActivityPosition = coeActivityPositionRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeActivityPosition);
		return coeActivityPosition;
	}

	@Override
	public CoeActivityPositionCover findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityPositionCover coeActivityPosition = coeActivityPositionRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityPosition);
		return coeActivityPosition;
	}

	/**
	 * 根据IdList查询CoeActivityPositionCover实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityPositionCover> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeActivityPositionCover> coeActivityPositionList = coeActivityPositionRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", coeActivityPositionList);
		return coeActivityPositionList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeActivityPositionCover> findPage(CoeActivityPositionCover coeActivityPosition, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeActivityPositionCoverSpecification.getConditionWithQsl(coeActivityPosition);
		Page<CoeActivityPositionCover> page = coeActivityPositionRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CoeActivityPositionCover entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		 CoeActivityPositionCover entity = findByMainIdAndUserId(mainId, userId);
		 return null != entity;
	}

	@Override
	public CoeActivityPositionCover findByMainIdAndUserId(Long mainId, Long userId) {
		return coeActivityPositionRepository.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<CoeActivityPositionCover> findByMainId(Long mainId, Pageable pageable) {
		return coeActivityPositionRepository.findByMainId( mainId,  pageable);
	}
	
	@Override
	public void deleteByRefIdList(List<Long> refIdList) {
		coeActivityPositionRepository.deleteByRefIdList(refIdList);
	}
	
	@Override
	public void deleteByRefId(Long refId) {
		coeActivityPositionRepository.deleteByRefId(refId);
	}
	
	public List<CoeActivityPositionCover> findByRefId(Long refId) {
		return coeActivityPositionRepository.findByRefId(refId);
	}
	
	public List<CoeActivityPositionCover> findByRefIdList(List<Long> refIdList) {
		List<CoeActivityPositionCover> refList = coeActivityPositionRepository.findByRefIdList(refIdList);
		return refList;
	}

}
