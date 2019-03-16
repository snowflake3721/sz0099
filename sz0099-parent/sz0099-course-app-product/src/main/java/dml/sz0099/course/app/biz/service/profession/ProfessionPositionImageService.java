package dml.sz0099.course.app.biz.service.profession;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.ProfessionPosition;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPositionImage;

/**
 * ProfessionPositionImageService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ProfessionPositionImageService extends GenericService<ProfessionPositionImage,Long>{


	/**
	 * 根据Id查询ProfessionPositionImage实体对象
	 * @param id
	 * @return
	 */
	public ProfessionPositionImage findById(Long id);
	
	public boolean existById(Long id);
	
	public ProfessionPositionImage findByAid(Long aid);
	
	/**
	 * 根据IdList查询ProfessionPositionImage实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ProfessionPositionImage> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param professionPosition
	 * @return
	 */
	public ProfessionPositionImage persistEntity(ProfessionPositionImage professionPosition) ;
	
	
	public ProfessionPositionImage mergeEntity(ProfessionPositionImage professionPosition) ; 
	
	public ProfessionPositionImage saveOrUpdate(ProfessionPositionImage professionPosition) ;
	
	public Page<ProfessionPositionImage> findPage(ProfessionPositionImage professionPosition, Pageable pageable) ;
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public ProfessionPositionImage findByMainIdAndUserId(Long mainId, Long userId);
	public Page<ProfessionPositionImage> findByMainId(Long mainId, Pageable pageable) ; 
	
	public boolean hasPositionByMainIdAndUserId(Long mainId, Long userId);
	public ProfessionPositionImage mergeForPosition(ProfessionPositionImage professionPosition);
	
	public ProfessionPosition mergeForPosition(ProfessionPosition professionPosition);
	
	public ProfessionPosition addPositionImage(ProfessionPosition professionPosition);
	public void deleteByRefIdList(List<Long> refIdList);
	public void deleteByRefId(Long refId);
	
	public List<ProfessionPositionImage> findByRefId(Long refId);
	public List<ProfessionPositionImage> findByRefIdList(List<Long> refIdList);
	
}
