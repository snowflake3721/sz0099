package dml.sz0099.course.app.persist.dao.order;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.order.CoeOrderTransfer;

/**
 * CoeOrderTransferDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeOrderTransferDao extends GenericDao<CoeOrderTransfer,Long>{

	/**
	 * 根据Id查询CoeOrderTransfer实体对象
	 * @param id
	 * @return
	 */
	CoeOrderTransfer findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeOrderTransfer findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeOrderTransfer实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeOrderTransfer> findByIdList(List<Long> idList);
	
	public Page<CoeOrderTransfer> findPage(CoeOrderTransfer coeOrderTransfer, Pageable pageable);
	
}
