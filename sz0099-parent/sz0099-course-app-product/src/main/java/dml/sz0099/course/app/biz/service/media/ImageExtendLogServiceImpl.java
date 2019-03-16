package dml.sz0099.course.app.biz.service.media;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.jit4j.core.service.GenericServiceImpl;
import org.jit8j.core.util.GsonBuilderUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.persist.dao.media.ImageExtendLogDao;
import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.entity.media.ImageExtendContent;
import dml.sz0099.course.app.persist.entity.media.ImageExtendLog;


/**
 * 
 * @formatter:off
 * description: ImageExtendLogServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class ImageExtendLogServiceImpl extends GenericServiceImpl<ImageExtendLog, Long> implements ImageExtendLogService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageExtendLogServiceImpl.class);

	@Autowired
	private ImageExtendLogDao imageExtendLogDao;
	
	@Autowired
	private ImageExtendContentService imageExtendContentService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = imageExtendLogDao;
	}

	/**
	 * 根据Id查询ImageExtendLog实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ImageExtendLog findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		ImageExtendLog imageExtendLog = imageExtendLogDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, imageExtendLog);
		return imageExtendLog;
	}
	
	@Override
	public ImageExtendLog findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		ImageExtendLog imageExtendLog = imageExtendLogDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, imageExtendLog);
		return imageExtendLog;
	}

	/**
	 * 根据IdList查询ImageExtendLog实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ImageExtendLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<ImageExtendLog> imageExtendLogList = imageExtendLogDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", imageExtendLogList);
		return imageExtendLogList;
	}

	@Transactional
	@Override
	public ImageExtendLog persistEntity(ImageExtendLog imageExtendLog) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		ImageExtendLog entity = save(imageExtendLog);
		Long id = imageExtendLog.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(ImageExtendLog.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public ImageExtendLog mergeEntity(ImageExtendLog imageExtendLog) {
		Long id = imageExtendLog.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		ImageExtendLog entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(imageExtendLog.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(ImageExtendLog.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public ImageExtendLog saveOrUpdate(ImageExtendLog imageExtendLog) {
		Long id = imageExtendLog.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		ImageExtendLog entity = null;
		if(null != id) {
			entity = mergeEntity(imageExtendLog);
		}else {
			entity = persistEntity(imageExtendLog);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ImageExtendLog> findPage(ImageExtendLog imageExtendLog, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<ImageExtendLog> page = imageExtendLogDao.findPage(imageExtendLog, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return imageExtendLogDao.existById(id);
	}

	@Transactional
	@Override
	public ImageExtendLog persistForFail(ImageExtend imageExtend) {
		ImageExtendLog imageExtendLog = new ImageExtendLog();
		
		imageExtendLog.setExtendId(imageExtend.getId());
		imageExtendLog.setDevId(imageExtend.getDevId());
		imageExtendLog.setDomain(imageExtend.getDomain());
		imageExtendLog.setModule(imageExtend.getModule());
		imageExtendLog.setPosition(imageExtend.getPosition());
		imageExtendLog.setPositionId(imageExtend.getPositionId());
		imageExtendLog.setProject(imageExtend.getProject());
		imageExtendLog.setUserId(imageExtend.getUserId());
		imageExtendLog.setVariety(imageExtend.getVariety());
		imageExtendLog.setCreatedBy(imageExtend.getCreatedBy());
		imageExtendLog = persistEntity(imageExtendLog);
		ImageExtendContent content = new ImageExtendContent();
		content.setExtendLogId(imageExtendLog.getId());
		content.setContent(GsonBuilderUtils.toJsonOmitnull(imageExtend));
		content = imageExtendContentService.persistEntity(content);
		imageExtendLog.setContent(content);
		
		return imageExtendLog;
	}

}
