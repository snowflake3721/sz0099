package dml.sz0099.course.app.persist.dao.product;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.product.CoeProduct;

/**
 * CoeProductDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeProductDao extends GenericDao<CoeProduct,Long>{

	/**
	 * 根据Id查询CoeProduct实体对象
	 * @param id
	 * @return
	 */
	CoeProduct findById(Long id);
	
	/**
	 * 根据IdList查询CoeProduct实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeProduct> findByIdList(List<Long> idList);
	
	public List<CoeProduct> findDraftList(CoeProduct coeProduct);
	
	public Long countDraftList(CoeProduct coeProduct);
	
	public List<CoeProduct> findShelvedByName(String name);
	
	public List<CoeProduct> findShelvedByTitle(String title);
	
	public Page<CoeProduct> findByShelved(CoeProduct coeProduct, Pageable pageable);
	
	public Page<CoeProduct> findPublished(CoeProduct coeProduct, Pageable pageable);
	
}
