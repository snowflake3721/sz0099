package dml.sz0099.course.app.client.wrapper.profession;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.media.ImageRef;
import dml.sz0099.course.app.persist.entity.profession.PhotoBanner;
import dml.sz0099.course.app.persist.entity.profession.bo.PhotoBannerBo;


/**
 * PhotoBannerWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface PhotoBannerWrapper {

	/**
	 * 根据Id查询PhotoBanner实体对象
	 * @param id
	 * @return
	 */
	public PhotoBanner findById(Long id);
	
	public boolean existById(Long id);
	
	public PhotoBanner findByAid(Long aid);
	
	/**
	 * 根据IdList查询PhotoBanner实体对象列表
	 * @param idList
	 * @return
	 */
	public List<PhotoBanner> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param photoBanner
	 * @return
	 */
	public PhotoBanner persistEntity(PhotoBanner photoBanner) ;
	
	public PhotoBanner mergeEntity(PhotoBanner photoBanner) ; 
	
	public PhotoBanner saveOrUpdate(PhotoBanner photoBanner) ;
	
	public Page<PhotoBanner> findPage(PhotoBanner photoBanner, Pageable pageable) ; 

	public void deleteBySubIdAndMainId(Long subId, Long mainId );

	public List<PhotoBanner> findBySubIdListAndMainId(List<Long> idList, Long mainId);
	public Map<Long,List<PhotoBanner>> findByMainIdListAndSubId(List<Long> mainIdList, Long subId);

	public void deleteBySubIdListAndMainId(List<Long> subIdList, Long mainId, Long userId, boolean cascade);
	public void deleteById(PhotoBanner photoBanner);
	public void deleteByIdList(PhotoBannerBo photoBanner);
	
	public PhotoBanner createPhotoBanner(PhotoBanner photoBanner);
	public List<PhotoBanner> createPhotoBanner(List<PhotoBanner> photoBannerList) ;
	
	public PhotoBanner mergeForTitle(PhotoBanner photoBanner);
	public List<PhotoBanner> mergeListForTitle(List<PhotoBanner> photoBannerList) ;
	
	public List<PhotoBanner> persistForPhoto(List<PhotoBanner> photoList);
	
	
	public void deleteImageById(ImageRef ref);
	public PhotoBanner addImageById(ImageRef ref);
	public PhotoBanner changeImageById(ImageRef ref);
	
	public PhotoBanner createPhotoBanner(ImageRef ref);
	
	public PhotoBanner mergeRefForDescription(PhotoBanner photoBanner);
	public PhotoBanner mergeRefForOrder(PhotoBanner photoBanner);

	public Long countByMainId(Long mainId);


}
