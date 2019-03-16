package dml.sz0099.course.app.biz.service.position;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.position.PositionExtend;

/**
 * PositionExtendService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface PositionExtendService extends GenericService<PositionExtend,Long>{


	/**
	 * 根据Id查询PositionExtend实体对象
	 * @param id
	 * @return
	 */
	public PositionExtend findById(Long id);
	
	public boolean existById(Long id);
	
	public PositionExtend findByAid(Long aid);
	
	/**
	 * 根据IdList查询PositionExtend实体对象列表
	 * @param idList
	 * @return
	 */
	public List<PositionExtend> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param positionExtend
	 * @return
	 */
	public PositionExtend persistEntity(PositionExtend positionExtend) ;
	
	
	public PositionExtend mergeEntity(PositionExtend positionExtend) ; 
	
	public PositionExtend saveOrUpdate(PositionExtend positionExtend) ;
	
	public Page<PositionExtend> findPage(PositionExtend positionExtend, Pageable pageable) ;
	
	public PositionExtend create(PositionExtend positionExtend) ;
	
	public PositionExtend findByPositionId(Long positionId);
	
	public PositionExtend findPositionExtend(PositionExtend extend) ;
	
	public Long findPositionIdById(Long id);
	
	public Long countByUserId(Long userId) ;
	
	public PositionExtend deleteEntity(PositionExtend extend);

	
}
