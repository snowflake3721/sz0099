package dml.sz0099.course.app.client.wrapper.profession;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.ProfessionRef;


/**
 * ProfessionRefWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ProfessionRefWrapper {

	/**
	 * 根据Id查询ProfessionRef实体对象
	 * @param id
	 * @return
	 */
	public ProfessionRef findById(Long id);
	
	public ProfessionRef findById(Long id, boolean withBanner) ;
	
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
	
	public Long countForMain(ProfessionRef professionRef);

	public Long countForSub(ProfessionRef professionRef);

	public Long countForBase(ProfessionRef professionRef);
	
	public Long findPositionId(Long id);
	public List<ProfessionRef> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList);
	
	public ProfessionRef changeSingle(ProfessionRef professionRef);
	public ProfessionRef addProfessionRef(ProfessionRef professionRef);
	
	public void deleteById(ProfessionRef professionRef);
	public void deleteProfessionRef(ProfessionRef professionRef);
	public ProfessionRef deleteByMainIdAndSubId(ProfessionRef professionRef);
	
	public ProfessionRef openProfessionRef(ProfessionRef professionRef);
	public ProfessionRef mergeProfessionRef(ProfessionRef professionRef);
	public ProfessionRef mergeSimpleSingle(ProfessionRef professionRef);
	public ProfessionRef deleteRefByBaseId(ProfessionRef professionRef);
	
	
	
	public Page<ProfessionRef> findPageByBaseId(Long baseId, Pageable pageable, boolean withCover, boolean withBanner);
	
	public ProfessionRef findMainIdAndBaseId(ProfessionRef professionRef) ;
}
