package dml.sz0099.course.app.persist.dao.profession;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.ProfessionPositionCover;

/**
 * @formatter:off
 * ProfessionPositionCoverDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * @formatter:on
 */
public interface ProfessionPositionCoverDao extends GenericDao<ProfessionPositionCover,Long>{

	/**
	 * 根据Id查询ProfessionPositionCover实体对象
	 * @param id
	 * @return
	 */
	ProfessionPositionCover findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	ProfessionPositionCover findByAid(Long aid);
	
	/**
	 * 根据IdList查询ProfessionPositionCover实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ProfessionPositionCover> findByIdList(List<Long> idList);
	
	public Page<ProfessionPositionCover> findPage(ProfessionPositionCover professionPosition, Pageable pageable);
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public ProfessionPositionCover findByMainIdAndUserId(Long mainId, Long userId);
	public Page<ProfessionPositionCover> findByMainId(Long mainId, Pageable pageable) ; 
	
	public List<ProfessionPositionCover> findByRefId(Long positionId) ;
	public List<ProfessionPositionCover> findByRefIdList(List<Long> refIdList) ;
	public void deleteByRefIdList(List<Long> refIdList);
	public void deleteByRefId(Long refId);
}
