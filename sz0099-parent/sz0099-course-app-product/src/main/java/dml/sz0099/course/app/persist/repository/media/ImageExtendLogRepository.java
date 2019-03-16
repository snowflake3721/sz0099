package dml.sz0099.course.app.persist.repository.media;

import java.util.List;

import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.media.ImageExtendLog;

/**
 * @formatter:off
 * 
 * description: ImageExtendLogRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface ImageExtendLogRepository extends BasicJpaRepository<ImageExtendLog,Long> {

	@Query("select e from ImageExtendLog e where e.id=?1")
	ImageExtendLog findById(Long id);
	
	@Query("select e from ImageExtendLog e where e.aid=?1")
	ImageExtendLog findByAid(Long aid);
	
	@Query("select e from ImageExtendLog e where e.id in ?1")
	public List<ImageExtendLog> findByIdList(List<Long> idList);
}
