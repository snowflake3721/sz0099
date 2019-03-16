package dml.sz0099.course.app.persist.dao.profession;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.Profession;

/**
 * ProfessionDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ProfessionDao extends GenericDao<Profession,Long>{

	/**
	 * 根据Id查询Profession实体对象
	 * @param id
	 * @return
	 */
	Profession findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	Profession findByAid(Long aid);
	
	/**
	 * 根据IdList查询Profession实体对象列表
	 * @param idList
	 * @return
	 */
	public List<Profession> findByIdList(List<Long> idList);
	
	public Page<Profession> findPage(Profession profession, Pageable pageable);
	
	public Page<Profession> findPublished(Profession profession, Pageable pageable);
	
	public List<Profession> findDraftList(Profession profession);
	
	public Long countDraftList(Profession profession);
	
	public List<Profession> findByUserId(Long userId);
	
	public Long countByUserId(Long userId) ;
	
	public List<Profession> findPublishedByName(String name);
	
	public List<Profession> findPublishedByTitle(String title);
	
	public Page<Profession> findByPublished(Profession profession, Pageable pageable);
	
	public Page<Profession> findByPublishedNotSelf(Profession profession, Pageable pageable);
	
	public Long countForPublishedWithoutSelf(Long userId, Long positionId);
	
	public Page<Profession> findPageByMainTypeAndUserId(Integer mainType, List<Long> userIdList, Integer publishStatus, Pageable pageable) ;
	public List<Profession> findListByMainTypeAndUserId(Integer mainType, List<Long> userIdList, Integer publishStatus);
	public List<Profession> findByUserIdAndMainType(Integer mainType,Long userId, Integer publishStatus);
	public List<Profession> findByUserIdAndMainType( Integer mainType, Long userId);
}
