package dml.sz0099.course.app.biz.service.paragraph;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.jit4j.core.service.GenericServiceImpl;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.persist.dao.paragraph.PhotoParagDao;
import dml.sz0099.course.app.persist.entity.paragraph.PhotoParag;


/**
 * 
 * @formatter:off
 * description: PhotoParagServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class PhotoParagServiceImpl extends GenericServiceImpl<PhotoParag, Long> implements PhotoParagService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PhotoParagServiceImpl.class);

	@Autowired
	private PhotoParagDao photoParagDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = photoParagDao;
	}

	/**
	 * 根据Id查询PhotoParag实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public PhotoParag findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		PhotoParag photoParag = photoParagDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, photoParag);
		return photoParag;
	}
	
	@Override
	public PhotoParag findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		PhotoParag photoParag = photoParagDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, photoParag);
		return photoParag;
	}

	/**
	 * 根据IdList查询PhotoParag实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<PhotoParag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<PhotoParag> photoParagList = photoParagDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", photoParagList);
		return photoParagList;
	}

	@Transactional
	@Override
	public PhotoParag persistEntity(PhotoParag photoParag) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		PhotoParag entity = save(photoParag);
		Long id = photoParag.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(PhotoParag.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public PhotoParag mergeEntity(PhotoParag photoParag) {
		Long id = photoParag.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		PhotoParag entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(photoParag.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(PhotoParag.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public PhotoParag saveOrUpdate(PhotoParag photoParag) {
		Long id = photoParag.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		PhotoParag entity = null;
		if(null != id) {
			entity = mergeEntity(photoParag);
		}else {
			entity = persistEntity(photoParag);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PhotoParag> findPage(PhotoParag photoParag, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<PhotoParag> page = photoParagDao.findPage(photoParag, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return photoParagDao.existById(id);
	}

}
