package dml.sz0099.course.app.persist.dao.profession;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.PhotoParag;

/**
 * PhotoParagDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface PhotoParagDao extends GenericDao<PhotoParag,Long>{

	/**
	 * 根据Id查询PhotoParag实体对象
	 * @param id
	 * @return
	 */
	PhotoParag findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	PhotoParag findByAid(Long aid);
	
	/**
	 * 根据IdList查询PhotoParag实体对象列表
	 * @param idList
	 * @return
	 */
	public List<PhotoParag> findByIdList(List<Long> idList);
	
	public Page<PhotoParag> findPage(PhotoParag photoParag, Pageable pageable);
	


	public void deleteByParagIdAndUserId(Long paragId, Long userId );
	
	public List<PhotoParag> findByParagIdListAndUserId(List<Long> idList, Long userId) ;
	
	public void deleteByParagIdListAndUserId(List<Long> paragIdList, Long userId);

	public void deleteById(Long id);
	
	public List<PhotoParag> findByParagIdList(List<Long> paragIdList);

}
