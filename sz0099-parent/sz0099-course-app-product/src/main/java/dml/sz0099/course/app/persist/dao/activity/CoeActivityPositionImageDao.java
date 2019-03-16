package dml.sz0099.course.app.persist.dao.activity;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityPositionImage;

/**
 * CoeActivityPositionImageDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeActivityPositionImageDao extends GenericDao<CoeActivityPositionImage,Long>{

	/**
	 * 根据Id查询CoeActivityPositionImage实体对象
	 * @param id
	 * @return
	 */
	CoeActivityPositionImage findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeActivityPositionImage findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeActivityPositionImage实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeActivityPositionImage> findByIdList(List<Long> idList);
	
	public Page<CoeActivityPositionImage> findPage(CoeActivityPositionImage coeActivityPosition, Pageable pageable);
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public CoeActivityPositionImage findByMainIdAndUserId(Long mainId, Long userId);
	public Page<CoeActivityPositionImage> findByMainId(Long mainId, Pageable pageable) ; 
	
	public void deleteByRefIdList(List<Long> refIdList);
	public void deleteByRefId(Long refId);
	
	public List<CoeActivityPositionImage> findByRefId(Long refId) ;
	public List<CoeActivityPositionImage> findByRefIdList(List<Long> refIdList);
}
