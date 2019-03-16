package dml.sz0099.course.app.persist.dao.position;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.jit4j.core.persist.dao.GenericDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.position.PositionExtend;
import dml.sz0099.course.app.persist.repository.position.PositionExtendRepository;
import dml.sz0099.course.app.persist.specification.position.PositionExtendSpecification;

/**
 * PositionExtendDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class PositionExtendDaoImpl extends GenericDaoImpl<PositionExtend, Long> implements PositionExtendDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PositionExtendDaoImpl.class);

	@Autowired
	private PositionExtendRepository positionExtendRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = positionExtendRepository;
	}

	/**
	 * 根据Id查询PositionExtend实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public PositionExtend findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		PositionExtend positionExtend = positionExtendRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, positionExtend);
		return positionExtend;
	}

	@Override
	public PositionExtend findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		PositionExtend positionExtend = positionExtendRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, positionExtend);
		return positionExtend;
	}

	/**
	 * 根据IdList查询PositionExtend实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<PositionExtend> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<PositionExtend> positionExtendList = positionExtendRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", positionExtendList);
		return positionExtendList;
	}

	/**
	 * 条件查询
	 */
	public Page<PositionExtend> findPage(PositionExtend positionExtend, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = PositionExtendSpecification.getConditionWithQsl(positionExtend);
		Page<PositionExtend> page = positionExtendRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		PositionExtend entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	public PositionExtend findByPositionId(Long positionId) {
		return positionExtendRepository.findByPositionId(positionId);
	}
	
	public PositionExtend findPositionExtend(PositionExtend extend) {
		String devId = extend.getDevId();
		String project = extend.getProject();
		String module = extend.getModule();
		String variety = extend.getVariety();
		String position = extend.getPosition();
		if(StringUtils.isNotBlank(devId)
				&& StringUtils.isNotBlank(project)
				&& StringUtils.isNotBlank(module)
				&& StringUtils.isNotBlank(variety)
				&& StringUtils.isNotBlank(position)
				) {
			return positionExtendRepository.findPositionExtend(devId,project,module,variety,position);
		}
		return null;
	}

	@Override
	public Long findPositionIdById(Long id) {
		PositionExtend positionExtend = findById(id);
		Long positionId = null;
		if(null != positionExtend) {
			positionId = positionExtend.getPositionId();
		}
		return positionId;
	}
	
	public Long countByUserId(Long userId) {
		return positionExtendRepository.countByUserId(userId);
	}

}
