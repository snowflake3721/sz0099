package dml.sz0099.course.app.client.wrapper.paragraph;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.paragraph.Photo;


/**
 * PhotoWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface PhotoWrapper {

	/**
	 * 根据Id查询Photo实体对象
	 * @param id
	 * @return
	 */
	public Photo findById(Long id);
	
	public boolean existById(Long id);
	
	public Photo findByAid(Long aid);
	
	/**
	 * 根据IdList查询Photo实体对象列表
	 * @param idList
	 * @return
	 */
	public List<Photo> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param photo
	 * @return
	 */
	public Photo persistEntity(Photo photo) ;
	
	public Photo mergeEntity(Photo photo) ; 
	
	public Photo saveOrUpdate(Photo photo) ;
	
	public Page<Photo> findPage(Photo photo, Pageable pageable) ; 
	
}
