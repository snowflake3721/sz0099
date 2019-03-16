package dml.sz0099.course.app.biz.service.activity;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityFavirate;

/**
 * CoeActivityFavirateService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeActivityFavirateService extends GenericService<CoeActivityFavirate,Long>{


	/**
	 * 根据Id查询CoeActivityFavirate实体对象
	 * @param id
	 * @return
	 */
	public CoeActivityFavirate findById(Long id);
	
	public boolean existById(Long id);
	
	public CoeActivityFavirate findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeActivityFavirate实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeActivityFavirate> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeActivityFavirate
	 * @return
	 */
	public CoeActivityFavirate persistEntity(CoeActivityFavirate coeActivityFavirate) ;
	
	
	public CoeActivityFavirate mergeEntity(CoeActivityFavirate coeActivityFavirate) ; 
	
	public CoeActivityFavirate saveOrUpdate(CoeActivityFavirate coeActivityFavirate) ;
	
	public Page<CoeActivityFavirate> findPage(CoeActivityFavirate coeActivityFavirate, Pageable pageable) ;
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public CoeActivityFavirate findByMainIdAndUserId(Long mainId, Long userId);
	public Page<CoeActivityFavirate> findByMainId(Long mainId, Pageable pageable) ; 
	
	public boolean hasFaviratedByMainIdAndUserId(Long mainId, Long userId);
	public CoeActivityFavirate mergeForFavirate(CoeActivityFavirate coeActivityFavirate);
	
}
