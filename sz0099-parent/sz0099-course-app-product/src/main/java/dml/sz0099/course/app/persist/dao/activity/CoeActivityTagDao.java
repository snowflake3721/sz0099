package dml.sz0099.course.app.persist.dao.activity;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityTag;

/**
 * CoeActivityTagDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeActivityTagDao extends GenericDao<CoeActivityTag,Long>{

	/**
	 * 根据Id查询CoeActivityTag实体对象
	 * @param id
	 * @return
	 */
	CoeActivityTag findById(Long id);
	
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeActivityTag findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeActivityTag实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeActivityTag> findByIdList(List<Long> idList);
	
	public Page<CoeActivityTag> findPage(CoeActivityTag coeActivityTag, Pageable pageable);
	
	public CoeActivityTag findByMainIdAndName(CoeActivityTag coeActivityTag) ;
	
	public Long countByMainId(Long activityId);
	
	public List<CoeActivityTag> findByMainId(Long activityId);
	
	public List<CoeActivityTag> findByMainIdList(List<Long> activityIdList);
	
	public Page<CoeActivityTag> findPageWithNotself(CoeActivityTag coeActivityTag, Pageable pageable);
	
}
