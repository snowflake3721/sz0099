package dml.sz0099.course.app.biz.delegate.paragraph;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.paragraph.PhotoParagService;
import dml.sz0099.course.app.persist.entity.paragraph.PhotoParag;

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
public class PhotoParagDelegateImpl implements PhotoParagDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PhotoParagDelegateImpl.class);
	
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
}
