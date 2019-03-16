package dml.sz0099.course.app.biz.service.profession;

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
import dml.sz0099.course.app.persist.dao.profession.ProfessionTagDao;
import dml.sz0099.course.app.persist.entity.profession.ProfessionTag;
import dml.sz0099.course.app.persist.entity.tag.CoeTag;
import dml.sz0099.course.app.persist.entity.profession.ProfessionTag;


/**
 * 
 * @formatter:off
 * description: ProfessionTagServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class ProfessionTagServiceImpl extends GenericServiceImpl<ProfessionTag, Long> implements ProfessionTagService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionTagServiceImpl.class);

	@Autowired
	private ProfessionTagDao professionTagDao;
	
	@Autowired
	private CoeTagService coeTagService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = professionTagDao;
	}

	/**
	 * 根据Id查询ProfessionTag实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionTag findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		ProfessionTag professionTag = professionTagDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, professionTag);
		return professionTag;
	}
	
	@Override
	public ProfessionTag findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionTag professionTag = professionTagDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, professionTag);
		return professionTag;
	}

	/**
	 * 根据IdList查询ProfessionTag实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionTag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<ProfessionTag> professionTagList = professionTagDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", professionTagList);
		return professionTagList;
	}

	@Transactional
	@Override
	public ProfessionTag persistEntity(ProfessionTag professionTag) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		ProfessionTag entity = save(professionTag);
		Long id = professionTag.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(ProfessionTag.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public ProfessionTag mergeEntity(ProfessionTag professionTag) {
		Long id = professionTag.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		ProfessionTag entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(professionTag.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(ProfessionTag.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public ProfessionTag saveOrUpdate(ProfessionTag professionTag) {
		Long id = professionTag.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionTag entity = null;
		if(null != id) {
			entity = mergeEntity(professionTag);
		}else {
			entity = persistEntity(professionTag);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionTag> findPage(ProfessionTag professionTag, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<ProfessionTag> page = professionTagDao.findPage(professionTag, pageable);
		return page;
	}
	
	@Transactional
	public ProfessionTag addTag(ProfessionTag professionTag) {
		//检测本产品是否已经拥有此标签(在validator校验)
		//增加标签
		CoeTag tag = professionTag.getTag();
		String name = professionTag.getName();
		
		CoeTag entity = coeTagService.findByName(name);
		//1.检测标签表中是否存在，不存在，在标签表中增加记录
		if(null == entity) {
			if(tag==null) {
				tag = new CoeTag();
			}
			tag.setName(name);
			//做拼音转化存储至code TODO
			
			tag.setCreatedBy(professionTag.getCreatedBy());
			tag.setLastModifiedBy(professionTag.getCreatedBy());
			entity=coeTagService.persistEntity(tag);
		}
		
		professionTag.setTagId(entity.getId());
		
		//2.关联标签与产品id
		professionTag=persistEntity(professionTag);
		professionTag.setTag(entity);
		
		return professionTag;
	}
	
	@Transactional
	public ProfessionTag deleteTag(ProfessionTag professionTag) {
		Long pTagId = professionTag.getId();
		Long professionId = professionTag.getMainId();
		ProfessionTag entity = findById(pTagId);
		if (null != entity) {
			Long entityProfessionId = entity.getMainId();
			if (entityProfessionId.equals(professionId)) {
				delete(entity);
				professionTag.setSuccess(ProfessionTag.SUCCESS_YES);
			}
		}

		return professionTag;
	}
	
	public ProfessionTag findByMainIdAndName(ProfessionTag professionTag) {
		Long professionId = professionTag.getMainId();
		String name = professionTag.getName();
		if (null != professionId && StringUtils.isNotBlank(name)) {
			ProfessionTag tag = professionTagDao.findByMainIdAndName(professionTag);
			return tag;
		}
		return null;
	}
	
	public Long countByMainId(Long professionId) {
		return professionTagDao.countByMainId(professionId);
	}
	
	public List<ProfessionTag> findByMainId(Long professionId) {
		return professionTagDao.findByMainId(professionId);
	}
	
	public Map<Long, List<ProfessionTag>> findMapByMainIdList(List<Long> mainIdList) {
		List<ProfessionTag> content = professionTagDao.findByMainIdList(mainIdList);
		Map<Long, List<ProfessionTag>> map = new HashMap<>();
		if(null != content && !content.isEmpty()) {
			for(ProfessionTag c : content) {
				Long mainId = c.getMainId();
				List<ProfessionTag> entityList = map.get(mainId);
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
