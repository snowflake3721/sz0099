package dml.sz0099.course.app.persist.dao.product;

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

import dml.sz0099.course.app.persist.entity.product.CoeProductTag;
import dml.sz0099.course.app.persist.repository.product.CoeProductTagRepository;
import dml.sz0099.course.app.persist.specification.product.CoeProductTagSpecification;

/**
 * CoeProductTagDaoImpl
 * 数据访问封装
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Repository
public class CoeProductTagDaoImpl extends GenericDaoImpl<CoeProductTag, Long> implements CoeProductTagDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeProductTagDaoImpl.class);
	
	@Autowired
	private CoeProductTagRepository coeProductTagRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeProductTagRepository;
	}

	/**
	 * 根据Id查询CoeProductTag实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeProductTag findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeProductTag coeProductTag = coeProductTagRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeProductTag);
		return coeProductTag;
	}
	
	@Override
	public CoeProductTag findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeProductTag coeProductTag = coeProductTagRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeProductTag);
		return coeProductTag;
	}
	
	/**
	 * 根据IdList查询CoeProductTag实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeProductTag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeProductTag> coeProductTagList = coeProductTagRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ",  coeProductTagList);
		return coeProductTagList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeProductTag> findPage(CoeProductTag coeProductTag, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeProductTagSpecification.getConditionWithQsl(coeProductTag);
		Page<CoeProductTag> page = coeProductTagRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public CoeProductTag findByMainIdAndName(CoeProductTag coeProductTag) {
		Long productId = coeProductTag.getMainId();
		String name = coeProductTag.getName();
		return coeProductTagRepository.findByMainIdAndName(productId,name);
	}
	
	public Long countByMainId(Long productId) {
		return coeProductTagRepository.countByMainId(productId);
	}
	
	public List<CoeProductTag> findByMainId(Long productId){
		return coeProductTagRepository.findByMainId(productId);
	}

	@Override
	public List<CoeProductTag> findByMainIdList(List<Long> productIdList) {
		return coeProductTagRepository.findByMainIdList(productIdList);
	}

}
