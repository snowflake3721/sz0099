package dml.sz0099.course.app.biz.delegate.profession;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.ProfessionExtend;

/**
 * ProfessionExtendDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ProfessionExtendDelegate {

	/**
	 * 根据Id查询ProfessionExtend实体对象
	 * @param id
	 * @return
	 */
	public ProfessionExtend findById(Long id);
	
	public boolean existById(Long id);
	
	public ProfessionExtend findByAid(Long aid);

	/**
	 * 根据IdList查询ProfessionExtend实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ProfessionExtend> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param professionExtend
	 * @return
	 */
	public ProfessionExtend persistEntity(ProfessionExtend professionExtend) ;
	
	public ProfessionExtend mergeEntity(ProfessionExtend professionExtend) ; 
	
	public ProfessionExtend saveOrUpdate(ProfessionExtend professionExtend) ;
	
	public Page<ProfessionExtend> findPage(ProfessionExtend professionExtend, Pageable pageable) ;
	
	public List<ProfessionExtend> findAll();
	
	public ProfessionExtend create(ProfessionExtend professionExtend) ;
	
	public ProfessionExtend findByPositionId(Long positionId) ;

	public ProfessionExtend findProfessionExtend(ProfessionExtend extend) ;
	
	public Long findPositionIdById(Long id);
	
	public Long countByUserId(Long userId) ;
	
	public ProfessionExtend deleteEntity(ProfessionExtend extend);
	
}
