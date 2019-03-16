package dml.sz0099.course.app.client.wrapper.position;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.entity.position.PositionRef;
import dml.sz0099.course.app.persist.entity.position.PositionRef;


/**
 * PositionRefWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface PositionRefWrapper {

	/**
	 * 根据Id查询PositionRef实体对象
	 * @param id
	 * @return
	 */
	public PositionRef findById(Long id);
	
	public PositionRef findById(Long id, boolean withBanner) ;
	
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
	
	public Long countForMain(PositionRef positionRef);

	public Long countForSub(PositionRef positionRef);

	public Long countForBase(PositionRef positionRef);
	
	public Long findPositionId(Long id);
	public List<PositionRef> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList);
	
	public PositionRef changeSingle(PositionRef positionRef);
	public PositionRef addPositionRef(PositionRef positionRef);
	
	public void deleteById(PositionRef positionRef);
	public void deletePositionRef(PositionRef positionRef);
	public PositionRef deleteByMainIdAndSubId(PositionRef positionRef);
	
	public PositionRef openPositionRef(PositionRef positionRef);
	public PositionRef mergePositionRef(PositionRef positionRef);
	public PositionRef mergeSimpleSingle(PositionRef positionRef);
	public PositionRef deleteRefByBaseId(PositionRef positionRef);
	
	
	
	public Page<PositionRef> findPageByBaseId(Long baseId, Pageable pageable, boolean withImages);
	
	public PositionRef findMainIdAndBaseId(PositionRef positionRef) ;
}
