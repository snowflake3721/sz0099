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

import dml.sz0099.course.app.persist.dao.media.ImageExtendDao;
import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.entity.media.Imagebase;


/**
 * 
 * @formatter:off
 * description: ImageExtendServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class ImageExtendServiceImpl extends GenericServiceImpl<ImageExtend, Long> implements ImageExtendService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageExtendServiceImpl.class);

	@Autowired
	private ImageExtendDao imageExtendDao;
	
	@Autowired
	private ImagebaseService imagebaseService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = imageExtendDao;
	}

	/**
	 * 根据Id查询ImageExtend实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ImageExtend findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		ImageExtend imageExtend = imageExtendDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, imageExtend);
		return imageExtend;
	}
	
	@Override
	public ImageExtend findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		ImageExtend imageExtend = imageExtendDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, imageExtend);
		return imageExtend;
	}

	/**
	 * 根据IdList查询ImageExtend实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ImageExtend> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<ImageExtend> imageExtendList = imageExtendDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", imageExtendList);
		return imageExtendList;
	}

	@Transactional
	@Override
	public ImageExtend persistEntity(ImageExtend imageExtend) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		
		imageExtend.setMainMaxnum(Integer.valueOf(ImageExtend.MAINMAXNUM_100.getValueStr()));
		imageExtend.setSubMaxnum(Integer.valueOf(ImageExtend.SUBMAXNUM_5.getValueStr()));
		imageExtend.setSizeMax(Long.valueOf(ImageExtend.SIZEMAX_10M.getValueStr()));
		
		ImageExtend entity = save(imageExtend);
		Long id = imageExtend.getId();
		entity.setPositionId(id);
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(ImageExtend.SUCCESS_YES);
		save(entity);
		return entity;
	}
	
	@Transactional
	@Override
	public ImageExtend mergeEntity(ImageExtend imageExtend) {
		Long id = imageExtend.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		ImageExtend entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(imageExtend.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(ImageExtend.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public ImageExtend saveOrUpdate(ImageExtend imageExtend) {
		Long id = imageExtend.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		ImageExtend entity = null;
		if(null != id) {
			entity = mergeEntity(imageExtend);
		}else {
			entity = persistEntity(imageExtend);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ImageExtend> findPage(ImageExtend imageExtend, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<ImageExtend> page = imageExtendDao.findPage(imageExtend, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return imageExtendDao.existById(id);
	}
	
	public ImageExtend findByPositionId(Long positionId) {
		return imageExtendDao.findByPositionId(positionId);
	}
	
	public ImageExtend findImageExtend(ImageExtend extend) {
		return imageExtendDao.findImageExtend(extend);
	}
	
	@Transactional
	public ImageExtend saveForUpload(ImageExtend extend) {
		
		List<Imagebase> images = extend.getImages();
		images=imagebaseService.saveForUpload(images);
		extend.setImages(images);
		return extend;
	}

	@Override
	public Long findPositionIdById(Long id) {
		return imageExtendDao.findPositionIdById(id);
	}

}
