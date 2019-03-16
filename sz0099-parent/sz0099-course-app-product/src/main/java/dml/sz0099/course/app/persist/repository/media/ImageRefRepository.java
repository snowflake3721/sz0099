package dml.sz0099.course.app.persist.repository.media;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.media.ImageRef;

/**
 * @formatter:off
 * 
 * description: ImageRefRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface ImageRefRepository extends BasicJpaRepository<ImageRef,Long> {

	@Query("select e from ImageRef e where e.id=?1")
	ImageRef findById(Long id);
	
	@Query("select e from ImageRef e where e.aid=?1")
	ImageRef findByAid(Long aid);
	
	@Query("select e from ImageRef e where e.id in ?1")
	public List<ImageRef> findByIdList(List<Long> idList);
	
	@Query("select count(e) from ImageRef e where e.extendId=?1 and e.mainId=?2 and e.subId=?3")
	public Long countForSub(Long extendId, Long mainId, Long subId);
	
	@Query("select count(e) from ImageRef e where e.extendId=?1 and e.mainId=?2")
	public Long countForMain(Long extendId, Long mainId);
	
	@Query("select count(e) from ImageRef e where e.baseId=?1")
	public Long countForBase(Long baseId);
	
	@Query("select e from ImageRef e where e.extendId=?1 and e.mainId=?2")
	public List<ImageRef> findByExtendIdAndMainId(Long extendId, Long mainId);
	
	@Query("select e from ImageRef e where e.extendId=?1 and e.mainId=?2 and e.subId=?3")
	public List<ImageRef> findByExtendIdAndMainId(Long extendId, Long mainId, Long subId);
	
}
