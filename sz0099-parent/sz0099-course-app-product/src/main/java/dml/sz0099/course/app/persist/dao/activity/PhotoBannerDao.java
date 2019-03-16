package dml.sz0099.course.app.persist.dao.activity;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.PhotoBanner;

/**
 * PhotoBannerDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface PhotoBannerDao extends GenericDao<PhotoBanner,Long>{

	/**
	 * 根据Id查询PhotoBanner实体对象
	 * @param id
	 * @return
	 */
	PhotoBanner findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	PhotoBanner findByAid(Long aid);
	
	/**
	 * 根据IdList查询PhotoBanner实体对象列表
	 * @param idList
	 * @return
	 */
	public List<PhotoBanner> findByIdList(List<Long> idList);
	
	public Page<PhotoBanner> findPage(PhotoBanner photoBanner, Pageable pageable);
	
	public List<PhotoBanner> findByMainId(Long mainId) ;
	
	public List<PhotoBanner> findByMainIdAndSubId(Long mainId, Long subId) ;
	
	public void deleteBySubIdAndMainId(Long subId, Long mainId );
	
	public List<PhotoBanner> findBySubIdListAndMainId(List<Long> idList, Long mainId) ;

	public void deleteBySubIdListAndMainId(List<Long> subIdList, Long mainId);

	public void deleteById(Long id) ;
	
	public List<PhotoBanner> findByMainIdListAndSubId(List<Long> mainIdList, Long subId);

	public Long countByMainId(Long mainId);
}
