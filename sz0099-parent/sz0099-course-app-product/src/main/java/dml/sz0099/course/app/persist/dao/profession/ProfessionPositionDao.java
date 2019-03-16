package dml.sz0099.course.app.persist.dao.profession;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.ProfessionPosition;

/**
 * @formatter:off
 * ProfessionPositionDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * @formatter:on
 */
public interface ProfessionPositionDao extends GenericDao<ProfessionPosition,Long>{

	/**
	 * 根据Id查询ProfessionPosition实体对象
	 * @param id
	 * @return
	 */
	ProfessionPosition findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	ProfessionPosition findByAid(Long aid);
	
	/**
	 * 根据IdList查询ProfessionPosition实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ProfessionPosition> findByIdList(List<Long> idList);
	
	public Page<ProfessionPosition> findPage(ProfessionPosition professionPosition, Pageable pageable);
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public List<ProfessionPosition> findByMainIdAndUserId(Long mainId, Long userId);
	public Page<ProfessionPosition> findByMainId(Long mainId, Pageable pageable) ; 
	
	public ProfessionPosition findByMainIdAndPosition(Long mainId, Integer position) ;
	
	public List<ProfessionPosition> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList);
	
	public List<ProfessionPosition> findByBaseId(Long baseId);
	public ProfessionPosition deleteRefByBaseId(ProfessionPosition positionRef) ;
	
	public Page<ProfessionPosition> findPageForPosition(Long ponMainId,Long ponSubId, Long positionId, Integer ponPanel, Pageable pageable);
	public Page<ProfessionPosition> findByBaseId(Long baseId, Pageable pageable);
	public Page<ProfessionPosition> findByBaseIdList(List<Long> baseIdList, Pageable pageable);
}
