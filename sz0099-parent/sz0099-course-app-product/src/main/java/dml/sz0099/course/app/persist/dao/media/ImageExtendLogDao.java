package dml.sz0099.course.app.persist.dao.media;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.media.ImageExtendLog;

/**
 * ImageExtendLogDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ImageExtendLogDao extends GenericDao<ImageExtendLog,Long>{

	/**
	 * 根据Id查询ImageExtendLog实体对象
	 * @param id
	 * @return
	 */
	ImageExtendLog findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	ImageExtendLog findByAid(Long aid);
	
	/**
	 * 根据IdList查询ImageExtendLog实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ImageExtendLog> findByIdList(List<Long> idList);
	
	public Page<ImageExtendLog> findPage(ImageExtendLog imageExtendLog, Pageable pageable);
	
}
