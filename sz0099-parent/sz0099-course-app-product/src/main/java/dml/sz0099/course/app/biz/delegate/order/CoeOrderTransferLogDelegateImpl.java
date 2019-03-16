package dml.sz0099.course.app.biz.delegate.order;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.order.CoeOrderTransferLogService;
import dml.sz0099.course.app.persist.entity.order.CoeOrderTransferLog;

/**
 * coeOrderTransferLogServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeOrderTransferLogDelegateImpl implements CoeOrderTransferLogDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderTransferLogDelegateImpl.class);
	
	@Autowired
	private CoeOrderTransferLogService coeOrderTransferLogService;

	/**
	 * 根据Id查询CoeOrderTransferLog实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeOrderTransferLog findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeOrderTransferLog coeOrderTransferLog = coeOrderTransferLogService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeOrderTransferLog);
		return coeOrderTransferLog;
	}
	
	@Override
	public CoeOrderTransferLog findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeOrderTransferLog coeOrderTransferLog = coeOrderTransferLogService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOrderTransferLog);
		return coeOrderTransferLog;
	}
	
	/**
	 * 根据IdList查询CoeOrderTransferLog实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOrderTransferLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeOrderTransferLog> coeOrderTransferLogList = coeOrderTransferLogService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeOrderTransferLogList);
		return coeOrderTransferLogList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeOrderTransferLog persistEntity(CoeOrderTransferLog coeOrderTransferLog) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeOrderTransferLog entity = coeOrderTransferLogService.persistEntity(coeOrderTransferLog);
		Long id = coeOrderTransferLog.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOrderTransferLog mergeEntity(CoeOrderTransferLog coeOrderTransferLog) {
		Long id = coeOrderTransferLog.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeOrderTransferLog entity = coeOrderTransferLogService.mergeEntity(coeOrderTransferLog);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOrderTransferLog saveOrUpdate(CoeOrderTransferLog coeOrderTransferLog) {
		Long id = coeOrderTransferLog.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeOrderTransferLog entity = coeOrderTransferLogService.saveOrUpdate(coeOrderTransferLog);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeOrderTransferLog> findPage(CoeOrderTransferLog coeOrderTransferLog, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeOrderTransferLog> page = coeOrderTransferLogService.findPage(coeOrderTransferLog, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeOrderTransferLogService.existById(id);
	}
}
