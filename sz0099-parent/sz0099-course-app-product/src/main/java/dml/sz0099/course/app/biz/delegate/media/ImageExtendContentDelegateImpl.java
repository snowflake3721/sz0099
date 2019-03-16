package dml.sz0099.course.app.biz.delegate.media;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.media.ImageExtendContentService;
import dml.sz0099.course.app.persist.entity.media.ImageExtendContent;

/**
 * imageExtendContentServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class ImageExtendContentDelegateImpl implements ImageExtendContentDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageExtendContentDelegateImpl.class);
	
	@Autowired
	private ImageExtendContentService imageExtendContentService;

	/**
	 * 根据Id查询ImageExtendContent实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ImageExtendContent findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		ImageExtendContent imageExtendContent = imageExtendContentService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, imageExtendContent);
		return imageExtendContent;
	}
	
	@Override
	public ImageExtendContent findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		ImageExtendContent imageExtendContent = imageExtendContentService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, imageExtendContent);
		return imageExtendContent;
	}
	
	/**
	 * 根据IdList查询ImageExtendContent实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ImageExtendContent> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<ImageExtendContent> imageExtendContentList = imageExtendContentService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  imageExtendContentList);
		return imageExtendContentList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public ImageExtendContent persistEntity(ImageExtendContent imageExtendContent) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		ImageExtendContent entity = imageExtendContentService.persistEntity(imageExtendContent);
		Long id = imageExtendContent.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ImageExtendContent mergeEntity(ImageExtendContent imageExtendContent) {
		Long id = imageExtendContent.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		ImageExtendContent entity = imageExtendContentService.mergeEntity(imageExtendContent);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ImageExtendContent saveOrUpdate(ImageExtendContent imageExtendContent) {
		Long id = imageExtendContent.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		ImageExtendContent entity = imageExtendContentService.saveOrUpdate(imageExtendContent);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ImageExtendContent> findPage(ImageExtendContent imageExtendContent, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<ImageExtendContent> page = imageExtendContentService.findPage(imageExtendContent, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return imageExtendContentService.existById(id);
	}
}
