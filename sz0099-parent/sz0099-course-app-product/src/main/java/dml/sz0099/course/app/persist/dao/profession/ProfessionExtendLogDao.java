package dml.sz0099.course.app.persist.dao.profession;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.ProfessionExtendLog;

/**
 * ProfessionExtendLogDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ProfessionExtendLogDao extends GenericDao<ProfessionExtendLog,Long>{

	/**
	 * 根据Id查询ProfessionExtendLog实体对象
	 * @param id
	 * @return
	 */
	ProfessionExtendLog findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	ProfessionExtendLog findByAid(Long aid);
	
	/**
	 * 根据IdList查询ProfessionExtendLog实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ProfessionExtendLog> findByIdList(List<Long> idList);
	
	public Page<ProfessionExtendLog> findPage(ProfessionExtendLog professionExtendLog, Pageable pageable);
	
}
