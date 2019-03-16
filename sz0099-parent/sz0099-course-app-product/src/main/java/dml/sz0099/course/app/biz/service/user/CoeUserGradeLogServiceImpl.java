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

import dml.sz0099.course.app.persist.dao.user.CoeUserGradeLogDao;
import dml.sz0099.course.app.persist.entity.user.CoeUserGradeLog;


/**
 * 
 * @formatter:off
 * description: CoeUserGradeLogServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeUserGradeLogServiceImpl extends GenericServiceImpl<CoeUserGradeLog, Long> implements CoeUserGradeLogService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserGradeLogServiceImpl.class);

	@Autowired
	private CoeUserGradeLogDao coeUserGradeLogDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeUserGradeLogDao;
	}

	/**
	 * 根据Id查询CoeUserGradeLog实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeUserGradeLog findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeUserGradeLog coeUserGradeLog = coeUserGradeLogDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeUserGradeLog);
		return coeUserGradeLog;
	}
	
	@Override
	public CoeUserGradeLog findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeUserGradeLog coeUserGradeLog = coeUserGradeLogDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeUserGradeLog);
		return coeUserGradeLog;
	}

	/**
	 * 根据IdList查询CoeUserGradeLog实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeUserGradeLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeUserGradeLog> coeUserGradeLogList = coeUserGradeLogDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeUserGradeLogList);
		return coeUserGradeLogList;
	}

	@Transactional
	@Override
	public CoeUserGradeLog persistEntity(CoeUserGradeLog coeUserGradeLog) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeUserGradeLog entity = save(coeUserGradeLog);
		Long id = coeUserGradeLog.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeUserGradeLog.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeUserGradeLog mergeEntity(CoeUserGradeLog coeUserGradeLog) {
		Long id = coeUserGradeLog.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeUserGradeLog entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeUserGradeLog.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeUserGradeLog.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeUserGradeLog saveOrUpdate(CoeUserGradeLog coeUserGradeLog) {
		Long id = coeUserGradeLog.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeUserGradeLog entity = null;
		if(null != id) {
			entity = mergeEntity(coeUserGradeLog);
		}else {
			entity = persistEntity(coeUserGradeLog);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeUserGradeLog> findPage(CoeUserGradeLog coeUserGradeLog, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeUserGradeLog> page = coeUserGradeLogDao.findPage(coeUserGradeLog, pageable);
		return page;
	}

}
