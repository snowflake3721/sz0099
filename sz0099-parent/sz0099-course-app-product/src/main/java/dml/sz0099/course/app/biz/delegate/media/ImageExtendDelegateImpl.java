package dml.sz0099.course.app.biz.delegate.media;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.media.ImageExtendService;
import dml.sz0099.course.app.persist.entity.media.ImageExtend;

/**
 * imageExtendServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class ImageExtendDelegateImpl implements ImageExtendDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageExtendDelegateImpl.class);
	
	@Autowired
	private ImageExtendService imageExtendService;

	/**
	 * 根据Id查询ImageExtend实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ImageExtend findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		ImageExtend imageExtend = imageExtendService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, imageExtend);
		return imageExtend;
	}
	
	@Override
	public ImageExtend findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		ImageExtend imageExtend = imageExtendService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, imageExtend);
		return imageExtend;
	}
	
	/**
	 * 根据IdList查询ImageExtend实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ImageExtend> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<ImageExtend> imageExtendList = imageExtendService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  imageExtendList);
		return imageExtendList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public ImageExtend persistEntity(ImageExtend imageExtend) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		ImageExtend entity = imageExtendService.persistEntity(imageExtend);
		Long id = imageExtend.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ImageExtend mergeEntity(ImageExtend imageExtend) {
		Long id = imageExtend.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		ImageExtend entity = imageExtendService.mergeEntity(imageExtend);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ImageExtend saveOrUpdate(ImageExtend imageExtend) {
		Long id = imageExtend.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		ImageExtend entity = imageExtendService.saveOrUpdate(imageExtend);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ImageExtend> findPage(ImageExtend imageExtend, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<ImageExtend> page = imageExtendService.findPage(imageExtend, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return imageExtendService.existById(id);
	}
	
	public ImageExtend findByPositionId(Long positionId) {
		return imageExtendService.findByPositionId(positionId);
	}
	
	public ImageExtend findImageExtend(ImageExtend extend) {
		return imageExtendService.findImageExtend(extend);
	}
	
	public ImageExtend saveForUpload(ImageExtend extend) {
		return imageExtendService.saveForUpload(extend);
	}
}
