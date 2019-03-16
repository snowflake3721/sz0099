package dml.sz0099.course.app.code.persist.repository.template;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.code.persist.entity.template.Demo;

/**
 * @formatter:off
 * 
 * description: DemoRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface DemoRepository extends BasicJpaRepository<Demo,Long> {

	@Query("select e from Demo e where e.id=?1")
	Demo findById(Long id);
	
	@Query("select e from Demo e where e.aid=?1")
	Demo findByAid(Long aid);
	
	@Query("select e from Demo e where e.id in ?1")
	public List<Demo> findByIdList(List<Long> idList);
}
