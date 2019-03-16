package dml.sz0099.course.app.persist.dao.activity;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityUser;
import dml.sz0099.course.app.persist.entity.activity.vo.CoeActivityUserVo;

/**
 * CoeActivityUserDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeActivityUserDao extends GenericDao<CoeActivityUser,Long>{

	/**
	 * 根据Id查询CoeActivityUser实体对象
	 * @param id
	 * @return
	 */
	CoeActivityUser findById(Long id);
	
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeActivityUser findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeActivityUser实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeActivityUser> findByIdList(List<Long> idList);
	
	public Page<CoeActivityUser> findPage(CoeActivityUser coeActivityUser, Pageable pageable);
	
	public Long countByMainId(Long activityId);
	
	public List<CoeActivityUser> findByMainId(Long activityId);
	public Page<CoeActivityUser> findByMainId(Long activityId, Pageable pageable);
	
	public List<CoeActivityUser> findByBaseId(Long baseId);
	public CoeActivityUser findByBaseIdAndIdentity(Long baseId, String identity);
	public boolean existEffectiveUser(Long mainId, String identity, Long baseId) ;
	public Long countPayVerifyUsers(Long mainId);
	public List<CoeActivityUser> findByMainIdList(List<Long> activityIdList);
	
	public Page<CoeActivityUser> findPageWithNotself(CoeActivityUser coeActivityUser, Pageable pageable);
	
	public Long countByBaseId(Long baseId) ;
	
	public List<CoeActivityUserVo> countByMainIdList(List<Long> mainIdList) ;
}
