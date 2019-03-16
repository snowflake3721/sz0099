package dml.sz0099.course.app.persist.dao.user;

import java.util.Date;
import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.user.Sayword;

/**
 * SaywordDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface SaywordDao extends GenericDao<Sayword,Long>{

	/**
	 * 根据Id查询Sayword实体对象
	 * @param id
	 * @return
	 */
	Sayword findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	Sayword findByAid(Long aid);
	
	/**
	 * 根据IdList查询Sayword实体对象列表
	 * @param idList
	 * @return
	 */
	public List<Sayword> findByIdList(List<Long> idList);
	
	public Page<Sayword> findPage(Sayword sayword, Pageable pageable);
	
	public List<Sayword> findByUserIdAndMainId(Long userId, Integer mainType);
	
	public List<Sayword> findByUserIdAndPublishTime(Long userId, Date begin, Date end) ;
	
}
