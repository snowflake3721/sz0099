package dml.sz0099.course.app.biz.service.profession;

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

import dml.sz0099.course.app.persist.dao.profession.ProfessionFavirateDao;
import dml.sz0099.course.app.persist.entity.profession.ProfessionFavirate;


/**
 * 
 * @formatter:off
 * description: ProfessionFavirateServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class ProfessionFavirateServiceImpl extends GenericServiceImpl<ProfessionFavirate, Long> implements ProfessionFavirateService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionFavirateServiceImpl.class);

	@Autowired
	private ProfessionFavirateDao professionFavirateDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = professionFavirateDao;
	}

	/**
	 * 根据Id查询ProfessionFavirate实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionFavirate findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		ProfessionFavirate professionFavirate = professionFavirateDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, professionFavirate);
		return professionFavirate;
	}
	
	@Override
	public ProfessionFavirate findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionFavirate professionFavirate = professionFavirateDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, professionFavirate);
		return professionFavirate;
	}

	/**
	 * 根据IdList查询ProfessionFavirate实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionFavirate> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<ProfessionFavirate> professionFavirateList = professionFavirateDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", professionFavirateList);
		return professionFavirateList;
	}

	@Transactional
	@Override
	public ProfessionFavirate persistEntity(ProfessionFavirate professionFavirate) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		ProfessionFavirate entity = save(professionFavirate);
		Long id = professionFavirate.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(ProfessionFavirate.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public ProfessionFavirate mergeEntity(ProfessionFavirate professionFavirate) {
		Long id = professionFavirate.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		ProfessionFavirate entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(professionFavirate.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(ProfessionFavirate.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public ProfessionFavirate saveOrUpdate(ProfessionFavirate professionFavirate) {
		Long id = professionFavirate.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionFavirate entity = null;
		if(null != id) {
			entity = mergeEntity(professionFavirate);
		}else {
			entity = persistEntity(professionFavirate);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionFavirate> findPage(ProfessionFavirate professionFavirate, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<ProfessionFavirate> page = professionFavirateDao.findPage(professionFavirate, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return professionFavirateDao.existById(id);
	}

	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return professionFavirateDao.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public ProfessionFavirate findByMainIdAndUserId(Long mainId, Long userId) {
		return professionFavirateDao.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<ProfessionFavirate> findByMainId(Long mainId, Pageable pageable) {
		return professionFavirateDao.findByMainId( mainId,  pageable);
	}
	
	public boolean hasFaviratedByMainIdAndUserId(Long mainId, Long userId) {
		ProfessionFavirate entity = findByMainIdAndUserId(mainId,userId);
		if(null != entity) {
			if(ProfessionFavirate.STATUS_YES==entity.getStatus()) {
				return true;
			}
		}
		return false;
	}

	@Transactional
	@Override
	public ProfessionFavirate mergeForFavirate(ProfessionFavirate professionFavirate) {
		Long mainId = professionFavirate.getMainId();
		Long userId = professionFavirate.getUserId();
		ProfessionFavirate entity = findByMainIdAndUserId( mainId,  userId);
		if(null != entity) {
			entity.setNickname(professionFavirate.getNickname());
			entity.setHeadImg(professionFavirate.getHeadImg());
			entity.setSayword(professionFavirate.getSayword());
			entity.setStatus(professionFavirate.getStatus());
			entity.setLastModifiedBy(professionFavirate.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			entity = save(entity);
		}else {
			entity = save(professionFavirate);
		}
		return entity;
	}
	
}
