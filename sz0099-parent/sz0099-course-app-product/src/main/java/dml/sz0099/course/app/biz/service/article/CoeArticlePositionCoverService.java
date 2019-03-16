package dml.sz0099.course.app.biz.service.article;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.article.CoeArticlePosition;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePositionCover;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePositionImage;

/**
 * CoeArticlePositionCoverService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeArticlePositionCoverService extends GenericService<CoeArticlePositionCover,Long>{


	/**
	 * 根据Id查询CoeArticlePositionCover实体对象
	 * @param id
	 * @return
	 */
	public CoeArticlePositionCover findById(Long id);
	
	public boolean existById(Long id);
	
	public CoeArticlePositionCover findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeArticlePositionCover实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeArticlePositionCover> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeArticlePosition
	 * @return
	 */
	public CoeArticlePositionCover persistEntity(CoeArticlePositionCover coeArticlePosition) ;
	
	
	public CoeArticlePositionCover mergeEntity(CoeArticlePositionCover coeArticlePosition) ; 
	
	public CoeArticlePositionCover saveOrUpdate(CoeArticlePositionCover coeArticlePosition) ;
	
	public Page<CoeArticlePositionCover> findPage(CoeArticlePositionCover coeArticlePosition, Pageable pageable) ;
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public CoeArticlePositionCover findByMainIdAndUserId(Long mainId, Long userId);
	public Page<CoeArticlePositionCover> findByMainId(Long mainId, Pageable pageable) ; 
	
	public boolean hasPositionByMainIdAndUserId(Long mainId, Long userId);
	public CoeArticlePositionCover mergeForPosition(CoeArticlePositionCover coeArticlePosition);
	
	public CoeArticlePosition mergeForPosition(CoeArticlePosition coeArticlePosition);
	
	public CoeArticlePosition addPositionImage(CoeArticlePosition articlePosition);
	public void deleteByRefIdList(List<Long> refIdList);
	public void deleteByRefId(Long refId);
	
	public List<CoeArticlePositionCover> findByRefIdList(List<Long> refIdList);
	public List<CoeArticlePositionCover> findByRefId(Long refId);
	
}
