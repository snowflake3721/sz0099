package dml.sz0099.course.app.persist.dao.position;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.position.Position;

/**
 * PositionDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface PositionDao extends GenericDao<Position,Long>{

	/**
	 * 根据Id查询Position实体对象
	 * @param id
	 * @return
	 */
	Position findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	Position findByAid(Long aid);
	
	/**
	 * 根据IdList查询Position实体对象列表
	 * @param idList
	 * @return
	 */
	public List<Position> findByIdList(List<Long> idList);
	
	public Page<Position> findPage(Position position, Pageable pageable);
	
	public List<Position> findMainIdAndExtendId(Position position);
	
	public List<Position> findMainIdAndPositionId(Long mainId, Long positionId);
	
	public List<Position> findPosition(Long mainId, Long subId, Long positionId, Integer panel);
	
}
