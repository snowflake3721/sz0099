package dml.sz0099.course.app.persist.dao.profession;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.ProfessionExtendContent;

/**
 * ProfessionExtendContentDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ProfessionExtendContentDao extends GenericDao<ProfessionExtendContent,Long>{

	/**
	 * 根据Id查询ProfessionExtendContent实体对象
	 * @param id
	 * @return
	 */
	ProfessionExtendContent findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	ProfessionExtendContent findByAid(Long aid);
	
	/**
	 * 根据IdList查询ProfessionExtendContent实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ProfessionExtendContent> findByIdList(List<Long> idList);
	
	public Page<ProfessionExtendContent> findPage(ProfessionExtendContent professionExtendContent, Pageable pageable);
	
}
