package dml.sz0099.course.app.persist.dao.activity;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.CoeCategActivity;

/**
 * CoeCategActivityDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeCategActivityDao extends GenericDao<CoeCategActivity,Long>{

	/**
	 * 根据Id查询CoeCategActivity实体对象
	 * @param id
	 * @return
	 */
	CoeCategActivity findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeCategActivity findByAid(Long aid);
	
	
	
	
	/**
	 * 根据IdList查询CoeCategActivity实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeCategActivity> findByIdList(List<Long> idList);
	
	public Page<CoeCategActivity> findPage(CoeCategActivity coeCategActivity, Pageable pageable);
	
	public List<CoeCategActivity> findByMainIdList(List<Long> productIdList) ;
	
	public List<CoeCategActivity> findByMainId(Long productId);
	
	public Page<CoeCategActivity> findPageForPublish(CoeCategActivity coeCategActivity, Pageable pageable);
	public Page<CoeCategActivity> findPageForPublishFromDetail(CoeCategActivity coeCategActivity, Pageable pageable);
	
	public Page<CoeCategActivity> findPageForPublish(CoeCategActivity coeCategActivity, List<Long> excludeMainIdList, Pageable pageable);
	public Page<CoeCategActivity> findPageForPublishWithChildren(CoeCategActivity coeCategActivity, List<Long> baseIdList, List<Long> excludeMainIdList, Pageable pageable);
}
