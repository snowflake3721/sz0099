package dml.sz0099.course.app.client.wrapper.activity;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityFee;


/**
 * CoeActivityFeeWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeActivityFeeWrapper {

	/**
	 * 根据Id查询CoeActivityFee实体对象
	 * @param id
	 * @return
	 */
	public CoeActivityFee findById(Long id);
	
	public CoeActivityFee findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeActivityFee实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeActivityFee> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeActivityFee
	 * @return
	 */
	public CoeActivityFee persistEntity(CoeActivityFee coeActivityFee) ;
	
	public CoeActivityFee mergeEntity(CoeActivityFee coeActivityFee) ; 
	
	public CoeActivityFee saveOrUpdate(CoeActivityFee coeActivityFee) ;
	
	public Page<CoeActivityFee> findPage(CoeActivityFee coeActivityFee, Pageable pageable) ; 
	
	public CoeActivityFee findByMainId(CoeActivityFee coeActivityFee);
	public Long countByMainId(Long activityId);
	public CoeActivityFee findByMainId(Long activityId);
	public Map<Long,CoeActivityFee> findMapByMainIdList(List<Long> activityIdList);
	
	public CoeActivityFee addFee(CoeActivityFee coeActivityFee);
	public CoeActivityFee deleteFee(CoeActivityFee coeActivityFee);
	
	public Page<CoeActivityFee> findPageWithNotself(CoeActivityFee coeActivityFee, Pageable pageable);
	
	public CoeActivityFee mergeDescription(CoeActivityFee coeActivityFee);
	
	public CoeActivityFee mergeFee(CoeActivityFee coeActivityFee);
}
