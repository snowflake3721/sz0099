package dml.sz0099.course.app.biz.service.category;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.category.CategoryRef;

/**
 * CategoryRefService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CategoryRefService extends GenericService<CategoryRef,Long>{


	/**
	 * 根据Id查询CategoryRef实体对象
	 * @param id
	 * @return
	 */
	public CategoryRef findById(Long id);
	
	public boolean existById(Long id);
	
	public CategoryRef findByAid(Long aid);
	
	/**
	 * 根据IdList查询CategoryRef实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CategoryRef> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param categoryRef
	 * @return
	 */
	public CategoryRef persistEntity(CategoryRef categoryRef) ;
	
	
	public CategoryRef mergeEntity(CategoryRef categoryRef) ; 
	
	public CategoryRef saveOrUpdate(CategoryRef categoryRef) ;
	
	public Page<CategoryRef> findPage(CategoryRef categoryRef, Pageable pageable) ;
	
	public void deleteByBaseId(Long baseId);
	
	public Long countForBase(CategoryRef categoryRef);
	
	public CategoryRef changeSingle(CategoryRef categoryRef);
	
	
}
