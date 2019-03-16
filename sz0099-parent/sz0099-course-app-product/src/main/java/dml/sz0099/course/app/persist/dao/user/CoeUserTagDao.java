package dml.sz0099.course.app.persist.dao.user;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.user.CoeUserTag;

/**
 * CoeUserTagDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeUserTagDao extends GenericDao<CoeUserTag,Long>{

	/**
	 * 根据Id查询CoeUserTag实体对象
	 * @param id
	 * @return
	 */
	CoeUserTag findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeUserTag findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeUserTag实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeUserTag> findByIdList(List<Long> idList);
	
	public Page<CoeUserTag> findPage(CoeUserTag coeUserTag, Pageable pageable);
	
}
