package dml.sz0099.course.app.biz.service.position;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.article.CoeArticlePositionCover;
import dml.sz0099.course.app.persist.entity.position.PositionCover;
import dml.sz0099.course.app.persist.entity.position.PositionRef;

/**
 * PositionCoverService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface PositionCoverService extends GenericService<PositionCover,Long>{


	/**
	 * 根据Id查询PositionCover实体对象
	 * @param id
	 * @return
	 */
	public PositionCover findById(Long id);
	
	public boolean existById(Long id);
	
	public PositionCover findByAid(Long aid);
	
	/**
	 * 根据IdList查询PositionCover实体对象列表
	 * @param idList
	 * @return
	 */
	public List<PositionCover> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param positionCover
	 * @return
	 */
	public PositionCover persistEntity(PositionCover positionCover) ;
	
	
	public PositionCover mergeEntity(PositionCover positionCover) ; 
	
	public PositionCover saveOrUpdate(PositionCover positionCover) ;
	
	public Page<PositionCover> findPage(PositionCover positionCover, Pageable pageable) ;
	
	public List<PositionCover> findByRefIdList(List<Long> refIdList);
	public List<PositionCover> findByRefId(Long refId);
	
	public void deleteByRefIdList(List<Long> refIdList);
	public void deleteByRefId(Long refId);
	public PositionRef addPositionCover(PositionRef positionRef);
	public PositionRef mergeForPosition(PositionRef positionRef);
	
}
