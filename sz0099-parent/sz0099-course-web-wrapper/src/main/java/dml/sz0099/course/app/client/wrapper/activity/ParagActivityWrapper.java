package dml.sz0099.course.app.client.wrapper.activity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.ParagActivity;


/**
 * ParagActivityWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ParagActivityWrapper {

	/**
	 * 根据Id查询ParagActivity实体对象
	 * @param id
	 * @return
	 */
	public ParagActivity findById(Long id);
	
	public boolean existById(Long id);
	
	public ParagActivity findByAid(Long aid);
	
	/**
	 * 根据IdList查询ParagActivity实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ParagActivity> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param paragActivity
	 * @return
	 */
	public ParagActivity persistEntity(ParagActivity paragActivity) ;
	
	public ParagActivity mergeEntity(ParagActivity paragActivity) ; 
	
	public ParagActivity saveOrUpdate(ParagActivity paragActivity) ;
	
	public Page<ParagActivity> findPage(ParagActivity paragActivity, Pageable pageable) ; 
	

	public Long countByMainId(Long activityId);
	public Page<ParagActivity> findByMainId(Long activityId, Pageable pageable );
	public Page<ParagActivity> findByMainIdAndUserId(Long activityId, Long userId, Pageable pageable );

	public Page<ParagActivity> resetOrderSeq(Long activityId, Long userId);

	public void deleteByActivityIdAndUserId(Long activityId, Long userId );
	public ParagActivity createParagActivity(ParagActivity paragActivity);
	public void deleteByParagIdAndUserId(Long paragId, Long userId, boolean cascade );

}
