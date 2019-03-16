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

import dml.sz0099.course.app.persist.entity.profession.ProfessionFavirate;
import dml.sz0099.course.app.persist.repository.profession.ProfessionFavirateRepository;
import dml.sz0099.course.app.persist.specification.profession.ProfessionFavirateSpecification;

/**
 * ProfessionFavirateDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class ProfessionFavirateDaoImpl extends GenericDaoImpl<ProfessionFavirate, Long> implements ProfessionFavirateDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionFavirateDaoImpl.class);

	@Autowired
	private ProfessionFavirateRepository professionFavirateRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = professionFavirateRepository;
	}

	/**
	 * 根据Id查询ProfessionFavirate实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionFavirate findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		ProfessionFavirate professionFavirate = professionFavirateRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, professionFavirate);
		return professionFavirate;
	}

	@Override
	public ProfessionFavirate findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionFavirate professionFavirate = professionFavirateRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, professionFavirate);
		return professionFavirate;
	}

	/**
	 * 根据IdList查询ProfessionFavirate实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionFavirate> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<ProfessionFavirate> professionFavirateList = professionFavirateRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", professionFavirateList);
		return professionFavirateList;
	}

	/**
	 * 条件查询
	 */
	public Page<ProfessionFavirate> findPage(ProfessionFavirate professionFavirate, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = ProfessionFavirateSpecification.getConditionWithQsl(professionFavirate);
		Page<ProfessionFavirate> page = professionFavirateRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		ProfessionFavirate entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		 ProfessionFavirate entity = findByMainIdAndUserId(mainId, userId);
		 return null != entity;
	}

	@Override
	public ProfessionFavirate findByMainIdAndUserId(Long mainId, Long userId) {
		return professionFavirateRepository.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<ProfessionFavirate> findByMainId(Long mainId, Pageable pageable) {
		return professionFavirateRepository.findByMainId( mainId,  pageable);
	}
	
}
