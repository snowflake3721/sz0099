package dml.sz0099.course.app.persist.dao.activity;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityFavirate;

/**
 * CoeActivityFavirateDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeActivityFavirateDao extends GenericDao<CoeActivityFavirate,Long>{

	/**
	 * 根据Id查询CoeActivityFavirate实体对象
	 * @param id
	 * @return
	 */
	CoeActivityFavirate findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeActivityFavirate findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeActivityFavirate实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeActivityFavirate> findByIdList(List<Long> idList);
	
	public Page<CoeActivityFavirate> findPage(CoeActivityFavirate coeActivityFavirate, Pageable pageable);
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public CoeActivityFavirate findByMainIdAndUserId(Long mainId, Long userId);
	public Page<CoeActivityFavirate> findByMainId(Long mainId, Pageable pageable) ; 
}
