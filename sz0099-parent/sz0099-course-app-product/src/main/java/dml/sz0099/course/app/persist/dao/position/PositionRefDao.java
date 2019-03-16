package dml.sz0099.course.app.persist.dao.position;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.position.PositionRef;
import dml.sz0099.course.app.persist.entity.position.PositionRef;

/**
 * PositionRefDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface PositionRefDao extends GenericDao<PositionRef,Long>{

	/**
	 * 根据Id查询PositionRef实体对象
	 * @param id
	 * @return
	 */
	PositionRef findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	PositionRef findByAid(Long aid);
	
	/**
	 * 根据IdList查询PositionRef实体对象列表
	 * @param idList
	 * @return
	 */
	public List<PositionRef> findByIdList(List<Long> idList);
	
	public Page<PositionRef> findPage(PositionRef positionRef, Pageable pageable);
	
	public void deleteByBaseId(Long baseId) ;
	public void deleteByBaseIdList(List<Long> baseIdList);
	
	public Long countForBase(PositionRef positionRef);
	
	public List<PositionRef> findByMainId(Long mainId);
	
	public void deleteByMainId(Long mainId);
	
	public List<PositionRef> findByBaseId(Long baseId);
	
	public Page<PositionRef> findPageByBaseId(Long baseId, Pageable pageable);
	
	public List<PositionRef> findByBaseIdList(List<Long> baseIdList);
	
	public List<PositionRef> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList) ;
	
	public PositionRef findMainIdAndBaseId(PositionRef positionRef);
	
	public PositionRef deleteRefByBaseId(PositionRef positionRef) ;
}
