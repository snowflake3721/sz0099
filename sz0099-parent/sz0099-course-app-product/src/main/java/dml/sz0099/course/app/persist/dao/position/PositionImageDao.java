package dml.sz0099.course.app.persist.dao.position;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.position.PositionImage;

/**
 * PositionImageDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface PositionImageDao extends GenericDao<PositionImage,Long>{

	/**
	 * 根据Id查询PositionImage实体对象
	 * @param id
	 * @return
	 */
	PositionImage findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	PositionImage findByAid(Long aid);
	
	/**
	 * 根据IdList查询PositionImage实体对象列表
	 * @param idList
	 * @return
	 */
	public List<PositionImage> findByIdList(List<Long> idList);
	
	public Page<PositionImage> findPage(PositionImage positionImage, Pageable pageable);
	
	public List<PositionImage> findByRefIdList(List<Long> refIdList);
	
	public void deleteByRefIdList(List<Long> refIdList) ;
	public void deleteByRefId(Long refId);
	public List<PositionImage> findByRefId(Long refId);
}
