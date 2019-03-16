package dml.sz0099.course.app.biz.service.activity;

import java.util.List;
import java.util.Map;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityOrder;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityUser;
import dml.sz0099.course.app.persist.entity.activity.vo.CoeActivityUserVo;

/**
 * CoeActivityUserService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeActivityUserService extends GenericService<CoeActivityUser,Long>{


	/**
	 * 根据Id查询CoeActivityUser实体对象
	 * @param id
	 * @return
	 */
	public CoeActivityUser findById(Long id);
	
	public CoeActivityUser findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeActivityUser实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeActivityUser> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeActivityUser
	 * @return
	 */
	public CoeActivityUser persistEntity(CoeActivityUser coeActivityUser) ;
	
	
	public CoeActivityUser mergeEntity(CoeActivityUser coeActivityUser) ; 
	
	public CoeActivityUser saveOrUpdate(CoeActivityUser coeActivityUser) ;
	
	public Page<CoeActivityUser> findPage(CoeActivityUser coeActivityUser, Pageable pageable) ;
	
	public List<CoeActivityUser> findByMainId(CoeActivityUser coeActivityUser);
	
	public CoeActivityUser addUser(CoeActivityUser coeActivityUser) ;
	public CoeActivityUser addUser(CoeActivityOrder order) ;
	
	public CoeActivityUser deleteUser(CoeActivityUser coeActivityUser);
	
	public Long countByMainId(Long activityId) ;
	
	public List<CoeActivityUser> findByMainId(Long activityId);
	public Page<CoeActivityUser> findByMainId(Long activityId, Pageable pageable);
	
	public List<CoeActivityUser> findByBaseId(Long baseId);
	public CoeActivityUser findByBaseIdAndIdentity(Long baseId, String identity);
	
	public Map<Long, List<CoeActivityUser>> findMapByMainIdList(List<Long> mainIdList) ;
	
	public CoeActivityUser mergeMobile(CoeActivityUser coeActivityUser);
	public CoeActivityUser mergeRealname(CoeActivityUser coeActivityUser);
	public CoeActivityUser mergeIdentity(CoeActivityUser coeActivityUser);
	public CoeActivityUser mergeStatus(CoeActivityUser coeActivityUser);
	public CoeActivityOrder mergeStatus(CoeActivityOrder order);
	
	public CoeActivityOrder confirmOrder(CoeActivityOrder order);
	public CoeActivityOrder cancelOrder(CoeActivityOrder order);
	
	public boolean existEffectiveUser(Long mainId, String identity, Long baseId);
	
	public Long countPayVerifyUsers(Long mainId) ;
	
	public Long countByBaseId(Long baseId) ;
	
	public List<CoeActivityUserVo> countByMainIdList(List<Long> mainIdList) ;
	
}
