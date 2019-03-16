package dml.sz0099.course.app.client.wrapper.product;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.order.CoeOrderProdLogDelegate;
import dml.sz0099.course.app.persist.entity.order.CoeOrderProdLog;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeOrderProdLogWrapperImpl,组件封装
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
public class CoeOrderProdLogWrapperImpl implements CoeOrderProdLogWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderProdLogWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeOrderProdLogDelegate coeOrderProdLogDelegate;
	
	/**
	 * 根据Id查询CoeOrderProdLog实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeOrderProdLog findById(Long id) {
		LOGGER.debug("--- CoeOrderProdLogWrapperImpl.findById begin --------- id is:{} ", id);
		CoeOrderProdLog coeOrderProdLog = coeOrderProdLogDelegate.findById(id);
		LOGGER.debug("--- CoeOrderProdLogWrapperImpl.findById end --------- id is:{} , result is {} ", id, coeOrderProdLog);
		return coeOrderProdLog;
	}
	
	@Override
	public CoeOrderProdLog findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeOrderProdLog coeOrderProdLog = coeOrderProdLogDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOrderProdLog);
		return coeOrderProdLog;
	}
	
	/**
	 * 根据IdList查询CoeOrderProdLog实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOrderProdLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- CoeOrderProdLogWrapperImpl.findByIdList begin ---------  ");
		List<CoeOrderProdLog> coeOrderProdLogList = coeOrderProdLogDelegate.findByIdList(idList);
		LOGGER.debug("--- CoeOrderProdLogWrapperImpl.findByIdList end ---------  result is {} ",  coeOrderProdLogList);
		return coeOrderProdLogList;
	}
	
	@Override
	public CoeOrderProdLog persistEntity(CoeOrderProdLog coeOrderProdLog) {
		LOGGER.debug("--- CoeOrderProdLogWrapperImpl.persistEntity begin ---------  ");
		CoeOrderProdLog entity = coeOrderProdLogDelegate.persistEntity(coeOrderProdLog);
		Long id = coeOrderProdLog.getId();
		LOGGER.debug("--- CoeOrderProdLogWrapperImpl.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOrderProdLog mergeEntity(CoeOrderProdLog coeOrderProdLog) {
		Long id = coeOrderProdLog.getId();
		LOGGER.debug("--- CoeOrderProdLogWrapperImpl.mergeEntity begin, id is {} ---------  ",id);
		CoeOrderProdLog entity = coeOrderProdLogDelegate.mergeEntity(coeOrderProdLog);
		LOGGER.debug("--- CoeOrderProdLogWrapperImpl.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOrderProdLog saveOrUpdate(CoeOrderProdLog coeOrderProdLog) {
		Long id = coeOrderProdLog.getId();
		LOGGER.debug("--- CoeOrderProdLogWrapperImpl.saveOrUpdate begin, id is {} ---------  ",id);
		CoeOrderProdLog entity = coeOrderProdLogDelegate.saveOrUpdate(coeOrderProdLog);
		LOGGER.debug("--- CoeOrderProdLogWrapperImpl.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeOrderProdLog> findPage(CoeOrderProdLog coeOrderProdLog, Pageable pageable) {
		LOGGER.debug("--- CoeOrderProdLogWrapperImpl.findPage ---------  ");
		Page<CoeOrderProdLog> page = coeOrderProdLogDelegate.findPage(coeOrderProdLog, pageable);
		return page;
	}
}
