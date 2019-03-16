package dml.sz0099.course.app.client.wrapper.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.product.CoeProductLog;


/**
 * CoeProductLogWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeProductLogWrapper {

	/**
	 * 根据Id查询CoeProductLog实体对象
	 * @param id
	 * @return
	 */
	public CoeProductLog findById(Long id);
	
	public boolean existById(Long id);
	
	public CoeProductLog findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeProductLog实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeProductLog> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeProductLog
	 * @return
	 */
	public CoeProductLog persistEntity(CoeProductLog coeProductLog) ;
	
	public CoeProductLog mergeEntity(CoeProductLog coeProductLog) ; 
	
	public CoeProductLog saveOrUpdate(CoeProductLog coeProductLog) ;
	
	public Page<CoeProductLog> findPage(CoeProductLog coeProductLog, Pageable pageable) ; 
	
}
