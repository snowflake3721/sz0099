package dml.sz0099.course.app.biz.service.profession;

import java.util.List;
import java.util.Map;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.PhotoCover;
import dml.sz0099.course.app.persist.entity.profession.bo.PhotoCoverBo;

/**
 * PhotoCoverService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface PhotoCoverService extends GenericService<PhotoCover,Long>{


	/**
	 * 根据Id查询PhotoCover实体对象
	 * @param id
	 * @return
	 */
	public PhotoCover findById(Long id);
	
	public PhotoCover findById(Long id, boolean withPhoto);
	
	public boolean existById(Long id);
	
	public PhotoCover findByAid(Long aid);
	
	/**
	 * 根据IdList查询PhotoCover实体对象列表
	 * @param idList
	 * @return
	 */
	public List<PhotoCover> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param photoCover
	 * @return
	 */
	public PhotoCover persistEntity(PhotoCover photoCover) ;
	
	
	public PhotoCover mergeEntity(PhotoCover photoCover) ; 
	
	public PhotoCover saveOrUpdate(PhotoCover photoCover) ;
	
	public Page<PhotoCover> findPage(PhotoCover photoCover, Pageable pageable) ;
	
	public List<PhotoCover> findByMainId(Long mainId);
	
	public List<PhotoCover> findByMainIdAndSubId(Long mainId, Long subId) ;
	
	public void deleteImage(PhotoCover photoCover);
	
	
	
	public List<PhotoCover> findBySubIdListAndMainId(List<Long> idList, Long mainId);
	
	public void deleteBySubIdAndMainId(Long subId, Long mainId );
	public void deleteBySubIdListAndMainId(List<Long> subIdList, Long mainId, Long userId, boolean cascade);
	public void deleteById(PhotoCover photoCover);
	public void deleteById(PhotoCover photoCover, boolean cascade);
	public void deleteByIdList(PhotoCoverBo photoCover);
	public void deleteImageById(PhotoCover photoCover, boolean cascade);
	
	public PhotoCover addImageById(PhotoCover photoCover, boolean cascade);
	
	public PhotoCover createPhotoCover(PhotoCover photoCover);
	public List<PhotoCover> createPhotoCover(List<PhotoCover> photoCoverList) ;

	public List<PhotoCover> persistForPhoto(List<PhotoCover> photoList);
	
	public PhotoCover mergeForTitle(PhotoCover photoCover) ;

	public List<PhotoCover> mergeListForTitle(List<PhotoCover> photoCoverList) ;
	
	public Map<Long, List<PhotoCover>> findByMainIdListAndSubId(List<Long> mainIdList, Long subId);
	
	public PhotoCover mergeRefForDescription(PhotoCover photoCover);
	public PhotoCover mergeRefForOrder(PhotoCover photoCover);
	
	public Long countByMainId(Long mainId);
}
