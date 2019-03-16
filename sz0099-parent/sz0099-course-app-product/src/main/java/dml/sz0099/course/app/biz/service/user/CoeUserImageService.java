package dml.sz0099.course.app.biz.service.user;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.user.CoeUserImage;

/**
 * CoeUserImageService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeUserImageService extends GenericService<CoeUserImage,Long>{


	/**
	 * 根据Id查询CoeUserImage实体对象
	 * @param id
	 * @return
	 */
	public CoeUserImage findById(Long id);
	
	public boolean existById(Long id);
	
	public CoeUserImage findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeUserImage实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeUserImage> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param coeUserImage
	 * @return
	 */
	public CoeUserImage persistEntity(CoeUserImage coeUserImage) ;
	
	
	public CoeUserImage mergeEntity(CoeUserImage coeUserImage) ; 
	
	public CoeUserImage saveOrUpdate(CoeUserImage coeUserImage) ;
	
	public Page<CoeUserImage> findPage(CoeUserImage coeUserImage, Pageable pageable) ;
	
	public List<CoeUserImage> findByCoeUserId(Long coeUserId);
	public List<CoeUserImage> findByCoeUserIdAndSubIdList(Long coeUserId, List<Long> subIdList);
	
	public List<CoeUserImage> findByCoeUserId(Long coeUserId, Long subId) ;
	
	public void deleteImage(CoeUserImage userImage);
}
