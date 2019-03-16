package dml.sz0099.course.app.client.wrapper.order;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.order.CoeOrderTransferDelegate;
import dml.sz0099.course.app.persist.entity.order.CoeOrderTransfer;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeOrderTransferWrapperImpl,组件封装
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
public class CoeOrderTransferWrapperImpl implements CoeOrderTransferWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderTransferWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeOrderTransferDelegate coeOrderTransferDelegate;
	
	/**
	 * 根据Id查询CoeOrderTransfer实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeOrderTransfer findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		CoeOrderTransfer coeOrderTransfer = coeOrderTransferDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, coeOrderTransfer);
		return coeOrderTransfer;
	}
	
	@Override
	public CoeOrderTransfer findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeOrderTransfer coeOrderTransfer = coeOrderTransferDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOrderTransfer);
		return coeOrderTransfer;
	}
	
	/**
	 * 根据IdList查询CoeOrderTransfer实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOrderTransfer> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<CoeOrderTransfer> coeOrderTransferList = coeOrderTransferDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  coeOrderTransferList);
		return coeOrderTransferList;
	}
	
	@Override
	public CoeOrderTransfer persistEntity(CoeOrderTransfer coeOrderTransfer) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		CoeOrderTransfer entity = coeOrderTransferDelegate.persistEntity(coeOrderTransfer);
		Long id = coeOrderTransfer.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOrderTransfer mergeEntity(CoeOrderTransfer coeOrderTransfer) {
		Long id = coeOrderTransfer.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		CoeOrderTransfer entity = coeOrderTransferDelegate.mergeEntity(coeOrderTransfer);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOrderTransfer saveOrUpdate(CoeOrderTransfer coeOrderTransfer) {
		Long id = coeOrderTransfer.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		CoeOrderTransfer entity = coeOrderTransferDelegate.saveOrUpdate(coeOrderTransfer);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeOrderTransfer> findPage(CoeOrderTransfer coeOrderTransfer, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<CoeOrderTransfer> page = coeOrderTransferDelegate.findPage(coeOrderTransfer, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return coeOrderTransferDelegate.existById(id);
	}
}
