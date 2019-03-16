package dml.sz0099.course.app.persist.dao.position;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.position.PositionExtendLog;

/**
 * PositionExtendLogDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface PositionExtendLogDao extends GenericDao<PositionExtendLog,Long>{

	/**
	 * 根据Id查询PositionExtendLog实体对象
	 * @param id
	 * @return
	 */
	PositionExtendLog findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	PositionExtendLog findByAid(Long aid);
	
	/**
	 * 根据IdList查询PositionExtendLog实体对象列表
	 * @param idList
	 * @return
	 */
	public List<PositionExtendLog> findByIdList(List<Long> idList);
	
	public Page<PositionExtendLog> findPage(PositionExtendLog positionExtendLog, Pageable pageable);
	
}
