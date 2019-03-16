package dml.sz0099.course.app.persist.dao.product;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.product.CoeCategProd;

/**
 * CoeCategProdDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeCategProdDao extends GenericDao<CoeCategProd,Long>{

	/**
	 * 根据Id查询CoeCategProd实体对象
	 * @param id
	 * @return
	 */
	CoeCategProd findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeCategProd findByAid(Long aid);
	
	
	
	
	/**
	 * 根据IdList查询CoeCategProd实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeCategProd> findByIdList(List<Long> idList);
	
	public Page<CoeCategProd> findPage(CoeCategProd coeCategProd, Pageable pageable);
	
	public List<CoeCategProd> findByMainIdList(List<Long> productIdList) ;
	
	public List<CoeCategProd> findByMainId(Long productId);
	
}
