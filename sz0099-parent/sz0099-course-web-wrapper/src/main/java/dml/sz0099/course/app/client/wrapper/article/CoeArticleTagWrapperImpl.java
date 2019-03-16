package dml.sz0099.course.app.client.wrapper.article;

import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.article.CoeArticleTagDelegate;
import dml.sz0099.course.app.persist.entity.article.CoeArticleTag;
import dml.sz0099.course.app.persist.entity.article.CoeArticleTag;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeArticleTagWrapperImpl,组件封装
 * @author bruce yang at 2018-08-29 21:12:56
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Service
public class CoeArticleTagWrapperImpl implements CoeArticleTagWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeArticleTagWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeArticleTagDelegate coeArticleTagDelegate;
	
	/**
	 * 根据Id查询CoeArticleTag实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeArticleTag findById(Long id) {
		LOGGER.debug("--- CoeArticleTagWrapperImpl.findById begin --------- id is:{} ", id);
		CoeArticleTag coeArticleTag = coeArticleTagDelegate.findById(id);
		LOGGER.debug("--- CoeArticleTagWrapperImpl.findById end --------- id is:{} , result is {} ", id, coeArticleTag);
		return coeArticleTag;
	}
	
	@Override
	public CoeArticleTag findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeArticleTag coeArticleTag = coeArticleTagDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeArticleTag);
		return coeArticleTag;
	}
	
	/**
	 * 根据IdList查询CoeArticleTag实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeArticleTag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- CoeArticleTagWrapperImpl.findByIdList begin ---------  ");
		List<CoeArticleTag> coeArticleTagList = coeArticleTagDelegate.findByIdList(idList);
		LOGGER.debug("--- CoeArticleTagWrapperImpl.findByIdList end ---------  result is {} ",  coeArticleTagList);
		return coeArticleTagList;
	}
	
	@Override
	public CoeArticleTag persistEntity(CoeArticleTag coeArticleTag) {
		LOGGER.debug("--- CoeArticleTagWrapperImpl.persistEntity begin ---------  ");
		CoeArticleTag entity = coeArticleTagDelegate.persistEntity(coeArticleTag);
		Long id = coeArticleTag.getId();
		LOGGER.debug("--- CoeArticleTagWrapperImpl.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeArticleTag mergeEntity(CoeArticleTag coeArticleTag) {
		Long id = coeArticleTag.getId();
		LOGGER.debug("--- CoeArticleTagWrapperImpl.mergeEntity begin, id is {} ---------  ",id);
		CoeArticleTag entity = coeArticleTagDelegate.mergeEntity(coeArticleTag);
		LOGGER.debug("--- CoeArticleTagWrapperImpl.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeArticleTag saveOrUpdate(CoeArticleTag coeArticleTag) {
		Long id = coeArticleTag.getId();
		LOGGER.debug("--- CoeArticleTagWrapperImpl.saveOrUpdate begin, id is {} ---------  ",id);
		CoeArticleTag entity = coeArticleTagDelegate.saveOrUpdate(coeArticleTag);
		LOGGER.debug("--- CoeArticleTagWrapperImpl.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeArticleTag> findPage(CoeArticleTag coeArticleTag, Pageable pageable) {
		LOGGER.debug("--- CoeArticleTagWrapperImpl.findPage ---------  ");
		Page<CoeArticleTag> page = coeArticleTagDelegate.findPage(coeArticleTag, pageable);
		return page;
	}

	@Override
	public CoeArticleTag findByMainIdAndName(CoeArticleTag coeArticleTag) {
		
		return coeArticleTagDelegate.findByMainIdAndName(coeArticleTag);
	}
	
	public CoeArticleTag addTag(CoeArticleTag coeArticleTag) {
		return coeArticleTagDelegate.addTag(coeArticleTag);
	}

	@Override
	public CoeArticleTag deleteTag(CoeArticleTag coeArticleTag) {
		return coeArticleTagDelegate.deleteTag(coeArticleTag);
	}

	@Override
	public Long countByMainId(Long articleId) {
		return coeArticleTagDelegate.countByMainId(articleId);
	}
	
	public List<CoeArticleTag> findByMainId(Long articleId) {
		return coeArticleTagDelegate.findByMainId(articleId);
	}
	
	@Override
	public Map<Long, List<CoeArticleTag>> findMapByMainIdList(List<Long> articleIdList) {
		return coeArticleTagDelegate.findMapByMainIdList(articleIdList);
	}
	
	public Page<CoeArticleTag> findPageWithNotself(CoeArticleTag coeArticleTag, Pageable pageable){
		return coeArticleTagDelegate.findPageWithNotself(coeArticleTag, pageable);
	}
}
