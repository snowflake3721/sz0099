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

import dml.sz0099.course.app.persist.entity.activity.CoeActivityPositionImage;
import dml.sz0099.course.app.persist.repository.activity.CoeActivityPositionImageRepository;
import dml.sz0099.course.app.persist.specification.activity.CoeActivityPositionImageSpecification;

/**
 * CoeActivityPositionImageDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CoeActivityPostionImageDaoImpl extends GenericDaoImpl<CoeActivityPositionImage, Long> implements CoeActivityPositionImageDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityPostionImageDaoImpl.class);

	@Autowired
	private CoeActivityPositionImageRepository coeActivityPositionImageRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeActivityPositionImageRepository;
	}

	/**
	 * 根据Id查询CoeActivityPositionImage实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityPositionImage findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeActivityPositionImage coeActivityPosition = coeActivityPositionImageRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeActivityPosition);
		return coeActivityPosition;
	}

	@Override
	public CoeActivityPositionImage findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityPositionImage coeActivityPosition = coeActivityPositionImageRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityPosition);
		return coeActivityPosition;
	}

	/**
	 * 根据IdList查询CoeActivityPositionImage实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityPositionImage> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeActivityPositionImage> coeActivityPositionList = coeActivityPositionImageRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", coeActivityPositionList);
		return coeActivityPositionList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeActivityPositionImage> findPage(CoeActivityPositionImage coeActivityPosition, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeActivityPositionImageSpecification.getConditionWithQsl(coeActivityPosition);
		Page<CoeActivityPositionImage> page = coeActivityPositionImageRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CoeActivityPositionImage entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		 CoeActivityPositionImage entity = findByMainIdAndUserId(mainId, userId);
		 return null != entity;
	}

	@Override
	public CoeActivityPositionImage findByMainIdAndUserId(Long mainId, Long userId) {
		return coeActivityPositionImageRepository.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<CoeActivityPositionImage> findByMainId(Long mainId, Pageable pageable) {
		return coeActivityPositionImageRepository.findByMainId( mainId,  pageable);
	}
	
	@Override
	public void deleteByRefIdList(List<Long> refIdList) {
		coeActivityPositionImageRepository.deleteByRefIdList(refIdList);
	}
	
	@Override
	public void deleteByRefId(Long refId) {
		coeActivityPositionImageRepository.deleteByRefId(refId);
	}
	
	public List<CoeActivityPositionImage> findByRefId(Long refId) {
		return coeActivityPositionImageRepository.findByRefId(refId);
	}
	
	public List<CoeActivityPositionImage> findByRefIdList(List<Long> refIdList) {
		List<CoeActivityPositionImage> refList = coeActivityPositionImageRepository.findByRefIdList(refIdList);
		return refList;
	}

}
