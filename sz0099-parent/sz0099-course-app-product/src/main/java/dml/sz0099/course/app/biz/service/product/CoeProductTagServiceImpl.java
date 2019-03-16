package dml.sz0099.course.app.biz.service.product;

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
import dml.sz0099.course.app.persist.dao.product.CoeProductTagDao;
import dml.sz0099.course.app.persist.entity.product.CoeProductTag;
import dml.sz0099.course.app.persist.entity.tag.CoeTag;


/**
 * 
 * @formatter:off
 * description: CoeProductTagServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeProductTagServiceImpl extends GenericServiceImpl<CoeProductTag, Long> implements CoeProductTagService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeProductTagServiceImpl.class);

	@Autowired
	private CoeProductTagDao coeProductTagDao;
	
	@Autowired
	private CoeTagService coeTagService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeProductTagDao;
	}

	/**
	 * 根据Id查询CoeProductTag实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeProductTag findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeProductTag coeProductTag = coeProductTagDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeProductTag);
		return coeProductTag;
	}
	
	@Override
	public CoeProductTag findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeProductTag coeProductTag = coeProductTagDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeProductTag);
		return coeProductTag;
	}

	/**
	 * 根据IdList查询CoeProductTag实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeProductTag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeProductTag> coeProductTagList = coeProductTagDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeProductTagList);
		return coeProductTagList;
	}

	@Transactional
	@Override
	public CoeProductTag persistEntity(CoeProductTag coeProductTag) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeProductTag entity = save(coeProductTag);
		Long id = coeProductTag.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeProductTag.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeProductTag mergeEntity(CoeProductTag coeProductTag) {
		Long id = coeProductTag.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeProductTag entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeProductTag.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeProductTag.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeProductTag saveOrUpdate(CoeProductTag coeProductTag) {
		Long id = coeProductTag.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeProductTag entity = null;
		if(null != id) {
			entity = mergeEntity(coeProductTag);
		}else {
			entity = persistEntity(coeProductTag);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeProductTag> findPage(CoeProductTag coeProductTag, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeProductTag> page = coeProductTagDao.findPage(coeProductTag, pageable);
		return page;
	}
	
	@Transactional
	public CoeProductTag addTag(CoeProductTag coeProductTag) {
		//检测本产品是否已经拥有此标签(在validator校验)
		//增加标签
		CoeTag tag = coeProductTag.getTag();
		String name = coeProductTag.getName();
		
		CoeTag entity = coeTagService.findByName(name);
		//1.检测标签表中是否存在，不存在，在标签表中增加记录
		if(null == entity) {
			if(tag==null) {
				tag = new CoeTag();
			}
			tag.setName(name);
			//做拼音转化存储至code TODO
			
			tag.setCreatedBy(coeProductTag.getCreatedBy());
			tag.setLastModifiedBy(coeProductTag.getCreatedBy());
			entity=coeTagService.persistEntity(tag);
		}
		
		coeProductTag.setTagId(entity.getId());
		
		//2.关联标签与产品id
		coeProductTag=persistEntity(coeProductTag);
		coeProductTag.setTag(entity);
		
		return coeProductTag;
	}
	
	@Transactional
	public CoeProductTag deleteTag(CoeProductTag coeProductTag) {
		Long pTagId = coeProductTag.getId();
		Long productId = coeProductTag.getMainId();
		CoeProductTag entity = findById(pTagId);
		if (null != entity) {
			Long entityProductId = entity.getMainId();
			if (entityProductId.equals(productId)) {
				delete(entity);
				coeProductTag.setSuccess(CoeProductTag.SUCCESS_YES);
			}
		}

		return coeProductTag;
	}
	
	public CoeProductTag findByMainIdAndName(CoeProductTag coeProductTag) {
		Long productId = coeProductTag.getMainId();
		String name = coeProductTag.getName();
		if (null != productId && StringUtils.isNotBlank(name)) {
			CoeProductTag tag = coeProductTagDao.findByMainIdAndName(coeProductTag);
			return tag;
		}
		return null;
	}
	
	public Long countByMainId(Long productId) {
		return coeProductTagDao.countByMainId(productId);
	}
	
	public List<CoeProductTag> findByMainId(Long productId) {
		return coeProductTagDao.findByMainId(productId);
	}
	
	
	public Map<Long, List<CoeProductTag>> findMapByMainIdList(List<Long> productIdList) {
		List<CoeProductTag> content = coeProductTagDao.findByMainIdList(productIdList);
		Map<Long, List<CoeProductTag>> map = new HashMap<>();
		if(null != content && !content.isEmpty()) {
			for(CoeProductTag c : content) {
				Long mainId = c.getMainId();
				List<CoeProductTag> entityList = map.get(mainId);
				if(null == entityList) {
					entityList = new ArrayList<>();
					map.put(mainId, entityList);
				}
				entityList.add(c);
			}
		}
		return map;
	}

}
