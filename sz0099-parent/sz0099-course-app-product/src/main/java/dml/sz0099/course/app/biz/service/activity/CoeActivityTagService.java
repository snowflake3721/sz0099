package dml.sz0099.course.app.biz.service.activity;

import java.util.List;
import java.util.Map;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTag;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTag;

/**
 * CoeActivityTagService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeActivityTagService extends GenericService<CoeActivityTag,Long>{


	/**
	 * 根据Id查询CoeActivityTag实体对象
	 * @param id
	 * @return
	 */
	public CoeActivityTag findById(Long id);
	
	public CoeActivityTag findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeActivityTag实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeActivityTag> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param activityTag
	 * @return
	 */
	public CoeActivityTag persistEntity(CoeActivityTag activityTag) ;
	
	
	public CoeActivityTag mergeEntity(CoeActivityTag activityTag) ; 
	
	public CoeActivityTag saveOrUpdate(CoeActivityTag activityTag) ;
	
	public Page<CoeActivityTag> findPage(CoeActivityTag activityTag, Pageable pageable) ;
	
	public CoeActivityTag findByMainIdAndName(CoeActivityTag activityTag);
	
	public CoeActivityTag addTag(CoeActivityTag activityTag) ;
	
	public CoeActivityTag deleteTag(CoeActivityTag activityTag);
	
	public Long countByMainId(Long activityId) ;
	
	public List<CoeActivityTag> findByMainId(Long activityId);
	
	public Map<Long, List<CoeActivityTag>> findMapByMainIdList(List<Long> mainIdList) ;
	
	public Page<CoeActivityTag> findPageWithNotself(CoeActivityTag coeActivityTag, Pageable pageable);
	
	public CoeActivity syncFromTemplate(CoeActivity entity, CoeActivity template);
}
