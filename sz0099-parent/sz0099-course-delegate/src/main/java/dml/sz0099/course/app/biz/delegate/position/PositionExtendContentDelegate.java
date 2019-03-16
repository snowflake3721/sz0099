package dml.sz0099.course.app.biz.delegate.position;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.position.PositionExtendContent;

/**
 * PositionExtendContentDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface PositionExtendContentDelegate {

	/**
	 * 根据Id查询PositionExtendContent实体对象
	 * @param id
	 * @return
	 */
	public PositionExtendContent findById(Long id);
	
	public boolean existById(Long id);
	
	public PositionExtendContent findByAid(Long aid);

	/**
	 * 根据IdList查询PositionExtendContent实体对象列表
	 * @param idList
	 * @return
	 */
	public List<PositionExtendContent> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param positionExtendContent
	 * @return
	 */
	public PositionExtendContent persistEntity(PositionExtendContent positionExtendContent) ;
	
	public PositionExtendContent mergeEntity(PositionExtendContent positionExtendContent) ; 
	
	public PositionExtendContent saveOrUpdate(PositionExtendContent positionExtendContent) ;
	
	public Page<PositionExtendContent> findPage(PositionExtendContent positionExtendContent, Pageable pageable) ;
	
}
