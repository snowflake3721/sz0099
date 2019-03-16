package dml.sz0099.course.app.client.wrapper.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.product.CoeOpration;


/**
 * CoeOprationWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeOprationWrapper {

	/**
	 * 根据Id查询CoeOpration实体对象
	 * @param id
	 * @return
	 */
	public CoeOpration findById(Long id);
	
	public boolean existById(Long id);
	
	public CoeOpration findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeOpration实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeOpration> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeOpration
	 * @return
	 */
	public CoeOpration persistEntity(CoeOpration coeOpration) ;
	
	public CoeOpration mergeEntity(CoeOpration coeOpration) ; 
	
	public CoeOpration saveOrUpdate(CoeOpration coeOpration) ;
	
	public Page<CoeOpration> findPage(CoeOpration coeOpration, Pageable pageable) ; 
	
}
