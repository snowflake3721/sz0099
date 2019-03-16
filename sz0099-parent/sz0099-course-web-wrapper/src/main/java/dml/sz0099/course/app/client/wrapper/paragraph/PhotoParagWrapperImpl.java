package dml.sz0099.course.app.client.wrapper.paragraph;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.paragraph.PhotoParagDelegate;
import dml.sz0099.course.app.persist.entity.paragraph.PhotoParag;
import dml.sz0099.course.app.persist.entity.paragraph.bo.PhotoParagBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * PhotoParagWrapperImpl,组件封装
 * @author bruce yang at 2018-08-29 21:12:56
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Service
public class PhotoParagWrapperImpl implements PhotoParagWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PhotoParagWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private PhotoParagDelegate photoParagDelegate;
	
	/**
	 * 根据Id查询PhotoParag实体对象
	 * @param id
	 * @return
	 */
	@Override
	public PhotoParag findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		PhotoParag photoParag = photoParagDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, photoParag);
		return photoParag;
	}
	
	@Override
	public PhotoParag findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		PhotoParag photoParag = photoParagDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, photoParag);
		return photoParag;
	}
	
	/**
	 * 根据IdList查询PhotoParag实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<PhotoParag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<PhotoParag> photoParagList = photoParagDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  photoParagList);
		return photoParagList;
	}
	
	@Override
	public PhotoParag persistEntity(PhotoParag photoParag) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		PhotoParag entity = photoParagDelegate.persistEntity(photoParag);
		Long id = photoParag.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PhotoParag mergeEntity(PhotoParag photoParag) {
		Long id = photoParag.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		PhotoParag entity = photoParagDelegate.mergeEntity(photoParag);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PhotoParag saveOrUpdate(PhotoParag photoParag) {
		Long id = photoParag.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		PhotoParag entity = photoParagDelegate.saveOrUpdate(photoParag);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PhotoParag> findPage(PhotoParag photoParag, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<PhotoParag> page = photoParagDelegate.findPage(photoParag, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return photoParagDelegate.existById(id);
	}


	public void deleteByParagIdAndUserId(Long paragId, Long userId ){		

		LOGGER.debug("-------wrapper>>>PhotoParagWrapperImpl.deleteByParagIdAndUserId----------begin---------");
		photoParagDelegate.deleteByParagIdAndUserId( paragId,  userId );
	}

	@Override
	public List<PhotoParag> findByParagIdListAndUserId(List<Long> idList, Long userId) {
		return photoParagDelegate.findByParagIdListAndUserId(idList, userId);
	}

	@Override
	public void deleteByParagIdListAndUserId(List<Long> paragIdList, Long userId, boolean cascade) {
		photoParagDelegate.deleteByParagIdListAndUserId(paragIdList, userId,cascade);
		
	}

	@Override
	public void deleteById(PhotoParag photoParag) {
		photoParagDelegate.deleteById(photoParag);
	}

	@Override
	public void deleteByIdList(PhotoParagBo photoParag) {
		photoParagDelegate.deleteByIdList(photoParag);
		
	}

	@Override
	public PhotoParag createPhotoParag(PhotoParag photoParag) {
		return photoParagDelegate.createPhotoParag(photoParag);
	}

	@Override
	public List<PhotoParag> createPhotoParag(List<PhotoParag> photoParagList) {
		return photoParagDelegate.createPhotoParag(photoParagList);
	}

	@Override
	public PhotoParag mergeForTitle(PhotoParag photoParag) {
		
		return photoParagDelegate.mergeForTitle(photoParag);
	}

	@Override
	public List<PhotoParag> mergeListForTitle(List<PhotoParag> photoParagList) {
		return photoParagDelegate.mergeListForTitle(photoParagList);
	}


}
