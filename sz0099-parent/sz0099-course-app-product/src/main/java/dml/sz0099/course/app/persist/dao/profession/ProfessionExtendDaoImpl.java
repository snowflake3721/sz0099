package dml.sz0099.course.app.persist.dao.profession;

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

import dml.sz0099.course.app.persist.entity.profession.ProfessionExtend;
import dml.sz0099.course.app.persist.repository.profession.ProfessionExtendRepository;
import dml.sz0099.course.app.persist.specification.profession.ProfessionExtendSpecification;

/**
 * ProfessionExtendDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class ProfessionExtendDaoImpl extends GenericDaoImpl<ProfessionExtend, Long> implements ProfessionExtendDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionExtendDaoImpl.class);

	@Autowired
	private ProfessionExtendRepository professionExtendRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = professionExtendRepository;
	}

	/**
	 * 根据Id查询ProfessionExtend实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionExtend findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		ProfessionExtend professionExtend = professionExtendRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, professionExtend);
		return professionExtend;
	}

	@Override
	public ProfessionExtend findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionExtend professionExtend = professionExtendRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, professionExtend);
		return professionExtend;
	}

	/**
	 * 根据IdList查询ProfessionExtend实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionExtend> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<ProfessionExtend> professionExtendList = professionExtendRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", professionExtendList);
		return professionExtendList;
	}

	/**
	 * 条件查询
	 */
	public Page<ProfessionExtend> findPage(ProfessionExtend professionExtend, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = ProfessionExtendSpecification.getConditionWithQsl(professionExtend);
		Page<ProfessionExtend> page = professionExtendRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		ProfessionExtend entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	public ProfessionExtend findByPositionId(Long positionId) {
		return professionExtendRepository.findByPositionId(positionId);
	}
	
	public ProfessionExtend findProfessionExtend(ProfessionExtend extend) {
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
			return professionExtendRepository.findProfessionExtend(devId,project,module,variety,position);
		}
		return null;
	}

	@Override
	public Long findPositionIdById(Long id) {
		ProfessionExtend professionExtend = findById(id);
		Long positionId = null;
		if(null != professionExtend) {
			positionId = professionExtend.getPositionId();
		}
		return positionId;
	}
	
	public Long countByUserId(Long userId) {
		return professionExtendRepository.countByUserId(userId);
	}

}
