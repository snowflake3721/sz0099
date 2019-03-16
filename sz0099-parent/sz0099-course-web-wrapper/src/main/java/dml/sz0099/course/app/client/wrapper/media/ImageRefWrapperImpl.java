package dml.sz0099.course.app.client.wrapper.media;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.media.ImageRefDelegate;
import dml.sz0099.course.app.persist.entity.media.ImageRef;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ImageRefWrapperImpl,组件封装
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
public class ImageRefWrapperImpl implements ImageRefWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ImageRefWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private ImageRefDelegate imageRefDelegate;
	
	/**
	 * 根据Id查询ImageRef实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ImageRef findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		ImageRef imageRef = imageRefDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, imageRef);
		return imageRef;
	}
	
	@Override
	public ImageRef findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		ImageRef imageRef = imageRefDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, imageRef);
		return imageRef;
	}
	
	/**
	 * 根据IdList查询ImageRef实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ImageRef> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<ImageRef> imageRefList = imageRefDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  imageRefList);
		return imageRefList;
	}
	
	@Override
	public ImageRef persistEntity(ImageRef imageRef) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		ImageRef entity = imageRefDelegate.persistEntity(imageRef);
		Long id = imageRef.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ImageRef mergeEntity(ImageRef imageRef) {
		Long id = imageRef.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		ImageRef entity = imageRefDelegate.mergeEntity(imageRef);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}
	
	public ImageRef mergeForTitle(ImageRef imageRef) {
		Long id = imageRef.getId();
		LOGGER.debug("--- wrapper.mergeForTitle begin, id is {} ---------  ",id);
		ImageRef entity = imageRefDelegate.mergeForTitle(imageRef);
		return entity;
	}

	@Override
	public ImageRef saveOrUpdate(ImageRef imageRef) {
		Long id = imageRef.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		ImageRef entity = imageRefDelegate.saveOrUpdate(imageRef);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ImageRef> findPage(ImageRef imageRef, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<ImageRef> page = imageRefDelegate.findPage(imageRef, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return imageRefDelegate.existById(id);
	}
	
	public Long countForMain(ImageRef imageRef) {
		return imageRefDelegate.countForMain(imageRef);
	}

	public Long countForSub(ImageRef imageRef) {
		return imageRefDelegate.countForSub(imageRef);
	}

	public Long countForBase(ImageRef imageRef) {
		return imageRefDelegate.countForBase(imageRef);
	}
	
	public Long findPositionId(Long id) {
		return imageRefDelegate.findPositionId(id);
	}

	@Override
	public void deleteById(ImageRef imageRef) {
		imageRefDelegate.deleteById(imageRef);
	}

	@Override
	public ImageRef deleteByMainIdAndSubId(ImageRef imageRef) {
		return imageRefDelegate.deleteByMainIdAndSubId(imageRef) ;
		
	}
}
