package dml.sz0099.course.app.client.wrapper.profession;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.ProfessionExtendContent;


/**
 * ProfessionExtendContentWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ProfessionExtendContentWrapper {

	/**
	 * 根据Id查询ProfessionExtendContent实体对象
	 * @param id
	 * @return
	 */
	public ProfessionExtendContent findById(Long id);
	
	public boolean existById(Long id);
	
	public ProfessionExtendContent findByAid(Long aid);
	
	/**
	 * 根据IdList查询ProfessionExtendContent实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ProfessionExtendContent> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param professionExtendContent
	 * @return
	 */
	public ProfessionExtendContent persistEntity(ProfessionExtendContent professionExtendContent) ;
	
	public ProfessionExtendContent mergeEntity(ProfessionExtendContent professionExtendContent) ; 
	
	public ProfessionExtendContent saveOrUpdate(ProfessionExtendContent professionExtendContent) ;
	
	public Page<ProfessionExtendContent> findPage(ProfessionExtendContent professionExtendContent, Pageable pageable) ; 
	
}
