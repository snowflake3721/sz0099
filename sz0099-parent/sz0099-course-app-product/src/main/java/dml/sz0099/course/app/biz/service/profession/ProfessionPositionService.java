package dml.sz0099.course.app.biz.service.profession;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.ProfessionPosition;

/**
 * ProfessionPositionService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ProfessionPositionService extends GenericService<ProfessionPosition,Long>{


	/**
	 * 根据Id查询ProfessionPosition实体对象
	 * @param id
	 * @return
	 */
	public ProfessionPosition findById(Long id);
	
	public boolean existById(Long id);
	
	public ProfessionPosition findByAid(Long aid);
	
	/**
	 * 根据IdList查询ProfessionPosition实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ProfessionPosition> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param professionPosition
	 * @return
	 */
	public ProfessionPosition persistEntity(ProfessionPosition professionPosition) ;
	
	
	public ProfessionPosition mergeEntity(ProfessionPosition professionPosition) ; 
	
	public ProfessionPosition saveOrUpdate(ProfessionPosition professionPosition) ;
	
	public Page<ProfessionPosition> findPage(ProfessionPosition professionPosition, Pageable pageable) ;
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public List<ProfessionPosition> findByMainIdAndUserId(Long mainId, Long userId);
	public Page<ProfessionPosition> findByMainId(Long mainId, Pageable pageable) ; 
	
	public boolean hasPositionByMainIdAndUserId(Long mainId, Long userId);
	public ProfessionPosition mergeForPosition(ProfessionPosition professionPosition);
	public boolean hasPositionByMainIdAndPosition(Long mainId, Integer position);
	public ProfessionPosition findByMainIdAndPosition(Long mainId, Integer position);
	public List<ProfessionPosition> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList);
	
	public void deleteById(ProfessionPosition professionPosition);
	
	public ProfessionPosition mergePositionRef(ProfessionPosition professionPosition) ;
	public ProfessionPosition openPositionRef(ProfessionPosition positionRef) ;
	public ProfessionPosition mergeSimpleSingle(ProfessionPosition positionRef);
	public ProfessionPosition deleteRefByBaseId(ProfessionPosition positionRef);
	public List<ProfessionPosition> findByBaseId(ProfessionPosition positionRef) ;
	
	public Page<ProfessionPosition> findPageForPosition(Long ponMainId,Long ponSubId, Long positionId, Integer ponPanel, Pageable pageable);
	public Page<ProfessionPosition> findByBaseId(Long baseId, Pageable pageable);
	public Page<ProfessionPosition> findByBaseIdList(List<Long> baseIdList, Pageable pageable);
}
