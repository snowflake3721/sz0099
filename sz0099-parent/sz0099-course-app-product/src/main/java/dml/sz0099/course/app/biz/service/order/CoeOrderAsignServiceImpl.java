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

import dml.sz0099.course.app.persist.dao.order.CoeOrderAsignDao;
import dml.sz0099.course.app.persist.entity.order.CoeOrder;
import dml.sz0099.course.app.persist.entity.order.CoeOrderAsign;


/**
 * 
 * @formatter:off
 * description: CoeOrderAsignServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeOrderAsignServiceImpl extends GenericServiceImpl<CoeOrderAsign, Long> implements CoeOrderAsignService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderAsignServiceImpl.class);

	@Autowired
	private CoeOrderAsignDao coeOrderAsignDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeOrderAsignDao;
	}

	/**
	 * 根据Id查询CoeOrderAsign实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeOrderAsign findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeOrderAsign coeOrderAsign = coeOrderAsignDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeOrderAsign);
		return coeOrderAsign;
	}
	
	@Override
	public CoeOrderAsign findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeOrderAsign coeOrderAsign = coeOrderAsignDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOrderAsign);
		return coeOrderAsign;
	}

	/**
	 * 根据IdList查询CoeOrderAsign实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOrderAsign> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeOrderAsign> coeOrderAsignList = coeOrderAsignDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeOrderAsignList);
		return coeOrderAsignList;
	}

	@Transactional
	@Override
	public CoeOrderAsign persistEntity(CoeOrderAsign coeOrderAsign) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeOrderAsign entity = save(coeOrderAsign);
		Long id = coeOrderAsign.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeOrderAsign.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeOrderAsign mergeEntity(CoeOrderAsign coeOrderAsign) {
		Long id = coeOrderAsign.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeOrderAsign entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeOrderAsign.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeOrderAsign.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeOrderAsign saveOrUpdate(CoeOrderAsign coeOrderAsign) {
		Long id = coeOrderAsign.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeOrderAsign entity = null;
		if(null != id) {
			entity = mergeEntity(coeOrderAsign);
		}else {
			entity = persistEntity(coeOrderAsign);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeOrderAsign> findPage(CoeOrderAsign coeOrderAsign, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeOrderAsign> page = coeOrderAsignDao.findPage(coeOrderAsign, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeOrderAsignDao.existById(id);
	}

	@Transactional
	@Override
	public CoeOrderAsign mergeForInprocess(CoeOrder order) {
		if(null != order) {
			Long orderId = order.getId();
			CoeOrderAsign orderAsign = findById(orderId);//关系为一对一，强行设置该id与订单id相同
			if(null == orderAsign) {
				orderAsign = new CoeOrderAsign();
				orderAsign.setId(orderId);
			}
			DateTime dateTime = new DateTime();
			orderAsign.setBeginTime(dateTime.toDate());
			orderAsign.setFlowNo(order.getFlowNo());
			orderAsign.setFinalResolverId(order.getResolverId());
			orderAsign.setOrderId(orderId);
			orderAsign.setOwnerId(order.getOwnerId());
			orderAsign.setPrice(order.getPrice());
			orderAsign.setPriceVerify(order.getPrice());//初始化为相同，等待审核校验时校对
			//orderAsign.setProductId(order.getProductId());
			//orderAsign.setProductNo(order.getProductNo());
			orderAsign.setResolverId(order.getResolverId());
			orderAsign.setUserId(order.getUserId());
			orderAsign.setVerifyStatus(CoeOrderAsign.VERIFY_STATUS_INIT.getValueInt());
			
			orderAsign.setCreatedBy(order.getCreatedBy());
			orderAsign.setLastModifiedBy(order.getLastModifiedBy());
			orderAsign.setLastModifiedDate(dateTime);
			
			orderAsign = persistEntity(orderAsign);
			orderAsign.setSuccess(CoeOrderAsign.SUCCESS_YES);
			
			return orderAsign;
		}
		
		return null;
	}

	@Transactional
	@Override
	public CoeOrderAsign mergeForSent(CoeOrder order) {
		if(null != order) {
			Long orderId = order.getId();
			CoeOrderAsign orderAsign = findById(orderId);
			DateTime dateTime = new DateTime();
			if(null != orderAsign) {
				orderAsign.setFinalResolverId(order.getResolverId());
				
				orderAsign.setEndTime(dateTime.toDate());
				orderAsign.setVerifyStatus(CoeOrderAsign.VERIFY_STATUS_HAS_VERIFIED.getValueInt());
				orderAsign.setLastModifiedBy(order.getLastModifiedBy());
				orderAsign.setLastModifiedDate(dateTime);
				
				orderAsign = save(orderAsign);
				orderAsign.setSuccess(CoeOrderAsign.SUCCESS_YES);
			}
			
			return orderAsign;
		}
		return null;
	}
	
	public CoeOrderAsign findByOrderId(Long orderId) {
		CoeOrderAsign entity = coeOrderAsignDao.findByOrderId(orderId);
		return entity;
	}

}
