package dml.sz0099.course.app.client.wrapper.order;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.order.CoeOrderTransferLogDelegate;
import dml.sz0099.course.app.persist.entity.order.CoeOrderTransferLog;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeOrderTransferLogWrapperImpl,组件封装
 * @author bruce yang at 2018-08-29 21:12:56
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Service
public class CoeOrderTransferLogWrapperImpl implements CoeOrderTransferLogWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderTransferLogWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeOrderTransferLogDelegate coeOrderTransferLogDelegate;
	
	/**
	 * 根据Id查询CoeOrderTransferLog实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeOrderTransferLog findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		CoeOrderTransferLog coeOrderTransferLog = coeOrderTransferLogDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, coeOrderTransferLog);
		return coeOrderTransferLog;
	}
	
	@Override
	public CoeOrderTransferLog findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeOrderTransferLog coeOrderTransferLog = coeOrderTransferLogDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOrderTransferLog);
		return coeOrderTransferLog;
	}
	
	/**
	 * 根据IdList查询CoeOrderTransferLog实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOrderTransferLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<CoeOrderTransferLog> coeOrderTransferLogList = coeOrderTransferLogDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  coeOrderTransferLogList);
		return coeOrderTransferLogList;
	}
	
	@Override
	public CoeOrderTransferLog persistEntity(CoeOrderTransferLog coeOrderTransferLog) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		CoeOrderTransferLog entity = coeOrderTransferLogDelegate.persistEntity(coeOrderTransferLog);
		Long id = coeOrderTransferLog.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOrderTransferLog mergeEntity(CoeOrderTransferLog coeOrderTransferLog) {
		Long id = coeOrderTransferLog.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		CoeOrderTransferLog entity = coeOrderTransferLogDelegate.mergeEntity(coeOrderTransferLog);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOrderTransferLog saveOrUpdate(CoeOrderTransferLog coeOrderTransferLog) {
		Long id = coeOrderTransferLog.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		CoeOrderTransferLog entity = coeOrderTransferLogDelegate.saveOrUpdate(coeOrderTransferLog);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeOrderTransferLog> findPage(CoeOrderTransferLog coeOrderTransferLog, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<CoeOrderTransferLog> page = coeOrderTransferLogDelegate.findPage(coeOrderTransferLog, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return coeOrderTransferLogDelegate.existById(id);
	}
}
