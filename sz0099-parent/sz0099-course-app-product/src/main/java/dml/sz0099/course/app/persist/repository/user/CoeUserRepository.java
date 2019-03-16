package dml.sz0099.course.app.persist.repository.user;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.user.CoeUser;

/**
 * @formatter:off
 * 
 * description: CoeUserRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface CoeUserRepository extends BasicJpaRepository<CoeUser,Long> {

	@Query("select e from CoeUser e where e.id=?1")
	CoeUser findById(Long id);
	
	@Query("select e from CoeUser e where e.aid=?1")
	CoeUser findByAid(Long aid);
	
	@Query("select e from CoeUser e where e.id in ?1")
	public List<CoeUser> findByIdList(List<Long> idList);
	
	@Query("select e from CoeUser e where e.userId=?1")
	public CoeUser  findByUserId(Long userId);
	
	@Query("select e from CoeUser e where e.email=?1")
	public CoeUser findByEmail(String email) ;
	
	@Query("select e from CoeUser e where e.mobile=?1")
	public CoeUser findByMobile(String mobile) ;
	
	
	@Query("select e from CoeUser e where e.userId in ?1")
	public List<CoeUser> findByUserIdList(List<Long> userIdList);
}
