package dml.sz0099.course.app.persist.dao.tag;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.tag.CoeTag;

/**
 * CoeTagDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeTagDao extends GenericDao<CoeTag,Long>{

	/**
	 * 根据Id查询CoeTag实体对象
	 * @param id
	 * @return
	 */
	CoeTag findById(Long id);
	
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeTag findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeTag实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeTag> findByIdList(List<Long> idList);
	
	public Page<CoeTag> findPage(CoeTag coeTag, Pageable pageable);
	
	public CoeTag findByName(String name);
	
}
