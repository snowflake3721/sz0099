package dml.sz0099.course.app.persist.dao.media;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.media.ImageExtendContent;

/**
 * ImageExtendContentDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ImageExtendContentDao extends GenericDao<ImageExtendContent,Long>{

	/**
	 * 根据Id查询ImageExtendContent实体对象
	 * @param id
	 * @return
	 */
	ImageExtendContent findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	ImageExtendContent findByAid(Long aid);
	
	/**
	 * 根据IdList查询ImageExtendContent实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ImageExtendContent> findByIdList(List<Long> idList);
	
	public Page<ImageExtendContent> findPage(ImageExtendContent imageExtendContent, Pageable pageable);
	
}
