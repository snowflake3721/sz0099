package dml.sz0099.course.app.client.wrapper.position;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.entity.position.Position;


/**
 * PositionWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface PositionWrapper {

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
	
	public List<Position> findMainIdAndExtendId(Position position);
	
	public Position createPosition(Position position);
	
	public Long countByExtendId(Long extendId);
	
	public Position deletePosition(Position position);
	
	public Position deleteAllByExtend(Position position);
	
	/**
	 * 根据parentId获取顶级类，包含子级节点
	 * @param id
	 * @param cascade
	 * @return
	 */
	
	public Map<Long,Position> findMapByIdList(List<Long> idList);
	
	public Position findById(Long id, boolean cascade, boolean withImages, Pageable pageable) ;
	
	public List<Position> findBindList(Position position, Pageable pageable);
	
	/**
	 * 确定一个唯一位置
	 * @param positionId
	 * @param mainId
	 * @param subId
	 * @param panelId
	 * @return
	 *//*
	public Position findSingle(Long positionId, Long mainId, Long subId, Integer panelId);*/
	
	public List<Position> findPosition(Long mainId, Long subId, Long positionId, Integer panel);
}
