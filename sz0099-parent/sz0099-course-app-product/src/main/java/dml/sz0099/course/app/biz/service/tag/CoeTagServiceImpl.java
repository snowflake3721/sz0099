package dml.sz0099.course.app.biz.service.tag;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.jit4j.core.service.GenericServiceImpl;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.persist.dao.tag.CoeTagDao;
import dml.sz0099.course.app.persist.entity.tag.CoeTag;


/**
 * 
 * @formatter:off
 * description: CoeTagServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeTagServiceImpl extends GenericServiceImpl<CoeTag, Long> implements CoeTagService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeTagServiceImpl.class);

	@Autowired
	private CoeTagDao coeTagDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeTagDao;
	}

	/**
	 * 根据Id查询CoeTag实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeTag findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeTag coeTag = coeTagDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeTag);
		return coeTag;
	}
	
	@Override
	public CoeTag findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeTag coeTag = coeTagDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeTag);
		return coeTag;
	}

	/**
	 * 根据IdList查询CoeTag实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeTag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeTag> coeTagList = coeTagDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeTagList);
		return coeTagList;
	}

	@Transactional
	@Override
	public CoeTag persistEntity(CoeTag coeTag) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeTag entity = save(coeTag);
		Long id = coeTag.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeTag.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeTag mergeEntity(CoeTag coeTag) {
		Long id = coeTag.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeTag entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeTag.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeTag.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeTag saveOrUpdate(CoeTag coeTag) {
		Long id = coeTag.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeTag entity = null;
		if(null != id) {
			entity = mergeEntity(coeTag);
		}else {
			entity = persistEntity(coeTag);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeTag> findPage(CoeTag coeTag, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeTag> page = coeTagDao.findPage(coeTag, pageable);
		return page;
	}

	@Override
	public CoeTag findByName(String name) {
		return coeTagDao.findByName(name);
	}

}
