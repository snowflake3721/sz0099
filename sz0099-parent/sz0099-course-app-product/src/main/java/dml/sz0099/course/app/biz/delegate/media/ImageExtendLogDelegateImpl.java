package dml.sz0099.course.app.biz.delegate.media;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.media.ImageExtendLogService;
import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.entity.media.ImageExtendLog;

/**
 * imageExtendLogServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class ImageExtendLogDelegateImpl implements ImageExtendLogDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageExtendLogDelegateImpl.class);
	
	@Autowired
	private ImageExtendLogService imageExtendLogService;

	/**
	 * 根据Id查询ImageExtendLog实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ImageExtendLog findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		ImageExtendLog imageExtendLog = imageExtendLogService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, imageExtendLog);
		return imageExtendLog;
	}
	
	@Override
	public ImageExtendLog findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		ImageExtendLog imageExtendLog = imageExtendLogService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, imageExtendLog);
		return imageExtendLog;
	}
	
	/**
	 * 根据IdList查询ImageExtendLog实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ImageExtendLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<ImageExtendLog> imageExtendLogList = imageExtendLogService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  imageExtendLogList);
		return imageExtendLogList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public ImageExtendLog persistEntity(ImageExtendLog imageExtendLog) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		ImageExtendLog entity = imageExtendLogService.persistEntity(imageExtendLog);
		Long id = imageExtendLog.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ImageExtendLog mergeEntity(ImageExtendLog imageExtendLog) {
		Long id = imageExtendLog.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		ImageExtendLog entity = imageExtendLogService.mergeEntity(imageExtendLog);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ImageExtendLog saveOrUpdate(ImageExtendLog imageExtendLog) {
		Long id = imageExtendLog.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		ImageExtendLog entity = imageExtendLogService.saveOrUpdate(imageExtendLog);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ImageExtendLog> findPage(ImageExtendLog imageExtendLog, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<ImageExtendLog> page = imageExtendLogService.findPage(imageExtendLog, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return imageExtendLogService.existById(id);
	}

	@Override
	public ImageExtendLog persistForFail(ImageExtend imageExtend) {
		return imageExtendLogService.persistForFail(imageExtend);
	}
}
