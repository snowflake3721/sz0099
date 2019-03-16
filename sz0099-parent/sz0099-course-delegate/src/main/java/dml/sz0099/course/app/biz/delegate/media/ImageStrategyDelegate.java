package dml.sz0099.course.app.biz.delegate.media;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.data.ImageStrategy;

/**
 * ImageStrategyDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ImageStrategyDelegate {

	/**
	 * 根据Id查询ImageStrategy实体对象
	 * @param id
	 * @return
	 */
	public ImageStrategy findById(Long id);
	
	public boolean existById(Long id);
	
	public ImageStrategy findByAid(Long aid);

	/**
	 * 根据IdList查询ImageStrategy实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ImageStrategy> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param imageStrategy
	 * @return
	 */
	public ImageStrategy persistEntity(ImageStrategy imageStrategy) ;
	
	public ImageStrategy mergeEntity(ImageStrategy imageStrategy) ; 
	
	public ImageStrategy saveOrUpdate(ImageStrategy imageStrategy) ;
	
	public Page<ImageStrategy> findPage(ImageStrategy imageStrategy, Pageable pageable) ;
	
}
