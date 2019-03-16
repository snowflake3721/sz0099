package dml.sz0099.course.app.biz.service.product;

import java.util.List;
import java.util.Map;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.product.CoeCategProd;

/**
 * CoeCategProdService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeCategProdService extends GenericService<CoeCategProd,Long>{


	/**
	 * 根据Id查询CoeCategProd实体对象
	 * @param id
	 * @return
	 */
	public CoeCategProd findById(Long id);
	
	public boolean existById(Long id);
	
	public CoeCategProd findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeCategProd实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeCategProd> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeCategProd
	 * @return
	 */
	public CoeCategProd persistEntity(CoeCategProd coeCategProd) ;
	
	
	public CoeCategProd mergeEntity(CoeCategProd coeCategProd) ; 
	
	public CoeCategProd saveOrUpdate(CoeCategProd coeCategProd) ;
	
	public Page<CoeCategProd> findPage(CoeCategProd coeCategProd, Pageable pageable) ;
	
	public Map<Long, List<CoeCategProd>> findMapByMainIdList(List<Long> productIdList);
	
	public List<CoeCategProd> findByMainId(Long productId);
	
	public CoeCategProd changeCategory(CoeCategProd coeCategProd);
}
