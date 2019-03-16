package dml.sz0099.course.app.biz.service.paragraph;

import java.util.List;
import java.util.Map;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.paragraph.PhotoBanner;
import dml.sz0099.course.app.persist.entity.paragraph.PhotoBanner;
import dml.sz0099.course.app.persist.entity.paragraph.bo.PhotoBannerBo;

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
	 * @param photoCover
	 * @return
	 */
	public PhotoBanner persistEntity(PhotoBanner photoCover) ;
	
	
	public PhotoBanner mergeEntity(PhotoBanner photoCover) ; 
	
	public PhotoBanner saveOrUpdate(PhotoBanner photoCover) ;
	
	public Page<PhotoBanner> findPage(PhotoBanner photoCover, Pageable pageable) ;
	
	public List<PhotoBanner> findByMainId(Long mainId);
	
	public List<PhotoBanner> findByMainIdAndSubId(Long mainId, Long subId) ;
	
	public void deleteImage(PhotoBanner photoCover);
	
	
	
	public List<PhotoBanner> findBySubIdListAndMainId(List<Long> idList, Long mainId);
	
	public void deleteBySubIdAndMainId(Long subId, Long mainId );
	public void deleteBySubIdListAndMainId(List<Long> subIdList, Long mainId, Long userId, boolean cascade);
	public void deleteById(PhotoBanner photoCover);
	public void deleteByIdList(PhotoBannerBo photoCover);
	
	public PhotoBanner createPhotoBanner(PhotoBannerBo photoCover);
	public List<PhotoBanner> createPhotoBanner(List<PhotoBannerBo> photoCoverList) ;

	public List<PhotoBanner> persistForPhoto(List<PhotoBanner> photoList);
	
	public PhotoBanner mergeForTitle(PhotoBanner photoCover) ;

	public List<PhotoBanner> mergeListForTitle(List<PhotoBanner> photoCoverList) ;
	
	public Map<Long, List<PhotoBanner>> findByMainIdListAndSubId(List<Long> mainIdList, Long subId);
	
}
