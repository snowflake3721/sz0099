package dml.sz0099.course.app.persist.dao.activity;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityPraise;

/**
 * CoeActivityPraiseDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeActivityPraiseDao extends GenericDao<CoeActivityPraise,Long>{

	/**
	 * 根据Id查询CoeActivityPraise实体对象
	 * @param id
	 * @return
	 */
	CoeActivityPraise findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeActivityPraise findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeActivityPraise实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeActivityPraise> findByIdList(List<Long> idList);
	
	public Page<CoeActivityPraise> findPage(CoeActivityPraise coeActivityPraise, Pageable pageable);
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public CoeActivityPraise findByMainIdAndUserId(Long mainId, Long userId);
	public Page<CoeActivityPraise> findByMainId(Long mainId, Pageable pageable) ; 
}
