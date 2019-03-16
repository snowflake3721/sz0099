package dml.sz0099.course.app.client.wrapper.media;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.media.ImageExtendDelegate;
import dml.sz0099.course.app.persist.entity.media.ImageExtend;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ImageExtendWrapperImpl,组件封装
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
public class ImageExtendWrapperImpl implements ImageExtendWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ImageExtendWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private ImageExtendDelegate imageExtendDelegate;
	
	/**
	 * 根据Id查询ImageExtend实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ImageExtend findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		ImageExtend imageExtend = imageExtendDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, imageExtend);
		return imageExtend;
	}
	
	@Override
	public ImageExtend findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		ImageExtend imageExtend = imageExtendDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, imageExtend);
		return imageExtend;
	}
	
	/**
	 * 根据IdList查询ImageExtend实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ImageExtend> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<ImageExtend> imageExtendList = imageExtendDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  imageExtendList);
		return imageExtendList;
	}
	
	@Override
	public ImageExtend persistEntity(ImageExtend imageExtend) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		ImageExtend entity = imageExtendDelegate.persistEntity(imageExtend);
		Long id = entity.getId();
		imageExtend.setPositionId(entity.getPositionId());
		imageExtend.setId(entity.getId());
		imageExtend.setAid(entity.getAid());
		imageExtend.setMainMaxnum(entity.getMainMaxnum());
		imageExtend.setSubMaxnum(entity.getSubMaxnum());
		imageExtend.setSizeMax(entity.getSizeMax());
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ImageExtend mergeEntity(ImageExtend imageExtend) {
		Long id = imageExtend.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		ImageExtend entity = imageExtendDelegate.mergeEntity(imageExtend);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ImageExtend saveOrUpdate(ImageExtend imageExtend) {
		Long id = imageExtend.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		ImageExtend entity = imageExtendDelegate.saveOrUpdate(imageExtend);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ImageExtend> findPage(ImageExtend imageExtend, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<ImageExtend> page = imageExtendDelegate.findPage(imageExtend, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return imageExtendDelegate.existById(id);
	}

	@Override
	public ImageExtend findByPositionId(Long positionId) {
		return imageExtendDelegate.findByPositionId(positionId);
	}
	
	public ImageExtend findImageExtend(ImageExtend extend) {
		return imageExtendDelegate.findImageExtend(extend);
	}

	@Override
	public ImageExtend saveForUpload(ImageExtend extend) {
		return imageExtendDelegate.saveForUpload(extend);
	}
}
