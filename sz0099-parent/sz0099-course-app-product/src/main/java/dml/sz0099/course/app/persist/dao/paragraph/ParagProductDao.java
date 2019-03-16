package dml.sz0099.course.app.persist.dao.paragraph;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.paragraph.ParagProduct;

/**
 * ParagProductDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ParagProductDao extends GenericDao<ParagProduct,Long>{

	/**
	 * 根据Id查询ParagProduct实体对象
	 * @param id
	 * @return
	 */
	ParagProduct findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	ParagProduct findByAid(Long aid);
	
	/**
	 * 根据IdList查询ParagProduct实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ParagProduct> findByIdList(List<Long> idList);
	
	public Page<ParagProduct> findPage(ParagProduct paragProduct, Pageable pageable);
	public List<ParagProduct> findListByMainId(Long productId);
	public List<ParagProduct> findListByMainIdAndUserId(Long productId,Long userId);


	public Page<ParagProduct> findByMainId(Long productId, Pageable pageable );
	public Page<ParagProduct> findByMainIdAndUserId(Long productId, Long userId, Pageable pageable );



	public void deleteByMainIdAndUserId(Long productId, Long userId );
	public Long countByMainId(Long productId);
	public void deleteByParagIdAndUserId(Long paragId, Long userId, boolean cascade );

}
