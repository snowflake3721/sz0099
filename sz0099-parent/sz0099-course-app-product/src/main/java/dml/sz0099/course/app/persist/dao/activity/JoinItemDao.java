package dml.sz0099.course.app.persist.dao.activity;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.JoinItem;

/**
 * JoinItemDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface JoinItemDao extends GenericDao<JoinItem,Long>{

	/**
	 * 根据Id查询JoinItem实体对象
	 * @param id
	 * @return
	 */
	JoinItem findById(Long id);
	
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	JoinItem findByAid(Long aid);
	
	/**
	 * 根据IdList查询JoinItem实体对象列表
	 * @param idList
	 * @return
	 */
	public List<JoinItem> findByIdList(List<Long> idList);
	
	public Page<JoinItem> findPage(JoinItem joinItem, Pageable pageable);
	
	public Long countByMainId(Long activityId);
	
	public List<JoinItem> findByMainId(Long activityId);
	public List<JoinItem> findByBaseId(Long baseId);
	
	public List<JoinItem> findByMainIdList(List<Long> activityIdList);
	
	public Page<JoinItem> findPageWithNotself(JoinItem joinItem, Pageable pageable);
	
}
