package dml.sz0099.course.app.persist.dao.product;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.product.CoeGrade;

/**
 * CoeGradeDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeGradeDao extends GenericDao<CoeGrade,Long>{

	/**
	 * 根据Id查询CoeGrade实体对象
	 * @param id
	 * @return
	 */
	CoeGrade findById(Long id);
	
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeGrade findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeGrade实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeGrade> findByIdList(List<Long> idList);
	
	public Page<CoeGrade> findPage(CoeGrade coeGrade, Pageable pageable);
	
	public CoeGrade findByGrade(Integer grade);
	
}
