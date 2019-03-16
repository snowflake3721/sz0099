package dml.sz0099.course.app.biz.service.product;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.product.CoeGrade;

/**
 * CoeGradeService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeGradeService extends GenericService<CoeGrade,Long>{


	/**
	 * 根据Id查询CoeGrade实体对象
	 * @param id
	 * @return
	 */
	public CoeGrade findById(Long id);
	
	public CoeGrade findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeGrade实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeGrade> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeGrade
	 * @return
	 */
	public CoeGrade persistEntity(CoeGrade coeGrade) ;
	
	
	public CoeGrade mergeEntity(CoeGrade coeGrade) ; 
	
	public CoeGrade saveOrUpdate(CoeGrade coeGrade) ;
	
	public Page<CoeGrade> findPage(CoeGrade coeGrade, Pageable pageable) ;
	
	public List<CoeGrade> initData();
	
	public CoeGrade findByGrade(Integer grade);
	
}
