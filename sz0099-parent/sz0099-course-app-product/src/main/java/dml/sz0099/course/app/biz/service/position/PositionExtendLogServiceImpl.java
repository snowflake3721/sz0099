package dml.sz0099.course.app.biz.service.position;

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

import dml.sz0099.course.app.biz.service.position.PositionExtendContentService;
import dml.sz0099.course.app.persist.dao.position.PositionExtendLogDao;
import dml.sz0099.course.app.persist.entity.position.PositionExtend;
import dml.sz0099.course.app.persist.entity.position.PositionExtendContent;
import dml.sz0099.course.app.persist.entity.position.PositionExtendLog;
import dml.sz0099.course.app.persist.entity.position.PositionExtend;
import dml.sz0099.course.app.persist.entity.position.PositionExtendLog;


/**
 * 
 * @formatter:off
 * description: PositionExtendLogServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class PositionExtendLogServiceImpl extends GenericServiceImpl<PositionExtendLog, Long> implements PositionExtendLogService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PositionExtendLogServiceImpl.class);

	@Autowired
	private PositionExtendLogDao positionExtendLogDao;
	
	@Autowired
	private PositionExtendContentService positionExtendContentService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = positionExtendLogDao;
	}

	/**
	 * 根据Id查询PositionExtendLog实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public PositionExtendLog findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		PositionExtendLog positionExtendLog = positionExtendLogDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, positionExtendLog);
		return positionExtendLog;
	}
	
	@Override
	public PositionExtendLog findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		PositionExtendLog positionExtendLog = positionExtendLogDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, positionExtendLog);
		return positionExtendLog;
	}

	/**
	 * 根据IdList查询PositionExtendLog实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<PositionExtendLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<PositionExtendLog> positionExtendLogList = positionExtendLogDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", positionExtendLogList);
		return positionExtendLogList;
	}

	@Transactional
	@Override
	public PositionExtendLog persistEntity(PositionExtendLog positionExtendLog) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		PositionExtendLog entity = save(positionExtendLog);
		Long id = positionExtendLog.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(PositionExtendLog.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public PositionExtendLog mergeEntity(PositionExtendLog positionExtendLog) {
		Long id = positionExtendLog.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		PositionExtendLog entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(positionExtendLog.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(PositionExtendLog.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public PositionExtendLog saveOrUpdate(PositionExtendLog positionExtendLog) {
		Long id = positionExtendLog.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		PositionExtendLog entity = null;
		if(null != id) {
			entity = mergeEntity(positionExtendLog);
		}else {
			entity = persistEntity(positionExtendLog);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PositionExtendLog> findPage(PositionExtendLog positionExtendLog, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<PositionExtendLog> page = positionExtendLogDao.findPage(positionExtendLog, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return positionExtendLogDao.existById(id);
	}

	@Transactional
	@Override
	public PositionExtendLog persistForFail(PositionExtend positionExtend) {
		PositionExtendLog positionExtendLog = new PositionExtendLog();
		
		positionExtendLog.setExtendId(positionExtend.getId());
		positionExtendLog.setDevId(positionExtend.getDevId());
		positionExtendLog.setDomain(positionExtend.getDomain());
		positionExtendLog.setModule(positionExtend.getModule());
		positionExtendLog.setPosition(positionExtend.getPosition());
		positionExtendLog.setPositionId(positionExtend.getPositionId());
		positionExtendLog.setProject(positionExtend.getProject());
		positionExtendLog.setUserId(positionExtend.getUserId());
		positionExtendLog.setVariety(positionExtend.getVariety());
		positionExtendLog.setCreatedBy(positionExtend.getCreatedBy());
		positionExtendLog.setLastModifiedBy(positionExtend.getLastModifiedBy());
		positionExtendLog = persistEntity(positionExtendLog);
		PositionExtendContent content = new PositionExtendContent();
		content.setExtendLogId(positionExtendLog.getId());
		content.setContent(GsonBuilderUtils.toJsonOmitnull(positionExtend));
		content = positionExtendContentService.persistEntity(content);
		positionExtendLog.setContent(content);
		
		return positionExtendLog;
	}
}
