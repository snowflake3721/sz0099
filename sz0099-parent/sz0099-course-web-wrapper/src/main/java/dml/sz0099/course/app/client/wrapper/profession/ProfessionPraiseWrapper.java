package dml.sz0099.course.app.client.wrapper.profession;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.ProfessionPraise;


/**
 * ProfessionPraiseWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ProfessionPraiseWrapper {

	/**
	 * 根据Id查询ProfessionPraise实体对象
	 * @param id
	 * @return
	 */
	public ProfessionPraise findById(Long id);
	
	public boolean existById(Long id);
	
	public ProfessionPraise findByAid(Long aid);
	
	/**
	 * 根据IdList查询ProfessionPraise实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ProfessionPraise> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param professionPraise
	 * @return
	 */
	public ProfessionPraise persistEntity(ProfessionPraise professionPraise) ;
	
	public ProfessionPraise mergeEntity(ProfessionPraise professionPraise) ; 
	
	public ProfessionPraise saveOrUpdate(ProfessionPraise professionPraise) ;
	
	public Page<ProfessionPraise> findPage(ProfessionPraise professionPraise, Pageable pageable) ; 
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public boolean hasPraisedByMainIdAndUserId(Long mainId, Long userId);
	public ProfessionPraise findByMainIdAndUserId(Long mainId, Long userId);
	public Page<ProfessionPraise> findByMainId(Long mainId, Pageable pageable) ; 
	public ProfessionPraise praise(ProfessionPraise professionPraise);
	public ProfessionPraise mergeForRefreshTime(ProfessionPraise professionPraise);
	
}
