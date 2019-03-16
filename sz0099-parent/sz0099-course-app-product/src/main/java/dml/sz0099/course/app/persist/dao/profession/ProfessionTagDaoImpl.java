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

import dml.sz0099.course.app.persist.entity.profession.ProfessionTag;
import dml.sz0099.course.app.persist.repository.profession.ProfessionTagRepository;
import dml.sz0099.course.app.persist.specification.profession.ProfessionTagSpecification;

/**
 * ProfessionTagDaoImpl
 * 数据访问封装
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Repository
public class ProfessionTagDaoImpl extends GenericDaoImpl<ProfessionTag, Long> implements ProfessionTagDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionTagDaoImpl.class);
	
	@Autowired
	private ProfessionTagRepository professionTagRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = professionTagRepository;
	}

	/**
	 * 根据Id查询ProfessionTag实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionTag findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		ProfessionTag professionTag = professionTagRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, professionTag);
		return professionTag;
	}
	
	@Override
	public ProfessionTag findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionTag professionTag = professionTagRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, professionTag);
		return professionTag;
	}
	
	/**
	 * 根据IdList查询ProfessionTag实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionTag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<ProfessionTag> professionTagList = professionTagRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ",  professionTagList);
		return professionTagList;
	}

	/**
	 * 条件查询
	 */
	public Page<ProfessionTag> findPage(ProfessionTag professionTag, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = ProfessionTagSpecification.getConditionWithQsl(professionTag);
		Page<ProfessionTag> page = professionTagRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public ProfessionTag findByMainIdAndName(ProfessionTag professionTag) {
		Long professionId = professionTag.getMainId();
		String name = professionTag.getName();
		return professionTagRepository.findByMainIdAndName(professionId,name);
	}
	
	public Long countByMainId(Long professionId) {
		return professionTagRepository.countByMainId(professionId);
	}
	
	public List<ProfessionTag> findByMainId(Long professionId){
		return professionTagRepository.findByMainId(professionId);
	}
	
	@Override
	public List<ProfessionTag> findByMainIdList(List<Long> mainIdList) {
		return professionTagRepository.findByMainIdList(mainIdList);
	}

}
