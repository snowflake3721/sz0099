package dml.sz0099.course.app.persist.dao.profession;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.ProfessionRef;

/**
 * ProfessionRefDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ProfessionRefDao extends GenericDao<ProfessionRef,Long>{

	/**
	 * 根据Id查询ProfessionRef实体对象
	 * @param id
	 * @return
	 */
	ProfessionRef findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	ProfessionRef findByAid(Long aid);
	
	/**
	 * 根据IdList查询ProfessionRef实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ProfessionRef> findByIdList(List<Long> idList);
	
	public Page<ProfessionRef> findPage(ProfessionRef professionRef, Pageable pageable);
	
	public void deleteByBaseId(Long baseId) ;
	public void deleteByBaseIdList(List<Long> baseIdList);
	
	public Long countForBase(ProfessionRef professionRef);
	
	public List<ProfessionRef> findByMainId(Long mainId);
	
	public void deleteByMainId(Long mainId);
	
	public List<ProfessionRef> findByBaseId(Long baseId);
	
	public Page<ProfessionRef> findPageByBaseId(Long baseId, Pageable pageable);
	
	public List<ProfessionRef> findByBaseIdList(List<Long> baseIdList);
	
	public List<ProfessionRef> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList) ;
	
	public ProfessionRef findMainIdAndBaseId(ProfessionRef professionRef);
	
	public ProfessionRef deleteRefByBaseId(ProfessionRef professionRef) ;
}
