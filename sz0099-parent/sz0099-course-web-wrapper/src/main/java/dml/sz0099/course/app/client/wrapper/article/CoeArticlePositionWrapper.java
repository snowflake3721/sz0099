package dml.sz0099.course.app.client.wrapper.article;

import java.util.List;

import org.jit4j.core.persist.page.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.article.CoeArticlePosition;
import dml.sz0099.course.app.persist.entity.position.PositionRef;


/**
 * CoeArticlePositionWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeArticlePositionWrapper {

	/**
	 * 根据Id查询CoeArticlePosition实体对象
	 * @param id
	 * @return
	 */
	public CoeArticlePosition findById(Long id);
	
	public boolean existById(Long id);
	
	public CoeArticlePosition findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeArticlePosition实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeArticlePosition> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeArticlePosition
	 * @return
	 */
	public CoeArticlePosition persistEntity(CoeArticlePosition coeArticlePosition) ;
	
	public CoeArticlePosition mergeEntity(CoeArticlePosition coeArticlePosition) ; 
	
	public CoeArticlePosition saveOrUpdate(CoeArticlePosition coeArticlePosition) ;
	
	public Page<CoeArticlePosition> findPage(CoeArticlePosition coeArticlePosition, Pageable pageable) ; 
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public boolean hasPositionByMainIdAndUserId(Long mainId, Long userId);
	public List<CoeArticlePosition> findByMainIdAndUserId(Long mainId, Long userId);
	public Page<CoeArticlePosition> findByMainId(Long mainId, Pageable pageable) ; 
	 
	public CoeArticlePosition bindPosition(CoeArticlePosition coeArticlePosition);
	public CoeArticlePosition addPositionRef(CoeArticlePosition coeArticlePosition);
	
	public CoeArticlePosition findArticleByMainId(Long mainId);
	
	public List<CoeArticlePosition> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList);
	public PageResult<CoeArticlePosition> findPublishedForSelect(CoeArticlePosition articlePosition, Pageable pageable);
	
	public void deleteById(CoeArticlePosition coeArticlePosition);
	public void deletePositionRef(CoeArticlePosition coeArticlePosition);
	public CoeArticlePosition mergePositionRef(CoeArticlePosition coeArticlePosition) ;
	public CoeArticlePosition openPositionRef(CoeArticlePosition coeArticlePosition);
	public CoeArticlePosition mergeSimpleSingle(CoeArticlePosition positionRef);
	public CoeArticlePosition deleteRefByBaseId(CoeArticlePosition positionRef);
	public List<CoeArticlePosition> findByBaseId(CoeArticlePosition positionRef);
	
	public Page<CoeArticlePosition> findPageForPosition(Long ponMainId,Long ponSubId, Long positionId, Integer ponPanel, Pageable pageable);
	public Page<CoeArticlePosition> findByBaseId(Long baseId, Pageable pageable) ;
	public Page<CoeArticlePosition> findByBaseIdList(List<Long> baseIdList, Pageable pageable) ;
	
}
