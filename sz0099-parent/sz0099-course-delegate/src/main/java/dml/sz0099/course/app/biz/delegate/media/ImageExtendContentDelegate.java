package dml.sz0099.course.app.biz.delegate.media;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.media.ImageExtendContent;

/**
 * ImageExtendContentDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ImageExtendContentDelegate {

	/**
	 * 根据Id查询ImageExtendContent实体对象
	 * @param id
	 * @return
	 */
	public ImageExtendContent findById(Long id);
	
	public boolean existById(Long id);
	
	public ImageExtendContent findByAid(Long aid);

	/**
	 * 根据IdList查询ImageExtendContent实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ImageExtendContent> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param imageExtendContent
	 * @return
	 */
	public ImageExtendContent persistEntity(ImageExtendContent imageExtendContent) ;
	
	public ImageExtendContent mergeEntity(ImageExtendContent imageExtendContent) ; 
	
	public ImageExtendContent saveOrUpdate(ImageExtendContent imageExtendContent) ;
	
	public Page<ImageExtendContent> findPage(ImageExtendContent imageExtendContent, Pageable pageable) ;
	
}
