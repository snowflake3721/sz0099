package dml.sz0099.course.app.persist.dao.user;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.user.CoeUserGrade;

/**
 * CoeUserGradeDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeUserGradeDao extends GenericDao<CoeUserGrade,Long>{

	/**
	 * 根据Id查询CoeUserGrade实体对象
	 * @param id
	 * @return
	 */
	CoeUserGrade findById(Long id);
	
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeUserGrade findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeUserGrade实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeUserGrade> findByIdList(List<Long> idList);
	
	public CoeUserGrade findByUserId(Long userId);
	
	public Page<CoeUserGrade> findPage(CoeUserGrade coeUserGrade, Pageable pageable);
	
}
