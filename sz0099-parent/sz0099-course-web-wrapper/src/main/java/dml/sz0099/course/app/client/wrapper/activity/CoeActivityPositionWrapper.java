package dml.sz0099.course.app.client.wrapper.activity;

import java.util.List;

import org.jit4j.core.persist.page.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityPosition;
import dml.sz0099.course.app.persist.entity.position.PositionRef;


/**
 * CoeActivityPositionWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeActivityPositionWrapper {

	/**
	 * 根据Id查询CoeActivityPosition实体对象
	 * @param id
	 * @return
	 */
	public CoeActivityPosition findById(Long id);
	
	public boolean existById(Long id);
	
	public CoeActivityPosition findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeActivityPosition实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeActivityPosition> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeActivityPosition
	 * @return
	 */
	public CoeActivityPosition persistEntity(CoeActivityPosition coeActivityPosition) ;
	
	public CoeActivityPosition mergeEntity(CoeActivityPosition coeActivityPosition) ; 
	
	public CoeActivityPosition saveOrUpdate(CoeActivityPosition coeActivityPosition) ;
	
	public Page<CoeActivityPosition> findPage(CoeActivityPosition coeActivityPosition, Pageable pageable) ; 
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public boolean hasPositionByMainIdAndUserId(Long mainId, Long userId);
	public List<CoeActivityPosition> findByMainIdAndUserId(Long mainId, Long userId);
	public Page<CoeActivityPosition> findByMainId(Long mainId, Pageable pageable) ; 
	 
	public CoeActivityPosition bindPosition(CoeActivityPosition coeActivityPosition);
	public CoeActivityPosition addPositionRef(CoeActivityPosition coeActivityPosition);
	
	public CoeActivityPosition findActivityByMainId(Long mainId);
	
	public List<CoeActivityPosition> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList);
	public PageResult<CoeActivityPosition> findPublishedForSelect(CoeActivityPosition activityPosition, Pageable pageable);
	
	public void deleteById(CoeActivityPosition coeActivityPosition);
	public void deletePositionRef(CoeActivityPosition coeActivityPosition);
	public CoeActivityPosition mergePositionRef(CoeActivityPosition coeActivityPosition) ;
	public CoeActivityPosition openPositionRef(CoeActivityPosition coeActivityPosition);
	public CoeActivityPosition mergeSimpleSingle(CoeActivityPosition positionRef);
	public CoeActivityPosition deleteRefByBaseId(CoeActivityPosition positionRef);
	public List<CoeActivityPosition> findByBaseId(CoeActivityPosition positionRef);
	
	public Page<CoeActivityPosition> findPageForPosition(Long ponMainId,Long ponSubId, Long positionId, Integer ponPanel, Pageable pageable);
	public Page<CoeActivityPosition> findByBaseId(Long baseId, Pageable pageable) ;
	public Page<CoeActivityPosition> findByBaseIdList(List<Long> baseIdList, Pageable pageable) ;
	
}
