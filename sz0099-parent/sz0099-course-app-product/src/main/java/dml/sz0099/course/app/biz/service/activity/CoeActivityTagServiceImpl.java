package dml.sz0099.course.app.biz.service.activity;

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
import dml.sz0099.course.app.persist.dao.activity.CoeActivityTagDao;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTag;
import dml.sz0099.course.app.persist.entity.activity.CoeCategActivity;
import dml.sz0099.course.app.persist.entity.activity.JoinItem;
import dml.sz0099.course.app.persist.entity.tag.CoeTag;


/**
 * 
 * @formatter:off
 * description: CoeActivityTagServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeActivityTagServiceImpl extends GenericServiceImpl<CoeActivityTag, Long> implements CoeActivityTagService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityTagServiceImpl.class);

	@Autowired
	private CoeActivityTagDao coeActivityTagDao;
	
	@Autowired
	private CoeTagService coeTagService;
	
	@Autowired
	private CoeActivityService coeActivityService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeActivityTagDao;
	}

	/**
	 * 根据Id查询CoeActivityTag实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityTag findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeActivityTag coeActivityTag = coeActivityTagDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeActivityTag);
		return coeActivityTag;
	}
	
	@Override
	public CoeActivityTag findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityTag coeActivityTag = coeActivityTagDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityTag);
		return coeActivityTag;
	}

	/**
	 * 根据IdList查询CoeActivityTag实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityTag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeActivityTag> coeActivityTagList = coeActivityTagDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeActivityTagList);
		return coeActivityTagList;
	}

	@Transactional
	@Override
	public CoeActivityTag persistEntity(CoeActivityTag coeActivityTag) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeActivityTag entity = save(coeActivityTag);
		Long id = coeActivityTag.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeActivityTag.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeActivityTag mergeEntity(CoeActivityTag coeActivityTag) {
		Long id = coeActivityTag.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeActivityTag entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeActivityTag.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeActivityTag.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeActivityTag saveOrUpdate(CoeActivityTag coeActivityTag) {
		Long id = coeActivityTag.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeActivityTag entity = null;
		if(null != id) {
			entity = mergeEntity(coeActivityTag);
		}else {
			entity = persistEntity(coeActivityTag);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeActivityTag> findPage(CoeActivityTag coeActivityTag, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeActivityTag> page = coeActivityTagDao.findPage(coeActivityTag, pageable);
		return page;
	}
	
	@Transactional
	public CoeActivityTag addTag(CoeActivityTag coeActivityTag) {
		//检测本产品是否已经拥有此标签(在validator校验)
		//增加标签
		CoeTag tag = coeActivityTag.getTag();
		String name = coeActivityTag.getName();
		
		CoeTag entity = coeTagService.findByName(name);
		//1.检测标签表中是否存在，不存在，在标签表中增加记录
		if(null == entity) {
			if(tag==null) {
				tag = new CoeTag();
			}
			tag.setName(name);
			//做拼音转化存储至code TODO
			
			tag.setCreatedBy(coeActivityTag.getCreatedBy());
			tag.setLastModifiedBy(coeActivityTag.getCreatedBy());
			entity=coeTagService.persistEntity(tag);
		}
		
		coeActivityTag.setTagId(entity.getId());
		
		//2.关联标签与产品id
		coeActivityTag=persistEntity(coeActivityTag);
		coeActivityTag.setTag(entity);
		
		return coeActivityTag;
	}
	
	@Transactional
	public CoeActivityTag deleteTag(CoeActivityTag coeActivityTag) {
		Long pTagId = coeActivityTag.getId();
		Long activityId = coeActivityTag.getMainId();
		CoeActivityTag entity = findById(pTagId);
		if (null != entity) {
			Long entityProfessionId = entity.getMainId();
			if (entityProfessionId.equals(activityId)) {
				delete(entity);
				coeActivityTag.setSuccess(CoeActivityTag.SUCCESS_YES);
			}
		}

		return coeActivityTag;
	}
	
	public CoeActivityTag findByMainIdAndName(CoeActivityTag coeActivityTag) {
		Long activityId = coeActivityTag.getMainId();
		String name = coeActivityTag.getName();
		if (null != activityId && StringUtils.isNotBlank(name)) {
			CoeActivityTag tag = coeActivityTagDao.findByMainIdAndName(coeActivityTag);
			return tag;
		}
		return null;
	}
	
	public Long countByMainId(Long activityId) {
		return coeActivityTagDao.countByMainId(activityId);
	}
	
	public List<CoeActivityTag> findByMainId(Long activityId) {
		return coeActivityTagDao.findByMainId(activityId);
	}
	
	public Map<Long, List<CoeActivityTag>> findMapByMainIdList(List<Long> mainIdList) {
		List<CoeActivityTag> content = coeActivityTagDao.findByMainIdList(mainIdList);
		Map<Long, List<CoeActivityTag>> map = new HashMap<>();
		if(null != content && !content.isEmpty()) {
			for(CoeActivityTag c : content) {
				Long mainId = c.getMainId();
				List<CoeActivityTag> entityList = map.get(mainId);
				if(null == entityList) {
					entityList = new ArrayList<>();
					map.put(mainId, entityList);
				}
				entityList.add(c);
			}
		}
		return map;
	}
	
	public Page<CoeActivityTag> findPageWithNotself(CoeActivityTag coeActivityTag, Pageable pageable){
		Page<CoeActivityTag> page = coeActivityTagDao.findPageWithNotself(coeActivityTag, pageable);
		if(null != page && page.getTotalElements()>0) {
			List<CoeActivityTag> content = page.getContent();
			List<Long> mainIdList = new ArrayList<>(content.size());
			Map<Long, CoeActivityTag> tagMap = new HashMap<>(content.size());
			Map<Long, CoeActivityTag> tagRepeatMap = new HashMap<>(content.size());
			for(CoeActivityTag c : content) {
				Long mainId = c.getMainId();
				if(!mainIdList.contains(mainId)) {
					mainIdList.add(mainId);
					tagMap.put(mainId, c);
				}else {
					tagRepeatMap.put(mainId, c);
				}
			}
			List<CoeActivity> mainList = coeActivityService.findPublishByIdList(mainIdList);
			if(null != mainList && !mainList.isEmpty()) {
				for(CoeActivity m : mainList) {
					Long mid = m.getId();
					CoeActivityTag mc = tagMap.get(mid);
					if(null != mc) {
						mc.setActivity(m);
					}
					CoeActivityTag mcr = tagRepeatMap.get(mid);
					if(null != mcr) {
						mcr.setActivity(m);
					}
				}
			}
		}
		return page;
	}

	@Override
	public CoeActivity syncFromTemplate(CoeActivity entity, CoeActivity template) {
		Long mainId = entity.getId();
		Long templateId = template.getId();
		List<CoeActivityTag> tagEntityList = findByMainId(mainId);
		int i=0;
		int size=0;
		if(null != tagEntityList && !tagEntityList.isEmpty()) {
			size=tagEntityList.size();
		}
		//同步模板标签
		List<CoeActivityTag> activityTagList=findByMainId(templateId);
		if(null != activityTagList && !activityTagList.isEmpty()) {
			List<CoeActivityTag> actTagEntityList = new ArrayList<>(activityTagList.size());
			for(CoeActivityTag tag : activityTagList) {
				CoeActivityTag tagEntity = null;
				if(i<size) {
					tagEntity = activityTagList.get(i);
				}else {
					tagEntity=new CoeActivityTag();
					tagEntity.setCreatedBy(entity.getCreatedBy());
					DateTime creationDate = new DateTime();
					tagEntity.setCreatedDate(creationDate);
					tagEntity.setMainId(mainId);
				}
				i++;
				
				tagEntity.setTagId(tag.getTagId());
				tagEntity.setName(tag.getName());
				tagEntity.setLastModifiedBy(entity.getLastModifiedBy());
				tagEntity=save(tagEntity);
				actTagEntityList.add(tagEntity);
			}
			if(i<size) {
				//清除多余项
				for(;i<size;i++) {
					CoeActivityTag tagEntity = tagEntityList.get(i);
					delete(tagEntity);
				}
			}
			entity.setActivityTagList(actTagEntityList);
		}
		
		return entity;
	}

}
