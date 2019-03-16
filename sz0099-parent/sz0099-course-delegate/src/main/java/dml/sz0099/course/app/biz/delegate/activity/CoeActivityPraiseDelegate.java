package dml.sz0099.course.app.biz.delegate.activity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityPraise;

/**
 * CoeActivityPraiseDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeActivityPraiseDelegate {

	/**
	 * 根据Id查询CoeActivityPraise实体对象
	 * @param id
	 * @return
	 */
	public CoeActivityPraise findById(Long id);
	
	public boolean existById(Long id);
	
	public CoeActivityPraise findByAid(Long aid);

	/**
	 * 根据IdList查询CoeActivityPraise实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeActivityPraise> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeActivityPraise
	 * @return
	 */
	public CoeActivityPraise persistEntity(CoeActivityPraise coeActivityPraise) ;
	
	public CoeActivityPraise mergeEntity(CoeActivityPraise coeActivityPraise) ; 
	
	public CoeActivityPraise saveOrUpdate(CoeActivityPraise coeActivityPraise) ;
	
	public Page<CoeActivityPraise> findPage(CoeActivityPraise coeActivityPraise, Pageable pageable) ;
	
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public CoeActivityPraise findByMainIdAndUserId(Long mainId, Long userId);
	public Page<CoeActivityPraise> findByMainId(Long mainId, Pageable pageable) ; 
	public boolean hasPraisedByMainIdAndUserId(Long mainId, Long userId);
	public CoeActivityPraise mergeForPraise(CoeActivityPraise coeActivityPraise) ; 
	public CoeActivityPraise mergeForRefreshTime(CoeActivityPraise coeActivityPraise);
	
	public CoeActivityPraise praiseAgain(CoeActivityPraise coeActivityPraise);
}
