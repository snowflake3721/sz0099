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

import dml.sz0099.course.app.persist.entity.profession.ProfessionPraise;
import dml.sz0099.course.app.persist.repository.profession.ProfessionPraiseRepository;
import dml.sz0099.course.app.persist.specification.profession.ProfessionPraiseSpecification;

/**
 * ProfessionPraiseDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class ProfessionPraiseDaoImpl extends GenericDaoImpl<ProfessionPraise, Long> implements ProfessionPraiseDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionPraiseDaoImpl.class);

	@Autowired
	private ProfessionPraiseRepository professionPraiseRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = professionPraiseRepository;
	}

	/**
	 * 根据Id查询ProfessionPraise实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionPraise findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		ProfessionPraise professionPraise = professionPraiseRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, professionPraise);
		return professionPraise;
	}

	@Override
	public ProfessionPraise findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionPraise professionPraise = professionPraiseRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, professionPraise);
		return professionPraise;
	}

	/**
	 * 根据IdList查询ProfessionPraise实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionPraise> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<ProfessionPraise> professionPraiseList = professionPraiseRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", professionPraiseList);
		return professionPraiseList;
	}

	/**
	 * 条件查询
	 */
	public Page<ProfessionPraise> findPage(ProfessionPraise professionPraise, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = ProfessionPraiseSpecification.getConditionWithQsl(professionPraise);
		Page<ProfessionPraise> page = professionPraiseRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		ProfessionPraise entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		 ProfessionPraise entity = findByMainIdAndUserId(mainId, userId);
		 return null != entity;
	}

	@Override
	public ProfessionPraise findByMainIdAndUserId(Long mainId, Long userId) {
		return professionPraiseRepository.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<ProfessionPraise> findByMainId(Long mainId, Pageable pageable) {
		return professionPraiseRepository.findByMainId( mainId,  pageable);
	}

}
