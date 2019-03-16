package dml.sz0099.course.app.biz.service.category;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.category.CategoryExtendContent;

/**
 * CategoryExtendContentService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CategoryExtendContentService extends GenericService<CategoryExtendContent,Long>{


	/**
	 * 根据Id查询CategoryExtendContent实体对象
	 * @param id
	 * @return
	 */
	public CategoryExtendContent findById(Long id);
	
	public boolean existById(Long id);
	
	public CategoryExtendContent findByAid(Long aid);
	
	/**
	 * 根据IdList查询CategoryExtendContent实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CategoryExtendContent> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param categoryExtendContent
	 * @return
	 */
	public CategoryExtendContent persistEntity(CategoryExtendContent categoryExtendContent) ;
	
	
	public CategoryExtendContent mergeEntity(CategoryExtendContent categoryExtendContent) ; 
	
	public CategoryExtendContent saveOrUpdate(CategoryExtendContent categoryExtendContent) ;
	
	public Page<CategoryExtendContent> findPage(CategoryExtendContent categoryExtendContent, Pageable pageable) ;
	
}
