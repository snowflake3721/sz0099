package dml.sz0099.course.app.biz.service.activity;

import java.util.List;
import java.util.Map;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.PhotoBanner;
import dml.sz0099.course.app.persist.entity.activity.bo.PhotoBannerBo;

/**
 * PhotoBannerService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface PhotoBannerService extends GenericService<PhotoBanner,Long>{


	/**
	 * 根据Id查询PhotoBanner实体对象
	 * @param id
	 * @return
	 */
	public PhotoBanner findById(Long id);
	
	public PhotoBanner findById(Long id, boolean withPhoto);

	
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
	
	public List<PhotoBanner> findByMainId(Long mainId);
	
	public List<PhotoBanner> findByMainIdAndSubId(Long mainId, Long subId) ;
	
	public void deleteImage(PhotoBanner photoBanner);
	
	
	
	public List<PhotoBanner> findBySubIdListAndMainId(List<Long> idList, Long mainId);
	
	public void deleteBySubIdAndMainId(Long subId, Long mainId );
	public void deleteBySubIdListAndMainId(List<Long> subIdList, Long mainId, Long userId, boolean cascade);
	public void deleteById(PhotoBanner photoBanner);
	public void deleteByIdList(PhotoBannerBo photoBanner);
	
	public PhotoBanner createPhotoBanner(PhotoBanner photoBanner);
	public List<PhotoBanner> createPhotoBanner(List<PhotoBanner> photoBannerList) ;

	public List<PhotoBanner> persistForPhoto(List<PhotoBanner> photoList);
	
	public PhotoBanner mergeForTitle(PhotoBanner photoBanner) ;

	public List<PhotoBanner> mergeListForTitle(List<PhotoBanner> photoBannerList) ;
	
	public Map<Long, List<PhotoBanner>> findByMainIdListAndSubId(List<Long> mainIdList, Long subId);
	
	public void deleteImageById(PhotoBanner photoBanner, boolean cascade);
	
	public PhotoBanner addImageById(PhotoBanner photoBanner, boolean cascade);
	
	public PhotoBanner mergeRefForDescription(PhotoBanner photoBanner);
	public PhotoBanner mergeRefForOrder(PhotoBanner photoBanner);
	
	public Long countByMainId(Long mainId);
}
