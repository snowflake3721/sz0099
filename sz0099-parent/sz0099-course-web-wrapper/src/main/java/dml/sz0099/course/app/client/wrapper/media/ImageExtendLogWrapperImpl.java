package dml.sz0099.course.app.client.wrapper.media;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.media.ImageExtendLogDelegate;
import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.entity.media.ImageExtendLog;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ImageExtendLogWrapperImpl,组件封装
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
public class ImageExtendLogWrapperImpl implements ImageExtendLogWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ImageExtendLogWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private ImageExtendLogDelegate imageExtendLogDelegate;
	
	/**
	 * 根据Id查询ImageExtendLog实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ImageExtendLog findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		ImageExtendLog imageExtendLog = imageExtendLogDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, imageExtendLog);
		return imageExtendLog;
	}
	
	@Override
	public ImageExtendLog findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		ImageExtendLog imageExtendLog = imageExtendLogDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, imageExtendLog);
		return imageExtendLog;
	}
	
	/**
	 * 根据IdList查询ImageExtendLog实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ImageExtendLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<ImageExtendLog> imageExtendLogList = imageExtendLogDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  imageExtendLogList);
		return imageExtendLogList;
	}
	
	@Override
	public ImageExtendLog persistEntity(ImageExtendLog imageExtendLog) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		ImageExtendLog entity = imageExtendLogDelegate.persistEntity(imageExtendLog);
		Long id = imageExtendLog.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ImageExtendLog mergeEntity(ImageExtendLog imageExtendLog) {
		Long id = imageExtendLog.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		ImageExtendLog entity = imageExtendLogDelegate.mergeEntity(imageExtendLog);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ImageExtendLog saveOrUpdate(ImageExtendLog imageExtendLog) {
		Long id = imageExtendLog.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		ImageExtendLog entity = imageExtendLogDelegate.saveOrUpdate(imageExtendLog);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ImageExtendLog> findPage(ImageExtendLog imageExtendLog, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<ImageExtendLog> page = imageExtendLogDelegate.findPage(imageExtendLog, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return imageExtendLogDelegate.existById(id);
	}

	@Override
	public ImageExtendLog persistForFail(ImageExtend imageExtend) {
		return imageExtendLogDelegate.persistForFail(imageExtend);
	}
}
