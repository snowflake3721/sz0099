package dml.sz0099.course.app.biz.delegate.article;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.article.PhotoService;
import dml.sz0099.course.app.persist.entity.article.Photo;

/**
 * photoServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class ArticlePhotoDelegateImpl implements ArticlePhotoDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ArticlePhotoDelegateImpl.class);
	
	@Autowired
	private PhotoService photoService;

	/**
	 * 根据Id查询Photo实体对象
	 * @param id
	 * @return
	 */
	@Override
	public Photo findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		Photo photo = photoService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, photo);
		return photo;
	}
	
	@Override
	public Photo findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		Photo photo = photoService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, photo);
		return photo;
	}
	
	/**
	 * 根据IdList查询Photo实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<Photo> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<Photo> photoList = photoService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  photoList);
		return photoList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public Photo persistEntity(Photo photo) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		Photo entity = photoService.persistEntity(photo);
		Long id = photo.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Photo mergeEntity(Photo photo) {
		Long id = photo.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		Photo entity = photoService.mergeEntity(photo);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Photo saveOrUpdate(Photo photo) {
		Long id = photo.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		Photo entity = photoService.saveOrUpdate(photo);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<Photo> findPage(Photo photo, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<Photo> page = photoService.findPage(photo, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return photoService.existById(id);
	}


	public void deleteByIdListAndUserId(List<Long> idList, Long userId ){		

		LOGGER.debug("-------delegate>>>PhotoDelegateImpl.deleteByIdListAndUserId----------begin---------");

		photoService.deleteByIdListAndUserId(idList,  userId );

		LOGGER.info("-------delegate>>>PhotoDelegateImpl.deleteByIdListAndUserId----------end---------");

	}


}
