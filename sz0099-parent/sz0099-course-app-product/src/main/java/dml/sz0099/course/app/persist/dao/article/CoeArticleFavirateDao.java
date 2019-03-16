package dml.sz0099.course.app.persist.dao.article;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.article.CoeArticleFavirate;

/**
 * CoeArticleFavirateDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeArticleFavirateDao extends GenericDao<CoeArticleFavirate,Long>{

	/**
	 * 根据Id查询CoeArticleFavirate实体对象
	 * @param id
	 * @return
	 */
	CoeArticleFavirate findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeArticleFavirate findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeArticleFavirate实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeArticleFavirate> findByIdList(List<Long> idList);
	
	public Page<CoeArticleFavirate> findPage(CoeArticleFavirate coeArticleFavirate, Pageable pageable);
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public CoeArticleFavirate findByMainIdAndUserId(Long mainId, Long userId);
	public Page<CoeArticleFavirate> findByMainId(Long mainId, Pageable pageable) ; 
}
