package dml.sz0099.course.app.persist.dao.product;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.product.CoeOpration;

/**
 * CoeOprationDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeOprationDao extends GenericDao<CoeOpration,Long>{

	/**
	 * 根据Id查询CoeOpration实体对象
	 * @param id
	 * @return
	 */
	CoeOpration findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeOpration findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeOpration实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeOpration> findByIdList(List<Long> idList);
	
	public Page<CoeOpration> findPage(CoeOpration coeOpration, Pageable pageable);
	
}
