package dml.sz0099.course.app.code.persist.dao.template;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.code.persist.entity.template.Demo;

/**
 * DemoDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface DemoDao extends GenericDao<Demo,Long>{

	/**
	 * 根据Id查询Demo实体对象
	 * @param id
	 * @return
	 */
	Demo findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	Demo findByAid(Long aid);
	
	/**
	 * 根据IdList查询Demo实体对象列表
	 * @param idList
	 * @return
	 */
	public List<Demo> findByIdList(List<Long> idList);
	
	public Page<Demo> findPage(Demo demo, Pageable pageable);
	
}
