package dml.sz0099.course.app.persist.dao.article;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.article.CoeArticlePraise;

/**
 * CoeArticlePraiseDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeArticlePraiseDao extends GenericDao<CoeArticlePraise,Long>{

	/**
	 * 根据Id查询CoeArticlePraise实体对象
	 * @param id
	 * @return
	 */
	CoeArticlePraise findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeArticlePraise findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeArticlePraise实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeArticlePraise> findByIdList(List<Long> idList);
	
	public Page<CoeArticlePraise> findPage(CoeArticlePraise coeArticlePraise, Pageable pageable);
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public CoeArticlePraise findByMainIdAndUserId(Long mainId, Long userId);
	public Page<CoeArticlePraise> findByMainId(Long mainId, Pageable pageable) ; 
}
