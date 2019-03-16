package dml.sz0099.course.app.client.wrapper.media;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.media.ImageRef;


/**
 * ImageRefWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ImageRefWrapper {

	/**
	 * 根据Id查询ImageRef实体对象
	 * @param id
	 * @return
	 */
	public ImageRef findById(Long id);
	
	public boolean existById(Long id);
	
	public ImageRef findByAid(Long aid);
	
	/**
	 * 根据IdList查询ImageRef实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ImageRef> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param imageRef
	 * @return
	 */
	public ImageRef persistEntity(ImageRef imageRef) ;
	
	public ImageRef mergeEntity(ImageRef imageRef) ; 
	
	public ImageRef saveOrUpdate(ImageRef imageRef) ;
	
	public Page<ImageRef> findPage(ImageRef imageRef, Pageable pageable) ; 
	
	public Long countForMain(ImageRef imageRef);

	public Long countForSub(ImageRef imageRef);

	public Long countForBase(ImageRef imageRef);
	
	public Long findPositionId(Long id);
	
	public ImageRef mergeForTitle(ImageRef imageRef);
	
	public void deleteById(ImageRef imageRef);
	public ImageRef deleteByMainIdAndSubId(ImageRef imageRef);
	
}
