package dml.sz0099.course.app.biz.service.article;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.jit4j.core.service.GenericServiceImpl;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.service.tag.CoeTagService;
import dml.sz0099.course.app.persist.dao.article.CoeArticleTagDao;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.CoeArticleTag;
import dml.sz0099.course.app.persist.entity.tag.CoeTag;


/**
 * 
 * @formatter:off
 * description: CoeArticleTagServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeArticleTagServiceImpl extends GenericServiceImpl<CoeArticleTag, Long> implements CoeArticleTagService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeArticleTagServiceImpl.class);

	@Autowired
	private CoeArticleTagDao coeArticleTagDao;
	
	@Autowired
	private CoeTagService coeTagService;
	
	@Autowired
	private CoeArticleService coeArticleService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeArticleTagDao;
	}

	/**
	 * 根据Id查询CoeArticleTag实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeArticleTag findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeArticleTag coeArticleTag = coeArticleTagDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeArticleTag);
		return coeArticleTag;
	}
	
	@Override
	public CoeArticleTag findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeArticleTag coeArticleTag = coeArticleTagDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeArticleTag);
		return coeArticleTag;
	}

	/**
	 * 根据IdList查询CoeArticleTag实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeArticleTag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeArticleTag> coeArticleTagList = coeArticleTagDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeArticleTagList);
		return coeArticleTagList;
	}

	@Transactional
	@Override
	public CoeArticleTag persistEntity(CoeArticleTag coeArticleTag) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeArticleTag entity = save(coeArticleTag);
		Long id = coeArticleTag.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeArticleTag.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeArticleTag mergeEntity(CoeArticleTag coeArticleTag) {
		Long id = coeArticleTag.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeArticleTag entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeArticleTag.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeArticleTag.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeArticleTag saveOrUpdate(CoeArticleTag coeArticleTag) {
		Long id = coeArticleTag.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeArticleTag entity = null;
		if(null != id) {
			entity = mergeEntity(coeArticleTag);
		}else {
			entity = persistEntity(coeArticleTag);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeArticleTag> findPage(CoeArticleTag coeArticleTag, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeArticleTag> page = coeArticleTagDao.findPage(coeArticleTag, pageable);
		return page;
	}
	
	@Transactional
	public CoeArticleTag addTag(CoeArticleTag coeArticleTag) {
		//检测本产品是否已经拥有此标签(在validator校验)
		//增加标签
		CoeTag tag = coeArticleTag.getTag();
		String name = coeArticleTag.getName();
		
		CoeTag entity = coeTagService.findByName(name);
		//1.检测标签表中是否存在，不存在，在标签表中增加记录
		if(null == entity) {
			if(tag==null) {
				tag = new CoeTag();
			}
			tag.setName(name);
			//做拼音转化存储至code TODO
			
			tag.setCreatedBy(coeArticleTag.getCreatedBy());
			tag.setLastModifiedBy(coeArticleTag.getCreatedBy());
			entity=coeTagService.persistEntity(tag);
		}
		
		coeArticleTag.setTagId(entity.getId());
		
		//2.关联标签与产品id
		coeArticleTag=persistEntity(coeArticleTag);
		coeArticleTag.setTag(entity);
		
		return coeArticleTag;
	}
	
	@Transactional
	public CoeArticleTag deleteTag(CoeArticleTag coeArticleTag) {
		Long pTagId = coeArticleTag.getId();
		Long articleId = coeArticleTag.getMainId();
		CoeArticleTag entity = findById(pTagId);
		if (null != entity) {
			Long entityProfessionId = entity.getMainId();
			if (entityProfessionId.equals(articleId)) {
				delete(entity);
				coeArticleTag.setSuccess(CoeArticleTag.SUCCESS_YES);
			}
		}

		return coeArticleTag;
	}
	
	public CoeArticleTag findByMainIdAndName(CoeArticleTag coeArticleTag) {
		Long articleId = coeArticleTag.getMainId();
		String name = coeArticleTag.getName();
		if (null != articleId && StringUtils.isNotBlank(name)) {
			CoeArticleTag tag = coeArticleTagDao.findByMainIdAndName(coeArticleTag);
			return tag;
		}
		return null;
	}
	
	public Long countByMainId(Long articleId) {
		return coeArticleTagDao.countByMainId(articleId);
	}
	
	public List<CoeArticleTag> findByMainId(Long articleId) {
		return coeArticleTagDao.findByMainId(articleId);
	}
	
	public Map<Long, List<CoeArticleTag>> findMapByMainIdList(List<Long> mainIdList) {
		List<CoeArticleTag> content = coeArticleTagDao.findByMainIdList(mainIdList);
		Map<Long, List<CoeArticleTag>> map = new HashMap<>();
		if(null != content && !content.isEmpty()) {
			for(CoeArticleTag c : content) {
				Long mainId = c.getMainId();
				List<CoeArticleTag> entityList = map.get(mainId);
				if(null == entityList) {
					entityList = new ArrayList<>();
					map.put(mainId, entityList);
				}
				entityList.add(c);
			}
		}
		return map;
	}
	
	public Page<CoeArticleTag> findPageWithNotself(CoeArticleTag coeArticleTag, Pageable pageable){
		Page<CoeArticleTag> page = coeArticleTagDao.findPageWithNotself(coeArticleTag, pageable);
		if(null != page && page.getTotalElements()>0) {
			List<CoeArticleTag> content = page.getContent();
			List<Long> mainIdList = new ArrayList<>(content.size());
			Map<Long, CoeArticleTag> tagMap = new HashMap<>(content.size());
			Map<Long, CoeArticleTag> tagRepeatMap = new HashMap<>(content.size());
			for(CoeArticleTag c : content) {
				Long mainId = c.getMainId();
				if(!mainIdList.contains(mainId)) {
					mainIdList.add(mainId);
					tagMap.put(mainId, c);
				}else {
					tagRepeatMap.put(mainId, c);
				}
			}
			List<CoeArticle> mainList = coeArticleService.findPublishByIdList(mainIdList);
			if(null != mainList && !mainList.isEmpty()) {
				for(CoeArticle m : mainList) {
					Long mid = m.getId();
					CoeArticleTag mc = tagMap.get(mid);
					if(null != mc) {
						mc.setArticle(m);
					}
					CoeArticleTag mcr = tagRepeatMap.get(mid);
					if(null != mcr) {
						mcr.setArticle(m);
					}
				}
			}
		}
		return page;
	}

}
