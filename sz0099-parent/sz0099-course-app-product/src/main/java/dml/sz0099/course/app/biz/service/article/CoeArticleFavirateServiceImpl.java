package dml.sz0099.course.app.biz.service.article;

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

import dml.sz0099.course.app.persist.dao.article.CoeArticleFavirateDao;
import dml.sz0099.course.app.persist.entity.article.CoeArticleFavirate;


/**
 * 
 * @formatter:off
 * description: CoeArticleFavirateServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeArticleFavirateServiceImpl extends GenericServiceImpl<CoeArticleFavirate, Long> implements CoeArticleFavirateService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeArticleFavirateServiceImpl.class);

	@Autowired
	private CoeArticleFavirateDao coeArticleFavirateDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeArticleFavirateDao;
	}

	/**
	 * 根据Id查询CoeArticleFavirate实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeArticleFavirate findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeArticleFavirate coeArticleFavirate = coeArticleFavirateDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeArticleFavirate);
		return coeArticleFavirate;
	}
	
	@Override
	public CoeArticleFavirate findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeArticleFavirate coeArticleFavirate = coeArticleFavirateDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeArticleFavirate);
		return coeArticleFavirate;
	}

	/**
	 * 根据IdList查询CoeArticleFavirate实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeArticleFavirate> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeArticleFavirate> coeArticleFavirateList = coeArticleFavirateDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeArticleFavirateList);
		return coeArticleFavirateList;
	}

	@Transactional
	@Override
	public CoeArticleFavirate persistEntity(CoeArticleFavirate coeArticleFavirate) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeArticleFavirate entity = save(coeArticleFavirate);
		Long id = coeArticleFavirate.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeArticleFavirate.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeArticleFavirate mergeEntity(CoeArticleFavirate coeArticleFavirate) {
		Long id = coeArticleFavirate.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeArticleFavirate entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeArticleFavirate.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeArticleFavirate.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeArticleFavirate saveOrUpdate(CoeArticleFavirate coeArticleFavirate) {
		Long id = coeArticleFavirate.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeArticleFavirate entity = null;
		if(null != id) {
			entity = mergeEntity(coeArticleFavirate);
		}else {
			entity = persistEntity(coeArticleFavirate);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeArticleFavirate> findPage(CoeArticleFavirate coeArticleFavirate, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeArticleFavirate> page = coeArticleFavirateDao.findPage(coeArticleFavirate, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeArticleFavirateDao.existById(id);
	}

	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return coeArticleFavirateDao.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public CoeArticleFavirate findByMainIdAndUserId(Long mainId, Long userId) {
		return coeArticleFavirateDao.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<CoeArticleFavirate> findByMainId(Long mainId, Pageable pageable) {
		return coeArticleFavirateDao.findByMainId( mainId,  pageable);
	}
	
	public boolean hasFaviratedByMainIdAndUserId(Long mainId, Long userId) {
		CoeArticleFavirate entity = findByMainIdAndUserId(mainId,userId);
		if(null != entity) {
			if(CoeArticleFavirate.STATUS_YES==entity.getStatus()) {
				return true;
			}
		}
		return false;
	}

	@Transactional
	@Override
	public CoeArticleFavirate mergeForFavirate(CoeArticleFavirate coeArticleFavirate) {
		Long mainId = coeArticleFavirate.getMainId();
		Long userId = coeArticleFavirate.getUserId();
		CoeArticleFavirate entity = findByMainIdAndUserId( mainId,  userId);
		if(null != entity) {
			entity.setNickname(coeArticleFavirate.getNickname());
			entity.setHeadImg(coeArticleFavirate.getHeadImg());
			entity.setSayword(coeArticleFavirate.getSayword());
			entity.setStatus(coeArticleFavirate.getStatus());
			entity.setLastModifiedBy(coeArticleFavirate.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			entity = save(entity);
		}else {
			entity = save(coeArticleFavirate);
		}
		return entity;
	}

}
