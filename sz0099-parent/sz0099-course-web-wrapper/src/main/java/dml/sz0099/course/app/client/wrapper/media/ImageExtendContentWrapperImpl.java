package dml.sz0099.course.app.client.wrapper.media;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.media.ImageExtendContentDelegate;
import dml.sz0099.course.app.persist.entity.media.ImageExtendContent;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ImageExtendContentWrapperImpl,组件封装
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
public class ImageExtendContentWrapperImpl implements ImageExtendContentWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ImageExtendContentWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private ImageExtendContentDelegate imageExtendContentDelegate;
	
	/**
	 * 根据Id查询ImageExtendContent实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ImageExtendContent findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		ImageExtendContent imageExtendContent = imageExtendContentDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, imageExtendContent);
		return imageExtendContent;
	}
	
	@Override
	public ImageExtendContent findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		ImageExtendContent imageExtendContent = imageExtendContentDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, imageExtendContent);
		return imageExtendContent;
	}
	
	/**
	 * 根据IdList查询ImageExtendContent实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ImageExtendContent> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<ImageExtendContent> imageExtendContentList = imageExtendContentDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  imageExtendContentList);
		return imageExtendContentList;
	}
	
	@Override
	public ImageExtendContent persistEntity(ImageExtendContent imageExtendContent) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		ImageExtendContent entity = imageExtendContentDelegate.persistEntity(imageExtendContent);
		Long id = imageExtendContent.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ImageExtendContent mergeEntity(ImageExtendContent imageExtendContent) {
		Long id = imageExtendContent.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		ImageExtendContent entity = imageExtendContentDelegate.mergeEntity(imageExtendContent);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ImageExtendContent saveOrUpdate(ImageExtendContent imageExtendContent) {
		Long id = imageExtendContent.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		ImageExtendContent entity = imageExtendContentDelegate.saveOrUpdate(imageExtendContent);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ImageExtendContent> findPage(ImageExtendContent imageExtendContent, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<ImageExtendContent> page = imageExtendContentDelegate.findPage(imageExtendContent, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return imageExtendContentDelegate.existById(id);
	}
}
