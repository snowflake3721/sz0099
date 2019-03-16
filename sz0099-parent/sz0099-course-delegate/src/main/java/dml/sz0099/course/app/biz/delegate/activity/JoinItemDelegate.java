package dml.sz0099.course.app.biz.delegate.activity;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.JoinItem;
import dml.sz0099.course.app.persist.entity.activity.JoinItem;

/**
 * JoinItemDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface JoinItemDelegate {

	/**
	 * 根据Id查询JoinItem实体对象
	 * @param id
	 * @return
	 */
	public JoinItem findById(Long id);
	
	public JoinItem findByAid(Long aid);

	/**
	 * 根据IdList查询JoinItem实体对象列表
	 * @param idList
	 * @return
	 */
	public List<JoinItem> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param joinItem
	 * @return
	 */
	public JoinItem persistEntity(JoinItem joinItem) ;
	
	public JoinItem mergeEntity(JoinItem joinItem) ; 
	
	public JoinItem saveOrUpdate(JoinItem joinItem) ;
	
	public Page<JoinItem> findPage(JoinItem joinItem, Pageable pageable) ;
	
	public List<JoinItem> findByMainId(JoinItem joinItem);
	
	public JoinItem addJoinItem(JoinItem joinItem);
	
	public JoinItem deleteJoinItem(JoinItem joinItem);
	
	public Long countByMainId(Long activityId) ;
	
	public List<JoinItem> findByMainId(Long activityId) ;
	public List<JoinItem> findByBaseId(Long baseId) ;
	
	public Map<Long, List<JoinItem>> findMapByMainIdList(List<Long> activityIdList) ;
	
	public JoinItem mergeJoinTime(JoinItem joinItem);
	public JoinItem mergePlace(JoinItem joinItem);
	public JoinItem mergeDescription(JoinItem joinItem);
	
}
