package dml.sz0099.course.app.biz.delegate.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.user.CoeUserGradeLog;

/**
 * CoeUserGradeLogDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeUserGradeLogDelegate {

	/**
	 * 根据Id查询CoeUserGradeLog实体对象
	 * @param id
	 * @return
	 */
	public CoeUserGradeLog findById(Long id);
	
	public CoeUserGradeLog findByAid(Long aid);

	/**
	 * 根据IdList查询CoeUserGradeLog实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeUserGradeLog> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeUserGradeLog
	 * @return
	 */
	public CoeUserGradeLog persistEntity(CoeUserGradeLog coeUserGradeLog) ;
	
	public CoeUserGradeLog mergeEntity(CoeUserGradeLog coeUserGradeLog) ; 
	
	public CoeUserGradeLog saveOrUpdate(CoeUserGradeLog coeUserGradeLog) ;
	
	public Page<CoeUserGradeLog> findPage(CoeUserGradeLog coeUserGradeLog, Pageable pageable) ;
	
}
