package dml.sz0099.course.app.client.wrapper.order;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.order.CoeOrderProductDelegate;
import dml.sz0099.course.app.persist.entity.order.CoeOrderProduct;
import dml.sz0099.course.app.persist.entity.order.bo.CoeOrderProductBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeOrderProductWrapperImpl,组件封装
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
public class CoeOrderProductWrapperImpl implements CoeOrderProductWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderProductWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeOrderProductDelegate coeOrderProductDelegate;
	
	/**
	 * 根据Id查询CoeOrderProduct实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeOrderProduct findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		CoeOrderProduct coeOrderProduct = coeOrderProductDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, coeOrderProduct);
		return coeOrderProduct;
	}
	
	@Override
	public CoeOrderProduct findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeOrderProduct coeOrderProduct = coeOrderProductDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOrderProduct);
		return coeOrderProduct;
	}
	
	/**
	 * 根据IdList查询CoeOrderProduct实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOrderProduct> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<CoeOrderProduct> coeOrderProductList = coeOrderProductDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  coeOrderProductList);
		return coeOrderProductList;
	}
	
	@Override
	public CoeOrderProduct persistEntity(CoeOrderProduct coeOrderProduct) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		CoeOrderProduct entity = coeOrderProductDelegate.persistEntity(coeOrderProduct);
		Long id = coeOrderProduct.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOrderProduct mergeEntity(CoeOrderProduct coeOrderProduct) {
		Long id = coeOrderProduct.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		CoeOrderProduct entity = coeOrderProductDelegate.mergeEntity(coeOrderProduct);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOrderProduct saveOrUpdate(CoeOrderProduct coeOrderProduct) {
		Long id = coeOrderProduct.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		CoeOrderProduct entity = coeOrderProductDelegate.saveOrUpdate(coeOrderProduct);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeOrderProduct> findPage(CoeOrderProduct coeOrderProduct, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<CoeOrderProduct> page = coeOrderProductDelegate.findPage(coeOrderProduct, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return coeOrderProductDelegate.existById(id);
	}
	
	public Page<CoeOrderProduct> findPageForMyOrderList(CoeOrderProductBo orderProductBo, Pageable pageable){
		return coeOrderProductDelegate.findPageForMyOrderList(orderProductBo, pageable);
	}
}
