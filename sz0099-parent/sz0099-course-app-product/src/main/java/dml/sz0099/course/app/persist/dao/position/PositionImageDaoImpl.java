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

import dml.sz0099.course.app.persist.entity.position.PositionImage;
import dml.sz0099.course.app.persist.repository.position.PositionImageRepository;
import dml.sz0099.course.app.persist.specification.position.PositionImageSpecification;

/**
 * PositionImageDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class PositionImageDaoImpl extends GenericDaoImpl<PositionImage, Long> implements PositionImageDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PositionImageDaoImpl.class);

	@Autowired
	private PositionImageRepository positionImageRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = positionImageRepository;
	}

	/**
	 * 根据Id查询PositionImage实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public PositionImage findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		PositionImage positionImage = positionImageRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, positionImage);
		return positionImage;
	}

	@Override
	public PositionImage findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		PositionImage positionImage = positionImageRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, positionImage);
		return positionImage;
	}

	/**
	 * 根据IdList查询PositionImage实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<PositionImage> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<PositionImage> positionImageList = positionImageRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", positionImageList);
		return positionImageList;
	}

	/**
	 * 条件查询
	 */
	public Page<PositionImage> findPage(PositionImage positionImage, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = PositionImageSpecification.getConditionWithQsl(positionImage);
		Page<PositionImage> page = positionImageRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		PositionImage entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	public List<PositionImage> findByRefIdList(List<Long> refIdList) {
		List<PositionImage> refList = positionImageRepository.findByRefIdList(refIdList);
		return refList;
	}
	
	public void deleteByRefIdList(List<Long> refIdList) {
		positionImageRepository.deleteByRefIdList(refIdList);
	}
	
	@Override
	public void deleteByRefId(Long refId) {
		positionImageRepository.deleteByRefId(refId);
	}
	
	public List<PositionImage> findByRefId(Long refId) {
		return positionImageRepository.findByRefId(refId);
	}

}
