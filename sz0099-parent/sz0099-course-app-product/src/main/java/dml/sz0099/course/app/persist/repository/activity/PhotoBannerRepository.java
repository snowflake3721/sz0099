package dml.sz0099.course.app.persist.repository.activity;

import java.util.List;

import org.jit4j.app.code.template.CodeGenerated;
import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.activity.PhotoBanner;

/**
 * @formatter:off
 * 
 * description: PhotoBannerRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository("activityPhotoBannerRepository")
public interface PhotoBannerRepository extends BasicJpaRepository<PhotoBanner,Long> {

	@Query("select e from dml.sz0099.course.app.persist.entity.activity.PhotoBanner e where e.id=?1")
	PhotoBanner findById(Long id);
	
	@Query("select e from dml.sz0099.course.app.persist.entity.activity.PhotoBanner e where e.aid=?1")
	PhotoBanner findByAid(Long aid);
	
	@Query("select e from dml.sz0099.course.app.persist.entity.activity.PhotoBanner e where e.id in ?1")
	public List<PhotoBanner> findByIdList(List<Long> idList);
	
	@CodeGenerated
	@Modifying
	@Query("delete from dml.sz0099.course.app.persist.entity.activity.PhotoBanner e where e.subId=?1 and e.mainId=?2")
	public void deleteBySubIdAndMainId(Long subId,Long mainId);
	
	@Query("select e from dml.sz0099.course.app.persist.entity.activity.PhotoBanner e where e.subId in ?1 and e.mainId=?2 order by e.orderSeq asc")
	public List<PhotoBanner> findBySubIdListAndMainId(List<Long> subIdList, Long mainId) ;

	@Modifying
	@Query("delete from dml.sz0099.course.app.persist.entity.activity.PhotoBanner e where e.subId in ?1 and e.mainId=?2")
	public void deleteBySubIdListAndMainId(List<Long> subIdList, Long mainId);
	
	@Modifying
	@Query("delete from dml.sz0099.course.app.persist.entity.activity.PhotoBanner e where e.id =?1")
	public void deleteById(Long id);
	
	@Query("select e from dml.sz0099.course.app.persist.entity.activity.PhotoBanner e where e.mainId=?1")
	public List<PhotoBanner> findByMainId(Long mainId);
	
	@Query("select e from dml.sz0099.course.app.persist.entity.activity.PhotoBanner e where e.mainId=?1 and e.subId=?2")
	public List<PhotoBanner> findByMainIdAndSubId(Long mainId, Long subId);
	
	@Query("select e from dml.sz0099.course.app.persist.entity.activity.PhotoBanner e where e.mainId in ?1 and e.subId=?2")
	public List<PhotoBanner> findByMainIdListAndSubId(List<Long> mainIdList, Long subId);
	
	@Query("select count(e) from dml.sz0099.course.app.persist.entity.activity.PhotoBanner e where e.mainId=?1 and e.deleted=false")
	public Long countByMainId(Long mainId);
}
