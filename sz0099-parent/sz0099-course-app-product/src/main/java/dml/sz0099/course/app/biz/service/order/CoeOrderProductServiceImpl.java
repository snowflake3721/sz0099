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

import dml.sz0099.course.app.persist.dao.order.CoeOrderProductDao;
import dml.sz0099.course.app.persist.entity.order.CoeOrderProduct;
import dml.sz0099.course.app.persist.entity.order.bo.CoeOrderProductBo;


/**
 * 
 * @formatter:off
 * description: CoeOrderProductServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeOrderProductServiceImpl extends GenericServiceImpl<CoeOrderProduct, Long> implements CoeOrderProductService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderProductServiceImpl.class);

	@Autowired
	private CoeOrderProductDao coeOrderProductDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeOrderProductDao;
	}

	/**
	 * 根据Id查询CoeOrderProduct实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeOrderProduct findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeOrderProduct coeOrderProduct = coeOrderProductDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeOrderProduct);
		return coeOrderProduct;
	}
	
	@Override
	public CoeOrderProduct findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeOrderProduct coeOrderProduct = coeOrderProductDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOrderProduct);
		return coeOrderProduct;
	}

	/**
	 * 根据IdList查询CoeOrderProduct实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOrderProduct> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeOrderProduct> coeOrderProductList = coeOrderProductDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeOrderProductList);
		return coeOrderProductList;
	}

	@Transactional
	@Override
	public CoeOrderProduct persistEntity(CoeOrderProduct coeOrderProduct) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeOrderProduct entity = save(coeOrderProduct);
		Long id = coeOrderProduct.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeOrderProduct.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeOrderProduct mergeEntity(CoeOrderProduct coeOrderProduct) {
		Long id = coeOrderProduct.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeOrderProduct entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeOrderProduct.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeOrderProduct.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeOrderProduct saveOrUpdate(CoeOrderProduct coeOrderProduct) {
		Long id = coeOrderProduct.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeOrderProduct entity = null;
		if(null != id) {
			entity = mergeEntity(coeOrderProduct);
		}else {
			entity = persistEntity(coeOrderProduct);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeOrderProduct> findPage(CoeOrderProduct coeOrderProduct, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeOrderProduct> page = coeOrderProductDao.findPage(coeOrderProduct, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeOrderProductDao.existById(id);
	}

	@Override
	public List<CoeOrderProduct> persistEntityList(List<CoeOrderProduct> entityList) {
		entityList = save(entityList);
		return entityList;
	}

	@Override
	public Page<CoeOrderProduct> findPageForMyOrderList(CoeOrderProductBo orderProductBo, Pageable pageable) {
		return coeOrderProductDao.findPageForMyOrderList(orderProductBo, pageable);
	}

	@Override
	public List<CoeOrderProduct> findByOrderIdList(List<Long> orderIdList) {
		return coeOrderProductDao.findByOrderIdList(orderIdList);
	}
	
	public List<CoeOrderProduct> findByOrderId(Long orderId) {
		return coeOrderProductDao.findByOrderId(orderId);
	}

}
