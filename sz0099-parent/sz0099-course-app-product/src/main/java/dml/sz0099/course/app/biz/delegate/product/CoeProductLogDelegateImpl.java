package dml.sz0099.course.app.biz.delegate.product;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.product.CoeProductLogService;
import dml.sz0099.course.app.persist.entity.product.CoeProductLog;

/**
 * coeProductLogServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeProductLogDelegateImpl implements CoeProductLogDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeProductLogDelegateImpl.class);
	
	@Autowired
	private CoeProductLogService coeProductLogService;

	/**
	 * 根据Id查询CoeProductLog实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeProductLog findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeProductLog coeProductLog = coeProductLogService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeProductLog);
		return coeProductLog;
	}
	
	@Override
	public CoeProductLog findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeProductLog coeProductLog = coeProductLogService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeProductLog);
		return coeProductLog;
	}
	
	/**
	 * 根据IdList查询CoeProductLog实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeProductLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeProductLog> coeProductLogList = coeProductLogService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeProductLogList);
		return coeProductLogList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeProductLog persistEntity(CoeProductLog coeProductLog) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeProductLog entity = coeProductLogService.persistEntity(coeProductLog);
		Long id = coeProductLog.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeProductLog mergeEntity(CoeProductLog coeProductLog) {
		Long id = coeProductLog.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeProductLog entity = coeProductLogService.mergeEntity(coeProductLog);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeProductLog saveOrUpdate(CoeProductLog coeProductLog) {
		Long id = coeProductLog.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeProductLog entity = coeProductLogService.saveOrUpdate(coeProductLog);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeProductLog> findPage(CoeProductLog coeProductLog, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeProductLog> page = coeProductLogService.findPage(coeProductLog, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeProductLogService.existById(id);
	}
}
