package dml.sz0099.course.app.biz.delegate.position;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.position.PositionRef;
import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.entity.position.Position;

/**
 * PositionDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface PositionDelegate {

	/**
	 * 根据Id查询Position实体对象
	 * @param id
	 * @return
	 */
	public Position findById(Long id);
	
	public boolean existById(Long id);
	
	public Position findByAid(Long aid);

	/**
	 * 根据IdList查询Position实体对象列表
	 * @param idList
	 * @return
	 */
	public List<Position> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param position
	 * @return
	 */
	public Position persistEntity(Position position) ;
	
	public Position mergeEntity(Position position) ; 
	
	public Position saveOrUpdate(Position position) ;
	
	public Page<Position> findPage(Position position, Pageable pageable) ;
	
	public List<Position> findMainIdAndExtendId(Position position) ;
	
	public Position deletePosition(Position position);
	
	public Long countByExtendId(Long extendId) ;
	
	public Position deleteAllByExtend(Position position) ;
	
	public Map<Long,Position> findMapByIdList(List<Long> idList);
	
	public Position findById(Long id, boolean cascade, boolean withImages, Pageable pageable);
	
	public List<Position> findBindList(Position position, Pageable pageable);
	
	public List<Position> findPosition(Long mainId, Long subId, Long positionId, Integer panel);
	
}
