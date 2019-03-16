package dml.sz0099.course.app.biz.service.media;

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

import dml.sz0099.course.app.persist.dao.media.ImageExtendContentDao;
import dml.sz0099.course.app.persist.entity.media.ImageExtendContent;


/**
 * 
 * @formatter:off
 * description: ImageExtendContentServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class ImageExtendContentServiceImpl extends GenericServiceImpl<ImageExtendContent, Long> implements ImageExtendContentService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageExtendContentServiceImpl.class);

	@Autowired
	private ImageExtendContentDao imageExtendContentDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = imageExtendContentDao;
	}

	/**
	 * 根据Id查询ImageExtendContent实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ImageExtendContent findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		ImageExtendContent imageExtendContent = imageExtendContentDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, imageExtendContent);
		return imageExtendContent;
	}
	
	@Override
	public ImageExtendContent findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		ImageExtendContent imageExtendContent = imageExtendContentDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, imageExtendContent);
		return imageExtendContent;
	}

	/**
	 * 根据IdList查询ImageExtendContent实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ImageExtendContent> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<ImageExtendContent> imageExtendContentList = imageExtendContentDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", imageExtendContentList);
		return imageExtendContentList;
	}

	@Transactional
	@Override
	public ImageExtendContent persistEntity(ImageExtendContent imageExtendContent) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		ImageExtendContent entity = save(imageExtendContent);
		Long id = imageExtendContent.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(ImageExtendContent.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public ImageExtendContent mergeEntity(ImageExtendContent imageExtendContent) {
		Long id = imageExtendContent.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		ImageExtendContent entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(imageExtendContent.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(ImageExtendContent.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public ImageExtendContent saveOrUpdate(ImageExtendContent imageExtendContent) {
		Long id = imageExtendContent.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		ImageExtendContent entity = null;
		if(null != id) {
			entity = mergeEntity(imageExtendContent);
		}else {
			entity = persistEntity(imageExtendContent);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ImageExtendContent> findPage(ImageExtendContent imageExtendContent, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<ImageExtendContent> page = imageExtendContentDao.findPage(imageExtendContent, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return imageExtendContentDao.existById(id);
	}

}
