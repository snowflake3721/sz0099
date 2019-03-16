package dml.sz0099.course.app.persist.repository.user;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.CoeUserVerify;

/**
 * @formatter:off
 * 
 * description: CoeUserVerifyRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface CoeUserVerifyRepository extends BasicJpaRepository<CoeUserVerify,Long> {

	@Query("select e from CoeUserVerify e where e.id=?1")
	CoeUserVerify findById(Long id);
	
	@Query("select e from CoeUserVerify e where e.aid=?1")
	CoeUserVerify findByAid(Long aid);
	
	@Query("select e from CoeUserVerify e where e.id in ?1")
	public List<CoeUserVerify> findByIdList(List<Long> idList);
	
	@Query("select e from CoeUserVerify e where e.identity=?1 and e.userId !=?2")
	public CoeUserVerify findNotSelfbyIdentity(String identity, Long userId);
	
	@Query("select e from CoeUserVerify e where e.identity=?1")
	public CoeUserVerify findByIdentity(String identity);
	
	@Query("select e from CoeUserVerify e where e.userId=?1")
	public CoeUserVerify findByUserId(Long userId);
	
	@Query("select e from CoeUserVerify e where e.coeUserId=?1")
	public CoeUserVerify findByCoeUserId(Long coeUserId);
	
	@Query("select e from CoeUserVerify e where e.userId in ?1")
	public List<CoeUserVerify> findByUserIdList(List<Long> userIdList);
}
