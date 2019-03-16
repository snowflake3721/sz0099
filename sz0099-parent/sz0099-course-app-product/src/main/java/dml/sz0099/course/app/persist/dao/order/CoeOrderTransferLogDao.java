package dml.sz0099.course.app.persist.dao.order;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.order.CoeOrderTransferLog;

/**
 * CoeOrderTransferLogDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeOrderTransferLogDao extends GenericDao<CoeOrderTransferLog,Long>{

	/**
	 * 根据Id查询CoeOrderTransferLog实体对象
	 * @param id
	 * @return
	 */
	CoeOrderTransferLog findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeOrderTransferLog findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeOrderTransferLog实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeOrderTransferLog> findByIdList(List<Long> idList);
	
	public Page<CoeOrderTransferLog> findPage(CoeOrderTransferLog coeOrderTransferLog, Pageable pageable);
	
}
