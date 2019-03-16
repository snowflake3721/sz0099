package dml.sz0099.course.app.persist.repository.profession;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.profession.ProfessionImage;

/**
 * @formatter:off
 * 
 * description: ProfessionImageRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface ProfessionImageRepository extends BasicJpaRepository<ProfessionImage,Long> {

	@Query("select e from ProfessionImage e where e.id=?1")
	ProfessionImage findById(Long id);
	
	@Query("select e from ProfessionImage e where e.aid=?1")
	ProfessionImage findByAid(Long aid);
	
	@Query("select e from ProfessionImage e where e.id in ?1")
	public List<ProfessionImage> findByIdList(List<Long> idList);
	
	@Query("select e from ProfessionImage e where e.refId in ?1 order by e.orderSeq asc")
	public List<ProfessionImage> findByRefIdList(List<Long> refIdList);
	
	@Modifying
	@Query("delete from ProfessionImage e where e.refId in ?1")
	public void deleteByRefIdList(List<Long> refIdList);
	
	
	@Modifying
	@Query("delete from ProfessionImage e where e.refId=?1")
	public void deleteByRefId(Long refId);
	
	@Query("select e from ProfessionImage e where e.refId=?1")
	public List<ProfessionImage> findByRefId(Long refId);
}
