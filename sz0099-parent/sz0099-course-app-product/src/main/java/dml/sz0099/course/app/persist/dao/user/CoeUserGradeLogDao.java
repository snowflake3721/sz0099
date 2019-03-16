package dml.sz0099.course.app.persist.dao.user;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.user.CoeUserGradeLog;

/**
 * CoeUserGradeLogDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeUserGradeLogDao extends GenericDao<CoeUserGradeLog,Long>{

	/**
	 * 根据Id查询CoeUserGradeLog实体对象
	 * @param id
	 * @return
	 */
	CoeUserGradeLog findById(Long id);
	
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeUserGradeLog findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeUserGradeLog实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeUserGradeLog> findByIdList(List<Long> idList);
	
	public Page<CoeUserGradeLog> findPage(CoeUserGradeLog coeUserGradeLog, Pageable pageable);
	
}
