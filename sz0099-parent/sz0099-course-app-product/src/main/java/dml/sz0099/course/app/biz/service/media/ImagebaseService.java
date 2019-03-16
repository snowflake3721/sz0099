package dml.sz0099.course.app.biz.service.media;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.entity.media.Imagebase;

/**
 * ImagebaseService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ImagebaseService extends GenericService<Imagebase,Long>{


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
	
	public List<Imagebase> saveForUpload(List<Imagebase> images);
	
}
