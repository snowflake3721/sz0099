package dml.sz0099.course.app.client.wrapper.activity;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.PhotoCover;
import dml.sz0099.course.app.persist.entity.activity.PhotoCover;
import dml.sz0099.course.app.persist.entity.activity.bo.PhotoCoverBo;
import dml.sz0099.course.app.persist.entity.media.ImageRef;


/**
 * PhotoCoverWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface PhotoCoverWrapper {

	/**
	 * 根据Id查询PhotoCover实体对象
	 * @param id
	 * @return
	 */
	public PhotoCover findById(Long id);
	
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

	public void deleteBySubIdAndMainId(Long subId, Long mainId );

	public List<PhotoCover> findBySubIdListAndMainId(List<Long> idList, Long mainId);
	public Map<Long,List<PhotoCover>> findByMainIdListAndSubId(List<Long> mainIdList, Long subId);

	public void deleteBySubIdListAndMainId(List<Long> subIdList, Long mainId, Long userId, boolean cascade);
	public void deleteById(PhotoCover photoCover);
	public void deleteByIdList(PhotoCoverBo photoCover);
	
	public List<PhotoCover> createPhotoCover(List<PhotoCover> photoCoverList) ;
	
	public PhotoCover mergeForTitle(PhotoCover photoCover);
	public List<PhotoCover> mergeListForTitle(List<PhotoCover> photoCoverList) ;
	
	public List<PhotoCover> persistForPhoto(List<PhotoCover> photoList);
	
	
	public void deleteImageById(ImageRef ref);
	public PhotoCover addImageById(ImageRef ref);
	public PhotoCover changeImageById(ImageRef ref);
	
	public PhotoCover createPhotoCover(PhotoCover photoCover);
	public PhotoCover createPhotoCover(ImageRef ref);
	
	public PhotoCover mergeRefForDescription(PhotoCover photoCover);
	public PhotoCover mergeRefForOrder(PhotoCover photoCover);

	public Long countByMainId(Long mainId);
}
