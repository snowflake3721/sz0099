package dml.sz0099.course.app.biz.delegate.article;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.article.CoeArticlePraise;

/**
 * CoeArticlePraiseDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeArticlePraiseDelegate {

	/**
	 * 根据Id查询CoeArticlePraise实体对象
	 * @param id
	 * @return
	 */
	public CoeArticlePraise findById(Long id);
	
	public boolean existById(Long id);
	
	public CoeArticlePraise findByAid(Long aid);

	/**
	 * 根据IdList查询CoeArticlePraise实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeArticlePraise> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeArticlePraise
	 * @return
	 */
	public CoeArticlePraise persistEntity(CoeArticlePraise coeArticlePraise) ;
	
	public CoeArticlePraise mergeEntity(CoeArticlePraise coeArticlePraise) ; 
	
	public CoeArticlePraise saveOrUpdate(CoeArticlePraise coeArticlePraise) ;
	
	public Page<CoeArticlePraise> findPage(CoeArticlePraise coeArticlePraise, Pageable pageable) ;
	
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public CoeArticlePraise findByMainIdAndUserId(Long mainId, Long userId);
	public Page<CoeArticlePraise> findByMainId(Long mainId, Pageable pageable) ; 
	public boolean hasPraisedByMainIdAndUserId(Long mainId, Long userId);
	public CoeArticlePraise mergeForPraise(CoeArticlePraise coeArticlePraise) ; 
	public CoeArticlePraise mergeForRefreshTime(CoeArticlePraise coeArticlePraise);
	
	public CoeArticlePraise praiseAgain(CoeArticlePraise coeArticlePraise);
}
