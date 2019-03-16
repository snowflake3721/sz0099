package dml.sz0099.course.app.persist.dao.article;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.article.CoeArticlePositionImage;

/**
 * CoeArticlePositionImageDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeArticlePositionImageDao extends GenericDao<CoeArticlePositionImage,Long>{

	/**
	 * 根据Id查询CoeArticlePositionImage实体对象
	 * @param id
	 * @return
	 */
	CoeArticlePositionImage findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeArticlePositionImage findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeArticlePositionImage实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeArticlePositionImage> findByIdList(List<Long> idList);
	
	public Page<CoeArticlePositionImage> findPage(CoeArticlePositionImage coeArticlePosition, Pageable pageable);
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public CoeArticlePositionImage findByMainIdAndUserId(Long mainId, Long userId);
	public Page<CoeArticlePositionImage> findByMainId(Long mainId, Pageable pageable) ; 
	
	public void deleteByRefIdList(List<Long> refIdList);
	public void deleteByRefId(Long refId);
	
	public List<CoeArticlePositionImage> findByRefId(Long refId) ;
	public List<CoeArticlePositionImage> findByRefIdList(List<Long> refIdList);
}
