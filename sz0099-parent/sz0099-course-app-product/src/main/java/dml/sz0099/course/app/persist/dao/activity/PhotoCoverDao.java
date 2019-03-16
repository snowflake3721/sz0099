package dml.sz0099.course.app.persist.dao.activity;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.PhotoCover;

/**
 * PhotoCoverDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface PhotoCoverDao extends GenericDao<PhotoCover,Long>{

	/**
	 * 根据Id查询PhotoCover实体对象
	 * @param id
	 * @return
	 */
	PhotoCover findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	PhotoCover findByAid(Long aid);
	
	/**
	 * 根据IdList查询PhotoCover实体对象列表
	 * @param idList
	 * @return
	 */
	public List<PhotoCover> findByIdList(List<Long> idList);
	
	public Page<PhotoCover> findPage(PhotoCover photoCover, Pageable pageable);
	
	public List<PhotoCover> findByMainId(Long mainId) ;
	
	public List<PhotoCover> findByMainIdAndSubId(Long mainId, Long subId) ;
	
	public void deleteBySubIdAndMainId(Long subId, Long mainId );
	
	public List<PhotoCover> findBySubIdListAndMainId(List<Long> idList, Long mainId) ;

	public void deleteBySubIdListAndMainId(List<Long> subIdList, Long mainId);

	public void deleteById(Long id) ;
	
	public List<PhotoCover> findByMainIdListAndSubId(List<Long> mainIdList, Long subId);

	public Long countByMainId(Long mainId);
}
