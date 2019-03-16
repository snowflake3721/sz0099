package dml.sz0099.course.app.biz.service.profession;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.ProfessionPosition;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPositionCover;

/**
 * ProfessionPositionCoverService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ProfessionPositionCoverService extends GenericService<ProfessionPositionCover,Long>{


	/**
	 * 根据Id查询ProfessionPositionCover实体对象
	 * @param id
	 * @return
	 */
	public ProfessionPositionCover findById(Long id);
	
	public boolean existById(Long id);
	
	public ProfessionPositionCover findByAid(Long aid);
	
	/**
	 * 根据IdList查询ProfessionPositionCover实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ProfessionPositionCover> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param professionPosition
	 * @return
	 */
	public ProfessionPositionCover persistEntity(ProfessionPositionCover professionPosition) ;
	
	
	public ProfessionPositionCover mergeEntity(ProfessionPositionCover professionPosition) ; 
	
	public ProfessionPositionCover saveOrUpdate(ProfessionPositionCover professionPosition) ;
	
	public Page<ProfessionPositionCover> findPage(ProfessionPositionCover professionPosition, Pageable pageable) ;
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public ProfessionPositionCover findByMainIdAndUserId(Long mainId, Long userId);
	public Page<ProfessionPositionCover> findByMainId(Long mainId, Pageable pageable) ; 
	
	public boolean hasPositionByMainIdAndUserId(Long mainId, Long userId);
	public ProfessionPositionCover mergeForPosition(ProfessionPositionCover professionPosition);
	
	public ProfessionPosition mergeForPosition(ProfessionPosition professionPosition);
	
	public ProfessionPosition addPositionCover(ProfessionPosition professionPosition);
	public void deleteByRefIdList(List<Long> refIdList);
	public void deleteByRefId(Long refId);
	
	public List<ProfessionPositionCover> findByRefId(Long refId);
	public List<ProfessionPositionCover> findByRefIdList(List<Long> refIdList);
	
}
