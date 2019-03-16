package dml.sz0099.course.app.biz.delegate.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.user.CoeUserTag;

/**
 * CoeUserTagDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeUserTagDelegate {

	/**
	 * 根据Id查询CoeUserTag实体对象
	 * @param id
	 * @return
	 */
	public CoeUserTag findById(Long id);
	
	public boolean existById(Long id);
	
	public CoeUserTag findByAid(Long aid);

	/**
	 * 根据IdList查询CoeUserTag实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeUserTag> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeUserTag
	 * @return
	 */
	public CoeUserTag persistEntity(CoeUserTag coeUserTag) ;
	
	public CoeUserTag mergeEntity(CoeUserTag coeUserTag) ; 
	
	public CoeUserTag saveOrUpdate(CoeUserTag coeUserTag) ;
	
	public Page<CoeUserTag> findPage(CoeUserTag coeUserTag, Pageable pageable) ;
	
}
