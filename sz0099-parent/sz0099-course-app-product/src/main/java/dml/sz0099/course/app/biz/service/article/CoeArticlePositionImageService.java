package dml.sz0099.course.app.biz.service.article;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.article.CoeArticlePosition;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePositionImage;
import dml.sz0099.course.app.persist.entity.position.PositionCover;

/**
 * CoeArticlePositionImageService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeArticlePositionImageService extends GenericService<CoeArticlePositionImage,Long>{


	/**
	 * 根据Id查询CoeArticlePositionImage实体对象
	 * @param id
	 * @return
	 */
	public CoeArticlePositionImage findById(Long id);
	
	public boolean existById(Long id);
	
	public CoeArticlePositionImage findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeArticlePositionImage实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeArticlePositionImage> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeArticlePosition
	 * @return
	 */
	public CoeArticlePositionImage persistEntity(CoeArticlePositionImage coeArticlePosition) ;
	
	
	public CoeArticlePositionImage mergeEntity(CoeArticlePositionImage coeArticlePosition) ; 
	
	public CoeArticlePositionImage saveOrUpdate(CoeArticlePositionImage coeArticlePosition) ;
	
	public Page<CoeArticlePositionImage> findPage(CoeArticlePositionImage coeArticlePosition, Pageable pageable) ;
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public CoeArticlePositionImage findByMainIdAndUserId(Long mainId, Long userId);
	public Page<CoeArticlePositionImage> findByMainId(Long mainId, Pageable pageable) ; 
	
	public boolean hasPositionByMainIdAndUserId(Long mainId, Long userId);
	public CoeArticlePositionImage mergeForPosition(CoeArticlePositionImage coeArticlePosition);
	
	public CoeArticlePosition mergeForPosition(CoeArticlePosition coeArticlePosition);
	
	public CoeArticlePosition addPositionImage(CoeArticlePosition articlePosition);
	public void deleteByRefIdList(List<Long> refIdList);
	public void deleteByRefId(Long refId);
	
	public List<CoeArticlePositionImage> findByRefIdList(List<Long> refIdList);
	public List<CoeArticlePositionImage> findByRefId(Long refId);
	
}
