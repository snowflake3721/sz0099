package dml.sz0099.course.app.persist.dao.media;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.media.ImageExtend;

/**
 * ImageExtendDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ImageExtendDao extends GenericDao<ImageExtend,Long>{

	/**
	 * 根据Id查询ImageExtend实体对象
	 * @param id
	 * @return
	 */
	ImageExtend findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	ImageExtend findByAid(Long aid);
	
	/**
	 * 根据IdList查询ImageExtend实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ImageExtend> findByIdList(List<Long> idList);
	
	public Page<ImageExtend> findPage(ImageExtend imageExtend, Pageable pageable);
	
	public ImageExtend findByPositionId(Long positionId);
	
	public ImageExtend findImageExtend(ImageExtend extend);
	
	public Long findPositionIdById(Long id) ;
	
}
