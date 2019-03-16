package dml.sz0099.course.app.client.wrapper.order;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.order.CoeOrderExpressDelegate;
import dml.sz0099.course.app.persist.entity.order.CoeOrderExpress;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeOrderExpressWrapperImpl,组件封装
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
public class CoeOrderExpressWrapperImpl implements CoeOrderExpressWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderExpressWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeOrderExpressDelegate coeOrderExpressDelegate;
	
	/**
	 * 根据Id查询CoeOrderExpress实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeOrderExpress findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		CoeOrderExpress coeOrderExpress = coeOrderExpressDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, coeOrderExpress);
		return coeOrderExpress;
	}
	
	@Override
	public CoeOrderExpress findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeOrderExpress coeOrderExpress = coeOrderExpressDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOrderExpress);
		return coeOrderExpress;
	}
	
	/**
	 * 根据IdList查询CoeOrderExpress实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOrderExpress> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<CoeOrderExpress> coeOrderExpressList = coeOrderExpressDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  coeOrderExpressList);
		return coeOrderExpressList;
	}
	
	@Override
	public CoeOrderExpress persistEntity(CoeOrderExpress coeOrderExpress) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		CoeOrderExpress entity = coeOrderExpressDelegate.persistEntity(coeOrderExpress);
		Long id = coeOrderExpress.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOrderExpress mergeEntity(CoeOrderExpress coeOrderExpress) {
		Long id = coeOrderExpress.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		CoeOrderExpress entity = coeOrderExpressDelegate.mergeEntity(coeOrderExpress);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOrderExpress saveOrUpdate(CoeOrderExpress coeOrderExpress) {
		Long id = coeOrderExpress.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		CoeOrderExpress entity = coeOrderExpressDelegate.saveOrUpdate(coeOrderExpress);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeOrderExpress> findPage(CoeOrderExpress coeOrderExpress, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<CoeOrderExpress> page = coeOrderExpressDelegate.findPage(coeOrderExpress, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return coeOrderExpressDelegate.existById(id);
	}
}
