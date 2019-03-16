package dml.sz0099.course.app.client.wrapper.activity;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityTag;


/**
 * CoeActivityTagWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeActivityTagWrapper {

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
	 * @param coeActivityTag
	 * @return
	 */
	public CoeActivityTag persistEntity(CoeActivityTag coeActivityTag) ;
	
	public CoeActivityTag mergeEntity(CoeActivityTag coeActivityTag) ; 
	
	public CoeActivityTag saveOrUpdate(CoeActivityTag coeActivityTag) ;
	
	public Page<CoeActivityTag> findPage(CoeActivityTag coeActivityTag, Pageable pageable) ; 
	
	public CoeActivityTag findByMainIdAndName(CoeActivityTag coeActivityTag);
	public Long countByMainId(Long activityId);
	public List<CoeActivityTag> findByMainId(Long activityId);
	public Map<Long,List<CoeActivityTag>> findMapByMainIdList(List<Long> activityIdList);
	
	public CoeActivityTag addTag(CoeActivityTag coeActivityTag);
	public CoeActivityTag deleteTag(CoeActivityTag coeActivityTag);
	
	public Page<CoeActivityTag> findPageWithNotself(CoeActivityTag coeActivityTag, Pageable pageable);
}
