package dml.sz0099.course.app.biz.service.profession;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.ProfessionRef;

/**
 * ProfessionRefService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ProfessionRefService extends GenericService<ProfessionRef,Long>{


	/**
	 * 根据Id查询ProfessionRef实体对象
	 * @param id
	 * @return
	 */
	public ProfessionRef findById(Long id);
	
	public boolean existById(Long id);
	
	public ProfessionRef findByAid(Long aid);
	
	/**
	 * 根据IdList查询ProfessionRef实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ProfessionRef> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param professionRef
	 * @return
	 */
	public ProfessionRef persistEntity(ProfessionRef professionRef) ;
	
	
	public ProfessionRef mergeEntity(ProfessionRef professionRef) ; 
	
	public ProfessionRef saveOrUpdate(ProfessionRef professionRef) ;
	
	public Page<ProfessionRef> findPage(ProfessionRef professionRef, Pageable pageable) ;
	
	public void deleteByBaseId(Long baseId);
	public void deleteByBaseId(Long baseId, boolean cascade);
	public void deleteByBaseIdList(List<Long> baseIdList, boolean cascade);
	
	public Long countForBase(ProfessionRef professionRef);
	
	public ProfessionRef changeSingle(ProfessionRef professionRef);
	
	public List<ProfessionRef> findByBaseId(Long baseId);
	public List<ProfessionRef> findByBaseIdList(List<Long> baseIdList);
	
	public Page<ProfessionRef> findPageByBaseId(Long baseId, Pageable pageable, boolean withCover, boolean withBanner);
	public List<ProfessionRef> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList);
	
	public ProfessionRef findMainIdAndBaseId(ProfessionRef professionRef);
	
	public void deleteById(ProfessionRef professionRef) ;
	public void deleteByBaseId(ProfessionRef professionRef);
	
	public ProfessionRef mergeProfessionRef(ProfessionRef professionRef) ;
	public ProfessionRef openProfessionRef(ProfessionRef professionRef);
	public ProfessionRef mergeSimpleSingle(ProfessionRef professionRef);
	
	public ProfessionRef deleteRefByBaseId(ProfessionRef professionRef) ;
}
