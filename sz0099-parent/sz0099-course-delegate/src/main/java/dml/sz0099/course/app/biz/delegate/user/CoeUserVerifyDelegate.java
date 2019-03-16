package dml.sz0099.course.app.biz.delegate.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.user.CoeUserVerify;

/**
 * CoeUserVerifyDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeUserVerifyDelegate {

	/**
	 * 根据Id查询CoeUserVerify实体对象
	 * @param id
	 * @return
	 */
	public CoeUserVerify findById(Long id);
	
	public boolean existById(Long id);
	
	public CoeUserVerify findByAid(Long aid);

	/**
	 * 根据IdList查询CoeUserVerify实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeUserVerify> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeUserVerify
	 * @return
	 */
	public CoeUserVerify persistEntity(CoeUserVerify coeUserVerify) ;
	
	public CoeUserVerify mergeEntity(CoeUserVerify coeUserVerify) ; 
	
	public CoeUserVerify saveOrUpdate(CoeUserVerify coeUserVerify) ;
	
	public Page<CoeUserVerify> findPage(CoeUserVerify coeUserVerify, Pageable pageable) ;
	
	public CoeUserVerify verifyIdentity(CoeUserVerify coeUser);
	public CoeUserVerify applyIdentity(CoeUserVerify coeUser) ;
	
	public CoeUserVerify findByIdentity(CoeUserVerify coeUser) ;
	public CoeUserVerify findNotSelfByIdentity(CoeUserVerify coeUser) ;
	
	public CoeUserVerify findByUserId(Long userId);
	
	public CoeUserVerify mergeForRealname(CoeUserVerify coeUser);
	
	public Page<CoeUserVerify> findPageForVerify(CoeUserVerify coeUserVerify, Pageable pageable);
}
