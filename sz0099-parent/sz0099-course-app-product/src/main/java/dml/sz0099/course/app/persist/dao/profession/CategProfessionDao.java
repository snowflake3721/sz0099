package dml.sz0099.course.app.persist.dao.profession;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.CategProfession;

/**
 * CategProfessionDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CategProfessionDao extends GenericDao<CategProfession,Long>{

	/**
	 * 根据Id查询CategProfession实体对象
	 * @param id
	 * @return
	 */
	CategProfession findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CategProfession findByAid(Long aid);
	
	
	
	
	/**
	 * 根据IdList查询CategProfession实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CategProfession> findByIdList(List<Long> idList);
	
	public Page<CategProfession> findPage(CategProfession coeCategArticle, Pageable pageable);
	
	public List<CategProfession> findByMainIdList(List<Long> productIdList) ;
	
	public List<CategProfession> findByMainId(Long productId);
	
	public Page<CategProfession> findPageForPublish(CategProfession categProfession, Pageable pageable);
	public Page<CategProfession> findPageForPublishFromDetail(CategProfession categProfession, Pageable pageable);
	
	public Page<CategProfession> findPageForPublish(CategProfession categProfession, List<Long> excludeMainIdList, Pageable pageable);
	public Page<CategProfession> findPageForPublishWithChildren(CategProfession categProfession, List<Long> baseIdList, List<Long> excludeMainIdList, Pageable pageable);
}
