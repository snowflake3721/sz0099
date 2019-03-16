package dml.sz0099.course.app.persist.dao.user;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import dml.sz0099.course.app.persist.entity.user.CoeUser;

/**
 * CoeUserDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeUserDao extends GenericDao<CoeUser,Long>{

	/**
	 * 根据Id查询CoeUser实体对象
	 * @param id
	 * @return
	 */
	CoeUser findById(Long id);
	
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeUser findByAid(Long aid);
	
	public CoeUser  findByUserId(Long userId);
	
	/**
	 * 根据IdList查询CoeUser实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeUser> findByIdList(List<Long> idList);
	
	public Page<CoeUser> findPage(CoeUser coeUser, Pageable pageable);
	
	public CoeUser findByEmail(String email) ;
	
	public CoeUser findByMobile(String mobile);
	
	
	public List<CoeUser> findByUserIdList(List<Long> userIdList);
	
}
