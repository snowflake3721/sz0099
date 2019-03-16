package dml.sz0099.course.app.biz.service.activity;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityPosition;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPositionCover;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPositionImage;

/**
 * CoeActivityPositionCoverService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeActivityPositionCoverService extends GenericService<CoeActivityPositionCover,Long>{


	/**
	 * 根据Id查询CoeActivityPositionCover实体对象
	 * @param id
	 * @return
	 */
	public CoeActivityPositionCover findById(Long id);
	
	public boolean existById(Long id);
	
	public CoeActivityPositionCover findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeActivityPositionCover实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeActivityPositionCover> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeActivityPosition
	 * @return
	 */
	public CoeActivityPositionCover persistEntity(CoeActivityPositionCover coeActivityPosition) ;
	
	
	public CoeActivityPositionCover mergeEntity(CoeActivityPositionCover coeActivityPosition) ; 
	
	public CoeActivityPositionCover saveOrUpdate(CoeActivityPositionCover coeActivityPosition) ;
	
	public Page<CoeActivityPositionCover> findPage(CoeActivityPositionCover coeActivityPosition, Pageable pageable) ;
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public CoeActivityPositionCover findByMainIdAndUserId(Long mainId, Long userId);
	public Page<CoeActivityPositionCover> findByMainId(Long mainId, Pageable pageable) ; 
	
	public boolean hasPositionByMainIdAndUserId(Long mainId, Long userId);
	public CoeActivityPositionCover mergeForPosition(CoeActivityPositionCover coeActivityPosition);
	
	public CoeActivityPosition mergeForPosition(CoeActivityPosition coeActivityPosition);
	
	public CoeActivityPosition addPositionImage(CoeActivityPosition activityPosition);
	public void deleteByRefIdList(List<Long> refIdList);
	public void deleteByRefId(Long refId);
	
	public List<CoeActivityPositionCover> findByRefIdList(List<Long> refIdList);
	public List<CoeActivityPositionCover> findByRefId(Long refId);
	
}
