package dml.sz0099.course.app.biz.delegate.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.product.CoeProduct;

/**
 * CoeProductDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeProductDelegate {

	/**
	 * 根据Id查询CoeProduct实体对象
	 * @param id
	 * @return
	 */
	public CoeProduct findById(Long id);
	
	public boolean existById(Long id) ;

	/**
	 * 根据IdList查询CoeProduct实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeProduct> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeProduct
	 * @return
	 */
	public CoeProduct persistEntity(CoeProduct coeProduct) ;
	
	public CoeProduct createDraft(CoeProduct coeProduct) ;
	
	public CoeProduct mergeProduct(CoeProduct coeProduct);
	public CoeProduct mergeForBaseinfo(CoeProduct coeProduct);
	
	public CoeProduct saveOrUpdate(CoeProduct coeProduct);
	
	public CoeProduct mergeShelved(CoeProduct coeProduct);
	
	public CoeProduct mergeProductForLink(CoeProduct coeProduct);
	
	public CoeProduct mergeProductForPrice(CoeProduct coeProduct);
	
	public List<CoeProduct> findByShelved(CoeProduct coeProduct);
	public Page<CoeProduct> findByShelved(CoeProduct coeProduct, Pageable pageable);
	
	public List<CoeProduct> findShelvedByName(String name);
	
	public List<CoeProduct> findShelvedByTitle(String title);
	
	public List<CoeProduct> findDraftList(CoeProduct coeProduct);
	
	public Long countDraftList(CoeProduct coeProduct) ;
	
	public CoeProduct findDetail(Long id) ;
	
	public Page<CoeProduct> findPublished(CoeProduct coeProduct, Pageable pageable) ;
	
	public CoeProduct mergeForRefresh(CoeProduct coeProduct);
	
	public CoeProduct mergeForEditQickly(CoeProduct coeProduct) ;
	
	public CoeProduct mergeForPublish(CoeProduct coeProduct);
	
	public CoeProduct mergeForClosed(CoeProduct coeProduct) ;
	
	public CoeProduct mergeForDeleted(CoeProduct coeProduct);
	
	public boolean publishedById(Long id) ;
	
}
