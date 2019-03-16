package dml.sz0099.course.app.persist.dao.profession;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.ProfessionCover;

/**
 * @formatter:off
 * ProfessionCoverDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * @formatter:on
 */
public interface ProfessionCoverDao extends GenericDao<ProfessionCover,Long>{

	/**
	 * 根据Id查询ProfessionCover实体对象
	 * @param id
	 * @return
	 */
	ProfessionCover findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	ProfessionCover findByAid(Long aid);
	
	/**
	 * 根据IdList查询ProfessionCover实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ProfessionCover> findByIdList(List<Long> idList);
	
	public Page<ProfessionCover> findPage(ProfessionCover professionPosition, Pageable pageable);
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public ProfessionCover findByMainIdAndUserId(Long mainId, Long userId);
	public Page<ProfessionCover> findByMainId(Long mainId, Pageable pageable) ; 
	
	public List<ProfessionCover> findByRefId(Long positionId) ;
	public List<ProfessionCover> findByRefIdList(List<Long> refIdList) ;
	public void deleteByRefIdList(List<Long> refIdList);
	public void deleteByRefId(Long refId);
}
