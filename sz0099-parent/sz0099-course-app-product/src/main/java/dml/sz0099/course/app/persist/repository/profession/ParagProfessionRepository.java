package dml.sz0099.course.app.persist.repository.profession;

import java.util.List;

import org.jit4j.app.code.template.CodeGenerated;
import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.profession.ParagProfession;

/**
 * @formatter:off
 * 
 * description: ParagProfessionRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository("profParagProfessionRepository")
public interface ParagProfessionRepository extends BasicJpaRepository<ParagProfession,Long> {

	@Query("select e from ParagProfession e where e.id=?1")
	ParagProfession findById(Long id);
	
	@Query("select e from ParagProfession e where e.aid=?1")
	ParagProfession findByAid(Long aid);
	
	@Query("select e from ParagProfession e where e.id in ?1")
	public List<ParagProfession> findByIdList(List<Long> idList);
	
	@CodeGenerated
	@Query("select e from ParagProfession e where e.mainId=?1")
	public Page<ParagProfession> findByMainId(Long professionId, Pageable pageable);
	
	@CodeGenerated
	@Modifying
	@Query("delete from ParagProfession e where e.mainId=?1 and e.userId=?2")
	public void deleteByProfessionIdAndUserId(Long professionId,Long userId);
	
	@Modifying
	@Query("delete from ParagProfession e where e.paragId=?1 and e.userId=?2")
	public void deleteByParagIdAndUserId(Long paragId, Long userId);
	
	@Query("select e from ParagProfession e where e.mainId=?1")
	public List<ParagProfession> findListByMainId(Long professionId);
	
	@Query("select e from ParagProfession e where e.mainId=?1 and e.userId=?2")
	public List<ParagProfession> findListByMainIdAndUserId(Long professionId,Long userId);

	
	@Query("select e from ParagProfession e where e.mainId=?1 and e.userId=?2")
	public Page<ParagProfession> findByMainIdAndUserId(Long professionId, Long userId, Pageable pageable );
	
	@Query("select count(e) from ParagProfession e where e.mainId=?1")
	public Long countByMainId(Long professionId);
}
