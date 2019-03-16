package dml.sz0099.course.app.biz.service.order;

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

import dml.sz0099.course.app.persist.dao.order.CoeOrderTransferLogDao;
import dml.sz0099.course.app.persist.entity.order.CoeOrderTransferLog;


/**
 * 
 * @formatter:off
 * description: CoeOrderTransferLogServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeOrderTransferLogServiceImpl extends GenericServiceImpl<CoeOrderTransferLog, Long> implements CoeOrderTransferLogService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderTransferLogServiceImpl.class);

	@Autowired
	private CoeOrderTransferLogDao coeOrderTransferLogDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeOrderTransferLogDao;
	}

	/**
	 * 根据Id查询CoeOrderTransferLog实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeOrderTransferLog findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeOrderTransferLog coeOrderTransferLog = coeOrderTransferLogDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeOrderTransferLog);
		return coeOrderTransferLog;
	}
	
	@Override
	public CoeOrderTransferLog findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeOrderTransferLog coeOrderTransferLog = coeOrderTransferLogDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOrderTransferLog);
		return coeOrderTransferLog;
	}

	/**
	 * 根据IdList查询CoeOrderTransferLog实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOrderTransferLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeOrderTransferLog> coeOrderTransferLogList = coeOrderTransferLogDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeOrderTransferLogList);
		return coeOrderTransferLogList;
	}

	@Transactional
	@Override
	public CoeOrderTransferLog persistEntity(CoeOrderTransferLog coeOrderTransferLog) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeOrderTransferLog entity = save(coeOrderTransferLog);
		Long id = coeOrderTransferLog.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeOrderTransferLog.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeOrderTransferLog mergeEntity(CoeOrderTransferLog coeOrderTransferLog) {
		Long id = coeOrderTransferLog.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeOrderTransferLog entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeOrderTransferLog.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeOrderTransferLog.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeOrderTransferLog saveOrUpdate(CoeOrderTransferLog coeOrderTransferLog) {
		Long id = coeOrderTransferLog.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeOrderTransferLog entity = null;
		if(null != id) {
			entity = mergeEntity(coeOrderTransferLog);
		}else {
			entity = persistEntity(coeOrderTransferLog);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeOrderTransferLog> findPage(CoeOrderTransferLog coeOrderTransferLog, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeOrderTransferLog> page = coeOrderTransferLogDao.findPage(coeOrderTransferLog, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeOrderTransferLogDao.existById(id);
	}

}
