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

import dml.sz0099.course.app.persist.entity.position.PositionExtendContent;
import dml.sz0099.course.app.persist.repository.position.PositionExtendContentRepository;
import dml.sz0099.course.app.persist.specification.position.PositionExtendContentSpecification;

/**
 * PositionExtendContentDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class PositionExtendContentDaoImpl extends GenericDaoImpl<PositionExtendContent, Long> implements PositionExtendContentDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PositionExtendContentDaoImpl.class);

	@Autowired
	private PositionExtendContentRepository positionExtendContentRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = positionExtendContentRepository;
	}

	/**
	 * 根据Id查询PositionExtendContent实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public PositionExtendContent findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		PositionExtendContent positionExtendContent = positionExtendContentRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, positionExtendContent);
		return positionExtendContent;
	}

	@Override
	public PositionExtendContent findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		PositionExtendContent positionExtendContent = positionExtendContentRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, positionExtendContent);
		return positionExtendContent;
	}

	/**
	 * 根据IdList查询PositionExtendContent实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<PositionExtendContent> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<PositionExtendContent> positionExtendContentList = positionExtendContentRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", positionExtendContentList);
		return positionExtendContentList;
	}

	/**
	 * 条件查询
	 */
	public Page<PositionExtendContent> findPage(PositionExtendContent positionExtendContent, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = PositionExtendContentSpecification.getConditionWithQsl(positionExtendContent);
		Page<PositionExtendContent> page = positionExtendContentRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		PositionExtendContent entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}

}
