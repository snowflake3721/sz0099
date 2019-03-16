package dml.sz0099.course.app.persist.dao.article;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.article.CoeArticlePosition;

/**
 * CoeArticlePositionDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeArticlePositionDao extends GenericDao<CoeArticlePosition,Long>{

	/**
	 * 根据Id查询CoeArticlePosition实体对象
	 * @param id
	 * @return
	 */
	CoeArticlePosition findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeArticlePosition findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeArticlePosition实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeArticlePosition> findByIdList(List<Long> idList);
	
	public Page<CoeArticlePosition> findPage(CoeArticlePosition coeArticlePosition, Pageable pageable);
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public List<CoeArticlePosition> findByMainIdAndUserId(Long mainId, Long userId);
	public Page<CoeArticlePosition> findByMainId(Long mainId, Pageable pageable) ; 
	
	public CoeArticlePosition findByMainIdAndPosition(Long mainId, Integer position) ;
	
	public List<CoeArticlePosition> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList);
	
	public List<CoeArticlePosition> findByBaseId(Long baseId);
	public CoeArticlePosition deleteRefByBaseId(CoeArticlePosition positionRef) ;
	
	public Page<CoeArticlePosition> findPageForPosition(Long ponMainId,Long ponSubId, Long positionId, Integer ponPanel, Pageable pageable);
	public Page<CoeArticlePosition> findByBaseId(Long baseId, Pageable pageable);
	public Page<CoeArticlePosition> findByBaseIdList(List<Long> baseIdList, Pageable pageable);
}
