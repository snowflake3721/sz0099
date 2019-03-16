package dml.sz0099.course.app.biz.delegate.position;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.position.PositionImageService;
import dml.sz0099.course.app.persist.entity.position.PositionImage;

/**
 * positionImageServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class PositionImageDelegateImpl implements PositionImageDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PositionImageDelegateImpl.class);
	
	@Autowired
	private PositionImageService positionImageService;

	/**
	 * 根据Id查询PositionImage实体对象
	 * @param id
	 * @return
	 */
	@Override
	public PositionImage findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		PositionImage positionImage = positionImageService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, positionImage);
		return positionImage;
	}
	
	@Override
	public PositionImage findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		PositionImage positionImage = positionImageService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, positionImage);
		return positionImage;
	}
	
	/**
	 * 根据IdList查询PositionImage实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<PositionImage> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<PositionImage> positionImageList = positionImageService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  positionImageList);
		return positionImageList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public PositionImage persistEntity(PositionImage positionImage) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		PositionImage entity = positionImageService.persistEntity(positionImage);
		Long id = positionImage.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PositionImage mergeEntity(PositionImage positionImage) {
		Long id = positionImage.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		PositionImage entity = positionImageService.mergeEntity(positionImage);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PositionImage saveOrUpdate(PositionImage positionImage) {
		Long id = positionImage.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		PositionImage entity = positionImageService.saveOrUpdate(positionImage);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PositionImage> findPage(PositionImage positionImage, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<PositionImage> page = positionImageService.findPage(positionImage, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return positionImageService.existById(id);
	}
	
	public List<PositionImage> findByRefId(Long refId) {
		return positionImageService.findByRefId(refId);
	}
}
