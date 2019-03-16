package dml.sz0099.course.app.persist.dao.article;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.article.CoeArticle;

/**
 * CoeArticleDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeArticleDao extends GenericDao<CoeArticle,Long>{

	/**
	 * 根据Id查询CoeArticle实体对象
	 * @param id
	 * @return
	 */
	CoeArticle findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeArticle findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeArticle实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeArticle> findByIdList(List<Long> idList);
	
	public Page<CoeArticle> findPage(CoeArticle article, Pageable pageable);
	
	public Page<CoeArticle> findPublished(CoeArticle article, Pageable pageable);
	
	public List<CoeArticle> findDraftList(CoeArticle article);
	
	public Long countDraftList(CoeArticle article);
	
	public List<CoeArticle> findByUserId(Long userId);
	
	public Long countByUserId(Long userId) ;
	
	public List<CoeArticle> findPublishedByName(String name);
	
	public List<CoeArticle> findPublishedByTitle(String title);
	
	public Page<CoeArticle> findByPublished(CoeArticle coeArticle, Pageable pageable);
	
	public Page<CoeArticle> findPageByUserId(CoeArticle coeArticle, Pageable pageable);
	
	public Long countForPublishedWithoutSelf(Long userId);
	public Page<CoeArticle> findByPublishedNotSelf(CoeArticle coeArticle, Pageable pageable);
	
	public Page<CoeArticle> findPageByMainTypeAndUserId(Integer mainType, List<Long> userIdList, Integer publishStatus, Pageable pageable) ;
	
	public List<CoeArticle> findListByMainTypeAndUserId(Integer mainType, List<Long> userIdList, Integer publishStatus);

	public List<CoeArticle> findByUserIdAndMainType(Integer mainType, Long userId, Integer publishStatus);
	
	public List<CoeArticle> findByUserIdAndMainType(Integer mainType, Long userId);
	
	public List<CoeArticle> findPublishByIdList(List<Long> idList, Integer publishStatus);
	
	public Long countPublishedByUserId(CoeArticle coeArticle);
}
