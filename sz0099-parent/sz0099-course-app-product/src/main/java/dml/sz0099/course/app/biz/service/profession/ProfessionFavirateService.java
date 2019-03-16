package dml.sz0099.course.app.biz.service.profession;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.ProfessionFavirate;

/**
 * ProfessionFavirateService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ProfessionFavirateService extends GenericService<ProfessionFavirate,Long>{


	/**
	 * 根据Id查询ProfessionFavirate实体对象
	 * @param id
	 * @return
	 */
	public ProfessionFavirate findById(Long id);
	
	public boolean existById(Long id);
	
	public ProfessionFavirate findByAid(Long aid);
	
	/**
	 * 根据IdList查询ProfessionFavirate实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ProfessionFavirate> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param professionFavirate
	 * @return
	 */
	public ProfessionFavirate persistEntity(ProfessionFavirate professionFavirate) ;
	
	
	public ProfessionFavirate mergeEntity(ProfessionFavirate professionFavirate) ; 
	
	public ProfessionFavirate saveOrUpdate(ProfessionFavirate professionFavirate) ;
	
	public Page<ProfessionFavirate> findPage(ProfessionFavirate professionFavirate, Pageable pageable) ;
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public ProfessionFavirate findByMainIdAndUserId(Long mainId, Long userId);
	public Page<ProfessionFavirate> findByMainId(Long mainId, Pageable pageable) ; 
	
	public boolean hasFaviratedByMainIdAndUserId(Long mainId, Long userId);
	public ProfessionFavirate mergeForFavirate(ProfessionFavirate professionFavirate);
	
}
