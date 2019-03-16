package dml.sz0099.course.app.biz.delegate.article;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.article.CoeArticleTagService;
import dml.sz0099.course.app.persist.entity.article.CoeArticleTag;

/**
 * coeArticleTagServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeArticleTagDelegateImpl implements CoeArticleTagDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeArticleTagDelegateImpl.class);
	
	@Autowired
	private CoeArticleTagService coeArticleTagService;

	/**
	 * 根据Id查询CoeArticleTag实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeArticleTag findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeArticleTag coeArticleTag = coeArticleTagService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeArticleTag);
		return coeArticleTag;
	}
	
	@Override
	public CoeArticleTag findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeArticleTag coeArticleTag = coeArticleTagService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeArticleTag);
		return coeArticleTag;
	}
	
	/**
	 * 根据IdList查询CoeArticleTag实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeArticleTag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeArticleTag> coeArticleTagList = coeArticleTagService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeArticleTagList);
		return coeArticleTagList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeArticleTag persistEntity(CoeArticleTag coeArticleTag) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeArticleTag entity = coeArticleTagService.persistEntity(coeArticleTag);
		Long id = coeArticleTag.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeArticleTag mergeEntity(CoeArticleTag coeArticleTag) {
		Long id = coeArticleTag.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeArticleTag entity = coeArticleTagService.mergeEntity(coeArticleTag);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeArticleTag saveOrUpdate(CoeArticleTag coeArticleTag) {
		Long id = coeArticleTag.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeArticleTag entity = coeArticleTagService.saveOrUpdate(coeArticleTag);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeArticleTag> findPage(CoeArticleTag coeArticleTag, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeArticleTag> page = coeArticleTagService.findPage(coeArticleTag, pageable);
		return page;
	}

	@Override
	public CoeArticleTag findByMainIdAndName(CoeArticleTag coeArticleTag) {
		return coeArticleTagService.findByMainIdAndName(coeArticleTag);
	}
	
	public CoeArticleTag addTag(CoeArticleTag coeArticleTag) {
		return coeArticleTagService.addTag(coeArticleTag);
	}
	
	public CoeArticleTag deleteTag(CoeArticleTag coeArticleTag) {
		return coeArticleTagService.deleteTag(coeArticleTag);
	}
	
	public Long countByMainId(Long articleId) {
		return coeArticleTagService.countByMainId(articleId);
	}

	@Override
	public List<CoeArticleTag> findByMainId(Long articleId) {
		return coeArticleTagService.findByMainId(articleId);
	}
	
	public Map<Long, List<CoeArticleTag>> findMapByMainIdList(List<Long> mainIdList) {
		return coeArticleTagService.findMapByMainIdList(mainIdList);
	}
	
	public Page<CoeArticleTag> findPageWithNotself(CoeArticleTag coeArticleTag, Pageable pageable){
		return coeArticleTagService.findPageWithNotself(coeArticleTag, pageable);
	}
}
