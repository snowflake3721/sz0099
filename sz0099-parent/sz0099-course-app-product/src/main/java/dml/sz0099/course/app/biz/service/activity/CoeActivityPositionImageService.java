package dml.sz0099.course.app.biz.service.activity;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityPosition;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPositionImage;
import dml.sz0099.course.app.persist.entity.position.PositionCover;

/**
 * CoeActivityPositionImageService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeActivityPositionImageService extends GenericService<CoeActivityPositionImage,Long>{


	/**
	 * 根据Id查询CoeActivityPositionImage实体对象
	 * @param id
	 * @return
	 */
	public CoeActivityPositionImage findById(Long id);
	
	public boolean existById(Long id);
	
	public CoeActivityPositionImage findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeActivityPositionImage实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeActivityPositionImage> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeActivityPosition
	 * @return
	 */
	public CoeActivityPositionImage persistEntity(CoeActivityPositionImage coeActivityPosition) ;
	
	
	public CoeActivityPositionImage mergeEntity(CoeActivityPositionImage coeActivityPosition) ; 
	
	public CoeActivityPositionImage saveOrUpdate(CoeActivityPositionImage coeActivityPosition) ;
	
	public Page<CoeActivityPositionImage> findPage(CoeActivityPositionImage coeActivityPosition, Pageable pageable) ;
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public CoeActivityPositionImage findByMainIdAndUserId(Long mainId, Long userId);
	public Page<CoeActivityPositionImage> findByMainId(Long mainId, Pageable pageable) ; 
	
	public boolean hasPositionByMainIdAndUserId(Long mainId, Long userId);
	public CoeActivityPositionImage mergeForPosition(CoeActivityPositionImage coeActivityPosition);
	
	public CoeActivityPosition mergeForPosition(CoeActivityPosition coeActivityPosition);
	
	public CoeActivityPosition addPositionImage(CoeActivityPosition activityPosition);
	public void deleteByRefIdList(List<Long> refIdList);
	public void deleteByRefId(Long refId);
	
	public List<CoeActivityPositionImage> findByRefIdList(List<Long> refIdList);
	public List<CoeActivityPositionImage> findByRefId(Long refId);
	
}
