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

import dml.sz0099.course.app.persist.dao.order.CoeOrderProdLogDao;
import dml.sz0099.course.app.persist.entity.order.CoeOrder;
import dml.sz0099.course.app.persist.entity.order.CoeOrderProdLog;
import dml.sz0099.course.app.persist.entity.product.CoeProduct;


/**
 * 
 * @formatter:off
 * description: CoeOrderProdLogServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeOrderProdLogServiceImpl extends GenericServiceImpl<CoeOrderProdLog, Long> implements CoeOrderProdLogService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderProdLogServiceImpl.class);

	@Autowired
	private CoeOrderProdLogDao coeOrderProdLogDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeOrderProdLogDao;
	}

	/**
	 * 根据Id查询CoeOrderProdLog实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeOrderProdLog findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeOrderProdLog coeOrderProdLog = coeOrderProdLogDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeOrderProdLog);
		return coeOrderProdLog;
	}
	
	@Override
	public CoeOrderProdLog findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeOrderProdLog coeOrderProdLog = coeOrderProdLogDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOrderProdLog);
		return coeOrderProdLog;
	}

	/**
	 * 根据IdList查询CoeOrderProdLog实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOrderProdLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeOrderProdLog> coeOrderProdLogList = coeOrderProdLogDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeOrderProdLogList);
		return coeOrderProdLogList;
	}

	@Transactional
	@Override
	public CoeOrderProdLog persistEntity(CoeOrderProdLog coeOrderProdLog) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeOrderProdLog entity = save(coeOrderProdLog);
		Long id = coeOrderProdLog.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeOrderProdLog.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeOrderProdLog mergeEntity(CoeOrderProdLog coeOrderProdLog) {
		Long id = coeOrderProdLog.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeOrderProdLog entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeOrderProdLog.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeOrderProdLog.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeOrderProdLog saveOrUpdate(CoeOrderProdLog coeOrderProdLog) {
		Long id = coeOrderProdLog.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeOrderProdLog entity = null;
		if(null != id) {
			entity = mergeEntity(coeOrderProdLog);
		}else {
			entity = persistEntity(coeOrderProdLog);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeOrderProdLog> findPage(CoeOrderProdLog coeOrderProdLog, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeOrderProdLog> page = coeOrderProdLogDao.findPage(coeOrderProdLog, pageable);
		return page;
	}
	
	public CoeOrderProdLog findByOrderId(Long orderId) {
		CoeOrderProdLog entity = coeOrderProdLogDao.findByOrderId(orderId);
		
		return entity;
	}

	@Transactional
	@Override
	public CoeOrderProdLog saveOrUpdate(CoeProduct coeProduct, CoeOrder coeOrder) {
		//Long productId = coeOrder.getProductId();
		Long orderId = coeOrder.getId();
		CoeOrderProdLog entity = coeOrderProdLogDao.findByOrderId(orderId);
		if(null != entity) {
			//日志已存在，忽略更新
			return entity;
		}else {
			entity = new CoeOrderProdLog(); 
			entity.setDescription(coeProduct.getDescription());
			entity.setGrade(coeProduct.getGrade());
			entity.setMinutes(coeProduct.getMinutes());
			entity.setLink(coeProduct.getLink());
			entity.setPullMethod(coeProduct.getPullMethod());
			entity.setName(coeProduct.getName());
			entity.setOrderId(orderId);
			//entity.setProductNo(coeOrder.getProductNo());
			//entity.setPriceCur(coeOrder.getPriceCur());
			entity.setPriceOri(coeProduct.getPriceOri());
			//entity.setProductId(productId);
			entity.setStrategy(coeProduct.getStrategy());
			entity.setTitle(coeProduct.getTitle());
			entity.setCreatedBy(coeOrder.getCreatedBy());
			entity.setLastModifiedBy(coeOrder.getLastModifiedBy());
			entity.setCreatedDate(coeOrder.getLastModifiedDate());
			entity.setLastModifiedDate(coeOrder.getLastModifiedDate());
			entity.setSuccess(CoeOrderProdLog.SUCCESS_YES);
		}
		
		return entity;
	}

}
