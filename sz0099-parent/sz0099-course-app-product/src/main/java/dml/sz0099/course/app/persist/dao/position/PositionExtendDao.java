package dml.sz0099.course.app.persist.dao.position;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.position.PositionExtend;

/**
 * PositionExtendDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface PositionExtendDao extends GenericDao<PositionExtend,Long>{

	/**
	 * 根据Id查询PositionExtend实体对象
	 * @param id
	 * @return
	 */
	PositionExtend findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	PositionExtend findByAid(Long aid);
	
	/**
	 * 根据IdList查询PositionExtend实体对象列表
	 * @param idList
	 * @return
	 */
	public List<PositionExtend> findByIdList(List<Long> idList);
	
	public Page<PositionExtend> findPage(PositionExtend positionExtend, Pageable pageable);
	
	public PositionExtend findByPositionId(Long positionId) ;
	
	public PositionExtend findPositionExtend(PositionExtend extend);

	public Long findPositionIdById(Long id);
	
	public Long countByUserId(Long userId) ;
	
}
