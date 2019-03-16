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

import dml.sz0099.course.app.persist.entity.article.CoeArticlePositionImage;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePositionImage;
import dml.sz0099.course.app.persist.repository.article.CoeArticlePositionImageRepository;
import dml.sz0099.course.app.persist.specification.article.CoeArticlePositionImageSpecification;

/**
 * CoeArticlePositionImageDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CoeArticlePostionImageDaoImpl extends GenericDaoImpl<CoeArticlePositionImage, Long> implements CoeArticlePositionImageDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeArticlePostionImageDaoImpl.class);

	@Autowired
	private CoeArticlePositionImageRepository coeArticlePositionImageRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeArticlePositionImageRepository;
	}

	/**
	 * 根据Id查询CoeArticlePositionImage实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeArticlePositionImage findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeArticlePositionImage coeArticlePosition = coeArticlePositionImageRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeArticlePosition);
		return coeArticlePosition;
	}

	@Override
	public CoeArticlePositionImage findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeArticlePositionImage coeArticlePosition = coeArticlePositionImageRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeArticlePosition);
		return coeArticlePosition;
	}

	/**
	 * 根据IdList查询CoeArticlePositionImage实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeArticlePositionImage> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeArticlePositionImage> coeArticlePositionList = coeArticlePositionImageRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", coeArticlePositionList);
		return coeArticlePositionList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeArticlePositionImage> findPage(CoeArticlePositionImage coeArticlePosition, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeArticlePositionImageSpecification.getConditionWithQsl(coeArticlePosition);
		Page<CoeArticlePositionImage> page = coeArticlePositionImageRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CoeArticlePositionImage entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		 CoeArticlePositionImage entity = findByMainIdAndUserId(mainId, userId);
		 return null != entity;
	}

	@Override
	public CoeArticlePositionImage findByMainIdAndUserId(Long mainId, Long userId) {
		return coeArticlePositionImageRepository.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<CoeArticlePositionImage> findByMainId(Long mainId, Pageable pageable) {
		return coeArticlePositionImageRepository.findByMainId( mainId,  pageable);
	}
	
	@Override
	public void deleteByRefIdList(List<Long> refIdList) {
		coeArticlePositionImageRepository.deleteByRefIdList(refIdList);
	}
	
	@Override
	public void deleteByRefId(Long refId) {
		coeArticlePositionImageRepository.deleteByRefId(refId);
	}
	
	public List<CoeArticlePositionImage> findByRefId(Long refId) {
		return coeArticlePositionImageRepository.findByRefId(refId);
	}
	
	public List<CoeArticlePositionImage> findByRefIdList(List<Long> refIdList) {
		List<CoeArticlePositionImage> refList = coeArticlePositionImageRepository.findByRefIdList(refIdList);
		return refList;
	}

}
