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

import dml.sz0099.course.app.persist.entity.activity.CoeActivityTag;
import dml.sz0099.course.app.persist.repository.activity.CoeActivityTagRepository;
import dml.sz0099.course.app.persist.specification.activity.CoeActivityTagSpecification;

/**
 * CoeActivityTagDaoImpl
 * 数据访问封装
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Repository
public class CoeActivityTagDaoImpl extends GenericDaoImpl<CoeActivityTag, Long> implements CoeActivityTagDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityTagDaoImpl.class);
	
	@Autowired
	private CoeActivityTagRepository coeActivityTagRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeActivityTagRepository;
	}

	/**
	 * 根据Id查询CoeActivityTag实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityTag findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeActivityTag coeActivityTag = coeActivityTagRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeActivityTag);
		return coeActivityTag;
	}
	
	@Override
	public CoeActivityTag findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityTag coeActivityTag = coeActivityTagRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityTag);
		return coeActivityTag;
	}
	
	/**
	 * 根据IdList查询CoeActivityTag实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityTag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeActivityTag> coeActivityTagList = coeActivityTagRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ",  coeActivityTagList);
		return coeActivityTagList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeActivityTag> findPage(CoeActivityTag coeActivityTag, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeActivityTagSpecification.getConditionWithQsl(coeActivityTag);
		Page<CoeActivityTag> page = coeActivityTagRepository.findAll(condition, pageable);
		return page;
	}
	
	public Page<CoeActivityTag> findPageWithNotself(CoeActivityTag coeActivityTag, Pageable pageable) {
		LOGGER.debug("--- dao.findPageWithNotself ---------  ");
		BooleanExpression condition = CoeActivityTagSpecification.getConditionWithNotself(coeActivityTag);
		Page<CoeActivityTag> page = coeActivityTagRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public CoeActivityTag findByMainIdAndName(CoeActivityTag coeActivityTag) {
		Long professionId = coeActivityTag.getMainId();
		String name = coeActivityTag.getName();
		return coeActivityTagRepository.findByMainIdAndName(professionId,name);
	}
	
	public Long countByMainId(Long professionId) {
		return coeActivityTagRepository.countByMainId(professionId);
	}
	
	public List<CoeActivityTag> findByMainId(Long professionId){
		return coeActivityTagRepository.findByMainId(professionId);
	}
	
	@Override
	public List<CoeActivityTag> findByMainIdList(List<Long> activityIdList) {
		return coeActivityTagRepository.findByMainIdList(activityIdList);
	}

}
