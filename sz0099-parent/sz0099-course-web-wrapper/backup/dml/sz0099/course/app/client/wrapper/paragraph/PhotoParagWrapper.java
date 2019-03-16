package dml.sz0099.course.app.client.wrapper.paragraph;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.paragraph.PhotoParag;


/**
 * PhotoParagWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface PhotoParagWrapper {

	/**
	 * 根据Id查询PhotoParag实体对象
	 * @param id
	 * @return
	 */
	public PhotoParag findById(Long id);
	
	public boolean existById(Long id);
	
	public PhotoParag findByAid(Long aid);
	
	/**
	 * 根据IdList查询PhotoParag实体对象列表
	 * @param idList
	 * @return
	 */
	public List<PhotoParag> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param photoParag
	 * @return
	 */
	public PhotoParag persistEntity(PhotoParag photoParag) ;
	
	public PhotoParag mergeEntity(PhotoParag photoParag) ; 
	
	public PhotoParag saveOrUpdate(PhotoParag photoParag) ;
	
	public Page<PhotoParag> findPage(PhotoParag photoParag, Pageable pageable) ; 
	
}
