package dml.sz0099.course.app.biz.delegate.order;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.order.CoeOrderAsign;

/**
 * CoeOrderAsignDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeOrderAsignDelegate {

	/**
	 * 根据Id查询CoeOrderAsign实体对象
	 * @param id
	 * @return
	 */
	public CoeOrderAsign findById(Long id);
	
	public boolean existById(Long id);
	
	public CoeOrderAsign findByAid(Long aid);

	/**
	 * 根据IdList查询CoeOrderAsign实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeOrderAsign> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeOrderAsign
	 * @return
	 */
	public CoeOrderAsign persistEntity(CoeOrderAsign coeOrderAsign) ;
	
	public CoeOrderAsign mergeEntity(CoeOrderAsign coeOrderAsign) ; 
	
	public CoeOrderAsign saveOrUpdate(CoeOrderAsign coeOrderAsign) ;
	
	public Page<CoeOrderAsign> findPage(CoeOrderAsign coeOrderAsign, Pageable pageable) ;
	
}
