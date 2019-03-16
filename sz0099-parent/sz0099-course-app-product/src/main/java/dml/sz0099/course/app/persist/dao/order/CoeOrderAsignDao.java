package dml.sz0099.course.app.persist.dao.order;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.order.CoeOrderAsign;

/**
 * CoeOrderAsignDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeOrderAsignDao extends GenericDao<CoeOrderAsign,Long>{

	/**
	 * 根据Id查询CoeOrderAsign实体对象
	 * @param id
	 * @return
	 */
	CoeOrderAsign findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeOrderAsign findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeOrderAsign实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeOrderAsign> findByIdList(List<Long> idList);
	
	public Page<CoeOrderAsign> findPage(CoeOrderAsign coeOrderAsign, Pageable pageable);
	
	public CoeOrderAsign findByOrderId(Long orderId) ;
	
}
