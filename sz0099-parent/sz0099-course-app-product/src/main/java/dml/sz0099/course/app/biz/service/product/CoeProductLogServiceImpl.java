package dml.sz0099.course.app.biz.service.product;

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

import dml.sz0099.course.app.persist.dao.product.CoeProductLogDao;
import dml.sz0099.course.app.persist.entity.product.CoeProductLog;


/**
 * 
 * @formatter:off
 * description: CoeProductLogServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeProductLogServiceImpl extends GenericServiceImpl<CoeProductLog, Long> implements CoeProductLogService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeProductLogServiceImpl.class);

	@Autowired
	private CoeProductLogDao coeProductLogDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeProductLogDao;
	}

	/**
	 * 根据Id查询CoeProductLog实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeProductLog findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeProductLog coeProductLog = coeProductLogDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeProductLog);
		return coeProductLog;
	}
	
	@Override
	public CoeProductLog findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeProductLog coeProductLog = coeProductLogDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeProductLog);
		return coeProductLog;
	}

	/**
	 * 根据IdList查询CoeProductLog实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeProductLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeProductLog> coeProductLogList = coeProductLogDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeProductLogList);
		return coeProductLogList;
	}

	@Transactional
	@Override
	public CoeProductLog persistEntity(CoeProductLog coeProductLog) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeProductLog entity = save(coeProductLog);
		Long id = coeProductLog.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeProductLog.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeProductLog mergeEntity(CoeProductLog coeProductLog) {
		Long id = coeProductLog.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeProductLog entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeProductLog.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeProductLog.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeProductLog saveOrUpdate(CoeProductLog coeProductLog) {
		Long id = coeProductLog.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeProductLog entity = null;
		if(null != id) {
			entity = mergeEntity(coeProductLog);
		}else {
			entity = persistEntity(coeProductLog);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeProductLog> findPage(CoeProductLog coeProductLog, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeProductLog> page = coeProductLogDao.findPage(coeProductLog, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeProductLogDao.existById(id);
	}

}
