package dml.sz0099.course.app.persist.repository.tag;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.tag.CoeTag;

/**
 * @formatter:off
 * 
 * description: CoeTagRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface CoeTagRepository extends BasicJpaRepository<CoeTag,Long> {

	@Query("select e from CoeTag e where e.id=?1")
	CoeTag findById(Long id);
	
	@Query("select e from CoeTag e where e.aid=?1")
	CoeTag findByAid(Long aid);
	
	@Query("select e from CoeTag e where e.id in ?1")
	public List<CoeTag> findByIdList(List<Long> idList);
	
	
	@Query("select e from CoeTag e where e.name=?1")
	public CoeTag findByName(String name);
}
