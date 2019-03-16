package dml.sz0099.course.app.biz.delegate.media;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.media.ImagebaseService;
import dml.sz0099.course.app.persist.entity.media.Imagebase;

/**
 * imagebaseServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class ImagebaseDelegateImpl implements ImagebaseDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImagebaseDelegateImpl.class);
	
	@Autowired
	private ImagebaseService imagebaseService;

	/**
	 * 根据Id查询Imagebase实体对象
	 * @param id
	 * @return
	 */
	@Override
	public Imagebase findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		Imagebase imagebase = imagebaseService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, imagebase);
		return imagebase;
	}
	
	@Override
	public Imagebase findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		Imagebase imagebase = imagebaseService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, imagebase);
		return imagebase;
	}
	
	/**
	 * 根据IdList查询Imagebase实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<Imagebase> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<Imagebase> imagebaseList = imagebaseService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  imagebaseList);
		return imagebaseList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public Imagebase persistEntity(Imagebase imagebase) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		Imagebase entity = imagebaseService.persistEntity(imagebase);
		Long id = imagebase.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Imagebase mergeEntity(Imagebase imagebase) {
		Long id = imagebase.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		Imagebase entity = imagebaseService.mergeEntity(imagebase);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Imagebase saveOrUpdate(Imagebase imagebase) {
		Long id = imagebase.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		Imagebase entity = imagebaseService.saveOrUpdate(imagebase);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<Imagebase> findPage(Imagebase imagebase, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<Imagebase> page = imagebaseService.findPage(imagebase, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return imagebaseService.existById(id);
	}
}
