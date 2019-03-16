package dml.sz0099.course.app.biz.service.order;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.jit4j.core.service.GenericServiceImpl;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.persist.dao.order.CoeOrderExpressDao;
import dml.sz0099.course.app.persist.entity.order.CoeOrder;
import dml.sz0099.course.app.persist.entity.order.CoeOrderExpress;


/**
 * 
 * @formatter:off
 * description: CoeOrderExpressServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeOrderExpressServiceImpl extends GenericServiceImpl<CoeOrderExpress, Long> implements CoeOrderExpressService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderExpressServiceImpl.class);

	@Autowired
	private CoeOrderExpressDao coeOrderExpressDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeOrderExpressDao;
	}

	/**
	 * 根据Id查询CoeOrderExpress实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeOrderExpress findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeOrderExpress coeOrderExpress = coeOrderExpressDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeOrderExpress);
		return coeOrderExpress;
	}
	
	@Override
	public CoeOrderExpress findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeOrderExpress coeOrderExpress = coeOrderExpressDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOrderExpress);
		return coeOrderExpress;
	}

	/**
	 * 根据IdList查询CoeOrderExpress实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOrderExpress> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeOrderExpress> coeOrderExpressList = coeOrderExpressDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeOrderExpressList);
		return coeOrderExpressList;
	}

	@Transactional
	@Override
	public CoeOrderExpress persistEntity(CoeOrderExpress coeOrderExpress) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeOrderExpress entity = save(coeOrderExpress);
		Long id = coeOrderExpress.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeOrderExpress.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeOrderExpress mergeEntity(CoeOrderExpress coeOrderExpress) {
		Long id = coeOrderExpress.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeOrderExpress entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeOrderExpress.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeOrderExpress.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeOrderExpress saveOrUpdate(CoeOrderExpress coeOrderExpress) {
		Long id = coeOrderExpress.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeOrderExpress entity = null;
		if(null != id) {
			entity = mergeEntity(coeOrderExpress);
		}else {
			entity = persistEntity(coeOrderExpress);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeOrderExpress> findPage(CoeOrderExpress coeOrderExpress, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeOrderExpress> page = coeOrderExpressDao.findPage(coeOrderExpress, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeOrderExpressDao.existById(id);
	}
	
	@Override
	public CoeOrderExpress findByOrderId(Long orderId) {
		LOGGER.debug("--- service>>>findByOrderId begin --------- orderId is:{} ", orderId);
		CoeOrderExpress coeOrderExpress = coeOrderExpressDao.findByOrderId(orderId);
		LOGGER.debug("--- service>>>findByOrderId end --------- orderId is:{} , result is {} ", orderId, coeOrderExpress);
		return coeOrderExpress;
	}

	@Transactional
	@Override
	public CoeOrderExpress mergeForSent(CoeOrder order) {
		Long orderId = order.getId();
		CoeOrderExpress express = findByOrderId(orderId);
		if(null == express) {
			express = new CoeOrderExpress();
		}
		express.setCreatedBy(order.getCreatedBy());
		express.setLastModifiedBy(express.getLastModifiedBy());
		express.setLastModifiedDate(order.getLastModifiedDate());
		//express.setAddress(address);
		return express;
	}
	
	public CoeOrderExpress confirmOrder(CoeOrder order) {
		//确认下单
		return null;
	}

}
