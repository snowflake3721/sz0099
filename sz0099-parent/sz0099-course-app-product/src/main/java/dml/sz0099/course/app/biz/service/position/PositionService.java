package dml.sz0099.course.app.biz.service.position;

import java.util.List;
import java.util.Map;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.entity.position.Position;

/**
 * PositionService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface PositionService extends GenericService<Position,Long>{


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
	
	public List<Position> findMainIdAndPositionId(Long mainId, Long positionId);
	
	public List<Position> getSorted(List<Position> content);
	
	public Position deletePosition(Position position);
	
	public Long countByExtendId(Long extendId) ;
	
	public Position deleteAllByExtend(Position position) ;
	
	public Position findById(Long id, boolean cascade, boolean withImages, Pageable pageable);
	
	public Map<Long,Position> findMapByIdList(List<Long> idList);
	
	public List<Position> findBindList(Position position, Pageable pageable) ;
	
	public List<Position> findPosition(Long mainId, Long subId, Long positionId, Integer panel);
}
