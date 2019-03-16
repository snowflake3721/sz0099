package dml.sz0099.course.app.biz.delegate.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.user.CoeUserGrade;

/**
 * CoeUserGradeDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeUserGradeDelegate {

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
	
	/**
	 * 持久化实体
	 * @param coeUserGrade
	 * @return
	 */
	public CoeUserGrade persistEntity(CoeUserGrade coeUserGrade) ;
	
	public CoeUserGrade mergeEntity(CoeUserGrade coeUserGrade) ; 
	
	public CoeUserGrade saveOrUpdate(CoeUserGrade coeUserGrade) ;
	
	public Page<CoeUserGrade> findPage(CoeUserGrade coeUserGrade, Pageable pageable) ;
	
	public CoeUserGrade findByUserId(Long userId) ;
	
}
