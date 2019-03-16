package dml.sz0099.course.app.persist.dao.article;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.article.Photo;

/**
 * PhotoDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface PhotoDao extends GenericDao<Photo,Long>{

	/**
	 * 根据Id查询Photo实体对象
	 * @param id
	 * @return
	 */
	Photo findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	Photo findByAid(Long aid);
	
	/**
	 * 根据IdList查询Photo实体对象列表
	 * @param idList
	 * @return
	 */
	public List<Photo> findByIdList(List<Long> idList);
	
	public Page<Photo> findPage(Photo photo, Pageable pageable);
	
	public void deleteByIdListAndUserId(List<Long> idList, Long userId );


}
