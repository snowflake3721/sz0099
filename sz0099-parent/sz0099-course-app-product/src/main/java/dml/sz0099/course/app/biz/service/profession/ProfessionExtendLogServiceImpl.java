package dml.sz0099.course.app.biz.service.profession;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.jit4j.core.service.GenericServiceImpl;
import org.jit8j.core.util.GsonBuilderUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.service.profession.ProfessionExtendContentService;
import dml.sz0099.course.app.persist.dao.profession.ProfessionExtendLogDao;
import dml.sz0099.course.app.persist.entity.profession.ProfessionExtend;
import dml.sz0099.course.app.persist.entity.profession.ProfessionExtendContent;
import dml.sz0099.course.app.persist.entity.profession.ProfessionExtendLog;


/**
 * 
 * @formatter:off
 * description: ProfessionExtendLogServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class ProfessionExtendLogServiceImpl extends GenericServiceImpl<ProfessionExtendLog, Long> implements ProfessionExtendLogService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionExtendLogServiceImpl.class);

	@Autowired
	private ProfessionExtendLogDao professionExtendLogDao;
	
	@Autowired
	private ProfessionExtendContentService professionExtendContentService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = professionExtendLogDao;
	}

	/**
	 * 根据Id查询ProfessionExtendLog实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionExtendLog findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		ProfessionExtendLog professionExtendLog = professionExtendLogDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, professionExtendLog);
		return professionExtendLog;
	}
	
	@Override
	public ProfessionExtendLog findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionExtendLog professionExtendLog = professionExtendLogDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, professionExtendLog);
		return professionExtendLog;
	}

	/**
	 * 根据IdList查询ProfessionExtendLog实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionExtendLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<ProfessionExtendLog> professionExtendLogList = professionExtendLogDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", professionExtendLogList);
		return professionExtendLogList;
	}

	@Transactional
	@Override
	public ProfessionExtendLog persistEntity(ProfessionExtendLog professionExtendLog) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		ProfessionExtendLog entity = save(professionExtendLog);
		Long id = professionExtendLog.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(ProfessionExtendLog.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public ProfessionExtendLog mergeEntity(ProfessionExtendLog professionExtendLog) {
		Long id = professionExtendLog.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		ProfessionExtendLog entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(professionExtendLog.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(ProfessionExtendLog.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public ProfessionExtendLog saveOrUpdate(ProfessionExtendLog professionExtendLog) {
		Long id = professionExtendLog.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionExtendLog entity = null;
		if(null != id) {
			entity = mergeEntity(professionExtendLog);
		}else {
			entity = persistEntity(professionExtendLog);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionExtendLog> findPage(ProfessionExtendLog professionExtendLog, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<ProfessionExtendLog> page = professionExtendLogDao.findPage(professionExtendLog, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return professionExtendLogDao.existById(id);
	}

	@Transactional
	@Override
	public ProfessionExtendLog persistForFail(ProfessionExtend professionExtend) {
		ProfessionExtendLog professionExtendLog = new ProfessionExtendLog();
		
		professionExtendLog.setExtendId(professionExtend.getId());
		professionExtendLog.setDevId(professionExtend.getDevId());
		professionExtendLog.setDomain(professionExtend.getDomain());
		professionExtendLog.setModule(professionExtend.getModule());
		professionExtendLog.setPosition(professionExtend.getPosition());
		professionExtendLog.setPositionId(professionExtend.getPositionId());
		professionExtendLog.setProject(professionExtend.getProject());
		professionExtendLog.setUserId(professionExtend.getUserId());
		professionExtendLog.setVariety(professionExtend.getVariety());
		professionExtendLog.setCreatedBy(professionExtend.getCreatedBy());
		professionExtendLog.setLastModifiedBy(professionExtend.getLastModifiedBy());
		professionExtendLog = persistEntity(professionExtendLog);
		ProfessionExtendContent content = new ProfessionExtendContent();
		content.setExtendLogId(professionExtendLog.getId());
		content.setContent(GsonBuilderUtils.toJsonOmitnull(professionExtend));
		content = professionExtendContentService.persistEntity(content);
		professionExtendLog.setContent(content);
		
		return professionExtendLog;
	}
}
