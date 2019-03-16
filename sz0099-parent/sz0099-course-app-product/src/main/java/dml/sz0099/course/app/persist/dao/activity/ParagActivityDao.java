package dml.sz0099.course.app.persist.dao.activity;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.ParagActivity;

/**
 * ParagActivityDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ParagActivityDao extends GenericDao<ParagActivity,Long>{

	/**
	 * 根据Id查询ParagActivity实体对象
	 * @param id
	 * @return
	 */
	ParagActivity findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	ParagActivity findByAid(Long aid);
	
	/**
	 * 根据IdList查询ParagActivity实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ParagActivity> findByIdList(List<Long> idList);
	
	public Page<ParagActivity> findPage(ParagActivity paragProduct, Pageable pageable);
	public List<ParagActivity> findListByMainId(Long activityId);
	public List<ParagActivity> findListByMainIdAndUserId(Long activityId,Long userId);


	public Page<ParagActivity> findByMainId(Long activityId, Pageable pageable );
	public Page<ParagActivity> findByMainIdAndUserId(Long activityId, Long userId, Pageable pageable );



	public void deleteByActivityIdAndUserId(Long activityId, Long userId );
	public Long countByMainId(Long activityId);
	public void deleteByParagIdAndUserId(Long paragId, Long userId, boolean cascade );

}
