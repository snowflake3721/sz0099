package dml.sz0099.course.app.persist.dao.activity;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityPosition;

/**
 * CoeActivityPositionDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeActivityPositionDao extends GenericDao<CoeActivityPosition,Long>{

	/**
	 * 根据Id查询CoeActivityPosition实体对象
	 * @param id
	 * @return
	 */
	CoeActivityPosition findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeActivityPosition findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeActivityPosition实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeActivityPosition> findByIdList(List<Long> idList);
	
	public Page<CoeActivityPosition> findPage(CoeActivityPosition coeActivityPosition, Pageable pageable);
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public List<CoeActivityPosition> findByMainIdAndUserId(Long mainId, Long userId);
	public Page<CoeActivityPosition> findByMainId(Long mainId, Pageable pageable) ; 
	
	public CoeActivityPosition findByMainIdAndPosition(Long mainId, Integer position) ;
	
	public List<CoeActivityPosition> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList);
	
	public List<CoeActivityPosition> findByBaseId(Long baseId);
	public CoeActivityPosition deleteRefByBaseId(CoeActivityPosition positionRef) ;
	
	public Page<CoeActivityPosition> findPageForPosition(Long ponMainId,Long ponSubId, Long positionId, Integer ponPanel, Pageable pageable);
	public Page<CoeActivityPosition> findByBaseId(Long baseId, Pageable pageable);
	public Page<CoeActivityPosition> findByBaseIdList(List<Long> baseIdList, Pageable pageable);
}
