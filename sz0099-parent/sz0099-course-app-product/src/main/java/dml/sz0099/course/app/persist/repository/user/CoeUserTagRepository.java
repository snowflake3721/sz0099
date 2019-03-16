package dml.sz0099.course.app.persist.repository.user;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.user.CoeUserTag;

/**
 * @formatter:off
 * 
 * description: CoeUserTagRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface CoeUserTagRepository extends BasicJpaRepository<CoeUserTag,Long> {

	@Query("select e from CoeUserTag e where e.id=?1")
	CoeUserTag findById(Long id);
	
	@Query("select e from CoeUserTag e where e.aid=?1")
	CoeUserTag findByAid(Long aid);
	
	@Query("select e from CoeUserTag e where e.id in ?1")
	public List<CoeUserTag> findByIdList(List<Long> idList);
}
