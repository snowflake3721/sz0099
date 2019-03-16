package dml.sz0099.course.app.persist.dao.user;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.user.CoeUserBind;

/**
 * CoeUserBindDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeUserBindDao extends GenericDao<CoeUserBind,Long>{

	/**
	 * 根据Id查询CoeUserBind实体对象
	 * @param id
	 * @return
	 */
	CoeUserBind findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeUserBind findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeUserBind实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeUserBind> findByIdList(List<Long> idList);
	
	public Page<CoeUserBind> findPage(CoeUserBind coeUserBind, Pageable pageable);
	
}
