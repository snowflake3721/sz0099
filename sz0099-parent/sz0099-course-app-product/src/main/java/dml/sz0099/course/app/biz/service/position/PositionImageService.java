package dml.sz0099.course.app.biz.service.position;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.article.CoeArticlePositionImage;
import dml.sz0099.course.app.persist.entity.position.PositionImage;
import dml.sz0099.course.app.persist.entity.position.PositionRef;

/**
 * PositionImageService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface PositionImageService extends GenericService<PositionImage,Long>{


	/**
	 * 根据Id查询PositionImage实体对象
	 * @param id
	 * @return
	 */
	public PositionImage findById(Long id);
	
	public boolean existById(Long id);
	
	public PositionImage findByAid(Long aid);
	
	/**
	 * 根据IdList查询PositionImage实体对象列表
	 * @param idList
	 * @return
	 */
	public List<PositionImage> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param positionImage
	 * @return
	 */
	public PositionImage persistEntity(PositionImage positionImage) ;
	
	
	public PositionImage mergeEntity(PositionImage positionImage) ; 
	
	public PositionImage saveOrUpdate(PositionImage positionImage) ;
	
	public Page<PositionImage> findPage(PositionImage positionImage, Pageable pageable) ;
	
	public List<PositionImage> findByRefIdList(List<Long> refIdList);
	public List<PositionImage> findByRefId(Long refId);
	
	public void deleteByRefIdList(List<Long> refIdList);
	public void deleteByRefId(Long refId);
	public PositionRef addPositionImage(PositionRef positionRef);
	public PositionRef mergeForPosition(PositionRef positionRef);
	
}
