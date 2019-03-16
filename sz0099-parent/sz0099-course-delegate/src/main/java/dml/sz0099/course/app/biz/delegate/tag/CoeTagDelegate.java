package dml.sz0099.course.app.biz.delegate.tag;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.tag.CoeTag;

/**
 * CoeTagDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeTagDelegate {

	/**
	 * 根据Id查询CoeTag实体对象
	 * @param id
	 * @return
	 */
	public CoeTag findById(Long id);
	
	public CoeTag findByAid(Long aid);

	/**
	 * 根据IdList查询CoeTag实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeTag> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeTag
	 * @return
	 */
	public CoeTag persistEntity(CoeTag coeTag) ;
	
	public CoeTag mergeEntity(CoeTag coeTag) ; 
	
	public CoeTag saveOrUpdate(CoeTag coeTag) ;
	
	public Page<CoeTag> findPage(CoeTag coeTag, Pageable pageable) ;
	
}
