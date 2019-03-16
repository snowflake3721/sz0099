package dml.sz0099.course.app.persist.dao.user;

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

import dml.sz0099.course.app.persist.entity.user.CoeUserTag;
import dml.sz0099.course.app.persist.repository.user.CoeUserTagRepository;
import dml.sz0099.course.app.persist.specification.user.CoeUserTagSpecification;

/**
 * CoeUserTagDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CoeUserTagDaoImpl extends GenericDaoImpl<CoeUserTag, Long> implements CoeUserTagDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserTagDaoImpl.class);

	@Autowired
	private CoeUserTagRepository coeUserTagRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeUserTagRepository;
	}

	/**
	 * 根据Id查询CoeUserTag实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeUserTag findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeUserTag coeUserTag = coeUserTagRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeUserTag);
		return coeUserTag;
	}

	@Override
	public CoeUserTag findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeUserTag coeUserTag = coeUserTagRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeUserTag);
		return coeUserTag;
	}

	/**
	 * 根据IdList查询CoeUserTag实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeUserTag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeUserTag> coeUserTagList = coeUserTagRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", coeUserTagList);
		return coeUserTagList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeUserTag> findPage(CoeUserTag coeUserTag, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeUserTagSpecification.getConditionWithQsl(coeUserTag);
		Page<CoeUserTag> page = coeUserTagRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CoeUserTag entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}

}
