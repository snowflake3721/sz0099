package dml.sz0099.course.app.biz.delegate.media;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.media.ImageRefService;
import dml.sz0099.course.app.persist.entity.media.ImageRef;

/**
 * imageRefServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class ImageRefDelegateImpl implements ImageRefDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageRefDelegateImpl.class);
	
	@Autowired
	private ImageRefService imageRefService;

	/**
	 * 根据Id查询ImageRef实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ImageRef findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		ImageRef imageRef = imageRefService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, imageRef);
		return imageRef;
	}
	
	@Override
	public ImageRef findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		ImageRef imageRef = imageRefService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, imageRef);
		return imageRef;
	}
	
	/**
	 * 根据IdList查询ImageRef实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ImageRef> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<ImageRef> imageRefList = imageRefService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  imageRefList);
		return imageRefList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public ImageRef persistEntity(ImageRef imageRef) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		ImageRef entity = imageRefService.persistEntity(imageRef);
		Long id = imageRef.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ImageRef mergeEntity(ImageRef imageRef) {
		Long id = imageRef.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		ImageRef entity = imageRefService.mergeEntity(imageRef);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ImageRef saveOrUpdate(ImageRef imageRef) {
		Long id = imageRef.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		ImageRef entity = imageRefService.saveOrUpdate(imageRef);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ImageRef> findPage(ImageRef imageRef, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<ImageRef> page = imageRefService.findPage(imageRef, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return imageRefService.existById(id);
	}
	
	public Long countForMain(ImageRef imageRef) {
		return imageRefService.countForMain(imageRef);
	}
	
	public Long countForSub(ImageRef imageRef) {
		return imageRefService.countForSub(imageRef);
	}
	
	public Long countForBase(ImageRef imageRef) {
		return imageRefService.countForBase(imageRef);
	}

	@Override
	public Long findPositionId(Long id) {
		return imageRefService.findPositionId(id);
	}
	
	@Override
	public ImageRef mergeForTitle(ImageRef imageRef) {
		return imageRefService.mergeForTitle(imageRef);
	}
	@Override
	public void deleteById(ImageRef imageRef) {
		imageRefService.deleteById(imageRef);
	}
	
	public ImageRef deleteByMainIdAndSubId(ImageRef imageRef) {
		return imageRefService.deleteByMainIdAndSubId(imageRef) ;
		
	}
}
