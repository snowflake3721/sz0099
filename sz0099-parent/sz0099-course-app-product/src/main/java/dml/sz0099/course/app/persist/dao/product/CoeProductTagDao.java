package dml.sz0099.course.app.persist.dao.product;

import java.util.List;
import java.util.Map;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.product.CoeProductTag;

/**
 * CoeProductTagDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeProductTagDao extends GenericDao<CoeProductTag,Long>{

	/**
	 * 根据Id查询CoeProductTag实体对象
	 * @param id
	 * @return
	 */
	CoeProductTag findById(Long id);
	
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeProductTag findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeProductTag实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeProductTag> findByIdList(List<Long> idList);
	
	public Page<CoeProductTag> findPage(CoeProductTag coeProductTag, Pageable pageable);
	
	public CoeProductTag findByMainIdAndName(CoeProductTag coeProductTag) ;
	
	public Long countByMainId(Long productId);
	
	public List<CoeProductTag> findByMainId(Long productId);
	
	public List<CoeProductTag> findByMainIdList(List<Long> productIdList);
	
}
