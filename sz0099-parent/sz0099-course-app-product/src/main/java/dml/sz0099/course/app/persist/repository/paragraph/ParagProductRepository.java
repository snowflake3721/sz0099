package dml.sz0099.course.app.persist.repository.paragraph;

import java.util.List;

import org.jit4j.app.code.template.CodeGenerated;
import org.jit4j.core.persist.repository.BasicJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.paragraph.ParagProduct;

/**
 * @formatter:off
 * 
 * description: ParagProductRepository 数据访问接口
 * @author bruce yang at 2018-08-24 22:35:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */

@Repository
public interface ParagProductRepository extends BasicJpaRepository<ParagProduct,Long> {

	@Query("select e from ParagProduct e where e.id=?1")
	ParagProduct findById(Long id);
	
	@Query("select e from ParagProduct e where e.aid=?1")
	ParagProduct findByAid(Long aid);
	
	@Query("select e from ParagProduct e where e.id in ?1")
	public List<ParagProduct> findByIdList(List<Long> idList);
	
	@CodeGenerated
	@Query("select e from ParagProduct e where e.mainId=?1")
	public Page<ParagProduct> findByMainId(Long productId, Pageable pageable);
	
	@CodeGenerated
	@Modifying
	@Query("delete from ParagProduct e where e.mainId=?1 and e.userId=?2")
	public void deleteByMainIdAndUserId(Long productId,Long userId);
	
	@Modifying
	@Query("delete from ParagProduct e where e.paragId=?1 and e.userId=?2")
	public void deleteByParagIdAndUserId(Long paragId, Long userId);
	
	@Query("select e from ParagProduct e where e.mainId=?1")
	public List<ParagProduct> findListByMainId(Long productId);
	
	@Query("select e from ParagProduct e where e.mainId=?1 and e.userId=?2")
	public List<ParagProduct> findListByMainIdAndUserId(Long productId,Long userId);

	
	@Query("select e from ParagProduct e where e.mainId=?1 and e.userId=?2")
	public Page<ParagProduct> findByMainIdAndUserId(Long productId, Long userId, Pageable pageable );
	
	@Query("select count(e) from ParagProduct e where e.mainId=?1")
	public Long countByMainId(Long productId);
}
