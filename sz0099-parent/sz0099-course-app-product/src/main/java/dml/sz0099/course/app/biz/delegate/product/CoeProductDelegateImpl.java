package dml.sz0099.course.app.biz.delegate.product;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.product.CoeProductService;
import dml.sz0099.course.app.persist.entity.product.CoeProduct;

/**
 * CoeProductDelegateImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeProductDelegateImpl implements CoeProductDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeProductDelegateImpl.class);
	
	@Autowired
	private CoeProductService coeProductService;

	/**
	 * 根据Id查询CoeProduct实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeProduct findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeProduct coeProduct = coeProductService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeProduct);
		return coeProduct;
	}
	
	public boolean existById(Long id) {
		return coeProductService.existById(id);
	}
	
	/**
	 * 根据IdList查询CoeProduct实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeProduct> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeProduct> coeProductList = coeProductService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeProductList);
		return coeProductList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeProduct persistEntity(CoeProduct coeProduct) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeProduct entity = coeProductService.persistEntity(coeProduct);
		Long id = coeProduct.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}
	
	public CoeProduct createDraft(CoeProduct coeProduct) {
		
		CoeProduct entity = coeProductService.createDraft(coeProduct);
		return entity;
	}

	@Override
	public CoeProduct mergeProduct(CoeProduct coeProduct) {
		return coeProductService.mergeProduct(coeProduct);
	}
	@Override
	public CoeProduct mergeForBaseinfo(CoeProduct coeProduct) {
		return coeProductService.mergeForBaseinfo(coeProduct);
	}

	@Override
	public CoeProduct saveOrUpdate(CoeProduct coeProduct) {
		return coeProductService.saveOrUpdate(coeProduct);
	}

	@Override
	public CoeProduct mergeShelved(CoeProduct coeProduct) {
		return coeProductService.mergeShelved(coeProduct);
	}

	@Override
	public CoeProduct mergeProductForLink(CoeProduct coeProduct) {
		return coeProductService.mergeProductForLink(coeProduct);
	}

	@Override
	public CoeProduct mergeProductForPrice(CoeProduct coeProduct) {
		return coeProductService.mergeProductForPrice(coeProduct);
	}

	@Override
	public List<CoeProduct> findByShelved(CoeProduct coeProduct) {
		return coeProductService.findByShelved(coeProduct);
	}
	@Override
	public Page<CoeProduct> findByShelved(CoeProduct coeProduct, Pageable pageable){
		return coeProductService.findByShelved(coeProduct,pageable);
	}

	@Override
	public List<CoeProduct> findShelvedByName(String name) {
		return coeProductService.findShelvedByName(name);
	}

	@Override
	public List<CoeProduct> findShelvedByTitle(String title) {
		return coeProductService.findShelvedByTitle(title);
	}
	
	@Override
	public List<CoeProduct> findDraftList(CoeProduct coeProduct){
		return coeProductService.findDraftList(coeProduct);
	}
	
	@Override
	public Long countDraftList(CoeProduct coeProduct) {
		return coeProductService.countDraftList(coeProduct);
	}
	
	public CoeProduct findDetail(Long id) {
		return coeProductService.findDetail(id);
	}
	
	public Page<CoeProduct> findPublished(CoeProduct coeProduct, Pageable pageable) {
		return coeProductService.findPublished(coeProduct,pageable);
	}
	
	public CoeProduct mergeForRefresh(CoeProduct coeProduct) {
		return coeProductService.mergeForRefresh(coeProduct);
	}
	
	public CoeProduct mergeForEditQickly(CoeProduct coeProduct) {
		return coeProductService.mergeForEditQickly(coeProduct);
	}
	
	public CoeProduct mergeForPublish(CoeProduct coeProduct) {
		return coeProductService.mergeForPublish(coeProduct);
	}
	
	public CoeProduct mergeForClosed(CoeProduct coeProduct) {
		return coeProductService.mergeForClosed(coeProduct);
	}
	
	public CoeProduct mergeForDeleted(CoeProduct coeProduct) {
		return coeProductService.mergeForDeleted(coeProduct);
	}
	
	public boolean publishedById(Long id) {
		boolean published = coeProductService.publishedById(id);
		return published;
	}
}
