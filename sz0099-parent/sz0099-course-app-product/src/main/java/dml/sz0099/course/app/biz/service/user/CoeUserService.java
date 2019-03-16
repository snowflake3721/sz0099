package dml.sz0099.course.app.biz.service.user;

import java.util.List;
import java.util.Map;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.user.CoeUser;

/**
 * CoeUserService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeUserService extends GenericService<CoeUser,Long>{


	/**
	 * 根据Id查询CoeUser实体对象
	 * @param id
	 * @return
	 */
	public CoeUser findById(Long id);
	
	public CoeUser findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeUser实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeUser> findByIdList(List<Long> idList);
	
	public CoeUser  findByUserId(Long userId);
	
	
	
	
	/**
	 * 持久化实体
	 * @param coeUser
	 * @return
	 */
	public CoeUser persistEntity(CoeUser coeUser) ;
	
	public CoeUser createCoeUser(CoeUser coeUser) ;
	
	
	public CoeUser mergeEntity(CoeUser coeUser) ; 
	
	public CoeUser saveOrUpdate(CoeUser coeUser) ;
	
	public Page<CoeUser> findPage(CoeUser coeUser, Pageable pageable) ;
	
	public CoeUser findByEmail(String email) ;
	
	public CoeUser findByMobile(String mobile) ;
	
	public CoeUser mergeForEmail(CoeUser coeUser);
	
	public CoeUser mergeForMobile(CoeUser coeUser) ;
	
	public CoeUser deleteImage(CoeUser coeUser);
	
	public CoeUser mereForImage(CoeUser coeUser) ;
	
	public CoeUser  findByUserId(Long userId, boolean withImages, boolean withVerify, boolean withSayword) ;
	
	
	
	public CoeUser mergeForNickname(CoeUser coeUser);
	public CoeUser mergeForPostname(CoeUser coeUser);
	
	public CoeUser mergeForPostnameShow(CoeUser coeUser) ;
	public CoeUser mergeForMobileShow(CoeUser coeUser) ;
	public CoeUser mergeForEmailShow(CoeUser coeUser) ;
	public CoeUser mergeForQqShow(CoeUser coeUser) ;
	
	public CoeUser mergeForSayword(CoeUser coeUser) ;
	
	public List<CoeUser> findByUserIdList(List<Long> userIdList, boolean withImages) ;
	public List<CoeUser> findByUserIdList(List<Long> userIdList) ;
	
	public Map<Long, CoeUser> findMapByUserIdList(List<Long> userIdList, boolean withImages) ;
	
	public CoeUser  findByUserId(Long userId, boolean withVerify);
	
}
