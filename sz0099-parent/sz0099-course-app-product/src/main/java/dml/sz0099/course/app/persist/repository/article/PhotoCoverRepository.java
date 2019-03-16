package dml.sz0099.course.app.persist.repository.article;

import java.util.List;

import org.jit4j.app.code.template.CodeGenerated;
import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.article.PhotoCover;

/**
 * @formatter:off
 * 
 * description: PhotoCoverRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository("articlePhotoCoverRepository")
public interface PhotoCoverRepository extends BasicJpaRepository<PhotoCover,Long> {

	@Query("select e from dml.sz0099.course.app.persist.entity.article.PhotoCover e where e.id=?1")
	PhotoCover findById(Long id);
	
	@Query("select e from dml.sz0099.course.app.persist.entity.article.PhotoCover e where e.aid=?1")
	PhotoCover findByAid(Long aid);
	
	@Query("select e from dml.sz0099.course.app.persist.entity.article.PhotoCover e where e.id in ?1")
	public List<PhotoCover> findByIdList(List<Long> idList);
	
	@CodeGenerated
	@Modifying
	@Query("delete from dml.sz0099.course.app.persist.entity.article.PhotoCover e where e.subId=?1 and e.mainId=?2")
	public void deleteBySubIdAndMainId(Long subId,Long mainId);
	
	@Query("select e from dml.sz0099.course.app.persist.entity.article.PhotoCover e where e.subId in ?1 and e.mainId=?2 order by e.orderSeq asc")
	public List<PhotoCover> findBySubIdListAndMainId(List<Long> subIdList, Long mainId) ;

	@Modifying
	@Query("delete from dml.sz0099.course.app.persist.entity.article.PhotoCover e where e.subId in ?1 and e.mainId=?2")
	public void deleteBySubIdListAndMainId(List<Long> subIdList, Long mainId);
	
	@Modifying
	@Query("delete from dml.sz0099.course.app.persist.entity.article.PhotoCover e where e.id =?1")
	public void deleteById(Long id);
	
	@Query("select e from dml.sz0099.course.app.persist.entity.article.PhotoCover e where e.mainId=?1")
	public List<PhotoCover> findByMainId(Long mainId);
	
	@Query("select e from dml.sz0099.course.app.persist.entity.article.PhotoCover e where e.mainId=?1 and e.subId=?2")
	public List<PhotoCover> findByMainIdAndSubId(Long mainId, Long subId);
	
	@Query("select e from dml.sz0099.course.app.persist.entity.article.PhotoCover e where e.mainId in ?1 and e.subId=?2")
	public List<PhotoCover> findByMainIdListAndSubId(List<Long> mainIdList, Long subId);
	
	@Query("select count(e) from dml.sz0099.course.app.persist.entity.article.PhotoCover e where e.mainId=?1 and e.deleted=false")
	public Long countByMainId(Long mainId);
}
