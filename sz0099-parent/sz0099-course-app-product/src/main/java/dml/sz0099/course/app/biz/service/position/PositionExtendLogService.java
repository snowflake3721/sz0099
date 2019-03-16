package dml.sz0099.course.app.biz.service.position;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.position.PositionExtend;
import dml.sz0099.course.app.persist.entity.position.PositionExtendLog;

/**
 * PositionExtendLogService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface PositionExtendLogService extends GenericService<PositionExtendLog,Long>{


	/**
	 * 根据Id查询PositionExtendLog实体对象
	 * @param id
	 * @return
	 */
	public PositionExtendLog findById(Long id);
	
	public boolean existById(Long id);
	
	public PositionExtendLog findByAid(Long aid);
	
	/**
	 * 根据IdList查询PositionExtendLog实体对象列表
	 * @param idList
	 * @return
	 */
	public List<PositionExtendLog> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param positionExtendLog
	 * @return
	 */
	public PositionExtendLog persistEntity(PositionExtendLog positionExtendLog) ;
	
	
	public PositionExtendLog mergeEntity(PositionExtendLog positionExtendLog) ; 
	
	public PositionExtendLog saveOrUpdate(PositionExtendLog positionExtendLog) ;
	
	public Page<PositionExtendLog> findPage(PositionExtendLog positionExtendLog, Pageable pageable) ;
	
	public PositionExtendLog persistForFail(PositionExtend positionExtend);
}
