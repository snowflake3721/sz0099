package dml.sz0099.course.app.biz.delegate.media;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.media.Imagebase;

/**
 * ImagebaseDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ImagebaseDelegate {

	/**
	 * 根据Id查询Imagebase实体对象
	 * @param id
	 * @return
	 */
	public Imagebase findById(Long id);
	
	public boolean existById(Long id);
	
	public Imagebase findByAid(Long aid);

	/**
	 * 根据IdList查询Imagebase实体对象列表
	 * @param idList
	 * @return
	 */
	public List<Imagebase> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param imagebase
	 * @return
	 */
	public Imagebase persistEntity(Imagebase imagebase) ;
	
	public Imagebase mergeEntity(Imagebase imagebase) ; 
	
	public Imagebase saveOrUpdate(Imagebase imagebase) ;
	
	public Page<Imagebase> findPage(Imagebase imagebase, Pageable pageable) ;
	
}
