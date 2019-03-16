package dml.sz0099.course.app.biz.delegate.order;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.order.CoeOrderProdLogService;
import dml.sz0099.course.app.persist.entity.order.CoeOrderProdLog;

/**
 * coeOrderProdLogServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeOrderProdLogDelegateImpl implements CoeOrderProdLogDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderProdLogDelegateImpl.class);
	
	@Autowired
	private CoeOrderProdLogService coeOrderProdLogService;

	/**
	 * 根据Id查询CoeOrderProdLog实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeOrderProdLog findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeOrderProdLog coeOrderProdLog = coeOrderProdLogService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeOrderProdLog);
		return coeOrderProdLog;
	}
	
	@Override
	public CoeOrderProdLog findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeOrderProdLog coeOrderProdLog = coeOrderProdLogService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOrderProdLog);
		return coeOrderProdLog;
	}
	
	/**
	 * 根据IdList查询CoeOrderProdLog实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOrderProdLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeOrderProdLog> coeOrderProdLogList = coeOrderProdLogService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeOrderProdLogList);
		return coeOrderProdLogList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeOrderProdLog persistEntity(CoeOrderProdLog coeOrderProdLog) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeOrderProdLog entity = coeOrderProdLogService.persistEntity(coeOrderProdLog);
		Long id = coeOrderProdLog.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOrderProdLog mergeEntity(CoeOrderProdLog coeOrderProdLog) {
		Long id = coeOrderProdLog.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeOrderProdLog entity = coeOrderProdLogService.mergeEntity(coeOrderProdLog);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOrderProdLog saveOrUpdate(CoeOrderProdLog coeOrderProdLog) {
		Long id = coeOrderProdLog.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeOrderProdLog entity = coeOrderProdLogService.saveOrUpdate(coeOrderProdLog);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeOrderProdLog> findPage(CoeOrderProdLog coeOrderProdLog, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeOrderProdLog> page = coeOrderProdLogService.findPage(coeOrderProdLog, pageable);
		return page;
	}
}
