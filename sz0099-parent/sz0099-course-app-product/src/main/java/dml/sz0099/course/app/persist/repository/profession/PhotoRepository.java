package dml.sz0099.course.app.persist.repository.profession;

import java.util.List;

import org.jit4j.app.code.template.CodeGenerated;
import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.profession.Photo;

/**
 * @formatter:off
 * 
 * description: PhotoRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository("profPhotoRepository")
public interface PhotoRepository extends BasicJpaRepository<Photo,Long> {

	@Query("select e from dml.sz0099.course.app.persist.entity.profession.Photo e where e.id=?1")
	Photo findById(Long id);
	
	@Query("select e from dml.sz0099.course.app.persist.entity.profession.Photo e where e.aid=?1")
	Photo findByAid(Long aid);
	
	@Query("select e from dml.sz0099.course.app.persist.entity.profession.Photo e where e.id in ?1")
	public List<Photo> findByIdList(List<Long> idList);
	
	@CodeGenerated
	@Modifying
	@Query("delete from dml.sz0099.course.app.persist.entity.profession.Photo e where e.id in ?1 and e.userId=?2")
	public void deleteByIdListAndUserId(List<Long> idList, Long userId);

}
