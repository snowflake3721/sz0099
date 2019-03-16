package dml.sz0099.course.app.client.wrapper.position;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.position.PositionImageDelegate;
import dml.sz0099.course.app.persist.entity.position.PositionImage;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * PositionImageWrapperImpl,组件封装
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
public class PositionImageWrapperImpl implements PositionImageWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PositionImageWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private PositionImageDelegate positionImageDelegate;
	
	/**
	 * 根据Id查询PositionImage实体对象
	 * @param id
	 * @return
	 */
	@Override
	public PositionImage findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		PositionImage positionImage = positionImageDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, positionImage);
		return positionImage;
	}
	
	@Override
	public PositionImage findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		PositionImage positionImage = positionImageDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, positionImage);
		return positionImage;
	}
	
	/**
	 * 根据IdList查询PositionImage实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<PositionImage> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<PositionImage> positionImageList = positionImageDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  positionImageList);
		return positionImageList;
	}
	
	@Override
	public PositionImage persistEntity(PositionImage positionImage) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		PositionImage entity = positionImageDelegate.persistEntity(positionImage);
		Long id = positionImage.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PositionImage mergeEntity(PositionImage positionImage) {
		Long id = positionImage.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		PositionImage entity = positionImageDelegate.mergeEntity(positionImage);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PositionImage saveOrUpdate(PositionImage positionImage) {
		Long id = positionImage.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		PositionImage entity = positionImageDelegate.saveOrUpdate(positionImage);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PositionImage> findPage(PositionImage positionImage, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<PositionImage> page = positionImageDelegate.findPage(positionImage, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return positionImageDelegate.existById(id);
	}
	
	public List<PositionImage> findByRefId(Long refId) {
		return positionImageDelegate.findByRefId(refId);
	}
}
