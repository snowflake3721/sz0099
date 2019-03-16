package dml.sz0099.course.app.biz.delegate.order;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.order.CoeOrderTransferService;
import dml.sz0099.course.app.persist.entity.order.CoeOrderTransfer;

/**
 * coeOrderTransferServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeOrderTransferDelegateImpl implements CoeOrderTransferDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderTransferDelegateImpl.class);
	
	@Autowired
	private CoeOrderTransferService coeOrderTransferService;

	/**
	 * 根据Id查询CoeOrderTransfer实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeOrderTransfer findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeOrderTransfer coeOrderTransfer = coeOrderTransferService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeOrderTransfer);
		return coeOrderTransfer;
	}
	
	@Override
	public CoeOrderTransfer findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeOrderTransfer coeOrderTransfer = coeOrderTransferService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOrderTransfer);
		return coeOrderTransfer;
	}
	
	/**
	 * 根据IdList查询CoeOrderTransfer实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOrderTransfer> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeOrderTransfer> coeOrderTransferList = coeOrderTransferService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeOrderTransferList);
		return coeOrderTransferList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeOrderTransfer persistEntity(CoeOrderTransfer coeOrderTransfer) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeOrderTransfer entity = coeOrderTransferService.persistEntity(coeOrderTransfer);
		Long id = coeOrderTransfer.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOrderTransfer mergeEntity(CoeOrderTransfer coeOrderTransfer) {
		Long id = coeOrderTransfer.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeOrderTransfer entity = coeOrderTransferService.mergeEntity(coeOrderTransfer);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOrderTransfer saveOrUpdate(CoeOrderTransfer coeOrderTransfer) {
		Long id = coeOrderTransfer.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeOrderTransfer entity = coeOrderTransferService.saveOrUpdate(coeOrderTransfer);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeOrderTransfer> findPage(CoeOrderTransfer coeOrderTransfer, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeOrderTransfer> page = coeOrderTransferService.findPage(coeOrderTransfer, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeOrderTransferService.existById(id);
	}
}
