package dml.sz0099.course.app.biz.delegate.order;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.order.CoeOrderProductService;
import dml.sz0099.course.app.persist.entity.order.CoeOrderProduct;
import dml.sz0099.course.app.persist.entity.order.bo.CoeOrderProductBo;

/**
 * coeOrderProductServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeOrderProductDelegateImpl implements CoeOrderProductDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderProductDelegateImpl.class);
	
	@Autowired
	private CoeOrderProductService coeOrderProductService;

	/**
	 * 根据Id查询CoeOrderProduct实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeOrderProduct findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeOrderProduct coeOrderProduct = coeOrderProductService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeOrderProduct);
		return coeOrderProduct;
	}
	
	@Override
	public CoeOrderProduct findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeOrderProduct coeOrderProduct = coeOrderProductService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOrderProduct);
		return coeOrderProduct;
	}
	
	/**
	 * 根据IdList查询CoeOrderProduct实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOrderProduct> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeOrderProduct> coeOrderProductList = coeOrderProductService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeOrderProductList);
		return coeOrderProductList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeOrderProduct persistEntity(CoeOrderProduct coeOrderProduct) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeOrderProduct entity = coeOrderProductService.persistEntity(coeOrderProduct);
		Long id = coeOrderProduct.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOrderProduct mergeEntity(CoeOrderProduct coeOrderProduct) {
		Long id = coeOrderProduct.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeOrderProduct entity = coeOrderProductService.mergeEntity(coeOrderProduct);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOrderProduct saveOrUpdate(CoeOrderProduct coeOrderProduct) {
		Long id = coeOrderProduct.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeOrderProduct entity = coeOrderProductService.saveOrUpdate(coeOrderProduct);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeOrderProduct> findPage(CoeOrderProduct coeOrderProduct, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeOrderProduct> page = coeOrderProductService.findPage(coeOrderProduct, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeOrderProductService.existById(id);
	}
	
	public Page<CoeOrderProduct> findPageForMyOrderList(CoeOrderProductBo orderProductBo, Pageable pageable){
		return coeOrderProductService.findPageForMyOrderList(orderProductBo, pageable);
	}
}
