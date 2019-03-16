package dml.sz0099.course.app.biz.delegate.order;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.order.CoeOrderExpressService;
import dml.sz0099.course.app.persist.entity.order.CoeOrderExpress;

/**
 * coeOrderExpressServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeOrderExpressDelegateImpl implements CoeOrderExpressDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderExpressDelegateImpl.class);
	
	@Autowired
	private CoeOrderExpressService coeOrderExpressService;

	/**
	 * 根据Id查询CoeOrderExpress实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeOrderExpress findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeOrderExpress coeOrderExpress = coeOrderExpressService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeOrderExpress);
		return coeOrderExpress;
	}
	
	@Override
	public CoeOrderExpress findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeOrderExpress coeOrderExpress = coeOrderExpressService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOrderExpress);
		return coeOrderExpress;
	}
	
	/**
	 * 根据IdList查询CoeOrderExpress实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOrderExpress> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeOrderExpress> coeOrderExpressList = coeOrderExpressService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeOrderExpressList);
		return coeOrderExpressList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeOrderExpress persistEntity(CoeOrderExpress coeOrderExpress) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeOrderExpress entity = coeOrderExpressService.persistEntity(coeOrderExpress);
		Long id = coeOrderExpress.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOrderExpress mergeEntity(CoeOrderExpress coeOrderExpress) {
		Long id = coeOrderExpress.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeOrderExpress entity = coeOrderExpressService.mergeEntity(coeOrderExpress);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOrderExpress saveOrUpdate(CoeOrderExpress coeOrderExpress) {
		Long id = coeOrderExpress.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeOrderExpress entity = coeOrderExpressService.saveOrUpdate(coeOrderExpress);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeOrderExpress> findPage(CoeOrderExpress coeOrderExpress, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeOrderExpress> page = coeOrderExpressService.findPage(coeOrderExpress, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeOrderExpressService.existById(id);
	}
}
