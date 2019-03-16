package dml.sz0099.course.app.biz.service.position;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.position.PositionRef;
import dml.sz0099.course.app.persist.entity.position.PositionRef;

/**
 * PositionRefService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface PositionRefService extends GenericService<PositionRef,Long>{


	/**
	 * 根据Id查询PositionRef实体对象
	 * @param id
	 * @return
	 */
	public PositionRef findById(Long id);
	
	public boolean existById(Long id);
	
	public PositionRef findByAid(Long aid);
	
	/**
	 * 根据IdList查询PositionRef实体对象列表
	 * @param idList
	 * @return
	 */
	public List<PositionRef> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param positionRef
	 * @return
	 */
	public PositionRef persistEntity(PositionRef positionRef) ;
	
	
	public PositionRef mergeEntity(PositionRef positionRef) ; 
	
	public PositionRef saveOrUpdate(PositionRef positionRef) ;
	
	public Page<PositionRef> findPage(PositionRef positionRef, Pageable pageable) ;
	
	public void deleteByBaseId(Long baseId);
	public void deleteByBaseId(Long baseId, boolean cascade);
	public void deleteByBaseIdList(List<Long> baseIdList, boolean cascade);
	
	public Long countForBase(PositionRef positionRef);
	
	public PositionRef changeSingle(PositionRef positionRef);
	
	public List<PositionRef> findByBaseId(Long baseId);
	public List<PositionRef> findByBaseIdList(List<Long> baseIdList);
	
	public Page<PositionRef> findPageByBaseId(Long baseId, Pageable pageable, boolean withImages);
	public List<PositionRef> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList);
	
	public PositionRef findMainIdAndBaseId(PositionRef positionRef);
	
	public void deleteById(PositionRef positionRef) ;
	public void deleteByBaseId(PositionRef positionRef);
	
	public PositionRef mergePositionRef(PositionRef positionRef) ;
	public PositionRef openPositionRef(PositionRef positionRef);
	public PositionRef mergeSimpleSingle(PositionRef positionRef);
	
	public PositionRef deleteRefByBaseId(PositionRef positionRef) ;
}
