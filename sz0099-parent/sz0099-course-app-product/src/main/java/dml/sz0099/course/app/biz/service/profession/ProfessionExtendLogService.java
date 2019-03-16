package dml.sz0099.course.app.biz.service.profession;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.ProfessionExtend;
import dml.sz0099.course.app.persist.entity.profession.ProfessionExtendLog;

/**
 * ProfessionExtendLogService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ProfessionExtendLogService extends GenericService<ProfessionExtendLog,Long>{


	/**
	 * 根据Id查询ProfessionExtendLog实体对象
	 * @param id
	 * @return
	 */
	public ProfessionExtendLog findById(Long id);
	
	public boolean existById(Long id);
	
	public ProfessionExtendLog findByAid(Long aid);
	
	/**
	 * 根据IdList查询ProfessionExtendLog实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ProfessionExtendLog> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param professionExtendLog
	 * @return
	 */
	public ProfessionExtendLog persistEntity(ProfessionExtendLog professionExtendLog) ;
	
	
	public ProfessionExtendLog mergeEntity(ProfessionExtendLog professionExtendLog) ; 
	
	public ProfessionExtendLog saveOrUpdate(ProfessionExtendLog professionExtendLog) ;
	
	public Page<ProfessionExtendLog> findPage(ProfessionExtendLog professionExtendLog, Pageable pageable) ;
	
	public ProfessionExtendLog persistForFail(ProfessionExtend professionExtend);
}
