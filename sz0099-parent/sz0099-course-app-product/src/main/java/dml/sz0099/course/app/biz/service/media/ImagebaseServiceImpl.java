package dml.sz0099.course.app.biz.service.media;

import java.io.Serializable;
import java.util.ArrayList;
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

import dml.sz0099.course.app.persist.dao.media.ImagebaseDao;
import dml.sz0099.course.app.persist.entity.media.ImageRef;
import dml.sz0099.course.app.persist.entity.media.Imagebase;


/**
 * 
 * @formatter:off
 * description: ImagebaseServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class ImagebaseServiceImpl extends GenericServiceImpl<Imagebase, Long> implements ImagebaseService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImagebaseServiceImpl.class);

	@Autowired
	private ImagebaseDao imagebaseDao;
	
	@Autowired
	private ImageRefService imageRefService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = imagebaseDao;
	}

	/**
	 * 根据Id查询Imagebase实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Imagebase findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		Imagebase imagebase = imagebaseDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, imagebase);
		return imagebase;
	}
	
	@Override
	public Imagebase findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		Imagebase imagebase = imagebaseDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, imagebase);
		return imagebase;
	}

	/**
	 * 根据IdList查询Imagebase实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<Imagebase> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<Imagebase> imagebaseList = imagebaseDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", imagebaseList);
		return imagebaseList;
	}

	@Transactional
	@Override
	public Imagebase persistEntity(Imagebase imagebase) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		Imagebase entity = save(imagebase);
		Long id = imagebase.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(Imagebase.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public Imagebase mergeEntity(Imagebase imagebase) {
		Long id = imagebase.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		Imagebase entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(imagebase.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(Imagebase.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public Imagebase saveOrUpdate(Imagebase imagebase) {
		Long id = imagebase.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		Imagebase entity = null;
		if(null != id) {
			entity = mergeEntity(imagebase);
		}else {
			entity = persistEntity(imagebase);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<Imagebase> findPage(Imagebase imagebase, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<Imagebase> page = imagebaseDao.findPage(imagebase, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return imagebaseDao.existById(id);
	}

	@Transactional
	@Override
	public List<Imagebase> saveForUpload(List<Imagebase> images) {
		if (null != images && !images.isEmpty()) {
			List<Imagebase> entityList = new ArrayList<>(images.size());
			for (Imagebase image : images) {
				ImageRef ref = image.getImageRef();
				Integer sub = image.getSubRefNum();
				if(null == sub || sub==0) {
					image.setSubRefNum(1);
				}
				Integer main = image.getMainRefNum();
				if(null == main || main==0) {
					image.setMainRefNum(1);
				}
				ref = imageRefService.persistEntity(ref);
				image=save(image);
				image.setImageRef(ref);
				entityList.add(image);
				
			}
			images=entityList;
		}
		return images;
	}

}
