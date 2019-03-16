package dml.sz0099.course.app.client.wrapper.activity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.activity.PhotoParag;
import dml.sz0099.course.app.persist.entity.activity.bo.PhotoParagBo;
import dml.sz0099.course.app.persist.entity.media.ImageRef;


/**
 * PhotoParagWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface PhotoParagWrapper {

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

	public void deleteByParagIdAndUserId(Long paragId, Long userId );

	public List<PhotoParag> findByParagIdListAndUserId(List<Long> idList, Long userId);

	public void deleteByParagIdListAndUserId(List<Long> paragIdList, Long userId, boolean cascade);
	public void deleteById(PhotoParag photoParag);
	public void deleteByIdList(PhotoParagBo photoParag);
	public void deleteImageById(ImageRef ref);
	public PhotoParag addImageById(ImageRef ref);
	public PhotoParag changeImageById(ImageRef ref);
	
	public PhotoParag createPhotoParag(PhotoParag photoParag);
	public PhotoParag createPhotoParag(ImageRef ref);
	public List<PhotoParag> createPhotoParag(List<PhotoParag> photoParagList) ;
	
	public PhotoParag mergeForTitle(PhotoParag photoParag);
	public List<PhotoParag> mergeListForTitle(List<PhotoParag> photoParagList) ;
	public PhotoParag mergeRefForDescription(PhotoParag photoParag);
	public PhotoParag mergeRefForOrder(PhotoParag photoParag);

	public Long countByMainId(Long mainId);
	
	public void mergeTemplateById(PhotoParag photoParag) ;
	public void mergeTemplateById(ImageRef ref) ;
	
	public Long countNotSelfByTemplateId(Long templateId) ;
	
}
