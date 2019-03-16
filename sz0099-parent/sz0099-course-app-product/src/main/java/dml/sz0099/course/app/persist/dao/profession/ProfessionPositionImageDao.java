package dml.sz0099.course.app.persist.dao.profession;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.ProfessionPositionImage;

/**
 * @formatter:off
 * ProfessionPositionImageDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * @formatter:on
 */
public interface ProfessionPositionImageDao extends GenericDao<ProfessionPositionImage,Long>{

	/**
	 * 根据Id查询ProfessionPositionImage实体对象
	 * @param id
	 * @return
	 */
	ProfessionPositionImage findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	ProfessionPositionImage findByAid(Long aid);
	
	/**
	 * 根据IdList查询ProfessionPositionImage实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ProfessionPositionImage> findByIdList(List<Long> idList);
	
	public Page<ProfessionPositionImage> findPage(ProfessionPositionImage professionPosition, Pageable pageable);
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public ProfessionPositionImage findByMainIdAndUserId(Long mainId, Long userId);
	public Page<ProfessionPositionImage> findByMainId(Long mainId, Pageable pageable) ; 
	
	public List<ProfessionPositionImage> findByRefId(Long positionId) ;
	public List<ProfessionPositionImage> findByRefIdList(List<Long> refIdList);
	public void deleteByRefIdList(List<Long> refIdList);
	public void deleteByRefId(Long refId);
}
