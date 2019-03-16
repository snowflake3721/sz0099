package dml.sz0099.course.app.client.wrapper.product;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.product.CoeProductLogDelegate;
import dml.sz0099.course.app.persist.entity.product.CoeProductLog;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeProductLogWrapperImpl,组件封装
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
public class CoeProductLogWrapperImpl implements CoeProductLogWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeProductLogWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeProductLogDelegate coeProductLogDelegate;
	
	/**
	 * 根据Id查询CoeProductLog实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeProductLog findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		CoeProductLog coeProductLog = coeProductLogDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, coeProductLog);
		return coeProductLog;
	}
	
	@Override
	public CoeProductLog findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeProductLog coeProductLog = coeProductLogDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeProductLog);
		return coeProductLog;
	}
	
	/**
	 * 根据IdList查询CoeProductLog实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeProductLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<CoeProductLog> coeProductLogList = coeProductLogDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  coeProductLogList);
		return coeProductLogList;
	}
	
	@Override
	public CoeProductLog persistEntity(CoeProductLog coeProductLog) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		CoeProductLog entity = coeProductLogDelegate.persistEntity(coeProductLog);
		Long id = coeProductLog.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeProductLog mergeEntity(CoeProductLog coeProductLog) {
		Long id = coeProductLog.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		CoeProductLog entity = coeProductLogDelegate.mergeEntity(coeProductLog);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeProductLog saveOrUpdate(CoeProductLog coeProductLog) {
		Long id = coeProductLog.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		CoeProductLog entity = coeProductLogDelegate.saveOrUpdate(coeProductLog);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeProductLog> findPage(CoeProductLog coeProductLog, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<CoeProductLog> page = coeProductLogDelegate.findPage(coeProductLog, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return coeProductLogDelegate.existById(id);
	}
}
