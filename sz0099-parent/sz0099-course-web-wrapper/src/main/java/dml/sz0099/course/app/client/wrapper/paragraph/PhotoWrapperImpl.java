package dml.sz0099.course.app.client.wrapper.paragraph;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.paragraph.PhotoDelegate;
import dml.sz0099.course.app.persist.entity.paragraph.Photo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * PhotoWrapperImpl,组件封装
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
public class PhotoWrapperImpl implements PhotoWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PhotoWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private PhotoDelegate photoDelegate;
	
	/**
	 * 根据Id查询Photo实体对象
	 * @param id
	 * @return
	 */
	@Override
	public Photo findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		Photo photo = photoDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, photo);
		return photo;
	}
	
	@Override
	public Photo findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		Photo photo = photoDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, photo);
		return photo;
	}
	
	/**
	 * 根据IdList查询Photo实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<Photo> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<Photo> photoList = photoDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  photoList);
		return photoList;
	}
	
	@Override
	public Photo persistEntity(Photo photo) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		Photo entity = photoDelegate.persistEntity(photo);
		Long id = photo.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Photo mergeEntity(Photo photo) {
		Long id = photo.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		Photo entity = photoDelegate.mergeEntity(photo);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Photo saveOrUpdate(Photo photo) {
		Long id = photo.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		Photo entity = photoDelegate.saveOrUpdate(photo);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<Photo> findPage(Photo photo, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<Photo> page = photoDelegate.findPage(photo, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return photoDelegate.existById(id);
	}


	public void deleteByIdListAndUserId(List<Long> idList, Long userId  ){		

		LOGGER.debug("-------wrapper>>>PhotoWrapperImpl.deleteByIdListAndUserId----------begin---------");

		photoDelegate.deleteByIdListAndUserId( idList , userId);


	}


}
