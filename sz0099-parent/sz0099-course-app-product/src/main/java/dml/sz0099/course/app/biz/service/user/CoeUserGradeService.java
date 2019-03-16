package dml.sz0099.course.app.biz.service.user;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.CoeUserGrade;

/**
 * CoeUserGradeService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeUserGradeService extends GenericService<CoeUserGrade,Long>{


	/**
	 * 根据Id查询CoeUserGrade实体对象
	 * @param id
	 * @return
	 */
	public CoeUserGrade findById(Long id);
	
	public CoeUserGrade findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeUserGrade实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeUserGrade> findByIdList(List<Long> idList);
	
	public CoeUserGrade  findByUserId(Long userId);
	
	/**
	 * 持久化实体
	 * @param coeUserGrade
	 * @return
	 */
	public CoeUserGrade persistEntity(CoeUserGrade coeUserGrade) ;
	
	public CoeUserGrade createUserGrade(CoeUser user) ;
	
	public CoeUserGrade mergeEntity(CoeUserGrade coeUserGrade) ; 
	
	public CoeUserGrade saveOrUpdate(CoeUserGrade coeUserGrade) ;
	
	public Page<CoeUserGrade> findPage(CoeUserGrade coeUserGrade, Pageable pageable) ;
	
	
	
}
