package dml.sz0099.course.app.biz.delegate.product;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.product.CoeProductTag;

/**
 * CoeProductTagDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeProductTagDelegate {

	/**
	 * 根据Id查询CoeProductTag实体对象
	 * @param id
	 * @return
	 */
	public CoeProductTag findById(Long id);
	
	public CoeProductTag findByAid(Long aid);

	/**
	 * 根据IdList查询CoeProductTag实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeProductTag> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeProductTag
	 * @return
	 */
	public CoeProductTag persistEntity(CoeProductTag coeProductTag) ;
	
	public CoeProductTag mergeEntity(CoeProductTag coeProductTag) ; 
	
	public CoeProductTag saveOrUpdate(CoeProductTag coeProductTag) ;
	
	public Page<CoeProductTag> findPage(CoeProductTag coeProductTag, Pageable pageable) ;
	
	public CoeProductTag findByMainIdAndName(CoeProductTag coeProductTag);
	
	public CoeProductTag addTag(CoeProductTag coeProductTag);
	
	public CoeProductTag deleteTag(CoeProductTag coeProductTag);
	
	public Long countByMainId(Long productId) ;
	
	public List<CoeProductTag> findByMainId(Long productId) ;
	
	public Map<Long, List<CoeProductTag>> findMapByMainIdList(List<Long> productIdList) ;
	
}
