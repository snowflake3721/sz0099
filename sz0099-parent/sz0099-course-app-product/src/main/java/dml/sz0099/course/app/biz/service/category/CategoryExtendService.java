package dml.sz0099.course.app.biz.service.category;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.category.CategoryExtend;

/**
 * CategoryExtendService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CategoryExtendService extends GenericService<CategoryExtend,Long>{


	/**
	 * 根据Id查询CategoryExtend实体对象
	 * @param id
	 * @return
	 */
	public CategoryExtend findById(Long id);
	
	public boolean existById(Long id);
	
	public CategoryExtend findByAid(Long aid);
	
	/**
	 * 根据IdList查询CategoryExtend实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CategoryExtend> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param categoryExtend
	 * @return
	 */
	public CategoryExtend persistEntity(CategoryExtend categoryExtend) ;
	
	
	public CategoryExtend mergeEntity(CategoryExtend categoryExtend) ; 
	
	public CategoryExtend saveOrUpdate(CategoryExtend categoryExtend) ;
	
	public Page<CategoryExtend> findPage(CategoryExtend categoryExtend, Pageable pageable) ;
	
	public CategoryExtend findByPositionId(Long positionId);
	
	public CategoryExtend findCategoryExtend(CategoryExtend extend) ;
	
	public Long findPositionIdById(Long id);
	
	public Long countByUserId(Long userId) ;
	
	public CategoryExtend deleteEntity(CategoryExtend extend);
}
