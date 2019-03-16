package dml.sz0099.course.app.persist.dao.user;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.CoeUserVerify;

/**
 * CoeUserVerifyDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeUserVerifyDao extends GenericDao<CoeUserVerify,Long>{

	/**
	 * 根据Id查询CoeUserVerify实体对象
	 * @param id
	 * @return
	 */
	CoeUserVerify findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeUserVerify findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeUserVerify实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeUserVerify> findByIdList(List<Long> idList);
	
	public Page<CoeUserVerify> findPage(CoeUserVerify coeUserVerify, Pageable pageable);
	
	public CoeUserVerify findNotSelfByIdentity(CoeUserVerify coeUser) ;
	public CoeUserVerify findByIdentity(String identity);
	
	public CoeUserVerify findByUserId(Long userId);
	
	public CoeUserVerify findByCoeUserId(Long coeUserId);
	
	public List<CoeUserVerify> findByUserIdList(List<Long> userIdList);
	
	public Page<CoeUserVerify> findPageForVerify(CoeUserVerify coeUserVerify, Pageable pageable);
	
	
}
