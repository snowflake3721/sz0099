package dml.sz0099.course.app.biz.service.activity;

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

import dml.sz0099.course.app.persist.dao.activity.CoeActivityFavirateDao;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityFavirate;


/**
 * 
 * @formatter:off
 * description: CoeActivityFavirateServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeActivityFavirateServiceImpl extends GenericServiceImpl<CoeActivityFavirate, Long> implements CoeActivityFavirateService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityFavirateServiceImpl.class);

	@Autowired
	private CoeActivityFavirateDao coeActivityFavirateDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeActivityFavirateDao;
	}

	/**
	 * 根据Id查询CoeActivityFavirate实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityFavirate findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeActivityFavirate coeActivityFavirate = coeActivityFavirateDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeActivityFavirate);
		return coeActivityFavirate;
	}
	
	@Override
	public CoeActivityFavirate findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityFavirate coeActivityFavirate = coeActivityFavirateDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityFavirate);
		return coeActivityFavirate;
	}

	/**
	 * 根据IdList查询CoeActivityFavirate实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityFavirate> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeActivityFavirate> coeActivityFavirateList = coeActivityFavirateDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeActivityFavirateList);
		return coeActivityFavirateList;
	}

	@Transactional
	@Override
	public CoeActivityFavirate persistEntity(CoeActivityFavirate coeActivityFavirate) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeActivityFavirate entity = save(coeActivityFavirate);
		Long id = coeActivityFavirate.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeActivityFavirate.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeActivityFavirate mergeEntity(CoeActivityFavirate coeActivityFavirate) {
		Long id = coeActivityFavirate.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeActivityFavirate entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeActivityFavirate.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeActivityFavirate.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeActivityFavirate saveOrUpdate(CoeActivityFavirate coeActivityFavirate) {
		Long id = coeActivityFavirate.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeActivityFavirate entity = null;
		if(null != id) {
			entity = mergeEntity(coeActivityFavirate);
		}else {
			entity = persistEntity(coeActivityFavirate);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeActivityFavirate> findPage(CoeActivityFavirate coeActivityFavirate, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeActivityFavirate> page = coeActivityFavirateDao.findPage(coeActivityFavirate, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeActivityFavirateDao.existById(id);
	}

	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return coeActivityFavirateDao.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public CoeActivityFavirate findByMainIdAndUserId(Long mainId, Long userId) {
		return coeActivityFavirateDao.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<CoeActivityFavirate> findByMainId(Long mainId, Pageable pageable) {
		return coeActivityFavirateDao.findByMainId( mainId,  pageable);
	}
	
	public boolean hasFaviratedByMainIdAndUserId(Long mainId, Long userId) {
		CoeActivityFavirate entity = findByMainIdAndUserId(mainId,userId);
		if(null != entity) {
			if(CoeActivityFavirate.STATUS_YES==entity.getStatus()) {
				return true;
			}
		}
		return false;
	}

	@Transactional
	@Override
	public CoeActivityFavirate mergeForFavirate(CoeActivityFavirate coeActivityFavirate) {
		Long mainId = coeActivityFavirate.getMainId();
		Long userId = coeActivityFavirate.getUserId();
		CoeActivityFavirate entity = findByMainIdAndUserId( mainId,  userId);
		if(null != entity) {
			entity.setNickname(coeActivityFavirate.getNickname());
			entity.setHeadImg(coeActivityFavirate.getHeadImg());
			entity.setSayword(coeActivityFavirate.getSayword());
			entity.setStatus(coeActivityFavirate.getStatus());
			entity.setLastModifiedBy(coeActivityFavirate.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			entity = save(entity);
		}else {
			entity = save(coeActivityFavirate);
		}
		return entity;
	}

}
