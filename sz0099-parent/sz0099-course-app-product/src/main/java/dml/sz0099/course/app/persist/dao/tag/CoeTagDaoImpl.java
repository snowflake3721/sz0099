package dml.sz0099.course.app.persist.dao.tag;

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

import dml.sz0099.course.app.persist.entity.tag.CoeTag;
import dml.sz0099.course.app.persist.repository.tag.CoeTagRepository;
import dml.sz0099.course.app.persist.specification.tag.CoeTagSpecification;

/**
 * CoeTagDaoImpl
 * 数据访问封装
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Repository
public class CoeTagDaoImpl extends GenericDaoImpl<CoeTag, Long> implements CoeTagDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeTagDaoImpl.class);
	
	@Autowired
	private CoeTagRepository coeTagRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeTagRepository;
	}

	/**
	 * 根据Id查询CoeTag实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeTag findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeTag coeTag = coeTagRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeTag);
		return coeTag;
	}
	
	@Override
	public CoeTag findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeTag coeTag = coeTagRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeTag);
		return coeTag;
	}
	
	/**
	 * 根据IdList查询CoeTag实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeTag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeTag> coeTagList = coeTagRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ",  coeTagList);
		return coeTagList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeTag> findPage(CoeTag coeTag, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeTagSpecification.getConditionWithQsl(coeTag);
		Page<CoeTag> page = coeTagRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public CoeTag findByName(String name) {
		return coeTagRepository.findByName(name);
	}

}
