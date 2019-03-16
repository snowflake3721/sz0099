package dml.sz0099.course.app.persist.dao.media;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.media.Imagebase;

/**
 * ImagebaseDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ImagebaseDao extends GenericDao<Imagebase,Long>{

	/**
	 * 根据Id查询Imagebase实体对象
	 * @param id
	 * @return
	 */
	Imagebase findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	Imagebase findByAid(Long aid);
	
	/**
	 * 根据IdList查询Imagebase实体对象列表
	 * @param idList
	 * @return
	 */
	public List<Imagebase> findByIdList(List<Long> idList);
	
	public Page<Imagebase> findPage(Imagebase imagebase, Pageable pageable);
	
}
