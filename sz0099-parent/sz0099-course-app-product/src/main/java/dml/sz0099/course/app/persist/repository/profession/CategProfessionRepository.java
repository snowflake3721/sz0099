package dml.sz0099.course.app.persist.repository.profession;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.profession.CategProfession;

/**
 * @formatter:off
 * 
 * description: CategProfessionRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface CategProfessionRepository extends BasicJpaRepository<CategProfession,Long> {

	@Query("select e from CategProfession e where e.id=?1")
	CategProfession findById(Long id);
	
	@Query("select e from CategProfession e where e.aid=?1")
	CategProfession findByAid(Long aid);
	
	@Query("select e from CategProfession e where e.id in ?1")
	public List<CategProfession> findByIdList(List<Long> idList);
	
	@Query("select e from CategProfession e where e.mainId in ?1 and e.deleted=false")
	public List<CategProfession> findByMainIdList(List<Long> mainIdList);
	
	@Query("select e from CategProfession e where e.mainId=?1 and e.deleted=false")
	public List<CategProfession> findByMainId(Long mainId);
}
