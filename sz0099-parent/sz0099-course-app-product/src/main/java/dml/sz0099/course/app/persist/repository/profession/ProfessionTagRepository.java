package dml.sz0099.course.app.persist.repository.profession;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.profession.ProfessionTag;

/**
 * @formatter:off
 * 
 * description: ProfessionTagRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface ProfessionTagRepository extends BasicJpaRepository<ProfessionTag,Long> {

	@Query("select e from ProfessionTag e where e.id=?1")
	ProfessionTag findById(Long id);
	
	@Query("select e from ProfessionTag e where e.aid=?1")
	ProfessionTag findByAid(Long aid);
	
	@Query("select e from ProfessionTag e where e.id in ?1")
	public List<ProfessionTag> findByIdList(List<Long> idList);
	
	@Query("select e from ProfessionTag e where e.mainId=?1 and e.name=?2")
	public ProfessionTag findByMainIdAndName(Long professionId, String name);
	
	@Query("select count(e) from ProfessionTag e where e.mainId=?1")
	public Long countByMainId(Long professionId) ;
	
	@Query("select e from ProfessionTag e where e.mainId=?1")
	public List<ProfessionTag> findByMainId(Long professionId) ;
	
	@Query("select e from ProfessionTag e where e.mainId in ?1 and e.deleted=false")
	public List<ProfessionTag> findByMainIdList(List<Long> mainIdList) ;
}
