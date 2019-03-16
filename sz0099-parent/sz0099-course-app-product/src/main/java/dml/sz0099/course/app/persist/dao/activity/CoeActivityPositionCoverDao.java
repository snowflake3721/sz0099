package dml.sz0099.course.app.persist.dao.activity;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityPositionCover;

/**
 * CoeActivityPositionCoverDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeActivityPositionCoverDao extends GenericDao<CoeActivityPositionCover,Long>{

	/**
	 * 根据Id查询CoeActivityPositionCover实体对象
	 * @param id
	 * @return
	 */
	CoeActivityPositionCover findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeActivityPositionCover findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeActivityPositionCover实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeActivityPositionCover> findByIdList(List<Long> idList);
	
	public Page<CoeActivityPositionCover> findPage(CoeActivityPositionCover coeActivityPosition, Pageable pageable);
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public CoeActivityPositionCover findByMainIdAndUserId(Long mainId, Long userId);
	public Page<CoeActivityPositionCover> findByMainId(Long mainId, Pageable pageable) ; 
	
	public void deleteByRefIdList(List<Long> refIdList);
	public void deleteByRefId(Long refId);
	
	public List<CoeActivityPositionCover> findByRefId(Long positionId) ;
	public List<CoeActivityPositionCover> findByRefIdList(List<Long> refIdList) ;
}
