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

import dml.sz0099.course.app.persist.dao.paragraph.PhotoDao;
import dml.sz0099.course.app.persist.entity.paragraph.Photo;


/**
 * 
 * @formatter:off
 * description: PhotoServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class PhotoServiceImpl extends GenericServiceImpl<Photo, Long> implements PhotoService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PhotoServiceImpl.class);

	@Autowired
	private PhotoDao photoDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = photoDao;
	}

	/**
	 * 根据Id查询Photo实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Photo findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		Photo photo = photoDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, photo);
		return photo;
	}
	
	@Override
	public Photo findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		Photo photo = photoDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, photo);
		return photo;
	}

	/**
	 * 根据IdList查询Photo实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<Photo> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<Photo> photoList = photoDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", photoList);
		return photoList;
	}

	@Transactional
	@Override
	public Photo persistEntity(Photo photo) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		Photo entity = save(photo);
		Long id = photo.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(Photo.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public Photo mergeEntity(Photo photo) {
		Long id = photo.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		Photo entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(photo.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(Photo.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public Photo saveOrUpdate(Photo photo) {
		Long id = photo.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		Photo entity = null;
		if(null != id) {
			entity = mergeEntity(photo);
		}else {
			entity = persistEntity(photo);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<Photo> findPage(Photo photo, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<Photo> page = photoDao.findPage(photo, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return photoDao.existById(id);
	}

}
