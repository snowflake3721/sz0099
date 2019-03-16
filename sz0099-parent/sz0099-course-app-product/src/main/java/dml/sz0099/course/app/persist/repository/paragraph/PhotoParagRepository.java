package dml.sz0099.course.app.persist.repository.paragraph;

import java.util.List;

import org.jit4j.app.code.template.CodeGenerated;
import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.paragraph.PhotoParag;

/**
 * @formatter:off
 * 
 * description: PhotoParagRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface PhotoParagRepository extends BasicJpaRepository<PhotoParag,Long> {

	@Query("select e from dml.sz0099.course.app.persist.entity.paragraph.PhotoParag e where e.id=?1")
	PhotoParag findById(Long id);
	
	@Query("select e from dml.sz0099.course.app.persist.entity.paragraph.PhotoParag e where e.aid=?1")
	PhotoParag findByAid(Long aid);
	
	@Query("select e from dml.sz0099.course.app.persist.entity.paragraph.PhotoParag e where e.id in ?1")
	public List<PhotoParag> findByIdList(List<Long> idList);
	
	@CodeGenerated
	@Modifying
	@Query("delete from dml.sz0099.course.app.persist.entity.paragraph.PhotoParag e where e.paragId=?1 and e.userId=?2")
	public void deleteByParagIdAndUserId(Long paragId,Long userId);
	
	@Query("select e from dml.sz0099.course.app.persist.entity.paragraph.PhotoParag e where e.paragId in ?1 and e.userId=?2")
	public List<PhotoParag> findByParagIdListAndUserId(List<Long> paragIdList, Long userId) ;

	@Modifying
	@Query("delete from dml.sz0099.course.app.persist.entity.paragraph.PhotoParag e where e.paragId in ?1 and e.userId=?2")
	public void deleteByParagIdListAndUserId(List<Long> paragIdList, Long userId);
	
	@Modifying
	@Query("delete from dml.sz0099.course.app.persist.entity.paragraph.PhotoParag e where e.paragId =?1")
	public void deleteById(Long id);
	
	@Query("select e from dml.sz0099.course.app.persist.entity.paragraph.PhotoParag e where e.paragId in ?1 order by e.orderSeq asc")
	public List<PhotoParag> findByParagIdList(List<Long> paragIdList);
}
