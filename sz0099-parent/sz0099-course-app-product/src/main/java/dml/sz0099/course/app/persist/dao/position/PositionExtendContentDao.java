package dml.sz0099.course.app.persist.dao.position;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.position.PositionExtendContent;

/**
 * PositionExtendContentDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface PositionExtendContentDao extends GenericDao<PositionExtendContent,Long>{

	/**
	 * 根据Id查询PositionExtendContent实体对象
	 * @param id
	 * @return
	 */
	PositionExtendContent findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	PositionExtendContent findByAid(Long aid);
	
	/**
	 * 根据IdList查询PositionExtendContent实体对象列表
	 * @param idList
	 * @return
	 */
	public List<PositionExtendContent> findByIdList(List<Long> idList);
	
	public Page<PositionExtendContent> findPage(PositionExtendContent positionExtendContent, Pageable pageable);
	
}
