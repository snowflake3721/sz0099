package dml.sz0099.course.app.biz.delegate.article;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.article.PhotoParagService;
import dml.sz0099.course.app.persist.entity.article.PhotoParag;
import dml.sz0099.course.app.persist.entity.article.bo.PhotoParagBo;

/**
 * photoParagServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class ArticlePhotoParagDelegateImpl implements ArticlePhotoParagDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ArticlePhotoParagDelegateImpl.class);
	
	@Autowired
	private PhotoParagService photoParagService;

	/**
	 * 根据Id查询PhotoParag实体对象
	 * @param id
	 * @return
	 */
	@Override
	public PhotoParag findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		PhotoParag photoParag = photoParagService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, photoParag);
		return photoParag;
	}
	
	public PhotoParag findById(Long id, boolean withPhoto) {
		return photoParagService.findById(id,withPhoto);
	}
	
	@Override
	public PhotoParag findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		PhotoParag photoParag = photoParagService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, photoParag);
		return photoParag;
	}
	
	/**
	 * 根据IdList查询PhotoParag实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<PhotoParag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<PhotoParag> photoParagList = photoParagService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  photoParagList);
		return photoParagList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public PhotoParag persistEntity(PhotoParag photoParag) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		PhotoParag entity = photoParagService.persistEntity(photoParag);
		Long id = photoParag.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PhotoParag mergeEntity(PhotoParag photoParag) {
		Long id = photoParag.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		PhotoParag entity = photoParagService.mergeEntity(photoParag);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PhotoParag saveOrUpdate(PhotoParag photoParag) {
		Long id = photoParag.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		PhotoParag entity = photoParagService.saveOrUpdate(photoParag);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PhotoParag> findPage(PhotoParag photoParag, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<PhotoParag> page = photoParagService.findPage(photoParag, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return photoParagService.existById(id);
	}


	public void deleteByParagIdAndUserId(Long paragId, Long userId ){		

		LOGGER.debug("-------delegate>>>PhotoParagDelegateImpl.deleteByParagIdAndUserId----------begin---------");
		photoParagService.deleteByParagIdAndUserId( paragId,  userId );
	}

	@Override
	public void deleteByParagIdListAndUserId(List<Long> paragIdList, Long userId, boolean cascade) {
		photoParagService.deleteByParagIdListAndUserId(paragIdList, userId, cascade);
	}

	@Override
	public void deleteById(PhotoParag photoParag) {
		photoParagService.deleteById(photoParag);
	}
	public void deleteById(PhotoParag photoParag, boolean cascade) {
		photoParagService.deleteById(photoParag, cascade);
	}

	public void deleteImageById(PhotoParag photoParag, boolean cascade) {
		photoParagService.deleteImageById(photoParag, cascade);
	}
	
	public PhotoParag addImageById(PhotoParag photoParag, boolean cascade) {
		return photoParagService.addImageById(photoParag, cascade);
	}
	
	@Override
	public void deleteByIdList(PhotoParagBo photoParag) {
		photoParagService.deleteByIdList(photoParag);
	}

	@Override
	public PhotoParag createPhotoParag(PhotoParag photoParag) {
		return photoParagService.createPhotoParag(photoParag);
	}

	@Override
	public List<PhotoParag> createPhotoParag(List<PhotoParag> photoParagList) {
		return photoParagService.createPhotoParag(photoParagList);
	}

	@Override
	public List<PhotoParag> findByParagIdListAndUserId(List<Long> idList, Long userId) {
		return photoParagService.findByParagIdListAndUserId(idList, userId);
	}

	@Override
	public PhotoParag mergeForTitle(PhotoParag photoParag) {
		return photoParagService.mergeForTitle(photoParag);
	}
	
	public PhotoParag mergeRefForDescription(PhotoParag photoParag) {
		
		return photoParagService.mergeRefForDescription(photoParag);
	}
	public PhotoParag mergeRefForOrder(PhotoParag photoParag) {
		
		return photoParagService.mergeRefForOrder(photoParag);
	}

	@Override
	public List<PhotoParag> mergeListForTitle(List<PhotoParag> photoParagList){
		return photoParagService.mergeListForTitle(photoParagList);
	}


	public Long countByMainId(Long mainId) {
		return photoParagService.countByMainId(mainId);
	}
}
