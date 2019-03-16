package dml.sz0099.course.app.biz.delegate.category;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.category.CategoryExtend;
import dml.sz0099.course.app.persist.entity.category.CategoryExtendLog;

/**
 * CategoryExtendLogDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CategoryExtendLogDelegate {

	/**
	 * 根据Id查询CategoryExtendLog实体对象
	 * @param id
	 * @return
	 */
	public CategoryExtendLog findById(Long id);
	
	public boolean existById(Long id);
	
	public CategoryExtendLog findByAid(Long aid);

	/**
	 * 根据IdList查询CategoryExtendLog实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CategoryExtendLog> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param categoryExtendLog
	 * @return
	 */
	public CategoryExtendLog persistEntity(CategoryExtendLog categoryExtendLog) ;
	
	public CategoryExtendLog persistForFail(CategoryExtend categoryExtend) ;
	
	public CategoryExtendLog mergeEntity(CategoryExtendLog categoryExtendLog) ; 
	
	public CategoryExtendLog saveOrUpdate(CategoryExtendLog categoryExtendLog) ;
	
	public Page<CategoryExtendLog> findPage(CategoryExtendLog categoryExtendLog, Pageable pageable) ;
	
}
