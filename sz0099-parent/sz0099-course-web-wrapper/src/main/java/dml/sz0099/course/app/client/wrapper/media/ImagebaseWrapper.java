package dml.sz0099.course.app.client.wrapper.media;

import java.util.List;

import org.jit4j.core.pub.transfer.dto.ImageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.media.Imagebase;


/**
 * ImagebaseWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ImagebaseWrapper {

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
	
	public Imagebase persistImage(ImageRequest request);
	
}
