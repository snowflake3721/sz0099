package dml.sz0099.course.app.biz.delegate.article;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.article.CoeArticleFavirate;

/**
 * CoeArticleFavirateDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeArticleFavirateDelegate {

	/**
	 * 根据Id查询CoeArticleFavirate实体对象
	 * @param id
	 * @return
	 */
	public CoeArticleFavirate findById(Long id);
	
	public boolean existById(Long id);
	
	public CoeArticleFavirate findByAid(Long aid);

	/**
	 * 根据IdList查询CoeArticleFavirate实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeArticleFavirate> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeArticleFavirate
	 * @return
	 */
	public CoeArticleFavirate persistEntity(CoeArticleFavirate coeArticleFavirate) ;
	
	public CoeArticleFavirate mergeEntity(CoeArticleFavirate coeArticleFavirate) ; 
	
	public CoeArticleFavirate saveOrUpdate(CoeArticleFavirate coeArticleFavirate) ;
	
	public Page<CoeArticleFavirate> findPage(CoeArticleFavirate coeArticleFavirate, Pageable pageable) ;
	
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public CoeArticleFavirate findByMainIdAndUserId(Long mainId, Long userId);
	public Page<CoeArticleFavirate> findByMainId(Long mainId, Pageable pageable) ; 
	public boolean hasFaviratedByMainIdAndUserId(Long mainId, Long userId);
	public CoeArticleFavirate mergeForFavirate(CoeArticleFavirate coeArticleFavirate) ; 
}
