package dml.sz0099.course.app.persist.dao.activity;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.CoeActivity;

/**
 * CoeActivityDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeActivityDao extends GenericDao<CoeActivity,Long>{

	/**
	 * 根据Id查询CoeActivity实体对象
	 * @param id
	 * @return
	 */
	CoeActivity findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeActivity findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeActivity实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeActivity> findByIdList(List<Long> idList);
	
	public Page<CoeActivity> findPage(CoeActivity activity, Pageable pageable);
	
	public Page<CoeActivity> findPublished(CoeActivity activity, Pageable pageable);
	
	public List<CoeActivity> findDraftList(CoeActivity activity);
	
	public Long countDraftList(CoeActivity activity);
	public Long countTemplate(Long userId, Integer template) ;
	
	public List<CoeActivity> findByUserId(Long userId);
	
	public Long countByUserId(Long userId) ;
	
	public List<CoeActivity> findPublishedByName(String name);
	
	public List<CoeActivity> findPublishedByTitle(String title);
	
	public Page<CoeActivity> findByPublished(CoeActivity coeActivity, Pageable pageable);
	
	public Page<CoeActivity> findPageByUserId(CoeActivity coeActivity, Pageable pageable);
	
	public Long countForPublishedWithoutSelf(Long userId);
	public Page<CoeActivity> findByPublishedNotSelf(CoeActivity coeActivity, Pageable pageable);
	
	public Page<CoeActivity> findPageByMainTypeAndUserId(Integer mainType, List<Long> userIdList, Integer publishStatus, Pageable pageable) ;
	
	public List<CoeActivity> findListByMainTypeAndUserId(Integer mainType, List<Long> userIdList, Integer publishStatus);

	public List<CoeActivity> findByUserIdAndMainType(Integer mainType, Long userId, Integer publishStatus);
	
	public List<CoeActivity> findByUserIdAndMainType(Integer mainType, Long userId);
	
	public List<CoeActivity> findPublishByIdList(List<Long> idList, Integer publishStatus);
	
	public Page<CoeActivity> findPageForTemplate(Long userId,Integer template, Pageable pageable);
	public Page<CoeActivity> findPageForTemplate(Integer template, Pageable pageable);
	public Page<CoeActivity> findPageForTemplate(Long userId,Integer template, Long id, Pageable pageable);
	
	
}
