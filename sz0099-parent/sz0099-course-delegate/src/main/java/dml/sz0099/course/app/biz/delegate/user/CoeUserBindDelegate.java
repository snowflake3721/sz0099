package dml.sz0099.course.app.biz.delegate.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.user.CoeUserBind;

/**
 * CoeUserBindDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeUserBindDelegate {

	/**
	 * 根据Id查询CoeUserBind实体对象
	 * @param id
	 * @return
	 */
	public CoeUserBind findById(Long id);
	
	public boolean existById(Long id);
	
	public CoeUserBind findByAid(Long aid);

	/**
	 * 根据IdList查询CoeUserBind实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeUserBind> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeUserBind
	 * @return
	 */
	public CoeUserBind persistEntity(CoeUserBind coeUserBind) ;
	
	public CoeUserBind mergeEntity(CoeUserBind coeUserBind) ; 
	
	public CoeUserBind saveOrUpdate(CoeUserBind coeUserBind) ;
	
	public Page<CoeUserBind> findPage(CoeUserBind coeUserBind, Pageable pageable) ;
	
}
