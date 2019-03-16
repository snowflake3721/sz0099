package dml.sz0099.course.app.biz.service.profession;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.ProfessionRef;
import dml.sz0099.course.app.persist.entity.profession.ProfessionCover;
import dml.sz0099.course.app.persist.entity.profession.ProfessionImage;

/**
 * ProfessionCoverService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ProfessionCoverService extends GenericService<ProfessionCover,Long>{


	/**
	 * 根据Id查询ProfessionCover实体对象
	 * @param id
	 * @return
	 */
	public ProfessionCover findById(Long id);
	
	public boolean existById(Long id);
	
	public ProfessionCover findByAid(Long aid);
	
	/**
	 * 根据IdList查询ProfessionCover实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ProfessionCover> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param professionRef
	 * @return
	 */
	public ProfessionCover persistEntity(ProfessionCover professionRef) ;
	
	
	public ProfessionCover mergeEntity(ProfessionCover professionRef) ; 
	
	public ProfessionCover saveOrUpdate(ProfessionCover professionRef) ;
	
	public Page<ProfessionCover> findPage(ProfessionCover professionRef, Pageable pageable) ;
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public ProfessionCover findByMainIdAndUserId(Long mainId, Long userId);
	public Page<ProfessionCover> findByMainId(Long mainId, Pageable pageable) ; 
	public List<ProfessionCover> findByRefIdList(List<Long> refIdList);
	
	public boolean hasPositionByMainIdAndUserId(Long mainId, Long userId);
	public ProfessionCover mergeForProfession(ProfessionCover professionRef);
	
	public ProfessionRef mergeForProfession(ProfessionRef professionRef);
	
	public ProfessionRef addProfessionCover(ProfessionRef professionRef);
	public void deleteByRefIdList(List<Long> refIdList);
	public void deleteByRefId(Long refId);
	
}
