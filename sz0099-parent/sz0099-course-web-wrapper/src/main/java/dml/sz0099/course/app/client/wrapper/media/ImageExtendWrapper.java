package dml.sz0099.course.app.client.wrapper.media;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.media.ImageExtend;


/**
 * ImageExtendWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ImageExtendWrapper {

	/**
	 * 根据Id查询ImageExtend实体对象
	 * @param id
	 * @return
	 */
	public ImageExtend findById(Long id);
	
	public boolean existById(Long id);
	
	public ImageExtend findByAid(Long aid);
	
	/**
	 * 根据IdList查询ImageExtend实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ImageExtend> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param imageExtend
	 * @return
	 */
	public ImageExtend persistEntity(ImageExtend imageExtend) ;
	
	public ImageExtend mergeEntity(ImageExtend imageExtend) ; 
	
	public ImageExtend saveOrUpdate(ImageExtend imageExtend) ;
	
	public Page<ImageExtend> findPage(ImageExtend imageExtend, Pageable pageable) ; 
	
	public ImageExtend findByPositionId(Long positionId);
	
	public ImageExtend findImageExtend(ImageExtend extend) ;
	
	public ImageExtend saveForUpload(ImageExtend extend);
	
}
