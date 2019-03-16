package dml.sz0099.course.app.biz.service.paragraph;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.paragraph.PhotoParag;
import dml.sz0099.course.app.persist.entity.paragraph.bo.PhotoParagBo;

/**
 * PhotoParagService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface PhotoParagService extends GenericService<PhotoParag,Long>{


	/**
	 * 根据Id查询PhotoParag实体对象
	 * @param id
	 * @return
	 */
	public PhotoParag findById(Long id);
	
	public boolean existById(Long id);
	
	public PhotoParag findByAid(Long aid);
	
	/**
	 * 根据IdList查询PhotoParag实体对象列表
	 * @param idList
	 * @return
	 */
	public List<PhotoParag> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param photoParag
	 * @return
	 */
	public PhotoParag persistEntity(PhotoParag photoParag) ;
	
	
	public PhotoParag mergeEntity(PhotoParag photoParag) ; 
	
	public PhotoParag saveOrUpdate(PhotoParag photoParag) ;
	
	public Page<PhotoParag> findPage(PhotoParag photoParag, Pageable pageable) ;
	

	public List<PhotoParag> findByParagIdListAndUserId(List<Long> idList, Long userId);
	public List<PhotoParag> findByParagIdList(List<Long> paragIdList);
	
	public void deleteByParagIdAndUserId(Long paragId, Long userId );
	public void deleteByParagIdListAndUserId(List<Long> paragIdList, Long userId, boolean cascade);
	public void deleteById(PhotoParag photoParag);
	public void deleteByIdList(PhotoParagBo photoParag);
	
	public PhotoParag createPhotoParag(PhotoParag photoParag);
	public List<PhotoParag> createPhotoParag(List<PhotoParag> photoParagList) ;

	public List<PhotoParag> persistForPhoto(List<PhotoParag> photoList);
	
	public PhotoParag mergeForTitle(PhotoParag photoParag) ;

	public List<PhotoParag> mergeListForTitle(List<PhotoParag> photoParagList) ;
}