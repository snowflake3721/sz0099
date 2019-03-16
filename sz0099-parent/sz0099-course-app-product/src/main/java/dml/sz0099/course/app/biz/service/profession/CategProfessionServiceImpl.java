package dml.sz0099.course.app.biz.service.profession;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.jit4j.core.service.GenericServiceImpl;
import org.jit8j.core.util.CollectionUtil;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.persist.dao.profession.CategProfessionDao;
import dml.sz0099.course.app.persist.entity.profession.CategProfession;
import dml.sz0099.course.app.persist.entity.profession.Profession;


/**
 * 
 * @formatter:off
 * description: CategProfessionServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CategProfessionServiceImpl extends GenericServiceImpl<CategProfession, Long> implements CategProfessionService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategProfessionServiceImpl.class);

	@Autowired
	private CategProfessionDao categProfessionDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = categProfessionDao;
	}

	/**
	 * 根据Id查询CategProfession实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CategProfession findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CategProfession categProfession = categProfessionDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, categProfession);
		return categProfession;
	}
	
	@Override
	public CategProfession findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CategProfession categProfession = categProfessionDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, categProfession);
		return categProfession;
	}

	/**
	 * 根据IdList查询CategProfession实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CategProfession> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CategProfession> categProfessionList = categProfessionDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", categProfessionList);
		return categProfessionList;
	}

	@Transactional
	@Override
	public CategProfession persistEntity(CategProfession categProfession) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CategProfession entity = save(categProfession);
		Long id = categProfession.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CategProfession.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CategProfession mergeEntity(CategProfession categProfession) {
		Long id = categProfession.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CategProfession entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(categProfession.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CategProfession.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CategProfession saveOrUpdate(CategProfession categProfession) {
		Long id = categProfession.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CategProfession entity = null;
		if(null != id) {
			entity = mergeEntity(categProfession);
		}else {
			entity = persistEntity(categProfession);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CategProfession> findPage(CategProfession categProfession, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CategProfession> page = categProfessionDao.findPage(categProfession, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return categProfessionDao.existById(id);
	}
	
	public List<CategProfession> findByMainId(Long professionId){
		return categProfessionDao.findByMainId(professionId);
	}

	public List<CategProfession> findByMainIdList(List<Long> professionIdList) {
		return categProfessionDao.findByMainIdList(professionIdList);
	}
	
	public Map<Long, List<CategProfession>> findMapByMainIdList(List<Long> professionIdList) {
		List<CategProfession> content = findByMainIdList(professionIdList);
		Map<Long, List<CategProfession>> map = null;
		if(null != content && !content.isEmpty()) {
			/*for(CategProfession c : content) {
				Long mainId = c.getMainId();
				List<CategProfession> entityList = map.get(mainId);
				if(null == entityList) {
					entityList = new ArrayList<>();
					map.put(mainId, entityList);
				}
				entityList.add(c);
			}*/
			map = CollectionUtil.convertList2Map(content, "mainId");
		}
		return map;
	}
	
	@Transactional
	public CategProfession changeCategory(CategProfession categProfession) {
		Long maidId = categProfession.getMainId();
		Long baseId = categProfession.getBaseId();
		List<CategProfession> entityList = findByMainId(maidId);
		boolean exist=false;
		if(null != entityList && !entityList.isEmpty()) {
			for(CategProfession entity : entityList) {
				Long entityBaseId = entity.getBaseId();
				if(baseId.equals(entityBaseId)) {
					exist=true;
					continue;
				}
				delete(entity);
				categProfession.setSuccess(CategProfession.SUCCESS_YES);
			}
		}
		if(!exist) {
			categProfession = save(categProfession);
			categProfession.setSuccess(CategProfession.SUCCESS_YES);
		}
		return categProfession;
	}
	
	public Page<CategProfession> findPageForPublish(CategProfession categProfession, Pageable pageable) {
		Profession profession = categProfession.getProfession();
		if(null == profession) {
			profession = new Profession();
			categProfession.setProfession(profession);
		}
		profession.setPublishStatus(Profession.PUBLISH_STATUS_PUBLISH.getValueInt());
		
		return categProfessionDao.findPageForPublish(categProfession, pageable);
	}
	
	public Page<CategProfession> findPageForPublishFromDetail(CategProfession categProfession, Pageable pageable){
		Profession profession = categProfession.getProfession();
		if(null == profession) {
			profession = new Profession();
			categProfession.setProfession(profession);
		}
		profession.setPublishStatus(Profession.PUBLISH_STATUS_PUBLISH.getValueInt());
		
		return categProfessionDao.findPageForPublishFromDetail(categProfession, pageable);
	}
	
	public Page<CategProfession> findPageForPublish(CategProfession categProfession, List<Long> excludeMainIdList, Pageable pageable) {
		Profession profession = categProfession.getProfession();
		if(null == profession) {
			profession = new Profession();
			categProfession.setProfession(profession);
		}
		profession.setPublishStatus(Profession.PUBLISH_STATUS_PUBLISH.getValueInt());
		
		return categProfessionDao.findPageForPublish(categProfession, excludeMainIdList, pageable);
	}
	
	public Page<CategProfession> findPageForPublishWithChildren(CategProfession categProfession, List<Long> baseIdList, List<Long> excludeMainIdList, Pageable pageable){
		Profession profession = categProfession.getProfession();
		if(null == profession) {
			profession = new Profession();
			categProfession.setProfession(profession);
		}
		profession.setPublishStatus(Profession.PUBLISH_STATUS_PUBLISH.getValueInt());
		
		return categProfessionDao.findPageForPublishWithChildren(categProfession, baseIdList, excludeMainIdList, pageable);
	}
	


}
