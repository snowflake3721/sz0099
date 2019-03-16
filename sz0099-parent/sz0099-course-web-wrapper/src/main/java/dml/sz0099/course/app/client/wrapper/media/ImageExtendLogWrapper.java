package dml.sz0099.course.app.client.wrapper.media;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.entity.media.ImageExtendLog;


/**
 * ImageExtendLogWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ImageExtendLogWrapper {

	/**
	 * 根据Id查询ImageExtendLog实体对象
	 * @param id
	 * @return
	 */
	public ImageExtendLog findById(Long id);
	
	public boolean existById(Long id);
	
	public ImageExtendLog findByAid(Long aid);
	
	/**
	 * 根据IdList查询ImageExtendLog实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ImageExtendLog> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param imageExtendLog
	 * @return
	 */
	public ImageExtendLog persistEntity(ImageExtendLog imageExtendLog) ;
	
	public ImageExtendLog persistForFail(ImageExtend imageExtend) ;
	
	public ImageExtendLog mergeEntity(ImageExtendLog imageExtendLog) ; 
	
	public ImageExtendLog saveOrUpdate(ImageExtendLog imageExtendLog) ;
	
	public Page<ImageExtendLog> findPage(ImageExtendLog imageExtendLog, Pageable pageable) ; 
	
}
