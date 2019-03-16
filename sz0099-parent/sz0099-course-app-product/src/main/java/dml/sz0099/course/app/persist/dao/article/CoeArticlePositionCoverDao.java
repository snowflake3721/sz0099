package dml.sz0099.course.app.persist.dao.article;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.article.CoeArticlePositionCover;

/**
 * CoeArticlePositionCoverDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeArticlePositionCoverDao extends GenericDao<CoeArticlePositionCover,Long>{

	/**
	 * 根据Id查询CoeArticlePositionCover实体对象
	 * @param id
	 * @return
	 */
	CoeArticlePositionCover findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeArticlePositionCover findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeArticlePositionCover实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeArticlePositionCover> findByIdList(List<Long> idList);
	
	public Page<CoeArticlePositionCover> findPage(CoeArticlePositionCover coeArticlePosition, Pageable pageable);
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public CoeArticlePositionCover findByMainIdAndUserId(Long mainId, Long userId);
	public Page<CoeArticlePositionCover> findByMainId(Long mainId, Pageable pageable) ; 
	
	public void deleteByRefIdList(List<Long> refIdList);
	public void deleteByRefId(Long refId);
	
	public List<CoeArticlePositionCover> findByRefId(Long positionId) ;
	public List<CoeArticlePositionCover> findByRefIdList(List<Long> refIdList) ;
}
