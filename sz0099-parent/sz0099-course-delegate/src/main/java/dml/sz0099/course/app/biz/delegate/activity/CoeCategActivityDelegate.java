package dml.sz0099.course.app.biz.delegate.activity;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.CoeCategActivity;

/**
 * CoeCategActivityDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeCategActivityDelegate {

	/**
	 * 根据Id查询CoeCategActivity实体对象
	 * @param id
	 * @return
	 */
	public CoeCategActivity findById(Long id);
	
	public boolean existById(Long id);
	
	public CoeCategActivity findByAid(Long aid);

	/**
	 * 根据IdList查询CoeCategActivity实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeCategActivity> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeCategActivity
	 * @return
	 */
	public CoeCategActivity persistEntity(CoeCategActivity coeCategActivity) ;
	
	public CoeCategActivity mergeEntity(CoeCategActivity coeCategActivity) ; 
	
	public CoeCategActivity saveOrUpdate(CoeCategActivity coeCategActivity) ;
	
	public Page<CoeCategActivity> findPage(CoeCategActivity coeCategActivity, Pageable pageable) ;
	
	public Map<Long, List<CoeCategActivity>> findMapByMainIdList(List<Long> activityIdList);
	
	public List<CoeCategActivity> findByMainId(Long activityId);
	
	public CoeCategActivity changeCategory(CoeCategActivity coeCategActivity);
	
	public Page<CoeCategActivity> findPageForPublish(CoeCategActivity coeCategActivity, Pageable pageable) ;
	
	public Page<CoeCategActivity> findPageForPublishFromDetail(CoeCategActivity coeCategActivity, Pageable pageable);
	
	public Page<CoeCategActivity> findPageForPublish(CoeCategActivity coeCategActivity, List<Long> excludeMainIdList, Pageable pageable) ;
	public Page<CoeCategActivity> findPageForPublishWithChildren(CoeCategActivity coeCategActivity, List<Long> baseIdList,List<Long> excludeMainIdList, Pageable pageable);
}
