package dml.sz0099.course.app.persist.dao.media;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.media.ImageRef;

/**
 * ImageRefDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ImageRefDao extends GenericDao<ImageRef,Long>{

	/**
	 * 根据Id查询ImageRef实体对象
	 * @param id
	 * @return
	 */
	ImageRef findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	ImageRef findByAid(Long aid);
	
	/**
	 * 根据IdList查询ImageRef实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ImageRef> findByIdList(List<Long> idList);
	
	public Page<ImageRef> findPage(ImageRef imageRef, Pageable pageable);
	
	public Long countForMain(ImageRef imageRef);
	
	public Long countForSub(ImageRef imageRef);
	
	public Long countForBase(ImageRef imageRef) ;
	
	/**
	 * @param extendId
	 * @param mainId
	 * @return
	 */
	 List<ImageRef> findByExtendIdAndMainId(Long extendId, Long mainId) ;

	/**
	 * @param extendId
	 * @param mainId
	 * @param subId
	 * @return
	 */
	 List<ImageRef> findByExtendIdAndMainId(Long extendId, Long mainId, Long subId) ;
}
