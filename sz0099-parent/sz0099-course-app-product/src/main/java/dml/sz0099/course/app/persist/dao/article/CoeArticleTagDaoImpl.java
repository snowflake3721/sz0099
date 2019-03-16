package dml.sz0099.course.app.persist.dao.article;

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

import dml.sz0099.course.app.persist.entity.article.CoeArticleTag;
import dml.sz0099.course.app.persist.repository.article.CoeArticleTagRepository;
import dml.sz0099.course.app.persist.specification.article.CoeArticleTagSpecification;

/**
 * CoeArticleTagDaoImpl
 * 数据访问封装
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Repository
public class CoeArticleTagDaoImpl extends GenericDaoImpl<CoeArticleTag, Long> implements CoeArticleTagDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeArticleTagDaoImpl.class);
	
	@Autowired
	private CoeArticleTagRepository coeArticleTagRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeArticleTagRepository;
	}

	/**
	 * 根据Id查询CoeArticleTag实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeArticleTag findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeArticleTag coeArticleTag = coeArticleTagRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeArticleTag);
		return coeArticleTag;
	}
	
	@Override
	public CoeArticleTag findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeArticleTag coeArticleTag = coeArticleTagRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeArticleTag);
		return coeArticleTag;
	}
	
	/**
	 * 根据IdList查询CoeArticleTag实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeArticleTag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeArticleTag> coeArticleTagList = coeArticleTagRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ",  coeArticleTagList);
		return coeArticleTagList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeArticleTag> findPage(CoeArticleTag coeArticleTag, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeArticleTagSpecification.getConditionWithQsl(coeArticleTag);
		Page<CoeArticleTag> page = coeArticleTagRepository.findAll(condition, pageable);
		return page;
	}
	
	public Page<CoeArticleTag> findPageWithNotself(CoeArticleTag coeArticleTag, Pageable pageable) {
		LOGGER.debug("--- dao.findPageWithNotself ---------  ");
		BooleanExpression condition = CoeArticleTagSpecification.getConditionWithNotself(coeArticleTag);
		Page<CoeArticleTag> page = coeArticleTagRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public CoeArticleTag findByMainIdAndName(CoeArticleTag coeArticleTag) {
		Long professionId = coeArticleTag.getMainId();
		String name = coeArticleTag.getName();
		return coeArticleTagRepository.findByMainIdAndName(professionId,name);
	}
	
	public Long countByMainId(Long professionId) {
		return coeArticleTagRepository.countByMainId(professionId);
	}
	
	public List<CoeArticleTag> findByMainId(Long professionId){
		return coeArticleTagRepository.findByMainId(professionId);
	}
	
	@Override
	public List<CoeArticleTag> findByMainIdList(List<Long> articleIdList) {
		return coeArticleTagRepository.findByMainIdList(articleIdList);
	}

}
