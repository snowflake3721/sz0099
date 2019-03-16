package dml.sz0099.course.app.biz.service.user;

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

import dml.sz0099.course.app.persist.dao.user.CoeUserTagDao;
import dml.sz0099.course.app.persist.entity.user.CoeUserTag;


/**
 * 
 * @formatter:off
 * description: CoeUserTagServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeUserTagServiceImpl extends GenericServiceImpl<CoeUserTag, Long> implements CoeUserTagService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserTagServiceImpl.class);

	@Autowired
	private CoeUserTagDao coeUserTagDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeUserTagDao;
	}

	/**
	 * 根据Id查询CoeUserTag实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeUserTag findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeUserTag coeUserTag = coeUserTagDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeUserTag);
		return coeUserTag;
	}
	
	@Override
	public CoeUserTag findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeUserTag coeUserTag = coeUserTagDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeUserTag);
		return coeUserTag;
	}

	/**
	 * 根据IdList查询CoeUserTag实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeUserTag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeUserTag> coeUserTagList = coeUserTagDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeUserTagList);
		return coeUserTagList;
	}

	@Transactional
	@Override
	public CoeUserTag persistEntity(CoeUserTag coeUserTag) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeUserTag entity = save(coeUserTag);
		Long id = coeUserTag.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeUserTag.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeUserTag mergeEntity(CoeUserTag coeUserTag) {
		Long id = coeUserTag.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeUserTag entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeUserTag.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeUserTag.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeUserTag saveOrUpdate(CoeUserTag coeUserTag) {
		Long id = coeUserTag.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeUserTag entity = null;
		if(null != id) {
			entity = mergeEntity(coeUserTag);
		}else {
			entity = persistEntity(coeUserTag);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeUserTag> findPage(CoeUserTag coeUserTag, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeUserTag> page = coeUserTagDao.findPage(coeUserTag, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeUserTagDao.existById(id);
	}

}
