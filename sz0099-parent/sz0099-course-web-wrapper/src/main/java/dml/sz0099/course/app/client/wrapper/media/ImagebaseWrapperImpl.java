package dml.sz0099.course.app.client.wrapper.media;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.jit4j.core.pub.transfer.dto.ImageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.media.ImagebaseDelegate;
import dml.sz0099.course.app.persist.entity.media.Imagebase;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ImagebaseWrapperImpl,组件封装
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
public class ImagebaseWrapperImpl implements ImagebaseWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ImagebaseWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private ImagebaseDelegate imagebaseDelegate;
	
	/**
	 * 根据Id查询Imagebase实体对象
	 * @param id
	 * @return
	 */
	@Override
	public Imagebase findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		Imagebase imagebase = imagebaseDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, imagebase);
		return imagebase;
	}
	
	@Override
	public Imagebase findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		Imagebase imagebase = imagebaseDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, imagebase);
		return imagebase;
	}
	
	/**
	 * 根据IdList查询Imagebase实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<Imagebase> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<Imagebase> imagebaseList = imagebaseDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  imagebaseList);
		return imagebaseList;
	}
	
	@Override
	public Imagebase persistEntity(Imagebase imagebase) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		Imagebase entity = imagebaseDelegate.persistEntity(imagebase);
		Long id = imagebase.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Imagebase mergeEntity(Imagebase imagebase) {
		Long id = imagebase.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		Imagebase entity = imagebaseDelegate.mergeEntity(imagebase);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Imagebase saveOrUpdate(Imagebase imagebase) {
		Long id = imagebase.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		Imagebase entity = imagebaseDelegate.saveOrUpdate(imagebase);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<Imagebase> findPage(Imagebase imagebase, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<Imagebase> page = imagebaseDelegate.findPage(imagebase, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return imagebaseDelegate.existById(id);
	}
	
	
	
	public Imagebase persistImage(ImageRequest request) {
		
		Imagebase imagebase = new Imagebase();
		String devId = request.getDevId();
		
		LOGGER.debug(devId);
		
		
		return imagebase;
	}
}
