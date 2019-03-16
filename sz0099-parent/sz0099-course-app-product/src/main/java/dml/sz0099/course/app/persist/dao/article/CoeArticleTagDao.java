package dml.sz0099.course.app.persist.dao.article;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.article.CoeArticleTag;
import dml.sz0099.course.app.persist.entity.article.CoeArticleTag;

/**
 * CoeArticleTagDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeArticleTagDao extends GenericDao<CoeArticleTag,Long>{

	/**
	 * 根据Id查询CoeArticleTag实体对象
	 * @param id
	 * @return
	 */
	CoeArticleTag findById(Long id);
	
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeArticleTag findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeArticleTag实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeArticleTag> findByIdList(List<Long> idList);
	
	public Page<CoeArticleTag> findPage(CoeArticleTag coeArticleTag, Pageable pageable);
	
	public CoeArticleTag findByMainIdAndName(CoeArticleTag coeArticleTag) ;
	
	public Long countByMainId(Long articleId);
	
	public List<CoeArticleTag> findByMainId(Long articleId);
	
	public List<CoeArticleTag> findByMainIdList(List<Long> articleIdList);
	
	public Page<CoeArticleTag> findPageWithNotself(CoeArticleTag coeArticleTag, Pageable pageable);
	
}
